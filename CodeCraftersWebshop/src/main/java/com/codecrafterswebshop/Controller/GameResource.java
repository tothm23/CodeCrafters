package com.codecrafterswebshop.Controller;

import com.codecrafterswebshop.Service.TokenService;
import com.codecrafterswebshop.Model.Game;
import com.codecrafterswebshop.Service.GameService;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    @Context
    private UriInfo uriInfo;
    @Context
    private HttpHeaders headers;
    private Logger logger;
    private String time;
    private Response unauthorized;

    public GameResource() {
        this.logger = LogManager.getLogger(GameResource.class.getName());
        this.time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        this.unauthorized = Response
                .status(Response.Status.UNAUTHORIZED)
                .entity("Hozzáférés megtagadva!")
                .type(MediaType.TEXT_PLAIN).build();
    }

    @GET
    @Path("{gameId}")
    public Response game(@PathParam("gameId") Integer id) {
        JSONObject result = GameService.game(id);
        Response response = Response.status(Response.Status.OK).entity(result.toString())
                .type(MediaType.APPLICATION_JSON).build();

        logger.log(Level.INFO, time + "  <--  [" + response.getStatus() + "] GET " + uriInfo.getPath());
        return response;
    }

    @POST
    public Response newGame(Game g) {
        Response notAdmin = Response
                .status(Response.Status.UNAUTHORIZED)
                .entity("Játékot csak adminisztrátor hozhat létre!")
                .type(MediaType.TEXT_PLAIN).build();

        TokenService.filterAdmin(notAdmin, headers);

        String result = GameService.newGame(g.getName(), g.getPrice(),
                g.getDescription(), g.getImage(), g.getAgeLimit(),
                g.getDiscount(), g.getInStock(),
                g.getDeviceId(), g.getPlatformId()
        );
        Response response = Response.status(Response.Status.CREATED).entity(result)
                .type(MediaType.APPLICATION_JSON).build();

        logger.log(Level.INFO, time + "  <--  [" + response.getStatus() + "] POST " + uriInfo.getPath());
        return response;
    }

    @PUT
    @Path("{gameId}")
    public Response updateGame(Game g, @PathParam("gameId") Integer id) {
        Response notAdmin = Response
                .status(Response.Status.UNAUTHORIZED)
                .entity("Játékot csak adminisztrátor szerkeszthet!")
                .type(MediaType.TEXT_PLAIN).build();

        TokenService.filterAdmin(notAdmin, headers);

        JSONObject result = GameService.game(id);
        String update = GameService.updateGame(id, g.getName(), g.getPrice(),
                g.getDescription(), g.getImage(), g.getAgeLimit(),
                g.getDiscount(), g.getInStock(),
                g.getDeviceId(), g.getPlatformId()
        );

        Response response = result.length() == 0 ? Response.status(Response.Status.NOT_FOUND).entity(update)
                .type(MediaType.APPLICATION_JSON).build() : Response.status(Response.Status.OK).entity(update)
                .type(MediaType.APPLICATION_JSON).build();

        logger.log(Level.INFO, time + "  <--  [" + response.getStatus() + "] PUT " + uriInfo.getPath());
        return response;
    }

    @DELETE
    @Path("{gameId}")
    public Response deleteGame(@PathParam("gameId") Integer id) {
        Response notAdmin = Response
                .status(Response.Status.UNAUTHORIZED)
                .entity("Játékot csak adminisztrátor törölhet!")
                .type(MediaType.TEXT_PLAIN).build();

        TokenService.filterAdmin(notAdmin, headers);

        JSONObject result = GameService.game(id);
        String delete = GameService.deleteGame(id);
        Response response = result.length() == 0 ? Response.status(Response.Status.NOT_FOUND).entity(delete)
                .type(MediaType.APPLICATION_JSON).build() : Response.status(Response.Status.OK).entity(delete)
                .type(MediaType.APPLICATION_JSON).build();

        logger.log(Level.INFO, time + "  <--  [" + response.getStatus() + "] DELETE " + uriInfo.getPath());
        return response;
    }
}
