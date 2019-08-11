package com.application.tchapj.consultation.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.application.tchapj.App;
import com.application.tchapj.R;
import com.application.tchapj.base.BaseBean;
import com.application.tchapj.base.BaseMvpActivity;
import com.application.tchapj.consultation.adapter.CommentReplyListAdapter;
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
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.litepal.util.Const;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 回复列表
 */
public class CommentReplyListActivity extends BaseMvpActivity<IFindDetailView, FindDetailPresenter> implements IFindDetailView, CommentReplyListAdapter.CommentReplyListClickListener {

    @BindView(R.id.activity_find_detail_list_rv)
    RecyclerView recyclerView;
    @BindView(R.id.activity_find_detail_refreshLayout)
    SmartRefreshLayout refreshLayout;
    //发布评论相关
    @BindView(R.id.find_detail_comment_count_ll)
    LinearLayout commentCountLl;
    @BindView(R.id.find_detail_comment_count_tv)
    TextView commentCountTv;
    @BindView(R.id.find_detail_comment_add_edt)
    EditText addCommentEdt;//底部评论输入框，此界面是回复楼主，也就是对评论进行评论
    @BindView(R.id.find_detail_comment_add_tv)
    TextView addCommentTv;//发布评论按钮


    private int pageNum = 1;
    private int pageSize = 10;
    CommentReplyListAdapter commentReplyListAdapter;
    List<CommentReplyInfoBean> commentReplyInfoBeans = new ArrayList<>();
    CommentInfoBean commentInfoBean;

    //发布评论相关
    private String commentContent = "";//评论编辑框
    private int commentCounts = 0;
    private int clickTypeAddComment = 2;// 2：一级评论、点击底部编辑框  3：二级评论
    private int clickCommentReplyIndex = -1;
    private String toNickName;
    List<CommentReplyInfoBean> addCommenList = new ArrayList<>();//新添加的所有评论
    private int likeState = -1;
    private long mLastTime = 0;//上次点赞时间戳
    private int clickPositionLike = -1;//点赞所在的position,-1表示最上方楼主评论

    @Override
    protected void initToolbar(ToolbarHelper toolbarHelper) {
        toolbarHelper.setTitle("回复列表");
    }

    @Override
    public int getRootViewId() {
        return R.layout.activity_find_detail;
    }

    @Override
    public void initUI() {

        commentReplyListAdapter = new CommentReplyListAdapter(this);
        commentReplyListAdapter.setClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(commentReplyListAdapter);

        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pageNum ++;
                getCommentReplyData();
            }
        });

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageNum = 1;
                getCommentReplyData();
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
                if (heightDiff > DensityUtil.dp2px(CommentReplyListActivity.this, 200)) {
                    //键盘弹出
                    addCommentTv.setVisibility(View.VISIBLE);
                    commentCountLl.setVisibility(View.GONE);
                    addCommentEdt.requestFocus();
                    if(clickTypeAddComment == 2){
                        addCommentEdt.setHint("回复@" + commentInfoBean.getName());
                    }
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

        if(getIntent() != null && getIntent().getSerializableExtra("comment_info") instanceof CommentInfoBean){
            commentInfoBean = (CommentInfoBean) getIntent().getSerializableExtra("comment_info");
            commentCounts = getIntent().getIntExtra("comment_counts", 0);
            commentReplyListAdapter.setCommentData(commentInfoBean);
        }
        getCommentReplyData();
    }

    private void getCommentReplyData() {
        if(commentInfoBean != null){
            getPresenter().getNewsCommentsReplyResult(commentInfoBean.getTopicId(), commentInfoBean.getId(), pageNum, pageSize);
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

    public static void start(Activity context, int requestCode, CommentInfoBean commentInfoBean, int commentCounts) {
        Intent starter = new Intent(context, CommentReplyListActivity.class);
        starter.putExtra("comment_info", commentInfoBean);
        starter.putExtra("comment_counts", commentCounts);
        context.startActivityForResult(starter, requestCode);
    }


    //获取单条资讯评论列表返回(此界面不用)
    @Override
    public void getNewsCommentsResult(NewsCommentResultBean data) {
    }

    //添加评论返回
    @Override
    public void getAddNewsCommentsResult(BaseBean<AddNewsCommentResultBean> baseBean) {
        if(baseBean != null && baseBean.getCode().equals("000") && baseBean.getData() != null){

            ToastUtil.show(this, "评论成功");

            //收起软键盘，隐藏发布按钮，显示评论数
            addCommentEdt.setHint("评一评");
            CommonUtils.hideSoftKeyboard(addCommentEdt);
            commentCounts ++;
            commentCountTv.setText(commentCounts + "");

           if (clickTypeAddComment == 2 || clickTypeAddComment == 3) {
                CommentReplyInfoBean commentReplyInfoBean = new CommentReplyInfoBean();
                commentReplyInfoBean.setId(baseBean.getData().getCommentId());
                commentReplyInfoBean.setFromHeaderUrl(App.getHeadImgUrl());
                commentReplyInfoBean.setFromNickName(App.getNickName());
                commentReplyInfoBean.setReplyContent(commentContent);
                commentReplyInfoBean.setAuthor(baseBean.getData().getIsAuthor());
                if(clickTypeAddComment == 3){//0:回复评论 1:回复别人
                    commentReplyInfoBean.setReplyType(1);
                    commentReplyInfoBean.setToNickName(toNickName);
                }else{
                    commentReplyInfoBean.setReplyType(0);
                }
               commentReplyInfoBeans.add(0, commentReplyInfoBean);
               addCommenList.add(commentReplyInfoBean);

            }
            commentReplyListAdapter.setCommentReplyData(commentReplyInfoBeans);

        }else{
            ToastUtil.show(this, "评论失败");
        }

        clickTypeAddComment = 2;//默认点击底部评论，评论的是当前楼主，对评论进行评论

    }

    //关注返回（此界面不用）
    @Override
    public void getNewsAttentionResult(BaseBean<NewsAttentionResultBean> baseBean) {
    }

    //回复评论列表返回
    @Override
    public void getNewsCommentsReplyResult(BaseBean<CommentsResultBean> baseBean) {
        if (baseBean != null && baseBean.getCode().equals("000") && baseBean.getData() != null) {
            if(pageNum == 1){
                commentReplyInfoBeans.clear();
            }
            commentReplyInfoBeans.addAll(baseBean.getData().getList());
            commentReplyListAdapter.setCommentReplyData(commentReplyInfoBeans);
            if((baseBean.getData().getList() == null || baseBean.getData().getList().size() == 0) && refreshLayout.isEnableLoadMore()){
                pageNum --;
            }

        }
        if (refreshLayout.isEnableLoadMore())refreshLayout.finishLoadMore();
        if (refreshLayout.isEnableRefresh()) refreshLayout.finishRefresh();
    }

    //点赞返回
    @Override
    public void onGetZanBeanResult(ZanBean zanBean) {

        if(zanBean != null && zanBean.getCode().equals("000")){
            if(clickPositionLike == -1){
                if(!StringUtils.isNullOrEmpty(commentInfoBean.getState()) && commentInfoBean.getState().equals("1")){
                    //取消点赞成功
                    likeState = 0;
                    commentInfoBean.setState("0");
                    commentInfoBean.setLikes(commentInfoBean.getLikes() - 1);
                }else{
                    //点赞成功
                    likeState = 1;
                    commentInfoBean.setState("1");
                    commentInfoBean.setLikes(commentInfoBean.getLikes() + 1);
                }
            }else{
                CommentReplyInfoBean commentReplyInfoBean = commentReplyInfoBeans.get(clickPositionLike);
                if(!StringUtils.isNullOrEmpty(commentReplyInfoBean.getState()) && commentReplyInfoBean.getState().equals("1")){
                    //取消点赞成功
                    commentReplyInfoBean.setState("0");
                    commentReplyInfoBean.setLikes(commentReplyInfoBean.getLikes() - 1);
                }else{
                    //点赞成功
                    commentReplyInfoBean.setState("1");
                    commentReplyInfoBean.setLikes(commentReplyInfoBean.getLikes() + 1);
                }
            }

            commentReplyListAdapter.notifyDataSetChanged();


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


                    if(CommonUtils.isLogin(this)){

                        if(clickTypeAddComment == 2){
                            getPresenter().getAddNewsReplyCommentsResult(commentInfoBean.getTopicId(), commentContent, 0,
                                    commentInfoBean.getId(), commentInfoBean.getMemberId());

                        }else if(clickTypeAddComment == 3){
                            getPresenter().getAddNewsReplyCommentsResult(commentInfoBean.getTopicId(), commentContent, 1,
                                    commentInfoBean.getId(), commentReplyInfoBeans.get(clickCommentReplyIndex).getFromMemberId());
                        }

                    }

                }
                break;
        }
    }

    //回复
    @Override
    public void addCommentClick(int commentIndex, int commentReplyIndex) {
        clickCommentReplyIndex = commentReplyIndex;
        if(clickCommentReplyIndex == -1){
            clickTypeAddComment = 2;
            toNickName = commentInfoBean.getName();
        }else{
            clickTypeAddComment = 3;
            toNickName = commentReplyInfoBeans.get(commentReplyIndex).getFromNickName();
        }
        addCommentEdt.setHint("回复@" + toNickName);
        CommonUtils.showSoftKeyboard(addCommentEdt);//显示软键盘
    }

    //点赞click
    @Override
    public void commentLikeClick(int likeIndex) {
        if(CommonUtils.isLogin(this)){

            clickPositionLike = likeIndex;
            if(likeIndex == -1){
                //评论点赞
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
            }else{
                //回复评论点赞
                CommentReplyInfoBean commentReplyInfoBean = commentReplyInfoBeans.get(likeIndex);
                if(!StringUtils.isNullOrEmpty(commentReplyInfoBean.getState()) && commentReplyInfoBean.getState().equals("1")){// state“0”未点赞  “1”已点赞
                    getPresenter().getZanBeanResult(commentReplyInfoBean.getId(), "3", "1");
                }else{
                    if ((System.currentTimeMillis() - mLastTime) > 30000) {//防止快速点击操作
                        getPresenter().getZanBeanResult(commentReplyInfoBean.getId(), "3", "0");
                        mLastTime = System.currentTimeMillis();
                    } else {
                        ToastUtil.show(this, "请不要点赞太频繁");
                    }

                }
            }
        }


    }

    //系统返回键
    @Override
    public void onBackPressed() {
        setResultToActivity();
        super.onBackPressed();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                //标题栏的返回键
                setResultToActivity();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    //把添加的评论数据传给上个界面，让发现详情页的评论数据可以实时刷新
    private void setResultToActivity() {
        Intent resultIntent = new Intent();
        if(addCommenList != null && addCommenList.size() > 0){
            String addCommenListStr = JSON.toJSONString(addCommenList);
            resultIntent.putExtra("add_commen_list_str", addCommenListStr);
        }
        if(likeState != -1){
            resultIntent.putExtra("like_state", likeState);
        }
        setResult(Activity.RESULT_OK, resultIntent);
    }
}
