// Generated code from Butter Knife. Do not modify!
package com.application.tchapj.task.activity;

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

public class CollarTaskCircleFriendLinkActivity_ViewBinding implements Unbinder {
  private CollarTaskCircleFriendLinkActivity target;

  private View view2131755315;

  @UiThread
  public CollarTaskCircleFriendLinkActivity_ViewBinding(CollarTaskCircleFriendLinkActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public CollarTaskCircleFriendLinkActivity_ViewBinding(final CollarTaskCircleFriendLinkActivity target, View source) {
    this.target = target;

    View view;
    target.taskImgIv = Utils.findRequiredViewAsType(source, R.id.collar_task_circle_friend_task_iv, "field 'taskImgIv'", ImageView.class);
    target.taskNameTv = Utils.findRequiredViewAsType(source, R.id.collar_task_circle_friend_task_name_tv, "field 'taskNameTv'", TextView.class);
    target.nickNameTv = Utils.findRequiredViewAsType(source, R.id.collar_task_circle_friend_task_nick_name_tv, "field 'nickNameTv'", TextView.class);
    target.countdownTimeTv = Utils.findRequiredViewAsType(source, R.id.collar_task_circle_friend_task_countdown_tv, "field 'countdownTimeTv'", TextView.class);
    target.requireContentTv = Utils.findRequiredViewAsType(source, R.id.collar_task_circle_friend_task_requirecontent_tv, "field 'requireContentTv'", TextView.class);
    target.linkClickTv = Utils.findRequiredViewAsType(source, R.id.collar_task_circle_friend_task_link_url_click_tv, "field 'linkClickTv'", TextView.class);
    target.taskInfoFirstLl = Utils.findRequiredViewAsType(source, R.id.collar_task_circle_friend_task_info_first_ll, "field 'taskInfoFirstLl'", LinearLayout.class);
    target.taskInfoSecondLl = Utils.findRequiredViewAsType(source, R.id.collar_task_circle_friend_task_info_second_ll, "field 'taskInfoSecondLl'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.collar_task_circle_friend_task_link_share_btn, "method 'onViewClicked'");
    view2131755315 = view;
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
    CollarTaskCircleFriendLinkActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.taskImgIv = null;
    target.taskNameTv = null;
    target.nickNameTv = null;
    target.countdownTimeTv = null;
    target.requireContentTv = null;
    target.linkClickTv = null;
    target.taskInfoFirstLl = null;
    target.taskInfoSecondLl = null;

    view2131755315.setOnClickListener(null);
    view2131755315 = null;
  }
}
