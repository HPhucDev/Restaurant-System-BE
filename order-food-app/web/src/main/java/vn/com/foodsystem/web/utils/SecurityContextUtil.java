package vn.com.foodsystem.web.utils;

import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityContextUtil {

    private SecurityContextUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static Object getUserDetails() {
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
