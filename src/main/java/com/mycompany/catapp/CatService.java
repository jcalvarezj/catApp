/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.catapp;

import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author jcalvarezj
 */
public class CatService {
    
    public void showCats() throws IOException {
        String baseUrl = PropertyLoader.getInstance().getBaseUrl();
        
        OkHttpClient okClient = new OkHttpClient();
        
        Request request = new Request
                            .Builder()
                            .url(baseUrl)
                            .get()
                            .build();
        
        Response response = okClient.newCall(request).execute();
        
        String jsonData = response.body().string();
        
        Gson gson = new Gson();
        Cat[] cats = gson.fromJson(jsonData, Cat[].class);
                
        HttpURLConnection httpcon = (HttpURLConnection) new URL(cats[0].getUrl()).openConnection();
        httpcon.addRequestProperty("User-Agent", "");
        BufferedImage bufferedImage = ImageIO.read(httpcon.getInputStream());
        ImageIcon catImage = new ImageIcon(bufferedImage);
        
        if (catImage.getIconWidth() > 800) {
            Image image = catImage.getImage();
            Image rescaling = image.getScaledInstance(800, 600, Image.SCALE_SMOOTH);
            catImage = new ImageIcon(rescaling);
        }
        
        int selection = -1;
        
        ArrayList<String> options = new ArrayList<>();
        options.add("1. See other picture");
        options.add("2. Go back");
        
        String option = (String) JOptionPane.showInputDialog(null, "Cat id: " + cats[0].getId(),
            "Choose an option", JOptionPane.INFORMATION_MESSAGE, catImage, options.toArray(), options.get(0));
        
        selection = options.indexOf(option);        
        
        switch (selection) {
            case 0:
                showCats();
                break;
        }
        
    }
}
