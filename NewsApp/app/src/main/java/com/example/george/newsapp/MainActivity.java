package com.example.george.newsapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.health.PackageHealthStats;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    ScrollView mScroller;
    LinearLayout mLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.deleteDatabase("NewsDB");
        mScroller = (ScrollView)findViewById(R.id.scrollView);
        mLayout = mScroller.findViewById(R.id.innerLayout);
        renderArticlesPerDate();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        renderArticlesPerDate();
    }

    protected void renderArticlesPerDate()
    {
        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        DatabaseReference mArticlesRef = mDatabase.getReference().child("/Articles");
        Log.e("Listener", "Adding");
        mArticlesRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mLayout.removeAllViews();
                Log.e("Data", "Changed");
                int count = Integer.parseInt(dataSnapshot.child("/Count").getValue().toString());
                for(int i=0;i<count&& i<10;i++)
                {
                    Log.e("Data", "Entered the loop");
                    DataSnapshot currentArticle = dataSnapshot.child("/"+i);
                    Article mArticle = new Article(i, currentArticle.child("Title").getValue().toString(), currentArticle.child("Body").getValue().toString(), currentArticle.child("Author").getValue().toString(), currentArticle.child("Date").getValue().toString(), currentArticle.child("Time").getValue().toString(), currentArticle.child("Region").getValue().toString());
                    LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService
                            (Context.LAYOUT_INFLATER_SERVICE);
                    View articleSection = inflater.inflate(R.layout.article_section, null);
                    articleSection.setOnClickListener(layoutClicker);
                    articleSection.setTag(i);
                    ((TextView) articleSection.findViewById(R.id.titleText)).setText(mArticle.Title);
                    ((TextView) articleSection.findViewById(R.id.dateView)).setText(mArticle.Date + ", " + mArticle.Time);
                    mLayout.addView(articleSection);
                }
//                return myArticles;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void renderArticle(View v)
    {
        int tag = Integer.parseInt(v.getTag().toString());
        Intent viewArticle = new Intent(this, Article_view.class);
        startActivity(viewArticle);
    }

    View.OnClickListener layoutClicker = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            renderArticle(view);
        }
    };

    public void addArticlePage(View v)
    {
        Intent addArticle = new Intent(this, AddArticle.class);
        startActivity(addArticle);
    }
}
