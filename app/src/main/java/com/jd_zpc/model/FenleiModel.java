package com.jd_zpc.model;

import com.jd_zpc.bean.FenleiBean;
import com.jd_zpc.bean.ParentBean;
import com.jd_zpc.utils.Api;
import com.jd_zpc.utils.ApiService;
import com.jd_zpc.utils.OnNetListener;
import com.jd_zpc.utils.RetrofitUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by DELL on 2017/11/7.
 */

public class FenleiModel {
    public void getProduct(final OnNetListener onNetListener) {
        ApiService apiService = RetrofitUtil.getInstance().getApiService(Api.devIP, ApiService.class);
        Call<FenleiBean> product = apiService.getProduct();
        product.enqueue(new Callback<FenleiBean>() {
            @Override
            public void onResponse(Call<FenleiBean> call, Response<FenleiBean> response) {
                FenleiBean body = response.body();
                onNetListener.onSuccess(body);
            }

            @Override
            public void onFailure(Call<FenleiBean> call, Throwable t) {

            }
        });
    }

    public void getProduct2(int cid, final OnNetListener onNetListener) {
        ApiService apiService = RetrofitUtil.getInstance().getApiService(Api.devIP, ApiService.class);
        Call<ParentBean> product2 = apiService.getProduct2(cid);
        product2.enqueue(new Callback<ParentBean>() {
            @Override
            public void onResponse(Call<ParentBean> call, Response<ParentBean> response) {
                ParentBean parentBean = response.body();
                onNetListener.onSuccess(parentBean);
            }

            @Override
            public void onFailure(Call<ParentBean> call, Throwable t) {

            }
        });

    }
}
