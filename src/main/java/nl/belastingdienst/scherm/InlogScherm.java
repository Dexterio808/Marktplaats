package nl.belastingdienst.scherm;

import javax.inject.*;
import java.io.*;
import java.util.Scanner;

public class InlogScherm {



    public void run(){
        Scanner scanner = new Scanner(System.in);
        boolean runInlog = true;
        while(runInlog) {
            System.out.println("0.exit");
            System.out.println("1.wwtest");
            int keuze = Integer.parseInt(scanner.nextLine());
            switch (keuze) {
                case 0:
                    runInlog = false;
                    System.out.println("Exit");
                    break;
                case 1:
                    wachtwoordTest();
                    break;
                default:
                    System.out.println("Invalid input");
                    break;
            }
        }
    }

    void wachtwoordTest(){
        Console csl = System.console();
        System.out.println("wachtwoord test: ");
        String fmt = "%2$5s %3$10s%n";
        char[] test = csl.readPassword(fmt, "Password: ");
        test.toString();
    }

}
