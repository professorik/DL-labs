import helper.SHA1;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author professorik
 * @created 20/02/2022 - 12:58
 * @project DL-labs
 */
class SHA1Test {

    @Test
    void checkLeadingZero(){
        assertEquals(SHA1.getSHA1Hash("[]0"), "03f733eb4c2176cfb51ec51f79b2882bb58a4aaf");
    }

    @Test
    void getSHA1HashLongString(){
        assertEquals(SHA1.getSHA1Hash("a".repeat(1_000_000)), "34aa973cd4c4daa4f61eeb2bdbad27316534016f");
    }

    @Test
    void getSHA1HashShortString() {
        assertEquals(SHA1.getSHA1Hash("abc"), "a9993e364706816aba3e25717850c26c9cd0d89d");
    }

    @Test
    void getSHA1HashNormalString() {
        assertEquals(SHA1.getSHA1Hash("abcdbcdecdefdefgefghfghighijhijkijkljklmklmnlmnomnopnopq"), "84983e441c3bd26ebaae4aa1f95129e5e54670f1");
    }
}
