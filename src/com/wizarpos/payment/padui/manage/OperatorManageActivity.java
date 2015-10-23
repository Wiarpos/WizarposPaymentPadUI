package com.wizarpos.payment.padui.manage;

import com.wizarpos.payment.padui.R;
import com.wizarpos.payment.padui.common.BaseViewActivity;

import android.os.Bundle;
import android.view.View;
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
		findViewById(R.id.btn_prepage).setOnClickListener(this);
		findViewById(R.id.btn_nextpage).setOnClickListener(this);
		findViewById(R.id.btn_add_cashier).setOnClickListener(this);
		findViewById(R.id.btn_modify_cashierinfo).setOnClickListener(this);
		findViewById(R.id.btn_del_cashierinfo).setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.btn_prepage://上一页
			
			break;
		case R.id.btn_nextpage://下一页
			
			break;
		case R.id.btn_add_cashier://新增
			
			break;
		case R.id.btn_modify_cashierinfo://修改
			
			break;
		case R.id.btn_del_cashierinfo://删除
			
			break;

		default:
			break;
		}
	}
}
