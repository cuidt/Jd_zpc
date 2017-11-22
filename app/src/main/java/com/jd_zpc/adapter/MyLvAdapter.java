package com.jd_zpc.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jd_zpc.R;
import com.jd_zpc.bean.FenleiBean;

import java.util.List;

/**
 * Created by DELL on 2017/11/7.
 */

public class MyLvAdapter extends BaseAdapter {
    private Context context;
    private List<FenleiBean.DataBean> list;

    public MyLvAdapter(Context context, List<FenleiBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
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
            holder=new ViewHolder();
            convertView = View.inflate(context, R.layout.lv_item,null);
           holder.tv_name= convertView.findViewById(R.id.lv_name);
           convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        FenleiBean.DataBean dataBean = list.get(position);
        holder.tv_name.setText(dataBean.getName());
        return convertView;
    }

    class ViewHolder {
        TextView tv_name;
    }
}
