package com.codecrafterswebshop.Model;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

/**
 *
 * @author tothm23
 */
public class TestRunner {

    public static void main(String[] args) {
        runTests(UserTest.class);
        runTests(GameTest.class);
    }

    public static void runTests(Class<?> testClass) {
        Result result = JUnitCore.runClasses(testClass);

        System.out.println("\nTest Class: " + testClass.getName());
        System.out.println("Number of tests: " + result.getRunCount());
        System.out.println("Number of succesful tests: " + (result.getRunCount() - result.getFailureCount()));
        System.out.println("Number of failed tests: " + result.getFailureCount() + "\n");
    }
}
