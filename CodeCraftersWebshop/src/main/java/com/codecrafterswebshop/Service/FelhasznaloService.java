package com.codecrafterswebshop.Service;

import com.codecrafterswebshop.Model.Felhasznalo;
import java.util.List;

/**
 *
 * @author tothm23
 */
public class FelhasznaloService {

    public static List<Felhasznalo> felhasznalok() {
        return Felhasznalo.felhasznalok();
    }
}
