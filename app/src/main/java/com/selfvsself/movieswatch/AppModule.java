package com.selfvsself.movieswatch;

import com.selfvsself.movieswatch.Presenter.IMainPresenter;
import com.selfvsself.movieswatch.Presenter.MainPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Provides
    public IMainPresenter getMainPresenter() {
        return new MainPresenter();
    }
}
