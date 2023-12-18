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

    public static int vegosszeg(List<Map<String, Object>> termekkulcsok) {
        int vegosszeg = 0;

        for (Map<String, Object> termek : termekkulcsok) {
            for (Map.Entry<String, Object> entry : termek.entrySet()) {

                if (entry.getKey().equals("vegosszeg")) {
                    vegosszeg += (Integer) entry.getValue();
                }
            }

        }

        return vegosszeg;
    }

    public static String html(List<Map<String, Object>> termekkulcsok) {
        StringBuilder sb = new StringBuilder();

        sb.append("<!DOCTYPE html>\n"
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
                + "      }\n"
                + "\n"
                + "      .szoveg {\n"
                + "        margin: 10px 20px;\n"
                + "      }\n"
                + "\n"
                + "      table {\n"
                + "        border-collapse: collapse;\n"
                + "        border-radius: 20px;\n"
                + "\n"
                + "        margin: 40px auto;\n"
                + "        background-color: #404040;\n"
                + "        color: white;\n"
                + "      }\n"
                + "\n"
                + "      th {\n"
                + "        padding: 20px 20px 10px;\n"
                + "        text-align: left;\n"
                + "      }\n"
                + "\n"
                + "      td {\n"
                + "        padding: 10px 20px;\n"
                + "      }\n"
                + "\n"
                + "      .szoveg-jobbra{\n"
                + "        margin: 20px;\n"
                + "        text-align: right;\n"
                + "      }\n"
                + "\n"
                + "      \n"
                + "    </style>\n"
                + "  </head>\n"
                + "\n"
                + "  <body>\n"
                + "    <div class=\"kosar\">\n"
                + "      <h1 class=\"header\">Kedves ").append(felhasznaloNev(termekkulcsok)).append("!</h1>\n"
                + "      <p class=\"szoveg\">Köszönjük a rendelését! Az alábbiakban látható a rendelés összesítése:</p>\n"
                + "\n"
                + "      <table>\n"
                + "        <tr>");

        for (String key : termekkulcsok.get(0).keySet()) {

            if (key.equals("nev")) {
                sb.append("<th>").append("Játék").append("</th>");
            }
            if (key.equals("vegosszeg")) {
                sb.append("<th>").append("Ár").append("</th>");
            }
            if (key.equals("kulcs")) {
                sb.append("<th>").append("Termékkulcs").append("</th>");
            }

        }

        sb.append("</tr>");

        for (Map<String, Object> termek : termekkulcsok) {
            sb.append("<tr>");
            for (Map.Entry<String, Object> entry : termek.entrySet()) {
                if (entry.getKey().equals("nev") || entry.getKey().equals("vegosszeg")) {
                    sb.append("<td>").append(entry.getValue()).append("</td>");
                }
                if (entry.getKey().equals("kulcs")) {
                    sb.append("<td><b>").append(entry.getValue()).append("</b></td>");
                }

            }
            sb.append("</tr>");
        }

        sb.append(" </table>\n"
                + "\n"
                + "      <h2 class=\"szoveg-jobbra\">Összesen: <b id=\"vegosszeg\">").append(vegosszeg(termekkulcsok)).append(" FT</b></h2>\n"
                + "\n"
                + "      <p class=\"szoveg\">Üdvözlettel,</p>\n"
                + "      <p class=\"szoveg\">CodeCrafters</p>\n"
                + "    </div>\n"
                + "  </body>\n"
                + "</html>");

        return new String(sb);
    }

    public static boolean email(List<Map<String, Object>> termekkulcsok) {

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session;
        session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("gaminghours32@gmail.com", "psmumawhditzojgl");
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(cimzett(termekkulcsok), true));
            message.setSubject("Megrendelésed összeállítottuk");
            message.setContent(html(termekkulcsok), "text/html");
            Transport.send(message);

            return true;

        } catch (MessagingException me) {
            System.out.println("Hiba történt. " + me.getMessage());
        }

        return false;
    }
}
