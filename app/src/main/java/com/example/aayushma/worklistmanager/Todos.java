package com.example.aayushma.worklistmanager;

import java.io.Serializable;

/**
 * Created by aayushma on 2/29/2016.
 */
public class Todos  implements Serializable{
    public int id;
    public  String lists;
    public  String description;
    public  String datetime;

    public Todos(int id, String lists, String description, String datetime) {
        this.id=id;
        this.lists = lists;
        this.description = description;
        this.datetime = datetime;
    }
    public Todos(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLists() {
        return lists;
    }

    public void setLists(String lists) {
        this.lists = lists;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }
}
