package nl.belastingdienst.product.Dienst;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.belastingdienst.Categorie.Dienst.DienstCategorie;
import nl.belastingdienst.gebruiker.Gebruiker;
import nl.belastingdienst.product.Artikel;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Dienst extends Artikel {
    @ManyToOne
    private DienstCategorie categorie;

    public Dienst(Gebruiker gebruiker, String naam, String omschrijving, double prijs, DienstCategorie categorie) {
        super(gebruiker, naam, omschrijving, prijs, false, false, LocalDate.now());
        this.categorie = categorie;
    }
}
