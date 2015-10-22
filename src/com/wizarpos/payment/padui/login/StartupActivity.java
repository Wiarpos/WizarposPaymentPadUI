package com.wizarpos.payment.padui.login;

import com.wizarpos.atool.net.volley2.Response;
import com.wizarpos.pay.common.Constants;
import com.wizarpos.pay.common.DialogHelper;
import com.wizarpos.pay.common.DialogHelper.DialogCallback;
import com.wizarpos.pay.common.base.BasePresenter.ResultListener;
import com.wizarpos.pay.common.device.DeviceManager;
import com.wizarpos.pay.db.AppStateDef;
import com.wizarpos.pay.db.AppStateManager;
import com.wizarpos.pay.login.presenter.StartupPresenter;
import com.wizarpos.payment.padui.R;
import com.wizarpos.payment.padui.cashier.activity.MainMenuActivity;
import com.wizarpos.payment.padui.common.BaseViewActivity;

import android.content.Intent;
import android.os.Bundle;

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
		presenter = new StartupPresenter(this);
		go();//测试，其它厂商没有证书
//		init();
	}

	private void init() {
		presenter.getPubCertificate(new ResultListener() {// 取证书

			@Override
			public void onSuccess(Response response) {
				go();
			}

			@Override
			public void onFaild(Response response) {
				DialogHelper.showDialog(StartupActivity.this, response.msg, new DialogCallback() {
					
					public void callback() {
						StartupActivity.this.finish();
						
					}
				});
			}
		});
		
	}


	private void go() {
		if (Constants.TRUE.equals(AppStateManager.getState(AppStateDef.isLogin, Constants.FALSE))) {
			startActivity(new Intent(this, MainMenuActivity.class));
		} else {
			startActivity(new Intent(this, LoginActivity.class));
		}
		this.finish();
	}
}
