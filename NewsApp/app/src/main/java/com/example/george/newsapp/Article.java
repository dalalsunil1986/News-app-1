package com.example.george.newsapp;

import java.util.Arrays;

/**
 * Created by George on 12-Oct-17.
 */

public class Article {

    public int id;
    public String Title;
    public String Body;
    public String Author;
    public String Date;
    public String Time;
    public String Region;
    public byte[] photo;


    public Article(int id, String title, String body, String author, String date, String time, String region) {

        this.id = id;
        Title = title;
        Body = body;
        Author = author;
        Date = date;
        Time = time;
        Region = region;
    }
}
