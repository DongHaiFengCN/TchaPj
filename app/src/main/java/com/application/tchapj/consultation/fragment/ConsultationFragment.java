package com.application.tchapj.consultation.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.application.tchapj.App;
import com.application.tchapj.Constants;
import com.application.tchapj.R;
import com.application.tchapj.WebViewActivity;
import com.application.tchapj.base.BaseBean;
import com.application.tchapj.base.BaseMvpFragment;
import com.application.tchapj.bean.UserInfo;
import com.application.tchapj.consultation.activity.ChannelActivity;
import com.application.tchapj.consultation.activity.UploadVideoActivity;
import com.application.tchapj.consultation.bean.ConsultationTopModel;
import com.application.tchapj.consultation.bean.IsAuthorData;
import com.application.tchapj.consultation.bean.UpdateAuthorData;
import com.application.tchapj.consultation.presenter.ConsultationTobPresenter;
import com.application.tchapj.consultation.view.IConsultationTobView;
import com.application.tchapj.my.activity.IdentityActivity;
import com.application.tchapj.my.bean.UserModel;
import com.application.tchapj.rxbus.ChangeAnswerEvent;
import com.application.tchapj.rxbus.RxBus;
import com.application.tchapj.utils.CommonDialogUtil;
import com.application.tchapj.utils.CommonUtils;
import com.application.tchapj.utils.ToastUtil;
import com.application.tchapj.utils2.qiniu.utils.StringUtils;
import com.application.tchapj.utils2.share.SharedPreferencesUtils;
import com.application.tchapj.widiget.DensityUtil;
import com.iflytek.cloud.thirdparty.C;
import com.iflytek.cloud.thirdparty.P;
import com.king.base.adapter.ViewPagerFragmentAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import freemarker.template.utility.StringUtil;
import rx.Subscription;
import rx.functions.Action1;

import static com.application.tchapj.DataManager.getDataManager;


// 咨询页Fragment
public class ConsultationFragment extends BaseMvpFragment<IConsultationTobView, ConsultationTobPresenter> implements IConsultationTobView, View.OnClickListener {


    @BindView(R.id.iv_operation)
    ImageView iv_operation;
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.viewpager)
    ViewPager viewpager;

    Subscription mSubscription;
    Unbinder unbinder;

    private ViewPagerFragmentAdapter viewPagerFragmentAdapter;

    private List<CharSequence> listTitle; // 标题
    private List<CharSequence> listTitleId; // 标题

    List<CharSequence> listTemp;
    List<CharSequence> listTempId;

    private List<CharSequence> listTitle2; // 标题

    private List<Fragment> fragments;  // Fragment

    private ConsultationInfoFragment categoryFragment;

    private List<ConsultationTopModel.PersonSelectModelResult.NewStypeSelect> newStypeSelects = new ArrayList<>();

    private Context mContext;
    private Dialog publishContentDialog;//发布图文...
    private String uploadClickType = "";//底部Dialog。发布图文、视频...

    // 接收参数
    public static ConsultationFragment newInstance(String param) {
        Bundle args = new Bundle();
        args.putString("args", param);
        ConsultationFragment fragment = new ConsultationFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_consultation;
    }

    @Override
    public void initUI() {

        mContext = getActivity();

        listTitle = new ArrayList<>();
        listTitle2 = new ArrayList<>();
        listTemp = new ArrayList<>();
        listTempId = new ArrayList<>();
        fragments = new ArrayList<>();

        categoryFragment = new ConsultationInfoFragment();

        iv_operation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ChannelActivity.class);
                startActivity(intent);
            }
        });

        // 添加的
        mSubscription = RxBus.getDefault().toObserverable(ChangeAnswerEvent.class)
                .subscribe(new Action1<ChangeAnswerEvent>() {
                    @Override
                    public void call(ChangeAnswerEvent changeAnswerEvent) {

                        if (changeAnswerEvent != null && changeAnswerEvent.getTitles() != null && changeAnswerEvent.getTitles().size() != 0) {

                            listTemp.clear();
                            listTempId.clear();
                            fragments.clear();
                            listTemp.addAll(changeAnswerEvent.getTitles());
                            listTempId.addAll(changeAnswerEvent.getTitlesId());
                            for (int i = 0; i < listTemp.size(); i++) {
                                fragments.add(categoryFragment.newInstance((String) listTempId.get(i), ConsultationInfoFragment.FROM_TYPE.CONSULTATION_FRAGMENT.ordinal()));
                            }
                            toSetList(listTitle, listTemp, false);
                            toSetList(listTitleId, listTempId, false);

                            if (viewPagerFragmentAdapter != null) {
                                viewPagerFragmentAdapter.setListTitle(listTitle);
                                viewPagerFragmentAdapter.setListData(fragments);
                                viewPagerFragmentAdapter.notifyDataSetChanged();
                            }

                            viewPagerFragmentAdapter = new ViewPagerFragmentAdapter(getChildFragmentManager(), fragments, listTitle);

                            viewpager.setAdapter(viewPagerFragmentAdapter);

                            tabs.setupWithViewPager(viewpager);
                        }


                    }

                });

    }


    @Override
    public void initData() {
        getPresenter().getConsultationTobResult();
    }

    @Override
    public ConsultationTobPresenter createPresenter() {

        return new ConsultationTobPresenter(getApp());
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

    private void showDialog() {
        publishContentDialog = new Dialog(mContext, R.style.DialogStyle);
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_dialog_consultation, null);
        publishContentDialog.setContentView(view);
//        LinearLayout graphicLl = view.findViewById(R.id.dialog_consultation_ll_graphic).setOnClickListener(this);
//        LinearLayout videoLl = view.findViewById(R.id.dialog_consultation_ll_video).setOnClickListener(this);
//        LinearLayout longVideoLl = view.findViewById(R.id.dialog_consultation_ll_long_video).setOnClickListener(this);
//        LinearLayout reprintGraphicLl = view.findViewById(R.id.dialog_consultation_ll_reprint_graphic).setOnClickListener(this);
        view.findViewById(R.id.dialog_consultation_ll_graphic).setOnClickListener(this);
        view.findViewById(R.id.dialog_consultation_ll_video).setOnClickListener(this);
        view.findViewById(R.id.dialog_consultation_ll_long_video).setOnClickListener(this);
        view.findViewById(R.id.dialog_consultation_ll_reprint_graphic).setOnClickListener(this);

        view.findViewById(R.id.dialog_consultation_fl_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                publishContentDialog.dismiss();
            }
        });

        Window dialogWindow = publishContentDialog.getWindow();
        WindowManager.LayoutParams layoutParams = dialogWindow.getAttributes();
        WindowManager wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);

        layoutParams.width = wm.getDefaultDisplay().getWidth();
        layoutParams.gravity = Gravity.BOTTOM;
        dialogWindow.setAttributes(layoutParams);
        publishContentDialog.show();

    }

    @OnClick(R.id.iv_edit_consultation)
    public void onViewClicked() {
        if (CommonUtils.isLogin(getActivity())) {

            if ("2".equals(getDataManager().quickGetMetaData(R.string.lingState, String.class))
                    || "2".equals(getDataManager().quickGetMetaData(R.string.mtState, String.class))
                    || "2".equals(getDataManager().quickGetMetaData(R.string.mrState, String.class))) {

                if ("1".equals(getDataManager().quickGetMetaData(R.string.isAuthor, String.class))) {
                    //已申请微呼百应号
                    showDialog();
                } else {

                    final Dialog dialog = new Dialog(context,R.style.DialogStyle);
                    View view = LayoutInflater.from(context).inflate(R.layout.layout_dialog_consultation_verify_identity, null);
                    dialog.setContentView(view);
                    final RadioButton radioButton = view.findViewById(R.id.dialog_consultation_verify_identity_rdo_btn);
                    final TextView confirmTv = view.findViewById(R.id.dialog_consultation_verify_identity_confirm_tv);
                    LinearLayout rdoLl = view.findViewById(R.id.dialog_consultation_verify_identity_rdo_ll);
                    TextView tipTv = view.findViewById(R.id.dialog_consultation_verify_identity_tip_tv);
                    rdoLl.setVisibility(View.VISIBLE);
                    tipTv.setVisibility(View.GONE);
                    confirmTv.setBackgroundResource(R.drawable.bg_dialog_consultation_verify_identity_confirm);
                    confirmTv.setText("确认开通");

                    view.findViewById(R.id.dialog_consultation_verify_identity_fl).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });

                    //协议
                    view.findViewById(R.id.dialog_consultation_verify_identity_agreement_tv).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(context, WebViewActivity.class);
                            intent.putExtra(WebViewActivity.URL_KEY, Constants.ACCOUNT_NUMBER_AGREEMENT);
                            intent.putExtra(WebViewActivity.TITLE, "");
                            intent.putExtra(WebViewActivity.URL_TYPE, "All");
                            context.startActivity(intent);

                        }
                    });

                    radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                            if(b){
                                confirmTv.setBackgroundResource(R.drawable.bg_dialog_consultation_verify_identity_confirm);
                            }else{
                                confirmTv.setBackgroundResource(R.drawable.bg_dialog_consultation_verify_identity_confirm_normal);
                            }
                        }
                    });

                    confirmTv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                            if(radioButton.isChecked()){
                                Intent intent = new Intent(context, WebViewActivity.class);
                                intent.putExtra(WebViewActivity.URL_KEY, Constants.ACCOUNT_NUMBER_AGREEMENT);
                                intent.putExtra(WebViewActivity.TITLE, "");
                                intent.putExtra(WebViewActivity.URL_TYPE, "All");
                                context.startActivity(intent);
                            }

                        }
                    });


                    Window dialogWindow = dialog.getWindow();
                    WindowManager.LayoutParams layoutParams = dialogWindow.getAttributes();
                    WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

                    layoutParams.width = wm.getDefaultDisplay().getWidth() - DensityUtil.dpTopx(context, 25);
                    dialogWindow.setAttributes(layoutParams);
                    dialog.show();


                }
            }else {

                //进行身份认证
                final Dialog dialog = new Dialog(context,R.style.DialogStyle);
                View view = LayoutInflater.from(context).inflate(R.layout.layout_dialog_consultation_verify_identity, null);
                dialog.setContentView(view);
                final TextView confirmTv = view.findViewById(R.id.dialog_consultation_verify_identity_confirm_tv);

                LinearLayout rdoLl = view.findViewById(R.id.dialog_consultation_verify_identity_rdo_ll);
                TextView tipTv = view.findViewById(R.id.dialog_consultation_verify_identity_tip_tv);
                rdoLl.setVisibility(View.GONE);
                tipTv.setVisibility(View.VISIBLE);
                confirmTv.setBackgroundResource(R.drawable.bg_dialog_consultation_verify_identity_confirm);
                confirmTv.setText("身份激活");

                view.findViewById(R.id.dialog_consultation_verify_identity_fl).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });


                confirmTv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();

                        IdentityActivity.start(getActivity(), SharedPreferencesUtils.getInstance().getUserInfo());

                    }
                });


                Window dialogWindow = dialog.getWindow();
                WindowManager.LayoutParams layoutParams = dialogWindow.getAttributes();
                WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

                layoutParams.width = wm.getDefaultDisplay().getWidth() - DensityUtil.dpTopx(context, 25);
                dialogWindow.setAttributes(layoutParams);
                dialog.show();


            }
        }




    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.dialog_consultation_ll_graphic:
                uploadClickType = "2";
                UploadVideoActivity.start(mContext, uploadClickType);
                publishContentDialog.dismiss();
                break;
            case R.id.dialog_consultation_ll_video:
                uploadClickType = "5";
                UploadVideoActivity.start(mContext, uploadClickType);
                publishContentDialog.dismiss();
                break;
            case R.id.dialog_consultation_ll_long_video:
                uploadClickType = "3";
                UploadVideoActivity.start(mContext, uploadClickType);
                publishContentDialog.dismiss();
                break;
            case R.id.dialog_consultation_ll_reprint_graphic:
                ToastUtil.show(getActivity(), "此功能尚未开通");
                break;
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    // 得到全部咨询数据 respon
    @Override
    public void onGetConsultationTobResult(ConsultationTopModel consultationTopModel) {


        if ("000".equals(consultationTopModel.getCode())) {

            newStypeSelects = consultationTopModel.getData().getNewstypeList();
            fragments.clear();
            listTemp.clear();
            listTempId.clear();

            listTemp.add("推荐");
            fragments.add(categoryFragment.newInstance("推荐", ConsultationInfoFragment.FROM_TYPE.CONSULTATION_FRAGMENT.ordinal()));
            listTemp.add("热点");
            fragments.add(categoryFragment.newInstance("99", ConsultationInfoFragment.FROM_TYPE.CONSULTATION_FRAGMENT.ordinal()));


            for (int i = 0; i < newStypeSelects.size(); i++) {
                listTemp.add(newStypeSelects.get(i).getName());
                fragments.add(categoryFragment.newInstance(newStypeSelects.get(i).getId(), ConsultationInfoFragment.FROM_TYPE.CONSULTATION_FRAGMENT.ordinal()));
            }

            toSetList(listTitle, listTemp, false);
            toSetList(listTitleId, listTempId, false);

        }

        if (viewPagerFragmentAdapter != null) {
            viewPagerFragmentAdapter.setListTitle(listTitle);
            viewPagerFragmentAdapter.setListData(fragments);
            viewPagerFragmentAdapter.notifyDataSetChanged();
        }

        viewPagerFragmentAdapter = new ViewPagerFragmentAdapter(getChildFragmentManager(), fragments, listTitle);

        viewpager.setAdapter(viewPagerFragmentAdapter);

        tabs.setupWithViewPager(viewpager);

    }


    //开通微呼百应账号
    @Override
    public void onUpdateAuthor(BaseBean baseBean) {
        if (baseBean != null && baseBean.getCode().equals("000")) {
            ToastUtil.show(getActivity(), "开通成功");
            getPresenter().getMemberInfo(App.getId());//更新本地用户信息
        }

    }

    //获取个人中心信息
    @Override
    public void onGetMemberInfo(final UserInfo userInfo) {
        SharedPreferencesUtils.getInstance().setUserInfo(userInfo);
    }


    public interface ConsultationFragmentOpenDialogClickListener {
        void agreementClick();//运营规范

        void confirmClick();
    }

    public interface ConsultationFragmentActivateDialogClickListener {
        void goActivateClick();
    }


}
