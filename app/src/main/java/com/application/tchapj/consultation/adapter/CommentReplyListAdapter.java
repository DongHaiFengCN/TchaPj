package com.application.tchapj.consultation.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.application.tchapj.R;
import com.application.tchapj.consultation.bean.CommentInfoBean;
import com.application.tchapj.consultation.bean.CommentReplyInfoBean;
import com.application.tchapj.utils.GlideUtils;
import com.application.tchapj.utils.Utils;
import com.application.tchapj.utils2.CircleImageView;
import com.application.tchapj.utils2.qiniu.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CommentReplyListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final LayoutInflater mLayoutInflater;
    private final Context mContext;
    private List<CommentReplyInfoBean> commentReplyInfoBeanList = new ArrayList<>();
    private CommentInfoBean commentInfo;

    private CommentReplyListClickListener listener;

    public enum ITEM_TYPE {
        ITEM_TYPE_COMMENT,
        ITEM_TYPE_COMMENT_REPLY
    }

    public CommentReplyListAdapter(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        notifyDataSetChanged();
    }

    public void setCommentReplyData(List<CommentReplyInfoBean> commentReplyInfoBeanList) {
        this.commentReplyInfoBeanList = commentReplyInfoBeanList;
        notifyDataSetChanged();
    }


    public void setCommentData(CommentInfoBean commentInfo) {
        this.commentInfo = commentInfo;
        notifyDataSetChanged();
    }

    public void setClickListener(CommentReplyListClickListener listener){
        this.listener = listener;
    }


    @Override
    public int getItemCount() {
        return 1 + commentReplyInfoBeanList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0){
            return ITEM_TYPE.ITEM_TYPE_COMMENT.ordinal();
        }else{
            return ITEM_TYPE.ITEM_TYPE_COMMENT_REPLY.ordinal();
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == ITEM_TYPE.ITEM_TYPE_COMMENT.ordinal()){
            return new NewsCommentViewHolder(mLayoutInflater.inflate(R.layout.layout_item_find_detail_comment, parent, false));
        }else{
            return new NewsCommentReplyViewHolder(mLayoutInflater.inflate(R.layout.layout_item_find_detail_comment_reply, parent, false));
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof NewsCommentViewHolder) {
            final NewsCommentViewHolder commentViewHolder = (NewsCommentViewHolder) holder;
            GlideUtils.loadAvatarCircleImageView(mContext, commentInfo.getHeadimgurl(), commentViewHolder.findDetailCommentCiv);
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
                commentViewHolder.findDetailCommentLikeLl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        listener.commentLikeClick(-1);


                    }
                });
            }




            if(listener != null){
                commentViewHolder.findDetailCommentLl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        listener.addCommentClick(0, -1 );
                    }
                });
            }

            commentViewHolder.findDetailCommentCommentCountTv.setVisibility(View.GONE);
            commentViewHolder.findDetailCommentView.setVisibility(View.GONE);

        }else if(holder instanceof NewsCommentReplyViewHolder){
            NewsCommentReplyViewHolder commentReplyViewHolder = (NewsCommentReplyViewHolder) holder;
            CommentReplyInfoBean replyInfoBean = commentReplyInfoBeanList.get(position - 1);
            GlideUtils.loadAvatarCircleImageView(mContext, replyInfoBean.getFromHeaderUrl(), commentReplyViewHolder.replyCiv);
            if (!StringUtils.isNullOrEmpty(replyInfoBean.getFromNickName())) {
                commentReplyViewHolder.replyNameTv.setText(replyInfoBean.getFromNickName());
            }
            if (!StringUtils.isNullOrEmpty(replyInfoBean.getAuthor()) && replyInfoBean.getAuthor().equals("1")) {
                commentReplyViewHolder.replyAuthorTv.setVisibility(View.VISIBLE);
            } else {
                commentReplyViewHolder.replyAuthorTv.setVisibility(View.GONE);
            }

            //设置点赞
            commentReplyViewHolder.replyLikeLl.setVisibility(View.VISIBLE);
            if(!StringUtils.isNullOrEmpty(replyInfoBean.getState()) && replyInfoBean.getState().equals("1")){// state“0”未点赞  “1”已点赞
                commentReplyViewHolder.replyLikeIv.setBackgroundResource(R.drawable.find_detail_comment_like_icon);
            }else{
                commentReplyViewHolder.replyLikeIv.setBackgroundResource(R.drawable.find_detail_comment_like_normal_icon);
            }

            if(listener != null){
                commentReplyViewHolder.replyLikeLl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        listener.commentLikeClick(position - 1);


                    }
                });
            }

            commentReplyViewHolder.replyLikeTv.setText(replyInfoBean.getLikes() + "");//点赞量


            //设置内容content
            if (replyInfoBean.getReplyType() == 1) {
                final SpannableStringBuilder style = new SpannableStringBuilder();
                if (!StringUtils.isNullOrEmpty(replyInfoBean.getToNickName()) && !StringUtils.isNullOrEmpty(replyInfoBean.getReplyContent())) {
                    style.append("回复@" + replyInfoBean.getToNickName() + ":" + replyInfoBean.getReplyContent());
                    //设置部分文字颜色
                    ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(Color.parseColor("#ff9296ab"));
                    style.setSpan(foregroundColorSpan, 2, 3 + replyInfoBean.getToNickName().length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
                    commentReplyViewHolder.replyContentTv.setMovementMethod(LinkMovementMethod.getInstance());
                    commentReplyViewHolder.replyContentTv.setText(style);
                }

            } else {
                commentReplyViewHolder.replyContentTv.setText(replyInfoBean.getReplyContent());
            }

            if(listener != null){
                commentReplyViewHolder.replyLl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        listener.addCommentClick(0, position - 1);
                    }
                });
            }

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
        ImageView findDetailCommentLikeIv;
        @BindView(R.id.find_detail_comment_likes_tv)
        TextView findDetailCommentLikesTv;
        @BindView(R.id.find_detail_comment_content_tv)
        TextView findDetailCommentContentTv;
        @BindView(R.id.find_detail_comment_ll)
        LinearLayout findDetailCommentLl;
        @BindView(R.id.find_detail_comment_time_tv)
        TextView findDetailCommentTimetv;
        @BindView(R.id.find_detail_comment_comment_count_tv)
        TextView findDetailCommentCommentCountTv;
        @BindView(R.id.find_detail_comment_view)
        View findDetailCommentView;
        @BindView(R.id.find_detail_comment_like_ll)
        LinearLayout findDetailCommentLikeLl;

        public NewsCommentViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class NewsCommentReplyViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.find_detail_comment_reply_linear_layout)
        LinearLayout replyLl;
        @BindView(R.id.find_detail_comment_reply_civ)
        CircleImageView replyCiv;
        @BindView(R.id.find_detail_comment_reply_name_tv)
        TextView replyNameTv;
        @BindView(R.id.find_detail_comment_reply_content_tv)
        TextView replyContentTv;
        @BindView(R.id.find_detail_comment_reply_author_tag_tv)
        TextView replyAuthorTv;
        @BindView(R.id.find_detail_comment_reply_like_ll)
        LinearLayout replyLikeLl;
        @BindView(R.id.find_detail_comment_reply_like_iv)
        ImageView replyLikeIv;
        @BindView(R.id.find_detail_comment_reply_likes_tv)
        TextView replyLikeTv;

        public NewsCommentReplyViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface CommentReplyListClickListener{
        void addCommentClick(int commentIndex, int commentReplyIndex);//添加评论
        void commentLikeClick(int likeIndex);//点赞
    }

}
