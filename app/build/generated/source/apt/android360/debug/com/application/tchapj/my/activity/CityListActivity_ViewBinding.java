// Generated code from Butter Knife. Do not modify!
package com.application.tchapj.my.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.application.tchapj.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CityListActivity_ViewBinding implements Unbinder {
  private CityListActivity target;

  private View view2131755296;

  @UiThread
  public CityListActivity_ViewBinding(CityListActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public CityListActivity_ViewBinding(final CityListActivity target, View source) {
    this.target = target;

    View view;
    target.cityListPositionNameTv = Utils.findRequiredViewAsType(source, R.id.city_list_position_name_tv, "field 'cityListPositionNameTv'", TextView.class);
    target.cityListPositionNameDescTv = Utils.findRequiredViewAsType(source, R.id.city_list_position_name_desc_tv, "field 'cityListPositionNameDescTv'", TextView.class);
    view = Utils.findRequiredView(source, R.id.city_list_again_position_tv, "field 'cityListAgainPositionTv' and method 'onViewClicked'");
    target.cityListAgainPositionTv = Utils.castView(view, R.id.city_list_again_position_tv, "field 'cityListAgainPositionTv'", TextView.class);
    view2131755296 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.listView = Utils.findRequiredViewAsType(source, R.id.activity_city_list_lv, "field 'listView'", ListView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    CityListActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.cityListPositionNameTv = null;
    target.cityListPositionNameDescTv = null;
    target.cityListAgainPositionTv = null;
    target.listView = null;

    view2131755296.setOnClickListener(null);
    view2131755296 = null;
  }
}
