package com.codecrafterswebshop.Controller;

import com.codecrafterswebshop.Model.Jatek;
import com.codecrafterswebshop.Service.JatekService;
import java.util.List;
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

/**
 * REST Web Service
 *
 * @author tothm23
 */
@Path("jatekok")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class JatekokResource {

    public JatekokResource() {
    }

    @GET
    @Path("{jatekId}")
    public List<Jatek> jatek(@PathParam("jatekId") Integer id) {
        List<Jatek> result = JatekService.jatek(id);
        return result;
    }

    @POST
    public Response ujJatek(Jatek j) {
        String result = JatekService.ujJatek(j.getNev(), j.getAr(),
                j.getLeiras(), j.getKep(), j.getKorhatar(), j.getAkcio(), j.getMennyisegraktaron(),
                j.getKategoriaId(), j.getEszkozId(), j.getPlatformId()
        );
        return Response.status(Response.Status.CREATED).entity(result)
                .type(MediaType.APPLICATION_JSON).build();
    }

    @PUT
    @Path("{jatekId}")
    public Response frissitesJatek(Jatek j, @PathParam("jatekId") Integer id) {
        String result = JatekService.frissitesJatek(id, j.getNev(), j.getAr(),
                j.getLeiras(), j.getKep(), j.getKorhatar(), j.getAkcio(), j.getMennyisegraktaron(),
                j.getKategoriaId(), j.getEszkozId(), j.getPlatformId()
        );
        return Response.status(Response.Status.OK).entity(result)
                .type(MediaType.APPLICATION_JSON).build();
    }

    @DELETE
    @Path("{jatekId}")
    public Response torlesJatek(@PathParam("jatekId") Integer id) {
        String result = JatekService.torlesJatek(id);
        return Response.status(Response.Status.OK).entity(result)
                .type(MediaType.APPLICATION_JSON).build();
    }
}
