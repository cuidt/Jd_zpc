package com.jd_zpc.wxapi;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.jd_zpc.R;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;

/**
 * Created by DELL on 2017/11/17.
 */

public class WXPayEntryActivity extends AppCompatActivity implements IWXAPIEventHandler {
    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {
        if (baseResp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.app_tip);
            builder.setMessage(getString(R.string.pay_result_callback_msg, baseResp.errStr + ";code=" + String.valueOf(baseResp.errCode)));
            builder.show();
        }
    }
}
