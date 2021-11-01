package nl.belastingdienst.scherm;

import javax.inject.Inject;
import java.util.Scanner;

public class Hoofdschrem {
    @Inject
    private InlogScherm inlogS;
    @Inject
    private RegistreerGebruikerScherm registreerS;

    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean runApp = true;
        while (runApp) {
            System.out.println("Welkom bij de BD Marktplaats:");
            System.out.println("Menu:");
            System.out.println("1. Inloggen");
            System.out.println("2. Gebruiker registreren");
            System.out.println("0. Afsluiten");
            System.out.print("Kies door[0-2] te typen :");
            int keuze = scanner.nextInt();
            System.out.println("");
            switch (keuze) {
                case 0:
                    runApp = false;
                    break;
                case 1:
                    inlogS.run();
                    break;
                case 2:
                    registreerS.run();
                    break;
            }
        }
    }
}
