package com.wizarpos.payment.padui.view.fragment;

import com.wizarpos.barcode.scanner.IScanEvent;
import com.wizarpos.barcode.scanner.ScannerRelativeLayout;
import com.wizarpos.barcode.scanner.ScannerResult;
import com.wizarpos.pay.common.device.DeviceManager;
import com.wizarpos.payment.padui.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class ScanQrcodeFragment extends Fragment{
	private ScannerRelativeLayout scanner;
	private TextView tvOrderNo,tvOrderAmount,tvOrderState;
	private EditText etScanBarcode;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_scan_qrcode_order, null);
		setupView(view);
		return view;
	}
	
	private void setupView (View view) {
		scanner = (ScannerRelativeLayout) view.findViewById(R.id.re_scanner_progress);
		tvOrderNo = (TextView) view.findViewById(R.id.tv_order_no);
		tvOrderAmount = (TextView) view.findViewById(R.id.tv_order_amount);
		tvOrderState = (TextView) view.findViewById(R.id.tv_order_state);
		etScanBarcode = (EditText) view.findViewById(R.id.et_scan_code);
		initScan();
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
	}
	
	private void initScan () {
		scanner.setCameraIndex(DeviceManager.getInstance().getCameraIndex());
		scanner.startScan();
		scanner.setEncodeFormat("CODE_128");
		scanner.setScanSuccessListener(new IScanEvent() {
			@Override
			public void scanCompleted(ScannerResult result) {
				
			}
		});
	}
}
