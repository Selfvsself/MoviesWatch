package com.selfvsself.movieswatch;

import com.selfvsself.movieswatch.Model.Repository.DBHelper.DBRepository;
import com.selfvsself.movieswatch.Model.Repository.Repository;
import com.selfvsself.movieswatch.Presenter.AddMoviePresenter;
import com.selfvsself.movieswatch.Presenter.IAddMoviePresenter;
import com.selfvsself.movieswatch.Presenter.IMainPresenter;
import com.selfvsself.movieswatch.Presenter.MainPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Singleton
    @Provides
    public Repository getRepository() {
        return new Repository();
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
