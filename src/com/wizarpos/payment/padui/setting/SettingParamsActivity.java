package com.wizarpos.payment.padui.setting;

import com.wizarpos.pay.db.AppConfigDef;
import com.wizarpos.pay.db.AppConfigHelper;
import com.wizarpos.pay.setting.presenter.AppConfiger;
import com.wizarpos.payment.padui.R;
import com.wizarpos.payment.padui.common.BaseViewActivity;
import com.wizarpos.payment.padui.setting.basicalinfo.AuthCodeActivity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * 
 * @author hong 设置基本参数
 *
 */
public class SettingParamsActivity extends BaseViewActivity {
	private TextView hostTv;//主机
	private TextView portTv;//端口
	private TextView vensionTv;//版本号
	private AppConfiger appConfiger;
	private String url = "";
	private String port = "";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initView();
	}

	private void initView() {
		setMainView(R.layout.activity_basical_info_setting);
		setTitleText("基本信息设置");
		findViewById(R.id.btn_basical_info_back).setOnClickListener(this);
		findViewById(R.id.btn_basical_info_confirm).setOnClickListener(this);
		findViewById(R.id.btn_basical_info_next_page).setOnClickListener(this);
		hostTv = (TextView) findViewById(R.id.hostIp);
		portTv = (TextView) findViewById(R.id.hostPort);
		vensionTv = (TextView) findViewById(R.id.version_name);
		url = AppConfigHelper.getConfig(AppConfigDef.ip);
		port = AppConfigHelper.getConfig(AppConfigDef.port);
		hostTv.setText(url);
		portTv.setText(port);
		vensionTv.setText(getVersion());
	}
	//获取版本号
	private String getVersion() {
		String versionName = "";
		try {
			PackageInfo pkg = getPackageManager().getPackageInfo(getApplication().getPackageName(), 0);
			versionName = pkg.versionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return versionName;
	}
	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.btn_basical_info_back:// 返回
			this.finish();
			break;
		case R.id.btn_basical_info_confirm:// 确定
			this.finish();
			break;
		case R.id.btn_basical_info_next_page:// 下一个
			startActivity(new Intent(SettingParamsActivity.this, AuthCodeActivity.class));
			this.finish();
			break;

		default:
			break;
		}
	}
}
