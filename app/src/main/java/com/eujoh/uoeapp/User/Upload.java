package com.eujoh.uoeapp.User;

public class Upload {
    private String itemName, itemDesc, imageUrl;

    public Upload(){
//        empty constructor youtube
    }
    public Upload(String itemName, String itemDesc, String imageUrl){
        if (itemName.trim().equals("")){
            itemName = "No Name";
        }
        itemName = itemName;
        itemDesc = itemDesc;
        imageUrl = imageUrl;
    }
    public String getItemName(){
        return itemName;
    }
    public void setItemName(String itemName){
        itemName = itemName;
    }

    public String getItemDesc(){
        return itemDesc;
    }
    public void setItemDesc(String itemDesc){
        itemDesc = itemDesc;
    }

    public String getImageUrl(){
        return imageUrl;
    }
    public void setImageUrl(String imageUrl){
        imageUrl = imageUrl;
    }
}
