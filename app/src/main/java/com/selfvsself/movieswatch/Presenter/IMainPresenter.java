package com.selfvsself.movieswatch.Presenter;

import com.selfvsself.movieswatch.Model.Movie;
import com.selfvsself.movieswatch.Model.RecyclerAdapter;

public interface IMainPresenter {

    public RecyclerAdapter getRecyclerAdapter();

    public Movie deleteMovie(int index);

    public void addMovie(Movie movie);
}
