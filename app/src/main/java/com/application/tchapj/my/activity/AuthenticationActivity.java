package com.application.tchapj.my.activity;

import android.os.Build;
import android.os.Bundle;


import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.application.tchapj.App;
import com.application.tchapj.R;
import com.application.tchapj.base.BaseBean;
import com.application.tchapj.utils2.SharedPreferences;

import java.util.Calendar;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.application.tchapj.DataManager.getDataManager;

/**
 * @author 董海峰
 */
public class AuthenticationActivity extends AppCompatActivity {
    private EditText name, number;
    private App mApp;
    private static final int NUMBER_SIZE = 18;
    private static final int NUMBER_SPECIAL = 17;
    private static final String SUCCESS = "000";

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApp = (App) getApplication();

        setContentView(R.layout.activity_authentication);
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        name = findViewById(R.id.nameEt);
        number = findViewById(R.id.numberEt);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.primary));
        }
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mApp.getAppComponent()
                .getAPIService()
                .updateIndetity("002", "", "pm.member.updateIdentity", "1.0", "JSON", "", "1", getDataManager().quickGetMetaData(R.string.id,String.class), "370123199007063413", "dd")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseBean>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.e("DOAING",e.getMessage());
                        Toast.makeText(AuthenticationActivity.this, "数据提交失败，请重新尝试提交。", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(BaseBean baseBean) {

                        Log.e("DOAING", baseBean.getDescription() + "------");
                        if (SUCCESS.equals(baseBean.getCode())) {

                           // getDataManager().setMetaDataById(R.string.identity, id, true);

                            Toast.makeText(AuthenticationActivity.this, baseBean.getDescription(), Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                });
    }

    public void onClick(View view) {
        Editable na = name.getText();
        Editable nb = number.getText();
        if (na.length() == 0) {
            name.setError("用户名不能为空！");
            return;
        }
        if (nb.length() == 0) {
            number.setError("身份证号不能为空！");
            return;
        }
        if (nb.length() != NUMBER_SIZE) {
            number.setError("身份证号长度不对");
            return;
        }
        if (na.length() > 0 && (nb.length() == NUMBER_SIZE)) {
            final String id = nb.toString();
            if (isIdentityCard(id)) {
                mApp.getAppComponent()
                        .getAPIService()
                        .updateIndetity("002", "", "pm.member.updateIdentity", "1.0", "JSON", "", "1", getDataManager().quickGetMetaData(R.string.id,String.class), id, na.toString())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<BaseBean>() {
                            @Override
                            public void onCompleted() {
                            }

                            @Override
                            public void onError(Throwable e) {

                                Log.e("DOAING",e.getMessage());
                                Toast.makeText(AuthenticationActivity.this, "数据提交失败，请重新尝试提交。", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onNext(BaseBean baseBean) {

                                Log.e("DOAING", baseBean.getDescription() + "------");
                                if (SUCCESS.equals(baseBean.getCode())) {

                                    getDataManager().setMetaDataById(R.string.identity, id, true);

                                    Toast.makeText(AuthenticationActivity.this, baseBean.getDescription(), Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            }
                        });
            } else {
                number.setError("身份证号不合法");
            }
        }
    }

    /**
     * 校验是不是身份证号码
     *
     * @param id 号码
     * @return 是否是身份证
     */
    public static boolean isIdentityCard(String id) {
        int y, m, d;
        if (id == null) {
            return false;
        }
        id = id.toUpperCase();
        String x1 = "X";
        if (id.contains(x1) && id.indexOf(x1) != NUMBER_SPECIAL) {
            return false;
        }
        String x2 = "x";
        if (id.contains(x2) && id.indexOf(x2) != NUMBER_SPECIAL) {
            return false;
        }
        char verifyBit = '0';
        int sum = (id.charAt(0) - verifyBit) * 7 + (id.charAt(1) - verifyBit) * 9 + (id.charAt(2) - verifyBit) * 10
                + (id.charAt(3) - verifyBit) * 5 + (id.charAt(4) - verifyBit) * 8 + (id.charAt(5) - verifyBit) * 4
                + (id.charAt(6) - verifyBit) * 2 + (id.charAt(7) - verifyBit) + (id.charAt(8) - verifyBit) * 6
                + (id.charAt(9) - verifyBit) * 3 + (id.charAt(10) - verifyBit) * 7 + (id.charAt(11) - verifyBit) * 9
                + (id.charAt(12) - verifyBit) * 10 + (id.charAt(13) - verifyBit) * 5 + (id.charAt(14) - verifyBit) * 8
                + (id.charAt(15) - verifyBit) * 4 + (id.charAt(16) - verifyBit) * 2;
        sum = sum % 11;
        switch (sum) {
            case 0:
                verifyBit = '1';
                break;
            case 1:
                verifyBit = '0';
                break;
            case 2:
                verifyBit = 'X';
                break;
            case 3:
                verifyBit = '9';
                break;
            case 4:
                verifyBit = '8';
                break;
            case 5:
                verifyBit = '7';
                break;
            case 6:
                verifyBit = '6';
                break;
            case 7:
                verifyBit = '5';
                break;
            case 8:
                verifyBit = '4';
                break;
            case 9:
                verifyBit = '3';
                break;
            case 10:
                verifyBit = '2';
                break;
            default:
                break;
        }
        if (id.charAt(NUMBER_SPECIAL) != verifyBit) {
            return false;
        }
        y = Integer.parseInt(id.substring(6, 10), 10);
        m = Integer.parseInt(id.substring(10, 12), 10);
        d = Integer.parseInt(id.substring(12, 14), 10);
        int current = Calendar.getInstance().get(Calendar.YEAR);
        int yMax = 1870;
        if (y > current || y < yMax) {
            return false;
        }
        int mMax = 12;
        if (m < 1 || m > mMax) {
            return false;
        }
        return d >= 1 && d <= 31;
    }
}
