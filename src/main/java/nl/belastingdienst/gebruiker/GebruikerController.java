package nl.belastingdienst.gebruiker;

import nl.belastingdienst.abstractbp.ControllerBP;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

public class GebruikerController implements ControllerBP<Gebruiker> {

    @Inject
    private GebruikerService gebruikerService;

    @Override
    public List<Gebruiker> findAll() {
        return gebruikerService.findAll();
    }

    @Override
    public Optional<Gebruiker> findById(Long id) {
        return Optional.ofNullable(gebruikerService.find(id));
    }

    @Override
    public void save(Gebruiker gebruiker) {
        gebruikerService.save(gebruiker);
    }

    @Override
    public void update(Gebruiker gebruiker) {
        gebruikerService.update(gebruiker);
    }

    @Override
    public void delete(Gebruiker gebruiker) {
        gebruikerService.delete(gebruiker);
    }
}
