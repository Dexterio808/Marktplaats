package nl.belastingdienst.gebruiker;

import nl.belastingdienst.App;
import nl.belastingdienst.util.AlternativeProducers;
import org.jboss.weld.junit5.auto.AddBeanClasses;
import org.jboss.weld.junit5.auto.AddPackages;
import org.jboss.weld.junit5.auto.EnableAlternatives;
import org.jboss.weld.junit5.auto.EnableAutoWeld;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@EnableAutoWeld
@AddPackages(App.class)
@AddBeanClasses(AlternativeProducers.class)
@EnableAlternatives(AlternativeProducers.class)
class GebruikerDaoTest {

    @Inject
    private GebruikerDao target;

    @BeforeEach
    void run(){
        Gebruiker gebruiker = Gebruiker.builder().name("testGebruiker").email("pgebruiker@test.nl").build();
        target.save(gebruiker);
        Gebruiker gebruiker2 = Gebruiker.builder().name("testGebruiker2").email("pgebruiker2@test.nl").build();
        target.save(gebruiker2);
    }

    @Test
    void find() {
        assertEquals("testGebruiker", target.findByID(1L).getName());
    }

    @Test
    void findAll() {
        List<Gebruiker> gebruikers = target.findAll();

        assertEquals(2, gebruikers.size());
        assertEquals("testGebruiker", gebruikers.get(0).getName());
        assertEquals("testGebruiker2", gebruikers.get(1).getName());
    }

    @Test
    void save() {
        Gebruiker pepijn = Gebruiker.builder().name("Pepijn").email("pep@test.nl").build();
        target.save(pepijn);

        List<Gebruiker> gebruikers = target.findAll();

        assertTrue(gebruikers.contains(pepijn));
    }

    @Test
    void update() {
        Gebruiker g =target.findByID(1L);
        g.setName("Aangepast");
        target.update(g);
        assertEquals("Aangepast" ,target.findByID(1L).getName());
    }

    @Test
    void delete() {
        target.delete(target.findByID(1L));
        List<Gebruiker> gebruikers = target.findAll();

        assertEquals(1 ,gebruikers.size());

    }
}