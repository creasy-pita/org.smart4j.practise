package org.smart4j.framework;

import org.smart4j.framework.bean.*;
import org.smart4j.framework.helper.BeanHelper;
import org.smart4j.framework.helper.ConfigHelper;
import org.smart4j.framework.helper.ControllerHelper;
import org.smart4j.framework.helper.ServletHelper;
import org.smart4j.framwork.util.JsonUtil;
import org.smart4j.framwork.util.ReflectionUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

/**
 * Created by creasypita on 8/22/2019.
 */
@WebServlet("/*")
public class DispatcherServlet extends HttpServlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        HelperLoader.init();
        ServletContext servletContext = servletConfig.getServletContext();

        registerServlet(servletContext);
    }

    private void registerServlet(ServletContext servletContext) {
        ServletRegistration jspServlet = servletContext.getServletRegistration("jsp");
        jspServlet.addMapping("/index.jsp");
        jspServlet.addMapping(ConfigHelper.getAppJspPath() + "*");

        ServletRegistration defaultServlet = servletContext.getServletRegistration("default");
        defaultServlet.addMapping("/favicon.ico");
        //defaultServlet.addMapping(ConfigHelper.getAppAssetPath() + "*");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletHelper.init(req, resp);
        String requestMethod = req.getMethod().toLowerCase();
        String requestPath = req.getPathInfo();
        Handler handler = ControllerHelper.getHandler(requestMethod, requestPath);
        if(handler != null)
        {
            Class<?> controllerClass = handler.getControllerClass();
            Object controllerBean = BeanHelper.getBean(controllerClass);
            //compose action method params from httpservletrequest
            Param param = GetParamsFromHttpRequest(req);
            Object[] params = {param};
            Method actionMethod = handler.getActionMethod();
            //View type : redirect to another controller | forward to a view of jsp language | json data
            Object result = ReflectionUtil.invokeMethod(controllerBean, actionMethod, params);
            if(result instanceof View)
            {
                View view = (View)result;
                String path = view.getPath();
                if(view.getPath().startsWith("/"))
                {
                    resp.sendRedirect(req.getContextPath()+ path);
                }
                else
                {
                    Map<String,Object> map = view.getModel();
                    for (Map.Entry<String, Object> entry: map.entrySet()) {
                        req.setAttribute(entry.getKey(), entry.getValue());
                    }
                    req.getRequestDispatcher(ConfigHelper.getAppJspPath()+ path).forward(req, resp);
                }
            }
            else if(result instanceof Data)
            {
                Object object = ((Data) result).getModel();
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");
                PrintWriter writer = resp.getWriter();
                String jsonData = JsonUtil.toJson(object);
                writer.print(jsonData);
                writer.flush();
                writer.close();
            }
        }



    }

    private Param GetParamsFromHttpRequest(HttpServletRequest request)
    {
        Enumeration<String> es = request.getParameterNames();
        List<FormParam> formParams = new ArrayList<FormParam>();
        while(es.hasMoreElements())
        {
            String pName = es.nextElement();
            formParams.add(new FormParam(pName, request.getParameter(pName)));
        }
        return new Param(formParams);
    }
}
