package com.codecrafterswebshop.Model;

import com.codecrafterswebshop.Exception.UserException;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

/**
 *
 * @author tothm23
 */
public class UserTest {

    @Test(expected = UserException.class)
    public void testLastNameException() throws UserException {
        System.out.println("testLastNameException");

        String lastName = "";
        boolean result = User.checkLastName(lastName);

        assertTrue(result);
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testLastNameEmpty() throws UserException {
        System.out.println("testLastNameEmpty");

        thrown.expect(UserException.class);
        thrown.expectMessage("A felhasználó vezetékneve nem lehet üres!");
        String lastName = "";
        boolean result = User.checkLastName(lastName);

        assertTrue(result);
    }

    @Test
    public void testLastNameLong() throws UserException {
        System.out.println("testLastNameLong");

        thrown.expect(UserException.class);
        thrown.expectMessage("A felhasználó vezetékneve nem lehet 100 karakternél hosszabb!");

        String lastName = "Elekkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk";
        boolean result = User.checkLastName(lastName);

        assertTrue(result);
    }

    @Test
    public void testLastNameSpecial() throws UserException {
        System.out.println("testLastNameSpecial");

        thrown.expect(UserException.class);
        thrown.expectMessage("A felhasználó vezetékneve nem tartalmazhat speciális karaktert!");

        String lastName = "Elek?";
        boolean result = User.checkLastName(lastName);

        assertTrue(result);
    }

    @Test
    public void testFirstNameEmpty() throws UserException {
        System.out.println("testFirstNameEmpty");

        thrown.expect(UserException.class);
        thrown.expectMessage("A felhasználó keresztneve nem lehet üres!");

        String firstName = "";
        boolean result = User.checkFirstName(firstName);

        assertTrue(result);
    }

    @Test
    public void testFirstNameLong() throws UserException {
        System.out.println("testFirstNameLong");

        thrown.expect(UserException.class);
        thrown.expectMessage("A felhasználó keresztneve nem lehet 100 karakternél hosszabb!");

        String firstName = "Elekkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk";
        boolean result = User.checkFirstName(firstName);

        assertTrue(result);
    }

    @Test
    public void testFirstNameSpecial() throws UserException {
        System.out.println("testFirstNameSpecial");

        thrown.expect(UserException.class);
        thrown.expectMessage("A felhasználó keresztneve nem tartalmazhat speciális karaktert!");

        String firstName = "Elek?";
        boolean result = User.checkFirstName(firstName);

        assertTrue(result);
    }

    @Test
    public void testEmailEmpty() throws UserException {
        System.out.println("testEmailEmpty");

        thrown.expect(UserException.class);
        thrown.expectMessage("A felhasználó emailje nem lehet üres!");

        String email = "";
        boolean result = User.checkEmail(email);

        assertTrue(result);
    }

    @Test
    public void testEmailLong() throws UserException {
        System.out.println("testEmailLong");

        thrown.expect(UserException.class);
        thrown.expectMessage("A felhasználó emailje nem lehet 100 karakternél hosszabb!");

        String email = "kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk@gmail.com";
        boolean result = User.checkEmail(email);

        assertTrue(result);
    }

    @Test
    public void testEmailValid() throws UserException {
        System.out.println("testEmailValid");

        thrown.expect(UserException.class);
        thrown.expectMessage("A felhasználó emailjének tartalmaznia kell a @ karaktert!");

        String email = "kkgmail.com";
        boolean result = User.checkEmail(email);

        assertTrue(result);
    }

    @Test
    public void testPasswordEmpty() throws UserException {
        System.out.println("testPasswordEmpty");

        thrown.expect(UserException.class);
        thrown.expectMessage("A felhasználó jelszava nem lehet üres!");

        String password = "";
        boolean result = User.checkPassword(password);

        assertTrue(result);
    }

    @Test
    public void testPasswordLong() throws UserException {
        System.out.println("testPasswordLong");

        thrown.expect(UserException.class);
        thrown.expectMessage("A felhasználó jelszava nem lehet 100 karakternél hosszabb!");

        String password = "kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk123456789*";
        boolean result = User.checkPassword(password);

        assertTrue(result);
    }

    @Test
    public void testPasswordShort() throws UserException {
        System.out.println("testPasswordShort");

        thrown.expect(UserException.class);
        thrown.expectMessage("A felhasználó jelszava nem lehet 8 karakternél rövidebb!");

        String password = "asd123";
        boolean result = User.checkPassword(password);

        assertTrue(result);
    }

    @Test
    public void testPasswordNumber() throws UserException {
        System.out.println("testPasswordNumber");

        thrown.expect(UserException.class);
        thrown.expectMessage("A felhasználó jelszavának tartalmaznia kell számot!");

        String password = "asdASDqwe*";
        boolean result = User.checkPassword(password);

        assertTrue(result);
    }

    @Test
    public void testPasswordLetter() throws UserException {
        System.out.println("testPasswordLetter");

        thrown.expect(UserException.class);
        thrown.expectMessage("A felhasználó jelszavának tartalmaznia kell betűt!");

        String password = "123789456*";
        boolean result = User.checkPassword(password);

        assertTrue(result);
    }

    @Test
    public void testPasswordSpecial() throws UserException {
        System.out.println("testPasswordSpecial");

        thrown.expect(UserException.class);
        thrown.expectMessage("A felhasználó jelszavának tartalmaznia kell speciális karaktert!");

        String password = "asdASD123q";
        boolean result = User.checkPassword(password);

        assertTrue(result);
    }
}
