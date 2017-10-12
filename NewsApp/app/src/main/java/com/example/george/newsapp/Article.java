package com.example.george.newsapp;

import java.util.Arrays;

/**
 * Created by George on 12-Oct-17.
 */

public class Article {

    protected int id;
    protected String title;
    protected String body;
    protected byte[] photo;
    protected String author;
    protected String date;
    protected String time;
    protected String region;


    public Article(int id, String title, String body, byte[] photo, String author, String date, String time, String region) {

        this.id = id;
        this.title = title;
        this.body = body;
        this.photo = photo;
        this.author = author;
        this.date = date;
        this.time = time;
        this.region = region;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
