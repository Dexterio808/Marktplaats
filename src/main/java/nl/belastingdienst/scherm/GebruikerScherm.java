package nl.belastingdienst.scherm;

import lombok.extern.slf4j.Slf4j;
import nl.belastingdienst.util.CurrentUser;

import javax.inject.Inject;
import java.util.Scanner;

import static nl.belastingdienst.util.Util.print;
import static nl.belastingdienst.util.Util.readLine;

@Slf4j
public class GebruikerScherm {
    @Inject
    private Scanner sc;
    @Inject
    private RegelementenScherm regelementenScherm;
    @Inject
    private ArtikelAanmaken artikelAanmakenScherm;
    @Inject
    private RaadpleegMijnArtikelenScherm raadpleegMijnArtikelenScherm;

    void run() {
        print("Welkom " + CurrentUser.gebruiker.getName());
        if (!CurrentUser.gebruiker.isAgreedTerms()) {
            regelementenScherm.run();
            if (CurrentUser.gebruiker.isAgreedTerms()) {
                runGebruikerMenu();
            }
        } else {
            runGebruikerMenu();
        }
    }

    private void runGebruikerMenu() {
        boolean runMenu = true;
        while (runMenu) {

            print("GebruikerMenu:");
            print("_".repeat(15));
            print("(1) - Artikel Aanmaken");
            print("(2) - Mijn Artikelen Raadplegen");
            print("(3) - Artikel kopen");
            print("(x) - Uitloggen");
            print("Keuze: ");

            String input = readLine();
            switch (input) {
                case "1":
                    artikelAanmakenScherm.run();
                    break;
                case "2":
                    raadpleegMijnArtikelenScherm.run();
                    break;
                case "3":
                    print("NIP");
                    break;
                case "x":
                    runMenu = false;
                    break;
                default:
                    print("Invalid input");
                    break;
            }
        }
    }


}
