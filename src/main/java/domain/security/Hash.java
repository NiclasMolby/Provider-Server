package domain.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Hash {

    /**
     * Henter først en instans af MessageDigest med MD5 algoritmen, en cryptografisk hash funktion,
     * der laver 128-bit hash værdier.
     * Derefter tilføjes salt-værdien til hash funktionen.
     * Derefter bliver passwordet hashet (digestet) til decimaler.
     * Så bliver det i et loop lavet om til hexadecimaler ved at ANDe alle bits
     * med 0xff.
     * @param passwordToHash Det password der skal hashes
     * @param salt Den salt-værdi der skal bruges til at hashe passwordToHash med
     * @return Det hashede password
     */
    public String getHashedPassword(String passwordToHash, byte[] salt) {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder builder = new StringBuilder();
            for(int i = 0; i < bytes.length; i++) {
                builder.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = builder.toString();
        }
        catch(NoSuchAlgorithmException e) {
            System.err.println(e);
        }
        return generatedPassword;
    }

    /**
     * Henter instansen af SecureRandom, med algoritmen SHA1PRNG, fra provideren SUN,
     * en pseudo random number generator algoritme.
     * Herefter laves et nyt byte array med 16 pladser. Dette array bliver
     * fyldt ud med et pseudo random byte array fra SecureRandom generatoren.
     * @return Den tilfældigt genererede salt-værdi.
     */
    public byte[] getSalt() {
        byte[] salt = new byte[16];
        SecureRandom sr = new SecureRandom();
        sr.nextBytes(salt);

        return salt;
    }
}
