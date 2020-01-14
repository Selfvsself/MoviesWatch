package com.selfvsself.movieswatch.DI;

import android.content.Context;

import com.selfvsself.movieswatch.Model.Repository.DBHelper.DBHelper;
import com.selfvsself.movieswatch.Model.Repository.DBHelper.DBRepository;

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
