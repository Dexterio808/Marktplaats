package nl.belastingdienst.categorie.Dienst;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import nl.belastingdienst.categorie.Categorie;

import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DienstCategorie extends Categorie {
    private String dienstOmschrijving;


}
