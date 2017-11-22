package com.jd_zpc.model;

import com.jd_zpc.bean.BaseBean;
import com.jd_zpc.utils.Api;
import com.jd_zpc.utils.ApiService;
import com.jd_zpc.utils.OnNetListener;
import com.jd_zpc.utils.RetrofitUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by DELL on 2017/11/3.
 */

public class RegisterModel {
    public void register(String name, String password, final OnNetListener onNetListener){
        ApiService apiService = RetrofitUtil.getInstance().getApiService(Api.devIP, ApiService.class);
        Call<BaseBean> call = apiService.register(name, password);
        call.enqueue(new Callback<BaseBean>() {
            @Override
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                BaseBean baseBean = response.body();
                onNetListener.onSuccess(baseBean);
            }

            @Override
            public void onFailure(Call<BaseBean> call, Throwable t) {

            }
        });
    }
}
