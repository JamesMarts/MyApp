package com.example.lijuw.myapp;

import java.util.Vector;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * @author shouli.luo
 * @time 2013-3-28 下午01:01:20
 * @introduce 玩法控件
 */
public class PlayWayView extends LinearLayout implements OnClickListener {

	private String playWays[];
	private int playWayIds[];
	private String strPlayWayIds[];
	private int allSize;
	private Vector<TextView> mVecTv = new Vector<TextView>();// 存放所有球
	private Context mContext;

	private int itemWidth, itemHeight;

	private int preIndex = 0;// 上一个的选择

	private static int LINE_SIZE = 3;// 默认一行显示的个数

	private IPlayWayChangeCallBack callBack;
	private IPlayWayChangeCallBackStr callBackStr;

	private int defaultBgId = R.drawable.rect_dgray_solid;
	// private int pressBgId = R.drawable.grid_bg_pressed;
	// private int pressBgId = R.drawable.get_code_p;
	private int tickId = R.drawable.btn_card_select_pressed;

	private String unSelColor = "#484848";
	private String selColor = "#ff0029";

	private int playWaySplitIndex = 0;

	private static final String TAG = "PlayWayView";

	public PlayWayView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.mContext = context;
//		int widthPixels = mContext.getResources().getDisplayMetrics().widthPixels;
//		itemWidth = widthPixels * 3 / 10;
//		itemHeight = itemWidth >> 1;
		itemWidth = getResources().getDimensionPixelSize(R.dimen.lt_95_dip);
		itemHeight = getResources().getDimensionPixelSize(R.dimen.lt_33_dip);
	}

	public void initView(int index, int allSzie, String[] playWays, int[] playWayIds) {
		this.playWaySplitIndex = index;
		this.allSize = allSzie;
		this.playWays = playWays;
		this.playWayIds = playWayIds;
		if (playWaySplitIndex != 0) {
			initWidget();
		} else {
			initWidght(allSzie);
		}
	}
	public void initView(int index, int allSzie, String[] playWays, String[] playWayIds) {
		this.playWaySplitIndex = index;
		this.allSize = allSzie;
		this.playWays = playWays;
		this.strPlayWayIds = playWayIds;
		if (playWaySplitIndex != 0) {
			initWidget();
		} else {
			initWidght(allSzie);
		}
	}

	/**
	 * @param splitIndex
	 * @param allSzie
	 * @param playWays
	 * @param playWayIds
	 * @param str1
	 * @param str2
	 */
	public void initView2(int splitIndex, int allSzie, String[] playWays, int[] playWayIds, String str1, String str2) {
		this.playWaySplitIndex = splitIndex;
		this.allSize = allSzie;
		this.playWays = playWays;
		this.playWayIds = playWayIds;
		if (playWaySplitIndex != 0) {
			initWidget(str1, str2);
		} else {
			initWidght(allSzie);
		}
	}

	/**
	 * qiufeng add
	 * 
	 * @param str1
	 *            类别字符串1
	 * @param str2
	 *            类别字符串2
	 */
	private void initWidget(String str1, String str2) {

		int lineCount = ((float) allSize / LINE_SIZE) > allSize / LINE_SIZE ? allSize / LINE_SIZE + 1 : allSize / LINE_SIZE;
		Log.i(TAG, "count:" + lineCount);
		int remain = allSize;
		int tempLineSize = LINE_SIZE;

		addParentPlayWayView(str1);

		for (int i = 0; i < lineCount; i++) {
			LinearLayout subLayout = new LinearLayout(mContext);
			subLayout.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
			if (remain < LINE_SIZE) {
				tempLineSize = remain;
			}
			for (int j = 0; j < tempLineSize; j++) {
				TextView textView = new TextView(mContext);
				// textView.setLayoutParams(new
				// LinearLayout.LayoutParams(0,LayoutParams.WRAP_CONTENT,
				// 1.0f));
				LayoutParams textParams = new LinearLayout.LayoutParams(itemWidth, itemHeight);
				textParams.setMargins(3, 3, 3, 3);
				textView.setLayoutParams(textParams);
				textView.setBackgroundResource(defaultBgId);
				textView.setGravity(Gravity.CENTER);
				textView.setSingleLine(true);
				// if(playWays[playWaySplitIndex+i*LINE_SIZE+j].length() <= 4){
				textView.setTextSize(ComTools.convertTextSize(mContext, R.dimen.text_size_medium));
				// }else{
				// textView.setTextSize(12);
				// }
				textView.setText(playWays[i * LINE_SIZE + j]);
				//textView.setId(playWayIds[i * LINE_SIZE + j]);
				textView.setTextColor(Color.parseColor(unSelColor));

				textView.setTag(false);
				subLayout.addView(textView);
				mVecTv.add(textView);
				textView.setOnClickListener(this);

				if (playWaySplitIndex == (i * LINE_SIZE + j) + 1) {
					addView(subLayout);
					addParentPlayWayView(str2);
					initWidght(allSize - playWaySplitIndex);
					return;
				}
			}
			remain = remain - LINE_SIZE;
			addView(subLayout);
		}
		// setSelect(0);
	}

	/**
	 * @author shouli.luo
	 * @time 2013-4-8 下午01:47:12
	 * @方法作用： 设置选择的位置
	 * @param index
	 */
	public void setSelect(int index) {



		TextView v = mVecTv.get(index);
		v.setTextColor(Color.parseColor(selColor));
		v.setBackgroundResource(tickId);
		// v.setCompoundDrawablesWithIntrinsicBounds(null, null,
		// mContext.getResources().getDrawable(tickId), null);
		v.setTag(true);

		if (index != preIndex) {
			TextView preV = mVecTv.get(preIndex);
			preV.setTextColor(Color.parseColor(unSelColor));
			preV.setBackgroundResource(defaultBgId);
			preV.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
			preV.setTag(false);
		}
		preIndex = index;
	}

	private void addParentPlayWayView(String str) {
		TextView playTv = new TextView(mContext);
		playTv.setGravity(Gravity.CENTER);
		playTv.setPadding(0, 10, 0, 10);
		playTv.setTextColor(Color.parseColor(unSelColor));
		playTv.setTextSize(18);
		playTv.setText(str);
		addView(playTv);
	}

	private void initWidget() {

		int lineCount = ((float) allSize / LINE_SIZE) > allSize / LINE_SIZE ? allSize / LINE_SIZE + 1 : allSize / LINE_SIZE;
//		Logger.i(TAG, "count:" + lineCount);
		int remain = allSize;
		int tempLineSize = LINE_SIZE;

		addParentPlayWayView("普通投注");

		for (int i = 0; i < lineCount; i++) {
			LinearLayout subLayout = new LinearLayout(mContext);
			subLayout.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
			if (remain < LINE_SIZE) {
				tempLineSize = remain;
			}
			for (int j = 0; j < tempLineSize; j++) {
				TextView textView = new TextView(mContext);
				// textView.setLayoutParams(new
				// LinearLayout.LayoutParams(0,LayoutParams.WRAP_CONTENT,
				// 1.0f));
				LayoutParams textParams = new LinearLayout.LayoutParams(itemWidth, itemHeight);
				textParams.setMargins(3, 3, 3, 3);
				textView.setLayoutParams(textParams);
				textView.setBackgroundResource(defaultBgId);
				textView.setGravity(Gravity.CENTER);
				textView.setSingleLine(true);
				// if(playWays[playWaySplitIndex+i*LINE_SIZE+j].length() <= 4){
				textView.setTextSize(ComTools.convertTextSize(mContext, R.dimen.text_size_medium));
				// }else{
				// textView.setTextSize(12);
				// }
				textView.setText(playWays[i * LINE_SIZE + j]);
				//textView.setId(playWayIds[i * LINE_SIZE + j]);
				textView.setTextColor(Color.parseColor(unSelColor));

				textView.setTag(false);
				subLayout.addView(textView);
				mVecTv.add(textView);
				textView.setOnClickListener(this);

				if (playWaySplitIndex == (i * LINE_SIZE + j) + 1) {
					addView(subLayout);
					addParentPlayWayView("胆拖投注");
					initWidght(allSize - playWaySplitIndex);
					return;
				}
			}
			remain = remain - LINE_SIZE;
			addView(subLayout);
		}
		// setSelect(0);
	}

	private void initWidght(int allSize) {
		int lineCount = ((float) allSize / LINE_SIZE) > allSize / LINE_SIZE ? allSize / LINE_SIZE + 1 : allSize / LINE_SIZE;
//		Logger.i(TAG, "count:" + lineCount);
		int remain = allSize;
		int tempLineSize = LINE_SIZE;

		for (int i = 0; i < lineCount; i++) {
			LinearLayout subLayout = new LinearLayout(mContext);
			subLayout.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
			if (remain < LINE_SIZE) {
				tempLineSize = remain;
			}
			for (int j = 0; j < tempLineSize; j++) {
				TextView textView = new TextView(mContext);
				// textView.setLayoutParams(new
				// LinearLayout.LayoutParams(0,LayoutParams.WRAP_CONTENT,
				// 1.0f));
				LayoutParams textParams = new LinearLayout.LayoutParams(itemWidth, itemHeight);
				textParams.setMargins(3, 3, 3, 3);
				textView.setLayoutParams(textParams);
				textView.setBackgroundResource(defaultBgId);
				textView.setGravity(Gravity.CENTER);
				textView.setSingleLine(true);

				// if(playWays[playWaySplitIndex+i*LINE_SIZE+j].length() <= 4){
				textView.setTextSize(ComTools.convertTextSize(mContext, R.dimen.text_size_medium));
				// }else{
				// textView.setTextSize(12);
				// }
				textView.setText(playWays[playWaySplitIndex + i * LINE_SIZE + j]);
				//textView.setId(playWayIds[playWaySplitIndex + i * LINE_SIZE + j]);
				textView.setTextColor(Color.parseColor(unSelColor));
				textView.setTag(false);
				subLayout.addView(textView);
				mVecTv.add(textView);
				textView.setOnClickListener(this);
			}
			remain = remain - LINE_SIZE;
			addView(subLayout);
		}
		// setSelect(0);
	}

	public void setOnPlayWayChangeListener(IPlayWayChangeCallBack callback) {
		this.callBack = callback;
	}

	// private int preX,preY;
	// private int preRawX,preRawY;

	// @Override
	// public boolean onInterceptTouchEvent(MotionEvent ev) {
	// // TODO Auto-generated method stub
	//
	// int curX = (int)ev.getX();
	// int curY = (int)ev.getY();
	// int curRawX = (int)ev.getRawX();
	// int curRawY = (int)ev.getRawY();
	//
	// PalLog.d(TAG, "curX："+curX);
	// PalLog.d(TAG, "curY："+curY);
	// PalLog.d(TAG, "curRawX："+curRawX);
	// PalLog.d(TAG, "curRawY："+curRawY);
	//
	// // if(curX > singleBallSize*lineSize){
	// // return true;
	// // }
	// int location = (curY/singleBallSize)*lineSize+curX/singleBallSize;
	//
	// PalLog.d(TAG, "location:"+location);
	//
	// PalLog.v(TAG, "Action："+ev.getAction());
	//
	// if(ev.getAction() == MotionEvent.ACTION_DOWN){
	//
	// preX = (int)ev.getX();
	// preY = (int)ev.getY();
	// preRawX = (int)ev.getRawX();
	// preRawY = (int)ev.getRawY();
	//
	// PalLog.e(TAG, "ACTION_DOWN");
	//
	// if(location < mVecTv.size()){
	// mVecTv.get(location).getLocationInWindow(mLocation);
	// bigBall.setText( mVecTv.get(location).getText());
	// mPop.showAtLocation(this, Gravity.LEFT|Gravity.TOP,
	// mLocation[0]-relLocatin[0], mLocation[1]-relLocatin[1]);
	// // if((Boolean)mVec.get(location).getTag()){
	// // mVec.get(location).setTag(false);
	// // mVec.get(location).setBackgroundResource(defaultBall);
	// // }else{
	// // mVec.get(location).setTag(true);
	// // mVec.get(location).setBackgroundResource(redBall);
	// // }
	// }
	//
	// } else if(ev.getAction() == MotionEvent.ACTION_UP){
	//
	// PalLog.e(TAG, "ACTION_UP");
	// mPop.dismiss();
	//
	// // if((Boolean)mVec.get(location).getTag()){
	// // mVec.get(location).setTag(false);
	// // mVec.get(location).setBackgroundResource(defaultBall);
	// // }else{
	// // mVec.get(location).setTag(true);
	// // mVec.get(location).setBackgroundResource(redBall);
	// // }
	// }else if(ev.getAction() == MotionEvent.ACTION_MOVE){
	// PalLog.e(TAG, "ACTION_MOVE");
	//
	// if(Math.abs(preX-curX) > 15
	// || Math.abs(preY-curY) > 15
	// || Math.abs(preRawX-curRawX) > 15
	// || Math.abs(preRawY-curRawY) > 15){
	// mPop.dismiss();
	// }
	// }else if(ev.getAction() == MotionEvent.ACTION_CANCEL){
	// PalLog.e(TAG, "ACTION_CANCEL");
	// mPop.dismiss();
	// }
	// return super.onInterceptTouchEvent(ev);
	// }

	public interface IPlayWayChangeCallBack {

		public void playWayChange(boolean isSameClick, int playWayId, String playWayName);
	}
	
	public interface IPlayWayChangeCallBackStr {
		
		public void playWayChange(boolean isSameClick, String id, String playWayName);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

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
			callBack.playWayChange(isSameClick, playWayIds[index], ((TextView) v).getText().toString());
		}
		
		if(callBackStr != null){
			callBackStr.playWayChange(isSameClick, strPlayWayIds[index], ((TextView) v).getText().toString());
		}
		
	}

	public int[] getPlayWayIds() {
		return playWayIds;
	}

	public void setPlayWayIds(int[] playWayIds) {
		setSelect(0);
		this.playWayIds = playWayIds;
	}

	public IPlayWayChangeCallBackStr getCallBackStr() {
		return callBackStr;
	}

	public void setCallBackStr(IPlayWayChangeCallBackStr callBackStr) {
		this.callBackStr = callBackStr;
	}
	
	
	
}
