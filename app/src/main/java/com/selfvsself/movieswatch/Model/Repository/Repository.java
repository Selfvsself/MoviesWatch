package com.selfvsself.movieswatch.Model.Repository;

import com.selfvsself.movieswatch.Model.Movie;
import com.selfvsself.movieswatch.Model.Repository.DBHelper.DBRepository;
import com.selfvsself.movieswatch.Presenter.IRepMainPresenter;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;

@Module
public class Repository {

    private DBRepository dbRepository;
    private IRepMainPresenter mainPresenter;
    private List<Movie> movieList = new ArrayList<>();
    public Repository(DBRepository dbRepository) {
        this.dbRepository = dbRepository;
    }

    public void addMovies(Movie movie) {
        dbRepository.addMovie(movie);
        movieList.add(movie);
        mainPresenter.notifyAboutAddingMovie(movie);
    }

    public void editMovies(Movie movie) {
        int index = 0;
        for (int i = 0 ; i < movieList.size(); i++) {
            if (movieList.get(i).getTitle().equals(movie.getTitle())) {
                index = i;
                break;
            }
        }
        movieList.get(index).setTitle(movie.getTitle());
        movieList.get(index).setGenre(movie.getGenre());
        movieList.get(index).setDescription(movie.getDescription());
        movieList.get(index).setRating(movie.getRating());
        dbRepository.update(movie);
        mainPresenter.notifyAboutEditMovie(movie);
    }

    public Movie deleteMovie(int index) {
        Movie deleteMovie = movieList.get(index);
        movieList.remove(index);
        dbRepository.deleteMovie(deleteMovie);
        return deleteMovie;
    }

    public List<Movie> getAllMovies() {
        if (movieList.size() == 0) movieList = dbRepository.readAll();
        return movieList;
    }

    public Movie getMovie(int id) {
        for (Movie movie : movieList) {
            if (movie.getId().equals(String.valueOf(id))) {
                return movie;
            }
        }
        return new Movie();
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

    public boolean checkThatDoesNotExist(String title, int id) {
        boolean isNotExist = true;
        List<Movie> tmpList = movieList;
        for (Movie movie : tmpList) {
            if (movie.getTitle().equalsIgnoreCase(title) && !movie.getId().equals(String.valueOf(id))) {
                isNotExist = false;
            }
        }
        return isNotExist;
    }
}
