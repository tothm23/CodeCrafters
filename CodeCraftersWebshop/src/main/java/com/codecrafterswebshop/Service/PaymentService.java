package com.codecrafterswebshop.Service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.Token;
import com.stripe.param.ChargeCreateParams;
import com.stripe.param.CustomerCreateParams;
import com.stripe.param.TokenCreateParams;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author tothm23
 */
public class PaymentService {

    public static boolean processPayment(String stripeSecretKey, String customerName, String customerEmail, String token, Long amount) {
        Stripe.apiKey = stripeSecretKey;
        Logger logger = LogManager.getLogger(PaymentService.class.getName());

        try {
            CustomerCreateParams params
                    = CustomerCreateParams.builder()
                            .setName(customerName)
                            .setEmail(customerEmail)
                            .setSource(token)
                            .build();
            Customer customer = Customer.create(params);

            Charge.create(
                    new ChargeCreateParams.Builder()
                            .setAmount(amount * 100)
                            .setCurrency("huf")
                            .setDescription("CodeCraftersWebshop vásárlás")
                            .setCustomer(customer.getId())
                            .build());

            return true;
        } catch (StripeException e) {
            logger.log(Level.ERROR, e);
            return false;
        }
    }

    public static String pay(String customerName, String customerEmail, Long amount,
            String cardNumber, String cardExpMonth, String cardExpYear, String cardCvc) {
        Stripe.apiKey = "pk_test_51OjIDZEtu0vYeAdJrekXMTZJTnjq9eMDG24eF9RJZ4ix6zNm1rua1w1HUkBZQew6DmRqswT2xafttbULLjMsq2qg00Mqzstqtl";

        try {
            TokenCreateParams params = TokenCreateParams.builder()
                    .setCard(TokenCreateParams.Card.builder()
                            .setNumber(cardNumber)
                            .setExpMonth(cardExpMonth)
                            .setExpYear(cardExpYear)
                            .setCvc(cardCvc)
                            .build()
                    )
                    .build();
            Token token = Token.create(params);
            if (processPayment("sk_test_51OjIDZEtu0vYeAdJUzt40UYgZkmFkGrsqpaKJIbfAHKRYAEq5z664kRxqVJVL7Ba2VwbMKG8AwUXXZswJCc2a5fg00qRsn9e94",
                    customerName, customerEmail, token.getId(), amount)) {
                return "Sikeres fizetés!";
            } else {
                return "Hiba a fizetésnél!";
            }

        } catch (StripeException e) {
            return e.getMessage();
        }
    }

}
