package domain.security;

import io.swagger.model.PublicKey;

import java.math.BigInteger;
import java.util.Random;

public class RSA {
    // A random generated big prime number
    private BigInteger p;

    // A random generated big prime number
    private BigInteger q;

    // The product of the two prime numbers as well as part of the public and private key
    private BigInteger N;

    // The phi to compute e and d
    private BigInteger phi;

    // Part of the public key
    private BigInteger e;

    // Part of the private key
    private BigInteger d;

    // The lenght of the key
    private int bitlength = 1024;

    private Random random;

    private PublicKey publicKey;

    private static RSA instance;

    private RSA() {

        // Create a random number generator
        random = new Random();

        // Gets a random big prime number
        p = BigInteger.probablePrime(bitlength, random);

        // Gets a random big prime number
        q = BigInteger.probablePrime(bitlength, random);

        // Calculate the product of the prime numbers
        N = p.multiply(q);

        // Compute the phi as (p-1) * (q-1)
        phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

        // Gets a random prime number
        e = BigInteger.probablePrime(bitlength/2, random);

        // e has to be coprime to phi                   e has to be 1 < e < phi
        // So their gcd have to be 1
        while (phi.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(phi) < 0 ) {

            e.add(BigInteger.ONE);

        }

        // Compute the d as e^-1 % phi
        d = e.modInverse(phi);

        // Compute the public key
        publicKey = new PublicKey().e(e.toString()).n(N.toString());

    }

    public static RSA getRSA(){
        if(instance == null) {
            instance = new RSA();
        }
        return instance;
    }

    // Decrypt message

    public byte[] decrypt(String message) {

        // Converts the message to a BigInteger to do the decrypt algorithm
        BigInteger messageAsBigInt = new BigInteger(message);

        // Do the decrypt algorithm and return as a byte array
        return messageAsBigInt.modPow(d, N).toByteArray();

    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

}

