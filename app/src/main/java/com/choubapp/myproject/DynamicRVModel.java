package com.choubapp.myproject;

public class DynamicRVModel {

    String name, details;
    private int image;

    public DynamicRVModel(String name, String details, int image) {

        this.name = name;
        this.details = details;
        this.image = image;

    }

    public String getName() {
        return name;
    }

    public String getDetails(){

        return details;
    }

    public int getImage(){

        return image;
    }


}

