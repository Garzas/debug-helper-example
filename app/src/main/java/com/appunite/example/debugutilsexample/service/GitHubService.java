package com.appunite.example.debugutilsexample.service;

import com.appunite.example.debugutilsexample.model.Repos;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import rx.Observable;

public interface GitHubService {

    @Headers("Accept: application/vnd.github.v3+json")
    @GET("/orgs/{name}/repos")
    Observable<List<Repos>> getRespos(
            @Path("name") String name
    );

}
