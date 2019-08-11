package com.application.tchapj.main.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.application.tchapj.R;
import com.application.tchapj.main.bean.HomeMediaList;
import com.application.tchapj.utils2.CircleImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import java.util.List;

/**
 * Created by lijuan on 2016/9/12.
 */
public class HomeMediaAdapter2 extends BaseAdapter {

    private  Context mContext;
    private List<HomeMediaList.HomeMediaListResult.HomeMediaModel> mDatas;
    private LayoutInflater inflater;
    /**
     * 页数下标,从0开始(当前是第几页)
     */
    private int curIndex;
    /**
     * 每一页显示的个数
     */
    private int pageSize;

    public HomeMediaAdapter2(Context context, List<HomeMediaList.HomeMediaListResult.HomeMediaModel> mDatas, int curIndex, int pageSize) {
        inflater = LayoutInflater.from(context);
        this.mDatas = mDatas;
        this.curIndex = curIndex;
        this.pageSize = pageSize;
        mContext = context;
    }

    /**
     * 先判断数据集的大小是否足够显示满本页？mDatas.size() > (curIndex+1)*pageSize,
     * 如果够，则直接返回每一页显示的最大条目个数pageSize,
     * 如果不够，则有几项返回几,(mDatas.size() - curIndex * pageSize);(也就是最后一页的时候就显示剩余item)
     */
    @Override
    public int getCount() {
        return mDatas.size() > (curIndex + 1) * pageSize ? pageSize : (mDatas.size() - curIndex * pageSize);

    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position + curIndex * pageSize);
    }

    @Override
    public long getItemId(int position) {
        return position + curIndex * pageSize;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_home_media, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.item = (LinearLayout) convertView.findViewById(R.id.item);
            viewHolder.iv_head = (CircleImageView) convertView.findViewById(R.id.iv_head);
            viewHolder.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
            viewHolder.title_1 = (TextView) convertView.findViewById(R.id.title_1);
            viewHolder.title_2 = (TextView) convertView.findViewById(R.id.title_2);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        /**
         * 在给View绑定显示的数据时，计算正确的position = position + curIndex * pageSize，
         */
        final int pos = position + curIndex * pageSize;

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .priority(Priority.HIGH)
                .diskCacheStrategy(DiskCacheStrategy.ALL);

        final ViewHolder finalViewHolder = viewHolder;

        Glide.with(mContext)
                .asBitmap()
                .apply(options)
                .load(mDatas.get(pos).getHeadUrl())
                .into(new BitmapImageViewTarget(viewHolder.iv_head) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.
                                        create(mContext.getResources(), resource);
                        //circularBitmapDrawable.setCornerRadius(8); // 圆角
                        finalViewHolder.iv_head.setImageDrawable(circularBitmapDrawable);
                    }
                });

        viewHolder.tv_title.setText(mDatas.get(pos).getNickName());
        viewHolder.title_1.setText(mDatas.get(pos).getContent());
        viewHolder.title_2.setText(mDatas.get(pos).getFansTotal());

        return convertView;
    }

    class ViewHolder {

        public LinearLayout item;
        public CircleImageView iv_head;
        public TextView tv_title;
        public TextView title_1;
        public TextView title_2;
        /*@BindView(R.id.title_3)
        TextView title_3;*/
        /*@BindView(R.id.title_4)
        TextView title_4;*/

    }

}