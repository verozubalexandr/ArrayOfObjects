package opu;

import java.util.Random;

public class utilityPasswordMaker {

    public static final int MIN_PASSWORD_LENGTH = 8;
    private static int SHUFFLE_NUM = 10;

    public static boolean isCorrectPassword(final String password) {
        // password validity check: at least 8 chars length, 1 UppercaseLetter, 1 lowerCase letter, 1 digit, 1 non AlphaOrDigit
        boolean hasUppercaseLetter = false;
        boolean hasLowerCaseLetter = false;
        boolean hasDigit = false;
        boolean hasNonAlphaOrDigit = false;
        boolean result = false;

        if (password.length() > MIN_PASSWORD_LENGTH) {
            for (final Character character : password.toCharArray()) {
                if (Character.isUpperCase(character)) {
                    hasUppercaseLetter = true;
                    break;
                }
            }
            for (final Character character : password.toCharArray()) {
                if (Character.isLowerCase(character)) {
                    hasLowerCaseLetter = true;
                    break;
                }
            }
            for (final Character character : password.toCharArray()) {
                if (Character.isDigit(character)) {
                    hasDigit = true;
                    break;
                }
            }
            for (final Character character : password.toCharArray()) {
                if (!Character.isAlphabetic(character) && !Character.isDigit(character)) {
                    hasNonAlphaOrDigit = true;
                    break;
                }
            }

            if (hasUppercaseLetter && hasLowerCaseLetter && hasDigit && hasNonAlphaOrDigit) result = true;
        }

        return result;
    }

    //task 2
    public static String generatePassword() {
        String password = "";

        // password generation
        final String chars = "!@#$%^&*()_+~`|\\?><";
        final String upperCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        final String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
        final String digits = "0123456789";
        Random random = new Random();

        for (int i = 0; i < 2; i++) {
            password += chars.charAt(random.nextInt(chars.length()));
            password += upperCaseLetters.charAt(random.nextInt(upperCaseLetters.length()));
            password += lowerCaseLetters.charAt(random.nextInt(lowerCaseLetters.length()));
            password += digits.charAt(random.nextInt(digits.length()));
        }

        // todo chars of the password MANY times to prevent analysis
        char[] passwordToChar = password.toCharArray();
        int reverse = 1;
        int index;
        for (int i = 0; i < SHUFFLE_NUM; i++) {
            if (i >= password.length() - 1) {
                index = i - reverse;
                reverse++;
            } else
                index = i;
            int randomIndex = ((int) (Math.random() * password.length()));
            char memoryChar = passwordToChar[index];
            if (randomIndex != index) {
                passwordToChar[index] = passwordToChar[randomIndex];
                passwordToChar[randomIndex] = memoryChar;
            }
        }
        String outPassword = new String(passwordToChar);

        return outPassword;
    }
}
