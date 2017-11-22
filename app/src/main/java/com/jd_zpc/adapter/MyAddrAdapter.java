package com.jd_zpc.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jd_zpc.R;
import com.jd_zpc.bean.AddressBean;
import com.jd_zpc.bean.FenleiBean;

import java.util.List;

/**
 * Created by DELL on 2017/11/15.
 */

public class MyAddrAdapter extends BaseAdapter{
    private Context context;
    private List<AddressBean.DataBean> list;

    public MyAddrAdapter(Context context, List<AddressBean.DataBean> list) {
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
            convertView = View.inflate(context, R.layout.address_item,null);
            holder.tv_mobile= convertView.findViewById(R.id.tv_mobile);
            holder.tv_addr= convertView.findViewById(R.id.tv_addr);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        AddressBean.DataBean dataBean = list.get(position);
        long mobile = dataBean.getMobile();
        String addr = dataBean.getAddr();
        holder.tv_addr.setText(addr);
        String s = String.valueOf(mobile);
        holder.tv_mobile.setText(s);
        return convertView;
    }
    class ViewHolder{
        TextView tv_mobile,tv_addr;
    }
}
