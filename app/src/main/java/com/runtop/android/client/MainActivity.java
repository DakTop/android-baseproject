package com.runtop.android.client;

import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.runtop.android.baselibrary.network.RetrofitClient;
import com.runtop.android.baselibrary.retrofit.SchedulerTransformer;
import com.runtop.android.functionlibrary.retrofit.ObserverHandler;
import com.runtop.android.functionlibrary.retrofit.ObserverListener;

public class MainActivity extends AppCompatActivity {

    TestApi api;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        api = RetrofitClient.instance().creatApi(TestApi.class);
        //
    }


    public void clickIt(View view) {

        api.getData("0", "1")
                .compose(new SchedulerTransformer())
                .subscribe(new ObserverHandler<TestData>(this, "加载中...", null) {
                    @Override
                    public void onSuccess(TestData testData) {
                        super.onSuccess(testData);
                    }
                });
    }

}
