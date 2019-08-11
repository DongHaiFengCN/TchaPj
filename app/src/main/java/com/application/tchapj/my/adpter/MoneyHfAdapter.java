package com.application.tchapj.my.adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.application.tchapj.R;
import com.application.tchapj.my.bean.MoneyInfoListBean;

import java.util.List;

/**
 * Created by Administrator on 2018\9\10 0010.
 */
// 花费钱数适配器
public class MoneyHfAdapter extends RecyclerView.Adapter<MoneyHfAdapter.MoneyHfViewHolder> {

    private Context mContext;

    private List<MoneyInfoListBean.MoneyInfoListBeanResult.MoneyInfoTasks> moneyInfoTasksList;
    private final LayoutInflater mLayoutInflater;


    public MoneyHfAdapter(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    public void setData(List<MoneyInfoListBean.MoneyInfoListBeanResult.MoneyInfoTasks> datas) {
        moneyInfoTasksList = datas;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return moneyInfoTasksList != null ? moneyInfoTasksList.size() : 0;
    }

    @Override
    public MoneyHfAdapter.MoneyHfViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MoneyHfViewHolder(mLayoutInflater.inflate(R.layout.fragment_hf_item, parent, false));

    }

    @Override
    public void onBindViewHolder(final MoneyHfViewHolder holder, int position) {
        final MoneyInfoListBean.MoneyInfoListBeanResult.MoneyInfoTasks moneyInfoTasks = moneyInfoTasksList.get(position);

        ((MoneyHfViewHolder) holder).hf_tv.setText(moneyInfoTasks.getName());

        // 0 朋友圈 1 微博 2 抖音合拍 3 抖音原创 4其他
        if(moneyInfoTasks.getTaskType().equals("0")){
            ((MoneyHfViewHolder) holder).hf_tv2.setText("朋友圈");
        }else if(moneyInfoTasks.getTaskType().equals("1")){
            ((MoneyHfViewHolder) holder).hf_tv2.setText("微博");
        }else if(moneyInfoTasks.getTaskType().equals("2")){
            ((MoneyHfViewHolder) holder).hf_tv2.setText("抖音合拍");
        }else if(moneyInfoTasks.getTaskType().equals("3")){
            ((MoneyHfViewHolder) holder).hf_tv2.setText("抖音原创");
        }else {
            ((MoneyHfViewHolder) holder).hf_tv2.setText("其他");
        }

        ((MoneyHfViewHolder) holder).hf_tv3.setText(Double.toString(moneyInfoTasks.getMoney()));


    }


    class MoneyHfViewHolder extends RecyclerView.ViewHolder {

        public TextView hf_tv;
        public TextView hf_tv2;
        public TextView hf_tv3;


        public MoneyHfViewHolder(View itemView) {
            super(itemView);


            hf_tv = (TextView) itemView.findViewById(R.id.hf_tv);
            hf_tv2 = (TextView) itemView.findViewById(R.id.hf_tv2);
            hf_tv3 = (TextView) itemView.findViewById(R.id.hf_tv3);

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
