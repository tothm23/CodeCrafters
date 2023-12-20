package com.codecrafterswebshop.Service;

import com.codecrafterswebshop.Exception.KosarException;
import com.codecrafterswebshop.Model.Kosar;

/**
 *
 * @author tothm23
 */
public class RendelesService {

    public static String rendeles(Integer felhasznaloIdBE) {
        try {
            if (!Kosar.felhasznaloIdKosarEllenorzes(felhasznaloIdBE)) {
                return "Hibás felhasznaloId!";
            } else if (!EmailService.email(Kosar.termekKulcs(felhasznaloIdBE))) {
                return "Hiba az email küldésénél!";
            } else if (Kosar.rendeles(felhasznaloIdBE)) {
                return "Sikeres rendelés!";
            } else {
                return "Hiba a rendelésnél!";
            }
        } catch (KosarException ex) {
            return ex.getMessage();
        }
    }

}
