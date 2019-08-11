// Generated code from Butter Knife. Do not modify!
package com.application.tchapj.task.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.NestedScrollView;
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

public class TaskSquareDyYcActivity_ViewBinding implements Unbinder {
  private TaskSquareDyYcActivity target;

  private View view2131755509;

  @UiThread
  public TaskSquareDyYcActivity_ViewBinding(TaskSquareDyYcActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public TaskSquareDyYcActivity_ViewBinding(final TaskSquareDyYcActivity target, View source) {
    this.target = target;

    View view;
    target.orginalstateLine = Utils.findRequiredViewAsType(source, R.id.orginalstateLine, "field 'orginalstateLine'", ImageView.class);
    target.releasefStateOn1 = Utils.findRequiredViewAsType(source, R.id.releasefStateOn1, "field 'releasefStateOn1'", ImageView.class);
    target.releasefStateOff4 = Utils.findRequiredViewAsType(source, R.id.releasefStateOff4, "field 'releasefStateOff4'", ImageView.class);
    target.releasefStateOff2 = Utils.findRequiredViewAsType(source, R.id.releasefStateOff2, "field 'releasefStateOff2'", ImageView.class);
    target.releasefTv1 = Utils.findRequiredViewAsType(source, R.id.releasefTv1, "field 'releasefTv1'", TextView.class);
    target.originalfTv2 = Utils.findRequiredViewAsType(source, R.id.originalfTv2, "field 'originalfTv2'", TextView.class);
    target.originalfTv3 = Utils.findRequiredViewAsType(source, R.id.originalfTv3, "field 'originalfTv3'", TextView.class);
    target.releasefBg = Utils.findRequiredViewAsType(source, R.id.releasefBg, "field 'releasefBg'", ImageView.class);
    target.originalimg = Utils.findRequiredViewAsType(source, R.id.originalimg, "field 'originalimg'", ImageView.class);
    target.friendCircleImg = Utils.findRequiredViewAsType(source, R.id.friendCircleImg, "field 'friendCircleImg'", ImageView.class);
    target.friendCircleTv = Utils.findRequiredViewAsType(source, R.id.friendCircleTv, "field 'friendCircleTv'", TextView.class);
    target.releasefNameTv = Utils.findRequiredViewAsType(source, R.id.releasefNameTv, "field 'releasefNameTv'", TextView.class);
    target.activityTimeTv = Utils.findRequiredViewAsType(source, R.id.activityTimeTv, "field 'activityTimeTv'", TextView.class);
    target.activityTimeValueTv = Utils.findRequiredViewAsType(source, R.id.activityTimeValueTv, "field 'activityTimeValueTv'", TextView.class);
    target.releasefBg1 = Utils.findRequiredViewAsType(source, R.id.releasefBg1, "field 'releasefBg1'", ImageView.class);
    target.releasefActivitysReTv = Utils.findRequiredViewAsType(source, R.id.releasefActivitysReTv, "field 'releasefActivitysReTv'", TextView.class);
    target.releasefActivitysReValueTv1 = Utils.findRequiredViewAsType(source, R.id.releasefActivitysReValueTv1, "field 'releasefActivitysReValueTv1'", TextView.class);
    target.releasefBg5 = Utils.findRequiredViewAsType(source, R.id.releasefBg5, "field 'releasefBg5'", ImageView.class);
    target.releasefBountyTv = Utils.findRequiredViewAsType(source, R.id.releasefBountyTv, "field 'releasefBountyTv'", TextView.class);
    target.releasefMoneyImg = Utils.findRequiredViewAsType(source, R.id.releasefMoneyImg, "field 'releasefMoneyImg'", ImageView.class);
    target.releasefMoneyValueTv = Utils.findRequiredViewAsType(source, R.id.releasefMoneyValueTv, "field 'releasefMoneyValueTv'", TextView.class);
    view = Utils.findRequiredView(source, R.id.hairFriendsBtn, "field 'hairFriendsBtn' and method 'onViewClicked'");
    target.hairFriendsBtn = Utils.castView(view, R.id.hairFriendsBtn, "field 'hairFriendsBtn'", Button.class);
    view2131755509 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.ScrollView = Utils.findRequiredViewAsType(source, R.id.ScrollView, "field 'ScrollView'", NestedScrollView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    TaskSquareDyYcActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.orginalstateLine = null;
    target.releasefStateOn1 = null;
    target.releasefStateOff4 = null;
    target.releasefStateOff2 = null;
    target.releasefTv1 = null;
    target.originalfTv2 = null;
    target.originalfTv3 = null;
    target.releasefBg = null;
    target.originalimg = null;
    target.friendCircleImg = null;
    target.friendCircleTv = null;
    target.releasefNameTv = null;
    target.activityTimeTv = null;
    target.activityTimeValueTv = null;
    target.releasefBg1 = null;
    target.releasefActivitysReTv = null;
    target.releasefActivitysReValueTv1 = null;
    target.releasefBg5 = null;
    target.releasefBountyTv = null;
    target.releasefMoneyImg = null;
    target.releasefMoneyValueTv = null;
    target.hairFriendsBtn = null;
    target.ScrollView = null;

    view2131755509.setOnClickListener(null);
    view2131755509 = null;
  }
}
