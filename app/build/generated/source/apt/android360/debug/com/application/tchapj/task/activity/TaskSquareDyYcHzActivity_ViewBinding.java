// Generated code from Butter Knife. Do not modify!
package com.application.tchapj.task.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.application.tchapj.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TaskSquareDyYcHzActivity_ViewBinding implements Unbinder {
  private TaskSquareDyYcHzActivity target;

  @UiThread
  public TaskSquareDyYcHzActivity_ViewBinding(TaskSquareDyYcHzActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public TaskSquareDyYcHzActivity_ViewBinding(TaskSquareDyYcHzActivity target, View source) {
    this.target = target;

    target.uploadAudioStateLine = Utils.findRequiredViewAsType(source, R.id.uploadAudioStateLine, "field 'uploadAudioStateLine'", ImageView.class);
    target.uploadAudioStateOn1 = Utils.findRequiredViewAsType(source, R.id.uploadAudioStateOn1, "field 'uploadAudioStateOn1'", ImageView.class);
    target.orginalstateLine = Utils.findRequiredViewAsType(source, R.id.orginalstateLine, "field 'orginalstateLine'", ImageView.class);
    target.releasefStateOn1 = Utils.findRequiredViewAsType(source, R.id.releasefStateOn1, "field 'releasefStateOn1'", ImageView.class);
    target.releasefStateOff4 = Utils.findRequiredViewAsType(source, R.id.releasefStateOff4, "field 'releasefStateOff4'", ImageView.class);
    target.releasefStateOff2 = Utils.findRequiredViewAsType(source, R.id.releasefStateOff2, "field 'releasefStateOff2'", ImageView.class);
    target.releasefTv1 = Utils.findRequiredViewAsType(source, R.id.releasefTv1, "field 'releasefTv1'", TextView.class);
    target.originalfTv2 = Utils.findRequiredViewAsType(source, R.id.originalfTv2, "field 'originalfTv2'", TextView.class);
    target.originalfTv3 = Utils.findRequiredViewAsType(source, R.id.originalfTv3, "field 'originalfTv3'", TextView.class);
    target.uploadAudioBg = Utils.findRequiredViewAsType(source, R.id.uploadAudioBg, "field 'uploadAudioBg'", ImageView.class);
    target.uploadAudioImg = Utils.findRequiredViewAsType(source, R.id.uploadAudioImg, "field 'uploadAudioImg'", ImageView.class);
    target.uploadAudioImg1 = Utils.findRequiredViewAsType(source, R.id.uploadAudioImg1, "field 'uploadAudioImg1'", ImageView.class);
    target.uploadAudioTv = Utils.findRequiredViewAsType(source, R.id.uploadAudioTv, "field 'uploadAudioTv'", TextView.class);
    target.uploadAudioNameTv = Utils.findRequiredViewAsType(source, R.id.uploadAudioNameTv, "field 'uploadAudioNameTv'", TextView.class);
    target.uploadAudioCountDownTv = Utils.findRequiredViewAsType(source, R.id.uploadAudioCountDownTv, "field 'uploadAudioCountDownTv'", TextView.class);
    target.uploadAudioCountDownValueTv = Utils.findRequiredViewAsType(source, R.id.uploadAudioCountDownValueTv, "field 'uploadAudioCountDownValueTv'", TextView.class);
    target.uploadAudioBg1 = Utils.findRequiredViewAsType(source, R.id.uploadAudioBg1, "field 'uploadAudioBg1'", ImageView.class);
    target.uploadAudioImg2 = Utils.findRequiredViewAsType(source, R.id.uploadAudioImg2, "field 'uploadAudioImg2'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    TaskSquareDyYcHzActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.uploadAudioStateLine = null;
    target.uploadAudioStateOn1 = null;
    target.orginalstateLine = null;
    target.releasefStateOn1 = null;
    target.releasefStateOff4 = null;
    target.releasefStateOff2 = null;
    target.releasefTv1 = null;
    target.originalfTv2 = null;
    target.originalfTv3 = null;
    target.uploadAudioBg = null;
    target.uploadAudioImg = null;
    target.uploadAudioImg1 = null;
    target.uploadAudioTv = null;
    target.uploadAudioNameTv = null;
    target.uploadAudioCountDownTv = null;
    target.uploadAudioCountDownValueTv = null;
    target.uploadAudioBg1 = null;
    target.uploadAudioImg2 = null;
  }
}
