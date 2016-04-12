package com.appunite.example.debugutilsexample.service;

import com.appunite.example.debugutilsexample.model.Repos;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface GitHubService {

    @GET("/orgs/{name}/repos")
    Observable<List<Repos>> getRespos(
            @Path("name") String name
    );

}
