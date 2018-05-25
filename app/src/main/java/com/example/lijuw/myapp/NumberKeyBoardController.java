package com.example.lijuw.myapp;

import android.content.Context;
import android.text.Editable;
import android.text.InputType;
import android.text.Selection;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

public class NumberKeyBoardController implements OnClickListener{

	private Context mContext;
	private TextView textView1;
	private TextView textView2;
	private TextView textView3;
	private TextView textView4;
	private TextView textView5;
	private TextView textView6;
	private TextView textView7;
	private TextView textView8;
	private TextView textView9;
	private TextView tvConfirm;
	private TextView textView0;
	private TextView tvDelete;

	private EditText focusTextView;
	private View view;
	
	private boolean isKeyBoardShow;
	
	public NumberKeyBoardController(Context mContext) {
		super();
		this.mContext = mContext;
		init();
	}

	public NumberKeyBoardController(Context mContext,EditText focusTextView) {
		super();
		this.mContext = mContext;
		this.focusTextView = focusTextView;
		init();
	}

	private void init(){
		
		if(focusTextView instanceof EditText){
			final EditText edit = (EditText) focusTextView;
			edit.addTextChangedListener(new SectionLastWatcher());
//			edit.setOnTouchListener(new OnTouchListener() {
//				
//				@Override
//				public boolean onTouch(View v, MotionEvent event) {
//					int inputback = edit.getInputType();
//					edit.setInputType(InputType.TYPE_NULL);
//					
//					edit.setInputType(inputback);
//					return false;
//				}
//			});
		}
		
		view = View.inflate(mContext, R.layout.layout_number_keyboard, null);
		//view.setVisibility(View.GONE);
		textView1 = (TextView)view.findViewById( R.id.textView1 );
		textView2 = (TextView)view.findViewById( R.id.textView2 );
		textView3 = (TextView)view.findViewById( R.id.textView3 );
		textView4 = (TextView)view.findViewById( R.id.textView4 );
		textView5 = (TextView)view.findViewById( R.id.textView5 );
		textView6 = (TextView)view.findViewById( R.id.textView6 );
		textView7 = (TextView)view.findViewById( R.id.textView7 );
		textView8 = (TextView)view.findViewById( R.id.textView8 );
		textView9 = (TextView)view.findViewById( R.id.textView9 );
		tvConfirm = (TextView)view.findViewById( R.id.tv_confirm );
		textView0 = (TextView)view.findViewById( R.id.textView0 );
		tvDelete = (TextView)view.findViewById( R.id.tv_delete );
		
		textView1.setOnClickListener(this);
		textView2.setOnClickListener(this);
		textView3.setOnClickListener(this);
		textView4.setOnClickListener(this);
		textView5.setOnClickListener(this);
		textView6.setOnClickListener(this);
		textView7.setOnClickListener(this);
		textView8.setOnClickListener(this);
		textView9.setOnClickListener(this);
		textView0.setOnClickListener(this);
		tvConfirm.setOnClickListener(this);
		tvDelete.setOnClickListener(this);
	}

	public View topLayout;
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_confirm:
			if(topLayout != null)
				topLayout.setVisibility(View.GONE);
			hideKeyBoard();
			onConfirmClick();
			if(inputChangeListener != null){
				focusTextView.setTag(0);
				inputChangeListener.onTextChange(focusTextView, focusTextView.getText().toString());
			}
			break;
		case R.id.tv_delete:
			String text = focusTextView.getText().toString();
			if(!TextUtils.isEmpty(text)){
				focusTextView.setText(text.substring(0, text.length() - 1));
			}
			
			if(inputChangeListener != null){
				focusTextView.setTag(1);
				inputChangeListener.onTextChange(focusTextView, focusTextView.getText().toString());
			}
			break;
		default:
			String number = focusTextView.getText().toString().concat(((TextView)v).getText().toString());
//			if(!focusTextView.isInputMethodTarget()){
//				hideKeyBoard();
//				focusTextView.performClick();
//				return;
//			}
//			focusTextView.setInputType(InputType.TYPE_CLASS_TEXT);
			Logger.i("keyboard", "target :" + focusTextView.isInputMethodTarget());
			focusTextView.setText(number); 
			if(inputChangeListener != null){
				focusTextView.setTag(1);
				inputChangeListener.onTextChange(focusTextView,number);
			}
			
//			ViewNumber viewNumber = new ViewNumber();
//			viewNumber.tv = focusTextView;
//			viewNumber.text =  number;
//			EventBus.getDefault().post(viewNumber);
			
			break;
		}
		
	}
	
	static public class ViewNumber{
		public TextView tv;
		public String text;
	}
	
	
	public boolean isKeyBoardShow() {
		return isKeyBoardShow;
	}

	public View getView() {
		return view;
	}

	public TextView getFocusTextView() {
		return focusTextView;
	}

	public void setFocusTextView(EditText focusTextView) {
		if(focusTextView.getTag() == null && focusTextView instanceof EditText){
			EditText edit = (EditText) focusTextView;
			edit.addTextChangedListener(new SectionLastWatcher());
		}
		this.focusTextView = focusTextView;
	}

	public void hideKeyBoard(){
		isKeyBoardShow = false;
		view.setVisibility(View.GONE);
		focusTextView.clearFocus();
	}
	
	public void showKeyBoard(){
		isKeyBoardShow = true;
		if(topLayout != null)
			topLayout.setVisibility(View.VISIBLE);
//		focusTextView.setInputType(InputType.TYPE_CLASS_TEXT);
		view.setVisibility(View.VISIBLE);
	}
	
	public OnInputChangeListener inputChangeListener;
	
	
	public OnInputChangeListener getInputChangeListener() {
		return inputChangeListener;
	}

	public void setInputChangeListener(OnInputChangeListener inputChangeListener) {
		this.inputChangeListener = inputChangeListener;
	}


	public interface OnInputChangeListener{
		void onTextChange(TextView tv,String changeNum);
	}
	
	protected void onConfirmClick() {
		
	}
	
	class SectionLastWatcher implements TextWatcher{

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
			
		}

		@Override
		public void afterTextChanged(Editable s) {
			Selection.setSelection(s, s.length());
		}
		
	}
	
	
}
