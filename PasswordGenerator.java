import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PasswordGenerator {

    private static final String UPPER = "ABCDEFGHJKLMNPQRSTUVWXYZ"; // Removed I, O
    private static final String LOWER = "abcdefghijkmnopqrstuvwxyz"; // Removed l
    private static final String DIGITS = "123456789"; // Removed 0
    private static final String SPECIAL = "!@#$%^&*()_-+=<>?/[]{}";

    public static String generateStrongPassword(int length) {
        if (length < 12) {
            throw new IllegalArgumentException("Password length must be at least 12 characters.");
        }

        String allChars = UPPER + LOWER + DIGITS + SPECIAL;
        SecureRandom random = new SecureRandom();

// Instead of a ArrayList, create an empty array of the exact length needed
        char[] passwordChars = new char[length];

// Fill the first 4 slots
        passwordChars[0] = UPPER.charAt(random.nextInt(UPPER.length()));
        passwordChars[1] = LOWER.charAt(random.nextInt(LOWER.length()));
        passwordChars[2] = DIGITS.charAt(random.nextInt(DIGITS.length()));
        passwordChars[3] = SPECIAL.charAt(random.nextInt(SPECIAL.length()));

// Fill the rest
        for (int i = 4; i < length; i++) {
            passwordChars[i] = allChars.charAt(random.nextInt(allChars.length()));
        }

// Shuffle the array manually (since Collections. shuffle doesn't work on primitive arrays)
        for (int i = passwordChars.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            char temp = passwordChars[i];
            passwordChars[i] = passwordChars[j];
            passwordChars[j] = temp;
        }

// Convert directly to String without a loop!
        return new String(passwordChars);

    }

    public static void main(String[] args) {
        // Simulating the user clicking "Generate Password" on your sign-up page
        try {
            String suggestedPassword = PasswordGenerator.generateStrongPassword(16);
            System.out.println("Suggested Password for New User: " + suggestedPassword);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
