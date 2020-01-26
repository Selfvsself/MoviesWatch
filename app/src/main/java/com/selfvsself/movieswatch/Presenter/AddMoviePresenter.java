package com.selfvsself.movieswatch.Presenter;

import com.selfvsself.movieswatch.Model.Movie;
import com.selfvsself.movieswatch.Model.Repository.IMainRepository;

import java.util.List;

public class AddMoviePresenter implements IAddMoviePresenter {

    private static final String SEEK_VALUE_0 = "I'll watch a movie when it’s boring";
    private static final String SEEK_VALUE_1 = "Сan be seen this month";
    private static final String SEEK_VALUE_2 = "I can watch a look on the phone on the road";
    private static final String SEEK_VALUE_3 = "I'll look at the weekend";
    private static final String SEEK_VALUE_4 = "Urgent need to see";
    private static final String SEEK_VALUE_5 = "What I have been waiting for my whole life";

    private IMainRepository repository;

    public AddMoviePresenter(IMainRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<String> getAllGenres() {
        return repository.getAllGenre();
    }

    @Override
    public boolean addMovie(Movie movie) {
        if (movie.getTitle().length() == 0) return false;
        return repository.addMovie(movie);
    }

    @Override
    public boolean editMovie(Movie movie) {
        if (movie.getTitle().length() == 0) return false;
        return repository.editMovie(movie);
    }

    @Override
    public String getHintByRating(int rating) {
        String result = "Error";
        switch (rating) {
            case 0:
                result = SEEK_VALUE_0;
                break;
            case 1:
                result = SEEK_VALUE_1;
                break;
            case 2:
                result = SEEK_VALUE_2;
                break;
            case 3:
                result = SEEK_VALUE_3;
                break;
            case 4:
                result = SEEK_VALUE_4;
                break;
            case 5:
                result = SEEK_VALUE_5;
                break;
        }
        return result;
    }

    @Override
    public Movie getMovieByID(int id) {
        return repository.getMovieByID(id);
    }
}
