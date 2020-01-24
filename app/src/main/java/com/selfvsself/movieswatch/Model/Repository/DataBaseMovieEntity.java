package com.selfvsself.movieswatch.Model.Repository;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.selfvsself.movieswatch.Model.Movie;

@Database(entities = {Movie.class}, version = 1, exportSchema = false)
public abstract class DataBaseMovieEntity extends RoomDatabase {

    public abstract MovieDAO movieDAO();
}
