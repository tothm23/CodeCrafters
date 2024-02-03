package com.codecrafterswebshop.Controller;

import com.codecrafterswebshop.Model.Basket;
import com.codecrafterswebshop.Service.BasketService;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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

    @GET
    @Path("{userId}")
    public Response userBasket(@PathParam("userId") Integer id) {
        JSONArray result = BasketService.userBasket(id);
        return Response.status(Response.Status.OK).entity(result.toString())
                .type(MediaType.APPLICATION_JSON).build();
    }

    @POST
    public Response basket(Basket k) {
        String result = BasketService.basket(k.getGameId(), k.getUserId());
        return Response.status(Response.Status.OK).entity(result)
                .type(MediaType.APPLICATION_JSON).build();
    }

    @DELETE
    public Response deleteGameBasket(@QueryParam("userId") Integer userId, @QueryParam("gameId") Integer gameId) {
        String result = BasketService.deleteGameBasket(userId, gameId);
        return Response.status(Response.Status.OK).entity(result)
                .type(MediaType.APPLICATION_JSON).build();
    }
}
