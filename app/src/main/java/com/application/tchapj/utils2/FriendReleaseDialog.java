package com.application.tchapj.utils2;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.application.tchapj.R;

// 对话框
public class FriendReleaseDialog {

    private Context context;
    private Dialog dialog;
    private OnDialogLinstener onDialogLinstener;

    public FriendReleaseDialog(@NonNull Context context) {
        this.context = context;
        dialog = new Dialog(context, R.style.BottomDialog_Animation);
    }

    public void createFriendReleaseDialog() {

        final int widthPixels = context.getResources().getDisplayMetrics().widthPixels;
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_friendrelease, null);
        dialog.setContentView(view);
        Button friendReleaseDialogBtn = view.findViewById(R.id.friendReleaseDialogBtn);
        TextView friendReleaseDialogTime = view.findViewById(R.id.friendReleaseDialogTime);
        if (onDialogLinstener != null) {
            onDialogLinstener.init(friendReleaseDialogTime);
        }
        friendReleaseDialogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onDialogLinstener != null) {
                    onDialogLinstener.cancle();
                }
            }
        });
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = widthPixels;
        view.setLayoutParams(layoutParams);
        dialog.show();
    }

    public boolean isShowing() {
        return dialog.isShowing();
    }

    public void dismiss() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    public void setOnDialogLinstener(OnDialogLinstener onDialogLinstener) {
        this.onDialogLinstener = onDialogLinstener;
    }

    public interface OnDialogLinstener {
        void init(TextView tv);

        void cancle();
    }

}
