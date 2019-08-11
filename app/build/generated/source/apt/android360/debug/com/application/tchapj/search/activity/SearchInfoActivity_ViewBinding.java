// Generated code from Butter Knife. Do not modify!
package com.application.tchapj.search.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.application.tchapj.R;
import com.jude.easyrecyclerview.EasyRecyclerView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SearchInfoActivity_ViewBinding implements Unbinder {
  private SearchInfoActivity target;

  @UiThread
  public SearchInfoActivity_ViewBinding(SearchInfoActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SearchInfoActivity_ViewBinding(SearchInfoActivity target, View source) {
    this.target = target;

    target.search_easyRecyclerView = Utils.findRequiredViewAsType(source, R.id.search_easyRecyclerView, "field 'search_easyRecyclerView'", EasyRecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SearchInfoActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.search_easyRecyclerView = null;
  }
}
