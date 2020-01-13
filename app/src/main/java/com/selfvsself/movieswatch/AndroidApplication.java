package com.selfvsself.movieswatch;

import android.app.Application;

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
