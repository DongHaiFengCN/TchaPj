package com.application.tchapj.my.adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.application.tchapj.R;
import com.application.tchapj.my.bean.FrozenInfoBean;
import com.application.tchapj.utils.CommonUtils;
import com.application.tchapj.utils.ToastUtil;

import java.math.BigDecimal;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 冻结资金列表适配器
 */
public class FrozenListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final LayoutInflater mLayoutInflater;
    private final Context mContext;
    private List<FrozenInfoBean> frozenInfoBeans;

    private FrozenListClickListener clickListener;

    public FrozenListAdapter(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        notifyDataSetChanged();
    }

    public void setDatas(List<FrozenInfoBean> frozenInfoBeans) {
        this.frozenInfoBeans = frozenInfoBeans;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return frozenInfoBeans == null ? 0 : frozenInfoBeans.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mLayoutInflater.inflate(R.layout.layout_item_frozen_list, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ViewHolder && frozenInfoBeans != null && frozenInfoBeans.get(position) != null) {

            final FrozenInfoBean frozenInfoBean = frozenInfoBeans.get(position);
            final ViewHolder holder1 = (ViewHolder) holder;

            holder1.taskNameTv.setText(frozenInfoBean.getName());
            holder1.taskMoneyTv.setText(CommonUtils.moneyToVMoney(frozenInfoBean.getMoney()));

            String taskStatus = frozenInfoBean.getStatus();//1 审核中 2 进行中 3 已结束
            if(taskStatus != null){
                String taskStatusStr = "";
                if(taskStatus.equals("1")){
                    taskStatusStr = "审核中";
                } else if(taskStatus.equals("2")){
                    taskStatusStr = "进行中";
                } else if(taskStatus.equals("3")){
                    taskStatusStr = "已完成";
                } else if(taskStatus.equals("4")){
                    taskStatusStr = "审核不通过";
                }
                holder1.taskStateTv.setText(taskStatusStr);//任务状态
            }

            //冻结余额状态：0:未转出 1:已转出
            if(frozenInfoBean.getFrozenOutStatus() != null && frozenInfoBean.getFrozenOutStatus().equals("0")){
                if(taskStatus != null && (taskStatus.equals("3") || taskStatus.equals("4"))){
                    holder1.taskActionTv.setText("");//冻结余额状态
                    holder1.taskActionTv.setBackgroundResource(R.drawable.frozen_list_item_tobalance_icon);
                    if(clickListener != null){
                        holder1.taskActionTv.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                clickListener.frozenToBalanceClick(frozenInfoBean.getId(), frozenInfoBean.getMoney(), position);
                            }
                        });
                    }

                }else{
                    holder1.taskActionTv.setText("");
                    holder1.taskActionTv.setBackgroundResource(R.drawable.frozen_list_item_tobalance_icon_normal);
                    holder1.taskActionTv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            ToastUtil.show(mContext, "活动未结束，暂不可转入");
                        }
                    });
                }

            }else if(frozenInfoBean.getFrozenOutStatus() != null && frozenInfoBean.getFrozenOutStatus().equals("1")){
                holder1.taskActionTv.setText("已转入");
                holder1.taskActionTv.setBackground(null);
            }else{
                holder1.taskActionTv.setText("");
                holder1.taskActionTv.setBackground(null);
            }



        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_frozen_list_task_name)
        TextView taskNameTv ;
        @BindView(R.id.item_frozen_list_task_state)
        TextView taskStateTv;
        @BindView(R.id.item_frozen_list_task_action)
        public TextView taskActionTv;
        @BindView(R.id.item_frozen_list_task_money)
        TextView taskMoneyTv;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void setClickListener(FrozenListClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public interface FrozenListClickListener{
        void frozenToBalanceClick(String id, BigDecimal money, int position);
    }

}
