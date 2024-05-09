package com.codecrafterswebshop.Service;

import com.codecrafterswebshop.Exception.GameException;
import com.codecrafterswebshop.Model.Game;
import java.util.Map;
import org.json.JSONObject;

/**
 *
 * @author tothm23
 */
public class GameService {

    public static JSONObject game(Integer idBe) {
        Map<String, Object> game = Game.game(idBe);
        JSONObject obj = new JSONObject();

        for (Map.Entry<String, Object> set : game.entrySet()) {
            obj.put(set.getKey(), set.getValue());
        }

        return obj;
    }

    public static JSONObject emptyGame() {
        JSONObject obj = new JSONObject();
        obj.put("message", "A játék nem található!");
        return obj;
    }

    public static String newGame(String nameIN, Integer priceIN, String descriptionIN,
            String imageIN, Integer ageLimitIN, Integer discountIN, Integer inStockIN, Integer deviceIdIN, Integer platformIdIN) {
        try {

            if (!Game.checkNameUnique(nameIN)) {
                return "Hibás név!";
            } else if (!Game.checkName(nameIN)) {
                return "Hibás név!";
            } else if (!Game.checkPrice(priceIN)) {
                return "Hibás ár!";
            } else if (!Game.checkDescription(descriptionIN)) {
                return "Hibás leírás!";
            } else if (!Game.checkImageUnique(imageIN)) {
                return "Hibás kép!";
            } else if (!Game.checkImage(imageIN)) {
                return "Hibás kép!";
            } else if (!Game.checkAgeLimit(ageLimitIN)) {
                return "Hibás korhatár!";
            } else if (!Game.checkDiscount(discountIN)) {
                return "Hibás akció!";
            } else if (!Game.checkInStock(inStockIN)) {
                return "Hibás mennyiségraktáron!";
            } else if (Game.newGame(nameIN, priceIN, descriptionIN, imageIN, ageLimitIN, discountIN,
                    inStockIN, deviceIdIN, platformIdIN)) {
                return "Játék hozzáadva!";
            } else {
                return "Hiba a Játék hozzáadásánál!";
            }
        } catch (GameException ex) {
            return ex.getMessage();
        }
    }

    public static String updateGame(Integer idIN, String nameIN, Integer priceIN, String descriptionIN,
            String imageIN, Integer ageLimitIN, Integer discountIN, Integer inStockIN, Integer deviceIdIN, Integer platformIdIN) {
        try {
            if (Game.game(idIN).isEmpty()) {
                return "A játék nem létezik!";
            } else if (!Game.checkNameUnique(nameIN)) {
                return "Hibás név!";
            } else if (!Game.checkName(nameIN)) {
                return "Hibás név!";
            } else if (!Game.checkPrice(priceIN)) {
                return "Hibás ár!";
            } else if (!Game.checkDescription(descriptionIN)) {
                return "Hibás leírás!";
            } else if (!Game.checkImageUnique(imageIN)) {
                return "Hibás kép!";
            } else if (!Game.checkImage(imageIN)) {
                return "Hibás kép!";
            } else if (!Game.checkAgeLimit(ageLimitIN)) {
                return "Hibás korhatár!";
            } else if (!Game.checkDiscount(discountIN)) {
                return "Hibás akció!";
            } else if (!Game.checkInStock(inStockIN)) {
                return "Hibás mennyiségraktáron!";
            } else if (Game.updateGame(idIN, nameIN, priceIN, descriptionIN, imageIN, ageLimitIN, discountIN,
                    inStockIN, deviceIdIN, platformIdIN)) {
                return "Játék frissítve!";
            } else {
                return "Hiba a Játék frissítésénél!";
            }
        } catch (GameException ex) {
            return ex.getMessage();
        }
    }

    public static String deleteGame(Integer idIN) {
        try {
            if (Game.game(idIN).isEmpty()) {
                return "A játék nem létezik!";
            } else if (Game.deleteGame(idIN)) {
                return "Játék törölve!";
            } else {
                return "Hiba a Játék törlésénél!";
            }
        } catch (Exception ex) {
            return ex.getMessage();
        }
    }

}
