package com.wizarpos.payment.padui.statics;

import java.util.ArrayList;
import java.util.List;

import com.wizarpos.atool.net.volley2.Response;
import com.wizarpos.pay.common.base.BasePresenter.ResultListener;
import com.wizarpos.pay.common.utils.UIHelper;
import com.wizarpos.pay.statistics.presenter.StatisticsPresenter;
import com.wizarpos.payment.padui.R;
import com.wizarpos.payment.padui.common.BaseViewActivity;
import com.wizarpos.payment.padui.statics.adapter.TransactionDetailAdapter;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
/**
 * 
 * @author hong
 * 交易明细查询
 *
 */
public class TransactionDetailActivity extends BaseViewActivity {
	 private StatisticsPresenter presenter;
	 private ArrayList<String[]> recordListShow;
	 private TransactionDetailAdapter detailAdapter;
	 private ListView listView;
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			initView();
			initData();
		}
		private void initView(){
			setMainView(R.layout.activity_transaction_detail);
			setTitleText("交易明细查询");
			presenter = new StatisticsPresenter(this);
			findViewById(R.id.btn_transaction_detail_back).setOnClickListener(this);
			findViewById(R.id.btn_transaction_detail_prepage).setOnClickListener(this);
			findViewById(R.id.btn_transaction_detail_next_page).setOnClickListener(this);
			findViewById(R.id.btn_transaction_detail_print).setOnClickListener(this);
			listView = (ListView)findViewById(R.id.show_data_detail);
		}
		@Override
		public void onClick(View v) {
			super.onClick(v);
			switch (v.getId()) {
			case R.id.btn_transaction_detail_back://返回
				this.finish();
				break;
			case R.id.btn_transaction_detail_prepage://上一页
				
				break;
			case R.id.btn_transaction_detail_next_page://下一页
				
				break;
			case R.id.btn_transaction_detail_print://打印
				
				break;

			default:
				break;
			}
		}
		private void initData() {
			progresser.showProgress();
			detailAdapter = new TransactionDetailAdapter(this);
			presenter.transactionDetialQuery(new ResultListener() {
				
				@Override
				public void onSuccess(Response response) {
				progresser.showContent();
				@SuppressWarnings("unchecked")
				List<String[]> detialRecords = (ArrayList<String[]>) response.result;
				long totalPage = presenter.getTotalPage();// 总页数
					detailAdapter.list = detialRecords;
					listView.setAdapter(detailAdapter);
				}
				
				@Override
				public void onFaild(Response response) {
					progresser.showContent();
					UIHelper.ToastMessage(TransactionDetailActivity.this, response.msg);
					
				}
			});
		}
}
