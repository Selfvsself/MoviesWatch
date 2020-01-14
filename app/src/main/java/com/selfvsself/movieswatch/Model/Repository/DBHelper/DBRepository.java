package com.selfvsself.movieswatch.Model.Repository.DBHelper;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.selfvsself.movieswatch.Model.Movie;

import java.util.ArrayList;
import java.util.List;

public class DBRepository {

    private SQLiteDatabase database;
    private List<Movie> movieList;

    public DBRepository(DBHelper dbHelper) {
        database = dbHelper.getWritableDatabase();
    }

    public void addMovie(Movie movie) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.MOVIE_TITLE, movie.getTitle());
        contentValues.put(DBHelper.MOVIE_GENRE, movie.getGenre());
        contentValues.put(DBHelper.MOVIE_DESCRIPTION, movie.getDescription());
        contentValues.put(DBHelper.MOVIE_RATING, movie.getRating());

        database.insert(DBHelper.DATABASE_NAME, null, contentValues);
    }

    public List<Movie> readAll() {
        movieList = new ArrayList<>();
        Cursor cursor = database.query(DBHelper.DATABASE_NAME, null, null,
                null,null,null,null);

        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(DBHelper.MOVIE_ID);
            int titleIndex = cursor.getColumnIndex(DBHelper.MOVIE_TITLE);
            int genreIndex = cursor.getColumnIndex(DBHelper.MOVIE_GENRE);
            int descriptionIndex = cursor.getColumnIndex(DBHelper.MOVIE_DESCRIPTION);
            int ratingIndex = cursor.getColumnIndex(DBHelper.MOVIE_RATING);

            do {
                Movie addMovie = new Movie();
                addMovie.setId(cursor.getInt(idIndex));
                addMovie.setTitle(cursor.getString(titleIndex));
                addMovie.setGenre(cursor.getString(genreIndex));
                addMovie.setDescription(cursor.getString(descriptionIndex));
                addMovie.setRating(cursor.getString(ratingIndex));
                movieList.add(addMovie);
            } while (cursor.moveToNext());
            cursor.close();
        }
        return movieList;
    }

    public void deleteMovie(Movie movie) {
        database.delete(DBHelper.DATABASE_NAME,
                DBHelper.MOVIE_ID + "= ?", new String[] {movie.getId()});
    }

    public void update(Movie movie) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.MOVIE_TITLE, movie.getTitle());
        contentValues.put(DBHelper.MOVIE_GENRE, movie.getGenre());
        contentValues.put(DBHelper.MOVIE_DESCRIPTION, movie.getDescription());
        contentValues.put(DBHelper.MOVIE_RATING, movie.getRating());
        database.update(DBHelper.DATABASE_NAME, contentValues, DBHelper.MOVIE_ID + " = ?",
                new String[] {movie.getId()});
    }
}
