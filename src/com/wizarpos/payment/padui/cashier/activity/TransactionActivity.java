package com.wizarpos.payment.padui.cashier.activity;

import com.alibaba.fastjson.JSONObject;
import com.wizarpos.pay.cashier.presenter.TransactionTemsController;
import com.wizarpos.pay.cashier.thrid_app_controller.ThirdAppBroadcastReceiver;
import com.wizarpos.pay.cashier.thrid_app_controller.ThirdAppBroadcastReceiver.ThirdAppListener;
import com.wizarpos.pay.cashier.thrid_app_controller.ThirdAppFinisher;
import com.wizarpos.pay.cashier.thrid_app_controller.ThirdAppTransactionController;
import com.wizarpos.pay.cashier.thrid_app_controller.model.ThirdAppTransactionEXJsonResponse;
import com.wizarpos.pay.cashier.thrid_app_controller.model.ThirdAppTransactionResponse;
import com.wizarpos.pay.common.Constants;
import com.wizarpos.pay.db.AppStateDef;
import com.wizarpos.pay.db.AppStateManager;
import com.wizarpos.payment.padui.view.TransactionFlowController;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;

public class TransactionActivity extends TransactionFlowController implements ThirdAppFinisher {
	private ThirdAppBroadcastReceiver receiver; // 交易监听
	protected ThirdAppTransactionController thirdAppController;

	/**
	 * 注册交易监听广播
	 */
//	protected void registerReceiver(ThirdAppListener listener, String action) {
//		receiver = new ThirdAppBroadcastReceiver();
//		receiver.setListener(listener);
//		IntentFilter filter = new IntentFilter(action);
//		LocalBroadcastManager.getInstance(this).registerReceiver(receiver, filter);
//	}

	/**
	 * 取消注册交易监听广播
	 */
//	protected void unregisterReceiver() {
//		try {
//			if (receiver != null) {
//				LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
//			}
//		} catch (Exception e) {
//		}
//	}

	@Override
	public void onBackPressed() {// 禁用返回按钮
		back();
	}

	@Override
	protected void onTitleBackClikced() {
		back();
	}

	protected void back() {
		serviceFaild();
		this.finish();
	}

	protected void serviceFaild() {
		AppStateManager.setState(AppStateDef.isInService, Constants.FALSE);
		ThirdAppTransactionResponse response = new ThirdAppTransactionResponse();
		response.setCode(1);
		response.setMessage("用户取消");
		Intent intent = new Intent();
		intent.putExtra("response", JSONObject.toJSONString(response));
		intent.setAction(ThirdAppBroadcastReceiver.FILTER_TRANSACITON);
		LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
	}

	protected void serviceSuccess(Intent intent) {
		AppStateManager.setState(AppStateDef.isInService, Constants.FALSE);
		ThirdAppTransactionResponse response = praseResponseIntent(intent);
		if (response != null) {
			response.setCode(0);
			response.setMessage("success");
		} else {
			response = new ThirdAppTransactionResponse();
			response.setCode(1);
			response.setMessage("交易结果解析失败");
		}
		intent = new Intent();
		intent.putExtra("response", JSONObject.toJSONString(response));
		intent.setAction(ThirdAppBroadcastReceiver.FILTER_TRANSACITON);
		intent.setComponent(null);
		LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
	}

	protected void bundleThridTransactionResponse(Intent intent) {
		thirdAppController.bundleResponse(intent);
	}

	public ThirdAppTransactionResponse praseResponseIntent(Intent intent) {
		try {
			ThirdAppTransactionEXJsonResponse exJsonBean = new ThirdAppTransactionEXJsonResponse();
			exJsonBean.setExtraAmount(intent.getStringExtra(Constants.discountAmount));
			exJsonBean.setInputAmount(intent.getStringExtra(Constants.initAmount));
			exJsonBean.setPayAmount(intent.getStringExtra(Constants.realAmount));
			exJsonBean.setReduceAmount(intent.getStringExtra(Constants.reduceAmount));
			exJsonBean.setReturnAmount(intent.getStringExtra(Constants.changeAmount));
			exJsonBean.setTradeNo(intent.getStringExtra(Constants.tranId));
			setPayMode(intent.getIntExtra("TRANSACTION_TYPE", 0), exJsonBean);
			if (Constants.TRUE.equals(intent.getStringExtra("offline"))) {
				exJsonBean.setOffline(Constants.TRUE);
				exJsonBean.setOfflineTranLogId(intent.getStringExtra("tranLogId"));
			} else {
				exJsonBean.setMasterTranLogId(intent.getStringExtra("tranLogId"));
			}
			ThirdAppTransactionResponse appTransactionResponse = new ThirdAppTransactionResponse();
			appTransactionResponse.setExJson(exJsonBean);
			return appTransactionResponse;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private void setPayMode(int transactionType, ThirdAppTransactionEXJsonResponse exJsonBean) {
		exJsonBean.setPayMode(transactionType + "");
		switch (transactionType) {
		case TransactionTemsController.TRANSACTION_TYPE_BANK_CARD:
			exJsonBean.setPayModeDesc("银行卡");
			break;
		case TransactionTemsController.TRANSACTION_TYPE_MEMBER_CARD:
			exJsonBean.setPayModeDesc("会员卡");
		case TransactionTemsController.TRANSACTION_TYPE_CASH:
			exJsonBean.setPayModeDesc("现金");
			break;
		case TransactionTemsController.TRANSACTION_TYPE_OTHER:
			exJsonBean.setPayModeDesc("其他支付");
			break;
		case TransactionTemsController.TRANSACTION_TYPE_WEPAY_MEMBER_CARD:
			exJsonBean.setPayModeDesc("微信会员卡");
			break;
		case TransactionTemsController.TRANSACTION_TYPE_ALIPAY:
			exJsonBean.setPayModeDesc("支付宝");
			break;
		case TransactionTemsController.TRANSACTION_TYPE_WEPAY_PAY:
			exJsonBean.setPayModeDesc("微信");
			break;
		case TransactionTemsController.TRANSACTION_TYPE_TEN_PAY:
			exJsonBean.setPayModeDesc("QQ钱包");
			break;
		case TransactionTemsController.TRANSACTION_TYPE_BAIDU_PAY:
			exJsonBean.setPayModeDesc("百度支付");
			break;
		default:
			break;
		}

	}

	@Override
	public void finishTransacton(int resultCode, Intent intent) {
		setResult(resultCode, intent);
		this.finish();
	}

}
