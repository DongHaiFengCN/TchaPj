package com.application.tchapj.main.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.application.tchapj.App;
import com.application.tchapj.H5UrlData;
import com.application.tchapj.R;
import com.application.tchapj.WebViewActivity;
import com.application.tchapj.main.bean.HomeMediaList;
import com.application.tchapj.utils2.CircleImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

// 媒体
public class HomeMediaAdapter extends RecyclerView.Adapter<HomeMediaAdapter.ViewHolder> {

    private final LayoutInflater mLayoutInflater;
    private final Context mContext;
    private List<HomeMediaList.HomeMediaListResult.HomeMediaModel> datas;

    public HomeMediaAdapter(Context context){
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    public void setDatas(List<HomeMediaList.HomeMediaListResult.HomeMediaModel> datas){
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return datas==null?0:datas.size();
    }

    @Override
    public HomeMediaAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mLayoutInflater.inflate(R.layout.item_home_media, parent, false));
    }

    @Override
    public void onBindViewHolder(final HomeMediaAdapter.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            final HomeMediaList.HomeMediaListResult.HomeMediaModel model = datas.get(position);

            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .priority(Priority.HIGH)
                    .diskCacheStrategy(DiskCacheStrategy.NONE);

            Glide.with(mContext)
                    .asBitmap()
                    .apply(options)
                    .load(model.getHeadUrl())
                    .into(new BitmapImageViewTarget(holder.iv_head) {
                        @Override
                        protected void setResource(Bitmap resource) {
                            RoundedBitmapDrawable circularBitmapDrawable =
                                    RoundedBitmapDrawableFactory.
                                            create(mContext.getResources(), resource);
                            //circularBitmapDrawable.setCornerRadius(8); // 圆角
                            holder.iv_head.setImageDrawable(circularBitmapDrawable);
                        }
                    });

            holder.tv_title.setText(model.getNickName());
            holder.title_1.setText(model.getContent());
            holder.title_2.setText(model.getFansTotal());

            /*String[] str = model.getService_label().split(",");
            if (str.length>0) {
                holder.title_1.setText(str[0]);
            }else {
                holder.title_1.setVisibility(View.INVISIBLE);
            }
            if (str.length>1) {
                holder.title_2.setText(str[1]);
            }else {
                holder.title_2.setVisibility(View.INVISIBLE);
            }
            if (str.length>2) {
                holder.title_3.setText(str[2]);
            }else {
                holder.title_3.setVisibility(View.INVISIBLE);
            }
            if (str.length>3) {
                holder.title_4.setText(str[3]);
            }else {
                holder.title_4.setVisibility(View.INVISIBLE);
            }*/


            holder.item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(App.getId()==null){
                        Intent intent = new Intent(mContext, WebViewActivity.class);

                        intent.putExtra(WebViewActivity.URL_KEY
                                , H5UrlData.PEROSNDETAILS2 + model.getId() + "&memberId=");
                        intent.putExtra(WebViewActivity.TITLE, "");
                        mContext.startActivity(intent);
                    }else {
                        Intent intent = new Intent(mContext, WebViewActivity.class);

                        intent.putExtra(WebViewActivity.URL_KEY
                                , H5UrlData.PEROSNDETAILS2 + model.getId() + "&memberId=" + App.getId());
                        intent.putExtra(WebViewActivity.TITLE, "");
                        mContext.startActivity(intent);
                    }
                }
            });

        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.item)
        LinearLayout item;
        @BindView(R.id.iv_head)
        CircleImageView iv_head;
        @BindView(R.id.tv_title)
        TextView tv_title;
        @BindView(R.id.title_1)
        TextView title_1;
        @BindView(R.id.title_2)
        TextView title_2;
        /*@BindView(R.id.title_3)
        TextView title_3;*/
        /*@BindView(R.id.title_4)
        TextView title_4;*/

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

}
