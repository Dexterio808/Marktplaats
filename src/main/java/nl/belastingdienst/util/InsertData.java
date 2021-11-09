package nl.belastingdienst.util;

import nl.belastingdienst.bezorgwijze.Bezorgwijze;
import nl.belastingdienst.categorie.Dienst.DienstCategorie;
import nl.belastingdienst.categorie.Dienst.DienstCategorieDao;
import nl.belastingdienst.categorie.Product.ProductCategorie;
import nl.belastingdienst.categorie.Product.ProductCategorieDao;
import nl.belastingdienst.gebruiker.Gebruiker;
import nl.belastingdienst.gebruiker.GebruikerDao;
import nl.belastingdienst.product.Dienst.DienstDao;
import nl.belastingdienst.product.Product.ProductDao;

import javax.inject.Inject;
import java.util.HashSet;
import java.util.Set;

public class InsertData {

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

    public void run(){
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
    }
}
