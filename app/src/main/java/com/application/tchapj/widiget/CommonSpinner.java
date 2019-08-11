package com.application.tchapj.widiget;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.application.tchapj.R;
import com.application.tchapj.bean.CheckMainBean;

import java.util.List;

// 自定义CommonSpinner的实现类
// 自定义Spinner实体类不同
public class CommonSpinner<T> extends TextView implements View.OnClickListener {
    private TextView mTvTopView;
    private Context mContext;
    private SpinnerPopupWindow mPopupWindow;
    private List<T> mDatas;
    private OnSpinnerItemSelectListener mOnSpinnerItemSelectListener;

    public CommonSpinner(Context context) {
        this(context, null);
    }

    public CommonSpinner(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CommonSpinner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mTvTopView = this;
        mContext = context;
        mPopupWindow = new SpinnerPopupWindow(mContext);
        setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        mPopupWindow.showPopupWindow(mTvTopView);
    }

    public void setData(List<T> datas) {
        this.mDatas = datas;
    }



    class SpinnerPopupWindow extends PopupWindow {
        private Context mContext;
        private ListView mListView;
        private MyAdapter mMyAdapter;

        public SpinnerPopupWindow(Context context) {
            init(context);
        }

        private void init(Context context) {
            mContext = context;
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.layout_spinner, null);
            mListView = (ListView) view.findViewById(R.id.listview);
            mListView.setItemsCanFocus(true);
            mMyAdapter = new MyAdapter();
            mListView.setAdapter(mMyAdapter);
            this.setContentView(view);
            //设置宽高
            this.setWidth(mListView.getLayoutParams().width);
            this.setHeight(mListView.getLayoutParams().height);
            //让popupwindow获取焦点
            this.setFocusable(true);
            this.setOutsideTouchable(true);
            this.update();

            // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
            ColorDrawable dw = new ColorDrawable(00000000);
            this.setBackgroundDrawable(dw);
            //设置动画
            this.setAnimationStyle(R.style.SpinnerPopupWindowAnim);

        }

        class MyAdapter extends BaseAdapter {

            @Override
            public int getCount() {
                return mDatas == null ? 0 : mDatas.size();
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = View.inflate(mContext, R.layout.item_spinner, null);
                }
                Button bt = ViewHolder.get(convertView,R.id.bt_item);
                final T bean = mDatas.get(position);
                if(bean instanceof CheckMainBean.CheckMainData.AemesterList){
                    final CheckMainBean.CheckMainData.AemesterList beanT = (CheckMainBean.CheckMainData.AemesterList) bean;
                    bt.setText(beanT.getSemester());
                    bt.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            showPopupWindow(mTvTopView);

                            mTvTopView.setText(beanT.getSemester());
                            if (null != mOnSpinnerItemSelectListener) {
                                mOnSpinnerItemSelectListener.onItemSelectListener(position);
                            }
                        }
                    });
                }else{
                    bt.setText(bean.toString());
                    bt.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            showPopupWindow(mTvTopView);
                            mTvTopView.setText(bean.toString());
                            if (null != mOnSpinnerItemSelectListener) {
                                mOnSpinnerItemSelectListener.onItemSelectListener(position);
                            }
                        }
                    });
                }
                return convertView;
            }
        }

        public void showPopupWindow(View parent) {
            if (!this.isShowing()) {
                this.showAsDropDown(parent, -(this.getWidth() -parent.getWidth())/2, 0);
            } else {
                this.dismiss();
            }
        }

    }

    public void setOnSpinnerItemSelectListener(OnSpinnerItemSelectListener listener) {
        this.mOnSpinnerItemSelectListener = listener;
    }


    public interface OnSpinnerItemSelectListener {
        void onItemSelectListener(int pos);
    }

}
