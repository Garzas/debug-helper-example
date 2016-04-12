package com.appunite.example.debugutilsexample.dagger;

import android.content.Context;


import com.appunite.example.debugutilsexample.App;
import com.appunite.example.debugutilsexample.service.GitHubService;
import com.squareup.picasso.Picasso;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
        modules = {
                AppModule.class
        }
)
public interface AppComponent {

    void inject(App app);

    @ForApplication
    Context getContext();

    Picasso getPicasso();

    GitHubService getGitHubSerice();

}