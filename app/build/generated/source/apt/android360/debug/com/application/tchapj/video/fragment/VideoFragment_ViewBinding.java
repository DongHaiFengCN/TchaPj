// Generated code from Butter Knife. Do not modify!
package com.application.tchapj.video.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.application.tchapj.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import java.lang.IllegalStateException;
import java.lang.Override;

public class VideoFragment_ViewBinding implements Unbinder {
  private VideoFragment target;

  @UiThread
  public VideoFragment_ViewBinding(VideoFragment target, View source) {
    this.target = target;

    target.video_refreshLayout = Utils.findRequiredViewAsType(source, R.id.video_refreshLayout, "field 'video_refreshLayout'", SmartRefreshLayout.class);
    target.video_recyclerview = Utils.findRequiredViewAsType(source, R.id.video_recyclerview, "field 'video_recyclerview'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    VideoFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.video_refreshLayout = null;
    target.video_recyclerview = null;
  }
}
