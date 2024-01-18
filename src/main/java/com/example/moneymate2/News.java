package com.example.moneymate2;


import java.io.Serializable;

public class News implements Serializable {
    private String id;
    private String image;
    private String title;
    private String description;
    private String url;


    public News() {

    }



    public String getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public String getImage() {
        return image;
    }
    public String getUrl() {
        return url;
    }


    public void setImage(String image) {
        this.image = image;
    }
    public void setId(String id) {
        this.id = id;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setUrl(String url) {
        this.url = url;
    }


}

