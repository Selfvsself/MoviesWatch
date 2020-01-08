package com.selfvsself.movieswatch.Model;

import androidx.cardview.widget.CardView;

public class Movie {

    private String title;
    private String genre;
    private String description;

    public Movie() {
    }

    public Movie(String title, String genre, String description) {
        this.title = title;
        this.genre = genre;
        this.description = description;
    }

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

    public boolean isFiltered(String string) {
        return (title.toLowerCase().contains(string) ||
                genre.toLowerCase().contains(string) ||
                description.toLowerCase().contains(string));
    }
}
