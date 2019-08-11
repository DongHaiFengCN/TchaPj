package com.application.tchapj.utils2.imagepicker.imageloader;

import android.app.Activity;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.widget.ImageView;

import com.application.tchapj.utils2.imagepicker.loader.ImageLoader;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import java.io.File;

/**
 * ================================================
 * 作    者：jeasonlzy（廖子尧 Github地址：https://github.com/jeasonlzy0216
 * 版    本：1.0
 * 创建日期：2016/5/19
 * 描    述：
 * 修订历史：
 * ================================================
 */
public class GlideImageLoader implements ImageLoader {

    @Override
    public void displayImage(final Activity activity, String path, final ImageView imageView, int width, int height) {

        RequestOptions options = new RequestOptions()
                .priority(Priority.HIGH)
                .diskCacheStrategy(DiskCacheStrategy.NONE);

        Glide.with(activity)
                .asBitmap()
                .apply(options)
                .load(Uri.fromFile(new File(path)))
                .into(new BitmapImageViewTarget(imageView) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.
                                        create(activity.getResources(), resource);
                        //circularBitmapDrawable.setCornerRadius(8); // 圆角
                        imageView.setImageDrawable(circularBitmapDrawable);
                    }
                });

    }

    @Override
    public void displayImagePreview(final Activity activity, String path, final ImageView imageView, int width, int height) {

        RequestOptions options = new RequestOptions()
                .priority(Priority.HIGH)
                .diskCacheStrategy(DiskCacheStrategy.NONE);

        Glide.with(activity)
                .asBitmap()
                .apply(options)
                .load(Uri.fromFile(new File(path)))
                .into(new BitmapImageViewTarget(imageView) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.
                                        create(activity.getResources(), resource);
                        //circularBitmapDrawable.setCornerRadius(8); // 圆角
                        imageView.setImageDrawable(circularBitmapDrawable);
                    }
                });

    }

    @Override
    public void clearMemoryCache() {
    }
}
