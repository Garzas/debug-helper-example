package com.appunite.example.debugutilsexample.service;

import android.database.Observable;

import com.appunite.example.debugutilsexample.model.Repos;

import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubService {

    @GET("/orgs/{name}/repos")
    Observable<Repos> getRespos(
            @Path("name") String name
    );

}
