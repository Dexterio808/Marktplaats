package nl.belastingdienst.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.belastingdienst.Categorie.DienstCategorie;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Dienst extends Artikel{
    @ManyToOne
    private DienstCategorie categorie;
}
