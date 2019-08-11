package com.application.tchapj.my.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.application.tchapj.App;
import com.application.tchapj.R;
import com.application.tchapj.base.BaseBean;
import com.application.tchapj.base.BaseMvpActivity;
import com.application.tchapj.consultation.fragment.ConsultationFragment;
import com.application.tchapj.my.bean.MoneyInfoBean;
import com.application.tchapj.my.bean.MoneyInfoListBean;
import com.application.tchapj.my.bean.MoneyTransferBean;
import com.application.tchapj.my.presenter.MoneyPresenter;
import com.application.tchapj.my.view.IMoneyView;
import com.application.tchapj.utils.CommonUtils;
import com.application.tchapj.utils.MDDialog;
import com.application.tchapj.utils.ToastUtil;
import com.application.tchapj.utils2.qiniu.utils.StringUtils;
import com.application.tchapj.widiget.DensityUtil;
import com.application.tchapj.widiget.ToolbarHelper;

import java.math.BigDecimal;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

// 零钱页面
public class MyMoneyActivity extends BaseMvpActivity<IMoneyView, MoneyPresenter> implements IMoneyView {

    @BindView(R.id.detailed_tv)
    TextView detailedTv;

    @BindView(R.id.my_money_value)
    TextView my_money_value; // 余额
    @BindView(R.id.sy_tv)
    TextView sy_tv; // 总收益
    @BindView(R.id.dj_tv)
    TextView dj_tv; // 冻结

    @BindView(R.id.my_3_money)
    LinearLayout my_3_money;
    @BindView(R.id.my_10_money)
    LinearLayout my_10_money;
    @BindView(R.id.my_50_money)
    LinearLayout my_50_money;
    @BindView(R.id.my_100_money)
    LinearLayout my_100_money;
    @BindView(R.id.my_zdy_money)
    LinearLayout my_zdy_money;

    @BindView(R.id.my_30_money_tv)
    TextView my_30_money_tv;
    @BindView(R.id.my_100_money_tv)
    TextView my_100_money_tv;
    @BindView(R.id.my_500_money_tv)
    TextView my_500_money_tv;
    @BindView(R.id.my_1000_money_tv)
    TextView my_1000_money_tv;

    @BindView(R.id.promotion_fund_tv)
    TextView promotionFundTv;//推广金余额
    @BindView(R.id.task_occupy_tv)
    TextView taskOccupyTv;//任务占用
    @BindView(R.id.recharge_money_tv)
    TextView rechargeMoneyTv;//充值
    @BindView(R.id.cash_withdrawal_tv)
    TextView cashWithdrawalTv;//提现
    @BindView(R.id.ic_type)
    ImageView icType;
    @BindView(R.id.my_money_ll)
    LinearLayout myMoneyLl;
    @BindView(R.id.my_money_frozen_ll)
    LinearLayout myMoneyFrozenLl;

    private String headimgurl;

    private MoneyInfoBean.MoneyInfoBeanResult moneyInfoBeanResults;

    private MoneyTransferBean moneyTransferBeans;

    private String alipayId;// 支付宝ID
    private String transferStatus = "1";// 0：可以取现 1：上笔取现处理中
    private Dialog dialog;

    @Override
    protected void initToolbar(ToolbarHelper toolbarHelper) {

        toolbarHelper.setTitle("我的钱包");

        Intent intent = getIntent();

        headimgurl = intent.getStringExtra("headimgurl");

    }

    @Override
    public int getRootViewId() {
        return R.layout.activity_my_money;
    }

    @Override
    public void initUI() {

        initListener(); // 点击事件

    }

    @Override
    protected void onResume() {
        super.onResume();
        getPresenter().onGetMoneyInfoBeanResult(App.getId());
    }

    public void initListener() {


        my_3_money.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MDDialog.Builder(MyMoneyActivity.this)
//				        .setContentView(ll)
                        .setContentView(R.layout.content_dialog)
                        .setContentViewOperator(new MDDialog.ContentViewOperator() {
                            @Override
                            public void operate(View contentView) {
                                setDialogContentView(contentView, 3);

                            }
                        })
//                      .setMessages(messages)
                        .setTitle("标题")
                        .setNegativeButton(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                            }
                        })
                        .setPositiveButton(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        })
                        .setPositiveButtonMultiListener(new MDDialog.OnMultiClickListener() {

                            @Override
                            public void onClick(View clickedView, View contentView) {

                                my_money_value.setText(CommonUtils.moneyToVMoney(moneyInfoBeanResults.getSy()));
                                getPresenter().onGetMoneyTransferBeanResult(App.money, alipayId, App.getId(), moneyInfoBeanResults.getSy(), MyMoneyActivity.this);
                            }
                        })
                        .setNegativeButtonMultiListener(new MDDialog.OnMultiClickListener() {

                            @Override
                            public void onClick(View clickedView, View contentView) {
                                // 取消

                            }
                        })

                        .setWidthMaxDp(600)
                        .setShowTitle(false)  // 隐藏标题
                        .setShowButtons(true)
                        .create()
                        .show();
            }
        });

        my_10_money.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MDDialog.Builder(MyMoneyActivity.this)
//				        .setContentView(ll)
                        .setContentView(R.layout.content_dialog)
                        .setContentViewOperator(new MDDialog.ContentViewOperator() {
                            @Override
                            public void operate(View contentView) {

                                setDialogContentView(contentView, 10);
                            }
                        })
//                      .setMessages(messages)
                        .setTitle("标题")
                        .setNegativeButton(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                            }
                        })
                        .setPositiveButton(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        })
                        .setPositiveButtonMultiListener(new MDDialog.OnMultiClickListener() {

                            @Override
                            public void onClick(View clickedView, View contentView) {
                                // 确定
                                my_money_value.setText(CommonUtils.moneyToVMoney(moneyInfoBeanResults.getSy()));
                                getPresenter().onGetMoneyTransferBeanResult(App.money, alipayId, App.getId(), moneyInfoBeanResults.getSy(), MyMoneyActivity.this);
                            }
                        })
                        .setNegativeButtonMultiListener(new MDDialog.OnMultiClickListener() {

                            @Override
                            public void onClick(View clickedView, View contentView) {
                                // 取消

                            }
                        })

                        .setWidthMaxDp(600)
                        .setShowTitle(false)  // 隐藏标题
                        .setShowButtons(true)
                        .create()
                        .show();
            }

        });

        my_50_money.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MDDialog.Builder(MyMoneyActivity.this)
//				        .setContentView(ll)
                        .setContentView(R.layout.content_dialog)
                        .setContentViewOperator(new MDDialog.ContentViewOperator() {
                            @Override
                            public void operate(View contentView) {

                                setDialogContentView(contentView, 50);

                            }
                        })
//                      .setMessages(messages)
                        .setTitle("标题")
                        .setNegativeButton(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                            }
                        })
                        .setPositiveButton(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        })
                        .setPositiveButtonMultiListener(new MDDialog.OnMultiClickListener() {

                            @Override
                            public void onClick(View clickedView, View contentView) {
                                // 确定
                                /*if(App.money==0){
                                    Toast.makeText(MyMoneyActivity.this,"请选择金额",Toast.LENGTH_LONG).show();

                                }else if(App.money>Integer.parseInt(my_money_value.getText().toString())){
                                    Toast.makeText(MyMoneyActivity.this,"超过余额",Toast.LENGTH_LONG).show();
                                }else {
                                    getPresenter().onGetMoneyTransferBeanResult(Integer.toString(App.money),alipayId,App.getId());
                                }*/
                                my_money_value.setText(CommonUtils.moneyToVMoney(moneyInfoBeanResults.getSy()));
                                getPresenter().onGetMoneyTransferBeanResult(App.money, alipayId, App.getId(), moneyInfoBeanResults.getSy(), MyMoneyActivity.this);

                            }
                        })
                        .setNegativeButtonMultiListener(new MDDialog.OnMultiClickListener() {

                            @Override
                            public void onClick(View clickedView, View contentView) {
                                // 取消

                            }
                        })

                        .setWidthMaxDp(600)
                        .setShowTitle(false)  // 隐藏标题
                        .setShowButtons(true)
                        .create()
                        .show();

            }
        });

        my_100_money.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MDDialog.Builder(MyMoneyActivity.this)
//				        .setContentView(ll)
                        .setContentView(R.layout.content_dialog)
                        .setContentViewOperator(new MDDialog.ContentViewOperator() {
                            @Override
                            public void operate(View contentView) {

                                setDialogContentView(contentView, 100);


                            }
                        })
//                      .setMessages(messages)
                        .setTitle("标题")
                        .setNegativeButton(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                            }
                        })
                        .setPositiveButton(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        })
                        .setPositiveButtonMultiListener(new MDDialog.OnMultiClickListener() {

                            @Override
                            public void onClick(View clickedView, View contentView) {
                                // 确定
                                my_money_value.setText(CommonUtils.moneyToVMoney(moneyInfoBeanResults.getSy()));
                                getPresenter().onGetMoneyTransferBeanResult(App.money, alipayId, App.getId(), moneyInfoBeanResults.getSy(), MyMoneyActivity.this);

                            }
                        })
                        .setNegativeButtonMultiListener(new MDDialog.OnMultiClickListener() {

                            @Override
                            public void onClick(View clickedView, View contentView) {
                                // 取消

                            }
                        })

                        .setWidthMaxDp(600)
                        .setShowTitle(false)  // 隐藏标题
                        .setShowButtons(true)
                        .create()
                        .show();
            }
        });

        my_zdy_money.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
               //大额V币兑换
                vExchangeToMoney();


            }
        });

        detailedTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyMoneyActivity.this, MyMoneyMingXiActivity.class);
                startActivity(intent);
            }
        });

    }

    /**
     * V币兑换
     */
    private void vExchangeToMoney() {
        if (transferStatus.equals("0")) {
            dialog = new Dialog(MyMoneyActivity.this, R.style.DialogStyle);
            View view = LayoutInflater.from(MyMoneyActivity.this).inflate(R.layout.layout_wallet_customize_money_dialog, null);
            dialog.setContentView(view);

            final EditText amountEdt = view.findViewById(R.id.layout_dialog_wallet_amount_edt);//兑换金额
            final EditText bankOutletsEdt = view.findViewById(R.id.layout_dialog_wallet_bank_outlets);//开户行
            final EditText nameEdt = view.findViewById(R.id.layout_dialog_wallet_bank_name);//提现人姓名
            final EditText accountNumberEdt = view.findViewById(R.id.layout_dialog_wallet_account_number);//银行卡账号
            final TextView amountActualTv = view.findViewById(R.id.layout_dialog_wallet_amount_actual);

            TextView confirmTv = view.findViewById(R.id.layout_dialog_wallet_confirm_tv);//确认


            amountEdt.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }

                @Override
                public void afterTextChanged(Editable s) {

                    if (!StringUtils.isNullOrEmpty(amountEdt.getText().toString())) {
                        App.money = (int) (Integer.valueOf(amountEdt.getText().toString()) * 0.1);
                        amountActualTv.setText("实际到账" + App.money * 0.8 + "元");
                    } else {
                        amountActualTv.setText("");
                        App.money = 0;
                    }

                }
            });

            confirmTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (App.money <= 0) {
                        Toast.makeText(MyMoneyActivity.this, "提现金额必须大于0", Toast.LENGTH_LONG).show();
                    } else if (App.money > Double.valueOf(my_money_value.getText().toString())) {
                        Toast.makeText(MyMoneyActivity.this, "请输入小于余额的金额", Toast.LENGTH_LONG).show();
                    } else if (Integer.parseInt(amountEdt.getText().toString()) < 5000) {
                        Toast.makeText(MyMoneyActivity.this, "自定义兑换最少5000个/次", Toast.LENGTH_LONG).show();
                    } else {

                        String bankOutlets = bankOutletsEdt.getText().toString();//开户行
                        String name = nameEdt.getText().toString();//提现人姓名
                        String accountNumber = accountNumberEdt.getText().toString();//银行卡号
                        if (StringUtils.isNullOrEmpty(bankOutlets)) {
                            ToastUtil.show(MyMoneyActivity.this, "请输入开户行");
                            return;
                        } else if (StringUtils.isNullOrEmpty(name)) {
                            ToastUtil.show(MyMoneyActivity.this, "请输入提现人姓名");
                            return;
                        } else if (StringUtils.isNullOrEmpty(accountNumber)) {
                            ToastUtil.show(MyMoneyActivity.this, "请输入银行卡号");
                            return;
                        }

                        my_money_value.setText(CommonUtils.moneyToVMoney(moneyInfoBeanResults.getSy()));
                        getPresenter().onGetArtificialTransferBeanResult(Integer.toString(App.money), bankOutlets, name, accountNumber);
                    }
                }
            });


            dialog.show();
        } else {
            //不可以提现
            Toast.makeText(MyMoneyActivity.this, "您有一笔V币正在兑换中，请耐心等待下哦", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 提现推广金
     */
    public void cashWithdrawalExtensionMoney(){
        final Dialog dialog = new Dialog(this,R.style.DialogStyle);
        View view = LayoutInflater.from(this).inflate(R.layout.layout_dialog_cash_withdrawal_extension, null);
        dialog.setContentView(view);


        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams layoutParams = dialogWindow.getAttributes();
        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);

        layoutParams.width = wm.getDefaultDisplay().getWidth() - DensityUtil.dpTopx(this, 25);
        dialogWindow.setAttributes(layoutParams);
        dialog.show();
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
        if ("000".equals(moneyInfoBean.getCode())) {
            moneyInfoBeanResults = moneyInfoBean.getData();
            alipayId = moneyInfoBeanResults.getAlipayId();
            my_money_value.setText(CommonUtils.moneyToVMoney(moneyInfoBeanResults.getSy()));
            sy_tv.setText(moneyInfoBeanResults.getSalary().multiply(new BigDecimal(10)) + "");
            dj_tv.setText(CommonUtils.moneyToVMoney(moneyInfoBeanResults.getDj()));//冻结的钱 包含已付款未审核的任务的钱。会和V币解冻列表页有按钮（灰色和彩色按钮）的数据钱相加不一样

            transferStatus = moneyInfoBeanResults.getTransferStatus();


        }
    }

    @Override // 收益提现和退款--支付宝
    public void onGetMoneyTransferBeanResult(MoneyTransferBean moneyTransferBean) {
        if ("000".equals(moneyTransferBean.getCode())) {
            moneyTransferBeans = moneyTransferBean;
            moneyTransferBean.getCode();
            moneyTransferBean.getMethod();
//            Toast.makeText(MyMoneyActivity.this,moneyTransferBeans.getDescription(),Toast.LENGTH_LONG).show();

            Toast.makeText(MyMoneyActivity.this, "提现成功，可到支付宝账户中查看", Toast.LENGTH_LONG).show();

            //提现支付宝成功，刷新界面
            getPresenter().onGetMoneyInfoBeanResult(App.getId());
        } else {
            moneyTransferBeans = moneyTransferBean;
            Toast.makeText(MyMoneyActivity.this, moneyTransferBeans.getDescription(), Toast.LENGTH_LONG).show();
        }

    }

    @Override // 我的任务列表--零钱模块
    public void onGetMoneyInfoListBeanResult(MoneyInfoListBean moneyInfoListBean) {

    }

    //大额提现返回
    @Override
    public void onGetArtificialTransferBeanResult(BaseBean baseBean) {
        if ("000".equals(baseBean.getCode())) {
            Toast.makeText(MyMoneyActivity.this, "兑换申请提交成功，请耐心等待", Toast.LENGTH_LONG).show();
            if (dialog != null) {
                dialog.dismiss();
            }

            getPresenter().onGetMoneyInfoBeanResult(App.getId());

        }

    }


    @OnClick({R.id.my_money_frozen_ll, R.id.back_framelayout, R.id.recharge_money_tv, R.id.cash_withdrawal_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.my_money_frozen_ll:
                FrozenListActivity.start(MyMoneyActivity.this);
                break;
            case R.id.back_framelayout:
                finish();
                break;

            case R.id.recharge_money_tv:
                //充值
                showRechargeMoneyDialog(this);
                break;
            case R.id.cash_withdrawal_tv:
                //提现
                cashWithdrawalExtensionMoney();
                break;
        }

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


    /**
     * 提现支付宝的Dialog
     *
     * @param contentView
     * @param moneyNum    提现元的等级
     */
    private void setDialogContentView(View contentView, final int moneyNum) {

        LinearLayout xj_ll = (LinearLayout) contentView.findViewById(R.id.xj_ll);
        LinearLayout zdy_ll = (LinearLayout) contentView.findViewById(R.id.zdy_ll);
        LinearLayout iv_xj_ll = (LinearLayout) contentView.findViewById(R.id.iv_xj_ll);

        int imgResourse = R.mipmap.ic_my_3_money;
        switch (moneyNum) {
            case 3:
                imgResourse = R.mipmap.ic_my_3_money;
                break;
            case 10:
                imgResourse = R.mipmap.ic_my_10_money;
                break;
            case 50:
                imgResourse = R.mipmap.ic_my_50_money;
                break;
            case 100:
                imgResourse = R.mipmap.ic_my_1000_money;
        }

        iv_xj_ll.setBackgroundResource(imgResourse);


        xj_ll.setVisibility(View.VISIBLE);
        zdy_ll.setVisibility(View.GONE);

        final TextView moneyTipTv = contentView.findViewById(R.id.mddialog_money_tip_tv);
        final EditText xj_gs_et = contentView.findViewById(R.id.xj_gs_et);
        xj_gs_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {

                if (!TextUtils.isEmpty(xj_gs_et.getText().toString())) {
                    App.money = Integer.parseInt(xj_gs_et.getText().toString()) * moneyNum;
                    moneyTipTv.setText("扣除20%所得税，实际到账" + new BigDecimal(App.money).multiply(new BigDecimal(0.8)).setScale(1, BigDecimal.ROUND_HALF_UP).toPlainString() + "元");
                } else {
                    moneyTipTv.setText("");
                }


            }
        });
    }

    //充值
    public static void showRechargeMoneyDialog(final Context context) {
        final Dialog dialog = new Dialog(context,R.style.DialogStyle);
        View view = LayoutInflater.from(context).inflate(R.layout.layout_dialog_my_money_recharge, null);
        dialog.setContentView(view);


        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams layoutParams = dialogWindow.getAttributes();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

        layoutParams.width = wm.getDefaultDisplay().getWidth() - DensityUtil.dpTopx(context, 25);
        dialogWindow.setAttributes(layoutParams);
        dialog.show();

    }
}
