package com.example.george.newsapp;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.Layout;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.w3c.dom.Text;

public class Article_view extends Activity {
    protected int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_view);
        id = getIntent().getIntExtra("Article tag", 0);
        // Get the value of the article from the firebase
        FirebaseDatabase.getInstance().getReference().child("/Articles/"+id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // We got the data, edit the article xml
                ((TextView)findViewById(R.id.titleView)).setText(dataSnapshot.child("Title").getValue().toString());
                ((TextView)findViewById(R.id.bodyView)).setText(dataSnapshot.child("Body").getValue().toString());
                Uri download = Uri.parse(dataSnapshot.child("photo").toString());
//                StorageReference mReference = FirebaseStorage.getInstance().getReferenceFromUrl(download.toString());
                StorageReference photoRef = FirebaseStorage.getInstance().getReference().child("Articles").child("Images").child(id+"");
                photoRef.getBytes(1024*1024).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                    @Override
                    public void onSuccess(byte[] bytes) {
                        // We got the bytes for the file.
                        // Decode into bitmap and show them into the imageview
                        Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                        ((ImageView)findViewById(R.id.imageView)).setImageBitmap(bmp);
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(Article_view.this, "Fetch failed! Try again later.", Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }
}
