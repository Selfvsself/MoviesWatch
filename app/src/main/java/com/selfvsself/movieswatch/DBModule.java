package com.selfvsself.movieswatch;

import android.content.Context;

import androidx.annotation.NonNull;

import com.selfvsself.movieswatch.Model.Repository.DBHelper.DBHelper;
import com.selfvsself.movieswatch.Model.Repository.DBHelper.DBRepository;
import com.selfvsself.movieswatch.Model.Repository.Repository;
import com.selfvsself.movieswatch.Presenter.AddMoviePresenter;
import com.selfvsself.movieswatch.Presenter.IAddMoviePresenter;
import com.selfvsself.movieswatch.Presenter.IMainPresenter;
import com.selfvsself.movieswatch.Presenter.MainPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = ContextModule.class)
public class DBModule {

    @Provides
    public DBHelper getDBHelper(Context context) {
        return new DBHelper(context);
    }

    @Provides
    public DBRepository getDBRepository(DBHelper dbHelper) {
        return new DBRepository(dbHelper);
    }
}
