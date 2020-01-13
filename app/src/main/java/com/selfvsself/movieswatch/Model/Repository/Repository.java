package com.selfvsself.movieswatch.Model.Repository;

import com.selfvsself.movieswatch.Model.Movie;
import com.selfvsself.movieswatch.Model.Repository.DBHelper.DBRepository;
import com.selfvsself.movieswatch.Presenter.IRepMainPresenter;

import java.util.List;

import dagger.Module;

@Module
public class Repository {

    private DBRepository dbRepository;
    private IRepMainPresenter mainPresenter;

    public Repository(DBRepository dbRepository) {
        this.dbRepository = dbRepository;
    }

    public void addMovies(Movie movie) {
        dbRepository.addMovie(movie);
        mainPresenter.notifyAboutAddingMovie(movie);
    }

    public void deleteMovie(Movie movie) {
        dbRepository.deleteMovie(movie);
    }

    public List<Movie> getAllMovies() {
        return dbRepository.readAll();
    }

    public String[] getAllGenres() {
        String[] genres = { "Action", "Anime", "Adventure", "Animation", "Biography",
                "Comedy", "Documentary", "Drama", "Family", "Fantasy", "History", "Horror",
                "Music", "Musical", "Mystery", "Romance", "Sci-Fi", "Short Film",
                "Sport", "Superhero", "Thriller", "War", "Western" , "Аниме" , "Биография",
                "Биография", "Боевик", "Вестерн", "Военный", "Детектив",
                "Детский", "Для взрослых", "Документальный", "Драма", "Игра",
                "История", "Комедия", "Концерт", "Короткометражка", "Криминал", "Мелодрама",
                "Музыка", "Мультфильм", "Мюзикл", "Приключения", "Семейный", "Сериал", "Спорт",
                "Триллер", "Ужасы", "Фантастика", "Фильм нуар", "Фэнтези"};
        return genres;
    }

    public void setMainPresenter(IRepMainPresenter mainPresenter) {
        this.mainPresenter = mainPresenter;
    }

    public boolean checkThatDoesNotExist(String title) {
        boolean isNotExist = true;
        List<Movie> tmpList = dbRepository.readAll();
        for (Movie movie : tmpList) {
            if (movie.getTitle().equalsIgnoreCase(title)) {
                isNotExist = false;
            }
        }
        return isNotExist;
    }
}
