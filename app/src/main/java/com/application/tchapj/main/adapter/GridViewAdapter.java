package com.application.tchapj.main.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.application.tchapj.R;
import com.application.tchapj.main.bean.HomePerson;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import java.util.List;

import static com.application.tchapj.R.id.personDetails;

/**
 * Created by lijuan on 2016/9/12.
 */
public class GridViewAdapter extends BaseAdapter {

    private  Context mContext;
    private List<HomePerson.HomePersonResult.HomeMingren> mDatas;
    private LayoutInflater inflater;
    /**
     * 页数下标,从0开始(当前是第几页)
     */
    private int curIndex;
    /**
     * 每一页显示的个数
     */
    private int pageSize;

    public GridViewAdapter(Context context, List<HomePerson.HomePersonResult.HomeMingren> mDatas, int curIndex, int pageSize) {
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
            convertView = inflater.inflate(R.layout.item_gridview, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.personDetails = (RelativeLayout) convertView.findViewById(personDetails);
            viewHolder.new_tv_name = (TextView) convertView.findViewById(R.id.new_tv_name);
            viewHolder.new_iv01 = (ImageView) convertView.findViewById(R.id.new_iv01);
            viewHolder.new_tv_introduce = (TextView) convertView.findViewById(R.id.new_tv_introduce);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        /**
         * 在给View绑定显示的数据时，计算正确的position = position + curIndex * pageSize，
         */
        final int pos = position + curIndex * pageSize;
        viewHolder.new_tv_name.setText(mDatas.get(pos).getNickName());

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .priority(Priority.HIGH)
                .diskCacheStrategy(DiskCacheStrategy.ALL);

        final ViewHolder finalViewHolder = viewHolder;
        Glide.with(mContext)
                .asBitmap()
                .apply(options)
                .load(mDatas.get(pos).getBig_url())
                .into(new BitmapImageViewTarget(finalViewHolder.new_iv01) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.
                                        create(mContext.getResources(), resource);
                        circularBitmapDrawable.setCornerRadius(8); // 圆角
                        finalViewHolder.new_iv01.setImageDrawable(circularBitmapDrawable);
                    }
                });

        viewHolder.new_tv_introduce.setText(mDatas.get(pos).getContent());

        return convertView;
    }


    class ViewHolder {

        public RelativeLayout personDetails;
        public TextView new_tv_name;
        public ImageView new_iv01;
        public TextView new_tv_introduce;
    }
}