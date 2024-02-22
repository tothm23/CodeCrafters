package com.codecrafterswebshop.Config;

import java.io.IOException;
import java.util.List;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author tothm23
 */
@Provider
public class TokenFilter implements ContainerRequestFilter {

    private static final String AUTH_HEADER = "Authorization";
    private static final String AUTH_HEADER_PREFIX = "Bearer ";

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        Response unauthorized = Response
                .status(Response.Status.UNAUTHORIZED)
                .entity("Hozzáférés megtagadva!")
                .type(MediaType.TEXT_PLAIN).build();

        Response notAdmin = Response
                .status(Response.Status.UNAUTHORIZED)
                .entity("Játékot csak adminisztrátor szerkeszthet!")
                .type(MediaType.TEXT_PLAIN).build();

        if ((requestContext.getUriInfo().getPath().contains("game") && requestContext.getMethod().equals("POST"))
                || (requestContext.getUriInfo().getPath().contains("game") && requestContext.getMethod().equals("PUT"))
                || (requestContext.getUriInfo().getPath().contains("game") && requestContext.getMethod().equals("DELETE"))) {

            List<String> authHeader = requestContext.getHeaders().get(AUTH_HEADER);

            if (authHeader != null && !authHeader.isEmpty()) {

                String authToken = authHeader.get(0);
                authToken = authToken.replaceFirst(AUTH_HEADER_PREFIX, "");

                if (Token.decodeAdmin(authToken) == 0) {
                    requestContext.abortWith(notAdmin);
                }

            } else {
                requestContext.abortWith(unauthorized);
            }

        }

    }
}
