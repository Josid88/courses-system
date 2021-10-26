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
import jakarta.ws.rs.core.Response;
import org.glassfish.jersey.internal.guava.Lists;

import java.util.Arrays;
import java.util.List;

/**
 * Root resource (exposed at "courses" path)
 */
@Path("courses")
public class CourseRestEndpoint {

    @Context
    ServletContext servletContext;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response list(@HeaderParam("Authorization") String authorization) {
        Response.ResponseBuilder responseBuilder = Response.serverError();
        if (authorization == null) {
            responseBuilder = Response.status(Response.Status.BAD_REQUEST)
                    .entity("Missing auth.");
        } else {
            String[] authorizationSplit = authorization.split(" ");
            if (authorizationSplit.length != 2) {
                responseBuilder = Response.status(Response.Status.BAD_REQUEST.getStatusCode())
                        .entity("Wrong auth info.");
            } else {
                if ( !authorizationSplit[0].equalsIgnoreCase("token") ) {
                    responseBuilder = Response.status(Response.Status.BAD_REQUEST.getStatusCode())
                            .entity("Wrong auth info.");
                } else {
                    SecurityService securityService = (SecurityService) servletContext.getAttribute(CoursesContextListener.SECURITY_SERVICE);
                    AuthenticationData authenticationSession = securityService.getAuthenticationSession( authorizationSplit[1] );
                    if (authenticationSession != null) {
                        responseBuilder = Response.ok(Arrays.asList(
                                "Curso 1",
                                "Curso 2",
                                "Curso 3"
                        ));
                    } else {
                        responseBuilder = Response.status(Response.Status.UNAUTHORIZED)
                                .entity("token not registered");
                    }
                }
            }
        }
        return responseBuilder.build();
    }

}
