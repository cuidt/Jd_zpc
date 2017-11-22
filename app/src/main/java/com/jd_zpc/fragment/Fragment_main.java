package com.jd_zpc.fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.acker.simplezxing.activity.CaptureActivity;
import com.bumptech.glide.Glide;
import com.jd_zpc.R;
import com.jd_zpc.adapter.MyRvMiaoAdapter;
import com.jd_zpc.adapter.MyRvTuijianAdapter;
import com.jd_zpc.bean.Databean;
import com.jd_zpc.prestenter.Prestener;
import com.jd_zpc.view.DetailActivity;
import com.jd_zpc.view.FirstListener;
import com.jd_zpc.view.SearchActivity;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoaderInterface;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

/**
 * Created by DELL on 2017/11/2.
 */

public class Fragment_main extends Fragment implements FirstListener, View.OnClickListener {
    private View view;
    private Banner mBanner;
    private RecyclerView mRvMiao;
    private RecyclerView mRvTuijian;
    private ImageView mSaomiao;
    /**
     * 搜索
     */
    private EditText mSousuo;
    private ImageView mMsg;
    private static final int REQ_CODE_PERMISSION = 0x1111;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        initView(view);

        //startActivityForResult(new Intent(getActivity(), CaptureActivity.class), CaptureActivity.REQ_CODE);

        return view;
    }

    private void initView(View view) {
        mBanner = (Banner) view.findViewById(R.id.banner);
        Prestener prestener = new Prestener(this);
        prestener.getData();
        mRvMiao = (RecyclerView) view.findViewById(R.id.rv_miao);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRvMiao.setLayoutManager(linearLayoutManager);
        mRvTuijian = (RecyclerView) view.findViewById(R.id.rv_tuijian);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        mRvTuijian.setLayoutManager(gridLayoutManager);
        mSaomiao = (ImageView) view.findViewById(R.id.saomiao);
        mSaomiao.setOnClickListener(this);
        mSousuo = (EditText) view.findViewById(R.id.sousuo);
        mMsg = (ImageView) view.findViewById(R.id.msg);
        mSousuo.setOnClickListener(this);
    }

    @Override
    public void showData(Databean databean) {
        //为你推荐数据
        List<Databean.TuijianBean.ListBean> list1 = databean.getTuijian().getList();
        MyRvTuijianAdapter myRvTuijianAdapter = new MyRvTuijianAdapter(getActivity(), list1);
        mRvTuijian.setAdapter(myRvTuijianAdapter);

        myRvTuijianAdapter.setOnItemListener(new MyRvTuijianAdapter.OnItemListener() {
            @Override
            public void onItemClick(int pid, int position) {
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra("pid", pid);
                startActivity(intent);
                Toast.makeText(getActivity(), pid + "", Toast.LENGTH_SHORT).show();
            }
        });
        //京东秒杀数据
        List<Databean.MiaoshaBean.ListBeanX> list = databean.getMiaosha().getList();
        int size = list.size();
        Log.i("TAG", size + "");
        MyRvMiaoAdapter myRvMiaoAdapter = new MyRvMiaoAdapter(getActivity(), list);
        mRvMiao.setAdapter(myRvMiaoAdapter);
        //banner轮播图片
        List<Databean.DataBean> data = databean.getData();
        List<String> Imglist = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            String icon = data.get(i).getIcon();
            Imglist.add(icon);
        }

        mBanner.setImageLoader(new Glider());
        mBanner.setDelayTime(2000);
        mBanner.setImages(Imglist);
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        mBanner.start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.saomiao:
                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    // Do not have the permission of camera, request it.
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, REQ_CODE_PERMISSION);
                } else {
                    // Have gotten the permission
                    startCaptureActivityForResult();
                }
                break;
            case R.id.sousuo:
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
                break;
        }
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

    private void startCaptureActivityForResult() {
        Intent intent = new Intent(getActivity(), CaptureActivity.class);
        Bundle bundle = new Bundle();
        bundle.putBoolean(CaptureActivity.KEY_NEED_BEEP, CaptureActivity.VALUE_BEEP);
        bundle.putBoolean(CaptureActivity.KEY_NEED_VIBRATION, CaptureActivity.VALUE_VIBRATION);
        bundle.putBoolean(CaptureActivity.KEY_NEED_EXPOSURE, CaptureActivity.VALUE_NO_EXPOSURE);
        bundle.putByte(CaptureActivity.KEY_FLASHLIGHT_MODE, CaptureActivity.VALUE_FLASHLIGHT_OFF);
        bundle.putByte(CaptureActivity.KEY_ORIENTATION_MODE, CaptureActivity.VALUE_ORIENTATION_AUTO);
        bundle.putBoolean(CaptureActivity.KEY_SCAN_AREA_FULL_SCREEN, CaptureActivity.VALUE_SCAN_AREA_FULL_SCREEN);
        bundle.putBoolean(CaptureActivity.KEY_NEED_SCAN_HINT_TEXT, CaptureActivity.VALUE_SCAN_HINT_TEXT);
        intent.putExtra(CaptureActivity.EXTRA_SETTING_BUNDLE, bundle);
        startActivityForResult(intent, CaptureActivity.REQ_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQ_CODE_PERMISSION: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // User agree the permission
                    startCaptureActivityForResult();
                } else {
                    // User disagree the permission
                    Toast.makeText(getActivity(), "You must agree the camera permission request before you use the code scan function", Toast.LENGTH_LONG).show();
                }
            }
            break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case CaptureActivity.REQ_CODE:
                switch (resultCode) {
                    case RESULT_OK:
                        Toast.makeText(getActivity(), "扫描成功", Toast.LENGTH_SHORT).show();
                        break;
                    case RESULT_CANCELED:
                        if (data != null) {
                            Toast.makeText(getActivity(), "扫描", Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
                break;
        }
    }


}
