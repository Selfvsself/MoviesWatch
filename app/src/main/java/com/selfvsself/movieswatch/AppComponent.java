package com.selfvsself.movieswatch;

import com.selfvsself.movieswatch.View.AddMovieActivity;
import com.selfvsself.movieswatch.View.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    void inject(MainActivity mainActivity);
    void inject(AddMovieActivity addMovieActivity);
}
