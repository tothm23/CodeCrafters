package com.codecrafterswebshop.Controller;

import com.codecrafterswebshop.Model.Ajandekkartya;
import com.codecrafterswebshop.Service.AjandekkartyaService;
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
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * REST Web Service
 *
 * @author tothm23
 */
@Path("ajandekkartyak")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AjandekkartyakResource {

    public AjandekkartyakResource() {
    }

    @GET
    @Path("{ajandekKartyaId}")
    public Response ajandekKartya(@PathParam("ajandekKartyaId") Integer id) {
        JSONObject eredmeny = AjandekkartyaService.ajandekKartya(id);

        return eredmeny.length() == 0 ? Response.status(Response.Status.NOT_FOUND).entity(eredmeny.toString())
                .type(MediaType.APPLICATION_JSON).build()
                : Response.status(Response.Status.OK).entity(eredmeny.toString())
                        .type(MediaType.APPLICATION_JSON).build();
    }

    @POST
    public Response ujAjandekKartya(Ajandekkartya a) {
        String result = AjandekkartyaService.ujAjandekKartya(a.getNev(), a.getAr(),
                a.getLeiras(), a.getKep(), a.getAkcio(), a.getMennyisegraktaron(),
                a.getEszkozId(), a.getPlatformId());
        return Response.status(Response.Status.CREATED).entity(result)
                .type(MediaType.APPLICATION_JSON).build();
    }

    @PUT
    @Path("{ajandekKartyaId}")
    public Response frissitesAjandekKartya(@PathParam("ajandekKartyaId") Integer id, Ajandekkartya a) {
        JSONObject eredmeny = AjandekkartyaService.ajandekKartya(id);
        String frissites = AjandekkartyaService.frissitesAjandekKartya(id, a.getNev(), a.getAr(),
                a.getLeiras(), a.getKep(), a.getAkcio(), a.getMennyisegraktaron(),
                a.getEszkozId(), a.getPlatformId());

        return eredmeny.length() == 0 ? Response.status(Response.Status.NOT_FOUND).entity(frissites)
                .type(MediaType.APPLICATION_JSON).build() : Response.status(Response.Status.OK).entity(frissites)
                .type(MediaType.APPLICATION_JSON).build();
    }

    @DELETE
    @Path("{ajandekKartyaId}")
    public Response torlesAjandekKartya(@PathParam("ajandekKartyaId") Integer id) {
        JSONObject eredmeny = AjandekkartyaService.ajandekKartya(id);
        String torles = AjandekkartyaService.torlesAjandekKartya(id);

        return eredmeny.length() == 0 ? Response.status(Response.Status.NOT_FOUND).entity(torles)
                .type(MediaType.APPLICATION_JSON).build() : Response.status(Response.Status.OK).entity(torles)
                .type(MediaType.APPLICATION_JSON).build();
    }
}
