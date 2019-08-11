package com.application.tchapj.utils2.nineGridLayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.OnePlusNLayoutHelper;
import com.application.tchapj.R;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by hades on 17/10/21.
 */
public class NineGridLayout extends LinearLayout {

    private RecyclerView mRecyclerView;
    private Context mContext;
    private VirtualLayoutManager layoutManager;
    private List<LayoutHelper> helpers;
    private ItemTouchHelper itemTouchHelper;
    private GridLayoutHelper gridLayoutHelper;
    private OnePlusNLayoutHelper onePlusHelper;
    private ImageMulitVAdapter mMulitVAdapter;
    private int itemMargin = R.dimen.item_margin2;

    public NineGridLayout(Context context) {
        this(context, null);
    }

    public NineGridLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NineGridLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        initView(context);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.NineGridLayout);
        final int N = typedArray.getIndexCount();
        for (int i = 0; i < N; i++) {
            initAttr(typedArray.getIndex(i), typedArray);
        }
        typedArray.recycle();
        mMulitVAdapter = new ImageMulitVAdapter(layoutManager, mContext, itemMargin);
    }


    private void initAttr(int attr, TypedArray typedArray) {
        //间隔
        if (attr == R.styleable.NineGridLayout_itemMargin) {
            itemMargin = (int) typedArray.getDimension(attr, 5);
        }

    }


    private void initView(Context context) {
        mContext = context;
        View view = LayoutInflater.from(mContext).inflate(R.layout.nine_images_layout, this);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_images);
        layoutManager = new VirtualLayoutManager(mContext);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setNestedScrollingEnabled(false);
    }


    public void setItemDelegate(ItemDelegate itemDelegate) {
        mMulitVAdapter.setItemDelegate(itemDelegate);
    }


    //绑定数据，根据数据，先行计算recyclerview高度，固定高度，防止多重滑动时候冲突
    public void bindData(List<String> pictures) {
        if (pictures != null) {
            helpers = new LinkedList<>();
            gridLayoutHelper = new GridLayoutHelper(6);
            gridLayoutHelper.setGap(itemMargin);
            gridLayoutHelper.setHGap(itemMargin);
            onePlusHelper = new OnePlusNLayoutHelper(3);

            final int num = pictures.size();
            ViewGroup.LayoutParams layoutParams = mRecyclerView.getLayoutParams();
            int displayW = DisplayUtils.getDisplayWidth(mContext);

            int realWidth = displayW - DisplayUtils.dip2px(mContext, 20);

            layoutParams.width = realWidth;
            int height;

            if (num == 1) {
                height = layoutParams.width;
            } else if (num == 2) {
                height = (int) (realWidth * 0.5);
            } else if (num == 3) {
                height = (int) (realWidth * 0.66) - itemMargin - itemMargin / 2;
            } else if (num == 4) {
                height = (int) (realWidth * 0.5) + itemMargin + (int) (realWidth * 0.33);
            } else if (num == 5) {
                height = (int) (realWidth * 0.5) + itemMargin + (int) (realWidth * 0.33);
            } else if (num == 6) {
                height = (int) (realWidth * 0.66) + (int) (realWidth * 0.33) - itemMargin / 2;
            } else if (num == 7 || num == 8) {
                height = (int) (realWidth * 0.5) + 2 * itemMargin + (int) (realWidth * 0.33) * 2;
            } else {
                height = (int) ((realWidth * 0.33) * 3 + 3 * itemMargin - itemMargin / 2);
            }
            layoutParams.height = height;
            mRecyclerView.setLayoutParams(layoutParams);
            //根据数量和位置 设置span占比
            gridLayoutHelper.setSpanSizeLookup(new GridLayoutHelper.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if (num == 1) {
                        return 6;
                    } else if (num == 2) {
                        return 3;
                    } else if (num == 3) {
                        return 2;
                    } else if (num == 4) {
                        if (position == 0) {
                            return 6;
                        } else {
                            return 2;
                        }
                    } else if (num == 5) {
                        if (position == 0 || position == 1) {
                            return 3;
                        } else {
                            return 2;
                        }
                    } else if (num == 6) {
                        return 2;
                    } else if (num == 7) {
                        if (position == 0) {
                            return 6;
                        } else {
                            return 2;
                        }
                    } else if (num == 8) {
                        if (position == 0 || position == 1) {
                            return 3;
                        } else {
                            return 2;
                        }
                    } else {
                        return 2;
                    }
                }
            });
            helpers.clear();
            if (num == 3) {
                helpers.add(onePlusHelper);
                gridLayoutHelper.setItemCount(0);
            } else {
                if (num == 6) {
                    helpers.add(onePlusHelper);
                    gridLayoutHelper.setItemCount(3);
                } else {
                    gridLayoutHelper.setItemCount(num);
                }
                helpers.add(gridLayoutHelper);
            }
            layoutManager.setLayoutHelpers(helpers);
            mMulitVAdapter.bindData(pictures);
            mRecyclerView.setAdapter(mMulitVAdapter);

        }
    }



    public interface ItemDelegate {
        void onItemClick(int position);
    }

}
