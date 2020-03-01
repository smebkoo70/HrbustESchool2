package com.example.hrbusteschool.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hrbusteschool.Class.ItemInfo;
import com.example.hrbusteschool.R;
import com.example.hrbusteschool.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import com.example.hrbusteschool.Class.ItemInfo;

//本adapter的作用是被论坛模块调用
public class MyListAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater mlayoutInflater;
    List<String> mtitle;
    private List<ItemInfo> mList;

    private MyClickListener mListener;//所有listview的item共用同一个
    //自定义接口，用于回调按钮点击事件到Activity
    public interface MyClickListener{
        public void clickListener(View v);
    }

    public MyListAdapter(Context context){
        this.context = context;
        mlayoutInflater = LayoutInflater.from(context);
    }
    /*public MyListAdapter(Context context,List<ItemInfo> userBeansList,MyClickListener listener){
        mList = userBeansList;
        mListener = listener;

        this.context = context;
        mlayoutInflater = LayoutInflater.from(context);
    }*/
    private void InitListView()
    {

    }

    @Override
    public int getCount() {
        return 20;
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
            convertView = mlayoutInflater.inflate(R.layout.layout_list_item,null);
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
        int pos = position+1;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String localtime = simpleDateFormat.format(date);


        holder.tvTitle.setText("标题"+pos);
        holder.tvTime.setText(localtime);
        holder.tvContext.setText("context");
        //Glide.with(context).load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1554742176602&di=46cebc51f7c64b446c38793329e9b8d2&imgtype=0&src=http%3A%2F%2Fwww.ooqiu.com%2Fuploads%2Fallimg%2F170726%2F262-1FH6142G20-L.jpg").into(holder.imageView);
        return convertView;
    }
}
