package com.jd_zpc.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jd_zpc.R;
import com.jd_zpc.bean.BaseBean;
import com.jd_zpc.prestenter.Prestener;

public class Add_addressActivity extends BaseActivity implements View.OnClickListener,Add_addressListener {

    private EditText mEtAddressName;
    private EditText mEtAddressMobile;
    private EditText mEtAddress;
    /**
     * 保存并使用
     */
    private Button mBtShiyong;
    private Prestener prestener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);
        initView();
    }

    private void initView() {
        mEtAddressName = (EditText) findViewById(R.id.et_address_name);
        mEtAddressMobile = (EditText) findViewById(R.id.et_address_mobile);
        mEtAddress = (EditText) findViewById(R.id.et_address);
        mBtShiyong = (Button) findViewById(R.id.bt_shiyong);
        mBtShiyong.setOnClickListener(this);
        prestener = new Prestener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.bt_shiyong:
                SharedPreferences user = getSharedPreferences("user", MODE_PRIVATE);
                int uid = user.getInt("uid", 0);
                String token = user.getString("token", null);
                String name = mEtAddressName.getText().toString().trim();
                String mobile = mEtAddressMobile.getText().toString().trim();
                String address = mEtAddress.getText().toString().trim();
                prestener.addAddress(uid,name,mobile,address,token);
                break;
        }
    }

    @Override
    public void addAddress(BaseBean baseBean) {
        String msg = baseBean.getMsg();
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Add_addressActivity.this,AddressActivity.class);
        startActivity(intent);
        finish();
    }
}
