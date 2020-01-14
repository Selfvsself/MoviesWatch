package com.selfvsself.movieswatch;

import android.content.Context;

import com.selfvsself.movieswatch.DI.AppModule;
import com.selfvsself.movieswatch.DI.ContextModule;
import com.selfvsself.movieswatch.DI.DBModule;
import com.selfvsself.movieswatch.View.AddMovieActivity;
import com.selfvsself.movieswatch.View.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, DBModule.class, ContextModule.class})
public interface AppComponent {
    Context context(Context context);
    void inject(MainActivity mainActivity);
    void inject(AddMovieActivity addMovieActivity);
}
