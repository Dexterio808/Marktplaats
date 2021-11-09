package nl.belastingdienst.scherm;

import nl.belastingdienst.util.CurrentUser;

import javax.inject.Inject;
import java.util.Scanner;

import static nl.belastingdienst.util.Util.print;
import static nl.belastingdienst.util.Util.readLine;

public class RegelementenScherm {
    @Inject
    private Scanner sc;

    public void run(){
        termsAndConditions();
    }

    private void termsAndConditions() {
        print("Je bent nog niet akkoord gegaan met het regelement.");
        boolean completed = false;
        while (!completed) {
            print("Regelement Menu:");
            print("_".repeat(15));
            print("(1) - Instemmen regelement");
            print("(2) - Regelement in zien");
            print("(x) - Uitloggen en terug naar inlogscherm");
            String input = readLine();
            switch (input) {
                case "x":
                    print("Terug naar Inlogscherm");
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
                    print("Invalid input");
                    break;

            }
        }
    }

    private void regelementInzien() {
        print("Regelement:");
        print("_".repeat(15));
        print("1. Scam's verboden.");
        print("2. verplichte betaling. ");
        print("_".repeat(15));
        print("(x) - Terug");
        readLine();
    }

    private boolean AkkoordGaan() {
        print("Ga je Akkoord met de voorwaarde?");
        print("_".repeat(15));
        print("(J) Ja");
        print("(N) Nee");
        print("(x) Terug naar Regelement Menu.");
        String input = readLine().toLowerCase();
        switch (input) {
            case "j":
                CurrentUser.gebruiker.setAgreedTerms(true);
                print("Je bent akkoord gegaan met het regelement.");
                return true;
            case "n":
                print("Je bent niet akkord gegaan met het regelement.");
                break;
            case "x":
                break;
            default:
                print("Invalid input");
                AkkoordGaan();
                break;
        }
        return false;
    }
}
