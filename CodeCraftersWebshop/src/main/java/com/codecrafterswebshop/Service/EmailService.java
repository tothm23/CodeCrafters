package com.codecrafterswebshop.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import models.SendEnhancedRequestBody;
import models.SendRequestMessage;
import services.Courier;
import services.SendService;

/**
 *
 * @author tothm23
 */
public class EmailService {

    public static String cimzett(List<Map<String, Object>> termekkulcsok) {
        String cimzett = "";

        for (Map<String, Object> termek : termekkulcsok) {
            for (Map.Entry<String, Object> entry : termek.entrySet()) {

                if (entry.getKey().equals("email")) {
                    cimzett = (String) entry.getValue();
                    break;
                }
            }

        }

        return cimzett;
    }

    public static String felhasznaloNev(List<Map<String, Object>> termekkulcsok) {
        String felhasznaloNev = "";

        for (Map<String, Object> termek : termekkulcsok) {
            for (Map.Entry<String, Object> entry : termek.entrySet()) {

                if (entry.getKey().equals("felhasznaloNev")) {
                    felhasznaloNev = (String) entry.getValue();
                    break;
                }
            }

        }

        return felhasznaloNev;
    }

    public static boolean email(List<Map<String, Object>> termekkulcsok) {

        Courier.init("pk_prod_J3D8CY0BA74V2MKX0RQD6HSHFHX1");

        SendEnhancedRequestBody sendEnhancedRequestBody = new SendEnhancedRequestBody();
        SendRequestMessage sendRequestMessage = new SendRequestMessage();

        HashMap<String, String> to = new HashMap<>();
        to.put("email", cimzett(termekkulcsok));
        sendRequestMessage.setTo(to);

        HashMap<String, String> content = new HashMap<>();
        content.put("title", "Megrendelés értesítő");

        content.put("body", "Kedves {{felhasznaloNev}}!\n\n Köszönjük, hogy nálunk vásároltál!\n\n CodeCrafters");
        sendRequestMessage.setContent(content);

        HashMap<String, Object> data = new HashMap<>();
        data.put("felhasznaloNev", felhasznaloNev(termekkulcsok));

        sendRequestMessage.setData(data);
        sendEnhancedRequestBody.setMessage(sendRequestMessage);

        try {
            new SendService().sendEnhancedMessage(sendEnhancedRequestBody);
            return true;
        } catch (IOException e) {
            return false;
        }

    }
}
