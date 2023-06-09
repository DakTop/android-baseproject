package com.runtop.android.baselibrary.tool;

import android.text.TextUtils;
import android.widget.Toast;

import com.runtop.android.baselibrary.BaseApplication;

public class ToastTool {

    public static void showToast(String msg) {
        if (!TextUtils.isEmpty(msg)) {
            Toast.makeText(BaseApplication.getInstance(), msg, Toast.LENGTH_LONG);
        }
    }

}
