package com.selfvsself.movieswatch.View;

public interface AddMovieActivityView {

    public void setAssessmentText(String message);

    public String getMovieTitle();

    public String getMovieGenre();

    public String getMovieDescription();

    public String getMovieRating();

    public void setErrorMsgInTitle(String errorMsg);
}
