package allcom.pages;

import java.security.SecureRandom;

public class DataRandom {
    public static final String abcABC = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String DIG = "0123456789";
    public static final String ABC_DIG = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ01234567890123456789";
    public static final String ABC_SPACE_DIG = "abcdefghijklmnopqrstuvwxyz ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String HOUSE = "0123456789#";
    public static final String abc = "abcdefghijklmnopqrstuvwxyz";
    public static final String ABC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String CHAR = "!@#$%^&*";
    public static final String ALL = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*";
    public static final String AT = "@";
    public static final String DOT = ".";
    private static final SecureRandom random = new SecureRandom();
    public static String randomString(int min, int max, String chars) {
        if (min > max || min < 0) {
            throw new IllegalArgumentException("Invalid minLength or maxLength");
        }
        int length = min + random.nextInt(max - min + 1);
        StringBuilder result = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            char c = chars.charAt(random.nextInt(chars.length()));
            result.append(c);
        }
        return result.toString();
    }

    public static String randomEmail(int minUser, int maxUser, int minAt, int maxAt, int minDomain, int maxDomain, int minDot, int maxDot, int minCom, int maxCom) {
        String user = randomString(minUser, maxUser, ABC_DIG);
        String at = randomString(minAt, maxAt, AT);
        String domain = randomString(minDomain, maxDomain, ABC_DIG);
        String dot = randomString(minDot, maxDot, DOT);
        String com = randomString(minCom, maxCom, abcABC);
        return user + at + domain + dot + com;
    }

    public static String randomPhone(int minPhone, int maxPhone) {
        return randomString(minPhone, maxPhone, DIG);
    }

    public static String randomPassword(int minLength, int maxLength) {
        if (minLength > maxLength || minLength < 0) {
            throw new IllegalArgumentException("Invalid minLength or maxLength");
        }
        String allChars = ALL;
        StringBuilder password = new StringBuilder(minLength);
        password.append(randomChar(abc)).append(randomChar(ABC)).append(randomChar(DIG)).append(randomChar(CHAR));
        for (int i = 4; i < maxLength; i++) {
            char c = allChars.charAt(random.nextInt(allChars.length()));
            password.append(c);
        }
        return password.toString();
    }

    private static char randomChar(String chars) {
        return chars.charAt(DataRandom.random.nextInt(chars.length()));
    }
    public static String getValidFirstName() {
        return randomString(2, 20, abcABC);
    }
    public static String getValidLastName() {
        return randomString(2, 20, abcABC);
    }
    public static String getValidEmail() {
        return randomEmail(3, 15, 1, 1, 2, 20, 1, 1, 2, 10);
    }
    public static String getValidPhoneNumber() {
        return randomPhone(10, 12);
    }
    public static String getValidPassword() {
        return randomPassword(8, 25);
    }
    public static String getValidCompanyName() {
        return randomString(2, 25, ABC_DIG);
    }
    public static String getValidPosition() {
        return randomString(2, 25, ABC_SPACE_DIG);
    }
    public static String getValidTaxNumber() {
        return randomString(9, 20, DIG);
    }
    public static String getValidPostIndex() {
        return randomString(5, 5, DIG);
    }
    public static String getValidCity() {
        return randomString(3, 30, ABC_SPACE_DIG);
    }
    public static String getValidStreet() {
        return randomString(10, 25, ABC_SPACE_DIG);
    }
    public static String getValidHouseNumber() {
        return randomString(1, 5, HOUSE);
    }

}
