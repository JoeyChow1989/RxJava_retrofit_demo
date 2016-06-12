package com.marco.www.rxjava_retrofit_demo;

import android.support.v7.app.AppCompatActivity;

import rx.Subscription;

/**
 * Created by pc on 2016/6/12.
 */
public class BaseActivity extends AppCompatActivity
{

    protected Subscription subscription;

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        unsubscribe();
    }

    protected void unsubscribe()
    {
        if (subscription != null && !subscription.isUnsubscribed())
        {
            subscription.unsubscribe();
        }
    }
}