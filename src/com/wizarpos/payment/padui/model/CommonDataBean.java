package com.wizarpos.payment.padui.model;

import java.io.Serializable;

/**
 * @Author:Huangweicai
 * @Date:2015-8-12 下午3:06:58
 * @Reason:普通对象属性
 */
public class CommonDataBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private String titleValue;
	private int resValue;
	private int idValue;
	private boolean isOffline;// 除BUG 解决离线在线状态切换导致界面不刷新的问题 wu@[20150814]

	public String getTitleValue() {
		return titleValue;
	}

	public void setTitleValue(String titleValue) {
		this.titleValue = titleValue;
	}

	public int getResValue() {
		return resValue;
	}

	public void setResValue(int resValue) {
		this.resValue = resValue;
	}

	public int getIdValue() {
		return idValue;
	}

	public void setIdValue(int idValue) {
		this.idValue = idValue;
	}

	public boolean isOffline() {
		return isOffline;
	}

	public void setOffline(boolean isOffline) {
		this.isOffline = isOffline;
	}

}
