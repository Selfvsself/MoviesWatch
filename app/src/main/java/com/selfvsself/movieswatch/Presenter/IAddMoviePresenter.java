package com.selfvsself.movieswatch.Presenter;

import com.selfvsself.movieswatch.Model.Movie;
import com.selfvsself.movieswatch.View.AddMovieActivityView;
import com.selfvsself.movieswatch.View.EditMovieActivity;

public interface IAddMoviePresenter {

    public String[] getAllGenres();

    public void setProgressSeekBar(int progress);

    public void setView(AddMovieActivityView view);

    public boolean saveMovie();

    public Movie getMovie(int index);

    public boolean editMovie(int id);
}
