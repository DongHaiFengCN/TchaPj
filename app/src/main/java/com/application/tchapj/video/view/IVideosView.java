package com.application.tchapj.video.view;

import com.application.tchapj.base.BaseMvpView;
import com.application.tchapj.video.bean.VideosModel;

// 视频的View层接口
public interface IVideosView extends BaseMvpView {

    // 得到咨询数据
    void onGetVideos(VideosModel videosModel);

}
