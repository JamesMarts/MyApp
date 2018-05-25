package com.example.lijuw.myapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class BettingBaseActivity extends BaseActivity {

	int lotteryType = 0;

	private ImageButton rightBtn;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		initTitle();
	}

	protected void initTitle() {
		lotteryType = getIntent().getIntExtra(BetItem.LOTTERY_TYPE, -1);
		rightBtn = super.getRightImageBtn();
		rightBtn.setVisibility(View.VISIBLE);
		rightBtn.setImageResource(R.drawable.btn_banner_menu);
		rightBtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.right_imgbtn:

			break;
		}
		super.onClick(v);
	}



	public void gotoHelp() {


		String title;
		String url;
		int index = 0;
		switch (lotteryType) {
		case BetItem.TYPE_11XUAN5:
			index = 16;
			break;
		case BetItem.TYPE_JX11XUAN5:
			index = 31;
			break;
		case BetItem.TYPE_GD11XUAN5:
			index = 32;
			break;
		case BetItem.TYPE_SSQ:
			index = 14;
			break;
		case BetItem.TYPE_DLT:
			index = 15;
			break;
		case BetItem.TYPE_ZQDC:
			index = 17;
			break;
		case BetItem.TYPE_JCZQ:
			index = 18;
			break;
		case BetItem.TYPE_SFC:
			index = 19;
			break;
		case BetItem.TYPE_3D:
			index = 22;
			break;
		case BetItem.TYPE_QLC:
			index = 23;
			break;
		case BetItem.TYPE_QXC:
			index = 24;
			break;
		case BetItem.TYPE_PL3:
			index = 25;
			break;
		case BetItem.TYPE_PL5:
			index = 26;
			break;
		case BetItem.TYPE_JSSC:
			index = 27;
			break;
		case BetItem.TYPE_XSSC:
			index = 21;
			break;
		case BetItem.TYPE_JCLQ:
			index = 29;
			break;
		case BetItem.TYPE_K3:
			index = 30;
			break;

		default:

			break;
		}





//	/**
//	 * 从彩票投注页跳转到购彩记录页
//	 */
//	public void gotoGoucaijilu() {
//		if (checkToLogin()) {
//
//			// 清空ZCBetOkActivity数据
////			ZCBetOkActivity.clearInstance();
////
////			LocalActivityMgr.getInstance().getTopActivity().finish();
////			LocalActivityMgr.getInstance().getTopActivity().finish();
//			Intent intent = new Intent(mContext, GouCaiRecordActivity.class);
//			intent.putExtra("lotteryId", BetItem.getLotteryIdByLotteryType(lotteryType));
//			startActivity(intent);
//		}
	}


}