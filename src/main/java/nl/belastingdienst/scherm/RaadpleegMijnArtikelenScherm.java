package nl.belastingdienst.scherm;

import nl.belastingdienst.product.Dienst.Dienst;
import nl.belastingdienst.product.Dienst.DienstController;
import nl.belastingdienst.product.Product.Product;
import nl.belastingdienst.product.Product.ProductController;
import nl.belastingdienst.util.CurrentUser;

import javax.inject.Inject;
import java.util.List;
import java.util.Scanner;

import static nl.belastingdienst.util.Util.print;
import static nl.belastingdienst.util.Util.readLine;

public class RaadpleegMijnArtikelenScherm {
    @Inject
    Scanner sc;
    @Inject
    private ProductController productController;
    @Inject
    private DienstController dienstController;

    public void run() {
        runRaadpleegMenu();
    }

    private void runRaadpleegMenu() {
        boolean runMenu = true;
        while (runMenu) {

            print("Raadpleeg Artikelen Menu:");
            print("_".repeat(15));
            print("(1) - Raadpleeg Producten(te koop)");
            print("(2) - Raadpleeg Verkochte Producten");
            print("(x) - Terug");
            print("Keuze: ");

            String input = readLine();
            switch (input) {
                case "1":
                    raadpleegMijnProducten();
                    break;
                case "2":
                    raadpleegVerkochteProducten();
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

    private void raadpleegVerkochteProducten() {
        print("All uw verkochte producten:");
        List<Product> products = productController.findAllSoldProduct(CurrentUser.gebruiker.getId());
        List<Dienst> diensten = dienstController.findAllSoldDienst(CurrentUser.gebruiker.getId());
        int i = 1;

        print("Producten: ");
        if (products.size() > 0) {
            for (Product product : products) {
                print("ID: " + product.getId() + " Naam product: " + product.getNaam());
            }
        } else {
            print("No products found");
        }

        print("Diensten: ");
        if (products.size() > 0) {
            for (Dienst dienst : diensten) {
                print("ID: " + dienst.getId() + " Naam dienst: " + dienst.getNaam());
            }
        } else {
            print("No dienst found");
        }

        selectmenu();

    }

    private void raadpleegMijnProducten() {
        print("All uw artikelen:");
        List<Product> products = productController.findAllActiveProduct(CurrentUser.gebruiker.getId());
        List<Dienst> diensten = dienstController.findAllActiveDienst(CurrentUser.gebruiker.getId());
        int i = 1;

        print("Producten: ");
        if (products.size() > 0) {
            for (Product product : products) {
                print("ID: " + product.getId() + " Naam product: " + product.getNaam());
            }
        } else {
            print("No product found");
        }

        print("Diensten: ");
        if (diensten.size() > 0) {
            for (Dienst dienst : diensten) {
                print("ID: " + dienst.getId() + " Naam dienst: " + dienst.getNaam());
            }
        } else {
            print("No dienst found");
        }

        selectmenu();
    }

    private void selectmenu() {
        print("Raadpleeg Menu");
        print("_".repeat(15));
        print("(1) -> selecteer product");
        print("(2) -> selecteer dienst");
        print("(x) -> Terug naar Menu");
        print("Keuze: ");
        String input = readLine();
        switch (input) {
            case "1":
                selectProduct();
                break;
            case "2":
                selectDienst();
                break;
            case "x":
                print("Terug naar Raadpleeg Artikelen Menu");
                break;
            default:
                selectmenu();
                break;
        }
    }

    private void selectDienst() {
        print("Voer het Id in van de Dienst dat je wilt raadplegen");
        Long input = Long.parseLong(readLine());
        Dienst d = dienstController.findById(input);
        print("Naam: " + d.getNaam());
        print("Omschrijving: " + d.getOmschrijving());
        print("Prijs: " + d.getPrijs());
        print("Gereserveerd: " + d.isGereserveerd());
        print("Datum post: " + d.getPostDate());
        deleteDienstProcedure(d);

    }

    private void selectProduct() {
        print("Voer het Id in van het Product dat je wilt raadplegen");
        Long input = Long.parseLong(readLine());
        Product p = productController.findById(input);
        print("Naam: " + p.getNaam());
        print("Omschrijving: " + p.getOmschrijving());
        print("Prijs: " + p.getPrijs());
        print("Gereserveerd: " + p.isGereserveerd());
        print("Datum post: " + p.getPostDate());
        deleteProductProcedure(p);
    }

    private void deleteProductProcedure(Product p){
        boolean runDeleteMenu = true;
        while(runDeleteMenu){
            print("Product menu: ");
            print("_".repeat(15));
            print("(1) - Verwijder Product");
            print("(x) - Terug");
            switch (readLine()){
                case "1":
                    productController.delete(p);
                    print("Artikel verijderd");
                    break;
                case "x":
                    runDeleteMenu = false;
                    break;
                default:
                    print("Invalid input");
                    break;
            }
        }
    }

    private void deleteDienstProcedure(Dienst d){
        boolean runDeleteMenu = true;
        while(runDeleteMenu){
            print("Dienst menu: ");
            print("_".repeat(15));
            print("(1) - Verwijder Dienst");
            print("(x) - Terug");
            switch (readLine()){
                case "1":
                    dienstController.delete(d);
                    print("Artikel verijderd");
                    break;
                case "x":
                    runDeleteMenu = false;
                    break;
                default:
                    print("Invalid input");
                    break;
            }
        }
    }


}
