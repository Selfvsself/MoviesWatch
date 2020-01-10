package com.selfvsself.movieswatch.Model;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

@Module
public class Repository {

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
}
