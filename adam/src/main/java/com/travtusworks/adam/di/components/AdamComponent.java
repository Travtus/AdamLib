package com.travtusworks.adam.di.components;

import android.content.Context;

import com.travtusworks.adam.AdamActivity;
import com.travtusworks.adam.di.AdamScope;
import com.travtusworks.adam.di.modules.AdamModule;
import com.travtusworks.adam.di.modules.ContextModule;
import com.travtusworks.adam.di.modules.NetModule;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by teodora on 12.06.2018.
 */

@Singleton
@Component(modules = {ContextModule.class, NetModule.class, AdamModule.class})
@AdamScope
public interface AdamComponent {

    Context mContext();

    Retrofit retrofit();

    void inject(AdamActivity activity);

}
