package com.wizarpos.payment.padui.cashier.activity;

import com.wizarpos.payment.padui.R;
import com.wizarpos.payment.padui.common.BaseViewActivity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
/**
 * 
 * @author hong
 * 现金支付
 *
 */
public class CashPayActivity extends BaseViewActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initView();
	}
	private void initView(){
		setMainView(R.layout.activity_cash_pay);
		setTitle("现金");
		int btns[] = {R.id.cp_btn_back,R.id.cp_btn_pay,R.id.pay_ticket};
		setOnClickListenerByIds(btns, this);
	}
	
	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.cp_btn_back:

			break;
		case R.id.cp_btn_pay:

			break;
		case R.id.pay_ticket:

			break;
		default:
			break;
		}
	}
}
