package com.application.tchapj.utils2.channeltagview.listener;

import android.view.View;

/**
 * 描述：频道tag点击事件回调接口
 * Created by zhaohl on 2017-3-7.
 */

public interface OnChannelItemClicklistener {
    /**
     * 已选中频道item点击监听回调
     * @param itemView
     * @param position
     */
    public void onAddedChannelItemClick(View itemView, int position);

    /**
     * 未选中频道item点击监听回调
     * @param itemView
     * @param position
     */
    public void onUnAddedChannelItemClick(View itemView, int position);
}
