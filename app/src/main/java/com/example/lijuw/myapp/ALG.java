package com.example.lijuw.myapp;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import android.text.TextUtils;
import android.util.Log;

/**
 * @author shouli.luo
 * @time 2013-6-15 上午10:35:22
 * @introduce
 */
public class ALG {

	public static final String TAG = "ALG";
	public static ALG instance;

	/**
	 * 双色球大乐透后区的开始KEY
	 */
	public static final int BACK_KEY = 10;

	/**
	 * 时时彩
	 */
	public static final byte TYPE_SHISHICAI = 21;
	// 大小单双--复式需要拆成单式
	public static final byte TYPE_SHISHICAI_DXDS_DS = 20;
	// 一星彩
	public static final byte TYPE_SHISHICAI_YIXING_DS = 11;
	public static final byte TYPE_SHISHICAI_YIXING_FS = 31;
	// 二星彩
	public static final byte TYPE_SHISHICAI_ERXING_DS = 12;
	public static final byte TYPE_SHISHICAI_ERXING_FS = 32;
	public static final byte TYPE_SHISHICAI_ERXING_FX = 22;
	// 二星组选--复式需要拆成单式
	public static final byte TYPE_SHISHICAI_ERXINGZUXUAN_DS = 40;
	// 二星组选胆拖
	public static final byte TYPE_SHISHICAI_ERXINGZUXUAN_DT = 50;
	// 三星彩
	public static final byte TYPE_SHISHICAI_SANXING_DS = 13;
	public static final byte TYPE_SHISHICAI_SANXING_FS = 33;
	public static final byte TYPE_SHISHICAI_SANXING_FX = 23;
	// 四星彩
	public static final byte TYPE_SHISHICAI_SIXING_DS = 14;
	public static final byte TYPE_SHISHICAI_SIXING_FS = 34;
	public static final byte TYPE_SHISHICAI_SIXING_FX = 24;
	// 五星彩
	public static final byte TYPE_SHISHICAI_WUXING_DS = 15;
	public static final byte TYPE_SHISHICAI_WUXING_FS = 35;
	public static final byte TYPE_SHISHICAI_WUXING_FX = 25;
	public static final byte TYPE_SHISHICAI_WUXING_TX = 45;

	public static final byte TYPE_JC_SHENGFU = 22;

	public static final String NULL_NUMBER = "";
	public static final String SPACE_NUMBER = "  ";
	public static final String SPACE_LINE = "-";
	public static final String SPLIT_NUMBER = "|";
	public static final String SPLIT_SEMICOLON = ";";
	public static final String LEFT_BRACKET_NUMBER = "(";
	public static final String RIGHT_BRACKET_NUMBER = ")";
	public static final String ADD_NUMBER = "+";
	public static final String SPLIT_DOT = ",";
	public static final String SPLIT_COLON = ":";

	/**
	 * PK10 彩种
	 */
	public static final byte TYPE_PK10_PUTONGXUAN1 = 61;// PK10普通选1
	public static final byte TYPE_PK10_PUTONGXUAN2 = 62;// PK10普通选2
	public static final byte TYPE_PK10_PUTONGXUAN3 = 63;// PK10普通选3
	public static final byte TYPE_PK10_PUTONGXUAN4 = 64;// PK10普通选4
	public static final byte TYPE_PK10_PUTONGXUAN5 = 65;// PK10普通选5
	public static final byte TYPE_PK10_PUTONGXUAN6 = 66;// PK10普通选6
	public static final byte TYPE_PK10_PUTONGXUAN7 = 67;// PK10普通选7
	public static final byte TYPE_PK10_PUTONGXUAN8 = 68;// PK10普通选8
	public static final byte TYPE_PK10_PUTONGXUAN9 = 69;// PK10普通选9
	public static final byte TYPE_PK10_PUTONGXUAN10 = 70;// PK10普通选10
	public static final byte TYPE_PK10_JINGQUEXUAN2 = 72;// PK10精确选2
	public static final byte TYPE_PK10_JINGQUEXUAN3 = 73;// PK10精确选3
	public static final byte TYPE_PK10_JINGQUEXUAN4 = 74;// PK10精确选4

	/**
	 * PK10 玩法
	 */
	public static final byte TYPE_PK10_PUTONGXUAN_DANSHI = 1; // PK10普通选单式
	public static final byte TYPE_PK10_JINGQUEXUAN_DANSHI = 2; // PK10精确选单式

	public static final byte TYPE_PK10_FUSHI = 0; // PK10普通选和精确选复式

	/**
	 * 11选5 彩种
	 */
	public static final byte TYPE_11XUAN5_QIAN1 = 31;
	public static final byte TYPE_11XUAN5_QIAN2_ZHIXUAN = 39;
	public static final byte TYPE_11XUAN5_QIAN2_ZUXUAN = 41;
	public static final byte TYPE_11XUAN5_QIAN3_ZHIXUAN = 40;
	public static final byte TYPE_11XUAN5_QIAN3_ZUXUAN = 42;
	public static final byte TYPE_11XUAN5_RENXUANER = 32;
	public static final byte TYPE_11XUAN5_RENXUANSAN = 33;
	public static final byte TYPE_11XUAN5_RENXUANSI = 34;
	public static final byte TYPE_11XUAN5_RENXUANWU = 35;
	public static final byte TYPE_11XUAN5_RENXUANLIU = 36;
	public static final byte TYPE_11XUAN5_RENXUANQI = 37;
	public static final byte TYPE_11XUAN5_RENXUANBA = 38;

	/**
	 * 11选5 玩法类型
	 */
	public static final byte TYPE_11XUAN5_DANSHI = 0;
	public static final byte TYPE_11XUAN5_FUSHI = 1;
	public static final byte TYPE_11XUAN5_DANTUO = 2;
	public static final byte TYPE_11XUAN5_DINGWEIFUSHI = 3;

	/**
	 * KuaiLe8 彩种
	 */
	public static final byte TYPE_KUAILE8_XUAN1 = 51;
	public static final byte TYPE_KUAILE8_XUAN2 = 52;
	public static final byte TYPE_KUAILE8_XUAN3 = 53;
	public static final byte TYPE_KUAILE8_XUAN4 = 54;
	public static final byte TYPE_KUAILE8_XUAN5 = 55;
	public static final byte TYPE_KUAILE8_XUAN6 = 56;
	public static final byte TYPE_KUAILE8_XUAN7 = 57;
	public static final byte TYPE_KUAILE8_XUAN8 = 58;
	public static final byte TYPE_KUAILE8_XUAN9 = 59;
	public static final byte TYPE_KUAILE8_XUAN10 = 60;

	/**
	 * KuaiLe8 玩法
	 */
	public static final byte TYPE_KUAILE8_DANSHI = 1;// KuaiLe8单式
	public static final byte TYPE_KUAILE8_FUSHI = 2;// KuaiLe8复式

	/**
	 * 双色球
	 */
	public static final byte TYPE_SHUANGSEQIU = 1;
	// 单式
	public static final byte TYPE_SHUANGSEQIU_DANSHI = 0;
	// 复式
	public static final byte TYPE_SHUANGSEQIU_FUSHI = 43;
	// 胆拖复式
	public static final byte TYPE_SHUANGSEQIU_DANTUO = 3;

	/**
	 * 大乐透
	 */
	public static final byte TYPE_DALETOU = 4;
	// 单式
	public static final byte TYPE_DALETOU_DANSHI = 0;
	// 复式
	public static final byte TYPE_DALETOU_FUSHI = 1;
	// 胆拖复式
	public static final byte TYPE_DALETOU_DANTUO = 2;

	/**
	 * 数字彩
	 * 
	 * @return
	 */
	public static final byte TYPE_SANDFUCAI = 2;// 3D福彩

	// 直选单式
	public static final byte TYPE_SANDFUCAI_ZHIXUAN_DANSHI = 1;
	// 直选复式
	public static final byte TYPE_SANDFUCAI_ZHIXUAN_FUSHI = 12;
	// 直选胆拖
	public static final byte TYPE_SANDFUCAI_ZHIXUAN_DANTUO = 94;

	// 直选和值
	public static final byte TYPE_SANDFUCAI_ZHIXUAN_HEZHI = 13;
	// 组选和值
	public static final byte TYPE_SANDFUCAI_ZUXUAN_HEZHI = 14;
	// 组三单式
	public static final byte TYPE_SANDFUCAI_ZUSAN_DANSHI = 3;
	// 组三复式
	public static final byte TYPE_SANDFUCAI_ZUSAN_FUSHI = 98;
	// 组六单式
	public static final byte TYPE_SANDFUCAI_ZULIU_DANSHI = 5;
	// 组六复式
	public static final byte TYPE_SANDFUCAI_ZULIU_FUSHI = 99;

	public static final byte TYPE_PAILIEWU = 6;// 排列五
	// 单式
	public static final byte TYPE_PAILIEWU_DANSHI = 0;
	// 复式
	public static final byte TYPE_PAILIEWU_FUSHI = 1;
	// 胆拖复式
	public static final byte TYPE_PAILIEWU_DANTUO = 2;

	public static final byte TYPE_QIXINGCAI = 7;// 七星彩
	// 单式
	public static final byte TYPE_QIXINGCAI_DANSHI = 0;
	// 复式
	public static final byte TYPE_QIXINGCAI_FUSHI = 1;
	// 胆拖复式
	public static final byte TYPE_QIXINGCAI_DANTUO = 2;

	public static final byte TYPE_QILECAI = 3;// 七乐彩
	// 单式
	public static final byte TYPE_QILECAI_DANSHI = 0;
	// 复式
	public static final byte TYPE_QILECAI_FUSHI = 1;
	// 胆拖复式
	public static final byte TYPE_QILECAI_DANTUO = 2;
	// 复式或胆拖
	public static final byte TYPE_QIXINGCAI_FUSHI_DANTUO = 44;

	public static final byte TYPE_XUAN5OF22 = 8;// 22选5
	// 单式
	public static final byte TYPE_XUAN5OF22_DANSHI = 0;
	// 复式
	public static final byte TYPE_XUAN5OF22_FUSHI = 1;
	// //胆拖复式
	// public static final byte TYPE_XUAN5OF22_DANTUO = 2;

	public static final byte TYPE_PAILIESAN = 5;// 排列三
	// 直选单式
	public static final byte TYPE_PAILIESAN_ZHIXUAN_DANSHI = 0;
	// 直选复式
	public static final byte TYPE_PAILIESAN_ZHIXUAN_FUSHI = 1;
	// 直选组合
	public static final byte TYPE_PAILIESAN_ZHIXUAN_ZUHE = 7;
	// 直选胆拖
	public static final byte TYPE_PAILIESAN_ZHIXUAN_DANTUO = 8;
	// //直选和值
	// public static final byte TYPE_PAILIESAN_HEZHI_ZHI = 2;
	// //组选和值
	// public static final byte TYPE_PAILIESAN_HEZHI_ZU = 6;
	// 组三单式
	public static final byte TYPE_PAILIESAN_ZUSAN_DANSHI = 2;
	// 组三复式
	public static final byte TYPE_PAILIESAN_ZUSAN_FUSHI = 5;
	// 组三胆拖
	public static final byte TYPE_PAILIESAN_ZUSAN_DANTUO = 9;
	// 组六单式
	public static final byte TYPE_PAILIESAN_ZULIU_DANSHI = 3;
	// 组六复式
	public static final byte TYPE_PAILIESAN_ZULIU_FUSHI = 4;
	// 组六胆拖
	public static final byte TYPE_PAILIESAN_ZULIU_DANTUO = 10;

	// 足彩14场胜负彩
	public static final byte TYPE_SHENGFUCAI = 13;
	// 足彩胜负彩任9
	public static final byte TYPE_RENXUAN9 = 14;
	// 足彩六场半全场
	public static final byte TYPE_BANQUANCHANG = 15;
	// 足彩四场进球
	public static final byte TYPE_JINGQIUCAI = 16;

	// 胜负彩玩法-单式
	public static final byte TYPE_SHENGFUCAI_DANSHI = 0;
	// 胜负彩玩法-复式
	public static final byte TYPE_SHENGFUCAI_FUSHI = 1;

	/**
	 * 300 ：14场胜负彩 301 ：任选九 303 ：4场进球彩 302 ：6场半全场 400 ：足球单场 200 ：竞彩篮球 201 ：竞彩足球
	 * 001 ：双色球 002 ：福彩3D 009 ：北京3D 003 ：七乐彩 010 ：两步彩 113 ：超级大乐透 110 : 七星彩 108 :
	 * 排列3 109 : 排列5 111 : 22选5 107 : 11选5 119 : 新11选5 006 : 时时彩 016 : 快乐8 017 :
	 * PK拾
	 */
	public static final String LOTTERYNUM_SHENGFUCAI = "300";
	public static final String LOTTERYNUM_RENXUAN9 = "301";
	public static final String LOTTERYNUM_JINGQIUCAI = "303";
	public static final String LOTTERYNUM_BANQUANCHANG = "302";
	public static final String LOTTERYNUM_BEIJINGDANCHANG = "400";
	public static final String LOTTERYNUM_JINGCAILANQIU = "200";
	public static final String LOTTERYNUM_JINGCAIZUQIU = "201";
	public static final String LOTTERYNUM_SHUANGSEQIU = "001";
	public static final String LOTTERYNUM_FUCAI3D = "002";
	public static final String LOTTERYNUM_BEIJING3D = "009";
	public static final String LOTTERYNUM_QILECAI = "003";
	public static final String LOTTERYNUM_LIANGBUCAI = "010";
	public static final String LOTTERYNUM_DALETOU = "113";
	public static final String LOTTERYNUM_QIXINGCAI = "110";
	public static final String LOTTERYNUM_PAILIE3 = "108";
	public static final String LOTTERYNUM_PAILIE5 = "109";
	public static final String LOTTERYNUM_22XUAN5 = "111";
	public static final String LOTTERYNUM_11XUAN5 = "107";
	public static final String LOTTERYNUM_XIN11XUAN5 = "119";
	public static final String LOTTERYNUM_SHISHICAI = "006";
	public static final String LOTTERYNUM_KUAILE8 = "016";
	public static final String LOTTERYNUM_PK10 = "017";

	// /**
	// *@author shouli.luo
	// *@time 2013-6-15 上午10:35:05
	// *@方法作用：获得任9显示的数据
	// * @param tempNoDanMap
	// * @param tempDanMap
	// * @return
	// */
	// public static String getRen9ShowNumber(HashMap<Integer, Vector<String>>
	// tempNoDanMap,
	// HashMap<Integer, Vector<String>> tempDanMap,HashMap<Integer,
	// Vector<String>> map){
	// StringBuffer sb = new StringBuffer();
	//
	//
	// return null;
	//
	// }




	/**
	 * @author shouli.luo
	 * @return
	 * @return
	 * @time 2013-4-23 上午11:56:15
	 * @方法作用： 拿到 combList 计算注数
	 */
	private static int calculateCount(List<HashMap<Integer, Vector<String>>> combList) {

		int allCount = 0;
		for (int i = 0; i < combList.size(); i++) {
			HashMap<Integer, Vector<String>> subMap = combList.get(i);
			int count = 1;
			for (int j = 0; j < subMap.size(); j++) {
				Vector<String> vec = subMap.get(j);
				count = count * vec.size();
			}
			allCount = allCount + count;
		}
		return allCount;
	}

	/**
	 * 1、start 第一个true片段的起始位，end截止位 2、把第一个true片段都置false
	 * 3、数组从0下标起始到以第一个true片段元素数量减一为下标的位置都置true 4、把第一个true片段end截止位置true
	 * 
	 * @param bs
	 *            数组是否显示的标志位
	 * @param m
	 *            数组长度
	 * @return boolean 是否还有其他组合
	 */
	private static boolean moveNext(BitSet bs, int m) {
		int start = -1;
		while (start < m)
			if (bs.get(++start))
				break;
		if (start >= m)
			return false;

		int end = start;
		while (end < m)
			if (!bs.get(++end))
				break;
		if (end >= m)
			return false;
		for (int i = start; i < end; i++)
			bs.set(i, false);
		for (int i = 0; i < end - start - 1; i++)
			bs.set(i);
		bs.set(end);
		return true;
	}

	/**
	 * 得到生成的组合结果
	 * 
	 * @param
	 *
	 * @param bs
	 *            数组元素是否显示的标志位集合
	 */
	private static void getCombination(int m, BitSet bs, HashMap<Integer, Vector<String>> tempNoDanMap, List<HashMap<Integer, Vector<String>>> combList) {
		HashMap<Integer, Vector<String>> subList = new HashMap<Integer, Vector<String>>();
		int j = 0;
		for (int i = 0; i < m; i++) {
			if (bs.get(i)) {
				subList.put(j, tempNoDanMap.get(i));
				j++;
			}
		}
		combList.add(subList);
	}

	/**
	 * @author shouli.luo
	 * @time 2013-4-2 上午11:05:05
	 * @方法作用： 计算注数
	 * @param map
	 * @param lotteryType
	 *            彩种
	 * @param lotteryPlayWay
	 *            玩法
	 * @return
	 */
	public static int getBetCount(HashMap<Integer, Vector<String>> map, int lotteryType, int lotteryPlayWay) {
		int betCount = 0;

		if (map == null || map.size() == 0) {
			return betCount;
		}
		// PalLog.d(TAG, "type:"+lotteryType);
		// PalLog.d(TAG, "playway:"+lotteryPlayWay);
		switch (lotteryType) {
		case BetItem.TYPE_XSSC:
		case BetItem.TYPE_JSSC:
			// PalLog.d(TAG, "时时彩");
			betCount = calculateShiShiCai(map, lotteryType, lotteryPlayWay);
			break;
		/**
		 * 双色球
		 */
		case BetItem.TYPE_SSQ:
			switch (lotteryPlayWay) {
			case BetItem.PY_SSQ_PUTONG:
				betCount = calculateShuangSeDale(map, 6, 1);
				break;
			case BetItem.PY_SSQ_DANTUO:
				betCount = calculateShuangSeDanTuo(map, 6, 1);
				break;

			default:
				break;
			}
			break;
		/**
		 * 大乐透
		 */
		case BetItem.TYPE_DLT:
			switch (lotteryPlayWay) {
			case BetItem.PY_DLT_PUTONG:
				betCount = calculateShuangSeDale(map, 5, 2);
				break;
			case BetItem.PY_DLT_DANTUO:
				betCount = calculateDaleDanTuo(map, 5, 2);
				break;
			default:
				break;
			}
			break;
		// case TYPE_JC_SHENGFU:
		// int count_number = 5;
		// int count_second = 4;
		// for (int i = 1; i < count_second; i++) {
		// for (int j = count_second; j > i; j--) {
		// // betCount += (combination(count_second,j)*timesNumber();
		// }
		// }
		//
		// betCount = betCount * combination(count_number, count_second);
		// break;
		case BetItem.TYPE_3D:// 计算3D福彩
		case BetItem.TYPE_PL3:// 计算排列三
			betCount = calculate_3D_PAILIESAN_ZhuShu(map, lotteryType, lotteryPlayWay);
			break;
		case BetItem.TYPE_PL5:
			betCount = calculate_ZhuShu(map, 1);
			break;
		case BetItem.TYPE_QXC:
			betCount = calculate_ZhuShu(map, 1);
			break;
		case BetItem.TYPE_QLC:
			if (lotteryPlayWay == BetItem.PY_QLC_DANTUO)// 胆拖
				betCount = calculate_DanTuo(map, 7, false);
			else
				// 单复式
				betCount = calculate_ZhuShu(map, 7);
			break;
		/**
		 * 11选5 注数计算
		 */
		case BetItem.TYPE_11XUAN5:
		case BetItem.TYPE_JX11XUAN5:
		case BetItem.TYPE_GD11XUAN5:
			// Log.d(TAG, "map size:" + map.size());
			// betCount = get11Xuan5Bets(lotteryType,
			// getBetNumber(map, lotteryType, lotteryPlayWay),
			// lotteryPlayWay);
			betCount = calculate11xuan5(map, lotteryPlayWay);
			break;
		case BetItem.TYPE_K3:
			betCount = getK3Bets(map, lotteryPlayWay);
			break;

		case BetItem.TYPE_REN9:
			betCount = 1;
			// int count = 0;
			for (int i = 0; i < map.size(); i++) {
				// Vector<String> vec = map.get(i);
				// if(vec.size() == 0){
				// count ++;
				// }else{
				betCount = betCount * combination(map.get(i).size(), 1);
				// }
			}
			// if(count > 5){betCount = 0;}
			break;
		case BetItem.TYPE_SFC:
			betCount = 1;
			for (int i = 0; i < map.size(); i++) {
				Vector<String> vec = map.get(i);
				if (vec.size() == 0) {
					betCount = 0;
					break;
				} else {
					betCount = betCount * combination(map.get(i).size(), 1);
				}
			}
			break;
		default:
			break;
		}
		return betCount;
	}

	/**
	 * 快3计算注数
	 * 
	 * @param map
	 * @param lotteryPlayWay
	 * @return
	 */

	private static int getK3Bets(HashMap<Integer, Vector<String>> map, int lotteryPlayWay) {
		int bets = 0;
		Vector<String> vec1 = null;
		Vector<String> vec2 = null;
		Vector<String> vec3 = null;

		switch (lotteryPlayWay) {
		case BetItem.PY_K3_HZ:
			vec1 = map.get(0);
			bets = combination(vec1.size(), 1);
			// 和值
			break;
		case BetItem.PY_K3_3TH:
			vec1 = map.get(0);
			vec2 = map.get(1);
			bets = combination(vec1.size(), 1) + combination(vec2.size(), 1);
			// 三同号
			break;
		case BetItem.PY_K3_2TH_DX:
			vec1 = map.get(0);
			vec2 = map.get(1);
			bets = vec1.size() * vec2.size();
			break;
		case BetItem.PY_K3_2TH_FX:
			vec1 = map.get(0);
			bets = combination(vec1.size(), 1);
			break;
		case BetItem.PY_K3_3BTH:
			// 三不同号
			vec1 = map.get(0);
			vec2 = map.get(1);
			bets = combination(vec1.size(), 3) + combination(vec2.size(), 1);
			break;
		case BetItem.PY_K3_2BTH:
			// 二不同号
			vec1 = map.get(0);
			bets = combination(vec1.size(), 2);
			break;
		case BetItem.PY_K3_3BTH_DAN:
			// 三不同号胆拖
			vec1 = map.get(0);
			vec2 = map.get(1);
			bets = combination(vec2.size(), 3 - vec1.size());
			break;
		case BetItem.PY_K3_2BTH_DAN:
			// 二不同号胆拖
			vec1 = map.get(0);
			vec2 = map.get(1);
			if (vec1.size() == 1) {
				bets = combination(vec2.size(), 1);
			} else {
				bets = 0;
			}

			break;
		}
		return bets;
	}



	/**
	 * =======
	 * 
	 * @author shouli.luo
	 * @time 2013-4-2 上午10:54:41
	 * @方法作用： 计算11选5的注数
	 * @param map
	 * @param lotteryPlayWay
	 * @return
	 */
	private static int calculate11xuan5(HashMap<Integer, Vector<String>> map, int lotteryPlayWay) {

		int betCount = 0;
		switch (lotteryPlayWay) {
		case BetItem.PY_11XUAN5_REN2:
		case BetItem.PY_11XUAN5_REN3:
		case BetItem.PY_11XUAN5_REN4:
		case BetItem.PY_11XUAN5_REN5:
		case BetItem.PY_11XUAN5_REN6:
		case BetItem.PY_11XUAN5_REN7:
		case BetItem.PY_11XUAN5_REN8:
			if (map.get(0).size() > 1) {
				betCount = combination(map.get(0).size(), lotteryPlayWay - BetItem.TYPE_11XUAN5);
			}
			break;
		case BetItem.PY_11XUAN5_REN2_DAN:
		case BetItem.PY_11XUAN5_REN3_DAN:
		case BetItem.PY_11XUAN5_REN4_DAN:
		case BetItem.PY_11XUAN5_REN5_DAN:
		case BetItem.PY_11XUAN5_REN6_DAN:
		case BetItem.PY_11XUAN5_REN7_DAN:
			int danNum = map.get(0).size();
			int tuoNum = map.get(1).size();
			int maxDan = lotteryPlayWay - BetItem.TYPE_11XUAN5 - 11;
			if (danNum >= 1 && danNum <= maxDan && (danNum + tuoNum >= maxDan)) {
				betCount = combination(tuoNum, maxDan - danNum);
			}
			break;
		case BetItem.PY_11XUAN5_QIAN2ZU_DAN:
			int danNum1 = map.get(0).size();
			int tuoNum1 = map.get(1).size();
			if (danNum1 > 0 && tuoNum1 > 0) {
				betCount = tuoNum1;
			}
			break;
		case BetItem.PY_11XUAN5_QIAN3ZU_DAN:
			int danNum2 = map.get(0).size();
			int tuoNum2 = map.get(1).size();
			if (danNum2 > 0) {
				betCount = combination(tuoNum2, 3 - danNum2);
			}
			break;
		case BetItem.PY_11XUAN5_REN1:
			betCount = map.get(0).size();
			break;
		case BetItem.PY_11XUAN5_QIAN2ZHI:
			if (map.size() == 2) {
				betCount = map.get(0).size() * map.get(1).size();
			}
			break;
		case BetItem.PY_11XUAN5_QIAN2ZU:
			betCount = combination(map.get(0).size(), 2);
			break;
		case BetItem.PY_11XUAN5_QIAN3ZHI:
			if (map.size() == 3) {
				betCount = map.get(0).size() * map.get(1).size() * map.get(2).size();
			}
			break;
		case BetItem.PY_11XUAN5_QIAN3ZU:
			betCount = combination(map.get(0).size(), 3);
			break;
		default:
			break;
		}

		return betCount;

	}

	/**
	 * @author shouli.luo
	 * @time 2013-4-2 上午10:55:23
	 * @方法作用： 计算时时彩的注数
	 * @param map
	 * @param lotteryType
	 * @param lotteryPlayWay
	 * @return
	 */
	private static int calculateShiShiCai(HashMap<Integer, Vector<String>> map, int lotteryType, int lotteryPlayWay) {

		int betCount = 0;

		switch (lotteryType) {
		case BetItem.TYPE_XSSC:
			if (lotteryPlayWay == BetItem.PY_XSSC_2XZU || lotteryPlayWay == BetItem.PY_XSSC_3XZU6) {
				int m = 2;
				if (lotteryPlayWay == BetItem.PY_XSSC_2XZU) {
					m = 2;
				} else {
					m = 3;
				}
				if (map != null && map.size() > 0) {
					Vector<String> vec = map.get(0);
					if (vec.size() < m) {
						betCount = 0;
					} else {
						betCount = combination(vec.size(), m);
					}
				}
			}
			if (lotteryPlayWay == BetItem.PY_XSSC_3XZU3FU) {
				if (map == null || map.get(0).size() < 2) {
					betCount = 0;
				} else {
					betCount = factorial(map.get(0).size() - 1, map.get(0).size());
				}
			} else {
				betCount = 1;
				if (map != null && map.size() > 0) {
					for (int i = 0; i < map.size(); i++) {
						Vector<String> vec = map.get(i);
						betCount = betCount * vec.size();
					}
				} else {
					betCount = 0;
				}
			}
			break;
		case BetItem.TYPE_JSSC:
			if (lotteryPlayWay == BetItem.PY_JSSC_2XZU) {
				// || lotteryPlayWay == BetItem.PY_JSSC_3XZU6){
				int m = 2;
				if (lotteryPlayWay == BetItem.PY_JSSC_2XZU) {
					m = 2;
				} else {
					m = 3;
				}
				if (map != null && map.size() > 0) {
					Vector<String> vec = map.get(0);
					if (vec.size() < m) {
						betCount = 0;
					} else {
						betCount = combination(vec.size(), m);
					}
				}
			}
			// else if(lotteryPlayWay == BetItem.PY_JSSC_3XZU3FU){
			// if(map == null || map.get(0).size() < 2){
			// betCount = 0;
			// }else{
			// betCount = factorial(map.get(0).size()-1,map.get(0).size());
			// }
			// }
			else {
				betCount = 1;
				if (map != null && map.size() > 0) {
					for (int i = 0; i < map.size(); i++) {
						Vector<String> vec = map.get(i);
						betCount = betCount * vec.size();
					}
				} else {
					betCount = 0;
				}
			}
		default:
			break;
		}

		return betCount;

	}

	/**
	 * 双色球大乐透 单式复式注数
	 * 
	 * @param map
	 * @param fore_num
	 * @param back_num
	 * @return
	 */
	private static int calculateShuangSeDale(HashMap<Integer, Vector<String>> map, int fore_num, int back_num) {
		int foreSelectNum = 0;
		int backSelectNum = 0;
		Vector<String> v0 = map.get(0);
		Vector<String> v = map.get(1);
		if (v0 != null) {
			foreSelectNum = v0.size();
		}
		if (v != null) {
			backSelectNum = v.size();
		}
		int betCount = combination(foreSelectNum, fore_num) * combination(backSelectNum, back_num);
		// PalLog.d(TAG, "ShuangSeDale betCount:" + betCount);
		return betCount;
	}

	/**
	 * 双色球胆拖注数
	 * 
	 * @param map
	 * @param fore_num
	 * @param back_num
	 * @return
	 */
	private static int calculateShuangSeDanTuo(HashMap<Integer, Vector<String>> map, int fore_num, int back_num) {
		int betCount = 0;
		int backSelectNum = 0;
		int danNum = 0;
		int tuoNum = 0;
		if (map.get(0) != null) {
			danNum = map.get(0).size();
		}
		if (map.get(1) != null) {
			tuoNum = map.get(1).size();
		}
		if (danNum + tuoNum < fore_num || danNum + tuoNum <= 6) {
			betCount = 0;
		} else {
			backSelectNum = map.get(2).size();
			betCount = combination(tuoNum, fore_num - danNum) * combination(backSelectNum, back_num);
		}

		Log.i(TAG, "ShuangSeDanTuo betCount:" + betCount);
		return betCount;
	}

	/**
	 * 大乐透胆拖注数
	 * 
	 * @param map
	 * @param fore_num
	 * @param back_num
	 * @return
	 */
	private static int calculateDaleDanTuo(HashMap<Integer, Vector<String>> map, int fore_num, int back_num) {
		int betCount = 0;
		int danNum = map.get(0) == null ? 0 : map.get(0).size();
		int tuoNum = map.get(2) == null ? 0 : map.get(2).size();
		int backDanNum = map.get(1) == null ? 0 : map.get(1).size();
		int backTuoNum = map.get(3) == null ? 0 : map.get(3).size();
		if (danNum + tuoNum < fore_num || backDanNum + backTuoNum < back_num) {
			betCount = 0;
		} else {
			betCount = combination(tuoNum, fore_num - danNum) * combination(backTuoNum, back_num - backDanNum);
		}
		Log.i(TAG, "DaleDanTuo betCount:" + betCount);
		return betCount;
	}

	/**
	 * 双色球大乐透 单式复式号码
	 * 
	 * @param map
	 * @param type
	 * @return
	 */
	private static String calculateShuangSeDaleBetNum(HashMap<Integer, Vector<String>> map, int type, String split, String qianHouSplit, boolean isSaveStr) {
		StringBuffer betNumStr = new StringBuffer();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < map.size(); i++) {
			Vector<String> ve = null;
			ve = map.get(i);
			if (i == 0) {
				if (ve != null && ve.size() > 0) {
					for (int j = 0; j < ve.size(); j++) {
						String num = ve.elementAt(j);
						betNumStr.append(num);
						if (j != ve.size() - 1) {
							betNumStr.append(split);
						}
					}
				} else {
					betNumStr.append(ALG.SPACE_NUMBER);
				}
				betNumStr.append(qianHouSplit);

			} else if (i == 1) {
				if (ve != null && ve.size() > 0) {
					for (int j = 0; j < ve.size(); j++) {
						String num = ve.elementAt(j);
						sb.append(num);
						if (j != ve.size() - 1) {
							sb.append(split);
						}
					}
				} else {
					sb.append(ALG.SPACE_NUMBER);
				}
				if (isSaveStr) {
					betNumStr.append(sb.toString());
				} else {
					betNumStr.append(("<font color=\"#0000ff\">" + sb.toString() + "</font>"));
				}
			}
		}
		Log.d("ALG", "betNumStr:" + betNumStr.toString());
		return betNumStr.toString();
	}

	/**
	 * @author shouli.luo
	 * @time 2013-4-3 上午10:56:14
	 * @方法作用： 显示双色球大乐透的胆拖号码
	 * @param map
	 * @param type
	 * @param split
	 * @param dantuoSplit
	 * @param qianHouSplit
	 * @return
	 */
	private static String calculateSSDLDanTuoBetNum(HashMap<Integer, Vector<String>> map, int type, String split, String dantuoSplit, String qianHouSplit, boolean isSaveStr) {

		StringBuffer betNumStr = new StringBuffer();
		StringBuffer sb = new StringBuffer();

		if (!isSaveStr) {
			betNumStr.append(ALG.LEFT_BRACKET_NUMBER);
		}

		if (type == BetItem.PY_SSQ_DANTUO) {

			for (int i = 0; i < map.size(); i++) {
				if (i == 0 || i == 1) {
					Vector<String> ve = map.get(i);
					if (ve.size() > 0) {
						for (int j = 0; j < ve.size(); j++) {
							String num = ve.elementAt(j);
							betNumStr.append(num);
							if (j != ve.size() - 1)
								betNumStr.append(split);
						}
					} else {
						betNumStr.append(ALG.SPACE_NUMBER);
					}
					if (i == 0) {
						betNumStr.append(dantuoSplit);
					}
				} else {
					if (i == 2) {
						betNumStr.append(qianHouSplit);
						Vector<String> ve = map.get(i);
						if (ve.size() > 0) {
							for (int x = 0; x < ve.size(); x++) {
								String num = ve.elementAt(x);
								sb.append(num);
								if (x != ve.size() - 1)
									sb.append(split);
							}
						} else {
							sb.append(ALG.NULL_NUMBER);
						}
						if (isSaveStr) {
							betNumStr.append(sb.toString());
						} else {
							betNumStr.append(("<font color=\"#0000ff\">" + sb.toString() + "</font>"));
						}
					}
				}

			}

		} else if (type == BetItem.PY_DLT_DANTUO) {

			for (int i = 0; i < map.size(); i++) {

				if (i == 0 || i == 2) {
					Vector<String> ve = map.get(i);
					if (ve.size() > 0) {
						for (int j = 0; j < ve.size(); j++) {
							String num = ve.elementAt(j);
							betNumStr.append(num);
							if (j != ve.size() - 1)
								betNumStr.append(split);
						}
					} else {
						betNumStr.append(ALG.SPACE_NUMBER);
					}
					if (i != 2) {
						betNumStr.append(dantuoSplit);
					}
				} else {
					if (isSaveStr) {
						if (i == 3) {
							betNumStr.append(qianHouSplit);
						}
					} else {
						betNumStr.append(qianHouSplit);
					}
					if (i == 1 || i == 3) {
						if (i == 1) {
							if (!isSaveStr) {
								sb.append(ALG.LEFT_BRACKET_NUMBER);
							}
						}
						Vector<String> ve = map.get(i);
						// Logger.i(TAG, "i:" +i + " ve size:" + ve.size());
						if (ve.size() > 0) {
							for (int x = 0; x < ve.size(); x++) {
								String num = ve.elementAt(x);
								sb.append(num);
								if (x != ve.size() - 1)
									sb.append(split);
							}
						} else {
							sb.append(ALG.NULL_NUMBER);
						}
						if (i == 1) {
							sb.append(dantuoSplit);
						}
						if (i == 3) {
							if (isSaveStr) {
								betNumStr.append(sb.toString());
							} else {
								betNumStr.append(("<font color=\"#0000ff\">" + sb.toString() + "</font>"));
							}
						}
					}
				}

			}
		}

		Log.d("TouZhu", "betNumStr:" + betNumStr.toString());

		return betNumStr.toString();
	}

	// /**
	// *@author shouli.luo
	// *@time 2013-4-3 下午06:03:37
	// *@方法作用： 拼接保存的号码，以后便于拆分
	// * @param map
	// * @return
	// */
	// public static String getSaveNumber(HashMap<Integer, Vector<String>> map){
	// StringBuffer saveNumberBf = new StringBuffer();
	// for(int i=0;i<map.size();i++){
	// Vector<String> vec = map.get(i);
	// for(int j=0;j<vec.size();j++){
	// saveNumberBf.append(vec.get(j));
	// if(j != (vec.size()-1) && vec.size() > 1){
	// saveNumberBf.append(ALG.SPLIT_DOT);
	// }
	// }
	// if(i != (map.size()-1))
	// saveNumberBf.append(ALG.ADD_NUMBER);
	// }
	//
	// Log.d(TAG, " SaveNumber:" + saveNumberBf.toString());
	// return saveNumberBf.toString();
	// }

	private static String getSaveStr(HashMap<Integer, Vector<String>> map, String danSplit, String everySplit) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < map.size(); i++) {
			Vector<String> vec = map.get(i);
			for (int j = 0; j < vec.size(); j++) {
				sb.append(vec.get(j));
				if (j != vec.size() - 1) {
					sb.append(danSplit);
				}
			}
			if (i != map.size() - 1) {
				sb.append(everySplit);
			}
		}
		return sb.toString();
	}

	private static String getSaveStr(ArrayList<String> list,String split){
		StringBuffer sb = new StringBuffer();
		int len = list.size();
		for (int i = 0; i < len; i++) {
			sb.append(list.get(i));
			if(i != len - 1)
				sb.append(split);
		}
		return sb.toString();
	}
	
	public static String getSaveNumberBySpace(HashMap<Integer, Vector<String>> map, int count, int lotteryType, int lotteryPlayWay, String playTypeName) {
		return "";
	}

	/**
	 * @author shouli.luo
	 * @time 2013-4-3 下午06:03:37
	 * @方法作用： 拼接保存的号码，以后便于拆分
	 * @param map
	 * @return
	 */
	public static String getSaveNumber(HashMap<Integer, Vector<String>> map, int count, int lotteryType, int lotteryPlayWay, String playTypeName) {
		String betNumber = "";
		StringBuffer betNumStr = null;
		switch (lotteryType) {
		// case BetItem.TYPE_XSSC:
		case BetItem.TYPE_JSSC:

			if (lotteryPlayWay == BetItem.PY_JSSC_DXDS) {
				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < map.size(); i++) {
					Vector<String> vec = map.get(i);
					for (int j = 0; j < vec.size(); j++) {
						String temp = null;
						if (vec.get(j).equals("大")) {
							temp = "0";
						} else if (vec.get(j).equals("小")) {
							temp = "1";
						} else if (vec.get(j).equals("单")) {
							temp = "2";
						} else if (vec.get(j).equals("双")) {
							temp = "3";
						}
						sb.append(temp);
						if (j != vec.size() - 1) {
							sb.append(ALG.SPLIT_DOT);
						}
					}
					if (i != map.size() - 1) {
						sb.append(ALG.SPACE_LINE);
					}
				}
				betNumber = "[" + playTypeName + "]" + count + ALG.SPLIT_COLON + sb.toString();
			} else {
				betNumber = "[" + playTypeName + "]" + count + ALG.SPLIT_COLON + getSaveStr(map, ALG.SPLIT_DOT, ALG.SPACE_LINE);
			}
			break;
		case BetItem.TYPE_11XUAN5:
		case BetItem.TYPE_JX11XUAN5:
		case BetItem.TYPE_GD11XUAN5:

			if (lotteryPlayWay >= BetItem.PY_11XUAN5_REN2_DAN) {
				betNumber = "[" + playTypeName + "]" + count + ALG.SPLIT_COLON + getSaveStr(map, ALG.SPLIT_DOT, ALG.SPLIT_SEMICOLON);
			} else {
				betNumber = "[" + playTypeName + "]" + count + ALG.SPLIT_COLON + getSaveStr(map, ALG.SPLIT_DOT, ALG.SPACE_LINE);
			}
			// if(lotteryPlayWay >= BetItem.PY_11XUAN5_REN2_DAN){
			// betNumber =
			// mapToString_DanTuo(map,ALG.SPACE_NUMBER,ALG.RIGHT_BRACKET_NUMBER,true);
			// }else{
			// betNumStr = new StringBuffer();
			// for (int i = 0; i < map.size(); i++) {
			// Vector<String> ve = map.get(i);
			// if (ve != null && ve.size() > 0) {
			// for (int j = 0; j < ve.size(); j++) {
			// String num = ve.elementAt(j);
			// betNumStr.append(num);
			// if (j != ve.size() - 1)
			// betNumStr.append(ALG.SPACE_NUMBER);
			// }
			// } else {
			// betNumStr.append(ALG.SPACE_NUMBER);
			// }
			// if (i != map.size() - 1) {
			// betNumStr.append(ALG.SPLIT_NUMBER);
			// }
			// }
			// betNumber = betNumStr.toString();
			// }
			Log.d(TAG, "11xuan5 betNumStr:" + betNumber);
			break;
		// 双色球 大乐透
		case BetItem.TYPE_SSQ:
			switch (lotteryPlayWay) {
			case BetItem.PY_SSQ_DANTUO:
				betNumber = calculateSSDLDanTuoBetNum(map, lotteryPlayWay, ALG.SPLIT_DOT, ALG.SPLIT_SEMICOLON, ALG.SPLIT_NUMBER, true);
				break;
			default:
				betNumber = calculateShuangSeDaleBetNum(map, lotteryPlayWay, ALG.SPLIT_DOT, ALG.SPLIT_NUMBER, true);
				break;
			}
			betNumber = count + ALG.SPLIT_COLON + betNumber;
			break;
		case BetItem.TYPE_DLT:
			switch (lotteryPlayWay) {
			case BetItem.PY_DLT_DANTUO:
				betNumber = calculateSSDLDanTuoBetNum(map, lotteryPlayWay, ALG.SPLIT_DOT, ALG.SPLIT_SEMICOLON, ALG.SPLIT_NUMBER, true);
				break;
			default:
				betNumber = calculateShuangSeDaleBetNum(map, lotteryPlayWay, ALG.SPLIT_DOT, ALG.SPLIT_NUMBER, true);
				break;
			}
			betNumber = count + ALG.SPLIT_COLON + betNumber;
			break;

		// case TYPE_JC_SHENGFU:
		// break;
		case BetItem.TYPE_3D:// 计算3D福彩
			if (lotteryPlayWay == BetItem.PY_3D_ZHIXUAN) {
				betNumber = getSaveStr(map, ALG.SPLIT_DOT, ALG.SPACE_LINE);
			} else {
				betNumber = getSaveStr(map, ALG.SPLIT_DOT, ALG.SPLIT_DOT);
			}
			betNumber = "[" + playTypeName + "]" + count + ALG.SPLIT_COLON + betNumber;
			break;
		case BetItem.TYPE_PL3:// 计算排列三
			if (lotteryPlayWay == BetItem.PY_PL3_ZHIXUAN) {
				betNumber = getSaveStr(map, ALG.SPLIT_DOT, ALG.SPACE_LINE);
			} else {
				betNumber = getSaveStr(map, ALG.SPLIT_DOT, ALG.SPLIT_DOT);
			}
			betNumber = "[" + playTypeName + "]" + count + ALG.SPLIT_COLON + betNumber;
			break;
		case BetItem.TYPE_PL5:// 计算排列五
			betNumber = "[" + playTypeName + "]" + count + ALG.SPLIT_COLON + getSaveStr(map, ALG.SPLIT_DOT, ALG.SPACE_LINE);
			break;
		case BetItem.TYPE_QXC:
			betNumber = count + ALG.SPLIT_COLON + getSaveStr(map, ALG.SPLIT_DOT, ALG.SPACE_LINE);
			break;
		case BetItem.TYPE_QLC:
			if (lotteryPlayWay == BetItem.PY_QLC_PUTONG) {
				betNumber = getSaveStr(map, ALG.SPLIT_DOT, ALG.SPLIT_DOT);
			} else {
				betNumber = getSaveStr(map, ALG.SPLIT_DOT, ALG.SPLIT_SEMICOLON);
			}
			betNumber = count + ALG.SPLIT_COLON + betNumber;
			break;
		case BetItem.TYPE_KL8:
			betNumStr = new StringBuffer();
			for (int i = 0; i < map.size(); i++) {
				Vector<String> ve = map.get(i);
				if (ve.size() > 0) {
					for (int j = 0; j < ve.size(); j++) {
						String num = ve.elementAt(j);
						betNumStr.append(num);
						if (j != ve.size() - 1)
							betNumStr.append(ALG.SPACE_NUMBER);
					}
				} else {
					betNumStr.append(ALG.SPACE_NUMBER);
				}
			}
			betNumber = betNumStr.toString();
			Log.d(TAG, "KuaiLe8 betNumStr:" + betNumStr.toString());
			break;
		case BetItem.TYPE_K3:
			betNumber = getK3SaveNumber(map, count, lotteryPlayWay, playTypeName);
			break;
		case BetItem.TYPE_SFC:
		case BetItem.TYPE_REN9:
			betNumStr = new StringBuffer();
			for (int i = 0; i < map.size(); i++) {
				Vector<String> ve = map.get(i);
				if (ve != null && ve.size() > 0) {
					for (int j = 0; j < ve.size(); j++) {
						String num = ve.elementAt(j);
						betNumStr.append(num);
						if (j != ve.size() - 1)
							betNumStr.append(ALG.SPLIT_NUMBER);
					}
				} else {
					betNumStr.append(ALG.SPACE_LINE);
				}
				if (i != map.size() - 1) {
					betNumStr.append(ALG.ADD_NUMBER);
				}
			}
			betNumber = betNumStr.toString();
			break;
		default:
			break;
		}
		// betNumStr.append(String.valueOf(count), 0,
		// String.valueOf(count).length());
		// betNumStr.append(ALG.SPLIT_COLON, String.valueOf(count).length(), 1);
		// PalLog.d(TAG, count+ALG.SPLIT_COLON+betNumber);
		// if(lotteryType == BetItem.TYPE_SFC || lotteryType ==
		// BetItem.TYPE_REN9){
		return betNumber;
		// }else{
		// return count+ALG.SPLIT_COLON+betNumber;
		// }
	}

	/**
	 * @author shouli.luo
	 * @time 2013-4-3 下午06:02:41
	 * @方法作用： 拼接展示的号码
	 * @param map
	 * @param lotteryType
	 * @param lotteryPlayWay
	 * @return
	 */
	public static String getShowNumber(HashMap<Integer, Vector<String>> map, int lotteryType, int lotteryPlayWay) {
		String betNumber = "";
		StringBuffer betNumStr = null;
		switch (lotteryType) {
		case BetItem.TYPE_XSSC:
		case BetItem.TYPE_JSSC:
			betNumStr = new StringBuffer();
			for (int i = 0; i < map.size(); i++) {
				Vector<String> ve = map.get(i);
				if (ve.size() > 0) {
					for (int j = 0; j < ve.size(); j++) {
						String num = ve.elementAt(j);
						betNumStr.append(num);
					}
				} else {
					betNumStr.append(ALG.SPACE_NUMBER);
				}
				if (i != map.size() - 1)
					betNumStr.append(ALG.SPACE_NUMBER);
			}
			betNumber = betNumStr.toString();
			break;
		// 双色球 大乐透
		case BetItem.TYPE_SSQ:
			switch (lotteryPlayWay) {
			case BetItem.PY_SSQ_DANTUO:
				betNumber = calculateSSDLDanTuoBetNum(map, lotteryPlayWay, ALG.SPACE_NUMBER, ALG.RIGHT_BRACKET_NUMBER, ALG.SPACE_NUMBER, false);
				break;
			default:
				betNumber = calculateShuangSeDaleBetNum(map, lotteryPlayWay, ALG.SPACE_NUMBER, ALG.SPACE_NUMBER, false);
				break;
			}
			break;
		case BetItem.TYPE_DLT:
			switch (lotteryPlayWay) {
			case BetItem.PY_DLT_DANTUO:
				betNumber = calculateSSDLDanTuoBetNum(map, lotteryPlayWay, ALG.SPACE_NUMBER, ALG.RIGHT_BRACKET_NUMBER, ALG.SPACE_NUMBER, false);
				break;
			default:
				betNumber = calculateShuangSeDaleBetNum(map, lotteryPlayWay, ALG.SPACE_NUMBER, ALG.SPACE_NUMBER, false);
				break;
			}
			break;

		// case TYPE_JC_SHENGFU:
		// break;
		case BetItem.TYPE_3D:// 计算3D福彩
		case BetItem.TYPE_PL3:// 计算排列三
		case BetItem.TYPE_PL5:// 计算排列五
		case BetItem.TYPE_QXC:
		case BetItem.TYPE_QLC:
			betNumber = calculate_HaoMa2(map, lotteryType, lotteryPlayWay);
			break;
		case BetItem.TYPE_KL8:
			betNumStr = new StringBuffer();
			for (int i = 0; i < map.size(); i++) {
				Vector<String> ve = map.get(i);
				if (ve.size() > 0) {
					for (int j = 0; j < ve.size(); j++) {
						String num = ve.elementAt(j);
						betNumStr.append(num);
						if (j != ve.size() - 1)
							betNumStr.append(ALG.SPACE_NUMBER);
					}
				} else {
					betNumStr.append(ALG.SPACE_NUMBER);
				}
			}
			betNumber = betNumStr.toString();
			Log.d(TAG, "KuaiLe8 betNumStr:" + betNumStr.toString());
			break;
		case BetItem.TYPE_11XUAN5:
		case BetItem.TYPE_JX11XUAN5:
		case BetItem.TYPE_GD11XUAN5:

			if (lotteryPlayWay >= BetItem.PY_11XUAN5_REN2_DAN) {
				betNumber = mapToString_DanTuo(map, ALG.SPACE_NUMBER, ALG.RIGHT_BRACKET_NUMBER, true);
			} else {
				betNumStr = new StringBuffer();
				for (int i = 0; i < map.size(); i++) {
					Vector<String> ve = map.get(i);
					if (ve != null && ve.size() > 0) {
						for (int j = 0; j < ve.size(); j++) {
							String num = ve.elementAt(j);
							betNumStr.append(num);
							if (j != ve.size() - 1)
								betNumStr.append(ALG.SPACE_NUMBER);
						}
					} else {
						betNumStr.append(ALG.SPACE_NUMBER);
					}
					if (i != map.size() - 1) {
						betNumStr.append(ALG.SPLIT_NUMBER);
					}
				}
				betNumber = betNumStr.toString();
			}
			Log.d(TAG, "11xuan5 betNumStr:" + betNumber);
			break;
		case BetItem.TYPE_K3:
			
			if(lotteryPlayWay == BetItem.PY_K3_3TH){
				if(map.size() == 2){
					Vector<String> vector = map.get(1);
					if(vector.size() != 0)
					vector.set(0, "|三同号通选");
				}
			}
			
			if(lotteryPlayWay == BetItem.PY_K3_3BTH){
				if(map.size() == 2){
					Vector<String> vector = map.get(1);
					if(vector.size() != 0)
						vector.set(0, "|三连号通选");
				}
			}
			
			betNumStr = new StringBuffer();
			for (int i = 0; i < map.size(); i++) {
				Vector<String> ve = map.get(i);
				if (ve.size() > 0) {
					for (int j = 0; j < ve.size(); j++) {
						String num = ve.elementAt(j);
						betNumStr.append(num);
						if (j != ve.size() - 1)
							betNumStr.append(ALG.SPACE_NUMBER);
					}
				}
				betNumStr.append(ALG.SPACE_NUMBER);

			}
			betNumber = betNumStr.toString();
			break;
		case BetItem.TYPE_SFC:
		case BetItem.TYPE_REN9:
			betNumStr = new StringBuffer();
			for (int i = 0; i < map.size(); i++) {
				Vector<String> ve = map.get(i);
				if (ve != null && ve.size() > 0) {
					for (int j = 0; j < ve.size(); j++) {
						String num = ve.elementAt(j);
						betNumStr.append(num);
						if (j != ve.size() - 1)
							betNumStr.append(ALG.NULL_NUMBER);
					}
				} else {
					betNumStr.append(ALG.SPACE_LINE);
				}
				if (i != map.size() - 1) {
					betNumStr.append(ALG.SPACE_NUMBER);
				}
			}
			betNumber = betNumStr.toString();
			break;
		default:
			break;
		}
		return betNumber;
	}

	// /**
	// * 计算号码
	// *
	// * @param map
	// * @param lotteryType
	// * @param lotteryPlayWay
	// * @return
	// */
	// public static String getBetNumber(HashMap<Integer, Vector<String>> map,
	// int lotteryType, int lotteryPlayWay) {
	// String betNumber = "";
	// StringBuffer betNumStr;
	// switch (lotteryType) {
	// case BetItem.TYPE_XSSC:
	// case BetItem.TYPE_JSSC:
	// betNumStr = new StringBuffer();
	// for (int i = 0; i < map.size(); i++) {
	// Vector<String> ve = map.get(i);
	// if (ve.size() > 0) {
	// for (int j = 0; j < ve.size(); j++) {
	// String num = ve.elementAt(j);
	// betNumStr.append(num);
	// }
	// } else {
	// betNumStr.append(ALG.SPACE_NUMBER);
	// }
	// if (i != map.size() - 1)
	// betNumStr.append(",");
	// }
	// betNumber = betNumStr.toString();
	// break;
	// // 双色球 大乐透
	// case BetItem.TYPE_SSQ:
	// switch (lotteryPlayWay) {
	// case BetItem.PY_SSQ_DANTUO:
	// betNumber = calculateSSDLDanTuoBetNum(map, lotteryPlayWay,",","@","+");
	// break;
	// default:
	// betNumber = calculateShuangSeDaleBetNum(map, lotteryPlayWay,",","+");
	// break;
	// }
	// break;
	// case BetItem.TYPE_DLT:
	// switch (lotteryPlayWay) {
	// case BetItem.PY_DLT_DANTUO:
	// betNumber = calculateSSDLDanTuoBetNum(map,
	// lotteryPlayWay,"_","_,_","_:_");
	// break;
	// default:
	// betNumber = calculateShuangSeDaleBetNum(map, lotteryPlayWay,"_","_:_");
	// break;
	// }
	// break;
	//
	// // case TYPE_JC_SHENGFU:
	// // break;
	// case BetItem.TYPE_3D:// 计算3D福彩
	// case BetItem.TYPE_PL3:// 计算排列三
	// case BetItem.TYPE_PL5:// 计算排列五
	// case BetItem.TYPE_QXC:
	// case BetItem.TYPE_QLC:
	// // case TYPE_XUAN5OF22:
	// betNumber = calculate_HaoMa2(map, lotteryType, lotteryPlayWay);
	// break;
	// case BetItem.TYPE_KL8:
	// betNumStr = new StringBuffer();
	// for (int i = 0; i < map.size(); i++) {
	// Vector<String> ve = map.get(i);
	// if (ve.size() > 0) {
	// for (int j = 0; j < ve.size(); j++) {
	// String num = ve.elementAt(j);
	// betNumStr.append(num);
	// if (j != ve.size() - 1)
	// betNumStr.append("|");
	// }
	// } else {
	// betNumStr.append(ALG.SPACE_NUMBER);
	// }
	// }
	// betNumber = betNumStr.toString();
	// Log.d(TAG, "KuaiLe8 betNumStr:" + betNumStr.toString());
	// break;
	// case BetItem.TYPE_11XUAN5:
	//
	// betNumStr = new StringBuffer();
	// for (int i = 0; i < map.size(); i++) {
	// Vector<String> ve = map.get(i);
	// if (ve != null && ve.size() > 0) {
	// for (int j = 0; j < ve.size(); j++) {
	// String num = ve.elementAt(j);
	// betNumStr.append(num);
	// if (j != ve.size() - 1)
	// betNumStr.append(",");
	// }
	// } else {
	// betNumStr.append(ALG.SPACE_NUMBER);
	// }
	// if (i != map.size() - 1) {
	// betNumStr.append("|");
	// }
	// }
	// betNumber = betNumStr.toString();
	// Log.d(TAG, "11xuan5 betNumStr:" + betNumStr.toString());
	// break;
	//
	// default:
	// break;
	// }
	// return betNumber;
	// }

	// 计算3D福彩,排列三 注数
	private static int calculate_3D_PAILIESAN_ZhuShu(HashMap<Integer, Vector<String>> map, int lotteryType, int lotteryPlayWay) {

		int betCount = 1;
		Vector<String> ve;

		if (lotteryType == BetItem.TYPE_3D) {

			switch (lotteryPlayWay) {

			case BetItem.PY_3D_ZHIXUAN:// 单复式
				for (int i = 0; i < map.size(); i++) {
					ve = map.get(i);
					System.out.println("ve.size==" + ve.size());
					betCount = betCount * combination(ve.size(), 1);
				}
				break;
			// case BetItem.PY_3D_ZUSANDANSHI:// 组三单式
			// if(map.get(0).size() == 0 || map.get(1).size() == 0){
			// betCount = 0;
			// // betCount = combination(map.get(0).size(), 1)
			// // * combination(map.get(1).size(), 1);
			// }else{
			// betCount = 1;
			// }
			// break;
			case BetItem.PY_3D_ZUSANFUSHI:// 组三复式
				ve = map.get(0);
				betCount = 2 * combination(ve.size(), 2);
				break;
			case BetItem.PY_3D_ZULIU:// 组六单式
				ve = map.get(0);
				System.out.println("ve.size==" + ve.size());
				betCount = combination(ve.size(), 3);
				break;
			default:
				break;
			}
		} else {

			switch (lotteryPlayWay) {

			case BetItem.PY_PL3_ZHIXUAN:// 单复式
				for (int i = 0; i < map.size(); i++) {
					ve = map.get(i);
					System.out.println("ve.size==" + ve.size());
					betCount = betCount * combination(ve.size(), 1);
				}
				break;
			// case BetItem.PY_PL3_ZUSANDANSHI:// 组三单式
			// if(map.get(0).size() == 0 || map.get(1).size() == 0){
			// betCount = 0;
			// }else{
			// betCount = 1;
			// }
			// break;
			case BetItem.PY_PL3_ZUSANFUSHI:// 组三复式
				ve = map.get(0);
				betCount = 2 * combination(ve.size(), 2);
				break;
			case BetItem.PY_PL3_ZULIU:// 组六单式
				ve = map.get(0);
				betCount = combination(ve.size(), 3);
				break;
			default:
				break;
			}
		}
		return betCount;
	}

	// 计算 通用彩种 号码
	private static String calculate_HaoMa(HashMap<Integer, Vector<String>> map, byte lotteryType, byte lotteryPlayWay) {

		String str = null;
		switch (lotteryType) {
		/**
		 * 3D福彩
		 */
		case BetItem.TYPE_3D:
			switch (lotteryPlayWay) {
			// case BetItem.TYPE_3D_ZHIXUAN_DANSHI:
			// str = mapToString_DuoHang(map, null);
			// break;
			case BetItem.PY_3D_ZHIXUAN:
				str = mapToString_DuoHang(map, ",");
				break;
			// case BetItem.PY_3D_ZUSANDANSHI:
			// str = mapToString_TeShu(map, lotteryPlayWay, "");
			// break;
			case BetItem.PY_3D_ZULIU:
			case BetItem.PY_3D_ZUSANFUSHI:
				str = mapToString_DanHang(map, "");
				break;
			default:
				break;
			}
			break;
		/**
		 * 排列3
		 */
		case BetItem.TYPE_PL3:
			switch (lotteryPlayWay) {
			case BetItem.PY_PL3_ZHIXUAN:
				str = mapToString_DuoHang(map, "*");
				break;
			// case BetItem.PY_PL3_ZUSANDANSHI:
			// str = mapToString_TeShu(map, lotteryPlayWay, "*");
			// break;
			case BetItem.PY_PL3_ZULIU:
			case BetItem.PY_PL3_ZUSANFUSHI:
				str = mapToString_DanHang(map, "*");
				break;
			default:
				break;
			}
			break;
		case BetItem.TYPE_PL5:
		case BetItem.TYPE_QXC:
			str = mapToString_DuoHang(map, "*");
			break;
		case BetItem.TYPE_QLC:
			switch (lotteryPlayWay) {
			case BetItem.PY_QLC_PUTONG:
				str = mapToString_DanHang(map, "_");
				break;
			case BetItem.PY_QLC_DANTUO:
				str = mapToString_DanTuo(map, "_", ",", true);
				break;
			default:
				break;
			}
			break;
		// case TYPE_XUAN5OF22:
		// str = mapToString_DanHang(map, "*");
		// break;
		default:
			break;
		}
		return str;
	}

	// 计算 通用彩种 号码 把显示号码弄成和投注号码一样
	private static String calculate_HaoMa2(HashMap<Integer, Vector<String>> map, int lotteryType, int lotteryPlayWay) {

		String str = null;
		switch (lotteryType) {
		/**
		 * 3D福彩
		 */
		case BetItem.TYPE_3D:
			switch (lotteryPlayWay) {
			// case BetItem.TYPE_3D_ZHIXUAN_DANSHI:
			// str = mapToString_DuoHang(map, null);
			// break;
			case BetItem.PY_3D_ZHIXUAN:
				str = mapToString_DuoHang(map, ALG.SPACE_NUMBER);
				break;
			// case BetItem.PY_3D_ZUSANDANSHI:
			// str = mapToString_TeShu(map, lotteryPlayWay, ALG.NULL_NUMBER);
			// break;
			case BetItem.PY_3D_ZULIU:
			case BetItem.PY_3D_ZUSANFUSHI:
				str = mapToString_DanHang(map, ALG.NULL_NUMBER);
				break;
			default:
				break;
			}
			break;
		/**
		 * 排列3
		 */
		case BetItem.TYPE_PL3:
			switch (lotteryPlayWay) {
			case BetItem.PY_PL3_ZHIXUAN:
				str = mapToString_DuoHang(map, ALG.SPACE_NUMBER);
				break;
			// case BetItem.PY_PL3_ZUSANDANSHI:
			// str = mapToString_TeShu(map, lotteryPlayWay, ALG.NULL_NUMBER);
			// break;
			case BetItem.PY_PL3_ZULIU:
			case BetItem.PY_PL3_ZUSANFUSHI:
				str = mapToString_DanHang(map, ALG.NULL_NUMBER);
				break;
			default:
				break;
			}
			break;
		case BetItem.TYPE_PL5:
		case BetItem.TYPE_QXC:
			str = mapToString_DuoHang(map, ALG.SPACE_NUMBER);
			break;
		case BetItem.TYPE_QLC:
			switch (lotteryPlayWay) {
			case BetItem.PY_QLC_PUTONG:
				str = mapToString_DanHang(map, ALG.SPACE_NUMBER);
				break;
			case BetItem.PY_QLC_DANTUO:
				str = mapToString_DanTuo(map, ALG.SPACE_NUMBER, ALG.RIGHT_BRACKET_NUMBER, true);
				break;
			default:
				break;
			}
			break;
		// case TYPE_XUAN5OF22:
		// str = mapToString_DanHang(map, "*");
		// break;
		default:
			break;
		}
		return str;
	}

	private static String getK3SaveNumber(HashMap<Integer, Vector<String>> map, int count, int lotteryPlayWay, String playTypeName) {
		String betNumber = null;
		switch (lotteryPlayWay) {
		case BetItem.PY_K3_3TH:
			String third3TH = "";
			String third3THTong = "";
			
			if(map.get(0).size() != 0){
				third3TH = getSaveStr(vector2List(map.get(0)), ALG.SPLIT_DOT);
			}
			
			if(map.get(1).size() != 0){
				third3THTong = "三同号通选";
			}
			
			betNumber = third3TH + (TextUtils.isEmpty(third3TH) ?"":ALG.SPLIT_DOT) + third3THTong;
			break;
		case BetItem.PY_K3_2BTH_DAN:
		case BetItem.PY_K3_3BTH_DAN:
			betNumber = getSaveStr(map, ALG.SPLIT_DOT, ALG.SPLIT_SEMICOLON);
			break;
		case BetItem.PY_K3_2TH_DX:
			if (count == 1) {
				betNumber = getSaveStr(map, ALG.SPLIT_DOT, ALG.SPACE_LINE);
			} else if (count > 1) {
				Vector<String> vector1 = map.get(0);
				Vector<String> vector2 = map.get(1);
				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < vector1.size(); i++) {
					for (int j = 0; j < vector2.size(); j++) {
						sb.append(vector1.get(i));
						sb.append(ALG.SPACE_LINE);
						sb.append(vector2.get(j));
						sb.append(ALG.SPLIT_DOT);
					}
				}
				String betStr = sb.toString();
				betNumber = betStr.substring(0, betStr.lastIndexOf(ALG.SPLIT_DOT));
			}
			break;
		case BetItem.PY_K3_3BTH:
			String third3BTTong = "";
			String third3BT = "";
			if(map.get(1).size() != 0){
				third3BTTong = "三连号通选";
			}
			
			if(map.get(0).size() != 0){
				third3BT = getSaveStr(ComTools.combineToItemString(vector2List(map.get(0)), 3), ALG.SPLIT_DOT);
			}
			
			betNumber = third3BT + (TextUtils.isEmpty(third3BT) ?"":ALG.SPLIT_DOT) + third3BTTong;
			break;
		default:
			betNumber = getSaveStr(map, ALG.SPLIT_DOT, ALG.SPLIT_DOT);
			break;
		}
		betNumber = "[" + playTypeName + "]" + count + ALG.SPLIT_COLON + betNumber;
		return betNumber;
	}

	private static String mapToString_DanTuo(HashMap<Integer, Vector<String>> map, String split, String decollator, Boolean isTeShu) {

		StringBuffer betNumStr = new StringBuffer();
		betNumStr.append(ALG.LEFT_BRACKET_NUMBER);
		for (int i = 0; i < map.size(); i++) {
			Vector<String> ve = map.get(i);
			if (ve.size() > 0) {
				System.out.println("ve.size==" + ve.size());
				for (int j = 0; j < ve.size(); j++) {
					String num = ve.elementAt(j);
					betNumStr.append(num);
					if (j != ve.size() - 1)
						betNumStr.append(split);
				}
			} else {
				betNumStr.append(ALG.SPACE_NUMBER);
			}
			if (i != map.size() - 1) {
				if (isTeShu) {
					// betNumStr.append(split);
					betNumStr.append(decollator);
					// betNumStr.append(split);
				} else
					betNumStr.append(decollator);
			}
		}
		Log.d("TouZhu", "betNumStr:" + betNumStr.toString());
		return betNumStr.toString();
	}

	private static String mapToString_DuoHang(HashMap<Integer, Vector<String>> map, String decollator) {

		StringBuffer betNumStr = new StringBuffer();
		for (int i = 0; i < map.size(); i++) {
			Vector<String> ve = map.get(i);
			if (ve.size() > 0) {
				System.out.println("ve.size==" + ve.size());
				for (int j = 0; j < ve.size(); j++) {
					String num = ve.elementAt(j);
					betNumStr.append(num);
				}
			} else {
				betNumStr.append(ALG.SPACE_NUMBER);
			}
			if (i != map.size() - 1 && decollator != null) {
				betNumStr.append(decollator);
			}
		}
		Log.d("TouZhu", "betNumStr:" + betNumStr.toString());
		return betNumStr.toString();
	}

	private static String mapToString_DanHang(HashMap<Integer, Vector<String>> map, String decollator) {

		StringBuffer betNumStr = new StringBuffer();
		Vector<String> ve = map.get(0);
		int size = ve.size();
		if (ve.size() > 0) {
			System.out.println("ve.size==" + ve.size());
			for (int j = 0; j < size; j++) {
				String num = ve.elementAt(j);
				betNumStr.append(num);
				if (j != size - 1)
					betNumStr.append(decollator);
			}
		}
		Log.d("TouZhu", "betNumStr:" + betNumStr.toString());
		return betNumStr.toString();
	}

	// 计算 通用彩种 注数
	// num表示 从所选号码中取出num个号码
	private static int calculate_ZhuShu(HashMap<Integer, Vector<String>> map, int num) {

		int betCount = 1;
		Vector<String> ve;
		for (int i = 0; i < map.size(); i++) {
			ve = map.get(i);
			System.out.println("ve.size==" + ve.size());
			betCount = betCount * combination(ve.size(), num);
		}
		System.out.println("betCount==" + betCount);
		return betCount;
	}

	// 计算 通用彩种 胆拖
	// nums:需要选择多少号码
	private static int calculate_DanTuo(HashMap<Integer, Vector<String>> map, int nums, Boolean isSort) {

		int betCount = 1;
		int dan = map.get(0).size();
		int tuo = map.get(1).size();
		if (dan + tuo >= nums) {
			int needNums = nums - dan;
			if (!isSort)
				betCount = combination(tuo, needNums);
			else
				betCount = combination(tuo, needNums) * factorial(nums);
		} else {
			System.out.println("您选的号码太少，不能成为一注");
			betCount = 0;
		}
		System.out.println("betCount==" + betCount);

		return betCount;
	}

	/**
	 * 排列
	 * 
	 * @param m
	 * @param n
	 * @return
	 */
	public static int permutation(int m, int n) {
		if (m < n)
			return 0;
		else
			return factorial(m) / factorial(m - n);
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


	private static boolean isLegal(String[] nums) {
		Vector<String> vector = array2Vector(nums);
		if (!vector.contains(SPACE_NUMBER)) {
			return true;
		}
		return false;
	}

	/**
	 * 数组拷贝到Vector
	 * 
	 * @param arr
	 * @return
	 */
	public static Vector<String> array2Vector(String[] arr) {
		Vector<String> resultTemp = new Vector<String>();
		for (int i = 0; i < arr.length; i++) {
			resultTemp.addElement(arr[i]);
		}
		return resultTemp;
	}

	/**
	 * Vector拷贝到数组
	 * 
	 * @param vec
	 * @return
	 */
	public static String[] vector2Array(Vector<String> vec) {
		int size = vec.size();
		String[] arr = new String[size];
		for (int i = 0; i < size; i++) {
			arr[i] = vec.elementAt(i);
		}
		return arr;
	}
	
	/**
	 * Vector拷贝到数组
	 * 
	 * @param vec
	 * @return
	 */
	public static ArrayList<String> vector2List(Vector<String> vec) {
		int size = vec.size();
		ArrayList<String> arr = new ArrayList<String>(size);
		for (int i = 0; i < size; i++) {
			arr.add(vec.elementAt(i));
		}
		return arr;
	}

	/**
	 * 删除集合vec中数组args包含的号码
	 * 
	 * @param vec
	 * @param args
	 * @return
	 */
	public static Vector deleteRepeatElements(Vector vec, String[] args) {
		for (int i = 0; i < args.length; i++) {
			int j = vec.indexOf(args[i]);
			if (j >= 0) {
				vec.removeElementAt(j);
			}
		}
		return vec;
	}

}
