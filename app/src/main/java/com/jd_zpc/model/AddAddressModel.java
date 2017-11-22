package com.jd_zpc.model;

import com.jd_zpc.bean.BaseBean;
import com.jd_zpc.utils.Api;
import com.jd_zpc.utils.ApiService;
import com.jd_zpc.utils.OnNetListener;
import com.jd_zpc.utils.RetrofitUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by DELL on 2017/11/15.
 */

public class AddAddressModel {
    public void addAddress(int uid, String addr, String name, String mobile, String token, final OnNetListener onNetListener){
        ApiService apiService = RetrofitUtil.getInstance().getApiService(Api.devIP, ApiService.class);
        Call<BaseBean> call = apiService.addAddress(uid, name, mobile, addr, token);
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
