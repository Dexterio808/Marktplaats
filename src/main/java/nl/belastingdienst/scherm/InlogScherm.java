package nl.belastingdienst.scherm;

import lombok.extern.slf4j.Slf4j;
import nl.belastingdienst.gebruiker.Gebruiker;
import nl.belastingdienst.gebruiker.GebruikerDao;
import nl.belastingdienst.sec.Password;
import nl.belastingdienst.util.CurrentUser;

import javax.inject.Inject;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import static nl.belastingdienst.util.Util.print;
import static nl.belastingdienst.util.Util.readLine;

@Slf4j
public class InlogScherm {
    @Inject
    private Scanner sc;
    @Inject
    private GebruikerDao gebruikerDAO;
    @Inject
    private GebruikerScherm gebruikerScherm;

    public void run() {

        boolean runInlogMenu = true;
        while (runInlogMenu) {
            print("LoginMenu");
            print("_".repeat(15));
            print("(1) - Login");
            print("(x) - exit");
            print("Keuze: ");
            String keuze = readLine();
            switch (keuze) {
                case "x":
                    runInlogMenu = false;
                    print("Return to HoofdMenu...");
                    break;
                case "1":
                    loginProcedure();
                    break;
                default:
                    print("Invalid input");
                    break;
            }
        }
    }

    void loginProcedure() {
        String inputEMail;
        String inputPassword;

        print("Log in met jouw gegevens.");
        print("Email: ");
        inputEMail = readLine();
        print("Password: ");
        inputPassword  = readLine();

        Gebruiker g = gebruikerDAO.findByEmail(inputEMail);

        if (matchGebruikerAndPassword(g, inputPassword) /*|| testAccount(inputEMail)*/){//dev@mail.com for testing purposes
            print("Login Success!");
            CurrentUser.gebruiker = g;
            gebruikerScherm.run();
        } else {
            print("Login Failed!");
        }
        run();
    }

    private boolean testAccount(String inputEMail) {
        return (inputEMail.equals("dev@mail.com")||
                inputEMail.equals("dev2@mail.com"));
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
