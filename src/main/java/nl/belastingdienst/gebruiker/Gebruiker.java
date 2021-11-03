package nl.belastingdienst.gebruiker;

import lombok.*;
import lombok.extern.slf4j.*;
import nl.belastingdienst.abstractbp.AbstractEntity;
import nl.belastingdienst.bezorgwijze.Bezorgwijze;
import nl.belastingdienst.sec.*;

import javax.persistence.*;
import java.security.*;
import java.util.*;

@Slf4j
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Gebruiker extends AbstractEntity {

    private String name;

    @Column(unique = true)
    private String email;

    @Embedded
    private Adres address;

    private String password;
    private String salt;

    private boolean agreedTerms;

    @ElementCollection
    private Set<Bezorgwijze> bezorgwijzen;


    public Gebruiker(String name, String email, Adres address, Set<Bezorgwijze> bezorgwijzen) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.bezorgwijzen = bezorgwijzen;
    }

    @PrePersist
    public void hashPassword() {
        try {
            this.salt = Password.createSalt();
            this.password = Password.genAndHash(this.salt);
        }
        catch (NoSuchAlgorithmException e){
            log.info("Password gen Error");
        }
    }
}
