// Generated code from Butter Knife. Do not modify!
package com.application.tchapj.main.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.application.tchapj.R;
import com.application.tchapj.utils2.CircleImageView;
import com.application.tchapj.utils2.FlowGroupView;
import com.zhy.view.flowlayout.TagFlowLayout;
import java.lang.IllegalStateException;
import java.lang.Override;

public class PersonAdapter$ViewHolder_ViewBinding implements Unbinder {
  private PersonAdapter.ViewHolder target;

  @UiThread
  public PersonAdapter$ViewHolder_ViewBinding(PersonAdapter.ViewHolder target, View source) {
    this.target = target;

    target.ic_type = Utils.findRequiredViewAsType(source, R.id.ic_type, "field 'ic_type'", ImageView.class);
    target.tv_explain = Utils.findRequiredViewAsType(source, R.id.tv_explain, "field 'tv_explain'", TextView.class);
    target.tv_name = Utils.findRequiredViewAsType(source, R.id.tv_name, "field 'tv_name'", TextView.class);
    target.iv_head = Utils.findRequiredViewAsType(source, R.id.iv_head, "field 'iv_head'", CircleImageView.class);
    target.flow_view_group = Utils.findRequiredViewAsType(source, R.id.flow_view_group, "field 'flow_view_group'", FlowGroupView.class);
    target.zixunName = Utils.findRequiredViewAsType(source, R.id.zixunName, "field 'zixunName'", TextView.class);
    target.item_person = Utils.findRequiredViewAsType(source, R.id.item_person, "field 'item_person'", LinearLayout.class);
    target.flowLayout = Utils.findRequiredViewAsType(source, R.id.flowlayout, "field 'flowLayout'", TagFlowLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    PersonAdapter.ViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ic_type = null;
    target.tv_explain = null;
    target.tv_name = null;
    target.iv_head = null;
    target.flow_view_group = null;
    target.zixunName = null;
    target.item_person = null;
    target.flowLayout = null;
  }
}
