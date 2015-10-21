package com.wizarpos.payment.padui.common;

import com.wizarpos.pay.common.base.BaseActivity;
import com.wizarpos.payment.padui.R;
import com.wizarpos.payment.padui.ui.ErrorView.OnRetryListener;
import com.wizarpos.payment.padui.ui.ProgressLayout;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class BaseViewActivity extends BaseActivity implements OnClickListener, OnRetryListener, BaseViewStateListener {

	protected Toolbar toolbar;
	private TextView tvTitle;
	protected TextView tvRight;
	private RelativeLayout rlMain;
	protected ProgressLayout progresser;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_base);
		initToolbar();
		rlMain = (RelativeLayout) findViewById(R.id.rlMain);
		progresser = (ProgressLayout) findViewById(R.id.progress);
		progresser.setRetryListener(this);
		progresser.setBaseViewStateListener(this);
	}

	protected void setMainView(int layoutId) {
		View mainView = getLayoutInflater().inflate(layoutId, null);
		setMainView(mainView);
	}

	protected void setMainView(View view) {
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
		rlMain.addView(view, params);
		progresser.showContent();
	}

	/**
	 * 初始化状态栏
	 */
	protected void initToolbar() {
		toolbar = (Toolbar) findViewById(R.id.toolbar);
		tvTitle = (TextView) toolbar.findViewById(R.id.tvToolbarTitle);
		tvRight = (TextView) toolbar.findViewById(R.id.tvToolbarRight);
		setSupportActionBar(toolbar);
	}

	/**
	 * 显示标题栏左侧返回按钮
	 */
	protected void showTitleBack() {
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	}

	/**
	 * 设置标题栏文字
	 * 
	 * @param title
	 */
	protected void setTitleText(int title) {
		tvTitle.setText(title);
		showToolbar();
	}

	protected void setTitleRightImage(int title) {
		tvRight.setBackgroundResource(title);
		tvRight.setOnClickListener(this);
	}

	/**
	 * 设置标题栏文字
	 * 
	 * @param title
	 */
	protected void setTitleText(String title) {
		tvTitle.setText(title);
		showToolbar();
	}

	private void showToolbar() {
		if (toolbar.getVisibility() != View.VISIBLE) {
			toolbar.setVisibility(View.VISIBLE);
		}
		getSupportActionBar().setDisplayShowTitleEnabled(false);
	}

	public void showTitleRightText(){
		if(tvRight.getVisibility() != View.VISIBLE){
			tvRight.setVisibility(View.VISIBLE);
		}
	}
	
	public void hideTitleRightText(){
		if(tvRight.getVisibility() == View.VISIBLE){
			tvRight.setVisibility(View.INVISIBLE);
		}
	}
	
	/**
	 * 设置标题栏右侧文字
	 * 
	 * @param title
	 */
	protected void setTitleRight(int title) {
		tvRight.setText(title);
		tvRight.setOnClickListener(this);
	}

	protected void setTitleRightVisible(boolean flag) {
		if (flag) {
			tvRight.setVisibility(View.VISIBLE);
		} else {
			tvRight.setVisibility(View.GONE);
		}
	}

	/**
	 * 设置标题栏右侧文字并添加监听
	 * 
	 * @param title
	 */
	protected void setTitleRight(CharSequence title) {
		tvRight.setText(title);
		tvRight.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == android.R.id.home) {
			onTitleBackClikced();
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tvToolbarRight:
			onTitleRightClicked();
			break;
		default:
			break;
		}
	}

	/**
	 * 点击返回按钮
	 */
	protected void onTitleBackClikced() {
		this.finish();
	}

	/**
	 * 标题栏右侧文字被点击
	 */
	protected void onTitleRightClicked() {}


	@Override
	public void onRetry() {

	}

	public void setOnClickListenerById(int viewId, View.OnClickListener listener) {
		View view = findViewById(viewId);
		try {
			if (view != null) {
				view.setOnClickListener(listener);
			}
		} catch (Exception e) {}
	}

	public void setOnClickListenerByIds(int[] viewIds, View.OnClickListener listener) {
		for (int viewId : viewIds) {
			setOnClickListenerById(viewId, listener);
		}
	}

	public void startNewActivity(Class<?> cls) {
		startActivity(new Intent(this, cls));
		finish();
	}

	/**
	 * 判断是否有网络连接
	 * 
	 * @param context
	 * @return
	 */
	public boolean isNetworkConnected(Context context) {
		if (context != null) {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
			if (mNetworkInfo != null) { return mNetworkInfo.isAvailable(); }
		}
		return false;
	}

}
