package test;

import helper.RSA;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static helper.RSA.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author professorik
 * @created 20/02/2022 - 13:07
 * @project DL-labs
 */
class RSATest {
    @Test
    void checkRSA() {
        Key[] keys = RSA.generateKeys();

        String message = "Encryption test!,.zZ";
        BigInteger encrypted = encrypt(stringCipher(message), keys[0]);
        String decrypted = cipherToString(decrypt(encrypted, keys[1]));

        assertEquals(message, decrypted);
    }
}
