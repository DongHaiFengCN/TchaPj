// Generated code from Butter Knife. Do not modify!
package com.application.tchapj.my.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.application.tchapj.R;
import com.application.tchapj.utils2.CircleImageView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MyFragment_ViewBinding implements Unbinder {
  private MyFragment target;

  private View view2131755950;

  private View view2131755951;

  private View view2131755952;

  private View view2131755953;

  private View view2131755954;

  private View view2131755955;

  @UiThread
  public MyFragment_ViewBinding(final MyFragment target, View source) {
    this.target = target;

    View view;
    target.my_top_iv = Utils.findRequiredViewAsType(source, R.id.my_top_iv, "field 'my_top_iv'", CircleImageView.class);
    target.my_top_name = Utils.findRequiredViewAsType(source, R.id.my_top_name, "field 'my_top_name'", TextView.class);
    target.my_top_sex = Utils.findRequiredViewAsType(source, R.id.my_top_sex, "field 'my_top_sex'", ImageView.class);
    target.my_top_sex2 = Utils.findRequiredViewAsType(source, R.id.my_top_sex2, "field 'my_top_sex2'", ImageView.class);
    target.my_logon_tv = Utils.findRequiredViewAsType(source, R.id.my_logon_tv, "field 'my_logon_tv'", TextView.class);
    target.my_jiben_rl = Utils.findRequiredViewAsType(source, R.id.my_jiben_rl, "field 'my_jiben_rl'", RelativeLayout.class);
    target.my_whbyh_rl = Utils.findRequiredViewAsType(source, R.id.my_whbyh_rl, "field 'my_whbyh_rl'", RelativeLayout.class);
    target.my_shenfen_rl = Utils.findRequiredViewAsType(source, R.id.my_shenfen_rl, "field 'my_shenfen_rl'", RelativeLayout.class);
    target.my_renzheng_rl = Utils.findRequiredViewAsType(source, R.id.my_renzheng_rl, "field 'my_renzheng_rl'", RelativeLayout.class);
    target.my_xiaohb_rl = Utils.findRequiredViewAsType(source, R.id.my_xiaohb_rl, "field 'my_xiaohb_rl'", RelativeLayout.class);
    target.my_system_rl = Utils.findRequiredViewAsType(source, R.id.my_system_rl, "field 'my_system_rl'", RelativeLayout.class);
    target.my_contact_rl = Utils.findRequiredViewAsType(source, R.id.my_contact_rl, "field 'my_contact_rl'", RelativeLayout.class);
    target.my_jiben_iv = Utils.findRequiredViewAsType(source, R.id.my_jiben_iv, "field 'my_jiben_iv'", ImageView.class);
    target.my_renzheng_iv = Utils.findRequiredViewAsType(source, R.id.my_renzheng_iv, "field 'my_renzheng_iv'", ImageView.class);
    target.my_xiaohb_iv = Utils.findRequiredViewAsType(source, R.id.my_xiaohb_iv, "field 'my_xiaohb_iv'", ImageView.class);
    target.my_system_iv = Utils.findRequiredViewAsType(source, R.id.my_system_iv, "field 'my_system_iv'", ImageView.class);
    target.my_contact = Utils.findRequiredViewAsType(source, R.id.my_contact, "field 'my_contact'", ImageView.class);
    target.fragment_my_attentions_fans_ll = Utils.findRequiredViewAsType(source, R.id.fragment_my_attentions_fans_ll, "field 'fragment_my_attentions_fans_ll'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.fragment_my_attentions_tv, "field 'fragment_my_attentions_tv' and method 'onViewClicked'");
    target.fragment_my_attentions_tv = Utils.castView(view, R.id.fragment_my_attentions_tv, "field 'fragment_my_attentions_tv'", TextView.class);
    view2131755950 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.fragment_my_fans_tv, "field 'fragment_my_fans_tv' and method 'onViewClicked'");
    target.fragment_my_fans_tv = Utils.castView(view, R.id.fragment_my_fans_tv, "field 'fragment_my_fans_tv'", TextView.class);
    view2131755951 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.fragment_my_wallet_ll, "method 'onViewClicked'");
    view2131755952 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.fragment_my_content_manager_ll, "method 'onViewClicked'");
    view2131755953 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.fragment_my_content_analysis_ll, "method 'onViewClicked'");
    view2131755954 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.fragment_my_task_analysis_ll, "method 'onViewClicked'");
    view2131755955 = view;
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
    MyFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.my_top_iv = null;
    target.my_top_name = null;
    target.my_top_sex = null;
    target.my_top_sex2 = null;
    target.my_logon_tv = null;
    target.my_jiben_rl = null;
    target.my_whbyh_rl = null;
    target.my_shenfen_rl = null;
    target.my_renzheng_rl = null;
    target.my_xiaohb_rl = null;
    target.my_system_rl = null;
    target.my_contact_rl = null;
    target.my_jiben_iv = null;
    target.my_renzheng_iv = null;
    target.my_xiaohb_iv = null;
    target.my_system_iv = null;
    target.my_contact = null;
    target.fragment_my_attentions_fans_ll = null;
    target.fragment_my_attentions_tv = null;
    target.fragment_my_fans_tv = null;

    view2131755950.setOnClickListener(null);
    view2131755950 = null;
    view2131755951.setOnClickListener(null);
    view2131755951 = null;
    view2131755952.setOnClickListener(null);
    view2131755952 = null;
    view2131755953.setOnClickListener(null);
    view2131755953 = null;
    view2131755954.setOnClickListener(null);
    view2131755954 = null;
    view2131755955.setOnClickListener(null);
    view2131755955 = null;
  }
}
