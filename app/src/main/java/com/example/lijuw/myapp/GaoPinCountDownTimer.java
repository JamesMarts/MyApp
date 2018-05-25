package com.example.lijuw.myapp;

import android.os.CountDownTimer;
import android.widget.TextView;

public class GaoPinCountDownTimer extends CountDownTimer {

	private TextView showTextView;
	private CountDownCallBack callBack;
	private long mRemainTime;
	
	public GaoPinCountDownTimer(long millisInFuture, long countDownInterval) {
		super(millisInFuture, countDownInterval);

	}

	public GaoPinCountDownTimer(long millisInFuture) {
		super(millisInFuture, 1000);
	}
	
	
	@Override
	public void onTick(long millisUntilFinished) {
		mRemainTime = millisUntilFinished;
		int h = (int) (millisUntilFinished / (1000 * 60 * 60));
		int m = (int) (millisUntilFinished / (1000 * 60) % 60);
		int s = (int) (millisUntilFinished % (1000 * 60) / 1000);
		String hour = h < 10 ? "0" + h : h + "";
		String minute = m < 10 ? "0" + m : m + "";
		String second = s < 10 ? "0" + s : s + "";
		showTextView.setText(minute + "分" + second + "秒");
	}

	@Override
	public void onFinish() {
		mRemainTime = 0;
		if(callBack != null){
			callBack.onFinish();
		}
	}

	public TextView getShowTextView() {
		return showTextView;
	}

	public void setShowTextView(TextView showTextView) {
		this.showTextView = showTextView;
	}

	public interface CountDownCallBack{
		void onFinish();
	}

	public CountDownCallBack getCallBack() {
		return callBack;
	}

	public void setCallBack(CountDownCallBack callBack) {
		this.callBack = callBack;
	}

	public long getmRemainTime() {
		return mRemainTime;
	}
	
	
}
