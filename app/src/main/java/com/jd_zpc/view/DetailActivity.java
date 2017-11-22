package com.jd_zpc.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jd_zpc.R;
import com.jd_zpc.bean.BaseBean;
import com.jd_zpc.bean.DetailBean;
import com.jd_zpc.prestenter.Prestener;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoaderInterface;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends BaseActivity implements DetailListener, View.OnClickListener {

    private TextView mTvDetailTitle;
    private TextView mTvDetailSubhead;
    private TextView mTvDetailPrice;
    private Banner mBanner;
    /**
     * 购物车
     */
    private TextView mTvCar;
    /**
     * 加入购物车
     */
    private Button mBtCar;
    private Prestener prestener;
    /**
     * 分享
     */
    private TextView mTvDetailShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initView();
    }

    private void initView() {
        mTvDetailTitle = (TextView) findViewById(R.id.tv_detail_title);
        mTvDetailSubhead = (TextView) findViewById(R.id.tv_detail_subhead);
        mTvDetailPrice = (TextView) findViewById(R.id.tv_detail_price);
        Intent intent = getIntent();
        int pid = intent.getIntExtra("pid", 0);
        prestener = new Prestener(this);
        prestener.getDetail(pid);
        mBanner = (Banner) findViewById(R.id.banner);
        mTvCar = (TextView) findViewById(R.id.tv_car);
        mBtCar = (Button) findViewById(R.id.bt_car);
        mTvDetailShare = (TextView) findViewById(R.id.tv_detail_share);
        mTvDetailShare.setOnClickListener(this);
    }

    @Override
    public void setDetail(DetailBean detail) {
        String images = detail.getData().getImages();
        String title = detail.getData().getTitle();
        String subhead = detail.getData().getSubhead();
        double price = detail.getData().getPrice();
        final int pid = detail.getData().getPid();
        String[] split = images.split("\\|");
        List<String> Imglist = new ArrayList<>();
        for (int i = 0; i < split.length - 1; i++) {
            String img = split[i];
            Imglist.add(img);
        }
        mBanner.setImageLoader(new Glider());
        mBanner.setDelayTime(2000);
        mBanner.setImages(Imglist);
        mBanner.setBannerStyle(BannerConfig.NUM_INDICATOR);
        mBanner.setIndicatorGravity(BannerConfig.RIGHT);
        mBanner.start();

        mTvDetailPrice.setText("¥:" + price + "");
        mTvDetailTitle.setText(title);
        mTvDetailSubhead.setText(subhead);
        SharedPreferences user = getSharedPreferences("user", MODE_PRIVATE);
        final int uid = user.getInt("uid", 0);
        final String token = user.getString("token", null);

        mBtCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prestener.addCar(uid, pid, token);
            }
        });

    }

    @Override
    public void setAddCar(BaseBean baseBean) {
        Toast.makeText(DetailActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.tv_detail_share:
                /*UMWeb web = new UMWeb("http://www.baidu.com");
                web.setTitle("百度");
                web.setDescription("百度一下,你就知道!");
                new ShareAction(DetailActivity.this)
                        .setPlatform(SHARE_MEDIA.QQ)//传入平台
                        .withText("hello")//分享内容
                        .withMedia(web)
                        .setCallback(shareListener)//回调监听器
                        .share();*/
                ShareWeb("");
                break;
        }
    }

    private void ShareWeb(String thumb_img) {
        UMImage thumb = new UMImage(this, thumb_img);
        UMWeb web = new UMWeb("http://www.baidu.com");
        web.setThumb(thumb);
        web.setDescription("百度一下");
        web.setTitle("百度");
        new ShareAction(this).withMedia(web)
                .setPlatform(SHARE_MEDIA.WEIXIN).setCallback(shareListener).share();
    }

    private class Glider implements ImageLoaderInterface {
        @Override
        public void displayImage(Context context, Object path, View imageView) {
            Glide.with(context).load(path).into((ImageView) imageView);
        }

        @Override
        public View createImageView(Context context) {
            return null;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    private UMShareListener shareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Toast.makeText(DetailActivity.this, "成功了", Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(DetailActivity.this, "失败" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(DetailActivity.this, "取消了", Toast.LENGTH_LONG).show();

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        UMShareAPI.get(this).release();
    }
}
