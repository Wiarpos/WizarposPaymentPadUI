package com.wizarpos.payment.padui.setting.basicalinfo;

import com.wizarpos.payment.padui.R;
import com.wizarpos.payment.padui.common.BaseViewActivity;

import android.os.Bundle;
import android.view.View;

/**
 * 
 * @author hong 其他交易设置
 *
 */
public class OtherTransactionActivity extends BaseViewActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initView();
	}

	private void initView() {
		setMainView(R.layout.activity_other_payment_setting);
		setTitleText("其他交易设置");
		findViewById(R.id.btn_other_payment_setting_back).setOnClickListener(this);
		;
		findViewById(R.id.btn_other_payment_setting_confirm).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.btn_other_payment_setting_back://返回
			this.finish();
			break;
		case R.id.btn_other_payment_setting_confirm://确定
			
			break;

		default:
			break;
		}
	}
}
