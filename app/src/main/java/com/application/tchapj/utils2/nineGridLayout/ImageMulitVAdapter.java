package com.application.tchapj.utils2.nineGridLayout;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.alibaba.android.vlayout.VirtualLayoutAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.application.tchapj.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import java.util.ArrayList;
import java.util.List;


public class ImageMulitVAdapter extends VirtualLayoutAdapter<ImageMulitVAdapter.ImageViewHolder> {

    private List<String> pictures = new ArrayList<>();
    private Context context;
    private int itemMargin;
    private NineGridLayout.ItemDelegate mItemDelegate;

//    public ImageMulitVAdapter(@NonNull VirtualLayoutManager layoutManager, List<String> pictures, Context context, boolean canDrag, int itemMagrin) {
//        super(layoutManager);
//        this.pictures = pictures;
//        this.context = context;
//        this.canDrag = canDrag;
//        this.itemMargin = itemMagrin;
//    }


    public ImageMulitVAdapter(@NonNull VirtualLayoutManager layoutManager, Context context, int itemMargin) {
        super(layoutManager);
        this.context = context;
        this.itemMargin = itemMargin;
    }

    public void bindData(List<String> pictures) {
        this.pictures = pictures;
        notifyDataSetChanged();
    }

    public void setItemDelegate(NineGridLayout.ItemDelegate itemDelegate) {
        mItemDelegate = itemDelegate;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ImageViewHolder(LayoutInflater.from(context).inflate(R.layout.item_mulit_image, null));
    }

    @Override
    public void onBindViewHolder(final ImageViewHolder holder, final int position) {
        VirtualLayoutManager.LayoutParams layoutParams = new VirtualLayoutManager
                .LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        int width = 0, height = 0;


        int imageCount = pictures.size();

        int displayW = DisplayUtils.getDisplayWidth(context);


        int realWidth = displayW - DisplayUtils.dip2px(context, 20);

        if (imageCount == 1) {
            width = realWidth;
            height = width;
        } else if (imageCount == 2) {
            width = ( realWidth - itemMargin ) / 2;
            height = width;
        } else if (imageCount == 3) {
            if (position == 0) {
                width = (int) (realWidth * 0.66);
                height = width;
                layoutParams.rightMargin = itemMargin;
                layoutParams.bottomMargin = itemMargin;
            } else {
                if (position == 1 || position == 2) {
                    if (position == 1) {
                        layoutParams.bottomMargin = itemMargin / 2;
                    } else {
                        layoutParams.bottomMargin = itemMargin;
                    }
                }
                width = (int) ((realWidth * 0.33));
                height = width;
            }
        } else if (imageCount == 4) {
            if (position == 0) {
                width = realWidth;
                height = (int) (width * 0.5);
            } else {
                width = (int) (displayW * 0.33);
                height = width;
            }
        } else if (imageCount == 5) {
            if (position == 0 || position == 1) {
                width = (int) (realWidth * 0.5);
                height = width;
            } else {
                width = (int) (realWidth * 0.33);
                height = width;
            }
        } else if (imageCount == 6) {
            if (position == 0) {
                width = (int) (realWidth * 0.66);
                height = width;
                layoutParams.rightMargin = 10;
                layoutParams.bottomMargin = 10;
            } else {
                if (position == 1 || position == 2) {
                    if (position == 1) {
                        layoutParams.bottomMargin = itemMargin / 2;
                    } else {
                        layoutParams.bottomMargin = itemMargin;
                    }

                }
                width = (int) (realWidth * 0.33);
                height = width;
            }
        } else if (imageCount == 7) {
            if (position == 0) {
                width = realWidth;
                height = (int) (realWidth * 0.5);
            } else {
                width = (int) (realWidth * 0.33);
                height = width;
            }
        } else if (imageCount == 8) {
            if (position == 0 || position == 1) {
                width = (int) (realWidth * 0.5);
                height = width;
            } else {
                width = (int) (realWidth * 0.33);
                height = width;
            }
        } else {
            width = (int) (realWidth * 0.33) ;
            height = width;
        }
        layoutParams.width = width;
        layoutParams.height = height;
        holder.itemView.setLayoutParams(layoutParams);

        final String imageUrl = pictures.get(position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mItemDelegate != null) {
                    mItemDelegate.onItemClick(position);
                }
            }
        });

        /*//显示动图标签
        if(imageUrl.endsWith("gif")){
            holder.mGifLabel.setVisibility(View.VISIBLE);
        }*/

        /*RequestOptions options = new RequestOptions()
                *//*.placeholder(R.drawable.ic_placeholder) // 占位符*//*
                .centerCrop()
                .sizeMultiplier(0.5f)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .override(1000, 1000); // 分辨率*/

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .priority(Priority.HIGH)
                .diskCacheStrategy(DiskCacheStrategy.NONE);

        Glide.with(context)
                .asBitmap()
                .apply(options)
                .load(imageUrl)
                .into(new BitmapImageViewTarget(holder.mImageView) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.
                                        create(context.getResources(), resource);
                        //circularBitmapDrawable.setCornerRadius(8); // 圆角
                        holder.mImageView.setImageDrawable(circularBitmapDrawable);
                    }
                });
        /*RequestOptions options = new RequestOptions()
                .centerCrop()
                .priority(Priority.HIGH)
                .diskCacheStrategy(DiskCacheStrategy.NONE);

        Glide.with(context)
                .load(imageUrl)
                .apply(options)
                .into(holder.mImageView);*/

    }

    @Override
    public int getItemCount() {
        return pictures.size();
    }


    public List<String> getPictures() {
        return pictures;
    }


    class ImageViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        /*public ImageView mGifLabel;*/

        public ImageViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.item_mulit_image);
            //mGifLabel = (ImageView) itemView.findViewById(R.id.gif_label);
        }
    }

}