package com.application.tchapj.search.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import com.application.tchapj.search.bean.SearchBean;
import com.application.tchapj.utils2.CircleImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import java.util.List;

/**
 * Created by 安卓开发 on 2018/8/8.
 */

public class SearchInfoMostAdapter<T> extends RecyclerView.Adapter {

    private static final String TAG = "SearchInfoMostAdapter";

    private Context mContext;
    private LayoutInflater mInflater;
    private List<T> listData;
    private OnItemClickLitener mOnItemClickLitener;

    public SearchInfoMostAdapter(Context context, List<T> data) {
        this.mContext = context;
        this.listData = data;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemCount() {
        return listData != null ? listData.size() : 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i(TAG, "---onCreateViewHolder:");
        return new CustomViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.search_recycler_item_most, parent, false));
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        Log.i(TAG, "---onBindViewHolder:" + position);
        T data = listData.get(position);
        if (data instanceof SearchBean.SearchBeanResult.SearchMingrenList) {
            final SearchBean.SearchBeanResult.SearchMingrenList bean = (SearchBean.SearchBeanResult.SearchMingrenList) data;

            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .priority(Priority.HIGH)
                    .diskCacheStrategy(DiskCacheStrategy.NONE);

            Glide.with(mContext)
                    .asBitmap()
                    .apply(options)
                    .load(bean.getHeadUrl())
                    .into(new BitmapImageViewTarget(((CustomViewHolder) holder).search_most_iv_head) {
                        @Override
                        protected void setResource(Bitmap resource) {
                            RoundedBitmapDrawable circularBitmapDrawable =
                                    RoundedBitmapDrawableFactory.
                                            create(mContext.getResources(), resource);
                            //circularBitmapDrawable.setCornerRadius(8); // 圆角
                            ((CustomViewHolder) holder).search_most_iv_head.setImageDrawable(circularBitmapDrawable);
                        }
                    });

            ((CustomViewHolder) holder).search_most_tv_name.setText(bean.getNickName());
            ((CustomViewHolder) holder).search_tv_most_explain.setText(bean.getContent());
            ((CustomViewHolder) holder).search_most_zixunName.setText(bean.getNewsTypeId());

            ((CustomViewHolder) holder).search_most_ll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (App.getId() == null) {
                        Intent intent = new Intent(mContext, WebViewActivity.class);

                        intent.putExtra(WebViewActivity.URL_KEY
                                , H5UrlData.PEROSNDETAILS2 + bean.getId() + "&memberId=");
                        intent.putExtra(WebViewActivity.TITLE, "");
                        mContext.startActivity(intent);
                    } else {
                        Intent intent = new Intent(mContext, WebViewActivity.class);

                        intent.putExtra(WebViewActivity.URL_KEY
                                , H5UrlData.PEROSNDETAILS2 + bean.getId() + "&memberId=" + App.getId());
                        intent.putExtra(WebViewActivity.TITLE, "");
                        mContext.startActivity(intent);
                    }

                }
            });

        }
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        LinearLayout search_most_ll;
        CircleImageView search_most_iv_head;
        TextView search_most_tv_name;
        ImageView search_ic_most_type;
        TextView search_tv_most_explain;
        TextView search_most_zixunName;

        public CustomViewHolder(View itemView) {
            super(itemView);
            search_most_ll = (LinearLayout) itemView.findViewById(R.id.search_most_ll);
            search_most_iv_head = (CircleImageView) itemView.findViewById(R.id.search_most_iv_head);
            search_most_tv_name = (TextView) itemView.findViewById(R.id.search_most_tv_name);
            search_ic_most_type = (ImageView) itemView.findViewById(R.id.search_ic_most_type);
            search_tv_most_explain = (TextView) itemView.findViewById(R.id.search_tv_most_explain);
            search_most_zixunName = (TextView) itemView.findViewById(R.id.search_most_zixunName);

        }
    }

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {

        this.mOnItemClickLitener = mOnItemClickLitener;
    }

}
