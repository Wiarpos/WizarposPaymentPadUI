package com.wizarpos.payment.padui.login;

import com.wizarpos.payment.padui.R;
import com.wizarpos.payment.padui.cashier.activity.MainMenuActivity;
import com.wizarpos.payment.padui.common.BaseViewActivity;
import com.wizarpos.payment.padui.view.fragment.InputPadFragment;
import com.wizarpos.payment.padui.view.fragment.InputPadFragment.InputType;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * 
 * @author hong 登陆界面
 */
public class LoginActivity extends BaseViewActivity {
	private InputPadFragment inputPadFragment;
	private EditText userNameEt = null;
	private EditText passwordEt = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initView();
	}

	private void initView() {
		setMainView(R.layout.activity_login);
		setTitleText("登陆");
		userNameEt = (EditText) findViewById(R.id.loginName);
		passwordEt = (EditText) findViewById(R.id.loginPassword);
		inputPadFragment = new InputPadFragment();
		getSupportFragmentManager().beginTransaction().replace(R.id.flInputPad, inputPadFragment).commit();
		inputPadFragment.setEditView(userNameEt, InputType.TYPE_INPUT_NORMAL);
		findViewById(R.id.btn_back).setOnClickListener(this);
		findViewById(R.id.btn_confirm).setOnClickListener(this);
		// inputPadFragment.setEditView(passwordEt, InputType.TYPE_INPUT_PWD);
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.btn_back:
			LoginActivity.this.finish();
			break;
		case R.id.btn_confirm:
			startActivity(new Intent(LoginActivity.this, MainMenuActivity.class));
			this.finish();
			break;
		}
	}
}
