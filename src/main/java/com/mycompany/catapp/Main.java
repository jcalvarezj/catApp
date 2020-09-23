/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.catapp;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author jcalvarezj
 */
public final class Main {
    
    public static void main(String[] args) {
        CatService service = new CatService();
        
        int selection = -1;
        
        ArrayList<String> options = new ArrayList<>();
        options.add("1. Browse for cats");
        options.add("2. List my favorites");
        options.add("3. Exit");
        
        do {
            String option = (String) JOptionPane.showInputDialog(null, "Choose an option", "Cats App", 
                JOptionPane.INFORMATION_MESSAGE, null, options.toArray(), options.get(0));
            
            selection = options.indexOf(option);
            
            switch(selection) {
                case 0:
                    try {
                        service.showCats();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }                    
                    break;
                case 1:
                    try {
                        service.listBookmarks();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }                    
                    break;
            }
        } while (selection != options.size() - 1);
    }
    
}
