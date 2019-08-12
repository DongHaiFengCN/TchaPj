// Generated code from Butter Knife. Do not modify!
package com.application.tchapj.main.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.application.tchapj.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class StartUpAdvertActivity_ViewBinding implements Unbinder {
  private StartUpAdvertActivity target;

  private View view2131755758;

  private View view2131755759;

  @UiThread
  public StartUpAdvertActivity_ViewBinding(StartUpAdvertActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public StartUpAdvertActivity_ViewBinding(final StartUpAdvertActivity target, View source) {
    this.target = target;

    View view;
    target.tvTimeCount = Utils.findRequiredViewAsType(source, R.id.tvTimeCount, "field 'tvTimeCount'", TextView.class);
    view = Utils.findRequiredView(source, R.id.ivAdvertisement, "field 'ivAdvertisement' and method 'onViewClicked'");
    target.ivAdvertisement = Utils.castView(view, R.id.ivAdvertisement, "field 'ivAdvertisement'", ImageView.class);
    view2131755758 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.llTimeCount, "field 'llTimeCount' and method 'onViewClicked'");
    target.llTimeCount = Utils.castView(view, R.id.llTimeCount, "field 'llTimeCount'", LinearLayout.class);
    view2131755759 = view;
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
    StartUpAdvertActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvTimeCount = null;
    target.ivAdvertisement = null;
    target.llTimeCount = null;

    view2131755758.setOnClickListener(null);
    view2131755758 = null;
    view2131755759.setOnClickListener(null);
    view2131755759 = null;
  }
}
