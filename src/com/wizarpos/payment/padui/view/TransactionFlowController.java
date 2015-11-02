package com.wizarpos.payment.padui.view;

import com.wizarpos.pay.cashier.presenter.ITransactionFlowController;
import com.wizarpos.payment.padui.cashier.activity.BankCardPayActivity;
import com.wizarpos.payment.padui.cashier.activity.CashPayActivity;
import com.wizarpos.payment.padui.cashier.activity.OtherpayActivity;
import com.wizarpos.payment.padui.cashier.activity.PaySuccessActivity;
import com.wizarpos.payment.padui.cashier.activity.ThirdpayActivity;
import com.wizarpos.payment.padui.common.BaseViewActivity;

import android.app.Activity;
import android.content.Intent;

public class TransactionFlowController extends BaseViewActivity implements ITransactionFlowController{

	@Override
	public void toInputTicketView(Activity activity, String title, Intent intent, int requestCode) {
		
	}

	@Override
	public void toInputTicketView(Activity activity, String title, boolean isUseSwipe, Intent intent, int requestCode) {
		
	}

	@Override
	public void toInputView(Activity activity, String title, Intent intent, int requestCode) {
		
	}

	@Override
	public void toInputView(Activity activity, String title, boolean isUseSwipe, Intent intent, int requestCode) {
		
	}

	@Override
	public void toInputView(Activity activity, String title, boolean isUseSwipe, boolean isUseText, boolean isUseCamera,
			Intent intent, int requestCode) {
		
	}

	@Override
	public void toInputTicketView(Activity activity, String title, boolean isUseSwipe, boolean isUseText,
			boolean isUseCamera, Intent intent, int requestCode) {
		
	}
	//现金
	@Override
	public void toCashTransactionView(Activity activity, Intent intent) {
		intent.setClass(activity, CashPayActivity.class);
		startAcitvityAndResetTimer(activity,intent);
	}
	//组合支付现金
	@Override
	public void toCashMixTransactionView(Activity activity, Intent intent, int requestCode) {
		
	}
	//其它支付
	@Override
	public void toOtherTransactionView(Activity activity, Intent intent) {
		intent.setClass(activity, OtherpayActivity.class);
		startAcitvityAndResetTimer(activity, intent);
	}
	//组合支付中的其它支付
	@Override
	public void toOtherMixTransactionView(Activity activity, Intent intent, int requestCode) {
		
	}
	//银行卡支付
	@Override
	public void toCardTransactionView(Activity activity, Intent intent) {
		intent.setClass(activity, BankCardPayActivity.class);
		startActivity(intent);
		
	}
	//组合支付中的银行卡支付
	@Override
	public void toCardMixTransactionView(Activity activity, Intent intent, int requestCode) {
		
	}
	//组合支付中的会员卡支付
	@Override
	public void toMemberMixTransactionVew(Activity activity, Intent intent, int requestCode) {
		
	}
	//
	@Override
	public void toAlipayMicroTransaction(Activity activity, Intent intent) {
		intent.setClass(activity, ThirdpayActivity.class);
		startAcitvityAndResetTimer(activity,intent);
	}

	@Override
	public void toAlipayMixMicroTransaction(Activity activity, Intent intent, int requestCode) {
		
	}

	@Override
	public void toAlipayNativeTransaction(Activity activity, Intent intent) {
		
	}

	@Override
	public void toAlipayMixNativeTransaction(Activity activity, Intent intent, int requestCode) {
		
	}

	@Override
	public void toWepayMicroTransaction(Activity activity, Intent intent) {
		intent.setClass(activity, ThirdpayActivity.class);
		startAcitvityAndResetTimer(activity,intent);
	}

	@Override
	public void toWepayMixMicroTransaction(Activity activity, Intent intent, int requestCode) {
		
	}

	@Override
	public void toWepayNativeTransaction(Activity activity, Intent intent) {
		
	}

	@Override
	public void toWepayMixNativeTransaction(Activity activity, Intent intent, int requestCode) {
		
	}

	@Override
	public void toTenpayMicroTransaction(Activity activity, Intent intent) {
		intent.setClass(activity, ThirdpayActivity.class);
		startAcitvityAndResetTimer(activity,intent);
	}

	@Override
	public void toTenpayMixMicroTransaction(Activity activity, Intent intent, int requestCode) {
		
	}

	@Override
	public void toTenpayNativeTransaction(Activity activity, Intent intent) {
		
	}

	@Override
	public void toTenpayMixNativeTransaction(Activity activity, Intent intent, int requestCode) {
		
	}

	@Override
	public void toBaiduMicroTransaction(Activity activity, Intent intent) {
		intent.setClass(activity, ThirdpayActivity.class);
		startAcitvityAndResetTimer(activity, intent);
	}

	@Override
	public void toBaiduMixMicroTransaction(Activity activity, Intent intent, int requestCode) {
		
	}

	@Override
	public void toBaiduNativeTransaction(Activity activity, Intent intent) {
		
	}

	@Override
	public void toBaiduMixNativeTransaction(Activity activity, Intent intent, int requestCode) {
		
	}

	@Override
	public void toTransactionSuccess(Activity activity, Intent intent) {
		intent.setClass(activity, PaySuccessActivity.class);
		startAcitvityAndResetTimer(activity, intent);
		
	}

	@Override
	public void toMixTransaction(Activity activity, Intent intent) {
		
	}

	@Override
	public void toTransactionFaild() {
		
	}

	@Override
	public void toTicketPublishActivity(Activity activity, Intent intent) {
		
	}

	@Override
	public void toWepayTicketCancelMix(Activity activity, Intent intent, int requestCode) {
		
	}

	@Override
	public void toThridTicketCancelMix(Activity activity, Intent intent, int requestCode) {
		
	}

	@Override
	public void toMixMemberTicketPass(Activity activity, Intent intent, int requestCode) {
		
	}

	@Override
	public void toMixNormalTicketPass(Activity activity, Intent intent, int requestCode) {
		
	}
	private void startAcitvityAndResetTimer(Activity activity, Intent intent) {
		// TODO 加入计时器
		activity.startActivity(intent);
	}

	private void startAcitvityForResultAndResetTimer(Activity activity, Intent intent, int requestCode) {
		// TODO 加入计时器
		activity.startActivityForResult(intent, requestCode);
	}
	//会员卡
	public void toScanMemberCard(Activity activity, Intent intent){
		
	}

}
