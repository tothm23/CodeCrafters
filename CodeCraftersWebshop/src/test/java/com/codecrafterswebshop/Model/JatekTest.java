package com.codecrafterswebshop.Model;

import com.codecrafterswebshop.Exception.JatekException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertTrue;

/**
 *
 * @author tothm23
 */
public class JatekTest {

    @Test(expected = JatekException.class)
    public void tesztNevKivetel() throws JatekException {
        System.out.println("tesztNevKivetel");

        String nev = "";
        boolean eredmeny = Jatek.nevEllenorzes(nev);

        assertTrue(eredmeny);
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void tesztNevUres() throws JatekException {
        System.out.println("tesztNevUres");

        thrown.expect(JatekException.class);
        thrown.expectMessage("A játék neve nem lehet üres!");

        String nev = "";
        boolean eredmeny = Jatek.nevEllenorzes(nev);

        assertTrue(eredmeny);
    }

    @Test
    public void tesztNevHosszu() throws JatekException {
        System.out.println("tesztNevHosszu");

        thrown.expect(JatekException.class);
        thrown.expectMessage("A játék neve nem lehet 100 karakternél hosszabb!");

        String nev = "Minecraftkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk";
        boolean eredmeny = Jatek.nevEllenorzes(nev);

        assertTrue(eredmeny);
    }

    @Test
    public void tesztArNegativ() throws JatekException {
        System.out.println("tesztArNegativ");

        thrown.expect(JatekException.class);
        thrown.expectMessage("A játék ára nem lehet kisebb, mint 0!");

        int ar = -1;
        boolean eredmeny = Jatek.arEllenorzes(ar);

        assertTrue(eredmeny);
    }

    @Test
    public void tesztLeirasUres() throws JatekException {
        System.out.println("tesztLeirasUres");

        thrown.expect(JatekException.class);
        thrown.expectMessage("A játék leírása nem lehet üres!");

        String leiras = "";
        boolean eredmeny = Jatek.leirasEllenorzes(leiras);

        assertTrue(eredmeny);
    }

    @Test
    public void tesztKepUres() throws JatekException {
        System.out.println("tesztKepUres");

        thrown.expect(JatekException.class);
        thrown.expectMessage("A játék képe nem lehet üres!");

        String kep = "";
        boolean eredmeny = Jatek.kepEllenorzes(kep);

        assertTrue(eredmeny);
    }

    @Test
    public void tesztKorhatar() throws JatekException {
        System.out.println("tesztKorhatar");

        thrown.expect(JatekException.class);
        thrown.expectMessage("A játék korhatára nem térhet el a PEGI számoktól!");

        int korhatar = 11;
        boolean eredmeny = Jatek.korhatarEllenorzes(korhatar);

        assertTrue(eredmeny);
    }

    @Test
    public void tesztAkcioNegativ() throws JatekException {
        System.out.println("tesztAkcioNegativ");

        thrown.expect(JatekException.class);
        thrown.expectMessage("A játék akciója nem lehet kisebb, mint 0!");

        int akcio = -1;
        boolean eredmeny = Jatek.akcioEllenorzes(akcio);

        assertTrue(eredmeny);
    }

    @Test
    public void tesztAkcio() throws JatekException {
        System.out.println("tesztAkcio");

        thrown.expect(JatekException.class);
        thrown.expectMessage("A játék akciója nem lehet nagyobb, mint 100!");

        int akcio = 101;
        boolean eredmeny = Jatek.akcioEllenorzes(akcio);

        assertTrue(eredmeny);
    }

    @Test
    public void tesztMennyisegraktaronNegativ() throws JatekException {
        System.out.println("tesztMennyisegraktaronNegativ");

        thrown.expect(JatekException.class);
        thrown.expectMessage("A játék mennyisegraktaronja nem lehet kisebb, mint 0!");

        int mennyisegraktaron = -1;
        boolean eredmeny = Jatek.mennyisegraktaronEllenorzes(mennyisegraktaron);

        assertTrue(eredmeny);
    }
}
