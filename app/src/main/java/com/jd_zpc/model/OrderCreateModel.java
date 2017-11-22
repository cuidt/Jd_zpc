package com.jd_zpc.model;

import com.jd_zpc.bean.OrderCreate;
import com.jd_zpc.utils.Api;
import com.jd_zpc.utils.ApiService;
import com.jd_zpc.utils.OnNetListener;
import com.jd_zpc.utils.RetrofitUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by DELL on 2017/11/13.
 */

public class OrderCreateModel {
    public void orderCreate(int uid, String price, String token, final OnNetListener onNetListener){
        ApiService apiService = RetrofitUtil.getInstance().getApiService(Api.devIP, ApiService.class);
        Call<OrderCreate> order = apiService.createOrder(uid, price, token);
        order.enqueue(new Callback<OrderCreate>() {
            @Override
            public void onResponse(Call<OrderCreate> call, Response<OrderCreate> response) {
                OrderCreate orderCreate = response.body();
                onNetListener.onSuccess(orderCreate);
            }

            @Override
            public void onFailure(Call<OrderCreate> call, Throwable t) {

            }
        });
    }
}
