package com.inva.hipstertest.service.util;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * Utility class for generating random Strings.
 */
public final class RandomUtil {

    private static final int DEF_COUNT = 20;
    private static final int LOG_COUNT = 3;

    private RandomUtil() {
    }

    /**
     * Generate a password.
     *
     * @return the generated password
     */
    public static String generatePassword() {
        return RandomStringUtils.randomAlphanumeric(DEF_COUNT);
    }

    /**
     * Generate an activation key.
     *
     * @return the generated activation key
     */
    public static String generateActivationKey() {
        return RandomStringUtils.randomNumeric(DEF_COUNT);
    }

    /**
    * Generate a reset key.
    *
    * @return the generated reset key
    */
    public static String generateResetKey() {
        return RandomStringUtils.randomNumeric(DEF_COUNT);
    }

    /**
     * Generate a random login.
     *
     * @return the generated login for teacher
     */
    public static String generateLogin(String firstName, String lastName) {
        String returned = firstName.substring(0,1).toUpperCase()
            + lastName.substring(0,lastName.length()-3)
            + RandomStringUtils.randomAlphabetic(LOG_COUNT);
        return returned;
    }
}
