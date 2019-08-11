package com.application.tchapj.main.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
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
import com.application.tchapj.main.bean.PersonMediaModel;
import com.application.tchapj.utils2.CircleImageView;
import com.application.tchapj.utils2.FlowGroupView;
import com.application.tchapj.utils2.qiniu.utils.StringUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tanger on 2018/3/19.
 */

public class PersonAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final LayoutInflater mLayoutInflater;
    private final Context mContext;
    private List<PersonMediaModel.PersonMediaModelResult.PersonMedia> personMedias;

    public PersonAdapter(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        notifyDataSetChanged();
    }

    public void setDatas(List<PersonMediaModel.PersonMediaModelResult.PersonMedia> personMedias) {
        this.personMedias = personMedias;
        notifyDataSetChanged();
    }

    public void addDatas(List<PersonMediaModel.PersonMediaModelResult.PersonMedia> personMedias) {
//        this.personMedias = personMedias;
        for (int i = 0 ;i<personMedias.size();i++){
            this.personMedias.add(personMedias.get(i));
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return personMedias == null ? 0 : personMedias.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mLayoutInflater.inflate(R.layout.item_person, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ViewHolder) {
            final ViewHolder holder1 = (ViewHolder) holder;
            String type = personMedias.get(position).getCat_type();

            if (!StringUtils.isNullOrEmpty(type) && type.equals("0")){
                holder1.ic_type.setImageResource(R.mipmap.ic_red_v2);
            }else if (!StringUtils.isNullOrEmpty(type) &&type.equals("1")){
                holder1.ic_type.setImageResource(R.mipmap.ic_blue_v2);
            }

            holder1.tv_explain.setText(personMedias.get(position).getContent());
            holder1.tv_name.setText(personMedias.get(position).getNickName());

            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .priority(Priority.HIGH)
                    .error(R.mipmap.ic_media_head_default)
                    .placeholder(R.mipmap.ic_media_head_default)
                    .diskCacheStrategy(DiskCacheStrategy.ALL);

            Glide.with(mContext)
                    .asBitmap()
                    .apply(options)
                    .load(personMedias.get(position).getHeadUrl())
                    .into(new BitmapImageViewTarget(holder1.iv_head) {
                        @Override
                        protected void setResource(Bitmap resource) {
                            RoundedBitmapDrawable circularBitmapDrawable =
                                    RoundedBitmapDrawableFactory.
                                            create(mContext.getResources(), resource);
                            //circularBitmapDrawable.setCornerRadius(8); // 圆角
                            holder1.iv_head.setImageDrawable(circularBitmapDrawable);
                        }
                    });

            if(!StringUtils.isNullOrEmpty(personMedias.get(position).getZixunName())){
                holder1.zixunName.setText(personMedias.get(position).getZixunName());
                holder1.zixunName.setVisibility(View.VISIBLE);
            }else{
                holder1.zixunName.setVisibility(View.GONE);
            }

            String[] str;
            if(personMedias.get(position) != null && personMedias.get(position).getService_label() != null){
                str = personMedias.get(position).getService_label().split(",");
                List<String> tagList = Arrays.asList(str);
                for (int i = 0; i < tagList.size(); i++) {
                    TextView child = new TextView(mContext);
                    ViewGroup.MarginLayoutParams params = new ViewGroup.MarginLayoutParams(ViewGroup.MarginLayoutParams.WRAP_CONTENT, ViewGroup.MarginLayoutParams.WRAP_CONTENT);
                    params.setMargins(10, 5, 5, 5);
                    child.setLayoutParams(params);
                    child.setBackgroundResource(R.drawable.flow_style);
                    child.setText(tagList.get(i));
                    child.setTextColor(mContext.getResources().getColor(R.color.color88));
                    holder1.flow_view_group.addView(child);
                }

                holder1.flowLayout.setAdapter(new TagAdapter<String>(tagList) {
                    @Override
                    public View getView(FlowLayout parent, int position, String s) {
                        TextView tv = (TextView) mLayoutInflater.inflate(R.layout.item_tag,
                                holder1.flowLayout, false);
                        tv.setText(s);
                        return tv;
                    }

                });
            }


            holder1.item_person.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(App.getId()==null){
                        Intent intent = new Intent(mContext, WebViewActivity.class);

                        intent.putExtra(WebViewActivity.URL_KEY
                                , H5UrlData.PEROSNDETAILS2 + personMedias.get(position).getId() + "&memberId=");
                        intent.putExtra(WebViewActivity.TITLE, "");
                        mContext.startActivity(intent);
                    }else {
                        Intent intent = new Intent(mContext, WebViewActivity.class);

                        intent.putExtra(WebViewActivity.URL_KEY
                                , H5UrlData.PEROSNDETAILS2 + personMedias.get(position).getId() + "&memberId=" + App.getId());
                        intent.putExtra(WebViewActivity.TITLE, "");
                        mContext.startActivity(intent);
                    }

                }
            });
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ic_type)
        ImageView ic_type ;

        @BindView(R.id.tv_explain)
        TextView tv_explain;
        @BindView(R.id.tv_name)
        TextView tv_name;

        @BindView(R.id.iv_head)
        CircleImageView iv_head;

        @BindView(R.id.flow_view_group)
        FlowGroupView flow_view_group;

        @BindView(R.id.zixunName)
        TextView zixunName;

        @BindView(R.id.item_person)
        LinearLayout item_person;

        @BindView(R.id.flowlayout)
        TagFlowLayout flowLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
