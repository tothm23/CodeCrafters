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
            String cimzett = EmailService.cimzett(Kosar.termekKulcs(felhasznaloIdBE));
            String tartalom = EmailService.htmlMegrendeles(Kosar.termekKulcs(felhasznaloIdBE));

            if (!Kosar.felhasznaloIdKosarEllenorzes(felhasznaloIdBE)) {
                return "Hibás felhasznaloId!";
            } else if (!EmailService.email(cimzett, "Megrendelésed összeállítottuk", tartalom)) {
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
