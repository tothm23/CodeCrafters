package com.codecrafterswebshop.Service;

import com.codecrafterswebshop.Model.Felhasznalo;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/**
 *
 * @author tothm23
 */
public class FelhasznaloService {

    public static List<Felhasznalo> felhasznalok() {
        return Felhasznalo.felhasznalok();
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
