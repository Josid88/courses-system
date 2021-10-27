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

import java.util.Arrays;


/**
 * Root resource (exposed at "courses" path)
 */
@Path("courses")
public class CourseRestEndpoint {

    @Context
    ServletContext servletContext;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response list() {
        return Response.ok(Arrays.asList(
                "Curso 1",
                "Curso 2",
                "Curso 3"
        )).build();
    }

    @GET
    @Path("/main")
    @Produces(MediaType.APPLICATION_JSON)
    public Response mainList() {
        return Response.ok(Arrays.asList(
                "Curso a",
                "Curso b",
                "Curso c"
        )).build();
    }

}
