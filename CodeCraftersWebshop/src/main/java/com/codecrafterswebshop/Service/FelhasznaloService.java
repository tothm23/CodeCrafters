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

    public static String ujFelhasznalo(String felhasznaloNevBE, String vezetekNevBE, String keresztNevBE,
            String emailBE, String jelszoBE) {
        try {
            if (!Felhasznalo.felhasznaloNevEllenorzes(felhasznaloNevBE)) {
                return "Hibás felhasználónév!";
            } else if (!Felhasznalo.vezetekNevEllenorzes(vezetekNevBE)) {
                return "Hibás vezetéknév!";
            } else if (!Felhasznalo.keresztNevEllenorzes(keresztNevBE)) {
                return "Hibás keresztnév!";
            } else if (!Felhasznalo.emailEllenorzes(emailBE)) {
                return "Hibás email!";
            } else if (!Felhasznalo.jelszoEllenorzes(jelszoBE)) {
                return "Hibás jelszó!";
            } else if (Felhasznalo.ujFelhasznalo(felhasznaloNevBE, vezetekNevBE, keresztNevBE,
                    emailBE, jelszoBE)) {
                return "Felhasználó hozzáadva!";
            } else {
                return "Hiba a Felhasználó hozzáadásánál!";
            }
        } catch (FelhasznaloException ex) {
            return ex.getMessage();
        }
    }

    public static String frissitesFelhasznalo(Integer id, String felhasznaloNevBE, String vezetekNevBE, String keresztNevBE,
            String jelszoBE) {
        try {
            if (Felhasznalo.frissitesFelhasznalo(id, felhasznaloNevBE, vezetekNevBE, keresztNevBE,
                    jelszoBE)) {
                return "Felhasználó frissítve!";
            } else {
                return "Hiba a Felhasználó frissítésénél!";
            }
        } catch (Exception ex) {
            return ex.getMessage();
        }
    }

    public static String torlesFelhasznalo(Integer id) {
        try {
            if (Felhasznalo.torlesFelhasznalo(id)) {
                return "Felhasználó törölve!";
            } else {
                return "Hiba a Felhasználó törlésénél!";
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
