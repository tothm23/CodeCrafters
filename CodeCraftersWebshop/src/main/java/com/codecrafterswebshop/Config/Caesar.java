package com.codecrafterswebshop.Config;

/**
 *
 * @author tothm23
 */
public class Caesar {

    public static final int OFFSET = 12;

    public static String encrypt(String input, int offset) {
        StringBuilder encrypted = new StringBuilder();
        int[] getInputBytes = new int[input.length()];

        for (byte i = 0; i < getInputBytes.length; i++) {
            getInputBytes[i] = checkEncryptOffset(input.charAt(i), offset);
            encrypted.append((char) getInputBytes[i]);
        }

        return new String(encrypted);
    }

    private static int checkEncryptOffset(int input, int offset) {
        if (input > 126 || input + offset > 126) {
            return (33 + input - 126 - 1) + offset;
        } else if (input < 33 || input + offset < 33) {
            return (126 + input - 33 + 1) + offset;
        }

        return input + offset;
    }

}
