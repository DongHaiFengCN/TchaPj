package com.application.tchapj.consultation.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.application.tchapj.App;
import com.application.tchapj.H5UrlData;
import com.application.tchapj.R;
import com.application.tchapj.WebViewActivity;
import com.application.tchapj.consultation.activity.FindDetailActivity;
import com.application.tchapj.consultation.bean.CommentConfig;
import com.application.tchapj.consultation.bean.CommentlogsBean;
import com.application.tchapj.consultation.bean.LikelogsBean;
import com.application.tchapj.consultation.bean.MessageNews;
import com.application.tchapj.consultation.fragment.ConsultationInfoFragment;
import com.application.tchapj.my.adpter.GridImageAdapter;
import com.application.tchapj.my.fragment.FullyGridLayoutManager;
import com.application.tchapj.search.activity.SearchInfoActivity;
import com.application.tchapj.utils.CommonUtils;
import com.application.tchapj.utils.MDDialog;
import com.application.tchapj.utils.ToastUtil;
import com.application.tchapj.utils2.SDDateUtil;
import com.application.tchapj.utils2.imagenice9layout.DemoEntity;
import com.application.tchapj.utils2.imagenice9layout.ListAdapter;
import com.application.tchapj.utils2.pickers.util.LogUtils;
import com.application.tchapj.utils2.picture.PictureSelector;
import com.application.tchapj.utils2.picture.config.PictureMimeType;
import com.application.tchapj.utils2.picture.entity.LocalMedia;
import com.application.tchapj.utils2.picture.tools.ScreenUtils;
import com.application.tchapj.utils2.qiniu.utils.StringUtils;
import com.application.tchapj.utils2.videoplayer.NiceVideoPlayer;
import com.application.tchapj.utils2.videoplayer.TxVideoPlayerController;
import com.application.tchapj.widiget.CommentListView;
import com.application.tchapj.widiget.DensityUtil;
import com.application.tchapj.widiget.PraiseListView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by tanger on 2018/3/19.
 */

public class ConsultationInfoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static int IsLike = 0;

    public final static int TYPE_HEAD = 0;
    public final static int TYPE_CONTENT = 1;
    public static final int HEADVIEW_SIZE = 1;

    private final LayoutInflater mLayoutInflater;
    private final Context mContext;
    private SearchInfoActivity mActivity;
    private ConsultationInfoFragment mconsultationInfoFragment;

    private List<MessageNews> datas;

    List<DemoEntity> pictures = new ArrayList<>();
    ListAdapter mListAdapter;

    private GridImageAdapter gridImageAdapter;

    int width;

    boolean isExpand = true;

    private ClickItemListener mListener = null;//单击事件监听器

    private String deleteItems[] = {"删除评论"};

    public static enum ITEM_TYPE {
        ITEM_TYPE_IMAGE,
        ITEM_TYPE_IMAGE3,
        ITEM_TYPE_IMAGE1,
        ITEM_TYPE_IMAGE9,
        ITEM_TYPE_VIDEO
    }

    public enum ITEM_VIEW_STYLE {
        ITEM_VIEW_STYLE_NORMAL,
        ITEM_VIEW_STYLE_SHOW_DELETE
    }

    public int current_item_style = ITEM_VIEW_STYLE.ITEM_VIEW_STYLE_NORMAL.ordinal();

    //朋友圈模式展开收起
    private final int MAX_LINE_COUNT = 6;//最大显示行数
    private final int STATE_UNKNOW = -1;//未知状态
    private final int STATE_NOT_OVERFLOW = 1;//文本行数小于最大可显示行数状态
    private final int STATE_COLLAPSED = 2;//折叠状态
    private final int STATE_EXPANDED = 3;//展开状态
    /**
     * 注意：保存文本状态集合的key一定要是唯一的，如果用position。
     * 如果使用position作为key，则删除、增加条目的时候会出现显示错乱
     * 拓展：
     * 1.SparseArray是key固定是int类型，单纯数组的结合.被称为稀疏数组,不会有额外的开销, 也是以key和value对数据进行保存的,存储数据占用的内存空间比HashMap要小一些
     * 2.HashMap是数组和链表的结合体,被称为链表散列.
     */
    private HashMap<String, Integer> mTextStateList;//保存文本状态集合



    public void setCurrent_item_style(int current_item_style) {
        this.current_item_style = current_item_style;
        notifyDataSetChanged();
    }

    public ConsultationInfoAdapter(ConsultationInfoFragment consultationInfoFragment, Context context, ClickItemListener listener) {
        mconsultationInfoFragment = consultationInfoFragment;
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        width = wm.getDefaultDisplay().getWidth();

        this.mListener = listener;
        mTextStateList = new HashMap<>();
    }

    public ConsultationInfoAdapter(SearchInfoActivity activity, ClickItemListener listener) {
        mActivity = activity;
        mContext = activity;
        mLayoutInflater = LayoutInflater.from(mContext);
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        width = wm.getDefaultDisplay().getWidth();
        this.mListener = listener;
        mTextStateList = new HashMap<>();

    }


    public void setData(List<MessageNews> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    public List<MessageNews> getDatas() {
        return datas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE.ITEM_TYPE_IMAGE.ordinal()) {
            return new TextViewHolder(mLayoutInflater.inflate(R.layout.item_message_text, parent, false));
        } else if (viewType == ITEM_TYPE.ITEM_TYPE_IMAGE3.ordinal()) {
            return new ImageViewHolder(mLayoutInflater.inflate(R.layout.item_message_image, parent, false));
        } else if (viewType == ITEM_TYPE.ITEM_TYPE_IMAGE1.ordinal()) {
            return new ImageViewHolder1(mLayoutInflater.inflate(R.layout.item_message_image1, parent, false));
        } else if (viewType == ITEM_TYPE.ITEM_TYPE_IMAGE9.ordinal()) {
            return new SudokuViewHolder(mLayoutInflater.inflate(R.layout.item_message_soduku, parent, false));
        } else if (viewType == ITEM_TYPE.ITEM_TYPE_VIDEO.ordinal()) {
            return new VideoViewHolder(mLayoutInflater.inflate(R.layout.item_message_video, parent, false));
        } else {
            return null;
        }
    }

    @Override
    public int getItemViewType(int position) {
        String type = datas.get(position).getNew_model();  //0普通 1三图 2个人 3视频
        if ("0".equals(type)) {
            return ITEM_TYPE.ITEM_TYPE_IMAGE.ordinal();
        } else if ("1".equals(type)) {
            return ITEM_TYPE.ITEM_TYPE_IMAGE3.ordinal();
        } else if ("4".equals(type)) {
            return ITEM_TYPE.ITEM_TYPE_IMAGE1.ordinal();
        } else if ("2".equals(type) || "5".equals(type) || "6".equals(type)) {
            return ITEM_TYPE.ITEM_TYPE_IMAGE9.ordinal();
        } else {
            return ITEM_TYPE.ITEM_TYPE_VIDEO.ordinal();
        }
    }

    @Override
    public int getItemCount() {
        return datas != null ? datas.size() : 0;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        final MessageNews news = datas.get(position);

        App.pinglunID = news.getId();

        if (holder instanceof TextViewHolder) {  //1张图片
            final TextViewHolder textViewHolder = (TextViewHolder) holder;
            if(current_item_style == ITEM_VIEW_STYLE.ITEM_VIEW_STYLE_SHOW_DELETE.ordinal()){
                textViewHolder.tv_delete.setVisibility(View.VISIBLE);
            }else{
                textViewHolder.tv_delete.setVisibility(View.GONE);
            }

            String[] imgStr = news.getImgUrl().split(",");
            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .priority(Priority.HIGH)
                    .placeholder(R.mipmap.ic_message_text_default)
                    .error(R.mipmap.ic_message_text_default)
                    .diskCacheStrategy(DiskCacheStrategy.NONE);

            Glide.with(mContext)
                    .asBitmap()
                    .apply(options)
                    .load(imgStr[0])
                    .into(new BitmapImageViewTarget(textViewHolder.iv_image) {
                        @Override
                        protected void setResource(Bitmap resource) {
                            RoundedBitmapDrawable circularBitmapDrawable =
                                    RoundedBitmapDrawableFactory.
                                            create(mContext.getResources(), resource);
                            //circularBitmapDrawable.setCornerRadius(8); // 圆角
                            textViewHolder.iv_image.setImageDrawable(circularBitmapDrawable);
                        }
                    });


            //置顶
            if ("0".equals(news.getTop())) {
                //热门
                if ("0".equals(news.getIsTop())) {
                    textViewHolder.iv_hot.setVisibility(View.GONE);
                } else if ("1".equals(news.getIsTop())) {  //1为热门
                    textViewHolder.iv_hot.setImageResource(R.mipmap.ic_hot2);
                    textViewHolder.iv_hot.setVisibility(View.VISIBLE);
                }

            } else if ("1".equals(news.getTop())) {
                textViewHolder.iv_hot.setImageResource(R.mipmap.ic_consultation_info_topping);
                textViewHolder.iv_hot.setVisibility(View.VISIBLE);
            }


            textViewHolder.tv_content.setText(news.getTitle());

            textViewHolder.tv_time.setText(SDDateUtil.formatDuringFrom(news.getCreateTime()));
            textViewHolder.tv_title.setText(news.getR_name());
            textViewHolder.tv_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (clickItemListener != null) clickItemListener.onDeleteArticleClick(news.getId(), position);
                }
            });

            textViewHolder.rl_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (App.getId() == null) {
                        Intent intent = new Intent(mContext, WebViewActivity.class);

                        intent.putExtra(WebViewActivity.URL_KEY
                                , H5UrlData.Followtails2 + news.getId() + "&memberId=");
                        intent.putExtra(WebViewActivity.TITLE, "");
                        mContext.startActivity(intent);
                    } else {
                        Intent intent = new Intent(mContext, WebViewActivity.class);

                        intent.putExtra(WebViewActivity.URL_KEY
                                , H5UrlData.Followtails2 + news.getId() + "&memberId=" + App.getId());
                        intent.putExtra(WebViewActivity.TITLE, "");
                        mContext.startActivity(intent);
                    }
                }
            });

        } else if (holder instanceof ImageViewHolder) {  //3张图片
            final ImageViewHolder imageViewHolder = (ImageViewHolder) holder;
            if(current_item_style == ITEM_VIEW_STYLE.ITEM_VIEW_STYLE_SHOW_DELETE.ordinal()){
                imageViewHolder.tv_delete.setVisibility(View.VISIBLE);
            }else{
                imageViewHolder.tv_delete.setVisibility(View.GONE);
            }
            String[] imgStr = null;
            if(news != null && news.getImgUrl() != null){
                imgStr = news.getImgUrl().split(",");
            }
            if (imgStr != null && imgStr.length > 0) {

                RequestOptions options = new RequestOptions()
                        .centerCrop()
                        .priority(Priority.HIGH)
                        .placeholder(R.mipmap.ic_message_text_default)
                        .error(R.mipmap.ic_message_text_default)
                        .diskCacheStrategy(DiskCacheStrategy.NONE);

                Glide.with(mContext)
                        .asBitmap()
                        .apply(options)
                        .load(imgStr[0])
                        .into(new BitmapImageViewTarget(imageViewHolder.iv01) {
                            @Override
                            protected void setResource(Bitmap resource) {
                                RoundedBitmapDrawable circularBitmapDrawable =
                                        RoundedBitmapDrawableFactory.
                                                create(mContext.getResources(), resource);
                                //circularBitmapDrawable.setCornerRadius(8); // 圆角
                                imageViewHolder.iv01.setImageDrawable(circularBitmapDrawable);
                            }
                        });
            }
            if (imgStr != null && imgStr.length > 1) {

                RequestOptions options = new RequestOptions()
                        .centerCrop()
                        .priority(Priority.HIGH)
                        .placeholder(R.mipmap.ic_message_text_default)
                        .error(R.mipmap.ic_message_text_default)
                        .diskCacheStrategy(DiskCacheStrategy.NONE);

                Glide.with(mContext)
                        .asBitmap()
                        .apply(options)
                        .load(imgStr[1])
                        .into(new BitmapImageViewTarget(imageViewHolder.iv02) {
                            @Override
                            protected void setResource(Bitmap resource) {
                                RoundedBitmapDrawable circularBitmapDrawable =
                                        RoundedBitmapDrawableFactory.
                                                create(mContext.getResources(), resource);
                                //circularBitmapDrawable.setCornerRadius(8); // 圆角
                                imageViewHolder.iv02.setImageDrawable(circularBitmapDrawable);
                            }
                        });

            }
            if (imgStr != null && imgStr.length > 2) {

                RequestOptions options = new RequestOptions()
                        .centerCrop()
                        .priority(Priority.HIGH)
                        .placeholder(R.mipmap.ic_message_text_default)
                        .error(R.mipmap.ic_message_text_default)
                        .diskCacheStrategy(DiskCacheStrategy.NONE);

                Glide.with(mContext)
                        .asBitmap()
                        .apply(options)
                        .load(imgStr[2])
                        .into(new BitmapImageViewTarget(imageViewHolder.iv03) {
                            @Override
                            protected void setResource(Bitmap resource) {
                                RoundedBitmapDrawable circularBitmapDrawable =
                                        RoundedBitmapDrawableFactory.
                                                create(mContext.getResources(), resource);
                                //circularBitmapDrawable.setCornerRadius(8); // 圆角
                                imageViewHolder.iv03.setImageDrawable(circularBitmapDrawable);
                            }
                        });
            }

            imageViewHolder.tv_name.setText(news.getR_name());
            imageViewHolder.tv_time.setText(SDDateUtil.formatDuringFrom(news.getCreateTime()));
            imageViewHolder.tv_title.setText(news.getTitle());

            //置顶
            if ("0".equals(news.getTop())) {
                //热门
                if ("0".equals(news.getIsTop())) {
                    imageViewHolder.iv_hot.setVisibility(View.GONE);
                } else if ("1".equals(news.getIsTop())) {  //1为热门
                    imageViewHolder.iv_hot.setImageResource(R.mipmap.ic_hot2);
                    imageViewHolder.iv_hot.setVisibility(View.VISIBLE);
                }

            } else if ("1".equals(news.getTop())) {  //1为热门
                imageViewHolder.iv_hot.setImageResource(R.mipmap.ic_consultation_info_topping);
                imageViewHolder.iv_hot.setVisibility(View.VISIBLE);
            }


            imageViewHolder.ll_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (App.getId() == null) {
                        Intent intent = new Intent(mContext, WebViewActivity.class);

                        intent.putExtra(WebViewActivity.URL_KEY
                                , H5UrlData.Followtails2 + news.getId() + "&memberId=");
                        intent.putExtra(WebViewActivity.TITLE, "");
                        mContext.startActivity(intent);
                    } else {
                        Intent intent = new Intent(mContext, WebViewActivity.class);

                        intent.putExtra(WebViewActivity.URL_KEY
                                , H5UrlData.Followtails2 + news.getId() + "&memberId=" + App.getId());
                        intent.putExtra(WebViewActivity.TITLE, "");
                        mContext.startActivity(intent);
                    }
                }
            });

            imageViewHolder.tv_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (clickItemListener != null) clickItemListener.onDeleteArticleClick(news.getId(), position);
                }
            });

        } else if (holder instanceof ImageViewHolder1) {  //1张大图片
            final ImageViewHolder1 imageViewHolder1 = (ImageViewHolder1) holder;
            if(current_item_style == ITEM_VIEW_STYLE.ITEM_VIEW_STYLE_SHOW_DELETE.ordinal()){
                imageViewHolder1.tv_delete.setVisibility(View.VISIBLE);
            }else{
                imageViewHolder1.tv_delete.setVisibility(View.GONE);
            }

            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .priority(Priority.HIGH)
                    .placeholder(R.mipmap.ic_message_text_default)
                    .error(R.mipmap.ic_message_text_default)
                    .diskCacheStrategy(DiskCacheStrategy.NONE);

            Glide.with(mContext)
                    .asBitmap()
                    .apply(options)
                    .load(news.getImgUrl())
                    .into(new BitmapImageViewTarget(imageViewHolder1.iv) {
                        @Override
                        protected void setResource(Bitmap resource) {
                            RoundedBitmapDrawable circularBitmapDrawable =
                                    RoundedBitmapDrawableFactory.
                                            create(mContext.getResources(), resource);
                            //circularBitmapDrawable.setCornerRadius(8); // 圆角
                            imageViewHolder1.iv.setImageDrawable(circularBitmapDrawable);
                        }
                    });

            imageViewHolder1.tv_name1.setText(news.getR_name());
            imageViewHolder1.tv_time1.setText(SDDateUtil.formatDuringFrom(news.getCreateTime()));
            imageViewHolder1.tv_title1.setText(news.getTitle());
            //置顶
            if ("0".equals(news.getTop())) {
                //热门
                if ("0".equals(news.getIsTop())) {
                    imageViewHolder1.iv_hot1.setVisibility(View.GONE);
                } else if ("1".equals(news.getIsTop())) {  //1为热门
                    imageViewHolder1.iv_hot1.setImageResource(R.mipmap.ic_hot2);
                    imageViewHolder1.iv_hot1.setVisibility(View.VISIBLE);
                }

            } else if ("1".equals(news.getTop())) {  //1为热门
                imageViewHolder1.iv_hot1.setImageResource(R.mipmap.ic_consultation_info_topping);
                imageViewHolder1.iv_hot1.setVisibility(View.VISIBLE);
            }


            imageViewHolder1.ll_item1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (App.getId() == null) {
                        Intent intent = new Intent(mContext, WebViewActivity.class);

                        intent.putExtra(WebViewActivity.URL_KEY
                                , H5UrlData.Followtails2 + news.getId() + "&memberId=");
                        intent.putExtra(WebViewActivity.TITLE, "");
                        mContext.startActivity(intent);
                    } else {
                        Intent intent = new Intent(mContext, WebViewActivity.class);

                        intent.putExtra(WebViewActivity.URL_KEY
                                , H5UrlData.Followtails2 + news.getId() + "&memberId=" + App.getId());
                        intent.putExtra(WebViewActivity.TITLE, "");
                        mContext.startActivity(intent);
                    }
                }
            });

            imageViewHolder1.tv_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (clickItemListener != null) clickItemListener.onDeleteArticleClick(news.getId(), position);
                }
            });

        } else if (holder instanceof SudokuViewHolder) {//九图
            final SudokuViewHolder sudokuViewHolder = (SudokuViewHolder) holder;
            sudokuViewHolder.ll_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickItemListener.onItemClick(position);
                }
            });
            if(current_item_style == ITEM_VIEW_STYLE.ITEM_VIEW_STYLE_SHOW_DELETE.ordinal()){
                sudokuViewHolder.tv_delete.setVisibility(View.VISIBLE);
            }else{
                sudokuViewHolder.tv_delete.setVisibility(View.GONE);
            }
            sudokuViewHolder.album_comment_number.setText(news.getCommentCounts() + "");

            pictures.clear();
            List<String> picStrings = new ArrayList<>();
            if ((news.getImgUrl() != null) || (news.getExternalUrl() != null)) {
                sudokuViewHolder.item_nice9_imagerl.setVisibility(View.VISIBLE);
                if (news.getExternalUrl() == null) {
                    picStrings.clear();
                    final ArrayList<LocalMedia> selectList = new ArrayList<>();
                    String[] imgStr = news.getImgUrl().split(",");
                    for (int i = 0; i < imgStr.length; i++) {
                        picStrings.add(imgStr[i]);
                    }

                    for (String url : picStrings) {
                        LocalMedia imageItem = new LocalMedia();
                        imageItem.pictureType = "image/PNG";
                        imageItem.path = url;
                        imageItem.setType(news.getNew_model());
                        selectList.add(imageItem);
                        Log.e("135", "url = " + url);
                    }


                    Log.e("sssss", news.getId());

                    FullyGridLayoutManager manager;

                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    if ((!StringUtils.isNullOrEmpty(news.getNew_model()) && news.getNew_model().equals("6"))) {
                        manager = new FullyGridLayoutManager(mContext, 1
                                , GridLayoutManager.VERTICAL, false);
                    } else {

                        if (selectList != null && selectList.size() == 4) {
                            int recyclerViewWidth = ScreenUtils.getScreenWidth(mContext) -
                                    ((ScreenUtils.getScreenWidth(mContext) - DensityUtil.dp2px(mContext, 25)) / 3) - DensityUtil.dp2px(mContext, 25);
                            params = new LinearLayout.LayoutParams(recyclerViewWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
                            manager = new FullyGridLayoutManager(mContext, 2
                                    , GridLayoutManager.VERTICAL, false);
                        }else{
                            manager = new FullyGridLayoutManager(mContext, 3
                                    , GridLayoutManager.VERTICAL, false);
                        }



                    }


                    sudokuViewHolder.item_nice9_imagerl.setLayoutParams(params);

                    sudokuViewHolder.item_nice9_imagerl.setLayoutManager(manager);
                    gridImageAdapter = new GridImageAdapter(mContext);
                    gridImageAdapter.setList(selectList);
                    gridImageAdapter.notifyDataSetChanged();
                    gridImageAdapter.setSelectMax(selectList.size());

                /* 列之间分割线
                DividerItemDecoration divider = new DividerItemDecoration(mContext, DividerItemDecoration.HORIZONTAL);
                divider.setDrawable(ContextCompat.getDrawable(mContext, R.drawable.recycler_view_divider_consultation_circle_friends));
                sudokuViewHolder.item_nice9_imagerl.addItemDecoration(divider);*/

                    sudokuViewHolder.item_nice9_imagerl.setAdapter(gridImageAdapter);
                    gridImageAdapter.setOnItemClickListener(new GridImageAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(int position, View v) {
                            LocalMedia media = selectList.get(position);
                            String pictureType = media.getPictureType();
                            int mediaType = PictureMimeType.pictureToVideo(pictureType);
                            switch (mediaType) {
                                case 1:
                                    // 预览图片
                                    if(mconsultationInfoFragment != null){
                                        PictureSelector.create(mconsultationInfoFragment).externalPicturePreview(position, selectList);
                                    }else if(mActivity != null){
                                        PictureSelector.create(mActivity).externalPicturePreview(position, selectList);
                                    }

                                    break;
                                case 2:
                                    // 预览视频
                                    if(mconsultationInfoFragment != null){
                                        PictureSelector.create(mconsultationInfoFragment).externalPictureVideo(media.getPath());
                                    }else if(mActivity != null){
                                        PictureSelector.create(mActivity).externalPictureVideo(media.getPath());
                                    }

                                    break;
                                case 3:
                                    // 预览音频
                                    if(mconsultationInfoFragment != null){
                                        PictureSelector.create(mconsultationInfoFragment).externalPictureAudio(media.getPath());
                                    } else if(mActivity != null){
                                        PictureSelector.create(mActivity).externalPictureAudio(media.getPath());
                                    }
                                    break;
                            }
                        }
                    });


                } else {
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    sudokuViewHolder.item_nice9_imagerl.setLayoutParams(params);
                    picStrings.clear();
                    if (news.getExternalUrl() != null) {
                        final ArrayList<LocalMedia> selectList = new ArrayList<>();
                        String[] imgStr = news.getExternalUrl().split(",");
                        for (int i = 0; i < imgStr.length; i++) {
                            picStrings.add(imgStr[i]);
                        }
                        for (String url : picStrings) {
                            LocalMedia imageItem = new LocalMedia();
                            imageItem.pictureType = "video/mp4";
                            imageItem.path = url;
                            selectList.add(imageItem);
                            Log.e("1345", "url = " + url);
                        }

                        Log.e("1345", news.getId());

                        FullyGridLayoutManager manager = new FullyGridLayoutManager(mContext, 2
                                , GridLayoutManager.VERTICAL, false);

                        sudokuViewHolder.item_nice9_imagerl.setLayoutManager(manager);
                        gridImageAdapter = new GridImageAdapter(mContext);
                        gridImageAdapter.setList(selectList);
                        gridImageAdapter.notifyDataSetChanged();
                        gridImageAdapter.setSelectMax(selectList.size());
                        sudokuViewHolder.item_nice9_imagerl.setAdapter(gridImageAdapter);
                        gridImageAdapter.setOnItemClickListener(new GridImageAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(int position, View v) {
                                LocalMedia media = selectList.get(position);
                                String pictureType = media.getPictureType();
                                int mediaType = PictureMimeType.pictureToVideo(pictureType);
                                switch (mediaType) {
                                    case 1:
                                        // 预览图片
                                        PictureSelector.create(mconsultationInfoFragment).externalPicturePreview(position, selectList);
                                        break;
                                    case 2:
                                        // 预览视频
                                        PictureSelector.create(mconsultationInfoFragment).externalPictureVideo(media.getPath());
                                        break;
                                    case 3:
                                        // 预览音频
                                        PictureSelector.create(mconsultationInfoFragment).externalPictureAudio(media.getPath());
                                        break;
                                }
                            }
                        });

                    }

                /*mListAdapter = new ListAdapter(mContext, pictures);
                sudokuViewHolder.item_nice9_imagerl.setLayoutManager(new LinearLayoutManager(mContext));
                sudokuViewHolder.item_nice9_imagerl.setAdapter(mListAdapter);*/

                    // 将资源路径设置到图片选择实体类
                    // final ArrayList<LocalMedia> imageItems = new ArrayList<>();

                }

            }else{
                sudokuViewHolder.item_nice9_imagerl.setVisibility(View.GONE);
            }

            sudokuViewHolder.tv_name.setText(news.getR_name());
            sudokuViewHolder.tv_time.setText(SDDateUtil.formatDuringFrom(news.getCreateTime()));
            if (!StringUtils.isNullOrEmpty(news.getCon())) {
                sudokuViewHolder.tv_explain.setText(news.getCon());
                sudokuViewHolder.tv_explain.setVisibility(View.VISIBLE);
            } else {
                sudokuViewHolder.tv_explain.setVisibility(View.GONE);
            }


            if (!StringUtils.isNullOrEmpty(news.getConent())) {
//                sudokuViewHolder.tv_title.setText(news.getConent());
                sudokuViewHolder.contentTv.setVisibility(View.VISIBLE);
                setContent(sudokuViewHolder, position, news.getConent(), news.getId());
            } else {
                sudokuViewHolder.contentTv.setVisibility(View.GONE);
            }



            /*// 判断接受到的是否有表情图片，有则替换
            if (!StringUtils.isEmpty(news.getConent())) {
                for (int i = 0; i < EmojiUtils.picStr.length; i++) {
                    if (news.getConent().contains("[" + EmojiUtils.picStr[i] + "]")) {
                        String s1 = news.getConent();
                        String s = s1.replaceAll("\\[" + EmojiUtils.picStr[i] + "\\]", "<f" + EmojiUtils.picStr1[i] + ">");
                        news.setConent(s);
                    }
                }
            }

            if (news.getConent() != null && news.getConent().contains("<f") && news.getConent().contains(">")) {
                sudokuViewHolder.tv_title.setText("");
                String message = news.getConent();
                List<Object> results = new ArrayList<Object>();
                List<String> ems = new ArrayList<String>();
                Pattern patter = Pattern.compile("<f[\\w]*>");
                Matcher matcher = patter.matcher(news.getConent());
                while (matcher.find()) {
                    ems.add(matcher.group());
                }
                for (int i = 0; i < ems.size(); i++) {
                    if (message.startsWith("<f")) {
                        results.add(message.substring(0, 6));
                        message = message.substring(6, message.length());
                        if (message.length() > 0 && !message.startsWith("<f")) {
                            if (message.contains("<f") && message.contains(">")) {
                                int emsIndex = message.indexOf("<");
                                String itemMes = message.substring(0, emsIndex);
                                results.add(itemMes);
                                message = message.substring(emsIndex, message.length());
                            } else {
                                results.add(message);
                            }
                        }
                    } else {
                        int emsIndex = message.indexOf("<");
                        String itemMes = message.substring(0, emsIndex);
                        results.add(itemMes);
                        message = message.substring(emsIndex, message.length());
                        results.add(message.substring(0, 6));
                        message = message.substring(6, message.length());
                    }
                }
                ArrayList<SpannableString> list = new ArrayList<SpannableString>();
                for (int i = 0; i < results.size(); i++) {
                    list.add(null);
                }
                for (int i = 0; i < results.size(); i++) {
                    if (results.get(i).toString().startsWith("<f")) {
                        String emPath = results.get(i).toString().replace("<", "");
                        emPath = emPath.replace(">", "");
                        emPath = emPath.substring(1, 4);
                        list.set(i, Emoji.getImg(mContext, emPath));
                    }
                }
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i) != null) {
                        results.set(i, list.get(i));
                    }
                }
                for (int i = 0; i < results.size(); i++) {
                    sudokuViewHolder.tv_title.append((CharSequence) results.get(i));
                }
            } else if (!TextUtils.isEmpty(news.getConent())) {
                sudokuViewHolder.tv_title.setText(news.getConent());
                sudokuViewHolder.tv_title.setVisibility(View.VISIBLE);
            } else {
                sudokuViewHolder.tv_title.setVisibility(View.GONE);
            }
*/

            // 处理点赞列表
            final List<LikelogsBean> likelogsBeans = news.getLikelogs();

            // 处理评论列表
            final List<CommentlogsBean> commentlogsBeans = news.getCommentlogs();


            if ((likelogsBeans != null && likelogsBeans.size() > 0) && (commentlogsBeans != null && commentlogsBeans.size() > 0)) {
                sudokuViewHolder.line.setVisibility(View.VISIBLE);
            } else {
                sudokuViewHolder.line.setVisibility(View.GONE);
            }

            // 判断评论和点赞都不为空值
            if ((likelogsBeans != null || commentlogsBeans != null)) {
//                sudokuViewHolder.linearlayoutAll.setVisibility(View.VISIBLE);//评论和点赞数据不展示

                // 处理点赞列表
                if (likelogsBeans != null) {
                    sudokuViewHolder.album_praise_list_layout.setVisibility(View.VISIBLE);
                    sudokuViewHolder.praiseListView.setVisibility(View.VISIBLE);
                    sudokuViewHolder.parise_icon.setVisibility(View.VISIBLE);

                    // 设置点赞数据
                    sudokuViewHolder.praiseListView.setDatas(likelogsBeans);

                    // 点击事件 得到点赞的用户名和其他信息
                    sudokuViewHolder.praiseListView.setOnItemClickListener(new PraiseListView.OnItemClickListener() {
                        @Override
                        public void onClick(int position) {
                            String userName = likelogsBeans.get(position).getNickName();
                            //String userId = likelogsBeans.get(position).getSenderBean().getId();
                            Toast.makeText(mContext, userName, Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    sudokuViewHolder.album_praise_list_layout.setVisibility(View.GONE);
                }

                // 处理评论列表
                if (commentlogsBeans != null) {

                    sudokuViewHolder.commentList.setVisibility(View.VISIBLE);
                    sudokuViewHolder.commentList.setDatas(commentlogsBeans);

                    Log.e("11111111111", "处理评论列表:" + commentlogsBeans.get(0).getContent());
                    // 处理评论列表点击事件
                    sudokuViewHolder.commentList.setOnItemClickListener(new CommentListView.OnItemClickListener() {
                        @Override
                        public void onItemClick(int commentPosition) {
                            final CommentlogsBean commentsBean = commentlogsBeans.get(commentPosition);

                            // 判断ID 设置判断
                            // DataTest.curUser.getId().equals(commentsBean.getSender().getId())
                            if (commentsBean.getMemberId().equals(App.getId())) {
                                // 删除自己的评论
                                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                                builder.setItems(deleteItems, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                        //mListener.onDeleteItemButtonClick(position, commentsBean.getCommentId());
                                        mListener.onDeleteItemButtonClick(position, Integer.parseInt(App.getId()));
                                    }
                                });
                                builder.create().show();
                            } else {
                                //回复别人的评论
                                CommentConfig config = new CommentConfig();
                                config.circlePosition = position;
                                config.commentPosition = commentPosition;
                                config.commentType = CommentConfig.Type.REPLY;
                                //config.replyUser = commentsBean.getSender();
                                mListener.onItemButtonClick(config, news.getId(), commentlogsBeans.size());
                            }
                        }
                    });
                } else {
                    sudokuViewHolder.commentList.setVisibility(View.GONE);
                }

            } else {
//                sudokuViewHolder.linearlayoutAll.setVisibility(View.GONE);
            }

//            if ((likelogsBeans != null && likelogsBeans.size() > 0)) {
//                for(int i = 0;i<likelogsBeans.size();i++){
//                    if (likelogsBeans.get(i).getNickName().equals(SharedPreferencesUtils.getInstance().getNickName())){
//                        //列表中包含改元素，做逻辑
//                        Log.e("点赞+++++++", "1");
//                        IsLike = 1;
//                    }
//                }
//            }else {
//                IsLike = 0;
//            }

            IsLike = news.getIsLike();

            // 判断是否已点赞
            if (IsLike == 1) {
                sudokuViewHolder.album_praise.setImageResource(R.mipmap.circle_dynamic_praise_s);
            } else {
                sudokuViewHolder.album_praise.setImageResource(R.mipmap.circle_dynamic_praise_n);
            }
            sudokuViewHolder.album_praise_number.setText(news.getLikeTotal() + "");
            sudokuViewHolder.album_praise_layout.setTag(position + "");

            sudokuViewHolder.album_praise_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    mListener.addOrCancelLikes(position, news);

                }
            });

            // 打开发现详情页（内容 + 评论页）
            sudokuViewHolder.linearlayout_comment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    /**
//                     * 弹出输入框，让用户输入评论
//                     */
//                    CommentConfig config = new CommentConfig();
//                    config.circlePosition = position;
//                    config.commentType = CommentConfig.Type.PUBLIC;
//
//                    if(commentlogsBeans!=null){
//                        mListener.onItemButtonClick(config, news.getId(), commentlogsBeans.size());
//                    }else {
//                        mListener.onItemButtonClick(config, news.getId(), 0);
//                    }

                    FindDetailActivity.start(mContext, news, true);
                }
            });

            // 评论
            sudokuViewHolder.linearlayout_comment.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    /**
                     * 弹出输入框，让用户输入评论
                     */
                    CommentConfig config = new CommentConfig();
                    config.circlePosition = position;
                    config.commentType = CommentConfig.Type.PUBLIC;

                    if(commentlogsBeans!=null){
                        mListener.onItemButtonClick(config, news.getId(), commentlogsBeans.size());
                    }else {
                        mListener.onItemButtonClick(config, news.getId(), 0);
                    }
                    return true;
                }
            });

            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .priority(Priority.HIGH)
                    .error(R.mipmap.ic_media_head_default)
                    .placeholder(R.mipmap.ic_media_head_default)
                    .diskCacheStrategy(DiskCacheStrategy.NONE);

            Glide.with(mContext)
                    .asBitmap()
                    .apply(options)
                    .load(news.getR_url())
                    .into(new BitmapImageViewTarget(sudokuViewHolder.iv_head) {
                        @Override
                        protected void setResource(Bitmap resource) {
                            RoundedBitmapDrawable circularBitmapDrawable =
                                    RoundedBitmapDrawableFactory.
                                            create(mContext.getResources(), resource);
                            //circularBitmapDrawable.setCornerRadius(8); // 圆角
                            sudokuViewHolder.iv_head.setImageDrawable(circularBitmapDrawable);
                        }
                    });

            sudokuViewHolder.iv_head.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (clickItemListener != null) clickItemListener.onHeadImgClick(news);
                }
            });



            sudokuViewHolder.tv_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (clickItemListener != null){
                        clickItemListener.onDeleteArticleClick(news.getId(), position);
                    }
                }
            });


        } else if (holder instanceof VideoViewHolder) {
            final VideoViewHolder videoViewHolder = (VideoViewHolder) holder;
            if(current_item_style == ITEM_VIEW_STYLE.ITEM_VIEW_STYLE_SHOW_DELETE.ordinal()){
                videoViewHolder.tv_delete.setVisibility(View.VISIBLE);
            }else{
                videoViewHolder.tv_delete.setVisibility(View.GONE);
            }
            videoViewHolder.tv_name.setText(news.getR_name());
            videoViewHolder.tv_title.setText(news.getName());
            videoViewHolder.tv_time.setText(SDDateUtil.formatDuringFrom(news.getCreateTime()));


            //设置视频封面图
            ImageView thumbImageView = videoViewHolder.consultation_video_player.thumbImageView;
            if (!StringUtils.isNullOrEmpty(news.getImgUrl())) {
                RequestOptions options1 = new RequestOptions().centerCrop()
                        .placeholder(R.color.whitesmoke)
                        .error(R.color.whitesmoke)
                        .diskCacheStrategy(DiskCacheStrategy.ALL); // 缓存策略

                Glide.with(mContext)
                        .asBitmap()
                        .load(news.getImgUrl())
                        .apply(options1)
                        .into(thumbImageView);
            } else {
                //获得视频第一帧当作封面
               CommonUtils.setVideoCover(mContext, news.getExternalUrl(), thumbImageView);
            }
            //播放地址
            videoViewHolder.consultation_video_player.setUp(news.getExternalUrl(), videoViewHolder.consultation_video_player.SCREEN_LAYOUT_NORMAL);// ,"视频标题"
            videoViewHolder.consultation_video_player.backButton.setVisibility(View.GONE);//隐藏视频控件中的返回按钮

//            LogUtils.error("图片路径：", news.getImgUrl());
//            LogUtils.error("视频路径：", news.getExternalUrl());

            videoViewHolder.tv_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (clickItemListener != null) clickItemListener.onDeleteArticleClick(news.getId(), position);
                }
            });
        }
    }

    //1张图片
    public class TextViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_image)
        ImageView iv_image;
        @BindView(R.id.tv_content)
        TextView tv_content;
        @BindView(R.id.tv_title)
        TextView tv_title;
        @BindView(R.id.tv_time)
        TextView tv_time;
        @BindView(R.id.iv_hot)
        ImageView iv_hot;
        @BindView(R.id.rl_item)
        RelativeLayout rl_item;
        @BindView(R.id.tv_delete)
        TextView tv_delete;

        public TextViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

//        @OnClick(R.id.tv_delete)
//        public void onClick(View view) {
//
//        }

    }

    //3张图片
    public class ImageViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_title)
        TextView tv_title;
        @BindView(R.id.iv01)
        ImageView iv01;
        @BindView(R.id.iv02)
        ImageView iv02;
        @BindView(R.id.iv03)
        ImageView iv03;
        @BindView(R.id.tv_time)
        TextView tv_time;
        @BindView(R.id.iv_hot)
        ImageView iv_hot;
        @BindView(R.id.tv_name)
        TextView tv_name;
        @BindView(R.id.ll_item)
        LinearLayout ll_item;
        @BindView(R.id.tv_delete)
        TextView tv_delete;

        LinearLayout ll_iv;

        public ImageViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            ll_iv = (LinearLayout) itemView.findViewById(R.id.ll_iv);
            for (int i = 0; i < ll_iv.getChildCount(); i++) {
                ImageView img = (ImageView) ll_iv.getChildAt(i);
                ViewGroup.LayoutParams params = img.getLayoutParams();
                params.height = width / 9 * 2;
                params.width = width / 3;
                img.setLayoutParams(params);
            }
        }
    }

    //1张图片
    public class ImageViewHolder1 extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_title1)
        TextView tv_title1;
        @BindView(R.id.iv)
        ImageView iv;

        @BindView(R.id.tv_time1)
        TextView tv_time1;
        @BindView(R.id.iv_hot1)
        ImageView iv_hot1;
        @BindView(R.id.tv_name1)
        TextView tv_name1;
        @BindView(R.id.ll_item1)
        LinearLayout ll_item1;
        @BindView(R.id.tv_delete)
        TextView tv_delete;

        public ImageViewHolder1(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

    //9张图片
    public class SudokuViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_head)
        ImageView iv_head;
        @BindView(R.id.rl_head)
        RelativeLayout rl_head;
        @BindView(R.id.tv_name)
        TextView tv_name;
        @BindView(R.id.tv_explain)
        TextView tv_explain;
        @BindView(R.id.item_nice9_imagerl)
        RecyclerView item_nice9_imagerl;

        @BindView(R.id.tv_time)
        TextView tv_time;
        @BindView(R.id.tv_content)
        TextView contentTv;
        @BindView(R.id.tv_expand_or_fold)
        TextView expandOrFoldTv;//展开收起按钮
        @BindView(R.id.ll_item)
        LinearLayout ll_item;

        @BindView(R.id.album_praise_layout)
        LinearLayout album_praise_layout;
        @BindView(R.id.album_praise)
        public ImageView album_praise;
        @BindView(R.id.album_praise_number)
        public TextView album_praise_number;

        @BindView(R.id.linearlayout_comment)
        LinearLayout linearlayout_comment;
        @BindView(R.id.album_comment)
        ImageView album_comment;
        @BindView(R.id.album_comment_number)
        TextView album_comment_number;

        @BindView(R.id.linearlayoutAll)
        LinearLayout linearlayoutAll;
        @BindView(R.id.album_praise_list_layout)
        LinearLayout album_praise_list_layout;
        @BindView(R.id.parise_icon)
        ImageView parise_icon;
        @BindView(R.id.praiseListView)
        PraiseListView praiseListView;
        @BindView(R.id.line)
        View line;
        @BindView(R.id.commentList)
        CommentListView commentList;
        @BindView(R.id.tv_delete)
        TextView tv_delete;

        public SudokuViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    //视频
    public class VideoViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.tv_title)
        TextView tv_title;

        @BindView(R.id.tv_name)
        TextView tv_name;
        @BindView(R.id.tv_time)
        TextView tv_time;

        @BindView(R.id.consultation_video_player)
        JCVideoPlayerStandard consultation_video_player;
        @BindView(R.id.tv_delete)
        TextView tv_delete;

        public VideoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private void setViewHeight(LinearLayout ll) {
        for (int i = 0; i < ll.getChildCount(); i++) {
            ImageView img = (ImageView) ll.getChildAt(i);
            ViewGroup.LayoutParams params = img.getLayoutParams();
            params.height = width / 3;
            params.width = width / 3;
            img.setLayoutParams(params);
        }
    }

    private ClickItemListener clickItemListener;

    public ClickItemListener getClickItemListener() {
        return clickItemListener;
    }

    public void setClickItemListener(ClickItemListener clickItemListener) {
        this.clickItemListener = clickItemListener;
    }

    public interface ClickItemListener {

        void onItemClick(int position);

        void onHeadImgClick(MessageNews messageNews);//头像一栏整个条目

        // 删除动态  // 参数一 表示删除的位置  参数二表示删除的ID
        void onDeleteItemButtonClick(int position, int commentId);

        // 添加/取消赞   参数一 表示点赞的位置
        void addOrCancelLikes(int mPosition, MessageNews news);

        // 回复评论  参数一 表示回复的实体类
        void onItemButtonClick(CommentConfig config, String id, int mSize);

        //删除资讯
        void onDeleteArticleClick(String news_Id, int deletePosition);

    }


    private void setContent(final SudokuViewHolder holder, final int position, String contentStr, final String newsId){
        Integer state = mTextStateList.get(datas.get(position).getId());
        //第一次初始化，未知状态
        if (state == null || state == STATE_UNKNOW) {
            holder.contentTv.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
                    //这个回掉会调用多次，获取完行数后记得注销监听
                    holder.contentTv.getViewTreeObserver().removeOnPreDrawListener(this);
                    //holder.content.getViewTreeObserver().addOnPreDrawListener(null);
                    //如果内容显示的行数大于最大显示行数
                    if (holder.contentTv.getLineCount() > MAX_LINE_COUNT) {
                        holder.contentTv.setMaxLines(MAX_LINE_COUNT);//设置最大显示行数
                        holder.expandOrFoldTv.setVisibility(View.VISIBLE);//显示“全文”
                        holder.expandOrFoldTv.setText("全文");
                        mTextStateList.put(newsId, STATE_COLLAPSED);//保存状态
                    } else {
                        holder.expandOrFoldTv.setVisibility(View.GONE);
                        mTextStateList.put(newsId, STATE_NOT_OVERFLOW);
                    }
                    return true;
                }
            });

            holder.contentTv.setMaxLines(Integer.MAX_VALUE);//设置文本的最大行数，为整数的最大数值
            holder.contentTv.setText(contentStr);
        } else {
            //如果之前已经初始化过了，则使用保存的状态。
            switch (state) {
                case STATE_NOT_OVERFLOW:
                    holder.expandOrFoldTv.setVisibility(View.GONE);
                    break;
                case STATE_COLLAPSED:
                    holder.contentTv.setMaxLines(MAX_LINE_COUNT);
                    holder.expandOrFoldTv.setVisibility(View.VISIBLE);
                    holder.expandOrFoldTv.setText("全文");
                    break;
                case STATE_EXPANDED:
                    holder.contentTv.setMaxLines(Integer.MAX_VALUE);
                    holder.expandOrFoldTv.setVisibility(View.VISIBLE);
                    holder.expandOrFoldTv.setText("收起");
                    break;
            }
            holder.contentTv.setText(contentStr);
        }

        //全文和收起的点击事件
        holder.expandOrFoldTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer state = mTextStateList.get(datas.get(position).getId());
                if (state != null && state == STATE_COLLAPSED) {
                    holder.contentTv.setMaxLines(Integer.MAX_VALUE);
                    holder.expandOrFoldTv.setText("收起");
                    mTextStateList.put(datas.get(position).getId() + "", STATE_EXPANDED);
                } else if (state != null && state == STATE_EXPANDED) {
                    holder.contentTv.setMaxLines(MAX_LINE_COUNT);
                    holder.expandOrFoldTv.setText("全文");
                    mTextStateList.put(datas.get(position).getId() + "", STATE_COLLAPSED);
                }
            }
        });
    }

    private void setContentShowState(final SudokuViewHolder sudokuViewHolder, MessageNews news) {
        if (news.getConent().length() > 150) {
            sudokuViewHolder.expandOrFoldTv.setVisibility(View.VISIBLE);
            setTextState(sudokuViewHolder, isExpand);
            sudokuViewHolder.expandOrFoldTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isExpand) {
                        isExpand = false;
                    } else {
                        isExpand = true;
                    }
                    setTextState(sudokuViewHolder, isExpand);
                }
            });

        } else {
            sudokuViewHolder.expandOrFoldTv.setVisibility(View.GONE);
            sudokuViewHolder.contentTv.setMaxLines(Integer.MAX_VALUE);
        }
    }

    private void setTextState(SudokuViewHolder sudokuViewHolder, boolean isExpand) {
        if (isExpand) {
            sudokuViewHolder.contentTv.setMaxLines(Integer.MAX_VALUE);
            sudokuViewHolder.expandOrFoldTv.setText("收起");
        } else {
            sudokuViewHolder.contentTv.setMaxLines(2);
            sudokuViewHolder.expandOrFoldTv.setText("全文");
        }
    }

}
