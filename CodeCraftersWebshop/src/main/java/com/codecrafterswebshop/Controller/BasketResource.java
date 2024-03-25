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
    private Response unauthorized;

    public BasketResource() {
        this.logger = LogManager.getLogger(GameResource.class.getName());
        this.time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        this.unauthorized = Response
                .status(Response.Status.UNAUTHORIZED)
                .entity("Hozzáférés megtagadva!")
                .type(MediaType.TEXT_PLAIN).build();
    }

    @GET
    public Response userBasket() {
        int id = TokenService.decodeUser(headers);

        if (id == -1) {
            return unauthorized;
        }

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

        int id = TokenService.decodeUser(headers);

        if (id == -1) {
            return unauthorized;
        }

        TokenService.filterUser(userResponse, headers, id);

        String result = BasketService.basket(k.getGameId(), id);
        Response response = Response.status(Response.Status.OK).entity(result)
                .type(MediaType.APPLICATION_JSON).build();

        logger.log(Level.INFO, time + "  <--  [" + response.getStatus() + "] POST " + uriInfo.getPath());
        return response;
    }

    @DELETE
    public Response deleteGameBasket(@QueryParam("gameId") Integer gameId) {
        Response userResponse = Response
                .status(Response.Status.UNAUTHORIZED)
                .entity("Minden felhasználó csak a saját kosarából törölhet terméket!")
                .type(MediaType.TEXT_PLAIN).build();

        int id = TokenService.decodeUser(headers);

        if (id == -1) {
            return unauthorized;
        }

        TokenService.filterUser(userResponse, headers, id);

        String result = BasketService.deleteGameBasket(id, gameId);
        Response response = Response.status(Response.Status.OK).entity(result)
                .type(MediaType.APPLICATION_JSON).build();

        logger.log(Level.INFO, time + "  <--  [" + response.getStatus() + "] DELETE " + uriInfo.getPath());
        return response;
    }
}
