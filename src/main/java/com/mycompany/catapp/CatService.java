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
import java.io.IOException;

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
        
        for (Cat cat : cats) {
            System.out.println("--- " + cat);
        }
    }
}
