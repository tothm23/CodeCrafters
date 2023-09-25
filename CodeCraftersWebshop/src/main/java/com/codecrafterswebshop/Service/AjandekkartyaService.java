package com.codecrafterswebshop.Service;

import com.codecrafterswebshop.Model.Ajandekkartya;
import java.util.List;

/**
 *
 * @author tothm23
 */
public class AjandekkartyaService {

    public static List<Ajandekkartya> ajandekKartya(Integer ajandekKartyaIdBE) {
        return Ajandekkartya.ajandekKartya(ajandekKartyaIdBE);
    }

    public static String ujAjandekKartya(String nevBE, Integer arBE, String leirasBE,
            String kepBE, Integer akcioBE, Integer mennyisegraktaronBE, Integer eszkozIdBE, Integer platformIdBE) {
        try {
            if (Ajandekkartya.ujAjandekKartya(nevBE, arBE, leirasBE, kepBE, akcioBE, mennyisegraktaronBE, eszkozIdBE, platformIdBE)) {
                return "Ajándék kártya hozzáadva!";
            } else {
                return "Hiba az ajándék kártya hozzáadásánál!";
            }
        } catch (Exception ex) {
            return ex.getMessage();
        }
    }

    public static String frissitesAjandekKartya(Integer idBE, String nevBE, Integer arBE, String leirasBE,
            String kepBE, Integer akcioBE, Integer mennyisegraktaronBE, Integer eszkozIdBE, Integer platformIdBE) {
        try {
            if (Ajandekkartya.frissitesAjandekKartya(idBE, nevBE, arBE, leirasBE, kepBE, akcioBE, mennyisegraktaronBE, eszkozIdBE, platformIdBE)) {
                return "Ajándék kártya frissítve!";
            } else {
                return "Hiba az ajándék kártya frissítésénél!";
            }
        } catch (Exception ex) {
            return ex.getMessage();
        }
    }

    public static String torlesAjandekKartya(Integer idBE) {
        try {
            if (Ajandekkartya.torlesAjandekKartya(idBE)) {
                return "Ajándék kártya törölve!";
            } else {
                return "Hiba az ajándék kártya törlésénél!";
            }
        } catch (Exception ex) {
            return ex.getMessage();
        }
    }
}
