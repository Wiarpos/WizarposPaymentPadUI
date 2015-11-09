package com.wizarpos.payment.padui.cashier.activity;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wizarpos.atool.net.volley2.Response;
import com.wizarpos.pay.cashier.presenter.TransactionTemsController;
import com.wizarpos.pay.cashier.presenter.transaction.TransactionFactory;
import com.wizarpos.pay.cashier.presenter.transaction.impl.OtherpayTransactionImpl;
import com.wizarpos.pay.cashier.presenter.transaction.inf.OtherPayTransaction;
import com.wizarpos.pay.common.Constants;
import com.wizarpos.pay.common.base.BasePresenter.ResultListener;
import com.wizarpos.pay.common.utils.UIHelper;
import com.wizarpos.pay.model.OtherPayReq;
import com.wizarpos.payment.padui.R;
import com.wizarpos.payment.padui.cashier.adapter.OtherPayAdapter;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.AdapterView.OnItemClickListener;
/**
 * 
 * @author hong
 * 其它支付
 *
 */
public class OtherpayActivity extends TransactionActivity {
	private OtherPayTransaction transaction;
	private String TAG = "OtherPayActivity";
	private ArrayList<String> recordList;
	private ArrayList<Integer> drawList;
	private String serviceId;
	private Intent intent;
	private String serviceName;
	private String initAmount;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		transaction = TransactionFactory.newOtherPayTransaction(this);
		intent = getIntent();
		initAmount = intent.getStringExtra("initAmount");
		initView();
		getData();
	}
	private void initView(){
		setMainView(R.layout.activity_manage_operator_head);
		setTitleText("其他支付");
	}
	private void getData(){

		progresser.showProgress();
		((OtherpayTransactionImpl) transaction).setInitListener(new ResultListener() {
			@Override
			public void onSuccess(Response response) {
				progresser.showContent();
				Log.e(TAG, String.valueOf(response.result));
				JSONArray jsonArray = (JSONArray) response.getResult();
				final List<String[]> serviceList = new ArrayList<String[]>();
				for (int i = 0; i < jsonArray.size(); i++) {
					String serviceType[] = new String[3];
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					boolean usedFlag = jsonObject.getBooleanValue("usedFlag");
					serviceId = jsonObject.getString("serviceId");
					serviceName = jsonObject.getString("serviceName");
					serviceType[0] = String.valueOf(usedFlag);
					serviceType[1] = serviceName;
					serviceType[2] = serviceId;
					serviceList.add(serviceType);
				}
				GridView gridview = (GridView) findViewById(R.id.gridview);
				recordList = new ArrayList<String>();
				for (int i = 0; i < serviceList.size(); i++) {
					if (serviceList.get(i)[0] != null && "true".equals(serviceList.get(i)[0])) {
						recordList.add(serviceList.get(i)[1]);
					}
				}
				drawList = new ArrayList<Integer>();
				getColorBackgroud();
				gridview.setAdapter(new OtherPayAdapter(recordList, drawList, OtherpayActivity.this));
				gridview.setOnItemClickListener(new OnItemClickListener() {
					public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
						serviceId = serviceList.get(position)[2];
						OtherPayReq otherPayReq = new OtherPayReq();
						otherPayReq.setPayAmount(initAmount);
						otherPayReq.setExtraAmount("0");
						otherPayReq.setInputAmount(initAmount);
						otherPayReq.setServiceId(serviceId);
						otherPayReq.setIsPayComingForm("0");
						otherPayReq.setServiceName(serviceName);
						intent.putExtra("otherPay", otherPayReq);
						transaction.handleIntent(intent);
						transaction.trans(new ResultListener() {
							@Override
							public void onSuccess(Response response) {
								Log.e(TAG, String.valueOf(response.result));
								intent.putExtra("reduceAmount", "0");
								intent.putExtra("response", String.valueOf(response.result));
								intent.putExtra(Constants.TRANSACTION_TYPE, TransactionTemsController.TRANSACTION_TYPE_OTHER);
								toTransactionSuccess(OtherpayActivity.this, intent);
								OtherpayActivity.this.finish();
							}

							@Override
							public void onFaild(Response response) {
								progresser.showContent();
								Log.e(TAG, String.valueOf(response.result));
								UIHelper.ToastMessage(OtherpayActivity.this, response.msg);
							}
						});
					}
				});
			}

			private void getColorBackgroud() {
				drawList.add(R.drawable.btn_selector_color_red);
				drawList.add(R.drawable.btn_selector_color_blue);
				drawList.add(R.drawable.btn_selector_color_green);
				drawList.add(R.drawable.btn_selector_color_lake_blue);
				drawList.add(R.drawable.btn_selector_color_red);
				drawList.add(R.drawable.btn_selector_color_blue);
				drawList.add(R.drawable.btn_selector_color_green);
				drawList.add(R.drawable.btn_selector_color_lake_blue);
				drawList.add(R.drawable.btn_selector_color_lake_blue);
			}

			@Override
			public void onFaild(Response response) {
				Log.e(TAG, String.valueOf(response.result));
			}
		});
	}
}
