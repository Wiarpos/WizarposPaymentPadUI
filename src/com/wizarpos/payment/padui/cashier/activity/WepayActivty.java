package com.wizarpos.payment.padui.cashier.activity;

import com.wizarpos.payment.padui.R;
import com.wizarpos.payment.padui.view.fragment.ScanQrcodeFragment;
import com.wizarpos.payment.padui.view.fragment.ScannedQrcodeFragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
/**
 * 
 * @author hong
 * 微信支付
 *
 */
public class WepayActivty extends TransactionActivity {
	private TextView lineScanQrcode,lineScannedQrcode;
	private ScanQrcodeFragment scanQrcodeFragment;
	private ScannedQrcodeFragment scannedQrcodeFragment;
	private Button btnScan,btnScanned;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initView();
	}
	private void initView(){
		setMainView(R.layout.activity_alipay);
		setTitleText("微信支付");
		int[] btns = { R.id.scan_qrcode_pay, R.id.scanned_qrcode_pay,R.id.wp_btn_change_qrcode,
				R.id.wp_btn_print_weichat_pay_qr_code,R.id.wp_btn_check_order_state };
		btnScan = (Button) findViewById(R.id.scan_qrcode_pay);
		btnScanned = (Button) findViewById(R.id.scanned_qrcode_pay);
		lineScannedQrcode = (TextView) findViewById(R.id.line_scanned_qrcode);
		lineScanQrcode = (TextView) findViewById(R.id.line_scan_qrcode);
		scanQrcodeFragment = new ScanQrcodeFragment();
		scannedQrcodeFragment = new ScannedQrcodeFragment();
		getSupportFragmentManager().beginTransaction().replace(R.id.fl_BAT_Fragment, scanQrcodeFragment).commit();
		setOnClickListenerByIds(btns, this);
	}
	
	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.scan_qrcode_pay:
			btnScan.setTextColor(getResources().getColor(R.color.blue_color));
			btnScanned.setTextColor(getResources().getColor(R.color.table_view_title_text_color));
			lineScanQrcode.setBackgroundColor(getResources().getColor(R.color.blue_color));
			lineScannedQrcode.setBackgroundColor(getResources().getColor(R.color.gray_color));
			getSupportFragmentManager().beginTransaction().replace(R.id.fl_BAT_Fragment, scanQrcodeFragment).commit();
			break;
		case R.id.scanned_qrcode_pay:
			btnScanned.setTextColor(getResources().getColor(R.color.blue_color));
			btnScan.setTextColor(getResources().getColor(R.color.table_view_title_text_color));
			lineScannedQrcode.setBackgroundColor(getResources().getColor(R.color.blue_color));
			lineScanQrcode.setBackgroundColor(getResources().getColor(R.color.gray_color));
			getSupportFragmentManager().beginTransaction().replace(R.id.fl_BAT_Fragment, scannedQrcodeFragment).commit();
			break;
		case R.id.wp_btn_change_qrcode:

			break;
		case R.id.wp_btn_print_weichat_pay_qr_code:

			break;
		case R.id.wp_btn_check_order_state:

			break;
		default:
			break;
		}
	}
}
