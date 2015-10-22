package com.wizarpos.payment.padui.setting.basicalinfo;

import com.wizarpos.payment.padui.R;
import com.wizarpos.payment.padui.common.BaseViewActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
/**
 * 
 * @author hong
 *	设置验证码
 */
public class AuthCodeActivity extends BaseViewActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initView();
	}
	private void initView(){
		setMainView(R.layout.activity_auth_code);
		setTitleText("请输入验证码");
		findViewById(R.id.btn_auth_code_back).setOnClickListener(this);
		findViewById(R.id.btn_auth_code_confirm).setOnClickListener(this);
		findViewById(R.id.btn_auth_code_next_page).setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.btn_auth_code_back://返回
			this.finish();
			break;
		case R.id.btn_auth_code_confirm://确定
			
			break;
		case R.id.btn_auth_code_next_page://下一个
			startActivity(new Intent(AuthCodeActivity.this,OtherTransactionActivity.class));
			this.finish();
			break;
		default:
			break;
		}
	}
}
