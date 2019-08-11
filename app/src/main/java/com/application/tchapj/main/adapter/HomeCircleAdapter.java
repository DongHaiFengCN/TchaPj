package com.application.tchapj.main.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.application.tchapj.R;
import com.application.tchapj.main.activity.CircleActivity;
import com.application.tchapj.main.bean.HomeCircleModel;
import com.application.tchapj.utils2.CircleImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


// 新找圈子
public class HomeCircleAdapter extends RecyclerView.Adapter<HomeCircleAdapter.ViewHolder>{

    private final LayoutInflater mLayoutInflater;
    private final Context mContext;
    private List<HomeCircleModel.HomeCircleModelResult.HomeCircle> datas;

    public HomeCircleAdapter(Context context){
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    public void setDatas(List<HomeCircleModel.HomeCircleModelResult.HomeCircle> datas){
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
        return new ViewHolder(mLayoutInflater.inflate(R.layout.item_home_new_circle, parent, false));
    }


    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        if (holder instanceof ViewHolder) {
            final HomeCircleModel.HomeCircleModelResult.HomeCircle model = datas.get(position);

            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .priority(Priority.HIGH)
                    .diskCacheStrategy(DiskCacheStrategy.ALL);

            Glide.with(mContext)
                    .asBitmap()
                    .apply(options)
                    .load(model.getImgUrl())
                    .into(new BitmapImageViewTarget(holder.iv_circle_head) {
                        @Override
                        protected void setResource(Bitmap resource) {
                            RoundedBitmapDrawable circularBitmapDrawable =
                                    RoundedBitmapDrawableFactory.
                                            create(mContext.getResources(), resource);
                            //circularBitmapDrawable.setCornerRadius(8); // 圆角
                            holder.iv_circle_head.setImageDrawable(circularBitmapDrawable);
                        }
                    });

            holder.ll_circle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(mContext,CircleActivity.class);
                    intent.putExtra("circleTypeId", model.getId());
                    intent.putExtra("circleName", model.getName());
                    mContext.startActivity(intent);
                }
            });

        }

    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.ll_circle)
        LinearLayout ll_circle;
        @BindView(R.id.iv_circle_head)
        CircleImageView iv_circle_head;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

}
