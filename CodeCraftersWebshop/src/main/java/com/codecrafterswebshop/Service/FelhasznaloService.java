package com.codecrafterswebshop.Service;

import com.codecrafterswebshop.Model.Felhasznalo;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author tothm23
 */
public class FelhasznaloService {

    public static JSONArray felhasznalok() {
        JSONArray jsonArray = new JSONArray();

        List<Map<String, Object>> ajandekKartyak = Felhasznalo.felhasznalok();

        for (Map<String, Object> ajandekKartya : ajandekKartyak) {
            JSONObject obj = new JSONObject();
            obj.put("id", ajandekKartya.get("id"));
            obj.put("felhasznaloNev", ajandekKartya.get("felhasznaloNev"));
            obj.put("vezetekNev", ajandekKartya.get("vezetekNev"));
            obj.put("keresztNev", ajandekKartya.get("keresztNev"));
            obj.put("szuletesiDatum", ajandekKartya.get("szuletesiDatum"));
            obj.put("email", ajandekKartya.get("email"));
            obj.put("jelszo", ajandekKartya.get("jelszo"));
            obj.put("orszag", ajandekKartya.get("orszag"));
            obj.put("telefon", ajandekKartya.get("telefon"));
            obj.put("aktiv", ajandekKartya.get("aktiv"));
            obj.put("profilkep", ajandekKartya.get("profilkep"));
            obj.put("letrehozva", ajandekKartya.get("letrehozva"));

            jsonArray.put(obj);
        }

        return jsonArray;
    }

    public static String ujFelhasznalo(String felhasznaloNevBE, String vezetekNevBE, String keresztNev,
            String szuletesiDatumBE, String emailBE, String jelszoBE, String orszagBE, String telefon) {
        try {
            if (Felhasznalo.ujFelhasznalo(felhasznaloNevBE, vezetekNevBE, keresztNev,
                    szuletesiDatumBE, emailBE, jelszoBE, orszagBE, telefon)) {
                return "Felhasználó hozzáadva!";
            } else {
                return "Hiba a Felhasználó hozzáadásánál!";
            }
        } catch (Exception ex) {
            return ex.getMessage();
        }
    }

    public static JSONObject felhasznaloBelepes(String felhasznaloNevBE, String jelszoBE) {

        Integer id = Felhasznalo.felhasznaloBelepes(felhasznaloNevBE, jelszoBE);
        Map<String, Object> felhasznalo = Felhasznalo.felhasznalo(id);
        JSONObject obj = new JSONObject();

        for (Map.Entry<String, Object> set : felhasznalo.entrySet()) {
            obj.put(set.getKey(), set.getValue());
        }

        return obj;
    }
}
