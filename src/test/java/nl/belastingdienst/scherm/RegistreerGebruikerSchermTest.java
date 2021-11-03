package nl.belastingdienst.scherm;

import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class RegistreerGebruikerSchermTest {
    @Inject
    RegistreerGebruikerScherm target;

    @Test
    public void getAdresFromUserTest(){

        //when()
        target.getAdresFromUser();
        //assertEquals();
    }

}