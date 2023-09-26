package com.codecrafterswebshop.Controller;

import com.codecrafterswebshop.Model.Jatek;
import com.codecrafterswebshop.Service.JatekService;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
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

    @POST
    public Response ujJatek(Jatek j) {
        String result = JatekService.ujJatek(j.getNev(), j.getAr(),
                j.getLeiras(), j.getKep(), j.getKorhatar(), j.getAkcio(), j.getMennyisegraktaron(),
                j.getKategoriaId(), j.getEszkozId(), j.getPlatformId()
        );
        return Response.status(Response.Status.OK).entity(result)
                .type(MediaType.APPLICATION_JSON).build();
    }
}
