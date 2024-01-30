package com.codecrafterswebshop.Service;

import com.codecrafterswebshop.Model.Game;
import java.util.Map;
import org.json.JSONObject;

/**
 *
 * @author tothm23
 */
public class GameService {

    public static JSONObject game(Integer idBe) {
        Map<String, Object> game = Game.game(idBe);
        JSONObject obj = new JSONObject();

        for (Map.Entry<String, Object> set : game.entrySet()) {
            obj.put(set.getKey(), set.getValue());
        }

        return obj;
    }

}
