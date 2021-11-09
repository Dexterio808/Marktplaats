package nl.belastingdienst.scherm;

import javax.inject.Inject;
import java.util.Scanner;

import static nl.belastingdienst.util.Util.*;

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
            print("Welkom bij de BD Marktplaats:");
            print("HoofdMenu:");
            print("_".repeat(15));
            print("(1) - Inloggen");
            print("(2) - Gebruiker registreren");
            print("(x) - Afsluiten");

            switch (prompt("Keuze: ")) {
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
