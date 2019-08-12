package com.application.tchapj.video.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.application.tchapj.App;
import com.application.tchapj.H5UrlData;
import com.application.tchapj.R;
import com.application.tchapj.WebViewActivity;
import com.application.tchapj.utils.CommonUtils;
import com.application.tchapj.utils2.CircleImageView;
import com.application.tchapj.utils2.SDDateUtil;
import com.application.tchapj.utils2.qiniu.utils.StringUtils;
import com.application.tchapj.utils2.share.OnekeyShare;
import com.application.tchapj.video.bean.VideosModel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.iflytek.cloud.thirdparty.O;

import java.io.File;
import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;


/**
 * Created by tanger on 2018/3/19.
 */

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {

    private Context mContext;
    private List<VideosModel.VideosResult.Videos> mVideoList;

    public int getPlayIndex() {
        return playIndex;
    }

    private int playIndex = -1;


    public VideoAdapter(Context context) {
        mContext = context;
    }

    public void setData(List<VideosModel.VideosResult.Videos> datas) {
        mVideoList = datas;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mVideoList != null ? mVideoList.size() : 0;
    }

    @Override
    public VideoAdapter.VideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.layout_fragment_item_video, parent, false);
        VideoViewHolder holder = new VideoViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(final VideoViewHolder holder, final int position) {
        final VideosModel.VideosResult.Videos video = mVideoList.get(position);

        //设置视频封面图
        final ImageView thumbImageView = holder.videoPlayer.thumbImageView;
        if (!StringUtils.isNullOrEmpty(video.getImgUrl())) {

            RequestOptions options1 = new RequestOptions().centerCrop()
                    .placeholder(R.color.whitesmoke)
                    .error(R.color.whitesmoke)
                    .diskCacheStrategy(DiskCacheStrategy.ALL); // 缓存策略

            Glide.with(mContext)
                    .asBitmap()
                    .load(video.getImgUrl())
                    .apply(options1)
                    .into(thumbImageView);
        } else {
            //获得视频第一帧当作封面
            CommonUtils.setVideoCover(mContext, video.getExternalUrl(), thumbImageView);
        }
        //播放地址
        holder.videoPlayer.setUp(video.getExternalUrl(), JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL);// ,"视频标题"
        holder.videoPlayer.backButton.setVisibility(View.VISIBLE);//隐藏视频控件中的返回按钮

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.ic_media_head_default)
                .error(R.mipmap.ic_media_head_default)
                .priority(Priority.HIGH)
                .diskCacheStrategy(DiskCacheStrategy.NONE);
        // 设置头像
        Glide.with(mContext)
                .asBitmap()
                .apply(options)
                .load(video.getR_url())
                .into(new BitmapImageViewTarget(((VideoViewHolder) holder).iv_video) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.
                                        create(mContext.getResources(), resource);
                        //circularBitmapDrawable.setCornerRadius(8); // 圆角
                        (holder).iv_video.setImageDrawable(circularBitmapDrawable);
                    }
                });


        (holder).iv_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (StringUtils.isNullOrEmpty(App.getId())) {
                    Intent intent = new Intent(mContext, WebViewActivity.class);

                    intent.putExtra(WebViewActivity.URL_KEY
                            , H5UrlData.Followtails2 + video.getId() + "&memberId=");
                    intent.putExtra(WebViewActivity.TITLE, "");
                    mContext.startActivity(intent);
                } else {
                    Intent intent = new Intent(mContext, WebViewActivity.class);

                    intent.putExtra(WebViewActivity.URL_KEY
                            , H5UrlData.Followtails2 + video.getId() + "&memberId=" + App.getId());
                    intent.putExtra(WebViewActivity.TITLE, "");
                    mContext.startActivity(intent);
                }
            }
        });
        ((VideoViewHolder) holder).mingzi_tv.setText(video.getName()); // 名字

        ((VideoViewHolder) holder).tv_video_time.setText(SDDateUtil.formatDuringFrom(video.getCreateTime())); // 时间

        ((VideoViewHolder) holder).iv_video_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (App.getId() == null) {
                    Intent intent = new Intent(mContext, WebViewActivity.class);

                    intent.putExtra(WebViewActivity.URL_KEY
                            , H5UrlData.Videocomments2 + video.getId() + "&memberId=");
                    intent.putExtra(WebViewActivity.TITLE, "");
                    mContext.startActivity(intent);
                } else {
                    Intent intent = new Intent(mContext, WebViewActivity.class);

                    intent.putExtra(WebViewActivity.URL_KEY
                            , H5UrlData.Videocomments2 + video.getId() + "&memberId=" + App.getId());
                    intent.putExtra(WebViewActivity.TITLE, "");
                    mContext.startActivity(intent);
                }

            }
        });

        ((VideoViewHolder) holder).iv_video_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);            //分享视频只能单个分享
                shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(video.getExternalUrl())));
                shareIntent.setType("audio/*");
                mContext.startActivity(Intent.createChooser(shareIntent, "分享到"));

                /*showShare(video);*/

            }
        });


    }


    class VideoViewHolder extends RecyclerView.ViewHolder {

        public CircleImageView iv_video;

        public TextView mingzi_tv;
        public ImageView iv_video_tab;
        public TextView tv_video_time;
        public ImageView iv_video_comment;
        public ImageView iv_video_share;

        public LinearLayout linearLayout;
        public JCVideoPlayerStandard videoPlayer;

        public VideoViewHolder(View itemView) {
            super(itemView);

            linearLayout = itemView.findViewById(R.id.ll_item);
            iv_video = (CircleImageView) itemView.findViewById(R.id.iv_video);
            mingzi_tv = (TextView) itemView.findViewById(R.id.mingzi_tv);
            iv_video_tab = (ImageView) itemView.findViewById(R.id.iv_video_tab);
            tv_video_time = (TextView) itemView.findViewById(R.id.tv_video_time);
            iv_video_comment = (ImageView) itemView.findViewById(R.id.iv_video_comment);
            iv_video_share = (ImageView) itemView.findViewById(R.id.iv_video_share);
            videoPlayer = itemView.findViewById(R.id.video_play);
        }

    }

    // 显示分享
    public void showShare(final VideosModel.VideosResult.Videos bean) {

        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // 分享时Notification的图标和文字  2.5.9以后的版本不     调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));

        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle(bean.getName());

        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl(bean.getExternalUrl());

        // text是分享文本，所有平台都需要这个字段
        oks.setText(bean.getName());

        // 分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
        oks.setImageUrl(bean.getImgUrl());

        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl(bean.getExternalUrl());

        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("");

        // site是分享此内容的网站名称
        // ，仅在QQ空间使用
        oks.setSite(bean.getName());

        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl(bean.getExternalUrl());

        /*// title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle("分享标题");
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本 http://sharesdk.cn");

        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
        oks.setImageUrl("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg");

        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("https://www.baidu.com/");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("哔哩哔哩");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite("哔哩哔哩b");
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("https://www.baidu.com/");*/

        // 启动分享GUI
        oks.show(mContext);

    }


}
