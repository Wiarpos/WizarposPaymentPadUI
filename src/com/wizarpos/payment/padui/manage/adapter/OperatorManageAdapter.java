package com.wizarpos.payment.padui.manage.adapter;

import java.util.ArrayList;
import java.util.List;

import com.wizarpos.pay.db.UserBean;
import com.wizarpos.payment.padui.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 操作员管理适配器
 * 
 * @author hong
 *
 */
public class OperatorManageAdapter extends BaseAdapter {
	private Context context;
	public List<UserBean> list = new ArrayList<UserBean>();
	
	public OperatorManageAdapter(Context context) {
		this.context = context;
	}
	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.operator_manage_adapter, null);
			holder = new ViewHolder();
			holder.operatorName = (TextView) convertView.findViewById(R.id.operator_name);
			holder.operatorRealName = (TextView) convertView.findViewById(R.id.operator_real_name);
			holder.operatorTel = (TextView) convertView.findViewById(R.id.opertor_tel);
			
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.operatorName.setText(list.get(position).getOperId());
		holder.operatorRealName.setText(list.get(position).getRealName());
		holder.operatorTel.setText(list.get(position).getPhone());
		return convertView;
	}

	private class ViewHolder {
		TextView operatorName, operatorRealName, operatorTel;
	}

	public void setDataChanged(List<UserBean> list) {
		if (list == null) {
			this.list.clear();
		} else {
			this.list = list;
		}
		this.notifyDataSetChanged();
	}

	public void addDataChanged(List<UserBean> list) {
		if (list == null) {
			this.list.clear();
		} else {
			this.list.addAll(list);
		}
		this.notifyDataSetChanged();
	}

	public void clear() {
		this.list.clear();
		this.notifyDataSetChanged();
	}

}
