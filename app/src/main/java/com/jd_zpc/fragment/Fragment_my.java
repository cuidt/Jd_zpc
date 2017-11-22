package com.jd_zpc.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jd_zpc.R;
import com.jd_zpc.bean.BaseBean;
import com.jd_zpc.bean.Users;
import com.jd_zpc.prestenter.Prestener;
import com.jd_zpc.view.LoginActivity;
import com.jd_zpc.view.UpLoadListener;
import com.jd_zpc.view.UserInfoListener;
import com.makeramen.roundedimageview.RoundedImageView;

/**
 * Created by DELL on 2017/11/2.
 */

public class Fragment_my extends Fragment implements View.OnClickListener {
    private View view;
    private RoundedImageView mMyUser;
    /**
     * 登录/注册
     */
    private TextView mTvNickname;
    private LinearLayout mLlMyReg;
    private RelativeLayout mRlReg;
    /**
     * 我的订单
     */
    private TextView mTvMyOrder;
    /**
     * 白条
     */
    private TextView mTextView;
    private int uid;
    private String token;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        SharedPreferences user = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        uid = user.getInt("uid", 0);
        token = user.getString("token", null);
        boolean islogin = user.getBoolean("islogin", false);
        initView(view);
        if (islogin){
            Prestener prestener = new Prestener(new UserInfoListener() {
            @Override
            public void userInfo(Users users) {
                String msg = users.getMsg();
                Log.i("UserInfo",msg);
                String username = users.getData().getUsername();
                String icon = (String) users.getData().getIcon();
                Log.i("UserInfo_icon",icon);
                mTvNickname.setText(username);
                Glide.with(getActivity()).load(icon).into(mMyUser);
            }
        });
        prestener.getUserInfo(uid,token);
        }


        return view;
    }

    private void initView(View view) {
        mMyUser = (RoundedImageView) view.findViewById(R.id.my_user);
        mTvNickname = (TextView) view.findViewById(R.id.tv_nickname);
        mTvNickname.setOnClickListener(this);
        mLlMyReg = (LinearLayout) view.findViewById(R.id.ll_my_reg);
        mRlReg = (RelativeLayout) view.findViewById(R.id.rl_reg);
        mTvMyOrder = (TextView) view.findViewById(R.id.tv_myOrder);
        mTextView = (TextView) view.findViewById(R.id.textView);
        mMyUser.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.tv_nickname:
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.my_user:
                Prestener prestener = new Prestener(new UpLoadListener() {
                    @Override
                    public void upload(BaseBean baseBean) {
                        Log.i("xxxxxx", baseBean.getMsg());
                    }
                });
                prestener.upimage(uid, token);
                break;
        }
    }
}
