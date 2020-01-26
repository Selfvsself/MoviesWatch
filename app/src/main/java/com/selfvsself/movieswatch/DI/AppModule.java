package com.selfvsself.movieswatch.DI;

import android.content.Context;

import com.selfvsself.movieswatch.Model.Repository.DB.DBRepository;
import com.selfvsself.movieswatch.Model.Repository.MainRepository;
import com.selfvsself.movieswatch.Model.Repository.Resources.ResourcesHelper;
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
    public ResourcesHelper getResourcesHelper(Context context) {
        return new ResourcesHelper(context);
    }

    @Singleton
    @Provides
    public DBRepository getDBRepository(Context context) {
        return new DBRepository(context);
    }

    @Singleton
    @Provides
    public MainRepository getRepository(DBRepository dbRepository, ResourcesHelper resourcesHelper) {
        return new MainRepository(dbRepository, resourcesHelper);
    }

    @Singleton
    @Provides
    public IMainPresenter getMainPresenter(MainRepository repository) {
        return new MainPresenter(repository);
    }

    @Singleton
    @Provides
    public IAddMoviePresenter getAddMoviePresenter(MainRepository repository) {
        return new AddMoviePresenter(repository);
    }
}
