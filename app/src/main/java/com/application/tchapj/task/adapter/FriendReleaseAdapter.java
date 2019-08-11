package com.application.tchapj.task.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.application.tchapj.R;
import com.application.tchapj.base.ListBaseAdapter;
import com.application.tchapj.base.SuperViewHolder;
import com.application.tchapj.utils.GlideUtils;
import com.application.tchapj.utils.Utils;

public class FriendReleaseAdapter extends ListBaseAdapter<String> {

    private OnItemClickListener onItemClickListener;

    public FriendReleaseAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_adapter_friendrelease;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, final int position) {

        ImageView frendReleaseItemImg = holder.getView(R.id.frendReleaseItemImg);
        int width = Utils.getDisplayWidth(mContext) - Utils.dip2px(60f, mContext);
        ViewGroup.LayoutParams ps = frendReleaseItemImg.getLayoutParams();
        ps.height = width / 3;
        ps.width = width / 3;
        frendReleaseItemImg.setLayoutParams(ps);
        GlideUtils.loadRoundedImageView(mContext, getDataList().get(position), frendReleaseItemImg, R.color.gainsboro);
        frendReleaseItemImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(position);
                }
            }
        });
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int pos);
    }
}
