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

import java.util.List;

public class MainActivity extends AppCompatActivity {

    TestApi api;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        api = RetrofitClient.instance().creatApi(TestApi.class);
        //


    }

    DownloadTool downloadTool;

    private void downloadVideo() {
        downloadTool = new DownloadTool();
        downloadTool.downloadFile("http://gdown.baidu.com/data/wisegame/97a1b097cad18e3c/QQ_1296.apk", new DownloadListener() {
            @Override
            public void onStart() {
                Log.e("下载：", "onStart: ");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        mCircleProgressLayout.setVisibility(View.VISIBLE);
                    }
                });
            }

            @Override
            public void onProgress(final int currentLength) {
                Log.e("下载：", "onLoading: " + currentLength);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        mCircleProgress.setProgress(currentLength);
                    }
                });
            }

            @Override
            public void onFinish(String localPath) {

                Log.e("下载：", "onFinish: " + localPath);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        mCircleProgressLayout.setVisibility(View.GONE);


                    }
                });
            }

            @Override
            public void onFailure(final String erroInfo) {
                Log.e("下载：", "onFailure: " + erroInfo);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        Toast.makeText(MainActivity.this, erroInfo, Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
            }
        });
    }


    public void clickIt(View view) {
        if (KbPermissionUtils.needRequestPermission()) { //判断是否需要动态申请权限
            KbPermission.with(this)
                    .requestCode(100)
                    .permission(Manifest.permission.WRITE_EXTERNAL_STORAGE) //需要申请的权限(支持不定长参数)
                    .callBack(new KbPermissionListener() {
                        @Override
                        public void onPermit(int requestCode, String... permission) { //允许权限的回调
                            downloadVideo(); //处理具体下载过程
                        }

                        @Override
                        public void onCancel(int requestCode, String... permission) { //拒绝权限的回调
                            KbPermissionUtils.goSetting(MainActivity.this); //跳转至当前app的权限设置界面
                        }
                    })
                    .send();
        } else {
            downloadVideo(); //处理具体下载过程
        }
    }

}
