package com.codecrafterswebshop.Controller;

import com.codecrafterswebshop.Service.TokenService;
import com.codecrafterswebshop.Model.Basket;
import com.codecrafterswebshop.Service.BasketService;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;

/**
 * REST Web Service
 *
 * @author tothm23
 */
@Path("basket")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BasketResource {

    @Context
    private UriInfo uriInfo;
    @Context
    private HttpHeaders headers;
    private Logger logger;
    private String time;

    public BasketResource() {
        this.logger = LogManager.getLogger(GameResource.class.getName());
        this.time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    @GET
    @Path("{userId}")
    public Response userBasket(@PathParam("userId") Integer id) {
        Response userResponse = Response
                .status(Response.Status.UNAUTHORIZED)
                .entity("Minden felhasználó csak a saját kosarát tekintheti meg!")
                .type(MediaType.TEXT_PLAIN).build();

        TokenService.filterUser(userResponse, headers, id);

        JSONArray result = BasketService.userBasket(id);
        Response response = result.isEmpty() ? Response.status(Response.Status.OK).entity("A kosár üres!")
                .type(MediaType.APPLICATION_JSON).build() : Response.status(Response.Status.OK).entity(result.toString())
                .type(MediaType.APPLICATION_JSON).build();

        logger.log(Level.INFO, time + "  <--  [" + response.getStatus() + "] GET " + uriInfo.getPath());
        return response;
    }

    @POST
    public Response basket(Basket k) {
        Response userResponse = Response
                .status(Response.Status.UNAUTHORIZED)
                .entity("Minden felhasználó csak a saját kosarához adhat hozzá terméket!")
                .type(MediaType.TEXT_PLAIN).build();

        TokenService.filterUser(userResponse, headers, k.getUserId());

        String result = BasketService.basket(k.getGameId(), k.getUserId());
        Response response = Response.status(Response.Status.OK).entity(result)
                .type(MediaType.APPLICATION_JSON).build();

        logger.log(Level.INFO, time + "  <--  [" + response.getStatus() + "] POST " + uriInfo.getPath());
        return response;
    }

    @DELETE
    public Response deleteGameBasket(@QueryParam("userId") Integer userId, @QueryParam("gameId") Integer gameId) {
        Response userResponse = Response
                .status(Response.Status.UNAUTHORIZED)
                .entity("Minden felhasználó csak a saját kosarából törölhet terméket!")
                .type(MediaType.TEXT_PLAIN).build();

        TokenService.filterUser(userResponse, headers, userId);

        String result = BasketService.deleteGameBasket(userId, gameId);
        Response response = Response.status(Response.Status.OK).entity(result)
                .type(MediaType.APPLICATION_JSON).build();

        logger.log(Level.INFO, time + "  <--  [" + response.getStatus() + "] DELETE " + uriInfo.getPath());
        return response;
    }
}
