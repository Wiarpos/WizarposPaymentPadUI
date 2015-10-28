package com.wizarpos.payment.padui.cashier.activity.paymentterm;

import com.wizarpos.atool.net.volley2.Response;
import com.wizarpos.payment.padui.R;
import com.wizarpos.payment.padui.cashier.activity.ThirdpayScanActivity;
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
public class WepayActivty extends ThirdpayScanActivity {
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
				R.id.print_qrcode,R.id.wp_btn_check_order_state };
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
		case R.id.scan_qrcode_pay://被扫支付
			btnScan.setTextColor(getResources().getColor(R.color.blue_color));
			btnScanned.setTextColor(getResources().getColor(R.color.table_view_title_text_color));
			lineScanQrcode.setBackgroundColor(getResources().getColor(R.color.blue_color));
			lineScannedQrcode.setBackgroundColor(getResources().getColor(R.color.gray_color));
			getSupportFragmentManager().beginTransaction().replace(R.id.fl_BAT_Fragment, scanQrcodeFragment).commit();
			break;
		case R.id.scanned_qrcode_pay://主扫支付
			btnScanned.setTextColor(getResources().getColor(R.color.blue_color));
			btnScan.setTextColor(getResources().getColor(R.color.table_view_title_text_color));
			lineScannedQrcode.setBackgroundColor(getResources().getColor(R.color.blue_color));
			lineScanQrcode.setBackgroundColor(getResources().getColor(R.color.gray_color));
			getSupportFragmentManager().beginTransaction().replace(R.id.fl_BAT_Fragment, scannedQrcodeFragment).commit();
			break;
		case R.id.wp_btn_change_qrcode://切换摄像头

			break;
		case R.id.print_qrcode://打印订单二维码

			break;
		case R.id.wp_btn_check_order_state://检查订单状态

			break;
		default:
			break;
		}
	}
	@Override
	public void display(Response response) {
		// TODO Auto-generated method stub
		
	}
}
