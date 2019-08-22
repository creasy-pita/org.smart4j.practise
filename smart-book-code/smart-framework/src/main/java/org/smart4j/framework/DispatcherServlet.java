package org.smart4j.framework;

import org.smart4j.framework.bean.*;
import org.smart4j.framework.helper.BeanHelper;
import org.smart4j.framework.helper.ControllerHelper;
import org.smart4j.framwork.util.ReflectionUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
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
public class DispatcherServlet extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        HelperLoader.init();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestMethod = req.getMethod();
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
                if(view.getPath().startsWith("/"))
                {
                    resp.sendRedirect(req.getContextPath()+ view.getPath());
                }
                else
                {
                    Map<String,Object> map = view.getModel();
                    for (Map.Entry<String, Object> entry: map.entrySet()) {
                        req.setAttribute(entry.getKey(), entry.getValue());
                    }
                    req.getRequestDispatcher(view.getPath());
                }
            }
            else if(result instanceof Data)
            {
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");
                PrintWriter writer = resp.getWriter();
                String jsonData = "";//TBD Object convert to json data
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
