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
    private static final String URL_PREFIX = "termekek";

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        Response jogosulatlan = Response
                .status(Response.Status.UNAUTHORIZED)
                .entity("Hozzáférés megtagadva")
                .type(MediaType.TEXT_PLAIN).build();

        if (requestContext.getUriInfo().getPath().contains(URL_PREFIX)) {

            List<String> authFejlec = requestContext.getHeaders().get(AUTH_HEADER);

            if (authFejlec != null && !authFejlec.isEmpty()) {

                String authToken = authFejlec.get(0);
                authToken = authToken.replaceFirst(AUTH_HEADER_PREFIX, "");

                if (Token.dekodolas(authToken) == 1) {
                    return;
                } else if (Token.dekodolas(authToken) == 2) {
                    requestContext.abortWith(jogosulatlan);
                }

            } else {
                requestContext.abortWith(jogosulatlan);
            }

        }

    }
}
