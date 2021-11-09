package nl.belastingdienst.scherm;

import lombok.extern.slf4j.Slf4j;
import nl.belastingdienst.categorie.Categorie;
import nl.belastingdienst.categorie.Dienst.DienstCategorie;
import nl.belastingdienst.categorie.Dienst.DienstCategorieController;
import nl.belastingdienst.categorie.Product.ProductCategorie;
import nl.belastingdienst.categorie.Product.ProductCategorieController;
import nl.belastingdienst.bezorgwijze.Bezorgwijze;
import nl.belastingdienst.product.Dienst.Dienst;
import nl.belastingdienst.product.Dienst.DienstController;
import nl.belastingdienst.product.Product.Product;
import nl.belastingdienst.product.Product.ProductController;
import nl.belastingdienst.util.CurrentUser;

import javax.inject.Inject;
import java.util.*;

import static nl.belastingdienst.util.Util.print;
import static nl.belastingdienst.util.Util.readLine;

@Slf4j
public class ArtikelAanmaken {
    @Inject
    private Scanner sc;

    @Inject
    private DienstCategorieController dienstCC;

    @Inject
    private ProductCategorieController productCC;

    @Inject
    private DienstController dienstController;

    @Inject
    private ProductController productController;


    public void run() {
        boolean runMenu = true;

        while (runMenu) {

            print("GebruikerMenu:");
            print("_".repeat(15));
            print("(1) - Product Aanmaken.");
            print("(2) - Dienst Aanmaken.");
            print("(x) - Terug naar gebruiker scherm.");
            print("Keuze: ");

            String input = readLine();

            switch (input) {
                case "1":
                    createArtikel("product");
                    break;
                case "2":
                    createArtikel("dienst");
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

    private void createArtikel(String type) {
        String naam;
        DienstCategorie dienstCategorie = null;
        ProductCategorie productCategorie = null;
        double prijs;
        String omschrijving;
        Set<Bezorgwijze> bezorgwijze = new HashSet<>();

        print("Nieuw " + type + " aanmaken:");
        naam = getNaam();
        if (type.equals("product")) {
            productCategorie = ((ProductCategorie) getCategorie(type));
        } else {
            dienstCategorie = ((DienstCategorie) getCategorie(type));
        }
        prijs = getPrijs();
        omschrijving = getOmschrijving();

        if (type.equals("product")) {
            bezorgwijze = getBezorgwijze();
        }

        if (type.equals("product")) {
            productController.save(new Product(CurrentUser.gebruiker, naam, omschrijving, prijs, productCategorie, bezorgwijze));
            print("Product opgeslagen");
        } else {
            dienstController.save(new Dienst(CurrentUser.gebruiker, naam, omschrijving, prijs, dienstCategorie));
            print("Dienst opgeslagen");
        }


    }

    private Set<Bezorgwijze> getBezorgwijze() {
        print("Bezorgwijze: ");
        displayBezorgwijze();
        return inputBezorgwijze();

    }

    private Set<Bezorgwijze> inputBezorgwijze() {
        List<Bezorgwijze> allBezorgwijzen = new ArrayList<>(CurrentUser.gebruiker.getBezorgwijzen());
        Set<Bezorgwijze> bezorgwijzen = new HashSet<>();
        print("Kies een bezorgwijze: ");
        int input = Integer.parseInt(readLine());
        bezorgwijzen.add(allBezorgwijzen.get(input - 1));
        return bezorgwijzen;

    }

    private void displayBezorgwijze() {
        List<Bezorgwijze> allBezorgwijzen = new ArrayList<>(CurrentUser.gebruiker.getBezorgwijzen());
        for (int i = 0; i < allBezorgwijzen.size(); i++) {
            print((i + 1) + ". " + allBezorgwijzen.get(i));
        }
    }

    private String getOmschrijving() {
        print("Omschrijving Artikel:");
        return readLine();
    }

    private double getPrijs() {
        print("Prijs: ");
        double input = Double.parseDouble(readLine());
        if (input < 0d) {
            getPrijs();
        }
        return input;
    }

    private Categorie getCategorie(String type) {
        print("Categoriën:");
        if (type.equals("product")) {
            displayCategorien(type);
            print("Kies een categorie:");
            return getCategorieKeuze(type);
        } else {
            displayCategorien(type);
            print("Kies een categorie:");
            return getCategorieKeuze(type);
        }
    }

    private Categorie getCategorieKeuze(String type) {
        int index = Integer.parseInt(readLine());
        if (type.equals("product")) {
            return productCC.findAll().get(index -1);
        } else {
            return dienstCC.findAll().get(index -1);
        }
    }

    private void displayCategorien(String type) {
        List<DienstCategorie> dCategorien = dienstCC.findAll();
        List<ProductCategorie> pCategorien = productCC.findAll();

        if (type.equals("product")) {

            for (int i = 0; i < pCategorien.size(); i++) {
                print((i + 1) + ". " + pCategorien.get(i).getProductOmschrijving());
            }
        } else {
            for (int i = 0; i < dCategorien.size(); i++) {
                print((i + 1) + ". " + dCategorien.get(i).getDienstOmschrijving());
            }
        }
    }

    private String getNaam() {
        print("Naam Artikel:");
        String input = readLine();
        if (input == null) {
            getNaam();
        }
        return input;
    }
}
