/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.catapp;

import com.google.gson.annotations.SerializedName;

/**
 *
 * @author jcalvarezj
 */
public class CatBookmark {
    private String id;    
    @SerializedName("image_id")
    private String imageId;
    private CatImage image;

    public CatBookmark(String id, String imageId, CatImage image) {
        this.id = id;
        this.imageId = imageId;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public CatImage getImage() {
        return image;
    }

    public void setImage(CatImage image) {
        this.image = image;
    }
    
    
}
