// Generated code from Butter Knife. Do not modify!
package com.application.tchapj.task.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.application.tchapj.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class UploadAuditActivity_ViewBinding implements Unbinder {
  private UploadAuditActivity target;

  private View view2131755768;

  private View view2131755509;

  @UiThread
  public UploadAuditActivity_ViewBinding(UploadAuditActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public UploadAuditActivity_ViewBinding(final UploadAuditActivity target, View source) {
    this.target = target;

    View view;
    target.uploadAudioImg = Utils.findRequiredViewAsType(source, R.id.uploadAudioImg, "field 'uploadAudioImg'", ImageView.class);
    target.uploadAudioTv = Utils.findRequiredViewAsType(source, R.id.uploadAudioTv, "field 'uploadAudioTv'", TextView.class);
    target.uploadAudioNameTv = Utils.findRequiredViewAsType(source, R.id.uploadAudioNameTv, "field 'uploadAudioNameTv'", TextView.class);
    target.uploadAudioCountDownValueTv = Utils.findRequiredViewAsType(source, R.id.uploadAudioCountDownValueTv, "field 'uploadAudioCountDownValueTv'", TextView.class);
    target.uploadAudioSubmitValueTv = Utils.findRequiredViewAsType(source, R.id.uploadAudioSubmitValueTv, "field 'uploadAudioSubmitValueTv'", TextView.class);
    target.uploadAudioContent1 = Utils.findRequiredViewAsType(source, R.id.uploadAudioContent1, "field 'uploadAudioContent1'", TextView.class);
    target.uploadAudioBtnTime = Utils.findRequiredViewAsType(source, R.id.uploadAudioBtnTime, "field 'uploadAudioBtnTime'", TextView.class);
    view = Utils.findRequiredView(source, R.id.uploadAudioBtn, "field 'uploadAudioBtn' and method 'onViewClicked'");
    target.uploadAudioBtn = Utils.castView(view, R.id.uploadAudioBtn, "field 'uploadAudioBtn'", Button.class);
    view2131755768 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.uploadAudioImg1 = Utils.findRequiredViewAsType(source, R.id.uploadAudioImg1, "field 'uploadAudioImg1'", ImageView.class);
    target.vido_rl = Utils.findRequiredViewAsType(source, R.id.vido_rl, "field 'vido_rl'", RecyclerView.class);
    target.activityDataLayout = Utils.findRequiredViewAsType(source, R.id.activity_data_cl, "field 'activityDataLayout'", ConstraintLayout.class);
    target.copywritingTv = Utils.findRequiredViewAsType(source, R.id.releasefDownloadValueTv1, "field 'copywritingTv'", TextView.class);
    target.saveImgRv = Utils.findRequiredViewAsType(source, R.id.releasefValueRv, "field 'saveImgRv'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.coypTv, "method 'onViewClicked'");
    view2131755509 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    UploadAuditActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.uploadAudioImg = null;
    target.uploadAudioTv = null;
    target.uploadAudioNameTv = null;
    target.uploadAudioCountDownValueTv = null;
    target.uploadAudioSubmitValueTv = null;
    target.uploadAudioContent1 = null;
    target.uploadAudioBtnTime = null;
    target.uploadAudioBtn = null;
    target.uploadAudioImg1 = null;
    target.vido_rl = null;
    target.activityDataLayout = null;
    target.copywritingTv = null;
    target.saveImgRv = null;

    view2131755768.setOnClickListener(null);
    view2131755768 = null;
    view2131755509.setOnClickListener(null);
    view2131755509 = null;
  }
}
