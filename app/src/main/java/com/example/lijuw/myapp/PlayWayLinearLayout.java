package com.example.lijuw.myapp;

import java.util.Vector;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;


public class PlayWayLinearLayout extends LinearLayout implements android.view.View.OnClickListener{

	private Context mContext;
	private int mChildWidth;
	private int mChildHeight;
	private String playWays[];
	private int playWayIds[];
	private Vector<TextView> mVecTv = new Vector<TextView>();
	private int preIndex;// 上一个的选择
	private PlayWayView.IPlayWayChangeCallBack callBack;
	private String unSelColor = "#FF000000";  
	private String selColor = "#FFFF0000";
	private int defaultBgId = 0;
	private int tickId = R.drawable.rect_line_bottom;

	public PlayWayLinearLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public PlayWayLinearLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public PlayWayLinearLayout(Context context) {
		super(context);
		init(context);
	}

	private void init(Context context) {
		mContext = context;
		setOrientation(LinearLayout.HORIZONTAL);
		mChildHeight = getResources().getDimensionPixelSize(R.dimen.lt_40_dip);
	}

	public void initView(int allSzie,int preIndex,String[] playWays, int[] playWayIds) {
		if(allSzie == 0 || allSzie == 1){
			return;
		}
		
		this.playWayIds = playWayIds;
		this.playWays = playWays;
		this.preIndex = preIndex;
		
		WindowManager manager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
		int screenWidth = manager.getDefaultDisplay().getWidth();
		if(allSzie >= 4){
			mChildWidth = screenWidth / 4;
		}else{
			mChildWidth = screenWidth / allSzie;
		}
		
		initWidget();
	}
	
	private void initWidget(){
		String[] playWayNames = this.playWays;
		int len = playWayNames.length;
		for (int i = 0; i < len; i++) {
			TextView tv = new TextView(mContext);
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(mChildWidth, mChildHeight);
			params.gravity = Gravity.CENTER;
			tv.setText(playWayNames[i]);
			tv.setGravity(Gravity.CENTER);
			tv.setSingleLine(true);
			tv.setLayoutParams(params);
			tv.setTextSize(ComTools.convertTextSize(mContext, R.dimen.lt_14_dip));
			tv.setId(playWayIds[i]);
			tv.setOnClickListener(this);
			tv.setBackgroundResource(defaultBgId);
			if(preIndex == i){
				tv.setTag(true);
				tv.setTextColor(Color.parseColor(selColor));
				Drawable drawable = getResources().getDrawable(tickId);
				drawable.setBounds(0, 0, mChildWidth, drawable.getIntrinsicHeight());
				tv.setCompoundDrawables(null, null, null, drawable);
				//tv.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0,tickId);
			}else{
				tv.setTag(false);
				tv.setTextColor(Color.parseColor(unSelColor));
				tv.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0,defaultBgId);
			}
			
				if(i !=0 ){
					View v = new View(mContext);
					v.setBackgroundColor(Color.GRAY);
					LayoutParams params2 = new LinearLayout.LayoutParams(getResources().getDimensionPixelSize(R.dimen.lt_1_dip), LayoutParams.MATCH_PARENT);
					params2.topMargin = 5;
					params2.bottomMargin = 5;
					v.setLayoutParams(params2);
					addView(v);
				}
			
			mVecTv.add(tv);
			addView(tv);
		}
	}
	
	public void setSelect(int index){
		TextView v = mVecTv.get(index);
		v.setTextColor(Color.parseColor(selColor));
		Drawable drawable = getResources().getDrawable(tickId);
		drawable.setBounds(0, 0, mChildWidth, drawable.getIntrinsicHeight());
		v.setCompoundDrawables(null, null, null, drawable);
		v.setTag(true);

		if (index != preIndex) {
			TextView preV = mVecTv.get(preIndex);
			preV.setTextColor(Color.parseColor(unSelColor));
			preV.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0,defaultBgId);
			preV.setTag(false);
		}
		preIndex = index;
	}

	public void setOnPlayWayChangeListener(PlayWayView.IPlayWayChangeCallBack callback) {
		this.callBack = callback;
	}

	@Override
	public void onClick(View v) {
		boolean isSameClick = false;

		int index = mVecTv.indexOf(v);
		if (!(Boolean) v.getTag()) {
			isSameClick = false;
			setSelect(index);
			v.setTag(true);
		} else {
			isSameClick = true;
		}
		if (callBack != null) {
			callBack.playWayChange(isSameClick, v.getId(), ((TextView) v).getText().toString());
		}
	}
	
}
