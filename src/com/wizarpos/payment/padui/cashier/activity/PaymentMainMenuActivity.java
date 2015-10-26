package com.wizarpos.payment.padui.cashier.activity;

import java.util.ArrayList;

import com.wizarpos.payment.padui.R;
import com.wizarpos.payment.padui.adapter.PayWayAdapter;
import com.wizarpos.payment.padui.adapter.PayWayAdapter.OnPayItemClick;
import com.wizarpos.payment.padui.model.CommonDataBean;
import com.wizarpos.payment.padui.view.fragment.InputPadFragmentKeyBoard;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
/**
 * 
 * @author hong
 * 收银选择支付方式主界面
 */
public class PaymentMainMenuActivity extends TransactionActivity implements OnPayItemClick {
	private InputPadFragmentKeyBoard inputPadFragmentKeyBoard;
	private RecyclerView rvPayWay;
	private PayWayAdapter payWayAdapter;
	private ArrayList<CommonDataBean> mDataList;
	protected boolean isOffline = false;
	public static final int id_bank_card_btn = 100;
	public static final int id_member_card_btn = 101;
	public static final int id_cash_pay_btn = 102;
	public static final int id_mix_pay_btn = 103;
	public static final int id_ticket_check_btn = 104;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initView();
		initRvPay();
	}
	private void initView(){
		setMainView(R.layout.activity_payment_main);
		setTitleText("收银");
		inputPadFragmentKeyBoard = InputPadFragmentKeyBoard.newInstance(InputPadFragmentKeyBoard.KEYBOARDTYPE_SIMPLE);
		getSupportFragmentManager().beginTransaction().replace(R.id.flInputPad, inputPadFragmentKeyBoard).commit();
	}
	
	private void initRvPay() {
		rvPayWay = (RecyclerView) findViewById(R.id.rvPayWay);
		rvPayWay.setLayoutManager(new GridLayoutManager(this, 4));
		payWayAdapter = new PayWayAdapter(this);
		rvPayWay.setAdapter(payWayAdapter);
		payWayAdapter.setOnItemClick(this);
		mDataList = new ArrayList<CommonDataBean>();
		CommonDataBean bankBean = new CommonDataBean();
		bankBean.setTitleValue("银行卡");
		bankBean.setOffline(isOffline);// 除BUG 离线状态不更新界面 wu@[20150814]
		bankBean.setResValue(R.drawable.btn_selector_bank_card_pay);
		bankBean.setIdValue(id_bank_card_btn);
		mDataList.add(bankBean);
		CommonDataBean memberBean = new CommonDataBean();
		memberBean.setTitleValue("会员卡");
		memberBean.setOffline(isOffline);
		memberBean.setResValue(R.drawable.btn_selector_member_card_pay);
		memberBean.setIdValue(id_member_card_btn);
		mDataList.add(memberBean);
		CommonDataBean cashBean = new CommonDataBean();
		cashBean.setTitleValue("现金");
		// cashBean.setOffline(isOffline);
		cashBean.setResValue(R.drawable.btn_selector_cash_pay);
		cashBean.setIdValue(id_cash_pay_btn);
		mDataList.add(cashBean);
		CommonDataBean mixBean = new CommonDataBean();
		mixBean.setTitleValue("组合支付");
		mixBean.setOffline(isOffline);
		mixBean.setResValue(R.drawable.btn_selector_mix_pay);
		mixBean.setIdValue(id_mix_pay_btn);
		mDataList.add(mixBean);
		CommonDataBean ticketbean = new CommonDataBean();
		ticketbean.setTitleValue("卡券核销");
		ticketbean.setOffline(isOffline);
		ticketbean.setResValue(R.drawable.btn_selector_mix_pay);
		ticketbean.setIdValue(id_ticket_check_btn);
		mDataList.add(ticketbean);
	}
	@Override
	public void onPayItemClick(View v) {
		// TODO Auto-generated method stub
		
	}
	
}
