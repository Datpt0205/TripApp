package com.example.tripapp.model;

import java.io.Serializable;

public class Item implements Serializable {
    private int id;
    private String name, destination, date, risk, description;

    public Item(){

    }

    public Item(int id, String name, String destination, String date, String risk, String description){
        this.id = id;
        this.name = name;
        this.destination = destination;
        this.date = date;
        this.risk = risk;
        this.description = description;
    }

    public Item(String name, String destination, String date, String risk,String description){
        this.name = name;
        this.destination = destination;
        this.date = date;
        this.risk = risk;
        this.description = description;
    }

    public int getId(){
        return  id;
    }
    public void setId(int id){
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getDestination() {
        return destination;
    }
    public void setDestination(String destination){
        this.destination = destination;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date){
        this.date = date;
    }

    public String getRisk() {
        return risk;
    }
    public void setRisk(String risk){
        this.risk = risk;
    }

    public String getDescription(){
        return description;
    }
    public void setDescription(String description){
        this.description = description;
    }
}
