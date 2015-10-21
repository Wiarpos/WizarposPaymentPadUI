package com.wizarpos.payment.padui.manage;

import com.wizarpos.payment.padui.R;
import com.wizarpos.payment.padui.common.BaseViewActivity;

import android.os.Bundle;
/**
 * 
 * @author hong
 *	操作员管理界面
 */
public class OperatorManageActivity extends BaseViewActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initView();
	}
	private void initView(){
		setMainView(R.layout.activity_operator_manage);
		setTitleText("操作员管理");
	}
}
