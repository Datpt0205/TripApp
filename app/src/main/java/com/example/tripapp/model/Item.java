package com.example.tripapp.model;

import java.io.Serializable;

public class Item implements Serializable {
    private int id;
    private String title, category, price, date;

    public Item(){

    }

    public Item(int id, String title, String category, String price, String date){
        this.id = id;
        this.title = title;
        this.category = category;
        this.price = price;
        this.date = date;
    }

    public Item(String title, String category, String price, String date){
        this.title = title;
        this.category = category;
        this.price = price;
        this.date = date;
    }

    public int getId(){
        return  id;
    }

    public void setId(int id){
        this.id = id;
    }
}
