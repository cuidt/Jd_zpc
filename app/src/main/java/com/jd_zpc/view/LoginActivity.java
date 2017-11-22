package com.jd_zpc.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jd_zpc.R;
import com.jd_zpc.bean.BaseBean;
import com.jd_zpc.bean.Users;
import com.jd_zpc.prestenter.Prestener;

public class LoginActivity extends BaseActivity implements View.OnClickListener, ILoginListener {

    /**
     * 请输入账号:
     */
    private EditText mEtName;
    /**
     * 请输入密码:
     */
    private EditText mEtPassword;
    /**
     * 登录
     */
    private Button mBtLogin;
    /**
     * 手机快速注册
     */
    private TextView mTvRegister;
    /**
     * 忘记密码
     */
    private TextView mTvForget;
    private Prestener prestener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    //初始化组件
    private void initView() {
        mEtName = (EditText) findViewById(R.id.et_name);
        mEtPassword = (EditText) findViewById(R.id.et_password);
        mBtLogin = (Button) findViewById(R.id.bt_login);
        mBtLogin.setOnClickListener(this);
        mTvRegister = (TextView) findViewById(R.id.tv_register);
        mTvRegister.setOnClickListener(this);
        mTvForget = (TextView) findViewById(R.id.tv_forget);
        mTvForget.setOnClickListener(this);
    }

    //点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.bt_login:
                String name = mEtName.getText().toString().trim();
                String password = mEtPassword.getText().toString().trim();
                prestener = new Prestener(this);
                prestener.login(name,password);
                finish();
                break;
            case R.id.tv_register:
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.tv_forget:
                break;
        }
    }


    @Override
    public void login(Users users) {
        int uid = users.getData().getUid();
        String token = users.getData().getToken();
        SharedPreferences user = getSharedPreferences("user", MODE_PRIVATE);
        SharedPreferences.Editor edit = user.edit();
        edit.putInt("uid",uid);
        edit.putString("token",token);
        edit.putBoolean("islogin",true);
        edit.commit();
        Log.i("XXXXXXXXXXXXXXXXXXXXX",uid+"   "+token);
        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
    }
}
