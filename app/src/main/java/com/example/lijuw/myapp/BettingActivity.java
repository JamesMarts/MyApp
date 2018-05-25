package com.example.lijuw.myapp;

import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.PaintDrawable;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import de.greenrobot.event.EventBus;

import static com.example.lijuw.myapp.BetItem.PY_DLT_PUTONG;
import static com.example.lijuw.myapp.BetItem.PY_SSQ_PUTONG;
import static com.example.lijuw.myapp.BetItem.TYPE_DLT;

/**
 * 数字彩选号界面
 * 
 * @author gl
 */
public class BettingActivity extends BettingBaseActivity implements BallView.IBallSelChangeCallBack {

	private Vibrator vib;
	private int lotteryType;// 彩票类型
	private int playWayType; // 玩法类型
	private String lotteryName;
	private boolean isAddZero;
	private int[] playWayIds;
	private String[] playWayNames;
	private int playSplitIndex;
	private BetItem mBetItem;
	private int playWayIndex;
	private ImageView mTriangleIv;
	private Button clear;
	private Button confirm;
	private TextView total_count;
	private TextView total_price;
	private TextView tv_current_periodnumber;
	private TextView tv_countdown_time;
	private TextView mTitleTv;
	private PlayWayView mPlayWayView;
	private PopupWindow mPop;
	private LinearLayout bet_panel;
	private BallView[] mBallViews; // 所有球View
	private String saveNum; // 选中本地保存的号码
	public boolean isCanBet = false; // 是否能投注
	public HashMap<Integer, Vector<String>> map = new HashMap<Integer, Vector<String>>();// 选中map
	private int count; // 彩票总注数
	private String appStr = "";
	private boolean isFinish = false;// 是否是点击item或者点击添加自选号码弹出的页面
	private PeriodInfo mPeriodInfo;
	private TextView tv_end_date;
	private int _ID;
	private LinearLayout ll_left_latest_period;
	private LinearLayout ll_right_latest_period;
	private TextView tv_last_period;
	private TextView tv_last_period_result;
	protected LinearLayout ll_awardinfo_root;
	private LinearLayout ll_last_awardinfo_root2;
	private long lastClickTime;
	private int prePlayIndex = 0;
	private String[] danPlayWayNames = new String[] { "普通投注", "胆拖投注" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_betting);

		initRedTitleStyle();
		enableShake();
		findView();
		loadData();
	}

	private void findView() {
		tv_current_periodnumber = (TextView) findViewById(R.id.tv_current_periodnumber);
		tv_end_date = (TextView) findViewById(R.id.tv_end_date);
		mTriangleIv = (ImageView) findViewById(R.id.center_textview_image);
		clear = (Button) findViewById(R.id.clear);
		confirm = (Button) findViewById(R.id.confirm);
		total_count = (TextView) findViewById(R.id.total_count);
		total_price = (TextView) findViewById(R.id.total_price);
		tv_countdown_time = (TextView) findViewById(R.id.tv_countdown_time);
		mTitleTv = getCenterTextView();
		bet_panel = (LinearLayout) findViewById(R.id.bet_panel);
		ll_awardinfo_root = (LinearLayout) findViewById(R.id.ll_awardinfo_root);
		ll_last_awardinfo_root2 = (LinearLayout) findViewById(R.id.ll_last_awardinfo_root2);
		ll_last_awardinfo_root2.setTag(false);

		ll_left_latest_period = (LinearLayout) findViewById(R.id.ll_left_latest_period);
		ll_right_latest_period = (LinearLayout) findViewById(R.id.ll_right_latest_period);
		tv_last_period = (TextView) findViewById(R.id.tv_last_period);
		tv_last_period_result = (TextView) findViewById(R.id.tv_last_period_result);


		mTitleTv.setOnClickListener(this);
		clear.setOnClickListener(this);
		confirm.setOnClickListener(this);
		// ll_awardinfo_root.setOnClickListener(this);
	}

	private void loadData() {
		vib = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);
		lotteryType =TYPE_DLT;
		playWayType =PY_DLT_PUTONG;
		saveNum = getIntent().getStringExtra(BetOkActivity.SAVE_NUM);
		isFinish = getIntent().getBooleanExtra(BetOkActivity.FINISH, false);
		_ID = getIntent().getIntExtra(DatabaseHelper.Betting._ID, -1);

		lotteryName = BetItem.getLotteryNameByType(lotteryType);
		isAddZero = BetItem.getNeedAddZeroByType(lotteryType);
		playWayIds = BetItem.getPlayWayIdByType(lotteryType);
		playWayNames = BetItem.getPlayWayNameByType(lotteryType);
		playSplitIndex = BetItem.getPlayWaySplitIndexByType(lotteryType);

		if (playWayType == -1) {
			playWayType = playWayIds[0];
			mBetItem = new BetItem(this, lotteryType, playWayIds[0]);
		} else {
			mBetItem = new BetItem(this, lotteryType, playWayType);
		}
		playWayIndex = BetItem.getLocationByPlayType(playWayType, playWayIds);

		initView();
		initData();
	}

	private void initView() {
		setTitleLeftButtonVisibility(true);
		setCenterTitle(lotteryName + "-" + playWayNames[playWayIndex]);

		if (playWayNames.length == 1) {
			setCenterViewClickable(false);
		} else {
			setCenterViewClickable(true);
		}

		switch (lotteryType) {
		case BetItem.TYPE_SSQ:
		case TYPE_DLT:
		case BetItem.TYPE_QLC:
			initTitlePlayWayView();
			break;
		case BetItem.TYPE_PL3:
		case BetItem.TYPE_3D:
		case BetItem.TYPE_JSSC:
			initScrollPlayWayView();
			break;
		case BetItem.TYPE_QXC:
			break;
		case BetItem.TYPE_11XUAN5:
		case BetItem.TYPE_JX11XUAN5:
		case BetItem.TYPE_GD11XUAN5:
		case BetItem.TYPE_K3:
			int[] danPlayWayIds = BetItem.getDanPlayByPlayWayType(lotteryType, playWayType);
			if (danPlayWayIds != null)
				initTitlePlayWayView(danPlayWayIds);
			initScrollPlayWayView();
			break;
		}

			initBallView();
			initBallData();

	}

	private void initTitlePlayWayView() {
		if(mPlayWayView == null){
			View v = View.inflate(this, R.layout.layout_switch_rule, null);
			mPlayWayView = (PlayWayView) v.findViewById(R.id.playway_view);
			mPlayWayView.initView(playSplitIndex, playWayNames.length, playWayNames, playWayIds);
			mPlayWayView.setSelect(playWayIndex);
			mPlayWayView.setOnPlayWayChangeListener(new TitlePlayWayChangeListener());
			mPop = new PopupWindow(v, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
			mPop.setBackgroundDrawable(new PaintDrawable(Color.parseColor("#00000000")));
			mPop.setFocusable(true);
			// mPop.setAnimationStyle(R.style.AnimationPreview);
			mPop.setOnDismissListener(new OnDismissListener() {

				@Override
				public void onDismiss() {
					performRotateAnima(mTriangleIv, -180, 0);
				}
			});
		}
	}

	private void initTitlePlayWayView(int[] ids) {
		if (mPlayWayView == null) {
			View v = View.inflate(this, R.layout.layout_switch_rule, null);
			mPlayWayView = (PlayWayView) v.findViewById(R.id.playway_view);
			mPlayWayView.initView(0, 2, danPlayWayNames, ids);
			mPlayWayView.setOnPlayWayChangeListener(new TitlePlayWayChangeListener());
			mPop = new PopupWindow(v, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
			mPop.setBackgroundDrawable(new PaintDrawable(Color.parseColor("#00000000")));
			mPop.setFocusable(true);
			// mPop.setAnimationStyle(R.style.AnimationPreview);
			mPop.setOnDismissListener(new OnDismissListener() {

				@Override
				public void onDismiss() {
					performRotateAnima(mTriangleIv, -180, 0);
				}
			});
		} else {
			mPlayWayView.setPlayWayIds(ids);
		}

	}

	private void initScrollPlayWayView() {
		mTriangleIv.setVisibility(View.GONE);
		PlayWayLinearLayout ll_playWayView = (PlayWayLinearLayout) findViewById(R.id.ll_playWayView);
		ll_playWayView.initView(playWayNames.length, BetItem.getLocationByPlayType(playWayType, playWayIds), playWayNames, playWayIds);
		ll_playWayView.setOnPlayWayChangeListener(new ScrollPlayWayChangeListener());
	}

	private void setCenterViewClickable(boolean click) {
		if (click) {
			mTriangleIv.setVisibility(View.VISIBLE);
		} else {
			mTriangleIv.setVisibility(View.GONE);
		}
		mTitleTv.setClickable(click);
	}

	private GaoPinCountDownTimer timer;

	private void initData() {
		showLoadingState("");
//		getService().doGetPeriodNumber(lotteryType, new Listener<String>() {
//
//			@Override
//			public void onResponse(String json) {
//				stopLoadingState();
//				Logger.i(TAG, json);
//				mPeriodInfo = PeriodInfo.getPeriodInfo(lotteryType, json);
//				if (mPeriodInfo == null) {
//					return;
//				}
//
//				mPeriodInfo.setCurTime(System.currentTimeMillis());
//
//				tv_current_periodnumber.setText(mPeriodInfo.getPeriodNumber() + "期");
//				tv_end_date.setText(mPeriodInfo.getEndTime());
//
//				// ArrayList<AwardItem> awardList = mPeriodInfo.getAwardList();
//				// AwardItem lastAwardItem = awardList.get(0);
//				// tv_last_period.setText(lastAwardItem.periodNumber + "期");
//				// tv_last_period_result.setText(Html.fromHtml(PeriodInfo.splitHtmlResult(lotteryType,
//				// lastAwardItem.result)));
//
//				// int len = awardList.size();
//				// if (len > 6) {
//				// len = 6;
//				// }
//				// ll_left_latest_period.removeAllViews();
//				// ll_right_latest_period.removeAllViews();
//				// for (int i = 1; i < len; i++) {
//				// AwardItem awardItem = awardList.get(i);
//				// TextView tv1 = new TextView(mContext);
//				// tv1.setText(awardItem.periodNumber + "期");
//				// tv1.setTextColor(Color.parseColor("#ff4301"));
//				// int size1 = ComTools.px2sp(mContext,
//				// getResources().getDimensionPixelOffset(R.dimen.text_size_480_16px));
//				// tv1.setTextSize(size1);
//				// ll_left_latest_period.addView(tv1, 0);
//				//
//				// TextView tv2 = new TextView(mContext);
//				// tv2.setText(Html.fromHtml(PeriodInfo.splitHtmlResult(lotteryType,
//				// awardItem.result)));
//				//
//				// int size2 = ComTools.px2sp(mContext,
//				// getResources().getDimensionPixelOffset(R.dimen.text_size_480_16px));
//				// tv2.setTextSize(size2);
//				// ll_right_latest_period.addView(tv2, 0);
//				// }
//
//				if (lotteryType == BetItem.TYPE_JSSC || lotteryType == BetItem.TYPE_GD11XUAN5 || lotteryType == BetItem.TYPE_JX11XUAN5 || lotteryType == BetItem.TYPE_K3) {
//					timer = new GaoPinCountDownTimer(Integer.parseInt(mPeriodInfo.getEndTime()), 1000);
//					timer.setShowTextView(tv_end_date);
//					timer.setCallBack(new CountDownCallBack() {
//
//						@Override
//						public void onFinish() {
//							initData();
//						}
//					});
//					timer.start();
//				}
//				setYiLouInfo(lotteryType, mBetItem.getPlayWayType(), mPeriodInfo);
//			}
//
//		}, getDefaultErrorCallBack());
	}

	/**
	 * 初始化选号界面
	 */
	private void initBallView() {
		bet_panel.removeAllViews();

		int itemCount = mBetItem.getItemCount();
		mBallViews = new BallView[itemCount];
		for (int i = 0; i < itemCount; i++) {
			View bet_view = View.inflate(this, R.layout.bet_item, null);
			RelativeLayout relative = (RelativeLayout) bet_view.findViewById(R.id.RelativeLayout1);
			TextView tv = (TextView) bet_view.findViewById(R.id.choose_indicator);
			LinearLayout linear = (LinearLayout) bet_view.findViewById(R.id.bet_item_hint_panel);
			TextView hint1 = (TextView) bet_view.findViewById(R.id.hint);
			TextView hint2 = (TextView) bet_view.findViewById(R.id.hint2);

			mBallViews[i] = (BallView) bet_view.findViewById(R.id.choose_panel);

			BetItem.SubBetItem subItem = mBetItem.getSubBetItem()[i];

			if (mBetItem.getLeftStrs() == null) {
				relative.setVisibility(View.GONE);
				mBallViews[i].initView(i, subItem.getBallCount(), subItem.getAtLeastSelCount(), subItem.getAtMostSelCount(), mBetItem.getBallColors()[i], false, subItem.getBallStrs(), subItem.getRateStrs());
			} else {
				relative.setVisibility(View.VISIBLE);
				tv.setText(mBetItem.getLeftStrs()[i]);
				mBallViews[i].initView(i, subItem.getBallCount(), subItem.getAtLeastSelCount(), subItem.getAtMostSelCount(), mBetItem.getBallColors()[i], true, subItem.getBallStrs(), subItem.getRateStrs());
			}

			if (!subItem.getisLeftTvShow() && !subItem.getisShapeTvShow()) {
				linear.setVisibility(View.GONE);
			} else {
				linear.setVisibility(View.VISIBLE);

				if (subItem.getisLeftTvShow()) {
					hint1.setVisibility(View.VISIBLE);
					hint1.setText(subItem.getHintStr());
				} else {
					hint1.setVisibility(View.INVISIBLE);
				}

				if (subItem.getisShapeTvShow()) {
					hint2.setVisibility(View.VISIBLE);
				} else {
					hint2.setVisibility(View.GONE);
				}
			}
			mBallViews[i].setOnBallChangeListener(this);
			bet_panel.addView(bet_view);
		}

	}

	/**
	 * @author shouli.luo
	 * @time 2013-4-8 下午02:36:03
	 * @方法作用： 初始化球的选中状态,大小单双单独处理
	 */
	private void initBallData() {

		if (saveNum != null && !saveNum.equals("")) {
			// String[] tempStrs = saveNum.split("\\+");
			String[] tempStrs = null;
			int index = saveNum.indexOf(":");
			String result = "";
			if (index > 0) {
				result = saveNum.substring(index + 1);
			}
			if (lotteryType == BetItem.TYPE_QXC || lotteryType == BetItem.TYPE_3D || lotteryType == BetItem.TYPE_PL3 || lotteryType == BetItem.TYPE_PL5 || lotteryType == BetItem.TYPE_JX11XUAN5 || lotteryType == BetItem.TYPE_GD11XUAN5
					|| lotteryType == BetItem.TYPE_JSSC) {

				tempStrs = result.split("\\-");
				Logger.i(TAG, " tempStrs:" + tempStrs[0]);
			} else {
				result = result.replaceAll("-", ",");
				Logger.i(TAG, " result:" + result);

				tempStrs = result.split("\\|");
			}

			if ((lotteryType == BetItem.TYPE_XSSC) || (lotteryType == BetItem.TYPE_JSSC)) {
				if (playWayType == BetItem.PY_JSSC_DXDS) {
					for (int i = 0; i < tempStrs.length; i++) {
						mBallViews[i].setRandom(new String[] { tempStrs[i] });
					}
				} else {
					for (int i = 0; i < tempStrs.length; i++) {
						mBallViews[i].setRandom(tempStrs[i].split(","));
					}
				}
			} else {
				tempStrs = filtTemp(tempStrs);
				for (int i = 0; i < tempStrs.length; i++) {
					Logger.i(TAG, "str :" + tempStrs[i]);
					// String[] subTempStrs = tempStrs[i].split("\\|");
					String[] subTempStrs = tempStrs[i].split(",");
					mBallViews[i].setRandom(subTempStrs);
				}
			}

		}
	}



	// 过滤胆拖玩法的";"
	private String[] filtTemp(String[] array) {
		List<String> list = new ArrayList<String>();
		int len = array.length;
		for (int i = 0; i < len; i++) {
			if (array[i].contains(";")) {
				String[] split = array[i].split(";");
				for (int j = 0; j < split.length; j++) {
					list.add(split[j]);
				}
			} else {
				list.add(array[i]);
			}
		}
		if (lotteryType == TYPE_DLT && playWayType == BetItem.PY_DLT_DANTUO) {
			String[] dltSave = new String[4];
			dltSave[0] = list.get(0);
			dltSave[1] = list.get(2);
			dltSave[2] = list.get(1);
			dltSave[3] = list.get(3);
			return dltSave;
		}
		return ComTools.listToArray(list);
	}

	@Override
	protected void onShake() {
		if (mBetItem.isCanRandomSel()) {
			genRandomOne();
		}
	}
	

	protected void onDestroy() {
		if (timer != null) {
			timer.cancel();
			timer = null;
		}
		EventBus.getDefault().unregister(this);
		CaipiaoDao.getInstance().insertLastPlayWayType(mContext, lotteryType, playWayType);
		super.onDestroy();
	};

	@Override
	public void onClick(View v) {
		if (System.currentTimeMillis() - lastClickTime < 500) {
			Logger.i(TAG, "点击间隔小于500毫秒");
			return;
		}
		switch (v.getId()) {
		case R.id.center_textview:
			if (mPop != null) {
				if (mPop.isShowing()) {
					mPop.dismiss();
				} else {
					mPop.showAsDropDown(v);
					performRotateAnima(mTriangleIv, 0, -180);
				}
			}
			break;
		case R.id.ll_awardinfo_root:
			// boolean show = (Boolean) ll_last_awardinfo_root2.getTag();
			// ll_last_awardinfo_root2.setVisibility(show ? View.GONE :
			// View.VISIBLE);
			// ll_last_awardinfo_root2.setTag(!show);
			break;
		case R.id.clear:
			if (lotteryType == BetItem.TYPE_K3) {

			} else {
				for (int i = 0; i < mBallViews.length; i++) {
					mBallViews[i].clear();
				}
				isCanBet = false;
				setBottomInfo(0, 0);
			}
			break;
		case R.id.confirm:
			if (isCanBet && count > 0) {

				if (lotteryType == BetItem.TYPE_K3) {
					if (mBetItem.getPlayWayType() == BetItem.PY_K3_2TH_FX) {
						HashMap<Integer, Vector<String>> map = this.map;
						Set<Integer> keySet = map.keySet();
						for (Integer integer : keySet) {
							Vector<String> vector = map.get(integer);
							for (int i = 0; i < vector.size(); i++) {
								vector.set(i, vector.get(i).replace("*", ""));
							}
						}
					}
				}

				GenRandom.orderNum(map, isAddZero);

				String saveStr = ALG.getSaveNumber(map, count, lotteryType, mBetItem.getPlayWayType(), mBetItem.getPlayWayTypeName());
				String numStrs = ALG.getShowNumber(map, lotteryType, mBetItem.getPlayWayType());

				Logger.i(TAG, saveStr);
				Logger.i(TAG, numStrs);

				if (lotteryType == BetItem.TYPE_11XUAN5 || lotteryType == BetItem.TYPE_GD11XUAN5 || lotteryType == BetItem.TYPE_JX11XUAN5) {
					if (mBetItem.getPlayWayType() >= BetItem.PY_11XUAN5_REN2_DAN) {
						appStr = "胆拖-";
					} else {
						appStr = "普通-";
					}
				} else {
					appStr = "";
				}

				if (isFinish) {
					Logger.i(TAG, "isFinish,true:返回到投注界面");
					if (saveNum == null) {
						// 插入投注号码到数据库
						CaipiaoDao.getInstance().insertBettingNum(this, mBetItem.getPlayWayType(), appStr + mBetItem.getPlayWayName(), lotteryType, lotteryName, numStrs, saveStr, count);
					} else {
						// 更新投注号码到数据库
						CaipiaoDao.getInstance().updateBettingNum(this, mBetItem.getPlayWayType(), appStr + mBetItem.getPlayWayName(), lotteryType, lotteryName, numStrs, saveStr, count, _ID);
					}

					Intent i = new Intent();
					setResult(RESULT_OK, i);
				} else {
					Logger.i(TAG, "isFinish,false:打开投注界面");
					mPeriodInfo=new PeriodInfo();
					mPeriodInfo.setPeriodNumber("a");
					if (mPeriodInfo != null) {
						Intent i = new Intent(mContext, BetOkActivity.class);
						// 插入投注号码
						CaipiaoDao.getInstance().insertBettingNum(this, mBetItem.getPlayWayType(), appStr + mBetItem.getPlayWayName(), lotteryType, lotteryName, numStrs, saveStr, count);
						i.putExtra(BetItem.LOTTERY_TYPE, lotteryType);
						i.putExtra(BetItem.PLAYWAY_TYPE, mBetItem.getPlayWayType());
						i.putExtra(BetItem.PERIODINFO, mPeriodInfo);
						i.putExtra(BetItem.LOTTERY_PERIODID, getIntent().getIntExtra(BetItem.LOTTERY_PERIODID, -1));
						if (timer != null)
							i.putExtra("remainTime", timer.getmRemainTime());
						startActivity(i);
					} else {
						Toast.makeText(BettingActivity.this, "请等待获取服务器时间", Toast.LENGTH_SHORT).show();
						return;
					}
				}
				finish();
				overridePendingTransition(R.anim.umeng_fb_slide_in_from_right, R.anim.umeng_fb_slide_out_from_left);
			} else {
				if (mBetItem.isCanRandomSel()) {
					genRandomOne();
				} else {
					Toast.makeText(BettingActivity.this, "请至少选择一注", Toast.LENGTH_SHORT).show();
				}
			}

			break;
		}
		super.onClick(v);
		lastClickTime = System.currentTimeMillis();
	}

	@Override
	public void ballChange(Vector<String> mVec, TextView v, int index) {
		Logger.i(TAG, "index:" + index + ",ballChange:" + mVec);

		isCanBet = true;

		if (v == null) {
			calBetCountAndRevenue();
			return;
		}

		String ballnum = v.getText().toString();

		// 第一步,处理不能选择同样的球
		if (!mBetItem.isCanSelSameBall()) {
			setSameBall(ballnum, index);
		}

		// 第二步,再判断是否可以成为一注,如果不能组成一注就不必算了
		for (int i = 0; i < mBallViews.length; i++) {
			if (!mBallViews[i].getIsCanBet()) {
				isCanBet = false;
				setBottomInfo(0, 0);
				return;
			}
		}

		// 第三步,计算注数,根据彩种注数生产盈利信息
		calBetCountAndRevenue();

		for (int i = 0; i < mBallViews.length; i++) {
			mBallViews[i].setExceedPrice(false, count);
		}

	}

	/**
	 * @author shouli.luo
	 * @time 2013-4-1 下午12:21:56
	 * @方法作用： 此方法设置相同的球不能同时选择,并对双色球和大乐透单独处理
	 * @param ballnum
	 * @param curIndex
	 */
	private void setSameBall(String ballnum, int curIndex) {

		if (lotteryType == BetItem.TYPE_SSQ && mBetItem.getPlayWayType() == BetItem.PY_SSQ_DANTUO) {
			int samIndex = 2;
			if (curIndex == 0) {
				samIndex = 1;
			} else if (curIndex == 1) {
				samIndex = 0;
			}

			if (samIndex != 2) {
				Vector<String> vec1 = mBallViews[samIndex].getSelBallVec();
				if (vec1.contains(ballnum)) {
					mBallViews[samIndex].setUnSel(ballnum);
				}
			}
		} else if (lotteryType == TYPE_DLT && mBetItem.getPlayWayType() == BetItem.PY_DLT_DANTUO) {
			int samIndex = 0;
			if (curIndex == 0) {
				samIndex = 2;
			} else if (curIndex == 1) {
				samIndex = 3;
			} else if (curIndex == 2) {
				samIndex = 0;
			} else if (curIndex == 3) {
				samIndex = 1;
			}

			Vector<String> vec1 = mBallViews[samIndex].getSelBallVec();
			if (vec1.contains(ballnum)) {
				mBallViews[samIndex].setUnSel(ballnum);
			}

		} else {
			for (int i = 0; i < mBallViews.length; i++) {
				if (i != curIndex) {
					Vector<String> cur = mBallViews[i].getSelBallVec();
					if (cur.contains(ballnum)) {
						mBallViews[i].setUnSel(ballnum);
					}
				}
			}
		}
	}

	private void setPlayWayTitle(int playWay, String playWayName) {
		if (lotteryType == BetItem.TYPE_11XUAN5) {
			if (playWay >= BetItem.PY_11XUAN5_REN2_DAN && playWay <= BetItem.PY_11XUAN5_QIAN3ZU_DAN) {
				mTitleTv.setText(lotteryName + "-胆拖-" + playWayName);
			} else {
				mTitleTv.setText(lotteryName + "-" + playWayName);
			}
		} else {
			mTitleTv.setText(lotteryName + "-" + playWayName);
		}
	}

	private void setBottomInfo(int count, int totalPrice) {
		total_count.setText(String.valueOf(count));
		total_price.setText(String.valueOf(totalPrice));
	}

	/**
	 * @author shouli.luo
	 * @time 2013-4-3 下午02:17:19
	 * @方法作用： 计算注数和盈利
	 */
	private void calBetCountAndRevenue() {
		for (int i = 0; i < mBallViews.length; i++) {
			map.put(i, mBallViews[i].getSelBallVec());
		}
		count = ALG.getBetCount(map, lotteryType, mBetItem.getPlayWayType());
		setBottomInfo(count, count * 2);

		if (lotteryType == BetItem.TYPE_11XUAN5 || lotteryType == BetItem.TYPE_JSSC) {
			String revenue_info = RevenueInfoCreator.getInfo(map, lotteryType, mBetItem.getPlayWayType(), count);
			Logger.i(TAG, "盈利信息:" + revenue_info);
		}

	}

	/**
	 * 生成随机一注
	 */
	private void genRandomOne() {
		vib.vibrate(100);
		try {
			switch (lotteryType) {
			// case BetItem.TYPE_JX11XUAN5:
			// case BetItem.TYPE_GD11XUAN5:
			// List<Integer> list = new ArrayList<Integer>();
			// Random r = new Random();
			// list.add(r.nextInt(11) + 1);
			// while (list.size() != mBallViews.length) {
			// int v = r.nextInt(11) + 1;
			// if (!list.contains(v)) {
			// list.add(v);
			// }
			// }
			// for (int i = 0; i < mBallViews.length; i++) {
			// mBallViews[i].setRandom(new int[] { list.get(i) });
			// mBallViews[i].setExceedPrice(false, 1);
			// }
			// break;
			case BetItem.TYPE_K3:

				break;
			default:
				for (int i = 0; i < mBallViews.length; i++) {
					int[] nums = GenRandom.genNotRepeatNum(mBallViews[i].getAtLeastCount(), mBallViews[i].getAllBallCount());
					mBallViews[i].setRandom(nums);
					mBallViews[i].setExceedPrice(false, 1);
				}
				break;
			}
		} catch (GenRandom.TargetMinException e) {
			e.printStackTrace();
		}
	}

	private void setYiLouInfo(int lotteryType, int playWayType, PeriodInfo periodInfo) {
		if (mBallViews != null && periodInfo != null && mBallViews.length > 0) {
			List<String[]> rateList = periodInfo.getRateStrByPlayType(lotteryType, playWayType);
			int len = mBallViews.length;
			if (rateList == null || rateList.size() == 0) {
				for (int i = 0; i < len; i++) {
					mBallViews[i].setRateVisible(false);
				}
			} else {
				for (int i = 0; i < len; i++) {
					mBallViews[i].setRataStrs(rateList.get(i));
				}
			}
		}
	}





	private String arrayToString(int[] array) {
		int sum = 0;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < array.length; i++) {
			sum += array[i];
			sb.append(array[i]);
			if (i == array.length - 1) {
				sb.append("=");
			} else {
				sb.append("+");
			}
		}
		sb.append(sum);
		return sb.toString();
	}




	class TitlePlayWayChangeListener implements PlayWayView.IPlayWayChangeCallBack {

		@Override
		public void playWayChange(boolean isSameClick, int playWayId, String playWayName) {
			if (mPop != null)
				mPop.dismiss();
			if (!isSameClick) {
				changePlayWay(playWayId, playWayName);
			}
		}
	}

	class ScrollPlayWayChangeListener implements PlayWayView.IPlayWayChangeCallBack {

		@Override
		public void playWayChange(boolean isSameClick, int playWayId, String playWayName) {

			if (!isSameClick) {
				int[] danPlayByPlayWayIds = BetItem.getDanPlayByPlayWayType(lotteryType, playWayId);
				if (danPlayByPlayWayIds != null) {
					initTitlePlayWayView(danPlayByPlayWayIds);
					setCenterViewClickable(true);
				} else {
					setCenterViewClickable(false);
				}
				changePlayWay(playWayId, playWayName);
			}
		}
	}

	private void changePlayWay(int playWayId, String playWayName) {
		isCanBet = false;
		map.clear();
		mBetItem.setPlayWay(playWayId);
		playWayType = playWayId;
		setPlayWayTitle(playWayId, playWayName);
		setBottomInfo(0, 0);
		if (lotteryType == BetItem.TYPE_K3) {

		} else {
			initBallView();
		}
		setYiLouInfo(lotteryType, playWayId, mPeriodInfo);
	}

//	@Override
//	public void onBackPressed() {
//		quit();
//	}
//
//	@Override
//	protected void onLeftButtonClick() {
//		quit();
//	}
//
//	private void quit() {
//		CaipiaoDao.getInstance().insertLastPlayWayType(mContext, lotteryType, playWayType);
//		finish();
//	}

}
