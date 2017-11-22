package com.jd_zpc.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.jd_zpc.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class WXZhifuActivity extends BaseActivity implements View.OnClickListener {

    /**
     * 确认支付
     */
    private Button mBtWxPay;
    private IWXAPI api;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wxzhifu);
        //  商户APP工程中引入微信JAR包，调用API前，需要先向微信注册您的APPID
        api = WXAPIFactory.createWXAPI(this, null);
        // 将该app注册到微信
        api.registerApp("wx8d99aede85c725a5");//wx8d99aede85c725a5
        initView();

    }

    private void initView() {
        mBtWxPay = (Button) findViewById(R.id.bt_wxPay);
        mBtWxPay.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.bt_wxPay:
                //微信支付 专入订单号
                wechatPay("27839139829498");
                break;
        }
    }
    //微信支付
    private void wechatPay(String order_sn) {
        String url = "http://www.55chai.com/morder/app_pay?order_sn=" + order_sn;//获取微信支付参数的服务端地址
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(getApplicationContext(), url, new TextHttpResponseHandler() {

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Toast.makeText(getApplicationContext(), "订单生成失败", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Log.i("xml", responseString + "");
                try {
                    JSONObject jsonObject = new JSONObject(responseString);
                    if (jsonObject.getInt("code") == 200) {//正常获取了订单所需参数
                        JSONObject paramsJson = jsonObject.getJSONObject("data");
                        PayReq request = new PayReq();
                        request.appId = paramsJson.getString("appid");
                        request.partnerId = paramsJson.getString("partnerid");
                        request.prepayId = paramsJson.getString("prepayid");
                        request.packageValue = paramsJson.getString("package");
                        request.nonceStr = paramsJson.getString("noncestr");
                        request.timeStamp = paramsJson.getLong("timestamp") + "";
                        request.sign = paramsJson.getString("sign");
                        api.sendReq(request);
                    } else {
                        Toast.makeText(getApplicationContext(), "获取订单信息失败", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(), "生成支付失败", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });
    }

}
