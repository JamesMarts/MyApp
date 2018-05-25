package com.example.lijuw.myapp;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.Html;
import android.text.InputType;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.logger.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.xml.transform.ErrorListener;

import de.greenrobot.event.EventBus;

/**
 * @author shouli.luo
 * @time 2013-3-27 下午04:28:33
 * @introduce 选球选好点击确认跳转的页面
 */
public class BetOkActivity extends BaseActivity implements TextWatcher {

	private static final String TAG = "BetOkActivity";
	public static final String FINISH = "FINISH";// 是否是点击item或者点击添加自选号码弹出的页面
	public static final String UPDATE_BET_NUMBER = "UPDATE_BET_NUMBER";// 是否是修改用戶投注的號碼
	public static final String SAVE_NUM = "SAVE_NUM";// 保存的号码
	public static final String ActionType = "actionType";
	public static final String DORANDOM = "doRandom";

	HashMap<Integer, Vector<String>> map = new HashMap<Integer, Vector<String>>();

	private LinearLayout follow_panel, setting_1, append_panel, append_stop_zui;// 追号控件，中奖后停止追号控件
	private Button clearanceBtn;
	private ProgressButton payBtn;
	private Button clearBtn;
	public Button addZxBtn, addJxBtn;
	public LinearLayout betBtnsLayout;

	/*
	 * 追号设置按钮
	 */
	private Button bt_zhuihaoshezhi;

	private EditText editZui, editTou;
	private CheckBox ckStopZui, dlTZuiTou;
	private ListView mListView;
	private TextView totalPriceTv;

	private int playWayType, lotteryType, periodId;
	private String playWayName, lotteryName;
	private BettingAdapter mAdapter;

	private double deviceDensity = 0.0;

	private BetItem mBetItem;
	private boolean isAddZero = false;
	private boolean isToBet = false; // 是否是直接跳转到此页面

	private String appStr = "";

	private PeriodInfo mPeriodInfo;

	private int maxChase = 40;

	private int mChaseNum = 0;

	private int danCount = 0;// 胆的个数

	private int totalCount = 0;// 总共多少注

	private int singlePrice = 2;// 没注的价格，针对处理大乐透，追加投注一注变3元的情况
	private boolean isSelectZuiTou = false;

	private GaoPinCountDownTimer timer;
	private TextView info_zhu;
	private TextView info_bei;
	private TextView info_qi;
	private Button add_random5_bet;
	private TextView info_zhuijia;
	private LinearLayout ll_keyboard;
    private String postStr = null;

    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bet_ok);
		initRedTitleStyle();
		enableShake();
		initData();
		initView();
		getLeftButton().setImageDrawable(getResources().getDrawable(R.drawable.btn_banner_home_back));
		EventBus.getDefault().register(this);
	}

	private void initData() {
		isToBet = getIntent().getBooleanExtra(BetItem.TO_BET, false);
		playWayType = getIntent().getIntExtra(BetItem.PLAYWAY_TYPE, -1);
		lotteryType = getIntent().getIntExtra(BetItem.LOTTERY_TYPE, -1);
		periodId = getIntent().getIntExtra(BetItem.LOTTERY_PERIODID, -1);
		danCount = getIntent().getIntExtra(BetItem.DAN_COUNT, 0);
		// saveNum = getIntent().getStringExtra(BetItem.SAVE_NUM);
		isAddZero = BetItem.getNeedAddZeroByType(lotteryType);
		mBetItem = new BetItem(this, lotteryType, playWayType);
		playWayName = mBetItem.getPlayWayName();
		lotteryName = BetItem.getLotteryNameByType(lotteryType);

		if (isToBet) {
//			getPeriodNumber();
		} else {
			mPeriodInfo = (PeriodInfo) getIntent().getSerializableExtra(BetItem.PERIODINFO);

			if (checkIsGaoPin()) {
				long remainTime = getIntent().getLongExtra("remainTime", 0);
				Logger.i(TAG, "remainTime:" + remainTime);
				enableTimer(remainTime);
			}
		}
	}

//	private void getPeriodNumber() {
//		showLoadingState();
//		getService().doGetPeriodNumber(lotteryType, new Listener<String>() {
//
//			@Override
//			public void onResponse(String json) {
//				stopLoadingState();
//				Logger.i(TAG, json);
//				mPeriodInfo = PeriodInfo.getPeriodInfo(lotteryType, json);
//				if (mPeriodInfo != null) {
//					mPeriodInfo.setCurTime(System.currentTimeMillis());
//					if (checkIsGaoPin())
//						enableTimer(Integer.parseInt(mPeriodInfo.getEndTime()));
//				}
//			}
//		}, new ErrorListener() {
//
//			@Override
//			public void onErrorResponse(VolleyError error) {
//				stopLoadingState();
//			}
//		});
//	}

	private void enableTimer(long time) {
		timer = new GaoPinCountDownTimer(time);
		timer.setShowTextView(getCenterTextView());
		timer.setCallBack(new GaoPinCountDownTimer.CountDownCallBack() {

			@Override
			public void onFinish() {
				setCenterTitle(mPeriodInfo.getPeriodNumber() + "已截止");
				showGaoPinPeriodUselessDialog();
			}
		});
		timer.start();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		if (mAdapter != null) {
			mAdapter.getCursor().requery();
		}
		super.onResume();
	}

	@Override
	protected void onDestroy() {
		if(timer != null){
			timer.cancel();
			timer = null;
		}
		super.onDestroy();
        EventBus.getDefault().unregister(this);
	}
	
	private void initView() {

		et_focus = (EditText) findViewById(R.id.et_focus);

		if (lotteryType == BetItem.TYPE_SFC || lotteryType == BetItem.TYPE_REN9) {

			// list_header.betBtnsLayout.setVisibility(View.GONE);
		} else {
			append_panel = (LinearLayout) findViewById(R.id.append_panel);
			append_stop_zui = (LinearLayout) findViewById(R.id.append_stop_zui);
			follow_panel = (LinearLayout) findViewById(R.id.follow_panel);
			setting_1 = (LinearLayout) findViewById(R.id.setting_1);

			if (lotteryType == BetItem.TYPE_DLT) {
				showBetSetingPanel();
			} else {
				dismissBetSettingPanel();
			}

		}

		if (lotteryType != BetItem.TYPE_GD11XUAN5 && lotteryType != BetItem.TYPE_JX11XUAN5 && lotteryType != BetItem.TYPE_11XUAN5 && lotteryType != BetItem.TYPE_XSSC && lotteryType != BetItem.TYPE_JSSC && lotteryType != BetItem.TYPE_K3) {// 高频彩没有合买
			getRightTextView().setVisibility(View.GONE);
			getRightTextView().setText("合买");
			getRightTextView().setTextColor(Color.WHITE);
			getRightTextView().setTextSize(ComTools.convertTextSize(mContext, R.dimen.text_size_small));
			Drawable drawable = getResources().getDrawable(R.drawable.btn_back_pressed);
			getRightTextView().setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
			getRightTextView().setOnClickListener(this);
		}

		// 追号设置按钮
		bt_zhuihaoshezhi = (Button) findViewById(R.id.bt_zhuihaoshezhi);
		bt_zhuihaoshezhi.setOnClickListener(this);

		// 暫時只对高频彩的做追号设置
		if (lotteryType == BetItem.TYPE_GD11XUAN5 || lotteryType == BetItem.TYPE_JX11XUAN5 || lotteryType == BetItem.TYPE_11XUAN5 || lotteryType == BetItem.TYPE_XSSC || lotteryType == BetItem.TYPE_JSSC || lotteryType == BetItem.TYPE_K3) {
			// bt_zhuihaoshezhi.setVisibility(View.VISIBLE);
		} else {
			// bt_zhuihaoshezhi.setVisibility(View.GONE);
		}

		iv_no_data_shake = (ImageView) findViewById(R.id.iv_no_data_shake);

		betBtnsLayout = (LinearLayout) findViewById(R.id.bet_btns_layout);
		addZxBtn = (Button) findViewById(R.id.add_bet);
		addJxBtn = (Button) findViewById(R.id.add_random_bet);
		add_random5_bet = (Button) findViewById(R.id.add_random5_bet);
		addZxBtn.setOnClickListener(BetOkActivity.this);
		addJxBtn.setOnClickListener(BetOkActivity.this);
		add_random5_bet.setOnClickListener(BetOkActivity.this);

		clearBtn = (Button) findViewById(R.id.clear);
		clearanceBtn = (Button) findViewById(R.id.clearance);
		clearanceBtn.setVisibility(View.GONE);
		payBtn = (ProgressButton) findViewById(R.id.pay);
		payBtn.setOnClickListener(this);

		clearBtn.setOnClickListener(this);
		editZui = (EditText) findViewById(R.id.follow_periods);
		// editZui.setOnClickListener(this);
		editZui.setInputType(InputType.TYPE_NULL);
		keyBoardController = new NumberKeyBoardController(this, editZui) {
			@Override
			protected void onConfirmClick() {
				ll_keyboard.setVisibility(View.GONE);
			}
		};
		keyBoardController.setInputChangeListener(new NumberKeyBoardController.OnInputChangeListener() {

			@Override
			public void onTextChange(TextView tv, String changeNum) {
				doNumberChange(tv, changeNum);
			}

		});
		ll_keyboard = (LinearLayout) findViewById(R.id.ll_keyboard);
		ll_keyboard.setVisibility(View.GONE);
		ll_keyboard.addView(keyBoardController.getView());

		editZui.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus) {
					v.setBackgroundResource(R.drawable.rect_blue);
					keyBoardController.setFocusTextView(editZui);
					if (!keyBoardController.isKeyBoardShow()) {
						ll_keyboard.setVisibility(View.VISIBLE);
						keyBoardController.showKeyBoard();
					}
				} else {
					v.setBackgroundColor(Color.WHITE);
					keyBoardController.hideKeyBoard();
				}
			}
		});

		editTou = (EditText) findViewById(R.id.bet_times);
		// editTou.setOnClickListener(this);
		editTou.setInputType(InputType.TYPE_NULL);
		editTou.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus) {
					v.setBackgroundResource(R.drawable.rect_blue);
					keyBoardController.setFocusTextView(editTou);
					if (!keyBoardController.isKeyBoardShow()) {
						ll_keyboard.setVisibility(View.VISIBLE);
						keyBoardController.showKeyBoard();
					}
				} else {
					v.setBackgroundColor(Color.WHITE);
					keyBoardController.hideKeyBoard();
				}
			}
		});

		editZui.addTextChangedListener(this);
		editTou.addTextChangedListener(this);

		ckStopZui = (CheckBox) findViewById(R.id.follow_mode);
		dlTZuiTou = (CheckBox) findViewById(R.id.append_bet);

		// 处理大乐透追加投注
		dlTZuiTou.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				dealDltZuiTou(isChecked);
			}
		});

		info_zhu = (TextView) findViewById(R.id.info_zhu);
		info_bei = (TextView) findViewById(R.id.info_bei);
		info_qi = (TextView) findViewById(R.id.info_qi);
		info_zhuijia = (TextView) findViewById(R.id.info_zhuijia);

		totalPriceTv = (TextView) findViewById(R.id.total_price);

		setTitleLeftButtonVisibility(true);
		setCenterTitle(lotteryName + "投注");
		mListView = (ListView) findViewById(R.id.betting_list);
		// mListView.addFooterView(list_footer.footView, null, false);

		mAdapter = new BettingAdapter(this, CaipiaoDao.getInstance().queryCursorByType(this, lotteryType));

		mListView.setAdapter(mAdapter);

		// countListView();

		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
				Cursor c = (Cursor) arg0.getAdapter().getItem(position);
				LotteryInfo lotteryInfo = CaipiaoDao.getInstance().getBettingNum(c);
				performEdit(lotteryInfo);
			}
		});


        if(ComTools.USER_FILTER){
            if(getApp().isLogin()){
                if(!ComTools.IS_OPEN_USER ){
                    payBtn.setEnabled(false);
                }
            }else {
                payBtn.setEnabled(false);
            }
        }

	}

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {

			if (totalCount == 0) {
				et_focus.requestFocus();
			} else {
				et_focus.clearFocus();
			}

			info_zhu.setText(String.valueOf(totalCount));
			info_bei.setText(String.valueOf(zt));
			info_qi.setText(String.valueOf(qici_count));
			totalPriceTv.setText(String.valueOf(totalCostmoney));

			// editZui.clearFocus();
			// editTou.clearFocus();
			// editZui.setFocusable(true);
			// totalPriceTv.requestFocus();
			// ll_keyboard.setVisibility(View.GONE);
		};
	};

	private void doNumberChange(TextView tv, String changeNum) {
		if (tv.equals(editZui)) {
			int len = changeNum.length();
			if (len >= 1) {
				checkMaxNum(tv, changeNum);
				Integer number = Integer.parseInt(changeNum);

				// 期数大于1显示中奖后停止追号的面板
				if (number != null) {
					if (number > 1) {

						showBetSetingPanel();
					} else {
						dismissBetSettingPanel();
					}
				}
				mChaseNum = checkMinNum(tv, changeNum);
			} else {
				Toast.makeText(BetOkActivity.this, "至少投一期",Toast.LENGTH_SHORT).show();
				// dismissBetSettingPanel();
				// tv.setText("1");
			}
		} else if (tv.equals(editTou)) {
			int len = changeNum.length();
			if (len >= 1) {
				checkMinNum(tv, changeNum);
			} else {
				Toast.makeText(BetOkActivity.this, "至少投一倍",Toast.LENGTH_SHORT).show();
				// tv.setText("1");
			}
		}
		showTotalPrice();
	}

	@Override
	protected void onShake() {
		addJxBtn.performClick();
	}

	private void performEdit(LotteryInfo lotteryInfo) {

		Logger.i(TAG, "_id:" + lotteryInfo.getId());
		Intent i = new Intent(BetOkActivity.this, BettingActivity.class);
		i.putExtra(BetItem.LOTTERY_TYPE, lotteryType);
		i.putExtra(BetItem.PLAYWAY_TYPE, mBetItem.getPlayWayType());
		// i.putExtra(BetItem.PLAYWAY_TYPE, lotteryInfo.getPlayWayType());
		i.putExtra(SAVE_NUM, lotteryInfo.getSaveNo());
		// i.putExtra(SAVE_NUM, lotteryInfo.getlotteryNo());
		i.putExtra(DatabaseHelper.Betting._ID, lotteryInfo.getId());
		i.putExtra(UPDATE_BET_NUMBER, true);
		i.putExtra(FINISH, true);
		startActivity(i);
	}

	protected void dealDltZuiTou(boolean isChecked) {
		isSelectZuiTou = isChecked;
		if (isChecked) {
			singlePrice = 3;
		} else {
			singlePrice = 2;
		}

		mAdapter.notifyDataSetChanged();// 修改出票面板显示
		showTotalPrice();// 修改底部价格显示
	}

	/**
	 * @author shouli.luo
	 * @time 2013-4-3 下午05:32:34
	 * @方法作用： 机选
	 */
	private void doRandom(boolean isDaXiaoDanShuang) {

		if (lotteryType == BetItem.TYPE_K3) {

			// if (K3BetAddRandomNumHelper.doRandomListener != null) {
			// K3BetAddRandomNumHelper.doRandomListener.doRandom();
			// }

		} else {

			map.clear();
			getAndPutRandomNumberToMap(isDaXiaoDanShuang);

			int count = ALG.getBetCount(map, lotteryType, mBetItem.getPlayWayType());
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
			CaipiaoDao.getInstance().insertBettingNum(this, mBetItem.getPlayWayType(), appStr + mBetItem.getPlayWayName(), lotteryType, lotteryName, numStrs, saveStr, count);
		}

		mAdapter.getCursor().requery();
		mAdapter.notifyDataSetChanged();
		showTotalPrice();
	}

	private void getAndPutRandomNumberToMap(boolean isDaXiaoDanShuang) {

		try {
			int index = 0;
			while (index < mBetItem.getItemCount()) {
				Vector<String> nums = GenRandom.genNotRepeatNumVec(mBetItem.getSubBetItem()[index].getAtLeastSelCount(), mBetItem.getSubBetItem()[index].getBallCount(), isDaXiaoDanShuang);
				if (!mBetItem.isCanSelSameBall() && index > 0 && checkIsRepSel(index, nums)) {
					continue;
				} else {
					map.put(index, nums);
					index++;
				}
			}
		} catch (GenRandom.TargetMinException e) {
			e.printStackTrace();
		}

	}

	private boolean checkIsRepSel(int index, Vector<String> nums) {

		for (int i = 0; i < index; i++) {
			Vector<String> selNum = map.get(i);
			for (int j = 0; j < nums.size(); j++) {
				String numStr = nums.get(j);
				if (selNum.contains(numStr)) {
					return true;
				}

			}
		}

		return false;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.delete_icon:
			deleteItem((LotteryInfo) v.getTag());
			break;
		case R.id.iv_edit:
			performEdit((LotteryInfo) v.getTag());
			break;
		case R.id.add_random5_bet:
			for (int i = 0; i < 5; i++) {
				addJxBtn.performClick();
			}
			break;
		case R.id.add_random_bet:

			if (lotteryType == BetItem.TYPE_REN9 || lotteryType == BetItem.TYPE_SFC) {
				// try {
				// GenRandom.getZuCaiNumMap(map, lotteryType);
				// int count = ALG.getBetCount(map, lotteryType,
				// mBetItem.getPlayWayType());
				// GenRandom.orderNum(map,false);
				// String saveStr = ALG.getSaveNumber(map, count,lotteryType,
				// mBetItem.getPlayWayType(),mBetItem.getPlayWayTypeName());
				// String numStrs = ALG.getShowNumber(map, lotteryType,
				// mBetItem.getPlayWayType());
				// CaipiaoDao.getInstance().insertBettingNum(this,
				// mBetItem.getPlayWayType(),
				// "普通投注", lotteryType, lotteryName, numStrs,saveStr, count);
				// mAdapter.getCursor().requery();
				// } catch (TargetMinException e) {
				// // TODO Auto-generated catch block
				// e.printStackTrace();
				// }
			} else {
				if (!mBetItem.isCanRandomSel()) {
					switch (lotteryType) {
					case BetItem.TYPE_SSQ:
						if (playWayType == BetItem.PY_SSQ_DANTUO) {
							// mBetItem.setPlayWay(BetItem.PY_SSQ_PUTONG);
						}
						break;
					case BetItem.TYPE_DLT:
						if (playWayType == BetItem.PY_DLT_DANTUO) {
							// mBetItem.setPlayWay(BetItem.PY_DLT_PUTONG);
						}
						break;
					case BetItem.TYPE_QLC:
						if (playWayType == BetItem.PY_QLC_DANTUO) {
							// mBetItem.setPlayWay(BetItem.PY_DLT_PUTONG);
						}
						break;
					case BetItem.TYPE_11XUAN5:
					case BetItem.TYPE_JX11XUAN5:
					case BetItem.TYPE_GD11XUAN5:
						if (playWayType >= BetItem.PY_11XUAN5_REN2_DAN) {
							// mBetItem.setPlayWay(playWayType - 12);
						}
						break;
					default:
						break;
					}
				}
				if ((lotteryType == BetItem.TYPE_XSSC && playWayType == BetItem.PY_XSSC_DXDS) || (lotteryType == BetItem.TYPE_JSSC && playWayType == BetItem.PY_JSSC_DXDS)) {
					doRandom(true);
				} else if (lotteryType == BetItem.TYPE_K3) {

					doRandom(false);

				} else {
					if (!mBetItem.isCanRandomSel()) {
						ToastManager.showShortToast(mContext, "该玩法不支持机选!");
					} else {
						doRandom(false);
					}
				}
			}
			// countListView();

			break;
		case R.id.add_bet:
			Intent intent = null;
			if (lotteryType == BetItem.TYPE_SFC || lotteryType == BetItem.TYPE_REN9) {
				// i = new
				// Intent(BetOkActivity1.this,BetMatchTwoActivity.class);
			} else {
				intent = new Intent(BetOkActivity.this, BettingActivity.class);
				intent.putExtra(BetItem.LOTTERY_TYPE, lotteryType);
				intent.putExtra(BetItem.PLAYWAY_TYPE, mBetItem.getPlayWayType());
				intent.putExtra(FINISH, true);
				startActivityForResult(intent, 10);
			}
			// countListView();
			break;
		case R.id.pay:// 付款
			if (true) {

                if (!checkUserInput()) {
					payBtn.stopLoadingAnimation();
					return;
				}

                double betPrice = 0;
				int multiple = Integer.valueOf(editTou.getText().toString());
				int zhuiHaoCount = Integer.valueOf(editZui.getText().toString());

				if (lotteryType == BetItem.TYPE_REN9 || lotteryType == BetItem.TYPE_SFC) {

				} else {
					periodNumber = null;
					if (lotteryType == BetItem.TYPE_11XUAN5 || lotteryType == BetItem.TYPE_JX11XUAN5 || lotteryType == BetItem.TYPE_GD11XUAN5 || lotteryType == BetItem.TYPE_JSSC || lotteryType == BetItem.TYPE_XSSC) {
						if (mPeriodInfo != null) {
							// Date date = mPeriodInfo.getNewDate();
							// long destanceTime = System.currentTimeMillis()
							// - mPeriodInfo.getCurTime();
							// long time = date.getTime() + destanceTime;
							// Logger.i("time", "sum_cur:" + destanceTime /
							// 1000);
							// // formatLongToTimeStr(destanceTime, date);
							// periodNumber = PeriodInfo.getPeriodNumber(
							// lotteryType, mPeriodInfo.getbeforeTime(),
							// new Date(time));
							periodNumber = mPeriodInfo.getPeriodNumber();

						} else {
							Toast.makeText(BetOkActivity.this, "请等待获取服务器期次信息", Toast.LENGTH_SHORT).show();
							payBtn.stopLoadingAnimation();
							return;
						}
					} else {
						if (mPeriodInfo != null) {
							periodNumber = mPeriodInfo.getPeriodNumber();
							// periodId = mPeriodInfo.getCurrentPeriodId();
							// if(lotteryType==BetItem.TYPE_K3){
							// periodId = mPeriodInfo.getCurrentPeriodId();
							// }
						} else {
							Toast.makeText(BetOkActivity.this, "请等待获取服务器期次信息", Toast.LENGTH_SHORT).show();
							payBtn.stopLoadingAnimation();
							return;
						}

					}

					String createForm_shareType = zhuiHaoCount > 1 ? BetItem.SHARE_TYPE_CAHSE : BetItem.SHARE_TYPE_SELF;
					boolean isDltZjtz = dlTZuiTou.isChecked();// 是否是大樂透追加投注
					boolean prizeStop = ckStopZui.isChecked();// 是否中奖停止追期

					List<LotteryInfo> betNums = CaipiaoDao.getInstance().getBettingNumList(this, lotteryType);
					QiuCaiBetInfo qiuCaiBetInfo = new QiuCaiBetInfo();
					postStr = qiuCaiBetInfo.getPostStrByLotteryType(lotteryType, periodNumber, betNums, multiple, zhuiHaoCount, prizeStop, isDltZjtz);

//					if (!checkAccountBalance(qiuCaiBetInfo.getTotalPrice())) {
//						// 去充值页面
//						showRechargeDialog();
//						payBtn.stopLoadingAnimation();
//						return;
//					}

                    betPrice = qiuCaiBetInfo.getTotalPrice();
					Logger.i(TAG, "postStr:" + postStr);
				}
				// Logger.i(TAG, postStr);

                BetPlugin.doBet(this,betPrice);

			} else {
				payBtn.stopLoadingAnimation();
			}
			break;
		case R.id.clear:
			if (mAdapter.getCursor().getCount() > 0) {
				createClearDialog();
			}
			break;
		case R.id.right_tv:

		// 追号按钮事件
		case R.id.bt_zhuihaoshezhi:
			goToZHSetting();

			break;
		case R.id.bet_times:
			keyBoardController.setFocusTextView(editTou);
			if (!keyBoardController.isKeyBoardShow()) {
				ll_keyboard.setVisibility(View.VISIBLE);
				keyBoardController.showKeyBoard();
			}
			break;
		case R.id.follow_periods:
			keyBoardController.setFocusTextView(editZui);
			if (!keyBoardController.isKeyBoardShow()) {
				ll_keyboard.setVisibility(View.VISIBLE);
				keyBoardController.showKeyBoard();
			}
			break;
		default:
			break;
		}
		super.onClick(v);
	}

	private boolean checkUserInput() {
		boolean userInputOK = true;
		if (TextUtils.isEmpty(editTou.getText().toString())) {
			ToastManager.showShortToast(mContext, "至少投一倍");
			editTou.setText(String.valueOf(1));
			userInputOK = false;
		}

		if (TextUtils.isEmpty(editZui.getText().toString())) {
			ToastManager.showShortToast(mContext, "至少买一期");
			editZui.setText(String.valueOf(1));
			userInputOK = false;
		}

		if(mAdapter != null){
			if (mAdapter.getCount() <= 0) {
				ToastManager.showShortToast(mContext, R.string.tips_choice_at_least_one);
				userInputOK = false;
			}
		}else{
			userInputOK = false;
		}
		

		if (checkIsGaoPin()) {
			if (timer.getmRemainTime() <= 0 && noCheckGaoPin) {
				showGaoPinPeriodUselessDialog();
				userInputOK = false;
			}
		}

		return userInputOK;
	}

	// 跳转到追号设置
	private void goToZHSetting() {

		if (mPeriodInfo != null) {
			periodNumber = mPeriodInfo.getPeriodNumber();
		}

		// 判断能不能进入智能追号
		List<LotteryInfo> betNums = CaipiaoDao.getInstance().getBettingNumList(this, lotteryType);

		if (betNums.size() == 0) {
			// 不能进入追号设置界面
			Toast.makeText(mContext, "请选择一个投注方案！", Toast.LENGTH_SHORT).show();
			return;
		} else if (betNums.size() > 0) {

			ZuiHaoSettingInfo info = new ZuiHaoSettingInfo();
			int multiple = 1;
			String times = editTou.getText().toString();
			if (!times.equals("")) {
				multiple = Integer.parseInt(times);
			}
			// 投了多少期
			int qici_count = 1;
			String qici_count_Str = editZui.getText().toString();

			if (!qici_count_Str.equals("")) {
				qici_count = Integer.parseInt(qici_count_Str);
			}

			info.beforeSetting_betPeriodsCount = qici_count;
			info.beforeSetting_Multiple = multiple;
			info.currentPeriodNumber = periodNumber;
			info.lotteryType = lotteryType;
			info.playType = playWayType;
			info.betCount = totalCount;

			Intent intent = null;
			if (betNums.size() == 1) {
				// 进入智能追号界面
				// LotteryInfo lotteryInfo = betNums.get(0);
				// intent = new Intent(this, ZNZHSettingActivity.class);
				// HashMap<Integer, Vector<String>> map =
				// ALG.transformSaveNumberToMap(lotteryInfo.getlotteryType(),
				// lotteryInfo.getPlayWayType(), lotteryInfo.getSaveNo());
				//
				// int[] bonus = RevenueInfoCreator.getSchemeMinMaxBonus(map,
				// lotteryInfo.getlotteryType(), lotteryInfo.getPlayWayType(),
				// totalCount);
				//
				// info.minBouns = bonus[0];
				// info.maxBouns = bonus[1];
				//
				// // 当最小盈利小于0时没法进入智能追号
				// if ((info.minBouns - 2 * totalCount) < 0) {
				//
				// DialogUtil.createOnlyShowMessageDialog(this, "温馨提示",
				// "您投注的方案您投注的方案不符合盈利追号", "知道了").show();
				// return;
				// }

			} else {
				// 进入普通追号界面
				// intent = new Intent(this, ZhuihaoshezhiActivity.class);
			}

			// 进入普通追号界面
			// intent = new Intent(this, ZhuihaoshezhiActivity.class);
			// intent.putExtra(ZuiHaoSettingInfo.ZH_SETTING_INFO, info);
			//
			// startActivity(intent);
		}

	}


    public void onEvent(BetPlugin.BetResult obj){
        doBet(lotteryType, postStr);
    }

	/**
	 * 投注确认页
	 * 
	 * @param lotteryType
	 * @param postStr
	 */
	private void doBet(final int lotteryType, final String postStr) {

		showLoadingState();
//		getService().doBetting(lotteryType, postStr, mPeriodInfo.getWareID(), false, new Listener<String>() {
//
//			@Override
//			public void onResponse(String response) {
//				stopLoadingState();
//				payBtn.stopLoadingAnimation();
//				Logger.i(TAG, response);
//				if (!TextUtils.isEmpty(response)) {
//					try {
//						JSONObject jsonObject = new JSONObject(response);
//						int status = jsonObject.optInt("status");
//						boolean betOk = status == 0;
//						if (betOk) {
//
//							UMDATA.uploadBetData(mContext, UMDATA.TYPE_BET_NUMBER, totalCostmoney);
//
//							CaipiaoDao.getInstance().deleteBettingNumByType(BetOkActivity.this, lotteryType);
//							if (mAdapter != null) {
//								mAdapter.getCursor().requery();
//								mAdapter.notifyDataSetChanged();
//							}
//							showBetOkDialog();
//						} else {
//							String data = jsonObject.optString("data");
//							ToastManager.showShortToast(mContext, jsonObject.optString("msg") + data);
//						}
//					} catch (JSONException e) {
//						e.printStackTrace();
//					}
//				}
//
//			}
//		}, new ErrorListener() {
//
//			@Override
//			public void onErrorResponse(VolleyError error) {
//				stopLoadingState();
//				payBtn.stopLoadingAnimation();
//				ErrorTips.showNetErrorToast();
//			}
//		});
	}

//	private void showBetOkDialog() {
//		AlertDialog dialog = DialogUtil.showNormalDialog(BetOkActivity.this, getString(R.string.app_name), getString(R.string.tips_bet_ok), getString(R.string.btn_text_continue), getString(R.string.btn_text_bet_records),
//				new DialogInterface.OnClickListener() {
//
//					@Override
//					public void onClick(DialogInterface dialog, int which) {
//						switch (which) {
//						case DialogInterface.BUTTON_POSITIVE:
//							ComTools.doBetting(BetOkActivity.this, lotteryType);
//							break;
//						case DialogInterface.BUTTON_NEGATIVE:
//							Intent intent = new Intent(mContext, GouCaiRecordActivity.class);
//							intent.putExtra("lotteryId", BetItem.getLotteryIdByLotteryType(lotteryType));
//							startActivity(intent);
//							break;
//						}
//						dialog.dismiss();
//						finish();
//					}
//				});
//		dialog.setCanceledOnTouchOutside(false);
//	}

	private boolean checkIsGaoPin() {
		return lotteryType == BetItem.TYPE_K3 || lotteryType == BetItem.TYPE_JSSC || lotteryType == BetItem.TYPE_JX11XUAN5 || lotteryType == BetItem.TYPE_GD11XUAN5;
	}

	private boolean noCheckGaoPin = true;

	private void showGaoPinPeriodUselessDialog() {
		try {
			if (TextUtils.isEmpty(periodNumber)) {
				noCheckGaoPin = false;
				StringBuilder sb = new StringBuilder();
				sb.append("当前期:");
				sb.append(periodNumber);
				sb.append("已经截止,是否投入下一期:");
				sb.append(Integer.parseInt(periodNumber) + 1);
				sb.append("?");
				DialogUtil.showNormalDialog(this, null, sb.toString(), "确定", "取消", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						switch (which) {
						case DialogInterface.BUTTON_POSITIVE:
							periodNumber = String.valueOf(Integer.parseInt(periodNumber) + 1);
							if (mPeriodInfo != null)
								mPeriodInfo.setPeriodNumber(periodNumber);
							payBtn.performClick();
							break;
						case DialogInterface.BUTTON_NEGATIVE:
							CaipiaoDao.getInstance().deleteBettingNumByType(BetOkActivity.this, lotteryType);
							finish();
							break;
						}
						noCheckGaoPin = true;
					}
				});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void gotoHelp() {

		// Logger.i(TAG, "gotoHelp url:" + HelpActivity.sData[0]);
		//
		// String title = getString(R.string.commission);
		// String url = "file:///android_asset/help/notice.html";// + "#" +
		// // sData[arg2];
		// Intent intent = new Intent();
		// intent.putExtra(HelpActivity.KEY_TITLE, title);
		// intent.putExtra(HelpActivity.KEY_URL, url);
		// intent.setClass(this, HelpWebViewActivity.class);
		// startActivity(intent);
	}

	private void heMai() {

		if (mAdapter.getCount() <= 0) {
			ToastManager.showShortToast(mContext, R.string.tips_choice_at_least_one);
			return;
		}

		int units = 0;
		String postStr = null;
		List<LotteryInfo> infoList = CaipiaoDao.getInstance().getBettingNumList(this, lotteryType);
		for (int i = 0; i < infoList.size(); i++) {
			LotteryInfo info = infoList.get(i);
			units = units + info.getBetCount();
		}

		// 倍数
		int multiple1 = editTou.getText().toString().equals("") ? 1 : Integer.valueOf(editTou.getText().toString());

		if (units * multiple1 * singlePrice >= 2) {
			if (lotteryType == BetItem.TYPE_REN9 || lotteryType == BetItem.TYPE_SFC) {
//				postStr = SfRen9Info.getSfRen9PostData(CaipiaoDao.getInstance().getBettingNumList(this, lotteryType), multiple1, periodId, BetItem.SHARE_TYPE_TOGETHER, BetItem.getTypeNameByType(lotteryType), danCount);
			} else {

				boolean isDltZjtz = dlTZuiTou.isChecked();// 是否是大樂透追加投注
				boolean isZjStop = ckStopZui.isChecked();// 是否中奖停止追期

				if (mPeriodInfo != null) {
					periodNumber = mPeriodInfo.getPeriodNumber();
					// periodId = mPeriodInfo.getCurrentPeriodId();
					// if(lotteryType==BetItem.TYPE_K3){
					// periodId = mPeriodInfo.getCurrentPeriodId();
					// }
				} else {
					Toast.makeText(BetOkActivity.this, "请等待获取服务器期次信息", Toast.LENGTH_SHORT).show();
					return;
				}

				postStr = new QiuCaiBetInfo().getPostStrByLotteryType(lotteryType, periodNumber, infoList, multiple1, 1, isZjStop, isDltZjtz);
			}

			Intent intent1 = new Intent(BetOkActivity.this, CreateGroupBuyActivity.class);
			intent1.putExtra(BetItem.SHARE_TYPE_TOGETHER, postStr);
			intent1.putExtra(BetItem.LOTTERY_TYPE, lotteryType);

			int allMoney = singlePrice * units * multiple1;
			intent1.putExtra("allMoney", allMoney);
			if (allMoney < 8) {
				Toast.makeText(BetOkActivity.this, "合买金额不能小于8元!", Toast.LENGTH_SHORT).show();
				return;
			}
			if (mPeriodInfo != null) {
				intent1.putExtra(BetItem.PERIODINFO, mPeriodInfo);
			}
			startActivity(intent1);
		} else {
			Toast.makeText(this, "合买金额不能少于8元", Toast.LENGTH_SHORT).show();
		}
	}

	public void formatLongToTimeStr(long l, Date date) {
		String str = "";
		int hour = 0;
		int minute = 0;
		int second = 0;
		second = (int) l / 1000;
		if (second > 60) {
			minute = second / 60;
			second = second % 60;
		}

		if (minute > 60) {
			hour = minute / 60;
			minute = minute % 60;
		}
		String strtime = hour + "小时" + minute + "分钟" + second + "秒";
		Logger.i(TAG, "sum_time:" + strtime);
		date.setHours(date.getHours() + hour);
		if (date.getSeconds() + second > 59) {
			date.setMinutes(date.getMinutes() + 1);
			date.setSeconds((date.getSeconds() + second) % 60);
		} else {
			date.setSeconds(date.getSeconds() + second);
		}

		if (date.getMinutes() + minute > 59) {
			date.setHours(date.getHours() + 1);
			date.setMinutes((date.getMinutes() + minute) % 60);
		} else {
			date.setMinutes(date.getMinutes() + minute);
		}
	}

	private String periodNumber;
	private double totalCostmoney;

	/**
	 * 是否退出客户端的对话框
	 */
	private void createExitDialog() {
		Builder builder = new AlertDialog.Builder(this);

		builder.setMessage(getString(R.string.cache_bet_warning)).setPositiveButton(R.string.madelist_save, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				dialog.dismiss();
				BetOkActivity.this.finish();
			}
		}).setNegativeButton(R.string.exit_without_saved, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				CaipiaoDao.getInstance().deleteBettingNumByType(BetOkActivity.this, lotteryType);
				dialog.dismiss();
				BetOkActivity.this.finish();
			}
		}).show();
	}

	/**
	 * 是否退出客户端的对话框
	 */
	private void creatDeleteAllDialog() {
		Builder builder = new AlertDialog.Builder(this);

		builder.setMessage(getString(R.string.exit_bet_hint)).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				CaipiaoDao.getInstance().deleteBettingNumByType(BetOkActivity.this, lotteryType);
				dialog.dismiss();
				BetOkActivity.this.finish();
			}
		}).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				dialog.dismiss();
			}
		}).show();
	}

	/**
	 * 是否退出客户端的对话框
	 */
	private void createDeleteOneDialog(final int _id) {
		Builder builder = new AlertDialog.Builder(this);

		builder.setMessage(getString(R.string.warning_del_number)).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				dialog.dismiss();
				CaipiaoDao.getInstance().deleteSingleBettingNum(BetOkActivity.this, _id);
				mAdapter.getCursor().requery();
				showTotalPrice();
			}
		}).setNegativeButton(R.string.mls_status_cancel, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				dialog.dismiss();
			}
		}).show();
	}

	public void showHemaiDialog() {
		AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
		dialog.setTitle(null);
		dialog.setMessage(getString(R.string.groupbuy_follows_warning)).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				dialog.dismiss();
				heMai();
				editZui.requestFocus();
				editZui.setText("1");

			}
		}).setNegativeButton(R.string.mls_status_cancel, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				dialog.dismiss();
			}
		}).show();

	}

	/**
	 * 是否退出客户端的对话框
	 */
	private void createClearDialog() {
		Builder builder = new AlertDialog.Builder(this);

		builder.setMessage(getString(R.string.clear_hint_content)).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				dialog.dismiss();
				CaipiaoDao.getInstance().deleteBettingNumByType(BetOkActivity.this, lotteryType);
				mAdapter.getCursor().requery();

				if (editTou != null) {
					editTou.setText("1");
				}

				if (editZui != null) {
					editZui.setText("1");
				}

			}
		}).setNegativeButton(R.string.mls_status_cancel, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				dialog.dismiss();
			}
		}).show();
	}

	@Override
	protected void onLeftButtonClick() {
		// quit();
		super.onLeftButtonClick();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		// if (event.getKeyCode() == KeyEvent.KEYCODE_BACK && event.getAction()
		// == KeyEvent.ACTION_DOWN) {
		// quit();
		// }
		return super.onKeyDown(keyCode, event);
	}

	private void quit() {
		if (mAdapter.getCursor().getCount() > 0) {
			if (lotteryType == BetItem.TYPE_SFC || lotteryType == BetItem.TYPE_REN9) {
				creatDeleteAllDialog();
			} else {
				createExitDialog();
			}
		}
		noCheckGaoPin = true;
	}

	/**
	 * 继承CursorAdapter，用于适配ListView
	 */
	private class BettingAdapter extends CursorAdapter {

		LayoutInflater mInflater;

		public BettingAdapter(Context context, Cursor c) {
			super(context, c);
			mInflater = LayoutInflater.from(context);
			// TODO Auto-generated constructor stub
		}

		@Override
		public View newView(Context context, Cursor cursor, ViewGroup parent) {
			BetNumberHolder holder = new BetNumberHolder();
			View convertView = mInflater.inflate(R.layout.item_number_list, parent, false);
			holder.delete_icon_iv = (ImageView) convertView.findViewById(R.id.delete_icon);
			holder.iv_edit = (ImageView) convertView.findViewById(R.id.iv_edit);
			holder.numbersTv = (TextView) convertView.findViewById(R.id.numbers);
			holder.betWayTv = (TextView) convertView.findViewById(R.id.betway);
			holder.betTimesTv = (TextView) convertView.findViewById(R.id.bet_times);
			holder.view1 = convertView.findViewById(R.id.view1);
			convertView.setTag(holder);
			return convertView;
		}

		@Override
		public void bindView(final View convertView, Context context, final Cursor cursor) {
			// TODO Auto-generated method stub
			BetNumberHolder holder = (BetNumberHolder) convertView.getTag();
			final LotteryInfo lotteryInfo = CaipiaoDao.getInstance().getBettingNum(cursor);

			holder.delete_icon_iv.setTag(lotteryInfo);
			holder.delete_icon_iv.setOnClickListener(BetOkActivity.this);

			holder.iv_edit.setTag(lotteryInfo);
			holder.iv_edit.setOnClickListener(BetOkActivity.this);

			Spanned text = Html.fromHtml(lotteryInfo.getlotteryNo());
			holder.numbersTv.setText(text);
			holder.betWayTv.setText(lotteryInfo.getPlayWayName());
			holder.betTimesTv.setText(lotteryInfo.getBetCount() + "注 共" + lotteryInfo.getBetCount() * singlePrice + "元");
			showTotalPrice();
		}

		@Override
		public void notifyDataSetChanged() {
			if (getCount() == 0) {
				iv_no_data_shake.setVisibility(View.VISIBLE);
			} else {
				iv_no_data_shake.setVisibility(View.GONE);
			}
			super.notifyDataSetChanged();
		}
		// new OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// // TODO Auto-generated method stub
		// mListView.performItemClick(convertView, cursor.getPosition(), 0);
		// }
		// }

	}

	class BetNumberHolder {
		ImageView delete_icon_iv;
		ImageView iv_edit;
		TextView numbersTv;
		TextView betWayTv;
		TextView betTimesTv;
		View view1;
	}

	@Override
	public void afterTextChanged(Editable editable) {

	}

	@Override
	public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
	}

	@Override
	public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
		//
		// if (editZui.isFocused()) {
		// Editable editable = editZui.getText();
		// int len = editable.length();
		// String str = editable.toString();
		//
		// if (len >= 1) {
		// // if (len == 3) {
		// // checkMaxNum(editZui, str);
		// // }
		//
		// checkMaxNum(editZui, str);
		//
		// Integer number = Integer.parseInt(str);
		//
		// // 期数大于1显示中奖后停止追号的面板
		// if (number != null) {
		// if (number > 1) {
		//
		// showBetSetingPanel();
		// } else {
		// dismissBetSettingPanel();
		// }
		// }
		//
		// mChaseNum = checkMinNum(editZui, str);
		// } else {
		// ToastManager.showShortToast(BetOkActivity.this, "至少投一期");
		// dismissBetSettingPanel();
		// }
		// } else if (editTou.isFocused()) {
		// Editable editable = editTou.getText();
		// int len = editable.length();
		// String str = editable.toString();
		// if (len >= 1) {
		// checkMinNum(editTou, str);
		// } else {
		// ToastManager.showShortToast(BetOkActivity.this, "至少投一倍");
		// }
		//
		// }
		//
		// showTotalPrice();

	}

	private int checkMinNum(TextView edit, String str) {
		int num = 0;
		try {
			num = Integer.parseInt(str);
			if (num == 0) {
				num = 1;
				edit.setText(1 + "");
				Toast.makeText(mContext, "最小输入是1", Toast.LENGTH_SHORT).show();
			}

			if ((lotteryType == BetItem.TYPE_3D || lotteryType == BetItem.TYPE_QLC) && num > 50) {
				Toast.makeText(mContext, "最大倍数是50", Toast.LENGTH_SHORT).show();
				edit.setText(String.valueOf(50));
				return 50;
			}

			if ((lotteryType == BetItem.TYPE_PL3 || lotteryType == BetItem.TYPE_PL5 || lotteryType == BetItem.TYPE_QXC) && num > 99) {
				Toast.makeText(mContext, "最大倍数是99", Toast.LENGTH_SHORT).show();
				edit.setText(String.valueOf(99));
				return 99;
			}

			if (str.length() > 1 && num < 10) {
				edit.setText(num + "");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return num;
	}

	private int checkMaxNum(TextView tv, String str) {
		int num = 0;
		try {
			num = Integer.parseInt(str);
			if (num > maxChase) {
				num = maxChase;
				tv.setText(maxChase + "");
				// tv.setSelection(str.length() - 1);
				Toast.makeText(mContext, "可输入最大值是" + maxChase, Toast.LENGTH_SHORT).show();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return num;
	}

	private void showTotalPrice() {

		Cursor cursor = CaipiaoDao.getInstance().queryCursorByType(this, lotteryType);
		totalCount = 0;// 重置为0，再计算
		for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
			LotteryInfo bi = CaipiaoDao.getInstance().getBettingNum(cursor);
			totalCount += bi.getBetCount();
		}
		cursor.close();
		zt = 1;
		// 投了多少倍
		String times = editTou.getText().toString();
		if (!times.equals("")) {
			zt = Integer.parseInt(times);
		}
		qici_count = 1;
		String qici_count_Str = editZui.getText().toString();

		if (!TextUtils.isEmpty(qici_count_Str)) {
			qici_count = Integer.parseInt(qici_count_Str);
		}

		totalCostmoney = singlePrice * totalCount * zt * qici_count;
		// String text = String.valueOf(totalCount + "注x" + zt + "倍x" +
		// qici_count + "期");
		if ((lotteryType == BetItem.TYPE_DLT) && isSelectZuiTou) {
			// 判断是否选择追加注
			info_zhuijia.setText(" 追加投注");
		}
		// info_zhu.setText(String.valueOf(totalCount) );
		// info_bei.setText(String.valueOf(zt));
		// info_qi.setText(String.valueOf(qici_count));
		// totalPriceTv.setText(String.valueOf(totalCostmoney));
		handler.sendEmptyMessage(0);
		Logger.i(TAG, "total money:" + totalCostmoney);
		// totalPriceTv.setText(totalCostmoney + "");
	}

	/**
	 * 追号设置请求编号
	 */
	public static final int RESULT_CODE_ZHUIHAOSHEZHI = 800;
	private NumberKeyBoardController keyBoardController;
	private int qici_count;
	private int zt;
	private ImageView iv_no_data_shake;
	private EditText et_focus;

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		Logger.i(TAG, "onActivityResult resultCode:" + resultCode);
		switch (resultCode) {

		case RESULT_OK:
			Bundle b = data.getExtras();
			// String string = b.getString("CALCULATION");
			if (mAdapter != null) {
				mAdapter.getCursor().requery();
				// countListView();
				mAdapter.notifyDataSetChanged();
			}
			break;

		// 追号设置完成
		case RESULT_CODE_ZHUIHAOSHEZHI:

			break;

		}
	}


	private void deleteItem(LotteryInfo lotteryInfo) {
		createDeleteOneDialog(lotteryInfo.getId());
	}

	private void showBetSetingPanel() {
		setting_1.setVisibility(View.VISIBLE);
		append_panel.setVisibility(View.INVISIBLE);
		append_stop_zui.setVisibility(View.VISIBLE);
		if (lotteryType == BetItem.TYPE_DLT) {
			append_panel.setVisibility(View.VISIBLE);
		}
	}

	private void dismissBetSettingPanel() {

		if (lotteryType == BetItem.TYPE_DLT) {
			showBetSetingPanel();
		} else {
			setting_1.setVisibility(View.GONE);
		}
	}

	// public void onEvent(ViewNumber viewNumber){
	// doNumberChange(viewNumber.tv, viewNumber.text);
	// }

}
