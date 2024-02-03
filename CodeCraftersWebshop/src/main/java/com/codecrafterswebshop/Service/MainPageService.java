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
public class MainPageService {

    public static JSONArray mainPage() {

        JSONArray jsonArray = new JSONArray();

        List<Map<String, Object>> carouselGames = Game.carouselGames();
        List<Map<String, Object>> bestsellers = Game.bestsellers();
        List<Map<String, Object>> newGames = Game.newGames();

        for (Map<String, Object> game : carouselGames) {
            JSONObject obj = new JSONObject();

            obj.put("id", game.get("id"));
            obj.put("name", game.get("name"));
            obj.put("price", game.get("price"));
            obj.put("image", game.get("image"));
            obj.put("discount", game.get("discount"));
            obj.put("type", "carousel");

            jsonArray.put(obj);
        }

        for (Map<String, Object> bestseller : bestsellers) {
            JSONObject obj = new JSONObject();

            obj.put("id", bestseller.get("id"));
            obj.put("name", bestseller.get("name"));
            obj.put("price", bestseller.get("price"));
            obj.put("image", bestseller.get("image"));
            obj.put("discount", bestseller.get("discount"));
            obj.put("type", "bestseller");

            jsonArray.put(obj);
        }

        for (Map<String, Object> game : newGames) {
            JSONObject obj = new JSONObject();

            obj.put("id", game.get("id"));
            obj.put("name", game.get("name"));
            obj.put("price", game.get("price"));
            obj.put("image", game.get("image"));
            obj.put("discount", game.get("discount"));
            obj.put("type", "new");

            jsonArray.put(obj);
        }

        return jsonArray;
    }

}
