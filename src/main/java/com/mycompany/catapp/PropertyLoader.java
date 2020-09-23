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
 * @author jcalvarezj
 */
public final class PropertyLoader {
    
    private final static String PROPERTIES_FILE = "config.properties";    
    private static PropertyLoader instance;

    private PropertyLoader() {
    }
    
    public static PropertyLoader getInstance() {
        if (instance == null)
            instance = new PropertyLoader();
        
        return instance;
    }
    
    public String getApiKey() {
        Properties props = new Properties();
        
        String result = "";
        
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(PROPERTIES_FILE)) {
            
            if (inputStream != null)
                props.load(inputStream);
            else
                throw new FileNotFoundException("Property file " + PROPERTIES_FILE + " not found in ClassPath");
            
            result = props.getProperty("apiKey");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return result;
    }
    
    public String getBaseUrl() {
        Properties props = new Properties();
        
        String result = "";
        
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(PROPERTIES_FILE)) {
            
            if (inputStream != null)
                props.load(inputStream);
            else
                throw new FileNotFoundException("Property file " + PROPERTIES_FILE + " not found in ClassPath");
            
            result = props.getProperty("baseUrl");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return result;
    }
    
}