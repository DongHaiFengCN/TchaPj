package com.application.tchapj.my.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.application.tchapj.App;
import com.application.tchapj.R;
import com.application.tchapj.base.BaseBean;
import com.application.tchapj.base.BaseMvpFragment;
import com.application.tchapj.bean.PromotionResultBean;
import com.application.tchapj.my.adpter.MoneySyAdapter;
import com.application.tchapj.my.bean.MoneyInfoBean;
import com.application.tchapj.my.bean.MoneyInfoListBean;
import com.application.tchapj.my.bean.MoneyTransferBean;
import com.application.tchapj.my.presenter.MoneyPresenter;
import com.application.tchapj.my.view.IMoneyView;
import com.application.tchapj.widiget.LogUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.application.tchapj.DataManager.getDataManager;


// 提现
public class MoneyTxFragment extends Fragment {

    SmartRefreshLayout money_tx_srl;
    RecyclerView money_tx_rv;

    private int pageNum = 1;
    private MyAdapter myAdapter;
    // 提现
    private List<MoneyInfoListBean.MoneyInfoListBeanResult.With> with;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tx, null);
        money_tx_srl = view.findViewById(R.id.money_tx_srl);
        money_tx_rv = view.findViewById(R.id.money_tx_rv);
        myAdapter = new MyAdapter();
        money_tx_rv.setAdapter(myAdapter);
        money_tx_rv.setLayoutManager(new LinearLayoutManager(getActivity()));

        money_tx_srl.setEnableRefresh(false);
        money_tx_srl.setOnLoadMoreListener(new OnLoadMoreListener() {
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
        ((App) (getActivity().getApplication())).getAppComponent()
                .getAPIService() // 所有接口对象
                .getMoneyInfoListBeanResult(pageNum + "", "10", getDataManager().quickGetMetaData(R.string.id,String.class), "4", "002", "1.0", "","JSON")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MoneyInfoListBean>() {
                    @Override
                    public void onCompleted() {
                        money_tx_srl.finishLoadMore();
                    }

                    @Override
                    public void onError(Throwable e) {
                        money_tx_srl.finishLoadMore();
                        pageNum--;
                        Toast.makeText(getActivity(),"获取提现记录失败！请尝试上拉重新加载",Toast.LENGTH_SHORT).show();
                    }

                    @Override // 得到数据
                    public void onNext(MoneyInfoListBean moneyInfoListBean) {
                        if (with == null) {
                            with = new ArrayList<>();
                        }
                        with.addAll(moneyInfoListBean.getData().getWiths());
                        myAdapter.notifyDataSetChanged();
                        money_tx_srl.finishLoadMore();

                    }
                });
    }

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            @SuppressLint("InflateParams")
            View view = getLayoutInflater().inflate(R.layout.fragment_tx_item, null);
            return new ViewHolder(view);
        }

        @SuppressLint("SetTextI18n")
        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

            MoneyInfoListBean.MoneyInfoListBeanResult.With with1 = with.get(position);

            holder.amountTv.setText(with1.getMoney());
            holder.statusTv.setText(with1.getStatus());
            holder.timeTv.setText(with1.getCreateTime()+"");
            holder.accountTv.setText(with1.getAccountNumber());
        }

        @Override
        public int getItemCount() {
            return with == null ? 0 : with.size();
        }




        class ViewHolder extends RecyclerView.ViewHolder {
            TextView timeTv;
            TextView amountTv;
            TextView statusTv;
            TextView accountTv;

            ViewHolder(View itemView) {
                super(itemView);
                itemView.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
                timeTv = itemView.findViewById(R.id.time_tv);
                amountTv = itemView.findViewById(R.id.amount_tv);
                statusTv = itemView.findViewById(R.id.status_tv);
                accountTv = itemView.findViewById(R.id.account_tv);
            }

        }

    }

}
