package com.wizarpos.payment.padui.statics;

import com.wizarpos.payment.padui.R;
import com.wizarpos.payment.padui.common.BaseViewActivity;

import android.os.Bundle;
import android.view.View;
/**
 * 
 * @author hong
 * 交易明细查询
 *
 */
public class TransactionDetailActivity extends BaseViewActivity {
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			initView();
		}
		private void initView(){
			setMainView(R.layout.activity_transaction_detail);
			setTitleText("交易明细查询");
			findViewById(R.id.btn_transaction_detail_back).setOnClickListener(this);
			findViewById(R.id.btn_transaction_detail_prepage).setOnClickListener(this);
			findViewById(R.id.btn_transaction_detail_next_page).setOnClickListener(this);
			findViewById(R.id.btn_transaction_detail_print).setOnClickListener(this);
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
}
