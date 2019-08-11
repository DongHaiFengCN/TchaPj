package com.application.tchapj.my.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;

import com.application.tchapj.App;
import com.application.tchapj.R;
import com.application.tchapj.base.BaseActvity;
import com.application.tchapj.my.service.LocationService;
import com.application.tchapj.widiget.ToolbarHelper;
import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;

import java.util.ArrayList;

/**
 * Created by 安卓开发 on 2018/7/28.
 */

public class ContactActivity extends BaseActvity {

    private FloatingActionButton fl_dh_bt;
    private MapView mMapView;//显示控件
    private BaiduMap mBaiduMap;//mapView内get出来的一个地图控件


    private boolean mMapMoveFlag; //开始定位时，点了按钮就让地图中心移动到指定经纬度

    private final int SDK_PERMISSION_REQUEST = 127;
    private BDAbstractLocationListener mListener = null;

    @Override
    protected void initToolbar(ToolbarHelper toolbarHelper) {

        toolbarHelper.setTitle("联系我们");
    }


    @Override
    protected int getContentViewId() {

        //百度sdk初始化
        setBaiduListener();

        return R.layout.activity_contact;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //视图处理 & 定位发起
        initView();

    }

    // 初始化基本的视图
    private void initView() {

        /*Button dingwei = (Button) findViewById(R.id.btn_dingwei);
        dingwei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startLocation();
            }
        });*/

        mMapView = findViewById(R.id.bmapView);
        fl_dh_bt = findViewById(R.id.fl_dh_bt);

        //获取权限
//        getPersimmions();

        //百度地图UI处理
        handleUI();

        //设置地图中心点到成都
       /* setMapCorePoint(39.901625,   116.530679 );//北京
        setMapCorePoint(30.663791, 104.07281);//成都*/

        setMapCorePoint(36.6588, 117.0799);
        addPointerMarker(setMapCorePoint(36.6588, 117.0799));

        fl_dh_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + App.KF));
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "4001011359"));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });


    }


    // 百度SDK初始化，建议在Application中进行
    private void setBaiduListener() {
        //获取mLocationService实例，建议应用中只初始化1个location实例，然后使用，可以参考其他示例的activity，都是通过此种方式获取mLocationService实例的
        ((App)getApplication()).locationService.registerListener(mListener = new BDAbstractLocationListener() {

            @Override
            public void onReceiveLocation(BDLocation bdLocation) {
                Log.e("shitmap", "定位成功");

                if (mMapMoveFlag) {

                    final double longitude = bdLocation.getLongitude();//精度
                    final double latitude = bdLocation.getLatitude();//纬度
                    bdLocation.getAddress();

                    Log.e("shitmap", "    longitude 精度 == " + longitude + "   latitude  纬度  == " + latitude);
                    LatLng pointer = setMapCorePoint(latitude, longitude);
                    addPointerMarker(pointer);
                    mMapMoveFlag = false;
                }
            }
        });
        ((App) getApplication()).locationService.start();

    }

    // 地图中心点设置
    private LatLng setMapCorePoint(double vd, double jd) {

        // 设定中心点坐标   latlng(经度,纬度)
        LatLng cenpt = new LatLng(vd, jd);

        //定义地图状态
        MapStatus mMapStatus = new MapStatus.Builder()
                .target(cenpt)
                .zoom(19)
                .build();

        // 定义MapStatusUpdate对象，以便描述地图状态将要发生的变化
        final MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);


        // 改变地图状态位置
        // mBaiduMap.setMapStatus(mMapStatusUpdate);
        mBaiduMap.animateMapStatus(mMapStatusUpdate);


        return cenpt;
    }

    // 添加marker点
    private void addPointerMarker(LatLng pointer){
        // 定位完成最后，盖上一个icon，标识出定位的点
        // 定义Maker坐标点
        // 构建Marker图标
        mBaiduMap.clear(); // 清理marker
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.mipmap.ic_map_sign);
        // 构建MarkerOption，用于在地图上添加Marker
        OverlayOptions option = new MarkerOptions()
                .position(pointer)
                .icon(bitmap);
        // 在地图上添加Marker，并显示
        mBaiduMap.addOverlay(option);
    }

    // UI处理
    private void handleUI() {
        // 地图缩放比例尺调整
        zoomMap();
        // 清除一些地图上不需要的标志（看个人需求）
        clearFlags();
    }

    // 地图缩放比例尺调整
    // 具体的level映射表：http://blog.csdn.net/daveleecn/article/details/50537908
    private void zoomMap() {
        mBaiduMap = mMapView.getMap();

        MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(19f);
        mBaiduMap.setMapStatus(msu);
    }

    // 定制清除一些地图上的标志 1.清除缩放控件 2.地图上比例尺 3.隐藏logo
    private void clearFlags() {

        // 这俩行代码都行，看个人喜好使用
        // mMapView.removeViewAt(2);
        //清除缩放控件
        mMapView.showZoomControls(false);

        // 地图上比例尺
        mMapView.showScaleControl(false);

        // 隐藏logo
        mMapView.removeViewAt(1);

        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);

    }

    @TargetApi(23)
    private void getPersimmions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ArrayList<String> permissions = new ArrayList<String>();
            /***
             * 定位权限为必须权限，用户如果禁止，则每次进入都会申请
             */
            // 定位精确位置
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
            }

            if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION);
            }

            /*
             * 读写权限和电话状态权限非必要权限(建议授予)只会申请一次，用户同意或者禁止，只会弹一次
			 */
            // 读写权限
            if (addPermission(permissions, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
//                permissionInfo += "Manifest.permission.WRITE_EXTERNAL_STORAGE Deny \n";
            }
            // 读取电话状态权限
            if (addPermission(permissions, Manifest.permission.READ_PHONE_STATE)) {
//                permissionInfo += "Manifest.permission.READ_PHONE_STATE Deny \n";
            }

            if (permissions.size() > 0) {
                requestPermissions(permissions.toArray(new String[permissions.size()]), SDK_PERMISSION_REQUEST);
            }
        }
    }

    @TargetApi(23)
    private boolean addPermission(ArrayList<String> permissionsList, String permission) {
        if (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) { // 如果应用没有获得对应权限,则添加到列表中,准备批量申请
            if (shouldShowRequestPermissionRationale(permission)) {
                return true;
            } else {
                permissionsList.add(permission);
                return false;
            }

        } else {
            return true;
        }
    }

    @TargetApi(23)
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        // TODO Auto-generated method stub
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }

}
