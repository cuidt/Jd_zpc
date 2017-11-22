package com.jd_zpc.model;

import com.jd_zpc.bean.Databean;
import com.jd_zpc.utils.Api;
import com.jd_zpc.utils.ApiService;
import com.jd_zpc.utils.OnNetListener;
import com.jd_zpc.utils.RetrofitUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by DELL on 2017/11/6.
 */

public class FirstModel {

    public void getData(final OnNetListener onNetListener){
        ApiService apiService = RetrofitUtil.getInstance().getApiService(Api.devIP, ApiService.class);
        Call<Databean> data = apiService.getData();
        data.enqueue(new Callback<Databean>() {
            @Override
            public void onResponse(Call<Databean> call, Response<Databean> response) {
                Databean databean = response.body();
                onNetListener.onSuccess(databean);
            }

            @Override
            public void onFailure(Call<Databean> call, Throwable t) {

            }
        });

    }
}
