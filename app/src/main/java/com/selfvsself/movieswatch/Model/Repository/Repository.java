package com.selfvsself.movieswatch.Model.Repository;

import android.content.Context;

import androidx.room.Room;

import com.selfvsself.movieswatch.Model.Movie;

import java.util.concurrent.atomic.AtomicBoolean;

import dagger.Module;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.ReplaySubject;

@Module
public class Repository {

    private ReplaySubject<Movie> movieListSubject;
    private Disposable addDataBaseDisposable;
    private MovieDAO movieDAO;

    public Repository(Context context) {
        DataBaseMovieEntity dataBase = Room.databaseBuilder(context, DataBaseMovieEntity.class, "rmovie_database").build();
        movieDAO = dataBase.movieDAO();
        movieListSubject = ReplaySubject.create();
        addDataBaseDisposable = Observable.fromCallable(movieDAO::getAll)
                .subscribeOn(Schedulers.single())
                .concatMap(Observable::fromIterable)
                .subscribe(str -> {
                    movieListSubject.onNext(str);
                });
    }

    public void dispose() {
        addDataBaseDisposable.dispose();
    }

    public ReplaySubject<Movie> getMovieListSubject() {
        return movieListSubject;
    }

    public void addMovies(Movie movie) {
        Completable c = Completable.fromRunnable(() -> {
            movieDAO.insert(movie);
        });
        addDataBaseDisposable = c.subscribeOn(Schedulers.single()).subscribeWith(new DisposableCompletableObserver() {
            @Override
            public void onComplete() {
                movieListSubject.onNext(movie);
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }
        });
    }

    public void editMovies(Movie movie) {
        Completable c = Completable.fromRunnable(() -> {
//            dbRepository.update(movie);
        });
        addDataBaseDisposable = c.subscribeWith(new DisposableCompletableObserver() {
            @Override
            public void onComplete() {
                movieListSubject.onNext(movie);
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }
        });
    }

    public void deleteMovie(Movie movie) {
        movieDAO.delete(movie);
    }

    public Movie getMovie(int id) {
        final Movie movie = new Movie();
        movieListSubject
                .filter(m -> m.getId() == id)
                .subscribe(new DisposableObserver<Movie>() {
                    @Override
                    public void onNext(Movie m) {
                        movie.setId(m.getId());
                        movie.setTitle(m.getTitle());
                        movie.setDescription(m.getDescription());
                        movie.setGenre(m.getGenre());
                        movie.setRating(m.getRating());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return movie;
    }

    public String[] getAllGenres() {
        String[] genres = {"Action", "Anime", "Adventure", "Animation", "Biography",
                "Comedy", "Documentary", "Drama", "Family", "Fantasy", "History", "Horror",
                "Music", "Musical", "Mystery", "Romance", "Sci-Fi", "Short Film",
                "Sport", "Superhero", "Thriller", "War", "Western", "Аниме", "Биография",
                "Биография", "Боевик", "Вестерн", "Военный", "Детектив",
                "Детский", "Для взрослых", "Документальный", "Драма", "Игра",
                "История", "Комедия", "Концерт", "Короткометражка", "Криминал", "Мелодрама",
                "Музыка", "Мультфильм", "Мюзикл", "Приключения", "Семейный", "Сериал", "Спорт",
                "Триллер", "Ужасы", "Фантастика", "Фильм нуар", "Фэнтези"};
        return genres;
    }

    public boolean checkThatDoesNotExist(String title, int id) {
        AtomicBoolean isNotExist = new AtomicBoolean(true);
        movieListSubject
                .filter(m -> m.getTitle().equalsIgnoreCase(title))
                .filter(m -> m.getId() != id)
                .count()
                .subscribe(s -> isNotExist.set(false), e-> isNotExist.set(true));
        return isNotExist.get();
    }
}
