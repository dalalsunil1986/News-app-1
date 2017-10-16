package com.example.george.newsapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;

public class AddArticle extends Activity {

    String title, body, author, date, time, region;
    DatabaseReference mDb;
    int count;
    Uri downloadUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_article);
        FirebaseDatabase.getInstance().getReference().child("/Articles/Count").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                count=Integer.parseInt(dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
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
                Article myArticle;
                if(downloadUrl!=null)
                    myArticle = new Article(count, title, body, author, date, time, region, downloadUrl.toString());
                else myArticle = new Article(count, title, body, author, date, time, region);
                mDb.child("/Articles/"+count).setValue(myArticle);
                mDb.child("/Articles/Count").setValue(count+1);
                finish();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    static final int REQUEST_IMAGE_CAPTURE = 1;

    public void dispatchTakePictureIntent(View v) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] imageData = baos.toByteArray();
            StorageReference imageRef = FirebaseStorage.getInstance().getReference().child("/Articles/Images/"+count);
            UploadTask uploadTask = imageRef.putBytes(imageData);
            uploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    // Handle unsuccessful uploads
                }
            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                    downloadUrl = taskSnapshot.getDownloadUrl();
                }
            });
        }
    }
}
