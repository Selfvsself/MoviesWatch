package com.selfvsself.movieswatch.Presenter;

import com.selfvsself.movieswatch.Model.Movie;
import com.selfvsself.movieswatch.Model.RecyclerAdapter;
import com.selfvsself.movieswatch.Model.Repository.Repository;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

public class MainPresenter implements IMainPresenter, IRepMainPresenter{

    private Repository repository;
    private RecyclerAdapter recyclerAdapter;
    private List<Movie> movieList;

    @Inject
    public MainPresenter(Repository repository) {
        this.repository = repository;
        movieList = new ArrayList<>();
        getAllMovies();
        recyclerAdapter = new RecyclerAdapter(movieList);
        repository.setMainPresenter(this);
    }

    private void getAllMovies() {
        movieList.clear();
        movieList.addAll(repository.getAllMovies());
    }

    @Override
    public RecyclerAdapter getRecyclerAdapter() {
        return recyclerAdapter;
    }

    @Override
    public Movie deleteMovie(int index) {
        Movie deletedMovie = repository.deleteMovie(index);
        movieList.remove(index);
        recyclerAdapter.notifyItemRemoved(index);
        return deletedMovie;
    }

    @Override
    public void addMovie(Movie movie) {
        repository.addMovies(movie);
    }

    @Override
    public void refreshItem(int position) {
        recyclerAdapter.notifyItemChanged(position);
    }

    @Override
    public void searchItem(String searchFilter) {
        searchFilter = searchFilter.toLowerCase();
        List<Movie> removeMovie = new ArrayList<>();
        getAllMovies();
        for (Movie movie : movieList) {
            if (!movie.isFiltered(searchFilter)) {
                removeMovie.add(movie);
            }
        }
        movieList.removeAll(removeMovie);
        recyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public void sortByName() {
        Collections.sort(movieList, new Comparator<Movie>() {
            @Override
            public int compare(Movie o1, Movie o2) {
                return o1.getTitle().toLowerCase().compareTo(o2.getTitle().toLowerCase());
            }
        });
        recyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public void sortByNameDown() {
        Collections.sort(movieList, new Comparator<Movie>() {
            @Override
            public int compare(Movie o1, Movie o2) {
                return o2.getTitle().toLowerCase().compareTo(o1.getTitle().toLowerCase());
            }
        });
        recyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public void sortByGenre() {
        Collections.sort(movieList, new Comparator<Movie>() {
            @Override
            public int compare(Movie o1, Movie o2) {
                return o1.getGenre().toLowerCase().compareTo(o2.getGenre().toLowerCase());
            }
        });
        recyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public void sortGenreDown() {
        Collections.sort(movieList, new Comparator<Movie>() {
            @Override
            public int compare(Movie o1, Movie o2) {
                return o2.getGenre().toLowerCase().compareTo(o1.getGenre().toLowerCase());
            }
        });
        recyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public void sortByRating() {
        Collections.sort(movieList, new Comparator<Movie>() {
            @Override
            public int compare(Movie o1, Movie o2) {
                return o1.getRating().compareTo(o2.getRating());
            }
        });
        recyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public void sortByRatingDown() {
        Collections.sort(movieList, new Comparator<Movie>() {
            @Override
            public int compare(Movie o1, Movie o2) {
                return o2.getRating().compareTo(o1.getRating());
            }
        });
        recyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public void notifyAboutAddingMovie(Movie movie) {
        movieList.add(movie);
        recyclerAdapter.notifyItemInserted(movieList.size() - 1);
    }

    @Override
    public void notifyAboutEditMovie(Movie movie) {
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
        recyclerAdapter.notifyItemChanged(index);
    }
}
