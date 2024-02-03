package com.codecrafterswebshop.Service;

import com.codecrafterswebshop.Exception.BasketException;
import com.codecrafterswebshop.Model.Basket;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author tothm23
 */
public class BasketService {

    public static JSONArray userBasket(Integer userIdIN) {

        List<Map<String, Object>> baskets = Basket.userBasket(userIdIN);
        JSONArray jsonArray = new JSONArray();

        for (Map<String, Object> basket : baskets) {
            JSONObject obj = new JSONObject();

            obj.put("id", basket.get("id"));
            obj.put("name", basket.get("name"));
            obj.put("amount", basket.get("amount"));
            obj.put("image", basket.get("image"));

            jsonArray.put(obj);
        }

        return jsonArray;
    }

    public static String basket(Integer gameIdIN, Integer userIdIN) {
        try {
            if (!Basket.checkGameId(gameIdIN)) {
                return "Hibás gameId!";
            } else if (!Basket.checkUserId(userIdIN)) {
                return "Hibás felhasznaloId!";
            } else if (Basket.basket(gameIdIN, userIdIN)) {
                return "Játék hozzáadva a kosárhoz!";
            } else {
                return "Hiba a Játék hozzáadásánál!";
            }
        } catch (BasketException ex) {
            return ex.getMessage();
        }
    }

    public static String deleteGameBasket(Integer userIdIN, Integer gameIdIN) {
        try {
            if (!Basket.checkUserIdBasket(userIdIN)) {
                return "Hibás felhasznaloId!";
            } else if (!Basket.checkGameIdBasket(gameIdIN)) {
                return "Hibás gameId!";
            } else if (Basket.deleteGameFromBasket(userIdIN, gameIdIN)) {
                return "Játék törölve a kosárból!";
            } else {
                return "Hiba a Játék törlésénél!";
            }
        } catch (BasketException ex) {
            return ex.getMessage();
        }
    }
}
