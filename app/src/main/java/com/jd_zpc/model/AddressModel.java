package com.jd_zpc.model;

import com.jd_zpc.bean.AddressBean;
import com.jd_zpc.utils.Api;
import com.jd_zpc.utils.ApiService;
import com.jd_zpc.utils.OnNetListener;
import com.jd_zpc.utils.RetrofitUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by DELL on 2017/11/15.
 */

public class AddressModel {
    public void getAddress(int uid, String token, final OnNetListener onNetListener){
        ApiService apiService = RetrofitUtil.getInstance().getApiService(Api.devIP, ApiService.class);
        Call<AddressBean> address = apiService.getAddress(uid, token);
        address.enqueue(new Callback<AddressBean>() {
            @Override
            public void onResponse(Call<AddressBean> call, Response<AddressBean> response) {
                AddressBean addressBean = response.body();
                onNetListener.onSuccess(addressBean);
            }

            @Override
            public void onFailure(Call<AddressBean> call, Throwable t) {

            }
        });
    }
}
