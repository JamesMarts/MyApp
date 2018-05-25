package com.example.lijuw.myapp;


import com.orhanobut.logger.Logger;

/**
 * 拼接选号内容区信息
 * 
 * @author GL
 * 
 */
public class PostBetContent {

	private static final String TAG = "PostBetContent";

	private String[] infos; // 投注信息
	private String mPlayWay; // 玩法名称
	private LotteryInfo mLotteryInfo;
	private boolean zhuiJia; // 大乐透追加

	public PostBetContent(LotteryInfo lotteryInfo) {
		this.mLotteryInfo = lotteryInfo;
		init();
	}

	public PostBetContent(LotteryInfo lotteryInfo, boolean zhuiJia) {
		this.mLotteryInfo = lotteryInfo;
		this.zhuiJia = zhuiJia;
		init();
	}

	private void init() {
		int lotteryType = mLotteryInfo.getlotteryType();
		switch (lotteryType) {
		case BetItem.TYPE_SSQ:
			doShuangSeQiu();
			break;
		case BetItem.TYPE_DLT:
			doDLT();
			break;
		case BetItem.TYPE_QLC:
			doQLC();
			break;
		case BetItem.TYPE_QXC:
			doQXC();
			break;
		case BetItem.TYPE_PL3:
			doPL3();
			break;
		case BetItem.TYPE_PL5:
			doPL5();
			break;
		case BetItem.TYPE_3D:
			do3D();
			break;
		case BetItem.TYPE_JSSC:
			doSSC();
			break;
		case BetItem.TYPE_GD11XUAN5:
			do11XUAN5();
			break;
		case BetItem.TYPE_JX11XUAN5:
			do11XUAN5();
			break;
		case BetItem.TYPE_K3:
			doK3();
			break;
		}
	}


	public String[] getInfos() {
		return infos;
	}

	public void setInfos(String[] infos) {
		this.infos = infos;
	}

	public String getPlayWay() {
		return mPlayWay;
	}

	public void setPlayWay(String playWay) {
		this.mPlayWay = playWay;
	}

	// 普通 : "2:01,02,03,04,05,06|07,08";
	// 胆拖: "8:01,02,03,04,05;06,07|07,08";
	private void doShuangSeQiu() {
		String saveNum = mLotteryInfo.getSaveNo();
		saveNum = saveNum.substring(saveNum.indexOf(":") + 1).replaceAll(",", "");
		String[] split = saveNum.split("\\|");
		boolean danshi = mLotteryInfo.getBetCount() == 1;
		infos = new String[2];
		switch (mLotteryInfo.getPlayWayType()) {
		case BetItem.PY_SSQ_PUTONG:
			infos[0] = split[0];
			infos[1] = split[1];
			mPlayWay = danshi ? "单式" : "复式";
			break;
		case BetItem.PY_SSQ_DANTUO:
			infos[0] = getDanStr(split[0]);
			infos[1] = split[1];
			mPlayWay = "胆拖";
			break;
		}
	}

	private void doDLT() {
		String saveNum = mLotteryInfo.getSaveNo();
		saveNum = saveNum.substring(saveNum.indexOf(":") + 1).replaceAll(",", "");
		String[] split = saveNum.split("\\|");
		boolean danshi = mLotteryInfo.getBetCount() == 1;
		infos = new String[2];
		switch (mLotteryInfo.getPlayWayType()) {
		case BetItem.PY_DLT_PUTONG:
			infos[0] = split[0];
			infos[1] = split[1];
			mPlayWay = danshi ? "单式" : "复式胆拖";
			mPlayWay = zhuiJia ? mPlayWay + "追加" : mPlayWay;
			break;
		case BetItem.PY_DLT_DANTUO:
			infos[0] = getDanStr(split[0]);
			infos[1] = getDanStr(split[1]);
			mPlayWay = "复式胆拖";
			mPlayWay = zhuiJia ? mPlayWay + "追加" : mPlayWay;
			break;
		}
	}

	private void doQLC() {
		String saveNum = mLotteryInfo.getSaveNo();
		saveNum = saveNum.substring(saveNum.indexOf(":") + 1);
		String[] split = saveNum.split("\\|");
		boolean danshi = mLotteryInfo.getBetCount() == 1;

		switch (mLotteryInfo.getPlayWayType()) {
		case BetItem.PY_QLC_PUTONG:
			infos = new String[1];
			infos[0] = split[0];
			mPlayWay = danshi ? "单式" : "复式";
			break;
		case BetItem.PY_QLC_DANTUO:
			infos = new String[2];
			String[] redInfo = split[0].split(";"); // [0102030405,0607]
			StringBuilder sb = new StringBuilder();
			sb.append("[");
			sb.append(redInfo[0]);
			sb.append("]");
			infos[0] = sb.toString().replaceAll(",", "");
			infos[1] = redInfo[1].replaceAll(",", "");
			mPlayWay = "胆拖";
			break;
		}

	}

	// 0,2-3,4-4,5-2,4-3,4-3,4-2,7
	// 4-3-0-4-7-5-3
	// 52#14052#480#1579-01-8-5789-9-014-01234$七星彩~1~480#0#
	private void doQXC() {
		String saveNum = mLotteryInfo.getSaveNo();
		saveNum = saveNum.substring(saveNum.indexOf(":") + 1);
		boolean danshi = mLotteryInfo.getBetCount() == 1;
		infos = new String[1];
		if (danshi) {
			infos[0] = saveNum;
		} else {
			infos[0] = saveNum.replaceAll(",", "");
		}
		mPlayWay = "七星彩";
	}

	// 54#14120#1#4-0-9$直选~1~1#0#
	// 54#14120#6#012$组三复式~1~6#0#
	// 54#14120#10#12345$组六复式~1~10#0#
	private void doPL3() {
		String saveNum = mLotteryInfo.getSaveNo();
		saveNum = saveNum.substring(saveNum.indexOf(":") + 1);
		infos = new String[1];
		switch (mLotteryInfo.getPlayWayType()) {
		case BetItem.PY_PL3_ZHIXUAN:
			mPlayWay = "直选";
			break;
		case BetItem.PY_PL3_ZUSANFUSHI:
			mPlayWay = "组三复式";
			break;
		case BetItem.PY_PL3_ZULIU:
			mPlayWay = "组六复式";
			break;
		}
		infos[0] = saveNum.replaceAll(",", "");
	}

	// 53#14120#4#0-12-2-3-34$排列5~1~4#0#
	private void doPL5() {
		String saveNum = mLotteryInfo.getSaveNo();
		saveNum = saveNum.substring(saveNum.indexOf(":") + 1);
		infos = new String[1];
		infos[0] = saveNum.replaceAll(",", "");
		mPlayWay = "排列5";
	}

	// 7-3-9$直选单式~1~1
	// 12-35-3$直选复式~1~4
	// 2-1-1$组三单式~1~1
	// 2345$组三复式~1~12
	// 0-1-2$组六单式~1~1
	// 12345$组六复式~1~10
	private void do3D() {
		String saveNum = mLotteryInfo.getSaveNo();
		saveNum = saveNum.substring(saveNum.indexOf(":") + 1);
		infos = new String[1];
		switch (mLotteryInfo.getPlayWayType()) {
		case BetItem.PY_3D_ZHIXUAN:
			if (mLotteryInfo.getBetCount() == 1) {
				mPlayWay = "直选单式";
			} else {
				mPlayWay = "直选复式";
			}
			infos[0] = saveNum.replaceAll(",", "");
			break;
		case BetItem.PY_3D_ZUSANFUSHI:
			if (mLotteryInfo.getBetCount() == 1) {
				mPlayWay = "组三单式";
			} else {
				mPlayWay = "组三复式";
			}
			infos[0] = saveNum.replaceAll(",", "");
			break;
		case BetItem.PY_3D_ZULIU:
			if (mLotteryInfo.getBetCount() == 1) {
				mPlayWay = "组六单式";
				infos[0] = saveNum.replaceAll(",", "-");
			} else {
				mPlayWay = "组六复式";
				infos[0] = saveNum.replaceAll(",", "");
			}
			break;
		}
	}
	
	//2$一星~1~1|3$一星~1~1|4$一星~1~1
	//567-678$二星~1~9
	//3457$二星组选~1~6
	//34-56-67$三星~1~8
	//57-23-24-8-49$五星~1~16
	//67-3-6789-2-456$五星通选~1~24
	//双-小$大小单双~1~1
	private void doSSC() {
		String saveNum = mLotteryInfo.getSaveNo();
		saveNum = saveNum.substring(saveNum.indexOf(":") + 1);
		switch (mLotteryInfo.getPlayWayType()) {
		case BetItem.PY_JSSC_1XFU:
			infos = new String[1];
			infos[0] = saveNum.replaceAll(",", "");
			mPlayWay = "一星";
			break;
		case BetItem.PY_JSSC_2XFU:
			infos = new String[1];
			infos[0] = saveNum.replaceAll(",", "");
			mPlayWay = "二星";
			break;
		case BetItem.PY_JSSC_2XZU:
			infos = new String[1];
			infos[0] = saveNum.replaceAll(",", "");
			mPlayWay = "二星组选";
			break;
		case BetItem.PY_JSSC_3XFU:
			infos = new String[1];
			infos[0] = saveNum.replaceAll(",", "");
			mPlayWay = "三星";
			break;
		case BetItem.PY_JSSC_5XFU:
			infos = new String[1];
			infos[0] = saveNum.replaceAll(",", "");
			mPlayWay = "五星";
			break;
		case BetItem.PY_JSSC_5XTONG:
			infos = new String[1];
			infos[0] = saveNum.replaceAll(",", "");
			mPlayWay = "五星通选";
			break;
		case BetItem.PY_JSSC_DXDS:
			infos = new String[1];
			String[] split = saveNum.split("-");
			StringBuilder sb = new StringBuilder();
			sb.append(getDXDSStr(split[0]));
			sb.append("-");
			sb.append(getDXDSStr(split[1]));
			infos[0] = sb.toString();
			mPlayWay = "大小单双";
			break;
		}
	}

	//030405----$前一直选~1~3
	//04050607$任选二~1~6  , [04]050607$任选二胆拖~1~3
	//03040507$任选三~1~4  , [0407]080910$任选三胆拖~1~3
	//040506070809$任选四~1~15   , [030406]070809$任选四胆拖~1~3
	//060708091011$任选五~1~6  , [04050608]091011$任选五胆拖~1~3
	//05060708091011$任选六~1~7  , [02030405]060708$任选六胆拖~1~3
	//0405060708091011$任选七~1~8  , [020306070811]0410$任选七胆拖~1~2
	//0304050607080910$任选八~1~1  , 0102030405060708$任选八~1~1|0102030405060709$任选八~1~1
	//03040506---$前二组选~1~6  , [06]07080910---$前二组选胆拖~1~4
	//0405060708-091011---$前二直选~1~15 
	//0304050607--$前三组选~1~10  , [0406]070809--$前三组选胆拖~1~3
	//0405-0607-0809--$前三直选~1~8
	private void do11XUAN5() {
		String saveNum = mLotteryInfo.getSaveNo();
		saveNum = saveNum.substring(saveNum.indexOf(":") + 1);
		switch (mLotteryInfo.getPlayWayType()) {
		case BetItem.PY_11XUAN5_REN1:
			infos = new String[1];
			infos[0] = saveNum.replaceAll(",", "").concat("----");
			mPlayWay = "前一直选";
			break;
		case BetItem.PY_11XUAN5_REN2:
			infos = new String[1];
			infos[0] = saveNum.replaceAll(",", "");
			mPlayWay = "任选二";
			break;
		case BetItem.PY_11XUAN5_REN3:
			infos = new String[1];
			infos[0] = saveNum.replaceAll(",", "");
			mPlayWay = "任选三";
			break;
		case BetItem.PY_11XUAN5_REN4:
			infos = new String[1];
			infos[0] = saveNum.replaceAll(",", "");
			mPlayWay = "任选四";
			break;
		case BetItem.PY_11XUAN5_REN5:
			infos = new String[1];
			infos[0] = saveNum.replaceAll(",", "");
			mPlayWay = "任选五";
			break;
		case BetItem.PY_11XUAN5_REN6:
			infos = new String[1];
			infos[0] = saveNum.replaceAll(",", "");
			mPlayWay = "任选六";
			break;
		case BetItem.PY_11XUAN5_REN7:
			infos = new String[1];
			infos[0] = saveNum.replaceAll(",", "");
			mPlayWay = "任选七";
			break;
		case BetItem.PY_11XUAN5_REN8:
			infos = new String[1];
			infos[0] = saveNum.replaceAll(",", "");
			mPlayWay = "任选八";
			break;
		case BetItem.PY_11XUAN5_QIAN2ZHI:
			infos = new String[1];
			infos[0] = saveNum.replaceAll(",", "").concat("---");
			mPlayWay = "前二直选";
			break;
		case BetItem.PY_11XUAN5_QIAN2ZU:
			infos = new String[1];
			infos[0] = saveNum.replaceAll(",", "").concat("---");
			mPlayWay = "前二组选";
			break;
		case BetItem.PY_11XUAN5_QIAN3ZHI:
			infos = new String[1];
			infos[0] = saveNum.replaceAll(",", "").concat("--");
			mPlayWay = "前三直选";
			break;
		case BetItem.PY_11XUAN5_QIAN3ZU:
			infos = new String[1];
			infos[0] = saveNum.replaceAll(",", "").concat("--");
			mPlayWay = "前三组选";
			break;
		case BetItem.PY_11XUAN5_REN2_DAN:
			infos = new String[1];
			infos[0] = getDanStr(saveNum.replaceAll(",", ""));
			mPlayWay = "任选二胆拖";
			break;
		case BetItem.PY_11XUAN5_REN3_DAN:
			infos = new String[1];
			infos[0] = getDanStr(saveNum.replaceAll(",", ""));
			mPlayWay = "任选三胆拖";
			break;
		case BetItem.PY_11XUAN5_REN4_DAN:
			infos = new String[1];
			infos[0] = getDanStr(saveNum.replaceAll(",", ""));
			mPlayWay = "任选四胆拖";
			break;
		case BetItem.PY_11XUAN5_REN5_DAN:
			infos = new String[1];
			infos[0] = getDanStr(saveNum.replaceAll(",", ""));
			mPlayWay = "任选五胆拖";
			break;
		case BetItem.PY_11XUAN5_REN6_DAN:
			infos = new String[1];
			infos[0] = getDanStr(saveNum.replaceAll(",", ""));
			mPlayWay = "任选六胆拖";
			break;
		case BetItem.PY_11XUAN5_REN7_DAN:
			infos = new String[1];
			infos[0] = getDanStr(saveNum.replaceAll(",", ""));
			mPlayWay = "任选七胆拖";
			break;
		case BetItem.PY_11XUAN5_QIAN2ZU_DAN:
			infos = new String[1];
			infos[0] = getDanStr(saveNum.replaceAll(",", "")).concat("---");
			mPlayWay = "前二组选胆拖";
			break;
		case BetItem.PY_11XUAN5_QIAN3ZU_DAN:
			infos = new String[1];
			infos[0] = getDanStr(saveNum.replaceAll(",", "")).concat("--");
			mPlayWay = "前三组选胆拖";
			break;
		}
	}

	//按;分割得到长度为2的数组,第一位是胆码,第二位是拖码,返回"[胆码]拖码"的格式.    
	private String getDanStr(String info){
		String[] split = info.split(";");
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		sb.append(split[0]);
		sb.append("]");
		sb.append(split[1]);
		return sb.toString();
	}
	
	//通过数字获得大小单双   2大 1小 5单 4双  ,传入2-2 返回大-大
	private String getDXDSStr(String str){
		String result = null;
		if("2".equals(str)){
			result = "大";
		}else if("1".equals(str)){
			result = "小";
		}else if("5".equals(str)){
			result = "单";
		}else if("4".equals(str)){
			result = "双";
		}
 		return result;
	}
	
	private void doK3() {
		String saveNum = mLotteryInfo.getSaveNo();
		saveNum = saveNum.substring(saveNum.indexOf(":") + 1);
		Logger.i("BetOkActivity", saveNum);
		switch (mLotteryInfo.getPlayWayType()) {
		case BetItem.PY_K3_HZ:
			infos = new String[1];
			infos[0] = saveNum;
			mPlayWay = "和值";
			break;
		case BetItem.PY_K3_3TH:
			infos = new String[1];
			if(saveNum.contains("三同号通选")){
				infos[0] = "777";
				mPlayWay = "三同号通选";
			}else{
				infos[0] = saveNum;
				mPlayWay = "三同号单选";
			}
			break;
		case BetItem.PY_K3_2TH_DX:
			infos = new String[1];
			infos[0] = saveNum;
			mPlayWay = "二同号单选";
			break;
		case BetItem.PY_K3_2TH_FX:
			infos = new String[1];
			infos[0] = saveNum;
			mPlayWay = "二同号复选";
			break;
		case BetItem.PY_K3_3BTH:
			infos = new String[1];
			if(saveNum.contains("三连号通选")){
				infos[0] = "789";
				mPlayWay = "三连号通选";
			}else{
				infos[0] = saveNum;
				mPlayWay = "三不同号";
			}
			break;
		case BetItem.PY_K3_2BTH:
			infos = new String[1];
			infos[0] = saveNum;
			mPlayWay = "二不同号";
			break;
		}
	}
}
