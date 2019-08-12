package com.application.tchapj.my.activity;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.application.tchapj.App;
import com.application.tchapj.R;
import com.application.tchapj.base.BaseBean;
import com.application.tchapj.base.BaseMvpActivity;
import com.application.tchapj.bean.PromotionResultBean;
import com.application.tchapj.my.bean.MoneyInfoBean;
import com.application.tchapj.my.bean.MoneyInfoListBean;
import com.application.tchapj.my.bean.MoneyTransferBean;
import com.application.tchapj.my.fragment.MoneyCzFragment;
import com.application.tchapj.my.fragment.MoneyHfFragment;
import com.application.tchapj.my.fragment.MoneySyFragment;
import com.application.tchapj.my.fragment.MoneyTxFragment;
import com.application.tchapj.my.presenter.MoneyPresenter;
import com.application.tchapj.my.view.IMoneyView;
import com.application.tchapj.task.adapter.TaskPagerAdapter;
import com.application.tchapj.widiget.ToolbarHelper;
import com.iflytek.cloud.thirdparty.T;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


// 明细
public class MyMoneyMingXiActivity extends BaseMvpActivity<IMoneyView, MoneyPresenter> implements IMoneyView {

    @BindView(R.id.top_money_group)
    RadioGroup top_money_group;
    @BindView(R.id.top_money_sy)
    RadioButton top_money_sy;
    @BindView(R.id.top_money_hf)
    RadioButton top_money_hf;

    @BindView(R.id.top_money_pager)
    ViewPager top_money_pager;

    private List<Fragment> fragments;

    @Override
    protected void initToolbar(ToolbarHelper toolbarHelper) {

        toolbarHelper.setTitle("明细");
    }

    @Override
    public int getRootViewId() {
        return R.layout.activity_my_moneymingxi;
    }

    @Override
    public void initUI() {

        getPresenter().onGetMoneyInfoBeanResult(App.getId());

        fragments = new ArrayList<>();
        top_money_sy.setChecked(true);

        //收益
        fragments.add(new MoneySyFragment());

        //花费
        fragments.add(new MoneyHfFragment());

        //充值
        fragments.add(new MoneyCzFragment());
        // fragments.add(new MoneyCzFragment());

        fragments.add(new MoneyTxFragment());

        top_money_pager.setAdapter(new TaskPagerAdapter(getSupportFragmentManager(), fragments));
        top_money_pager.setCurrentItem(0); // 默认

        initListener(); // 点击事件

    }

    public void initListener() {

        top_money_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

                int current = 0;

                switch (checkedId) {

                    case R.id.top_money_sy:
                        current = 0;
                        break;
                    case R.id.top_money_hf:
                        current = 1;
                        break;
                    case R.id.top_money_cz:
                        current = 2;
                        break;
                    case R.id.top_money_tx:
                        current = 3;
                        break;
                }

                if (top_money_pager.getCurrentItem() != current) {
                    top_money_pager.setCurrentItem(current);
                }

            }
        });

        top_money_pager.setOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                int currentItem = top_money_pager.getCurrentItem();
                switch (currentItem) {
                    case 0:
                        top_money_group.check(R.id.top_money_sy);
                        break;
                    case 1:
                        top_money_group.check(R.id.top_money_hf);
                        break;
                    case 2:
                        top_money_group.check(R.id.top_money_cz);
                        break;
                    case 3:
                        top_money_group.check(R.id.top_money_tx);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public void initData() {

    }

    @NonNull
    @Override
    public MoneyPresenter createPresenter() {
        return new MoneyPresenter(getApp());
    }

    @Override // 获取钱包信息
    public void onGetMoneyInfoBeanResult(MoneyInfoBean moneyInfoBean) {
    }

    @Override // 收益提现和退款--支付宝
    public void onGetMoneyTransferBeanResult(MoneyTransferBean moneyTransferBean) {

    }

    @Override // 我的任务列表--零钱模块
    public void onGetMoneyInfoListBeanResult(MoneyInfoListBean moneyInfoListBean) {

    }

    @Override
    public void onGetArtificialTransferBeanResult(BaseBean baseBean) {

    }

    @Override
    public void promotionResultBeanBaseBean(BaseBean<PromotionResultBean> baseBean) {

    }

    @Override
    public void promotionSuccess(BaseBean baseBean) {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

    }

}
