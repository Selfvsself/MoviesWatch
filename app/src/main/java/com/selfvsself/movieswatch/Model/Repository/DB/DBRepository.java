package com.selfvsself.movieswatch.Model.Repository.DB;

import android.content.Context;
import android.util.Log;

import androidx.room.Room;

import com.selfvsself.movieswatch.Model.Movie;

import dagger.Module;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@Module
public class DBRepository implements IDBRepository {

    private static final String TAG = "DbRepository";
    private MovieDAO movieDAO;
    private Observable<Movie> readDBObservable;
    private Disposable movieDeleteDisposable;
    private Disposable movieAddDisposable;

    public DBRepository(Context context) {
        DataBaseMovieEntity dataBase = Room.databaseBuilder(context, DataBaseMovieEntity.class, "rmovie_database").build();
        movieDAO = dataBase.movieDAO();
        readDBObservable = Observable.fromCallable(movieDAO::getAll)
                .subscribeOn(Schedulers.single())
                .concatMap(Observable::fromIterable);
    }

    @Override
    public Observable<Movie> getReadDataBaseObservable() {
        return readDBObservable;
    }

    @Override
    public void dispose() {
        if (movieDeleteDisposable != null) movieDeleteDisposable.dispose();
        if (movieAddDisposable != null) movieAddDisposable.dispose();
    }

    @Override
    public void deleteMovie(Movie movie) {
        movieDeleteDisposable = Completable.fromRunnable(() -> movieDAO.delete(movie))
                .subscribeOn(Schedulers.single())
                .subscribe(() -> Log.v(TAG, String.format("Movie(id = \'%d\') is deleted", movie.getId())));
    }

    @Override
    public void addMovie(Movie movie) {
        movieAddDisposable = Completable.fromRunnable(() -> movieDAO.insert(movie))
                .subscribeOn(Schedulers.single())
                .subscribe(() -> Log.v(TAG, String.format("Movie(id = \'%d\') is added", movie.getId())));
    }
}
