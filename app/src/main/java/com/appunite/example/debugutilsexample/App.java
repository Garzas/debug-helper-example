package com.appunite.example.debugutilsexample;

import android.app.Application;

import com.appunite.example.debugutilsexample.dagger.AppComponent;
import com.appunite.example.debugutilsexample.dagger.AppModule;
import com.appunite.example.debugutilsexample.dagger.DaggerAppComponent;


public class App extends Application {

    private AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        setupGraph();
    }

    private void setupGraph() {
        component = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
        component.inject(this);
    }

    public static AppComponent getAppComponent(Application app) {
        return ((App) app).component;
    }
}
