package com.dev.courses.endpoint;

import com.dev.courses.api.AuthenticationData;
import com.dev.courses.security.SecurityService;
import com.dev.courses.webapp.CoursesContextListener;
import jakarta.servlet.ServletContext;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;

/**
 * Root resource (exposed at "courses" path)
 */
@Path("courses")
public class CourseRestEndpoint {

    @Context
    ServletContext servletContext;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String list(@HeaderParam("Authorization") String authorization) {
        String[] authorizationSplit = authorization.split(" ");
        String result = "Server Error";

        if (authorizationSplit.length >= 2) {

            if (authorizationSplit[0].equalsIgnoreCase("token")) {
                SecurityService securityService = (SecurityService) servletContext.getAttribute(CoursesContextListener.SECURITY_SERVICE);
                AuthenticationData authenticationSession = securityService.getAuthenticationSession(authorizationSplit[1]);
                if (authenticationSession != null) {
                    result = "Curso 1\nCurso 2\nCurso 3";
                } else {
                    result = "Not authorized";
                }
            }

        }

        return result;
    }

}
