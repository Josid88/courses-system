package com.dev.courses.webapp;

import com.dev.courses.security.SecurityService;
import com.dev.courses.security.SecurityServiceHardcodedImpl;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class CoursesContextListener implements ServletContextListener {

    public static final String SECURITY_SERVICE = "SecurityService";

    @Override
    public void contextInitialized(ServletContextEvent context) {
        SecurityService securityService = new SecurityServiceHardcodedImpl();
        context.getServletContext().setAttribute(SECURITY_SERVICE, securityService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

}
