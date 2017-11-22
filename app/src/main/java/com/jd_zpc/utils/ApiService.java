package com.jd_zpc.utils;

import com.jd_zpc.bean.AddressBean;
import com.jd_zpc.bean.BaseBean;
import com.jd_zpc.bean.CarBean;
import com.jd_zpc.bean.Databean;
import com.jd_zpc.bean.DetailBean;
import com.jd_zpc.bean.FenleiBean;
import com.jd_zpc.bean.GoodsBean;
import com.jd_zpc.bean.OrderCreate;
import com.jd_zpc.bean.ParentBean;
import com.jd_zpc.bean.SearchBean;
import com.jd_zpc.bean.Users;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by DELL on 2017/11/3.
 */

public interface ApiService {
    /**
     * 登录
     *
     * @param name
     * @param password
     * @return
     */

    @GET("user/login")
    Call<Users> login(@Query("mobile") String name, @Query("password") String password);

    /**
     * 注册
     *
     * @param name
     * @param password
     * @return
     */
    @GET("user/reg")
    Call<BaseBean> register(@Query("mobile") String name, @Query("password") String password);

    /**
     * 首页广告数据
     *
     * @return
     */
    @GET("ad/getAd")
    Call<Databean> getData();

    //详情
    @GET("product/getProductDetail")
    Call<DetailBean> getDetail(@Query("pid") int pid);

    //商品分类
    @GET("product/getCatagory")
    Call<FenleiBean> getProduct();

    //商品分类
    @GET("product/getProductCatagory")
    Call<ParentBean> getProduct2(@Query("cid") int cid);

    //子分类列表
    @GET("product/getProducts")
    Call<GoodsBean> getGoodsList(@Query("pscid") int pscid);

    //加入购物车
    @GET("product/addCart")
    Call<BaseBean> addCart(@Query("uid") int uid, @Query("pid") int pid, @Query("token") String token);

    //查询购物车
    @GET("product/getCarts")
    Call<CarBean> getCart(@Query("uid") int uid, @Query("token") String token);

    //删除购物车
    @GET("product/deleteCart")
    Call<BaseBean> delCart(@Query("uid") int uid, @Query("pid") int pid, @Query("token") String token);

    //创建订单
    @GET("product/createOrder")
    Call<OrderCreate> createOrder(@Query("uid") int uid, @Query("price") String price, @Query("token") String token);

    //新建收货地址
    @GET("user/addAddr")
    Call<BaseBean> addAddress(@Query("uid") int uid, @Query("name") String name, @Query("mobile") String mobile, @Query("addr") String addr, @Query("token") String token);

    //收货地址列表
    @GET("user/getAddrs")
    Call<AddressBean> getAddress(@Query("uid") int uid, @Query("token") String token);

    //查询
    @GET("product/searchProducts")
    Call<SearchBean> getSearch(@Query("keywords") String keywords, @Query("source") String source);

    //上传头像
    @Multipart
    @POST("file/upload")
    Call<BaseBean> upLoadImage(@Part("uid") RequestBody uid, @Part MultipartBody.Part file, @Part("token") RequestBody token);

    //获取用户信息
    @GET("user/getUserInfo")
    Call<Users> getUserInfo(@Query("uid") int uid,@Query("token") String token);
}
