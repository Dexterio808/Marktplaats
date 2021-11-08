package nl.belastingdienst.product.Product;

import lombok.*;
import nl.belastingdienst.categorie.Product.ProductCategorie;
import nl.belastingdienst.bezorgwijze.Bezorgwijze;
import nl.belastingdienst.gebruiker.Gebruiker;
import nl.belastingdienst.product.Artikel;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product extends Artikel {
    @ManyToOne
    private ProductCategorie categorie;
    @ElementCollection
    private Set<Bezorgwijze> bezorgwijzen;

    public Product(Gebruiker gebruiker, String naam, String omschrijving, double prijs, ProductCategorie categorie, Set<Bezorgwijze> bezorgwijzen) {
        super(gebruiker, naam, omschrijving, prijs, false, false, LocalDate.now());
        this.categorie = categorie;
        this.bezorgwijzen = bezorgwijzen;
    }
}
