package com.wizarpos.payment.padui.adapter;

import java.util.ArrayList;

import com.wizarpos.payment.padui.R;
import com.wizarpos.payment.padui.model.CommonDataBean;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * @Author:Huangweicai
 * @Date:2015-7-29 下午3:29:47
 * @Reason:这里用一句话说明
 */
public class PayWayAdapter extends RecyclerView.Adapter<PayWayAdapter.MyViewHolder> {
	private Context mContext;
	private ArrayList<CommonDataBean> mList = new ArrayList<CommonDataBean>();
	public static String MSG_TAG = "msg";
	public static String IMG_TAG = "img";

	private OnPayItemClick listener;

	public PayWayAdapter(Context context) {
		mContext = context;
	}

	public interface OnPayItemClick {
		public void onPayItemClick(View v);
	}

	public void setOnItemClick(OnPayItemClick listener) {
		this.listener = listener;
	}

	class MyViewHolder extends ViewHolder implements OnClickListener {

		Button btPayWay;

		public MyViewHolder(View view) {
			super(view);
			btPayWay = (Button) view.findViewById(R.id.btPayWay);
			btPayWay.setOnClickListener(this);
		}

		@Override
		public void onClick(View v) {
			listener.onPayItemClick(v);
		}
	}

	@Override
	public int getItemCount() {
		return mList.size();
	}

	@Override
	public void onBindViewHolder(MyViewHolder holder, int position) {
		holder.btPayWay.setId(mList.get(position).getIdValue());
		if (mList.get(position).isOffline()) {// 除BUG 离线模式不更新UI wu@[20150814]
			holder.btPayWay.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.btn_selector_color_gray));
			holder.btPayWay.setEnabled(false);
		} else {
			holder.btPayWay.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.btn_selector_typebox));
			holder.btPayWay.setEnabled(true);
		}
		holder.btPayWay.setText(mList.get(position).getTitleValue());
		Drawable drawable = mContext.getResources().getDrawable(mList.get(position).getResValue());
		holder.btPayWay.setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null);
	}

	@Override
	public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		MyViewHolder holder = new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_payway, parent, false));

		return holder;
	}

	public void setDataChanged(ArrayList<CommonDataBean> mList) {
		this.mList = mList;
		notifyDataSetChanged();
	}

}
