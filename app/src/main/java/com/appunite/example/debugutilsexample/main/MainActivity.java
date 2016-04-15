package com.appunite.example.debugutilsexample.main;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.appunite.example.debugutilsexample.App;
import com.appunite.example.debugutilsexample.BaseActivity;
import com.appunite.example.debugutilsexample.R;
import com.appunite.example.debugutilsexample.dagger.ActivityModule;
import com.appunite.example.debugutilsexample.dagger.BaseActivityComponent;
import com.appunite.example.debugutilsexample.presenter.MainPresenter;
import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class MainActivity extends BaseActivity {

    @Inject
    MainPresenter presenter;

    @Inject
    MainAdapter adapter;

    @InjectView(R.id.simple_text)
    TextView simpleText;
    @InjectView(R.id.main_recyclerview)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainActivityComponent component = (MainActivityComponent) getActivityComponent();
        component.inject(this);
        ButterKnife.inject(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        presenter.getTitleObservable()
                .compose(this.<String>bindToLifecycle())
                .subscribe(RxTextView.text(simpleText));

        presenter.getItemListObservable()
                .compose(this.<List<MainPresenter.BaseItem>>bindToLifecycle())
                .subscribe(adapter);
    }

    @Nonnull
    @Override
    public BaseActivityComponent createActivityComponent(@Nullable Bundle savedInstanceState) {
        return DaggerMainActivityComponent
                .builder()
                .activityModule(new ActivityModule(this))
                .appComponent(App.getAppComponent(getApplication()))
                .build();
    }
}
