package com.application.tchapj.main.activity;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.application.tchapj.App;
import com.application.tchapj.R;
import com.application.tchapj.base.BaseMvpActivity;
import com.application.tchapj.main.bean.MessagenotificationBean;
import com.application.tchapj.main.presenter.MessagenoficationPresenter;
import com.application.tchapj.main.view.IMessagenoficationView;
import com.application.tchapj.widiget.ToolbarHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by 安卓开发 on 2018/8/10.
 */

public class MessagenotificationActivity extends BaseMvpActivity<IMessagenoficationView, MessagenoficationPresenter>
        implements IMessagenoficationView {

    @BindView(R.id.toolbar_menu_iv)
    ImageView toolbar_menu_iv;
    @BindView(R.id.listView)
    ListView mListView;

    private List<Map<String, Object>> list = new ArrayList<>();

    private MessagenotificationBean.MessagenotificationBeanResult messagenotificationBeanResult =new MessagenotificationBean.MessagenotificationBeanResult() ;

    private Map<String, Object> map = new HashMap<>();

    @Override
    protected void initToolbar(ToolbarHelper toolbarHelper) {

        toolbarHelper.setTitle("消息通知");
    }

    @Override
    public int getRootViewId() {
        return R.layout.activity_message_notification;
    }

    @Override
    public void initUI() {

        if(App.getId()!=null){
            getPresenter().onGetMessagenotificationResult(App.getId());
        }else {
            initAdapter();
        }

        toolbar_menu_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.clear();
                mListView.setAdapter(new TimeLineAdapter(MessagenotificationActivity.this, list));
                mListView.notify();
            }
        });

    }

    @Override
    public void initData() {

    }

    @NonNull
    @Override
    public MessagenoficationPresenter createPresenter() {
        return new MessagenoficationPresenter(getApp());
    }

    @Override
    public void onGetMessagenotificationResult(MessagenotificationBean messagenotificationBean) {

        if ("000".equals(messagenotificationBean.getCode())) {
            messagenotificationBeanResult = messagenotificationBean.getData();
            map.put("title", messagenotificationBeanResult.getTitle());
            map.put("time", messagenotificationBeanResult.getCreateTime());
            list.add(map);
        }

        initAdapter();
    }

    private void initAdapter() {

        /*map = new HashMap<>();
        map.put("title", "派件中 快递员** 123456789");
        map.put("time", "2017-08-14 11:21:58");
        list.add(map);

        map = new HashMap<>();
        map.put("title", "快件已到达 上海浦东集散中心");
        map.put("time", "2017-08-14 11:21:58");
        list.add(map);

        map = new HashMap<>();
        map.put("title", "快件已到达 上海虹桥集散中心");
        map.put("time", "2017-08-14 11:21:58");
        list.add(map);

        map = new HashMap<>();
        map.put("title", "苏州吴江大客户营业部 已发出");
        map.put("time", "2017-08-14 11:21:58");
        list.add(map);

        map = new HashMap<>();
        map.put("title", "包裹正在等待揽收");
        map.put("time", "2017-08-14 11:21:58");
        list.add(map);*/

        mListView.setAdapter(new TimeLineAdapter(this, list));

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


    class TimeLineAdapter extends BaseAdapter {

        private Context context;
        private List<Map<String, Object>> list;
        private LayoutInflater inflater;

        public TimeLineAdapter(Context context, List<Map<String, Object>> list) {
            super();
            this.context = context;
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TimeLineHolder viewHolder;
            if (convertView == null) {
                inflater = LayoutInflater.from(parent.getContext());
                convertView = inflater.inflate(R.layout.item_time_line, null);
                viewHolder = new TimeLineHolder();
                viewHolder.title = (TextView) convertView.findViewById(R.id.title);
                viewHolder.time = (TextView) convertView.findViewById(R.id.time);
                viewHolder.view0 = convertView.findViewById(R.id.view0);
                viewHolder.view4 = convertView.findViewById(R.id.view4);
                viewHolder.image = (ImageView) convertView.findViewById(R.id.image);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (TimeLineHolder) convertView.getTag();
            }
            //根据key获取value
            String titleStr = list.get(position).get("title").toString();
            viewHolder.title.setText(titleStr);
            String timeStr = list.get(position).get("time").toString();
            viewHolder.time.setText(timeStr);
            //第一个去掉上面的竖线，并设置颜色
            if (position == 0) {
                viewHolder.title.setTextColor(ContextCompat.getColor(context, R.color.tool_bar_right));
                viewHolder.time.setTextColor(ContextCompat.getColor(context, R.color.tool_bar_right));
                viewHolder.image.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.shape_oval_dot_orage));
                viewHolder.view0.setVisibility(View.INVISIBLE);
            } else {
                viewHolder.title.setTextColor(Color.GRAY);
                viewHolder.time.setTextColor(Color.GRAY);
            }
            //最后一个去掉底部的下划线
            if (position == list.size() - 1) {
                viewHolder.view4.setVisibility(View.INVISIBLE);
            } else {
                viewHolder.view4.setVisibility(View.VISIBLE);
            }
            return convertView;
        }

        class TimeLineHolder {
            private TextView title, time;
            private View view0, view4;
            private ImageView image;
        }
    }
}
