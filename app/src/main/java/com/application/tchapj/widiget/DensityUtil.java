package com.application.tchapj.widiget;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;

import com.application.tchapj.App;

// 格式转换工具类
public class DensityUtil {

    private DensityUtil(){
        throw new AssertionError();
    }


    /**
     * dp转px
     * @param context
     * @param dpVal
     * @return
     */
    public static int dp2px(Context context, float dpVal){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dpVal,getDisplayMetrics(context));
    }

    /**
     * dp转px
     * @param context
     * @return
     */
    public static int dpTopx(Context context, float dpValue){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * sp转px
     * @param context
     * @param spVal
     * @return
     */
    public static int sp2px(Context context, float spVal){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,spVal,getDisplayMetrics(context));
    }

    /**
     * px转dp
     * @param context
     * @param pxVal
     * @return
     */
    public static int px2dp(Context context, float pxVal){
        return (int) (pxVal / getDisplayMetrics(context).density + 0.5f);
    }

    /**
     * px转sp
     * @param context
     * @param pxVal
     * @return
     */
    public static int px2sp(Context context, float pxVal){
        return (int) (pxVal / getDisplayMetrics(context).scaledDensity + 0.5f);
    }


    /**
     * 获取DisplayMetrics
     * @param context
     * @return
     */
    public static DisplayMetrics getDisplayMetrics(Context context){
        return context.getResources().getDisplayMetrics();
    }


    /**
     * 通过Resources获取屏幕宽
     */
    public static int getScreenWidth(){
        DisplayMetrics dm = App.getContext().getResources().getDisplayMetrics();
        return dm.widthPixels;
    }





}
