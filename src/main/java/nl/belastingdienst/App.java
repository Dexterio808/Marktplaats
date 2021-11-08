package nl.belastingdienst;

import lombok.extern.slf4j.*;
import nl.belastingdienst.categorie.Dienst.DienstCategorie;
import nl.belastingdienst.categorie.Dienst.DienstCategorieDao;
import nl.belastingdienst.categorie.Product.ProductCategorie;
import nl.belastingdienst.categorie.Product.ProductCategorieDao;
import nl.belastingdienst.bezorgwijze.Bezorgwijze;
import nl.belastingdienst.gebruiker.Gebruiker;
import nl.belastingdienst.gebruiker.GebruikerDao;
import nl.belastingdienst.product.Dienst.DienstDao;
import nl.belastingdienst.product.Product.ProductDao;
import nl.belastingdienst.scherm.Hoofdschrem;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.HashSet;
import java.util.Set;

@Singleton
@Slf4j
public class App {
    @Inject
    private Hoofdschrem hoofdschrem;
    @Inject
    private GebruikerDao gebruikerDAO;
    @Inject
    private ProductCategorieDao productCategorieDao;
    @Inject
    private DienstCategorieDao dienstCategorieDao;
    @Inject
    private ProductDao productDao;
    @Inject
    private DienstDao dienstDao;

    public static void main(String[] args) {
        Weld weld = new Weld();
        WeldContainer weldContainer = weld.initialize(); // de engine/container

        App app = weldContainer.select(App.class).get(); // vanaf nu kan ik in App en zijn dependencies (dao) @Inject gebruiken.
        app.start();

        weld.shutdown();
    }


    private void start(){
        log.info("Starting App");
        Set<Bezorgwijze> bezorgwijzeSet = new HashSet<>();
        bezorgwijzeSet.add(Bezorgwijze.THUIS);
        Gebruiker g = Gebruiker.builder().name("dev").email("dev@mail.com").bezorgwijzen(bezorgwijzeSet).agreedTerms(true).build();
        gebruikerDAO.save(g);

        ProductCategorie pcat = new ProductCategorie("Electronica");
        productCategorieDao.save(pcat);

        ProductCategorie pcat2 = new ProductCategorie("Meubel");
        productCategorieDao.save(pcat2);

        DienstCategorie dcat = new DienstCategorie("Schoonmaken");
        dienstCategorieDao.save(dcat);

        DienstCategorie dcat2 = new DienstCategorie("Les geven");
        dienstCategorieDao.save(dcat2);



        hoofdschrem.run();
    }
}
