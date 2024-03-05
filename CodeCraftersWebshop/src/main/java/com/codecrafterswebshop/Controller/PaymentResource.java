package com.codecrafterswebshop.Controller;

import com.codecrafterswebshop.Model.Basket;
import com.codecrafterswebshop.Model.Payment;
import com.codecrafterswebshop.Service.OrderService;
import com.codecrafterswebshop.Service.PaymentService;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * REST Web Service
 *
 * @author tothm23
 */
@Path("payment")
@Consumes(MediaType.APPLICATION_JSON)
public class PaymentResource {

    @Context
    private UriInfo uriInfo;
    private Logger logger;
    private String time;

    public PaymentResource() {
        this.logger = LogManager.getLogger(PaymentResource.class.getName());
        this.time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    @POST
    public Response pay(Payment p) {
        String result = PaymentService.pay(p.getCustomerName(), p.getCustomerEmail(),
                p.getAmount(), p.getCardNumber(), p.getCardExpMonth(), p.getCardExpYear(), p.getCardCvc());
        Response response = Response.status(Response.Status.CREATED).entity(result)
                .type(MediaType.APPLICATION_JSON).build();

        logger.log(Level.INFO, time + "  <--  [" + response.getStatus() + "] POST " + uriInfo.getPath());
        return response;
    }
}
