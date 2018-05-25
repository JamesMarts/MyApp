package com.example.lijuw.myapp;

import java.util.Vector;

import android.app.Service;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.PaintDrawable;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;


/**
 * @author shouli.luo
 * @time 2013-3-26 下午12:35:36
 * @introduce 选球控件
 */
public class BallView extends LinearLayout implements OnClickListener {

	private int allSize;// 一共有多少球
	private int lineSize;// 每一行占几个球
	// private int destance;//每个球之间的距离
	private int maxSelCount;// 最多可选择几个
	private int minSelCount;// 最少可选择几个
	private int screen_width;// 屏幕宽度
	private int ball_view_width;// 投注面板的大小
	private int left_panel_dip = 58;

	private String[] ballStr;// 每个球上的数字
	private String[] rataStrs;// 球下方出现的数字
	private int[] singleBallSize = new int[2];// 单个球的大小
	private int singleBallHeight;
	// private int[] bigBallSize = new int[2];
	private int index;// 在所有球item的位置
	private int gestureScope;// 手势范围
	private int panel_mar = 15;// 最小 ,投注面板的左右内边距
	private int padding;// 小球的上下左右内边距
	private int[] mLocation = new int[2];
	private int[] relLocatin = new int[2];
	private Vector<TextView> mVecTv = new Vector<TextView>();// 存放所有球
	private Vector<TextView> mVecNumTv = new Vector<TextView>();// 存放所有球下面的数字
	private Vector<String> mVecSelNum = new Vector<String>();
	private Context mContext;
	private Vibrator vib = null;

	private boolean isExceedPrice = false;

	// private int widthPixels;

	private IBallSelChangeCallBack callBack;

	private TextView bigBall;

	private LayoutInflater inflater;
	private boolean isCanBet = false;// 能否投注

	
	
	private int defaultBall = R.drawable.ball_gray;
	private int smallBall, clickBall;
	private String textColor, whiteColor;

	private static final String TAG = "BallView";

	private View lastview;
	private int left_panel_width;

	// 悬浮的大球
	private DanglingBallView danglingBallView;

	public BallView(Context context, AttributeSet attrs) {
		super(context, attrs);
		inflater = LayoutInflater.from(context);
		this.mContext = context;
		screen_width = mContext.getResources().getDisplayMetrics().widthPixels;
		vib = (Vibrator) mContext.getSystemService(Service.VIBRATOR_SERVICE);
	}

	public void initView(int index, int ballCount, int minSelCount, int maxSelCount, boolean isRedBall, boolean isLeftVisible, String[] strs, String[] rataStrs) {
		// getLocationInWindow(mSelfLocation);
		padding = getResources().getDimensionPixelSize(R.dimen.lt_3_dip);
		this.index = index;
		this.allSize = ballCount;
		this.maxSelCount = maxSelCount;
		this.minSelCount = minSelCount;
		this.ballStr = strs;
		this.rataStrs = rataStrs;
		rateViewHeight = getResources().getDimensionPixelSize(R.dimen.ball_view_rate_height);
		if (minSelCount == 0) {
			isCanBet = true;
		}

		if(screen_width == 1080){
			padding = 21;   
		}
		
		this.singleBallSize[0] = BitmapFactory.decodeResource(mContext.getResources(), defaultBall).getWidth() + 2 * padding;
		//this.singleBallSize[0] = BitmapFactory.decodeResource(mContext.getResources(), defaultBall).getWidth();

		this.gestureScope = singleBallSize[0] / 3;  
		if (isLeftVisible) {
			// 左边显示
			left_panel_width = ComTools.dip2px(mContext, left_panel_dip);
		} else {
			// 左边不显示
			//left_panel_width = ComTools.dip2px(mContext, left_panel_dip);
			left_panel_width = 0;
			if(screen_width == 1080){
				panel_mar = 150;
			}else{
				panel_mar = ComTools.dip2px(mContext, left_panel_dip);
			}
		}
		ball_view_width = screen_width - left_panel_width;

		if (ballCount == 10) {
			this.lineSize = 5;
		} else {
			this.lineSize = calculateLineSize(ball_view_width, singleBallSize[0]);
		}

		if (isRedBall) {
			smallBall = R.drawable.ball_red;
			clickBall = R.drawable.ball_red_clicked;
			textColor = "#c1001f";
			defaultBall = R.drawable.ball_gray;
		} else {
			smallBall = R.drawable.ball_blue;
			clickBall = R.drawable.ball_blue_clicked;
			textColor = "#0965e2";
			defaultBall = R.drawable.blue_ball_default;
		}
		this.whiteColor = "#ffffff";

		danglingBallView = new DanglingBallView();

		// this.destance = width/lineSize -
		// BitmapFactory.decodeResource(mContext.getResources(),
		// defaultBall).getWidth();
		initWidget();

	}

	public int getAllBallCount() {
		return allSize;
	}

	public int getAtLeastCount() {
		return minSelCount;
	}

	public int getShakeCount() {
		return 1;
	}

	public int getIndex() {
		return index;
	}

	/**
	 * @author shouli.luo
	 * @time 2013-4-3 下午02:43:27
	 * @方法作用： 设置机选
	 * @param ramdom
	 */
	public void setRandom(String[] ramdom) {
		clear();
		if (!isRandomEnd)
			return;
		MainApplication.getApp().doBackTask(new RandomRunnable(ramdom));
	}

	/**
	 * @author shouli.luo
	 * @time 2013-4-3 下午02:43:27
	 * @方法作用： 设置机选
	 * @param ramdom
	 */
	public void setRandom(int[] ramdom) {
		clear();
		if (!isRandomEnd)
			return;
		MainApplication.getApp().doBackTask(new RandomRunnable(ramdom));
	}

	private static final int BALL_CHANGE_END = 1;
	private static final int BALL_CHANGE_INDEX = 2;
	private Handler randomHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			int index = (Integer) msg.obj;
			switch (msg.what) {
			case BALL_CHANGE_INDEX:
				setSelectTextView(mVecTv.get(index), index);
				break;
			case BALL_CHANGE_END:
				if (callBack != null) {
					isCanBet = true;
					callBack.ballChange(mVecSelNum, null, index);
				}
				break;
			}
		};
	};

	private boolean isRandomEnd = true;

	class RandomRunnable implements Runnable {

		private int[] intRamdom;
		private String[] strRandom;

		public RandomRunnable(int[] ramdom) {
			super();
			this.intRamdom = ramdom;
		}

		public RandomRunnable(String[] strRandom) {
			super();
			this.strRandom = strRandom;
		}

		@Override
		public void run() {
			try {
				int len = 0;
				if (intRamdom != null) {
					len = intRamdom.length;
				} else {
					len = strRandom.length;
				}

				isRandomEnd = false;
				for (int i = 0; i < len; i++) {
					int index = 0;
					if (intRamdom != null) {
						index = intRamdom[i] - 1;
					} else {
						String str = strRandom[i];
						if (allSize > 10) {
							index = Integer.parseInt(str) - 1;
						} else {
							index = Integer.parseInt(str);
						}
					}
					Message msg = randomHandler.obtainMessage();
					msg.what = BALL_CHANGE_INDEX;
					msg.obj = index;
					randomHandler.sendMessage(msg);
					Thread.sleep(70);
				}
				Message msg = randomHandler.obtainMessage();
				msg.what = BALL_CHANGE_END;
				msg.obj = index;
				randomHandler.sendMessage(msg);
				isRandomEnd = true;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	// 改变选中球的状态
	private void setSelectTextView(TextView tv, final int index) {
		SoundManager.getInstance(mContext).playSound(SoundManager.ID_POP);
		tv.setBackgroundResource(smallBall);
		tv.setTextColor(Color.parseColor(whiteColor));
		tv.setTag(true);
		mVecSelNum.add(tv.getText().toString());
	}

	public String[] getRataStrs() {
		return rataStrs;
	}

	public void setRataStrs(String[] rataStrs) {
		this.rataStrs = rataStrs;
		// setRateVisible(true);
	}

	
	private boolean isRateShow;
	
	
	public boolean isRateShow() {
		return isRateShow;
	}

	public void setRateShow(boolean isRateShow) {
		this.isRateShow = isRateShow;
	}

	private int rateViewHeight;

	/**
	 * @author shouli.luo
	 * @time 2013-4-8 上午10:56:13
	 * @方法作用： 球下方的概率数字是否显示
	 * @param isVisible
	 */
	public void setRateVisible(boolean isVisible) {
		if (isVisible && rataStrs != null) {
			for (int i = 0; i < mVecNumTv.size(); i++) {
				mVecNumTv.get(i).setText(rataStrs[i]);
				mVecNumTv.get(i).setVisibility(View.VISIBLE);
			}
			singleBallSize[1] = singleBallHeight;
			isRateShow = true;
		} else {
			for (int i = 0; i < mVecNumTv.size(); i++) {
				mVecNumTv.get(i).setVisibility(View.GONE);
			}
			singleBallSize[1] = singleBallSize[0];
			isRateShow = false;
		}
	}

	private boolean hasMeasured = false;

	/**
	 * @author shouli.luo
	 * @time 2013-4-8 上午09:52:20
	 * @方法作用： 计算球的尺寸
	 */
	private void calBallSize(final View v) {

		if (!hasMeasured) {
			ViewTreeObserver vto = v.getViewTreeObserver();

			vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
				public boolean onPreDraw() {
					if (!hasMeasured) {
						singleBallSize[1] = v.getMeasuredHeight() ;
						singleBallHeight = v.getMeasuredHeight();
						// singleBallSize[0] = v.getMeasuredWidth();
						// 获取到宽度和高度后，可用于计算
						hasMeasured = true;
					}
					return true;
				}
			});
		}

	}

	private int calculateLineSize(int width, int ball_width) {
		float result = (width - 30) / ball_width;
		int size = (int) Math.floor(result);
		if (size > 8) {
			size = 8;
		}
		panel_mar = (width - (size * ball_width)) / 2;
		return size;
	}

	/**
	 * @author shouli.luo
	 * @time 2013-3-27 下午03:30:51
	 * @方法作用： 是否可以成为一注
	 * @return
	 */
	public boolean getIsCanBet() {
		return isCanBet;
	}

	private void initWidget() {   

		this.setPadding(panel_mar, 5, panel_mar, 5);
		int lineCount = ((float) allSize / lineSize) > allSize / lineSize ? allSize / lineSize + 1 : allSize / lineSize;
		Log.i(TAG, "count:" + lineCount);
		int remain = allSize;
		int tempLineSize = lineSize;

		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
		lp.setMargins(padding, padding, padding, padding);
		
//		if(screen_width == 1080){
//			lp.setMargins(42, 42, 0, 0);
//		}
		
		for (int i = 0; i < lineCount; i++) {
			LinearLayout subLayout = new LinearLayout(mContext);
			subLayout.setOrientation(LinearLayout.HORIZONTAL);
			subLayout.setOnClickListener(null);
			subLayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
			if (remain < lineSize) {
				tempLineSize = remain;
			}
			for (int j = 0; j < tempLineSize; j++) {
				final View v = inflater.inflate(R.layout.ball, null);
				calBallSize(v);
				TextView textView = (TextView) v.findViewById(R.id.ball_view);
				TextView rataView = (TextView) v.findViewById(R.id.rate_view);
				if (rataStrs != null) {
					rataView.setText(rataStrs[i * lineSize + j]);
				} else {
					rataView.setVisibility(View.GONE);
				}
				// TextView textView = new TextView(mContext);
				textView.setLayoutParams(lp);
				textView.setBackgroundResource(defaultBall);
				textView.setGravity(Gravity.CENTER);
				if (ballStr == null) {
					if (allSize > 10) {
						if (i * lineSize + j < 9) {
							textView.setText("0" + String.valueOf(i * lineSize + j + 1));
						} else {
							textView.setText(String.valueOf(i * lineSize + j + 1));
						}
					} else {
						textView.setText(String.valueOf(i * lineSize + j));
					}
				} else {
					textView.setText(ballStr[i * lineSize + j]);
				}
				textView.setTextColor(Color.parseColor(textColor));
				// textView.setTextSize(15);
				textView.setId(i * lineSize + j);
				textView.setTag(false);
				subLayout.addView(v);
				mVecTv.add(textView);
				mVecNumTv.add(rataView);
				// textView.setOnClickListener(this);
				// rataView.setOnClickListener(this);
			}
			remain = remain - lineSize;
			addView(subLayout);
		}
	}

	public void clear() {
		mVecSelNum.clear();
		isCanBet = false;
		for (int i = 0; i < mVecTv.size(); i++) {
			TextView tv = mVecTv.get(i);
			if ((Boolean) tv.getTag()) {
				tv.setBackgroundResource(defaultBall);
				tv.setTextColor(Color.parseColor(textColor));
				tv.setTag(false);
			}
		}
	}

	public void setOnBallChangeListener(IBallSelChangeCallBack callback) {
		this.callBack = callback;
	}

	public Vector<String> getSelBallVec() {
		return mVecSelNum;
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {

		int curX = (int) ev.getX();
		int curY = (int) ev.getY();

		// if(isRateStrShow()){
		// System.out.println("rateViewHeight:"+rateViewHeight);
		// curY = curY + 24;
		// }

		boolean isClickOnBall = true;
		// 没点在小球上
		if ((curX <= panel_mar) || (curX > (singleBallSize[0] * lineSize + panel_mar))) {
			isClickOnBall = false;
		}

		int y = 0;
		if (isRateShow) {
			y = (curY / (singleBallSize[1] + rateViewHeight)) * lineSize;
		} else {
			y = (curY / singleBallSize[1]) * lineSize;
		}

		int click_ball_Location = y + (curX - panel_mar) / singleBallSize[0];// 计算点在投注面板中那个小球的位置上

		danglingBallView.doTouch(ev, isClickOnBall, click_ball_Location);

		return super.onInterceptTouchEvent(ev);
	}

	public interface IBallSelChangeCallBack {

		public void ballChange(Vector<String> mVec, TextView v, int index);
	}

	public void setUnSel(TextView v) {
		v.setBackgroundResource(defaultBall);
		((TextView) v).setTextColor(Color.parseColor(textColor));
		mVecSelNum.remove(((TextView) v).getText().toString());
	}

	public void setUnSel(String numStr) {
		for (int i = 0; i < mVecTv.size(); i++) {
			TextView tv = mVecTv.get(i);
			if (tv.getText().toString().equals(numStr)) {
				tv.setBackgroundResource(defaultBall);
				tv.setTag(false);
				tv.setTextColor(Color.parseColor(textColor));
				mVecSelNum.remove(tv.getText().toString());
				break;
			}
		}
	}

	public void setSel(TextView v) {
		v.setBackgroundResource(smallBall);
		((TextView) v).setTextColor(Color.parseColor(whiteColor));
		mVecSelNum.add(((TextView) v).getText().toString());
	}

	private boolean isExceedPrice() {
		return isExceedPrice;

	}

	private int count = 1;

	public void setExceedPrice(boolean isExceedPrice, int count) {
		this.isExceedPrice = isExceedPrice;
		this.count = count;
		if (count > 10000 && isExceedPrice == true) {
			removelast();
		}
	}

	private void removelast() {
		if (lastview != null) {
			setUnSel(((TextView) lastview).getText().toString());
			// if (callBack != null) {
			// callBack.ballChange(mVecSelNum, (TextView) lastview, index);
			// }
			lastview = null;
		}
	}

	@Override
	public void onClick(View v) {
		if (true) {
			vib.vibrate(100);
		}
		Log.i(TAG, "!!!tag:" + v.getTag() + " count:" + count);
		if ((Boolean) v.getTag()) {
			v.setBackgroundResource(defaultBall);
			((TextView) v).setTextColor(Color.parseColor(textColor));
			mVecSelNum.remove(((TextView) v).getText().toString());
		} else {

			if (isExceedPrice() && count > 10000) {
				Toast.makeText(mContext, R.string.too_much_money_hint, Toast.LENGTH_SHORT).show();
				if (callBack != null) {
					callBack.ballChange(mVecSelNum, (TextView) v, index);
				}
				return;
			}
			if (maxSelCount != -1 && mVecSelNum.size() >= maxSelCount) {
				Toast.makeText(mContext, "最多只能选择" + maxSelCount + "个号", Toast.LENGTH_SHORT).show();
				return;
			}

			lastview = v;
			v.setBackgroundResource(smallBall);
			((TextView) v).setTextColor(Color.parseColor(whiteColor));
			mVecSelNum.add(((TextView) v).getText().toString());

		}
		v.setTag(!(Boolean) v.getTag());
		if (mVecSelNum.size() < minSelCount) {
			isCanBet = false;
		} else {
			isCanBet = true;
		}
		if (callBack != null) {
			callBack.ballChange(mVecSelNum, (TextView) v, index);
		}
	}

	// 悬浮的大球
	class DanglingBallView {

		public PopupWindow mPop;
		public View danglingBallView;
		public TextView bigBall;

		private int curX, curY;
		private int preX, preY;
		private int location_in_pop;// 正在pop中的小球位置
		private int curLocation;

		public DanglingBallView() {
			danglingBallView = LayoutInflater.from(mContext).inflate(R.layout.big_ball, null);
			bigBall = (TextView) danglingBallView.findViewById(R.id.tv_bigball);

		}

		public void doTouch(MotionEvent ev, boolean isClickOnBall, int click_ball_Location) {
			if (ev.getAction() == MotionEvent.ACTION_DOWN) {
				doTouchDown(ev, isClickOnBall, click_ball_Location);

			} else if (ev.getAction() == MotionEvent.ACTION_UP) {
				doTouchUp(ev, isClickOnBall, click_ball_Location);

			} else if (ev.getAction() == MotionEvent.ACTION_MOVE) {
				doTouchMove(ev, isClickOnBall, click_ball_Location);

			} else if (ev.getAction() == MotionEvent.ACTION_CANCEL) {
				doTouchCancle(ev, isClickOnBall, click_ball_Location);
			}

		}

		public void showAtLocation(int index) {
			if (mPop == null) {
				mPop = new PopupWindow(danglingBallView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
				mPop.setBackgroundDrawable(new PaintDrawable(Color.parseColor("#00000000")));
				mPop.setFocusable(true);
			}

			int[] smallBallLocation = new int[2];

			mVecTv.get(index).getLocationInWindow(smallBallLocation);
			bigBall.setText(mVecTv.get(index).getText());
			this.bigBall.setBackgroundResource(clickBall);

			Bitmap b = BitmapFactory.decodeResource(mContext.getResources(), clickBall);

			relLocatin[0] = ((b.getWidth() - singleBallSize[0]) >> 1) + padding ;
			relLocatin[1] = b.getHeight() - singleBallSize[0] + 2 * padding;

		  //mPop.showAtLocation(BallView.this, Gravity.LEFT | Gravity.TOP,smallBallLocation[0] - relLocatin[0]-x, smallBallLocation[1] - relLocatin[1]-y);
			mPop.showAtLocation(BallView.this, Gravity.LEFT | Gravity.TOP, smallBallLocation[0] - relLocatin[0], smallBallLocation[1] - relLocatin[1]);

			location_in_pop = index;
		}

		public void dismiss() {
			if (mPop != null) {
				if (mPop.isShowing()) {
					mPop.dismiss();
				}

			}
		}

		/**
		 * 
		 * @param ev
		 *            点击事件
		 * @param isClickOnBall
		 *            是否点击到了投注面板的小球上
		 * @param location
		 *            点击的小球在投注面板上的位置
		 */
		public void doTouchDown(MotionEvent ev, boolean isClickOnBall, int location) {
			if (!isClickOnBall) {
				return;
			}
			curX = (int) ev.getX();
			curY = (int) ev.getY();
			curLocation = location;
			if (location < mVecTv.size()) {
				// 显示浮起的大球
				showAtLocation(location);
				location_in_pop = curLocation;
			}

			preX = curX;
			preY = curY;

		}

		public void doTouchUp(MotionEvent ev, boolean isClickOnBall, int location) {
			curX = (int) ev.getX();
			curY = (int) ev.getY();

			curLocation = location;
			dismiss();
			if (curLocation == location_in_pop) {
				onClick(mVecTv.get(curLocation));
			}

		}

		public void doTouchMove(MotionEvent ev, boolean isClickOnBall, int location) {
			curX = (int) ev.getX();
			curY = (int) ev.getY();
			curLocation = location;
			float rawX = ev.getRawX();
			float rawY = ev.getRawY();
			// 计算手指移动的距离便宜选中小球的距离

			// 得到被选中的小球 textView
			TextView selBall = mVecTv.get(location_in_pop);
			int[] selBallLocation = new int[2];
			selBall.getLocationOnScreen(selBallLocation);

			// 判断当前点是否在选中的小球的点击范围内
			int selBallWidth = selBall.getWidth();
			int selBallHeight = selBall.getHeight();

			boolean x_in = (selBallLocation[0] < rawX) && (rawX < (selBallLocation[0] + selBallWidth)); // x是否满足
			boolean y_in = (selBallLocation[1] < rawY) && (rawY < (selBallLocation[1] + selBallHeight));// //y是否满足

			if (x_in && y_in) {
				return;
			} else {
				// if(curLocation!=location_in_pop)
				// {
				// Log.d(TAG, "showNew");
				// showAtLocation(curLocation);
				// }else
				// {
				// Log.d(TAG, "dismiss");
				// dismiss();
				// }
				dismiss();
			}

			// if (Math.abs(preX - curX) > gestureScope
			// || Math.abs(preY - curY) > gestureScope) {
			// dismiss();
			// }
		}

		public void doTouchCancle(MotionEvent ev, boolean isClickOnBall, int location) {
			dismiss();
		}

	}
}
