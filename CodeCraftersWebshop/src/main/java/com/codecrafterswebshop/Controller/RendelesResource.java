package com.codecrafterswebshop.Controller;

import com.codecrafterswebshop.Model.Kosar;
import com.codecrafterswebshop.Service.RendelesService;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author tothm23
 */
@Path("rendeles")
@Consumes(MediaType.APPLICATION_JSON)
public class RendelesResource {

    @POST
    public Response rendeles(Kosar k) {
        String eredmeny = RendelesService.rendeles(k.getFelhasznaloId());
        return Response.status(Response.Status.CREATED).entity(eredmeny)
                .type(MediaType.APPLICATION_JSON).build();
    }

}
