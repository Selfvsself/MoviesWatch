package com.selfvsself.movieswatch.Presenter;

import androidx.cardview.widget.CardView;

import com.selfvsself.movieswatch.AppModule;
import com.selfvsself.movieswatch.DaggerAppComponent;
import com.selfvsself.movieswatch.Model.Movie;
import com.selfvsself.movieswatch.Model.RecyclerAdapter;
import com.selfvsself.movieswatch.Model.Repository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MainPresenter implements IMainPresenter{

    private Repository repository;
    private RecyclerAdapter recyclerAdapter;

    @Inject
    public MainPresenter(Repository repository) {
        this.repository = repository;
        recyclerAdapter = new RecyclerAdapter(getAllMovies());
    }

    private List<Movie> getAllMovies() {
        return repository.getAllMovies();
    }

    @Override
    public RecyclerAdapter getRecyclerAdapter() {
        return recyclerAdapter;
    }
}
