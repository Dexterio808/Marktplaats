package nl.belastingdienst.gebruiker;

import com.sun.istack.NotNull;
import lombok.*;
import nl.belastingdienst.abstractbp.AbstractEntity;
import nl.belastingdienst.bezorgwijze.Bezorgwijze;

import javax.persistence.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Gebruiker extends AbstractEntity {

    private String name;

    @Column(unique = true) @NotNull
    private String email;

    @Embedded
    private Adres adres;

    @NotNull
    private String wachtwoord;

    @ElementCollection
    private Set<Bezorgwijze> bezorgwijzen;


    public Gebruiker(String name,  String email, Adres adres, Set<Bezorgwijze> bezorgwijzen) {
        this.name = name;
        this.email = email;
        this.adres = adres;
        this.bezorgwijzen = bezorgwijzen;
    }
}
