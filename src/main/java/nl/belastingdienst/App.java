package nl.belastingdienst;

import nl.belastingdienst.scherm.Hoofdschrem;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class App {


    public static void main(String[] args) {
        Weld weld = new Weld();
        WeldContainer weldContainer = weld.initialize(); // de engine/container

        App app = weldContainer.select(App.class).get(); // vanaf nu kan ik in App en zijn dependencies (dao) @Inject gebruiken.
        app.start();

        weld.shutdown();
    }


    private void start(){

    }
}
