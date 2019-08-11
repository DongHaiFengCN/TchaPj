package com.application.tchapj.utils;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;

import java.io.File;

// SDCard检测类
public class UtilSDCard {

    /**
     * 获取SDCard 路径
     * 
     * @return SDCard 路径
     * @since V1.0
     */
    public static File getSDCardPath() {
        return Environment.getExternalStorageDirectory();
    }

    /**
     * 获取SDCard剩下的大小
     *
     * @return SDCard剩下的大小
     * @since V1.0
     */
    public static long getSDCardRemainSize() {
        StatFs statfs = new StatFs(Environment.getExternalStorageDirectory().getPath());
        long blockSize = statfs.getBlockSize();
        long availableBlocks = statfs.getAvailableBlocks();
        return availableBlocks * blockSize;
    }

    /**
     * 获取SDCard的状态
     *
     * @return SDCard 可用的状态
     */
    public static boolean isSDCardUsable() {
        boolean SDCardMounted = false;
        String sDStateString = Environment.getExternalStorageState();
        if (sDStateString.equals(Environment.MEDIA_MOUNTED)) {
            SDCardMounted = true;
        }

        // 是否正在检测SD卡
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_CHECKING)
                || Environment.getExternalStorageState().equals(Environment.MEDIA_NOFS)) {
            SDCardMounted = false;
        }

        // 检测是否插有SD卡
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_REMOVED)
                || Environment.getExternalStorageState().equals(Environment.MEDIA_UNMOUNTED)) {
            SDCardMounted = false;
        }

        // 检测SD卡是否连接电脑共享
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_SHARED)) {
            SDCardMounted = false;
        }

        return SDCardMounted;
    }

    //TODO 路径修改  获取数据库存放位置
    public static String getDatabaseName(Context context, String databasename){
        boolean isSdcardEnable =false;
        String state = Environment.getExternalStorageState();
        if(Environment.MEDIA_MOUNTED.equals(state)){//SDCard是否插入
            isSdcardEnable = true;
        }
        String dbPath = null;
        if(isSdcardEnable){
            dbPath = Environment.getExternalStorageDirectory().getPath() +"/dzldb/";
        }else{//未插入SDCard，建在内存中
            dbPath =context.getFilesDir().getPath() + "/dzldb/";
        }
        File dbp = new File(dbPath);
        if(!dbp.exists()){
            dbp.mkdirs();
        }
        databasename = dbPath +databasename;
        return databasename;
    }

}