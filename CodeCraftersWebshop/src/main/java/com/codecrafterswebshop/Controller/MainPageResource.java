package com.codecrafterswebshop.Controller;

import com.codecrafterswebshop.Service.MainPageService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONArray;

/**
 * REST Web Service
 *
 * @author tothm23
 */
@Path("mainPage")
@Produces(MediaType.APPLICATION_JSON)
public class MainPageResource {

    @GET
    public Response mainPage() {
        JSONArray result = MainPageService.mainPage();
        return Response.status(Response.Status.OK).entity(result.toString())
                .type(MediaType.APPLICATION_JSON).build();
    }

}
