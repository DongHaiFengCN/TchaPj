package com.application.tchapj.task.view;

import com.application.tchapj.base.BaseMvpView;
import com.application.tchapj.task.bean.FriendReleaseBean;
import com.application.tchapj.task.bean.TaskSquareInfoModel;

import okhttp3.ResponseBody;


public interface IUploadAudioView extends BaseMvpView {

    /**
     * 上传审核材料详情
     */
    void onGetUploadAudioData(TaskSquareInfoModel friendReleaseBean);

    /**
     * 上传审核材料
     */
    void upload(String fileUrl);

    /**
     * 提交申请
     */
    void submit(ResponseBody responseBody);

}
