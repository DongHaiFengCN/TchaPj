package com.application.tchapj.consultation.fragment;


import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.application.tchapj.App;
import com.application.tchapj.H5UrlData;
import com.application.tchapj.R;
import com.application.tchapj.WebViewActivity;
import com.application.tchapj.base.BaseModel;
import com.application.tchapj.base.BaseMvpFragment;
import com.application.tchapj.consultation.activity.FindDetailActivity;
import com.application.tchapj.consultation.adapter.ConsultationInfoAdapter;
import com.application.tchapj.consultation.bean.CommentConfig;
import com.application.tchapj.consultation.bean.CommentlogsBean;
import com.application.tchapj.consultation.bean.ConsultationNewsModel;
import com.application.tchapj.consultation.bean.InsertComments;
import com.application.tchapj.consultation.bean.LikelogsBean;
import com.application.tchapj.consultation.bean.MessageNews;
import com.application.tchapj.consultation.bean.ZanBean;
import com.application.tchapj.consultation.presenter.ConsultationInfoPresenter;
import com.application.tchapj.consultation.view.IConsultationInfoView;
import com.application.tchapj.login.activity.LoginMainActivity;
import com.application.tchapj.my.bean.UserModel;
import com.application.tchapj.utils.CommonDialogUtil;
import com.application.tchapj.utils.KeyboardUtility;
import com.application.tchapj.utils.ToastUtil;
import com.application.tchapj.utils2.qiniu.utils.StringUtils;
import com.application.tchapj.utils2.share.SharedPreferencesUtils;
import com.application.tchapj.widiget.CommentListView;
import com.application.tchapj.widiget.LogUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

import static com.application.tchapj.utils.SizeUtil.dip2px;


// 发现信息页面
public class ConsultationInfoFragment extends BaseMvpFragment<IConsultationInfoView, ConsultationInfoPresenter> implements IConsultationInfoView ,ConsultationInfoAdapter.ClickItemListener{

    String TYPE = "TYPE";
    int mPosition;
    String news_id;

    @BindView(R.id.bodyLayout)
    RelativeLayout bodyLayout;

    @BindView(R.id.consultation_recyclerview)
    RecyclerView consultation_recyclerview;
    @BindView(R.id.consultation_refreshLayout)
    SmartRefreshLayout consultation_refreshLayout;

    @BindView(R.id.editTextBodyLl)
    LinearLayout editTextBodyLl;
    @BindView(R.id.comment_et)
    EditText comment_et;
    @BindView(R.id.comment_send)
    Button comment_send;

    private int currentKeyboardH;// 当前软键盘的高度
    private int screenHeight; // 当前屏幕高度
    private int editTextBodyHeight; // 当前输入框的高度
    private int selectCircleItemH; // 当前选中朋友圈的高度
    private int selectCommentItemOffset; // 当前选中评论item的偏移量

    private static CommentConfig commentConfig;
    private LinearLayoutManager layoutManager;

    private int pageNum = 1;
    private int pageSize = 10;
    private String type;
    private int fromType;
    private ConsultationInfoAdapter consultationInfoAdapter;

    private ArrayList<OnSoftKeyboardStateChangedListener> mKeyboardStateListeners;      //软键盘状态监听列表
    private ViewTreeObserver.OnGlobalLayoutListener mLayoutChangeListener;
    private boolean mIsSoftKeyboardShowing;

    private List<MessageNews> ConsultationInfo = new ArrayList<>();
    private int deletePosition = -1;//删除文章的下标
    private int isLikeCancel;

    private HashMap<Integer, Long> mLastTimes = new HashMap<>();//上次点赞时间。key是position

    public enum  FROM_TYPE{
        CONTENT_MANAGER_ACTIVITY,
        CONSULTATION_FRAGMENT
    }


    public ConsultationInfoFragment newInstance(String param1, int fromType) {

        Bundle args = new Bundle();
        ConsultationInfoFragment fragment = new ConsultationInfoFragment();

        fragment.type = param1;
        fragment.fromType = fromType;
        pageNum = 1;

        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_consultationinfo;
    }

    @Override
    public void initUI() {

        // 计算偏移量时 注意
        layoutManager = new LinearLayoutManager(App.getContext());
        consultation_recyclerview.setLayoutManager(layoutManager);

        consultationInfoAdapter = new ConsultationInfoAdapter(ConsultationInfoFragment.this,getContext(),this);

        if(fromType == FROM_TYPE.CONTENT_MANAGER_ACTIVITY.ordinal()){
            consultationInfoAdapter.setCurrent_item_style(ConsultationInfoAdapter.ITEM_VIEW_STYLE.ITEM_VIEW_STYLE_SHOW_DELETE.ordinal());
        }
        consultationInfoAdapter.setClickItemListener(this);
        consultation_recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        //添加自定义分割线
        DividerItemDecoration divider = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.recycler_view_line_divider));
        consultation_recyclerview.addItemDecoration(divider);
        consultation_recyclerview.setAdapter(consultationInfoAdapter);

        setViewTreeObserver();

        initListener();
    }

    private void initListener() {

        consultation_refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout consultation_refreshLayout) {
                pageNum = 1;
                if (ConsultationInfo!=null){
                    ConsultationInfo.clear();
                }
                getType();
            }
        });

        consultation_refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout consultation_refreshLayout) {
                pageNum++;
                getType();
            }
        });

        consultation_recyclerview.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                } else {
                }
            }
        });

        // 触发recyclerview的时候关闭
        consultation_recyclerview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                if (editTextBodyLl.getVisibility() == View.VISIBLE) {
                    updateEditTextBodyVisible(View.GONE, null);
                    return true;
                }
                return false;
            }
        });

        // 发布评论
        comment_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = comment_et.getText().toString().trim();
                if (TextUtils.isEmpty(content)) {
                    Toast.makeText(getContext(), "评论内容不能为空...", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (App.getId() == null) {
                    dialogs();
                } else  {
                    getPresenter().getInsertCommentsResult(content,App.getId(),news_id);
                }

                CommentlogsBean item = new CommentlogsBean();

                item.setContent(content);

                if(SharedPreferencesUtils.getInstance().getNickName()!=null){
                    item.setMemberId(SharedPreferencesUtils.getInstance().getNickName());
                }else {
                    item.setMemberId("微呼百应会员");
                }

                updateAddComment(commentConfig.circlePosition, item);
                updateEditTextBodyVisible(View.GONE, null);

                /*CommentsBean commentsBean = null;
                if (commentConfig.commentType == CommentConfig.Type.PUBLIC) { //自己发表评论
                    commentsBean = DataTest.createPublicComment(content);
                } else if (commentConfig.commentType == CommentConfig.Type.REPLY) {//回复别人的评论
                    commentsBean = DataTest.createReplyComment(commentConfig.replyUser, content);
                }
                updateAddComment(commentConfig.circlePosition, commentsBean);
                updateEditTextBodyVisible(View.GONE, null);*/

            }
        });

    }

    @Override // 回复评论
    public void onItemButtonClick(CommentConfig config ,String id,int size) {
        commentConfig = config;
        news_id = id;
        if (commentConfig.commentType == CommentConfig.Type.REPLY) {
            comment_et.setHint("回复" + commentConfig.replyUser.getUsername() + ":");
        }
        updateEditTextBodyVisible(View.VISIBLE, commentConfig);
    }

    //删除文章
    @Override
    public void onDeleteArticleClick(final String news_Id, final int deletePosition) {
        CommonDialogUtil.consultationDeleteDialog(getActivity(),  new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                getPresenter().deleteNewsResult(news_Id);
                ConsultationInfoFragment.this.deletePosition = deletePosition;
            }
        });



    }

    @Override
    public void onItemClick(int position) {
        //item点击事件，目前只有9图模式有设置
        if(ConsultationInfo != null && position >= 0 && position < ConsultationInfo.size()){
            FindDetailActivity.start(getActivity(), ConsultationInfo.get(position), false);
        }
    }

    //9图模式、长视频 头像一栏点击事件
    @Override
    public void onHeadImgClick(MessageNews news) {
        if(news != null){
            //名人/媒体  resourceId http://api.whby.ctrl.cn/weihubaiying/index.html?Id=000&memberId=72376893f972475988cd77fd468ce9bb
            //达人  tid weihubaiying/gerenQZ.html?Id=

            String url;
            if (StringUtils.isNullOrEmpty(news.getTid())) {
                //名人/媒体
                url = H5UrlData.PEROSNDETAILS2 + news.getResourcesId() + "&memberId=" + App.getId();

            } else {
                //达人 只有达人有tid
                url = H5UrlData.Circletails2 + news.getTid() + "&memberId=" + App.getId();
            }

            Intent intent = new Intent(getActivity(), WebViewActivity.class);
            intent.putExtra(WebViewActivity.URL_KEY, url);
            intent.putExtra(WebViewActivity.TITLE, "");
            startActivity(intent);
        }



    }

    @Override // 删除动态
    public void onDeleteItemButtonClick(int position, int commentId) {

    }

    @Override // 添加/取消赞
    public void addOrCancelLikes(int position, MessageNews news) {
        mPosition = position;
        if (StringUtils.isNullOrEmpty(App.getId())) {
            dialogs();
        } else {
            if (news.getIsLike() == 1) {
                //取消点赞
                isLikeCancel = 1;
                updateAddPraise(mPosition);
                getPresenter().getZanBeanResult(news.getId(), "1", isLikeCancel + "");
            } else {
                //点赞
                if (mLastTimes.get(position) == null || (System.currentTimeMillis() - mLastTimes.get(position)) > 30000) {//防止快速点击操作
                    isLikeCancel = 0;
                    updateAddPraise(mPosition);
                    getPresenter().getZanBeanResult(news.getId(), "1", isLikeCancel + "");
                    mLastTimes.put(position, System.currentTimeMillis());
                } else {
                    ToastUtil.show(getActivity(), "请不要点赞太频繁");
                }
            }
        }
    }

    @Override // 初始化数据
    public void initData() {
        getType();
        getPresenter().getUserModelResult(App.getId());
    }

    // 判断fragment类型
    private void getType(){
        if(fromType == FROM_TYPE.CONSULTATION_FRAGMENT.ordinal()){
            //发现 tab页
            if (type.equals("推荐")){
                getPresenter().getConsultationResultAll(pageNum+"",pageSize+"");
                //getPresenter().getConsultationResult("0",pageNum+"",pageSize+"");
            }else {
                getPresenter().getConsultationResult(type,pageNum+"",pageSize+"");
                Log.e("类型", type);
            }
        }else{
            //内容管理-我的资讯列表
            getPresenter().getUserNewsList(App.getId(), type, pageNum+"",pageSize+"");

        }

    }

    @Override
    public ConsultationInfoPresenter createPresenter() {
        return new ConsultationInfoPresenter(getApp());
    }

    @Override
    public void onGetConsultationInfoResultAll(ConsultationNewsModel consultationNewsModel) {

        if ("000".equals(consultationNewsModel.getCode())) {
            if (pageNum==1){
                ConsultationInfo = consultationNewsModel.getData().getNews();
            }else{
                ConsultationInfo.addAll(consultationNewsModel.getData().getNews());
            }
            consultationInfoAdapter.setData(ConsultationInfo);
            if (consultation_refreshLayout.isEnableRefresh()){
                consultation_refreshLayout.finishRefresh();
            }
            if (consultation_refreshLayout.isEnableLoadMore()){
                consultation_refreshLayout.finishLoadMore();
            }
        }
    }

    @Override
    public void onGetConsultationInfoResult(ConsultationNewsModel consultationNewsModel) {

        if ("000".equals(consultationNewsModel.getCode())) {
            if (pageNum==1){
                ConsultationInfo = consultationNewsModel.getData().getNews();
            }else{
                ConsultationInfo.addAll(consultationNewsModel.getData().getNews());
            }
            consultationInfoAdapter.setData(ConsultationInfo);
            if (consultation_refreshLayout.isEnableRefresh()){
                consultation_refreshLayout.finishRefresh();
            }
            if (consultation_refreshLayout.isEnableLoadMore()){
                consultation_refreshLayout.finishLoadMore();
            }
        }

    }

    @Override
    public void onGetUserModelResult(UserModel userModelBean) {

        SharedPreferencesUtils.getInstance().setNickName(userModelBean.getData().getNickName());
    }

    //删除文章结果
    @Override
    public void onDeleteNewsRespon(BaseModel baseModel) {
        if(baseModel != null && baseModel.getCode().equals("000")){
            ToastUtil.show(getActivity(), "删除成功");
            if(deletePosition >= 0){
                ConsultationInfo.remove(deletePosition);
                consultationInfoAdapter.notifyDataSetChanged();
            }


        }

    }

    @Override // 发评论
    public void onGetInsertCommentsResult(InsertComments insertComments) {

        if ("000".equals(insertComments.getCode())) {

            Toast.makeText(getContext(), "发表成功", Toast.LENGTH_SHORT).show();

        }

    }

    @Override // 点赞
    public void onGetZanBeanResult(ZanBean zanBean) {

        if ("000".equals(zanBean.getCode())) {
            ZanBean zanBeans = zanBean;
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
        LogUtils.w(e);

        if (consultation_refreshLayout.isEnableRefresh()){
            consultation_refreshLayout.finishRefresh();
        }
        if (consultation_refreshLayout.isEnableLoadMore()){
            consultation_refreshLayout.finishLoadMore();
        }
    }

    private void dialogs() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("请先进行登录");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Intent intent = new Intent(getContext(), LoginMainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  //最关键是这句
                startActivity(intent);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.dismiss();
            }
        });

        builder.create().show();
    }

    // 计算软键盘的高度
    private void setViewTreeObserver() {

        final ViewTreeObserver swipeRefreshLayoutVTO = bodyLayout.getViewTreeObserver();
        swipeRefreshLayoutVTO.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect r = new Rect();
                bodyLayout.getWindowVisibleDisplayFrame(r);

                mIsSoftKeyboardShowing = false;
                mKeyboardStateListeners = new ArrayList<OnSoftKeyboardStateChangedListener>();
                //如果屏幕高度和Window可见区域高度差值大于整个屏幕高度的1/3，则表示软键盘显示中，否则软键盘为隐藏状态。
                int heightDifference = screenHeight - (r.bottom - r.top);
                boolean isKeyboardShowing = heightDifference > screenHeight/3;
                //如果之前软键盘状态为显示，现在为关闭，或者之前为关闭，现在为显示，则表示软键盘的状态发生了改变
                if ((mIsSoftKeyboardShowing && !isKeyboardShowing) || (!mIsSoftKeyboardShowing && isKeyboardShowing)) {
                    mIsSoftKeyboardShowing = isKeyboardShowing;
                    for (int i = 0; i < mKeyboardStateListeners.size(); i++) {
                        OnSoftKeyboardStateChangedListener listener = mKeyboardStateListeners.get(i);
                        listener.OnSoftKeyboardStateChanged(mIsSoftKeyboardShowing, heightDifference);
                    }
                }

                int statusBarH = getStatusBarHeight();//状态栏高度
                int screenH = bodyLayout.getRootView().getHeight();
                if (r.top != statusBarH) {
                    //在这个demo中r.top代表的是状态栏高度，在沉浸式状态栏时r.top＝0，通过getStatusBarHeight获取状态栏高度
                    r.top = statusBarH;
                }
                int keyboardH = screenH - (r.bottom - r.top);
                Log.d(TYPE, "screenH＝ " + screenH + " &keyboardH = " + keyboardH + " &r.bottom=" + r.bottom + " &top=" + r.top + " &statusBarH=" + statusBarH);

                if (keyboardH == currentKeyboardH) {//有变化时才处理，否则会陷入死循环
                    return;
                }
                currentKeyboardH = keyboardH;
                screenHeight = screenH;//应用屏幕的高度
                editTextBodyHeight = editTextBodyLl.getHeight();

                if (keyboardH < 150) {//说明是隐藏键盘的情况
                    updateEditTextBodyVisible(View.GONE, null);
                    RelativeLayout.LayoutParams params2 = (RelativeLayout.LayoutParams) editTextBodyLl.getLayoutParams();
                    params2.width = dip2px(getContext(), bodyLayout.getRootView().getWidth());
                    params2.height = dip2px(getContext(), 0);
                    editTextBodyLl.setLayoutParams(params2);
                    return;
                }else {
                    RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) editTextBodyLl.getLayoutParams();
                    params.width = dip2px(getContext(), bodyLayout.getRootView().getWidth());
                    params.height = dip2px(getContext(), heightDifference/3-50);
                    editTextBodyLl.setLayoutParams(params);

                }

                //偏移listview
                if (layoutManager != null && commentConfig != null) {
                    layoutManager.scrollToPositionWithOffset(commentConfig.circlePosition + ConsultationInfoAdapter.HEADVIEW_SIZE, getListViewOffset(commentConfig));
                }
            }
        });
    }

    public interface OnSoftKeyboardStateChangedListener {
        public void OnSoftKeyboardStateChanged(boolean isKeyBoardShow, int keyboardHeight);
    }

    // 获取状态栏高度
    private int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    // 测量偏移量 @param commentConfig @return
    private int getListViewOffset(CommentConfig commentConfig) {
        if (commentConfig == null)
            return 0;
        //这里如果你的listview上面还有其它占高度的控件，则需要减去该控件高度，listview的headview除外。
        //int listviewOffset = mScreenHeight - mSelectCircleItemH - mCurrentKeyboardH - mEditTextBodyHeight;
        /*int listviewOffset = screenHeight - selectCircleItemH - currentKeyboardH - editTextBodyHeight - topTitle.getHeight();*/
        int listviewOffset = screenHeight - selectCircleItemH - currentKeyboardH - editTextBodyHeight;
        if (commentConfig.commentType == CommentConfig.Type.REPLY) {
            //回复评论的情况
            listviewOffset = listviewOffset + selectCommentItemOffset;
        }
        Log.e(TYPE, "listviewOffset : " + listviewOffset);
        return listviewOffset;
    }

    // 设置软键盘的显示隐藏 @param visibility
    public void updateEditTextBodyVisible(int visibility, CommentConfig config) {
        commentConfig = config;
        editTextBodyLl.setVisibility(visibility);
        // 计算偏移量
        measureCircleItemHighAndCommentItemOffset(commentConfig);
        if (View.VISIBLE == visibility) {
            comment_et.requestFocus();
            //弹出键盘
            KeyboardUtility.showSoftInput(comment_et.getContext(), comment_et);
        } else if (View.GONE == visibility) {
            //隐藏键盘
            KeyboardUtility.hideSoftInput(comment_et.getContext(), comment_et);

        }
    }

    // 计算偏移量
    private void measureCircleItemHighAndCommentItemOffset(CommentConfig commentConfig) {
        if (commentConfig == null)
            return;

        int firstPosition = layoutManager.findFirstVisibleItemPosition();
        //只能返回当前可见区域（列表可滚动）的子项
        View selectCircleItem = layoutManager.getChildAt(commentConfig.circlePosition + ConsultationInfoAdapter.HEADVIEW_SIZE - firstPosition);

        if (selectCircleItem != null) {
            selectCircleItemH = selectCircleItem.getHeight();
        }
        if (commentConfig.commentType == CommentConfig.Type.REPLY) {
            //回复评论的情况
            CommentListView commentLv = (CommentListView) selectCircleItem.findViewById(R.id.commentList);
            if (commentLv != null) {
                //找到要回复的评论view,计算出该view距离所属动态底部的距离
                View selectCommentItem = commentLv.getChildAt(commentConfig.commentPosition);
                if (selectCommentItem != null) {
                    //选择的commentItem距选择的CircleItem底部的距离
                    selectCommentItemOffset = 0;
                    View parentView = selectCommentItem;
                    do {
                        int subItemBottom = parentView.getBottom();
                        parentView = (View) parentView.getParent();
                        if (parentView != null) {
                            selectCommentItemOffset += (parentView.getHeight() - subItemBottom);
                        }
                    } while (parentView != null && parentView != selectCircleItem);
                }
            }
        }
    }

    // 添加评论  @param circlePosition  @param addItem
    public void updateAddComment(int Position, CommentlogsBean addItem) {
        if (addItem != null) {
            MessageNews news = consultationInfoAdapter.getDatas().get(Position);
            if (null != news.getCommentlogs()) {
                news.getCommentlogs().add(addItem);
            } else {
                List<CommentlogsBean> list = new ArrayList<>();
                list.add(addItem);
                news.setCommentlogs(list);
            }
            consultationInfoAdapter.notifyDataSetChanged();
        }
        //清空评论文本
        comment_et.setText("");
    }

    // 添加赞
    public void updateAddPraise(int position) {
        LikelogsBean addItem = new LikelogsBean();

        if(SharedPreferencesUtils.getInstance().getNickName()!=null){
            addItem.setNickName(SharedPreferencesUtils.getInstance().getNickName());
        }else {
            addItem.setNickName("微呼百应会员");
        }

        MessageNews news = consultationInfoAdapter.getDatas().get(position);

        if (null != news.getLikelogs()) {
            news.getLikelogs().add(addItem);
        } else {
            List<LikelogsBean> list = new ArrayList<>();
            list.add(addItem);
            news.setLikelogs(list);
        }
        ConsultationInfoAdapter.SudokuViewHolder viewHolder = (ConsultationInfoAdapter.SudokuViewHolder) consultation_recyclerview.findViewHolderForAdapterPosition(position);
        // 设置点赞
        String isLikeStr = "";
        if (isLikeCancel == 0) {
            //点赞
            news.setIsLike(1);
            news.setLikeTotal(news.getLikeTotal() + 1);
            isLikeStr = "成功点赞";
            viewHolder.album_praise.setImageResource(R.mipmap.circle_dynamic_praise_s);
        } else if (isLikeCancel == 1) {
            //取消点赞
            news.setIsLike(0);
            news.setLikeTotal(news.getLikeTotal() - 1);
            isLikeStr = "取消点赞";
            viewHolder.album_praise.setImageResource(R.mipmap.circle_dynamic_praise_n);
        }
        consultationInfoAdapter.getDatas().set(position, news);
        viewHolder.album_praise_number.setText(news.getLikeTotal() + "");

        if (!StringUtils.isNullOrEmpty(isLikeStr)) {
            Toast.makeText(getContext(), isLikeStr, Toast.LENGTH_SHORT).show();
        }

    }

}
