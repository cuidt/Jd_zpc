package com.jd_zpc.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jd_zpc.R;
import com.jd_zpc.bean.SearchBean;

import java.util.List;

/**
 * Created by DELL on 2017/11/19.
 */

public class MySearchAdapter extends BaseAdapter {
    private Context context;
    private List<SearchBean.DataBean> list;

    public MySearchAdapter(Context context, List<SearchBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        if (!(list.size()==0)){
            return list.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.lv_search_item, null);
            holder.sdv_search = convertView.findViewById(R.id.sdv_search);
            holder.tv_search_name = convertView.findViewById(R.id.tv_search_name);
            holder.tv_search_price = convertView.findViewById(R.id.tv_search_price);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        SearchBean.DataBean dataBean = list.get(position);
        String images = dataBean.getImages();
        String[] split = images.split("\\|");
        String image = split[0];
        double price = dataBean.getPrice();
        holder.sdv_search.setImageURI(image);
        holder.tv_search_name.setText(dataBean.getTitle());
        holder.tv_search_price.setText(price+"");
        return convertView;
    }
    class ViewHolder {
        SimpleDraweeView sdv_search;
        TextView tv_search_name;
        TextView tv_search_price;
    }
}
