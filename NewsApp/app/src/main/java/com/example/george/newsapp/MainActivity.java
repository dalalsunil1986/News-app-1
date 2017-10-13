package com.example.george.newsapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.health.PackageHealthStats;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    DatabaseManager mDb;
    ScrollView mScroller;
    LinearLayout mLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.deleteDatabase("NewsDB");
        mDb = new DatabaseManager(this);
        mScroller = (ScrollView)findViewById(R.id.scrollView);
        mLayout = mScroller.findViewById(R.id.innerLayout);
        renderArticlesPerDate();
    }

    protected void renderArticlesPerDate()
    {
        Article[] mArticles = mDb.getArticlesOrderedByDate();
        if(mArticles!=null) {
            for (int i = 0; i < mArticles.length && i<10; i++) {
                LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService
                        (Context.LAYOUT_INFLATER_SERVICE);
                View articleSection = inflater.inflate(R.layout.article_section, null);
                ((TextView) articleSection.findViewById(R.id.titleText)).setText(mArticles[i].getTitle());
                ((TextView) articleSection.findViewById(R.id.dateView)).setText(mArticles[i].getDate() + ", " + mArticles[i].getTime());
                byte[] byteArray = mArticles[i].getPhoto();
                Bitmap bm = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
                ((ImageView) articleSection.findViewById(R.id.thumbnailView)).setImageBitmap(bm);
                mLayout.addView(articleSection);
            }
        }
    }

    protected void addArticle()
    {
        Article mArt = new Article(0, "My Article", "This is the body", "I am the author", "13/OCT/2017", "10:30 PM", "Nazareth", new byte[0]);
        mDb.addArticleByObject(mArt);
    }
}
