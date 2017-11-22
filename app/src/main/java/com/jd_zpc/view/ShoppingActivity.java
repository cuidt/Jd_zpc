package com.jd_zpc.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import com.jd_zpc.R;
import com.jd_zpc.zhifubao.PayDemoActivity;

public class ShoppingActivity extends BaseActivity implements View.OnClickListener {

    /**
     * 微信支付
     */
    private RadioButton mBtWexin;
    /**
     * 支付宝支付
     */
    private RadioButton mBtZhifubao;
    /**
     * 确认支付
     */
    private Button mBtQueren;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);
        initView();
    }

    private void initView() {
        mBtWexin = (RadioButton) findViewById(R.id.bt_wexin);
        mBtZhifubao = (RadioButton) findViewById(R.id.bt_zhifubao);
        mBtQueren = (Button) findViewById(R.id.bt_queren);
        mBtQueren.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.bt_queren:
                if (mBtWexin.isChecked()){
                    Toast.makeText(this, "微信支付", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ShoppingActivity.this, WXZhifuActivity.class);
                    startActivity(intent);
                }
                if (mBtZhifubao.isChecked()){
                    Toast.makeText(this, "支付宝支付", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ShoppingActivity.this, PayDemoActivity.class);
                    startActivity(intent);
                }
                break;
        }
    }
}
