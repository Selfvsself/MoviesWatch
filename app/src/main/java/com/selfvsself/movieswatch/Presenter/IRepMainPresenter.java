package com.selfvsself.movieswatch.Presenter;

import com.selfvsself.movieswatch.Model.Movie;

public interface IRepMainPresenter {

    public void notifyAboutAddingMovie(Movie movie);
    public void notifyAboutEditMovie(Movie movie);
}
