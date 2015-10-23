package com.wizarpos.payment.padui.view;

import com.wizarpos.pay.cashier.presenter.ITransactionFlowController;
import com.wizarpos.payment.padui.common.BaseViewActivity;

import android.app.Activity;
import android.content.Intent;

public class TransactionFlowController extends BaseViewActivity implements ITransactionFlowController{

	@Override
	public void toInputTicketView(Activity activity, String title, Intent intent, int requestCode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void toInputTicketView(Activity activity, String title, boolean isUseSwipe, Intent intent, int requestCode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void toInputView(Activity activity, String title, Intent intent, int requestCode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void toInputView(Activity activity, String title, boolean isUseSwipe, Intent intent, int requestCode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void toInputView(Activity activity, String title, boolean isUseSwipe, boolean isUseText, boolean isUseCamera,
			Intent intent, int requestCode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void toInputTicketView(Activity activity, String title, boolean isUseSwipe, boolean isUseText,
			boolean isUseCamera, Intent intent, int requestCode) {
		// TODO Auto-generated method stub
		
	}
	//现金
	@Override
	public void toCashTransactionView(Activity activity, Intent intent) {
		// TODO Auto-generated method stub
		
	}
	//组合支付现金
	@Override
	public void toCashMixTransactionView(Activity activity, Intent intent, int requestCode) {
		// TODO Auto-generated method stub
		
	}
	//其它支付
	@Override
	public void toOtherTransactionView(Activity activity, Intent intent) {
		// TODO Auto-generated method stub
		
	}
	//组合支付中的其它支付
	@Override
	public void toOtherMixTransactionView(Activity activity, Intent intent, int requestCode) {
		// TODO Auto-generated method stub
		
	}
	//银行卡支付
	@Override
	public void toCardTransactionView(Activity activity, Intent intent) {
		// TODO Auto-generated method stub
		
	}
	//组合支付中的银行卡支付
	@Override
	public void toCardMixTransactionView(Activity activity, Intent intent, int requestCode) {
		// TODO Auto-generated method stub
		
	}
	//组合支付中的会员卡支付
	@Override
	public void toMemberMixTransactionVew(Activity activity, Intent intent, int requestCode) {
		// TODO Auto-generated method stub
		
	}
	//
	@Override
	public void toAlipayMicroTransaction(Activity activity, Intent intent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void toAlipayMixMicroTransaction(Activity activity, Intent intent, int requestCode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void toAlipayNativeTransaction(Activity activity, Intent intent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void toAlipayMixNativeTransaction(Activity activity, Intent intent, int requestCode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void toWepayMicroTransaction(Activity activity, Intent intent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void toWepayMixMicroTransaction(Activity activity, Intent intent, int requestCode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void toWepayNativeTransaction(Activity activity, Intent intent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void toWepayMixNativeTransaction(Activity activity, Intent intent, int requestCode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void toTenpayMicroTransaction(Activity activity, Intent intent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void toTenpayMixMicroTransaction(Activity activity, Intent intent, int requestCode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void toTenpayNativeTransaction(Activity activity, Intent intent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void toTenpayMixNativeTransaction(Activity activity, Intent intent, int requestCode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void toBaiduMicroTransaction(Activity activity, Intent intent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void toBaiduMixMicroTransaction(Activity activity, Intent intent, int requestCode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void toBaiduNativeTransaction(Activity activity, Intent intent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void toBaiduMixNativeTransaction(Activity activity, Intent intent, int requestCode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void toTransactionSuccess(Activity activity, Intent intent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void toMixTransaction(Activity activity, Intent intent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void toTransactionFaild() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void toTicketPublishActivity(Activity activity, Intent intent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void toWepayTicketCancelMix(Activity activity, Intent intent, int requestCode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void toThridTicketCancelMix(Activity activity, Intent intent, int requestCode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void toMixMemberTicketPass(Activity activity, Intent intent, int requestCode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void toMixNormalTicketPass(Activity activity, Intent intent, int requestCode) {
		// TODO Auto-generated method stub
		
	}

}
