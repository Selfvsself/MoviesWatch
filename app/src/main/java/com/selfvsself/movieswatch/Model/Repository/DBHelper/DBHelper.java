package com.selfvsself.movieswatch.Model.Repository.DBHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "movies_database";
    public static final String MOVIE_ID = "_id";
    public static final String MOVIE_TITLE  = "title";
    public static final String MOVIE_GENRE = "genre";
    public static final String MOVIE_DESCRIPTION = "description";
    public static final String MOVIE_RATING = "rating";

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(String.format("CREATE TABLE %s ( %s integer primary key autoincrement, %s TEXT, %s TEXT, %s TEXT, %s INTEGER );",
                DATABASE_NAME, MOVIE_ID, MOVIE_TITLE, MOVIE_GENRE, MOVIE_DESCRIPTION, MOVIE_RATING));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
