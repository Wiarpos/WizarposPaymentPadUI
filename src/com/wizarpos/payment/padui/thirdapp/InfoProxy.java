package com.wizarpos.payment.padui.thirdapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.alibaba.fastjson.JSONObject;
import com.wizarpos.atool.net.volley2.Response;
import com.wizarpos.pay.db.AppConfigDef;
import com.wizarpos.pay.db.AppConfigHelper;
import com.wizarpos.pay.db.UserDao;

/**
 * @author wu
 * 
 */
public class InfoProxy extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent intent = new Intent();
		String name = getIntent().getStringExtra("name");
		String passwd = getIntent().getStringExtra("passwd");
		if (TextUtils.isEmpty(name) || TextUtils.isEmpty(passwd) || !UserDao.getInstance().isUser(name, passwd)) {
			intent.putExtra("response", JSONObject.toJSONString(new Response(1, "用户名密码错误")));
			setResult(RESULT_CANCELED, intent);
			this.finish();
		}
		JSONObject j = new JSONObject();
		j.put("wi", AppConfigHelper.getConfig(AppConfigDef.weixin_app_id));
		j.put("wk", AppConfigHelper.getConfig(AppConfigDef.weixin_app_key));
		j.put("ws", AppConfigHelper.getConfig(AppConfigDef.weixin_app_secret));
		intent.putExtra("response", JSONObject.toJSONString(new Response(1, "success", j.toJSONString())));
		setResult(RESULT_OK, intent);
		this.finish();
	}
}
