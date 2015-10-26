package com.wizarpos.payment.padui.view.fragment;

import com.wizarpos.barcode.scanner.IScanEvent;
import com.wizarpos.barcode.scanner.ScannerRelativeLayout;
import com.wizarpos.barcode.scanner.ScannerResult;
import com.wizarpos.pay.common.device.DeviceManager;
import com.wizarpos.payment.padui.R;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
/**
 * 被扫支付
 *
 */
public class ScanQrcodeFragment extends BaseFragment{
	private ScannerRelativeLayout scanner;
	private TextView tvOrderNo,tvOrderAmount,tvOrderState;
	private EditText etScanBarcode;
	/** 操作类型*/
	private static final int SCAN_START = 1;
	/** 标志位，标志已经初始化完成 */
    private boolean isPrepared;
    /** 是否已被加载过一次，第二次就不再去请求数据了 */
    private boolean mHasLoadedOnce;
	private Handler handler = new Handler () {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case SCAN_START:
				if (scanner != null) {
					scanner.startScan();
				}
				break;
			}
		};
	};
	
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
		isPrepared = true;
		lazyLoad();
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
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		if (scanner != null) {
			scanner.stopScan();
		}
	}
	
	@Override
	protected void lazyLoad() {
		if (!isPrepared || isVisible || mHasLoadedOnce) {
			return;
		}
		if (scanner != null) {
			scanner.onResume();
		}
		new Thread() {
			public void run() {
				try {
					sleep(500L);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				handler.sendEmptyMessage(SCAN_START);
			}
		}.start();
		initScan();
	}

	@Override
	protected void invisible() {
		if (scanner != null) {
			scanner.stopScan();
		}
	}

}
