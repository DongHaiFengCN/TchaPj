// Generated code from Butter Knife. Do not modify!
package com.application.tchapj.main.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.application.tchapj.R;
import com.application.tchapj.utils2.TypeTextView;
import com.bigkoo.convenientbanner.ConvenientBanner;
import java.lang.IllegalStateException;
import java.lang.Override;

public class HomeFragment_ViewBinding implements Unbinder {
  private HomeFragment target;

  private View view2131755917;

  private View view2131755931;

  private View view2131755921;

  private View view2131755926;

  private View view2131756244;

  private View view2131756246;

  private View view2131755923;

  private View view2131755924;

  private View view2131755925;

  @UiThread
  public HomeFragment_ViewBinding(final HomeFragment target, View source) {
    this.target = target;

    View view;
    target.toolbar2 = Utils.findRequiredViewAsType(source, R.id.toolbar2, "field 'toolbar2'", Toolbar.class);
    target.ScrollView = Utils.findRequiredViewAsType(source, R.id.ScrollView, "field 'ScrollView'", NestedScrollView.class);
    view = Utils.findRequiredView(source, R.id.search_rl, "field 'search_rl' and method 'onViewClicked'");
    target.search_rl = Utils.castView(view, R.id.search_rl, "field 'search_rl'", RelativeLayout.class);
    view2131755917 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.toutiao = Utils.findRequiredViewAsType(source, R.id.toutiao, "field 'toutiao'", RelativeLayout.class);
    view = Utils.findRequiredView(source, R.id.person_more, "field 'person_more' and method 'onViewClicked'");
    target.person_more = Utils.castView(view, R.id.person_more, "field 'person_more'", TextView.class);
    view2131755931 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.et_search = Utils.findRequiredViewAsType(source, R.id.et_search, "field 'et_search'", TypeTextView.class);
    view = Utils.findRequiredView(source, R.id.ic_message, "field 'ic_message' and method 'onViewClicked'");
    target.ic_message = Utils.castView(view, R.id.ic_message, "field 'ic_message'", ImageView.class);
    view2131755921 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.ic_spot = Utils.findRequiredViewAsType(source, R.id.ic_spot, "field 'ic_spot'", ImageView.class);
    target.banner = Utils.findRequiredViewAsType(source, R.id.banner, "field 'banner'", ConvenientBanner.class);
    view = Utils.findRequiredView(source, R.id.ll_response, "field 'll_response' and method 'onViewClicked'");
    target.ll_response = Utils.castView(view, R.id.ll_response, "field 'll_response'", LinearLayout.class);
    view2131755926 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.viewFlipper = Utils.findRequiredViewAsType(source, R.id.viewFlipper, "field 'viewFlipper'", ViewFlipper.class);
    target.follow_recycle = Utils.findRequiredViewAsType(source, R.id.follow_recycle, "field 'follow_recycle'", RecyclerView.class);
    target.mPager = Utils.findRequiredViewAsType(source, R.id.viewpager, "field 'mPager'", ViewPager.class);
    target.mLlDot = Utils.findRequiredViewAsType(source, R.id.ll_dot, "field 'mLlDot'", LinearLayout.class);
    target.media_view_pager = Utils.findRequiredViewAsType(source, R.id.media_view_pager, "field 'media_view_pager'", ViewPager.class);
    target.media_ll_dot = Utils.findRequiredViewAsType(source, R.id.media_ll_dot, "field 'media_ll_dot'", LinearLayout.class);
    target.circle_recycle = Utils.findRequiredViewAsType(source, R.id.circle_recycle, "field 'circle_recycle'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.follow_more, "method 'onViewClicked'");
    view2131756244 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.media_more, "method 'onViewClicked'");
    view2131756246 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_person, "method 'onViewClicked'");
    view2131755923 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_media, "method 'onViewClicked'");
    view2131755924 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_circle, "method 'onViewClicked'");
    view2131755925 = view;
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
    HomeFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbar2 = null;
    target.ScrollView = null;
    target.search_rl = null;
    target.toutiao = null;
    target.person_more = null;
    target.et_search = null;
    target.ic_message = null;
    target.ic_spot = null;
    target.banner = null;
    target.ll_response = null;
    target.viewFlipper = null;
    target.follow_recycle = null;
    target.mPager = null;
    target.mLlDot = null;
    target.media_view_pager = null;
    target.media_ll_dot = null;
    target.circle_recycle = null;

    view2131755917.setOnClickListener(null);
    view2131755917 = null;
    view2131755931.setOnClickListener(null);
    view2131755931 = null;
    view2131755921.setOnClickListener(null);
    view2131755921 = null;
    view2131755926.setOnClickListener(null);
    view2131755926 = null;
    view2131756244.setOnClickListener(null);
    view2131756244 = null;
    view2131756246.setOnClickListener(null);
    view2131756246 = null;
    view2131755923.setOnClickListener(null);
    view2131755923 = null;
    view2131755924.setOnClickListener(null);
    view2131755924 = null;
    view2131755925.setOnClickListener(null);
    view2131755925 = null;
  }
}
