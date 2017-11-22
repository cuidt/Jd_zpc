package com.jd_zpc.prestenter;

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
import com.jd_zpc.model.AddAddressModel;
import com.jd_zpc.model.AddCarModel;
import com.jd_zpc.model.AddressModel;
import com.jd_zpc.model.DelCarModel;
import com.jd_zpc.model.DetailModel;
import com.jd_zpc.model.FenleiModel;
import com.jd_zpc.model.FirstModel;
import com.jd_zpc.model.GetCarMmodel;
import com.jd_zpc.model.GoodsModel;
import com.jd_zpc.model.LoginModel;
import com.jd_zpc.model.OrderCreateModel;
import com.jd_zpc.model.RegisterModel;
import com.jd_zpc.model.SearchModel;
import com.jd_zpc.model.UpLoadModel;
import com.jd_zpc.model.UserInfoModel;
import com.jd_zpc.utils.OnNetListener;
import com.jd_zpc.view.Add_addressListener;
import com.jd_zpc.view.AddressListener;
import com.jd_zpc.view.DelCarListener;
import com.jd_zpc.view.DetailListener;
import com.jd_zpc.view.FenleiListener;
import com.jd_zpc.view.FirstListener;
import com.jd_zpc.view.GetCarListener;
import com.jd_zpc.view.GoodsListListener;
import com.jd_zpc.view.ILoginListener;
import com.jd_zpc.view.IRegisterListener;
import com.jd_zpc.view.OrderCreateListener;
import com.jd_zpc.view.SearchListener;
import com.jd_zpc.view.UpLoadListener;
import com.jd_zpc.view.UserInfoListener;

/**
 * Created by DELL on 2017/11/3.
 */

public class Prestener {
    //登录
    ILoginListener iLoginListener;
    private LoginModel loginModel;
    //注册
    IRegisterListener iRegisterListener;
    private RegisterModel registerModel;
    //主页
    FirstListener firstListener;
    private FirstModel firstModel;
    //详情
    DetailListener detailListener;
    private DetailModel detailModel;
    //一级分类
    FenleiListener fenleiListener;
    private FenleiModel fenleiModel;
    //商品列表
    GoodsListListener goodsListListener;
    private GoodsModel goodsModel;
    private AddCarModel addCarModel;
    //查询购物车
    GetCarListener getCarListener;
    private GetCarMmodel getCarMmodel;
    private DelCarModel delCarModel;
    //创建订单
    OrderCreateListener orderCreateListener;
    private OrderCreateModel orderCreateModel;
    //新建收货地址
    Add_addressListener add_addressListener;
    private AddAddressModel addAddressModel;
    //获取收货地址列表
    AddressListener addressListener;
    private AddressModel addressModel;
    //搜索
    SearchListener searchListener;
    private SearchModel searchModel;
    //上传头像
    UpLoadListener upLoadListener;
    private UpLoadModel upLoadModel;
    //获取用户信息
    UserInfoListener userInfoListener;
    private UserInfoModel userInfoModel;

    public Prestener(UserInfoListener userInfoListener) {
        this.userInfoListener = userInfoListener;
        userInfoModel = new UserInfoModel();
    }

    public Prestener(UpLoadListener upLoadListener) {
        this.upLoadListener = upLoadListener;
        upLoadModel = new UpLoadModel();
    }

    public Prestener(SearchListener searchListener) {
        this.searchListener = searchListener;
        searchModel = new SearchModel();
    }

    public Prestener(AddressListener addressListener) {
        this.addressListener = addressListener;
        addressModel = new AddressModel();
    }

    public Prestener(Add_addressListener add_addressListener) {
        this.add_addressListener = add_addressListener;
        addAddressModel = new AddAddressModel();
    }

    public Prestener(OrderCreateListener orderCreateListener) {
        this.orderCreateListener = orderCreateListener;
        orderCreateModel = new OrderCreateModel();
    }

    public Prestener(GetCarListener getCarListener) {
        this.getCarListener = getCarListener;
        getCarMmodel = new GetCarMmodel();
        delCarModel = new DelCarModel();
    }

    public Prestener(GoodsListListener goodsListListener) {
        this.goodsListListener = goodsListListener;
        goodsModel = new GoodsModel();
    }

    public Prestener(FenleiListener fenleiListener) {
        this.fenleiListener = fenleiListener;
        fenleiModel = new FenleiModel();
    }

    public Prestener(DetailListener detailListener) {
        this.detailListener = detailListener;
        detailModel = new DetailModel();
        addCarModel = new AddCarModel();
    }

    public Prestener(FirstListener firstListener) {
        this.firstListener = firstListener;
        firstModel = new FirstModel();
    }

    public Prestener(ILoginListener iLoginListener) {
        this.iLoginListener = iLoginListener;
        loginModel = new LoginModel();
    }

    public Prestener(IRegisterListener iRegisterListener) {
        this.iRegisterListener = iRegisterListener;
        registerModel = new RegisterModel();
    }

    //登录
    public void login(String name, String password) {
        loginModel.login(name, password, new OnNetListener() {
            @Override
            public void onSuccess(BaseBean baseBean) {
                Users users = (Users) baseBean;
                iLoginListener.login(users);
            }
        });
    }

    //注册
    public void register(String name, String password) {
        registerModel.register(name, password, new OnNetListener() {
            @Override
            public void onSuccess(BaseBean baseBean) {
                iRegisterListener.register(baseBean);
            }
        });
    }

    //首页
    public void getData() {
        firstModel.getData(new OnNetListener() {
            @Override
            public void onSuccess(BaseBean baseBean) {
                Databean databean = (Databean) baseBean;
                firstListener.showData(databean);
            }
        });
    }

    //详情
    public void getDetail(int pid) {
        detailModel.getDrtail(pid, new OnNetListener() {
            @Override
            public void onSuccess(BaseBean baseBean) {
                DetailBean detailBean = (DetailBean) baseBean;
                detailListener.setDetail(detailBean);
            }
        });
    }

    //一级分类
    public void getProduct() {
        fenleiModel.getProduct(new OnNetListener() {
            @Override
            public void onSuccess(BaseBean baseBean) {
                FenleiBean fenleiBean = (FenleiBean) baseBean;
                fenleiListener.setProduct(fenleiBean);
            }
        });
    }
    //二级分类
    public void getProduct2(int cid) {
        fenleiModel.getProduct2(cid, new OnNetListener() {
            @Override
            public void onSuccess(BaseBean baseBean) {
                ParentBean parentBean = (ParentBean) baseBean;
                fenleiListener.setProduct2(parentBean);
            }
        });
    }
    //商品列表
    public void getGoodsList(int pscid){
        goodsModel.getGoods(pscid, new OnNetListener() {
            @Override
            public void onSuccess(BaseBean baseBean) {
                GoodsBean goodsBean = (GoodsBean) baseBean;
                goodsListListener.setList(goodsBean);
            }
        });
    }
    //加入购物车
    public void addCar(int uid, int pid, String token){
        addCarModel.addCar(uid, pid, token, new OnNetListener() {
            @Override
            public void onSuccess(BaseBean baseBean) {
                detailListener.setAddCar(baseBean);
            }
        });
    }
    //查询购物车
    public void getCar(int uid ,String token){
        getCarMmodel.getCar(uid, token, new OnNetListener() {
            @Override
            public void onSuccess(BaseBean baseBean) {
                CarBean carBean = (CarBean) baseBean;
                getCarListener.setCar(carBean);
            }
        });
    }
    //删除购物车
    public void delCar(int uid, int pid, String token){

        delCarModel.delCar(uid, pid, token, new OnNetListener() {
            @Override
            public void onSuccess(BaseBean baseBean) {
                getCarListener.setdelCar(baseBean);
            }
        });
    }
    //创建订单
    public void orderCreate(int uid,String price,String token){
        orderCreateModel.orderCreate(uid, price, token, new OnNetListener() {
            @Override
            public void onSuccess(BaseBean baseBean) {
              OrderCreate orderCreate = (OrderCreate) baseBean;
              orderCreateListener.setOrderCreate(orderCreate);
            }
        });
    }
    //新建收货地址
    public void addAddress(int uid,String name,String mobile,String addr,String token){
        addAddressModel.addAddress(uid, addr, name, mobile, token, new OnNetListener() {
            @Override
            public void onSuccess(BaseBean baseBean) {
               add_addressListener.addAddress(baseBean);
            }
        });
    }
    //获取收货地址列表
    public void getAddress(int uid,String token){
        addressModel.getAddress(uid, token, new OnNetListener() {
            @Override
            public void onSuccess(BaseBean baseBean) {
                AddressBean addressBean = (AddressBean) baseBean;
                addressListener.getAddress(addressBean);
            }
        });
    }
    //搜索
    public void getSearch(String keywords){
        searchModel.getSearch(keywords, new OnNetListener() {
            @Override
            public void onSuccess(BaseBean baseBean) {
                SearchBean searchBean = (SearchBean) baseBean;
                searchListener.setSearch(searchBean);
            }
        });
    }
    //上传图片
    public void upimage(int uid,String token){
        upLoadModel.upLoadimage(uid, token, new OnNetListener() {
            @Override
            public void onSuccess(BaseBean baseBean) {
                upLoadListener.upload(baseBean);
            }
        });
    }
    //获取用户信息
    public void getUserInfo(int uid,String token){
        userInfoModel.getUserInfo(uid, token, new OnNetListener() {
            @Override
            public void onSuccess(BaseBean baseBean) {
                Users users = (Users) baseBean;
                userInfoListener.userInfo(users);
            }
        });
    }
}
