package com.codecrafterswebshop.Service;

import com.codecrafterswebshop.Model.Kosar;
import java.util.List;
import java.util.Map;

/**
 *
 * @author tothm23
 */
public class RendelesService {

    public static String rendeles(Integer felhasznaloIdBE) {
        try {
            if (Kosar.rendeles(felhasznaloIdBE)) {
                termekkulcs(felhasznaloIdBE);
                return "Sikeres rendelés!";
            } else {
                return "Hiba a rendelésnél!";
            }
        } catch (Exception ex) {
            return ex.getMessage();
        }
    }

    public static void termekkulcs(Integer felhasznaloIdBE) {

        List<Map<String, Object>> termekkulcsok = Kosar.termekkulcs(felhasznaloIdBE);

        for (Map<String, Object> termek : termekkulcsok) {
            for (Map.Entry<String, Object> entry : termek.entrySet()) {
                System.out.println(entry.getKey() + " : " + entry.getValue());
            }
        }
    }

}
