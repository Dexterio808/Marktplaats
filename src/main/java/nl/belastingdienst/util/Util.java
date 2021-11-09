package nl.belastingdienst.util;

import java.util.Scanner;

public class Util {

    public static Scanner scanner;

    public static boolean testmode = false;

    public static void print(String s) {
        System.out.println(s);
    }

    public static String prompt(String message) {
        System.out.println(message);
        return readLine();
    }

    public static String readLine() {
        String line = scanner.hasNextLine() ? scanner.nextLine() : "NO INPUT AVAILABLE";
        if (testmode) {
            print(line);
            sleep(1000);
        }
        return line;
    }

    public static void sleep(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }
}
