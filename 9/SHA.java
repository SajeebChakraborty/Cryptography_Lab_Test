import java.io.File;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Scanner;

class SHA {
    public static void main(String[] args) throws Exception {
        File f = new File("input.txt");
        Scanner sc = new Scanner(f);
        String msg = "";
        while (sc.hasNextLine())
            msg += sc.nextLine() + " ";
        sc.close();
        System.out.println(msg);
        System.out.println("Hashed: " + getSHA(msg));
    }

    public static String getSHA(String msg) {
        String ans = "";
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] arr = md.digest(msg.getBytes());
            BigInteger num = new BigInteger(1, arr);
            ans = num.toString(16);
            while (ans.length() < 40)
                ans = "0" + ans;
        } catch (Exception e) {
            System.out.println(e);
        }
        return ans;
    }
}