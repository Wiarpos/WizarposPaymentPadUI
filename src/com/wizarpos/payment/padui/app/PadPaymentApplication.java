package com.wizarpos.payment.padui.app;

import com.wizarpos.log.util.LogEx;
import com.wizarpos.log.util.LogEx.LogLevelType;
import com.wizarpos.pay.app.CustomActivityOnCrash;
import com.wizarpos.pay.app.PaymentApplication;
import com.wizarpos.pay.common.Constants;
import com.wizarpos.pay.db.AppConfigDef;
import com.wizarpos.pay.db.AppConfigHelper;

public class PadPaymentApplication extends PaymentApplication {
	private static PadPaymentApplication application;

	@Override
	public void onCreate() {
		super.onCreate();
		application = this;
	}

	protected void setConfig() {
		CustomActivityOnCrash.install(this);
		if (Constants.DEBUGING) {
			LogEx.setLogLevel(LogLevelType.TYPE_LOG_LEVEL_DEBUG);// 显示debug日志,
																	// 便于调试
			CustomActivityOnCrash.setShowErrorDetails(true);// 显示错误详情, 便于调试
		} else {
			CustomActivityOnCrash.setShowErrorDetails(false);
		}

		AppConfigHelper.setConfig(AppConfigDef.test_use_printer, Constants.TRUE);// 打印模块
		AppConfigHelper.setConfig(AppConfigDef.sn, "WP14371000000283");
	}

	public static PadPaymentApplication getInstance() {
		return application;
	}
}
