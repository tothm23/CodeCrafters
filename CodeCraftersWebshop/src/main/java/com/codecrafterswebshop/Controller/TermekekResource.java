package com.codecrafterswebshop.Controller;

import com.codecrafterswebshop.Service.TermekekService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONArray;

/**
 * REST Web Service
 *
 * @author tothm23
 */
@Path("termekek")
public class TermekekResource {

    @GET
    public Response termekek() {
        JSONArray eredmeny = TermekekService.termekek();
        return Response.status(Response.Status.OK).entity(eredmeny.toString())
                .type(MediaType.APPLICATION_JSON).build();
    }
}
