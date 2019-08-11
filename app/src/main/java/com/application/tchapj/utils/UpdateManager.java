package com.application.tchapj.utils;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.PowerManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.Toast;


import com.application.tchapj.BuildConfig;
import com.application.tchapj.MainActivity;
import com.application.tchapj.R;
import com.application.tchapj.utils2.pickers.util.LogUtils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import static android.os.AsyncTask.THREAD_POOL_EXECUTOR;


/**
 * 版本更新控制类
 * <p>
 * 修改单例模式
 */
public class UpdateManager {
    final static int DOWNLOAD_SUCCESS = 1;
    final static int NOTIFY_ID = 0;

    private Context context;
    private View progressView;
    private RemoteViews remoteViews;

    private Notification mNotification;
    private NotificationManager notificationManagear;
    private String updateMessage;//更新信息
    String CHANNEL_ID = "tchapj_channel_1";

    private UpdateManager() {
    }

    /*
    静态内部类
     */
    private static class UpdateManagerInstance {
        private static UpdateManager instance;

        static {
            instance = new UpdateManager();
        }
    }

    public static UpdateManager getInstance() {
        return UpdateManagerInstance.instance;
    }

    /**
     * 下载进度更新Handler
     */
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case DOWNLOAD_SUCCESS:
                    //直接调用应用安装
                    String filePath = (String) msg.obj;
                    LogUtils.error("UpdateManager", filePath);
                    installApp(filePath);
                    notificationManagear.notify(NOTIFY_ID, mNotification);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        notificationManagear.deleteNotificationChannel(CHANNEL_ID);
                    }
                    break;
            }
            super.handleMessage(msg);
        }
    };


    public void installApp(String filePath) {

        LogUtils.error("UpdateManager", "1111111");
        File apkfile = new File(filePath);
        if (!apkfile.exists()) {
            LogUtils.error("UpdateManager", "222222");
            return;
        }


        Intent intent;
        LogUtils.error("UpdateManager", "333333");
        //判断是否是AndroidN以及更高的版本
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent = new Intent(Intent.ACTION_INSTALL_PACKAGE);
            LogUtils.error("UpdateManager", "666666");
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".fileProvider", apkfile);
            intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
        } else {
            intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.fromFile(apkfile), "application/vnd.android.package-archive");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        LogUtils.error("UpdateManager", "444444");
        context.startActivity(intent);
        LogUtils.error("UpdateManager", "5555555");

//        Intent i = new Intent(Intent.ACTION_VIEW);
//        i.setDataAndType(Uri.parse("file://" + apkfile.toString()), "application/vnd.android.package-archive");
    }

    /**
     * 检查版本更新
     */
    public void checkUpdate(int newestVersionCode, String downloadUrl) {
        remoteViews = new RemoteViews(context.getPackageName(), R.layout.layout_update_manager_notifaction_progress);


        PackageInfo packageInfo = null;
        //downloadUrl="http://h5.thefair.net.cn/v1/download/app?a=chicken&p=android&t=1234";
        try {
            packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            double oldVersionName = packageInfo.versionCode;
            double newVersionName = newestVersionCode;
            if (newVersionName > oldVersionName) {
                updateMessage = "已有新版本 V" + newestVersionCode;
                updateDialog(downloadUrl);//通知栏
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(context, "包名没有找到", Toast.LENGTH_LONG).show();
        }

    }


    public void updateDialog(final String downloadUrl) {

        CommonDialogUtil.showUpdateApkDialog(context, new UpdateMangerDialogClickListener() {
            @Override
            public void confirmClick() {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {       //根据不同的api采用并行模式进行开启
                    new AppDownload().executeOnExecutor(THREAD_POOL_EXECUTOR,downloadUrl);
                } else {
                    new AppDownload().execute(downloadUrl);
                }


            }

            @Override
            public void cancelmClick() {

            }
        });

    }




    public class AppDownload extends AsyncTask<String, Integer, String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showNotification();
        }

        @Override
        protected String doInBackground(String... params) {
            InputStream is = null;
            FileOutputStream fos = null;
            File app = null;
            BufferedInputStream bis = null;

            try {
                String url = params[0];
                URLConnection conn = new URL(params[0]).openConnection();
                is = conn.getInputStream();
                int fileLength = conn.getContentLength();
                //File file = new File(context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath());
                File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath());
                if (!file.exists()) {
                    file.mkdirs();
                }
                //String fileName = TextUtils.isEmpty(mTaskEntity.getFileName()) ? FileUtils.getFileNameFromUrl(Uri.parse(mTaskEntity.getUrl()).getPath()) : mTaskEntity.getFileName();
                //String appName = url.substring(url.lastIndexOf("/"), url.indexOf('?'));
                String appName = getFileNameFromUrl(Uri.parse(url).getPath());
                app = new File(file, appName);
                if (!app.exists()) {
                    app.createNewFile();
                }

                fos = new FileOutputStream(app);

                bis = new BufferedInputStream(conn.getInputStream());
                byte data[] = new byte[4 * 1024];
                long total = 0;
                int count;
                while ((count = bis.read(data)) != -1) {
                    total += count;
                    fos.write(data, 0, count);
                    fos.flush();

                    int progress = (int)(total * 100 / fileLength);
                    if(progress % 25 == 0){
                        publishProgress(progress);
                    }

                }


            } catch (Exception e) {
                e.printStackTrace();
                return null;
            } finally {
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            return app.getPath();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            int mProgress = values[0];

            remoteViews.setTextViewText(R.id.download_progress_tv, mProgress + "%");
            remoteViews.setProgressBar(R.id.download_progress_bar, 100, mProgress, false);
            notificationManagear.notify(NOTIFY_ID, mNotification); //通知状态变更
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (result == null) {
                return;
            }
            Message msg = handler.obtainMessage();
            msg.what = DOWNLOAD_SUCCESS;
            msg.obj = result;
            handler.sendMessage(msg);
        }

    }

    private void showNotification() {
        //兼容Android O(8.0)系统通知
        notificationManagear = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "tchapj_channel_name";
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name,  NotificationManager.IMPORTANCE_HIGH);
            channel.enableLights(true);//是否在桌面icon右上角展示小红点
            channel.setLightColor(Color.GREEN);//小红点颜色
            channel.setShowBadge(false); //是否在久按桌面图标时显示此渠道的通知

            //创建通知时指定channelID
            //向上兼容 用Notification.Builder构造notification对象
            mNotification = new Notification.Builder(context, CHANNEL_ID)
                    .setContentTitle("通知栏标题")
                    .setContentText("这是消息通过通知栏的内容")
                    .setCustomContentView(remoteViews)
                    .setWhen(System.currentTimeMillis())
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setColor(Color.parseColor("#FEDA26"))
                    .setLargeIcon(BitmapFactory.decodeResource(context.getResources(),R.mipmap.ic_launcher))
                    .setTicker("开始下载")
                    .setOngoing(true)
                    .build();
            notificationManagear.createNotificationChannel(channel);

        } else {
            //向下兼容 用NotificationCompat.Builder构造notification对象
            mNotification = new NotificationCompat.Builder(context)
                    .setContentTitle("通知栏标题")
                    .setContentText("这是消息通过通知栏的内容")
                    .setCustomContentView(remoteViews)
                    .setWhen(System.currentTimeMillis())
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setColor(Color.parseColor("#FEDA26"))
                    .setLargeIcon(BitmapFactory.decodeResource(context.getResources(),R.mipmap.ic_launcher))
                    .setTicker("开始下载")
                    .setOngoing(true)
                    .build();
        }


        notificationManagear.notify(NOTIFY_ID, mNotification);
    }

    /**
     * 从url获取 如果url为空，则文件名为当前时间毫秒值
     *
     * @param url download file url
     * @return file name
     */
    public static String getFileNameFromUrl(String url) {
        if (!TextUtils.isEmpty(url)) {
            return url.substring(url.lastIndexOf("/") + 1);
        }
        return System.currentTimeMillis() + "";
    }


    public void setContext(Context context) {
        this.context = context;
    }


    public interface UpdateMangerDialogClickListener{
        void confirmClick();
        void cancelmClick();
    }

}