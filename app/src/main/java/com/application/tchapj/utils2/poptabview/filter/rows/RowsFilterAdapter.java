package com.application.tchapj.utils2.poptabview.filter.rows;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;

import com.application.tchapj.R;
import com.application.tchapj.utils2.poptabview.base.SuperAdapter;
import com.application.tchapj.utils2.poptabview.base.SuperListener;
import com.application.tchapj.utils2.poptabview.listener.OnHolderClickedListener;
import com.application.tchapj.utils2.poptabview.listener.OnSingleItemClickListener;

import static com.application.tchapj.utils2.poptabview.FilterConfig.ROWS_INITIAL_COUNT;


/**
 * 筛选器adapter
 *
 * @author  ccj sj
 */
public class RowsFilterAdapter extends SuperAdapter {

    private boolean isExpand = false;//是否已展开

    @Override // 筛选器标签
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_filter, parent, false);
        return new FilterViewHolder(v, this);
    }

    public RowsFilterAdapter(SuperListener listener, int singleOrMultiply) {
        super(null, listener, singleOrMultiply);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getData() != null && position < getData().size()) {
            FilterViewHolder viewHolder = (FilterViewHolder) holder;
            viewHolder.tv_filter.setText(getData().get(position).getTab_name());

            if (getCheckedLists().contains(position)) {
                viewHolder.tv_filter.setChecked(true);
            } else {
                viewHolder.tv_filter.setChecked(false);
            }
        }
    }

    @Override
    public int getItemCount() {
        if (getData() == null) {
            return 0;
        } else if (getData().size() > ROWS_INITIAL_COUNT && !isExpand) {
            return ROWS_INITIAL_COUNT;
        }
        return getData().size();
    }

    @Override
    public void onFilterItemClick() {
        ((OnSingleItemClickListener) getListener()).onSingleItemClickListener( getCheckedLists());

    }


    public void clear() {
        if (getData() != null) {
            getData().clear();
            isExpand = false;
            notifyDataSetChanged();
        }
    }


    public void setExpand(boolean isExpand) {
        this.isExpand = isExpand;
        notifyDataSetChanged();
    }



    public static class FilterViewHolder extends SuperFilterViewHolder  {

        CheckedTextView tv_filter;

        public FilterViewHolder(View itemView, OnHolderClickedListener listener) {
            super(itemView,listener);
            tv_filter = (CheckedTextView) itemView.findViewById(R.id.tv_filter);
        }
    }
}
