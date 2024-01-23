package com.codecrafterswebshop.Service;

import com.codecrafterswebshop.Model.Jatek;
import java.util.Map;
import org.json.JSONObject;

/**
 *
 * @author tothm23
 */
public class JatekService {

    public static JSONObject jatek(Integer idBe) {
        Map<String, Object> jatek = Jatek.jatek(idBe);
        JSONObject obj = new JSONObject();

        for (Map.Entry<String, Object> set : jatek.entrySet()) {
            obj.put(set.getKey(), set.getValue());
        }

        return obj;
    }

}
