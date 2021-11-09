package nl.belastingdienst;

import nl.belastingdienst.util.AlternativeProducers;
import nl.belastingdienst.util.Util;
import org.jboss.weld.junit5.auto.AddBeanClasses;
import org.jboss.weld.junit5.auto.AddPackages;
import org.jboss.weld.junit5.auto.EnableAlternatives;
import org.jboss.weld.junit5.auto.EnableAutoWeld;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;
import java.util.StringJoiner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@EnableAutoWeld
@AddPackages(App.class)
@AddBeanClasses(AlternativeProducers.class)
@EnableAlternatives(AlternativeProducers.class)
@ExtendWith(MockitoExtension.class)
public class AppE2E {
    //private Scanner mockScanner =  mock(Scanner.class);

    @Mock
    private InputStream in;

    @InjectMocks
    private App app;

    @BeforeEach
    void setup(){
        Util.testmode = true;
    }



    @Test
    void gebruikerRegistreren(){

        setCommands("2","1","Pepijn", "pepijn@mail.nl", "1,2", "Korenstraat", "25A", "7339DL", "Apeldoorn", "x", "x" );

        App.main(new String[]{""});

    }

    @Test
    void gebruikerHandelingen(){

        setCommands("1", "1", "dev@mail.com", "", //Inloggen
                "1", "1", "Laptop", "1", "250.00", "HP Laptop gebruikt", "1",//Product aanmaken
                "2", "Code Revieuw", "2", "30.00", "Java code revieuwen", //Dienst aanmaken
                "x", "2", "1", "1", "1", "x", //raadpleeg product
                "1", "2", "1", "1", //raadpleeg en verwijder dienst
                "x", "1", // nieuw overzicht producten en diensten
                "x", "x", "x", "x", "x", "x"); //exit

        App.main(new String[]{""});

    }

    private void setCommands(String... commands) {
        System.setIn(new ByteArrayInputStream(toLines(commands).getBytes()));
    }

    private String toLines(String... commands) {
        StringJoiner sj = new StringJoiner(System.lineSeparator());
        for (String command : commands) {
            sj.add(command);
        }
        return sj.toString();
    }


}
