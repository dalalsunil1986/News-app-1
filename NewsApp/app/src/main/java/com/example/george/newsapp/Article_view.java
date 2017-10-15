package com.example.george.newsapp;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.text.Layout;
import android.view.ViewGroup;

public class Article_view extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_view);
        int width = (int)(getWindowManager().getDefaultDisplay().getWidth()/0.8);
        int height = (int)(getWindowManager().getDefaultDisplay().getHeight()/0.8);
        ViewGroup.LayoutParams mParams = new ActionBar.LayoutParams(width, height);

    }
}
