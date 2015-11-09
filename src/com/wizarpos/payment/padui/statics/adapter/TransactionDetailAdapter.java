package com.wizarpos.payment.padui.statics.adapter;

import java.util.ArrayList;
import java.util.List;

import com.wizarpos.pay.common.utils.Logger2;
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
 *交易明细数据适配器
 * 
 * @author hong
 *
 */
public class TransactionDetailAdapter extends BaseAdapter {
	private Context context;
	public List<String[]> list = new ArrayList<String[]>();
	
	public TransactionDetailAdapter(Context context) {
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
			convertView = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.transaction_detail_adapter, null);
			holder = new ViewHolder();
			holder.transactionCode = (TextView) convertView.findViewById(R.id.transaction_code);
			holder.transactionType = (TextView) convertView.findViewById(R.id.transaction_type);
			holder.transactionDate = (TextView) convertView.findViewById(R.id.transaction_date);
			holder.transactionAmount = (TextView) convertView.findViewById(R.id.transaction_amount);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.transactionCode.setText(list.get(position)[0]);
		holder.transactionType.setText(list.get(position)[1]);
		holder.transactionDate.setText(list.get(position)[2]);
		holder.transactionAmount.setText(list.get(position)[3]);
		return convertView;
	}

	private class ViewHolder {
		TextView transactionCode, transactionType,transactionDate,transactionAmount;
	}

	public void setDataChanged(List<String[]> list) {
		if (list == null) {
			this.list.clear();
		} else {
			this.list = list;
		}
		this.notifyDataSetChanged();
	}

	public void addDataChanged(List<String[]> list) {
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
