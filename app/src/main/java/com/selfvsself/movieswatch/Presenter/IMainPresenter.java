package com.selfvsself.movieswatch.Presenter;

import com.selfvsself.movieswatch.Model.Movie;
import com.selfvsself.movieswatch.Model.RecyclerAdapter;

public interface IMainPresenter {

    public RecyclerAdapter getRecyclerAdapter();

    public Movie deleteMovie(int index);

    public void addMovie(Movie movie);

    public void refreshItem(int position);

    public void sortByName();
    public void sortByNameDown();
    public void sortByGenre();
    public void sortGenreDown();
    public void sortByRating();
    public void sortByRatingDown();
}
