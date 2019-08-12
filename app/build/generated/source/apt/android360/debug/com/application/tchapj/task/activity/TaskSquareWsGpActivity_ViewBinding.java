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

public class TaskSquareWsGpActivity_ViewBinding implements Unbinder {
  private TaskSquareWsGpActivity target;

  private View view2131755512;

  private View view2131755514;

  @UiThread
  public TaskSquareWsGpActivity_ViewBinding(TaskSquareWsGpActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public TaskSquareWsGpActivity_ViewBinding(final TaskSquareWsGpActivity target, View source) {
    this.target = target;

    View view;
    target.taskdygenpaiStateLine = Utils.findRequiredViewAsType(source, R.id.taskdygenpaiStateLine, "field 'taskdygenpaiStateLine'", ImageView.class);
    target.taskdyStateOn1 = Utils.findRequiredViewAsType(source, R.id.taskdyStateOn1, "field 'taskdyStateOn1'", ImageView.class);
    target.taskdyStateoff4 = Utils.findRequiredViewAsType(source, R.id.taskdyStateoff4, "field 'taskdyStateoff4'", ImageView.class);
    target.taskdyStateOff2 = Utils.findRequiredViewAsType(source, R.id.taskdyStateOff2, "field 'taskdyStateOff2'", ImageView.class);
    target.taskdyStateOff3 = Utils.findRequiredViewAsType(source, R.id.taskdyStateOff3, "field 'taskdyStateOff3'", ImageView.class);
    target.taskdyfTv1 = Utils.findRequiredViewAsType(source, R.id.taskdyfTv1, "field 'taskdyfTv1'", TextView.class);
    target.taskdyfTv2 = Utils.findRequiredViewAsType(source, R.id.taskdyfTv2, "field 'taskdyfTv2'", TextView.class);
    target.taskdyfTv3 = Utils.findRequiredViewAsType(source, R.id.taskdyfTv3, "field 'taskdyfTv3'", TextView.class);
    target.taskdyfTv4 = Utils.findRequiredViewAsType(source, R.id.taskdyfTv4, "field 'taskdyfTv4'", TextView.class);
    target.releasefBg = Utils.findRequiredViewAsType(source, R.id.releasefBg, "field 'releasefBg'", ImageView.class);
    target.taskdyfImg = Utils.findRequiredViewAsType(source, R.id.taskdyfImg, "field 'taskdyfImg'", ImageView.class);
    target.taskdyCircleImg = Utils.findRequiredViewAsType(source, R.id.taskdyCircleImg, "field 'taskdyCircleImg'", ImageView.class);
    target.taskdyCircleTv = Utils.findRequiredViewAsType(source, R.id.taskdyCircleTv, "field 'taskdyCircleTv'", TextView.class);
    target.taskdyfNameTv = Utils.findRequiredViewAsType(source, R.id.taskdyfNameTv, "field 'taskdyfNameTv'", TextView.class);
    target.taskdyfActivitysTv = Utils.findRequiredViewAsType(source, R.id.taskdyfActivitysTv, "field 'taskdyfActivitysTv'", TextView.class);
    target.releasefActivitysValueTv1 = Utils.findRequiredViewAsType(source, R.id.releasefActivitysValueTv1, "field 'releasefActivitysValueTv1'", TextView.class);
    target.releasefBg4 = Utils.findRequiredViewAsType(source, R.id.releasefBg4, "field 'releasefBg4'", ImageView.class);
    target.releasefActivitysReTv = Utils.findRequiredViewAsType(source, R.id.releasefActivitysReTv, "field 'releasefActivitysReTv'", TextView.class);
    target.releasefActivitysReValueTv1 = Utils.findRequiredViewAsType(source, R.id.releasefActivitysReValueTv1, "field 'releasefActivitysReValueTv1'", TextView.class);
    target.releasefBg5 = Utils.findRequiredViewAsType(source, R.id.releasefBg5, "field 'releasefBg5'", ImageView.class);
    target.releasefBountyTv = Utils.findRequiredViewAsType(source, R.id.releasefBountyTv, "field 'releasefBountyTv'", TextView.class);
    target.releasefMoneyImg = Utils.findRequiredViewAsType(source, R.id.releasefMoneyImg, "field 'releasefMoneyImg'", ImageView.class);
    target.releasefMoneyValueTv = Utils.findRequiredViewAsType(source, R.id.releasefMoneyValueTv, "field 'releasefMoneyValueTv'", TextView.class);
    target.releasefBg6 = Utils.findRequiredViewAsType(source, R.id.releasefBg6, "field 'releasefBg6'", ImageView.class);
    target.releasefCountdownTv = Utils.findRequiredViewAsType(source, R.id.releasefCountdownTv, "field 'releasefCountdownTv'", TextView.class);
    target.releasefCountdownValueTv1 = Utils.findRequiredViewAsType(source, R.id.releasefCountdownValueTv1, "field 'releasefCountdownValueTv1'", TextView.class);
    target.releasefDownloadTv = Utils.findRequiredViewAsType(source, R.id.releasefDownloadTv, "field 'releasefDownloadTv'", TextView.class);
    view = Utils.findRequiredView(source, R.id.releasefValueRv, "field 'releasefValueRv' and method 'onViewClicked'");
    target.releasefValueRv = Utils.castView(view, R.id.releasefValueRv, "field 'releasefValueRv'", ImageView.class);
    view2131755512 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.hairFriendsBtn, "field 'hairFriendsBtn' and method 'onViewClicked'");
    target.hairFriendsBtn = Utils.castView(view, R.id.hairFriendsBtn, "field 'hairFriendsBtn'", Button.class);
    view2131755514 = view;
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
    TaskSquareWsGpActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.taskdygenpaiStateLine = null;
    target.taskdyStateOn1 = null;
    target.taskdyStateoff4 = null;
    target.taskdyStateOff2 = null;
    target.taskdyStateOff3 = null;
    target.taskdyfTv1 = null;
    target.taskdyfTv2 = null;
    target.taskdyfTv3 = null;
    target.taskdyfTv4 = null;
    target.releasefBg = null;
    target.taskdyfImg = null;
    target.taskdyCircleImg = null;
    target.taskdyCircleTv = null;
    target.taskdyfNameTv = null;
    target.taskdyfActivitysTv = null;
    target.releasefActivitysValueTv1 = null;
    target.releasefBg4 = null;
    target.releasefActivitysReTv = null;
    target.releasefActivitysReValueTv1 = null;
    target.releasefBg5 = null;
    target.releasefBountyTv = null;
    target.releasefMoneyImg = null;
    target.releasefMoneyValueTv = null;
    target.releasefBg6 = null;
    target.releasefCountdownTv = null;
    target.releasefCountdownValueTv1 = null;
    target.releasefDownloadTv = null;
    target.releasefValueRv = null;
    target.hairFriendsBtn = null;
    target.ScrollView = null;

    view2131755512.setOnClickListener(null);
    view2131755512 = null;
    view2131755514.setOnClickListener(null);
    view2131755514 = null;
  }
}
