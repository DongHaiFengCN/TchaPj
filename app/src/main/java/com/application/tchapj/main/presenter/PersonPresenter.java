package com.application.tchapj.main.presenter;

import com.application.tchapj.App;
import com.application.tchapj.base.BaseMvpPresenter;
import com.application.tchapj.main.bean.PersonMediaModel;
import com.application.tchapj.main.bean.PersonSelectModel;
import com.application.tchapj.main.view.IPersonView;
import com.king.base.util.LogUtils;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Sun on 2018/6/24.
 */

public class PersonPresenter extends BaseMvpPresenter<IPersonView> {

    public PersonPresenter(App app) {
        super(app);
    }

   /* // 全部名人分类
    public void getPersonMediaModelResult(String type, String pageNum, String pageSize){
        // isViewAttached()方法判断是否在显示器上显示
        if(isViewAttached()){
            getView().showProgress();
        }

        // 观察者被观察者模式
        // 得到根接口路径
        getAppComponent()
                .getAPIService() // 所有接口对象
                .getPersonMediaModelResult(type,pageNum,pageSize,"002","1.0","","JSON") // 得到登录接口
                .subscribeOn(Schedulers.io()) // 订阅方式
                .observeOn(AndroidSchedulers.mainThread()) // 指定线程
                .subscribe(new Subscriber<PersonMediaModel>() {  // 将数据绑定到实体类的操作
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
                    public void onNext(PersonMediaModel personMediaModel) {
                        LogUtils.d("Response:"+ personMediaModel);
                        if(isViewAttached())
                            getView().onGetPersonMediaModelResult(personMediaModel); // 得到一层数据

                    }
                });
    }*/

    // 名人分类 数据
    public void getPersonMediaResult(String cat_type, String newsTypeId, String serviceiId, String pageNum, String pageSize){
        // isViewAttached()方法判断是否在显示器上显示
        if(isViewAttached()){
            getView().showProgress();
        }

        // 观察者被观察者模式
        // 得到根接口路径
        getAppComponent()
                .getAPIService() // 所有接口对象
                .getPersonMediaResult(cat_type,newsTypeId,serviceiId,pageNum,pageSize,"002","1.0","","JSON") // 得到登录接口
                .subscribeOn(Schedulers.io()) // 订阅方式
                .observeOn(AndroidSchedulers.mainThread()) // 指定线程
                .subscribe(new Subscriber<PersonMediaModel>() {  // 将数据绑定到实体类的操作
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
                    public void onNext(PersonMediaModel personMediaModel) {
                        LogUtils.d("Response:"+ personMediaModel);
                        if(isViewAttached())
                            getView().onGetPersonMediaResult(personMediaModel); // 得到一层数据

                    }
                });
    }

    /*// 全部名人分类更多
    public void getPersonMediaModelResultMost(String type, String pageNum, String pageSize){
        // isViewAttached()方法判断是否在显示器上显示
        if(isViewAttached()){
            getView().showProgress();
        }

        // 观察者被观察者模式
        // 得到根接口路径
        getAppComponent()
                .getAPIService() // 所有接口对象
                .getPersonMediaModelResult(type,pageNum,pageSize,"002","1.0","","JSON") // 得到登录接口
                .subscribeOn(Schedulers.io()) // 订阅方式
                .observeOn(AndroidSchedulers.mainThread()) // 指定线程
                .subscribe(new Subscriber<PersonMediaModel>() {  // 将数据绑定到实体类的操作
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
                    public void onNext(PersonMediaModel personMediaModel) {
                        LogUtils.d("Response:"+ personMediaModel);
                        if(isViewAttached())
                            getView().onGetPersonMediaModelResultMost(personMediaModel); // 得到一层数据

                    }
                });
    }*/


    // 分类
    public void getPersonSelectModelResult(){
        // isViewAttached()方法判断是否在显示器上显示
        if(isViewAttached()){
            getView().showProgress();
        }

        // 观察者被观察者模式
        // 得到根接口路径
        getAppComponent()
                .getAPIService() // 所有接口对象
                .getPersonSelectModelResult("002","1.0","","JSON") // 得到登录接口
                .subscribeOn(Schedulers.io()) // 订阅方式
                .observeOn(AndroidSchedulers.mainThread()) // 指定线程
                .subscribe(new Subscriber<PersonSelectModel>() {  // 将数据绑定到实体类的操作
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
                    public void onNext(PersonSelectModel personSelectModel) {
                        LogUtils.d("Response:"+ personSelectModel);
                        if(isViewAttached())
                            getView().onGetPersonSelectModelResult(personSelectModel); // 得到一层数据

                    }
                });
    }

}
