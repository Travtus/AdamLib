package com.travtusworks.adam.di.modules;

import com.travtusworks.adam.di.AdamScope;
import com.travtusworks.adam.presenter.AdamContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by teodora on 12.06.2018.
 */

@Module
public class AdamModule {

    private AdamContract.View mView;

    public AdamModule(AdamContract.View mView){
        this.mView = mView;
    }

    public void setView(AdamContract.View mView){
        this.mView = mView;
    }

    @Provides
    @AdamScope
    AdamContract.View providesAdamScreenContractView(){
        return mView;
    }

}
