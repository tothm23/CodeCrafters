package com.codecrafterswebshop.Model;

import com.codecrafterswebshop.Exception.GameException;
import static org.junit.Assert.assertTrue;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 *
 * @author tothm23
 */
public class GameTest {

    @Test(expected = GameException.class)
    public void testNameException() throws GameException {
        System.out.println("testNameException");

        String name = "";
        boolean result = Game.checkName(name);

        assertTrue(result);
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testNameEmpty() throws GameException {
        System.out.println("testNameEmpty");

        thrown.expect(GameException.class);
        thrown.expectMessage("A játék neve nem lehet üres!");
        String name = "";
        boolean result = Game.checkName(name);

        assertTrue(result);
    }

    @Test
    public void testNameLong() throws GameException {
        System.out.println("testNameEmpty");

        thrown.expect(GameException.class);
        thrown.expectMessage("A játék neve nem lehet 100 karakternél hosszabb!");
        String name = "Forzakkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk";
        boolean result = Game.checkName(name);

        assertTrue(result);
    }

    @Test
    public void testPrice() throws GameException {
        System.out.println("testPrice");

        thrown.expect(GameException.class);
        thrown.expectMessage("A játék ára nem lehet kisebb, mint 0!");
        int price = -1;
        boolean result = Game.checkPrice(price);

        assertTrue(result);
    }

    @Test
    public void testDescription() throws GameException {
        System.out.println("testDescription");

        thrown.expect(GameException.class);
        thrown.expectMessage("A játék leírása nem lehet üres!");
        String description = "";
        boolean result = Game.checkDescription(description);

        assertTrue(result);
    }

    @Test
    public void testImage() throws GameException {
        System.out.println("testImage");

        thrown.expect(GameException.class);
        thrown.expectMessage("A játék képe nem lehet üres!");
        String image = "";
        boolean result = Game.checkImage(image);

        assertTrue(result);
    }

    @Test
    public void testAgeLimit() throws GameException {
        System.out.println("testAgeLimit");

        thrown.expect(GameException.class);
        thrown.expectMessage("A játék korhatára nem térhet el a PEGI számoktól!");
        int age = 13;
        boolean result = Game.checkAgeLimit(age);

        assertTrue(result);
    }

    @Test
    public void testDiscountZero() throws GameException {
        System.out.println("testDiscountZero");

        thrown.expect(GameException.class);
        thrown.expectMessage("A játék akciója nem lehet kisebb, mint 0!");
        int discount = -1;
        boolean result = Game.checkDiscount(discount);

        assertTrue(result);
    }

    @Test
    public void testDiscountHigh() throws GameException {
        System.out.println("testDiscountHigh");

        thrown.expect(GameException.class);
        thrown.expectMessage("A játék akciója nem lehet nagyobb, mint 100!");
        int discount = 101;
        boolean result = Game.checkDiscount(discount);

        assertTrue(result);
    }

    @Test
    public void testInStock() throws GameException {
        System.out.println("testInStock");

        thrown.expect(GameException.class);
        thrown.expectMessage("A játék raktáron lévő mennyisége nem lehet kisebb, mint 0!");
        int price = -1;
        boolean result = Game.checkInStock(price);

        assertTrue(result);
    }

}
