package nl.belastingdienst.categorie;

import lombok.*;
import nl.belastingdienst.abstractbp.AbstractEntity;

import javax.persistence.MappedSuperclass;

@Data
@NoArgsConstructor
@MappedSuperclass
public abstract class Categorie extends AbstractEntity {

}
