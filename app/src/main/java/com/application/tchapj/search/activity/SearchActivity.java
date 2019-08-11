package com.application.tchapj.search.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.application.tchapj.R;
import com.application.tchapj.base.BaseMvpActivity;
import com.application.tchapj.my.bean.MicroTabBean;
import com.application.tchapj.search.bean.SearchBean;
import com.application.tchapj.search.greendao.Info;
import com.application.tchapj.search.greendao.InfoDao;
import com.application.tchapj.search.presenter.SearchPresenter;
import com.application.tchapj.search.view.ISearchView;
import com.application.tchapj.utils.CommonUtils;
import com.application.tchapj.widiget.ToolbarHelper;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.application.tchapj.R.id.dao_tv_name;


public class SearchActivity extends BaseMvpActivity<ISearchView, SearchPresenter>
        implements ISearchView {

    @BindView(R.id.search_edit)
    EditText search_edit;
    @BindView(R.id.search_tv)
    TextView search_tv;

    @BindView(R.id.top_search_flow_layout)
    TagFlowLayout top_search_flow_layout;
    @BindView(R.id.search_history_clear_all_tv)
    TextView search_history_clear_all_tv;

    @BindView(R.id.search_history_rv)
    ListView search_history_rv;

    // 数据库操作类
    private InfoDao dao;

    // 数据库实体类
    private List<Info> infos;
    private DaoAdapter daoadapter;

    // 标签
    private List<MicroTabBean.MessageNewsResult.MessageNews> newstypeList = new ArrayList<>();

    @Override
    protected void initToolbar(ToolbarHelper toolbarHelper) {

    }

    @Override
    public int getRootViewId() {
        return R.layout.activity_search;
    }


    @Override
    public void initUI() {

        getPresenter().onGetMicroTabResult();

        dao = new InfoDao(this);
        infos = dao.findAll();

        daoadapter = new DaoAdapter();
        search_history_rv.setAdapter(daoadapter);


        search_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openSearchInfoActivity();


            }

        });
    }

    private void openSearchInfoActivity() {

        String name = search_edit.getText().toString().trim();

        if (TextUtils.isEmpty(name)) {
            Toast.makeText(SearchActivity.this, "数据不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        for (int i = 0; i < infos.size(); i ++){
            if(name.equals(infos.get(i).getName())){
                dao.delete(infos.get(i).getId());
                break;
            }
        }

        long result = dao.add(name);
        if (result > 0) {

            infos = dao.findAll();
            daoadapter.notifyDataSetChanged(); // 从新调用getcount 调用getview
        }

        Intent intent = new Intent(SearchActivity.this, SearchInfoActivity.class);
        intent.putExtra("Name", name);
        startActivity(intent);
    }

    @Override
    public void initData() {

    }

    @NonNull
    @Override
    public SearchPresenter createPresenter() {
        return new SearchPresenter(getApp());
    }


    @Override // 标签
    public void onGetMicroTabBeanResult(MicroTabBean microTabBean) {

        if ("000".equals(microTabBean.getCode())) {
            newstypeList = microTabBean.getData().getNewstypeList();
            initSizeData();
        }

    }

    @Override // 搜索内容
    public void onGetSearchBeanResult(SearchBean searchBean) {

    }

    @Override // 查看更多
    public void onGetSearchBeanResultMost(SearchBean searchBean) {

    }

    // 初始化数据
    private void initSizeData() {

        top_search_flow_layout.setAdapter(new TagAdapter<MicroTabBean.MessageNewsResult.MessageNews>(newstypeList) {
            @Override
            public View getView(FlowLayout parent, int position, MicroTabBean.MessageNewsResult.MessageNews messageNews) {
                assert SearchActivity.this != null;
                TextView tv = (TextView) LayoutInflater.from(SearchActivity.this).inflate(R.layout.flow_layout_tv,
                        parent, false);
                assert messageNews != null;
                final String name = messageNews.getName();
                tv.setText(name);
                setItemBackground(tv);

                top_search_flow_layout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
                    @Override
                    public boolean onTagClick(View view, int position, FlowLayout parent) {
                        showTopSearchView(position);
                        return true;
                    }
                });
                return tv;
            }

        });
    }

    private void showTopSearchView(int position) {

        search_edit.setText(newstypeList.get(position).getName().trim());
        search_edit.setSelection(search_edit.getText().length());
        openSearchInfoActivity();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

    }

    private void setItemBackground(TextView tv) {
        tv.setBackgroundColor(CommonUtils.randomTagColor());
        tv.setTextColor(ContextCompat.getColor(SearchActivity.this, R.color.white));
    }

    private class DaoAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return infos.size();
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            System.out.println("position" + position);

            View view = View.inflate(SearchActivity.this, R.layout.dao_item, null);

            final TextView tv_name = (TextView) view.findViewById(dao_tv_name);
            ImageView iv_delete = (ImageView) view.findViewById(R.id.dao_iv_delete);

            iv_delete.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(SearchActivity.this);
                    builder.setTitle("提醒");
                    builder.setMessage("是否确定删除？");
                    builder.setPositiveButton("确定删除", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            int result = dao.delete(infos.get(infos.size() - position - 1).getId());
                            Toast.makeText(SearchActivity.this, "删除了" + result + "个记录", Toast.LENGTH_SHORT).show();
                            // 重新获取数据
                            infos = dao.findAll();

                            // 适配器刷新
                            daoadapter.notifyDataSetChanged();
                        }
                    });
                    builder.setNegativeButton("取消", null);
                    builder.create().show();
                }
            });

            tv_name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    search_edit.setText(tv_name.getText().toString());
                    search_edit.setSelection(search_edit.getText().length());
                    openSearchInfoActivity();
                }
            });

            tv_name.setText(infos.get(infos.size() - position - 1).getName());//为了让历史记录倒叙排列

            return view;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }
    }

}
