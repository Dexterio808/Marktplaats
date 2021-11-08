package nl.belastingdienst.scherm;

import nl.belastingdienst.product.Dienst.Dienst;
import nl.belastingdienst.product.Dienst.DienstController;
import nl.belastingdienst.product.Product.Product;
import nl.belastingdienst.product.Product.ProductController;
import nl.belastingdienst.util.CurrentUser;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class RaadpleegMijnArtikelenScherm {
    @Inject
    Scanner sc;
    @Inject
    private ProductController productController;
    @Inject
    private DienstController dienstController;

    public void run(){
        runRaadpleegMenu();
    }

    private void runRaadpleegMenu() {
        boolean runMenu = true;
        while (runMenu) {

            System.out.println("Raadpleeg Artikelen Menu:");
            System.out.println("_".repeat(15));
            System.out.println("(1) - Raadpleeg Producten(te koop)");
            System.out.println("(2) - Raadpleeg Verkochte Producten");
            System.out.println("(x) - Terug");
            System.out.println("Keuze: ");

            String input = sc.nextLine();
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
                    System.out.println("Invalid input");
                    break;
            }
        }
    }

    private void raadpleegVerkochteProducten() {
        System.out.println("All uw verkochte producten:");
        List<Product> products = productController.findAllSoldProduct(CurrentUser.gebruiker.getId());
        List<Dienst> diensten = dienstController.findAllSoldDienst(CurrentUser.gebruiker.getId());
        int i = 1;

        System.out.println("Producten: ");
        if (products.size() > 0) {
            for (Product product : products) {
                System.out.println("ID: " + product.getId() + " Naam product: "+ product.getNaam());
            }
        } else {
            System.out.println("No products found");
        }

        System.out.println("Diensten: ");
        if (products.size() > 0) {
            for (Dienst dienst : diensten) {
                System.out.println("ID: "+dienst.getId()+ " Naam dienst: " + dienst.getNaam());
            }
        } else {
            System.out.println("No dienst found");
        }

        selectmenu();

    }

    private void raadpleegMijnProducten() {
        System.out.println("All uw artikelen:");
        List<Product> products = productController.findAllActiveProduct(CurrentUser.gebruiker.getId());
        List<Dienst> diensten = dienstController.findAllActiveDienst(CurrentUser.gebruiker.getId());
        int i = 1;

        System.out.println("Producten: ");
        if (products.size() > 0) {
            for (Product product : products) {
                System.out.println("ID: " + product.getId() + " Naam product: "+ product.getNaam());
            }
        } else {
            System.out.println("No products found");
        }

        System.out.println("Diensten: ");
        if (products.size() > 0) {
            for (Dienst dienst : diensten) {
                System.out.println("ID: "+dienst.getId()+ " Naam dienst: " + dienst.getNaam());
            }
        } else {
            System.out.println("No dienst found");
        }

        selectmenu();
    }

    private void selectmenu() {
        System.out.println("Raadpleeg Menu");
        System.out.println("_".repeat(15));
        System.out.println("(1) -> selecteer product");
        System.out.println("(2) -> selecteer dienst");
        System.out.println("(x) -> Terug naar Menu");
        System.out.println("Keuze: ");
        String input = sc.nextLine();
        switch(input){
            case "1":
                selectProduct();
                break;
            case "2":
                selectDienst();
                break;
            case "x":
                System.out.println("Terug naar Raadpleeg Artikelen Menu");
                break;
            default:
                selectmenu();
                break;
        }
    }

    private void selectDienst() {
        System.out.println("Voer het Id in van de Dienst dat je wilt raadplegen");
        Long input = Long.parseLong(sc.nextLine());
        Dienst d = dienstController.findById(input);
        System.out.println(d.toString());


    }

    private void selectProduct() {
        System.out.println("Voer het Id in van het Product dat je wilt raadplegen");
        Long input = Long.parseLong(sc.nextLine());
        Product p = productController.findById(input);
        System.out.println(p.toString());


    }


}
