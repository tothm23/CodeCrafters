package com.codecrafterswebshop.Service;

import com.codecrafterswebshop.Model.Ajandekkartya;
import com.codecrafterswebshop.Model.Jatek;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author tothm23
 */
public class TermekekService {

    public static JSONArray termekek() {
        JSONArray jsonArray = new JSONArray();

        List<Map<String, Object>> ajandekKartyak = Ajandekkartya.ajandekKartyak();

        for (Map<String, Object> ajandekKartya : ajandekKartyak) {
            JSONObject obj = new JSONObject();
            obj.put("id", ajandekKartya.get("id"));
            obj.put("nev", ajandekKartya.get("nev"));
            obj.put("ar", ajandekKartya.get("ar"));
            obj.put("leiras", ajandekKartya.get("leiras"));
            obj.put("kep", ajandekKartya.get("kep"));
            obj.put("akcio", ajandekKartya.get("akcio"));
            obj.put("mennyisegraktaron", ajandekKartya.get("mennyisegraktaron"));
            obj.put("eszkoz", ajandekKartya.get("eszkoz"));
            obj.put("platform", ajandekKartya.get("platform"));

            jsonArray.put(obj);
        }

        List<Map<String, Object>> jatekok = Jatek.jatekok();

        for (Map<String, Object> jatek : jatekok) {
            JSONObject obj = new JSONObject();
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

            jsonArray.put(obj);
        }

        return jsonArray;
    }

}
