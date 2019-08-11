package com.application.tchapj.utils2.poptabview.filter.link;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;

import com.application.tchapj.R;
import com.application.tchapj.utils2.poptabview.base.BaseFilterTabBean;
import com.application.tchapj.utils2.poptabview.listener.OnHolderClickedListener;

import java.util.List;



/**
 * 左侧一级筛选adapter
 * @author ccj on 17/3/23.
 */
public class FirstFilterAdapter extends RecyclerView.Adapter implements OnHolderClickedListener {

    private OnFirstItemClickListener mListener;

    private List<BaseFilterTabBean> mData;

    private int checkedPosition=0;
    private int type=-1;

    public FirstFilterAdapter(List<BaseFilterTabBean> beanList, OnFirstItemClickListener listener, int single2mutiple) {
        this.mData = beanList;
        mListener = listener;
        this.type=single2mutiple;
    }



    public FirstFilterAdapter(OnFirstItemClickListener listener) {
        mListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_filter_primary, parent, false);
        return new FilterViewHolder(v, this);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (mData != null && position < mData.size()) {
            FilterViewHolder viewHolder = (FilterViewHolder) holder;
            viewHolder.tv_filter.setText(mData.get(position).getTab_name());
            if (checkedPosition==(position)) {
                viewHolder.tv_filter.setChecked(true);
                viewHolder.tv_filter.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.arrow_left, 0);
            } else {
                viewHolder.tv_filter.setChecked(false);
                viewHolder.tv_filter.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            }
        }
    }

    @Override
    public int getItemCount() {
        if (mData == null) {
            return 0;
        }
        return mData.size();
    }

    public void setData(List<BaseFilterTabBean> data) {
        mData = data;
        notifyDataSetChanged();
    }

    public void clear() {
        if (mData != null) {
            mData.clear();
            checkedPosition=0;
            notifyDataSetChanged();
        }
    }

    public void clearData() {
        if (mData != null) {
            mData.clear();
            checkedPosition=0;
            notifyDataSetChanged();
        }
    }

    /**
     * 设置选中的id
     * @param checkedPosition
     */
    public void setCheckedPosition(int checkedPosition) {
        this.checkedPosition=checkedPosition;
        notifyDataSetChanged();
    }

    @Override
    public void onItemClick(int position) {
        if (position < mData.size()) {
            checkedPosition=position;
            mListener.onFirstItemClick(position,mData.get(position) );
            notifyDataSetChanged();

        }
    }

    public static class FilterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        CheckedTextView tv_filter;
        OnHolderClickedListener mListener;

        public FilterViewHolder(View itemView, OnHolderClickedListener listener) {
            super(itemView);
            tv_filter = (CheckedTextView) itemView.findViewById(R.id.tv_filter);
            tv_filter.setOnClickListener(this);
            mListener = listener;
        }

        @Override
        public void onClick(View v) {
            if (getAdapterPosition() != RecyclerView.NO_POSITION) {
                if (v instanceof CheckedTextView) {
                    mListener.onItemClick(getAdapterPosition());
                }
            }
        }
    }

    public interface OnFirstItemClickListener {
        void onFirstItemClick(int position, BaseFilterTabBean filterTabBeen);
    }
}
