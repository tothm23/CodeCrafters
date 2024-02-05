package com.codecrafterswebshop.Service;

import com.codecrafterswebshop.Model.Game;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author tothm23
 */
public class ProductsService {

    public static JSONArray products() {
        JSONArray jsonArray = new JSONArray();
        List<Map<String, Object>> games = Game.games();

        for (Map<String, Object> game : games) {
            JSONObject obj = new JSONObject();
            obj.put("id", game.get("id"));
            obj.put("gameName", game.get("gameName"));
            obj.put("price", game.get("price"));
            obj.put("description", game.get("description"));
            obj.put("image", game.get("image"));
            obj.put("ageLimit", game.get("ageLimit"));
            obj.put("discount", game.get("discount"));
            obj.put("inStock", game.get("inStock"));
            obj.put("device", game.get("device"));
            obj.put("platform", game.get("platform"));

            jsonArray.put(obj);
        }

        return jsonArray;
    }

}
