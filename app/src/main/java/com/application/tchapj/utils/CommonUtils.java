package com.application.tchapj.utils;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.application.tchapj.App;
import com.application.tchapj.Constants;
import com.application.tchapj.R;
import com.application.tchapj.task.activity.ImagePreviewCopyActivity;
import com.application.tchapj.utils2.LogHelper;
import com.application.tchapj.utils2.picture.tools.DateUtils;
import com.application.tchapj.utils2.qiniu.utils.StringUtils;
import com.application.tchapj.widiget.LogUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;




import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Random;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @author quchao
 * @date 2017/11/27
 */

public class CommonUtils {

    private static final String TAG = CommonUtils.class.getSimpleName();

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dp2px(float dpValue) {
        final float scale = App.getInstance().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * Show message
     *
     * @param activity Activity
     * @param msg message
     */
    public static void showMessage(Activity activity, String msg) {
        LogHelper.e("showMessage ：" + msg);
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * Show message
     *
     * @param activity Activity
     * @param msg message
     */
    public static void showSnackMessage(Activity activity, String msg) {
        LogHelper.e("showSnackMessage ：" + msg);
        Snackbar snackbar = Snackbar.make(activity.getWindow().getDecorView(), msg, Snackbar.LENGTH_SHORT);
        View view = snackbar.getView();
        ((TextView) view.findViewById(R.id.snackbar_text)).setTextColor(ContextCompat.getColor(activity, R.color.white));
        snackbar.show();
    }

    /**
     * 判断2个对象是否相等
     *
     * @param a Object a
     * @param b Object b
     * @return isEqual
     */
    public static boolean isEquals(Object a, Object b) {
        return (a == null) ? (b == null) : a.equals(b);
    }

    /**
     * 检查是否有可用网络
     */
    public static boolean isNetworkConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) App.getInstance().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        assert connectivityManager != null;
        return connectivityManager.getActiveNetworkInfo() != null;
    }

    /**
     * 获取进程号对应的进程名
     *
     * @param pid 进程号
     * @return 进程名
     */
    public static String getProcessName(int pid) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 获取随机rgb颜色值
     */
    public static int randomColor() {
        Random random = new Random();
        //0-190, 如果颜色值过大,就越接近白色,就看不清了,所以需要限定范围
        int red =random.nextInt(150);
        //0-190
        int green =random.nextInt(150);
        //0-190
        int blue =random.nextInt(150);
        //使用rgb混合生成一种新的颜色,Color.rgb生成的是一个int数
        return Color.rgb(red,green, blue);
    }

    public static int randomTagColor() {
        int randomNum = new Random().nextInt();
        int position = randomNum % Constants.TAB_COLORS.length;
        if (position < 0) {
            position = -position;
        }
        return Constants.TAB_COLORS[position];
    }

    /**
     * 泛型转换工具方法 eg:object ==> map<String, String>
     *
     * @param object Object
     * @param <T> 转换得到的泛型对象
     * @return T
     */
    @SuppressWarnings("unchecked")
    public static <T> T cast(Object object) {
        return (T) object;
    }

    /**
     *
     * @param diff 毫秒
     * @return
     */
    public static String getTimeStampDiffer(long diff) {

        String timeDiff = "";

        int ss = 1000;
        int mi = ss * 60;
        int hh = mi * 60;
        int dd = hh * 24;

        long day = diff / dd;
        long hour = (diff - day * dd) / hh;
        long minute = (diff - day * dd - hour * hh) / mi;
        long second = (diff - day * dd - hour * hh - minute * mi) / ss;
        long milliSecond = diff - day * dd - hour * hh - minute * mi - second * ss;

        String strDay = "" + day; //天
        String strHour = "" + hour;//小时
        String strMinute = "" + minute;//分钟
        String strSecond = second < 10 ? "0" + second : "" + second;//秒
//        String strMilliSecond = milliSecond < 10 ? "0" + milliSecond : "" + milliSecond;//毫秒
//        strMilliSecond = milliSecond < 100 ? "0" + strMilliSecond : "" + strMilliSecond;


        if(!StringUtils.isNullOrEmpty(strDay) && !strDay.equals("0")){
            timeDiff += strDay + "天";
        }

        if(!StringUtils.isNullOrEmpty(strHour) && !strHour.equals("0")){
            timeDiff += strHour + "小时";
        }

        if(!StringUtils.isNullOrEmpty(strMinute) && !strMinute.equals("0")){
            timeDiff += strMinute + "分钟";
        }

        if(!StringUtils.isNullOrEmpty(strSecond) && !strSecond.equals("0")&& !strSecond.equals("00")){
            timeDiff += strSecond + "秒";
        }

        return timeDiff;
    }

    public static void openApp(Context context, String packageName) {
        final Intent intent = context.getPackageManager().getLaunchIntentForPackage(packageName);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
        context.startActivity(intent);
    }

    //人民币元转V币
    public static String moneyToVMoney(BigDecimal money){
        BigDecimal vMoneyValue = money.multiply(new BigDecimal(10));//为了防止精度丢失所以用BigDecimal
        vMoneyValue = vMoneyValue.setScale(1);
        return vMoneyValue + "";
    }

    //判断是否登录 return true为已登陆
    public static boolean isLogin(Context context){
        if(StringUtils.isNullOrEmpty(App.getId())){
            CommonDialogUtil.showLoginDialog(context);
            return false;
        }else{
            return true;
        }
    }

    /**
     * 隐藏软键盘(只适用于Activity，不适用于Fragment)
     */
    public static void hideSoftKeyboard(View view) {
//        View view = activity.getCurrentFocus();
//        if (view != null) {
//            InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
//            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
//        }

        InputMethodManager imm = (InputMethodManager) view.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /**
     * 显示软键盘(只适用于Activity，不适用于Fragment)
     */
    public static void showSoftKeyboard(View view) {
//        View view = activity.getCurrentFocus();
//        if (view != null) {
//            InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
//            inputMethodManager.showSoftInput(view, InputMethodManager.SHOW_FORCED);
//        }
        InputMethodManager imm = (InputMethodManager) view.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            view.requestFocus();
            imm.showSoftInput(view, 0);
        }


    }

    //复制文本到粘贴板
    public static void copyTextToClipboard(Context context, String contentStr) {
        ClipboardManager mClipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText("", contentStr);
        mClipboardManager.setPrimaryClip(clipData);
    }


    //保存网络图片到本地
    public static void saveImage(final Context context, final String url) {

        new AsyncTask<Void, Integer, File>() {

            @Override
            protected File doInBackground(Void... params) {
                File file = null;
                try {
                    FutureTarget<File> future = Glide
                            .with(context)
                            .load(url)
                            .downloadOnly(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL);

                    file = future.get();

                    // 首先保存图片
                    File appDir = new File(Environment.getExternalStorageDirectory(), "Pictures");
                    if (!appDir.exists()) {
                        appDir.mkdirs();
                    }
                    String fileName = System.currentTimeMillis() + ".jpg";
                    File destFile = new File(appDir, fileName);

                    FileUtil.copyFile(file, destFile);


                    // 最后通知图库更新
                    context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                            Uri.fromFile(new File(destFile.getPath()))));


                } catch (Exception e) {
                    Log.e(TAG, e.getMessage());
                }
                return file;
            }

            @Override
            protected void onPostExecute(File file) {

                //保存成功

            }

            @Override
            protected void onProgressUpdate(Integer... values) {
                super.onProgressUpdate(values);
            }
        }.execute();

    }


    //根据url获取音视频长度
    public static void setVideoDuration(final String url, final TextView tv_duration) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String duration="";
                android.media.MediaMetadataRetriever mmr = new android.media.MediaMetadataRetriever();
                try {
                    if (url != null) {
                        HashMap<String, String> headers=null;
                        if (headers == null) {
                            headers = new HashMap<String, String>();

                            headers.put("User-Agent", "Mozilla/5.0 (Linux; U; Android 4.4.2; zh-CN; MW-KW-001 Build/JRO03C) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 UCBrowser/1.0.0.001 U4/0.8.0 Mobile Safari/533.1");
                        }
                        mmr.setDataSource(url, headers);
                    }

                    duration = mmr.extractMetadata(android.media.MediaMetadataRetriever.METADATA_KEY_DURATION);
                } catch (Exception ex) {
                } finally {
                    mmr.release();
                }
                LogUtils.e("ryan", "duration " + duration);
                tv_duration.setText(DateUtils.timeParse(Integer.parseInt(duration)));
            }
        }).start();
    }


    /**
     * 设置视频封面图
     * @param url 视频url
     * @param imageView 展示封面图的view控件
     */

    public static void setVideoCover(Context context, final String url, final ImageView imageView) {

        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Glide.with(context)
                .setDefaultRequestOptions(
                        new RequestOptions()
                                .frame(0000000)
                                .centerCrop()
                                .error(R.color.whitesmoke)//可以忽略
                                .placeholder(R.color.whitesmoke)//可以忽略
                )
                .load(url)
                .into(imageView);


    }



}
