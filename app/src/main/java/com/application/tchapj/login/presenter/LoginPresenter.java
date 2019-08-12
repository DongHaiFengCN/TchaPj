package com.application.tchapj.login.presenter;

import com.application.tchapj.App;
import com.application.tchapj.R;
import com.application.tchapj.base.BaseMvpPresenter;
import com.application.tchapj.login.bean.LoginResult;
import com.application.tchapj.login.bean.NewPhoneLoginResult;
import com.application.tchapj.login.view.ILoginView;
import com.application.tchapj.utils.ToastUtil;
import com.king.base.util.LogUtils;
import com.king.base.util.StringUtils;

import java.util.regex.Pattern;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

// 登录界面的 Presenter 层 业务逻辑继承该层的接口  并 实现View层接口和Model层业务的处理逻辑
@Deprecated
public class LoginPresenter extends BaseMvpPresenter<ILoginView> {


    public LoginPresenter(App app) {
        super(app);
    }

    // 登录
    public void getLoginResult(String username, String password){
        // isViewAttached()方法判断是否在显示器上显示
        if(isViewAttached()){
            getView().showProgress();
        }

        // 观察者被观察者模式
        // 得到根接口路径
        getAppComponent()
                .getAPIService() // 所有接口对象
                .getLoginResult(username,password,"002","1.0","","JSON") // 得到登录接口
                .subscribeOn(Schedulers.io()) // 订阅方式
                .observeOn(AndroidSchedulers.mainThread()) // 指定线程
                .subscribe(new Subscriber<LoginResult>() {  // 将数据绑定到实体类的操作
                    @Override
                    public void onCompleted() {
                        if(isViewAttached())
                            getView().onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(isViewAttached())
                            getView().onError(e);
                    }

                    @Override // 得到数据
                    public void onNext(LoginResult loginResultBean) {
                        LogUtils.d("Response:"+ loginResultBean);
                        if(isViewAttached())
                            getView().onGetLoginResult(loginResultBean); // 得到一层数据

                        if(loginResultBean!=null){
                            if(isViewAttached())
                                getView().onGetLoginResultUsInfo(loginResultBean.getData().getLoginInfo()); // 得到二层数据
                        }
                    }
                });
    }

    // 密码
    public void getUpdateSwResult(String username, String password,String code){
        // isViewAttached()方法判断是否在显示器上显示
        if(isViewAttached()){
            getView().showProgress();
        }

        // 观察者被观察者模式
        // 得到根接口路径
        getAppComponent()
                .getAPIService() // 所有接口对象
                .getUpdateSwResult(username,password,code,"002","1.0","","JSON") // 得到登录接口
                .subscribeOn(Schedulers.io()) // 订阅方式
                .observeOn(AndroidSchedulers.mainThread()) // 指定线程
                .subscribe(new Subscriber<LoginResult>() {  // 将数据绑定到实体类的操作
                    @Override
                    public void onCompleted() {
                        if(isViewAttached())
                            getView().onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(isViewAttached())
                            getView().onError(e);
                    }

                    @Override // 得到数据
                    public void onNext(LoginResult loginResultBean) {
                        LogUtils.d("Response:"+ loginResultBean);
                        if(isViewAttached())
                            getView().onGetUpdateResult(loginResultBean); // 得到一层数据
                    }
                });
    }

    // 注册
    public void getRegisterResult(String username, String password,String code){
        // isViewAttached()方法判断是否在显示器上显示
        if(isViewAttached()){
            getView().showProgress();
        }

        // 观察者被观察者模式
        // 得到根接口路径
        getAppComponent()
                .getAPIService() // 所有接口对象
                .getRegisterResult(username,password,code,"002","1.0","","JSON") // 得到登录接口
                .subscribeOn(Schedulers.io()) // 订阅方式
                .observeOn(AndroidSchedulers.mainThread()) // 指定线程
                .subscribe(new Subscriber<LoginResult>() {  // 将数据绑定到实体类的操作
                    @Override
                    public void onCompleted() {
                        if(isViewAttached())
                            getView().onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(isViewAttached())
                            getView().onError(e);
                    }

                    @Override // 得到数据
                    public void onNext(LoginResult loginResultBean) {
                        LogUtils.d("Response:"+ loginResultBean);
                        if(isViewAttached())
                            getView().onGetRegisterResult(loginResultBean); // 得到一层数据

                        if(loginResultBean!=null){
                            if(isViewAttached())
                                getView().onGetLoginResultUsInfo(loginResultBean.getData().getLoginInfo()); // 得到二层数据
                        }
                    }
                });
    }

    // 验证码
    public void getSmsCodeResult(String username){
        // isViewAttached()方法判断是否在显示器上显示
        if(isViewAttached()){
            getView().showProgress();
        }

        // 观察者被观察者模式
        // 得到根接口路径
        /*getAppComponent()
                .getAPIService() // 所有接口对象
                .getSmsCodeResult(username,"002","1.0","","JSON") // 得到登录接口
                .subscribeOn(Schedulers.io()) // 订阅方式
                .observeOn(AndroidSchedulers.mainThread()) // 指定线程
                .subscribe(new Subscriber<LoginResult>() {  // 将数据绑定到实体类的操作
                    @Override
                    public void onCompleted() {
                        if(isViewAttached())
                            getView().onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(isViewAttached())
                            getView().onError(e);
                    }

                    @Override // 得到数据
                    public void onNext(LoginResult loginResultBean) {
                        LogUtils.d("Response:"+ loginResultBean);
                        if(isViewAttached())
                            getView().onGetSmsCodeResult(loginResultBean); // 得到一层数据

                        if(loginResultBean!=null){
                            if(isViewAttached())
                                getView().onGetLoginResultUsInfo(loginResultBean.getData().getLoginInfo()); // 得到二层数据
                        }
                    }
                });*/
    }

    // 新手机验证登录
    public void getNewPhoneLoginResult(String username,String code){
        // isViewAttached()方法判断是否在显示器上显示
        if(isViewAttached()){
            getView().showProgress();
        }

        // 观察者被观察者模式
        // 得到根接口路径
        getAppComponent()
                .getAPIService() // 所有接口对象
                .getNewPhoneLoginResult(username,code,"002","1.0","","JSON") // 得到登录接口
                .subscribeOn(Schedulers.io()) // 订阅方式
                .observeOn(AndroidSchedulers.mainThread()) // 指定线程
                .subscribe(new Subscriber<NewPhoneLoginResult>() {  // 将数据绑定到实体类的操作
                    @Override
                    public void onCompleted() {
                        if(isViewAttached())
                            getView().onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(isViewAttached())
                            getView().onError(e);
                    }

                    @Override // 得到数据
                    public void onNext(NewPhoneLoginResult newPhoneLoginResult) {
                        LogUtils.d("Response:"+ newPhoneLoginResult);
                        if(isViewAttached())
                            getView().onGetNewPhoneLoginResult(newPhoneLoginResult); // 得到一层数据

                    }
                });
    }

    // 新手机第三方登录
    public void getThirdLoginResult(String tid, String logintype, String name, String sex, String headimgurl){
        // isViewAttached()方法判断是否在显示器上显示
        if(isViewAttached()){
            getView().showProgress();
        }

        name = getFilterName(name);

        // 观察者被观察者模式
        // 得到根接口路径
        getAppComponent()
                .getAPIService() // 所有接口对象
                .getThirdLoginResult(tid,logintype,name,sex,headimgurl,"002","1.0","","JSON") // 得到登录接口
                .subscribeOn(Schedulers.io()) // 订阅方式
                .observeOn(AndroidSchedulers.mainThread()) // 指定线程
                .subscribe(new Subscriber<NewPhoneLoginResult>() {  // 将数据绑定到实体类的操作
                    @Override
                    public void onCompleted() {
                        if(isViewAttached())
                            getView().onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(isViewAttached())
                            getView().onError(e);
                    }

                    @Override // 得到数据
                    public void onNext(NewPhoneLoginResult newPhoneLoginResult) {
                        LogUtils.d("Response:"+ newPhoneLoginResult);
                        if(isViewAttached())
                            getView().onGetThirdLoginResult(newPhoneLoginResult); // 得到一层数据

                    }
                });
    }

    private String getFilterName(String name) {
        if (StringUtils.isEmpty(name)) {
            return "";
        }
        Pattern emojiPattern = Pattern.compile(
                "[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",
                Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < name.length(); i++) {
            char valueChar = name.charAt(i);

            if (emojiPattern.matcher(valueChar + "").find()) {
                continue;
            } else {
                stringBuilder.append(valueChar);
            }
        }
        return stringBuilder.toString();
    }

}
