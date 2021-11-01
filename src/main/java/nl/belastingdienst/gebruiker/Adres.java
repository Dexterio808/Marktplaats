package nl.belastingdienst.gebruiker;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Adres {
    private String straat;
    private int huisnummer;
    private String postcode;
    private String stad;
}
