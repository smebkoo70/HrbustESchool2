package com.example.hrbusteschool.Class;

import android.widget.TextView;
//listview的内部组件
public class ItemInfo {
    public TextView tvTitle,tvTime,tvContext;

    public TextView getTvTitle() {
        return tvTitle;
    }

    public void setTvTitle(TextView tvTitle) {
        this.tvTitle = tvTitle;
    }

    public TextView getTvTime() {
        return tvTime;
    }

    public void setTvTime(TextView tvTime) {
        this.tvTime = tvTime;
    }

    public TextView getTvContext() {
        return tvContext;
    }

    public void setTvContext(TextView tvContext) {
        this.tvContext = tvContext;
    }
}
