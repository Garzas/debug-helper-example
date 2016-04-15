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
    private final Observable<ResponseOrError<List<Repos>>> reposObservable;
    @Nonnull
    private final PublishSubject<Object> refreshSubject = PublishSubject.create();

    @Inject
    public GitHubDao(final GitHubService service) {

        reposObservable = service.getRespos()
                .compose(ResponseOrError.<List<Repos>>toResponseOrErrorObservable())
                .compose(MoreOperators.<List<Repos>>repeatOnError(Schedulers.io()))
//                                .compose(MoreOperators.<ResponseOrError<List<Repos>>>refresh(refreshSubject))
                .subscribeOn(Schedulers.io())
                .compose(MoreOperators.<ResponseOrError<List<Repos>>>cacheWithTimeout(AndroidSchedulers.mainThread()))
                .compose(ObservableExtensions.<ResponseOrError<List<Repos>>>behaviorRefCount())
                .observeOn(AndroidSchedulers.mainThread());
    }


    @Nonnull
    public Observable<ResponseOrError<List<Repos>>> getReposObservable() {
        return reposObservable;
    }

    @Nonnull
    public Observer<Object> refreshObserver() {
        return refreshSubject;
    }
}
