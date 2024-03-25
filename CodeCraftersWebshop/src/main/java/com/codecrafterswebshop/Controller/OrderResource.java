package com.codecrafterswebshop.Controller;

import com.codecrafterswebshop.Service.TokenService;
import com.codecrafterswebshop.Service.OrderService;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * REST Web Service
 *
 * @author tothm23
 */
@Path("order")
@Consumes(MediaType.APPLICATION_JSON)
public class OrderResource {

    @Context
    private UriInfo uriInfo;
    @Context
    private HttpHeaders headers;
    private Logger logger;
    private String time;

    public OrderResource() {
        this.logger = LogManager.getLogger(GameResource.class.getName());
        this.time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    @POST
    @Path("{chargeId}")
    public Response order(@PathParam("chargeId") String chargeId) {
        Response userResponse = Response
                .status(Response.Status.UNAUTHORIZED)
                .entity("Minden felhasználó csak a saját termékeit rendelheti meg!")
                .type(MediaType.TEXT_PLAIN).build();

        Response unauthorized = Response
                .status(Response.Status.UNAUTHORIZED)
                .entity("Hozzáférés megtagadva!")
                .type(MediaType.TEXT_PLAIN).build();

        int id = TokenService.decodeUser(headers);

        if (id == -1) {
            return unauthorized;
        }

        TokenService.filterUser(userResponse, headers, id);

        String result = OrderService.order(id, chargeId);
        Response response = Response.status(Response.Status.CREATED).entity(result)
                .type(MediaType.APPLICATION_JSON).build();

        logger.log(Level.INFO, time + "  <--  [" + response.getStatus() + "] POST " + uriInfo.getPath());
        return response;
    }

}
