package com.application.tchapj.my.adpter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.application.tchapj.App;
import com.application.tchapj.H5UrlData;
import com.application.tchapj.R;
import com.application.tchapj.WebViewActivity;
import com.application.tchapj.main.bean.PersonMediaModel;
import com.application.tchapj.my.bean.FansInfoBean;
import com.application.tchapj.utils2.CircleImageView;
import com.application.tchapj.utils2.FlowGroupView;
import com.application.tchapj.utils2.qiniu.utils.StringUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FansListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final LayoutInflater mLayoutInflater;
    private final Context mContext;
    private List<FansInfoBean> fansInfoBeans;

    public FansListAdapter(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        notifyDataSetChanged();
    }

    public void setDatas(List<FansInfoBean> fansInfoBeans) {
        this.fansInfoBeans = fansInfoBeans;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return fansInfoBeans == null ? 0 : fansInfoBeans.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mLayoutInflater.inflate(R.layout.layout_item_fans_list, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ViewHolder) {
            final ViewHolder holder1 = (ViewHolder) holder;
            String headImg = fansInfoBeans.get(position).getHeadimgurl();
            String nickName = fansInfoBeans.get(position).getNick_name();

            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .placeholder(R.mipmap.ic_media_head_default)
                    .error(R.mipmap.ic_media_head_default)
                    .priority(Priority.HIGH)
                    .diskCacheStrategy(DiskCacheStrategy.NONE);

            Glide.with(mContext)
                    .asBitmap()
                    .apply(options)
                    .load(headImg)
                    .into(new BitmapImageViewTarget(holder1.headIv) {
                        @Override
                        protected void setResource(Bitmap resource) {
                            RoundedBitmapDrawable circularBitmapDrawable =
                                    RoundedBitmapDrawableFactory.
                                            create(mContext.getResources(), resource);
                            //circularBitmapDrawable.setCornerRadius(8); // 圆角
                            holder1.headIv.setImageDrawable(circularBitmapDrawable);
                        }
                    });

            holder1.nameTv.setText(nickName);

        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.layout_item_fans_list_head_iv)
        CircleImageView headIv ;
        @BindView(R.id.layout_item_fans_list_head_nick_name_tv)
        TextView nameTv;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
