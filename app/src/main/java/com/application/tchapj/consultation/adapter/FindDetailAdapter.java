package com.application.tchapj.consultation.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.application.tchapj.MainActivity;
import com.application.tchapj.R;
import com.application.tchapj.consultation.activity.FindDetailActivity;
import com.application.tchapj.consultation.bean.CommentInfoBean;
import com.application.tchapj.consultation.bean.CommentReplyInfoBean;
import com.application.tchapj.consultation.bean.MessageNews;
import com.application.tchapj.my.adpter.GridImageAdapter;
import com.application.tchapj.my.fragment.FullyGridLayoutManager;
import com.application.tchapj.utils.GlideUtils;
import com.application.tchapj.utils.ToastUtil;
import com.application.tchapj.utils.Utils;
import com.application.tchapj.utils2.CircleImageView;
import com.application.tchapj.utils2.SDDateUtil;
import com.application.tchapj.utils2.picture.PictureSelector;
import com.application.tchapj.utils2.picture.config.PictureMimeType;
import com.application.tchapj.utils2.picture.entity.LocalMedia;
import com.application.tchapj.utils2.picture.tools.ScreenUtils;
import com.application.tchapj.utils2.qiniu.utils.StringUtils;
import com.application.tchapj.widiget.DensityUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FindDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final String TAG = FindDetailAdapter.class.getSimpleName();

    private FindDetailActivity findDetailActivity;
    private Context context;
    MessageNews newsContent;
    List<CommentInfoBean> commentInfoBeans = new ArrayList<>();
    private final LayoutInflater mLayoutInflater;
    private Integer commentCounts = 0;
    private Integer isAttention = 0;// 1 已关注
    private FindDetailClickListener listener;
    private ViewGroup mParent;


    public enum ViewType{
        NEWS_CONTENT,
        NEWS_CONTENT_COMMENT,
        NEWS_COMMENT
    }


    public void setNewsContent(MessageNews newsContent) {
        this.newsContent = newsContent;
        notifyDataSetChanged();
    }

    public void setCommentData(List<CommentInfoBean> commentInfoBeans, Integer commentCounts){
        this.commentInfoBeans = commentInfoBeans;
        this.commentCounts = commentCounts;
        notifyDataSetChanged();
    }

    public void setCommentCounts(Integer commentCounts) {
        this.commentCounts = commentCounts;
        notifyDataSetChanged();
    }

    public void setIsAttention(Integer isAttention) {
        this.isAttention = isAttention;
        notifyDataSetChanged();
    }

    public void setClickListener(FindDetailClickListener listener){
        this.listener = listener;
    }


    public FindDetailAdapter(FindDetailActivity findDetailActivity, List<CommentInfoBean> commentInfoBeans) {
        this.findDetailActivity = findDetailActivity;
        this.commentInfoBeans = commentInfoBeans;
        this.context = findDetailActivity;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0){
            return ViewType.NEWS_CONTENT.ordinal();
        } else if(position == 1){
            return ViewType.NEWS_CONTENT_COMMENT.ordinal();
        } else{
            return ViewType.NEWS_COMMENT.ordinal();
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mParent = parent;
        if(viewType == ViewType.NEWS_CONTENT.ordinal()){
           return new NewsContentViewHolder(mLayoutInflater.inflate(R.layout.layout_item_find_detail_content, parent, false));
        } else if (viewType == ViewType.NEWS_CONTENT_COMMENT.ordinal()){
            return new NewsContentCommentViewHolder(mLayoutInflater.inflate(R.layout.layout_item_find_detail_content_comment, parent, false));
        } else{
            return new NewsCommentViewHolder(mLayoutInflater.inflate(R.layout.layout_item_find_detail_comment, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        if(holder instanceof NewsContentViewHolder){
            NewsContentViewHolder contentViewHolder = (NewsContentViewHolder) holder;

            if(newsContent != null){
                GlideUtils.loadAvatarCircleImageView(context, newsContent.getR_url(), contentViewHolder.findDetailContentAvatarCiv);
                contentViewHolder.findDetailContentNameTv.setText(newsContent.getR_name());
                contentViewHolder.findDetailContentContentstrTv.setText(newsContent.getConent());
                contentViewHolder.findDetailContentTimeTv.setText(SDDateUtil.formatDuringFrom(newsContent.getCreateTime()));

                if(isAttention != null && isAttention == 1){
                    //已关注
                    contentViewHolder.findDetailContentAttentionTv.setBackgroundResource(R.drawable.find_detail_content_follow_icon);
                }else{
                    //未关注
                    contentViewHolder.findDetailContentAttentionTv.setBackgroundResource(R.drawable.find_detail_content_follow_icon_default);
                }

                if(listener != null){
                    contentViewHolder.findDetailContentAttentionTv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //0：关注  1：取消关注
                            if(isAttention == 1){
                                listener.attentionClick("1");
                            }else{
                                listener.attentionClick("0");
                            }

                        }
                    });
                }

                if ((newsContent.getImgUrl() != null) || (newsContent.getExternalUrl() != null)) {

                String urlStr;
                String pictureType;
                int spanCount;
                //设置九图
                if (StringUtils.isNullOrEmpty(newsContent.getExternalUrl())) {
                    //图片
                    urlStr = newsContent.getImgUrl();
                    pictureType = "image/PNG";
                    spanCount = 3;
                } else {
                    //视频
                    urlStr = newsContent.getExternalUrl();
                    pictureType = "video/mp4";
                    spanCount = 2;
                }

                    final ArrayList<LocalMedia> selectList = new ArrayList<>();
                    if (!StringUtils.isNullOrEmpty(urlStr)) {
                        for (String url : urlStr.split(",")) {
                            LocalMedia imageItem = new LocalMedia();
                            imageItem.pictureType = pictureType;
                            imageItem.path = url;
                            imageItem.setType(newsContent.getNew_model());
                            selectList.add(imageItem);
                            Log.e(TAG, "url = " + url);
                        }
                    }
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    FullyGridLayoutManager manager;
                    if ((!StringUtils.isNullOrEmpty(newsContent.getNew_model()) && newsContent.getNew_model().equals("6"))) {
                        manager = new FullyGridLayoutManager(context, 1
                                , GridLayoutManager.VERTICAL, false);
                    } else {


                        if (selectList != null && selectList.size() == 4) {
                            int recyclerViewWidth = ScreenUtils.getScreenWidth(context) -
                                    ((ScreenUtils.getScreenWidth(context) - DensityUtil.dp2px(context, 25)) / 3) - DensityUtil.dp2px(context, 25);
                            params = new LinearLayout.LayoutParams(recyclerViewWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
                            manager = new FullyGridLayoutManager(context, 2
                                    , GridLayoutManager.VERTICAL, false);
                        } else {
                            manager = new FullyGridLayoutManager(context, spanCount
                                    , GridLayoutManager.VERTICAL, false);
                        }

                    }
                    params.leftMargin = DensityUtil.dp2px(context, 15);
                    params.rightMargin = DensityUtil.dp2px(context, 10);

                    GridImageAdapter gridImageAdapter = new GridImageAdapter(context);
                    gridImageAdapter.setList(selectList);
                    gridImageAdapter.notifyDataSetChanged();
                    gridImageAdapter.setSelectMax(selectList.size());

                    contentViewHolder.find_detail_content_rv.setLayoutParams(params);
                    contentViewHolder.find_detail_content_rv.setLayoutManager(manager);
                    contentViewHolder.find_detail_content_rv.setAdapter(gridImageAdapter);

                    gridImageAdapter.setOnItemClickListener(new GridImageAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(int position, View v) {
                            LocalMedia media = selectList.get(position);
                            String pictureType = media.getPictureType();
                            int mediaType = PictureMimeType.pictureToVideo(pictureType);
                            switch (mediaType) {
                                case 1:
                                    // 预览图片
                                    PictureSelector.create(findDetailActivity).externalPicturePreview(position, selectList);
                                    break;
                                case 2:
                                    // 预览视频
                                    PictureSelector.create(findDetailActivity).externalPictureVideo(media.getPath());
                                    break;
                                case 3:
                                    // 预览音频
                                    PictureSelector.create(findDetailActivity).externalPictureAudio(media.getPath());
                                    break;
                            }
                        }
                    });


                }

            } else {
                contentViewHolder.find_detail_content_rv.setVisibility(View.GONE);
            }
        } else if(holder instanceof NewsContentCommentViewHolder){
            NewsContentCommentViewHolder contentCommentViewHolder = (NewsContentCommentViewHolder) holder;
            contentCommentViewHolder.findDetailContentCommentSizeTv.setText("全部评论  ·  " + commentCounts);
        }else{
            NewsCommentViewHolder commentViewHolder = (NewsCommentViewHolder) holder;
            CommentInfoBean commentInfo;
            if(commentInfoBeans != null && commentInfoBeans.size() > position - 2 && commentInfoBeans.get(position - 2) != null){
                commentInfo = commentInfoBeans.get(position - 2);
                GlideUtils.loadAvatarCircleImageView(context, commentInfo.getHeadimgurl(), commentViewHolder.findDetailCommentCiv);
                commentViewHolder.findDetailCommentNameTv.setText(commentInfo.getName());
                commentViewHolder.findDetailCommentLikesTv.setText(commentInfo.getLikes() + "");//点赞量
                commentViewHolder.findDetailCommentContentTv.setText(commentInfo.getContent());

                commentViewHolder.findDetailCommentTimetv.setText(Utils.getDataFormatString(commentInfo.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));

                if(!StringUtils.isNullOrEmpty(commentInfo.getAuthor()) && commentInfo.getAuthor().equals("1")){// author “0”不是作者  “1”是作者
                    commentViewHolder.findDetailCommentNameTagTv.setVisibility(View.VISIBLE);
                }else{
                    commentViewHolder.findDetailCommentNameTagTv.setVisibility(View.GONE);
                }
                if(!StringUtils.isNullOrEmpty(commentInfo.getState()) && commentInfo.getState().equals("1")){// state“0”未点赞  “1”已点赞
                    commentViewHolder.findDetailCommentLikeIv.setBackgroundResource(R.drawable.find_detail_comment_like_icon);
                }else{
                    commentViewHolder.findDetailCommentLikeIv.setBackgroundResource(R.drawable.find_detail_comment_like_normal_icon);
                }

                if(listener != null){
                    commentViewHolder.findDetailCommentLl.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            listener.addCommentClick(position - 2, -1 );
                        }
                    });
                }

                if(listener != null){
                    commentViewHolder.findDetailCommentLikeLl.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                                listener.commentLikeClick(position - 2);


                        }
                    });
                }


                commentViewHolder.findDetailCommentCommentCountTv.setVisibility(View.GONE);
                if(commentInfo.getReplyList() != null && commentInfo.getReplyList().size() > 0){
                    if(commentInfo.getReplyCounts() > 2){
                        commentViewHolder.findDetailCommentCommentCountTv.setText(commentInfo.getReplyCounts() + "条回复");
                        commentViewHolder.findDetailCommentCommentCountTv.setVisibility(View.VISIBLE);
                        if(listener != null){
                            commentViewHolder.findDetailCommentCommentCountTv.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    listener.startCommentReplyListClick(position - 2);
                                }
                            });
                        }
                    }

                    commentViewHolder.findDetailCommentReplyLl.setVisibility(View.VISIBLE);
                    commentViewHolder.findDetailCommentReplyLl.removeAllViews();
                    for (int i = 0; i < commentInfo.getReplyList().size(); i ++){
                        CommentReplyInfoBean replyInfoBean = commentInfo.getReplyList().get(i);
                        View replyView = mLayoutInflater.inflate(R.layout.layout_item_find_detail_comment_reply, mParent, false);
                        LinearLayout replyLl = replyView.findViewById(R.id.find_detail_comment_reply_linear_layout);
                        CircleImageView replyCiv = replyView.findViewById(R.id.find_detail_comment_reply_civ);
                        TextView replyNameTv = replyView.findViewById(R.id.find_detail_comment_reply_name_tv);
                        TextView replyContentTv = replyView.findViewById(R.id.find_detail_comment_reply_content_tv);
                        TextView replyAuthorTv = replyView.findViewById(R.id.find_detail_comment_reply_author_tag_tv);//作者tag
                        LinearLayout replyLikeLl = replyView.findViewById(R.id.find_detail_comment_reply_like_ll);//点赞布局

                        GlideUtils.loadAvatarCircleImageView(context, replyInfoBean.getFromHeaderUrl(), replyCiv);
                        if(!StringUtils.isNullOrEmpty(replyInfoBean.getFromNickName())){
                            replyNameTv.setText(replyInfoBean.getFromNickName());
                        }
                        if(!StringUtils.isNullOrEmpty(replyInfoBean.getAuthor()) && replyInfoBean.getAuthor().equals("1")){
                            replyAuthorTv.setVisibility(View.VISIBLE);
                        }else{
                            replyAuthorTv.setVisibility(View.GONE);
                        }
                        replyLikeLl.setVisibility(View.GONE);

                        //设置内容content
                        if (replyInfoBean.getReplyType() == 1) {
                            final SpannableStringBuilder style = new SpannableStringBuilder();
                            if (!StringUtils.isNullOrEmpty(replyInfoBean.getToNickName()) && !StringUtils.isNullOrEmpty(replyInfoBean.getReplyContent())) {
                                style.append("回复@" + replyInfoBean.getToNickName() + ":" + replyInfoBean.getReplyContent());
                                /* //设置部分文字点击事件
                                   ClickableSpan clickableSpan = new ClickableSpan() {
                                   @Override
                                   public void onClick(View widget) {
                                       Toast.makeText(context, "触发点击事件!", Toast.LENGTH_SHORT).show();
                                   }

                                   @Override
                                   public void updateDrawState(@NonNull TextPaint ds) {
                                   ds.setUnderlineText(false);
                                  }
                                  };
                            style.setSpan(clickableSpan, 2, 3 + replyInfoBean.getToNickName().length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);*/
                                //设置部分文字颜色
                                ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(Color.parseColor("#ff9296ab"));
                                style.setSpan(foregroundColorSpan, 2, 3 + replyInfoBean.getToNickName().length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
                                replyContentTv.setMovementMethod(LinkMovementMethod.getInstance());
                                replyContentTv.setText(style);
                            }

                        }else{
                            replyContentTv.setText(replyInfoBean.getReplyContent());
                        }

                        if(listener != null){
//                            replyContentTv.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View view) {
//                                    listener.addCommentClickReply();
//                                }
//                            });
                            final int finalI = i;
                            View.OnClickListener clickListener = new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    listener.addCommentClick(position - 2, finalI);
                                }
                            };

                            replyView.setOnClickListener(clickListener);
                            replyContentTv.setOnClickListener(clickListener);
                        }

                        commentViewHolder.findDetailCommentReplyLl.addView(replyView);

                        if(i == 1)
                            break;

                    }
                }else{
                    commentViewHolder.findDetailCommentReplyLl.setVisibility(View.GONE);
                }

            }
        }
    }


    @Override
    public int getItemCount() {
        return commentInfoBeans.size() + 2;
    }

    public class NewsContentViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.find_detail_content_avatar_civ)
        CircleImageView findDetailContentAvatarCiv;
        @BindView(R.id.find_detail_content_name_tv)
        TextView findDetailContentNameTv;
        @BindView(R.id.find_detail_content_contentstr_tv)
        TextView findDetailContentContentstrTv;
        @BindView(R.id.find_detail_content_rv)
        RecyclerView find_detail_content_rv;
        @BindView(R.id.find_detail_content_time_tv)
        TextView findDetailContentTimeTv;
        @BindView(R.id.find_detail_content_attention)
        TextView findDetailContentAttentionTv;

        public NewsContentViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    public class NewsContentCommentViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.find_detail_content_comment_size)
        TextView findDetailContentCommentSizeTv;

        public NewsContentCommentViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class NewsCommentViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.find_detail_comment_civ)
        CircleImageView findDetailCommentCiv;
        @BindView(R.id.find_detail_comment_name_tv)
        TextView findDetailCommentNameTv;
        @BindView(R.id.find_detail_comment_name_tag_tv)
        TextView findDetailCommentNameTagTv;
        @BindView(R.id.find_detail_comment_like_iv)
        public ImageView findDetailCommentLikeIv;
        @BindView(R.id.find_detail_comment_likes_tv)
        TextView findDetailCommentLikesTv;
        @BindView(R.id.find_detail_comment_content_tv)
        TextView findDetailCommentContentTv;
        @BindView(R.id.find_detail_comment_reply_ll)
        LinearLayout findDetailCommentReplyLl;
        @BindView(R.id.find_detail_comment_ll)
        LinearLayout findDetailCommentLl;
        @BindView(R.id.find_detail_comment_time_tv)
        TextView findDetailCommentTimetv;
        @BindView(R.id.find_detail_comment_comment_count_tv)
        TextView findDetailCommentCommentCountTv;
        @BindView(R.id.find_detail_comment_like_ll)
        LinearLayout findDetailCommentLikeLl;

        public NewsCommentViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface FindDetailClickListener{
        void addCommentClick(int commentIndex, int commentReplyIndex);//添加评论
        void attentionClick(String isCancel);//关注
        void startCommentReplyListClick(int commentIndex);//回复列表
        void commentLikeClick(int likeIndex);//点赞
    }
}
