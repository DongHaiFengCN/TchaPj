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

public class SearchInfoMostActivity_ViewBinding implements Unbinder {
  private SearchInfoMostActivity target;

  @UiThread
  public SearchInfoMostActivity_ViewBinding(SearchInfoMostActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SearchInfoMostActivity_ViewBinding(SearchInfoMostActivity target, View source) {
    this.target = target;

    target.search_info_most_rv = Utils.findRequiredViewAsType(source, R.id.search_info_most_rv, "field 'search_info_most_rv'", EasyRecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SearchInfoMostActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.search_info_most_rv = null;
  }
}
