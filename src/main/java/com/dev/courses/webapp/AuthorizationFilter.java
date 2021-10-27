package com.dev.courses.webapp;

import com.dev.courses.api.AuthenticationData;
import com.dev.courses.security.SecurityService;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.core.Response;

import java.io.IOException;
import java.util.Arrays;


@WebFilter(urlPatterns = {"/api/v1/courses", "/api/v1/courses/*"})
public class AuthorizationFilter implements Filter {


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        String authorization = httpRequest.getHeader("Authorization");

        boolean responseSent = false;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        if (authorization == null) {
            httpResponse.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing auth.");
            responseSent = true;

        } else {
            String[] authorizationSplit = authorization.split(" ");

            if (authorizationSplit.length != 2) {
                httpResponse.sendError(HttpServletResponse.SC_BAD_REQUEST, "Wrong auth info.");
                responseSent = true;

            } else {
                if ( !authorizationSplit[0].equalsIgnoreCase("token") ) {
                    httpResponse.sendError(HttpServletResponse.SC_BAD_REQUEST, "Wrong auth info.");
                    responseSent = true;

                } else {
                    ServletContext servletContext = servletRequest.getServletContext();
                    SecurityService securityService = (SecurityService) servletContext.getAttribute(CoursesContextListener.SECURITY_SERVICE);
                    AuthenticationData authenticationSession = securityService.getAuthenticationSession( authorizationSplit[1] );

                    if (authenticationSession != null) {
                        filterChain.doFilter(servletRequest, servletResponse);
                        responseSent = true;

                    } else {
                        httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "TOKEN not registered");
                        responseSent = true;

                    }
                }
            }
        }

        if (!responseSent) {
            httpResponse.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Case not implemented");
        }
    }

}
