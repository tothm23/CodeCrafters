package com.codecrafterswebshop.Service;

import com.codecrafterswebshop.Model.Kosar;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author tothm23
 */
public class KosarService {

    public static JSONArray felhasznaloKosar(Integer felhasznaloIdBE) {

        List<Map<String, Object>> kosarak = Kosar.felhasznaloKosar(felhasznaloIdBE);
        JSONArray jsonArray = new JSONArray();

        for (Map<String, Object> kosar : kosarak) {
            JSONObject obj = new JSONObject();
            obj.put("nev", kosar.get("nev"));
            obj.put("vegosszeg", kosar.get("vegosszeg"));
            obj.put("kep", kosar.get("kep"));

            jsonArray.put(obj);
        }

        return jsonArray;
    }
}
