package test;

import org.junit.jupiter.api.Test;

import static helper.Vigenere.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author professorik
 * @created 20/02/2022 - 13:16
 * @project DL-labs
 */
class VigenereTest {

    @Test
    void testVigenere(){
        String message = "The quick brown fox jumps over 13 lazy dogs.";
        String key = "cryptii";
        String cipher = cipher(message, key);
        assertNotEquals(message, cipher);
        assertEquals(message, decode(cipher, key));
    }
}
