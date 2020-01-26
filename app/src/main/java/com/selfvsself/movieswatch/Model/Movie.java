package com.selfvsself.movieswatch.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Comparator;

@Entity
public class Movie implements Comparator<Movie> {

    @PrimaryKey()
    private int id;
    private String title;
    private String genre;
    private String description;
    private int rating;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRating() {
        return rating;
    }

    public String getFormattedRating() {
        return formatRating(getRating());
    }

    public static String formatRating(int rating) {
        float formattedRating = 2.5f + (float) rating / 2;
        return String.valueOf(formattedRating);
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static int getUniqueID(Movie movie) {
        return movie.getTitle().hashCode();
    }

    public int compareTo(Movie movie) {
        return compare(this, movie);
    }

    @Override
    public int compare(Movie movie1, Movie movie2) {
        int result = movie2.getRating() - movie1.getRating();
        if (result == 0) {
            result = movie1.getTitle().compareToIgnoreCase(movie2.getTitle());
        }
        return result;
    }
}
