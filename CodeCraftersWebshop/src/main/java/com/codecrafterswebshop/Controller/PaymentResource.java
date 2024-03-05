package com.codecrafterswebshop.Controller;

import com.codecrafterswebshop.Model.CreditCard;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author tothm23
 */
@Path("payment")
public class PaymentResource {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void processOrder(CreditCard creditCard) {
        System.out.println("Received payment details:");
        System.out.println("Card Number: " + creditCard.getNumber());
        System.out.println("Expiration Month: " + creditCard.getExpMonth());
        System.out.println("Expiration Year: " + creditCard.getExpYear());
        System.out.println("CVC: " + creditCard.getCvc());
    }
}
