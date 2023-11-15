package com.codecrafterswebshop.Controller;

import com.codecrafterswebshop.Service.FooldalService;
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
@Path("fooldal")
public class FooldalResource {

    public FooldalResource() {
    }

    @GET
    public Response _3veletlenjatek() {
        JSONArray eredmeny = FooldalService._3veletlenjatek();
        return Response.status(Response.Status.OK).entity(eredmeny.toString())
                .type(MediaType.APPLICATION_JSON).build();
    }

}
