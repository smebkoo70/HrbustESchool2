package com.example.hrbusteschool.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hrbusteschool.R;


//本adapter的作用是被新闻模块调用
public class MyNewsListAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater layoutInflater;

    public MyNewsListAdapter(Context context){
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }



    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    static  class ViewHolder {
        public ImageView imageView;
        public TextView tvTitle,tvTime,tvContext;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            convertView = layoutInflater.inflate(R.layout.layout_list_item,null);
            holder = new ViewHolder();
            holder.imageView = convertView.findViewById(R.id.image_iv);
            holder.tvTitle = convertView.findViewById(R.id.tv_title);
            holder.tvTime = convertView.findViewById(R.id.tv_time);
            holder.tvContext = convertView.findViewById(R.id.tv_context);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        //给控件赋值
        holder.tvTitle.setText("标题");
        holder.tvTime.setText("1942-07-15");
        holder.tvContext.setText("context");
        //Glide.with(context).load("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3769856076,312081180&fm=26&gp=0.jpg").into(holder.imageView);
        return convertView;
    }
}
