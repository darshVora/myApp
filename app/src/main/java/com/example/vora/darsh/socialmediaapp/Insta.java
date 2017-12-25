package com.example.vora.darsh.socialmediaapp;

/**
 * Created by darsh on 24-Dec-17.
 */

public class Insta {

    private String title,image,desc,username;

    public Insta() {
    }

    public Insta(String title, String image, String desc,String username) {
        this.title = title;
        this.image = image;
        this.desc = desc;
        this.username = username;
    }

    public String getTitle() {
        return title;
    }

    public String getUsername() {
        return username;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
