package nl.belastingdienst;

import com.github.christianspruijt.jwt.JWT;
import lombok.extern.slf4j.Slf4j;
import nl.belastingdienst.scherm.Hoofdschrem;
import nl.belastingdienst.util.InsertData;
import nl.belastingdienst.util.Util;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Scanner;

@Singleton
@Slf4j
public class App {
    @Inject
    private Hoofdschrem hoofdschrem;
    @Inject
    private InsertData insertData;

    public static void main(String[] args) {
        Weld weld = new Weld();
        WeldContainer weldContainer = weld.initialize(); // de engine/container

        App app = weldContainer.select(App.class).get(); // vanaf nu kan ik in App en zijn dependencies (dao) @Inject gebruiken.
        app.start();

        weld.shutdown();
    }


    void start() {
        log.info("Starting App");
        boolean runBrowserApp = true; ///TURN FALSE FOR TEST
        if (runBrowserApp) JWT.init();
        Util.scanner = new Scanner(System.in);
        insertData.run();
        hoofdschrem.run();
    }
}
