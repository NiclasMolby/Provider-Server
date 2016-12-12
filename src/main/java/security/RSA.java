package security;

import io.swagger.model.PublicKey;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Random;

public class RSA {

    private BigInteger p;

    private BigInteger q;

    private BigInteger N;

    private BigInteger phi;

    private BigInteger e;

    private BigInteger d;

    private int bitlength = 32;

    private Random r;

    private PublicKey publicKey;

    public RSA() {

        r = new Random();

        p = BigInteger.probablePrime(bitlength, r);

        q = BigInteger.probablePrime(bitlength, r);

        N = p.multiply(q);

        phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

        e = BigInteger.probablePrime(bitlength/2, r);

        while (phi.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(phi) < 0 ) {

            e.add(BigInteger.ONE);

        }

        d = e.modInverse(phi);

        publicKey = new PublicKey().e(new BigDecimal(e)).n(new BigDecimal(N));

    }

    public static void main (String[] args) throws IOException {

        RSA rsa = new RSA();

        String teststring="Test streng";

        // encrypt

        byte[] encrypted = rsa.encrypt(teststring.getBytes());

        //System.out.println("Encrypted String in Bytes: " + bytesToString(encrypted));

        // decrypt

        //byte[] decrypted = rsa.decrypt(encrypted);

        //System.out.println("Decrypted String in Bytes: " +  bytesToString(decrypted));

        //System.out.println("Decrypted String: " + new String(decrypted));

    }

    private static byte[] bytesToString(String encrypted) {

        String[] bytesString = encrypted.split("-");
        byte[] bytes = new byte[bytesString.length];
        for(int i = 0 ; i < bytes.length ; ++i) {
           // bytes[i] = Byte.parseByte(bytesString[i]); Byte.parseByte();
        }
        return bytes;

    }

    //Encrypt message

    public byte[] encrypt(byte[] message) {

        return (new BigInteger(message)).modPow(e, N).toByteArray();

    }

    // Decrypt message

    public byte[] decrypt(byte[] message) {


        return (new BigInteger(message)).modPow(d, N).toByteArray();

    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

}

