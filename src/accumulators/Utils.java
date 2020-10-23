package accumulators;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Utils {
    private static final byte[] zero = {0};
    private static final byte[] one = {1};
    private static final byte[] two = {2};

    public static byte[] decode(String encodedStringBase64) {
        if (encodedStringBase64.equals("null"))
            return null;
        return Base64.getDecoder().decode(encodedStringBase64);
    }

    public static String encode(byte[] bytes) {
        return new String(Base64.getEncoder().encode(bytes));
    }

    public static byte[] generateValueHash(byte[] value) {
        if (value == null)
            return null;
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            outputStream.write(zero);
            outputStream.write(value);
            return generateHash(outputStream.toByteArray());
        } catch (IOException ignored) {
        }
        return null;
    }

    public static byte[] generateNodeHash(byte[] left, byte[] right) {
        if (left == null && right == null) {
            return null;
        }
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            outputStream.write(one);
            if (left != null) {
                outputStream.write(left);
            }
            outputStream.write(two);
            if (right != null) {
                outputStream.write(right);
            }
            return generateHash(outputStream.toByteArray());
        } catch (IOException ignored) {
        }
        return null;
    }

    private static byte[] generateHash(byte[] bytes) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("SHA-256");
            return digest.digest(bytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
