package com.wizarpos.payment.padui.cashier.activity.paymentterm;

import com.wizarpos.atool.net.volley2.Response;
import com.wizarpos.atool.tool.Tools;
import com.wizarpos.pay.cashier.pay_tems.bat.inf.MicroTransaction;
import com.wizarpos.pay.cashier.pay_tems.bat.inf.NativeTransaction;
import com.wizarpos.pay.cashier.presenter.transaction.TransactionFactory;
import com.wizarpos.pay.common.Constants;
import com.wizarpos.pay.common.base.BasePresenter.ResultListener;
import com.wizarpos.pay.common.utils.UIHelper;
import com.wizarpos.pay.common.view.MaterialImageLoading;
import com.wizarpos.payment.padui.R;
import com.wizarpos.payment.padui.cashier.activity.ThirdpayScanActivity;
import com.wizarpos.payment.padui.view.fragment.ScanQrcodeFragment;
import com.wizarpos.payment.padui.view.fragment.ScannedQrcodeFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
/**
 * 
 * @author hong
 * 支付宝支付
 *
 */
public class AlipayActivity extends ThirdpayScanActivity {
	private TextView lineScanQrcode,lineScannedQrcode;
	private Button btnScan,btnScanned;
	private MicroTransaction batMicroTransaction;//支付宝被扫
	private NativeTransaction batNativeTransaction;//支付宝主扫
	private String realAmount = null;
	private String ivUrl;
	private boolean MICRO_FLAG = true;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getData();
		initView();
	}
	private void getData(){
		Intent intent=getIntent();
		realAmount = intent.getStringExtra(Constants.realAmount); //增加realAmoun
		batMicroTransaction = TransactionFactory.newBatMicroTransaction(this);
		batNativeTransaction = TransactionFactory.newBatNativeTransaction(this);
		intent.putExtra("payTypeFlag", Constants.ALIPAY_BAT);
		if (MICRO_FLAG) {
		 batMicroTransaction.handleIntent(intent);
		}else {
		 batNativeTransaction.handleIntent(intent);
			
		}
	}
	private void initView(){
		setTitleText("支付宝支付");
		btnScan = (Button) findViewById(R.id.scan_qrcode_pay);
		btnScanned = (Button) findViewById(R.id.scanned_qrcode_pay);
		lineScannedQrcode = (TextView) findViewById(R.id.line_scanned_qrcode);
		lineScanQrcode = (TextView) findViewById(R.id.line_scan_qrcode);
	}
	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.scan_qrcode_pay://被扫支付
			showMicroMenu();
			getSupportFragmentManager().beginTransaction().add(R.id.fl_BAT_Fragment, fragment).commit();
			break;
		case R.id.scanned_qrcode_pay://主扫支付
			getSupportFragmentManager().beginTransaction().remove(fragment).commit();
			showNativeMenu();
			progresser.showProgress();
			nativepay();
			break;
		case R.id.wp_btn_change_qrcode://切换摄像头

			break;
		case R.id.print_qrcode://打印订单二维码

			break;
		case R.id.wp_btn_check_order_state://检查订单状态
			UIHelper.ToastMessage(this, "已支付");
			break;
		default:
			break;
		}
	}
	// 显示主扫界面
	private void showNativeMenu() {
		nativeRemind.setVisibility(View.VISIBLE);
		printQRCode.setVisibility(View.VISIBLE);
		showNativeMenu.setVisibility(View.VISIBLE);
		showMicroMenu.setVisibility(View.GONE);
		btnScanned.setTextColor(getResources().getColor(R.color.blue_color));
		btnScan.setTextColor(getResources().getColor(R.color.table_view_title_text_color));
		lineScannedQrcode.setBackgroundColor(getResources().getColor(R.color.blue_color));
		lineScanQrcode.setBackgroundColor(getResources().getColor(R.color.gray_color));
		MICRO_FLAG = false;
	}

	// 显示被扫界面
	private void showMicroMenu() {
		nativeRemind.setVisibility(View.GONE);
		printQRCode.setVisibility(View.GONE);
		showMicroMenu.setVisibility(View.VISIBLE);
		showNativeMenu.setVisibility(View.GONE);
		btnScan.setTextColor(getResources().getColor(R.color.blue_color));
		btnScanned.setTextColor(getResources().getColor(R.color.table_view_title_text_color));
		lineScanQrcode.setBackgroundColor(getResources().getColor(R.color.blue_color));
		lineScannedQrcode.setBackgroundColor(getResources().getColor(R.color.gray_color));
		MICRO_FLAG = true;
	}

	//被扫支付
	protected void micropay(String authCode) {
		batMicroTransaction.batPay(Constants.ALIPAY_BAT, authCode, new ResultListener() {
			
			@Override
			public void onSuccess(Response response) {
				progresser.showContent();
				Intent responseIntent = batMicroTransaction.bundleResult();
				deliverResult(batMicroTransaction.isMixTransaction(), responseIntent);
			}
			
			@Override
			public void onFaild(Response response) {
				progresser.showContent();
				if (response.code == 1) {
					//TODO 显示支付状态
				} else {
					UIHelper.ToastMessage(AlipayActivity.this, response.msg);
				}
				
			}
		});
		
	}
	//主扫支付
	protected void nativepay() {
		batNativeTransaction.batPay(Constants.ALIPAY_BAT, new ResultListener() {
			
			@Override
			public void onSuccess(Response response) {
				progresser.showContent();
				ivUrl = response.getResult().toString();
				batNativeTransaction.listenResult(new ResultListener() {

					@Override
					public void onSuccess(Response response) {
						Intent resultIntent = batNativeTransaction.bundleResult();
						deliverResult(batNativeTransaction.isMixTransaction(), resultIntent);
					}

					@Override
					public void onFaild(Response response) {
						UIHelper.ToastMessage(AlipayActivity.this, response.getMsg());
					}
				});
			}
			
			@Override
			public void onFaild(Response response) {
				
			}
		});
		
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (batMicroTransaction != null) {
			batMicroTransaction.onDestory();
		}
		if (batNativeTransaction != null) {
			batNativeTransaction.onDestory();
		}
	}
	@Override
	public void display(Response response) {
		if (response.getResult() == null) { return; }
		String authCode = response.getResult().toString();
		progresser.showProgress();
		micropay(authCode);
	}
}
