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
import java.lang.IllegalStateException;
import java.lang.Override;

public class MingPeopleAdapter$ViewHolder_ViewBinding implements Unbinder {
  private MingPeopleAdapter.ViewHolder target;

  @UiThread
  public MingPeopleAdapter$ViewHolder_ViewBinding(MingPeopleAdapter.ViewHolder target, View source) {
    this.target = target;

    target.ll_mingpeople = Utils.findRequiredViewAsType(source, R.id.ll_mingpeople, "field 'll_mingpeople'", LinearLayout.class);
    target.iv_lv = Utils.findRequiredViewAsType(source, R.id.iv_lv, "field 'iv_lv'", ImageView.class);
    target.tv_ph = Utils.findRequiredViewAsType(source, R.id.tv_ph, "field 'tv_ph'", TextView.class);
    target.iv_portrait = Utils.findRequiredViewAsType(source, R.id.iv_portrait, "field 'iv_portrait'", CircleImageView.class);
    target.tv_name = Utils.findRequiredViewAsType(source, R.id.tv_name, "field 'tv_name'", TextView.class);
    target.tv_velue = Utils.findRequiredViewAsType(source, R.id.tv_velue, "field 'tv_velue'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MingPeopleAdapter.ViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ll_mingpeople = null;
    target.iv_lv = null;
    target.tv_ph = null;
    target.iv_portrait = null;
    target.tv_name = null;
    target.tv_velue = null;
  }
}
