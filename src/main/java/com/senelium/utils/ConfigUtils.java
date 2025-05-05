package com.senelium.utils;

public class ConfigUtils {

    /**
     * Gets configuration value by first checking system properties,
     * then environment variables. Returns the default value if not found.
     */
    public static String get(String prop, String env, String defaultValue) {
        var value = System.getProperty(prop);
        if (value != null && !value.trim().isEmpty()) {
            return value.trim();
        }

        value = System.getenv(env);
        if (value != null && !value.trim().isEmpty()) {
            return value.trim();
        }

        return defaultValue;
    }

    public static String get(String prop, String env) {
        return get(prop, env, null);
    }
}
