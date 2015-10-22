package com.wizarpos.payment.padui.statics;

import com.wizarpos.payment.padui.R;
import com.wizarpos.payment.padui.common.BaseViewActivity;
import com.wizarpos.payment.padui.view.fragment.InputPadFragment;

import android.os.Bundle;
import android.view.View;

/**
 * 
 * @author hong 交易撤销
 *
 */
public class TransactionCancleActivity extends BaseViewActivity {
	private InputPadFragment inputPadFragment;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initView();
	}

	private void initView() {
		setMainView(R.layout.activity_transaction_cancle);
		setTitleText("请输入交易流水号");
		findViewById(R.id.btn_back_transaction_cancle).setOnClickListener(this);
		findViewById(R.id.btn_confirm_transaction_cancle).setOnClickListener(this);
		inputPadFragment = new InputPadFragment();
		getSupportFragmentManager().beginTransaction().replace(R.id.flInputPad, inputPadFragment).commit();
	}
	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.btn_back_transaction_cancle://返回
			this.finish();
			break;
		case R.id.btn_confirm_transaction_cancle://确定
			
			break;

		default:
			break;
		}
	}
}
