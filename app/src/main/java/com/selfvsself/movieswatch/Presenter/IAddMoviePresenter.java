package com.selfvsself.movieswatch.Presenter;

import com.selfvsself.movieswatch.Model.Movie;

import java.util.List;

public interface IAddMoviePresenter {

    List<String> getAllGenres();

    boolean addMovie(Movie movie);

    boolean editMovie(Movie movie);

    String getHintByRating(int rating);

    Movie getMovieByID(int id);
}
