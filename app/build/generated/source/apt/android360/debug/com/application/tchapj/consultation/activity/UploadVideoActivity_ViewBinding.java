// Generated code from Butter Knife. Do not modify!
package com.application.tchapj.consultation.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.application.tchapj.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class UploadVideoActivity_ViewBinding implements Unbinder {
  private UploadVideoActivity target;

  private View view2131755774;

  private View view2131755775;

  @UiThread
  public UploadVideoActivity_ViewBinding(UploadVideoActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public UploadVideoActivity_ViewBinding(final UploadVideoActivity target, View source) {
    this.target = target;

    View view;
    target.toolbarTitle = Utils.findRequiredViewAsType(source, R.id.toolbar_title, "field 'toolbarTitle'", TextView.class);
    target.toolbarMenuTitle = Utils.findRequiredViewAsType(source, R.id.toolbar_menu_title, "field 'toolbarMenuTitle'", TextView.class);
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    view = Utils.findRequiredView(source, R.id.upload_video_cancel_tv, "field 'uploadVideoCancelTv' and method 'onViewClicked'");
    target.uploadVideoCancelTv = Utils.castView(view, R.id.upload_video_cancel_tv, "field 'uploadVideoCancelTv'", TextView.class);
    view2131755774 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.upload_video_upload_tv, "field 'uploadVideoUploadTv' and method 'onViewClicked'");
    target.uploadVideoUploadTv = Utils.castView(view, R.id.upload_video_upload_tv, "field 'uploadVideoUploadTv'", TextView.class);
    view2131755775 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.uploadVideoContentEdt = Utils.findRequiredViewAsType(source, R.id.upload_video_content_edt, "field 'uploadVideoContentEdt'", EditText.class);
    target.uploadVideoTitleEdt = Utils.findRequiredViewAsType(source, R.id.upload_video_title_edt, "field 'uploadVideoTitleEdt'", EditText.class);
    target.uploadImgRv = Utils.findRequiredViewAsType(source, R.id.upload_img_rv, "field 'uploadImgRv'", RecyclerView.class);
    target.uploadVideoRv = Utils.findRequiredViewAsType(source, R.id.upload_video_rv, "field 'uploadVideoRv'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    UploadVideoActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbarTitle = null;
    target.toolbarMenuTitle = null;
    target.toolbar = null;
    target.uploadVideoCancelTv = null;
    target.uploadVideoUploadTv = null;
    target.uploadVideoContentEdt = null;
    target.uploadVideoTitleEdt = null;
    target.uploadImgRv = null;
    target.uploadVideoRv = null;

    view2131755774.setOnClickListener(null);
    view2131755774 = null;
    view2131755775.setOnClickListener(null);
    view2131755775 = null;
  }
}
