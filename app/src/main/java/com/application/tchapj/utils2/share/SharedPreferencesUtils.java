package com.application.tchapj.utils2.share;

import android.content.Context;
import android.content.SharedPreferences;

import com.alibaba.fastjson.JSON;
import com.application.tchapj.bean.TaskDraftBean;
import com.application.tchapj.bean.UserInfo;
import com.application.tchapj.main.bean.StartInitiationDataModel;

/**
 * description ：
 * project name：MyAppProject
 * author : Vincent
 * creation date: 2017/2/21 8:45
 *
 * @version 1.0
 */

public class SharedPreferencesUtils {

    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;
    private Context mContext;

    private static final String SP_KEY_USER_INFO = "sp_key_user_info";//用户信息
    private static final String SP_KEY_NICK_NAME = "SP_KEY_NICK_NAME";
    private static final String SP_KEY_START_INITIATION_DATA = "sp_key_start_initiation_data";
    private static final String SP_KEY_SAVE_TASK_TO_DRAFT = "sp_key_save_task_to_draft";//保存任务要草稿


    public SharedPreferencesUtils(Context context, String name){
        this.mContext = context;
        sharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public SharedPreferencesUtils(){

    }


    public static SharedPreferences.Editor getEditor() {
        if(editor!=null)
            return editor;
        else
            return null;
    }

    /**
     * 写入String类型键值对
     * @param key
     * @param vlaues
     * @return
     */
    public boolean putString(String key, String vlaues){
        editor.putString(key,vlaues);
        boolean result = editor.commit();
        return result;
    }

    /**
     * 写入long
     * @param key
     * @param values
     * @return
     */
    public boolean putLong(String key, Long values){
        editor.putLong(key,values);
        boolean result = editor.commit();
        return result;
    }

    /**
     * 写入int
     * @param key
     * @param values
     * @return
     */
    public boolean putInt(String key, int values){
        editor.putInt(key,values);
        boolean result = editor.commit();
        return result;
    }

    /**
     * 写入boolean
     * @param key
     * @param values
     * @return
     */
    public boolean putBoolean(String key, boolean values){
        editor.putBoolean(key,values);
        boolean result = editor.commit();
        return result;
    }

    /**
     * 写入float
     * @param key
     * @param values
     * @return
     */
    public boolean putFloat(String key, float values){
        editor.putFloat(key,values);
        boolean result = editor.commit();
        return result;
    }

    /**
     * 移除某个值
     * @param key
     * @return
     */
    public boolean removeValues(String key){
        editor.remove(key);
        boolean result = editor.commit();
        return result;
    }

    /**
     * 清理
     * @return
     */
    public boolean clear(){
        editor.clear();
        return editor.commit();
    }

    /**
     * 获取String，默认值
     * @param key
     * @param defaultValues
     * @return
     */
    public String getString(String key, String defaultValues){
        if(sharedPreferences != null){
            return sharedPreferences.getString(key,defaultValues);
        }else{
            return "";
        }

    }

    /**
     * 设置String，默认值
     * @param key
     * @param defaultValues
     * @return
     */
    public void setString(String key, String defaultValues){
        editor.putString(key,defaultValues);
        editor.apply();
    }

    /**
     * 获取long
     * @param key
     * @return
     */
    public long getLoing(String key){
        return sharedPreferences.getLong(key,0);
    }

    /**
     * 获取int值
     * @param key
     * @return
     */
    public int getInt(String key){
        return sharedPreferences.getInt(key,0);
    }

    /**
     * 获取boolean
     * @param key
     * @return
     */
    public boolean getBoolean(String key){
        return sharedPreferences.getBoolean(key,false);
    }

    /**
     * 获取float值
     * @param key
     * @return
     */
    public float getFloat(String key){
        return sharedPreferences.getFloat(key,0);
    }


    public StartInitiationDataModel getStartInitiationData() {
        String dataStr = getInstance().getString(SP_KEY_START_INITIATION_DATA, null);
        StartInitiationDataModel startInitiationDataModel = null;
        if (dataStr != null) {
            startInitiationDataModel = JSON.parseObject(dataStr, StartInitiationDataModel.class);
        }

        if(startInitiationDataModel != null){
            return startInitiationDataModel;
        }else{
            return new StartInitiationDataModel();
        }
    }

    public void setStartInitiationData(StartInitiationDataModel startInitiationDataModel) {
        getInstance().setString(SP_KEY_START_INITIATION_DATA, JSON.toJSONString(startInitiationDataModel));
    }



    public UserInfo getUserInfo() {
        String userInfoStr = getInstance().getString(SP_KEY_USER_INFO, null);
        UserInfo userInfo = null;
        if (userInfoStr != null) {
            userInfo = JSON.parseObject(userInfoStr, UserInfo.class);
        }

        if(userInfo != null){
            return userInfo;
        }else{
            return new UserInfo();
        }
    }

    public void setUserInfo(UserInfo userInfo) {
        getInstance().setString(SP_KEY_USER_INFO, JSON.toJSONString(userInfo));
    }


    public TaskDraftBean getTaskToDraft() {
        String str = getInstance().getString(SP_KEY_SAVE_TASK_TO_DRAFT, null);
        TaskDraftBean bean = null;
        if (str != null) {
            bean = JSON.parseObject(str, TaskDraftBean.class);
        }

        return bean;

    }

    //保存图文任务到草稿
    public void setTaskToDraft(TaskDraftBean taskToDraft) {
        getInstance().setString(SP_KEY_SAVE_TASK_TO_DRAFT, JSON.toJSONString(taskToDraft));
    }

    public void setNickName(String nickName){
        getInstance().setString(SP_KEY_NICK_NAME, nickName);
    }

    public String getNickName(){
        return getInstance().getString(SP_KEY_NICK_NAME, null);
    }


    public static SharedPreferencesUtils getInstance(){
        return SharedPreferencesUtilsInstance.INSTANCE;
    }

    private static class SharedPreferencesUtilsInstance{
        private static final SharedPreferencesUtils INSTANCE = new SharedPreferencesUtils();
    }
}
