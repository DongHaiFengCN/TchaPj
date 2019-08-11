// Generated code from Butter Knife. Do not modify!
package com.application.tchapj.main.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.application.tchapj.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MessagenotificationActivity_ViewBinding implements Unbinder {
  private MessagenotificationActivity target;

  @UiThread
  public MessagenotificationActivity_ViewBinding(MessagenotificationActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MessagenotificationActivity_ViewBinding(MessagenotificationActivity target, View source) {
    this.target = target;

    target.toolbar_menu_iv = Utils.findRequiredViewAsType(source, R.id.toolbar_menu_iv, "field 'toolbar_menu_iv'", ImageView.class);
    target.mListView = Utils.findRequiredViewAsType(source, R.id.listView, "field 'mListView'", ListView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MessagenotificationActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbar_menu_iv = null;
    target.mListView = null;
  }
}
