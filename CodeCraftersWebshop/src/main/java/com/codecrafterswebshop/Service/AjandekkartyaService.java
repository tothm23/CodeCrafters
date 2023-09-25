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
}
