package com.codecrafterswebshop.Controller;

import com.codecrafterswebshop.Service.GameService;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
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
@Path("jatek")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GameResource {

    @GET
    @Path("{jatekId}")
    public Response game(@PathParam("jatekId") Integer id) {
        JSONObject result = GameService.game(id);
        return Response.status(Response.Status.OK).entity(result.toString())
                .type(MediaType.APPLICATION_JSON).build();
    }
}
