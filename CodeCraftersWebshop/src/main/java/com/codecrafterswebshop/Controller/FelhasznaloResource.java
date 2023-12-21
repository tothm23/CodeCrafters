package com.codecrafterswebshop.Controller;

import com.codecrafterswebshop.Model.Felhasznalo;
import com.codecrafterswebshop.Service.FelhasznaloService;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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
@Path("felhasznalo")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FelhasznaloResource {

    @POST
    public Response ujFelhasznalo(Felhasznalo f) {
        String eredmeny = FelhasznaloService.ujFelhasznalo(f.getFelhasznaloNev(), f.getVezetekNev(),
                f.getKeresztNev(), f.getEmail(), f.getJelszo());
        return Response.status(Response.Status.CREATED).entity(eredmeny)
                .type(MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Path("bejelentkezes")
    public Response felhasznaloBelepes(Felhasznalo f) {
        JSONObject eredmeny = FelhasznaloService.felhasznaloBelepes(f.getFelhasznaloNev(), f.getJelszo());
        return Response.status(Response.Status.OK).entity(eredmeny.toString())
                .type(MediaType.APPLICATION_JSON).build();
    }

    @PUT
    @Path("{felhasznaloId}")
    public Response frissitesFelhasznalo(Felhasznalo f, @PathParam("felhasznaloId") Integer id) {
        JSONObject eredmeny = FelhasznaloService.felhasznalo(id);
        String frisssites = FelhasznaloService.frissitesFelhasznalo(id, f.getFelhasznaloNev(), f.getVezetekNev(),
                f.getKeresztNev(), f.getJelszo());

        return eredmeny.length() == 0 ? Response.status(Response.Status.NOT_FOUND).entity(frisssites)
                .type(MediaType.APPLICATION_JSON).build() : Response.status(Response.Status.OK).entity(frisssites)
                .type(MediaType.APPLICATION_JSON).build();
    }

    @DELETE
    @Path("{felhasznaloId}")
    public Response torlesFelhasznalo(@PathParam("felhasznaloId") Integer id) {
        JSONObject eredmeny = FelhasznaloService.felhasznalo(id);
        String torles = FelhasznaloService.torlesFelhasznalo(id);

        return eredmeny.length() == 0 ? Response.status(Response.Status.NOT_FOUND).entity(torles)
                .type(MediaType.APPLICATION_JSON).build() : Response.status(Response.Status.OK).entity(torles)
                .type(MediaType.APPLICATION_JSON).build();
    }

}
