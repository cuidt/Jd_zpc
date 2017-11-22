package com.jd_zpc.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.jd_zpc.R;
import com.jd_zpc.adapter.MyErAdapter;
import com.jd_zpc.bean.BaseBean;
import com.jd_zpc.bean.CarBean;
import com.jd_zpc.bean.MessageCounEvent;
import com.jd_zpc.bean.MessageEvent;
import com.jd_zpc.prestenter.Prestener;
import com.jd_zpc.view.GetCarListener;
import com.jd_zpc.view.OrderActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 2017/11/2.
 */

public class Fragment_car extends Fragment implements View.OnClickListener, GetCarListener {
    private View view;
    private ExpandableListView mElv;
    /**
     * 全选
     */
    private CheckBox mCbAll;
    /**
     * 总计：
     */
    private TextView mTvTotal;
    /**
     * 去结算
     */
    private Button mBtZf;
    private Prestener prestener;
    private int uid;
    private String token;
    private MyErAdapter myErAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_car, container, false);
        EventBus.getDefault().register(this);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mElv = (ExpandableListView) view.findViewById(R.id.elv);
        mCbAll = (CheckBox) view.findViewById(R.id.cbAll);
        mTvTotal = (TextView) view.findViewById(R.id.tvTotal);
        mBtZf = (Button) view.findViewById(R.id.bt_zf);
        mBtZf.setOnClickListener(this);
        SharedPreferences user = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        uid = user.getInt("uid", 0);
        token = user.getString("token", null);
        prestener = new Prestener(this);
        prestener.getCar(uid, token);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.bt_zf:
                Intent intent = new Intent(getActivity(), OrderActivity.class);
                String totalPrice = mTvTotal.getText().toString();

                if (!totalPrice.equals("总计:")&&!totalPrice.equals("总计: 0.0 元")){
                    String[] split = totalPrice.split(" ");
                    String str = split[1];
                    intent.putExtra("totalprice", str);
                    startActivity(intent);
                }
                Toast.makeText(getActivity(), "请先选择商品!", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void setCar(CarBean carBean) {
        List<CarBean.DataBean> data = carBean.getData();
        List<List<CarBean.DataBean.ListBean>> clist = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            List<CarBean.DataBean.ListBean> list = data.get(i).getList();
            clist.add(list);
        }
        myErAdapter = new MyErAdapter(getActivity(), data, clist);
        //隐藏下拉三角
        mElv.setGroupIndicator(null);
        mElv.setAdapter(myErAdapter);
        //全部展开
        for (int i = 0; i < data.size(); i++) {
            mElv.expandGroup(i);
        }
        mCbAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myErAdapter.allchecked(mCbAll.isChecked());
            }
        });
        myErAdapter.setItemcllick(new MyErAdapter.OnItemClistener() {
            @Override
            public void onitemclick(int pid) {
                prestener.delCar(uid,pid,token);
            }
        });
    }

    @Override
    public void setdelCar(BaseBean baseBean) {
        String msg = baseBean.getMsg();
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Subscribe
    public void messageEvent(MessageEvent messageEvent) {
        mCbAll.setChecked(messageEvent.isFlag());
    }

    @Subscribe
    public void messageCountEvent(MessageCounEvent msg) {
        mTvTotal.setText("总计: " + msg.getMoney() + " 元");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }
}
