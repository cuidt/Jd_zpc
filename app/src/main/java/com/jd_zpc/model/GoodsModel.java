package com.jd_zpc.model;

import com.jd_zpc.bean.GoodsBean;
import com.jd_zpc.utils.Api;
import com.jd_zpc.utils.ApiService;
import com.jd_zpc.utils.OnNetListener;
import com.jd_zpc.utils.RetrofitUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by DELL on 2017/11/8.
 */

public class GoodsModel {
    public void getGoods(int pscid, final OnNetListener onNetListener){
        ApiService apiService = RetrofitUtil.getInstance().getApiService(Api.devIP, ApiService.class);
        Call<GoodsBean> goodsList = apiService.getGoodsList(pscid);
        goodsList.enqueue(new Callback<GoodsBean>() {
            @Override
            public void onResponse(Call<GoodsBean> call, Response<GoodsBean> response) {
                GoodsBean goodsBean = response.body();
                onNetListener.onSuccess(goodsBean);
            }

            @Override
            public void onFailure(Call<GoodsBean> call, Throwable t) {

            }
        });
    }
}
