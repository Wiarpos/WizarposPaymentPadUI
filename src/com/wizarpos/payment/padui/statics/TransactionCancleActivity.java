package com.wizarpos.payment.padui.statics;

import com.wizarpos.atool.net.volley2.Response;
import com.wizarpos.pay.cashier.model.PayTranRsp;
import com.wizarpos.pay.cashier.presenter.transaction.impl.TransactionCancelPresenter;
import com.wizarpos.pay.common.base.BasePresenter.ResultListener;
import com.wizarpos.pay.common.utils.UIHelper;
import com.wizarpos.payment.padui.R;
import com.wizarpos.payment.padui.common.BaseViewActivity;
import com.wizarpos.payment.padui.view.fragment.InputPadFragment;
import com.wizarpos.payment.padui.view.fragment.InputPadFragment.InputType;

import android.os.Bundle;
import android.provider.Contacts.Intents.UI;
import android.view.View;
import android.widget.EditText;

/**
 * 
 * @author hong 交易撤销
 *
 */
public class TransactionCancleActivity extends BaseViewActivity {
	private InputPadFragment inputPadFragment;
	private EditText transactionNumEt;
	private String transactionNumber;// 交易流水号
	private TransactionCancelPresenter transactionCanclePresent;
	private PayTranRsp bean;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initView();
		initData();
	}

	private void initView() {
		setMainView(R.layout.activity_transaction_cancle);
		setTitleText("请输入交易流水号");
		findViewById(R.id.btn_back_transaction_cancle).setOnClickListener(this);
		findViewById(R.id.btn_confirm_transaction_cancle).setOnClickListener(this);
		transactionNumEt = (EditText) findViewById(R.id.transation_code);// 获取流水号
		inputPadFragment = new InputPadFragment();
		getSupportFragmentManager().beginTransaction().replace(R.id.flInputPad, inputPadFragment).commit();
	}

	private void initData() {
		inputPadFragment.setEditView(transactionNumEt, InputType.TYPE_INPUT_NORMAL);
		transactionCanclePresent = new TransactionCancelPresenter(this);
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.btn_back_transaction_cancle:// 返回
			this.finish();
			break;
		case R.id.btn_confirm_transaction_cancle:// 确定
			transactionNumber = "P" + transactionNumEt.getText().toString();
			query(transactionNumber);
			break;

		default:
			break;
		}
	}

	private void query(String transactionNumber) {
		progresser.showProgress();
		transactionCanclePresent.getTransactionDetial(transactionNumber, new ResultListener() {

			@Override
			public void onSuccess(Response response) {
				bean = (PayTranRsp) response.result;
				transactionCanclePresent.cancel(bean, new ResultListener() {

					@Override
					public void onSuccess(Response response) {
						progresser.showContent();
						UIHelper.ToastMessage(TransactionCancleActivity.this, response.msg);
					}

					@Override
					public void onFaild(Response response) {
						progresser.showContent();
						UIHelper.ToastMessage(TransactionCancleActivity.this, response.msg);
					}
				});

			}

			@Override
			public void onFaild(Response response) {
				progresser.showContent();
				UIHelper.ToastMessage(TransactionCancleActivity.this, response.msg);
			}
		});
	}
}
