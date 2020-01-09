package com.runtop.android.client;


import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TestApi {
    @GET("/sup/news/list")
    Observable<TestData<List<String>>> getData(@Query("type") String type, @Query("page") String page);
}
