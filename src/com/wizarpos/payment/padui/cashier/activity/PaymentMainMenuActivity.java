package com.wizarpos.payment.padui.cashier.activity;

import java.util.ArrayList;

import com.wizarpos.pay.common.utils.Calculater;
import com.wizarpos.payment.padui.R;
import com.wizarpos.payment.padui.cashier.adapter.PayWayAdapter;
import com.wizarpos.payment.padui.cashier.adapter.PayWayAdapter.OnPayItemClick;
import com.wizarpos.payment.padui.common.Constants;
import com.wizarpos.payment.padui.model.CommonDataBean;
import com.wizarpos.payment.padui.view.fragment.InputPadFragment;
import com.wizarpos.payment.padui.view.fragment.InputPadFragmentKeyBoard;
import com.wizarpos.payment.padui.view.fragment.InputPadFragmentKeyBoard.InputType;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
/**
 * 
 * @author hong
 * 收银选择支付方式主界面
 */
public class PaymentMainMenuActivity extends TransactionActivity implements OnPayItemClick {
	private InputPadFragmentKeyBoard inputPadFragmentKeyboard;
	private RecyclerView rvPayWay;
	private PayWayAdapter payWayAdapter;
	private ArrayList<CommonDataBean> mDataList;
	protected boolean isOffline = false;
	private EditText inputAmountEt = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initView();
		initRvPay();
	}
	private void initView(){
		setMainView(R.layout.activity_payment_main);
		setTitleText("收银");
		inputAmountEt = (EditText)findViewById(R.id.amount);
		inputPadFragmentKeyboard = InputPadFragmentKeyBoard.newInstance(InputPadFragment.KEYBOARDTYPE_SIMPLE);
		getSupportFragmentManager().beginTransaction().replace(R.id.flInputPad, inputPadFragmentKeyboard).commit();
		inputPadFragmentKeyboard.setEditView(inputAmountEt, InputType.TYPE_INPUT_MONEY);
	}
	
	private void initRvPay() {
		rvPayWay = (RecyclerView) findViewById(R.id.rvPayWay);
		rvPayWay.setLayoutManager(new GridLayoutManager(this, 4));
		payWayAdapter = new PayWayAdapter(this);
		mDataList = new ArrayList<CommonDataBean>();
		//银行卡按钮
		CommonDataBean bankBean = new CommonDataBean();
		bankBean.setTitleValue("银行卡");
		bankBean.setOffline(isOffline);
		bankBean.setResValue(R.drawable.btn_selector_bank_card_pay);
		bankBean.setIdValue(Constants.ID_BANK_CARD_BTN);
		mDataList.add(bankBean);
		//会员卡按钮
		CommonDataBean memberBean = new CommonDataBean();
		memberBean.setTitleValue("会员卡");
		memberBean.setOffline(isOffline);
		memberBean.setResValue(R.drawable.btn_selector_member_card_pay);
		memberBean.setIdValue(Constants.ID_MEMBER_CARD_BTN);
		mDataList.add(memberBean);
		//现金按钮
		CommonDataBean cashBean = new CommonDataBean();
		cashBean.setTitleValue("现金");
		// cashBean.setOffline(isOffline);
		cashBean.setResValue(R.drawable.btn_selector_cash_pay);
		cashBean.setIdValue(Constants.ID_CASH_PAY_BTN);
		mDataList.add(cashBean);
		//组合支付按钮
		CommonDataBean mixBean = new CommonDataBean();
		mixBean.setTitleValue("组合支付");
		mixBean.setOffline(isOffline);
		mixBean.setResValue(R.drawable.btn_selector_mix_pay);
		mixBean.setIdValue(Constants.ID_MIX_PAY_BTN);
		mDataList.add(mixBean);
		//卡券核销按钮
		CommonDataBean ticketbean = new CommonDataBean();
		ticketbean.setTitleValue("卡券核销");
		ticketbean.setOffline(isOffline);
		ticketbean.setResValue(R.drawable.btn_selector_mix_pay);
		ticketbean.setIdValue(Constants.ID_TICKET_CHECK_BTN);
		mDataList.add(ticketbean);
		//支付宝按钮
		CommonDataBean alipaybean = new CommonDataBean();
		alipaybean.setTitleValue("支付宝");
		alipaybean.setOffline(isOffline);
		alipaybean.setResValue(R.drawable.btn_selector_ali_pay);
		alipaybean.setIdValue(Constants.ID_ALIPAY_BTN);
		mDataList.add(alipaybean);
		//微信按钮
		CommonDataBean wepaybean = new CommonDataBean();
		wepaybean.setTitleValue("微信");
		wepaybean.setOffline(isOffline);
		wepaybean.setResValue(R.drawable.btn_selector_wechat_pay);
		wepaybean.setIdValue(Constants.ID_WEPAY_BTN);
		mDataList.add(wepaybean);
		//QQ钱包按钮
		CommonDataBean tenpaybean = new CommonDataBean();
		tenpaybean.setTitleValue("QQ钱包");
		tenpaybean.setOffline(isOffline);
		tenpaybean.setResValue(R.drawable.btn_selector_tencent_pay);
		tenpaybean.setIdValue(Constants.ID_TENPAY_BTN);
		mDataList.add(tenpaybean);
		//百度钱包按钮
		CommonDataBean baidupaybean = new CommonDataBean();
		baidupaybean.setTitleValue("百度钱包");
		baidupaybean.setOffline(isOffline);
		baidupaybean.setResValue(R.drawable.btn_selector_baidu_pay);
		baidupaybean.setIdValue(Constants.ID_BAIDUPAY_BTN);
		mDataList.add(baidupaybean);
		//其他支付按钮
		CommonDataBean otherpaybean = new CommonDataBean();
		otherpaybean.setTitleValue("其他支付");
		otherpaybean.setOffline(isOffline);
		otherpaybean.setResValue(R.drawable.btn_selector_mix_pay);
		otherpaybean.setIdValue(Constants.ID_OTHERPAY_BTN);
		mDataList.add(otherpaybean);
		payWayAdapter.setDataChanged(mDataList);
		rvPayWay.setAdapter(payWayAdapter);
		payWayAdapter.setOnItemClick(this);
		
	}
	@Override
	public void onPayItemClick(View v) {
		Intent intent = new Intent();
		String initAmount = Calculater.formotYuan(inputAmountEt.getText().toString());
		intent.putExtra(com.wizarpos.pay.common.Constants.initAmount, initAmount);
		switch (v.getId()) {
		case Constants.ID_CASH_PAY_BTN ://现金支付
			toCashTransactionView(this, intent);
			break;
		case Constants.ID_BANK_CARD_BTN ://银行卡支付
			toCardTransactionView(this, intent);
			break;
		case Constants.ID_MEMBER_CARD_BTN ://会员卡支付
			toScanMemberCard(this, intent);
			break;
		case Constants.ID_MIX_PAY_BTN ://组合支付
			toCashTransactionView(this, intent);
			break;
		case Constants.ID_ALIPAY_BTN ://支付宝支付
			intent.putExtra(Constants.PAYMENTTYPE, Constants.ALIPAY);
			toAlipayMicroTransaction(this, intent);
			break;
		case Constants.ID_WEPAY_BTN ://微信支付
			intent.putExtra(Constants.PAYMENTTYPE, Constants.WEPAY);
			toWepayMicroTransaction(this, intent);
			break;
		case Constants.ID_TENPAY_BTN ://QQ钱包支付
			intent.putExtra(Constants.PAYMENTTYPE, Constants.TENPAY);
			toTenpayMicroTransaction(this, intent);
			break;
		case Constants.ID_BAIDUPAY_BTN ://百度钱包支付
			intent.putExtra(Constants.PAYMENTTYPE, Constants.BAIDUPAY);
			toBaiduMicroTransaction(this, intent);
			break;
		case Constants.ID_OTHERPAY_BTN ://其他支付
			toOtherTransactionView(this, intent);
			break;
		default:
			break;
		}
		
	}
	
}
