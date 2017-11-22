package com.jd_zpc.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.jd_zpc.R;
import com.jd_zpc.adapter.MyAddrAdapter;
import com.jd_zpc.bean.AddressBean;
import com.jd_zpc.prestenter.Prestener;

import java.util.List;

public class AddressActivity extends BaseActivity implements View.OnClickListener, AddressListener {

    private ListView mLvAddlist;
    /**
     * 新建地址
     */
    private Button mBtAddressAdd;
    private Prestener prestener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        initView();
    }

    private void initView() {
        mLvAddlist = (ListView) findViewById(R.id.lv_addlist);
        mBtAddressAdd = (Button) findViewById(R.id.bt_address_add);
        mBtAddressAdd.setOnClickListener(this);
        SharedPreferences user = getSharedPreferences("user", MODE_PRIVATE);
        int uid = user.getInt("uid", 0);
        String token = user.getString("token", null);
        prestener = new Prestener(this);
        prestener.getAddress(uid, token);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.bt_address_add:
                Intent intent = new Intent(AddressActivity.this, Add_addressActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void getAddress(AddressBean addressBean) {
        final List<AddressBean.DataBean> data = addressBean.getData();
        MyAddrAdapter adapter = new MyAddrAdapter(this, data);
        mLvAddlist.setAdapter(adapter);
        mLvAddlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String addr = data.get(position).getAddr();
                long mobile = data.get(position).getMobile();
                String phoneNum = String.valueOf(mobile);
                Intent intent = new Intent(AddressActivity.this,OrderActivity.class);
                intent.putExtra("addr",addr);
                intent.putExtra("phoneNum",phoneNum);
                setResult(11,intent);
                finish();
            }
        });

    }
}
