package com.bawei.myapplication.widget;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bawei.myapplication.R;

/**
 * author : Eaves
 * desc   : 流式布局
 * date   : 2019/11/27
 */
public class MyFloatView extends ViewGroup {

    private Context mContext;
    public MyFloatView(Context context) {
        super(context);
        mContext = context;
    }

    public MyFloatView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        int space = 20;
        int left = 0;
        int top = space;
        int bottom = 0;
        int right = 0;

        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {

            View childAt = getChildAt(i);
            childAt.measure(0,0);
            int height = childAt.getMeasuredHeight();
            int width = childAt.getMeasuredWidth();

            Log.e("MyMessage", "onLayout: 高"+height+"   宽"+width);

            left = space + right;
            right = left + width;
            bottom = top + height;

            if (right > getMeasuredWidth()){
                top = bottom + space;
                bottom = top + height;
                left = space;
                right = left + width;
            }

            childAt.layout(left,top,right,bottom);
        }


    }

    public void addTag(String tagName){

        TextView textView = new TextView(mContext);
        textView.setText(tagName);
        textView.setTextSize(18);
        textView.setBackgroundResource(R.drawable.my_tag_shape);
        textView.setTextColor(Color.BLACK);

        addView(textView,0);

    }

}
