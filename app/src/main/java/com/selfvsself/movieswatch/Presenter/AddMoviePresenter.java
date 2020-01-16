package com.selfvsself.movieswatch.Presenter;

import com.selfvsself.movieswatch.Model.Movie;
import com.selfvsself.movieswatch.Model.Repository.Repository;
import com.selfvsself.movieswatch.View.AddMovieActivityView;

public class AddMoviePresenter implements IAddMoviePresenter{

    private static final String SEEK_VALUE_0 = "I'll watch a movie when it’s boring";
    private static final String SEEK_VALUE_1 = "Сan be seen this month";
    private static final String SEEK_VALUE_2 = "I can watch a look on the phone on the road";
    private static final String SEEK_VALUE_3 = "I'll look at the weekend";
    private static final String SEEK_VALUE_4 = "Urgent need to see";
    private static final String SEEK_VALUE_5 = "What I have been waiting for my whole life";

    private Repository repository;
    private AddMovieActivityView view;

    public AddMoviePresenter(Repository repository) {
        this.repository = repository;
    }

    @Override
    public String[] getAllGenres() {
        return repository.getAllGenres();
    }

    @Override
    public void setView(AddMovieActivityView view) {
        this.view = view;
    }

    @Override
    public boolean saveMovie() {
        boolean isSaved = false;
        boolean isNotExist = repository.checkThatDoesNotExist(view.getMovieTitle(), -1);
        if (view.getMovieTitle().length() > 0 && isNotExist) {
            Movie movie = new Movie();
            movie.setTitle(view.getMovieTitle());
            movie.setGenre(view.getMovieGenre());
            movie.setDescription(view.getMovieDescription());
            movie.setRating(view.getMovieRating());
            repository.addMovies(movie);
            isSaved = true;
        } else {
            view.setErrorMsgInTitle("Incorrect movie title");
        }
        return isSaved;
    }

    @Override
    public Movie getMovie(int id) {
        return repository.getMovie(id);
    }

    @Override
    public boolean editMovie(int id) {
        boolean isSaved = false;
        boolean isNotExist = repository.checkThatDoesNotExist(view.getMovieTitle(), id);
        if (view.getMovieTitle().length() > 0 && isNotExist) {
            Movie movie = new Movie();
            movie.setId(id);
            movie.setTitle(view.getMovieTitle());
            movie.setGenre(view.getMovieGenre());
            movie.setDescription(view.getMovieDescription());
            movie.setRating(view.getMovieRating());
            repository.editMovies(movie);
            isSaved = true;
        } else {
            view.setErrorMsgInTitle("Incorrect movie title");
        }
        return isSaved;
    }

    private String getAssessment(int progress) {
        String result;
        if (progress < 1) {
            result = SEEK_VALUE_0;
        } else if (progress < 2) {
            result = SEEK_VALUE_1;
        } else if (progress < 3) {
            result = SEEK_VALUE_2;
        } else if (progress < 4) {
            result = SEEK_VALUE_3;
        } else if (progress < 5) {
            result = SEEK_VALUE_4;
        } else {
            result = SEEK_VALUE_5;
        }
        return result;
    }

    @Override
    public void setProgressSeekBar(int progress) {
        view.setAssessmentText(getAssessment(progress));
    }
}
