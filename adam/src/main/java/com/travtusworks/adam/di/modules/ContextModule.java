package com.travtusworks.adam.di.modules;

import android.content.Context;
import android.support.annotation.NonNull;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by teodora on 12.06.2018.
 */

@Singleton
@Module
public class ContextModule {

    private final Context context;

    public ContextModule(@NonNull Context context){
        this.context = context;
    }

    @Provides
    @Singleton
    Context provideContext(){
        return this.context;
    }

}
