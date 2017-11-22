package com.jd_zpc.model;

import com.jd_zpc.bean.DetailBean;
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

public class DetailModel {
    public void getDrtail(int pid , final OnNetListener onNetListener){
        ApiService apiService = RetrofitUtil.getInstance().getApiService(Api.devIP, ApiService.class);
        Call<DetailBean> detail = apiService.getDetail(pid);
        detail.enqueue(new Callback<DetailBean>() {
            @Override
            public void onResponse(Call<DetailBean> call, Response<DetailBean> response) {
                DetailBean detailBean = response.body();
                onNetListener.onSuccess(detailBean);
            }

            @Override
            public void onFailure(Call<DetailBean> call, Throwable t) {

            }
        });
    }
}
