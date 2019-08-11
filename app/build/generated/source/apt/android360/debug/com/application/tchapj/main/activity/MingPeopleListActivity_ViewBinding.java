// Generated code from Butter Knife. Do not modify!
package com.application.tchapj.main.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.application.tchapj.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MingPeopleListActivity_ViewBinding implements Unbinder {
  private MingPeopleListActivity target;

  @UiThread
  public MingPeopleListActivity_ViewBinding(MingPeopleListActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MingPeopleListActivity_ViewBinding(MingPeopleListActivity target, View source) {
    this.target = target;

    target.mingpeople_recyclerview = Utils.findRequiredViewAsType(source, R.id.mingpeople_recyclerview, "field 'mingpeople_recyclerview'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MingPeopleListActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mingpeople_recyclerview = null;
  }
}
