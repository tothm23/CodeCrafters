package com.codecrafterswebshop.Service;

import com.codecrafterswebshop.Model.Jatek;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author tothm23
 */
public class FooldalService {

    public static JSONArray fooldal() {

        JSONArray jsonArray = new JSONArray();
        JSONObject obj = new JSONObject();

        List<Map<String, Object>> jatekok = Jatek._3veletlenjatek();
        List<Map<String, Object>> bestsellerek = Jatek.bestseller();

        for (Map<String, Object> jatek : jatekok) {
            obj.put("id", jatek.get("id"));
            obj.put("nev", jatek.get("nev"));
            obj.put("ar", jatek.get("ar"));
            obj.put("leiras", jatek.get("leiras"));
            obj.put("kep", jatek.get("kep"));
            obj.put("korhatar", jatek.get("korhatar"));
            obj.put("akcio", jatek.get("akcio"));
            obj.put("mennyisegraktaron", jatek.get("mennyisegraktaron"));
            obj.put("eszkoz", jatek.get("eszkoz"));
            obj.put("platform", jatek.get("platform"));
            obj.put("tipus", "veletlen");

            jsonArray.put(obj);
        }

        for (Map<String, Object> bestseller : bestsellerek) {
            obj.put("id", bestseller.get("id"));
            obj.put("nev", bestseller.get("nev"));
            obj.put("ar", bestseller.get("ar"));
            obj.put("kep", bestseller.get("kep"));
            obj.put("akcio", bestseller.get("akcio"));
            obj.put("tipus", "bestseller");

            jsonArray.put(obj);
        }

        return jsonArray;
    }

}
