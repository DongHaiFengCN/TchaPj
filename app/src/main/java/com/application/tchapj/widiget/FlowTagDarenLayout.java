package com.application.tchapj.widiget;

import android.content.Context;
import android.content.DialogInterface;
import android.database.DataSetObserver;
import android.support.v7.app.AlertDialog;
import android.util.AttributeSet;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;

import com.application.tchapj.R;
import com.application.tchapj.utils.ToastUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 流式标签布局
public class FlowTagDarenLayout extends ViewGroup {
    /**
     * FlowLayout not support checked
     */
    public static final int FLOW_TAG_CHECKED_NONE = 0;
    /**
     * FlowLayout support single-select
     */
    public static final int FLOW_TAG_CHECKED_SINGLE = 1;
    /**
     * FlowLayout support multi-select
     */
    public static final int FLOW_TAG_CHECKED_MULTI = 2;

    /**
     * Should be used by subclasses to listen to changes in the dataset
     */
    AdapterDataSetObserver mDataSetObserver;

    /**
     * The adapter containing the data to be displayed by this view
     */
    ListAdapter mAdapter;

    /**
     * the tag click event callback
     */
    FlowTagDarenLayout.OnTagClickListener mOnTagClickListener;

    /**
     * the tag select event callback
     */
    FlowTagDarenLayout.OnTagSelectListener mOnTagSelectListener;

    /**
     * 标签流式布局选中模式，默认是不支持选中的
     */
    private int mTagCheckMode = FLOW_TAG_CHECKED_NONE;

    /**
     * 存储选中的tag
     */
    private SparseBooleanArray mCheckedTagArray = new SparseBooleanArray();
    /**
     * 子View的宽度，如果为0 则为warp_content
     */
    private int mWidth;

    public Context context;

    private int[] unSelectResId = {R.drawable.icon_talent_baby_mother_normal, R.drawable.icon_talent_artist_normal, R.drawable.icon_talent_internet_celebrity_normal
            ,R.drawable.icon_talent_student_normal, R.drawable.icon_talent_reporter_normal, R.drawable.icon_talent_skill_normal, R.drawable.icon_talent_elderly_normal
            ,R.drawable.icon_talent_wechat_business_normal, R.drawable.icon_talent_other_normal};
    private int[] selectResId = {R.drawable.icon_talent_baby_mother, R.drawable.icon_talent_artist, R.drawable.icon_talent_internet_celebrity
            ,R.drawable.icon_talent_student, R.drawable.icon_talent_reporter, R.drawable.icon_talent_skill, R.drawable.icon_talent_elderly
            ,R.drawable.icon_talent_wechat_business, R.drawable.icon_talent_other};


    public FlowTagDarenLayout(Context context) {
        super(context);
        this.context = context;

    }

    public FlowTagDarenLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

    }

    public FlowTagDarenLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        //获取Padding
        // 获得它的父容器为它设置的测量模式和大小
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);

        //FlowLayout最终的宽度和高度值
        int resultWidth = 0;
        int resultHeight = 0;

        //测量时每一行的宽度
        int lineWidth = 0;
        //测量时每一行的高度，加起来就是FlowLayout的高度
        int lineHeight = 0;

        //遍历每个子元素
        for (int i = 0, childCount = getChildCount(); i < childCount; i++) {
            View childView = getChildAt(i);
            //测量每一个子view的宽和高
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);

            //获取到测量的宽和高
            int childWidth = childView.getMeasuredWidth();
            int childHeight = childView.getMeasuredHeight();

            //因为子View可能设置margin，这里要加上margin的距离
            MarginLayoutParams mlp = (MarginLayoutParams) childView.getLayoutParams();
            int realChildWidth = childWidth + mlp.leftMargin + mlp.rightMargin;
            int realChildHeight = childHeight + mlp.topMargin + mlp.bottomMargin;

            //如果当前一行的宽度加上要加入的子view的宽度大于父容器给的宽度，就换行
            if ((lineWidth + realChildWidth) > sizeWidth) {
                //换行
                resultWidth = Math.max(lineWidth, realChildWidth);
                resultHeight += realChildHeight;
                //换行了，lineWidth和lineHeight重新算
                lineWidth = realChildWidth;
                lineHeight = realChildHeight;
            } else {
                //不换行，直接相加
                lineWidth += realChildWidth;
                //每一行的高度取二者最大值
                lineHeight = Math.max(lineHeight, realChildHeight);
            }

            //遍历到最后一个的时候，肯定走的是不换行
            if (i == childCount - 1) {
                resultWidth = Math.max(lineWidth, resultWidth);
                resultHeight += lineHeight;
            }

            setMeasuredDimension(modeWidth == MeasureSpec.EXACTLY ? sizeWidth : resultWidth,
                    modeHeight == MeasureSpec.EXACTLY ? sizeHeight : resultHeight);

        }

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        int flowWidth = getWidth();

        int childLeft = 0;
        int childTop = 0;

        //遍历子控件，记录每个子view的位置
        for (int i = 0, childCount = getChildCount(); i < childCount; i++) {
            View childView = getChildAt(i);

            //跳过View.GONE的子View
            if (childView.getVisibility() == View.GONE) {
                continue;
            }

            //获取到测量的宽和高
            int childWidth = childView.getMeasuredWidth();
            int childHeight = childView.getMeasuredHeight();

            //因为子View可能设置margin，这里要加上margin的距离
            MarginLayoutParams mlp = (MarginLayoutParams) childView.getLayoutParams();

            if (childLeft + mlp.leftMargin + childWidth + mlp.rightMargin > flowWidth) {
                //换行处理
                childTop += (mlp.topMargin + childHeight + mlp.bottomMargin);
                childLeft = 0;
            }
            //布局
            int left = childLeft + mlp.leftMargin;
            int top = childTop + mlp.topMargin;
            int right = childLeft + mlp.leftMargin + childWidth;
            int bottom = childTop + mlp.topMargin + childHeight;
            childView.layout(left, top, right, bottom);

            childLeft += (mlp.leftMargin + childWidth + mlp.rightMargin);
        }
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    public ListAdapter getAdapter() {
        return mAdapter;
    }

    class AdapterDataSetObserver extends DataSetObserver {
        @Override
        public void onChanged() {
            super.onChanged();
            reloadData();
        }

        @Override
        public void onInvalidated() {
            super.onInvalidated();
        }
    }


    /**
     * 子View个数
     *
     * @param width
     */
    public void setChildWidth(int width) {
        this.mWidth = width;
    }

    /**
     * 重新加载刷新数据
     */
    private void reloadData() {
        removeAllViews();

        MarginLayoutParams mMarginLayoutParams = new MarginLayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        if (mWidth != 0) {
            mMarginLayoutParams.width = mWidth;
        }
        boolean isSetted = false;
        for (int i = 0; i < mAdapter.getCount(); i++) {
            final int j = i;
            mCheckedTagArray.put(i, false);
            final View childView = mAdapter.getView(i, null, this);
            addView(childView, mMarginLayoutParams);

            if (mAdapter instanceof OnInitSelectedPosition) {
                boolean isSelected = ((OnInitSelectedPosition) mAdapter).isSelectedPosition(i);
                //判断一下模式
                if (mTagCheckMode == FLOW_TAG_CHECKED_SINGLE) {
                    //单选只有第一个起作用
                    if (isSelected && !isSetted) {
                        mCheckedTagArray.put(i, true);
                        childView.setSelected(true);
                        ((ImageView)childView).setImageResource(selectResId[i]);
                        isSetted = true;
                    }
                } else if (mTagCheckMode == FLOW_TAG_CHECKED_MULTI) {
                    if (isSelected) {
                        mCheckedTagArray.put(i, true);
                        if (mCheckedTagArray.size() <= 3) {
                            childView.setSelected(true);
                            ((ImageView)childView).setImageResource(selectResId[i]);
                        }
                    }else{
                        mCheckedTagArray.delete(i);
                        childView.setSelected(false);
                        ((ImageView)childView).setImageResource(unSelectResId[i]);
                    }
                }
            }

            final int finalI = i;
            childView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mTagCheckMode == FLOW_TAG_CHECKED_NONE) {
                        if (mOnTagClickListener != null) {
                            mOnTagClickListener.onItemClick(FlowTagDarenLayout.this, childView, j);
                        }
                    } else if (mTagCheckMode == FLOW_TAG_CHECKED_SINGLE) {
                        //判断状态
                        if (mCheckedTagArray.get(j)) {
                            //单选模式下，必须选择一个
//                            mCheckedTagArray.put(j, false);
//                            childView.setSelected(false);
//                            if (mOnTagSelectListener != null) {
//                                mOnTagSelectListener.onItemSelect(FlowTagLayout.this, j,new ArrayList<Integer>());
//                            }
                            return;
                        }
                        //更新全部状态为fasle

                        for (int k = 0; k < mAdapter.getCount(); k++) {
                            mCheckedTagArray.put(k, false);
                            getChildAt(k).setSelected(false);
                            ((ImageView)childView).setImageResource(unSelectResId[finalI]);
                        }
                        //更新点击状态
                        mCheckedTagArray.put(j, true);
                        childView.setSelected(true);
                        ((ImageView)childView).setImageResource(selectResId[finalI]);

                        if (mOnTagSelectListener != null) {
                            mOnTagSelectListener.onItemSelect(FlowTagDarenLayout.this, j, Arrays.asList(j));
                        }
                    } else if (mTagCheckMode == FLOW_TAG_CHECKED_MULTI) {



                        if (mCheckedTagArray.get(j)) {
                            mCheckedTagArray.delete(j);
                            childView.setSelected(false);
                            ((ImageView)childView).setImageResource(unSelectResId[finalI]);
                        } else {
                            if(mCheckedTagArray.size() >= 3){
                                ToastUtil.show(context, "最多选择3个");
                                return;
                            }else{
                                mCheckedTagArray.put(j, true);
                                childView.setSelected(true);
                                ((ImageView)childView).setImageResource(selectResId[finalI]);

                            }
                        }

                        List<Integer> list = new ArrayList<>();
                        for (int k = 0; k < mAdapter.getCount(); k++) {
                            if (mCheckedTagArray.get(k)) {
                                list.add(k);
                            }
                        }
                        if (mOnTagSelectListener != null) {
                            mOnTagSelectListener.onItemSelect(FlowTagDarenLayout.this, j, list);
                        }

                    }
                }
            });
        }
    }

    public void setOnTagClickListener(OnTagClickListener onTagClickListener) {
        this.mOnTagClickListener = onTagClickListener;
    }

    public void setOnTagSelectListener(OnTagSelectListener onTagSelectListener) {
        this.mOnTagSelectListener = onTagSelectListener;
    }

    /**
     * 像ListView、GridView一样使用FlowLayout
     *
     * @param adapter
     */
    public void setAdapter(ListAdapter adapter) {
        if (mAdapter != null && mDataSetObserver != null) {
            mAdapter.unregisterDataSetObserver(mDataSetObserver);
        }

        //清除现有的数据
        removeAllViews();
        mAdapter = adapter;

        if (mAdapter != null) {
            mDataSetObserver = new AdapterDataSetObserver();
            mAdapter.registerDataSetObserver(mDataSetObserver);
        }
    }

    /**
     * 获取标签模式
     *
     * @return
     */
    public int getmTagCheckMode() {
        return mTagCheckMode;
    }

    /**
     * 设置标签选中模式
     *
     * @param tagMode
     */
    public void setTagCheckedMode(int tagMode) {
        this.mTagCheckMode = tagMode;
    }

    public interface OnTagSelectListener {
        void onItemSelect(FlowTagDarenLayout parent, int positoin, List<Integer> selectedList);
    }

    public interface OnTagClickListener {
        void onItemClick(FlowTagDarenLayout parent, View view, int position);
    }
}