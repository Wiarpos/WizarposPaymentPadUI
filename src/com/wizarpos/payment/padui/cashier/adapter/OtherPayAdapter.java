package com.wizarpos.payment.padui.cashier.adapter;

import java.util.List;

import com.wizarpos.payment.padui.R;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


public class OtherPayAdapter extends BaseAdapter {

	LayoutInflater inflater = null;
	private Context context = null;
	// 图片
	private int imgs = 0;
	private List<String> recordList = null;
	private List<Integer> drawList = null;

	public OtherPayAdapter(List<String> recordList, List<Integer> drawList, Context context) {
		this.recordList = recordList;
		this.drawList = drawList;
		this.context = context;
	}

	public int getCount() {
		return recordList.size();
	}

	public Object getItem(int item) {
		return item;
	}

	public long getItemId(int id) {
		return id;
	}

	class ViewHolder {
		TextView textView;
	}

	ViewHolder holder = null;

	// 创建View方法
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(R.layout.night_item, null);
			holder = new ViewHolder();
			holder.textView = (TextView) convertView.findViewById(R.id.TextView);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.textView.setBackgroundResource(drawList.get(position));// 为TextView设置图片资源
		holder.textView.setText(recordList.get(position));
		holder.textView.setTextColor(Color.parseColor("#FFFFFF"));
		holder.textView.setTextSize(35);
		holder.textView.setGravity(Gravity.CENTER);
		return convertView;
	}

}
