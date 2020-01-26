package com.selfvsself.movieswatch.Model.Repository;

import android.util.Log;

import com.selfvsself.movieswatch.Model.Movie;
import com.selfvsself.movieswatch.Model.Repository.DB.IDBRepository;
import com.selfvsself.movieswatch.Model.Repository.Resources.IResourcesHelper;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;

@Module
public class MainRepository implements IMainRepository {

    private static final String TAG = "MainRepository";
    private IDBRepository dbRepository;
    private IResourcesHelper resourcesHelper;
    private Disposable readDBDisposable;
    private List<Movie> movieList;
    private PublishSubject<Integer> addMoviesSubject;
    private PublishSubject<Integer> deleteMoviesSubject;

    public MainRepository(IDBRepository dbRepository, IResourcesHelper resourcesHelper) {
        this.resourcesHelper = resourcesHelper;
        this.dbRepository = dbRepository;
        movieList = new ArrayList<>();
        addMoviesSubject = PublishSubject.create();
        deleteMoviesSubject = PublishSubject.create();
        readDBDisposable = dbRepository.getReadDataBaseObservable().subscribe(
                movie -> movieList.add(movie),
                Throwable::printStackTrace,
                () -> Log.v(TAG, "Complete read DataBase"));
    }

    @Override
    public List<Movie> getMovieList() {
        return movieList;
    }

    @Override
    public void dispose() {
        if (readDBDisposable != null) readDBDisposable.dispose();
        dbRepository.dispose();
    }

    @Override
    public Movie deleteMovie(int index) {
        Movie deletedMovie = movieList.get(index);
        dbRepository.deleteMovie(deletedMovie);
        movieList.remove(index);
        deleteMoviesSubject.onNext(index);
        return deletedMovie;
    }

    @Override
    public boolean addMovie(Movie movie) {
        if (movie.getId() == 0) {
            movie.setId(Movie.getUniqueID(movie));
        }
        int addingIndex = getAddingIndex(movie);
        if (isUniqueMovieByName(movie)) {
            dbRepository.addMovie(movie);
            movieList.add(addingIndex, movie);
            addMoviesSubject.onNext(addingIndex);
            return true;
        }
        return false;
    }

    @Override
    public boolean editMovie(Movie movie) {
        boolean isResult = false;
        if (isUniqueMovieForEdit(movie)) {
            int indexDeleteMovie = getMovieIndexInList(movie);
            deleteMovie(indexDeleteMovie);
            movie.setId(Movie.getUniqueID(movie));
            isResult = addMovie(movie);
        }
        return isResult;
    }

    private int getMovieIndexInList(Movie movie) {
        for (int i = 0; i < movieList.size(); i++) {
            if (movieList.get(i).getId() == movie.getId()) return i;
        }
        return -1;
    }

    @Override
    public Movie getMovieByIndex(int index) {
        return movieList.get(index);
    }

    @Override
    public Movie getMovieByID(int id) {
        Movie resultMovie = new Movie();
        for (Movie movie : movieList) {
            if (movie.getId() == id) {
                resultMovie = movie;
                break;
            }
        }
        return resultMovie;
    }

    @Override
    public List<String> getAllGenre() {
        return resourcesHelper.getStandardGenres();
    }

    private int getAddingIndex(Movie movie) {
        int left = -1;
        int right = movieList.size();
        int middle;

        while ((right - left) > 1) {
            middle = (right + left) / 2;
            if (movieList.get(middle).compareTo(movie) < 0) {
                left = middle;
            } else {
                right = middle;
            }
        }
        return right;
    }

    private boolean isUniqueMovieByName(Movie movie) {
        boolean isResult = true;
        for (Movie m : movieList) {
            if (m.getTitle().equalsIgnoreCase(movie.getTitle())) isResult = false;
        }
        return isResult;
    }

    private boolean isUniqueMovieForEdit(Movie movie) {
        boolean isResult = true;
        for (Movie m : movieList) {
            if (m.getTitle().equalsIgnoreCase(movie.getTitle()) && m.getId() != movie.getId())
                isResult = false;
        }
        return isResult;
    }

    @Override
    public PublishSubject<Integer> getAddMovieSubject() {
        return addMoviesSubject;
    }

    @Override
    public PublishSubject<Integer> getDeleteMovieSubject() {
        return deleteMoviesSubject;
    }
}
