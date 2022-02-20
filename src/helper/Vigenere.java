package helper;

public class Vigenere {
    public static String cipher(String plaintext, String key){
        key = key.toLowerCase();
        StringBuilder res = new StringBuilder();
        int keyL = key.length();
        for (int i = 0; i < plaintext.length(); i++) {
            char tmp = plaintext.charAt(i);
            if (Character.isAlphabetic(tmp)) {
                char delta = Character.isUpperCase(tmp)? 'A': 'a';
                res = res.append((char) ((tmp + key.charAt(i % keyL) - 'a' - delta) % 26 + delta));
            }else{
                res = res.append(plaintext.charAt(i));
            }
        }
        return res.toString();
    }

    public static String decode(String cipher, String key){
        key = key.toLowerCase();
        StringBuilder res = new StringBuilder();
        int keyL = key.length();
        for (int i = 0; i < cipher.length(); i++) {
            char tmp = cipher.charAt(i);
            if (Character.isAlphabetic(tmp)) {
                char delta = Character.isUpperCase(tmp)? 'A': 'a';
                tmp = Character.toLowerCase(tmp);
                res = res.append((char) ((tmp - key.charAt(i % keyL) + 26) % 26 + delta));
            }else{
                res = res.append(cipher.charAt(i));
            }
        }
        return res.toString();
    }
}
