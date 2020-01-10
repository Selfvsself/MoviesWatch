package com.selfvsself.movieswatch.Presenter;

import com.selfvsself.movieswatch.View.AddMovieActivityView;

public interface IAddMoviePresenter {

    public String[] getAllGenres();

    public void setProgressSeekBar(int progress);

    public void setView(AddMovieActivityView view);
}
