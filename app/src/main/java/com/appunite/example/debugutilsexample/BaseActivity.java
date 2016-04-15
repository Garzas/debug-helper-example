package com.appunite.example.debugutilsexample;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.appunite.debughelper.DebugHelper;
import com.appunite.example.debugutilsexample.dagger.BaseActivityComponent;
import com.appunite.example.debugutilsexample.dagger.BaseActivityComponentProvider;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import javax.annotation.Nonnull;


public abstract class BaseActivity extends RxAppCompatActivity implements BaseActivityComponentProvider {

    private BaseActivityComponent activityComponent;

    private DebugHelper debugHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        activityComponent = createActivityComponent(savedInstanceState);
        super.onCreate(savedInstanceState);
        debugHelper = new DebugHelper(this);

    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(debugHelper.setContentView(layoutResID));
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(debugHelper.setContentView(view));
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(debugHelper.setContentView(view), params);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        debugHelper.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        debugHelper.onResume();
    }

    @Nonnull
    public BaseActivityComponent getActivityComponent() {
        return activityComponent;
    }
}
