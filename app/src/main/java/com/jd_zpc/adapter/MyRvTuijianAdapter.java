package com.jd_zpc.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jd_zpc.R;
import com.jd_zpc.bean.Databean;

import java.util.List;

/**
 * Created by DELL on 2017/11/7.
 */

public class MyRvTuijianAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<Databean.TuijianBean.ListBean> list;
    private OnItemListener onItemListener;

    public MyRvTuijianAdapter(Context context, List<Databean.TuijianBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    public interface OnItemListener {
        void onItemClick(int pid, int position);
    }

    public void setOnItemListener(OnItemListener onItemListener) {
        this.onItemListener = onItemListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.tuijian_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        String title = list.get(position).getTitle();
        double price = list.get(position).getPrice();
        final int pid = list.get(position).getPid();
        String images = list.get(position).getImages();
        String[] split = images.split("\\|");
        String image = split[0];
        myViewHolder.sdv_tuijain.setImageURI(image);
        myViewHolder.tv_title.setText(title);
        myViewHolder.tv_price.setText("¥:" + price + "");
        myViewHolder.ll_tuijian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemListener!=null){
                    onItemListener.onItemClick(pid,position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView sdv_tuijain;
        private final TextView tv_title;
        private final TextView tv_price;
        private final LinearLayout ll_tuijian;

        public MyViewHolder(View itemView) {
            super(itemView);
            sdv_tuijain = itemView.findViewById(R.id.sdv_tuijian);
            tv_title = itemView.findViewById(R.id.tv_tuijian_title);
            tv_price = itemView.findViewById(R.id.tv_tuijian_price);
            ll_tuijian = itemView.findViewById(R.id.ll_tuijian);
        }
    }
}
