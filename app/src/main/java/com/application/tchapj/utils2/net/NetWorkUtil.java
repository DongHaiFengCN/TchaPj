package com.application.tchapj.utils2.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Net工具
 * 
 * @author 江钰锋
 * @version [版本号, 2014年6月19日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class NetWorkUtil
{
    /**
     * 网络类型枚举
     * 
     * @author 江钰锋
     * @version [版本号, 2014年6月19日]
     * @see [相关类/方法]
     * @since [产品/模块版本]
     */
    public static enum NetType
    {
        /**
         * 没有网络
         */
        NOT,
        
        /**
         * wifi
         */
        wifi,
        
        /**
         * net网络
         */
        CMNET,
        
        /**
         * wap网络
         */
        CMWAP,
        
        /**
         * 未知类型
         */
        noneNet
    }
    
    /**
     * 网络是否可用
     * 
     * @param context
     * @return
     */
    public static boolean isNetworkAvailable(Context context)
    {
        ConnectivityManager mgr = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] info = mgr.getAllNetworkInfo();
        if (info != null)
        {
            for (int i = 0; i < info.length; i++)
            {
                if (info[i].getState() == NetworkInfo.State.CONNECTED)
                {
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * 判断是否有网络连接
     * 
     * @param context
     * @return
     */
    public static boolean isNetworkConnected(Context context)
    {
        if (context != null)
        {
            ConnectivityManager mConnectivityManager =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null)
            {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }
    
    /**
     * 判断WIFI网络是否可用
     * 
     * @param context
     * @return
     */
    public static boolean isWifiConnected(Context context)
    {
        if (context != null)
        {
            ConnectivityManager mConnectivityManager =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mWiFiNetworkInfo = mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (mWiFiNetworkInfo != null)
            {
                return mWiFiNetworkInfo.isAvailable();
            }
        }
        return false;
    }
    
    /**
     * 判断MOBILE网络是否可用
     * 
     * @param context
     * @return
     */
    public static boolean isMobileConnected(Context context)
    {
        if (context != null)
        {
            ConnectivityManager mConnectivityManager =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mMobileNetworkInfo = mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (mMobileNetworkInfo != null)
            {
                return mMobileNetworkInfo.isAvailable();
            }
        }
        return false;
    }
    
    /**
     * 获取当前网络连接的类型信息
     * 
     * @param context
     * @return
     */
    public static int getConnectedType(Context context)
    {
        if (context != null)
        {
            ConnectivityManager mConnectivityManager =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null && mNetworkInfo.isAvailable())
            {
                return mNetworkInfo.getType();
            }
        }
        return -1;
    }
    
    /**
     *  获取当前的网络状态 -1：没有网络 1：WIFI网络2：wap 网络3：net网络
     * 
     * @param context
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static NetType getAPNType(Context context)
    {
        ConnectivityManager connMgr = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo == null)
        {
            return NetType.noneNet;
        }
        int nType = networkInfo.getType();
        
        if (nType == ConnectivityManager.TYPE_MOBILE)
        {
            if (networkInfo.getExtraInfo().equalsIgnoreCase("cmnet"))
            {
                return NetType.CMNET;
            }
            
            else
            {
                return NetType.CMWAP;
            }
        }
        else if (nType == ConnectivityManager.TYPE_WIFI)
        {
            return NetType.wifi;
        }
        return NetType.noneNet;
        
    }
}
