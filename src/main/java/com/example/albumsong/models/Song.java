package com.example.albumsong.models;

public class Song {
    private int id;
    private String img;
    private String name;
    private String country;
    private String date;
    private String singer;
    private String category;


    public Song(int id, String img, String name, String country, String date, String singer, String category) {
        this.id = id;
        this.img = img;
        this.name = name;
        this.country = country;
        this.date = date;
        this.singer = singer;
        this.category = category;
    }

    public Song(int id) {
        this.id = id;
    }

    public Song(String img, String name, String country, String date, String singer, String category) {
        this.img = img;
        this.name = name;
        this.country = country;
        this.date = date;
        this.singer = singer;
        this.category = category;
    }


    public int getId() {
        return id;
    }

    public String getImg() {
        return img;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getDate() {
        return date;
    }

    public String getSinger() {
        return singer;
    }

    public String getCategory() {
        return category;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
