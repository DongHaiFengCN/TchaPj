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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.application.tchapj.App;
import com.application.tchapj.H5UrlData;
import com.application.tchapj.R;
import com.application.tchapj.WebViewActivity;
import com.application.tchapj.main.bean.MingPeopleListBean;
import com.application.tchapj.utils2.CircleImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/10/10.
 */

public class MingPeopleAdapter extends RecyclerView.Adapter<MingPeopleAdapter.ViewHolder> {

    private Context mContext;
    private final LayoutInflater mLayoutInflater;
    private List<MingPeopleListBean.DataBean.HuoyueListBean> huoyueLists;


    public MingPeopleAdapter(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    public void setData(List<MingPeopleListBean.DataBean.HuoyueListBean> huoyueLists) {
        this.huoyueLists = huoyueLists;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return huoyueLists != null ? huoyueLists.size() : 0;
    }

    @Override
    public MingPeopleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(mLayoutInflater.inflate(R.layout.item_mingpeople, parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final MingPeopleListBean.DataBean.HuoyueListBean huoyueListBean = huoyueLists.get(position);

        if(position==0){
            ((ViewHolder) holder).iv_lv.setImageResource(R.mipmap.ic_lv1);
        }else if(position==1){
            ((ViewHolder) holder).iv_lv.setImageResource(R.mipmap.ic_lv2);
        }else if(position==2){
            ((ViewHolder) holder).iv_lv.setImageResource(R.mipmap.ic_lv3);
        }else if(position>2){
            ((ViewHolder) holder).iv_lv.setVisibility(View.GONE);
            ((ViewHolder) holder).tv_ph.setText(position+1+"");
            ((ViewHolder) holder).tv_ph.setVisibility(View.VISIBLE);
        }

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .priority(Priority.HIGH)
                .diskCacheStrategy(DiskCacheStrategy.NONE);

        Glide.with(mContext)
                .asBitmap()
                .apply(options)
                .load(huoyueListBean.getHeadUrl())
                .into(new BitmapImageViewTarget(((ViewHolder) holder).iv_portrait) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.
                                        create(mContext.getResources(), resource);
                        //circularBitmapDrawable.setCornerRadius(8); // 圆角
                        ((ViewHolder) holder).iv_portrait.setImageDrawable(circularBitmapDrawable);
                    }
                });

        ((ViewHolder) holder).tv_name.setText(huoyueListBean.getNickName());
        ((ViewHolder) holder).tv_velue.setText(huoyueListBean.getActivity());

        ((ViewHolder) holder).ll_mingpeople.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(App.getId()==null){
                    Intent intent = new Intent(mContext, WebViewActivity.class);

                    intent.putExtra(WebViewActivity.URL_KEY
                            , H5UrlData.PEROSNDETAILS2 + huoyueListBean.getId() + "&memberId=");
                    intent.putExtra(WebViewActivity.TITLE, "");
                    mContext.startActivity(intent);
                }else {
                    Intent intent = new Intent(mContext, WebViewActivity.class);

                    intent.putExtra(WebViewActivity.URL_KEY
                            , H5UrlData.PEROSNDETAILS2 + huoyueListBean.getId() + "&memberId=" + App.getId());
                    intent.putExtra(WebViewActivity.TITLE, "");
                    mContext.startActivity(intent);
                }

            }
        });

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ll_mingpeople)
        LinearLayout ll_mingpeople ;

        @BindView(R.id.iv_lv)
        ImageView iv_lv ;

        @BindView(R.id.tv_ph)
        TextView tv_ph;

        @BindView(R.id.iv_portrait)
        CircleImageView iv_portrait;

        @BindView(R.id.tv_name)
        TextView tv_name;

        @BindView(R.id.tv_velue)
        TextView tv_velue;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
