package com.appunite.example.debugutilsexample.main;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.appunite.example.debugutilsexample.App;
import com.appunite.example.debugutilsexample.BaseActivity;
import com.appunite.example.debugutilsexample.R;
import com.appunite.example.debugutilsexample.dagger.ActivityModule;
import com.appunite.example.debugutilsexample.dagger.BaseActivityComponent;
import com.appunite.example.debugutilsexample.model.Repos;
import com.appunite.example.debugutilsexample.presenter.MainPresenter;
import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import rx.functions.Action1;


public class MainActivity extends BaseActivity {

    @Inject
    MainPresenter presenter;

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
        recyclerView.setAdapter(new MainAdapter());


        presenter.getStringObservable()
                .compose(this.<String>bindToLifecycle())
                .subscribe(RxTextView.text(simpleText));

        presenter.getRepositoriesList()
                .compose(this.<List<Repos>>bindToLifecycle())
                .subscribe(new Action1<List<Repos>>() {
                    @Override
                    public void call(List<Repos> reposList) {
                        Toast.makeText(getApplicationContext(), "Data was downloaded successful", Toast.LENGTH_SHORT).show();
                    }
                });
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
