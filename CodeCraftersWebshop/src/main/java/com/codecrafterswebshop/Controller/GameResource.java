package com.codecrafterswebshop.Controller;

import com.codecrafterswebshop.Model.Game;
import com.codecrafterswebshop.Service.GameService;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONObject;

/**
 * REST Web Service
 *
 * @author tothm23
 */
@Path("game")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GameResource {

    @GET
    @Path("{gameId}")
    public Response game(@PathParam("gameId") Integer id) {
        JSONObject result = GameService.game(id);
        return Response.status(Response.Status.OK).entity(result.toString())
                .type(MediaType.APPLICATION_JSON).build();
    }

    @POST
    public Response newGame(Game g) {
        String result = GameService.newGame(g.getName(), g.getPrice(),
                g.getDescription(), g.getImage(), g.getAgeLimit(),
                g.getDiscount(), g.getInStock(),
                g.getDeviceId(), g.getPlatformId()
        );
        return Response.status(Response.Status.CREATED).entity(result)
                .type(MediaType.APPLICATION_JSON).build();
    }

    @PUT
    @Path("{gameId}")
    public Response updateGame(Game g, @PathParam("gameId") Integer id) {
        JSONObject result = GameService.game(id);
        String update = GameService.updateGame(id, g.getName(), g.getPrice(),
                g.getDescription(), g.getImage(), g.getAgeLimit(),
                g.getDiscount(), g.getInStock(),
                g.getDeviceId(), g.getPlatformId()
        );

        return result.length() == 0 ? Response.status(Response.Status.NOT_FOUND).entity(update)
                .type(MediaType.APPLICATION_JSON).build() : Response.status(Response.Status.OK).entity(update)
                .type(MediaType.APPLICATION_JSON).build();
    }

    @DELETE
    @Path("{gameId}")
    public Response deleteGame(@PathParam("gameId") Integer id) {
        JSONObject result = GameService.game(id);
        String delete = GameService.deleteGame(id);

        return result.length() == 0 ? Response.status(Response.Status.NOT_FOUND).entity(delete)
                .type(MediaType.APPLICATION_JSON).build() : Response.status(Response.Status.OK).entity(delete)
                .type(MediaType.APPLICATION_JSON).build();
    }
}
