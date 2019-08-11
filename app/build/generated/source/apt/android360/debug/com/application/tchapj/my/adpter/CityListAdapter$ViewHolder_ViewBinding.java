// Generated code from Butter Knife. Do not modify!
package com.application.tchapj.my.adpter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.application.tchapj.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CityListAdapter$ViewHolder_ViewBinding implements Unbinder {
  private CityListAdapter.ViewHolder target;

  @UiThread
  public CityListAdapter$ViewHolder_ViewBinding(CityListAdapter.ViewHolder target, View source) {
    this.target = target;

    target.cityTv = Utils.findRequiredViewAsType(source, R.id.layout_item_city_list_city_name_tv, "field 'cityTv'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    CityListAdapter.ViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.cityTv = null;
  }
}
