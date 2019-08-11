package com.application.tchapj.widiget;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Administrator on 2018/4/13.
 */

public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

    private int spanCount;
    //    private int     spacing;
    private boolean includeEdge;
    private int horiSpacing;
    private int vertiSpacing;

    public GridSpacingItemDecoration(int spanCount, int horiSpacing, int vertiSpacing, boolean includeEdge) {
        this.spanCount = spanCount;
//        this.spacing = spacing;
        this.horiSpacing = horiSpacing;
        this.vertiSpacing = vertiSpacing;
        this.includeEdge = includeEdge;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view); // item position
        int column = position % spanCount; // item column

        if (includeEdge) {
            outRect.left = horiSpacing - column * horiSpacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
            outRect.right = (column + 1) * horiSpacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

            if (position < spanCount) { // top edge
                outRect.top = vertiSpacing;
            }
            outRect.bottom = vertiSpacing; // item bottom
        } else {
            outRect.left = column * horiSpacing / spanCount; // column * ((1f / spanCount) * spacing)
            outRect.right = horiSpacing - (column + 1) * horiSpacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
            if (position >= spanCount) {
                outRect.top = vertiSpacing; // item top
            }
        }
    }
}
