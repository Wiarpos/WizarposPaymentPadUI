package com.wizarpos.payment.padui.login;

import com.alibaba.fastjson.JSONObject;
import com.wizarpos.atool.net.volley2.Response;
import com.wizarpos.pay.cashier.thrid_app_controller.ThirdAppTransactionController;
import com.wizarpos.pay.cashier.thrid_app_controller.model.ThirdAppLoginResponse;
import com.wizarpos.pay.common.Constants;
import com.wizarpos.pay.common.base.BasePresenter.ResultListener;
import com.wizarpos.pay.common.device.DeviceManager;
import com.wizarpos.pay.common.utils.UIHelper;
import com.wizarpos.pay.db.AppStateDef;
import com.wizarpos.pay.db.AppStateManager;
import com.wizarpos.pay.login.presenter.LoginPresenter2;
import com.wizarpos.pay.login.presenter.LoginPresenter2.LoginPresenterListener;
import com.wizarpos.payment.padui.R;
import com.wizarpos.payment.padui.cashier.activity.MainMenuActivity;
import com.wizarpos.payment.padui.cashier.activity.TransactionActivity;
import com.wizarpos.payment.padui.thirdapp.ThirdAppBroadcastReceiver.ThirdAppListener;
import com.wizarpos.payment.padui.view.fragment.InputPadFragment;
import com.wizarpos.payment.padui.view.fragment.InputPadFragment.InputType;
import com.wizarpos.payment.padui.view.fragment.InputPadFragmentKeyBoard;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

/**
 * 
 * @author hong 登陆界面
 */
public class LoginActivity extends TransactionActivity implements LoginPresenterListener, ThirdAppListener {
	private LoginPresenter2 loginpresenter;
	private InputPadFragmentKeyBoard inputPadFragmentKeyBoard;
	private EditText userNameEt = null;
	private EditText passwordEt = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initThirdAppController();
		initView();
		loginpresenter = new LoginPresenter2(this);
		loginpresenter.handleIntent(null);
		if (thirdAppController.isInservice()) {
			getPubCertificate();
		}
	}

	private void initThirdAppController() {
		thirdAppController = ThirdAppTransactionController.getInstance();
		thirdAppController.setThridAppFinisher(this);
		thirdAppController.parseRequest(getIntent());
	}

	// 获取证书
	private void getPubCertificate() {
		progresser.showProgress();
		DeviceManager.getInstance().getPubCertificate(new ResultListener() {

			@Override
			public void onSuccess(Response response) {
				progresser.showContent();
			}

			@Override
			public void onFaild(Response response) {
				progresser.showContent();
				com.wizarpos.pay.common.DialogHelper.showDialog(LoginActivity.this, response.msg,
						new com.wizarpos.pay.common.DialogHelper.DialogCallback() {
					@Override
					public void callback() {
						back();
					}
				});
			}
		});
	}

	private void initView() {
		setMainView(R.layout.activity_login);
		setTitleText("登陆");
		userNameEt = (EditText) findViewById(R.id.loginName);
		passwordEt = (EditText) findViewById(R.id.loginPassword);
		inputPadFragmentKeyBoard = InputPadFragmentKeyBoard.newInstance(InputPadFragmentKeyBoard.KEYBOARDTYPE_SIMPLE);
		getSupportFragmentManager().beginTransaction().replace(R.id.flInputPad, inputPadFragmentKeyBoard).commit();
		setOnClickListenerById(R.id.btn_back, this);
		setOnClickListenerById(R.id.btn_confirm, this);
		userNameEt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				inputPadFragmentKeyBoard.setEditView(userNameEt, com.wizarpos.payment.padui.view.fragment.InputPadFragmentKeyBoard.InputType.TYPE_INPUT_NORMAL);
			}
		});
		passwordEt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				inputPadFragmentKeyBoard.setEditView(passwordEt, com.wizarpos.payment.padui.view.fragment.InputPadFragmentKeyBoard.InputType.TYPE_INPUT_NORMAL);
			}
		});
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.btn_back:
			back();
			break;
		case R.id.btn_confirm:
			String username = userNameEt.getText().toString();
			String password = passwordEt.getText().toString();
			UIHelper.hideSoftInput(this);
			Log.d("TAG", "username is :" + username + " password is :" + password);
			doLogin("00", "111111");
			break;

		}
	}

	private void doLogin(String username, String password) {
		progresser.showProgress();
		loginpresenter.login(username, password);
	}

	@Override
	public void onLoginSuccess(Response response) {
		if (thirdAppController.isInservice()) {
			thirdAppController.bundleLoginResponse();
			thirdAppController.reset();
		} else {
			startNewActivity(MainMenuActivity.class);
			AppStateManager.setState(AppStateDef.isLogin, Constants.TRUE);
		}

	}

	@Override
	protected void back() {
		try {
			AppStateManager.setState(AppStateDef.isInService, Constants.FALSE);
			ThirdAppLoginResponse response = new ThirdAppLoginResponse();
			response.setCode(1);
			response.setMessage("用户取消");
			Intent intent = new Intent();
			intent.putExtra("response", JSONObject.toJSONString(response));
			setResult(RESULT_CANCELED, intent);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			thirdAppController.reset();
			finish();
		}
	}

	@Override
	public void onLoginFaild(Response response) {
		progresser.showContent();
		com.wizarpos.pay.common.DialogHelper.showDialog(LoginActivity.this, response.msg);
	}

	@Override
	public void onResult(Intent intent) {
		try {
			if (thirdAppController.isInservice()) {
				bundleThridTransactionResponse(intent);
			} else {
				this.finish();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void onDestroy() {
		loginpresenter.onDestory();
		super.onDestroy();
	}
}
