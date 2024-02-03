package com.codecrafterswebshop.Controller;

import com.codecrafterswebshop.Model.Basket;
import com.codecrafterswebshop.Service.OrderService;
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
@Path("order")
@Consumes(MediaType.APPLICATION_JSON)
public class OrderResource {

    @POST
    public Response order(Basket k) {
        String result = OrderService.order(k.getUserId());
        return Response.status(Response.Status.CREATED).entity(result)
                .type(MediaType.APPLICATION_JSON).build();
    }

}
