package com.jd_zpc.model;

import android.os.Environment;

import com.jd_zpc.bean.BaseBean;
import com.jd_zpc.utils.Api;
import com.jd_zpc.utils.ApiService;
import com.jd_zpc.utils.OnNetListener;
import com.jd_zpc.utils.RetrofitUtil;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by DELL on 2017/11/21.
 */

public class UpLoadModel {
    public void upLoadimage(int uid, String token, final OnNetListener onNetListener){
        ApiService apiService = RetrofitUtil.getInstance().getApiService(Api.devIP, ApiService.class);

        File file = new File(Environment.getExternalStorageDirectory()+"/Pictures/Screenshots/a.jpg");

        final RequestBody requestBody = RequestBody.create(MediaType.parse("application/otcet-stream"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
        RequestBody uids = RequestBody.create(MediaType.parse("multipart/form-data"), uid + "");
        RequestBody tokn = RequestBody.create(MediaType.parse("multipart/form-data"), token);
        Call<BaseBean> call = apiService.upLoadImage(uids, body, tokn);
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
