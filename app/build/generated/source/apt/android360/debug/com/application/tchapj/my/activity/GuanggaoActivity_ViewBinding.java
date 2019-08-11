// Generated code from Butter Knife. Do not modify!
package com.application.tchapj.my.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.application.tchapj.R;
import com.application.tchapj.widiget.FlowTagLayout;
import java.lang.IllegalStateException;
import java.lang.Override;

public class GuanggaoActivity_ViewBinding implements Unbinder {
  private GuanggaoActivity target;

  @UiThread
  public GuanggaoActivity_ViewBinding(GuanggaoActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public GuanggaoActivity_ViewBinding(GuanggaoActivity target, View source) {
    this.target = target;

    target.guangao_gsname_et = Utils.findRequiredViewAsType(source, R.id.guangao_gsname_et, "field 'guangao_gsname_et'", EditText.class);
    target.guangao_zhiz_et = Utils.findRequiredViewAsType(source, R.id.guangao_zhiz_et, "field 'guangao_zhiz_et'", EditText.class);
    target.guangao_gsjcname_et = Utils.findRequiredViewAsType(source, R.id.guangao_gsjcname_et, "field 'guangao_gsjcname_et'", EditText.class);
    target.guangao_name_et = Utils.findRequiredViewAsType(source, R.id.guangao_name_et, "field 'guangao_name_et'", EditText.class);
    target.guangao_shouji_et = Utils.findRequiredViewAsType(source, R.id.guangao_shouji_et, "field 'guangao_shouji_et'", EditText.class);
    target.guangao_code_et = Utils.findRequiredViewAsType(source, R.id.guangao_code_et, "field 'guangao_code_et'", EditText.class);
    target.guangao_code_tv = Utils.findRequiredViewAsType(source, R.id.guangao_code_tv, "field 'guangao_code_tv'", TextView.class);
    target.guangao_rv = Utils.findRequiredViewAsType(source, R.id.guangao_rv, "field 'guangao_rv'", RecyclerView.class);
    target.guangao_bv = Utils.findRequiredViewAsType(source, R.id.guangao_bv, "field 'guangao_bv'", Button.class);
    target.guanggao_industry_fl = Utils.findRequiredViewAsType(source, R.id.guanggao_industry_fl, "field 'guanggao_industry_fl'", FlowTagLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    GuanggaoActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.guangao_gsname_et = null;
    target.guangao_zhiz_et = null;
    target.guangao_gsjcname_et = null;
    target.guangao_name_et = null;
    target.guangao_shouji_et = null;
    target.guangao_code_et = null;
    target.guangao_code_tv = null;
    target.guangao_rv = null;
    target.guangao_bv = null;
    target.guanggao_industry_fl = null;
  }
}
