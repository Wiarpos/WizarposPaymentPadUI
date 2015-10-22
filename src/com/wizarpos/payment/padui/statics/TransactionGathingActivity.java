package com.wizarpos.payment.padui.statics;

import com.wizarpos.payment.padui.R;
import com.wizarpos.payment.padui.common.BaseViewActivity;

import android.os.Bundle;
import android.view.View;

/**
 * 
 * @author hong 汇总查询
 *
 */
public class TransactionGathingActivity extends BaseViewActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initView();
	}

	private void initView() {
		setMainView(R.layout.activity_transaction_sum);
		setTitleText("交易汇总查询");
		findViewById(R.id.btn_transaction_sum_back).setOnClickListener(this);
		findViewById(R.id.btn_transaction_sum_print).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.btn_transaction_sum_back:
			this.finish();
			break;
		case R.id.btn_transaction_sum_print:
			
			break;

		default:
			break;
		}
	}
}
