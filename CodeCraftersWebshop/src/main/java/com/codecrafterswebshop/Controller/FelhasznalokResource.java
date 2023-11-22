package com.codecrafterswebshop.Controller;

import com.codecrafterswebshop.Model.Felhasznalo;
import com.codecrafterswebshop.Service.FelhasznaloService;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONObject;

/**
 * REST Web Service
 *
 * @author tothm23
 */
@Path("felhasznalok")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FelhasznalokResource {

    public FelhasznalokResource() {
    }

    @POST
    public Response ujFelhasznalo(Felhasznalo f) {
        String result = FelhasznaloService.ujFelhasznalo(f.getFelhasznaloNev(), f.getVezetekNev(),
                f.getKeresztNev(), f.getEmail(), f.getJelszo());
        return Response.status(Response.Status.CREATED).entity(result)
                .type(MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Path("bejelentkezes")
    public Response felhasznaloBelepes(Felhasznalo f) {
        JSONObject eredmeny = FelhasznaloService.felhasznaloBelepes(f.getFelhasznaloNev(), f.getJelszo());
        return Response.status(Response.Status.OK).entity(eredmeny.toString())
                .type(MediaType.APPLICATION_JSON).build();
    }

}
