package com.selfvsself.movieswatch.Model.Repository.DB;

import com.selfvsself.movieswatch.Model.Movie;

import io.reactivex.Observable;

public interface IDBRepository {

    Observable<Movie> getReadDataBaseObservable();

    void dispose();

    void deleteMovie(Movie movie);

    void addMovie(Movie movie);
}
