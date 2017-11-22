package com.jd_zpc.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jd_zpc.R;
import com.jd_zpc.bean.OrderCreate;
import com.jd_zpc.prestenter.Prestener;

public class OrderActivity extends BaseActivity implements View.OnClickListener, OrderCreateListener {

    /**
     * 退出
     */
    private TextView mTvOrderBack;
    private TextView mTvOrderName;
    /**
     * ..
     */
    private TextView mTvOrderAddress;
    private LinearLayout mLlOrderAddress;
    /**
     * 1111
     */
    private TextView mTvOrderPrice;
    /**
     * 立即下单
     */
    private Button mBtOrder;
    private String totalprice;
    private Prestener prestener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        initView();
        Intent intent = getIntent();
        totalprice = intent.getStringExtra("totalprice");
        mTvOrderPrice.setText("实付款: ¥" + totalprice);
        mTvOrderName.setText("收货地址");
    }

    private void initView() {
        mTvOrderBack = (TextView) findViewById(R.id.tv_order_back);
        mTvOrderBack.setOnClickListener(this);
        mTvOrderName = (TextView) findViewById(R.id.tv_order_name);
        mTvOrderAddress = (TextView) findViewById(R.id.tv_order_address);
        mLlOrderAddress = (LinearLayout) findViewById(R.id.ll_order_address);
        mLlOrderAddress.setOnClickListener(this);
        mTvOrderPrice = (TextView) findViewById(R.id.tv_order_price);
        mBtOrder = (Button) findViewById(R.id.bt_order);
        mBtOrder.setOnClickListener(this);
        prestener = new Prestener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.tv_order_back:
                finish();
                break;
            case R.id.ll_order_address:
                Intent intent = new Intent(OrderActivity.this, AddressActivity.class);
                startActivityForResult(intent, 11);
                break;
            case R.id.bt_order:
                SharedPreferences user = getSharedPreferences("user", MODE_PRIVATE);
                int uid = user.getInt("uid", 0);
                String token = user.getString("token", null);
                prestener.orderCreate(uid, totalprice, token);
                break;
        }
    }

    @Override
    public void setOrderCreate(OrderCreate orderCreate) {
        String msg = orderCreate.getMsg();
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(OrderActivity.this,ShoppingActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String addr = data.getStringExtra("addr");
        String phoneNum = data.getStringExtra("phoneNum");
        mTvOrderName.setText(phoneNum + "   电话 地址");
        mTvOrderAddress.setText(addr);
    }
}
