package com.selfvsself.movieswatch.Presenter;

import com.selfvsself.movieswatch.Model.Movie;
import com.selfvsself.movieswatch.Model.RecyclerAdapter;

public interface IMainPresenter {

    RecyclerAdapter getRecyclerAdapter();

    void dispose();

    Movie deleteMovie(int index);

    void restoringMovie(Movie movie);

    int getMovieID(int index);

    void refreshViewOnItem(int index);
}
