package com.example.lijuw.myapp;

import java.util.ArrayList;

import android.util.Log;



public class CPBaoMath {

	// 单注最大倍数
	private final static int MAX_LOTTERY_MULTIPLE_NUM = 9999;

	private CPBaoMath() {

	}

	// /********************** 普通投注 **********************/
	// public static final int PY_11XUAN5_REN1 = TYPE_11XUAN5 + 1; // 前一
	// public static final int PY_11XUAN5_REN2 = TYPE_11XUAN5 + 2; // 任选2
	// public static final int PY_11XUAN5_REN3 = TYPE_11XUAN5 + 3; // 任选3
	// public static final int PY_11XUAN5_REN4 = TYPE_11XUAN5 + 4; // 任选4
	// public static final int PY_11XUAN5_REN5 = TYPE_11XUAN5 + 5; // 任选5
	// public static final int PY_11XUAN5_REN6 = TYPE_11XUAN5 + 6; // 任选6
	// public static final int PY_11XUAN5_REN7 = TYPE_11XUAN5 + 7; // 任选7
	// public static final int PY_11XUAN5_REN8 = TYPE_11XUAN5 + 8; // 任选8
	// public static final int PY_11XUAN5_QIAN2ZHI = TYPE_11XUAN5 + 9; // 前二直
	// public static final int PY_11XUAN5_QIAN2ZU = TYPE_11XUAN5 + 10; // 前二组
	// public static final int PY_11XUAN5_QIAN3ZHI = TYPE_11XUAN5 + 11; // 前三直
	// public static final int PY_11XUAN5_QIAN3ZU = TYPE_11XUAN5 + 12; // 前三组
	// /********************** 胆拖投注 **********************/
	// public static final int PY_11XUAN5_REN2_DAN = TYPE_11XUAN5 + 13; // 任选2
	// public static final int PY_11XUAN5_REN3_DAN = TYPE_11XUAN5 + 14; // 任选3
	// public static final int PY_11XUAN5_REN4_DAN = TYPE_11XUAN5 + 15; // 任选4
	// public static final int PY_11XUAN5_REN5_DAN = TYPE_11XUAN5 + 16; // 任选5
	// public static final int PY_11XUAN5_REN6_DAN = TYPE_11XUAN5 + 17; // 任选6
	// public static final int PY_11XUAN5_REN7_DAN = TYPE_11XUAN5 + 18; // 任选7
	// public static final int PY_11XUAN5_QIAN2ZU_DAN = TYPE_11XUAN5 + 19; //
	// 前二组
	// public static final int PY_11XUAN5_QIAN3ZU_DAN = TYPE_11XUAN5 + 20; //
	// 前三组
	/**
	 * 
	 * @param baseAmount
	 *            中奖基本奖金
	 * @param betNumber
	 *            总注数
	 * @param lotteryType
	 *            11选5玩法类型
	 * @param selNumber
	 *            选择的球的个数
	 * @return 最大奖金
	 */
	public static int getBiggestWinBy11Xuan5(int baseAmount, int betNumber,
			int lotteryType, int selNumber) {
		int lowAmount = 0;
		int highAmount = 0;
		int lowProfits = 0;
		int highProfits = 0;
		Log.i("lotteryType", "" + lotteryType);

		if ((lotteryType == BetItem.PY_11XUAN5_REN1)
				|| (lotteryType >= BetItem.PY_11XUAN5_QIAN2ZHI && lotteryType <= BetItem.PY_11XUAN5_QIAN3ZU)
				|| lotteryType == BetItem.PY_11XUAN5_REN5) {
			lowAmount = baseAmount;
			lowProfits = lowAmount - betNumber * 2;
			return lowAmount;
		} else if (lotteryType >= BetItem.PY_11XUAN5_REN2
				&& lotteryType <= BetItem.PY_11XUAN5_REN4) {
			lowAmount = baseAmount;
			lowProfits = lowAmount - betNumber * 2;

			if (betNumber == 1) {
				return lowAmount;
			}

			highAmount = baseAmount * betNumber;
			highProfits = highAmount - betNumber * 2;

			return highAmount;
		} else {
			if (lotteryType == BetItem.PY_11XUAN5_REN6) {
				// lowAmount = baseAmount * (selNumber - 5);
				lowAmount = baseAmount * ALG.combination((selNumber - 5), 1);
				lowProfits = lowAmount - betNumber * 2;
				return lowAmount;

			} else if (lotteryType == BetItem.PY_11XUAN5_REN7) {
				// lowAmount = baseAmount * (selNumber - 5) * (7-5);
				lowAmount = baseAmount * ALG.combination((selNumber - 5), 2);

				lowProfits = lowAmount - betNumber * 2;
				return lowAmount;

			} else {
				lowAmount = baseAmount * ALG.combination((selNumber - 5), 3);
				lowProfits = lowAmount - betNumber * 2;
				return lowAmount;
			}
		}
	}

	/**
	 * 
	 * @param aProfitPercent
	 *            最低盈利率
	 * @param aMaxBonus
	 *            最大中奖奖金
	 * @param aZhuNum
	 *            每期注数
	 * @param aIssueNum
	 *            连续追号期数
	 * @param aStartMul
	 *            起始倍数
	 * @return 各期次倍数array， 如果无法计算用户的方案返回 null
	 */
	public static ArrayList<Integer> getPercent(int aProfitPercent,
			int aMaxBonus, int aZhuNum, int aIssueNum, int aStartMul){
		ArrayList<Integer> res = new ArrayList<Integer>();
		// NSInteger sumIssue = 0;
		int sumMoney = 0;
		sumMoney += aZhuNum * aStartMul * 2;

		if ((aStartMul * aMaxBonus - sumMoney) * 100 / sumMoney < aProfitPercent) {
			return null;
		}

		if (aStartMul >= MAX_LOTTERY_MULTIPLE_NUM) {
			return null;
		}

		Integer addArr = aStartMul;
		res.add(aStartMul);

		boolean hasError = false;

		for (int i = 1; i < aIssueNum; i++) {
			int maxCurMulNum = 1;

			if ((100 * aMaxBonus - 100 * 2 * aZhuNum - 2 * aZhuNum
					* aProfitPercent) <= 0) {
				hasError = true;
				break;
			}

			float maxCurMulNumF = (float) (1.0 * (100 * sumMoney + sumMoney
					* aProfitPercent) / (100 * aMaxBonus - 100 * 2 * aZhuNum - 2
					* aZhuNum * aProfitPercent));

			int maxCurMulNumInt = (int) maxCurMulNumF;
			if (maxCurMulNumInt == maxCurMulNumF) {
				maxCurMulNum = maxCurMulNumInt;
			} else {
				maxCurMulNum = maxCurMulNumInt + 1;
			}

			if (maxCurMulNum < aStartMul) {
				maxCurMulNum = aStartMul;
			}

			if (maxCurMulNum >= MAX_LOTTERY_MULTIPLE_NUM) {
				break;
			}

			addArr = maxCurMulNum;
			res.add(addArr);

			sumMoney += 2 * aZhuNum * maxCurMulNum;

		}

		if (hasError == true) {
			return null;
		} else {
			return res;
		}

	}

	/**
	 * 
	 * @param aPreIssureNum
	 * @param aPreProfitPercent
	 *            前 aPreIssureNum 期次 盈利率 aPreProfitPercent
	 * @param aOtherProfitPercent
	 *            之后盈利率
	 * @param aMaxBonus
	 *            最大中奖奖金
	 * @param aZhuNum
	 *            每期注数
	 * @param aIssueNum
	 *            连续追号期数
	 * @param aStartMul
	 *            起始倍数
	 * @return 各期次倍数array， 如果无法计算用户的方案返回 null
	 */
	public static ArrayList<Integer> getMixPercent(int aPreIssureNum,
			int aPreProfitPercent, int aOtherProfitPercent, int aMaxBonus,
			int aZhuNum, int aIssueNum, int aStartMul) {
		ArrayList<Integer> res = new ArrayList<Integer>();

		// NSInteger sumIssue = 0;
		int sumMoney = 0;
		sumMoney += aZhuNum * aStartMul * 2;

		if (aPreIssureNum <= 0) {
			return null;
		}

		if ((aStartMul * aMaxBonus - sumMoney) * 100 / sumMoney < aPreProfitPercent) {
			return null;
		}

		if (aStartMul >= MAX_LOTTERY_MULTIPLE_NUM) {
			return null;
		}

		Integer addArr = aStartMul;
		res.add(addArr);

		boolean hasError = false;

		for (int i = 1; i < aIssueNum; i++) {

			int profitPercent = aPreProfitPercent;

			if (i >= aPreIssureNum) {
				profitPercent = aOtherProfitPercent;
			}

			Integer maxCurMulNum = 1;

			if ((100 * aMaxBonus - 100 * 2 * aZhuNum - 2 * aZhuNum
					* profitPercent) <= 0) {
				hasError = true;
				break;
			}

			float maxCurMulNumF = (float) (1.0 * (100 * sumMoney + sumMoney
					* profitPercent) / (100 * aMaxBonus - 100 * 2 * aZhuNum - 2
					* aZhuNum * profitPercent));

			int maxCurMulNumInt = (int) maxCurMulNumF;
			if (maxCurMulNumInt == maxCurMulNumF) {
				maxCurMulNum = maxCurMulNumInt;
			} else {
				maxCurMulNum = maxCurMulNumInt + 1;
			}

			if (maxCurMulNum < aStartMul) {
				maxCurMulNum = aStartMul;
			}

			if (maxCurMulNum >= MAX_LOTTERY_MULTIPLE_NUM) {
				break;
			}

			addArr = maxCurMulNum;
			res.add(addArr);

			sumMoney += 2 * aZhuNum * maxCurMulNum;

		}

		if (hasError) {
			return null;
		} else {
			return res;
		}
	}

	/**
	 * 
	 * @param aMinProfit
	 *            最小盈利金额
	 * @param aMaxBonus
	 *            最大中奖奖金
	 * @param aZhuNum
	 *            每期注数
	 * @param aIssueNum
	 *            连续追号期数
	 * @param
	 *            起始倍数
	 * @return 各期次倍数array， 如果无法计算用户的方案返回 null
	 */
	public static ArrayList<Integer> getWIthMinProfit(int aMinProfit,
			int aMaxBonus, int aZhuNum, int aIssueNum, int aaStartMul) {
		ArrayList<Integer> res = new ArrayList<Integer>();

		int aStartMul = 1;

		int sumMoney = 0;

		if (aMaxBonus <= 2 * aZhuNum) {
			return null;
		}

		float startF = (float) (aMinProfit * 1.0 / (aMaxBonus - 2 * aZhuNum));
		int startInt = (int) startF;

		if (startF == startInt) {
			aStartMul = startInt;
		} else {
			aStartMul = startInt + 1;
		}

		if (aStartMul < aaStartMul) {
			aStartMul = aaStartMul;
		}

		sumMoney += aZhuNum * aStartMul * 2;

		if (aStartMul >= MAX_LOTTERY_MULTIPLE_NUM) {
			return null;
		}

		Integer addArr = aStartMul;
		res.add(addArr);

		boolean hasError = false;

		for (int i = 1; i < aIssueNum; i++) {

			Integer maxCurMulNum = 1;

			if (aMaxBonus - 2 * aZhuNum <= 0) {
				hasError = true;
				break;
			}

			float maxCurMulNumF = (float) ((aMinProfit + sumMoney) * 1.0 / (aMaxBonus - 2 * aZhuNum));

			int maxCurMulNumInt = (int) maxCurMulNumF;
			if (maxCurMulNumInt == maxCurMulNumF) {
				maxCurMulNum = maxCurMulNumInt;
			} else {
				maxCurMulNum = maxCurMulNumInt + 1;
			}

			if (maxCurMulNum < aaStartMul) {
				maxCurMulNum = aaStartMul;
			}

			if (maxCurMulNum >= MAX_LOTTERY_MULTIPLE_NUM) {
				break;
			}

			addArr = maxCurMulNum;
			res.add(maxCurMulNum);

			sumMoney += 2 * aZhuNum * maxCurMulNum;

		}

		if (hasError) {
			return null;
		} else {
			return res;
		}
	}

	
}
