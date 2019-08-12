// Generated code from Butter Knife. Do not modify!
package com.application.tchapj.my.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.application.tchapj.R;
import com.application.tchapj.widiget.FlowTagLayout;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MeitiActivity_ViewBinding implements Unbinder {
  private MeitiActivity target;

  private View view2131756318;

  @UiThread
  public MeitiActivity_ViewBinding(MeitiActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MeitiActivity_ViewBinding(final MeitiActivity target, View source) {
    this.target = target;

    View view;
    target.micro_media_fl = Utils.findRequiredViewAsType(source, R.id.micro_media_fl, "field 'micro_media_fl'", FlowTagLayout.class);
    target.micro_media_name = Utils.findRequiredViewAsType(source, R.id.micro_media_name, "field 'micro_media_name'", EditText.class);
    target.micro_media_rv = Utils.findRequiredViewAsType(source, R.id.micro_media_rv, "field 'micro_media_rv'", RecyclerView.class);
    target.micro_media_next = Utils.findRequiredViewAsType(source, R.id.micro_media_next, "field 'micro_media_next'", Button.class);
    target.meiti_img_tv = Utils.findRequiredViewAsType(source, R.id.meiti_img_tv, "field 'meiti_img_tv'", TextView.class);
    target.meiti_name_et = Utils.findRequiredViewAsType(source, R.id.meiti_name_et, "field 'meiti_name_et'", EditText.class);
    target.mingren_sfz_et = Utils.findRequiredViewAsType(source, R.id.mingren_sfz_et, "field 'mingren_sfz_et'", EditText.class);
    target.meiti_shouji_et = Utils.findRequiredViewAsType(source, R.id.meiti_shouji_et, "field 'meiti_shouji_et'", EditText.class);
    target.meiti_radiogroup = Utils.findRequiredViewAsType(source, R.id.meiti_radiogroup, "field 'meiti_radiogroup'", RadioGroup.class);
    target.permanentCityNameTv = Utils.findRequiredViewAsType(source, R.id.permanent_address_city_name_tv, "field 'permanentCityNameTv'", TextView.class);
    view = Utils.findRequiredView(source, R.id.permanent_address_city_rl, "method 'onViewClicked'");
    view2131756318 = view;
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
    MeitiActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.micro_media_fl = null;
    target.micro_media_name = null;
    target.micro_media_rv = null;
    target.micro_media_next = null;
    target.meiti_img_tv = null;
    target.meiti_name_et = null;
    target.mingren_sfz_et = null;
    target.meiti_shouji_et = null;
    target.meiti_radiogroup = null;
    target.permanentCityNameTv = null;

    view2131756318.setOnClickListener(null);
    view2131756318 = null;
  }
}
