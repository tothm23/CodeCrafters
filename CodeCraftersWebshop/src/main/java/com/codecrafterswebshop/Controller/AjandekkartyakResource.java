package com.codecrafterswebshop.Controller;

import com.codecrafterswebshop.Model.Ajandekkartya;
import com.codecrafterswebshop.Service.AjandekkartyaService;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
}
