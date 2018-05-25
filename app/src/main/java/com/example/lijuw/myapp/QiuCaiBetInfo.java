package com.example.lijuw.myapp;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gl
 * @time 2014年5月7日17:58:49
 * @introduce 球彩的投注信息拼接
 */
public class QiuCaiBetInfo {

	private double TotalPrice;
	
	/**
	 * 获得投注信息
	 * 
	 * @param lotteryType
	 *            彩票玩法类型
	 * @param periodNumber
	 *            期号
	 * @param betNums
	 *            投注信息
	 * @param multiple
	 *            倍数
	 * @param zhuiHaoCount
	 *            追号期数
	 * @param prizeStop
	 *            是否中奖停止
	 * @return
	 */
	public String getPostStrByLotteryType(int lotteryType, String periodNumber, List<LotteryInfo> betNums, int multiple, int zhuiHaoCount, boolean prizeStop,boolean zhuiJia) {
		StringBuilder sb = new StringBuilder();
		// 200#2014050#1# 091419282930-05$单式~1~1 #0#
		// 200#2014050#2# 091419282930-05$单式~1~1|021113242733-16$单式~1~1 #0#
		// 200#2014050#58#
		// 091419282930-05$单式~1~1|021113242733-16$单式~1~1|1214151617252728-0107$复式~1~56
		// #0#
		// 200#2014050#58#
		// 091419282930-05$单式~1~1|021113242733-16$单式~1~1|1214151617252728-0107$复式~1~56
		// #56# 2014050期$1:58$2014051:1:58
		// 彩票ID#期号#总注数#红球区号码-篮球区号码$单式~倍数~注数 #追号类型#
		
		betNums = convertDanshi(betNums, lotteryType);
		
		int totalCount = getTotalCount(betNums);
		sb.append(getFirstStr(periodNumber, lotteryType, totalCount, multiple));
		sb.append(getSecondStr(betNums, multiple,zhuiJia));
		sb.append(getThirdStr(Integer.parseInt(periodNumber), totalCount, zhuiHaoCount, prizeStop,multiple));
		return sb.toString();
	}

	/**
	 * 获得每期投注总数
	 * 
	 * @param betNums
	 *            投注信息
	 * @param
	 *
	 * @return
	 */
	private int getTotalCount(List<LotteryInfo> betNums) {
		int len = betNums.size();
		int num = 0;
		for (int i = 0; i < len; i++) {
			LotteryInfo lotteryInfo = betNums.get(i);
			num += lotteryInfo.getBetCount();
		}
		return num;
	}

	// 投注第一部分信息
	// 200#2014050#1#
	// 彩票ID#期号#总注数#
	private String getFirstStr(String periodNumber, int lotteryType, int totalCount, int multiple) {
		StringBuilder sb = new StringBuilder();
		sb.append(BetItem.getLotteryIdByLotteryType(lotteryType));
		sb.append("#");
		sb.append(periodNumber);
		sb.append("#");
		this.TotalPrice = totalCount * multiple * 2;
		sb.append(totalCount * multiple);
		sb.append("#");
		return sb.toString();
	}

	/**
	 * <br/>
	 * 投注第二部分信息,多个按"|"分割 <br/>
	 * 091419282930-05$单式~1~1 <br/>
	 * 091419282930-05$单式~1~1|021113242733-16$单式~1~1|1214151617252728-0107$复式~1~
	 * 56 <br/>
	 * 红球区号码-篮球区号码$单式~倍数~注数  
	 * 
	 * @param betNums
	 *            投注信息
	 * @param multiple
	 *            倍数
	 * @param
	 *
	 * @return
	 */
	private String getSecondStr(List<LotteryInfo> betNums, int multiple,boolean zhuiJia) {
		StringBuilder sb = new StringBuilder();
		int len = betNums.size();
		for (int i = 0; i < len; i++) {

			LotteryInfo lotteryInfo = betNums.get(i);
			PostBetContent splitByLotteryInfo = new PostBetContent(lotteryInfo,zhuiJia);
			int count = lotteryInfo.getBetCount(); // 单个选号注数
			String playWay = splitByLotteryInfo.getPlayWay();
			String[] infos = splitByLotteryInfo.getInfos();
			if(infos == null){
				continue;
			}
			int splitCount = infos.length;
			for (int j = 0; j < splitCount; j++) {
				sb.append(infos[j]);
				if (j != splitCount - 1) {
					sb.append("-");
				}
			}

			sb.append("$");
			sb.append(playWay);
			sb.append("~");
			sb.append(multiple);
			sb.append("~");
			sb.append(multiple * count);

			if (i != len - 1) {
				sb.append("|");
			}

		}
		return sb.toString();
	}

	/**
	 * 
	 * <br/>
	 * 投注第三部分信息 <br/>
	 * #0# 不追号,后面无信息 <br/>
	 * #48# 2014050期 $1:58$ 2014051:1:58 追号中奖不停止 X期$倍数:倍数*注数$期号:倍数:倍数*注数 <br/>
	 * #56# 2014050期 $1:58$ 2014051:1:58 追号中奖停止
	 * 
	 * @param periodNumber
	 *            期号
	 * @param totalCount
	 *            当前期投注总数
	 * @param zhuiHaoCount
	 *            追号期数
	 * @param prizeStop
	 *            中奖是否停止
	 * @return
	 */
	private String getThirdStr(int periodNumber, int totalCount, int zhuiHaoCount, boolean prizeStop,int multiple) {
		StringBuilder sb = new StringBuilder();
		int zhuiHaoType = 1;
		if (zhuiHaoCount == 1) {
			zhuiHaoType = 0;
		} else if (zhuiHaoCount > 1 && !prizeStop) {
			zhuiHaoType = 48;
		} else if (zhuiHaoCount > 1 && prizeStop) {
			zhuiHaoType = 56;
		}

		sb.append("#");
		sb.append(zhuiHaoType);
		sb.append("#");

		if (zhuiHaoType == 0) {
			return sb.toString();
		}

		// 服务器接口追号不包括当前期,所以-1
		if (zhuiHaoCount > 1) {
			zhuiHaoCount -= 1;
		}

		sb.append(periodNumber);
		sb.append("期");

		sb.append("$");
		sb.append(zhuiHaoCount);
		sb.append(":");
		sb.append(totalCount * zhuiHaoCount * multiple);
		sb.append("$");

		for (int i = 0; i < zhuiHaoCount; i++) {
			// 2014051:1:58|2014052:1:58
			sb.append(periodNumber + i + 1);
			sb.append(":");
			sb.append(multiple);
			sb.append(":");
			sb.append(multiple * totalCount);

			if (i != zhuiHaoCount - 1) {
				sb.append("|");
			}

		}

		return sb.toString();
	}
	
	
	private List<LotteryInfo> convertDanshi(List<LotteryInfo> info,int lotteryType){
		List<LotteryInfo> danShiInfos = null;;
		switch (lotteryType) {
		case BetItem.TYPE_K3:
			danShiInfos = new ArrayList<LotteryInfo>();
			for (LotteryInfo lotteryInfo : info) {
				int playWayType = lotteryInfo.getPlayWayType();
				if(playWayType == BetItem.PY_K3_3TH || playWayType == BetItem.PY_K3_3BTH || playWayType == BetItem.PY_K3_2TH_DX){
					String saveNo = lotteryInfo.getSaveNo();
					String[] split = saveNo.substring(saveNo.indexOf(":") + 1).split(",");
					//danShiInfos.remove(lotteryInfo);
					for (String string : split) {
						LotteryInfo danShiInfo = new LotteryInfo(lotteryInfo);
						danShiInfo.setSaveNo(string);
						danShiInfos.add(danShiInfo);
					}
				}else{
					danShiInfos.add(lotteryInfo);
				}
			}
			break;
		default:
			//danShiInfos.addAll(info);
			return info;
		}
		return danShiInfos;
	}

	public double getTotalPrice() {
		return TotalPrice;
	}
	
	
}
