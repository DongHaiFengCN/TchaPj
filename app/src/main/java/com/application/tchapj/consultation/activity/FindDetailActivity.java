package com.application.tchapj.consultation.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.application.tchapj.App;
import com.application.tchapj.R;
import com.application.tchapj.base.BaseBean;
import com.application.tchapj.base.BaseMvpActivity;
import com.application.tchapj.consultation.adapter.FindDetailAdapter;
import com.application.tchapj.consultation.bean.AddNewsCommentResultBean;
import com.application.tchapj.consultation.bean.CommentInfoBean;
import com.application.tchapj.consultation.bean.CommentReplyInfoBean;
import com.application.tchapj.consultation.bean.CommentsResultBean;
import com.application.tchapj.consultation.bean.MessageNews;
import com.application.tchapj.consultation.bean.NewsAttentionResultBean;
import com.application.tchapj.consultation.bean.NewsCommentResultBean;
import com.application.tchapj.consultation.bean.ZanBean;
import com.application.tchapj.consultation.presenter.FindDetailPresenter;
import com.application.tchapj.consultation.view.IFindDetailView;
import com.application.tchapj.utils.CommonUtils;
import com.application.tchapj.utils.ToastUtil;
import com.application.tchapj.utils2.qiniu.utils.StringUtils;
import com.application.tchapj.widiget.DensityUtil;
import com.application.tchapj.widiget.ToolbarHelper;
import com.application.tchapj.widiget.ViewHolder;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;

/***
 * 发现详情页   （原来叫“资讯”）
 * 内容评论页
 */
public class FindDetailActivity extends BaseMvpActivity<IFindDetailView, FindDetailPresenter> implements IFindDetailView, FindDetailAdapter.FindDetailClickListener{

    @BindView(R.id.activity_find_detail_refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.activity_find_detail_list_rv)
    RecyclerView recyclerView;
    @BindView(R.id.find_detail_comment_count_ll)
    LinearLayout commentCountLl;
    @BindView(R.id.find_detail_comment_count_tv)
    TextView commentCountTv;
    @BindView(R.id.find_detail_comment_add_edt)
    EditText addCommentEdt;//评论输入框
    @BindView(R.id.find_detail_comment_add_tv)
    TextView addCommentTv;//发布评论按钮

    private int pageNum = 1;
    private int pageSize = 10;
    FindDetailAdapter findDetailAdapter;

    NewsCommentResultBean newsCommentBean;
    List<CommentInfoBean> commentInfoBeans = new ArrayList<>();

    MessageNews mNews;
    private String commentContent = "";//评论编辑框
    private int commentCounts = 0;
    private int clickTypeAddComment = 1;// 1：点击底部编辑框  2：一级评论  3：二级评论
    private int clickCommentIndex = -1;
    private int clickCommentReplyIndex = -1;
    private String toNickName;
    private int clickPositionStartReply = -1;//X条回复，点击时楼主position
    private int clickPositionLike = -1;//点赞所在的position
    private long mLastTime = 0;//上次点赞时间戳
    private boolean isFormComment = false;
    private LinearLayoutManager linearLayoutManager;
    //滑动到评论
    private int mToMovePosition = -1;
    private boolean mNeedScroll;


    @Override
    protected void initToolbar(ToolbarHelper toolbarHelper) {
        toolbarHelper.setTitle("微呼百应");
    }

    @Override
    public int getRootViewId() {
        return R.layout.activity_find_detail;
    }

    @Override
    public void initUI() {

        findDetailAdapter = new FindDetailAdapter(this, commentInfoBeans);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(findDetailAdapter);
        refreshLayout.setEnableRefresh(false);


        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pageNum ++;
                getNewsComments();
            }
        });

        //默认“发布”按钮隐藏，评论数量显示
        addCommentTv.setVisibility(View.GONE);
        commentCountLl.setVisibility(View.VISIBLE);

        //键盘弹出收起监听
        final View activityRootView = findViewById(R.id.find_detail_rootview);
        activityRootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int heightDiff = activityRootView.getRootView().getHeight() - activityRootView.getHeight();
                if (heightDiff > DensityUtil.dp2px(FindDetailActivity.this, 200)) {
                    //键盘弹出
                    addCommentTv.setVisibility(View.VISIBLE);
                    commentCountLl.setVisibility(View.GONE);
                    addCommentEdt.requestFocus();
                }else{
                    //键盘收起
                    addCommentTv.setVisibility(View.GONE);
                    commentCountLl.setVisibility(View.VISIBLE);
                    addCommentEdt.setText("");
                    addCommentEdt.setHint("评一评");
                    addCommentEdt.clearFocus();
                }
            }
        });


    }

    @Override
    public void initData() {

        Intent intent = getIntent();
        mNews = (MessageNews) intent.getSerializableExtra("news_bean");
        isFormComment = intent.getBooleanExtra("is_form_comment", false);

        findDetailAdapter.setNewsContent(mNews);
        findDetailAdapter.setClickListener(this);

        getNewsComments();

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //进行第二次滚动（最后的距离）
                if (mNeedScroll){
                    mNeedScroll = false;
                    moveToPosition(mToMovePosition);
                }
            }
        });
    }



    private void getNewsComments() {
        if(mNews != null){
            getPresenter().getNewsCommentsResult(mNews.getId(), pageNum, pageSize);
        }

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    protected void onPause() {
        super.onPause();
        CommonUtils.hideSoftKeyboard(addCommentEdt);
    }

    @NonNull
    @Override
    public FindDetailPresenter createPresenter() {
        return new FindDetailPresenter(getApp());
    }

    public static void start(Context context, MessageNews news, boolean isFormComment) {
        Intent starter = new Intent(context, FindDetailActivity.class);
        starter.putExtra("news_bean", news);
        starter.putExtra("is_form_comment", isFormComment);
        context.startActivity(starter);
    }

    //获取单条资讯评论列表返回
    @Override
    public void getNewsCommentsResult(NewsCommentResultBean data) {
        if (data != null) {
            if(pageNum == 1){
                commentInfoBeans.clear();
            }

            newsCommentBean = data;
            commentCounts = data.getCounts();
            commentCountTv.setText(commentCounts + "");//当前文章 总评论数
            commentInfoBeans.addAll(data.getList());
            findDetailAdapter.setIsAttention(data.getIsAttention());
            findDetailAdapter.setCommentData(commentInfoBeans, commentCounts);

            moveToPosition(1);

        }
        if (refreshLayout.isEnableLoadMore()) refreshLayout.finishLoadMore();
        if (refreshLayout.isEnableRefresh()) refreshLayout.finishRefresh();

    }


    //添加评论返回
    @Override
    public void getAddNewsCommentsResult(BaseBean<AddNewsCommentResultBean> baseBean) {
        if(baseBean != null && baseBean.getCode().equals("000") && baseBean.getData() != null){

            ToastUtil.show(this, "评论成功");

            //收起软键盘，隐藏发布按钮，显示评论数
            addCommentEdt.setHint("评一评");
            CommonUtils.hideSoftKeyboard(addCommentEdt);
            commentCountTv.setText(commentCounts + "");

            if (clickTypeAddComment == 1) {
                CommentInfoBean commentInfoBean = new CommentInfoBean();
                commentInfoBean.setId(baseBean.getData().getCommentId());
                commentInfoBean.setHeadimgurl(App.getHeadImgUrl());
                commentInfoBean.setName(App.getNickName());
                commentInfoBean.setContent(commentContent);
                commentInfoBean.setAuthor(baseBean.getData().getIsAuthor());
                commentInfoBean.setCreateTime(System.currentTimeMillis());
                commentCounts ++;
                commentInfoBeans.add(0, commentInfoBean);
            } else if (clickTypeAddComment == 2 || clickTypeAddComment == 3) {
                CommentReplyInfoBean commentReplyInfoBean = new CommentReplyInfoBean();
                commentReplyInfoBean.setId(baseBean.getData().getCommentId());
                commentReplyInfoBean.setFromHeaderUrl(App.getHeadImgUrl());
                commentReplyInfoBean.setFromNickName(App.getNickName());
                commentReplyInfoBean.setReplyContent(commentContent);
                commentReplyInfoBean.setAuthor(baseBean.getData().getIsAuthor());
                commentReplyInfoBean.setFromMemberId(App.getId());
                if(clickTypeAddComment == 3){//0:回复评论 1:回复别人
                    commentReplyInfoBean.setReplyType(1);
                    commentReplyInfoBean.setToNickName(toNickName);
                }else{
                    commentReplyInfoBean.setReplyType(0);
                }
                commentInfoBeans.get(clickCommentIndex).setReplyCounts(commentInfoBeans.get(clickCommentIndex).getReplyCounts() + 1);
                if(commentInfoBeans.get(clickCommentIndex).getReplyList() != null){
                    commentInfoBeans.get(clickCommentIndex).getReplyList().add(0, commentReplyInfoBean);
                }else{
                    ArrayList<CommentReplyInfoBean> replyInfoBeans = new ArrayList<>();
                    replyInfoBeans.add(0, commentReplyInfoBean);
                    commentInfoBeans.get(clickCommentIndex).setReplyList(replyInfoBeans);
                }

            }
            findDetailAdapter.setCommentData(commentInfoBeans, commentCounts);

        }else{
            ToastUtil.show(this, "评论失败");
        }

        clickTypeAddComment = 1;//默认是点击底部评论


    }

    //关注返回
    @Override
    public void getNewsAttentionResult(BaseBean<NewsAttentionResultBean> baseBean) {
        if(baseBean != null && baseBean.getCode().equals("000") && baseBean.getData() != null){
            findDetailAdapter.setIsAttention(baseBean.getData().getAttention());
            if(baseBean.getData().getAttention() == 1){
                ToastUtil.show(this, "关注成功");
            }else {
                ToastUtil.show(this, "取消关注成功");
            }
        }
    }

    //回复评论列表返回（此界面不用）
    @Override
    public void getNewsCommentsReplyResult(BaseBean<CommentsResultBean> baseBean) {

    }

    //点赞返回
    @Override
    public void onGetZanBeanResult(ZanBean zanBean) {
        if(zanBean != null && zanBean.getCode().equals("000")){
            CommentInfoBean commentInfoBean = commentInfoBeans.get(clickPositionLike);
            if(!StringUtils.isNullOrEmpty(commentInfoBean.getState()) && commentInfoBean.getState().equals("1")){
                //取消点赞成功
                commentInfoBean.setState("0");
                commentInfoBean.setLikes(commentInfoBean.getLikes() - 1);
            }else{
                //点赞成功
                commentInfoBean.setState("1");
                commentInfoBean.setLikes(commentInfoBean.getLikes() + 1);
            }

//            FindDetailAdapter.NewsCommentViewHolder commentViewHolder = (FindDetailAdapter.NewsCommentViewHolder) recyclerView.findViewHolderForAdapterPosition(clickPositionLike);
//            if(commentViewHolder != null){
//
//                if(!StringUtils.isNullOrEmpty(commentInfoBean.getState()) && commentInfoBean.getState().equals("1")){// state“0”未点赞  “1”已点赞
//                    commentViewHolder.findDetailCommentLikeIv.setBackgroundResource(R.drawable.find_detail_comment_like_icon);
//                }else{
//                    commentViewHolder.findDetailCommentLikeIv.setBackgroundResource(R.drawable.find_detail_comment_like_normal_icon);
//                }
//
//            }
            findDetailAdapter.notifyDataSetChanged();


        }

    }

    @OnClick({R.id.find_detail_comment_add_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.find_detail_comment_add_tv:

                if(CommonUtils.isLogin(this)){

                    commentContent = addCommentEdt.getText().toString();
                    if(StringUtils.isNullOrEmpty(commentContent)){
                        ToastUtil.show(this, "评论内容不能为空");
                        return;
                    }

                    if(clickTypeAddComment == 1){
                        getPresenter().getAddNewsCommentsResult(mNews.getId(), commentContent);
                    }else if(clickTypeAddComment == 2){
                        getPresenter().getAddNewsReplyCommentsResult(mNews.getId(), commentContent, 0,
                                commentInfoBeans.get(clickCommentIndex).getId(), commentInfoBeans.get(clickCommentIndex).getMemberId());

                    }else if(clickTypeAddComment == 3){
                        getPresenter().getAddNewsReplyCommentsResult(mNews.getId(), commentContent, 1,
                                commentInfoBeans.get(clickCommentIndex).getId(), commentInfoBeans.get(clickCommentIndex).getReplyList().get(clickCommentReplyIndex).getFromMemberId());
                    }

                }

                break;
        }
    }

    //适配器里的一级评论和二级评论点击事件
    @Override
    public void addCommentClick(int commentIndex, int commentReplyIndex) {
        if(CommonUtils.isLogin(this)){
            clickCommentIndex = commentIndex;
            clickCommentReplyIndex = commentReplyIndex;
            if(clickCommentReplyIndex == -1){
                clickTypeAddComment = 2;
                toNickName = commentInfoBeans.get(clickCommentIndex).getName();
            }else{
                clickTypeAddComment = 3;
                toNickName = commentInfoBeans.get(clickCommentIndex).getReplyList().get(commentReplyIndex).getFromNickName();
            }
            addCommentEdt.setHint("回复@" + toNickName);
            CommonUtils.showSoftKeyboard(addCommentEdt);//显示软键盘
        }

    }

    //关注点击事件
    @Override
    public void attentionClick(String isCancel) {
        if(CommonUtils.isLogin(this)){
            if(mNews != null){
                getPresenter().getNewsAttentionResult(mNews.getId(), isCancel);
            }
        }

    }

    //共x条回复点击事件
    @Override
    public void startCommentReplyListClick(int commentIndex) {
        clickPositionStartReply = commentIndex;
        CommentReplyListActivity.start(this, 100, commentInfoBeans.get(commentIndex), commentCounts);
    }

    //点赞、取消点赞
    @Override
    public void commentLikeClick(int likeIndex) {

        if(CommonUtils.isLogin(this)){
            clickPositionLike = likeIndex;
            CommentInfoBean commentInfoBean = commentInfoBeans.get(likeIndex);
            if(!StringUtils.isNullOrEmpty(commentInfoBean.getState()) && commentInfoBean.getState().equals("1")){// state“0”未点赞  “1”已点赞
                getPresenter().getZanBeanResult(commentInfoBean.getId(), "2", "1");
            }else{
                if ((System.currentTimeMillis() - mLastTime) > 30000) {//防止快速点击操作
                    getPresenter().getZanBeanResult(commentInfoBean.getId(), "2", "0");
                    mLastTime = System.currentTimeMillis();
                } else {
                    ToastUtil.show(this, "请不要点赞太频繁");
                }

            }
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 100 && resultCode == Activity.RESULT_OK){
            String addCommenListStr = data.getStringExtra("add_commen_list_str");
            int likeState = data.getIntExtra("like_state", -1);
            if(!StringUtils.isNullOrEmpty(addCommenListStr)){
                List<CommentReplyInfoBean> addCommenList = JSON.parseArray(addCommenListStr, CommentReplyInfoBean.class);
                if(addCommenList != null){
                    for (int i = 0; i < addCommenList.size(); i ++){
                        commentInfoBeans.get(clickPositionStartReply).getReplyList().add(0, addCommenList.get(i));
                    }

                    commentInfoBeans.get(clickPositionStartReply).setReplyCounts(commentInfoBeans.get(clickPositionStartReply).getReplyCounts() + addCommenList.size());//设置评论回复的最新总条数
                }
            }

            if(likeState != -1 && likeState == 0){
                //取消点赞
                commentInfoBeans.get(clickPositionStartReply).setState(likeState + "");
                commentInfoBeans.get(clickPositionStartReply).setLikes(commentInfoBeans.get(clickPositionStartReply).getLikes() - 1);
            }else if(likeState != -1 && likeState == 1){
                //点赞成功
                commentInfoBeans.get(clickPositionStartReply).setState(likeState + "");
                commentInfoBeans.get(clickPositionStartReply).setLikes(commentInfoBeans.get(clickPositionStartReply).getLikes() + 1);
            }
            findDetailAdapter.notifyDataSetChanged();
        }

    }

    private void moveToPosition(int position) {

        if(isFormComment){
            mToMovePosition = position;

            int firstItem = linearLayoutManager.findFirstVisibleItemPosition();// 第一个可见位置
            int lastItem = linearLayoutManager.findLastVisibleItemPosition();// 最后一个可见位置

            if (position < firstItem) {
                recyclerView.smoothScrollToPosition(position);
            } else if (position <= lastItem) {
                int movePosition = position - firstItem;
                if (movePosition >= 0 && movePosition < recyclerView.getChildCount()) {
                    int top = recyclerView.getChildAt(movePosition).getTop();
                    recyclerView.smoothScrollBy(0, top - DensityUtil.dp2px(this, 5));
                }
            } else {
                recyclerView.smoothScrollToPosition(position);
                mToMovePosition = position;
                mNeedScroll = true;
            }
        }

    }

}
