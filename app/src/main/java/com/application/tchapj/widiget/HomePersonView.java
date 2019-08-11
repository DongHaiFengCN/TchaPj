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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.application.tchapj.App;
import com.application.tchapj.H5UrlData;
import com.application.tchapj.R;
import com.application.tchapj.WebViewActivity;
import com.application.tchapj.main.bean.HomePerson;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;


public class HomePersonView extends RelativeLayout {

    private TextView tv_introduce,tv_name;
    private ImageView iv_01;
    private RelativeLayout personDetails;

    public HomePersonView(Context context) {
        super(context);
        initView(context);
    }

    public HomePersonView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public HomePersonView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context){
        LayoutInflater.from(context).inflate(R.layout.item_home_person,this);
        tv_introduce = (TextView) findViewById(R.id.tv_introduce);
        tv_name = (TextView) findViewById(R.id.tv_name);
        iv_01 = (ImageView) findViewById(R.id.iv01);
        personDetails = (RelativeLayout) findViewById(R.id.personDetails);
    }

    //传递过来的数据给控件赋值
    public void setData(final HomePerson.HomePersonResult.HomeMingren data){
        tv_introduce.setText(data.getContent());
        tv_name.setText(data.getNickName());

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .priority(Priority.HIGH)
                .diskCacheStrategy(DiskCacheStrategy.NONE);

        Glide.with(getContext())
                .asBitmap()
                .apply(options)
                .load(data.getBig_url())
                .into(new BitmapImageViewTarget(iv_01) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.
                                        create(getContext().getResources(), resource);
                        //circularBitmapDrawable.setCornerRadius(8); // 圆角
                        iv_01.setImageDrawable(circularBitmapDrawable);
                    }
                });

        personDetails.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),WebViewActivity.class);
                intent.putExtra(WebViewActivity.URL_KEY, H5UrlData.PEROSNDETAILS+data.getId()+"&member="+ App.getId()+"&mobile="+"13345122570");
                getContext().startActivity(intent);
            }
        });
    }
}
