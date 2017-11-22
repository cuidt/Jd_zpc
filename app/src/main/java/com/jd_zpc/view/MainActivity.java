package com.jd_zpc.view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jd_zpc.R;
import com.jd_zpc.fragment.Fragment_car;
import com.jd_zpc.fragment.Fragment_class;
import com.jd_zpc.fragment.Fragment_find;
import com.jd_zpc.fragment.Fragment_main;
import com.jd_zpc.fragment.Fragment_my;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private FrameLayout mFl;
    private ImageView mIv1;
    /**
     * 首页
     */
    private TextView mTv1;
    private LinearLayout mLl1;
    private ImageView mIv2;
    /**
     * 发现
     */
    private TextView mTv2;
    private LinearLayout mLl2;
    private ImageView mIv3;
    /**
     * 分类
     */
    private TextView mTv3;
    private LinearLayout mLl3;
    private ImageView mIv4;
    /**
     * 购物车
     */
    private TextView mTv4;
    private LinearLayout mLl4;
    private ImageView mIv5;
    /**
     * 我的
     */
    private TextView mTv5;
    private LinearLayout mLl5;
    private Fragment f1, f2, f3, f4,f5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

    }

    private void initView() {
        mFl = (FrameLayout) findViewById(R.id.fl);
        mIv1 = (ImageView) findViewById(R.id.iv_1);
        mLl1 = (LinearLayout) findViewById(R.id.ll_1);
        mLl1.setOnClickListener(this);
        mIv2 = (ImageView) findViewById(R.id.iv_2);
        mLl2 = (LinearLayout) findViewById(R.id.ll_2);
        mLl2.setOnClickListener(this);
        mIv3 = (ImageView) findViewById(R.id.iv_3);
        mLl3 = (LinearLayout) findViewById(R.id.ll_3);
        mLl3.setOnClickListener(this);
        mIv4 = (ImageView) findViewById(R.id.iv_4);
        mLl4 = (LinearLayout) findViewById(R.id.ll_4);
        mLl4.setOnClickListener(this);
        mIv5 = (ImageView) findViewById(R.id.iv_5);
        mLl5 = (LinearLayout) findViewById(R.id.ll_5);
        mLl5.setOnClickListener(this);
        initFragment1();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.ll_1:
                initFragment1();
                break;
            case R.id.ll_2:
                initFragment2();
                break;
            case R.id.ll_3:
                initFragment3();
                break;
            case R.id.ll_4:
                initFragment4();
                break;
            case R.id.ll_5:
                initFragment5();
                break;
        }
    }
    private void initFragment1() {
        //开启事务，fragment的控制是由事务来实现的
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();


        // 初始化fragment
        if (f1 == null) {
            f1 = new Fragment_main();
        }
        transaction.replace(R.id.fl, f1);

        //提交事务
        transaction.commit();
        mIv1.setImageResource(R.mipmap.ac1);
        mIv2.setImageResource(R.mipmap.abw);
        mIv3.setImageResource(R.mipmap.aby);
        mIv4.setImageResource(R.mipmap.abu);
        mIv5.setImageResource(R.mipmap.ac2);
    }
    private void initFragment2() {
        //开启事务，fragment的控制是由事务来实现的
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();


        // 初始化fragment
        if (f2 == null) {
            f2 = new Fragment_class();
        }
        transaction.replace(R.id.fl, f2);

        //提交事务
        transaction.commit();
        mIv1.setImageResource(R.mipmap.ac0);
        mIv2.setImageResource(R.mipmap.abx);
        mIv3.setImageResource(R.mipmap.aby);
        mIv4.setImageResource(R.mipmap.abu);
        mIv5.setImageResource(R.mipmap.ac2);
    }
    private void initFragment3() {
        //开启事务，fragment的控制是由事务来实现的
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();


        // 初始化fragment
        if (f3 == null) {
            f3 = new Fragment_find();
        }
        transaction.replace(R.id.fl, f3);

        //提交事务
        transaction.commit();
        mIv1.setImageResource(R.mipmap.ac0);
        mIv2.setImageResource(R.mipmap.abw);
        mIv3.setImageResource(R.mipmap.abz);
        mIv4.setImageResource(R.mipmap.abu);
        mIv5.setImageResource(R.mipmap.ac2);
    }
    private void initFragment4() {
        //开启事务，fragment的控制是由事务来实现的
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();


        // 初始化fragment
        if (f4 == null) {
            f4 = new Fragment_car();
        }
        transaction.replace(R.id.fl, f4);

        //提交事务
        transaction.commit();
        mIv1.setImageResource(R.mipmap.ac0);
        mIv2.setImageResource(R.mipmap.abw);
        mIv3.setImageResource(R.mipmap.aby);
        mIv4.setImageResource(R.mipmap.abv);
        mIv5.setImageResource(R.mipmap.ac2);
    }
    private void initFragment5() {
        //开启事务，fragment的控制是由事务来实现的
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();


        // 初始化fragment
        if (f5 == null) {
            f5 = new Fragment_my();
        }
        transaction.replace(R.id.fl, f5);

        //提交事务
        transaction.commit();
        mIv1.setImageResource(R.mipmap.ac0);
        mIv2.setImageResource(R.mipmap.abw);
        mIv3.setImageResource(R.mipmap.aby);
        mIv4.setImageResource(R.mipmap.abu);
        mIv5.setImageResource(R.mipmap.ac3);
    }
}
