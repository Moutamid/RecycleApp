package com.choubapp.myproject;

public class firebasemodel
{
    String material, item, date, note;

    public firebasemodel() {
    }

    //to store variable values
    public firebasemodel(String material, String item, String date, String note) {
        this.material = material;
        this.item = item;
        this.date = date;
        this.note = note;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}

