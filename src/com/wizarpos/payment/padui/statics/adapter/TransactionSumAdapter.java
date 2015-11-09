package com.wizarpos.payment.padui.statics.adapter;

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
 * 交易汇总适配器
 * 
 * @author hong
 *
 */
public class TransactionSumAdapter extends BaseAdapter {
	private Context context;
	public List<String[]> list = new ArrayList<String[]>();
	
	public TransactionSumAdapter(Context context) {
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
			convertView = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.transaction_sum_adapter, null);
			holder = new ViewHolder();
			holder.transactionType = (TextView) convertView.findViewById(R.id.sum_transaction_type);
			holder.transactionAccount = (TextView) convertView.findViewById(R.id.sum_transaction_account);
			holder.transactionAmount = (TextView) convertView.findViewById(R.id.sum_transaction_amount);
			
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.transactionType.setText(list.get(position)[0]);
		holder.transactionAccount.setText(list.get(position)[1]);
		holder.transactionAmount.setText(list.get(position)[2]);
		return convertView;
	}

	private class ViewHolder {
		TextView transactionType, transactionAccount, transactionAmount;
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
