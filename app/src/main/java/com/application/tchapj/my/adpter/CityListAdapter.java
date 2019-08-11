package com.application.tchapj.my.adpter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.application.tchapj.R;
import com.application.tchapj.main.bean.CityInfo;
import com.application.tchapj.my.bean.FansInfoBean;
import com.application.tchapj.utils2.CircleImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CityListAdapter extends BaseAdapter {

    private final LayoutInflater mLayoutInflater;
    private final Context mContext;
    private List<CityInfo> cityInfoBeans;

    public CityListAdapter(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        notifyDataSetChanged();
    }

    public void setDatas(List<CityInfo> cityInfoBeans) {
        this.cityInfoBeans = cityInfoBeans;
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return cityInfoBeans == null ? 0 : cityInfoBeans.size();
    }

//    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        return new ViewHolder(mLayoutInflater.inflate(R.layout.layout_item_city_list, parent, false));
//    }
//
//    @Override
//    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
//        if (holder instanceof ViewHolder) {
//            final ViewHolder holder1 = (ViewHolder) holder;
//            String cityName = cityInfoBeans.get(position).getCityName();
//            holder1.cityTv.setText(cityName);
//
//        }
//    }

    @Override
    public Object getItem(int position) {
        return cityInfoBeans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.layout_item_city_list, parent, false); //加载布局
            holder = new ViewHolder();
            holder.cityTv = convertView.findViewById(R.id.layout_item_city_list_city_name_tv);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.cityTv.setText(cityInfoBeans.get(position).getCityName());
        return convertView;
    }

    public class ViewHolder {

        @BindView(R.id.layout_item_city_list_city_name_tv)
        TextView cityTv;


    }

}
