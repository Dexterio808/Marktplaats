package nl.belastingdienst.scherm;

import javax.inject.Inject;
import java.util.Scanner;

public class Hoofdschrem {
    @Inject
    private InlogScherm inlogS;
    @Inject
    private RegistreerGebruikerScherm registreerS;
    @Inject
    private Scanner sc;

    public void run() {

        boolean runApp = true;
        while (runApp) {
            System.out.println("Welkom bij de BD Marktplaats:");
            System.out.println("HoofdMenu:");
            System.out.println("_".repeat(15));
            System.out.println("(1) - Inloggen");
            System.out.println("(2) - Gebruiker registreren");
            System.out.println("(x) - Afsluiten");
            System.out.println("Keuze:");
            String keuze = sc.nextLine();
            System.out.println("");
            switch (keuze) {
                case "x":
                    runApp = false;
                    break;
                case "1":
                    inlogS.run();
                    break;
                case "2":
                    registreerS.run();
                    break;
                default:
                    run();
                    break;
            }
        }
    }
}
