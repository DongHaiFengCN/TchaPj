package com.application.tchapj.my.fragment;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.application.tchapj.App;
import com.application.tchapj.R;
import com.application.tchapj.my.bean.MoneyInfoListBean;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.application.tchapj.DataManager.getDataManager;


/**
 * @author 董海峰
 */
public class MoneyCzFragment extends Fragment {
    SmartRefreshLayout money_cz_srl;
    RecyclerView money_cz_rv;

    private int pageNum = 1;
    private MyAdapter myAdapter;

    private List<MoneyInfoListBean.MoneyInfoListBeanResult.AppWallet> appWallets;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cz, null);
        money_cz_srl = view.findViewById(R.id.money_cz_srl);
        money_cz_rv = view.findViewById(R.id.money_cz_rv);

        myAdapter = new MyAdapter();
        money_cz_rv.setAdapter(myAdapter);
        money_cz_rv.setLayoutManager(new LinearLayoutManager(getContext()));

        money_cz_srl.setEnableRefresh(false);
        money_cz_srl.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pageNum++;
                onGetMoneyInfoListBeanResult();
            }
        });
        onGetMoneyInfoListBeanResult();
        return view;
    }

    public void onGetMoneyInfoListBeanResult() {
        ((App) (Objects.requireNonNull(getActivity()).getApplication())).getAppComponent()
                .getAPIService() // 所有接口对象
                .getMoneyInfoListBeanResult(pageNum+"" , "10", getDataManager().quickGetMetaData(R.string.id,String.class), "3", "002", "1.0", "","JSON")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MoneyInfoListBean>() {
                    @Override
                    public void onCompleted() {
                        money_cz_srl.finishLoadMore();
                    }

                    @Override
                    public void onError(Throwable e) {
                        pageNum--;
                        money_cz_srl.finishLoadMore();
                        Toast.makeText(getActivity(),"获取提充值录失败！请尝试上拉重新加载",Toast.LENGTH_SHORT).show();
                    }

                    @Override // 得到数据
                    public void onNext(MoneyInfoListBean moneyInfoListBean) {

                        if (appWallets == null) {
                            appWallets = new ArrayList<>();
                        }


                        appWallets.addAll(moneyInfoListBean.getData().getAppWallets());

                        myAdapter.notifyDataSetChanged();
                        money_cz_srl.finishLoadMore();
                    }
                });
    }


    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            @SuppressLint("InflateParams")
            View view = getLayoutInflater().inflate(R.layout.fragment_cz_item, null);
            return new ViewHolder(view);
        }

        @SuppressLint("SetTextI18n")
        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

            MoneyInfoListBean.MoneyInfoListBeanResult.AppWallet appWallet = appWallets.get(position);

            holder.amountTv.setText(String.valueOf(appWallet.getMoney()));
            holder.numberTv.setText(appWallet.getAppWalletLogId());
            holder.timeTv.setText(appWallet.getCreateTime()+"");
        }

        @Override
        public int getItemCount() {
            return appWallets == null ? 0 : appWallets.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            TextView timeTv;
            TextView amountTv;
            TextView numberTv;

            ViewHolder(View itemView) {
                super(itemView);

                timeTv = itemView.findViewById(R.id.time_tv);
                amountTv = itemView.findViewById(R.id.amount_tv);
                numberTv = itemView.findViewById(R.id.number_tv);

            }

        }

    }

}
