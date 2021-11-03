package nl.belastingdienst.scherm;

import lombok.extern.slf4j.Slf4j;
import nl.belastingdienst.gebruiker.Gebruiker;
import nl.belastingdienst.gebruiker.GebruikerService;
import nl.belastingdienst.sec.Password;
import nl.belastingdienst.util.CurrentUser;

import javax.inject.Inject;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

@Slf4j
public class InlogScherm {
    @Inject
    private Scanner sc;
    @Inject
    private GebruikerService gebruikerService;
    @Inject
    private GebruikerScherm gebruikerScherm;

    public void run() {

        boolean runInlogMenu = true;
        while (runInlogMenu) {
            System.out.println("LoginMenu");
            System.out.println("_".repeat(15));
            System.out.println("(1) - Login");
            System.out.println("(x) - exit");
            System.out.println("Keuze: ");
            String keuze = sc.nextLine();
            switch (keuze) {
                case "x":
                    runInlogMenu = false;
                    System.out.println("Return to HoofdMenu...");
                    break;
                case "1":
                    loginProcedure();
                    break;
                default:
                    System.out.println("Invalid input");
                    break;
            }
        }
    }

    void loginProcedure() {
        String inputEMail;
        String inputPassword;

        System.out.println("Log in met jouw gegevens.");
        System.out.println("Email: ");
        inputEMail = sc.nextLine();
        System.out.println("Password: ");
        inputPassword = sc.nextLine();
        Gebruiker g = gebruikerService.findByEmail(inputEMail);
        if (matchGebruikerAndPassword(g, inputPassword)){
            System.out.println("Login Success!");
            CurrentUser.gebruiker = g;
            gebruikerScherm.run();
        } else {
            System.out.println("Login Failed!");
        }
        run();
    }

    private boolean matchGebruikerAndPassword(Gebruiker g, String inputPassword) {
        try {
            String hashedinput = Password.hashPassword(g.getSalt(), inputPassword);
            if (g.getPassword().equals(hashedinput)) {
                return true;
            }
        } catch (NoSuchAlgorithmException e) {
            log.info("Hash failed");
        }
        return false;
    }

}
