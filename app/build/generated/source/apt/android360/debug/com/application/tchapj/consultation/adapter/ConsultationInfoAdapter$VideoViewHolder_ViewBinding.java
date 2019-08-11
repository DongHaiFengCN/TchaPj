// Generated code from Butter Knife. Do not modify!
package com.application.tchapj.consultation.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.application.tchapj.R;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ConsultationInfoAdapter$VideoViewHolder_ViewBinding implements Unbinder {
  private ConsultationInfoAdapter.VideoViewHolder target;

  @UiThread
  public ConsultationInfoAdapter$VideoViewHolder_ViewBinding(ConsultationInfoAdapter.VideoViewHolder target, View source) {
    this.target = target;

    target.tv_title = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tv_title'", TextView.class);
    target.tv_name = Utils.findRequiredViewAsType(source, R.id.tv_name, "field 'tv_name'", TextView.class);
    target.tv_time = Utils.findRequiredViewAsType(source, R.id.tv_time, "field 'tv_time'", TextView.class);
    target.consultation_video_player = Utils.findRequiredViewAsType(source, R.id.consultation_video_player, "field 'consultation_video_player'", JCVideoPlayerStandard.class);
    target.tv_delete = Utils.findRequiredViewAsType(source, R.id.tv_delete, "field 'tv_delete'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ConsultationInfoAdapter.VideoViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tv_title = null;
    target.tv_name = null;
    target.tv_time = null;
    target.consultation_video_player = null;
    target.tv_delete = null;
  }
}
