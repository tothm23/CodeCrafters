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
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author tothm23
 */
public class EmailService {

    public static String addressee(List<Map<String, Object>> productKeys) {
        String addressee = "";

        for (Map<String, Object> product : productKeys) {
            for (Map.Entry<String, Object> entry : product.entrySet()) {

                if (entry.getKey().equals("email")) {
                    addressee = (String) entry.getValue();
                    break;
                }
            }

        }

        return addressee;
    }

    public static String userName(List<Map<String, Object>> productKeys) {
        String userName = "";

        for (Map<String, Object> product : productKeys) {
            for (Map.Entry<String, Object> entry : product.entrySet()) {

                if (entry.getKey().equals("userName")) {
                    userName = (String) entry.getValue();
                    break;
                }
            }

        }

        return userName;
    }

    public static int amount(List<Map<String, Object>> productKeys) {
        int amount = 0;

        for (Map<String, Object> product : productKeys) {
            for (Map.Entry<String, Object> entry : product.entrySet()) {

                if (entry.getKey().equals("amount")) {
                    amount += (Integer) entry.getValue();
                }
            }

        }

        return amount;
    }

    public static String htmlOrder(List<Map<String, Object>> productKeys) {
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
                + "      <h1 class=\"header\">Kedves ").append(userName(productKeys)).append("!</h1>\n"
                + "      <p class=\"szoveg\">Köszönjük a rendelését! Az alábbiakban látható a rendelés összesítése:</p>\n"
                + "\n"
                + "      <table>\n"
                + "        <tr>");

        for (String key : productKeys.get(0).keySet()) {

            if (key.equals("name")) {
                sb.append("<th>").append("Játék").append("</th>");
            }
            if (key.equals("amount")) {
                sb.append("<th>").append("Ár").append("</th>");
            }
            if (key.equals("key")) {
                sb.append("<th>").append("Termékkulcs").append("</th>");
            }

        }

        sb.append("</tr>");

        for (Map<String, Object> product : productKeys) {
            sb.append("<tr>");
            for (Map.Entry<String, Object> entry : product.entrySet()) {
                if (entry.getKey().equals("name") || entry.getKey().equals("amount")) {
                    sb.append("<td>").append(entry.getValue()).append("</td>");
                }
                if (entry.getKey().equals("key")) {
                    sb.append("<td><b>").append(entry.getValue()).append("</b></td>");
                }

            }
            sb.append("</tr>");
        }

        sb.append(" </table>\n"
                + "\n"
                + "      <h2 class=\"szoveg-jobbra\">Összesen: <b id=\"amount\">").append(amount(productKeys)).append(" FT</b></h2>\n"
                + "\n"
                + "      <p class=\"szoveg\">Üdvözlettel,</p>\n"
                + "      <p class=\"szoveg\">CodeCrafters</p>\n"
                + "    </div>\n"
                + "  </body>\n"
                + "</html>");

        return new String(sb);
    }

    public static String htmlRegistration(String userName) {
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
                + "    </style>\n"
                + "  </head>\n"
                + "\n"
                + "  <body>\n"
                + "    <div class=\"kosar\">\n"
                + "      <h1 class=\"header\">Kedves ").append(userName).append("!</h1>\n"
                + "      <p class=\"szoveg\">Köszönjük a regisztrációd! Reméljük, hogy elégedett leszel szolgáltatásainkkal, és mindent megtalálsz, amire szüksége van!</p>\n"
                + "      <br />\n"
                + "\n"
                + "      <p class=\"szoveg\">Üdvözlettel,</p>\n"
                + "      <p class=\"szoveg\">CodeCrafters</p>\n"
                + "    </div>\n"
                + "  </body>\n"
                + "</html>");

        return new String(sb);
    }

    public static boolean email(String addressee, String subject, String content) {

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

        Logger logger = LogManager.getLogger(EmailService.class.getName());
        try {
            MimeMessage message = new MimeMessage(session);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(addressee, true));
            message.setSubject(subject);
            message.setContent(content, "text/html");
            Transport.send(message);

            return true;

        } catch (MessagingException me) {
            logger.log(Level.ERROR, me);
        }

        return false;
    }
}
