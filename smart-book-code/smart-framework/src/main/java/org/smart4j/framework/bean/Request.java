package org.smart4j.framework.bean;

/**
 * Created by creasypita on 8/21/2019.
 */
public class Request {
    private String requestMethod;

    public Request(String requestMethod, String requestPath) {
        this.requestMethod = requestMethod;
        this.requestPath = requestPath;
    }

    public String getRequestMethod() {
        return requestMethod;
    }


    public String getRequestPath() {
        return requestPath;
    }

    private String requestPath;


}
