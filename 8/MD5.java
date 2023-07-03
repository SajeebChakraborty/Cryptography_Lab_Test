import java.nio.charset.StandardCharsets;

public class MD5 {

    public static void main(String[] args) {
        String input = "Hello, World!";
        byte[] inputBytes = input.getBytes(StandardCharsets.UTF_8);
        byte[] md5Hash = calculateMD5(inputBytes);
        String md5HashString = bytesToHex(md5Hash);
        System.out.println("MD5 Hash: " + md5HashString);
    }

    public static byte[] calculateMD5(byte[] input) {
        int[] s = { 7, 12, 17, 22, 5, 9, 14, 20, 4, 11, 16, 23, 6, 10, 15, 21 };
        int[] K = new int[64];
        for (int i = 0; i < 64; i++) {
            K[i] = (int) (long) (Math.abs(Math.sin(i + 1)) * (1L << 32));
        }

        int a0 = 0x67452301;
        int b0 = 0xefcdab89;
        int c0 = 0x98badcfe;
        int d0 = 0x10325476;

        int[] M = new int[16];

        int length = input.length;
        int padding = (448 - (length * 8) % 512) % 512;
        int paddedLength = length + padding / 8 + 8;
        byte[] paddedInput = new byte[paddedLength];

        for (int i = 0; i < length; i++) {
            paddedInput[i] = input[i];
        }

        paddedInput[length] = (byte) 0x80;
        for (int i = 0; i < 8; i++) {
            paddedInput[paddedLength - 8 + i] = (byte) (length * 8 >> (8 * i));
        }

        for (int i = 0; i < paddedLength / 64; i++) {
            for (int j = 0; j < 16; j++) {
                M[j] = (paddedInput[i * 64 + j * 4] & 0xFF)
                        | ((paddedInput[i * 64 + j * 4 + 1] & 0xFF) << 8)
                        | ((paddedInput[i * 64 + j * 4 + 2] & 0xFF) << 16)
                        | ((paddedInput[i * 64 + j * 4 + 3] & 0xFF) << 24);
            }

            int A = a0;
            int B = b0;
            int C = c0;
            int D = d0;

            for (int j = 0; j < 64; j++) {
                int F, g;
                if (j < 16) {
                    F = (B & C) | (~B & D);
                    g = j;
                } else if (j < 32) {
                    F = (D & B) | (~D & C);
                    g = (5 * j + 1) % 16;
                } else if (j < 48) {
                    F = B ^ C ^ D;
                    g = (3 * j + 5) % 16;
                } else {
                    F = C ^ (B | ~D);
                    g = (7 * j) % 16;
                }

                int dTemp = D;
                D = C;
                C = B;
                B = B + Integer.rotateLeft((A + F + K[j] + M[g]), s[j]);
                A = dTemp;
            }

            a0 += A;
            b0 += B;
            c0 += C;
            d0 += D;
        }

        byte[] result = new byte[16];
        intToBytesLittleEndian(a0, result, 0);
        intToBytesLittleEndian(b0, result, 4);
        intToBytesLittleEndian(c0, result, 8);
        intToBytesLittleEndian(d0, result, 12);

        return result;
    }

    public static void intToBytesLittleEndian(int value, byte[] bytes, int offset) {
        bytes[offset] = (byte) (value & 0xFF);
        bytes[offset + 1] = (byte) ((value >> 8) & 0xFF);
        bytes[offset + 2] = (byte) ((value >> 16) & 0xFF);
        bytes[offset + 3] = (byte) ((value >> 24) & 0xFF);
    }

    public static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
