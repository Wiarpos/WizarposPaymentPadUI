package com.wizarpos.payment.padui.statics;

import android.os.Bundle;
import android.view.View;

import com.wizarpos.payment.padui.R;
import com.wizarpos.payment.padui.common.BaseViewActivity;

public class StatisticMenuActivity extends BaseViewActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initView();
	}
	
	private void initView () {
		setMainView(R.layout.activity_statistics_menu);
		setTitleText("统计查询");
		int[] btns = {R.id.sm_btn_tranlog_detail_menu,R.id.sm_btn_tranlog_sum_menu,R.id.sm_btn_tran_log_cancel_menu};
		setOnClickListenerByIds(btns, this);
	}
	
	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.sm_btn_tranlog_detail_menu:

			break;
		case R.id.sm_btn_tranlog_sum_menu:

			break;
		case R.id.sm_btn_tran_log_cancel_menu:

			break;
		default:
			break;
		}
	}
}
