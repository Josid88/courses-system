package com.dev.courses.endpoint;

import com.dev.courses.api.LoginData;
import com.dev.courses.api.AuthenticationData;
import com.dev.courses.security.AuthenticationStatus;
import com.dev.courses.security.SecurityService;
import com.dev.courses.security.SecurityServiceHardcodedImpl;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path("security")
public class SecurityRestEndpoint {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String login(
            @QueryParam("username") String username,
            @QueryParam("password") String password) {

        SecurityService securityService = new SecurityServiceHardcodedImpl();
        AuthenticationData result = securityService.authenticate(username, password);

        return result.toString();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(LoginData loginData) {

        SecurityService securityService = new SecurityServiceHardcodedImpl();
        AuthenticationData result = securityService.authenticate(loginData.getUsername(), loginData.getPassword());

        Response.ResponseBuilder responseBuilder = Response.serverError();
        if (result.getStatus() == AuthenticationStatus.AUTHENTICATED ) {
            responseBuilder = Response.ok(result);
        } else {
            responseBuilder = Response.status(Response.Status.BAD_REQUEST)
                    .entity(result);
        }

        return responseBuilder.build();
    }

}
