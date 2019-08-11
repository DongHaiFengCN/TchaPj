package com.application.tchapj.utils2.imagenice9layout;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.application.tchapj.R;
import com.application.tchapj.task.activity.ImagePreviewCopyActivity;
import com.application.tchapj.utils2.imagenice9layout.nice9.ImageNice9Layout;
import com.application.tchapj.utils2.imagepicker.ImagePicker;
import com.application.tchapj.utils2.imagepicker.bean.ImageItem;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by wxy on 2017/6/14.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private List<DemoEntity> mDemoEntities;
    private Context mContext;

    public ListAdapter(Context mContext, List<DemoEntity> demoEntities) {
        this.mContext = mContext;
        mDemoEntities = demoEntities;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_view, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.mImageNice9Layout.bindData(mDemoEntities.get(position).pictures);

        // 将资源路径设置到图片选择实体类
        final ArrayList<ImageItem> imageItems = new ArrayList<>();
        for (String url : mDemoEntities.get(position).pictures) {
            ImageItem imageItem = new ImageItem();
            imageItem.mimeType = "copy";
            imageItem.path = url;
            imageItems.add(imageItem);
            Log.e("135", "url = " + url);
        }

        holder.mImageNice9Layout.setItemDelegate(new ImageNice9Layout.ItemDelegate() {
            @Override
            public void onItemClick(int position) {

                // 预览图片
                Intent intentPreview = new Intent(mContext, ImagePreviewCopyActivity.class);
                intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, imageItems);
                intentPreview.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, position);
                intentPreview.putExtra(ImagePicker.EXTRA_FROM_ITEMS, true);
                // startActivityForResult(intentPreview, REQUEST_CODE_PREVIEW);
                mContext.startActivity(intentPreview);
                //Toast.makeText(mContext, "位置"+ position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDemoEntities.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        ImageNice9Layout mImageNice9Layout;

        public ViewHolder(View itemView) {
            super(itemView);
            mImageNice9Layout = (ImageNice9Layout) itemView.findViewById(R.id.item_nice9_image);
        }
    }
}
