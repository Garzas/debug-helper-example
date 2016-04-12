package com.appunite.example.debugutilsexample.presenter;


import com.appunite.example.debugutilsexample.dao.GitHubDao;
import com.appunite.example.debugutilsexample.model.Repos;

import java.util.List;

import javax.annotation.Nonnull;
import javax.inject.Inject;

import rx.Observable;
import rx.functions.Func1;

public class MainPresenter {

    @Nonnull
    private final Observable<String> stringObservable;
    private final Observable<List<Repos>> repositoriesList;

    @Inject
    public MainPresenter(final GitHubDao gitHubDao) {
        stringObservable = Observable.just("text from presenter");


        repositoriesList = gitHubDao.getReposObservable()
        .map(new Func1<List<Repos>, List<Repos>>() {
            @Override
            public List<Repos> call(List<Repos> reposes) {
                return reposes;
            }
        });
    }

    @Nonnull
    public Observable<String> getStringObservable() {
        return stringObservable;
    }

    @Nonnull
    public Observable<List<Repos>> getRepositoriesList() {
        return repositoriesList;
    }
}
