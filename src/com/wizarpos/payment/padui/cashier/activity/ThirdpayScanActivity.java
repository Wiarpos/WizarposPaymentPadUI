package com.wizarpos.payment.padui.cashier.activity;

import com.wizarpos.pay.cashier.presenter.MixTransactionFinishReceiver;
import com.wizarpos.pay.common.device.ScanFragment;
import com.wizarpos.pay.common.device.ScanFragment.DisplayListener;
import com.wizarpos.payment.padui.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
/**
 * 
 * @author hong
 *
 */
public abstract class ThirdpayScanActivity extends BATActivity implements DisplayListener{
	public static final String IS_SUCCESS = "isSuccess";
	public static final int TO_RESULT = 1;
	public ScanFragment fragment;
	public LinearLayout showMicroMenu;
	public LinearLayout showNativeMenu;
	public TextView nativeRemind;
	public Button printQRCode;
	public ImageView ivBarcode;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initWizarCamera(savedInstanceState);
	}
	private void initWizarCamera(Bundle savedInstanceState) {
		setMainView(R.layout.activity_alipay);
		int[] btns = { R.id.scan_qrcode_pay, R.id.scanned_qrcode_pay, R.id.wp_btn_change_qrcode,
				R.id.print_qrcode, R.id.wp_btn_check_order_state };
		fragment = new ScanFragment();
		fragment.setListener(this);
		showMicroMenu = (LinearLayout) findViewById(R.id.show_micro_menu);
		showNativeMenu = (LinearLayout) findViewById(R.id.show_native_menu);
		nativeRemind = (TextView) findViewById(R.id.native_remind);
		printQRCode = (Button) findViewById(R.id.print_qrcode);
		nativeRemind.setVisibility(View.GONE);
		printQRCode.setVisibility(View.GONE);
		ivBarcode = (ImageView) findViewById(R.id.qr_code);
		getSupportFragmentManager().beginTransaction().add(R.id.fl_BAT_Fragment, fragment).commit();
		setOnClickListenerByIds(btns, this);
	}
	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
		super.onActivityResult(requestCode, resultCode, intent);
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
	}
	/**
	 * 分发交易结果
	 * 
	 * @param isMixTransaction
	 * @param resultIntent
	 */
	protected void deliverResult(boolean isMixTransaction, Intent resultIntent) {
		if (isMixTransaction) {
			resultIntent.setAction(MixTransactionFinishReceiver.ACTION);
			ThirdpayScanActivity.this.sendBroadcast(resultIntent);
			ThirdpayScanActivity.this.finish();
		} else {
			toTransactionSuccess(ThirdpayScanActivity.this, resultIntent);
			ThirdpayScanActivity.this.finish();
		}
	}

	protected void setTitleTextIsMix(boolean isMixTransaction, String titleText) {
		setTitleText(isMixTransaction ? getResources().getString(R.string.mix_pay) + titleText : titleText);
	}
}
