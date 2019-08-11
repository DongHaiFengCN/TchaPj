// Generated code from Butter Knife. Do not modify!
package com.application.tchapj.task.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
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

public class FriendReleaseActivity_ViewBinding implements Unbinder {
  private FriendReleaseActivity target;

  private View view2131755504;

  private View view2131755509;

  @UiThread
  public FriendReleaseActivity_ViewBinding(FriendReleaseActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public FriendReleaseActivity_ViewBinding(final FriendReleaseActivity target, View source) {
    this.target = target;

    View view;
    target.releasefImg = Utils.findRequiredViewAsType(source, R.id.releasefImg, "field 'releasefImg'", ImageView.class);
    target.friendCircleTv = Utils.findRequiredViewAsType(source, R.id.friendCircleTv, "field 'friendCircleTv'", TextView.class);
    target.releasefNameTv = Utils.findRequiredViewAsType(source, R.id.releasefNameTv, "field 'releasefNameTv'", TextView.class);
    target.activityTimeValueTv = Utils.findRequiredViewAsType(source, R.id.activityTimeValueTv, "field 'activityTimeValueTv'", TextView.class);
    target.releasefActivitysValueTv1 = Utils.findRequiredViewAsType(source, R.id.releasefActivitysValueTv1, "field 'releasefActivitysValueTv1'", TextView.class);
    target.releasefMoneyValueTv = Utils.findRequiredViewAsType(source, R.id.releasefMoneyValueTv, "field 'releasefMoneyValueTv'", TextView.class);
    target.releasefDownloadValueTv1 = Utils.findRequiredViewAsType(source, R.id.releasefDownloadValueTv1, "field 'releasefDownloadValueTv1'", TextView.class);
    view = Utils.findRequiredView(source, R.id.coypTv, "field 'coypTv' and method 'onViewClicked'");
    target.coypTv = Utils.castView(view, R.id.coypTv, "field 'coypTv'", TextView.class);
    view2131755504 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.releasefValueRv = Utils.findRequiredViewAsType(source, R.id.releasefValueRv, "field 'releasefValueRv'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.hairFriendsBtn, "field 'hairFriendsBtn' and method 'onViewClicked'");
    target.hairFriendsBtn = Utils.castView(view, R.id.hairFriendsBtn, "field 'hairFriendsBtn'", Button.class);
    view2131755509 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.friendCircleImg = Utils.findRequiredViewAsType(source, R.id.friendCircleImg, "field 'friendCircleImg'", ImageView.class);
    target.releasefCopywritingTv = Utils.findRequiredViewAsType(source, R.id.releasefCopywritingTv, "field 'releasefCopywritingTv'", TextView.class);
    target.releasefMapTv = Utils.findRequiredViewAsType(source, R.id.releasefMapTv, "field 'releasefMapTv'", TextView.class);
    target.releasefContentTv = Utils.findRequiredViewAsType(source, R.id.releasefContentTv, "field 'releasefContentTv'", TextView.class);
    target.requireTv = Utils.findRequiredViewAsType(source, R.id.task_square_info_requirecontent_tv, "field 'requireTv'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    FriendReleaseActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.releasefImg = null;
    target.friendCircleTv = null;
    target.releasefNameTv = null;
    target.activityTimeValueTv = null;
    target.releasefActivitysValueTv1 = null;
    target.releasefMoneyValueTv = null;
    target.releasefDownloadValueTv1 = null;
    target.coypTv = null;
    target.releasefValueRv = null;
    target.hairFriendsBtn = null;
    target.friendCircleImg = null;
    target.releasefCopywritingTv = null;
    target.releasefMapTv = null;
    target.releasefContentTv = null;
    target.requireTv = null;

    view2131755504.setOnClickListener(null);
    view2131755504 = null;
    view2131755509.setOnClickListener(null);
    view2131755509 = null;
  }
}
