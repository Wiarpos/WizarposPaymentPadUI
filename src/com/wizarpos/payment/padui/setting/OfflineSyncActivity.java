package com.wizarpos.payment.padui.setting;

import java.util.ArrayList;
import java.util.List;

import com.wizarpos.atool.net.volley2.Response;
import com.wizarpos.pay.common.Constants;
import com.wizarpos.pay.common.base.BasePresenter.ResultListener;
import com.wizarpos.pay.common.utils.UIHelper;
import com.wizarpos.pay.db.AppStateDef;
import com.wizarpos.pay.db.AppStateManager;
import com.wizarpos.pay.db.CashPayRepair;
import com.wizarpos.pay.manager.presenter.OfflineHelper;
import com.wizarpos.payment.padui.R;
import com.wizarpos.payment.padui.common.BaseViewActivity;
import com.wizarpos.payment.padui.setting.adapter.OfflineTransactionDataAdapter;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
/**
 * 
 * @author hong
 * 数据同步
 *
 */
public class OfflineSyncActivity extends BaseViewActivity {
	 private OfflineTransactionDataAdapter offlineAdapter;
	 private ListView listView;
	 private OfflineHelper offlineHelper;
	 private List<CashPayRepair> listPay ;//显示离线数据
	 private boolean net;//网络是否正常
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initView();
		initData();
	}
	private void initView(){
		setMainView(R.layout.activity_offline_sync);
		setTitleText("离线同步");
		listView = (ListView)findViewById(R.id.offline_list_view);
	}
	private void initData() {
		offlineHelper = new OfflineHelper(this);
		offlineAdapter = new OfflineTransactionDataAdapter(this);
		listPay = new ArrayList<CashPayRepair>();
		listPay = offlineHelper.getOfflineTransaction();
		offlineAdapter.list = listPay;
		listView.setAdapter(offlineAdapter);
		net = Constants.TRUE.equals(AppStateManager.getState(AppStateDef.isOffline));
	}
	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.btn_offline_sync_back://返回
			OfflineSyncActivity.this.finish();
			break;
		case R.id.btn_sync_update://同步更新
			update(net);
			break;
		case R.id.btn_offline_upload://离线上传
			upload(net);
			break;
		default:
			break;
		}
	}
	/**
	 * 更新
	 * @param net
	 */
	private void update(boolean net) {
		if (net) {
			UIHelper.ToastMessage(OfflineSyncActivity.this, "请检查网络");
			return;
		}
		progresser.showProgress();
		offlineHelper.update(new ResultListener() {
			@Override
			public void onSuccess(Response response) {
				progresser.showContent();
				UIHelper.ToastMessage(OfflineSyncActivity.this, response.msg);
			}

			@Override
			public void onFaild(Response response) {
				progresser.showContent();
				UIHelper.ToastMessage(OfflineSyncActivity.this, response.msg);
			}
		});
		
	}
	/**
	 * 上传
	 * @param net
	 */
	private void upload(boolean net) {
		if (net) {
			UIHelper.ToastMessage(OfflineSyncActivity.this, "请检查网络");
			return ;
		}
		progresser.showProgress();
		offlineHelper.upload(new ResultListener() {
			@Override
			public void onSuccess(Response response) {
				progresser.showContent();
				UIHelper.ToastMessage(OfflineSyncActivity.this, response.msg);
				listPay.removeAll(listPay);
				offlineAdapter.clear();
			}

			@Override
			public void onFaild(Response response) {
				progresser.showContent();
				UIHelper.ToastMessage(OfflineSyncActivity.this, response.msg);
			}
		});
		
	}
}
