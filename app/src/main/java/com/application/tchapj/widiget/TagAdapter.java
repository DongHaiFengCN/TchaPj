package com.application.tchapj.widiget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.application.tchapj.R;

import java.util.ArrayList;
import java.util.List;

// 标签适配器
public class TagAdapter<T> extends BaseAdapter implements OnInitSelectedPosition {

    private final Context mContext;
    private final List<T> mDataList;
    private int resId = -1;

    public TagAdapter(Context context) {
        this.mContext = context;
        mDataList = new ArrayList<>();
    }

    public TagAdapter(Context context, int resId) {
        this.mContext = context;
        mDataList = new ArrayList<>();
        this.resId = resId;
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
        View view;
        if(resId != -1){
            view = LayoutInflater.from(mContext).inflate(resId, null);
        }else{
            view = LayoutInflater.from(mContext).inflate(R.layout.tag_item, null);
        }



        TextView textView = (TextView) view.findViewById(R.id.tv_tag);
        T t = mDataList.get(position);

        if (t instanceof String) {
            textView.setText((String) t);
        }
        return view;
    }

    public void onlyAddAll(List<T> datas) {
        mDataList.addAll(datas);
        notifyDataSetChanged();
    }

    public void clearAndAddAll(List<T> datas) {
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
