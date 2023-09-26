package com.codecrafterswebshop.Controller;

import com.codecrafterswebshop.Model.Ajandekkartya;
import com.codecrafterswebshop.Service.AjandekkartyaService;
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
@Path("ajandekkartyak")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AjandekkartyakResource {
    
    public AjandekkartyakResource() {
    }
    
    @GET
    @Path("{ajandekKartyaId}")
    public List<Ajandekkartya> ajandekKartya(@PathParam("ajandekKartyaId") Integer id) {
        List<Ajandekkartya> result = AjandekkartyaService.ajandekKartya(id);
        return result;
    }
    
    @POST
    public Response ujAjandekKartya(Ajandekkartya a) {
        String result = AjandekkartyaService.ujAjandekKartya(a.getNev(), a.getAr(),
                a.getLeiras(), a.getKep(), a.getAkcio(), a.getMennyisegraktaron(),
                a.getEszkozId(), a.getPlatformId());
        return Response.status(Response.Status.OK).entity(result)
                .type(MediaType.APPLICATION_JSON).build();
    }
    
    @PUT
    @Path("{ajandekKartyaId}")
    public Response frissitesAjandekKartya(@PathParam("ajandekKartyaId") Integer id, Ajandekkartya a) {
        String result = AjandekkartyaService.frissitesAjandekKartya(id, a.getNev(), a.getAr(),
                a.getLeiras(), a.getKep(), a.getAkcio(), a.getMennyisegraktaron(),
                a.getEszkozId(), a.getPlatformId());
        return Response.status(Response.Status.OK).entity(result)
                .type(MediaType.APPLICATION_JSON).build();
    }
    
    @DELETE
    @Path("{ajandekKartyaId}")
    public Response torlesAjandekKartya(@PathParam("ajandekKartyaId") Integer id) {
        String result = AjandekkartyaService.torlesAjandekKartya(id);
        return Response.status(Response.Status.OK).entity(result)
                .type(MediaType.APPLICATION_JSON).build();
    }
}