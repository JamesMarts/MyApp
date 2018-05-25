package com.example.lijuw.myapp;

import java.util.HashMap;
import java.util.Vector;

import com.orhanobut.logger.Logger;

public class RevenueInfoCreator {
	public static final int TYPE_KL8 = 10;// 快乐8
	public static final int PY_KL8_REN1 = TYPE_KL8 + 1; // 任选1
	public static final int PY_KL8_REN2 = TYPE_KL8 + 2; // 任选2
	public static final int PY_KL8_REN3 = TYPE_KL8 + 3; // 任选3
	public static final int PY_KL8_REN4 = TYPE_KL8 + 4; // 任选4
	public static final int PY_KL8_REN5 = TYPE_KL8 + 5; // 任选5
	public static final int PY_KL8_REN6 = TYPE_KL8 + 6; // 任选6
	public static final int PY_KL8_REN7 = TYPE_KL8 + 7; // 任选7
	public static final int PY_KL8_REN8 = TYPE_KL8 + 8; // 任选8
	public static final int PY_KL8_REN9 = TYPE_KL8 + 9; // 任选8
	public static final int PY_KL8_REN10 = TYPE_KL8 + 10; // 任选8
	
	private static final String TAG = "RevenueInfoCreator";

	public static String getInfo(HashMap<Integer, Vector<String>> map,
			int lotteryType, int lotteryPlayWay, int count) {
		Vector<String> numbers = map.get(0);
		int size = numbers.size();
		String info = "";
		switch (lotteryType) {

		case BetItem.TYPE_KL8:
			info = getKl8Info(size, lotteryPlayWay);
			break;
		case BetItem.TYPE_11XUAN5:
		case BetItem.TYPE_JX11XUAN5:
		case BetItem.TYPE_GD11XUAN5:
			info = get11XUAN5Info(size, lotteryPlayWay, count);
			break;
		case BetItem.TYPE_JSSC:
			info = getSSCInfo(size, lotteryPlayWay, count);
			break;
		default:
			break;
		}
		return info;
	}

	private static String get11XUAN5Info(int totalSize, int lotteryPlayWay, int count) {
		String info = "";
		int preLeastBonus = 0;// 最低奖项奖金
		int preMaxBonus = 0;// 最高奖项奖金
		int preBetSize = 0;// 每一注多少号码,最高奖的中奖号码长度
		int leastCanWinSize = 0;// 最少中多少号码有奖，最小奖号码的长度
		switch (lotteryPlayWay) {
		case BetItem.PY_11XUAN5_REN1:
			// 玩法猜中号码任意一个，单注奖金6元
			preLeastBonus = 13;
			preMaxBonus = 13;
			preBetSize = 1;
			leastCanWinSize = 1;
			break;
		case BetItem.PY_11XUAN5_REN2:
		case BetItem.PY_11XUAN5_REN2_DAN:
			// 玩法猜中号码任意一个，单注奖金6元
			preLeastBonus = 6;
			preMaxBonus = 6;
			preBetSize = 2;
			leastCanWinSize = 2;
			break;
		case BetItem.PY_11XUAN5_REN3:
		case BetItem.PY_11XUAN5_REN3_DAN:
			preLeastBonus = 19;
			preMaxBonus = 19;
			preBetSize = 3;
			leastCanWinSize = 3;
			break;
		case BetItem.PY_11XUAN5_REN4:
		case BetItem.PY_11XUAN5_REN4_DAN:
			preLeastBonus = 78;
			preMaxBonus = 78;
			preBetSize = 4;
			leastCanWinSize = 4;
			break;
		case BetItem.PY_11XUAN5_REN5:
		case BetItem.PY_11XUAN5_REN5_DAN:
			preLeastBonus = 540;
			preMaxBonus = 540;
			preBetSize = 5;
			leastCanWinSize = 5;
			break;
		case BetItem.PY_11XUAN5_REN6:
		case BetItem.PY_11XUAN5_REN6_DAN:
			preLeastBonus = 90;
			preMaxBonus = 90;
			preBetSize = 6;
			leastCanWinSize = 5;
			break;
		case BetItem.PY_11XUAN5_REN7:
			preLeastBonus = 26;
			preMaxBonus = 26;
			preBetSize = 7;
			leastCanWinSize = 5;
			break;
		case BetItem.PY_11XUAN5_REN8:
			preLeastBonus = 9;
			preMaxBonus = 9;
			preBetSize = 8;
			leastCanWinSize = 5;
			break;
		case BetItem.PY_11XUAN5_QIAN2ZHI:
			preLeastBonus = 130;
			preMaxBonus = 130;
			preBetSize = 2;
			leastCanWinSize = 2;
			break;
		case BetItem.PY_11XUAN5_QIAN2ZU:
			preLeastBonus = 65;
			preMaxBonus = 65;
			preBetSize = 2;
			leastCanWinSize = 2;
			break;
		case BetItem.PY_11XUAN5_QIAN3ZHI:
			preLeastBonus = 1170;
			preMaxBonus = 1170;
			preBetSize = 3;
			leastCanWinSize = 3;
			break;
		case BetItem.PY_11XUAN5_QIAN3ZU:
			preLeastBonus = 195;
			preMaxBonus = 195;
			preBetSize = 3;
			leastCanWinSize = 3;
			break;
		default:
			break;
		}
		
		if((lotteryPlayWay == BetItem.PY_11XUAN5_REN2_DAN)
				||(lotteryPlayWay == BetItem.PY_11XUAN5_REN3_DAN)
				||(lotteryPlayWay == BetItem.PY_11XUAN5_REN4_DAN)
				||(lotteryPlayWay == BetItem.PY_11XUAN5_REN5_DAN)
				||(lotteryPlayWay == BetItem.PY_11XUAN5_REN6_DAN)
				||(lotteryPlayWay == BetItem.PY_11XUAN5_REN7_DAN)
				||(lotteryPlayWay == BetItem.PY_11XUAN5_QIAN2ZU_DAN)
				||(lotteryPlayWay == BetItem.PY_11XUAN5_QIAN3ZU_DAN)){
			
			return info = createSSCWXResult(count, preBetSize, leastCanWinSize,
					preLeastBonus, preMaxBonus);
			
		}
		else{
			return info = createSimpleStr(totalSize, preBetSize, leastCanWinSize,
					preLeastBonus, preMaxBonus);
		}

		
	}
	
	private static String getSSCInfo(int totalSize, int lotteryPlayWay, int count) {
		String info = "";
		int preLeastBonus = 0;// 最低奖项奖金
		int preMaxBonus = 0;// 最高奖项奖金
		int preBetSize = 0;// 每一注多少号码,最高奖的中奖号码长度
		int leastCanWinSize = 0;// 最少中多少号码有奖，最小奖号码的长度
		switch (lotteryPlayWay) {
		case BetItem.PY_JSSC_1XFU:
			preLeastBonus = 10;
			preMaxBonus = 10;
			preBetSize = 1;
			leastCanWinSize = 1;
			break;
		case BetItem.PY_JSSC_2XFU:
			preLeastBonus = 100;
			preMaxBonus = 100;
			preBetSize = 2;
			leastCanWinSize = 2;
			break;
		case BetItem.PY_JSSC_2XZU:
			preLeastBonus = 50;
			preMaxBonus = 50;
			preBetSize = 2;
			leastCanWinSize = 2;
			break;
		case BetItem.PY_JSSC_3XFU:
			preLeastBonus = 1000;
			preMaxBonus = 1000;
			preBetSize = 3;
			leastCanWinSize = 3;
			break;
		case BetItem.PY_JSSC_5XFU:
			preLeastBonus = 100000;
			preMaxBonus = 100000;
			preBetSize = 5;
			leastCanWinSize = 5;
			break;
		case BetItem.PY_JSSC_5XTONG:
			preLeastBonus = 20;
			preMaxBonus = 20440;
			preBetSize = 5;
			leastCanWinSize = 5;
			break;
		case BetItem.PY_JSSC_DXDS:
			preLeastBonus = 4;
			preMaxBonus = 4;
			preBetSize = 2;
			leastCanWinSize = 2;
			break;
		default:
			break;
		}
		
		if(lotteryPlayWay == BetItem.PY_JSSC_5XTONG
				||lotteryPlayWay == BetItem.PY_JSSC_DXDS){
			return info = createSSCWXResult(count, preBetSize, leastCanWinSize,
					preLeastBonus, preMaxBonus);
		}
		else{
			return info = createSimpleStr(totalSize, preBetSize, leastCanWinSize,
					preLeastBonus, preMaxBonus);
		}

	
	}

	private static String getKl8Info(int totalSize, int lotteryPlayWay) {
		String info = "";
		int preLeastBonus = 0;// 最低奖项奖金
		int preMaxBonus = 0;// 最高奖项奖金
		int preBetSize = 0;// 每一注多少号码,最高奖的中奖号码长度
		int leastCanWinSize = 0;// 最少中多少号码有奖，最小奖号码的长度
		switch (lotteryPlayWay) {
		case PY_KL8_REN1:
			// 玩法猜中号码任意一个，单注奖金4元
			preLeastBonus = 4;
			preMaxBonus = 4;
			preBetSize = 1;
			leastCanWinSize = 1;

			break;
		case PY_KL8_REN2:
			// 猜开奖号码任意二个，单注奖金16元
			preLeastBonus = 16;
			preMaxBonus = 16;
			preBetSize = 2;
			leastCanWinSize = 2;
			break;
		case PY_KL8_REN3:
			// 猜开奖号码任意三个 ，中3 奖金30元 ，中2 奖金 4元
			preBetSize = 3;
			preMaxBonus = 30;

			leastCanWinSize = 2;
			preLeastBonus = 4;
			break;
		case PY_KL8_REN4:
			// 猜开奖号码任意四个 ，中4奖金40元 ，中3 奖金10元，中2 奖金 2元
			preBetSize = 4;
			preMaxBonus = 40;

			leastCanWinSize = 2;
			preLeastBonus = 2;

			break;
		case PY_KL8_REN5:
			// 猜开奖号码任意五个 ，中5奖金250元， 中4奖金40元 ，中3 奖金4元
			preBetSize = 5;
			preMaxBonus = 250;

			leastCanWinSize = 3;
			preLeastBonus = 4;

			break;
		case PY_KL8_REN6:
			// 猜开奖号码任意六个 ，中6奖金600元，中5奖金500元，
			// 中4奖金8元 ，中3 奖金4元
			preBetSize = 6;
			preMaxBonus = 600;
			leastCanWinSize = 3;
			preLeastBonus = 4;
			break;
		case PY_KL8_REN7:
			// 猜开奖号码任意七个 ，中7奖金8000，中6奖金160元，
			// 中5奖金25元，中4奖金4元 ，中0奖金2元
			preBetSize = 7;
			preMaxBonus = 8000;

			leastCanWinSize = 0;
			preLeastBonus = 2;
			break;
		case PY_KL8_REN8:
			// 猜开奖号码任意八个 ，中8奖金20000，中7奖金700，中6奖金40元，
			// 中5奖金10元，中4奖金4元，中0奖金2元

			preBetSize = 8;
			preMaxBonus = 20000;

			leastCanWinSize = 0;
			preLeastBonus = 2;
			break;
		case PY_KL8_REN9:
			// 猜开奖号码任意九个 ，中9奖金50000，中8奖金500，
			// 中7奖金400，中6奖金40元，中5奖金6元，中0奖金2元
			preBetSize = 9;
			preMaxBonus = 50000;
			leastCanWinSize = 0;
			preLeastBonus = 2;
			break;
		case PY_KL8_REN10:
			// 猜开奖号码任意十个，中10，奖金500W，中0奖金10元
			preBetSize = 10;
			preMaxBonus = 5000000;

			leastCanWinSize = 0;
			preLeastBonus = 10;
			break;
		default:
			break;
		}

		return info = createStr(totalSize, preBetSize, leastCanWinSize,
				preLeastBonus, preMaxBonus);

	}

	/**
	 * 
	 * @param totalSize
	 *            总共投了多少号
	 * @param preBetSize
	 *            一注多少个号
	 * @param leastCanWinSize
	 *            最少中几个号能中奖
	 * @param preLeastBonus
	 *            最小奖的奖金
	 * @param preMaxBonus
	 *            最大奖的奖金
	 * @return
	 */
	private static String createSimpleStr(int totalSize, int preBetSize,
			int leastCanWinSize, int preLeastBonus, int preMaxBonus) {
//		int[] sizes = getLeastMaxSize(totalSize);
//		int leastWinSize = sizes[0]; // 最少中几个号码
//		int maxWinSize = sizes[1]; // 最多中了几个号码
		int count = 0;// 总共多少注
		int maxBetSize = 0;// 最多能选择多少个号码
		int leastBonusWinCount = 0;// 最低奖项的获奖注数
		int maxBonusWinCount = 0;// 最高奖项的获奖注数
		int leastBonus = 0;// 能获得的最少奖金
		int maxBonus = 0; // 能获得的最大奖金

		count = combination(totalSize, preBetSize);
//		if (preBetSize == leastCanWinSize) {
//			if (leastWinSize == 0) {
//				leastWinSize += leastCanWinSize;
//			}
//			leastBonusWinCount = combination(leastWinSize, leastCanWinSize);
//		} else {
//			leastBonusWinCount = combination(totalSize - leastCanWinSize,
//					preBetSize - leastCanWinSize);
//		}
		maxBonusWinCount = preMaxBonus;
//		leastBonusWinCount = leastBonusWinCount * preLeastBonus;
//		maxBonusWinCount = maxBonusWinCount * preMaxBonus;
		return getResult(count, maxBonusWinCount);

	}
	
	/**
	 * 
	 * @param totalSize
	 *            总共投了多少号
	 * @param preBetSize
	 *            一注多少个号
	 * @param leastCanWinSize
	 *            最少中几个号能中奖
	 * @param preLeastBonus
	 *            最小奖的奖金
	 * @param preMaxBonus
	 *            最大奖的奖金
	 * @return
	 */
	private static String createStr(int totalSize, int preBetSize,
			int leastCanWinSize, int preLeastBonus, int preMaxBonus) {
		int[] sizes = getLeastMaxSize(totalSize);
		int leastWinSize = sizes[0]; // 最少中几个号码
		int maxWinSize = sizes[1]; // 最多中了几个号码
		int count = 0;// 总共多少注
		int maxBetSize = 0;// 最多能选择多少个号码
		int leastBonusWinCount = 0;// 最低奖项的获奖注数
		int maxBonusWinCount = 0;// 最高奖项的获奖注数
		int leastBonus = 0;// 能获得的最少奖金
		int maxBonus = 0; // 能获得的最大奖金

		count = combination(totalSize, preBetSize);
		Logger.i(TAG, "preBetSize:" +preBetSize + " leastCanWinSize:" +leastCanWinSize);
		if (preBetSize == leastCanWinSize) {
			if (leastWinSize == 0) {
				leastWinSize += leastCanWinSize;
			}
			leastBonusWinCount = combination(leastWinSize, leastCanWinSize);
		} else {
			leastBonusWinCount = combination(totalSize - leastCanWinSize,
					preBetSize - leastCanWinSize);
		}
		
		maxBonusWinCount = combination(maxWinSize, preBetSize);
		Logger.i(TAG, "maxWinSize:" +maxWinSize + " preBetSize:" +preBetSize + " maxBonusWinCount:" +maxBonusWinCount);
		leastBonusWinCount = leastBonusWinCount * preLeastBonus;
		maxBonusWinCount = maxBonusWinCount * preMaxBonus;
		Logger.i(TAG, "preMaxBonus:" +preMaxBonus + " maxBonusWinCount:" +maxBonusWinCount );
		Logger.i(TAG, "maxBonusWinCount:" +maxBonusWinCount + " count:" +count + "leastBonusWinCount:" +leastBonusWinCount);
		Logger.i(TAG, "preBetSize:" +preBetSize + " totalSize:" +totalSize + "leastCanWinSize:" +leastCanWinSize);
		return getStr(count, leastBonusWinCount, maxBonusWinCount);

	}
	
	private static String createSSCWXResult(int totalSize, int preBetSize,
			int leastCanWinSize, int preLeastBonus, int preMaxBonus){
		int[] sizes = getLeastMaxSize(totalSize);
		int leastWinSize = 1; // 最少中几个号码
		int maxWinSize = 1; // 最多中了几个号码
		int count = 0;// 总共多少注
		int maxBetSize = 0;// 最多能选择多少个号码
		int leastBonusWinCount = 0;// 最低奖项的获奖注数
		int maxBonusWinCount = 0;// 最高奖项的获奖注数
		int leastBonus = 0;// 能获得的最少奖金
		int maxBonus = 0; // 能获得的最大奖金

		count = totalSize;
		Logger.i(TAG, "preBetSize:" +preBetSize + " leastCanWinSize:" +leastCanWinSize);
	
		leastBonusWinCount = 1;
		
		
		maxBonusWinCount = 1;
		Logger.i(TAG, "maxWinSize:" +maxWinSize + " preBetSize:" +preBetSize + " maxBonusWinCount:" +maxBonusWinCount);
		leastBonusWinCount = leastBonusWinCount * preLeastBonus;
		maxBonusWinCount = maxBonusWinCount * preMaxBonus;
		Logger.i(TAG, "preMaxBonus:" +preMaxBonus + " maxBonusWinCount:" +maxBonusWinCount );
		Logger.i(TAG, "maxBonusWinCount:" +maxBonusWinCount + " count:" +count + "leastBonusWinCount:" +leastBonusWinCount);
		Logger.i(TAG, "preBetSize:" +preBetSize + " totalSize:" +totalSize + "leastCanWinSize:" +leastCanWinSize);
		return getStr(count, leastBonusWinCount, maxBonusWinCount);
	}
	


	/**
	 * 判断投size个号，可能最少中几个号和最多中几个号
	 * 
	 * @param size
	 *            投了几个号码
	 * @return 最少可能中几个和最大可能中几个
	 */
	private static int[] getLeastMaxSize(int size) {
		int[] sizes = new int[2];

		// 单复式
		if (size <= 20) {
			// 最多中size个号
			// 最少中一个号
			sizes[0] = 0;
			sizes[1] = size;

		} else if (size <= 60) {
			// 最多中20个号
			// 最少中一个号
			sizes[0] = 0;
			sizes[1] = 20;

		} else {
			// 最多中20个号
			// 最少中size-60个号
			sizes[0] = size - 60;
			sizes[1] = 20;

		}
		return sizes;

	}

	/**
	 * 
	 * @param count
	 *            总共多少注
	 * @param leastBonus
	 *            最少奖金
	 * @param maxBonus
	 *            最多奖金
	 * @return
	 */
	private static String getStr(int count, int leastBonus, int maxBonus) {
		int leastProfit = leastBonus - 2 * count;
		int maxProfit = maxBonus - 2 * count;
		String str = null;
		str = maxProfit > 0 ? ",盈利" : "，亏损";
		if (maxProfit > 0) {
			if (maxProfit == leastProfit) {
				str = "奖金" + maxBonus + "元,盈利" + maxProfit + "元";
			} else {
				str = "奖金" + leastBonus + "至" + maxBonus + "元,盈利" + leastProfit
						+ "至" + maxProfit + "元";
			}

		} else {
			leastProfit = -leastProfit;
			maxProfit = -maxProfit;

			if (maxProfit == leastProfit) {
				str = "奖金" + maxBonus + "元,亏损" + maxProfit + "元";
			} else {
				str = "奖金" + leastBonus + "至" + maxBonus + "元,亏损" + maxProfit
						+ "至" + leastProfit + "元";
			}

		}

		return str;
	}
	
	
	/**
	 * 
	 * @param count
	 *            总共多少注
	 * @param
	 *
	 * @param maxBonus
	 *            最多奖金
	 * @return
	 */
	private static String getResult(int count, int maxBonus) {
		int maxProfit = maxBonus - 2 * count;
		String str = null;
		str = maxProfit > 0 ? ",盈利" : "，亏损";
		if (maxProfit > 0) {
		
			str = "奖金" + maxBonus + "元,盈利" + maxProfit + "元";
			

		} else {
	
			maxProfit = -maxProfit;
			str = "奖金" + maxBonus + "元,亏损" + maxProfit + "元";
			

		}

		return str;
	}


	/**
	 * 组合 从m中选出n个
	 * 
	 * @param m
	 * @param n
	 * @return
	 */
	public static int combination(int m, int n) {
		if (m < n) {
			return 0;
		} else {
			return factorial(m - n + 1, m) / factorial(1, n);
		}
	}

	/**
	 * 阶乘，n限于正整数
	 * 
	 * @param n
	 * @return
	 */
	public static int factorial(int n) {
		int total = 1;
		for (int i = 1; i <= n; i++) {
			total = total * i;
		}
		return total;
	}

	/**
	 * 计算start到stop阶乘 正整数
	 * 
	 * @param start
	 * @param stop
	 * @return
	 */
	public static int factorial(int start, int stop) {
		int total = 1;
		for (int i = start; i <= stop; i++) {
			total = total * i;
		}
		return total;
	}

}
