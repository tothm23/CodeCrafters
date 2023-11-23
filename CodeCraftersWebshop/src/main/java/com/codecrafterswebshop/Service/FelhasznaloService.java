package com.codecrafterswebshop.Service;

import com.codecrafterswebshop.Exception.FelhasznaloException;
import com.codecrafterswebshop.Model.Felhasznalo;
import java.util.Map;
import org.json.JSONObject;

/**
 *
 * @author tothm23
 */
public class FelhasznaloService {

    public static String ujFelhasznalo(String felhasznaloNevBE, String vezetekNevBE, String keresztNev,
            String emailBE, String jelszoBE) {
        try {
            if (!Felhasznalo.felhasznaloNevEllenorzes(felhasznaloNevBE)) {
                return "Hibás felhasználónév!";
            } else if (!Felhasznalo.vezetekNevEllenorzes(vezetekNevBE)) {
                return "Hibás vezetéknév!";
            } else if (Felhasznalo.ujFelhasznalo(felhasznaloNevBE, vezetekNevBE, keresztNev,
                    emailBE, jelszoBE)) {
                return "Felhasználó hozzáadva!";
            } else {
                return "Hiba a Felhasználó hozzáadásánál!";
            }
        } catch (FelhasznaloException ex) {
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
