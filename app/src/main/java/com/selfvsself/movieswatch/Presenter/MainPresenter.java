package com.selfvsself.movieswatch.Presenter;

import com.selfvsself.movieswatch.Model.Movie;
import com.selfvsself.movieswatch.Model.RecyclerAdapter;
import com.selfvsself.movieswatch.Model.Repository.IMainRepository;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;

public class MainPresenter implements IMainPresenter {

    private static final String TAG = "MainPresenter";
    private IMainRepository repository;
    private RecyclerAdapter recyclerAdapter;
    private Disposable addMovieDisposable;
    private Disposable deleteMovieDisposable;

    @Inject
    public MainPresenter(IMainRepository repository) {
        this.repository = repository;
        recyclerAdapter = new RecyclerAdapter(repository.getMovieList());
        PublishSubject<Integer> addMovieSubject = repository.getAddMovieSubject();
        addMovieDisposable = addMovieSubject.subscribe(index -> recyclerAdapter.notifyItemInserted(index),
                Throwable::printStackTrace);
        PublishSubject<Integer> deleteMovieSubject = repository.getDeleteMovieSubject();
        deleteMovieDisposable = deleteMovieSubject.subscribe(index -> recyclerAdapter.notifyItemRemoved(index),
                Throwable::printStackTrace);
    }

    @Override
    public RecyclerAdapter getRecyclerAdapter() {
        return recyclerAdapter;
    }

    @Override
    public void dispose() {
        repository.dispose();
        if (addMovieDisposable != null) addMovieDisposable.dispose();
        if (deleteMovieDisposable != null) deleteMovieDisposable.dispose();
    }

    @Override
    public Movie deleteMovie(int index) {
        return repository.deleteMovie(index);
    }

    @Override
    public void restoringMovie(Movie movie) {
        repository.addMovie(movie);
    }

    @Override
    public int getMovieID(int index) {
        Movie movie = repository.getMovieByIndex(index);
        return movie.getId();
    }

    @Override
    public void refreshViewOnItem(int index) {
        recyclerAdapter.notifyItemChanged(index);
    }
}
