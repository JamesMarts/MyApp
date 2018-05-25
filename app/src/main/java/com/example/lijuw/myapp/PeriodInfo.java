package com.example.lijuw.myapp;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


public class PeriodInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String TAG = "PeriodInfo";

	public static final String PERIOD_NUMBER = "PeriodNumber";

	private Date newDate;// 服务器当前时间
	private String periodNumber;// 期号
	private int beforeTime;// 提前几分钟截止
	private String prizeTime;// 开奖时间
	private long curTime;// 本地当前时间

	private String endedTime;// 截止时间

	private ArrayList<AwardItem> mAwardList;

	private Map<String, String> rateStrMap; // 遗漏信息

	private String mWareID; // 商品id,投注的时候需要

	public ArrayList<AwardItem> getAwardList() {
		return mAwardList;
	}

	public void setAwardList(ArrayList<AwardItem> mAwardList) {
		this.mAwardList = mAwardList;
	}

	public void setNewDate(Date date) {
		this.newDate = date;
	}

	public Date getNewDate() {
		return newDate;
	}

	public void setPeriodNumber(String period) {
		this.periodNumber = period;
	}

	public String getPeriodNumber() {
		return periodNumber;
	}

	public void setCurTime(long time) {
		this.curTime = time;
	}

	public long getCurTime() {
		return curTime;
	}

	public void setbeforeTime(int beforetime) {
		this.beforeTime = beforetime;
	}

	public int getbeforeTime() {
		return beforeTime;
	}

	public void setprizeTime(String prizetime) {
		this.prizeTime = prizetime;
	}

	public String getprizeTime() {
		return prizeTime;
	}

	public void setEndTime(String endtime) {
		this.endedTime = endtime;
	}

	public String getEndTime() {
		return endedTime;
	}

	public String getWareID() {
		return mWareID;
	}

	public void setWareID(String mWareID) {
		this.mWareID = mWareID;
	}

	public Map<String, String> getRateStrMap() {
		return rateStrMap;
	}

	public void setRateStrMap(Map<String, String> rateStrMap) {
		this.rateStrMap = rateStrMap;
	}

	public static Date StringToDate(String s) {
		Date time = new Date();
		SimpleDateFormat sd = ComTools.getStandardDateFormat();
		try {
			time = sd.parse(s);
		} catch (ParseException e) {
			System.out.println("输入的日期格式有误！");
		}
		return time;
	}

	public static String dateToString(Date mDate) {
		SimpleDateFormat sd = new SimpleDateFormat("yyMMdd");
		return sd.format(mDate);
	}

	public static PeriodInfo getPeriodInfo(int lotteryType, String json) {
		BYPeriodInfo byPeriodInfo = BYPeriodInfo.parseJson(lotteryType, json);
		if (byPeriodInfo == null) {
			return null;
		}
		PeriodInfo info = new PeriodInfo();

		if (lotteryType == BetItem.TYPE_JSSC || lotteryType == BetItem.TYPE_JX11XUAN5 || lotteryType == BetItem.TYPE_GD11XUAN5 || lotteryType == BetItem.TYPE_K3) {
			info.setEndTime(Long.parseLong(byPeriodInfo.newMinute) * 1000 + "");
			info.setPeriodNumber(byPeriodInfo.WareId.substring(2));
		} else {
			info.setEndTime(byPeriodInfo.IssueEndTime);
			info.setPeriodNumber(byPeriodInfo.IssueNo);
		}

		info.setWareID(byPeriodInfo.WareId);
		info.setNewDate(StringToDate(byPeriodInfo.SystemTime));
		info.setRateStrMap(byPeriodInfo.currentYL);
		// if (info.mAwardList == null) {
		// ArrayList<Record> records = byPeriodInfo.Records;
		// int len = records.size();
		// info.mAwardList = new ArrayList<AwardItem>(len);
		// for (int i = 0; i < len; i++) {
		// AwardItem item = new AwardItem();
		// Record record = records.get(i);
		// item.periodNumber = record.wareIssue;
		// item.result = record.wareResult;
		// item.prizeTime = record.PrizeTime;
		// info.mAwardList.add(item);
		// }
		// }
		return info;
	}

	/**
	 * @author shouli.luo
	 * @time 2013-6-3 下午05:25:35
	 * @方法作用： 获取期次字符串
	 * @param lotteryType
	 * @param beforeTime
	 * @param mDate
	 * @return
	 */
	public static String getPeriodNumber(int lotteryType, int beforeTime, Date mDate) {

		StringBuffer number = new StringBuffer();
		number.append(dateToString(mDate));
		Date originalDate = new Date();
		int num = 1;
		if (lotteryType == BetItem.TYPE_11XUAN5 || lotteryType == BetItem.TYPE_JX11XUAN5 || lotteryType == BetItem.TYPE_GD11XUAN5) {
			originalDate.setHours(9);
			originalDate.setMinutes(05);
			originalDate.setSeconds(0);
			num = getPeriodNumberByTime(originalDate, mDate, 10, beforeTime, 78);
			number.append(String.format("%02d", num));

		} else if (lotteryType == BetItem.TYPE_JSSC) {
			if (mDate.getHours() < 2 && mDate.getHours() >= 0) {
				originalDate.setHours(0);
				originalDate.setMinutes(5);
				originalDate.setSeconds(0);
				num = getPeriodNumberByTime(originalDate, mDate, 5, beforeTime, 23);
			} else if (mDate.getHours() < 22 && mDate.getHours() >= 10) {
				originalDate.setHours(10);
				originalDate.setMinutes(00);
				originalDate.setSeconds(0);
				num = getPeriodNumberByTime(originalDate, mDate, 10, beforeTime, 73) + 23;
			} else if (mDate.getHours() >= 22) {
				originalDate.setHours(22);
				originalDate.setMinutes(5);
				originalDate.setSeconds(0);
				num = getPeriodNumberByTime(originalDate, mDate, 5, beforeTime, 24) + 96;
			} else {
				num = 24;
			}
			number.append(String.format("%03d", num));
		}
//		Logger.i("PeriodInfo", number.toString());
		return number.toString();
		// return "20130527103";
	}

	public static String getRemainTime(int lotteryType, int beforeTime, Date date, int[] times) {
		Date originalDate = new Date();
		long diff = 0;
		int hour = date.getHours();
		int min = date.getMinutes();
		int sec = date.getSeconds();
		int dmin = min % 10;
		int stoptime = 5 - beforeTime;
		int dsec = 60 - sec;
		int dhour = 0;
		String strTime = "";

		// Logger.i("getRemainTime"," type:" + lotteryType);
		if (lotteryType == BetItem.TYPE_11XUAN5 || lotteryType == BetItem.TYPE_GD11XUAN5 || lotteryType == BetItem.TYPE_JX11XUAN5) {
			stoptime = 5 - beforeTime;
			dmin = min % 10;
			if ((hour == 8 && min >= 55) || (hour > 8 && hour < 21) || (hour == 21 && min < (55 - beforeTime))) {
				if (dmin < stoptime) {
					dmin = stoptime - dmin - 1;
				} else {
					dmin = 9 - (dmin - stoptime);
				}
				if (dsec == 60) {
					if (dmin == 9) {
						dmin = 0;
						dsec = 0;
					} else {
						dmin++;
						dsec = 0;
					}
				}

				// Logger.i("getRemainTime", "strTime:" +strTime + "dmin:" +dmin
				// + " stop:" +stoptime);

			} else {

				if (hour > 8) {
					dmin = 60 - min + stoptime - 1;
					dhour = (23 - hour + 9) + dmin / 60;

				} else {
					dmin = 60 - min + stoptime - 1;
					dhour = 9 - hour - 1;
					if (dsec == 60) {
						dmin++;
						dsec = 0;
					}
				}

				// Logger.i("getRemainTime", "strTime:" +strTime + "dhour:"
				// +dhour + " dmin:" +dmin);
			}

		} else if (lotteryType == BetItem.TYPE_JSSC) {
			stoptime = 10 - beforeTime;
			if ((date.getHours() < 2 && date.getHours() >= 0) || (date.getHours() >= 22)) {
				dmin = min % 5;
				stoptime = 5 - beforeTime;
				if (dmin < stoptime) {
					dmin = stoptime - dmin - 1;
				} else {
					dmin = 5 - (dmin - stoptime) - 1;
				}
				if (dsec == 60) {
					if (dmin == 4) {
						dmin = 0;
						dsec = 0;
					} else {
						dmin++;
						dsec = 0;
					}
				}

			} else if (date.getHours() < 22 && date.getHours() >= 10) {

				if (dmin < stoptime) {
					dmin = stoptime - dmin - 1;
				} else {
					dmin = 9 - (dmin - stoptime);
				}

				if (dsec == 60) {
					if (dmin == 9) {
						dmin = 0;
						dsec = 0;
					} else {
						dmin++;
						dsec = 0;
					}
				}

			} else {

				dmin = 60 - min - (10 - stoptime) - 1;
				dhour = 10 - hour - 1;
				if (dsec == 60) {
					dmin++;
					dsec = 0;
				}
			}

		}
		if (dhour > 0) {
			strTime = dhour + "小时" + dmin + "分";
		} else {
			if (dmin > 0) {
				if (dsec > 0) {
					strTime = dmin + "分" + dsec + "秒";
				} else {
					strTime = dmin + "分";
				}

			} else {
				strTime = dsec + "秒";
			}

		}

		if (times != null) {
			times[0] = dhour;
			times[1] = dmin;
			times[2] = dsec;
		}

		// Logger.i("getRemainTime", "strTime:" + strTime);
		return strTime;
	}

	/**
	 * @author shouli.luo
	 * @time 2013-5-27 下午05:30:01
	 * @方法作用：
	 * @param originalDate
	 *            一天最早开奖时间
	 * @param curDate
	 *            当前时间
	 * @param intervalTime
	 *            多少时间一期
	 * @param beforeTime
	 *            提前多少时间截止
	 * @param sumPeriod
	 *            总共的期数
	 * @return
	 */
	private static int getPeriodNumberByTime(Date originalDate, Date curDate, int intervalTime, int beforeTime, int sumPeriod) {

		// Logger.i("tag",
		// "sum_org_sec:"+originalDate.getSeconds()+"--"+"getMinutes:"+originalDate.getMinutes());
		// Logger.i("tag",
		// "sum_cur_sec:"+curDate.getSeconds()+"--"+"getMinutes:"+curDate.getMinutes());

		int periodNum = 1;// 期数
		int sumSec;
		sumSec = ((curDate.getHours() - originalDate.getHours()) * 60 + curDate.getMinutes() - originalDate.getMinutes()) * 60 + curDate.getSeconds() - originalDate.getSeconds();

		if ((curDate.getHours() < originalDate.getHours()) || (sumSec >= sumPeriod * 10 * 60)) {
			periodNum = 1;
		} else {
//			Logger.i("PeriodInfo", "sum_beforeTime:" + beforeTime);
			// Logger.i("PeriodInfo","sum_curDate:"+curDate);
			// Logger.i("PeriodInfo","sum_sumSec:"+sumSec);
//			Logger.i("PeriodInfo", "sum_periodNum:" + (float) (sumSec - beforeTime * 60) / (intervalTime * 60));
			periodNum = ((int) (sumSec / (intervalTime * 60))) + 2;

//			Logger.i("PeriodInfo", "sum_sum%:" + sumSec % (intervalTime * 60));

			if (beforeTime * 60 >= (intervalTime * 60 - sumSec % (intervalTime * 60))) { // 说明在截止投注的时间内
				periodNum++;
			}
		}
//		Logger.i("PeriodInfo", "sum_periodNum:" + periodNum);
		return periodNum;

	}

	public static String splitHtmlResult(int lotteryType, String result) {
		String str = "";
		String[] rsultArr = result.split(",");
		int arrLen = rsultArr.length;
		int splitIndex = 0;
		switch (lotteryType) {
		case BetItem.TYPE_SSQ:
		case BetItem.TYPE_QLC:
			splitIndex = arrLen - 1;
			str = "<FONT COLOR=RED>";
			break;
		case BetItem.TYPE_DLT:
			splitIndex = arrLen - 2;
			str = "<FONT COLOR=RED>";
			break;
		case BetItem.TYPE_QXC:
		case BetItem.TYPE_PL3:
		case BetItem.TYPE_PL5:
		case BetItem.TYPE_3D:
		case BetItem.TYPE_JSSC:
		case BetItem.TYPE_JX11XUAN5:
		case BetItem.TYPE_GD11XUAN5:
			splitIndex = arrLen;
			str = "<FONT COLOR=RED>";
			break;
		}

		for (int i = 0; i < arrLen; i++) {
			if (i >= splitIndex) {
				str += "</FONT><FONT COLOR=BLUE>" + rsultArr[i] + "</FONT>" + "&nbsp;&nbsp";
			} else {
				str += rsultArr[i] + "&nbsp;&nbsp;";
			}
		}

		return str;
	}

	/**
	 * 获取遗漏数据
	 * 
	 * @param lotteryType
	 * @param playWayType
	 * @return
	 */
	public List<String[]> getRateStrByPlayType(int lotteryType, int playWayType) {
		List<String[]> rateStrs = new ArrayList<String[]>();
		String ylStrInfo = null;
		if (rateStrMap == null || rateStrMap.size() == 0) {
			return rateStrs;
		}
		switch (lotteryType) {
		case BetItem.TYPE_GD11XUAN5:
		case BetItem.TYPE_JX11XUAN5:
			do11xuan5YL(rateStrs, ylStrInfo, playWayType);
			break;
		case BetItem.TYPE_JSSC:
			doSSCYL(rateStrs, ylStrInfo, playWayType);
			break;
		case BetItem.TYPE_SSQ:
			doSSQYL(rateStrs, ylStrInfo, playWayType);
			break;
		case BetItem.TYPE_DLT:
			doDLTYL(rateStrs, ylStrInfo, playWayType);
			break;
		case BetItem.TYPE_QLC:
			doQLCYL(rateStrs, ylStrInfo, playWayType);
			break;
		case BetItem.TYPE_QXC:
			doQXCYL(rateStrs, ylStrInfo, playWayType);
			break;
		case BetItem.TYPE_3D:
			do3DYL(rateStrs, ylStrInfo, playWayType);
			break;
		case BetItem.TYPE_PL3:
			doPL3YL(rateStrs, ylStrInfo, playWayType);
			break;
		case BetItem.TYPE_PL5:
			doPL5YL(rateStrs, ylStrInfo, playWayType);
			break;
		}
		return rateStrs;
	}

	private void doPL5YL(List<String[]> rateStrs, String ylStrInfo, int playWayType) {
		ylStrInfo = rateStrMap.get("PT");
		String[] ylStrs = ylStrInfo.split(",");
		String[] wanStrs = new String[10];
		String[] qianStrs = new String[10];
		String[] baiStrs = new String[10];
		String[] shiStrs = new String[10];
		String[] geStrs = new String[10];
		System.arraycopy(ylStrs, 0, wanStrs, 0, 10);
		System.arraycopy(ylStrs, 10, qianStrs, 0, 10);
		System.arraycopy(ylStrs, 20, baiStrs, 0, 10);
		System.arraycopy(ylStrs, 30, shiStrs, 0, 10);
		System.arraycopy(ylStrs, 40, geStrs, 0, 10);
		rateStrs.add(wanStrs);
		rateStrs.add(qianStrs);
		rateStrs.add(baiStrs);
		rateStrs.add(shiStrs);
		rateStrs.add(geStrs);
	}

	private void doPL3YL(List<String[]> rateStrs, String ylStrInfo, int playWayType) {
		do3DYL(rateStrs, ylStrInfo, playWayType);
	}

	private void do3DYL(List<String[]> rateStrs, String ylStrInfo, int playWayType) {
		String[] ylStrs = null;
		switch (playWayType) {
		case BetItem.PY_3D_ZHIXUAN:
		case BetItem.PY_PL3_ZHIXUAN:	
			ylStrInfo = rateStrMap.get("ZHIX");
			ylStrs = ylStrInfo.split(",");
			String[] baiStrs = new String[10]; 
			String[] shiStrs = new String[10]; 
			String[] geStrs = new String[10]; 
			System.arraycopy(ylStrs, 0, baiStrs, 0, 10);
			System.arraycopy(ylStrs, 10, shiStrs, 0, 10);
			System.arraycopy(ylStrs, 20, geStrs, 0, 10);
			rateStrs.add(baiStrs);
			rateStrs.add(shiStrs);
			rateStrs.add(geStrs);
			break;
		case BetItem.PY_3D_ZUSANFUSHI:
		case BetItem.PY_PL3_ZUSANFUSHI:	
			ylStrInfo = rateStrMap.get("ZUSAN");
			ylStrs = ylStrInfo.split(",");
			rateStrs.add(ylStrs);
			break;
		case BetItem.PY_3D_ZULIU:
		case BetItem.PY_PL3_ZULIU:	
			ylStrInfo = rateStrMap.get("ZULIU");
			ylStrs = ylStrInfo.split(",");
			rateStrs.add(ylStrs);
			break;
		}
	}

	private void doQXCYL(List<String[]> rateStrs, String ylStrInfo, int playWayType) {
		String[] ylStrs = null;
		ylStrInfo = rateStrMap.get("PT");
		ylStrs = ylStrInfo.split(",");
		String[] oneStrs = new String[10];
		String[] twoStrs = new String[10];
		String[] threeStrs = new String[10];
		String[] fourStrs = new String[10];
		String[] fiveStrs = new String[10];
		String[] sixStrs = new String[10];
		String[] sevenStrs = new String[10];
		System.arraycopy(ylStrs, 0, oneStrs, 0, 10);
		System.arraycopy(ylStrs, 10, twoStrs, 0, 10);
		System.arraycopy(ylStrs, 20, threeStrs, 0, 10);
		System.arraycopy(ylStrs, 30, fourStrs, 0, 10);
		System.arraycopy(ylStrs, 40, fiveStrs, 0, 10);
		System.arraycopy(ylStrs, 50, sixStrs, 0, 10);
		System.arraycopy(ylStrs, 60, sevenStrs, 0, 10);
		rateStrs.add(oneStrs);
		rateStrs.add(twoStrs);
		rateStrs.add(threeStrs);
		rateStrs.add(fourStrs);
		rateStrs.add(fiveStrs);
		rateStrs.add(sixStrs);
		rateStrs.add(sevenStrs);
	}

	private void doQLCYL(List<String[]> rateStrs, String ylStrInfo, int playWayType) {
		String[] ylStrs = null;
		switch (playWayType) {
		case BetItem.PY_QLC_PUTONG:
			ylStrInfo = rateStrMap.get("PT");
			ylStrs = ylStrInfo.split(",");
			rateStrs.add(ylStrs);
			break;
		case BetItem.PY_QLC_DANTUO:
			ylStrInfo = rateStrMap.get("DT");
			ylStrs = ylStrInfo.split(",");
			String[] redDMStrs = new String[30]; //红球胆码
			String[] redTMStrs = new String[30]; //红球拖码
			System.arraycopy(ylStrs, 0, redDMStrs, 0, 30);
			System.arraycopy(ylStrs, 30, redTMStrs, 0, 30);
			rateStrs.add(redDMStrs);
			rateStrs.add(redTMStrs);
			break;
		}
	}

	private void doDLTYL(List<String[]> rateStrs, String ylStrInfo, int playWayType) {
		String[] ylStrs = null;
		switch (playWayType) {
		case BetItem.PY_DLT_PUTONG:
			ylStrInfo = rateStrMap.get("PT");
			ylStrs = ylStrInfo.split(",");
			String[] redStrs = new String[35];
			String[] blueStrs = new String[12];
			System.arraycopy(ylStrs, 0, redStrs, 0, 35);
			System.arraycopy(ylStrs, 35, blueStrs, 0, 12);
			rateStrs.add(redStrs);
			rateStrs.add(blueStrs);
			break;
		case BetItem.PY_DLT_DANTUO:
			ylStrInfo = rateStrMap.get("DT");
			ylStrs = ylStrInfo.split(",");
			String[] redDMStrs = new String[35]; //红球胆码
			String[] redTMStrs = new String[35]; //红球拖码
			String[] blueDMtrs = new String[12]; //蓝球胆码
			String[] blueTMtrs = new String[12]; //蓝球拖码
			System.arraycopy(ylStrs, 0, redDMStrs, 0, 35);
			System.arraycopy(ylStrs, 35, blueDMtrs, 0, 12);
			System.arraycopy(ylStrs, 47, redTMStrs, 0, 35);
			System.arraycopy(ylStrs, 82, blueTMtrs, 0, 12);
			rateStrs.add(redDMStrs);
			rateStrs.add(blueDMtrs);
			rateStrs.add(redTMStrs);
			rateStrs.add(blueTMtrs);
			break;
		}
	}

	private void doSSQYL(List<String[]> rateStrs, String ylStrInfo, int playWayType) {
		String[] ylStrs = null;
		switch (playWayType) {
		case BetItem.PY_SSQ_PUTONG:
			ylStrInfo = rateStrMap.get("PT");
			ylStrs = ylStrInfo.split(",");
			String[] redStrs = new String[33];
			String[] blueStrs = new String[16];
			System.arraycopy(ylStrs, 0, redStrs, 0, 33);
			System.arraycopy(ylStrs, 33, blueStrs, 0, 16);
			rateStrs.add(redStrs);
			rateStrs.add(blueStrs);
			break;
		case BetItem.PY_SSQ_DANTUO:
			ylStrInfo = rateStrMap.get("DT");
			ylStrs = ylStrInfo.split(",");
			String[] redDMStrs = new String[33]; //红球胆码
			String[] redTMStrs = new String[33]; //红球拖码
			String[] blueDStrs = new String[16]; //篮球
			System.arraycopy(ylStrs, 0, redDMStrs, 0, 33);
			System.arraycopy(ylStrs, 33, redTMStrs, 0, 33);
			System.arraycopy(ylStrs, 66, blueDStrs, 0, 16);
			rateStrs.add(redDMStrs);
			rateStrs.add(redTMStrs);
			rateStrs.add(blueDStrs);
			break;
		}
	}

	private void doSSCYL(final List<String[]> rateStrs, String ylStrInfo, int playWayType) {
		switch (playWayType) {
		case BetItem.PY_JSSC_1XFU:
		case BetItem.PY_JSSC_2XFU:
		case BetItem.PY_JSSC_3XFU:
		case BetItem.PY_JSSC_5XFU:
		case BetItem.PY_JSSC_5XTONG:
			ylStrInfo = rateStrMap.get("5XZHIX");
			String[] ylStrs = ylStrInfo.split(",");// 得到一個长度为33的的数组
			String[] wanStrs = new String[10];
			String[] qianStrs = new String[10];
			String[] baiStrs = new String[10];
			String[] shiStrs = new String[10];
			String[] geStrs = new String[10];
			System.arraycopy(ylStrs, 0, wanStrs, 0, 10);
			System.arraycopy(ylStrs, 10, qianStrs, 0, 10);
			System.arraycopy(ylStrs, 20, baiStrs, 0, 10);
			System.arraycopy(ylStrs, 30, shiStrs, 0, 10);
			System.arraycopy(ylStrs, 40, geStrs, 0, 10);
			rateStrs.add(wanStrs);
			rateStrs.add(qianStrs);
			rateStrs.add(baiStrs);
			rateStrs.add(shiStrs);
			rateStrs.add(geStrs);
			break;
		case BetItem.PY_JSSC_2XZU:
			ylStrInfo = rateStrMap.get("2XZX");
			break;
		case BetItem.PY_JSSC_DXDS:
			ylStrInfo = rateStrMap.get("DXDS");
			break;
		}
	}

	private void do11xuan5YL(final List<String[]> rateStrs, String ylStrInfo, int playWayType) {
		switch (playWayType) {
		case BetItem.PY_11XUAN5_REN2:
		case BetItem.PY_11XUAN5_REN3:
		case BetItem.PY_11XUAN5_REN4:
		case BetItem.PY_11XUAN5_REN5:
		case BetItem.PY_11XUAN5_REN6:
		case BetItem.PY_11XUAN5_REN7:
		case BetItem.PY_11XUAN5_REN8:
			ylStrInfo = rateStrMap.get("RX");
			rateStrs.add(ylStrInfo.split(","));
			break;
		case BetItem.PY_11XUAN5_REN1:
		case BetItem.PY_11XUAN5_QIAN2ZHI:
		case BetItem.PY_11XUAN5_QIAN3ZHI:
			ylStrInfo = rateStrMap.get("QZHIX");
			String[] strs = ylStrInfo.split(",");// 得到一個长度为33的的数组
			String[] zhiXuanStrs1 = new String[11];// 直选第一位 有序 第一位
			String[] zhiXuanStrs2 = new String[11];// 直选第二位 有序 第二位
			String[] zhiXuanStrs3 = new String[11];// 直选第三位 有序 第三位置
			System.arraycopy(strs, 0, zhiXuanStrs1, 0, 11);
			System.arraycopy(strs, 11, zhiXuanStrs2, 0, 11);
			System.arraycopy(strs, 22, zhiXuanStrs3, 0, 11);
			rateStrs.add(zhiXuanStrs1);
			rateStrs.add(zhiXuanStrs2);
			rateStrs.add(zhiXuanStrs3);
			break;
		case BetItem.PY_11XUAN5_QIAN2ZU:
			ylStrInfo = rateStrMap.get("Q2ZX");
			break;
		case BetItem.PY_11XUAN5_QIAN3ZU:
			ylStrInfo = rateStrMap.get("Q3ZX");
			break;
		}
	}

}
