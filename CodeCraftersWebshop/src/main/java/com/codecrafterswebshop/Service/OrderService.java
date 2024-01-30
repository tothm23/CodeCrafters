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
            String addressee = EmailService.addressee(Basket.productKey(userIdIN));
            String content = EmailService.htmlOrder(Basket.productKey(userIdIN));

            if (!Basket.checkUserIdBasket(userIdIN)) {
                return "Hibás felhasznaloId!";
            } else if (!EmailService.email(addressee, "Megrendelésed összeállítottuk", content)) {
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
