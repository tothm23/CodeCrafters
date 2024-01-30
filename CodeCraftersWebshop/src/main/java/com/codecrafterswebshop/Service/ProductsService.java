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
        List<Map<String, Object>> games = Game.jatekok();

        for (Map<String, Object> game : games) {
            JSONObject obj = new JSONObject();
            obj.put("id", game.get("id"));
            obj.put("nev", game.get("nev"));
            obj.put("ar", game.get("ar"));
            obj.put("leiras", game.get("leiras"));
            obj.put("kep", game.get("kep"));
            obj.put("korhatar", game.get("korhatar"));
            obj.put("akcio", game.get("akcio"));
            obj.put("mennyisegraktaron", game.get("mennyisegraktaron"));
            obj.put("eszkoz", game.get("eszkoz"));
            obj.put("platform", game.get("platform"));

            jsonArray.put(obj);
        }

        return jsonArray;
    }

}
