import java.io.*;

public class Onetimepad {
    public static void main(String[] args) {
        // Declaring plain text
        String plainText = readFile("plaintext.txt");

        // Declaring key
        String key = readFile("key.txt");

        // plainText = plainText.toUpperCase();
        key = key.toUpperCase();

        String encryptedText = stringEncryption(plainText, key);
        writeFile("encrypt.txt", encryptedText);

        String decryptedText = stringDecryption(encryptedText, key);
        writeFile("decrypt.txt", decryptedText);

    }

    // Method 1: Returning encrypted text
    public static String stringEncryption(String text, String key) {
        String cipherText = "";
        int[] cipher = new int[text.length()];

        for (int i = 0; i < text.length(); i++) {
            if (Character.isUpperCase(text.charAt(i))) {
                cipher[i] = (text.charAt(i) - 'A' + key.charAt(i) - 'A') % 26;
                int x = cipher[i] + 'A' + 1;
                cipherText += (char) x;
            } else if (Character.isLowerCase(text.charAt(i))) {
                cipher[i] = (text.charAt(i) - 'a' + key.charAt(i) - 'A') % 26;
                int x = cipher[i] + 'a' + 1;
                cipherText += (char) x;
            } else {
                cipherText += text.charAt(i);
            }

        }
        return cipherText.toString();
    }

    // Method 2: Returning plain text
    public static String stringDecryption(String s, String key) {
        String plainText = "";
        int[] plain = new int[s.length()];

        for (int i = 0; i < s.length(); i++) {
            if (Character.isUpperCase(s.charAt(i))) {
                plain[i] = s.charAt(i) - 'A' - (key.charAt(i) - 'A');
                if (plain[i] < 0) {
                    plain[i] = plain[i] + 26;
                }
                int x = plain[i] + 'A' - 1;
                plainText += (char) x;
            } else if (Character.isLowerCase(s.charAt(i))) {
                plain[i] = s.charAt(i) - 'a' - (key.charAt(i) - 'A');
                if (plain[i] < 0) {
                    plain[i] = plain[i] + 26;
                }
                int x = plain[i] + 'a' - 1;
                plainText += (char) x;
            } else {
                plainText += s.charAt(i);
            }

        }
        return plainText.toString();
    }

    public static String readFile(String filename) {
        String content = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content += line;
            }
        } catch (IOException e) {
            System.out.println("File not found");
        }
        return content.toString();
    }

    public static void writeFile(String filename, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(content);
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }
}
