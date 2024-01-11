package com.codecrafterswebshop.Controller;

import com.codecrafterswebshop.Model.Kosar;
import com.codecrafterswebshop.Service.KosarService;
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
@Path("kosar")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class KosarResource {

    @GET
    @Path("{felhasznaloId}")
    public Response felhasznaloKosar(@PathParam("felhasznaloId") Integer id) {
        JSONArray eredmeny = KosarService.felhasznaloKosar(id);
        return Response.status(Response.Status.OK).entity(eredmeny.toString())
                .type(MediaType.APPLICATION_JSON).build();
    }

    @POST
    public Response kosar(Kosar k) {
        String eredmeny = KosarService.kosar(k.getJatekId(), k.getFelhasznaloId());
        return Response.status(Response.Status.OK).entity(eredmeny)
                .type(MediaType.APPLICATION_JSON).build();
    }

    @DELETE
    public Response torlesJatekKosarbol(@QueryParam("felhasznaloId") Integer felhasznaloId, @QueryParam("jatekId") Integer jatekId) {
        String eredmeny = KosarService.torlesJatekKosarbol(felhasznaloId, jatekId);
        return Response.status(Response.Status.OK).entity(eredmeny)
                .type(MediaType.APPLICATION_JSON).build();
    }
}
