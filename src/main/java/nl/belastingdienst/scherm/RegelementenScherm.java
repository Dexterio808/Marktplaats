package nl.belastingdienst.scherm;

import nl.belastingdienst.util.CurrentUser;

import javax.inject.Inject;
import java.util.Scanner;

public class RegelementenScherm {
    @Inject
    private Scanner sc;

    public void run(){
        termsAndConditions();
    }

    private void termsAndConditions() {
        System.out.println("Je bent nog niet akkoord gegaan met het regelement.");
        boolean completed = false;
        while (!completed) {
            System.out.println("Regelement Menu:");
            System.out.println("_".repeat(15));
            System.out.println("(1) - Instemmen regelement");
            System.out.println("(2) - Regelement in zien");
            System.out.println("(x) - Uitloggen en terug naar inlogscherm");
            String input = sc.nextLine();
            switch (input) {
                case "x":
                    System.out.println("Terug naar Inlogscherm");
                    CurrentUser.gebruiker = null;
                    completed = true;
                    break;
                case "1":
                    if (AkkoordGaan()) {completed = true;}
                    break;
                case "2":
                    regelementInzien();
                    break;
                default:
                    System.out.println("Invalid input");
                    break;

            }
        }
    }

    private void regelementInzien() {
        System.out.println("Regelement:");
        System.out.println("_".repeat(15));
        System.out.println("1. Scam's verboden.");
        System.out.println("2. verplichte betaling. ");
        System.out.println("_".repeat(15));
        System.out.println("(x) - Terug");
        sc.nextLine();
    }

    private boolean AkkoordGaan() {
        System.out.println("Ga je Akkoord met de voorwaarde?");
        System.out.println("_".repeat(15));
        System.out.println("(J) Ja");
        System.out.println("(N) Nee");
        System.out.println("(x) Terug naar Regelement Menu.");
        String input = sc.nextLine().toLowerCase();
        switch (input) {
            case "j":
                CurrentUser.gebruiker.setAgreedTerms(true);
                System.out.println("Je bent akkoord gegaan met het regelement.");
                return true;
            case "n":
                System.out.println("Je bent niet akkord gegaan met het regelement.");
                break;
            case "x":
                break;
            default:
                System.out.println("Invalid input");
                AkkoordGaan();
                break;
        }
        return false;
    }
}
