package com.jd_zpc.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.jd_zpc.R;
import com.jd_zpc.bean.BaseBean;
import com.jd_zpc.prestenter.Prestener;

public class RegisterActivity extends BaseActivity implements View.OnClickListener,IRegisterListener {

    private ImageView mIvBack;
    /**
     * 请输入手机号:
     */
    private EditText mEtName;
    /**
     * 请输入密码:
     */
    private EditText mEtPassword;
    /**
     * 注册
     */
    private Button mBtRegister;
    private Prestener prestener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    private void initView() {
        mIvBack = (ImageView) findViewById(R.id.iv_back);
        mIvBack.setOnClickListener(this);
        mEtName = (EditText) findViewById(R.id.et_name);
        mEtPassword = (EditText) findViewById(R.id.et_password);
        mBtRegister = (Button) findViewById(R.id.bt_register);
        mBtRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.iv_back:
                RegisterActivity.this.finish();
                break;
            case R.id.bt_register:
                String name = mEtName.getText().toString().trim();
                String password = mEtPassword.getText().toString().trim();
                prestener = new Prestener(this);
                prestener.register(name,password);
                break;
        }
    }

    @Override
    public void register(BaseBean baseBean) {
        Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
