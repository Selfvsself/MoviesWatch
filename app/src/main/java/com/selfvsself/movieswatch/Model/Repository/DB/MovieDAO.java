package com.selfvsself.movieswatch.Model.Repository.DB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.selfvsself.movieswatch.Model.Movie;

import java.util.List;

@Dao
public interface MovieDAO {

    @Query("SELECT * FROM movie ORDER BY rating DESC")
    List<Movie> getAll();

    @Query("SELECT * FROM movie WHERE id = :id")
    Movie getById(int id);

    @Insert
    void insert(Movie movie);

    @Delete
    void delete(Movie movie);
}
