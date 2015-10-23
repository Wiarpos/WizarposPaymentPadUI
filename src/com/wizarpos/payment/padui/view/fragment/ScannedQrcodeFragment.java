package com.wizarpos.payment.padui.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wizarpos.payment.padui.R;

public class ScannedQrcodeFragment extends Fragment{
	private ImageView ivQrcode;
	private TextView tvOrderNo,tvOrderAmount,tvOrderState;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_scanned_qrcode_order, null);
		setupView(view);
		return view;
	}
	
	private void setupView (View view) {
		ivQrcode = (ImageView) view.findViewById(R.id.alipay_qr_code_1);
		tvOrderNo = (TextView) view.findViewById(R.id.tv_order_no);
		tvOrderAmount = (TextView) view.findViewById(R.id.tv_order_amount);
		tvOrderState = (TextView) view.findViewById(R.id.tv_order_state);
	}
	
}
