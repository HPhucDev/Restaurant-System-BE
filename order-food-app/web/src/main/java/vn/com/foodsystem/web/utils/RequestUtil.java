package vn.com.foodsystem.web.utils;

import javax.servlet.http.HttpServletRequest;

public class RequestUtil {

    private RequestUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static String getServerInfo(HttpServletRequest request) {
        String scheme = request.getScheme();
        String host = request.getServerName();
        Integer port = request.getServerPort();
        String contextPath = request.getContextPath();

        String s = String.format("%s://%s:%s%s", scheme, host, port, contextPath);
        return s;
    }
}
