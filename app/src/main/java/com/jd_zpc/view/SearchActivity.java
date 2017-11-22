package com.jd_zpc.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.jd_zpc.R;
import com.jd_zpc.adapter.MySearchAdapter;
import com.jd_zpc.bean.SearchBean;
import com.jd_zpc.prestenter.Prestener;

import java.util.List;

public class SearchActivity extends BaseActivity implements View.OnClickListener ,SearchListener{

    /**
     * 请输入搜索内容
     */
    private EditText mSousuo;
    /**
     * 搜索
     */
    private Button mBtSearch;
    private ListView mLvSearch;
    private Prestener prestener;
    private List<SearchBean.DataBean> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();
    }

    private void initView() {
        mSousuo = (EditText) findViewById(R.id.sousuo);
        mBtSearch = (Button) findViewById(R.id.bt_search);
        mBtSearch.setOnClickListener(this);
        mLvSearch = (ListView) findViewById(R.id.lv_search);
        prestener = new Prestener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.bt_search:
                String sousuo = mSousuo.getText().toString().trim();
                prestener.getSearch(sousuo);
                break;
        }
    }

    @Override
    public void setSearch(SearchBean searchBean) {
        String msg = searchBean.getMsg();
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        data = searchBean.getData();
        MySearchAdapter mySearchAdapter = new MySearchAdapter(this, data);
        mLvSearch.setAdapter(mySearchAdapter);

        mLvSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int pid = data.get(position).getPid();
                Intent intent = new Intent(SearchActivity.this,DetailActivity.class);
                intent.putExtra("pid",pid);
                startActivity(intent);
            }
        });
    }
}
