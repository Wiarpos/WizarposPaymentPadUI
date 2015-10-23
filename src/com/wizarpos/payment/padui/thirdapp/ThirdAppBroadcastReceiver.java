package com.wizarpos.payment.padui.thirdapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ThirdAppBroadcastReceiver extends BroadcastReceiver {

	public static final String FILTER_TRANSACITON = "com.wizarpos.pay.thirdapp.ThirdAppBroadcastReceiver.Transaction";
	public static final String FILTER_RECHARGE = "com.wizarpos.pay.thirdapp.ThirdAppBroadcastReceiver.Recharge";
	public static final String FILTER_LOGIN = "com.wizarpos.pay.thirdapp.ThirdAppBroadcastReceiver.Login";

	public interface ThirdAppListener {
		public void onResult(Intent intent);
	}

	private ThirdAppListener listener;

	public void setListener(ThirdAppListener listener) {
		this.listener = listener;
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		if (listener != null) {
			listener.onResult(intent);
		}
	}

}
