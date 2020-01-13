package com.selfvsself.movieswatch.Presenter;

import com.selfvsself.movieswatch.Model.Movie;
import com.selfvsself.movieswatch.Model.RecyclerAdapter;
import com.selfvsself.movieswatch.Model.Repository.Repository;

import java.util.List;

import javax.inject.Inject;

public class MainPresenter implements IMainPresenter, IRepMainPresenter{

    private Repository repository;
    private RecyclerAdapter recyclerAdapter;
    private List<Movie> movieList;

    @Inject
    public MainPresenter(Repository repository) {
        this.repository = repository;
        recyclerAdapter = new RecyclerAdapter(getAllMovies());
        repository.setMainPresenter(this);
    }

    private List<Movie> getAllMovies() {
        movieList = repository.getAllMovies();
        return movieList;
    }

    @Override
    public RecyclerAdapter getRecyclerAdapter() {
        return recyclerAdapter;
    }

    @Override
    public Movie deleteMovie(int index) {
        Movie deletedMovie = movieList.get(index);
        repository.deleteMovie(deletedMovie);
        movieList.remove(index);
        recyclerAdapter.notifyItemRemoved(index);
        return deletedMovie;
    }

    @Override
    public void addMovie(Movie movie) {
        repository.addMovies(movie);
    }

    @Override
    public void notifyAboutAddingMovie(Movie movie) {
        movieList.add(movie);
        recyclerAdapter.notifyItemInserted(movieList.size() - 1);
    }
}
