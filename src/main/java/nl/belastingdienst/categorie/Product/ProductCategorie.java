package nl.belastingdienst.categorie.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import nl.belastingdienst.categorie.Categorie;

import javax.persistence.Entity;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductCategorie extends Categorie {
    private String productOmschrijving;
}
