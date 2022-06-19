package com.example.myapplication;

public class Birthday {
    private int id;
    private String name;
    private int year ;
    private int month;
    private int date;

    public Birthday(int id, String name, int year, int month, int date) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.month = month;
        this.date = date;
    }
    public Birthday(){

    }

    @Override
    public String toString() {
        return "Birthday{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", year=" + year +
                ", month=" + month +
                ", date=" + date +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }
}
