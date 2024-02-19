package com.codecrafterswebshop.Service;

import com.codecrafterswebshop.Config.Token;
import com.codecrafterswebshop.Exception.UserException;
import com.codecrafterswebshop.Model.User;
import java.util.Map;
import org.json.JSONObject;

/**
 *
 * @author tothm23
 */
public class UserService {

    public static JSONObject user(Integer idIN) {
        Map<String, Object> user = User.user(idIN);
        JSONObject obj = new JSONObject();

        for (Map.Entry<String, Object> set : user.entrySet()) {
            obj.put(set.getKey(), set.getValue());
        }

        return obj;
    }

    public static String registration(String userNameIN, String lastNameIN, String firstNameIN,
            String emailIN, String passwordIN) {
        try {
            String content = EmailService.htmlRegistration(userNameIN);
            if (!User.checkUsernameUnique(userNameIN)) {
                return "Hibás felhasználónév!";
            } else if (!User.checkUsername(userNameIN)) {
                return "Hibás felhasználónév!";
            } else if (!User.checkLastName(lastNameIN)) {
                return "Hibás vezetéknév!";
            } else if (!User.checkFirstName(firstNameIN)) {
                return "Hibás keresztnév!";
            } else if (!User.checkEmailUnique(emailIN)) {
                return "Hibás email!";
            } else if (!User.checkEmail(emailIN)) {
                return "Hibás email!";
            } else if (!User.checkPassword(passwordIN)) {
                return "Hibás jelszó!";
            } else if (!EmailService.email(emailIN, "Sikeres regisztráció", content)) {
                return "Hiba az email küldésénél!";
            } else if (User.registration(userNameIN, lastNameIN, firstNameIN,
                    emailIN, passwordIN)) {
                return "Sikeres regisztráció!";
            } else {
                return "Hiba a regisztráció során!";
            }
        } catch (UserException ex) {
            return ex.getMessage();
        }
    }

    public static JSONObject login(String userNameIN, String passwordIN) {

        JSONObject obj = new JSONObject();
        User u = User.login(userNameIN, passwordIN);
        String token = Token.create(u, 600000);

        if (u.getId() != null) {
            obj.put("token", token);
        } else {
            obj.put("token", "");
        }

        return obj;
    }

    public static String updateUser(Integer idIN, String userNameIN, String lastNameIN, String firstNameIN,
            String passwordIN) {
        try {
            if (user(idIN).length() == 0) {
                return "A Felhasználó nem található!";
            } else if (!User.checkUsernameUnique(userNameIN)) {
                return "Hibás felhasználónév!";
            } else if (!User.checkUsername(userNameIN)) {
                return "Hibás felhasználónév!";
            } else if (!User.checkLastName(lastNameIN)) {
                return "Hibás vezetéknév!";
            } else if (!User.checkFirstName(firstNameIN)) {
                return "Hibás keresztnév!";
            } else if (!User.checkPassword(passwordIN)) {
                return "Hibás jelszó!";
            } else if (User.updateUser(idIN, userNameIN, lastNameIN, firstNameIN,
                    passwordIN)) {
                return "Felhasználó frissítve!";
            } else {
                return "Hiba a Felhasználó frissítésénél!";
            }
        } catch (UserException ex) {
            return ex.getMessage();
        }
    }

    public static String deleteUser(Integer idIN) {
        try {
            if (user(idIN).length() == 0) {
                return "A Felhasználó nem található!";
            } else if (User.deleteUser(idIN)) {
                return "Felhasználó törölve!";
            } else {
                return "Hiba a Felhasználó törlésénél!";
            }
        } catch (Exception ex) {
            return ex.getMessage();
        }
    }

}
