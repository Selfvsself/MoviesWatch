package com.selfvsself.movieswatch.Model.Repository;

import com.selfvsself.movieswatch.Model.Movie;
import com.selfvsself.movieswatch.Model.Repository.DBHelper.DBRepository;

import java.util.concurrent.atomic.AtomicBoolean;

import dagger.Module;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.ReplaySubject;

@Module
public class Repository {

    private DBRepository dbRepository;
    private ReplaySubject<Movie> movieListSubject;
    private Disposable addDataBaseDisposable;

    public Repository(DBRepository dbRepository) {
        this.dbRepository = dbRepository;
        movieListSubject = ReplaySubject.create();
        addDataBaseDisposable = Observable.fromCallable(dbRepository::readAll)
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
            dbRepository.addMovie(movie);
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

    public void editMovies(Movie movie) {
        Completable c = Completable.fromRunnable(() -> {
            dbRepository.update(movie);
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
        dbRepository.deleteMovie(movie);
    }

    public Movie getMovie(int id) {
        final Movie movie = new Movie();
        movieListSubject
                .filter(m -> m.getId().equalsIgnoreCase(String.valueOf(id)))
                .subscribe(new DisposableObserver<Movie>() {
                    @Override
                    public void onNext(Movie m) {
                        movie.setId(Integer.parseInt(m.getId()));
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
                .filter(m -> !m.getId().equalsIgnoreCase(String.valueOf(id)))
                .count()
                .subscribe(s -> isNotExist.set(false), e-> isNotExist.set(true));
        return isNotExist.get();
    }
}
