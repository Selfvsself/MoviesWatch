package com.selfvsself.movieswatch.Model.Repository.Resources;

import android.content.Context;
import android.content.res.Resources;

import com.selfvsself.movieswatch.R;

import java.util.ArrayList;
import java.util.Arrays;

import dagger.Module;

@Module
public class ResourcesHelper implements IResourcesHelper {

    private Resources appResources;

    public ResourcesHelper(Context context) {
        appResources = context.getResources();
    }

    @Override
    public ArrayList getStandardGenres() {
        String[] arrayGenres = appResources.getStringArray(R.array.standard_genre);
        return new ArrayList(Arrays.asList(arrayGenres));
    }
}
