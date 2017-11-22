package com.jd_zpc.model;

import com.jd_zpc.bean.CarBean;
import com.jd_zpc.utils.Api;
import com.jd_zpc.utils.ApiService;
import com.jd_zpc.utils.OnNetListener;
import com.jd_zpc.utils.RetrofitUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by DELL on 2017/11/9.
 */

public class GetCarMmodel {
    public void getCar(int uid, String token, final OnNetListener onNetListener){
        ApiService apiService = RetrofitUtil.getInstance().getApiService(Api.devIP, ApiService.class);
        Call<CarBean> cart = apiService.getCart(uid, token);
        cart.enqueue(new Callback<CarBean>() {
            @Override
            public void onResponse(Call<CarBean> call, Response<CarBean> response) {
                CarBean carBean = response.body();
                onNetListener.onSuccess(carBean);
            }

            @Override
            public void onFailure(Call<CarBean> call, Throwable t) {

            }
        });

    }
}
