package com.wizarpos.payment.padui.login;

import com.wizarpos.pay.db.AppConfigDef;
import com.wizarpos.pay.db.AppConfigHelper;
import com.wizarpos.pay.login.presenter.StartupPresenter;
import com.wizarpos.payment.padui.R;
import com.wizarpos.payment.padui.common.BaseViewActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * 入口activity 闪屏
 * 
 * @author hong
 */
public class StartupActivity extends BaseViewActivity {

	private StartupPresenter presenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_startup);
//		DeviceManager.getInstance().getDeviceType();
//		presenter = new StartupPresenter(this);
		init();
	}

	private void init() {
		go();
		Log.d("sn", AppConfigHelper.getConfig(AppConfigDef.sn));//显示sn wu
//			presenter.getPubCertificate(new ResultListener() {// 取证书
//
//						@Override
//						public void onSuccess(Response response) {
//							go();
//						}
//
//						@Override
//						public void onFaild(Response response) {
//							DialogHelper.showDialog(StartupActivity.this, response.msg,new DialogCallback() {
//								
//								@Override
//								public void callback() {
//									StartupActivity.this.finish();
//								}
//							});
//						}
//					});
	}


	private void go() {
		startActivity(new Intent(this, LoginActivity.class));
//		if (Constants.TRUE.equals(AppStateManager.getState(AppStateDef.isLogin, Constants.FALSE))) {
//			startActivity(new Intent(this, MainFragmentActivity2.class));
//		} else {
//			startActivity(new Intent(this, LoginActivity.class));
//		}
		this.finish();
	}
}
