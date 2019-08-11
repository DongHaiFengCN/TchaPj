package com.application.tchapj.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.application.tchapj.R;
import com.application.tchapj.main.bean.PersonSelectModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tanger on 2018/3/19.
 */

public class ListTextAdapter extends RecyclerView.Adapter<ListTextAdapter.ViewHolder> {

    private final LayoutInflater mLayoutInflater;
    private final Context mContext;
    private List<PersonSelectModel.PersonSelectModelResult.NewStypeSelect> datas;

    public ListTextAdapter(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    public void setDatas(List<PersonSelectModel.PersonSelectModelResult.NewStypeSelect> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mLayoutInflater.inflate(R.layout.item_text_list, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        if (holder instanceof ViewHolder) {
            holder.title.setText(datas.get(position).getName());
            holder.title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (textClickListeren != null) {
                        textClickListeren.Click(datas.get(position).getId(), datas.get(position).getName());
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.title)
        TextView title;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }

    private OnTextClickListeren textClickListeren;

    public OnTextClickListeren getTextClickListeren() {
        return textClickListeren;
    }

    public void setTextClickListeren(OnTextClickListeren textClickListeren) {
        this.textClickListeren = textClickListeren;
    }

    public interface OnTextClickListeren {
        void Click(String id, String type);
    }
}
