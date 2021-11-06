package com.onethreefourtwo.gamegagner;

public class ScreenItem_Intro {
    String Title,Description;
    int ScreenImg;

    public ScreenItem_Intro(String title, String description, int screenimg){
        Title = title;
        Description = description;
        ScreenImg = screenimg;
    }
    public void setTitle(String title){
        Title = title;
    }
    public void setDescription(String description){
        Description = description;
    }
    public void setScreenImg(int screenImg){
        ScreenImg = screenImg;
    }
    public String getTitle(){
        return Title;
    }
    public String getDescription(){
        return  Description;
    }
    public int getScreenImg(){
        return ScreenImg;
    }
}
