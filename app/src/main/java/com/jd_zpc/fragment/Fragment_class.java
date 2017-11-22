package com.jd_zpc.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.jd_zpc.R;
import com.jd_zpc.adapter.MyLvAdapter;
import com.jd_zpc.adapter.MyParentRvAdapter;
import com.jd_zpc.bean.FenleiBean;
import com.jd_zpc.bean.ParentBean;
import com.jd_zpc.prestenter.Prestener;
import com.jd_zpc.view.FenleiListener;

import java.util.List;

/**
 * Created by DELL on 2017/11/2.
 */

public class Fragment_class extends Fragment implements FenleiListener{
    private View view;
    private ListView mLvClass;
    private RecyclerView mRvClass;
    private Prestener prestener;
    private MyLvAdapter myLvAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_class, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mLvClass = (ListView) view.findViewById(R.id.lv_class);
        mRvClass = (RecyclerView) view.findViewById(R.id.rv_class);
        mRvClass.setLayoutManager(new LinearLayoutManager(getActivity()));
        prestener = new Prestener(this);
        prestener.getProduct();
        prestener.getProduct2(1);

    }

    @Override
    public void setProduct(FenleiBean fenleiBean) {
        final List<FenleiBean.DataBean> data = fenleiBean.getData();
        myLvAdapter = new MyLvAdapter(getActivity(), data);
        mLvClass.setAdapter(myLvAdapter);
        mLvClass.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                parent.getItemAtPosition(position);
                int cid = data.get(position).getCid();
                prestener.getProduct2(cid);
            }
        });
    }

    @Override
    public void setProduct2(ParentBean parentBean) {
        List<ParentBean.DataBean> data = parentBean.getData();
        MyParentRvAdapter myParentRvAdapter = new MyParentRvAdapter(getActivity(), data);
        mRvClass.setAdapter(myParentRvAdapter);
    }

}
