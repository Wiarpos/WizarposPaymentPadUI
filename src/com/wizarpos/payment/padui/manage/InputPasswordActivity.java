package com.wizarpos.payment.padui.manage;

import com.wizarpos.payment.padui.R;
import com.wizarpos.payment.padui.common.BaseViewActivity;
import com.wizarpos.payment.padui.setting.SettingMainMenActivity;
import com.wizarpos.payment.padui.view.fragment.InputPadFragment;
import com.wizarpos.payment.padui.view.fragment.InputPadFragment.InputType;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
/**
 * 
 * @author hong
 *	输入密码
 */
public class InputPasswordActivity extends BaseViewActivity {
	private InputPadFragment inputPadFragment;
	private EditText passwordEt;
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			initView();
		}
		
	 private void initView(){
		 setMainView(R.layout.activity_input_password);
		 inputPadFragment = new InputPadFragment();
		 getSupportFragmentManager().beginTransaction().replace(R.id.flInputPad, inputPadFragment).commit();
		 passwordEt = (EditText)findViewById(R.id.password);
		 inputPadFragment.setEditView(passwordEt, InputType.TYPE_INPUT_PWD);
		 findViewById(R.id.btn_back_password).setOnClickListener(this);
		 findViewById(R.id.btn_confirm_password).setOnClickListener(this);
	 }
	 @Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.btn_back_password:
			this.finish();
			break;
			
		case R.id.btn_confirm_password:
			startActivity(new Intent(InputPasswordActivity.this,SettingMainMenActivity.class));
			this.finish();
			break;

		default:
			break;
		}
	}
}
