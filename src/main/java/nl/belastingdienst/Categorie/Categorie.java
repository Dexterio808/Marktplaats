package nl.belastingdienst.Categorie;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.belastingdienst.abstractbp.AbstractEntity;

import javax.persistence.MappedSuperclass;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class Categorie extends AbstractEntity {
    private String omschrijving;
}
