package com.wizarpos.payment.padui.view.fragment;

import com.wizarpos.atool.tool.Tools;
import com.wizarpos.payment.padui.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * 键盘fragment
 * 
 * @author wu
 */
public class InputPadFragment extends Fragment implements OnClickListener, OnLongClickListener {
	
	public interface OnKeyChangedListener{
		void onTextChanged(String newStr);
	}

	private OnKeyChangedListener onKeyChanged;
	
	public void setOnTextChangedListener(OnKeyChangedListener onKeyChanged) {
		this.onKeyChanged = onKeyChanged;
	}
	
	private static final String KEYBOARDTYPE = "keyboradType";
	public static final int KEYBOARDTYPE_COMMON = 0;
	public static final int KEYBOARDTYPE_SIMPLE = 1;

	private int keyboradType = 0;


	private EditText mEditView;
	private InputType inputType;

	public enum InputType {
		TYPE_INPUT_NORMAL(1), // 正常
		TYPE_INPUT_PWD(2), // 密码
		TYPE_INPUT_MONEY(3);// 金额

		InputType(int iValue) {
			this.m_iEnumValue = iValue;
		}

		public int getValue() {
			return m_iEnumValue;
		}

		public static boolean isSame(InputType oriInputType, InputType secInputType) {
			return secInputType == oriInputType;
		}

		private final int m_iEnumValue;
	}


	public static InputPadFragment newInstance(int type) {
		InputPadFragment fragment = new InputPadFragment();
		Bundle args = new Bundle();
		args.putInt(KEYBOARDTYPE, type);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			keyboradType = getArguments().getInt(KEYBOARDTYPE);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View inputPadView = inflater.inflate(R.layout.fragment_inputpad, container, false);
		View v0 = inputPadView.findViewById(R.id.iv0);
		v0.setTag("0");
		View v1 = inputPadView.findViewById(R.id.iv1);
		v0.setTag("1");
		View v2 = inputPadView.findViewById(R.id.iv2);
		v0.setTag("2");
		View v3 = inputPadView.findViewById(R.id.iv3);
		v0.setTag("3");
		View v4 = inputPadView.findViewById(R.id.iv4);
		v0.setTag("4");
		View v5 = inputPadView.findViewById(R.id.iv5);
		v0.setTag("5");
		View v6 = inputPadView.findViewById(R.id.iv6);
		v0.setTag("6");
		View v7 = inputPadView.findViewById(R.id.iv7);
		v0.setTag("7");
		View v8 = inputPadView.findViewById(R.id.iv8);
		v0.setTag("8");
		View v9 = inputPadView.findViewById(R.id.iv9);
		v0.setTag("9");
		View vDel = inputPadView.findViewById(R.id.ivDel);
		v0.setOnClickListener(this);
		v1.setOnClickListener(this);
		v2.setOnClickListener(this);
		v3.setOnClickListener(this);
		v4.setOnClickListener(this);
		v5.setOnClickListener(this);
		v6.setOnClickListener(this);
		v7.setOnClickListener(this);
		v8.setOnClickListener(this);
		v9.setOnClickListener(this);
		vDel.setOnClickListener(this);
		
		vDel.setOnLongClickListener(this);//添加长按清空所有内容 wu@[20150902]


		return inputPadView;
	}

	public void setEditView(EditText mEditView, InputType inputType) {
		this.mEditView = mEditView;
		this.inputType = inputType;
	}

	@Override
	public void onClick(View v) {
		if (mEditView == null) { return; }
		switch (v.getId()) {
		case R.id.iv0:
			onNumber("0");
			break;
		case R.id.iv1:
			onNumber("1");
			break;
		case R.id.iv2:
			onNumber("2");
			break;
		case R.id.iv3:
			onNumber("3");
			break;
		case R.id.iv4:
			onNumber("4");
			break;
		case R.id.iv5:
			onNumber("5");
			break;
		case R.id.iv6:
			onNumber("6");
			break;
		case R.id.iv7:
			onNumber("7");
			break;
		case R.id.iv8:
			onNumber("8");
			break;
		case R.id.iv9:
			onNumber("9");
			break;
		case R.id.ivDel:
			onDel();
			break;
		default:
			break;
		}
	}

	/**
	 * 
	 * @Author:Huangweicai
	 * @Date:2015-7-28 下午9:37:32
	 * @Reason:
	 * @param num
	 */
	private void onNumber(String num) {
//		if (mEditView == null) throw (new RuntimeException("未设置EditView,请调用setEditView函数"));
		if(mEditView == null){ return; }
		if (InputType.isSame(InputType.TYPE_INPUT_NORMAL, inputType))// 正常数字
		{
			String text = Tools.inputNumber(mEditView.getText().toString(), num);
			mEditView.setText(text);
			if (onKeyChanged != null) {
				onKeyChanged.onTextChanged(text);
			}
		} else if (InputType.isSame(InputType.TYPE_INPUT_MONEY, inputType))// 金额
		{
			String text = Tools.inputMoney(mEditView.getText().toString(), num);
			mEditView.setText(text);
			if (onKeyChanged != null) {
				onKeyChanged.onTextChanged(text);
			}
		} else if (InputType.isSame(InputType.TYPE_INPUT_PWD, inputType))// 密码
		{
			
		}
	}

	/**
	 * 
	 * @Author:Huangweicai
	 * @Date:2015-7-29 上午8:55:39
	 * @Reason:删除
	 */
	private void onDel() {
		String inputStr = mEditView.getText().toString(); //除bug 可能会导致崩了 wu
		if(TextUtils.isEmpty(inputStr)){
			return;
		}
		if (InputType.isSame(InputType.TYPE_INPUT_NORMAL, inputType))// 正常数字
		{
			String text = Tools.deleteNumber(inputStr);
			mEditView.setText(text);
			if(onKeyChanged != null){
				onKeyChanged.onTextChanged(text);
			}
		} else if (InputType.isSame(InputType.TYPE_INPUT_MONEY, inputType))// 金额
		{
			String text = Tools.deleteMoney(inputStr);
			mEditView.setText(text);
			if(onKeyChanged != null){
				onKeyChanged.onTextChanged(text);
			}
		} else if (InputType.isSame(InputType.TYPE_INPUT_PWD, inputType))// 密码
		{

		}
	}

	@Override
	public boolean onLongClick(View v) {
		if(mEditView == null){
			return false;
		}
		mEditView.setText("");
		return true;
	}

}
