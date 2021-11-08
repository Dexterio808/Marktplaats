package nl.belastingdienst.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.belastingdienst.abstractbp.AbstractEntity;
import nl.belastingdienst.gebruiker.Gebruiker;

import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;



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
    private boolean verkocht;
    private boolean gereserveerd;
    private LocalDate postDate;


    //TODO Multimedia toevoegen;

}
