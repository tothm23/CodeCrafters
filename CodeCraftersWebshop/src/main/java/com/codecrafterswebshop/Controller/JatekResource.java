package com.codecrafterswebshop.Controller;

import com.codecrafterswebshop.Model.Jatek;
import com.codecrafterswebshop.Service.JatekService;
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

    @POST
    public Response ujJatek(Jatek j) {
        String eredmeny = JatekService.ujJatek(j.getNev(), j.getAr(),
                j.getLeiras(), j.getKep(), j.getKorhatar(), j.getAkcio(), j.getMennyisegraktaron(),
                j.getEszkozId(), j.getPlatformId()
        );
        return Response.status(Response.Status.CREATED).entity(eredmeny)
                .type(MediaType.APPLICATION_JSON).build();
    }

    @PUT
    @Path("{jatekId}")
    public Response frissitesJatek(Jatek j, @PathParam("jatekId") Integer id) {
        JSONObject eredmeny = JatekService.jatek(id);
        String frissites = JatekService.frissitesJatek(id, j.getNev(), j.getAr(),
                j.getLeiras(), j.getKep(), j.getKorhatar(), j.getAkcio(), j.getMennyisegraktaron(),
                j.getEszkozId(), j.getPlatformId()
        );

        return eredmeny.length() == 0 ? Response.status(Response.Status.NOT_FOUND).entity(frissites)
                .type(MediaType.APPLICATION_JSON).build() : Response.status(Response.Status.OK).entity(frissites)
                .type(MediaType.APPLICATION_JSON).build();
    }

    @DELETE
    @Path("{jatekId}")
    public Response torlesJatek(@PathParam("jatekId") Integer id) {
        JSONObject eredmeny = JatekService.jatek(id);
        String torles = JatekService.torlesJatek(id);

        return eredmeny.length() == 0 ? Response.status(Response.Status.NOT_FOUND).entity(torles)
                .type(MediaType.APPLICATION_JSON).build() : Response.status(Response.Status.OK).entity(torles)
                .type(MediaType.APPLICATION_JSON).build();
    }
}
