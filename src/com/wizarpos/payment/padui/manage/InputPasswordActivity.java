package com.wizarpos.payment.padui.manage;

import com.wizarpos.pay.common.utils.UIHelper;
import com.wizarpos.pay.setting.presenter.AppConfiger;
import com.wizarpos.payment.padui.R;
import com.wizarpos.payment.padui.common.BaseViewActivity;
import com.wizarpos.payment.padui.setting.SettingMainMenActivity;
import com.wizarpos.payment.padui.view.fragment.InputPadFragment;
import com.wizarpos.payment.padui.view.fragment.InputPadFragment.InputType;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
/**
 * 
 * @author hong
 *	输入密码
 */
public class InputPasswordActivity extends BaseViewActivity {
	private InputPadFragment inputPadFragment;
	private EditText passwordEt;
	private AppConfiger present;
	private String password = null;
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			initView();
		}
		
	 private void initView(){
		 present = new AppConfiger(this);
		 setMainView(R.layout.activity_input_password);
		 setTitleText("设置安全密码");
		 inputPadFragment = InputPadFragment.newInstance(InputPadFragment.KEYBOARDTYPE_SIMPLE);
		 passwordEt = (EditText)findViewById(R.id.password);
		 getSupportFragmentManager().beginTransaction().replace(R.id.flInputPad, inputPadFragment).commit();
		 inputPadFragment.setEditView(passwordEt,InputType.TYPE_INPUT_NORMAL);
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
			password = passwordEt.getText().toString();
			if (present.checkSecurityPassword(password)) {
					Intent intent = new Intent(this, SettingMainMenActivity.class); //跳转到交易撤销界面
					startActivity(intent);
					finish();
			} else {
				UIHelper.ToastMessage(this, "安全密码错误", Toast.LENGTH_SHORT);
			}
			break;

		default:
			break;
		}
	}
}
