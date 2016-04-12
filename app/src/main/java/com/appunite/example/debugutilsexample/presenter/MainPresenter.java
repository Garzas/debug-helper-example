package com.appunite.example.debugutilsexample.presenter;


import javax.annotation.Nonnull;
import javax.inject.Inject;

import rx.Observable;

public class MainPresenter {

    @Nonnull
    private final Observable<String> stringObservable;

    @Inject
    public MainPresenter() {
        stringObservable = Observable.just("text from presenter");
    }

    @Nonnull
    public Observable<String> getStringObservable() {
        return stringObservable;
    }
}
