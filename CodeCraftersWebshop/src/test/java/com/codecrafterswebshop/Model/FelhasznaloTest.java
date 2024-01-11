package com.codecrafterswebshop.Model;

import com.codecrafterswebshop.Exception.FelhasznaloException;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

/**
 *
 * @author tothm23
 */
public class FelhasznaloTest {

    @Test(expected = FelhasznaloException.class)
    public void tesztVezetekNevKivetel() throws FelhasznaloException {
        System.out.println("tesztVezetekNevKivetel");

        String vezetekNev = "";
        boolean eredmeny = Felhasznalo.vezetekNevEllenorzes(vezetekNev);

        assertTrue(eredmeny);
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void tesztVezetekNevUres() throws FelhasznaloException {
        System.out.println("tesztVezetekNevUres");

        thrown.expect(FelhasznaloException.class);
        thrown.expectMessage("A felhasználó vezetékneve nem lehet üres!");
        String vezetekNev = "";
        boolean eredmeny = Felhasznalo.vezetekNevEllenorzes(vezetekNev);

        assertTrue(eredmeny);
    }

    @Test
    public void tesztVezetekNevHosszu() throws FelhasznaloException {
        System.out.println("tesztVezetekNevHosszu");

        thrown.expect(FelhasznaloException.class);
        thrown.expectMessage("A felhasználó vezetékneve nem lehet 100 karakternél hosszabb!");

        String vezetekNev = "Elekkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk";
        boolean eredmeny = Felhasznalo.vezetekNevEllenorzes(vezetekNev);

        assertTrue(eredmeny);
    }

    @Test
    public void tesztVezetekNevSpecialis() throws FelhasznaloException {
        System.out.println("tesztVezetekNevSpecialis");

        thrown.expect(FelhasznaloException.class);
        thrown.expectMessage("A felhasználó vezetékneve nem tartalmazhat speciális karaktert!");

        String vezetekNev = "Elek?";
        boolean eredmeny = Felhasznalo.vezetekNevEllenorzes(vezetekNev);

        assertTrue(eredmeny);
    }

    @Test
    public void tesztKeresztNevUres() throws FelhasznaloException {
        System.out.println("tesztKeresztNevUres");

        thrown.expect(FelhasznaloException.class);
        thrown.expectMessage("A felhasználó keresztneve nem lehet üres!");

        String vezetekNev = "";
        boolean eredmeny = Felhasznalo.keresztNevEllenorzes(vezetekNev);

        assertTrue(eredmeny);
    }

    @Test
    public void tesztKeresztNevHosszu() throws FelhasznaloException {
        System.out.println("tesztKeresztNevHosszu");

        thrown.expect(FelhasznaloException.class);
        thrown.expectMessage("A felhasználó keresztneve nem lehet 100 karakternél hosszabb!");

        String vezetekNev = "Elekkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk";
        boolean eredmeny = Felhasznalo.keresztNevEllenorzes(vezetekNev);

        assertTrue(eredmeny);
    }

    @Test
    public void tesztKeresztNevSpecialis() throws FelhasznaloException {
        System.out.println("tesztKeresztNevSpecialis");

        thrown.expect(FelhasznaloException.class);
        thrown.expectMessage("A felhasználó keresztneve nem tartalmazhat speciális karaktert!");

        String vezetekNev = "Elek?";
        boolean eredmeny = Felhasznalo.keresztNevEllenorzes(vezetekNev);

        assertTrue(eredmeny);
    }

    @Test
    public void tesztEmailUres() throws FelhasznaloException {
        System.out.println("tesztEmailUres");

        thrown.expect(FelhasznaloException.class);
        thrown.expectMessage("A felhasználó emailje nem lehet üres!");

        String email = "";
        boolean eredmeny = Felhasznalo.emailEllenorzes(email);

        assertTrue(eredmeny);
    }

    @Test
    public void tesztEmailHosszu() throws FelhasznaloException {
        System.out.println("tesztEmailHosszu");

        thrown.expect(FelhasznaloException.class);
        thrown.expectMessage("A felhasználó emailje nem lehet 100 karakternél hosszabb!");

        String email = "kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk@gmail.com";
        boolean eredmeny = Felhasznalo.emailEllenorzes(email);

        assertTrue(eredmeny);
    }

    @Test
    public void tesztEmailValos() throws FelhasznaloException {
        System.out.println("tesztEmailValos");

        thrown.expect(FelhasznaloException.class);
        thrown.expectMessage("A felhasználó emailjének tartalmaznia kell a @ karaktert!");

        String email = "kkgmail.com";
        boolean eredmeny = Felhasznalo.emailEllenorzes(email);

        assertTrue(eredmeny);
    }

    @Test
    public void tesztJelszoUres() throws FelhasznaloException {
        System.out.println("tesztJelszoUres");

        thrown.expect(FelhasznaloException.class);
        thrown.expectMessage("A felhasználó jelszava nem lehet üres!");

        String jelszo = "";
        boolean eredmeny = Felhasznalo.jelszoEllenorzes(jelszo);

        assertTrue(eredmeny);
    }

    @Test
    public void tesztJelszoHosszu() throws FelhasznaloException {
        System.out.println("tesztJelszoHosszu");

        thrown.expect(FelhasznaloException.class);
        thrown.expectMessage("A felhasználó jelszava nem lehet 100 karakternél hosszabb!");

        String jelszo = "kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk123456789*";
        boolean eredmeny = Felhasznalo.jelszoEllenorzes(jelszo);

        assertTrue(eredmeny);
    }

    @Test
    public void tesztJelszoRovid() throws FelhasznaloException {
        System.out.println("tesztJelszoRovid");

        thrown.expect(FelhasznaloException.class);
        thrown.expectMessage("A felhasználó jelszava nem lehet 8 karakternél rövidebb!");

        String jelszo = "asd123";
        boolean eredmeny = Felhasznalo.jelszoEllenorzes(jelszo);

        assertTrue(eredmeny);
    }

    @Test
    public void tesztJelszoSzam() throws FelhasznaloException {
        System.out.println("tesztJelszoSzam");

        thrown.expect(FelhasznaloException.class);
        thrown.expectMessage("A felhasználó jelszavának tartalmaznia kell számot!");

        String jelszo = "asdASDqwe*";
        boolean eredmeny = Felhasznalo.jelszoEllenorzes(jelszo);

        assertTrue(eredmeny);
    }

    @Test
    public void tesztJelszoBetu() throws FelhasznaloException {
        System.out.println("tesztJelszoBetu");

        thrown.expect(FelhasznaloException.class);
        thrown.expectMessage("A felhasználó jelszavának tartalmaznia kell betűt!");

        String jelszo = "123789456*";
        boolean eredmeny = Felhasznalo.jelszoEllenorzes(jelszo);

        assertTrue(eredmeny);
    }

    @Test
    public void tesztJelszoSpecialis() throws FelhasznaloException {
        System.out.println("tesztJelszoSpecialis");

        thrown.expect(FelhasznaloException.class);
        thrown.expectMessage("A felhasználó jelszavának tartalmaznia kell speciális karaktert!");

        String jelszo = "asdASD123q";
        boolean eredmeny = Felhasznalo.jelszoEllenorzes(jelszo);

        assertTrue(eredmeny);
    }
}
