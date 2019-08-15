package com.application.tchapj;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.util.Log;

import com.application.tchapj.login.bean.LoginResult;
import com.application.tchapj.my.bean.AlipayPrivateKeyBean;
import com.application.tchapj.my.bean.UserModel;
import com.application.tchapj.widiget.AopUtils;
import com.google.gson.Gson;


import java.util.HashMap;
import java.util.Map;

import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author 董海峰 2019/8/9
 * Description: 全局缓存数据统一处理中心
 */
public class DataManager {

   private static Gson gson = new Gson();
    private static final int INITIALCAPACITY = 16;
    private static SharedPreferences sharedPreferences;
    private static DataManager dataManager;
    private App application;

    /**
     * 登录广播
     */
    public static final String ACTION_LOGIN = "com.application.tchapj.login";

    /**
     * 缓存刷新广播
     */
    public static final String ACTION_FLUSH = "com.application.tchapj.flush";

    /**
     * 数据是否持久化
     */
    private HashMap<String, Object> map;

    private DataManager(Context context) {
        application = (App) (context.getApplicationContext());
        map = new HashMap<>(INITIALCAPACITY);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    /**
     * 初始化数据中心类，在application 创建调用
     *
     * @param context 上下文
     */
    public synchronized static void init(@NonNull Context context) {
        if (dataManager == null) {
            dataManager = new DataManager(context.getApplicationContext());
        }
    }

    public static DataManager getDataManager() {
        checkInit();
        return dataManager;
    }

    /**
     * 非持久化的方法
     *
     * @param key 关键次
     * @param t   数据
     */
    public void setMetaData(String key, Object t) {
        setMetaData(key, t, false);
    }

    public void setMetaDataById(int keyId, Object t) {
        setMetaDataById(keyId, t, false);
    }

    /**
     * 可持久化的数据
     *
     * @param key           关键词
     * @param t             数据
     * @param isPersistence 持久化的标志true持久化 false不持久化
     */
    private void setMetaData(String key, Object t, boolean isPersistence) {
        map.put(key, t);
        if (isPersistence) {
            sharedPreferencesPut(key, t);
        }
    }

    /**
     * 可持久化的数据
     *
     * @param keyId         关键词id
     * @param t             数据
     * @param isPersistence 持久化的标志true持久化 false不持久化
     */
    public void setMetaDataById(int keyId, Object t, boolean isPersistence) {

        String key = application.getString(keyId);
        map.put(key, t);
        if (isPersistence) {
            sharedPreferencesPut(key, t);
        }
    }

    @SuppressLint("CommitPrefEdits")
    private void sharedPreferencesPut(String key, Object t) {

        if (t instanceof String) {
            sharedPreferences.edit().putString(key, (String) t).apply();
        } else if (t instanceof Boolean) {
            sharedPreferences.edit().putBoolean(key, (Boolean) t).apply();
        } else if (t instanceof Float) {
            sharedPreferences.edit().putFloat(key, (Float) t).apply();
        } else if (t instanceof Integer) {
            sharedPreferences.edit().putInt(key, (Integer) t).apply();
        } else if (t instanceof Long) {
            sharedPreferences.edit().putLong(key, (Long) t).apply();
        }
    }

    private Object sharedPreferencesGet(String key, String type) {
        Object t = null;
        if (type.equals(String.class.getSimpleName())) {
            t = sharedPreferences.getString(key, "");

        } else if (type.equals(Boolean.class.getSimpleName())) {
            t = sharedPreferences.getBoolean(key, true);

        } else if (type.equals(Float.class.getSimpleName())) {
            t = sharedPreferences.getFloat(key, 0.0F);

        } else if (type.equals(Integer.class.getSimpleName())) {
            t = sharedPreferences.getInt(key, 0);

        } else if (type.equals(Long.class.getSimpleName())) {
            t = sharedPreferences.getLong(key, 0L);

        }

        return t;
    }

    /**
     * 从内存中数据
     *
     * @param keyId 关键词id
     * @return 查找的数据
     */
    public <T> T quickGetMetaData(int keyId, Class<T> t) {

        String key = application.getString(keyId);
        Object o = map.get(key);
        if (o == null) {
            o = sharedPreferencesGet(key, t.getSimpleName());

            if (o != null) {
                map.put(key, o);
            }
        }
        return (T) o;
    }


    /**
     * 通过memberId 去刷新说有的配置信息
     *
     * @param upDataListener 刷新回调接口，可以用于数据返回成功，更新ui
     */

    public void disposeMember(final UpDataListener upDataListener) {

        final String memberId = quickGetMetaData(R.string.id, String.class);

        Log.e("DOAING", "更新配置" + memberId);

        if (!"".equals(memberId)) {

            application
                    .getAppComponent()
                    .getAPIService()
                    .getMemberModelResult(getBaseQuestMap())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<UserModel>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            if (upDataListener != null) {

                                upDataListener.upData(false);
                            }
                        }

                        @Override
                        public void onNext(UserModel memberInfo) {

                            Log.e("DOAING++++++++", memberInfo.toString());

                            if ("000".equals(memberInfo.getCode()) && memberInfo.getData() != null) {

                                //实名认证信息
                                getDataManager().setMetaDataById(R.string.identity, memberInfo.getData().getRealName(), true);

                                //任务id taskApplyId
                                getDataManager().setMetaDataById(R.string.taskApplyId, memberInfo.getData().getTaskApplyId(), true);


                                //支付宝认证
                                getDataManager().setMetaDataById(R.string.alipay, memberInfo.getData().getAlipay(), true);


                                //用户名
                                getDataManager().setMetaDataById(R.string.realname, memberInfo.getData().getRealName(), true);

                                //用户昵称
                                getDataManager().setMetaDataById(R.string.nickName, memberInfo.getData().getNickName(), true);

                                //用户微信id
                                //getDataManager().setMetaDataById(R.string.wxId, memberInfo.getData()., true);

                                //QQ
                               // getDataManager().setMetaDataById(R.string.qqId, memberInfo.getData(), true);

                                //用户性别
                                getDataManager().setMetaDataById(R.string.sex, memberInfo.getData().getSex(), true);

                                //用户生日
                                getDataManager().setMetaDataById(R.string.birthday, memberInfo.getData().getBirthday(), true);

                                // 小微号认证 0-未申请 1-正在审核中 2-已通过 3-未通过
                                getDataManager().setMetaDataById(R.string.authState, memberInfo.getData().getAuthState(), true);

                                //媒体资源0-未申请 1-待提交媒体资源 2-媒体资源待审核 3-已通过 4未通过
                                getDataManager().setMetaDataById(R.string.lingState, memberInfo.getData().getLingState(), true);

                                //关注的人
                                getDataManager().setMetaDataById(R.string.attentions, memberInfo.getData().getAttentions(), true);


                                //广告主
                                getDataManager().setMetaDataById(R.string.faState, memberInfo.getData().getFaState(), true);


                                //达人
                                setMetaDataById(R.string.lingState, memberInfo.getData().getLingState(), true);

                                //媒体
                                setMetaDataById(R.string.mtState, memberInfo.getData().getMtState(), true);

                                //名人
                                 setMetaDataById(R.string.mrState, memberInfo.getData().getMrState(), true);

                                //维护白影
                                setMetaDataById(R.string.isAuthor, memberInfo.getData().getIsAuthor(), true);


                                //朋友圈
                                setMetaDataById(R.string.pyqState,memberInfo.getData().getPyqState(),true);

                                //微博
                                setMetaDataById(R.string.wbState,memberInfo.getData().getWbState(),true);

                                //抖音
                                setMetaDataById(R.string.dyState,memberInfo.getData().getDyState(),true);

                                //卫视
                                setMetaDataById(R.string.wsState,memberInfo.getData().getWsState(),true);

                                //其他
                                setMetaDataById(R.string.otherState,memberInfo.getData().getOtherState());


                                //拒绝原因
                                setMetaDataById(R.string.refusal,memberInfo.getData().getRefusal());

                                if (upDataListener != null) {

                                    upDataListener.upData(true);
                                }
                            } else {
                                if (upDataListener != null) {

                                    upDataListener.upData(true);
                                }
                            }

                        }
                    });


        }

        if (upDataListener != null) {

            upDataListener.upData(false);
        }

    }


    /**
     * 登录时调用的方法，获取memberId成功，通过本地广播分发数据到指定的接口
     *
     * @param name          用户名/手机号
     * @param passWorld     用户密码
     * @param isPersistence 是否初持久化
     */
    public void initMemberInfo(final String name, final String passWorld, boolean isPersistence, final LoginListener loginListener) {

        release();
        //第一步先登录获取memberId
        application.getAppComponent()
                .getAPIService()
                .getLoginResult(name, passWorld, "002", "1.0", "", "JSON")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<LoginResult>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        loginListener.login(false);
                        //  release();

                    }

                    @Override
                    public void onNext(LoginResult loginResultBean) {


                        if ("000".equals(loginResultBean.getCode()) && loginResultBean.getData() != null) {

                            LoginResult.DataBean.LoginInfoBean loginInfoBean = loginResultBean.getData().getLoginInfo();

                            // SharedPreferencesUtils.getInstance().setUserInfo(loginInfoBean);

                            //App.setId(loginInfoBean.getId());

                            //memberId
                            getDataManager().setMetaDataById(R.string.id, loginInfoBean.getId(), true);



                            //手机号
                            getDataManager().setMetaDataById(R.string.telephone, loginInfoBean.getMobile(), true);


                            //媒体
                            getDataManager().setMetaDataById(R.string.media, loginInfoBean.getMedia(), true);


                            loginListener.login(true);

                            //去拿支付宝的私钥

                            Map<String, String> map = new HashMap<>();
                            map.put("appKey", "002");
                            map.put("v", "1.0");
                            map.put("format", "JSON");
                            String sign1 = AopUtils.sign(map, "abc");

                            // 观察者被观察者模式
                            // 得到根接口路径
                            application.getAppComponent()
                                    .getAPIService() // 所有接口对象
                                    .getAlipayPrivateKeyBeanResult("002", "1.0", sign1, "JSON") // 得到登录接口
                                    .subscribeOn(Schedulers.io()) // 订阅方式
                                    .observeOn(AndroidSchedulers.mainThread()) // 指定线程
                                    .subscribe(new Subscriber<AlipayPrivateKeyBean>() {  // 将数据绑定到实体类的操作
                                        @Override
                                        public void onCompleted() {

                                        }

                                        @Override
                                        public void onError(Throwable e) {

                                        }

                                        @Override // 得到数据
                                        public void onNext(AlipayPrivateKeyBean alipayPrivateKeyBean) {

                                            setMetaDataById(R.string.RSA2_PRIVATE, alipayPrivateKeyBean.getData().getPrivatekey());
                                            Log.e("Response:", alipayPrivateKeyBean.getData().getPrivatekey());

                                        }
                                    });


                        } else {
                            loginListener.login(false);

                            release();

                        }

                    }
                });

    }

    private static void checkInit() {
        if (dataManager == null) {
            throw new NullPointerException("请先调用初始化方法DataManager.init(context)");
        }
    }

    /**
     * 清空所有缓存，退出账号时用
     */
    @SuppressLint("CommitPrefEdits")
    public void release() {

        map.clear();
        sharedPreferences.edit().clear().apply();
    }

    public interface UpDataListener {

        void upData(boolean getDataSuccess);
    }

    public interface LoginListener {

        void login(boolean isLogin);
    }

    public HashMap<String,String> getBaseQuestMap(){

        HashMap<String,String> map = new HashMap<>();

        map.put("memberId",quickGetMetaData(R.string.id, String.class));
        map.put("appKey","002");

        map.put("v","1.0");
        map.put("sign","");
        map.put("format","JSON");

        return map;
    }
}
