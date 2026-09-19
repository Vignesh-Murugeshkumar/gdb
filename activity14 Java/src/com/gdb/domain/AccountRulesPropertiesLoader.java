package com.gdb.domain;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class AccountRulesPropertiesLoader {
    private Properties properties = new Properties();

    public AccountRulesPropertiesLoader(String configPath) {
        loadProperties(configPath);
    }

    // Step 2.1 - Load the key=value pairs from configPath into 'properties' with properties.load(InputStream)
    private void loadProperties(String configPath) {
        InputStream in = null;
        try {
            in = getClass().getClassLoader().getResourceAsStream(configPath);
            if (in == null) {
                String resPath = configPath.startsWith("/") ? configPath : "/" + configPath;
                in = getClass().getResourceAsStream(resPath);
            }
            if (in == null) {
                File file = new File(configPath);
                if (!file.exists()) {
                    File altFile = new File("activity14 Java", configPath);
                    if (altFile.exists()) {
                        file = altFile;
                    }
                }
                if (file.exists()) {
                    in = new FileInputStream(file);
                }
            }
            if (in != null) {
                properties.load(in);
            }
        } catch (Exception e) {
            System.err.println("Warning: Failed to load properties from " + configPath + ": " + e.getMessage());
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception ignored) {
                }
            }
        }
    }

    // Step 2.2 - Return the value stored for key, or defaultValue if the key is missing.
    public String getProperty(String key, String defaultValue) {
        String val = properties.getProperty(key);
        return (val != null) ? val : defaultValue;
    }

    // Step 2.2 - Parse the value for key as a double (trim it first).
    // Return defaultValue if the key is missing or the value is not a number (NumberFormatException).
    public double getDouble(String key, double defaultValue) {
        String val = properties.getProperty(key);
        if (val == null) {
            return defaultValue;
        }
        val = val.trim();
        if (val.isEmpty()) {
            return defaultValue;
        }
        try {
            return Double.parseDouble(val);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    // Step 2.2 - Same as getDouble, but parse the value with Integer.parseInt.
    public int getInt(String key, int defaultValue) {
        String val = properties.getProperty(key);
        if (val == null) {
            return defaultValue;
        }
        val = val.trim();
        if (val.isEmpty()) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(val);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}
