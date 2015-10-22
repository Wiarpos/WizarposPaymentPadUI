package com.wizarpos.payment.padui.statics;

import com.wizarpos.payment.padui.R;
import com.wizarpos.payment.padui.common.BaseViewActivity;
import com.wizarpos.payment.padui.view.fragment.InputPadFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * 
 * @author hong 
 * 统计查询主界面
 *
 */
public class StaticsMainMenuActivity extends BaseViewActivity {
	private InputPadFragment inputPadFragment;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initView();
	}

	private void initView() {
		setMainView(R.layout.activity_statics_main_menu);
		setTitleText("统计查询");
		findViewById(R.id.btn_static_transaction_detail).setOnClickListener(this);
		findViewById(R.id.btn_static_transaction_sum).setOnClickListener(this);
		findViewById(R.id.btn_transaction_cancle).setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.btn_static_transaction_detail://交易明细
			startActivity(new Intent(StaticsMainMenuActivity.this,TransactionDetailActivity.class));
			break;
		case R.id.btn_static_transaction_sum://交易查询
			startActivity(new Intent(StaticsMainMenuActivity.this,TransactionGathingActivity.class));
			break;
		case R.id.btn_transaction_cancle://交易撤销
			startActivity(new Intent(StaticsMainMenuActivity.this,TransactionCancleActivity.class));
			break;

		default:
			break;
		}
	}
}
