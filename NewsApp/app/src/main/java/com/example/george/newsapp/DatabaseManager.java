package com.example.george.newsapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by George on 11-Oct-17.
 */

public class DatabaseManager {

    // We define the version of the database and it's name
    public static final String DB_NAME = "NewsDB";
    public static final int DB_VER=1;

    // We define the table name here:
    public static final String ARTICLE_TABLE = "ARTICLES";
    // We define the fields in the table
    public static final String ID = "ID";
    public static final String TITLE = "TITLE";
    public static final String BODY = "BODY";
    public static final String PHOTOS = "PHOTOS";
    public static final String AUTHOR = "AUTHOR";
    public static final String DATE = "DATE";
    public static final String TIME = "TIME";
    public static final String REGION = "REGION";

    public final DatabaseTool mDbTool;

    public static final String TABLE_ARTICLE_SCRIPT = "CREATE TABLE "+ARTICLE_TABLE+" ("+ID+" integer primary key autoincrement, "
            + TITLE+" text not null, "
            + BODY + " text not null, "
            + AUTHOR + " text not null, "
            + DATE + " text not null, "
            + TIME + " text not null, "
            + REGION + " text not null, "
            + PHOTOS + " blob)";

    public DatabaseManager(Context context) {
        mDbTool = new DatabaseTool(context, DB_NAME, null, DB_VER);
    }

    private class DatabaseTool extends SQLiteOpenHelper {
        public DatabaseTool(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL("drop table if exists "+DB_NAME);
            sqLiteDatabase.execSQL(TABLE_ARTICLE_SCRIPT);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }


    }

    public void addArticle(String title, String body, byte[] photo, String author, String date, String time, String region)
    {
        ContentValues mCv = new ContentValues();
        mCv.put(TITLE, title);
        mCv.put(BODY, body);
        mCv.put(PHOTOS, photo);
        mCv.put(AUTHOR, author);
        mCv.put(DATE, date);
        mCv.put(TIME, time);
        mCv.put(REGION, region);
        mDbTool.getWritableDatabase().insert(ARTICLE_TABLE, null, mCv);
    }

    public void addArticleByObject(Article mArticle)
    {
        ContentValues mCv = new ContentValues();
        mCv.put(TITLE, mArticle.getTitle());
        mCv.put(BODY, mArticle.getBody());
        mCv.put(PHOTOS, mArticle.getPhoto());
        mCv.put(AUTHOR, mArticle.getAuthor());
        mCv.put(DATE, mArticle.getDate());
        mCv.put(TIME, mArticle.getTime());
        mCv.put(REGION, mArticle.getRegion());
        mDbTool.getWritableDatabase().insert(ARTICLE_TABLE, null, mCv);
    }

    public Article getArticlebyId(String id)
    {
        SQLiteDatabase mDb = mDbTool.getReadableDatabase();
        Article mArticle=null;
        Cursor mCr = mDb.query(ARTICLE_TABLE, null, ID+"="+id, null, null, null, null);
        if(mCr.getCount()!=0)
        {
            mCr.moveToFirst();
            mArticle = new Article(mCr.getInt(0), mCr.getString(1), mCr.getString(2), mCr.getBlob(3), mCr.getString(4), mCr.getString(5), mCr.getString(6), mCr.getString(7));
        }
        return mArticle;
    }
}
