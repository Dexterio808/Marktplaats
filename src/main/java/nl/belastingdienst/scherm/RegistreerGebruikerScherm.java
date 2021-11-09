package nl.belastingdienst.scherm;

import nl.belastingdienst.bezorgwijze.Bezorgwijze;
import nl.belastingdienst.gebruiker.Adres;
import nl.belastingdienst.gebruiker.Gebruiker;
import nl.belastingdienst.gebruiker.GebruikerController;

import javax.inject.Inject;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static nl.belastingdienst.util.Util.print;
import static nl.belastingdienst.util.Util.readLine;

public class RegistreerGebruikerScherm {

    Pattern mailPattern = Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\." +
            "[a-zA-Z0-9_+&*-]+)*@" +
            "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
            "A-Z]{2,7}$");
    Pattern bezorgPattern = Pattern.compile("\\d+");
    @Inject
    private GebruikerController gebruikerController;

    public void run() {
        boolean runRGS = true;
        while (runRGS) {
            print("Registreer gebruiker Menu:");
            print("_".repeat(15));
            print("(1) - Nieuwe gebruiker registreren");
            print("(x) - Terug naar hoofdmenu");
            print("Keuze: ");

            String keuze = readLine();
            switch (keuze) {
                case "x":
                    runRGS = false;
                    break;
                case "1":
                    registratieProcedure();
                    break;
                default:
                    print("InvalidInput");
                    break;
            }
        }
    }

    private void registratieProcedure() {
        String naam;
        String email;
        Set<Bezorgwijze> gebruikerBezorgwijzen;
        Adres adres;

        print("Nieuwe Gebruiker aanmaken");
        naam = getValidName();
        print("");
        email = getValidEmail();
        print("");
        gebruikerBezorgwijzen = getBezorgwijzeFromInput();

        if (gebruikerBezorgwijzen.contains(Bezorgwijze.THUIS)) {
            adres = getAdresFromUser();
        } else {
            adres = adresMenu();
        }
        gebruikerController.save(new Gebruiker(naam, email, adres, gebruikerBezorgwijzen));
    }

     String getValidName() {
        print("naam: ");
        return readLine();
    }

     Adres adresMenu() {
        Adres adres = null;
        print("Adres opgeven?");
        print("(y)yes/(n)no");
        switch (readLine().toLowerCase()) {
            case "y":
                adres = getAdresFromUser();
                break;
            case "n":
                print("Geen adres opgegeven");
                break;
            default:
                print("Invalid input");
                adresMenu();
                break;
        }
        return adres;
    }

     Adres getAdresFromUser() {
        print("Straat: ");
        String straat = readLine();
        print("Huisnummer: ");
        String huisnummer = readLine();
        print("Postcode: ");
        String postcode = readLine();
        print("Stad: ");
        String stad = readLine();
        return new Adres(straat, huisnummer, postcode, stad);
    }

     Set<Bezorgwijze> getBezorgwijzeFromInput() {
        print("Selecteer bezorgwijze: ");
        displayBezorgwijze();
        print("Typ de nummers van de bezorgwijzen die toepasbaar zijn voor jou:");
        System.out.print("Typ 1,2,etc: ");
        String keuze = readLine();
        return getListOfBezorgwijze(extractBezorgKeuzeIndexes(keuze));
    }

     Set<Bezorgwijze> getListOfBezorgwijze(List<Integer> keuzes) {
        List<Bezorgwijze> bezorgEnums = Arrays.asList(Bezorgwijze.values());
        Set<Bezorgwijze> gekozenEnums = new HashSet<>();
        for (Integer keuze : keuzes) {
            gekozenEnums.add(bezorgEnums.get(keuze));
        }
        return gekozenEnums;
    }

     void displayBezorgwijze() {
        List<Bezorgwijze> displayEnums = Arrays.asList(Bezorgwijze.values());
        for (int i = 0; i < displayEnums.size(); i++) {
            print((i + 1) + ". " + displayEnums.get(i));
        }
    }

     List<Integer> extractBezorgKeuzeIndexes(String keuze) {
        List<Integer> integers = new ArrayList<>();
        Matcher m = bezorgPattern.matcher(keuze);
        while (m.find()) {
            integers.add((Integer.parseInt(m.group())) - 1);
        }
        return integers;
    }

     String getValidEmail() {
        print("Email: ");
        String mail = readLine();
        Matcher mat = mailPattern.matcher(mail);
        if (!mat.matches()) {
            getValidEmail();
            print(mail + " is een invalide email");
        }
        return mail;
    }
}
