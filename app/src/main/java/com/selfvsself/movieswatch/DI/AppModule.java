package com.selfvsself.movieswatch.DI;

import android.content.Context;

import com.selfvsself.movieswatch.Model.Repository.Repository;
import com.selfvsself.movieswatch.Presenter.AddMoviePresenter;
import com.selfvsself.movieswatch.Presenter.IAddMoviePresenter;
import com.selfvsself.movieswatch.Presenter.IMainPresenter;
import com.selfvsself.movieswatch.Presenter.MainPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = ContextModule.class)
public class AppModule {

    @Singleton
    @Provides
    public Repository getRepository(Context context) {
        return new Repository(context);
    }

    @Singleton
    @Provides
    public IMainPresenter getMainPresenter(Repository repository) {
        return new MainPresenter(repository);
    }

    @Singleton
    @Provides
    public IAddMoviePresenter getAddMoviePresenter(Repository repository) {
        return new AddMoviePresenter(repository);
    }
}
