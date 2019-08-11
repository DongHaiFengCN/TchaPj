package com.application.tchapj.video.fragment;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.application.tchapj.R;
import com.application.tchapj.base.BaseMvpFragment;
import com.application.tchapj.utils2.videoplayer.NiceVideoPlayerManager;
import com.application.tchapj.video.adapter.VideoAdapter;
import com.application.tchapj.video.bean.VideosModel;
import com.application.tchapj.video.presenter.VideoPresenter;
import com.application.tchapj.video.view.IVideosView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

import static android.content.Context.SENSOR_SERVICE;


// 视频页Fragment
public class VideoFragment extends BaseMvpFragment<IVideosView, VideoPresenter> implements IVideosView {

    @BindView(R.id.video_refreshLayout)
    SmartRefreshLayout video_refreshLayout;
    @BindView(R.id.video_recyclerview)
    RecyclerView video_recyclerview;

    private int pageNum = 1;
    private int pageSize = 10;

    private VideoAdapter videoAdapter;

    private String type;

    private List<VideosModel.VideosResult.Videos> videosModelInfo = new ArrayList<>();

    //video
    private SensorManager sensorManager;
    private JCVideoPlayer.JCAutoFullscreenListener sensorEventListener;


    // 接收参数
    public static VideoFragment newInstance(String param) {
        Bundle args = new Bundle();
        VideoFragment fragment = new VideoFragment();

        fragment.type = param;

        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_video;
    }

    @Override
    public void initUI() {

        videoAdapter = new VideoAdapter(getContext());
        video_recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        video_recyclerview.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        video_recyclerview.setAdapter(videoAdapter);


        initVideo();

        initListener();

    }

    private void initVideo() {

        sensorManager = (SensorManager) getActivity().getSystemService(SENSOR_SERVICE);
        sensorEventListener = new JCVideoPlayer.JCAutoFullscreenListener();
    }

    private void initListener() {

        video_refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageNum = 1;
                if (videosModelInfo!=null){
                    videosModelInfo.clear();
                }
                getPresenter().getVideoResult(pageNum+"",pageSize+"");
            }
        });

        video_refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pageNum++;
                getPresenter().getVideoResult(pageNum+"",pageSize+"");
            }
        });

        /*videoAdapter.setClickItemListener(new VideoAdapter.ClickItemListener() {
            @Override
            public void onclick(String id) {
                Intent intent = new Intent(getContext(),WebViewActivity.class);
                intent.putExtra(WebViewActivity.URL_KEY, H5UrlData.ZIXUNDETAILS+id);
                intent.putExtra(WebViewActivity.TITLE,"");
                getActivity().startActivity(intent);
            }
        });*/
    }

    @Override
    public void initData() {
        getPresenter().getVideoResult(pageNum+"",pageSize+"");
    }

    @Override
    public VideoPresenter createPresenter() {

        return new VideoPresenter(getApp()) ;
    }

    @Override
    public void onGetVideos(VideosModel videosModel) {

        if ("000".equals(videosModel.getCode())) {

            if (pageNum==1){
                videosModelInfo = videosModel.getData().getNews();
            }else{
                videosModelInfo.addAll(videosModel.getData().getNews());
            }

            videoAdapter.setData(videosModelInfo);

            if (video_refreshLayout.isEnableRefresh()){
                video_refreshLayout.finishRefresh();
            }
            if (video_refreshLayout.isEnableLoadMore()){
                video_refreshLayout.finishLoadMore();
            }
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

        if (video_refreshLayout.isEnableRefresh()){
            video_refreshLayout.finishRefresh();
        }
        if (video_refreshLayout.isEnableLoadMore()){
            video_refreshLayout.finishLoadMore();
        }

    }

    @Override
    public void onDestroy() {

        if (NiceVideoPlayerManager.instance().onBackPressd()) return;

        super.onDestroy();

    }


    @Override
    public void onResume() {
        super.onResume();
        Sensor accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(sensorEventListener, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(sensorEventListener);
        JCVideoPlayer.releaseAllVideos();
    }

}
