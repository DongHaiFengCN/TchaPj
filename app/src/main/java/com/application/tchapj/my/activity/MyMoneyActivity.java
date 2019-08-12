package com.application.tchapj.my.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.AuthTask;
import com.alipay.sdk.app.PayTask;
import com.application.tchapj.App;
import com.application.tchapj.MainActivity;
import com.application.tchapj.R;
import com.application.tchapj.alipay.AuthResult;
import com.application.tchapj.alipay.OrderInfoUtil2_0;
import com.application.tchapj.base.BaseBean;
import com.application.tchapj.base.BaseMvpActivity;

import com.application.tchapj.bean.PromotionResultBean;

import com.application.tchapj.my.bean.AlipayPowerBean;
import com.application.tchapj.my.bean.AlipayResponse;
import com.application.tchapj.my.bean.MoneyInfoBean;
import com.application.tchapj.my.bean.MoneyInfoListBean;
import com.application.tchapj.my.bean.MoneyTransferBean;
import com.application.tchapj.my.presenter.MoneyPresenter;
import com.application.tchapj.my.view.IMoneyView;
import com.application.tchapj.utils.MDDialog;
import com.application.tchapj.utils.ToastUtil;
import com.application.tchapj.utils2.SharedPreferences;
import com.application.tchapj.utils2.qiniu.utils.StringUtils;
import com.application.tchapj.widiget.AopUtils;
import com.application.tchapj.widiget.DensityUtil;
import com.application.tchapj.widiget.ToolbarHelper;
import com.google.gson.Gson;
import com.iflytek.cloud.Setting;
import com.nostra13.universalimageloader.utils.L;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.application.tchapj.DataManager.getDataManager;

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

    private MoneyInfoBean moneyInfoBeanResults;

    private MoneyTransferBean moneyTransferBeans;

    private String alipayId;// 支付宝ID
    private String transferStatus = "1";// 0：可以取现 1：上笔取现处理中
    private Dialog dialog;

    private String amount;
    private InputFilter lengthFilter;

    public String TARGET_ID = "2088721541076382";
    // 开发者 app_id
    public String APPID = "2018062560414948";

    // 开发者 pid
    public String PID = "2088721541076382";

    TextView alipayBt;
    TextView indentityBt;
    LinearLayout indentityLl;
    TextView infoTv;


    ImageView alipayIm;
    ImageView indentityIm;

    @Override
    protected void initToolbar(ToolbarHelper toolbarHelper) {

        toolbarHelper.setTitle("我的钱包");

    }

    @Override
    public int getRootViewId() {
        return R.layout.activity_my_money;
    }

    @Override
    public void initUI() {

        initListener(); // 点击事件
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onResume() {
        super.onResume();
        getPresenter().onGetMoneyInfoBeanResult(getDataManager().quickGetMetaData(R.string.id, String.class));
        refresh();


    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void refresh() {
        if (alipayBt != null && alipayIm != null) {
            String alipay = getDataManager().quickGetMetaData(R.string.alipay, String.class);
            if ("".equals(alipay) || "0".equals(alipay)) {
                alipayBt.setText("未绑定");
                alipayBt.setBackgroundResource(R.drawable.money_dialog_button);
                alipayIm.setImageDrawable(getResources().getDrawable(R.mipmap.ic_no));

            } else {
                alipayBt.setText("已绑定");
                alipayBt.setBackgroundColor(getColor(R.color.white));
                alipayIm.setImageDrawable(getResources().getDrawable(R.mipmap.ic_ok));
            }
        }

        if (indentityBt != null && indentityIm != null) {
            String identity = getDataManager().quickGetMetaData(R.string.identity, String.class);
            if ("".equals(identity)) {
                indentityBt.setText("未认证");
                indentityBt.setBackgroundResource(R.drawable.money_dialog_button);
                indentityIm.setImageDrawable(getResources().getDrawable(R.mipmap.ic_no));

            } else {
                indentityBt.setText("已认证");
                indentityBt.setBackgroundColor(getColor(R.color.white));
                indentityIm.setImageDrawable(getResources().getDrawable(R.mipmap.ic_ok));
            }
        }
    }

    public void initListener() {

        my_3_money.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {

                if (!yz(false)) {
                    new MDDialog.Builder(MyMoneyActivity.this)
                            .setContentView(R.layout.content_dialog)
                            .setContentViewOperator(new MDDialog.ContentViewOperator() {
                                @Override
                                public void operate(View contentView) {
                                    setDialogContentView(contentView, 3);

                                }
                            })
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

                                    // my_money_value.setText(moneyInfoBeanResults.getData().getSy());
                                    getPresenter().onGetMoneyTransferBeanResult(App.money, alipayId, App.getId(), new BigDecimal(moneyInfoBeanResults.getData().getSy()), MyMoneyActivity.this);
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

            }
        });

        my_10_money.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {

                if (!yz(false)) {
                    new MDDialog.Builder(MyMoneyActivity.this)
//				        .setCoantentView(ll)
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
                                    //  my_money_value.setText(moneyInfoBeanResults.getData().getSy());
                                    getPresenter().onGetMoneyTransferBeanResult(App.money, alipayId, App.getId(), new BigDecimal(moneyInfoBeanResults.getData().getSy()), MyMoneyActivity.this);
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

            }

        });

        my_50_money.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {

                if (!yz(false)) {
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
                                    getPresenter().onGetMoneyTransferBeanResult(App.money, alipayId, App.getId(), new BigDecimal(moneyInfoBeanResults.getData().getSy()), MyMoneyActivity.this);

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


            }
        });

        my_100_money.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {

                if (!yz(false)) {
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
                                    // my_money_value.setText(moneyInfoBeanResults.getSy().toString());
                                    getPresenter().onGetMoneyTransferBeanResult(App.money, alipayId, App.getId(), new BigDecimal(moneyInfoBeanResults.getData().getSy()), MyMoneyActivity.this);

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
     * 验证有没有实名和绑定手机号
     *
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    private boolean yz(boolean isRecharge) {

        final String identity = getDataManager().quickGetMetaData(R.string.identity, String.class);
        final String alipay = getDataManager().quickGetMetaData(R.string.alipay, String.class);

        if ("".equals(identity) || "".equals(alipay) || "0".equals(alipay)) {
            View inflate = getLayoutInflater().inflate(R.layout.activity_money_v_dialog, null);
            alipayBt = inflate.findViewById(R.id.alipay_status_bt);
            indentityBt = inflate.findViewById(R.id.indentity_status_bt);
            indentityLl = inflate.findViewById(R.id.indentity_status_ll);
            infoTv = inflate.findViewById(R.id.info_tv);
            alipayIm = inflate.findViewById(R.id.alipay_status_im);
            indentityIm = inflate.findViewById(R.id.indentity_status_im);
            if (isRecharge) {
                indentityLl.setVisibility(View.INVISIBLE);
                infoTv.setText("充值前请先关联支付宝");
            } else {
                indentityLl.setVisibility(View.VISIBLE);
                infoTv.setText("兑换前请先关联支付宝并完成实名认证");
            }


            alipayBt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //去绑定支付宝
                    if ("".equals(alipay) || "0".equals(alipay)) {

                        Map<String, String> authInfoMap = OrderInfoUtil2_0.buildAuthInfoMap(PID, APPID, TARGET_ID, true);
                        String info = OrderInfoUtil2_0.buildOrderParam(authInfoMap);
                        //String privateKey = SharedPreferences.getInstance().getString(getString(R.string.RSA2_PRIVATE), "");

                        String privateKey = getDataManager().quickGetMetaData(R.string.RSA2_PRIVATE, String.class);
                        if ("".equals(privateKey)) {
                            Toast.makeText(MyMoneyActivity.this, "缺失关键参数！", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        String sign = OrderInfoUtil2_0.getSign(authInfoMap, privateKey, true);
                        final String authInfo = info + "&" + sign;

                        Runnable authRunnable = new Runnable() {

                            @Override
                            public void run() {

                                // 构造AuthTask 对象
                                AuthTask authTask = new AuthTask(MyMoneyActivity.this);
                                // 调用授权接口，获取授权结果
                                Map<String, String> result = authTask.authV2(authInfo, true);
                                AuthResult authResult = new AuthResult(result, true);
                                String resultStatus = authResult.getResultStatus();
                                if (TextUtils.equals(resultStatus, "9000") && TextUtils.equals(authResult.getResultCode(), "200")) {
                                    App.authCode = authResult.getAuthCode();
                                    Map<String, String> map = new HashMap<>();
                                    map.put("appKey", "002");
                                    map.put("v", "1.0");
                                    map.put("format", "JSON");
                                    String sign1 = AopUtils.sign(map, "abc");
                                    // 观察者被观察者模式
                                    // 得到根接口路径
                                    getApp().getAppComponent()
                                            .getAPIService() // 所有接口对象
                                            .getAlipayPowerBeanResult(App.getId(), App.authCode, "002", "1.0", sign1, "JSON") // 得到登录接口
                                            .subscribeOn(Schedulers.io()) // 订阅方式
                                            .observeOn(AndroidSchedulers.mainThread()) // 指定线程
                                            .subscribe(new Subscriber<AlipayPowerBean>() {  // 将数据绑定到实体类的操作
                                                @Override
                                                public void onCompleted() {

                                                }

                                                @Override
                                                public void onError(Throwable e) {

                                                }

                                                @Override // 得到数据
                                                public void onNext(AlipayPowerBean alipayPowerBean) {

                                                    if ("000".equals(alipayPowerBean.getCode())) {

                                                        // SharedPreferences.getInstance().setString(getString(R.string.alipay), "1");

                                                        getDataManager().setMetaDataById(R.string.alipay, "1");

                                                    } else {
                                                        getDataManager().setMetaDataById(R.string.alipay, "0");

                                                        // SharedPreferences.getInstance().setString(getString(R.string.alipay), "0");
                                                    }

                                                    //刷新数据refresh();
                                                    refresh();

                                                }
                                            });


                                } else {
                                    // 其他状态值则为授权失败
                                    Toast.makeText(MyMoneyActivity.this, "授权失败", Toast.LENGTH_LONG).show();

                                }
                            }
                        };

                        // 必须异步调用
                        Thread authThread = new Thread(authRunnable);
                        authThread.start();
                    }
                }

            });
            indentityBt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //去实名认证
                    if ("".equals(identity)) {
                        startActivity(new Intent(MyMoneyActivity.this, AuthenticationActivity.class));
                    }

                }
            });

            if ("1".equals(alipay)) {
                alipayBt.setText("已绑定");
                alipayBt.setBackgroundColor(getColor(R.color.white));
                alipayIm.setImageDrawable(getResources().getDrawable(R.mipmap.ic_ok));
            } else {
                alipayBt.setText("未绑定");
                alipayBt.setBackgroundResource(R.drawable.money_dialog_button);
                alipayIm.setImageDrawable(getResources().getDrawable(R.mipmap.ic_no));

            }

            if ("".equals(identity)) {
                indentityBt.setText("未认证");
                indentityBt.setBackgroundResource(R.drawable.money_dialog_button);
                indentityIm.setImageDrawable(getResources().getDrawable(R.mipmap.ic_no));

            } else {
                indentityBt.setBackgroundColor(getColor(R.color.white));
                indentityBt.setText("已认证");
                indentityIm.setImageDrawable(getResources().getDrawable(R.mipmap.ic_ok));
            }


            new AlertDialog.Builder(MyMoneyActivity.this).setView(inflate)
                    .setPositiveButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    }).show();

            return true;

        } else {
            return false;
        }


    }

    /**
     * V币兑换
     */
    private void vExchangeToMoney() {
        if (transferStatus.equals("0")) {
            dialog = new Dialog(MyMoneyActivity.this, R.style.DialogStyle);
            View view = LayoutInflater.from(MyMoneyActivity.this).inflate(R.layout.layout_wallet_customize_money_dialog, null);
            dialog.setContentView(view);

            final EditText amountEdt = view.findViewById(R.id.layout_dialog_wallet_amount_edt);
            final EditText bankOutletsEdt = view.findViewById(R.id.layout_dialog_wallet_bank_outlets);
            final EditText nameEdt = view.findViewById(R.id.layout_dialog_wallet_bank_name);
            final EditText accountNumberEdt = view.findViewById(R.id.layout_dialog_wallet_account_number);
            final TextView amountActualTv = view.findViewById(R.id.layout_dialog_wallet_amount_actual);

            TextView confirmTv = view.findViewById(R.id.layout_dialog_wallet_confirm_tv);
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
    public void cashWithdrawalExtensionMoney() {
        final Dialog dialog = new Dialog(this, R.style.DialogStyle);
        View view = LayoutInflater.from(this).inflate(R.layout.layout_dialog_cash_withdrawal_extension, null);
        dialog.setContentView(view);

        final EditText amountEt = view.findViewById(R.id.layout_dialog_extension_amount_edt);
        lengthFilter = new InputFilter() {

            // source:当前输入的字符
            // start:输入字符的开始位置
            // end:输入字符的结束位置
            // dest：当前已显示的内容
            // dstart:当前光标开始位置
            // dent:当前光标结束位置
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {

                //首次输入.自动补齐0.
                if (dest.length() == 0 && ".".equals(source.toString())) {
                    return "0.";
                }

                //不允许首个字符为0还能输入0
                if (dest.length() == 0 && "0".equals(source.toString())) {
                    return "0.";
                }

                //当前输入框 内容为0 没有.情况下 自动补齐.
                if ("0".equals(dest.toString()) && (end > start)) {
                    return "." + source;
                }
                String dValue = dest.toString();
                String[] splitArray = dValue.split("\\.");
                if (splitArray.length > 1) {
                    String dotValue = splitArray[1];
                    if (dotValue.length() == 2) {
                        return "";
                    }
                }
                return null;
            }
        };
        amountEt.setFilters(new InputFilter[]{lengthFilter});

        final EditText nameEt = view.findViewById(R.id.layout_dialog_extension_bank_name);
        final EditText bankNameEt = view.findViewById(R.id.layout_dialog_extension_bank_outlets);
        final EditText bankCardNumberEt = view.findViewById(R.id.layout_dialog_extension_account_number);

        view.findViewById(R.id.layout_dialog_extension_confirm_tv)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Editable amount;
                        Editable bankName;
                        Editable name;
                        Editable bankCardNumber;
                        if ((amount = amountEt.getText()).length() == 0) {
                            Toast.makeText(MyMoneyActivity.this, "充值金额不能为空！", Toast.LENGTH_LONG).show();
                        } else if ((bankName = bankNameEt.getText()).length() == 0) {
                            Toast.makeText(MyMoneyActivity.this, "开户行名不能为空！", Toast.LENGTH_LONG).show();

                        } else if ((name = nameEt.getText()).length() == 0) {
                            Toast.makeText(MyMoneyActivity.this, "用户姓名不能为空！", Toast.LENGTH_LONG).show();

                        } else if ((bankCardNumber = bankCardNumberEt.getText()).length() == 0) {
                            Toast.makeText(MyMoneyActivity.this, "银行卡号不能为空！", Toast.LENGTH_LONG).show();

                        } else {

                            getPresenter().onGetArtificialTransferBeanResult(amount.toString(), bankName.toString(), name.toString(), bankCardNumber.toString());
                            dialog.dismiss();
                        }
                    }
                });


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

    @SuppressLint("SetTextI18n")
    @Override // 获取钱包信息
    public void onGetMoneyInfoBeanResult(MoneyInfoBean moneyInfoBean) {

        if ("000".equals(moneyInfoBean.getCode())) {
            moneyInfoBeanResults = moneyInfoBean;
            alipayId = moneyInfoBean.getData().getAlipayId();
            promotionFundTv.setText(moneyInfoBean.getData().getProSY() + "");

            Log.e("DOAING", moneyInfoBean.getData().getProSY() + "");
            taskOccupyTv.setText(moneyInfoBean.getData().getOccupy() + " 元");
            Log.e("DOAING", moneyInfoBean.getData().getOccupy() + " 元");
            sy_tv.setText(moneyInfoBean.getData().getSy() + "");
            Log.e("DOAING", moneyInfoBean.getData().getSy() + "");
            my_money_value.setText(moneyInfoBean.getData().getSy() + "");
            sy_tv.setText(moneyInfoBean.getData().getSalary() + "");
            Log.e("DOAING", moneyInfoBean.getData().getSalary() + "");

            // dj_tv.setText(moneyInfoBean.getData().getDj() + "");
            //冻结的钱 包含已付款未审核的任务的钱。会和V币解冻列表页有按钮（灰色和彩色按钮）的数据钱相加不一样
            transferStatus = moneyInfoBean.getData().getTransferStatus();

        } else {
            Toast.makeText(MyMoneyActivity.this, moneyInfoBean.getDescription(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override // 收益提现和退款--支付宝
    public void onGetMoneyTransferBeanResult(MoneyTransferBean moneyTransferBean) {
        if ("000".equals(moneyTransferBean.getCode())) {
            moneyTransferBeans = moneyTransferBean;
            moneyTransferBean.getCode();
            moneyTransferBean.getMethod();

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

    /**
     * 大额提现回调
     *
     * @param baseBean 基础回调信息
     */
    @Override
    public void onGetArtificialTransferBeanResult(BaseBean baseBean) {
        Toast.makeText(MyMoneyActivity.this, baseBean.getDescription(), Toast.LENGTH_LONG).show();
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    @Override
    public void promotionResultBeanBaseBean(final BaseBean<PromotionResultBean> baseBean) {
        String success1 = "000";
        if (success1.equals(baseBean.getCode())) {

            //todo
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String success2 = "9000";
                    PayTask payTask = new PayTask(MyMoneyActivity.this);
                    Map<String, String> stringMap = payTask.payV2(baseBean.getData().getOrderString(), true);
                    String resultStatus = stringMap.get("resultStatus");
                    if (success2.equals(resultStatus)) {

                        String result = stringMap.get("result");
                        Gson gson = new Gson();
                        AlipayResponse response = gson.fromJson(result, AlipayResponse.class);
                        String tradeNo = response.getAlipay_trade_app_pay_response().getTrade_no();
                        getPresenter().promotionOrderPaySuccess(amount, tradeNo);
                    } else {
                        Toast.makeText(MyMoneyActivity.this, baseBean.getDescription(), Toast.LENGTH_SHORT).show();
                    }
                }
            }).start();

        } else {
            Toast.makeText(MyMoneyActivity.this, baseBean.getDescription(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void promotionSuccess(BaseBean baseBean) {

        //最终的推广金账户修改成功
        if ("000".equals(baseBean.getCode())) {

            Toast.makeText(MyMoneyActivity.this, "充值成功", Toast.LENGTH_LONG).show();

        } else {
            //如果失败了，直接抛给人工
            AlertDialog.Builder builder = new AlertDialog.Builder(MyMoneyActivity.this);
            builder.setTitle("充值失败！⚠");
            builder.setPositiveButton("联系客服", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    // 跳转到联系我们界面
         /*           Intent intent = new Intent(MyMoneyActivity.this, ContactActivity.class);
                    startActivity(intent);*/
                }
            }).show();
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
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
                showRechargeMoneyDialog();
                break;
            case R.id.cash_withdrawal_tv:
                //提现
                cashWithdrawalExtensionMoney();
                break;

            default:
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

        LinearLayout xj_ll = contentView.findViewById(R.id.xj_ll);
        LinearLayout zdy_ll = contentView.findViewById(R.id.zdy_ll);
        LinearLayout iv_xj_ll = contentView.findViewById(R.id.iv_xj_ll);

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
                    moneyTipTv.setText("扣除20%所得税，实际到账" + new BigDecimal(App.money)
                            .multiply(new BigDecimal(0.8)).setScale(1, BigDecimal.ROUND_HALF_UP).toPlainString() + "元");
                } else {
                    moneyTipTv.setText("");
                }

            }
        });
    }

    /**
     * 充值界面
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void showRechargeMoneyDialog() {

        if (!yz(true)) {
            final Dialog dialog = new Dialog(this, R.style.DialogStyle);
            View view = getLayoutInflater().inflate(R.layout.layout_dialog_my_money_recharge, null);
            dialog.setContentView(view);
            final EditText amountEt = view.findViewById(R.id.my_money_recharge_edt);
            lengthFilter = new InputFilter() {

                // source:当前输入的字符
                // start:输入字符的开始位置
                // end:输入字符的结束位置
                // dest：当前已显示的内容
                // dstart:当前光标开始位置
                // dent:当前光标结束位置
                @Override
                public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {

                    //首次输入.自动补齐0.
                    if (dest.length() == 0 && ".".equals(source.toString())) {
                        return "0.";
                    }

                    //不允许首个字符为0还能输入0
                    if (dest.length() == 0 && "0".equals(source.toString())) {
                        return "0.";
                    }

                    //当前输入框 内容为0 没有.情况下 自动补齐.
                    if ("0".equals(dest.toString()) && (end > start)) {
                        return "." + source;
                    }
                    String dValue = dest.toString();
                    String[] splitArray = dValue.split("\\.");
                    if (splitArray.length > 1) {
                        String dotValue = splitArray[1];
                        if (dotValue.length() == 2) {
                            return "";
                        }
                    }
                    return null;
                }
            };
            amountEt.setFilters(new InputFilter[]{lengthFilter});
            TextView submitTv = view.findViewById(R.id.my_money_submit_recharge_tv);
            submitTv.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.M)
                @Override
                public void onClick(View view) {
                    Editable a;
                    if ((a = amountEt.getText()).length() > 0) {
                        amount = a.toString();
                        getPresenter().promotionAdvanceOrder(amount);
                        dialog.dismiss();

                    } else {
                        Toast.makeText(MyMoneyActivity.this, "输入金额不能为空！", Toast.LENGTH_LONG).show();
                    }
                }
            });


            Window dialogWindow = dialog.getWindow();
            WindowManager.LayoutParams layoutParams = dialogWindow.getAttributes();
            WindowManager wm = (WindowManager) getApplication().getSystemService(Context.WINDOW_SERVICE);

            layoutParams.width = wm.getDefaultDisplay().getWidth() - DensityUtil.dpTopx(getApplicationContext(), 25);
            dialogWindow.setAttributes(layoutParams);
            dialog.show();
        }


    }
}
