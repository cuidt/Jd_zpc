package com.jd_zpc.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jd_zpc.R;
import com.jd_zpc.bean.Databean;

import java.util.List;

/**
 * Created by DELL on 2017/11/7.
 */

public class MyRvMiaoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<Databean.MiaoshaBean.ListBeanX> list;

    public MyRvMiaoAdapter(Context context, List<Databean.MiaoshaBean.ListBeanX> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.maiosha_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        Databean.MiaoshaBean.ListBeanX listBeanX = list.get(position);
        double bargainPrice = listBeanX.getBargainPrice();
        double price = listBeanX.getPrice();
        String images = listBeanX.getImages();
        String[] split = images.split("\\|");
        String image = split[0];
        myViewHolder.sdv_miao.setImageURI(image);
        myViewHolder.tv_top.setText("¥:"+bargainPrice+"");
        myViewHolder.tv_bom.setText(price+"");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView sdv_miao;
        private final TextView tv_top;
        private final TextView tv_bom;

        public MyViewHolder(View itemView) {
            super(itemView);
            sdv_miao = itemView.findViewById(R.id.sdv_miaosha);
            tv_top = itemView.findViewById(R.id.tv_prive_top);
            tv_bom = itemView.findViewById(R.id.tv_prive_bom);
            tv_bom.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        }
    }
}
