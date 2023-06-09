package com.runtop.android.core.base.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import com.runtop.android.baselibrary.tool.ScreenTool;
import com.runtop.android.client.R;
import butterknife.ButterKnife;

/**
 * 基础Dialog
 */
public abstract class BaseDialog extends Dialog {

    public Context mContext;
    public View view;

    public BaseDialog(Context context) {
        super(context, R.style.BaseAlertDialog);
        this.mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = LayoutInflater.from(mContext).inflate(getLayoutId(), null);
        setContentView(view);
        ButterKnife.bind(this, view);
    }

    public abstract int getLayoutId();

    public BaseDialog(Context context, int themeResId) {
        super(context, themeResId);
        this.mContext = context;
    }

    public void setSizeByScreenPercent(double widthPercent, double heightPercent) {

        Window window = this.getWindow();
        if (window != null) {
            WindowManager.LayoutParams lp = this.getWindow().getAttributes();
            lp.width = (int) (ScreenTool.getScreenWidth(mContext) * widthPercent);
            lp.height = (int) (ScreenTool.getScreenHeight(mContext) * heightPercent);
            window.setAttributes(lp);
        }
    }

    public void setSize(int width, int height) {
        Window window = this.getWindow();
        if (window != null) {
            WindowManager.LayoutParams lp = this.getWindow().getAttributes();
            lp.width = ScreenTool.dp2px(mContext, width);
            lp.height = ScreenTool.dp2px(mContext, height);
            window.setAttributes(lp);
        }
    }

    public void setWidth(int width) {
        Window window = this.getWindow();
        if (window != null) {
            WindowManager.LayoutParams lp = this.getWindow().getAttributes();
            lp.width = ScreenTool.dp2px(mContext, width);
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            window.setAttributes(lp);
        }
    }

    public void destroy() {
        mContext = null;
        dismiss();
    }

    public void setStatusBar() {
        int flag = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        getWindow().setFlags(flag, flag);
        getWindow().setBackgroundDrawable(null);
    }

}
