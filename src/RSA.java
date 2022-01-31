import java.math.BigInteger;
import java.util.Random;

public class RSA {

    public static void main(String[] args) {
        BigInteger p = largePrime(512);
        BigInteger q = largePrime(512);
        BigInteger n = p.multiply(q);
        BigInteger phi = getPhi(p, q);
        BigInteger e = genE(phi);
        BigInteger d = extEuclid(e, phi)[1];
        Key publicKey = new Key(e, n), privateKey = new Key(d, n);

        String message = "Encryption test!,.zZ";
        BigInteger encrypted = encrypt(stringCipher(message), publicKey);
        String decrypted = cipherToString(decrypt(encrypted, privateKey));

        System.out.println(message);
        System.out.println(encrypted);
        System.out.println(decrypted);
    }

    public static BigInteger stringCipher(String message) {
        StringBuilder cipherString = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            int ch = message.charAt(i)+100;
            cipherString.append(ch);
        }
        return new BigInteger(String.valueOf(cipherString));
    }

    public static String cipherToString(BigInteger message) {
        String cipherString = message.toString();
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < cipherString.length(); i += 3) {
            output.append((char) (Integer.parseInt(cipherString.substring(i, i + 3))-100));
        }
        return output.toString();
    }

    public static BigInteger getPhi(BigInteger p, BigInteger q) {
        BigInteger phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
        return phi;
    }

    public static BigInteger genE(BigInteger phi) {
        Random rand = new Random();
        BigInteger e;
        do {
            e = new BigInteger(1024, rand);
            while (e.min(phi).equals(phi)) {
                e = new BigInteger(1024, rand);
            }
        } while (!e.gcd(phi).equals(BigInteger.ONE));
        return e;
    }

    private static BigInteger encrypt(BigInteger message, Key publicKey) {
        return message.modPow(publicKey.first, publicKey.second);
    }

    public static BigInteger decrypt(BigInteger message, Key privateKey) {
        return message.modPow(privateKey.first, privateKey.second);
    }

    public static BigInteger largePrime(int bits) {
        Random randomInteger = new Random();
        BigInteger largePrime = BigInteger.probablePrime(bits, randomInteger);
        return largePrime;
    }

    public static BigInteger[] extEuclid(BigInteger a, BigInteger b) {
        if (b.equals(BigInteger.ZERO)) return new BigInteger[]{
                a, BigInteger.ONE, BigInteger.ZERO
        };
        BigInteger[] values = extEuclid(b, a.mod(b));
        BigInteger d = values[0];
        BigInteger p = values[2];
        BigInteger q = values[1].subtract(a.divide(b).multiply(values[2]));
        return new BigInteger[]{
                d, p, q
        };
    }

    static class Key {
        private final BigInteger first;
        private final BigInteger second;

        public Key(BigInteger first, BigInteger second) {
            this.first = first;
            this.second = second;
        }
    }
}
