package com.selfvsself.movieswatch.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Movie {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String genre;
    private String description;
    private String rating;

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

    public String getRating() {
        return rating;
    }

    public String getFormattedRating() {
        float formattedRating= 2.5f + Float.parseFloat(rating) / 2;
        return String.valueOf(formattedRating);
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isFiltered(String string) {
        return (title.toLowerCase().contains(string) ||
                genre.toLowerCase().contains(string) ||
                description.toLowerCase().contains(string));
    }

    public int compareByNameUp(Movie movie) {
        return title.compareTo(movie.getTitle());
    }

    public int compareByNameDown(Movie movie) {
        return movie.getTitle().compareTo(title);
    }
}
