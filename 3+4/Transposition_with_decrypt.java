import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Transposition_with_decrypt {

	public static String reader(String fileName) {
		String line, content = "";
		try (BufferedReader bf = new BufferedReader(new FileReader(fileName))) {
			while ((line = bf.readLine()) != null) {
				content += line;
			}
		} catch (IOException e) {
			System.out.println("File not found");
		}
		return content.toString();
	}

	public static void writer(String fileName, String content) {
		try (BufferedWriter bf = new BufferedWriter(new FileWriter(fileName))) {
			bf.write(content);
		} catch (IOException e) {
			System.out.println("File not found");
		}
	}

	public static String encrypt(String message, int width) {
		String encryptMessage = "";
		for (int i = 0; i < width; i++) {
			for (int j = 0; j * width + i < message.length(); j++) {
				encryptMessage += message.charAt(j * width + i);
			}
		}
		return encryptMessage.toString();
	}

	public static String decrypt(String message, int width) {
		char[] decrypt = new char[message.length()];
		int row = message.length() / width;
		int rem = message.length() % width;
		int index = 0;
		Arrays.fill(decrypt, '?');
		for (int i = 0; i < width; i++, rem = Math.max(rem - 1, 0)) {
			int tem = row + ((rem > 0) ? 1 : 0);
			for (int j = 0; j < tem; j++) {
				decrypt[j * width + i] = message.charAt(index++);
			}
		}
		return new String(decrypt);
	}

	public static void main(String[] args) {
		String message = reader("input.txt");
		int width;
		System.out.println("Enter the width");
		Scanner scanner = new Scanner(System.in);
		width = scanner.nextInt();
		scanner.close();
		String encrypt, double_encrypt, decrypt, double_decrypt;
		encrypt = encrypt(message, width);
		writer("encrypt.txt", encrypt);
		double_encrypt = encrypt(encrypt, width);
		writer("double_encrypt.txt", double_encrypt);
		decrypt = decrypt(encrypt, width);
		writer("decrypt_from_one_transposition.txt", decrypt);
		decrypt = decrypt(double_encrypt, width);
		double_decrypt = decrypt(decrypt, width);
		writer("decrypt_from_double_transposition.txt", double_decrypt);
	}

}