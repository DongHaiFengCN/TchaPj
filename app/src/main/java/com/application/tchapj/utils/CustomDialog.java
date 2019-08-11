package com.application.tchapj.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.application.tchapj.R;

// 自定义对话框类
public class CustomDialog extends ProgressDialog
{

    private boolean isCancle = false;

    private TextView textView;
    private LayoutInflater mInflater;


    public CustomDialog(Context context)
    {
        super(context);
    }
    public CustomDialog(Context context, int theme)
    {
        super(context, theme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mInflater = LayoutInflater.from(getContext());
        init(getContext());
    }

    private void init(Context context)
    {
        //设置不可取消，点击其他区域不能取消，实际中可以抽出去封装供外包设置
//        setCancelable(isCancle);
//        setCanceledOnTouchOutside(isCancle);

        View view = mInflater.inflate(R.layout.dialog_layout,null);
        setContentView(view);
        textView = (TextView) view.findViewById(R.id.tv_load_dialog);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(params);
    }

    @Override
    public void show()
    {
        super.show();
    }

    public void setCancle(boolean cancle) {
        this.setCancelable(cancle);
        this.setCanceledOnTouchOutside(cancle);
    }

    public void setTip(String tip){
        textView.setText(tip);
    }
}