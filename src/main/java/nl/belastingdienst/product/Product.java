package nl.belastingdienst.product;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.belastingdienst.Categorie.ProductCategorie;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product extends Artikel{
    @ManyToOne
    private ProductCategorie categorie;
}
