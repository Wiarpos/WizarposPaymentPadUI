package com.wizarpos.payment.padui.cashier.activity.paymentterm;

import com.wizarpos.payment.padui.R;
import com.wizarpos.payment.padui.cashier.activity.TransactionActivity;
import com.wizarpos.payment.padui.view.fragment.ScanQrcodeFragment;

import android.os.Bundle;
import android.view.View;
/**
 * 
 * @author hong
 * QQ钱包支付
 *
 */
public class TenpayActivity extends TransactionActivity {
	private ScanQrcodeFragment scanQrcodeFragment;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initView();
	}
	
	private void initView(){
		setMainView(R.layout.activity_tencent_pay);
		setTitleText("QQ钱包支付");
		scanQrcodeFragment = new ScanQrcodeFragment();
		getSupportFragmentManager().beginTransaction().replace(R.id.fl_BAT_Fragment, scanQrcodeFragment).commit();
		setOnClickListenerById(R.id.wp_btn_check_order_state, this);
	}
	
	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.wp_btn_check_order_state://检查订单状态
			
			break;
		default:
			break;
		}
	}
}
