package com.application.tchapj.widiget;


import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.application.tchapj.App;
import com.application.tchapj.R;
import com.application.tchapj.consultation.bean.CommentlogsBean;
import com.application.tchapj.consultation.bean.ConsultationNewsModel;

import java.util.ArrayList;
import java.util.List;

// 自定义评论view
public class CommentListView extends LinearLayout {
    private List<CommentlogsBean> mDatas;
    private int itemColor;
    private int itemSelectorColor;
    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;
    private LayoutInflater layoutInflater;

    public void setDatas(List<CommentlogsBean> datas) {
        if (datas == null) {
            datas = new ArrayList<CommentlogsBean>();
        }
        mDatas = datas;
        notifyDataSetChanged();
    }

    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public OnItemLongClickListener getOnItemLongClickListener() {
        return onItemLongClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    public List<CommentlogsBean> getDatas() {
        return mDatas;
    }

    public CommentListView(Context context) {
        super(context);
    }

    public CommentListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(attrs);
    }

    public CommentListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(attrs);
    }


    protected void initAttrs(AttributeSet attrs) {
        TypedArray typedArray = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.PraiseListView, 0, 0);
        try {
            //textview的默认颜色
            itemColor = typedArray.getColor(R.styleable.PraiseListView_item_color, getResources().getColor(R.color.praise_item_default));
            itemSelectorColor = typedArray.getColor(R.styleable.PraiseListView_item_selector_color, getResources().getColor(R.color.praise_item_selector_default));

        } finally {
            typedArray.recycle();
        }
    }

    public void notifyDataSetChanged() {
        removeAllViews();
        if (mDatas == null || mDatas.size() == 0) {
            return;
        }
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        if(mDatas.size()<=8){
            for (int i = 0; i < mDatas.size(); i++) {
                final int index = i;
                View view = getView(index);
                if (view == null) {
                    throw new NullPointerException("listview item layout is null, please check getView()...");
                }
                addView(view, index, layoutParams);
            }
        }else {
            for (int i = 0; i < 8; i++) {
                final int index = i;
                View view = getView(index);
                if (view == null) {
                    throw new NullPointerException("listview item layout is null, please check getView()...");
                }
                addView(view, index, layoutParams);
            }
        }

        /*for (int i = 0; i < mDatas.size(); i++) {
            final int index = i;
            View view = getView(index);
            if (view == null) {
                throw new NullPointerException("listview item layout is null, please check getView()...");
            }
            addView(view, index, layoutParams);
        }*/
    }

    private View getView(final int position) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(getContext());
        }
        View convertView = layoutInflater.inflate(R.layout.comment_item_layout, null, false);
        TextView commentTv = (TextView) convertView.findViewById(R.id.comment_sender);
        final CircleMovementMethod circleMovementMethod = new CircleMovementMethod(itemSelectorColor, itemSelectorColor);

        final CommentlogsBean bean = mDatas.get(position);

        String name ;
        if(bean.getMemberId()==null){
            name = "";  // 回复的人
        }else {
            name = bean.getMemberId();  // 回复的人
        }

        String id = App.pinglunID; // 用户ID
        String toReplyName = ""; // 被回复的人


        /*final CommentsBean bean = mDatas.get(position);
        String name = bean.getSender().getUsername();  // 用户名
        String id = String.valueOf(bean.getSender().getId());// 用户ID

        String toReplyName = "";
        if (bean.getToReplyUser() != null) {
            toReplyName = bean.getToReplyUser().getUsername();
        }*/

        Log.e("11111111111", "Response:" + "用户名"+name+"用户ID"+id+"真实姓名"+toReplyName);

        SpannableStringBuilder builder = new SpannableStringBuilder();
        builder.append(setClickableSpan(name + "：", id));

        if (!TextUtils.isEmpty(toReplyName)) {
            builder.append(" 回复 ");
            //builder.append(setClickableSpan(toReplyName, bean.getToReplyUser().getId()));
            builder.append(setClickableSpan(toReplyName, id));
        }
        //转换表情字符
        String contentBodyStr = bean.getContent();
        builder.append(UrlUtils.formatUrlString(contentBodyStr));
        commentTv.setText(builder);

        commentTv.setMovementMethod(circleMovementMethod);
        commentTv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (circleMovementMethod.isPassToTv()) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(position);
                    }
                }
            }
        });
        commentTv.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (circleMovementMethod.isPassToTv()) {
                    if (onItemLongClickListener != null) {
                        onItemLongClickListener.onItemLongClick(position);
                    }
                    return true;
                }
                return false;
            }
        });

        return convertView;
    }

    @NonNull
    private SpannableString setClickableSpan(final String textStr, final String id) {
        SpannableString subjectSpanText = new SpannableString(textStr);
        subjectSpanText.setSpan(new SpannableClickable(itemColor) {
                                    @Override
                                    public void onClick(View widget) {
//                                        Toast.makeText(App.getContext(), textStr + " &id = " + id, Toast.LENGTH_SHORT).show();
                                        Toast.makeText(App.getContext(), textStr, Toast.LENGTH_SHORT).show();
                                    }
                                }, 0, subjectSpanText.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return subjectSpanText;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(int position);
    }
}
