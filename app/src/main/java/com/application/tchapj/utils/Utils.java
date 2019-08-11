package com.application.tchapj.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Environment;
import android.provider.MediaStore;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.webkit.WebView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

// 版本信息工具类
public class Utils {

    //  检查网络  暴露出来外部调用的接口方法
    public static boolean checkNetwork(Context ctx) {
        try {
            //  创建链接管理器对象
            ConnectivityManager cwjManager = (ConnectivityManager) ctx
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            //  判断链接管理器对象的状态
            if (cwjManager.getActiveNetworkInfo() != null) {

                return cwjManager.getActiveNetworkInfo().isAvailable();

            } else {
                return false;
            }
        } catch (Exception e) {
            return true;
        }
    }

    //退出应用
    public static void exitApplication(Context context) {
        //  创建意图对象
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        //  添加意图的类型
        startMain.addCategory(Intent.CATEGORY_HOME);
        //  设置意图的表示
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //  启动意图
        context.startActivity(startMain);
        //  调用系统取消对话框方法
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    // 获得网络类型
    public static String getNetworkType(Context context) {

        String typeName = "3G";

        try {
            //  创建链接管理器对象
            ConnectivityManager connectivity = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            //  判断链接管理器对象的状态
            if (connectivity != null) {

                //  得到  创建链接管理器对象  所有网络信息
                NetworkInfo[] info = connectivity.getAllNetworkInfo();

                if (info != null) {
                    for (int i = 0; i < info.length; i++) {
                        //  判断链接状态
                        if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                            //  得到信息
                            typeName = info[i].getTypeName();
                        }
                    }
                }
            }
        } catch (Exception e) {
        }
        return typeName.toLowerCase();
    }

    // 获取当前手机的分辨率，比如：480x820
    public static String getDisplayMetrics(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay()
                .getMetrics(dm);
        return dm.widthPixels + "x" + dm.heightPixels;
    }

    public static int getHeight(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay()
                .getMetrics(dm);
        return dm.heightPixels;
    }

    public static String getUserAgent(Context context) {
        WebView webView = new WebView(context);
        return webView.getSettings().getUserAgentString();
    }

    public static String getDeviceId(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyManager.getDeviceId();
    }

    // 获取AndroidManifest。xml文件里的versionName属性值
    public static String getVersionName(Context context) {
        String versionName = "";
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
            if (versionName == null || versionName.length() <= 0) {
                return "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return versionName;
    }

    // 获取Android版本号，比如：2.3.4
    public static String getAndroidVersion() {
        // return String.valueOf(android.os.Build.VERSION.SDK_INT);
        // return android.os.Build.MODEL + "," + android.os.Build.VERSION.SDK
        // + "," + android.os.Build.VERSION.RELEASE;
        String release = android.os.Build.VERSION.RELEASE;
        if (release.length() > 3)
            return release.substring(0, 3);
        else
            return release;
    }

    public static String getVersion(Context context)//获取版本号
    {
        try {
            PackageInfo pi = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return pi.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static int getVersionCode(Context context)//获取版本号(内部识别号)
    {
        try {
            PackageInfo pi = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return pi.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 获取登录设备mac地址
     *
     * @return
     */
    public static String getMac(Context context) {
        @SuppressLint("WifiManagerLeak") WifiManager wm = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        String mac = wm.getConnectionInfo().getMacAddress();
        return mac == null ? "" : mac;
    }

    /**
     * @bref dip转化px
     */
    public static int dip2px(float dip, Context context) {
        DisplayMetrics me = context.getResources().getDisplayMetrics();
        int margin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, me);
        return margin;
    }

    @SuppressLint("SimpleDateFormat")
    public static String getDataFormatString(long timestamp, String patten) {
        SimpleDateFormat format = new SimpleDateFormat(patten);
        return format.format(timestamp);
    }

    public static long gethours(long endtime) {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1 = formatter.format(currentTime);
        // 获取服务器返回的时间戳 转换成"yyyy-MM-dd HH:mm:ss"
        String date2 = Utils.formatData("yyyy-MM-dd HH:mm:ss", endtime);

        // 计算的时间差
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long hours = 0;

        try {
            Date d1 = df.parse(date1);
            Date d2 = df.parse(date2);
            long diff = d2.getTime() - d1.getTime();// 这样得到的差值是微秒级别


            long days = diff / (1000 * 60 * 60 * 24);
            // long hours = (diff - days * (1000 * 60 * 60 * 24))
            //             / (1000 * 60 * 60);
            hours = (diff / (60 * 60 * 1000) - days * 24);

        } catch (Exception e) {
        }
        return hours;
    }

    public static String formatData(String dataFormat, long timeStamp) {
        if (timeStamp == 0) {
            return "";
        }
        timeStamp = timeStamp;
        String result = "";
        SimpleDateFormat format = new SimpleDateFormat(dataFormat);
        result = format.format(new Date(timeStamp));
        return result;
    }

    /**
     * 保存图片到本地相册
     */
    public static void saveImageToPhotos(Context context, Bitmap bmp) {
        // 首先保存图片
        File appDir = new File(Environment.getExternalStorageDirectory(), "Pictures");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = System.currentTimeMillis() + ".jpg";
        File file = new File(appDir, fileName);
        //第1种保存图片的方法
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 把文件插入到系统图库  第2种保存图片的方法
//        try {
//            MediaStore.Images.Media.insertImage(context.getContentResolver(),
//                    file.getAbsolutePath(), fileName, null);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//            return;
//        }
        // 最后通知图库更新
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri uri = Uri.fromFile(file);
        intent.setData(uri);
        context.sendBroadcast(intent);
    }

    /**
     * 将URL转化成bitmap形式
     *
     * @param url
     * @return bitmap type
     */
    public static Bitmap returnBitMap(String url) {
        URL myFileUrl;
        Bitmap bitmap = null;
        try {
            myFileUrl = new URL(url);
            HttpURLConnection conn;
            conn = (HttpURLConnection) myFileUrl.openConnection();
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    /**
     * @bref 获取屏幕宽度
     */
    public static int getDisplayWidth(Context context) {
        DisplayMetrics me = context.getResources().getDisplayMetrics();
        int width = me.widthPixels;
        return width;
    }

    /**
     * 金额格式化（整形的数值不加小数点）
     *
     * @param money double 类型的钱数
     */
    public static String getFormatMoney(double money) {
        if (Double.isNaN(money)) return "";
        if (money == (int) money) {
            return (int) money + "";
        } else {
            return new DecimalFormat("0.00").format(money);
        }
    }

}
