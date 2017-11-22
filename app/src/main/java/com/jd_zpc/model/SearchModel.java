package com.jd_zpc.model;

import com.jd_zpc.bean.SearchBean;
import com.jd_zpc.utils.Api;
import com.jd_zpc.utils.ApiService;
import com.jd_zpc.utils.OnNetListener;
import com.jd_zpc.utils.RetrofitUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by DELL on 2017/11/19.
 */

public class SearchModel {
    public void getSearch(String keywords, final OnNetListener onNetListener){
        ApiService apiService = RetrofitUtil.getInstance().getApiService(Api.devIP, ApiService.class);
        Call<SearchBean> search = apiService.getSearch(keywords, "android");
        search.enqueue(new Callback<SearchBean>() {
            @Override
            public void onResponse(Call<SearchBean> call, Response<SearchBean> response) {
                SearchBean searchBean = response.body();
                onNetListener.onSuccess(searchBean);
            }

            @Override
            public void onFailure(Call<SearchBean> call, Throwable t) {

            }
        });
    }
}
