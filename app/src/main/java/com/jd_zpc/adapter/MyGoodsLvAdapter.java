package com.jd_zpc.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jd_zpc.R;
import com.jd_zpc.bean.FenleiBean;
import com.jd_zpc.bean.GoodsBean;

import java.util.List;

/**
 * Created by DELL on 2017/11/8.
 */

public class MyGoodsLvAdapter extends BaseAdapter {
    private Context context;
    private List<GoodsBean.DataBean> list;

    public MyGoodsLvAdapter(Context context, List<GoodsBean.DataBean> list) {
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
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.lv_goodslist_item, null);
            holder.sdv_goods = convertView.findViewById(R.id.sdv_goods);
            holder.tv_goods_name = convertView.findViewById(R.id.tv_goods_name);
            holder.tv_goods_price = convertView.findViewById(R.id.tv_goods_price);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        GoodsBean.DataBean dataBean = list.get(position);
        String images = dataBean.getImages();
        String[] split = images.split("\\|");
        String image = split[0];
        double price = dataBean.getPrice();
        holder.sdv_goods.setImageURI(image);
        holder.tv_goods_name.setText(dataBean.getTitle());
        holder.tv_goods_price.setText(price+"");
        return convertView;
    }

    class ViewHolder {
        SimpleDraweeView sdv_goods;
        TextView tv_goods_name;
        TextView tv_goods_price;
    }
}
