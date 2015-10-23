package com.wizarpos.payment.padui.setting.basicalinfo;

import com.wizarpos.payment.padui.R;
import com.wizarpos.payment.padui.common.BaseViewActivity;

import android.os.Bundle;
import android.view.View;
/**
 * 
 * @author hong
 * 设置基本信息
 *
 */
public class BasicalInfoActivity extends BaseViewActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initView();
	}
	private void initView(){
		setMainView(R.layout.activity_basical_info_setting);
		setTitleText("基本信息设置");
		findViewById(R.id.btn_basical_info_back).setOnClickListener(this);
		findViewById(R.id.btn_basical_info_confirm).setOnClickListener(this);
		findViewById(R.id.btn_basical_info_next_page).setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.btn_basical_info_back:
			this.finish();
			break;
		case R.id.btn_basical_info_confirm:
			
			break;
		case R.id.btn_basical_info_next_page:
			
			break;

		default:
			break;
		}
	}
}
