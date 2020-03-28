package com.example.hrbusteschool.View;


import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.hrbusteschool.R;

public class MyTextView extends View {
    private  Context con;
    private int inputview_input_icon;
    private String inputview_input_hint;
    private boolean inputview_is_pass;
    private static final String TAG = MyTextView.class.getSimpleName();

    //在View的构造方法中通过TypedArray获取
    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //TypedArray ta = context.obtainStyledAttributes(attrs,R.styleable.)
        //TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.test);
        //String text = ta.getString(R.styleable.test_testAttr);
        //int textAttr = ta.getInteger(R.styleable.test_text, -1);
        //Log.e(TAG, "text = " + text + " , textAttr = " + textAttr);
        //ta.recycle();
    }
}