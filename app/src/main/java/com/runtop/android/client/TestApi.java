package com.runtop.android.client;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TestApi {
    @GET("/sup/news/list")
    Observable<String> getData(@Query("type") String type, @Query("page") String page);
}
