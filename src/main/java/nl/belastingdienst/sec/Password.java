package nl.belastingdienst.sec;

import lombok.extern.slf4j.*;

import java.nio.charset.*;
import java.security.*;

@Slf4j
public class Password {

    public static String createSalt(){
        byte[] salt = new byte[16];
        SecureRandom sr = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        sr.nextBytes(salt);
        for (byte b : salt) {
            sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }

    public static String genAndHash(String salt) throws NoSuchAlgorithmException {
        return hashPassword(salt, genPassword());
    }

    public static String genPassword(){
        StringBuilder sb = new StringBuilder();
        SecureRandom sr = new SecureRandom();

        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        for (int i = 0; i < 8; i++) {
            int randomCharIndex = sr.nextInt(chars.length());
            sb.append(chars.charAt(randomCharIndex));
        }
        return sb.toString();
    }

    public static String hashPassword(String salt, String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        StringBuilder sb = new StringBuilder();
        md.update(salt.getBytes(StandardCharsets.UTF_8));
        byte[] hashedpassword = md.digest(password.getBytes(StandardCharsets.UTF_8));
        sb.setLength(0);    //Reset String builder
        for (byte b : hashedpassword) {
            sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }
}
