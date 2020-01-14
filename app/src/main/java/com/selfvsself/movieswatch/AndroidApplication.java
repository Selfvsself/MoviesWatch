package com.selfvsself.movieswatch;

import android.app.Application;

import com.selfvsself.movieswatch.DI.AppModule;
import com.selfvsself.movieswatch.DI.ContextModule;

public class AndroidApplication extends Application {

    public AppComponent getAppComponent() {
        return appComponent;
    }

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder().contextModule(new ContextModule(this)).appModule(new AppModule()).build();
    }
}
