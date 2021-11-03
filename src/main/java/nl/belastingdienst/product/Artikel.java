package nl.belastingdienst.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.belastingdienst.abstractbp.AbstractEntity;
import nl.belastingdienst.bezorgwijze.Bezorgwijze;
import nl.belastingdienst.gebruiker.Gebruiker;

import javax.persistence.ElementCollection;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import java.util.Set;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class Artikel extends AbstractEntity {
    @ManyToOne
    private Gebruiker gebruiker;
    private String naam;
    private String omschrijving;
    private double prijs;
    @ElementCollection
    private Set<Bezorgwijze> bezorgwijzen;
    //TODO Multimedia toevoegen;

}
