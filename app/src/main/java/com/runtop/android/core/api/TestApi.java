package com.runtop.android.core.api;

import com.runtop.android.client.bean.LoginResp;
import com.runtop.android.core.base.bean.BaseResponse;

import java.util.Map;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface TestApi {

    @POST(UrlPath.login)
    Observable<BaseResponse<LoginResp>> login(@Body Map<String, Object>request);

}
