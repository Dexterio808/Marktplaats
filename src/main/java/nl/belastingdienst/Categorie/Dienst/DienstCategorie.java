package nl.belastingdienst.Categorie.Dienst;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.belastingdienst.Categorie.Categorie;

import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DienstCategorie extends Categorie {
    private String dienstOmschrijving;


}
