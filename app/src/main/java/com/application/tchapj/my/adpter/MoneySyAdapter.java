package com.application.tchapj.my.adpter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.application.tchapj.R;
import com.application.tchapj.my.bean.MoneyInfoListBean;

import java.util.List;

// 收益钱数适配器
public class MoneySyAdapter extends RecyclerView.Adapter<MoneySyAdapter.MoneySyViewHolder> {

    private Context mContext;

    private List<MoneyInfoListBean.MoneyInfoListBeanResult.MoneyInfoTaskLogs> moneyInfoTaskLogsList;
    private final LayoutInflater mLayoutInflater;


    public MoneySyAdapter(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    public void setData(List<MoneyInfoListBean.MoneyInfoListBeanResult.MoneyInfoTaskLogs> datas) {
        moneyInfoTaskLogsList = datas;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return moneyInfoTaskLogsList != null ? moneyInfoTaskLogsList.size() : 0;
    }

    @Override
    public MoneySyAdapter.MoneySyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MoneySyViewHolder(mLayoutInflater.inflate(R.layout.fragment_sy_item, parent, false));

    }

    @Override
    public void onBindViewHolder(final MoneySyViewHolder holder, int position) {
        final MoneyInfoListBean.MoneyInfoListBeanResult.MoneyInfoTaskLogs moneyInfoTaskLogs = moneyInfoTaskLogsList.get(position);

        ((MoneySyViewHolder) holder).sy_tv.setText(moneyInfoTaskLogs.getName());

        // 0 朋友圈 1 微博 2 抖音合拍 3 抖音原创 4其他
        if(!TextUtils.isEmpty(moneyInfoTaskLogs.getTaskType())){
            if(moneyInfoTaskLogs.getTaskType().equals("0")){
                ((MoneySyViewHolder) holder).sy_tv2.setText("朋友圈");
            }else if(moneyInfoTaskLogs.getTaskType().equals("1")){
                ((MoneySyViewHolder) holder).sy_tv2.setText("微博");
            }else if(moneyInfoTaskLogs.getTaskType().equals("2")){
                ((MoneySyViewHolder) holder).sy_tv2.setText("抖音合拍");
            }else if(moneyInfoTaskLogs.getTaskType().equals("3")){
                ((MoneySyViewHolder) holder).sy_tv2.setText("抖音原创");
            }else {
                ((MoneySyViewHolder) holder).sy_tv2.setText("其他");
            }
        }

        ((MoneySyViewHolder) holder).sy_tv3.setText(Double.toString(moneyInfoTaskLogs.getSalary()));

    }


    class MoneySyViewHolder extends RecyclerView.ViewHolder {


        public TextView sy_tv;
        public TextView sy_tv2;
        public TextView sy_tv3;


        public MoneySyViewHolder(View itemView) {
            super(itemView);


            sy_tv = (TextView) itemView.findViewById(R.id.sy_tv);
            sy_tv2 = (TextView) itemView.findViewById(R.id.sy_tv2);
            sy_tv3 = (TextView) itemView.findViewById(R.id.sy_tv3);

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
