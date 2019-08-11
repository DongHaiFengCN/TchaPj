package com.application.tchapj.search.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.application.tchapj.App;
import com.application.tchapj.H5UrlData;
import com.application.tchapj.R;
import com.application.tchapj.WebViewActivity;
import com.application.tchapj.consultation.adapter.ConsultationInfoAdapter;
import com.application.tchapj.consultation.bean.CommentConfig;
import com.application.tchapj.consultation.bean.MessageNews;
import com.application.tchapj.my.adpter.GridImageAdapter;
import com.application.tchapj.my.fragment.FullyGridLayoutManager;
import com.application.tchapj.search.bean.SearchBean;
import com.application.tchapj.utils2.SDDateUtil;
import com.application.tchapj.utils2.imagenice9layout.DemoEntity;
import com.application.tchapj.utils2.imagenice9layout.ListAdapter;
import com.application.tchapj.utils2.picture.PictureSelector;
import com.application.tchapj.utils2.picture.config.PictureMimeType;
import com.application.tchapj.utils2.picture.entity.LocalMedia;
import com.application.tchapj.utils2.qiniu.utils.StringUtils;
import com.application.tchapj.utils2.videoplayer.NiceVideoPlayer;
import com.application.tchapj.utils2.videoplayer.TxVideoPlayerController;
import com.application.tchapj.widiget.CommentListView;
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

/**
 * Created by tanger on 2018/3/19.
 */

public class RecommendChildAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public final static int TYPE_HEAD = 0;
    public final static int TYPE_CONTENT = 1;
    public static final int HEADVIEW_SIZE = 1;

    private final LayoutInflater mLayoutInflater;
    private final Context mContext;

    private List<MessageNews> datas;

    private long mLastTime = 0;

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



    public RecommendChildAdapter(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        width = wm.getDefaultDisplay().getWidth();
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
        } else if ("2".equals(type) || "5".equals(type)) {
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

            String[] imgStr = news.getImgUrl().split(",");
            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .priority(Priority.HIGH)
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

            if ("0".equals(news.getTop())) {
                //热门
                if ("0".equals(news.getIsTop())) {
                    textViewHolder.iv_hot.setVisibility(View.GONE);
                } else if ("1".equals(news.getIsTop())) {  //1为热门
                    textViewHolder.iv_hot.setImageResource(R.mipmap.ic_hot2);
                    textViewHolder.iv_hot.setVisibility(View.VISIBLE);
                }

            } else if ("1".equals(news.getTop())) {  //1为热门
                textViewHolder.iv_hot.setImageResource(R.mipmap.ic_consultation_info_topping);
                textViewHolder.iv_hot.setVisibility(View.VISIBLE);
            }

            textViewHolder.tv_content.setText(news.getConent());

            textViewHolder.tv_time.setText(SDDateUtil.formatDuringFrom(news.getCreateTime()));
            textViewHolder.tv_title.setText(news.getName());
            textViewHolder.rl_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (clickItemListener != null) clickItemListener.onclick(news.getId());
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
            String[] imgStr = news.getImgUrl().split(",");
            if (imgStr.length > 0) {

                RequestOptions options = new RequestOptions()
                        .centerCrop()
                        .priority(Priority.HIGH)
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
            if (imgStr.length > 1) {

                RequestOptions options = new RequestOptions()
                        .centerCrop()
                        .priority(Priority.HIGH)
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
            if (imgStr.length > 2) {

                RequestOptions options = new RequestOptions()
                        .centerCrop()
                        .priority(Priority.HIGH)
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

            imageViewHolder.tv_name.setText(news.getName());
            imageViewHolder.tv_time.setText(SDDateUtil.formatDuringFrom(news.getCreateTime()));
            imageViewHolder.tv_title.setText(news.getName());
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
                    if (clickItemListener != null) clickItemListener.onclick(news.getId());
                }
            });

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

        } else if (holder instanceof ImageViewHolder1) {  //1张大图片
            final ImageViewHolder1 imageViewHolder1 = (ImageViewHolder1) holder;

            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .priority(Priority.HIGH)
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

            imageViewHolder1.tv_name1.setText(news.getName());
            imageViewHolder1.tv_time1.setText(SDDateUtil.formatDuringFrom(news.getCreateTime()));
            imageViewHolder1.tv_title1.setText(news.getConent());
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
                    if (clickItemListener != null) clickItemListener.onclick(news.getId());
                }
            });

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

        } else if (holder instanceof SudokuViewHolder) {//九图
            final SudokuViewHolder sudokuViewHolder = (SudokuViewHolder) holder;

            pictures.clear();
            List<String> picStrings = new ArrayList<>();
            if (news.getImgUrl() != null) {
                final ArrayList<LocalMedia> selectList = new ArrayList<>();
                String[] imgStr = news.getImgUrl().split(",");
                for (int i = 0; i < imgStr.length; i++) {
                    picStrings.add(imgStr[i]);
                }
                for (String url : picStrings) {
                    LocalMedia imageItem = new LocalMedia();
                    imageItem.pictureType = "image/PNG";
                    imageItem.path = url;
                    selectList.add(imageItem);
                    Log.e("1345", "url = " + url);
                }

                Log.e("1345", news.getId());

                FullyGridLayoutManager manager = new FullyGridLayoutManager(mContext, 3
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
                                PictureSelector.create((Activity) mContext).externalPicturePreview(position, selectList);
                                break;
                            case 2:
                                // 预览视频
                                PictureSelector.create((Activity) mContext).externalPictureVideo(media.getPath());
                                break;
                            case 3:
                                // 预览音频
                                PictureSelector.create((Activity) mContext).externalPictureAudio(media.getPath());
                                break;
                        }
                    }
                });

            }

            sudokuViewHolder.tv_name.setText(news.getName());
            sudokuViewHolder.tv_time.setText(SDDateUtil.formatDuringFrom(news.getCreateTime()));
            sudokuViewHolder.tv_explain.setVisibility(View.GONE);

            if (!StringUtils.isNullOrEmpty(news.getConent())) {
//                sudokuViewHolder.tv_title.setText(news.getConent());
                sudokuViewHolder.contentTv.setVisibility(View.VISIBLE);
                setContent(sudokuViewHolder, position, news.getConent(), news.getId());
            } else {
                sudokuViewHolder.contentTv.setVisibility(View.GONE);
            }

//            setContentShowState(sudokuViewHolder, news);

            /*RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .priority(Priority.HIGH)
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
                    });*/

            sudokuViewHolder.album_praise_layout.setVisibility(View.GONE);
            sudokuViewHolder.linearlayout_comment.setVisibility(View.GONE);
            sudokuViewHolder.iv_head.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (clickItemListener != null) clickItemListener.onclick(news.getId());
                }
            });

            sudokuViewHolder.iv_head.setVisibility(View.GONE);

            // PEROSNDETAILS2 名人媒体H5
            sudokuViewHolder.rl_head.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (App.getId() == null) {
                        Intent intent = new Intent(mContext, WebViewActivity.class);

                        intent.putExtra(WebViewActivity.URL_KEY
                                , H5UrlData.PEROSNDETAILS2 + news.getResourcesId() + "&memberId=");
                        intent.putExtra(WebViewActivity.TITLE, "");
                        mContext.startActivity(intent);
                    } else {
                        Intent intent = new Intent(mContext, WebViewActivity.class);

                        intent.putExtra(WebViewActivity.URL_KEY
                                , H5UrlData.PEROSNDETAILS2 + news.getResourcesId() + "&memberId=" + App.getId());
                        intent.putExtra(WebViewActivity.TITLE, "");
                        mContext.startActivity(intent);
                    }
                }
            });


        } else if (holder instanceof VideoViewHolder) {
            final VideoViewHolder videoViewHolder = (VideoViewHolder) holder;
            videoViewHolder.tv_name.setText(news.getName());
            videoViewHolder.tv_title.setText(news.getName());
            videoViewHolder.tv_time.setText(SDDateUtil.formatDuringFrom(news.getCreateTime()));

            ((VideoViewHolder) holder).mController.setTitle(""); // 视频标题 一定要设置不然会有缓存
            //mController.setLenght(Long.parseLong("视频时间"));  // 播放时长
            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .priority(Priority.HIGH)
                    .diskCacheStrategy(DiskCacheStrategy.NONE);

            Glide.with(mContext)
                    .asBitmap()
                    .apply(options)
                    .load(news.getImgUrl())
                    .into(new BitmapImageViewTarget(videoViewHolder.mController.imageView()) {
                        @Override
                        protected void setResource(Bitmap resource) {
                            RoundedBitmapDrawable circularBitmapDrawable =
                                    RoundedBitmapDrawableFactory.
                                            create(mContext.getResources(), resource);
                            //circularBitmapDrawable.setCornerRadius(8); // 圆角
                            videoViewHolder.mController.imageView().setImageDrawable(circularBitmapDrawable);
                        }
                    });


            //videoViewHolder.consultation_video_player.setUp(news.getExternalUrl(), null);

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

        public TextViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
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

        public ImageViewHolder1(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

    //9张图片
    public class SudokuViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.rl_head)
        RelativeLayout rl_head;
        @BindView(R.id.iv_head)
        ImageView iv_head;
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
        TextView expandOrFoldTv;
        @BindView(R.id.ll_item)
        LinearLayout ll_item;

        @BindView(R.id.album_praise_layout)
        LinearLayout album_praise_layout;
        @BindView(R.id.album_praise)
        ImageView album_praise;
        @BindView(R.id.album_praise_number)
        TextView album_praise_number;

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

        public SudokuViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    //视频
    public class VideoViewHolder extends RecyclerView.ViewHolder {

        public TxVideoPlayerController mController;

        @BindView(R.id.tv_title)
        TextView tv_title;

        @BindView(R.id.tv_name)
        TextView tv_name;
        @BindView(R.id.tv_time)
        TextView tv_time;

        @BindView(R.id.consultation_video_player)
        NiceVideoPlayer consultation_video_player;

        public VideoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            // 将列表中的每个视频设置为默认16:9的比例
            ViewGroup.LayoutParams params = consultation_video_player.getLayoutParams();
            params.width = itemView.getResources().getDisplayMetrics().widthPixels; // 宽度为屏幕宽度
            params.height = (int) (params.width * 9f / 16f);    // 高度为宽度的9/16
            consultation_video_player.setLayoutParams(params);

            mController = new TxVideoPlayerController(mContext);
            consultation_video_player.setController(mController);
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

        void onclick(String id);

        // 删除动态  // 参数一 表示删除的位置  参数二表示删除的ID
        void onDeleteItemButtonClick(int position, int commentId);

        // 添加赞   参数一 表示点赞的位置
        void addPraise(int mPosition, String id,int mSize);

        // 取消赞   参数一 表示点赞的位置  参数二 表示点赞的状态
        void deletePraise(int mCirclePosition);

        // 回复评论  参数一 表示回复的实体类
        void onItemButtonClick(CommentConfig config, String id,int mSize);

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

    private void setContent(final RecommendChildAdapter.SudokuViewHolder holder, final int position, String contentStr, final String newsId){
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
