package nl.belastingdienst.util;

import nl.belastingdienst.sec.*;

import javax.crypto.spec.*;
import javax.enterprise.inject.Produces;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.io.*;
import java.security.*;
import java.util.Scanner;

@Singleton
public class Producers {
    private Producers() {
    }

    @Produces
    public static EntityManager em() {
        return Persistence.createEntityManagerFactory("MySQL").createEntityManager();
    }

    @Produces
    public static Scanner sc(){
        return new Scanner(System.in);
    }

    @Produces
    public static MessageDigest md() throws NoSuchAlgorithmException {
        return MessageDigest.getInstance("SHA-512");
    }

    @Produces
    public static Password pw(){
        return new Password();
    }

}
