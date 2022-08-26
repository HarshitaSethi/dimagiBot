/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dimagi.dimagiBot.util;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 *
 * @author harshita.sethi
 */
@Configuration
@PropertySource("classpath:application.properties")
public class ReadPropertiesFile {

    private static Properties properties = new Properties();

    static {
        try {
            properties.load(new InputStreamReader(ReadPropertiesFile.class.getResourceAsStream("/application.properties")));
        } catch (IOException ex) {
            Logger.getLogger(ReadPropertiesFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static String getPropertyValue(String property) {
        return properties.getProperty(property);
    }

}
