package com.epam.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyLoader {

    private static final  String PROPERTIES_FILE = "C:\\Users\\Vladimir_Kotovich\\lab_proj\\lab_gh_task1\\src\\test\\resources\\framework.properties";
    private static Properties prop; // = new Properties();

    public static void loadProperties() {
        FileInputStream input = null;
        File file;
        try {
            file = new File(PROPERTIES_FILE);
            input = new FileInputStream(file);
            prop = new Properties();
            prop.load(input);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    System.err.println(e.getMessage());
                }
            }
        }
    }

    public static String getProperty(String prop_name) {
        return  prop.getProperty(prop_name);
    }
}
