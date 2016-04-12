package com.appunite.example.debugutilsexample.dagger;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;

import com.appunite.example.debugutilsexample.service.GitHubService;
import dagger.Component;

@ActivityScope
@Component(
        dependencies = AppComponent.class,
        modules = {
                ActivityModule.class,
        }
)
public interface BaseActivityComponent {

    @ForActivity
    Resources getResources();

    @ForActivity
    Context getActivityContext();

    LayoutInflater getLayoutInflater();

    GitHubService getGithubService();

}