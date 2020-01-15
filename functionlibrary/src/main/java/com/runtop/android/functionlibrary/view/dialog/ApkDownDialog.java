package com.runtop.android.functionlibrary.view.dialog;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.runtop.android.functionlibrary.R;
import com.runtop.android.functionlibrary.Tools.download.DownloadTool;
import com.runtop.android.functionlibrary.Tools.download.listener.DownloadListener;
import com.runtop.android.functionlibrary.Tools.permission.KbPermission;
import com.runtop.android.functionlibrary.Tools.permission.KbPermissionListener;
import com.runtop.android.functionlibrary.Tools.permission.KbPermissionUtils;
import com.runtop.android.functionlibrary.view.base.BaseDialog;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

import androidx.annotation.NonNull;

public class ApkDownDialog extends BaseDialog implements View.OnClickListener {

    private TextView tvDownload;
    private ProgressBar tvDownloadProgress;
    private DownloadTool downloadTool;
    private SoftReference<Activity> activity;
    private ProgressHandler handler;

    static class ProgressHandler extends Handler {
        WeakReference<ApkDownDialog> dialog;

        public ProgressHandler(ApkDownDialog apkDownDialog) {
            dialog = new WeakReference<>(apkDownDialog);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    dialog.get().progress(msg.arg1);
                    break;
            }
        }
    }

    ;

    public ApkDownDialog(@NonNull Activity context) {
        super(context, R.style.dialog);
        activity = new SoftReference<>(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_apk_download);
        tvDownload = findViewById(R.id.tv_download);
        tvDownload.setOnClickListener(this);
        tvDownloadProgress = findViewById(R.id.tv_download_progress);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_download) {
            handler = new ProgressHandler(this);
            tvDownload.setVisibility(View.INVISIBLE);
            tvDownloadProgress.setVisibility(View.VISIBLE);
            if (KbPermissionUtils.needRequestPermission()) { //判断是否需要动态申请权限
                KbPermission.with(activity.get())
                        .requestCode(100)
                        .permission(Manifest.permission.WRITE_EXTERNAL_STORAGE) //需要申请的权限(支持不定长参数)
                        .callBack(new KbPermissionListener() {
                            @Override
                            public void onPermit(int requestCode, String... permission) { //允许权限的回调
                                downloadVideo(); //处理具体下载过程
                            }

                            @Override
                            public void onCancel(int requestCode, String... permission) { //拒绝权限的回调
                                KbPermissionUtils.goSetting(activity.get()); //跳转至当前app的权限设置界面
                            }
                        })
                        .send();
            } else {
                downloadVideo(); //处理具体下载过程
            }
        }
    }


    private void downloadVideo() {
        downloadTool = new DownloadTool();
        downloadTool.downloadFile("http://gdown.baidu.com/data/wisegame/97a1b097cad18e3c/QQ_1296.apk", new DownloadListener() {
            @Override
            public void onStart() {
                Log.e("下载：", "onStart: ");
                activity.get().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                    }
                });
            }

            @Override
            public void onProgress(final int currentLength) {
                Message message = Message.obtain();
                message.arg1 = currentLength;
                message.what = 1;
                handler.sendMessage(message);
            }

            @Override
            public void onFinish(String localPath) {
                Log.e("下载：", "onFinish: " + localPath);
                activity.get().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvDownloadProgress.setVisibility(View.INVISIBLE);
                        ApkDownDialog.this.cancel();
                    }
                });
            }

            @Override
            public void onFailure(final String erroInfo) {
                Log.e("下载：", "onFailure: " + erroInfo);
                activity.get().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(activity.get().getApplicationContext(), erroInfo, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    protected void progress(int progress) {
        tvDownloadProgress.setProgress(progress);
    }
}
