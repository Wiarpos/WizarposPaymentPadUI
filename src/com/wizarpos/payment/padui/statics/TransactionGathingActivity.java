package com.wizarpos.payment.padui.statics;

import java.util.List;

import com.wizarpos.atool.net.volley2.Response;
import com.wizarpos.pay.common.base.BasePresenter.ResultListener;
import com.wizarpos.pay.common.utils.UIHelper;
import com.wizarpos.pay.statistics.model.GroupQueryResp;
import com.wizarpos.pay.statistics.presenter.StatisticsPresenter;
import com.wizarpos.payment.padui.R;
import com.wizarpos.payment.padui.common.BaseViewActivity;
import com.wizarpos.payment.padui.statics.adapter.TransactionSumAdapter;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

/**
 * 
 * @author hong 汇总查询
 *
 */
public class TransactionGathingActivity extends BaseViewActivity {
	private StatisticsPresenter statisticsPresenter;
	private List<String[]> recordListShow;//汇总数据
	private TransactionSumAdapter transactionSumAdapter ;
	private ListView sumListView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initView();
		initData();
	}

	private void initData() {
	 statisticsPresenter = new StatisticsPresenter(this);
	 transactionSumAdapter = new TransactionSumAdapter(this);
	 progresser.showProgress();
	 statisticsPresenter.transactionGroupQuery(new ResultListener() {
		
		@Override
		public void onSuccess(Response response) {
			progresser.showContent();
			GroupQueryResp result = (GroupQueryResp) response.result;
			recordListShow = result.getRecordListShow();
			transactionSumAdapter.list = recordListShow;
			sumListView.setAdapter(transactionSumAdapter);
		}
		
		@Override
		public void onFaild(Response response) {
			progresser.showContent();
			UIHelper.ToastMessage(TransactionGathingActivity.this, response.msg);
		}
	});
	}

	private void initView() {
		setMainView(R.layout.activity_transaction_sum);
		setTitleText("交易汇总查询");
		findViewById(R.id.btn_transaction_sum_back).setOnClickListener(this);
		findViewById(R.id.btn_transaction_sum_print).setOnClickListener(this);
		sumListView = (ListView)findViewById(R.id.sum_list_view);
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.btn_transaction_sum_back://返回
			this.finish();
			break;
		case R.id.btn_transaction_sum_print://打印
			
			break;

		default:
			break;
		}
	}
}
