package com.application.tchapj.consultation.activity;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Toast;

import com.application.tchapj.R;
import com.application.tchapj.base.BaseBean;
import com.application.tchapj.base.BaseMvpActivity;
import com.application.tchapj.bean.UserInfo;
import com.application.tchapj.consultation.bean.ConsultationTopModel;
import com.application.tchapj.consultation.bean.IsAuthorData;
import com.application.tchapj.consultation.bean.UpdateAuthorData;
import com.application.tchapj.consultation.presenter.ConsultationTobPresenter;
import com.application.tchapj.consultation.view.IConsultationTobView;
import com.application.tchapj.my.bean.UserModel;
import com.application.tchapj.rxbus.ChangeAnswerEvent;
import com.application.tchapj.rxbus.RxBus;
import com.application.tchapj.utils2.channeltagview.bean.ChannelItem;
import com.application.tchapj.utils2.channeltagview.listener.OnChannelItemClicklistener;
import com.application.tchapj.utils2.channeltagview.listener.UserActionListener;
import com.application.tchapj.utils2.channeltagview.view.ChannelTagView;
import com.application.tchapj.widiget.ToolbarHelper;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.badgeview.BGABadgeTextView;


public class ChannelActivity extends BaseMvpActivity<IConsultationTobView, ConsultationTobPresenter> implements IConsultationTobView {

    private ChannelTagView channelTagView;
    private List<String> titles = new ArrayList<>();
    private List<String> titlesId = new ArrayList<>();
    private ArrayList<ChannelItem> addedChannels = new ArrayList<>();
    private ArrayList<ChannelItem> unAddedChannels = new ArrayList<>();

    private List<ConsultationTopModel.PersonSelectModelResult.NewStypeSelect> newStypeSelects = new ArrayList<>();

    @Override
    protected void initToolbar(ToolbarHelper toolbarHelper) {

        toolbarHelper.setTitle("频道管理");

    }

    @Override
    public int getRootViewId() {
        return R.layout.activity_channel;
    }

    @Override
    public void initUI() {

        getPresenter().getConsultationTobResult();

        channelTagView = (ChannelTagView) findViewById(R.id.channel_tag_view);

    }

    @Override
    public void initData() {
        //getPresenter().getConsultationTobResult();
    }

    @NonNull
    @Override
    public ConsultationTobPresenter createPresenter() {
        return new ConsultationTobPresenter(getApp());
    }

    @Override
    public void onGetConsultationTobResult(ConsultationTopModel consultationTopModel) {

        if ("000".equals(consultationTopModel.getCode())) {

            newStypeSelects = consultationTopModel.getData().getNewstypeList();

            ChannelItem item = new ChannelItem();
            item.title = "推荐";
            addedChannels.add(item);
            ChannelItem itemHotSpot = new ChannelItem();
            itemHotSpot.title = "热点";
            addedChannels.add(itemHotSpot);


            for(int i=0;i<newStypeSelects.size();i++){

                ChannelItem item2 = new ChannelItem();
                item2.titleId = newStypeSelects.get(i).getId();
                item2.title = newStypeSelects.get(i).getName();
                addedChannels.add(item2);
                //unAddedChannels.add(item2);
            }

            /*String[] chanles = getResources().getStringArray(R.array.chanles);
            for (int i = 0; i < chanles.length/2; i++) {
                ChannelItem item = new ChannelItem();
                item.id = i;
                item.title = chanles[i];
                addedChannels.add(item);
            }
            for (int i = chanles.length/2; i < chanles.length; i++) {
                ChannelItem item = new ChannelItem();
                item.id = i;
                item.title = chanles[i];
                unAddedChannels.add(item);
            }*/

        }

        channelTagView.initChannels(addedChannels, unAddedChannels, new ChannelTagView.RedDotRemainderListener() {

            @Override
            public boolean showAddedChannelBadge(BGABadgeTextView itemView, int position) {
                if(addedChannels.get(position).title.equals("生活")){
                    return true;
                }else{
                    return false;
                }
            }

            @Override
            public boolean showUnAddedChannelBadge(BGABadgeTextView itemView, int position) {
                if(unAddedChannels.get(position).title.equals("数码")||unAddedChannels.get(position).title.equals("科技")){
                    return true;
                }else{
                    return false;
                }
            }

            @Override
            public void handleAddedChannelReddot(BGABadgeTextView itemView, int position) {
                itemView.showCirclePointBadge();
            }

            @Override
            public void handleUnAddedChannelReddot(BGABadgeTextView itemView, int position) {
                if(unAddedChannels.get(position).title.equals("科技")){
                    itemView.showTextBadge("new");
                }else{
                    itemView.showCirclePointBadge();
                }
            }

            @Override
            public void OnDragDismiss(BGABadgeTextView itemView, int position) {

            }

        });

//        channelTagView.setFixedChannelBg(R.drawable.fixed_item_bg);
//        channelTagView.setFixedChannel(0);
//        channelTagView.showPahtAnim(true);

        channelTagView.setOnChannelItemClicklistener(new OnChannelItemClicklistener() {

            @Override
            public void onAddedChannelItemClick(View itemView, int position) {
                //Toast.makeText(ChannelActivity.this,"打开-"+addedChannels.get(position).title, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onUnAddedChannelItemClick(View itemView, int position) {

                ChannelItem item = unAddedChannels.remove(position);
                addedChannels.add(item);
                Toast.makeText(ChannelActivity.this,"添加频道-"+item.title, Toast.LENGTH_SHORT).show();
                titles.clear();
                titlesId.clear();
                for(int i =0;i<addedChannels.size();i++){
                    titles.add(addedChannels.get(i).getTitle());
                    titlesId.add(addedChannels.get(i).getTitleId());
                }

            }
        });

        channelTagView.setUserActionListener(new UserActionListener() {
            @Override
            public void onMoved(int fromPos, int toPos, ArrayList<ChannelItem> checkedChannels) {
                Toast.makeText(ChannelActivity.this,"将-"+addedChannels.get(fromPos).title+" 换到 "+addedChannels.get(toPos).title, Toast.LENGTH_SHORT).show();
                addedChannels.clear();
                addedChannels.addAll(checkedChannels);
            }

            @Override // 删除
            public void onSwiped(int position, View itemView, ArrayList<ChannelItem> checkedChannels, ArrayList<ChannelItem> uncheckedChannels) {
                Toast.makeText(ChannelActivity.this,"删除-"+ChannelActivity.this.addedChannels.remove(position).title, Toast.LENGTH_SHORT).show();
                unAddedChannels.clear();
                unAddedChannels.addAll(uncheckedChannels);
                titles.clear();
                titlesId.clear();
                for(int i =0;i<addedChannels.size();i++){
                    titles.add(addedChannels.get(i).getTitle());
                    titlesId.add(addedChannels.get(i).getTitleId());
                }

            }
        });

    }


    @Override
    public void onUpdateAuthor(BaseBean baseBean) {

    }

    @Override
    public void onGetMemberInfo(UserInfo userInfo) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();


        // 消息机制
        ChangeAnswerEvent changeAnswerEvent = new ChangeAnswerEvent();
        changeAnswerEvent.setTitles(titles);
        changeAnswerEvent.setTitlesId(titlesId);
        RxBus.getDefault().post(changeAnswerEvent);
        titles.clear();
        titlesId.clear();
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

}
