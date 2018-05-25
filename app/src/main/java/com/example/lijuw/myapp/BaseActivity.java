package com.example.lijuw.myapp;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import javax.xml.transform.ErrorListener;

public class BaseActivity extends Activity implements View.OnClickListener {

	protected String TAG = "BaseActivity";

	private RelativeLayout mTitleView;

	private LinearLayout mBodyView;
	public Button mCenterButton, mRightButton;
	public ImageButton mImgRightBtn, mLeftButton;
	public TextView mRight_tv;
	public ImageButton mImgSecRightBtn;
	public ImageView mLeftDivider, mCenterDivider, mRightDivider, mRightSecDivider;
	public LinearLayout mCenterTitleLayout;
	public TextView mCentertTitleTextView;
	public ImageView mCenterTitleTextViewImage;

	public ImageView no_data_alert_panel;// 空数据提醒

	boolean isShowTitle = true;// 是否显示标题头 默认为true
	protected Context mContext;
	private LinearLayout ll_no_data_panel;

	private boolean isEnableShake = false;
	private ShakeListener shake;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.setContentView(R.layout.activity_base);
		TAG = this.getClass().getSimpleName();
		mContext = getApplicationContext();
		LocalActivityMgr.getInstance().pushActivity(this);
		initView();
	}

	private void initView() {

		loadingProgressBar = (ProgressBar) findViewById(R.id.progressBar1);

		mTitleView = (RelativeLayout) findViewById(R.id.title_view);
		mBodyView = (LinearLayout) findViewById(R.id.base_main_layout);
		mLeftButton = (ImageButton) findViewById(R.id.left_button);
		mCenterButton = (Button) findViewById(R.id.center_button);
		mRightButton = (Button) findViewById(R.id.right_button);
		mImgRightBtn = (ImageButton) findViewById(R.id.right_imgbtn);

		mRight_tv = (TextView) findViewById(R.id.right_tv);

		mImgSecRightBtn = (ImageButton) findViewById(R.id.right_sec_imgbtn);
		mLeftDivider = (ImageView) findViewById(R.id.left_divider);
		mCenterDivider = (ImageView) findViewById(R.id.center_divider);
		mRightDivider = (ImageView) findViewById(R.id.right_divider);
		mRightSecDivider = (ImageView) findViewById(R.id.right_sec_divider);

		mCenterTitleLayout = (LinearLayout) findViewById(R.id.center_layout);
		mCentertTitleTextView = (TextView) findViewById(R.id.center_textview);
		mCenterTitleTextViewImage = (ImageView) findViewById(R.id.center_textview_image);

		no_data_alert_panel = (ImageView) findViewById(R.id.no_data_alert_panel);
		ll_no_data_panel = (LinearLayout) findViewById(R.id.ll_no_data_panel);

		mLeftButton.setOnClickListener(this);
	}

	/**
	 * 启用摇一摇功能
	 */
	protected void enableShake() {
		isEnableShake = true;
	}

	protected void disableShake() {
		isEnableShake = false;
	}



	@Override
	protected void onResume() {
		super.onResume();
		if (isEnableShake) {
			shake = new ShakeListener(this);
			shake.setOnShakeListener(new ShakeListener.OnShakeListener() {

				@Override
				public void onShake() {
					BaseActivity.this.onShake();
				}
			});
		}

	}

	protected void onShake() {

	}

	@Override
	protected void onPause() {
		super.onPause();
		if (shake != null)
			shake.pause();

	}


	@Override
	protected void onDestroy() {
		super.onDestroy();
		LocalActivityMgr.getInstance().removeActivity(this);
	}

	public void setContentView(int view) {
		View bodyView = View.inflate(this,view, null);
		setContentView(bodyView);
	}

	public MainApplication getApp() {
		return ((MainApplication) getApplication());
	}


	public void setContentView(View bodyView) {
		if (mBodyView != null) {
			mBodyView.removeAllViews();
		}
		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
		if (isShowTitle) {
			int titleHeight = getResources().getDimensionPixelSize(R.dimen.lt_37_dip);
			bodyView.setPadding(bodyView.getPaddingLeft(), titleHeight, bodyView.getPaddingRight(), bodyView.getPaddingBottom());
		}
		mBodyView.addView(bodyView, layoutParams);
	}

	public ImageButton getLeftButton() {
		return mLeftButton;
	}

	public Button getRightButton() {
		return mRightButton;
	}

	public ImageButton getSecRightImageBtn() {
		return mImgSecRightBtn;
	}

	public ImageButton getRightImageBtn() {
		return mImgRightBtn;
	}

	public RelativeLayout getTitleView() {
		return mTitleView;
	}


	public Button getCenterButton() {
		return mCenterButton;
	}

	public TextView getCenterTextView() {
		return mCentertTitleTextView;
	}

	public TextView getRightTextView() {
		return mRight_tv;
	}

	public ImageView getCenterImageView() {
		return mCenterTitleTextViewImage;
	}

	/**
	 * 设置标题头是否显示
	 * 
	 * @param visibility
	 */
	public void setTitleViewVisibility(boolean visibility) {
		this.isShowTitle = visibility;
		if (visibility) {
			mTitleView.setVisibility(View.VISIBLE);
		} else {
			mTitleView.setVisibility(View.GONE);
		}
	}

	public void setTitleLeftButtonVisibility(boolean visibility) {
		if (visibility) {
			mLeftButton.setOnClickListener(this);
			mLeftButton.setVisibility(View.VISIBLE);
		} else {
			mLeftButton.setVisibility(View.GONE);
			mLeftDivider.setVisibility(View.GONE);
		}

	}


	public void setCenterTitle(String titleStr) {
		mCentertTitleTextView.setText(titleStr);
	}

	public void setCenterTitle(int resId) {
		mCentertTitleTextView.setText(resId);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.left_button:
			onLeftButtonClick();
			break;
		}
	}

	/**
	 * 左箭头回退事件
	 */
	protected void onLeftButtonClick() {
		finish();
//		overridePendingTransition(R.anim.umeng_fb_slide_in_from_left, R.anim.umeng_fb_slide_out_from_right);
	}


	/**
	 * 显示进度对话框
	 */
	public void showLoadingState() {
		showLoadingState("");
	}

	/**
	 * 显示进度对话框
	 */
	public void showLoadingState(String msg) {
		loadingProgressBar.setVisibility(View.VISIBLE);
	}

	/**
	 * 隐藏进度对话框
	 */
	public void stopLoadingState() {
		loadingProgressBar.setVisibility(View.GONE);
	}

	public void showNoDataAlert() {
		ll_no_data_panel.setVisibility(View.VISIBLE);
	}

	public void dismissNoDataAlert() {
		ll_no_data_panel.setVisibility(View.GONE);
	}







	protected void initRedTitleStyle() {
		mLeftButton.setImageResource(R.drawable.btn_white_back);
		getTitleView().setBackgroundColor(getResources().getColor(R.color.colorPrimary));
		getCenterTextView().setTextColor(Color.WHITE);
	}

	private ProgressBar loadingProgressBar;


	protected void performRotateAnima(View v, float fromDegrees, float toDegrees) {
		v.clearAnimation();
		RotateAnimation ra = new RotateAnimation(fromDegrees, toDegrees, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		ra.setDuration(200);
		ra.setFillAfter(true);
		v.startAnimation(ra);
	}
	




	
}
