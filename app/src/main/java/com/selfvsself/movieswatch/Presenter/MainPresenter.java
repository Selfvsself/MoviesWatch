package com.selfvsself.movieswatch.Presenter;

import androidx.cardview.widget.CardView;

import com.selfvsself.movieswatch.Model.Movie;

import java.util.ArrayList;
import java.util.List;

public class MainPresenter implements IMainPresenter{

    public MainPresenter() {
    }

    @Override
    public List<Movie> getAllMovies() {
        List<Movie> list = new ArrayList<>();
        list.add(new Movie());
        list.add(new Movie());
        list.add(new Movie());
        list.add(new Movie());
        list.add(new Movie());
        list.add(new Movie());
        return list;
    }
}
