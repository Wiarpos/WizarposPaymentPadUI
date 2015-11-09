package com.wizarpos.payment.padui.manage;

import java.util.ArrayList;
import java.util.List;

import com.wizarpos.pay.common.utils.UIHelper;
import com.wizarpos.pay.db.UserBean;
import com.wizarpos.pay.manager.presenter.CashierManager;
import com.wizarpos.payment.padui.R;
import com.wizarpos.payment.padui.common.BaseViewActivity;
import com.wizarpos.payment.padui.manage.adapter.OperatorManageAdapter;

import android.app.ActionBar.LayoutParams;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;
import u.aly.au;
import u.aly.u;
/**
 * 
 * @author hong
 *	操作员管理界面
 */
public class OperatorManageActivity extends BaseViewActivity {
	private List<UserBean> recordList; //所有操作数据
	private CashierManager cashierManager;
	private OperatorManageAdapter operatorManageAdapter;
	private ListView listView;
	private View optPopupView;
	private View subPopupView;
	private PopupWindow mOptPopupWindow;//显示操作员信息
	private PopupWindow mSubPopupWindow;//提示是否是确定删除提示
	private UserBean showBean;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		cashierManager = new CashierManager(this);
		operatorManageAdapter = new OperatorManageAdapter(this);
		initView();
	}
	private void initView(){
		setMainView(R.layout.activity_operator_manage);
		setTitleText("操作员管理");
		listView = (ListView)findViewById(R.id.cashier_data_list);
		initPopWindow();
		initData();
		initButton();

	}
	private void initData(){
		recordList = new ArrayList<UserBean>();
		recordList = cashierManager.getAllCashier();
		operatorManageAdapter.addDataChanged(recordList);
		listView.setAdapter(operatorManageAdapter);
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				showBean = recordList.get(position);
			}
		});
	}
	private void initButton() {
		findViewById(R.id.btn_prepage).setOnClickListener(this);
		findViewById(R.id.btn_nextpage).setOnClickListener(this);
		findViewById(R.id.btn_add_cashier).setOnClickListener(this);
		findViewById(R.id.btn_modify_cashierinfo).setOnClickListener(this);
		findViewById(R.id.btn_del_cashierinfo).setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.btn_prepage://上一页
			
			break;
		case R.id.btn_nextpage://下一页
			
			break;
		case R.id.btn_add_cashier://新增
			String add = "新增";
			showOptPopWindow(add);
			break;
		case R.id.btn_modify_cashierinfo://修改
			String modify = "修改";
			showOptPopWindow(modify);
//			cashierManager.updateCashier("00000","1111","2222","22222",1);
			break;
		case R.id.btn_del_cashierinfo://删除
			showSubPopWindow();
//			cashierManager.removeCashier("0000");
			break;

		default:
			break;
		}
	}
	private void initPopWindow() {
		optPopupView = getLayoutInflater().inflate(R.layout.popup_user_mange_opt, null);
		optPopupView.setFocusable(true);
		mOptPopupWindow = new PopupWindow(optPopupView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, true);
		mOptPopupWindow.setTouchable(true);
		mOptPopupWindow.setFocusable(true);
		mOptPopupWindow.setOutsideTouchable(true);
		mOptPopupWindow.setOnDismissListener(new OnDismissListener() {
			public void onDismiss() {
				backgroundAlpha(1f);
			}
		});
		EditText userNameEt = (EditText)optPopupView.findViewById(R.id.etUserName);
		EditText passwordEt = (EditText)optPopupView.findViewById(R.id.etPassword);
		EditText realNameEt = (EditText)optPopupView.findViewById(R.id.etRealName);
		EditText telEt = (EditText)optPopupView.findViewById(R.id.etPhone);
		CheckBox authrityCb = (CheckBox)optPopupView.findViewById(R.id.manage_checkBox);
		//修改
	if (null != showBean) {
			userNameEt.setText(showBean.getOperId());
			passwordEt.setText(showBean.getPassword());
			realNameEt.setText(showBean.getRealName());
			telEt.setText(showBean.getPhone());
			if (1==showBean.getType()) {
				authrityCb.setChecked(true);
			}else {
				authrityCb.setChecked(false);
			}
		}
		//添加
		final UserBean addBean = new UserBean();
		addBean.setOperId(userNameEt.getText().toString());
		addBean.setPassword(passwordEt.getText().toString());
		addBean.setRealName(realNameEt.getText().toString());
		addBean.setPhone(telEt.getText().toString());
		if (authrityCb.isChecked()) {
			addBean.setType(1);
		}
		Button  btnCancle = (Button)optPopupView.findViewById(R.id.btnCancel);//取消
		btnCancle.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mOptPopupWindow.dismiss();
			}
		});
		Button  btnConfirm = (Button)optPopupView.findViewById(R.id.btnOK);//确定
		btnConfirm.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mOptPopupWindow.dismiss();
				cashierManager.addCashier(addBean);
				recordList.add(addBean);
				operatorManageAdapter.clear();
				operatorManageAdapter.addDataChanged(recordList);
			}
		});
		
		subPopupView = getLayoutInflater().inflate(R.layout.popup_user_mange_submit, null);
		subPopupView.setFocusable(true);
		mSubPopupWindow = new PopupWindow(subPopupView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, true);
		mSubPopupWindow.setTouchable(true);
		mSubPopupWindow.setFocusable(true);
		mSubPopupWindow.setOutsideTouchable(true);
		mSubPopupWindow.setOnDismissListener(new OnDismissListener() {
			public void onDismiss() {
				backgroundAlpha(1f);
			}
		});
		Button  btnRemindCancle = (Button)subPopupView.findViewById(R.id.btnCancelSubmit);//取消
		btnRemindCancle.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mSubPopupWindow.dismiss();
				
			}
		});
		Button  btnRemindOk = (Button)subPopupView.findViewById(R.id.btnOKSubmit);//确定
		btnRemindOk.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mSubPopupWindow.dismiss();
				if (null != showBean) {
					cashierManager.removeCashier(showBean.getOperId());
					recordList.remove(showBean);
					operatorManageAdapter.setDataChanged(recordList);
				}
			}
		});
	}
	/**
	 * 设置添加屏幕的背景透明度
	 * 
	 * @param bgAlpha
	 */
	public void backgroundAlpha(float bgAlpha) {
		WindowManager.LayoutParams lp = getWindow().getAttributes();
		lp.alpha = bgAlpha; // 0.0-1.0
		getWindow().setAttributes(lp);
	}
	private void showOptPopWindow(String title) {
		TextView titleTv = (TextView)optPopupView.findViewById(R.id.user_manage_title_text);
		titleTv.setText(title);
		mOptPopupWindow.showAtLocation(getRootLayout(), Gravity.CENTER, 0, 0);
		backgroundAlpha(0.5f);
	}

	private void showSubPopWindow() {
		mSubPopupWindow.showAtLocation(getRootLayout(), Gravity.CENTER, 0, 0);
		backgroundAlpha(0.5f);
	}
	private View getRootLayout() {
		return findViewById(android.R.id.content);
	}
}
