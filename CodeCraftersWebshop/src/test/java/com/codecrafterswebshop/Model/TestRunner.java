package com.codecrafterswebshop.Model;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

/**
 *
 * @author tothm23
 */
public class TestRunner {

    public static void main(String[] args) {
        runTests(FelhasznaloTest.class);
    }

    public static void runTests(Class<?> testClass) {
        Result eredmeny = JUnitCore.runClasses(testClass);

        System.out.println("\nTesztelt osztály: " + testClass.getName());
        System.out.println("Futtatott tesztek száma: " + eredmeny.getRunCount());
        System.out.println("Sikeres tesztek száma: " + (eredmeny.getRunCount() - eredmeny.getFailureCount()));
        System.out.println("Hibás tesztek száma: " + eredmeny.getFailureCount() + "\n");
    }
}
