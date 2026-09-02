import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.util.Scanner;

public class ImageEncryption {

    static final String ALGORITHM = "AES";

    public static void encryptImage(String inputFile, String outputFile, String key) {
        try {
            byte[] data = readFile(inputFile);

            SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);

            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            byte[] encryptedData = cipher.doFinal(data);

            writeFile(outputFile, encryptedData);

            System.out.println("Image encrypted successfully!");
            System.out.println("Encrypted file: " + outputFile);

        } catch (Exception e) {
            System.out.println("Encryption error: " + e.getMessage());
        }
    }

    public static void decryptImage(String inputFile, String outputFile, String key) {
        try {
            byte[] encryptedData = readFile(inputFile);

            SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);

            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);

            byte[] decryptedData = cipher.doFinal(encryptedData);

            writeFile(outputFile, decryptedData);

            System.out.println("Image decrypted successfully!");
            System.out.println("Restored image: " + outputFile);

        } catch (Exception e) {
            System.out.println("Decryption error: " + e.getMessage());
        }
    }

    public static byte[] readFile(String fileName) throws IOException {
        FileInputStream file = new FileInputStream(fileName);
        byte[] data = file.readAllBytes();
        file.close();
        return data;
    }

    public static void writeFile(String fileName, byte[] data) throws IOException {
        FileOutputStream file = new FileOutputStream(fileName);
        file.write(data);
        file.close();
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("=================================");
        System.out.println("       IMAGE ENCRYPTION TOOL");
        System.out.println("=================================");

        System.out.println("1. Encrypt Image");
        System.out.println("2. Decrypt Image");
        System.out.print("Enter your choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter file name: ");
        String inputFile = scanner.nextLine();

        System.out.print("Enter output file name: ");
        String outputFile = scanner.nextLine();

        System.out.print("Enter 16-character key: ");
        String key = scanner.nextLine();

        if (key.length() != 16) {
            System.out.println("Error: Key must contain exactly 16 characters.");
            return;
        }

        if (choice == 1) {
            encryptImage(inputFile, outputFile, key);
        } 
        else if (choice == 2) {
            decryptImage(inputFile, outputFile, key);
        } 
        else {
            System.out.println("Invalid choice!");
        }

        scanner.close();
    }
}
