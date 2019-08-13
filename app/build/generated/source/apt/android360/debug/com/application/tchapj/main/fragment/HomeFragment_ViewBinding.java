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

  private View view2131755934;

  private View view2131755948;

  private View view2131755938;

  private View view2131755943;

  private View view2131756270;

  private View view2131756272;

  private View view2131755940;

  private View view2131755941;

  private View view2131755942;

  @UiThread
  public HomeFragment_ViewBinding(final HomeFragment target, View source) {
    this.target = target;

    View view;
    target.toolbar2 = Utils.findRequiredViewAsType(source, R.id.toolbar2, "field 'toolbar2'", Toolbar.class);
    target.ScrollView = Utils.findRequiredViewAsType(source, R.id.ScrollView, "field 'ScrollView'", NestedScrollView.class);
    view = Utils.findRequiredView(source, R.id.search_rl, "field 'search_rl' and method 'onViewClicked'");
    target.search_rl = Utils.castView(view, R.id.search_rl, "field 'search_rl'", RelativeLayout.class);
    view2131755934 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.toutiao = Utils.findRequiredViewAsType(source, R.id.toutiao, "field 'toutiao'", RelativeLayout.class);
    view = Utils.findRequiredView(source, R.id.person_more, "field 'person_more' and method 'onViewClicked'");
    target.person_more = Utils.castView(view, R.id.person_more, "field 'person_more'", TextView.class);
    view2131755948 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.et_search = Utils.findRequiredViewAsType(source, R.id.et_search, "field 'et_search'", TypeTextView.class);
    view = Utils.findRequiredView(source, R.id.ic_message, "field 'ic_message' and method 'onViewClicked'");
    target.ic_message = Utils.castView(view, R.id.ic_message, "field 'ic_message'", ImageView.class);
    view2131755938 = view;
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
    view2131755943 = view;
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
    view2131756270 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.media_more, "method 'onViewClicked'");
    view2131756272 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_person, "method 'onViewClicked'");
    view2131755940 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_media, "method 'onViewClicked'");
    view2131755941 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_circle, "method 'onViewClicked'");
    view2131755942 = view;
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

    view2131755934.setOnClickListener(null);
    view2131755934 = null;
    view2131755948.setOnClickListener(null);
    view2131755948 = null;
    view2131755938.setOnClickListener(null);
    view2131755938 = null;
    view2131755943.setOnClickListener(null);
    view2131755943 = null;
    view2131756270.setOnClickListener(null);
    view2131756270 = null;
    view2131756272.setOnClickListener(null);
    view2131756272 = null;
    view2131755940.setOnClickListener(null);
    view2131755940 = null;
    view2131755941.setOnClickListener(null);
    view2131755941 = null;
    view2131755942.setOnClickListener(null);
    view2131755942 = null;
  }
}
