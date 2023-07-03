import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Random;

public class RobinMiller {

    public static boolean isPrime(BigInteger n, int iterations) {
        // Base cases for small numbers
        if (n.equals(BigInteger.ZERO) || n.equals(BigInteger.ONE))
            return false;
        if (n.equals(BigInteger.TWO) || n.equals(BigInteger.valueOf(3)))
            return true;

        // Write n-1 as (2^r) * d
        int k = 0;
        BigInteger d = n.subtract(BigInteger.ONE);
        while (d.mod(BigInteger.TWO).equals(BigInteger.ZERO)) {
            k++;
            d = d.divide(BigInteger.TWO);
        }
        Random random = new Random();

        for (int i = 0; i < iterations; i++) {
            BigInteger a = new BigInteger(n.bitLength() - 2, random).add(BigInteger.TWO);

            BigInteger x = a.modPow(d, n);

            if (!x.equals(BigInteger.ONE) && !x.equals(n.subtract(BigInteger.ONE))) {
                int j = 1;
                while (j < k && !x.equals(n.subtract(BigInteger.ONE))) {
                    x = x.modPow(BigInteger.TWO, n);
                    if (x.equals(BigInteger.ONE))
                        return false; // n is composite
                    j++;
                }
                if (!x.equals(n.subtract(BigInteger.ONE)))
                    return false; // n is composite
            }
        }

        return true; // n is likely prime
    }

    public static void main(String[] args) {
        String bigNumber = readFile("input.txt");
        BigInteger number = new BigInteger(bigNumber);
        int iterations = 10;

        if (isPrime(number, iterations)) {
            System.out.println(number + " is likely prime.");
        } else {
            System.out.println(number + " is composite.");
        }
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
