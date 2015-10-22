package com.wizarpos.payment.padui.setting;

import com.wizarpos.payment.padui.R;
import com.wizarpos.payment.padui.common.BaseViewActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
/**
 * 
 * @author hong
 * 设置主界面
 */
public class SettingMainMenActivity extends BaseViewActivity {
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			initView();
		}
	private void initView(){
		setMainView(R.layout.activity_setting_main_menu);
		setTitleText("设置");
		findViewById(R.id.btn_password).setOnClickListener(this);
		findViewById(R.id.btn_params_setting).setOnClickListener(this);
		findViewById(R.id.btn_offline_sync).setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.btn_password: //修改密码
			startActivity(new Intent(SettingMainMenActivity.this,SettingPasswordActivity.class));
			break;
			
		case R.id.btn_params_setting: //基本参数设置
			startActivity(new Intent(SettingMainMenActivity.this,SettingParamsActivity.class));
			break;
			
		case R.id.btn_offline_sync://离线同步
			startActivity(new Intent(SettingMainMenActivity.this,OfflineSyncActivity.class));
			break;

		default:
			break;
		}
	}
}
