package com.db.graduate.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

public class PropertyLoader {
    private static String PROPERTY_FILE = System.getProperty(
            "dbConnector", "dbConnector.properties");

    public static Properties loadProperties() throws InvalidPropertiesFormatException {
        Properties properties = new Properties();
        InputStream input = null;

        try {

            input = PropertyLoader.class.getClassLoader().getResourceAsStream(PROPERTY_FILE);
            if (input == null) {
                throw new FileNotFoundException("Sorry, unable to find " + PROPERTY_FILE);
            }

            //load a properties file from class path, inside static method
            properties.load(input);

            return properties;

        } catch (IOException ex) {
            throw new InvalidPropertiesFormatException("Invalid properties: " + PROPERTY_FILE);
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
