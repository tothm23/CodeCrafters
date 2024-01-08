package com.codecrafterswebshop.Service;

import com.codecrafterswebshop.Config.Token;
import com.codecrafterswebshop.Exception.FelhasznaloException;
import com.codecrafterswebshop.Model.Felhasznalo;
import java.util.Map;
import org.json.JSONObject;

/**
 *
 * @author tothm23
 */
public class FelhasznaloService {

    public static JSONObject felhasznalo(Integer idBe) {
        Map<String, Object> felhasznalo = Felhasznalo.felhasznalo(idBe);
        JSONObject obj = new JSONObject();

        for (Map.Entry<String, Object> set : felhasznalo.entrySet()) {
            obj.put(set.getKey(), set.getValue());
        }

        return obj;
    }

    public static String ujFelhasznalo(String felhasznaloNevBE, String vezetekNevBE, String keresztNevBE,
            String emailBE, String jelszoBE) {
        try {
            String tartalom = EmailService.htmlRegisztracio(felhasznaloNevBE);
            if (!Felhasznalo.felhasznaloNevEgyediEllenorzes(felhasznaloNevBE)) {
                return "Hibás felhasználónév!";
            } else if (!Felhasznalo.felhasznaloNevEllenorzes(felhasznaloNevBE)) {
                return "Hibás felhasználónév!";
            } else if (!Felhasznalo.vezetekNevEllenorzes(vezetekNevBE)) {
                return "Hibás vezetéknév!";
            } else if (!Felhasznalo.keresztNevEllenorzes(keresztNevBE)) {
                return "Hibás keresztnév!";
            } else if (!Felhasznalo.emailEgyediEllenorzes(emailBE)) {
                return "Hibás email!";
            } else if (!Felhasznalo.emailEllenorzes(emailBE)) {
                return "Hibás email!";
            } else if (!Felhasznalo.jelszoEllenorzes(jelszoBE)) {
                return "Hibás jelszó!";
            } else if (!EmailService.email(emailBE, "Sikeres regisztráció", tartalom)) {
                return "Hiba az email küldésénél!";
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

    public static JSONObject felhasznaloBelepes(String felhasznaloNevBE, String jelszoBE) {

        JSONObject obj = new JSONObject();
        Felhasznalo f = Felhasznalo.felhasznaloBelepes(felhasznaloNevBE, jelszoBE);
        String token = Token.letrehozas(f, 600000);

        if (f.getId() != null) {
            obj.put("token", token);
        } else {
            obj.put("token", "");
        }

        return obj;
    }

    public static String frissitesFelhasznalo(Integer idBe, String felhasznaloNevBE, String vezetekNevBE, String keresztNevBE,
            String jelszoBE) {
        try {
            if (felhasznalo(idBe).length() == 0) {
                return "A Felhasználó nem található!";
            } else if (Felhasznalo.frissitesFelhasznalo(idBe, felhasznaloNevBE, vezetekNevBE, keresztNevBE,
                    jelszoBE)) {
                return "Felhasználó frissítve!";
            } else {
                return "Hiba a Felhasználó frissítésénél!";
            }
        } catch (Exception ex) {
            return ex.getMessage();
        }
    }

    public static String torlesFelhasznalo(Integer idBe) {
        try {
            if (felhasznalo(idBe).length() == 0) {
                return "A Felhasználó nem található!";
            } else if (Felhasznalo.torlesFelhasznalo(idBe)) {
                return "Felhasználó törölve!";
            } else {
                return "Hiba a Felhasználó törlésénél!";
            }
        } catch (Exception ex) {
            return ex.getMessage();
        }
    }

}
