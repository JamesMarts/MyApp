package com.example.lijuw.myapp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.widget.Toast;


public class ZuiHaoSettingInfo implements Serializable {

	public static String ZH_SETTING_INFO = "setting_info";
	// 传入的数据
	public int maxBouns = 0;// 最大獎金
	public int minBouns = 0;// 最小獎金
	public String currentPeriodNumber;
	public int beforeSetting_Multiple;// 设置前各期的投注倍数
	public int beforeSetting_betPeriodsCount;// 设置前投了多少期
	public int betCount;// 投了多少注
	public int lotteryType;// 彩种类型
	public int playType;
	public int periodId;

	// 设置的数据

	public static final int SETTINGBY_TYPE_1 = 0;
	public static final int SETTINGBY_TYPE_2 = 1;
	public static final int SETTINGBY_TYPE_3 = 2;

	public int SETTINGBY_TYPE = SETTINGBY_TYPE_1;

	public int totalSettingPeriodCount = 10;// 設置的连续追号总期数 ,默认10期
	public int startMultiple = 1;// 起始倍数

	public int minYinglilv = 30;// 全程最低盈利率

	public int prePeriodCount = 5;// 前多少期
	public int preYinglilv = 50; // 前几期的盈利百分比
	public int afterYinglilv = 20;// 后面的盈利百分比

	public int minYingliMoney = 30;// 全程最低盈利多少钱

	// 传出的数据
	public int afterSetting_betPeriodsCount;// 设置后投了多少期
	public ArrayList<Integer> afterSetting_MultipleList;// 设置后各期的投注倍数 ，按期次顺序add值
	public double totalCost;// 总共花费多少钱
	public boolean isWonStop = true;// 是否中奖后停止追号 默认为true
	public int stopMoney = 0;// 中奖多少钱停止追号

	public void toBet(Activity activity) {

		String createForm_shareType = afterSetting_betPeriodsCount > 1 ? BetItem.SHARE_TYPE_CAHSE
				: BetItem.SHARE_TYPE_SELF;
		int single_price = 2;
		boolean isDltZjtz = false;// 是否是大樂透追加投注
		boolean isZjStop = isWonStop;// 是否中奖停止追期
		List<LotteryInfo> betNums = CaipiaoDao.getInstance().getBettingNumList(
				activity, lotteryType);

//		String postStr = QiuCaiBetInfo.getZuiHaoBetPostDataByLotteryType(
//				betNums, lotteryType, playType, periodId, currentPeriodNumber,
//				afterSetting_MultipleList, single_price,
//				BetItem.SHARE_TYPE_SELF, createForm_shareType,
//				BetItem.getTypeNameByType(lotteryType),
//				afterSetting_MultipleList.size(), isDltZjtz, isZjStop,
//				totalCost, stopMoney);

		Toast.makeText(activity, "去投注确认界面", 0).show();
//		Intent intent = new Intent(activity, BetConfirmActivity.class);
//		intent.putExtra(BetItem.BET_POSTSTR, postStr);
//		intent.putExtra(BetItem.BET_COST, totalCost);
//		intent.putExtra(BetItem.BET_TYPE, BetItem.BET_TYPE_SELF);// 自购
//		intent.putExtra(BetItem.LOTTERY_TYPE, lotteryType);
//		intent.putExtra(PeriodInfo.PERIOD_NUMBER, currentPeriodNumber);
//		activity.startActivity(intent);

	}

	public String getCurrentPeriodShowNumber() {
		int strLength = 0;
		if (lotteryType == BetItem.TYPE_K3) {

			strLength = 2;

		} else if (lotteryType == BetItem.TYPE_11XUAN5
				|| lotteryType == BetItem.TYPE_JX11XUAN5
				|| lotteryType == BetItem.TYPE_GD11XUAN5) {

			strLength = 2;
		} else if (lotteryType == BetItem.TYPE_JSSC
				|| lotteryType == BetItem.TYPE_XSSC) {

			strLength = 3;
		}

		return currentPeriodNumber.substring(currentPeriodNumber.length()
				- strLength, currentPeriodNumber.length());
	}

	public ArrayList<Integer> getMultiplesBySettedTypeData() {
		ArrayList<Integer> numbers = null;
		switch (SETTINGBY_TYPE) {
		case 0:
			numbers = CPBaoMath.getPercent(minYinglilv, minBouns, betCount,
					totalSettingPeriodCount, startMultiple);
			break;
		case 1:

			numbers = CPBaoMath.getMixPercent(prePeriodCount, preYinglilv,
					afterYinglilv, minBouns, betCount, totalSettingPeriodCount,
					startMultiple);

			break;
		case 2:
			numbers = CPBaoMath.getWIthMinProfit(minYingliMoney, minBouns,
					betCount, totalSettingPeriodCount, startMultiple);
			break;
		}
		return numbers;
	}

	public ArrayList<Integer> addUnConformPeriod(
			ArrayList<Integer> numbers) {
		if (numbers != null) {
			while (numbers.size() < totalSettingPeriodCount) {
				numbers.add(0);
			}
		}

		return numbers;
	}
}
