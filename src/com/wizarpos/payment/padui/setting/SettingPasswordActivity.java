package com.wizarpos.payment.padui.setting;

import com.wizarpos.payment.padui.R;
import com.wizarpos.payment.padui.common.BaseViewActivity;
import com.wizarpos.payment.padui.view.fragment.InputPadFragment;

import android.os.Bundle;
import android.view.View;
/**
 * 
 * @author hong
 * 设置安全密码
 *
 */
public class SettingPasswordActivity extends BaseViewActivity {
	private InputPadFragment inputPadFragment;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initView();
	}
	private void initView(){
		setMainView(R.layout.activity_setting_password);
		setTitleText("设置安全密码");
		findViewById(R.id.btn_setting_password_back).setOnClickListener(this);
		findViewById(R.id.btn_setting_password_confirm).setOnClickListener(this);
		inputPadFragment = new InputPadFragment();
		getSupportFragmentManager().beginTransaction().replace(R.id.flInputPad, inputPadFragment).commit();
	}
	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.btn_setting_password_back://返回
			this.finish();
			break;
		case R.id.btn_setting_password_confirm://确定
			
			break;

		default:
			break;
		}
	}
}
