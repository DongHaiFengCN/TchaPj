package com.application.tchapj.utils2.agentweb;

/**
 * @author cenxiaozhong
 * @since 3.0.0
 */
public interface PermissionInterceptor {

    boolean intercept(String url, String[] permissions, String action);

}
