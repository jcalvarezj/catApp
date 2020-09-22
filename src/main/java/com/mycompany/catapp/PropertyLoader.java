/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.catapp;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author juan
 */
public final class PropertyLoader {
    
    public String getApiKey() {
        Properties props = new Properties();
        String propertiesFile = "config.properties";
        
        String result = "";
        
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propertiesFile)) {
            
            if (inputStream != null)
                props.load(inputStream);
            else
                throw new FileNotFoundException("Property file " + propertiesFile + " not found in ClassPath");
            
            result = props.getProperty("apiKey");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return result;
    }
    
}