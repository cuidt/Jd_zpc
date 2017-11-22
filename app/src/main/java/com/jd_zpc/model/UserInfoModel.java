package com.jd_zpc.model;

import com.jd_zpc.bean.Users;
import com.jd_zpc.utils.Api;
import com.jd_zpc.utils.ApiService;
import com.jd_zpc.utils.OnNetListener;
import com.jd_zpc.utils.RetrofitUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by DELL on 2017/11/21.
 */

public class UserInfoModel {
    public void getUserInfo(int uid, String token, final OnNetListener onNetListener){
        ApiService apiService = RetrofitUtil.getInstance().getApiService(Api.devIP, ApiService.class);
        Call<Users> userInfo = apiService.getUserInfo(uid, token);
        userInfo.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                Users users = response.body();
                onNetListener.onSuccess(users);
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {

            }
        });
    }
}
