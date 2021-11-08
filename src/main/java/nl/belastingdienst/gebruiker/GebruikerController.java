package nl.belastingdienst.gebruiker;

import nl.belastingdienst.abstractbp.ControllerBP;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

public class GebruikerController implements ControllerBP<Gebruiker> {

    @Inject
    private GebruikerDao gebruikerDAO;

    @Override
    public List<Gebruiker> findAll() {
        return gebruikerDAO.findAll();
    }

    @Override
    public Optional<Gebruiker> findById(Long id) {
        return Optional.ofNullable(gebruikerDAO.findByID(id));
    }

    @Override
    public void save(Gebruiker gebruiker) {
        gebruikerDAO.save(gebruiker);
    }

    @Override
    public void update(Gebruiker gebruiker) {
        gebruikerDAO.update(gebruiker);
    }

    @Override
    public void delete(Gebruiker gebruiker) {
        gebruikerDAO.delete(gebruiker);
    }
}
