package com.wizarpos.payment.padui.cashier.activity;

import java.util.ArrayList;

import com.wizarpos.atool.net.volley2.Response;
import com.wizarpos.pay.cashier.presenter.transaction.TransactionFactory;
import com.wizarpos.pay.cashier.presenter.transaction.inf.CashTransaction;
import com.wizarpos.pay.common.base.BasePresenter.ResultListener;
import com.wizarpos.pay.common.utils.Calculater;
import com.wizarpos.pay.common.utils.UIHelper;
import com.wizarpos.payment.padui.R;
import com.wizarpos.payment.padui.view.fragment.InputPadFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
/**
 * 
 * @author hong
 * 现金支付
 *
 */
public class CashPayActivity extends TransactionActivity {
	private CashTransaction cashTransaction;
	private InputPadFragment inputPadFragment;
	private TextView cpInitAmountTv;//初始金额
	private TextView cpReduceAmountTv;//扣减金额
	private TextView cpChargeAmountTv;//找零金额
	private EditText cpShouldAmountEt;//显示应收金额
	private EditText cpPayAmountEt;//显示实收金额
	private EditText cpTicketNumEt;//显示券号
	private ArrayList<String> cashList = new ArrayList<String>();
	private String ticketNum = "";
	private int TICKET_INFO = 10001;
	private static String initAmount = "", realAmount = "", changeAmount = "", shouldAmount = "",reduceAmount;
	private boolean isScan = false;// 是否是扫描
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initView();
	}
	private void initView(){
		cashTransaction = TransactionFactory.newCashTransaction(this);
		Intent intent=getIntent();
		cashTransaction.handleIntent(intent);
		setMainView(R.layout.activity_cash_pay);
		setTitleText("现金");
		int btns[] = {R.id.cp_btn_back,R.id.cp_btn_pay,R.id.pay_ticket};
		setOnClickListenerByIds(btns, this);
		inputPadFragment = InputPadFragment.newInstance(InputPadFragment.KEYBOARDTYPE_SIMPLE);
		getSupportFragmentManager().beginTransaction().replace(R.id.flInputPad, inputPadFragment).commit();
		cpInitAmountTv = (TextView) findViewById(R.id.cp_tv_input_amount);
		cpReduceAmountTv = (TextView) findViewById(R.id.cp_tv_reduce_amount);
		cpShouldAmountEt = (EditText) findViewById(R.id.cp_et_should_pay_amount);
		cpPayAmountEt = (EditText) findViewById(R.id.cp_et_pay_amount);
		cpChargeAmountTv = (TextView) findViewById(R.id.cp_tv_return_amount);
		initData();
		showData();
	}
	private void showData() {
		cpInitAmountTv.setText(Calculater.formotFen(initAmount));
		cpReduceAmountTv.setText(Calculater.formotFen(reduceAmount));
		cpChargeAmountTv.setText(Calculater.formotFen(changeAmount));
		cpShouldAmountEt.setText(Calculater.formotFen(shouldAmount));
		cpPayAmountEt.setText(Calculater.formotFen(realAmount));
	}
	private void initData() {
		initAmount = cashTransaction.getInitAmount();
		realAmount = cashTransaction.getRealAmount();
		changeAmount = cashTransaction.getChangeAmount();
		shouldAmount = cashTransaction.getShouldAmount();
		reduceAmount = cashTransaction.getReduceAmount();
	}
	
	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.cp_btn_back://返回收银
			this.finish();
			break;
		case R.id.cp_btn_pay://支付
			trans();
			break;
		case R.id.pay_ticket://扫描获取券号
			break;
		default:
			break;
		}
	}
	/**
	 * 支付
	 */
	private void trans() {
		String realAmount = cpPayAmountEt.getText().toString();
		if (realAmount.equals("0.00")) {
			realAmount = cashTransaction.getShouldAmount(); //Bugfix 应该是取应收金额 wu@[20150929]
		} else {
			realAmount = Calculater.formotYuan(realAmount);
		}
		cashTransaction.setRealAmount(realAmount);
		progresser.showProgress();
		cashTransaction.trans(new ResultListener() {

			@Override
			public void onSuccess(Response response) {
				progresser.showContent();
				Intent intent = (Intent) response.result;
				if (cashTransaction.isMixTransaction()) {
					setResult(RESULT_OK, intent);
					CashPayActivity.this.finish();
				} else {
					toTransactionSuccess(CashPayActivity.this, intent);
					CashPayActivity.this.finish();
				}
			}

			public void onFaild(Response response) {
				progresser.showContent();
				UIHelper.ToastMessage(CashPayActivity.this, response.msg);
			}
		});
	}
}
