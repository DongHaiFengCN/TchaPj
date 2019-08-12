// Generated code from Butter Knife. Do not modify!
package com.application.tchapj.task.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.application.tchapj.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LeadTaskWechatActivity_ViewBinding implements Unbinder {
  private LeadTaskWechatActivity target;

  private View view2131755509;

  private View view2131755603;

  @UiThread
  public LeadTaskWechatActivity_ViewBinding(LeadTaskWechatActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public LeadTaskWechatActivity_ViewBinding(final LeadTaskWechatActivity target, View source) {
    this.target = target;

    View view;
    target.taskdyStateOff2 = Utils.findRequiredViewAsType(source, R.id.taskdyStateOff2, "field 'taskdyStateOff2'", ImageView.class);
    target.taskdyStateOff3 = Utils.findRequiredViewAsType(source, R.id.taskdyStateOff3, "field 'taskdyStateOff3'", ImageView.class);
    target.taskdyStateOff4 = Utils.findRequiredViewAsType(source, R.id.taskdyStateoff4, "field 'taskdyStateOff4'", ImageView.class);
    target.releasefImg = Utils.findRequiredViewAsType(source, R.id.releasefImg, "field 'releasefImg'", ImageView.class);
    target.friendCircleTv = Utils.findRequiredViewAsType(source, R.id.friendCircleTv, "field 'friendCircleTv'", TextView.class);
    target.releasefNameTv = Utils.findRequiredViewAsType(source, R.id.releasefNameTv, "field 'releasefNameTv'", TextView.class);
    target.activityTimeValueTv = Utils.findRequiredViewAsType(source, R.id.activityTimeValueTv, "field 'activityTimeValueTv'", TextView.class);
    target.releasefMoneyValueTv = Utils.findRequiredViewAsType(source, R.id.releasefMoneyValueTv, "field 'releasefMoneyValueTv'", TextView.class);
    target.releasefDownloadValueTv1 = Utils.findRequiredViewAsType(source, R.id.releasefDownloadValueTv1, "field 'releasefDownloadValueTv1'", TextView.class);
    view = Utils.findRequiredView(source, R.id.coypTv, "field 'coypTv' and method 'onViewClicked'");
    target.coypTv = Utils.castView(view, R.id.coypTv, "field 'coypTv'", TextView.class);
    view2131755509 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.releasefValueRv = Utils.findRequiredViewAsType(source, R.id.releasefValueRv, "field 'releasefValueRv'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.lead_task_wechat_submit_tv, "field 'submitTv' and method 'onViewClicked'");
    target.submitTv = Utils.castView(view, R.id.lead_task_wechat_submit_tv, "field 'submitTv'", TextView.class);
    view2131755603 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.friendCircleImg = Utils.findRequiredViewAsType(source, R.id.friendCircleImg, "field 'friendCircleImg'", ImageView.class);
    target.releasefCopywritingTv = Utils.findRequiredViewAsType(source, R.id.releasefCopywritingTv, "field 'releasefCopywritingTv'", TextView.class);
    target.releasefMapTv = Utils.findRequiredViewAsType(source, R.id.releasefMapTv, "field 'releasefMapTv'", TextView.class);
    target.activityRequireTipTv = Utils.findRequiredViewAsType(source, R.id.lead_task_wechat_task_requirement_tip_tv, "field 'activityRequireTipTv'", TextView.class);
    target.activityGuideTipTv = Utils.findRequiredViewAsType(source, R.id.lead_task_wechat_activity_guide_tip_tv, "field 'activityGuideTipTv'", TextView.class);
    target.activityRequireTv = Utils.findRequiredViewAsType(source, R.id.lead_task_wechat_task_requirement_tv, "field 'activityRequireTv'", TextView.class);
    target.activityGuideTv = Utils.findRequiredViewAsType(source, R.id.lead_task_wechat_activity_guide_tv, "field 'activityGuideTv'", TextView.class);
    target.dottedView = Utils.findRequiredView(source, R.id.lead_task_wechat_task_requirement_dotted_view, "field 'dottedView'");
    target.submitDataLl = Utils.findRequiredViewAsType(source, R.id.lead_task_wechat_submit_data_ll, "field 'submitDataLl'", LinearLayout.class);
    target.taskStatusTv = Utils.findRequiredViewAsType(source, R.id.lead_task_wechat_task_status_tv, "field 'taskStatusTv'", TextView.class);
    target.screenshotRv = Utils.findRequiredViewAsType(source, R.id.lead_task_wechat_screenshot_rv, "field 'screenshotRv'", RecyclerView.class);
    target.countDownSubmitTv = Utils.findRequiredViewAsType(source, R.id.lead_task_wechat_submit_tv_count_down, "field 'countDownSubmitTv'", TextView.class);
    target.wechatAuditLl = Utils.findRequiredViewAsType(source, R.id.lead_task_wechat_audit_ll, "field 'wechatAuditLl'", LinearLayout.class);
    target.wechatAuditTv = Utils.findRequiredViewAsType(source, R.id.lead_task_wechat_audit_tv, "field 'wechatAuditTv'", TextView.class);
    target.auditRefuseTv = Utils.findRequiredViewAsType(source, R.id.lead_task_wechat_audit_refuse_tv, "field 'auditRefuseTv'", TextView.class);
    target.taskRemindTv = Utils.findRequiredViewAsType(source, R.id.lead_task_wechat_task_remind_tv, "field 'taskRemindTv'", TextView.class);
    target.autoSaveTv = Utils.findRequiredViewAsType(source, R.id.lead_task_wechat_auto_save_tv, "field 'autoSaveTv'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    LeadTaskWechatActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.taskdyStateOff2 = null;
    target.taskdyStateOff3 = null;
    target.taskdyStateOff4 = null;
    target.releasefImg = null;
    target.friendCircleTv = null;
    target.releasefNameTv = null;
    target.activityTimeValueTv = null;
    target.releasefMoneyValueTv = null;
    target.releasefDownloadValueTv1 = null;
    target.coypTv = null;
    target.releasefValueRv = null;
    target.submitTv = null;
    target.friendCircleImg = null;
    target.releasefCopywritingTv = null;
    target.releasefMapTv = null;
    target.activityRequireTipTv = null;
    target.activityGuideTipTv = null;
    target.activityRequireTv = null;
    target.activityGuideTv = null;
    target.dottedView = null;
    target.submitDataLl = null;
    target.taskStatusTv = null;
    target.screenshotRv = null;
    target.countDownSubmitTv = null;
    target.wechatAuditLl = null;
    target.wechatAuditTv = null;
    target.auditRefuseTv = null;
    target.taskRemindTv = null;
    target.autoSaveTv = null;

    view2131755509.setOnClickListener(null);
    view2131755509 = null;
    view2131755603.setOnClickListener(null);
    view2131755603 = null;
  }
}
