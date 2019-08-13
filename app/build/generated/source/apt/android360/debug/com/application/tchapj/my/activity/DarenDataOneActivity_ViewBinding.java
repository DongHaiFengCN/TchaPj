// Generated code from Butter Knife. Do not modify!
package com.application.tchapj.my.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.application.tchapj.R;
import com.application.tchapj.widiget.FlowTagDarenLayout;
import com.application.tchapj.widiget.FlowTagLayout;
import com.application.tchapj.widiget.MustWriteLinearLayout;
import java.lang.IllegalStateException;
import java.lang.Override;

public class DarenDataOneActivity_ViewBinding implements Unbinder {
  private DarenDataOneActivity target;

  private View view2131756344;

  @UiThread
  public DarenDataOneActivity_ViewBinding(DarenDataOneActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public DarenDataOneActivity_ViewBinding(final DarenDataOneActivity target, View source) {
    this.target = target;

    View view;
    target.headImgRv = Utils.findRequiredViewAsType(source, R.id.daren_one_head_img_rv, "field 'headImgRv'", RecyclerView.class);
    target.darendataone_media_resources_rv = Utils.findRequiredViewAsType(source, R.id.darendataone_media_resources_rv, "field 'darendataone_media_resources_rv'", RecyclerView.class);
    target.daren_one_name_et = Utils.findRequiredViewAsType(source, R.id.daren_one_name_et, "field 'daren_one_name_et'", EditText.class);
    target.daren_one_jieshao_et = Utils.findRequiredViewAsType(source, R.id.daren_one_jieshao_et, "field 'daren_one_jieshao_et'", EditText.class);
    target.darendata_rg = Utils.findRequiredViewAsType(source, R.id.darendata_rg, "field 'darendata_rg'", RadioGroup.class);
    target.darendata_rb_nan = Utils.findRequiredViewAsType(source, R.id.darendata_rb_nan, "field 'darendata_rb_nan'", RadioButton.class);
    target.darendata_rb_nv = Utils.findRequiredViewAsType(source, R.id.darendata_rb_nv, "field 'darendata_rb_nv'", RadioButton.class);
    target.daren_one_realname_et = Utils.findRequiredViewAsType(source, R.id.daren_one_realname_et, "field 'daren_one_realname_et'", EditText.class);
    target.daren_one_sfz_et = Utils.findRequiredViewAsType(source, R.id.daren_one_sfz_et, "field 'daren_one_sfz_et'", EditText.class);
    target.daren_fl = Utils.findRequiredViewAsType(source, R.id.daren_fl, "field 'daren_fl'", FlowTagDarenLayout.class);
    target.darenResourcesFlowTagl = Utils.findRequiredViewAsType(source, R.id.darendataone_media_resources_fl, "field 'darenResourcesFlowTagl'", FlowTagLayout.class);
    target.daren_one_next = Utils.findRequiredViewAsType(source, R.id.daren_one_next, "field 'daren_one_next'", Button.class);
    target.darendataone_media_resources_price_et = Utils.findRequiredViewAsType(source, R.id.darendataone_media_resources_price_et, "field 'darendataone_media_resources_price_et'", EditText.class);
    target.permanentCityNameTv = Utils.findRequiredViewAsType(source, R.id.permanent_address_city_name_tv, "field 'permanentCityNameTv'", TextView.class);
    target.writeLinearLayout = Utils.findRequiredViewAsType(source, R.id.writeLl, "field 'writeLinearLayout'", MustWriteLinearLayout.class);
    target.inviteCodeEdt = Utils.findRequiredViewAsType(source, R.id.daren_one_invite_et, "field 'inviteCodeEdt'", EditText.class);
    target.darendataone_media_resources_tip_tv = Utils.findRequiredViewAsType(source, R.id.darendataone_media_resources_tip_tv, "field 'darendataone_media_resources_tip_tv'", TextView.class);
    view = Utils.findRequiredView(source, R.id.permanent_address_city_rl, "method 'onViewClicked'");
    view2131756344 = view;
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
    DarenDataOneActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.headImgRv = null;
    target.darendataone_media_resources_rv = null;
    target.daren_one_name_et = null;
    target.daren_one_jieshao_et = null;
    target.darendata_rg = null;
    target.darendata_rb_nan = null;
    target.darendata_rb_nv = null;
    target.daren_one_realname_et = null;
    target.daren_one_sfz_et = null;
    target.daren_fl = null;
    target.darenResourcesFlowTagl = null;
    target.daren_one_next = null;
    target.darendataone_media_resources_price_et = null;
    target.permanentCityNameTv = null;
    target.writeLinearLayout = null;
    target.inviteCodeEdt = null;
    target.darendataone_media_resources_tip_tv = null;

    view2131756344.setOnClickListener(null);
    view2131756344 = null;
  }
}
