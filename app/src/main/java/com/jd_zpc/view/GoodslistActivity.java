package com.jd_zpc.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.jd_zpc.R;
import com.jd_zpc.adapter.MyGoodsLvAdapter;
import com.jd_zpc.bean.GoodsBean;
import com.jd_zpc.prestenter.Prestener;

import java.util.List;

public class GoodslistActivity extends BaseActivity implements GoodsListListener{

    private ListView mLvGoodslist;
    private Prestener prestener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goodslist);
        initView();
    }

    private void initView() {
        mLvGoodslist = (ListView) findViewById(R.id.lv_goodslist);
        Intent intent = getIntent();
        int pscid = intent.getIntExtra("pscid", 1);
        prestener = new Prestener(this);
        prestener.getGoodsList(pscid);
    }

    @Override
    public void setList(GoodsBean goodsBean) {
        final List<GoodsBean.DataBean> data = goodsBean.getData();
        MyGoodsLvAdapter myGoodsLvAdapter = new MyGoodsLvAdapter(this, data);
        mLvGoodslist.setAdapter(myGoodsLvAdapter);
        mLvGoodslist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int pid = data.get(position).getPid();
                Intent intent = new Intent(GoodslistActivity.this, DetailActivity.class);
                intent.putExtra("pid",pid);
                startActivity(intent);
            }
        });
    }
}
