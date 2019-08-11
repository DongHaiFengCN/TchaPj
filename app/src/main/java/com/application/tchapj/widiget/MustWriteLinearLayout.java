package com.application.tchapj.widiget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.application.tchapj.R;

import org.w3c.dom.Text;

/**
 * Create by zyy on 2019/4/16
 * Description: 加*号必填的LinearLayout自定义布局
 */
public class MustWriteLinearLayout extends LinearLayout {

    private View linearLayoutView;
    private TextView titleTv;

    public MustWriteLinearLayout(Context context) {
        this(context, null);
    }

    public MustWriteLinearLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MustWriteLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        linearLayoutView = LayoutInflater.from(context).inflate(R.layout.layout_must_write_ll,this);
        titleTv = linearLayoutView.findViewById(R.id.must_write_ll_title_tv);


        TypedArray typedArray=context.getTheme().obtainStyledAttributes(attrs,R.styleable.MustWriteLayout,defStyleAttr,0);
        int index=typedArray.getIndexCount();
        for(int i=0;i<index;i++){
            int attr=typedArray.getIndex(i);
            switch (attr){
                case R.styleable.MustWriteLayout_title_tv:
                    setTitle(typedArray.getString(R.styleable.MustWriteLayout_title_tv));
                    break;
            }
        }
        typedArray.recycle();
    }

    public void setTitle(String string) {
        titleTv.setText(string);
    }

}
