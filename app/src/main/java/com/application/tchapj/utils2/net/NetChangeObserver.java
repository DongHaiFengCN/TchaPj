package com.application.tchapj.utils2.net;


/**
 * 是检测网络改变的观察者
 * 
 * @author 江钰锋
 * @version [版本号, 2014年6月19日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface NetChangeObserver
{
    /**
     * 当前网络已连接
     * 
     * @param type 网络类型
     * @see [类、类#方法、类#成员]
     */
    void onConnect(NetWorkUtil.NetType type);
    
    /**
     * 当前没有网络连接
     */
    void onDisConnect();
}
