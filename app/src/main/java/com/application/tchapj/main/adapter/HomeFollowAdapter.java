package com.application.tchapj.main.adapter;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.annotation.NonNull;
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
import com.application.tchapj.main.bean.HomeTopData;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import net.wujingchao.android.view.SimpleTagImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

// 微关注
public class HomeFollowAdapter extends RecyclerView.Adapter<HomeFollowAdapter.ViewHolder>{

    private final LayoutInflater mLayoutInflater;
    private final Context mContext;
    private List<HomeTopData.HomeTopDataResult.HomeTopNews> datas;

    public HomeFollowAdapter(Context context){
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    public void setDatas(List<HomeTopData.HomeTopDataResult.HomeTopNews> datas){
        this.datas = datas;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return datas==null?0:datas.size();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(mLayoutInflater.inflate(R.layout.item_home_follow, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        if (holder instanceof ViewHolder) {
            final HomeTopData.HomeTopDataResult.HomeTopNews model = datas.get(position);

            // 图片标签设置
           /* holder.follow_iv_head.setTagTextSize(12);                  // 文字大小
            holder.follow_iv_head.setCornerDistance(10);               // 标签边距
            holder.follow_iv_head.setTagTextColor(Color.RED);         // 标签颜色
            holder.follow_iv_head.setTagText("hot");                   // 标签内容
            holder.follow_iv_head.setTagBackgroundColor(Color.WHITE); // 背景色
            holder.follow_iv_head.setTagWidth(14);                     // 标签宽度
            holder.follow_iv_head.setTagOrientation(SimpleTagImageView.LEFT_TOP); // 位置
            holder.follow_iv_head.setTagRoundRadius(0); // 图片角度
            */
            holder.follow_iv_head.setTagEnable(false);

            String[] str = model.getImgUrl().split(",");
            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .priority(Priority.HIGH)
                    .diskCacheStrategy(DiskCacheStrategy.ALL);

            Glide.with(mContext)
                    .asBitmap()
                    .apply(options)
                    .load(str[0])
                    .into(new BitmapImageViewTarget(holder.follow_iv_head) {
                        @Override
                        protected void setResource(Bitmap resource) {
                            RoundedBitmapDrawable circularBitmapDrawable =
                                    RoundedBitmapDrawableFactory.
                                            create(mContext.getResources(), resource);
                            //circularBitmapDrawable.setCornerRadius(8); // 圆角
                            holder.follow_iv_head.setImageDrawable(circularBitmapDrawable);
                        }
                    });

            holder.follow_tv_title.setText(model.getTitle());
            holder.follow_title_1.setText(model.getR_name());
            /*holder.follow_title_2.setText("标签");
            holder.follow_title_2.setVisibility(View.GONE);*/

            /*holder.follow_imageView.setBackgroundResource(R.mipmap.ic_red_v2);*/

            /*String[] str = model.getService_label().split(",");
            if (str.length>0) {
                holder.follow_title_1.setText(str[0]);
            }else {
                holder.follow_title_1.setVisibility(View.INVISIBLE);
            }
            if (str.length>1) {
                holder.follow_title_2.setText(str[1]);
            }else {
                holder.follow_title_2.setVisibility(View.INVISIBLE);
            }
            if (str.length>2) {
                holder.follow_title_3.setText(str[2]);
            }else {
                holder.follow_title_3.setVisibility(View.INVISIBLE);
            }
            if (str.length>3) {
                holder.follow_title_4.setText(str[3]);
            }else {
                holder.follow_title_4.setVisibility(View.INVISIBLE);
            }*/



            holder.follow_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    if(App.getId()==null){
                        Intent intent = new Intent(mContext, WebViewActivity.class);

                        intent.putExtra(WebViewActivity.URL_KEY
                                , H5UrlData.Followtails2 + datas.get(position).getId() + "&memberId=");
                        intent.putExtra(WebViewActivity.TITLE, "");
                        mContext.startActivity(intent);
                    }else {
                        Intent intent = new Intent(mContext, WebViewActivity.class);

                        intent.putExtra(WebViewActivity.URL_KEY
                                , H5UrlData.Followtails2 + datas.get(position).getId() + "&memberId=" + App.getId());
                        intent.putExtra(WebViewActivity.TITLE, "");
                        mContext.startActivity(intent);
                    }

                }
            });

        }

    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.follow_item)
        LinearLayout follow_item;
        @BindView(R.id.follow_iv_head)
        SimpleTagImageView follow_iv_head;
        @BindView(R.id.follow_tv_title)
        TextView follow_tv_title;
        @BindView(R.id.follow_imageView)
        ImageView follow_imageView;
        @BindView(R.id.follow_title_1)
        TextView follow_title_1;
        /*@BindView(R.id.follow_title_2)
        TextView follow_title_2;*/

       /* @BindView(R.id.follow_title_3)
        TextView follow_title_3;
        @BindView(R.id.follow_title_4)
        TextView follow_title_4;*/

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

}
