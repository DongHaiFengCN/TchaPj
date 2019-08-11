package com.application.tchapj.search.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.application.tchapj.App;
import com.application.tchapj.H5UrlData;
import com.application.tchapj.R;
import com.application.tchapj.WebViewActivity;
import com.application.tchapj.base.BaseMvpActivity;
import com.application.tchapj.consultation.adapter.ConsultationInfoAdapter;
import com.application.tchapj.consultation.bean.MessageNews;
import com.application.tchapj.search.activity.SearchInfoActivity;
import com.application.tchapj.search.activity.SearchInfoMostActivity;
import com.application.tchapj.search.bean.SearchBean;
import com.application.tchapj.utils2.CircleImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.jude.easyrecyclerview.EasyRecyclerView;

import java.util.List;

// 主页适配器实现类
public class SearchRecyclerAdapter extends RecyclerView.Adapter {
    private static final String TAG = "HomeRecyclerAdapter";

    private SearchInfoActivity mActivity;
    private Context mContext;

    private String name ;

    private List<SearchBean.SearchBeanResult.SearchMingrenList> listSearchMingrens;
    private List<MessageNews> listSearchNews;

    private ConsultationInfoAdapter recommendChildAdapter;

    // type
    /**
     * 头部
     */
    public static final int TYPE_SLIDER = 0xff01;
    /**
     * 普通条目
     */
    public static final int TYPE_TYPE1 = 0xff02;


    public SearchRecyclerAdapter(SearchInfoActivity activity, List<SearchBean.SearchBeanResult.SearchMingrenList> listSearchMingrens, List<MessageNews> listSearchNews, String name) {
        this.mActivity = activity;
        this.mContext = activity;
        this.listSearchMingrens = listSearchMingrens;
        this.listSearchNews = listSearchNews;
        this.name = name;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_SLIDER:
                return new HolderSlider(LayoutInflater.from(parent.getContext()).inflate(R.layout.search_recycler_item_slider, parent, false));
            case TYPE_TYPE1:
                return new HolderType1(LayoutInflater.from(parent.getContext()).inflate(R.layout.search_recycler_item_type_content, parent, false));
            default:
                Log.e(TAG, "viewholder is null");
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HolderSlider) {
            bindTypeSlider((HolderSlider) holder, position - 1);
        } else if (holder instanceof HolderType1) {
            bindType1((HolderType1) holder, position - 1);
        }
    }

    @Override // 返回总条数，共3种类型
    public int getItemCount() {
        return 2;

    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_SLIDER;
        } else {
            return TYPE_TYPE1;
        }
    }

    // TODO 设置头部信息
    private void bindTypeSlider(final HolderSlider holder, int position) {
        if(listSearchMingrens == null || listSearchMingrens.size() == 0){
            holder.search_ll.setVisibility(View.GONE);
            holder.search_ll2.setVisibility(View.GONE);
            holder.search_ll3.setVisibility(View.GONE);
            return;
        }


        if (listSearchMingrens.size() == 1) {

            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .priority(Priority.HIGH)
                    .diskCacheStrategy(DiskCacheStrategy.NONE);

            Glide.with(mContext)
                    .asBitmap()
                    .apply(options)
                    .load(listSearchMingrens.get(0).getHeadUrl())
                    .into(new BitmapImageViewTarget(holder.search_iv_head) {
                        @Override
                        protected void setResource(Bitmap resource) {
                            RoundedBitmapDrawable circularBitmapDrawable =
                                    RoundedBitmapDrawableFactory.
                                            create(mContext.getResources(), resource);
                            //circularBitmapDrawable.setCornerRadius(8); // 圆角
                            holder.search_iv_head.setImageDrawable(circularBitmapDrawable);
                        }
                    });

            holder.search_tv_name.setText(listSearchMingrens.get(0).getNickName());
            holder.search_tv_explain.setText(listSearchMingrens.get(0).getContent());
            holder.search_zixunName.setText(listSearchMingrens.get(0).getNewsTypeId());
            holder.search_ll.setVisibility(View.VISIBLE);
            holder.search_ll2.setVisibility(View.GONE);
            holder.search_ll3.setVisibility(View.GONE);

            holder.search_ll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(App.getId()==null){
                        Intent intent = new Intent(mContext, WebViewActivity.class);

                        intent.putExtra(WebViewActivity.URL_KEY
                                , H5UrlData.PEROSNDETAILS2 + listSearchMingrens.get(0).getId() + "&memberId=");
                        intent.putExtra(WebViewActivity.TITLE, "");
                        mContext.startActivity(intent);
                    }else {
                        Intent intent = new Intent(mContext, WebViewActivity.class);

                        intent.putExtra(WebViewActivity.URL_KEY
                                , H5UrlData.PEROSNDETAILS2 + listSearchMingrens.get(0).getId() + "&memberId=" + App.getId());
                        intent.putExtra(WebViewActivity.TITLE, "");
                        mContext.startActivity(intent);
                    }

                }
            });

        } else if (listSearchMingrens.size() == 2) {

            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .priority(Priority.HIGH)
                    .diskCacheStrategy(DiskCacheStrategy.NONE);

            Glide.with(mContext)
                    .asBitmap()
                    .apply(options)
                    .load(listSearchMingrens.get(0).getHeadUrl())
                    .into(new BitmapImageViewTarget(holder.search_iv_head) {
                        @Override
                        protected void setResource(Bitmap resource) {
                            RoundedBitmapDrawable circularBitmapDrawable =
                                    RoundedBitmapDrawableFactory.
                                            create(mContext.getResources(), resource);
                            //circularBitmapDrawable.setCornerRadius(8); // 圆角
                            holder.search_iv_head.setImageDrawable(circularBitmapDrawable);
                        }
                    });

            holder.search_tv_name.setText(listSearchMingrens.get(0).getNickName());
            holder.search_tv_explain.setText(listSearchMingrens.get(0).getContent());
            holder.search_zixunName.setText(listSearchMingrens.get(0).getNewsTypeId());

            Glide.with(mContext)
                    .asBitmap()
                    .apply(options)
                    .load(listSearchMingrens.get(1).getHeadUrl())
                    .into(new BitmapImageViewTarget(holder.search_iv_head2) {
                        @Override
                        protected void setResource(Bitmap resource) {
                            RoundedBitmapDrawable circularBitmapDrawable =
                                    RoundedBitmapDrawableFactory.
                                            create(mContext.getResources(), resource);
                            //circularBitmapDrawable.setCornerRadius(8); // 圆角
                            holder.search_iv_head2.setImageDrawable(circularBitmapDrawable);
                        }
                    });

            holder.search_tv_name2.setText(listSearchMingrens.get(1).getNickName());
            holder.search_tv_explain2.setText(listSearchMingrens.get(1).getContent());
            holder.search_zixunName2.setText(listSearchMingrens.get(1).getNewsTypeId());

            holder.search_ll.setVisibility(View.VISIBLE);
            holder.search_ll2.setVisibility(View.VISIBLE);
            holder.search_ll3.setVisibility(View.GONE);

            holder.search_ll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(App.getId()==null){
                        Intent intent = new Intent(mContext, WebViewActivity.class);

                        intent.putExtra(WebViewActivity.URL_KEY
                                , H5UrlData.PEROSNDETAILS2 + listSearchMingrens.get(0).getId() + "&memberId=");
                        intent.putExtra(WebViewActivity.TITLE, "");
                        mContext.startActivity(intent);
                    }else {
                        Intent intent = new Intent(mContext, WebViewActivity.class);

                        intent.putExtra(WebViewActivity.URL_KEY
                                , H5UrlData.PEROSNDETAILS2 + listSearchMingrens.get(0).getId() + "&memberId=" + App.getId());
                        intent.putExtra(WebViewActivity.TITLE, "");
                        mContext.startActivity(intent);
                    }

                }
            });

            holder.search_ll2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(App.getId()==null){
                        Intent intent = new Intent(mContext, WebViewActivity.class);

                        intent.putExtra(WebViewActivity.URL_KEY
                                , H5UrlData.PEROSNDETAILS2 + listSearchMingrens.get(1).getId() + "&memberId=");
                        intent.putExtra(WebViewActivity.TITLE, "");
                        mContext.startActivity(intent);
                    }else {
                        Intent intent = new Intent(mContext, WebViewActivity.class);

                        intent.putExtra(WebViewActivity.URL_KEY
                                , H5UrlData.PEROSNDETAILS2 + listSearchMingrens.get(1).getId() + "&memberId=" + App.getId());
                        intent.putExtra(WebViewActivity.TITLE, "");
                        mContext.startActivity(intent);
                    }

                }
            });


        } else if (listSearchMingrens.size() == 3) {

            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .priority(Priority.HIGH)
                    .diskCacheStrategy(DiskCacheStrategy.NONE);

            Glide.with(mContext)
                    .asBitmap()
                    .apply(options)
                    .load(listSearchMingrens.get(0).getHeadUrl())
                    .into(new BitmapImageViewTarget(holder.search_iv_head) {
                        @Override
                        protected void setResource(Bitmap resource) {
                            RoundedBitmapDrawable circularBitmapDrawable =
                                    RoundedBitmapDrawableFactory.
                                            create(mContext.getResources(), resource);
                            //circularBitmapDrawable.setCornerRadius(8); // 圆角
                            holder.search_iv_head.setImageDrawable(circularBitmapDrawable);
                        }
                    });

            holder.search_tv_name.setText(listSearchMingrens.get(0).getNickName());
            holder.search_tv_explain.setText(listSearchMingrens.get(0).getContent());
            holder.search_zixunName.setText(listSearchMingrens.get(0).getNewsTypeId());

            Glide.with(mContext)
                    .asBitmap()
                    .apply(options)
                    .load(listSearchMingrens.get(1).getHeadUrl())
                    .into(new BitmapImageViewTarget(holder.search_iv_head2) {
                        @Override
                        protected void setResource(Bitmap resource) {
                            RoundedBitmapDrawable circularBitmapDrawable =
                                    RoundedBitmapDrawableFactory.
                                            create(mContext.getResources(), resource);
                            //circularBitmapDrawable.setCornerRadius(8); // 圆角
                            holder.search_iv_head2.setImageDrawable(circularBitmapDrawable);
                        }
                    });

            holder.search_tv_name2.setText(listSearchMingrens.get(1).getNickName());
            holder.search_tv_explain2.setText(listSearchMingrens.get(1).getContent());
            holder.search_zixunName2.setText(listSearchMingrens.get(1).getNewsTypeId());

            Glide.with(mContext)
                    .asBitmap()
                    .apply(options)
                    .load(listSearchMingrens.get(2).getHeadUrl())
                    .into(new BitmapImageViewTarget(holder.search_iv_head3) {
                        @Override
                        protected void setResource(Bitmap resource) {
                            RoundedBitmapDrawable circularBitmapDrawable =
                                    RoundedBitmapDrawableFactory.
                                            create(mContext.getResources(), resource);
                            //circularBitmapDrawable.setCornerRadius(8); // 圆角
                            holder.search_iv_head3.setImageDrawable(circularBitmapDrawable);
                        }
                    });

            holder.search_tv_name3.setText(listSearchMingrens.get(2).getNickName());
            holder.search_tv_explain3.setText(listSearchMingrens.get(2).getContent());
            holder.search_zixunName3.setText(listSearchMingrens.get(2).getNewsTypeId());

            holder.search_ll.setVisibility(View.VISIBLE);
            holder.search_ll2.setVisibility(View.VISIBLE);
            holder.search_ll3.setVisibility(View.VISIBLE);

            holder.search_ll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(App.getId()==null){
                        Intent intent = new Intent(mContext, WebViewActivity.class);

                        intent.putExtra(WebViewActivity.URL_KEY
                                , H5UrlData.PEROSNDETAILS2 + listSearchMingrens.get(0).getId() + "&memberId=");
                        intent.putExtra(WebViewActivity.TITLE, "");
                        mContext.startActivity(intent);
                    }else {
                        Intent intent = new Intent(mContext, WebViewActivity.class);

                        intent.putExtra(WebViewActivity.URL_KEY
                                , H5UrlData.PEROSNDETAILS2 + listSearchMingrens.get(0).getId() + "&memberId=" + App.getId());
                        intent.putExtra(WebViewActivity.TITLE, "");
                        mContext.startActivity(intent);
                    }

                }
            });

            holder.search_ll2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(App.getId()==null){
                        Intent intent = new Intent(mContext, WebViewActivity.class);

                        intent.putExtra(WebViewActivity.URL_KEY
                                , H5UrlData.PEROSNDETAILS2 + listSearchMingrens.get(1).getId() + "&memberId=");
                        intent.putExtra(WebViewActivity.TITLE, "");
                        mContext.startActivity(intent);
                    }else {
                        Intent intent = new Intent(mContext, WebViewActivity.class);

                        intent.putExtra(WebViewActivity.URL_KEY
                                , H5UrlData.PEROSNDETAILS2 + listSearchMingrens.get(1).getId() + "&memberId=" + App.getId());
                        intent.putExtra(WebViewActivity.TITLE, "");
                        mContext.startActivity(intent);
                    }

                }
            });

            holder.search_ll3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(App.getId()==null){
                        Intent intent = new Intent(mContext, WebViewActivity.class);

                        intent.putExtra(WebViewActivity.URL_KEY
                                , H5UrlData.PEROSNDETAILS2 + listSearchMingrens.get(2).getId() + "&memberId=");
                        intent.putExtra(WebViewActivity.TITLE, "");
                        mContext.startActivity(intent);
                    }else {
                        Intent intent = new Intent(mContext, WebViewActivity.class);

                        intent.putExtra(WebViewActivity.URL_KEY
                                , H5UrlData.PEROSNDETAILS2 + listSearchMingrens.get(2).getId() + "&memberId=" + App.getId());
                        intent.putExtra(WebViewActivity.TITLE, "");
                        mContext.startActivity(intent);
                    }

                }
            });

        }

    }

    public class HolderSlider extends RecyclerView.ViewHolder {

        LinearLayout search_ll;
        CircleImageView search_iv_head;
        TextView search_tv_name;
        ImageView search_ic_type;
        TextView search_tv_explain;
        TextView search_zixunName;

        LinearLayout search_ll2;
        CircleImageView search_iv_head2;
        TextView search_tv_name2;
        ImageView search_ic_type2;
        TextView search_tv_explain2;
        TextView search_zixunName2;

        LinearLayout search_ll3;
        CircleImageView search_iv_head3;
        TextView search_tv_name3;
        ImageView search_ic_type3;
        TextView search_tv_explain3;
        TextView search_zixunName3;



        public HolderSlider(View itemView) {
            super(itemView);

            search_ll = (LinearLayout) itemView.findViewById(R.id.search_ll);
            search_iv_head = (CircleImageView) itemView.findViewById(R.id.search_iv_head);
            search_tv_name = (TextView) itemView.findViewById(R.id.search_tv_name);
            search_ic_type = (ImageView) itemView.findViewById(R.id.search_ic_type);
            search_tv_explain = (TextView) itemView.findViewById(R.id.search_tv_explain);
            search_zixunName = (TextView) itemView.findViewById(R.id.search_zixunName);

            search_ll2 = (LinearLayout) itemView.findViewById(R.id.search_ll2);
            search_iv_head2 = (CircleImageView) itemView.findViewById(R.id.search_iv_head2);
            search_tv_name2 = (TextView) itemView.findViewById(R.id.search_tv_name2);
            search_ic_type2 = (ImageView) itemView.findViewById(R.id.search_ic_type2);
            search_tv_explain2 = (TextView) itemView.findViewById(R.id.search_tv_explain2);
            search_zixunName2 = (TextView) itemView.findViewById(R.id.search_zixunName2);

            search_ll3 = (LinearLayout) itemView.findViewById(R.id.search_ll3);
            search_iv_head3 = (CircleImageView) itemView.findViewById(R.id.search_iv_head3);
            search_tv_name3 = (TextView) itemView.findViewById(R.id.search_tv_name3);
            search_ic_type3 = (ImageView) itemView.findViewById(R.id.search_ic_type3);
            search_tv_explain3 = (TextView) itemView.findViewById(R.id.search_tv_explain3);
            search_zixunName3 = (TextView) itemView.findViewById(R.id.search_zixunName3);

        }
    }

    private void bindType1(HolderType1 holder, final int position) {
        if (listSearchNews != null && listSearchNews.size() > 0) {

            recommendChildAdapter = new ConsultationInfoAdapter(mActivity, mActivity);
            holder.search_recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
            holder.search_recyclerView.addItemDecoration(new DividerItemDecoration(mContext,DividerItemDecoration.VERTICAL));
            recommendChildAdapter.setData(listSearchNews);

            holder.search_recyclerView.setAdapter(recommendChildAdapter);

        }

        holder.moreLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 查看更多
                Intent intent = new Intent(mContext, SearchInfoMostActivity.class);
                intent.putExtra("Name", name);
                mContext.startActivity(intent);
            }
        });


        if (mOnItemClickLitener != null) {
            holder.item_type_content.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickLitener.onItemClick(v, position);
                }
            });
        }
    }

    public class HolderType1 extends RecyclerView.ViewHolder {
        View item_type_content;
        LinearLayout moreLl;
        private EasyRecyclerView search_recyclerView;

        public HolderType1(View itemView) {
            super(itemView);

            search_recyclerView = (EasyRecyclerView) itemView.findViewById(R.id.search_recyclerView);
            moreLl = itemView.findViewById(R.id.search_llMore);

        }
    }

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }


}
