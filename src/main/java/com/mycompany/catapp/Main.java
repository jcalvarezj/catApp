/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.catapp;

/**
 *
 * @author juan
 */
public final class Main {
    public static void main(String[] args) {
        System.out.println("Gonna read!!!");
        
        PropertyLoader loader = new PropertyLoader();
        
        System.out.println("---> " + loader.getApiKey());
    }
}
