package com.runtop.android.client;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.runtop.android.baselibrary.network.RetrofitClient;
import com.runtop.android.baselibrary.retrofit.SchedulerTransformer;
import com.runtop.android.functionlibrary.Tools.download.DownloadTool;
import com.runtop.android.functionlibrary.Tools.download.listener.DownloadListener;
import com.runtop.android.functionlibrary.Tools.permission.KbPermission;
import com.runtop.android.functionlibrary.Tools.permission.KbPermissionListener;
import com.runtop.android.functionlibrary.Tools.permission.KbPermissionUtils;
import com.runtop.android.functionlibrary.retrofit.ObserverHandler;
import com.runtop.android.functionlibrary.retrofit.ObserverListener;
import com.runtop.android.functionlibrary.view.dialog.ApkDownDialog;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    TestApi api;

    ApkDownDialog apkDownDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        api = RetrofitClient.instance().creatApi(TestApi.class);
        //
        apkDownDialog = new ApkDownDialog(this);

    }

    public void clickIt(View v) {
        apkDownDialog.show();
    }

}
