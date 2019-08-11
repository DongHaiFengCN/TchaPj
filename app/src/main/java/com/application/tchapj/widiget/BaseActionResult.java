package com.application.tchapj.widiget;

// 动作执行结果封装基类
public class BaseActionResult {

    public static final String DEFAULT_ERROR_MSG = "error";
    /**
     * 运行成功
     */
    public static final String RESULT_CODE_SUCCESS = "1000";
    /**
     * 未登录
     */
    public static final String RESULT_CODE_NOLOGIN = "401";

    /**
     * 结果状态
     */
    public String ResultCode = "0";
    /**
     * 结果状态码
     */
    public String ResultStateCode;
    /**
     * 结果对象
     */
    public Object ResultObject;

    public Object ResultValue;
}
