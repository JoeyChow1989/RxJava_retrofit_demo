package com.marco.www.rxjava_retrofit_demo.Network;

import com.marco.www.rxjava_retrofit_demo.domain.Image;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by pc on 2016/6/12.
 */
public interface MyImageApi
{
    @GET("search")
    Observable<List<Image>> search(@Query("q") String query);
}
