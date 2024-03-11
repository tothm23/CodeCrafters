package com.codecrafterswebshop.Service;

import com.codecrafterswebshop.Exception.BasketException;
import com.codecrafterswebshop.Model.Basket;

/**
 *
 * @author tothm23
 */
public class OrderService {

    public static String order(Integer userIdIN) {
        try {
            if (!Basket.checkUserIdBasket(userIdIN)) {
                return "Hibás felhasznaloId!";
            } else if (!EmailService.email(EmailService.addressee(Basket.productKeys(userIdIN)),
                    "Megrendelésed összeállítottuk", EmailService.htmlOrder(Basket.productKeys(userIdIN)))) {
                return "Hiba az email küldésénél!";
            } else if (Basket.order(userIdIN)) {
                return "Sikeres rendelés!";
            } else {
                return "Hiba a rendelésnél!";
            }
        } catch (BasketException ex) {
            return ex.getMessage();
        }
    }

}
