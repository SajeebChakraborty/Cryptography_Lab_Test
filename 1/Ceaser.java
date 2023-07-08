import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

class Ceaser {
    // Encrypts text using a shift of s
    public String encrypt(String text, int s) {
        String result = "";

        for (int i = 0; i < text.length(); i++) {
            if (Character.isUpperCase(text.charAt(i))) {
                char ch = (char) (((int) text.charAt(i) +
                        s - 65) % 26 + 65);
                result += ch;
            } else if (Character.isLowerCase(text.charAt(i))) {
                char ch = (char) (((int) text.charAt(i) +
                        s - 97) % 26 + 97);
                result += ch;
            } else
                result += text.charAt(i);
        }
        return result;
    }

    public String decrypt(String text, int s) {
        String result = "";

        for (int i = 0; i < text.length(); i++) {
            if (Character.isUpperCase(text.charAt(i))) {
                char ch = (char) (((int) text.charAt(i) -
                        s - 65 + 26) % 26 + 65);
                result += ch;
            } else if (Character.isLowerCase(text.charAt(i))) {
                char ch = (char) (((int) text.charAt(i) -
                        s - 97 + 26) % 26 + 97);
                result += ch;
            } else
                result += text.charAt(i);
        }
        return result;
    }

    // Driver code
    public static void main(String[] args) {
        String text, result;
        text = readFile("./plaintext.txt");
        int s = 4;
        Ceaser obj = new Ceaser();
        result = obj.encrypt(text, s);
        writeFile("./Chipertext.txt", result);
        result = obj.decrypt(result, s);
        writeFile("./RecoverFile.txt", result);
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