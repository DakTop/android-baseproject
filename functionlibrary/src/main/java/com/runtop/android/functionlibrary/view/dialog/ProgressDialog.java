package com.runtop.android.functionlibrary.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.TextView;

import com.runtop.android.functionlibrary.R;


/**
 * 加载进度条
 */
public class ProgressDialog extends Dialog {
    public ProgressDialog(Context context, String s) {
        super(context, R.style.LoadingDialogStyle);
        setCanceledOnTouchOutside(false);
        init(s);
    }

    public ProgressDialog(Context context, int themeResId) {
        super(context, themeResId);
        init("");

    }

    public ProgressDialog(Context context, int themeResId, String strMsg) {
        super(context, themeResId);
        init(strMsg);

    }

    private void init(String strMsg) {
        this.setContentView(R.layout.dialog_common_progress);
        if (this.getWindow() != null) {
            this.getWindow().getAttributes().gravity = Gravity.CENTER;
        }
        TextView tvMsg = this.findViewById(R.id.dialog_common_progress_txt);
        if (tvMsg != null && !TextUtils.isEmpty(strMsg)) {
            tvMsg.setText(strMsg);
        }
    }


}
