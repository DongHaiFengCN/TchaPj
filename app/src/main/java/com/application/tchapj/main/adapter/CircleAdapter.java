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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.application.tchapj.App;
import com.application.tchapj.H5UrlData;
import com.application.tchapj.R;
import com.application.tchapj.WebViewActivity;
import com.application.tchapj.main.bean.HomeCircleInfoModel;
import com.application.tchapj.utils2.CircleImageView;
import com.application.tchapj.utils2.qiniu.utils.StringUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 安卓开发 on 2018/8/9.
 */

public class CircleAdapter extends RecyclerView.Adapter<CircleAdapter.ViewHolder> {

    private Context mContext;
    private final LayoutInflater mLayoutInflater;
    private List<HomeCircleInfoModel.HomeCircleInfoModelResult.HomeInfoCircle> infoCircles;


    public CircleAdapter(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    public void setData(List<HomeCircleInfoModel.HomeCircleInfoModelResult.HomeInfoCircle> datas) {
        infoCircles = datas;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return infoCircles != null ? infoCircles.size() : 0;
    }

    @Override
    public CircleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(mLayoutInflater.inflate(R.layout.item_circle, parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final HomeCircleInfoModel.HomeCircleInfoModelResult.HomeInfoCircle infoCircle = infoCircles.get(position);

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .priority(Priority.HIGH)
                .error(R.mipmap.ic_media_head_default)
                .placeholder(R.mipmap.ic_media_head_default)
                .diskCacheStrategy(DiskCacheStrategy.ALL);

        Glide.with(mContext)
                .asBitmap()
                .apply(options)
                .load(infoCircle.getHeadimageUrl())
                .into(new BitmapImageViewTarget(((ViewHolder) holder).circle_iv_head) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.
                                        create(mContext.getResources(), resource);
                        //circularBitmapDrawable.setCornerRadius(8); // 圆角
                        ((ViewHolder) holder).circle_iv_head.setImageDrawable(circularBitmapDrawable);
                    }
                });

        ((ViewHolder) holder).circle_tv_name.setText(infoCircle.getNickName());

        if(infoCircle.getSex()==0){
            ((ViewHolder) holder).circle_ic_type.setVisibility(View.VISIBLE);
            ((ViewHolder) holder).circle_ic_type2.setVisibility(View.GONE);
        }else {
            ((ViewHolder) holder).circle_ic_type.setVisibility(View.GONE);
            ((ViewHolder) holder).circle_ic_type2.setVisibility(View.VISIBLE);
        }

        ((ViewHolder) holder).circle_tv_explain.setText(infoCircle.getContent());


        if(infoCircle.getPyqStatus() != null && infoCircle.getPyqStatus().equals("1")){
            ((ViewHolder) holder).py_iv.setImageResource(R.mipmap.ic_py_select);

        }else {
            ((ViewHolder) holder).py_iv.setImageResource(R.mipmap.ic_py_select_normal);
        }

        if(infoCircle.getWbStatus() != null && infoCircle.getWbStatus().equals("1")){
            ((ViewHolder) holder).wb_iv.setImageResource(R.mipmap.ic_wb_select);

        }else {
            ((ViewHolder) holder).wb_iv.setImageResource(R.mipmap.ic_vb);
        }

        if(infoCircle.getWsStatus() != null && infoCircle.getWsStatus().equals("1")){
            ((ViewHolder) holder).tencent_video.setImageResource(R.mipmap.ic_tencent_video_select);
        }else {
            ((ViewHolder) holder).tencent_video.setImageResource(R.mipmap.ic_tencent_video);
        }

        if(infoCircle.getDyStatus() != null && infoCircle.getDyStatus().equals("1")){
            ((ViewHolder) holder).dy_iv.setImageResource(R.mipmap.ic_dy_select);
        }else {
            ((ViewHolder) holder).dy_iv.setImageResource(R.mipmap.ic_dy);
        }

        if(!StringUtils.isNullOrEmpty(infoCircle.getOtherStatus()) && infoCircle.getOtherStatus().equals("1")){
            ((ViewHolder) holder).other_iv.setImageResource(R.mipmap.ic_other_select);
        }else {
            ((ViewHolder) holder).other_iv.setImageResource(R.mipmap.ic_other);
        }


        ((ViewHolder) holder).item_circle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(StringUtils.isNullOrEmpty(App.getId())){
                    Intent intent = new Intent(mContext, WebViewActivity.class);

                    intent.putExtra(WebViewActivity.URL_KEY
                            , H5UrlData.Circletails2 + infoCircles.get(position).getId() + "&memberId=");
                    intent.putExtra(WebViewActivity.TITLE, "");
                    mContext.startActivity(intent);
                }else {
                    Intent intent = new Intent(mContext, WebViewActivity.class);

                    intent.putExtra(WebViewActivity.URL_KEY
                            , H5UrlData.Circletails2 + infoCircles.get(position).getId() + "&memberId=" + App.getId());
                    intent.putExtra(WebViewActivity.TITLE, "");
                    mContext.startActivity(intent);
                }
            }
        });

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_circle)
        RelativeLayout item_circle;

        @BindView(R.id.circle_iv_head)
        CircleImageView circle_iv_head;

        @BindView(R.id.circle_tv_name)
        TextView circle_tv_name;

        @BindView(R.id.circle_ic_type)
        ImageView circle_ic_type ;

        @BindView(R.id.circle_ic_type2)
        ImageView circle_ic_type2 ;

        @BindView(R.id.circle_tv_explain)
        TextView circle_tv_explain;

        @BindView(R.id.dy_iv)
        ImageView dy_iv ;
        @BindView(R.id.py_iv)
        ImageView py_iv ;
        @BindView(R.id.wb_iv)
        ImageView wb_iv ;
        @BindView(R.id.tencent_video)
        ImageView tencent_video;
        @BindView(R.id.other_iv)
        ImageView other_iv ;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
