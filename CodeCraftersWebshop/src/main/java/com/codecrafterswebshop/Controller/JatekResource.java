package com.codecrafterswebshop.Controller;

import com.codecrafterswebshop.Service.JatekService;
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
public class JatekResource {

    @GET
    @Path("{jatekId}")
    public Response jatek(@PathParam("jatekId") Integer id) {
        JSONObject eredmeny = JatekService.jatek(id);
        return Response.status(Response.Status.OK).entity(eredmeny.toString())
                .type(MediaType.APPLICATION_JSON).build();
    }
}
