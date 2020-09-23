/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.catapp;

import com.google.gson.Gson;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
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
        
        Request request = new Request.Builder()
            .url(baseUrl + "/images/search")
            .get()
            .build();
        
        Response response = okClient.newCall(request).execute();        
        String jsonData = response.body().string();        
        response.body().close();
        
        Gson gson = new Gson();
        Cat[] cats = gson.fromJson(jsonData, Cat[].class);
                
        HttpURLConnection httpcon = (HttpURLConnection) new URL(cats[0].getUrl()).openConnection();
        httpcon.addRequestProperty("User-Agent", "");
        BufferedImage bufferedImage = ImageIO.read(httpcon.getInputStream());
        ImageIcon catImage = new ImageIcon(bufferedImage);
        
        if (catImage.getIconWidth() > 800 || catImage.getIconHeight() > 600) {
            Image image = catImage.getImage();
            Image rescaling = image.getScaledInstance(800, 600, Image.SCALE_SMOOTH);
            catImage = new ImageIcon(rescaling);
        }
        
        int selection = -1;
        
        ArrayList<String> options = new ArrayList<>();
        options.add("1. See other picture");
        options.add("2. Bookmark cat");
        options.add("3. Go back");
        
        String option = (String) JOptionPane.showInputDialog(null, "Choose an option", "Cat id: " + cats[0].getId(),
            JOptionPane.INFORMATION_MESSAGE, catImage, options.toArray(), options.get(0));
        
        selection = options.indexOf(option);        
        
        switch (selection) {
            case 0:
                showCats();
                break;
            case 1:
                bookmarkCat(cats[0].getId());
                break;
        }        
    }
    
    public void listBookmarks() {
        String baseUrl = PropertyLoader.getInstance().getBaseUrl();
        
        OkHttpClient client = new OkHttpClient();
        
        Request request = new Request.Builder()
            .url(baseUrl + "/favourites")
            .get()
            .addHeader("x-api-key", PropertyLoader.getInstance().getApiKey())
            .build();
        
        try {
            Response response = client.newCall(request).execute();
            
            String jsonData = response.body().string();
            
            Gson gson = new Gson();
            
            CatBookmark[] bookmarks = gson.fromJson(jsonData, CatBookmark[].class);
            
            StringBuilder message = new StringBuilder("Bookmarked cat ids:\n");
            
            for (CatBookmark bookmark : bookmarks) {
                message.append(bookmark.getImageId());
                message.append("\n");
            }
            
            JOptionPane.showMessageDialog(null, message, "Bookmarks", JOptionPane.INFORMATION_MESSAGE);
                        
            response.body().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void bookmarkCat(String id) throws IOException {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\n  \"image_id\": \""+ id + "\"\n}");
        Request request = new Request.Builder()
            .url(PropertyLoader.getInstance().getBaseUrl() + "/favourites")
            .post(body)
            .addHeader("content-type", "application/json")
            .addHeader("x-api-key", PropertyLoader.getInstance().getApiKey())
            .build();

        try {
            Response response = client.newCall(request).execute();
        
            if (response.code() == 200)
                JOptionPane.showMessageDialog(null, "Bookmark added!", "Attention!", JOptionPane.INFORMATION_MESSAGE);                
            else
                JOptionPane.showMessageDialog(null, "Could not add bookmark!", "Attention!", JOptionPane.ERROR_MESSAGE);
        
            response.body().close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Could not add bookmark!", "Attention!", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}
