package com.example.caigouapp.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * 重写网格View，设置高度
 * 可滚动的GridView，这里是因为这个GridView被包裹在一个ScrollView中才需要设置纵向拉伸
 */

public class ScrollGridView extends GridView {
    public ScrollGridView(Context context) {
        super(context);
    }

    public ScrollGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, height);
    }
}