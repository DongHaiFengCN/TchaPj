// Generated code from Butter Knife. Do not modify!
package com.application.tchapj.search.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.application.tchapj.R;
import com.zhy.view.flowlayout.TagFlowLayout;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SearchActivity_ViewBinding implements Unbinder {
  private SearchActivity target;

  @UiThread
  public SearchActivity_ViewBinding(SearchActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SearchActivity_ViewBinding(SearchActivity target, View source) {
    this.target = target;

    target.search_edit = Utils.findRequiredViewAsType(source, R.id.search_edit, "field 'search_edit'", EditText.class);
    target.search_tv = Utils.findRequiredViewAsType(source, R.id.search_tv, "field 'search_tv'", TextView.class);
    target.top_search_flow_layout = Utils.findRequiredViewAsType(source, R.id.top_search_flow_layout, "field 'top_search_flow_layout'", TagFlowLayout.class);
    target.search_history_clear_all_tv = Utils.findRequiredViewAsType(source, R.id.search_history_clear_all_tv, "field 'search_history_clear_all_tv'", TextView.class);
    target.search_history_rv = Utils.findRequiredViewAsType(source, R.id.search_history_rv, "field 'search_history_rv'", ListView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SearchActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.search_edit = null;
    target.search_tv = null;
    target.top_search_flow_layout = null;
    target.search_history_clear_all_tv = null;
    target.search_history_rv = null;
  }
}
