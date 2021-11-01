package nl.belastingdienst.scherm;

import nl.belastingdienst.bezorgwijze.Bezorgwijze;
import nl.belastingdienst.gebruiker.Adres;
import nl.belastingdienst.gebruiker.Gebruiker;
import nl.belastingdienst.gebruiker.GebruikerController;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistreerGebruikerScherm {
    @Inject
    private GebruikerController gebruikerController;

    Scanner scanner = new Scanner(System.in);

    Pattern mailPattern = Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\." +
            "[a-zA-Z0-9_+&*-]+)*@" +
            "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
            "A-Z]{2,7}$");

    Pattern bezorgPattern = Pattern.compile("\\d+");


    public void run() {
        boolean runRGS = true;
        while (runRGS) {
            System.out.println("Registreer gebruiker Menu:");
            System.out.println("0. Terug naar hoofdmenu");
            System.out.println("1. Nieuwe gebruiker registreren");

            int keuze = scanner.nextInt();
            switch (keuze) {
                case 0:
                    runRGS = false;
                    break;
                case 1:
                    registratieProcedure();
                    break;
            }
        }
    }

    private void registratieProcedure() {
        String naam;
        String email;
        List<Bezorgwijze> gebruikerBezorgwijzen;
        Adres adres;

        System.out.println("Nieuwe Gebruiker aanmaken");
        System.out.print("naam: ");
        naam = scanner.nextLine();
        System.out.println();
        email = getValidEmail();
        System.out.println();
        gebruikerBezorgwijzen = getBezorgwijzeFromInput();

        if (thuisbezorgdIsChosen(gebruikerBezorgwijzen)) {
            adres = getAdresFromUser();
        } else {
            adres = adresMenu();
        }
        gebruikerController.save(new Gebruiker(naam, email, adres, gebruikerBezorgwijzen));
    }

    private Adres adresMenu() {
        Adres adres = null;
        System.out.println("Adres opgeven?");
        System.out.println("(y)yes/(n)no");
        switch (scanner.nextLine().toLowerCase()){
            case "y":
                adres = getAdresFromUser();
                break;
            case "n":
                System.out.println("Geen adres opgegeven");
                break;
            default:
                adresMenu();
                break;
        }
        return adres;
    }

    private Adres getAdresFromUser() {
        System.out.println("Straat: ");
        String straat = scanner.nextLine();
        System.out.println("Huisnummer: ");
        int huisnummer = Integer.parseInt(scanner.nextLine());
        System.out.println("Postcode: ");
        String postcode = scanner.nextLine();
        System.out.println("Stad: ");
        String stad = scanner.nextLine();
        return new Adres(straat, huisnummer, postcode, stad);
    }

    private boolean thuisbezorgdIsChosen(List<Bezorgwijze> bezorgwijzes) {
        boolean thuisbezorgd = false;
        for (int i = 0; i < bezorgwijzes.size(); i++) {
            if (bezorgwijzes.get(i).equals(Bezorgwijze.THUIS)){
                thuisbezorgd = true;
            }
        }
        return thuisbezorgd;
    }

    private List<Bezorgwijze> getBezorgwijzeFromInput() {
        List<Bezorgwijze> getBezorgwijzen = new ArrayList<>();
        System.out.println("Selecteer bezorgwijze: ");
        displayBezorgwijze();
        System.out.println("Typ de nummers van de bezorgwijzen die toepasbaar zijn voor jou:");
        System.out.print("Typ 1,2,etc: ");
        String keuze = scanner.nextLine();
        return getListOfBezorgwijze(extractBezorgKeuzeIndexes(keuze));
    }

    private List<Bezorgwijze> getListOfBezorgwijze(List<Integer> keuzes) {
        List<Bezorgwijze> bezorgEnums = Arrays.asList(Bezorgwijze.values());
        List<Bezorgwijze> gekozenEnums = new ArrayList<>();
        for (int i = 0; i < keuzes.size(); i++) {
            gekozenEnums.add(bezorgEnums.get(keuzes.get(i)));
        }
        return gekozenEnums;
    }

    private void displayBezorgwijze() {
        List<Bezorgwijze> displayEnums = Arrays.asList(Bezorgwijze.values());
        for (int i = 0; i < displayEnums.size(); i++) {
            System.out.println((i + 1) + ". " + displayEnums.get(i));
        }
    }

    private List<Integer> extractBezorgKeuzeIndexes(String keuze) {
        List<Integer> integers = new ArrayList<>();
        Matcher m = bezorgPattern.matcher(keuze);
        while (m.find()) {
            integers.add((Integer.parseInt(m.group())) - 1);
        }
        return integers;
    }

    private String getValidEmail() {
        System.out.println("Email: ");
        String mail = scanner.nextLine();
        Matcher mat = mailPattern.matcher(mail);
        if (!mat.matches()) {
            getValidEmail();
            System.out.println(mail + " is een invalide email");
        }
        return mail;
    }
}
