package com.application.tchapj.my.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.application.tchapj.App;
import com.application.tchapj.R;
import com.application.tchapj.base.BaseMvpActivity;
import com.application.tchapj.base.BaseMvpPresenter;
import com.application.tchapj.main.bean.CityInfo;
import com.application.tchapj.my.adpter.CityListAdapter;
import com.application.tchapj.my.service.LocationService;
import com.application.tchapj.utils.ToastUtil;
import com.application.tchapj.utils2.SharedPreferences;
import com.application.tchapj.utils2.pickers.entity.City;
import com.application.tchapj.utils2.qiniu.utils.StringUtils;
import com.application.tchapj.utils2.share.SharedPreferencesUtils;
import com.application.tchapj.widiget.ToolbarHelper;
import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.mapapi.model.LatLng;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;

import org.litepal.util.SharedUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * 全部地方频道
 */
public class CityListActivity extends BaseMvpActivity implements AdapterView.OnItemClickListener {


    @BindView(R.id.city_list_position_name_tv)
    TextView cityListPositionNameTv;
    @BindView(R.id.city_list_position_name_desc_tv)
    TextView cityListPositionNameDescTv;
    @BindView(R.id.city_list_again_position_tv)
    TextView cityListAgainPositionTv;
    @BindView(R.id.activity_city_list_lv)
    ListView listView;

    String cityStr = "";
    List<CityInfo> cityInfoList = new ArrayList<>();

    CityListAdapter cityListAdapter;
    private LocationService locationService;
    BDAbstractLocationListener mListener = new BDAbstractLocationListener() {

        @Override
        public void onReceiveLocation(BDLocation bdLocation) {

            if(bdLocation != null && !StringUtils.isNullOrEmpty(bdLocation.getCity())){
                cityStr = bdLocation.getCity().substring(0, bdLocation.getCity().length() - 1);
                cityListPositionNameTv.setText(cityStr);
            }


        }
    };

    @Override
    protected void initToolbar(ToolbarHelper toolbarHelper) {
        toolbarHelper.setTitle("全部地方频道");

    }

    @Override
    public int getRootViewId() {
        return R.layout.activity_city_list;
    }

    @Override
    public void initUI() {

        cityListAdapter = new CityListAdapter(this);
        if(SharedPreferencesUtils.getInstance().getStartInitiationData() != null && SharedPreferencesUtils.getInstance().getStartInitiationData().getCitys() != null){
            cityInfoList = SharedPreferencesUtils.getInstance().getStartInitiationData().getCitys();
            cityListAdapter.setDatas(SharedPreferencesUtils.getInstance().getStartInitiationData().getCitys());
        }
//        listView.setLayoutManager(new LinearLayoutManager(this));
        listView.setAdapter(cityListAdapter);
        listView.setOnItemClickListener(this);

        locationService = ((App) getApplication()).locationService;
        locationService.registerListener(mListener);
        locationService.start();
    }


    @Override
    public void initData() {

    }


    @NonNull
    @Override
    public BaseMvpPresenter createPresenter() {
        return new BaseMvpPresenter(getApp());
    }


    @OnClick({R.id.city_list_again_position_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.city_list_again_position_tv:
                //重新定位
                cityListPositionNameTv.setText("定位中...");
                locationService.registerListener(mListener);
                locationService.start();
                break;
        }
    }

    public static void start(Activity activity) {
        Intent starter = new Intent(activity, CityListActivity.class);
        activity.startActivityForResult(starter, 101);
    }

    /***
     * Stop location service
     */
    @Override
    protected void onStop() {
        locationService.unregisterListener(mListener); //注销掉监听
        locationService.stop(); //停止定位服务
        super.onStop();
    }

    @Override
    public void onBackPressed() {
        //物理按键返回
        setResultToActivity();
        super.onBackPressed();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                //标题栏的返回键
                setResultToActivity();
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    private void setResultToActivity() {
        Intent resultIntent = new Intent();
        if(!StringUtils.isNullOrEmpty(cityStr) && cityInfoList != null && cityInfoList.size() > 0){
            for (int i = 0; i < cityInfoList.size(); i ++){
                if(cityStr.equals(cityInfoList.get(i).getCityName())){
                    resultIntent.putExtra("city_name", cityInfoList.get(i).getCityName());
                    resultIntent.putExtra("city_id", cityInfoList.get(i).getStreamnumber());
                }
            }
        }
        setResult(Activity.RESULT_OK, resultIntent);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        Intent resultIntent = new Intent();
        resultIntent.putExtra("city_name", cityInfoList.get(position).getCityName());
        resultIntent.putExtra("city_id", cityInfoList.get(position).getStreamnumber());
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }
}
