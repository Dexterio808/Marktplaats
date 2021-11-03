package nl.belastingdienst;

import lombok.extern.slf4j.*;
import nl.belastingdienst.gebruiker.Gebruiker;
import nl.belastingdienst.gebruiker.GebruikerService;
import nl.belastingdienst.scherm.Hoofdschrem;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;

@Singleton
@Slf4j
public class App {
    @Inject
    private Hoofdschrem hoofdschrem;
    @Inject
    GebruikerService gebruikerService;

    public static void main(String[] args) {
        Weld weld = new Weld();
        WeldContainer weldContainer = weld.initialize(); // de engine/container

        App app = weldContainer.select(App.class).get(); // vanaf nu kan ik in App en zijn dependencies (dao) @Inject gebruiken.
        app.start();

        weld.shutdown();
    }


    private void start(){
        log.info("Starting App");
        Gebruiker g = Gebruiker.builder().name("dev").email("dev@mail.com").build();
        gebruikerService.save(g);
        hoofdschrem.run();
    }
}
