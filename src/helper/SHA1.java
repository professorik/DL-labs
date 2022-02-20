package helper;

import java.math.BigInteger;
import java.nio.ByteBuffer;

public class SHA1 {
    public static String getSHA1Hash(String message2) {
        int h0 = 0x67452301;
        int h1 = 0xEFCDAB89;
        int h2 = 0x98BADCFE;
        int h3 = 0x10325476;
        int h4 = 0xC3D2E1F0;
        String message = convertToBinary(message2);
        int ml = message.length();
        int mnew = 512 * (1 + ((ml + 65) / 512));
        message += "1";
        message += "0".repeat(mnew - ml - 65);
        message += String.format("%64s", Integer.toBinaryString(ml)).replaceAll(" ", "0");
        byte[] textBytes = new BigInteger(message, 2).toByteArray();
        byte[][] chunks = new byte[mnew / 512][64];
        for (int i = 0; i < chunks.length; i++) {
            for (int j = 0; j < 64; j++) {
                chunks[i][j] = textBytes[i * 64 + j];
            }
        }
        for (byte[] chunk : chunks) {
            int[] w = new int[80];
            for (int i = 0; i < 16; i++) {
                byte[] tmp = new byte[4];
                for (int j = 0; j < 4; j++) {
                    tmp[j] = chunk[i * 4 + j];
                }
                w[i] = ByteBuffer.wrap(tmp).getInt();
            }
            for (int i = 16; i < 80; i++) {
                w[i] = leftrotate(w[i - 3] ^ w[i - 8] ^ w[i - 14] ^ w[i - 16], 1);
            }
            int a = h0;
            int b = h1;
            int c = h2;
            int d = h3;
            int e = h4;
            for (int i = 0; i < 80; i++) {
                int f, k;
                if (i <= 19) {
                    f = (b & c) | ((~b) & d);
                    k = 0x5A827999;
                } else if (i <= 39) {
                    f = b ^ c ^ d;
                    k = 0x6ED9EBA1;
                } else if (i <= 59) {
                    f = (b & c) | (b & d) | (c & d);
                    k = 0x8F1BBCDC;
                } else {
                    f = b ^ c ^ d;
                    k = 0xCA62C1D6;
                }
                int temp = leftrotate(a, 5) + f + e + k + w[i];
                e = d;
                d = c;
                c = leftrotate(b, 30);
                b = a;
                a = temp;
            }
            h0 = h0 + a;
            h1 = h1 + b;
            h2 = h2 + c;
            h3 = h3 + d;
            h4 = h4 + e;
        }
        return String.format("%40s", Integer.toHexString(h0) + Integer.toHexString(h1) + Integer.toHexString(h2) + Integer.toHexString(h3) + Integer.toHexString(h4)).replaceAll("\\s", "0");
    }

    private static int leftrotate(int x, int shift) {
        return ((x << shift) | (x >>> (32 - shift)));
    }

    private static String convertToBinary(String word) {
        byte[] bytes = word.getBytes();
        StringBuilder binary = new StringBuilder();
        for (byte b : bytes) {
            int val = b;
            for (int i = 0; i < 8; i++) {
                binary.append((val & 128) == 0 ? 0 : 1);
                val <<= 1;
            }
        }
        return binary.toString();
    }
}
