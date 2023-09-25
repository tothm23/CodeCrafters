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
}
