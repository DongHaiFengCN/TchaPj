package com.application.tchapj.task.adapter;

import android.content.Context;
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

import com.application.tchapj.R;
import com.application.tchapj.task.bean.TaskModel;
import com.application.tchapj.utils.CommonUtils;
import com.application.tchapj.utils.Utils;
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
 * Created by 安卓开发 on 2018/7/24.
 */

public class TaskSquareAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "TaskSquareAdapter";

    private Context mContext;
    private List<T> listData;
    private LayoutInflater mLayoutInflater;
    private String countdownTime;

    public TaskSquareAdapter(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    public void setData(List<T> data) {
        this.listData = data;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return listData != null ? listData.size() : 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i(TAG, "---onCreateViewHolder:");
        return new CustomViewHolder(mLayoutInflater.inflate(R.layout.task_square_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        Log.i(TAG, "---onBindViewHolder:" + position);
        T data = listData.get(position);
        if (data instanceof TaskModel) {
            final TaskModel bean = (TaskModel) data;

            /*Glide.with(mContext)
                    .load(bean.getImgUrl())
                    .placeholder(R.drawable.img_default)
                    .crossFade()
                    .into(((CustomViewHolder) holder).task_tob_iv);*/

            ((CustomViewHolder) holder).task_tob_tv.setText(bean.getNickName());

            ((CustomViewHolder) holder).task_content_tv.setText(bean.getName());

            //((CustomViewHolder) holder).task_tob_time.setText("倒计时:"+ Utils.gethours(bean.getStartTime(),bean.getEndTime())+"小时");


            countdownTime = CommonUtils.getTimeStampDiffer(bean.getEndTime() - System.currentTimeMillis());

            ((CustomViewHolder) holder).task_tob_time.setText("倒计时：" + countdownTime);

            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .priority(Priority.HIGH)
                    .placeholder(R.mipmap.ic_message_text_default)
                    .error(R.mipmap.ic_message_text_default)
                    .diskCacheStrategy(DiskCacheStrategy.NONE);


            Glide.with(mContext)
                    .asBitmap()
                    .apply(options)
                    .load(bean.getImgUrl())
                    .into(((CustomViewHolder) holder).task_content_iv);

            String type = bean.getTaskType();
            if (type.equals("0") || type.equals("5")) {
                if (type.equals("0")) {
                    ((CustomViewHolder) holder).task_content_tab2.setText("图文");
                } else {
                    ((CustomViewHolder) holder).task_content_tab2.setText("链接");
                }
                ((CustomViewHolder) holder).task_content_tab2.setVisibility(View.VISIBLE);
                ((CustomViewHolder) holder).task_content_tab1.setText("发图文赏金");
                //0是朋友圈,1是微博，2是抖音合拍，3是抖音，4是其他

                Glide.with(mContext)
                        .asBitmap()
                        .apply(options)
                        .load(R.mipmap.ic_py_select)
                        .into(new BitmapImageViewTarget(((CustomViewHolder) holder).task_content_vip) {
                            @Override
                            protected void setResource(Bitmap resource) {
                                RoundedBitmapDrawable circularBitmapDrawable =
                                        RoundedBitmapDrawableFactory.
                                                create(mContext.getResources(), resource);
                                //circularBitmapDrawable.setCornerRadius(8); // 圆角
                                ((CustomViewHolder) holder).task_content_vip.setImageDrawable(circularBitmapDrawable);
                            }
                        });


            } else if (type.equals("1")) {

                ((CustomViewHolder) holder).task_content_tab1.setText("发微博赏金");

                ((CustomViewHolder) holder).task_content_tab2.setVisibility(View.GONE);


                Glide.with(mContext)
                        .asBitmap()
                        .apply(options)
                        .load(R.drawable.weibo)
                        .into(new BitmapImageViewTarget(((CustomViewHolder) holder).task_content_vip) {
                            @Override
                            protected void setResource(Bitmap resource) {
                                RoundedBitmapDrawable circularBitmapDrawable =
                                        RoundedBitmapDrawableFactory.
                                                create(mContext.getResources(), resource);
                                //circularBitmapDrawable.setCornerRadius(8); // 圆角
                                ((CustomViewHolder) holder).task_content_vip.setImageDrawable(circularBitmapDrawable);
                            }
                        });

            } else if (type.equals("2") || type.equals("3")) {
                if (type.equals("2")) {
                    ((CustomViewHolder) holder).task_content_tab2.setText("合拍");
                    ((CustomViewHolder) holder).task_content_tab1.setText("发抖音赏金");
                } else {
                    ((CustomViewHolder) holder).task_content_tab2.setText("原创");
                    ((CustomViewHolder) holder).task_content_tab1.setText("发抖音赏金");
                }

                ((CustomViewHolder) holder).task_content_tab2.setVisibility(View.VISIBLE);

                Glide.with(mContext)
                        .asBitmap()
                        .apply(options)
                        .load(R.drawable.douyin)
                        .into(new BitmapImageViewTarget(((CustomViewHolder) holder).task_content_vip) {
                            @Override
                            protected void setResource(Bitmap resource) {
                                RoundedBitmapDrawable circularBitmapDrawable =
                                        RoundedBitmapDrawableFactory.
                                                create(mContext.getResources(), resource);
                                //circularBitmapDrawable.setCornerRadius(8); // 圆角
                                ((CustomViewHolder) holder).task_content_vip.setImageDrawable(circularBitmapDrawable);
                            }
                        });

            } else if (type.equals("4")) {
                ((CustomViewHolder) holder).task_content_tab2.setVisibility(View.GONE);
                ((CustomViewHolder) holder).task_content_tab1.setText("发其他赏金");

            } else if(type.equals("6") || type.equals("7")){
                if (type.equals("6")) {
                    ((CustomViewHolder) holder).task_content_tab2.setText("合拍");
                } else {
                    ((CustomViewHolder) holder).task_content_tab2.setText("原创");
                }
                ((CustomViewHolder) holder).task_content_tab2.setVisibility(View.VISIBLE);
                ((CustomViewHolder) holder).task_content_tab1.setText("发微视赏金");

                Glide.with(mContext)
                        .asBitmap()
                        .apply(options)
                        .load(R.mipmap.ic_ws_select)
                        .into(new BitmapImageViewTarget(((CustomViewHolder) holder).task_content_vip) {
                            @Override
                            protected void setResource(Bitmap resource) {
                                RoundedBitmapDrawable circularBitmapDrawable =
                                        RoundedBitmapDrawableFactory.
                                                create(mContext.getResources(), resource);
                                //circularBitmapDrawable.setCornerRadius(8); // 圆角
                                ((CustomViewHolder) holder).task_content_vip.setImageDrawable(circularBitmapDrawable);
                            }
                        });
            }


            //((CustomViewHolder) holder).task_content_tab1.setText(bean.getNickName());
            ((CustomViewHolder) holder).task_content_frequency.setText(Utils.getFormatMoney(bean.getUnitPrice()) + "/次");

            if(mOnItemClickLitener!=null){
                ((CustomViewHolder) holder).task_ll.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mOnItemClickLitener.onItemClick(v,position);

                    }
                });

            }

        }
    }


    class CustomViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.task_ll)
        LinearLayout task_ll;
        @BindView(R.id.task_tob_iv)
        ImageView task_tob_iv;
        @BindView(R.id.task_tob_tv)
        TextView task_tob_tv;
        @BindView(R.id.task_tob_time)
        TextView task_tob_time;
        @BindView(R.id.task_content_iv)
        ImageView task_content_iv;
        @BindView(R.id.task_content_tv)
        TextView task_content_tv;
        @BindView(R.id.task_content_vip)
        CircleImageView task_content_vip;
        @BindView(R.id.task_content_tab1)
        TextView task_content_tab1;
        @BindView(R.id.task_content_tab2)
        TextView task_content_tab2;
        @BindView(R.id.task_content_frequency)
        TextView task_content_frequency;

        public CustomViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

        }
    }

    private OnItemClickLitener mOnItemClickLitener;

    public interface OnItemClickLitener {

        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }
}
