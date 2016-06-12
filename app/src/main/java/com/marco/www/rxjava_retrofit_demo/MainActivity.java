package com.marco.www.rxjava_retrofit_demo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.marco.www.rxjava_retrofit_demo.Network.NetWork;
import com.marco.www.rxjava_retrofit_demo.adapter.MyAdapter;
import com.marco.www.rxjava_retrofit_demo.domain.Image;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends BaseActivity
{
    @Bind(R.id.recyclerview)
    RecyclerView mRecyclerview;
    @Bind(R.id.swipe_layout)
    SwipeRefreshLayout swipeLayout;

    MyAdapter myAdapter = new MyAdapter();

    Observer<List<Image>> observer = new Observer<List<Image>>()
    {
        @Override
        public void onCompleted()
        {
        }

        @Override
        public void onError(Throwable e)
        {
            swipeLayout.setRefreshing(false);
            Toast.makeText(MainActivity.this, "数据加载失败", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onNext(List<Image> images)
        {
            swipeLayout.setRefreshing(false);
            myAdapter.setImages(images);
        }
    };

    private void search(String key)
    {
        subscription = NetWork.getMyImageApi()
                .search(key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        unsubscribe();
        myAdapter.setImages(null);
        swipeLayout.setRefreshing(true);
        search("装逼");
        initviews();
    }

    private void initviews()
    {
        mRecyclerview.setLayoutManager(new GridLayoutManager(this, 2));
        mRecyclerview.setAdapter(myAdapter);
        swipeLayout.setColorSchemeColors(Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW);
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        unsubscribe();
    }
}
