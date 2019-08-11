package com.application.tchapj.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.application.tchapj.R;
import com.application.tchapj.utils2.CircleImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

/**
 * Created by user on 2017/7/31.
 *
 * @Date: 2017/7/31
 * @Email: zzh_hz@126.com
 * @QQ: 1299234582
 * @Author: zzh
 * @Description: glide 类库的封装
 */
public class GlideUtils {

    /**
     * 加载圆形图片
     *
     * @param ctx     上下文
     * @param url     图片地址
     * @param view    ImageVeiw
     * @param errorId 图片加载错误时显示的图片
     */
    public static void loadCircleImageView(Context ctx, String url, ImageView view, int errorId) {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.centerCrop().priority(Priority.HIGH).diskCacheStrategy(DiskCacheStrategy.ALL).circleCrop().error(errorId);
        Glide.with(ctx).load(url).apply(requestOptions).into(view);
    }

    /**
     * 加载图片
     *
     * @param ctx     上下文
     * @param url     图片地址
     * @param view    ImageView
     * @param errorId 图片加载错误时显示的图片
     */
    public static void loadImageView(Context ctx, String url, ImageView view, int errorId) {
        RequestOptions requestOptions = new RequestOptions();
//        requestOptions.centerCrop().priority(Priority.HIGH).diskCacheStrategy(DiskCacheStrategy.ALL).error(errorId);
        Glide.with(ctx).load(url).apply(requestOptions).into(view);
    }

    /**
     * 加载图片 按比例缩放
     *
     * @param ctx     上下文
     * @param url     图片地址
     * @param view    ImageVeiw
     * @param errorId 图片加载错误时显示的图片
     */
    public static void loadScaleImageView(Context ctx, String url, final ImageView view, int errorId) {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.centerCrop().priority(Priority.HIGH).diskCacheStrategy(DiskCacheStrategy.ALL).error(errorId);
        Glide.with(ctx).load(url).apply(requestOptions).into(new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                BitmapDrawable bd = (BitmapDrawable) resource;
                Bitmap bm = bd.getBitmap();
                int vWidth = view.getWidth();
                int bWidth = bm.getWidth();
                double scale = (double) vWidth / (double) bWidth;
                int vHeight = (int) (scale * bm.getHeight());
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                layoutParams.height = vHeight;
                view.setLayoutParams(layoutParams);
                view.setImageBitmap(bm);
            }
        });
    }

    public static void loadRoundedImageView(final Context ctx, String url, final ImageView view, int errorId) {

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.centerCrop().priority(Priority.HIGH).diskCacheStrategy(DiskCacheStrategy.ALL).error(errorId);
        Glide.with(ctx).asBitmap().apply(requestOptions).load(url).into(new BitmapImageViewTarget(view) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.
                        create(ctx.getResources(), resource);
                circularBitmapDrawable.setCornerRadius(16); // 圆角
                view.setImageDrawable(circularBitmapDrawable);
            }
        });
    }

    public static void loadAvatarCircleImageView(final Context context, String imgUrl, final CircleImageView circleImageView) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .priority(Priority.HIGH)
                .error(R.mipmap.ic_media_head_default)
                .placeholder(R.mipmap.ic_media_head_default)
                .diskCacheStrategy(DiskCacheStrategy.NONE);

        Glide.with(context)
                .asBitmap()
                .apply(options)
                .load(imgUrl)
                .into(new BitmapImageViewTarget(circleImageView) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.
                                        create(context.getResources(), resource);
                        //circularBitmapDrawable.setCornerRadius(8); // 圆角
                        circleImageView.setImageDrawable(circularBitmapDrawable);
                    }
                });
    }
}
