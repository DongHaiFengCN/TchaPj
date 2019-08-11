// Generated code from Butter Knife. Do not modify!
package com.application.tchapj.consultation.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.application.tchapj.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ConsultationInfoFragment_ViewBinding implements Unbinder {
  private ConsultationInfoFragment target;

  @UiThread
  public ConsultationInfoFragment_ViewBinding(ConsultationInfoFragment target, View source) {
    this.target = target;

    target.bodyLayout = Utils.findRequiredViewAsType(source, R.id.bodyLayout, "field 'bodyLayout'", RelativeLayout.class);
    target.consultation_recyclerview = Utils.findRequiredViewAsType(source, R.id.consultation_recyclerview, "field 'consultation_recyclerview'", RecyclerView.class);
    target.consultation_refreshLayout = Utils.findRequiredViewAsType(source, R.id.consultation_refreshLayout, "field 'consultation_refreshLayout'", SmartRefreshLayout.class);
    target.editTextBodyLl = Utils.findRequiredViewAsType(source, R.id.editTextBodyLl, "field 'editTextBodyLl'", LinearLayout.class);
    target.comment_et = Utils.findRequiredViewAsType(source, R.id.comment_et, "field 'comment_et'", EditText.class);
    target.comment_send = Utils.findRequiredViewAsType(source, R.id.comment_send, "field 'comment_send'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ConsultationInfoFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.bodyLayout = null;
    target.consultation_recyclerview = null;
    target.consultation_refreshLayout = null;
    target.editTextBodyLl = null;
    target.comment_et = null;
    target.comment_send = null;
  }
}
