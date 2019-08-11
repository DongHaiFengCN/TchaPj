package com.application.tchapj.widiget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.application.tchapj.R;

import java.util.ArrayList;
import java.util.List;

// 标签适配器
public class TagDarenAdapter<T> extends BaseAdapter implements OnInitSelectedPosition {

    private final Context mContext;
    private final List<Integer> mDataList;

    public TagDarenAdapter(Context context) {
        this.mContext = context;
        mDataList = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_item_daren_tag, null);

        ImageView imageView = view.findViewById(R.id.daren_tag_iv);

        int imgWidth = (DensityUtil.getScreenWidth() - DensityUtil.dp2px(mContext, 20)) / 3;
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(imgWidth, imgWidth);
        imageView.setLayoutParams(layoutParams);

        int t = mDataList.get(position);
        imageView.setImageResource(t);

        return view;
    }

    public void onlyAddAll(List<Integer> datas) {
        mDataList.addAll(datas);
        notifyDataSetChanged();
    }

    public void clearAndAddAll(List<Integer> datas) {
        mDataList.clear();
        onlyAddAll(datas);
    }

    public int position;
    public void setSelected(int p) {
        position=p;
    }

    @Override
    public boolean isSelectedPosition(int poi) {
        if (poi == position) {
            return true;
        }
        return false;
    }
}
