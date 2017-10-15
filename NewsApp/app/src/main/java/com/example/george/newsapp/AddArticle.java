package com.example.george.newsapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddArticle extends Activity {

    String title, body, author, date, time, region;
    DatabaseReference mDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_article);
    }

    public void addArticle(View v)
    {
        title = ((EditText)findViewById(R.id.editTitle)).getText().toString();
        body = ((EditText)findViewById(R.id.editBody)).getText().toString();
        author = ((EditText)findViewById(R.id.editAuthor)).getText().toString();
        date = ((EditText)findViewById(R.id.editDate)).getText().toString();
        time = ((EditText)findViewById(R.id.editTime)).getText().toString();
        region = ((EditText)findViewById(R.id.editRegion)).getText().toString();
        // Get the count from Firebase
        mDb = FirebaseDatabase.getInstance().getReference();
        mDb.child("/Articles/Count").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int count = Integer.parseInt(dataSnapshot.getValue().toString());
                Article myArticle = new Article(count, title, body, author, date, time, region);
                mDb.child("/Articles/"+count).setValue(myArticle);
                mDb.child("/Articles/Count").setValue(count+1);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
