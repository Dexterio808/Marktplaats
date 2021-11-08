package nl.belastingdienst.scherm;

import lombok.extern.slf4j.Slf4j;
import nl.belastingdienst.util.CurrentUser;

import javax.inject.Inject;
import java.util.Scanner;

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
        System.out.println("Welkom " + CurrentUser.gebruiker.getName());
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

            System.out.println("GebruikerMenu:");
            System.out.println("_".repeat(15));
            System.out.println("(1) - Artikel Aanmaken");
            System.out.println("(2) - Mijn Artikelen Raadplegen");
            System.out.println("(3) - Artikel kopen");
            System.out.println("(x) - Uitloggen");
            System.out.println("Keuze: ");

            String input = sc.nextLine();
            switch (input) {
                case "1":
                    artikelAanmakenScherm.run();
                    break;
                case "2":
                    raadpleegMijnArtikelenScherm.run();
                    break;
                case "3":
                    System.out.println("NIP");
                    break;
                case "x":
                    runMenu = false;
                    break;
                default:
                    System.out.println("Invalid input");
                    break;
            }
        }
    }


}
