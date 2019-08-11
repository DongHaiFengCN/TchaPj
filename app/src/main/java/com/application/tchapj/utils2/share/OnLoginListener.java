package com.application.tchapj.utils2.share;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformDb;

/**
 * 第三方登录操作过程中会回调这个接口中的方法，不同方法衔接第
 * 三方登录与用户应用登录/注册的逻辑，故使用第三方登录时一定要实
 * 现本接口的不同方法，否则第三方登录是没有意义的。
 */
public interface OnLoginListener {
    /**
     * 授权成功
     *
     * @param platform
     * @param platDB
     */
    void authorizeSuccess(Platform platform, PlatformDb platDB);

    /**
     * 授权失败
     *
     * @param info
     */
    void getProfileError(String info);


}
