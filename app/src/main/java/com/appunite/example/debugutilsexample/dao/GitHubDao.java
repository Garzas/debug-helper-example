package com.appunite.example.debugutilsexample.dao;

import com.appunite.example.debugutilsexample.model.Repos;
import com.appunite.example.debugutilsexample.service.GitHubService;
import com.appunite.rx.ObservableExtensions;
import com.appunite.rx.ResponseOrError;
import com.appunite.rx.operators.MoreOperators;

import java.util.List;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

@Singleton
public class GitHubDao {

    @Nonnull
    private final Observable<List<Repos>> reposObservable;

    @Inject
    public GitHubDao(final GitHubService service) {

        reposObservable = service.getRespos()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    @Nonnull
    public Observable<List<Repos>> getReposObservable() {
        return reposObservable;
    }

}
