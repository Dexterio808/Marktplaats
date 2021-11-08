package nl.belastingdienst.Categorie.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.belastingdienst.Categorie.Categorie;

import javax.persistence.Entity;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCategorie extends Categorie {
    private String productOmschrijving;
}
