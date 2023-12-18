package com.codecrafterswebshop.Service;

import com.codecrafterswebshop.Model.Felhasznalo;
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
            if (!EmailService.email("keribence0@gmail.com", Kosar.termekKulcs(felhasznaloIdBE))) {
                return "Hiba az email küldésénél!";
            } else if (Kosar.rendeles(felhasznaloIdBE)) {
                return "Sikeres rendelés!";
            } else {
                return "Hiba a rendelésnél!";
            }
        } catch (Exception ex) {
            return ex.getMessage();
        }
    }

}
