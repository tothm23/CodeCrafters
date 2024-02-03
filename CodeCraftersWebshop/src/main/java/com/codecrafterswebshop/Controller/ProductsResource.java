package com.codecrafterswebshop.Controller;

import com.codecrafterswebshop.Service.ProductsService;
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
@Path("products")
public class ProductsResource {

    @GET
    public Response products() {
        JSONArray result = ProductsService.products();
        return Response.status(Response.Status.OK).entity(result.toString())
                .type(MediaType.APPLICATION_JSON).build();
    }
}
