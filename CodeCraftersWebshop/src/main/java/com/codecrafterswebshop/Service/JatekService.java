package com.codecrafterswebshop.Service;

import com.codecrafterswebshop.Model.Jatek;
import java.util.List;

/**
 *
 * @author tothm23
 */
public class JatekService {

    public static List<Jatek> jatek(Integer idBe) {
        return Jatek.jatek(idBe);
    }

    public static String ujJatek(String nevBE, Integer arBE, String leirasBE,
            String kepBE, Integer korhatarBE, Integer akcioBE, Integer mennyisegraktaronBE, Integer kategoriaIdBE, Integer eszkozIdBE, Integer platformIdBE) {
        try {
            if (Jatek.ujJatek(nevBE, arBE, leirasBE, kepBE, korhatarBE, akcioBE,
                    mennyisegraktaronBE, kategoriaIdBE, eszkozIdBE, platformIdBE)) {
                return "Játék hozzáadva!";
            } else {
                return "Hiba a Játék hozzáadásánál!";
            }
        } catch (Exception ex) {
            return ex.getMessage();
        }
    }

    public static String frissitesJatek(Integer idBE, String nevBE, Integer arBE, String leirasBE,
            String kepBE, Integer korhatarBE, Integer akcioBE, Integer mennyisegraktaronBE, Integer kategoriaIdBE, Integer eszkozIdBE, Integer platformIdBE) {
        try {
            if (Jatek.frissitesJatek(idBE, nevBE, arBE, leirasBE, kepBE, korhatarBE, akcioBE,
                    mennyisegraktaronBE, kategoriaIdBE, eszkozIdBE, platformIdBE)) {
                return "Játék frissítve!";
            } else {
                return "Hiba a Játék frissítésénél!";
            }
        } catch (Exception ex) {
            return ex.getMessage();
        }
    }

    public static String torlesJatek(Integer idBE) {
        try {
            if (Jatek.torlesJatek(idBE)) {
                return "Játék törölve!";
            } else {
                return "Hiba a Játék törlésénél!";
            }
        } catch (Exception ex) {
            return ex.getMessage();
        }
    }
}
