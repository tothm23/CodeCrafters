package com.codecrafterswebshop.Service;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author tothm23
 */
public class EmailService {

    public static boolean email(String cimzett, List<Map<String, Object>> termekkulcsok) {

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session;
        session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("tothm.214sz@acsjszki.hu", "TothH2**3");
            }
        });

        String html1 = "<!DOCTYPE html>\n"
                + "<html lang=\"hu\">\n"
                + "  <head>\n"
                + "    <meta charset=\"UTF-8\" />\n"
                + "    <style>\n"
                + "      * {\n"
                + "        box-sizing: border-box;\n"
                + "        margin: 0;\n"
                + "        padding: 0;\n"
                + "        font-family: Arial, Helvetica, sans-serif;\n"
                + "        color: white;\n"
                + "      }\n"
                + "\n"
                + "      html,\n"
                + "      body {\n"
                + "        background-color: #404040;\n"
                + "      }\n"
                + "\n"
                + "      .kosar {\n"
                + "        margin: 50px;\n"
                + "        border: black solid 1px;\n"
                + "        border-radius: 15px;\n"
                + "        background-color: #241f1f;\n"
                + "      }\n"
                + "\n"
                + "      .header {\n"
                + "        font-weight: bold;\n"
                + "        font-size: 20pt;\n"
                + "        padding: 20px;\n"
                + "        text-align: center;\n"
                + "      }\n"
                + "\n"
                + "      .card {\n"
                + "        display: flex;\n"
                + "        align-items: center;\n"
                + "        justify-content: space-between;\n"
                + "\n"
                + "        background-color: #404040;\n"
                + "        padding: 20px;\n"
                + "        margin: 20px;\n"
                + "\n"
                + "        border: black 1px solid;\n"
                + "        border-radius: 30px;\n"
                + "      }\n"
                + "\n"
                + "      .kosar-vegosszeg {\n"
                + "        margin: 20px;\n"
                + "        text-align: right;\n"
                + "      }\n"
                + "\n"
                + "      #kosar-vegosszeg-title {\n"
                + "        font-weight: lighter;\n"
                + "        font-size: 1.5em;\n"
                + "        margin-right: 20px;\n"
                + "        font-size: x-large;\n"
                + "      }\n"
                + "\n"
                + "      #vegosszeg {\n"
                + "        font-size: xx-large;\n"
                + "      }\n"
                + "    </style>\n"
                + "  </head>\n"
                + "\n"
                + "  <body>\n"
                + "    <div class=\"kosar\">\n"
                + "      <h2 class=\"header\">A rendelés tartalma</h2>\n"
                + "\n";

        String jatek = "";

        for (Map<String, Object> termek : termekkulcsok) {
            for (Map.Entry<String, Object> entry : termek.entrySet()) {
                System.out.println(entry.getKey() + " : " + entry.getValue());

                if (entry.getKey().equals("nev")) {
                    jatek
                            += "      <div class=\"card\">\n"
                            + "        <div>" + entry.getValue() + "</div>\n"
                            + "        <div>" + "" + "</div>\n"
                            + "      </div>\n";
                } else if (entry.getKey().equals("vegosszeg")) {
                    jatek
                            += "      <div class=\"card\">\n"
                            + "        <div>" + "" + "</div>\n"
                            + "        <div>" + entry.getValue() + "Ft</div>\n"
                            + "      </div>\n";
                }
            }
        }

        for (int i = 1; i < 4; i++) {

        }

        String html2 = "\n"
                + "      <div class=\"kosar-vegosszeg\">\n"
                + "        <strong id=\"kosar-vegosszeg-title\">Összesen:</strong>\n"
                + "        <span id=\"vegosszeg\">30 000 FT</span>\n"
                + "      </div>\n"
                + "    </div>\n"
                + "  </body>\n"
                + "</html>";

        try {
            MimeMessage message = new MimeMessage(session);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(cimzett, true));
            message.setSubject("Próba");
            message.setContent(html1 + jatek + html2, "text/html");
            Transport.send(message);

            return true;

        } catch (MessagingException me) {
            System.out.println("Hiba történt. " + me.getMessage());

        }

        return false;
    }
}
