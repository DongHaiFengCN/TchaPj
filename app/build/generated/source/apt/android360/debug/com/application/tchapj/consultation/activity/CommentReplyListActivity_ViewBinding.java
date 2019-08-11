// Generated code from Butter Knife. Do not modify!
package com.application.tchapj.consultation.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.application.tchapj.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CommentReplyListActivity_ViewBinding implements Unbinder {
  private CommentReplyListActivity target;

  private View view2131755472;

  @UiThread
  public CommentReplyListActivity_ViewBinding(CommentReplyListActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public CommentReplyListActivity_ViewBinding(final CommentReplyListActivity target, View source) {
    this.target = target;

    View view;
    target.recyclerView = Utils.findRequiredViewAsType(source, R.id.activity_find_detail_list_rv, "field 'recyclerView'", RecyclerView.class);
    target.refreshLayout = Utils.findRequiredViewAsType(source, R.id.activity_find_detail_refreshLayout, "field 'refreshLayout'", SmartRefreshLayout.class);
    target.commentCountLl = Utils.findRequiredViewAsType(source, R.id.find_detail_comment_count_ll, "field 'commentCountLl'", LinearLayout.class);
    target.commentCountTv = Utils.findRequiredViewAsType(source, R.id.find_detail_comment_count_tv, "field 'commentCountTv'", TextView.class);
    target.addCommentEdt = Utils.findRequiredViewAsType(source, R.id.find_detail_comment_add_edt, "field 'addCommentEdt'", EditText.class);
    view = Utils.findRequiredView(source, R.id.find_detail_comment_add_tv, "field 'addCommentTv' and method 'onViewClicked'");
    target.addCommentTv = Utils.castView(view, R.id.find_detail_comment_add_tv, "field 'addCommentTv'", TextView.class);
    view2131755472 = view;
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
    CommentReplyListActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.recyclerView = null;
    target.refreshLayout = null;
    target.commentCountLl = null;
    target.commentCountTv = null;
    target.addCommentEdt = null;
    target.addCommentTv = null;

    view2131755472.setOnClickListener(null);
    view2131755472 = null;
  }
}
