package com.runtop.android.core.provider;

import com.runtop.android.baselibrary.network.RetrofitClient;
import com.runtop.android.core.api.TestApi;

public class RequestManager {
    private static TestApi testApi;

    public static TestApi getTestApi(){
        if(testApi==null){
            testApi= RetrofitClient.instance().creatApi(TestApi.class);
        }
        return testApi;
    }
}
