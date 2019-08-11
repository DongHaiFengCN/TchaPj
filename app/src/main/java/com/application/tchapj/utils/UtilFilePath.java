package com.application.tchapj.utils;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

// 获取抓拍、录像路径类工具类
public class UtilFilePath {
	
	/**
     * 返回文件名称
     * 
     * @return Pictrue dir path.
     * @since V1.0
     */
	public static StringBuilder getFileName(String prefix, String suffix)
	{
		  Calendar c= Calendar.getInstance();
          int Year,Month,Day,Hour,Minute,Second;
          Year=c.get(Calendar.YEAR);
          Month=c.get(Calendar.MONTH);
          Day=c.get(Calendar.DAY_OF_MONTH);
          Hour=c.get(Calendar.HOUR);
          Minute=c.get(Calendar.MINUTE);
          Second=c.get(Calendar.SECOND);
          
          StringBuilder sFileName=new StringBuilder()
          .append(prefix)
			.append(c.get(Calendar.YEAR))
			.append((Month + 1) < 10 ? "0" + (Month + 1): (Month + 1))
			.append((Day < 10) ? "0" + Day : Day)
			.append("_")
			.append((Hour < 10) ? "0" + Hour : Hour)
			.append((Minute < 10) ? "0" + Minute : Minute)
			.append((Second < 10) ? "0" + Second : Second)
			.append(suffix);
          
          return sFileName;
	}

    /**
     * 获取图片目录
     * 
     * @return Pictrue dir path.
     * @since V1.0
     */
    public static File getPictureDirPath() {
        File SDFile = null;
        File mIVMSFolder = null;
        try {
            SDFile = android.os.Environment.getExternalStorageDirectory();
            String path = SDFile.getAbsolutePath() + File.separator + "HIKVISION";
            mIVMSFolder = new File(path);
            if ((null != mIVMSFolder) && (!mIVMSFolder.exists())) {
                mIVMSFolder.mkdir();
                mIVMSFolder.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mIVMSFolder;
    }

    /**
     * 获取录像目录
     * 
     * @return Video dir path.
     * @since V1.0
     */
    public static File getVideoDirPath() {
        File SDFile = null;
        File mIVMSFolder = null;
        try {
            SDFile = android.os.Environment.getExternalStorageDirectory();
            mIVMSFolder = new File(SDFile.getAbsolutePath() + File.separator + "HIKVISION");
            if ((null != mIVMSFolder) && (!mIVMSFolder.exists())) {
                mIVMSFolder.mkdir();
                mIVMSFolder.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mIVMSFolder;
    }
}
