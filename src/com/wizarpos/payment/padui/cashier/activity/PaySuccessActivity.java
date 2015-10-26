package com.wizarpos.payment.padui.cashier.activity;

import com.wizarpos.pay.common.utils.Calculater;
import com.wizarpos.payment.padui.R;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
/**
 * 
 * @author hong
 * 支付成功
 *
 */
public class PaySuccessActivity extends TransactionActivity {
	private TextView cardNumTv;//显示卡号
	private TextView inputAmountTv;//显示收银金额
	private TextView reduceAnountTv;//显示扣减金额
	private TextView paymentAmountTv;//显示支付金额
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initView();
		initData();
	}
	private void initView(){
		setMainView(R.layout.activity_payment_success);
		setTitleText("现金|券发行"); 
		cardNumTv = (TextView)findViewById(R.id.paysuccess_card_no);
		inputAmountTv = (TextView)findViewById(R.id.paysuccess_input_amount);
		reduceAnountTv = (TextView)findViewById(R.id.paysuccess_reduce_amount);
		paymentAmountTv = (TextView)findViewById(R.id.paysuccess_pay_amount);
	}
	private void initData(){
		Intent intent =getIntent();
		inputAmountTv.setText(Calculater.formotFen(intent.getStringExtra("initAmount")));
		reduceAnountTv.setText(Calculater.formotFen(intent.getStringExtra("reduceAmount")));
		paymentAmountTv.setText(Calculater.formotFen(intent.getStringExtra("realAmount")));
	}
}
