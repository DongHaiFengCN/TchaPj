package com.application.tchapj.widiget;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.application.tchapj.H5UrlData;
import com.application.tchapj.R;
import com.application.tchapj.WebViewActivity;
import com.application.tchapj.main.bean.HomeCircleModel;
import com.application.tchapj.utils2.CircleImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import butterknife.BindView;
import butterknife.ButterKnife;

// 老版本找圈子
public class HomeCircleView extends FrameLayout {

    @BindView(R.id.tv_keyword)
    TextView tv_keyword;
    @BindView(R.id.iv_head)
    CircleImageView iv_head;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.circle)
    LinearLayout circle;

    private Context mContext;

    public HomeCircleView(Context context) {
        super(context);
        mContext = context;
        initView(context);
    }

    public HomeCircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView(context);
    }

    public HomeCircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.item_home_circle, this);
        ButterKnife.bind(this);
    }

    //传递过来的数据给控件赋值
    public void setData(final HomeCircleModel.HomeCircleModelResult.HomeCircle model) {
        String url = model.getImgUrl();

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .priority(Priority.HIGH)
                .diskCacheStrategy(DiskCacheStrategy.NONE);

        Glide.with(mContext)
                .asBitmap()
                .apply(options)
                .load(url)
                .into(new BitmapImageViewTarget(iv_head) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.
                                        create(mContext.getResources(), resource);
                        //circularBitmapDrawable.setCornerRadius(8); // 圆角
                        iv_head.setImageDrawable(circularBitmapDrawable);
                    }
                });

        name.setText(model.getName());

        circle.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,WebViewActivity.class);
                intent.putExtra(WebViewActivity.URL_KEY, H5UrlData.CircleDETAILS+model.getId());
                intent.putExtra(WebViewActivity.TITLE,"");
                mContext.startActivity(intent);
            }
        });
    }
}
