package com.wizarpos.payment.padui.setting;

import com.wizarpos.payment.padui.R;
import com.wizarpos.payment.padui.common.BaseViewActivity;

import android.os.Bundle;
import android.view.View;
/**
 * 
 * @author hong
 * 数据同步
 *
 */
public class OfflineSyncActivity extends BaseViewActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initView();
	}
	private void initView(){
		setMainView(R.layout.activity_offline_sync);
		setTitleText("离线同步");
		
	}
	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.btn_offline_sync_back://返回
			OfflineSyncActivity.this.finish();
			break;
		case R.id.btn_sync_update://同步更新
			
			break;
		case R.id.btn_offline_upload://离线上传
			
			break;

		default:
			break;
		}
	}
}
