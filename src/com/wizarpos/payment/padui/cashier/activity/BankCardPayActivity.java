package com.wizarpos.payment.padui.cashier.activity;

import com.wizarpos.payment.padui.R;

import android.os.Bundle;
import android.view.View;
/**
 * 
 * @author hong
 * 银行卡支付
 *
 */
public class BankCardPayActivity extends TransactionActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initView();
	}
	private void initView(){
		setMainView(R.layout.activity_bank_pay);
		setTitle("银行卡");
		int btns[] = {R.id.b_back_btn,R.id.b_pay_btn,R.id.pay_ticket};
		setOnClickListenerByIds(btns, this);
	}
	
	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.b_back_btn:

			break;
		case R.id.b_pay_btn:

			break;
		case R.id.pay_ticket:

			break;
		default:
			break;
		}
	}
}
