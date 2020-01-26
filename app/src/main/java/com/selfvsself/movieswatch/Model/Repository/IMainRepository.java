package com.selfvsself.movieswatch.Model.Repository;

import com.selfvsself.movieswatch.Model.Movie;

import java.util.List;

import io.reactivex.subjects.PublishSubject;

public interface IMainRepository {

    List<Movie> getMovieList();

    void dispose();

    Movie deleteMovie(int index);

    boolean addMovie(Movie movie);

    boolean editMovie(Movie movie);

    Movie getMovieByIndex(int index);

    Movie getMovieByID(int id);

    List<String> getAllGenre();

    PublishSubject<Integer> getAddMovieSubject();

    PublishSubject<Integer> getDeleteMovieSubject();
}
