package com.appunite.example.debugutilsexample.dao;

import com.appunite.example.debugutilsexample.model.Repos;
import com.appunite.example.debugutilsexample.service.GitHubService;

import java.util.List;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

@Singleton
public class GitHubDao {

    @Nonnull
    private final Observable<List<Repos>> reposObservable;
    private final PublishSubject<Object> refreshSubject = PublishSubject.create();

    @Inject
    public GitHubDao(final GitHubService service) {

        reposObservable = refreshSubject.startWith(new Object())
                .flatMap(new Func1<Object, Observable<List<Repos>>>() {
                    @Override
                    public Observable<List<Repos>> call(Object o) {
                        return service.getRespos("appunite")
                                .subscribeOn(Schedulers.io());
                    }
                })
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Nonnull
    public Observable<List<Repos>> getReposObservable() {
        return reposObservable;
    }
}
