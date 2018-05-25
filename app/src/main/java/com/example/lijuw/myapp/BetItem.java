package com.example.lijuw.myapp;

import android.content.Context;



public class BetItem {

	public  static final String LOTTERY_TYPE = "lottery_type";//彩种
	public  static final String LOTTERY_NAME = "lottery_name";//彩种
	public  static final String LOTTERY_PERIODID = "periodId";//彩种

	public  static final String PLAYWAY_TYPE = "playway_type";//玩法类型
	public  static final String PLAYWAY_NAME = "playway_name";//玩法名
	public  static final String DAN_COUNT = "danCount";//但个数

	
	public  static final String PERIODINFO = "periodinfo";
	public  static final String TO_BET = "to_bet";//直接跳转到投注页面

	public static final String MATCH_SELECTED_COUNT = "matchSelectedCount";// 选中比赛数

	
	public  static final String SAVE_NUM = "saveNum";//玩法名

	
	
	//shareType
	public  static final String SHARE_TYPE_SELF = "SELF";//自购
	public  static final String SHARE_TYPE_TOGETHER = "TOGETHER";//合买
	public  static final String SHARE_TYPE_CAHSE = "CAHSE";//追号





	/********************************数字彩*******************************************/
	public static final int TYPE_ALL = 1000;
	
	public static final int TYPE_SSQ = 0;//双色球
	public static final int PY_SSQ_PUTONG = TYPE_SSQ+1; //普通投注
	public static final int PY_SSQ_DANTUO = TYPE_SSQ+2; //胆拖投注
	private static String[] ARRAY_SSQ_NAME = new String[]{"普通投注","胆拖投注"};
	private static int[] ARRAY_SSQ_ID = new int[]{PY_SSQ_PUTONG,PY_SSQ_DANTUO};

	public static final int TYPE_DLT = 1;//大乐透
	public static final int PY_DLT_PUTONG = TYPE_DLT+1; //普通投注
	public static final int PY_DLT_DANTUO = TYPE_DLT+2; //胆拖投注
	private static String[] ARRAY_DLT_NAME = new String[]{"普通投注","胆拖投注"};
	private static int[] ARRAY_DLT_ID = new int[]{PY_DLT_PUTONG,PY_DLT_DANTUO};

	public static final int TYPE_PL3 = 2;//排列三
	public static final int PY_PL3_ZHIXUAN = TYPE_PL3+1; //直选
//	public static final int PY_PL3_ZUSANDANSHI = TYPE_PL3+2; //组三单式
	public static final int PY_PL3_ZUSANFUSHI = TYPE_PL3+2; //组三复式
	public static final int PY_PL3_ZULIU = TYPE_PL3+3; //组六
	private static String[] ARRAY_PL3_TYPE_NAME = new String[]{"排三直选","排三组三","排三组六"};//用于提交到平台
	private static String[] ARRAY_PL3_NAME = new String[]{"直选","组三","组六"};
//	private static int[] ARRAY_PL3_ID = new int[]{PY_PL3_ZHIXUAN,PY_PL3_ZUSANDANSHI,
//		PY_PL3_ZUSANFUSHI,PY_PL3_ZULIU};
	private static int[] ARRAY_PL3_ID = new int[]{PY_PL3_ZHIXUAN,PY_PL3_ZUSANFUSHI,PY_PL3_ZULIU};

	public static final int TYPE_PL5 = 3;//排练5
	public static final int PY_PL5_PUTONG = TYPE_PL5+1; //普通投注
	private static String[] ARRAY_PL5_NAME = new String[]{"普通投注"};
	private static String[] ARRAY_PL5_TYPE_NAME = new String[]{"排五直选"};//用于提交到平台
	private static int[] ARRAY_PL5_ID = new int[]{PY_PL5_PUTONG};


	public static final int TYPE_QLC = 4;//七乐彩
	public static final int PY_QLC_PUTONG = TYPE_QLC+1; //普通投注
	public static final int PY_QLC_DANTUO = TYPE_QLC+2; //胆拖投注
	private static String[] ARRAY_QLC_NAME = new String[]{"普通投注","胆拖投注"};
	private static int[] ARRAY_QLC_ID = new int[]{PY_QLC_PUTONG,PY_QLC_DANTUO};


	public static final int TYPE_QXC = 5;//七星彩
	public static final int PY_QXC_PUTONG = TYPE_QXC+1; //普通投注
	private static String[] ARRAY_QXC_NAME = new String[]{"普通投注"};
	private static int[] ARRAY_QXC_ID = new int[]{PY_QXC_PUTONG};

	public static final int TYPE_3D = 6;//3d福彩
	public static final int PY_3D_ZHIXUAN = TYPE_3D+1; //直选
//	public static final int PY_3D_ZUSANDANSHI = TYPE_3D+2; //组三单式
	public static final int PY_3D_ZUSANFUSHI = TYPE_3D+2; //组三复式
	public static final int PY_3D_ZULIU = TYPE_3D+3; //组六
	private static String[] ARRAY_3D_TYPE_NAME = new String[]{"直选","组三","组六"};//用于提交到平台
	private static String[] ARRAY_3D_NAME = new String[]{"直选","组三","组六"};
//	private static int[] ARRAY_3D_ID = new int[]{PY_3D_ZHIXUAN,PY_3D_ZUSANDANSHI,
//		PY_3D_ZUSANFUSHI,PY_3D_ZULIU};
	private static int[] ARRAY_3D_ID = new int[]{PY_3D_ZHIXUAN,
	PY_3D_ZUSANFUSHI,PY_3D_ZULIU};


	public static final int TYPE_11XUAN5 = 7;//11选5
	/**********************普通投注**********************/
	public static final int PY_11XUAN5_REN1 = TYPE_11XUAN5+1; //前一
	public static final int PY_11XUAN5_REN2 = TYPE_11XUAN5+2; //任选2
	public static final int PY_11XUAN5_REN3 = TYPE_11XUAN5+3; //任选3
	public static final int PY_11XUAN5_REN4 = TYPE_11XUAN5+4; //任选4
	public static final int PY_11XUAN5_REN5 = TYPE_11XUAN5+5; //任选5
	public static final int PY_11XUAN5_REN6 = TYPE_11XUAN5+6; //任选6
	public static final int PY_11XUAN5_REN7 = TYPE_11XUAN5+7; //任选7
	public static final int PY_11XUAN5_REN8 = TYPE_11XUAN5+8; //任选8
	public static final int PY_11XUAN5_QIAN2ZHI = TYPE_11XUAN5+9; //前二直
	public static final int PY_11XUAN5_QIAN2ZU = TYPE_11XUAN5+10; //前二组
	public static final int PY_11XUAN5_QIAN3ZHI = TYPE_11XUAN5+11; //前三直
	public static final int PY_11XUAN5_QIAN3ZU = TYPE_11XUAN5+12; //前三组
	/**********************胆拖投注**********************/
	public static final int PY_11XUAN5_REN2_DAN = TYPE_11XUAN5+13; //任选2  
	public static final int PY_11XUAN5_REN3_DAN = TYPE_11XUAN5+14; //任选3
	public static final int PY_11XUAN5_REN4_DAN = TYPE_11XUAN5+15; //任选4
	public static final int PY_11XUAN5_REN5_DAN = TYPE_11XUAN5+16; //任选5
	public static final int PY_11XUAN5_REN6_DAN = TYPE_11XUAN5+17; //任选6
	public static final int PY_11XUAN5_REN7_DAN = TYPE_11XUAN5+18; //任选7
	public static final int PY_11XUAN5_QIAN2ZU_DAN = TYPE_11XUAN5+19; //前二组
	public static final int PY_11XUAN5_QIAN3ZU_DAN = TYPE_11XUAN5+20; //前三组
	private static String[] ARRAY_11XUAN5_NAME = new String[]{"前一","任选二","任选三",
		"任选四","任选五","任选六","任选七","任选八","前二直选","前二组选","前三直选","前三组选"};
	
	private static String[] ARRAY_11XUAN5_NAME_ALL = new String[]{"前一","任选二","任选三",
		"任选四","任选五","任选六","任选七","任选八","前二直选","前二组选","前三直选",
		"前三组选","任选二","任选三","任选四","任选五","任选六","任选七","前二组选","前三组选"};
	
	
	
	private static String[] ARRAY_11XUAN5_TYPE_NAME = new String[]{"选二","选三",
		"选四","选五","选六","选七","选八","选一","前二直选","前二组选","前三直选",
		"前三组选","选二","选三","选四","选五","选六","选七","前二组选","前三组选"};//用于提交到平台
	
	private static int[] ARRAY_11XUAN5_ID = new int[]{PY_11XUAN5_REN1, PY_11XUAN5_REN2,PY_11XUAN5_REN3,
		PY_11XUAN5_REN4,PY_11XUAN5_REN5,PY_11XUAN5_REN6,PY_11XUAN5_REN7,PY_11XUAN5_REN8,
		PY_11XUAN5_QIAN2ZHI,PY_11XUAN5_QIAN2ZU,PY_11XUAN5_QIAN3ZHI,PY_11XUAN5_QIAN3ZU};
	
	private static int[] ARRAY_11XUAN5_ID_ALL = new int[]{PY_11XUAN5_REN1,PY_11XUAN5_REN2,PY_11XUAN5_REN3,
		PY_11XUAN5_REN4,PY_11XUAN5_REN5,PY_11XUAN5_REN6,PY_11XUAN5_REN7,PY_11XUAN5_REN8,
		PY_11XUAN5_QIAN2ZHI,PY_11XUAN5_QIAN2ZU,PY_11XUAN5_QIAN3ZHI,PY_11XUAN5_QIAN3ZU,
		PY_11XUAN5_REN2_DAN,PY_11XUAN5_REN3_DAN,PY_11XUAN5_REN4_DAN,PY_11XUAN5_REN5_DAN,
		PY_11XUAN5_REN6_DAN,PY_11XUAN5_REN7_DAN,PY_11XUAN5_QIAN2ZU_DAN,PY_11XUAN5_QIAN3ZU_DAN};
	
	private static int[] ARRAY_11XUAN5_ID_DAN = new int[]{
		PY_11XUAN5_REN2_DAN,PY_11XUAN5_REN3_DAN,PY_11XUAN5_REN4_DAN,PY_11XUAN5_REN5_DAN,
		PY_11XUAN5_REN6_DAN,PY_11XUAN5_REN7_DAN,PY_11XUAN5_QIAN2ZU_DAN,PY_11XUAN5_QIAN3ZU_DAN};


	public static final int TYPE_XSSC = 8;//新时时彩
	public static final int PY_XSSC_1XFU = TYPE_XSSC+1; //
	public static final int PY_XSSC_2XFU = TYPE_XSSC+2; //
	public static final int PY_XSSC_2XZU = TYPE_XSSC+3; //
	public static final int PY_XSSC_3XFU = TYPE_XSSC+4; //
	public static final int PY_XSSC_3XZU3DAN = TYPE_XSSC+5; //
	public static final int PY_XSSC_3XZU3FU = TYPE_XSSC+6; //
	public static final int PY_XSSC_3XZU6 = TYPE_XSSC+7; //
	public static final int PY_XSSC_4XFU = TYPE_XSSC+8; //
	public static final int PY_XSSC_5XFU = TYPE_XSSC+9; //
	public static final int PY_XSSC_5XTONG = TYPE_XSSC+10; //
	public static final int PY_XSSC_DXDS = TYPE_XSSC+11; //
	private static String[] ARRAY_XSSC_NAME = new String[]{
		"一星复式","二星复式 ","二星组选","三星复式 ","三星组三单式",
		"三星组三复式 ","三星组六 ","四星复式 ","五星复式 ","五星通选 ","大小单双"};
	private static int[] ARRAY_XSSC_ID = new int[]{PY_XSSC_1XFU,PY_XSSC_2XFU,PY_XSSC_2XZU,
		PY_XSSC_3XFU,PY_XSSC_3XZU3DAN,PY_XSSC_3XZU3FU,PY_XSSC_3XZU6,PY_XSSC_4XFU,PY_XSSC_5XFU,
		PY_XSSC_5XTONG,PY_XSSC_DXDS};


	public static final int TYPE_JSSC = 9;// 旧时时彩
	public static final int PY_JSSC_1XFU = TYPE_JSSC + 1; //
	public static final int PY_JSSC_2XFU = TYPE_JSSC + 2; //
	public static final int PY_JSSC_2XZU = TYPE_JSSC + 3; //
	public static final int PY_JSSC_3XFU = TYPE_JSSC + 4; //
	// public static final int PY_JSSC_3XZU3DAN = TYPE_JSSC+5; //
	// public static final int PY_JSSC_3XZU3FU = TYPE_JSSC+6; //
	// public static final int PY_JSSC_3XZU6 = TYPE_JSSC+7; //
	public static final int PY_JSSC_5XFU = TYPE_JSSC + 5; //
	public static final int PY_JSSC_5XTONG = TYPE_JSSC + 6; //
	public static final int PY_JSSC_DXDS = TYPE_JSSC + 7; //
	// private static String[] ARRAY_JSSC_NAME = new String[]{
	// "一星复式","二星复式 ","二星组选","三星复式 ","三星组三单式",
	// "三星组三复式 ","三星组六 ","五星复式 ","五星通选 ","大小单双"};
	private static String[] ARRAY_JSSC_NAME = new String[] { "一星直选", "二星直选 ",
			"二星组选", "三星直选 ", "五星直选", "五星通选 ", "大小单双" };
	private static String[] ARRAY_JSSC_TYPE_NAME = new String[] { "一星直选",
			"二星直选", "二星组选", "三星直选", "五星直选", "五星通选", "大小单双" };// 用于提交到平台
	// private static int[] ARRAY_JSSC_ID = new
	// int[]{PY_JSSC_1XFU,PY_JSSC_2XFU,PY_JSSC_2XZU,
	// PY_JSSC_3XFU,PY_JSSC_3XZU3DAN,PY_JSSC_3XZU3FU,PY_JSSC_3XZU6,PY_JSSC_5XFU,
	// PY_JSSC_5XTONG,PY_JSSC_DXDS};
	private static int[] ARRAY_JSSC_ID = new int[] { PY_JSSC_1XFU,
			PY_JSSC_2XFU, PY_JSSC_2XZU, PY_JSSC_3XFU, PY_JSSC_5XFU,
			PY_JSSC_5XTONG, PY_JSSC_DXDS };


	public static final int TYPE_KL8 = 10;//快乐8
	public static final int PY_KL8_REN1 = TYPE_KL8+1; //任选1
	public static final int PY_KL8_REN2 = TYPE_KL8+2; //任选2
	public static final int PY_KL8_REN3 = TYPE_KL8+3; //任选3
	public static final int PY_KL8_REN4 = TYPE_KL8+4; //任选4
	public static final int PY_KL8_REN5 = TYPE_KL8+5; //任选5
	public static final int PY_KL8_REN6 = TYPE_KL8+6; //任选6
	public static final int PY_KL8_REN7 = TYPE_KL8+7; //任选7
	public static final int PY_KL8_REN8 = TYPE_KL8+8; //任选8
	public static final int PY_KL8_REN9 = TYPE_KL8+9; //任选8
	public static final int PY_KL8_REN10 = TYPE_KL8+10; //任选8
	private static String[] ARRAY_KL8_NAME = new String[]{"任选一","任选二","任选三",
		"任选四","任选五","任选六","任选七","任选八","任选九","任选十"};
	private static int[] ARRAY_KL8_ID = new int[]{PY_KL8_REN1,PY_KL8_REN2,PY_KL8_REN3,
		PY_KL8_REN4,PY_KL8_REN5,PY_KL8_REN6,PY_KL8_REN7,PY_KL8_REN8,PY_KL8_REN9,PY_KL8_REN10};

	
	public static final int TYPE_KLSF = 11;//快乐十分
	public static final int PY_X1ST = TYPE_KLSF+1;//选一数投
	public static final int PY_X1HT = TYPE_KLSF+2;//选一红投
	public static final int PY_X2RX = TYPE_KLSF+3;//选二任选
	public static final int PY_X3RX = TYPE_KLSF+4;//选三任选
	public static final int PY_X4RX = TYPE_KLSF+5;//选四任选
	public static final int PY_X5RX = TYPE_KLSF+6;//选五任选
	public static final int PY_L2LZU = TYPE_KLSF+7;//连二连组
	public static final int PY_L2LZHI = TYPE_KLSF+8;//连二连直
	private static String[] ARRAY_KLSF_NAME = new String[]{"选一数投","选一红投","选二任选","选三任选","选四任选","选五任选",
		"连二连组","连二连直"};
	private static int[] ARRAY_KLSF_ID = new int[]{PY_X1ST,PY_X1HT,PY_X2RX,PY_X3RX,PY_X4RX,PY_X5RX,PY_L2LZU,PY_L2LZHI};


	/********************************足彩*******************************************/

	public static final int TYPE_JCZQ = 50;// 竞彩足球
	public static final int PY_JCZQ_HT = TYPE_JCZQ + 1; // 混投 (目前是胜平负和让球胜平负)
//	public static final int PY_JCZQ_HT_SPF = TYPE_JCZQ + 2; // 混投 (目前是胜平负和让球胜平负)
	public static final int PY_JCZQ_SPF_FRQ = TYPE_JCZQ + 2; // 胜平负
	public static final int PY_JCZQ_SPF = TYPE_JCZQ + 3; // 让球胜平负
	public static final int PY_JCZQ_ZONG = TYPE_JCZQ + 4; // 总进球
	public static final int PY_JCZQ_BIFEN = TYPE_JCZQ + 5; // 猜比分
	public static final int PY_JCZQ_BQC = TYPE_JCZQ + 6; // 半全场

	public static String[] ARRAY_JCZQ_NAME = new String[] { "混投", "胜平负", "让球胜平负", "总进球数", "猜比分", "半全场", };
//	public static String[] ARRAY_JCZQ_NAME = new String[] { "混投", "胜平负混投"};
	public static String[] ARRAY_JCZQ_TYPE_NAME = new String[] { "HT", "FSPF", "SPF", "JQS", "BF", "BQQ", };// 用于提交到平台
//	public static String[] ARRAY_JCZQ_TYPE_NAME = new String[] { "HT", "SPFHT"};// 用于提交到平台
	private static int[] ARRAY_JCZQ_ID = new int[] { PY_JCZQ_HT, PY_JCZQ_SPF_FRQ, PY_JCZQ_SPF, PY_JCZQ_ZONG, PY_JCZQ_BIFEN, PY_JCZQ_BQC, };
//	private static int[] ARRAY_JCZQ_ID = new int[] { PY_JCZQ_HT, PY_JCZQ_HT_SPF };
	

	public static final int TYPE_ZQDC = 60;//足球单场
	public static final int PY_ZQDC_RQSPF = TYPE_ZQDC+1; //让球胜平负
	public static final int PY_ZQDC_SXDS = TYPE_ZQDC+2; //上下单双
	public static final int PY_ZQDC_ZJQ = TYPE_ZQDC+3; //总进球数
	public static final int PY_ZQDC_BQCSPF = TYPE_ZQDC+4; //半全场胜平负
	public static final int PY_ZQDC_BF = TYPE_ZQDC+5; //单场比分

	private static String[] ARRAY_ZQDC_NAME = new String[]{"让球胜平负","上下单双","总进球数","半全场胜平负","比分"};
	private static String[] ARRAY_ZQDC_TYPE_NAME = new String[]{"SPF","SXDS","ZJQS","BQQSPF","BF"};
	private static int[] ARRAY_ZQDC_ID = new int[]{PY_ZQDC_RQSPF,PY_ZQDC_SXDS,PY_ZQDC_ZJQ,PY_ZQDC_BQCSPF,PY_ZQDC_BF};

	public static final int TYPE_JCLQ = 13;//竞彩篮球
	public static final int PY_JCLQ_SF = TYPE_JCLQ+1; //胜负
	public static final int PY_JCLQ_RFSF = TYPE_JCLQ+2; //让分胜负
	public static final int PY_JCLQ_SFC = TYPE_JCLQ+3; //胜分差
	public static final int PY_JCLQ_DXF = TYPE_JCLQ+4; //大小分
	public static final int PY_JCLQ_HT = TYPE_JCLQ+5; //混投

	private static String[] ARRAY_JCLQ_NAME = new String[]{"胜负","让分胜负","胜分差","大小分","混投"};
//	private static String[] ARRAY_JCLQ_NAME = new String[]{"胜负","让分胜负","胜分差","大小分"};
	private static String[] ARRAY_JCLQ_TYPE_NAME = new String[]{"SF","RFSF","SFC","DXF","HT"};
	private static int[] ARRAY_JCLQ_ID = new int[]{PY_JCLQ_SF,PY_JCLQ_RFSF,PY_JCLQ_SFC,PY_JCLQ_DXF,PY_JCLQ_HT};
//	private static int[] ARRAY_JCLQ_ID = new int[]{PY_JCLQ_SF,PY_JCLQ_RFSF,PY_JCLQ_SFC,PY_JCLQ_DXF};

	public static final int TYPE_REN9 = 14;//任选9场
	public static final int PY_REN9_REN9 = TYPE_REN9+1; //任选9场
	private static String[] ARRAY_REN9_NAME = new String[]{"任选九场"};
	private static String[] ARRAY_REN9_TYPE_NAME = new String[]{"SFZC9"};
	private static int[] ARRAY_REN9_ID = new int[]{PY_REN9_REN9};

	public static final int TYPE_SFC = 15;//胜负彩
	public static final int PY_SFC_SFC = TYPE_SFC+1; //胜负彩
	private static String[] ARRAY_SFC_NAME = new String[]{"胜负彩14场"};
	private static String[] ARRAY_SFC_TYPE_NAME = new String[]{"SFZC14"};
	private static int[] ARRAY_SFC_ID = new int[]{PY_SFC_SFC};

	
    public static final int TYPE_K3 =16;//江苏快3
    /**********************普通投注**********************/
    public static final int PY_K3_HZ = TYPE_K3+1;//普通和值
    public static final int PY_K3_3TH = TYPE_K3+2;//三同号
    //public static final int PY_K3_2TH = TYPE_K3+3;//二同号
    public static final int PY_K3_2TH_DX = TYPE_K3+3;//二同号
    public static final int PY_K3_2TH_FX = TYPE_K3+4;//二同号
    public static final int PY_K3_3BTH = TYPE_K3+5;//三不同号
    public static final int PY_K3_2BTH = TYPE_K3+6;//二不同号
    public static final int PY_K3_3TH_TONG = TYPE_K3+10;//三同号通选
    public static final int PY_K3_3LH_TONG = TYPE_K3+11;//三连号通选
    /**********************胆拖投注**********************/
    public static final int PY_K3_3BTH_DAN = TYPE_K3+7;//三不同号
    public static final int PY_K3_2BTH_DAN = TYPE_K3+8;//二不同号
    private static String[] ARRAY_K3_NAME = new String[]{"和值","三同号","二同号单选","二同号复选","三不同号","二不同号"};
	private static int[] ARRAY_K3_ID = new int[]{PY_K3_HZ,PY_K3_3TH ,PY_K3_2TH_DX,PY_K3_2TH_FX,PY_K3_3BTH,PY_K3_2BTH};
	
	public static final int TYPE_22XUAN5 =17;//22选5
	
	public static final int TYPE_BJDC =18;//北京单场
	
	public static final int TYPE_LCZC =19;//
	
	public static final int TYPE_SCZC =20;//
	
	public static final int TYPE_GD11XUAN5 = 21;// 广东11选5
	public static final int TYPE_JX11XUAN5 = 22;// 江西11选5
	
	public static final int TYPE_6_BQC = 23; //6场半全场
	
	public static final int TYPE_4_JQC = 24; //4场进球彩
	
	/**
	 * 混投sp对应的玩法标示,查询对应sp时用
	 */
	public static final String SP_HT_SPF = "SPF", SP_HT_FSPF = "FSPF",
			SP_HT_ZJQ = "ZJQ", SP_HT_BQC = "BQC", SP_HT_BIFEN = "BIFEN";
	
	/**
	 *@author shouli.luo
	 *@time 2013-3-28 下午02:51:56
	 *@方法作用： 更具彩种，判断是否需要加0
	 */
	public static boolean getNeedAddZeroByType(int type){

		boolean addZero = false;

		switch (type) {
		case TYPE_SSQ:
		case TYPE_DLT: 
		case TYPE_11XUAN5:
		case TYPE_JX11XUAN5:
		case TYPE_GD11XUAN5:
		case TYPE_KL8:
		case TYPE_QLC:
		case TYPE_KLSF:	
			addZero = true;
			break;
		default:
			break;
		}
		return addZero;
	}


	/**
	 *@author shouli.luo
	 *@time 2013-4-7 下午04:31:42
	 *@方法作用： 根据彩种获取彩种名
	 * @param type
	 * @return
	 */
	public static String getLotteryNameByType(int type){
		
		String lotteryName = "";
		switch (type) {
		case TYPE_PL3:
			lotteryName = "排列3";
			break;
		case TYPE_SSQ:
			lotteryName = "双色球";
			break;
		case TYPE_DLT:
			lotteryName = "大乐透";
			break;
		case TYPE_PL5:
			lotteryName = "排列5";
			break;
		case TYPE_11XUAN5:
		case TYPE_JX11XUAN5:
		case TYPE_GD11XUAN5:	
			lotteryName = "11选5";
			break;
		case TYPE_3D:
			lotteryName = "3D";
			break;
		case TYPE_JSSC:
			lotteryName =  "时时彩";
			break;
		case TYPE_XSSC:
			lotteryName =  "新时时彩";
			break;
		case TYPE_KL8:
			lotteryName =  "快乐8";
			break;
		case TYPE_QLC:
			lotteryName =  "七乐彩";
			break;
		case TYPE_QXC:
			lotteryName =  "七星彩";
			break;
		case TYPE_JCZQ:
			lotteryName =  "竞彩足球";
			break;
		case TYPE_JCLQ:
			lotteryName =  "竞彩篮球";
			break;
		case TYPE_REN9:
			lotteryName =  "任选九场";
			break;
		case TYPE_SFC:
			lotteryName =  "胜负彩";
			break;
		case TYPE_ZQDC:
			lotteryName =  "北单";
			break;
		case TYPE_K3:
			lotteryName = "快3";
			break;
		case TYPE_KLSF:	
			lotteryName = "重庆快乐十分";
		default:
			break;
		}
		
		return lotteryName;
	}
	
	
	/**
	 *@author shouli.luo
	 *@time 2013-4-7 下午04:31:42
	 *@方法作用： 根据彩种获取英文名
	 * @param type
	 * @return
	 */
	public static String getTypeNameByType(int type){
		
		String lotteryName = "";
		switch (type) {
		case TYPE_PL3:
			lotteryName = "pl";
			break;
		case TYPE_SSQ:
			lotteryName = "ssq";
			break;
		case TYPE_DLT:
			lotteryName = "dlt";
			break;
		case TYPE_PL5:
			lotteryName = "pl";
			break;
		case TYPE_11XUAN5:
		case TYPE_JX11XUAN5:
		case TYPE_GD11XUAN5:
			lotteryName = "sdel11to5";
			break;
		case TYPE_3D:
			lotteryName = "welfare3d";
			break;
		case TYPE_JSSC:
			lotteryName =  "ssc";
			break;
		case TYPE_XSSC:
			lotteryName =  "ssc";
			break;
		case TYPE_KL8:
			lotteryName =  "kl8";
			break;
		case TYPE_QLC:
			lotteryName =  "qlc";
			break;
		case TYPE_QXC:
			lotteryName =  "qxc";
			break;
		case TYPE_JCZQ:
			lotteryName =  "竞彩足球";
			break;
		case TYPE_JCLQ:
			lotteryName =  "竞彩篮球";
			break;
		case TYPE_REN9:
			lotteryName =  "SFZC9";
			break;
		case TYPE_SFC:
			lotteryName =  "SFZC14";
			break;
		case TYPE_ZQDC:
			lotteryName =  "足球单场";
			break;
		case TYPE_K3:
			lotteryName = "快3";
			break;
		default:
			break;
		}
		
		return lotteryName;
	}
	
	/**
	 *@author shouli.luo
	 *@time 2013-3-28 下午02:51:56
	 *@方法作用： 根据彩种获取玩法
	 */
	public static int[] getPlayWayIdByType(int type){

		int[] playWays = null;

		switch (type) {
		case TYPE_PL3:
			playWays = ARRAY_PL3_ID;
			break;
		case TYPE_SSQ:
			playWays = ARRAY_SSQ_ID;
			break;
		case TYPE_DLT:
			playWays = ARRAY_DLT_ID;
			break;
		case TYPE_PL5:
			playWays = ARRAY_PL5_ID;
			break;
		case TYPE_11XUAN5:
		case TYPE_JX11XUAN5:
		case TYPE_GD11XUAN5:
			playWays = ARRAY_11XUAN5_ID;
			break;
		case TYPE_3D:
			playWays = ARRAY_3D_ID;
			break;
		case TYPE_JSSC:
			playWays = ARRAY_JSSC_ID;
			break;
		case TYPE_XSSC:
			playWays = ARRAY_XSSC_ID;
			break;
		case TYPE_KL8:
			playWays = ARRAY_KL8_ID;
			break;
		case TYPE_QLC:
			playWays = ARRAY_QLC_ID;
			break;
		case TYPE_QXC:
			playWays = ARRAY_QXC_ID;
			break;
		case TYPE_JCZQ:
			playWays = ARRAY_JCZQ_ID;
			break;
		case TYPE_JCLQ:
			playWays = ARRAY_JCLQ_ID;
			break;
		case TYPE_REN9:
			playWays = ARRAY_REN9_ID;
			break;
		case TYPE_SFC:
			playWays = ARRAY_SFC_ID;
			break;
		case TYPE_ZQDC:
			playWays = ARRAY_ZQDC_ID;
			break;
		case TYPE_K3:
			playWays = ARRAY_K3_ID;
			break;
		case TYPE_KLSF:	
			playWays = ARRAY_KLSF_ID;
			break;
		default:
			break;
		}
		return playWays;
	}


	/**
	 *@author shouli.luo
	 *@time 2013-3-29 上午10:54:56
	 *@方法作用： 获得玩法分割的位置，如普通投注 和 胆拖投注
	 * @param type
	 * @return 
	 */
	public static int getPlayWaySplitIndexByType(int type){

		int index = 0;

		switch (type) {
		case TYPE_11XUAN5:
		case TYPE_JX11XUAN5:
		case TYPE_GD11XUAN5:
			index = 12;
			break;
		case TYPE_K3:
			index = 6;
			break;
		default:
			break;
		}
		return index;
	}

	/**
	 *@author shouli.luo
	 *@time 2013-4-9 上午09:55:14
	 *@方法作用： 根据玩法获得位置
	 * @param playType
	 * @param playTypes
	 * @return
	 */
	public static int getLocationByPlayType(int playType,int[] playTypes){
		int index = 0;
		for(int i=0;i<playTypes.length;i++){
			if(playTypes[i] == playType){
				index =  i;
				break;
			}else{
			}
		}
		return index;
	}
	
	public static int getLocationByLotteryId(String id,String[] ids){
		int index = 0;
		for(int i=0;i<ids.length;i++){
			if(ids[i].equals(id)){
				index =  i;
				break;
			}else{
			}
		}
		return index;
	}
	
	
	
	/**
	 *@author shouli.luo
	 *@time 2013-3-28 下午02:51:56
	 *@方法作用： 根据彩种获取玩法
	 */
	public static String[] getPlayWayNameByType(int type){

		String[] playWays = null;

		switch (type) {
		case TYPE_PL3:
			playWays = ARRAY_PL3_NAME;
			break;
		case TYPE_SSQ: 
			playWays = ARRAY_SSQ_NAME;
			break;
		case TYPE_DLT: 
			playWays = ARRAY_DLT_NAME;
			break;
		case TYPE_PL5:
			playWays = ARRAY_PL5_NAME;
			break;
		case TYPE_11XUAN5:
		case TYPE_JX11XUAN5:
		case TYPE_GD11XUAN5:
			playWays = ARRAY_11XUAN5_NAME;
			break;
		case TYPE_3D:
			playWays = ARRAY_3D_NAME;
			break;
		case TYPE_JSSC:
			playWays = ARRAY_JSSC_NAME;
			break;
		case TYPE_XSSC:
			playWays = ARRAY_XSSC_NAME;
			break;
		case TYPE_KL8:
			playWays = ARRAY_KL8_NAME;
			break;
		case TYPE_QLC:
			playWays = ARRAY_QLC_NAME;
			break;
		case TYPE_QXC:
			playWays = ARRAY_QXC_NAME;
			break;
		case TYPE_JCZQ:
			playWays = ARRAY_JCZQ_NAME;
			break;
		case TYPE_JCLQ:
			playWays = ARRAY_JCLQ_NAME;
			break;
		case TYPE_REN9:
			playWays = ARRAY_REN9_NAME;
			break;
		case TYPE_SFC:
			playWays = ARRAY_SFC_NAME;
			break;
		case TYPE_ZQDC:
			playWays = ARRAY_ZQDC_NAME;
			break;
		case TYPE_K3:
			playWays = ARRAY_K3_NAME;
			break;
		case TYPE_KLSF:
			playWays = ARRAY_KLSF_NAME;
		default:
			break;
		}
		return playWays;
	}

	public static int getLotteryIdByLotteryType(int lotteryType) {
		int id = 0;
		switch (lotteryType) {
		case BetItem.TYPE_SSQ:
			id = 200;
			break;
		case BetItem.TYPE_DLT:
			id = 51;
			break;
		case BetItem.TYPE_GD11XUAN5:
			id = 100;
			break;
		case BetItem.TYPE_JX11XUAN5:
			id = 107;
			break;
		case BetItem.TYPE_3D:
			id = 201;
			break;
		case BetItem.TYPE_QLC:
			id = 202;
			break;
		case BetItem.TYPE_QXC:
			id = 52;
			break;
		case BetItem.TYPE_PL3:
			id = 54;
			break;
		case BetItem.TYPE_PL5:
			id = 53;
			break;
		case BetItem.TYPE_JSSC:
			id = 102;
			break;
		case BetItem.TYPE_KLSF:
			id = 114;
			break;
		case BetItem.TYPE_K3:
			id = 115;
			break;
		default:
			break;
		}
		return id;
	}
	
	
	public static String getStrLotteryIdByLotteryType(int lotteryType){
		String id = null;
			switch (lotteryType) {
			case BetItem.TYPE_JCZQ:
				id = "104,20,21,22,23,24,25,26";
//				id = "20,21,22,23,25";
				break;
			case BetItem.TYPE_JCLQ:
				id = "30,31,32,33,35";
				break;
			case BetItem.TYPE_ZQDC:
				id = "5,6,7,8,9";
				break;
			default:
				id = String.valueOf(getLotteryIdByLotteryType(lotteryType));
				break;
			}
		return id;	
	}
	
	public static int getLotteryTypeByLotteryId(String lotteryId){
		int lotteryType = 0;
		if("104,20,21,22,23,24,25,26".equals(lotteryId)){
			lotteryType = BetItem.TYPE_JCZQ;
		}else if("30,31,32,33,35".equals(lotteryId)){
			lotteryType = BetItem.TYPE_JCLQ;
		}else if("5,6,7,8,9".equals(lotteryId)){
			lotteryType = BetItem.TYPE_ZQDC;
		}else{
			lotteryType = getLotteryTypeByLotteryId(Integer.parseInt(lotteryId));
		}
		return lotteryType;
	}
	
	
	public static int getLotteryIdByPlayWayType(int playWayType) {
		int id = 0;
		switch (playWayType) {
		case BetItem.PY_JCZQ_SPF:
			id = 26;
			break;
		case BetItem.PY_JCZQ_BQC:
			id = 23;
			break;
		case BetItem.PY_JCZQ_ZONG:
			id = 22;
			break;
		case BetItem.PY_JCZQ_BIFEN:
			id = 21;
			break;
		case BetItem.PY_JCZQ_HT:
			id = 25;
			break;
		case BetItem.PY_JCZQ_SPF_FRQ:
			id = 20;
			break;
		case BetItem.PY_JCLQ_SF:
			id = 30;
			break;
		case BetItem.PY_JCLQ_RFSF:
			id = 31;
			break;
		case BetItem.PY_JCLQ_SFC:
			id = 32;
			break;
		case BetItem.PY_JCLQ_DXF:
			id = 33;
			break;
		case BetItem.PY_JCLQ_HT:
			id = 35;
			break;
		case BetItem.PY_ZQDC_RQSPF:
			id = 5;
			break;
		case BetItem.PY_ZQDC_BF:
			id = 9;
			break;
		case BetItem.PY_ZQDC_BQCSPF:
			id = 7;
			break;
		case BetItem.PY_ZQDC_ZJQ:
			id = 8;
			break;
		case BetItem.PY_ZQDC_SXDS:
			id = 6;
			break;
		}
		return id;
	}
	
	
	public static String getLotteryNameByPlayWayType(int playWayType){
		String name = null;
		switch (playWayType) {
		case BetItem.PY_JCZQ_HT:
			name = "竞彩足球混合过关";
			break;
		}
		return name;
	}
	
	/**
	 * 获取竞彩商品期号
	 * @param playWayType
	 * @return
	 */
	public static int getWareId(int playWayType) {
		int id = 0;
		switch (playWayType) {
		case BetItem.PY_JCZQ_SPF:
			id = 1;
			break;
		case BetItem.PY_JCZQ_BQC:
			id = 4;
			break;
		case BetItem.PY_JCZQ_ZONG:
			id = 3;
			break;
		case BetItem.PY_JCZQ_BIFEN:
			id = 2;
			break;
		case BetItem.PY_JCZQ_HT:
			id = 9223;
			break;
		case BetItem.PY_JCZQ_SPF_FRQ:
			id = 1;
			break;
		case BetItem.PY_JCLQ_SF:
			id = 182;
			break;
		case BetItem.PY_JCLQ_RFSF:
			id = 183;
			break;
		case BetItem.PY_JCLQ_SFC:
			id = 184;
			break;
		case BetItem.PY_JCLQ_DXF:
			id = 185;
			break;
		case BetItem.PY_JCLQ_HT:
			id = 9224;
			break;

		}
		return id;
	}
	
	public static int getLotteryTypeByLotteryId(int lotteryId){
		int lotteryType = 0;
		switch (lotteryId) {
		case 200:
			lotteryType = BetItem.TYPE_SSQ;
			break;
		case 51:
			lotteryType = BetItem.TYPE_DLT;
			break;
		case 100:
			lotteryType = BetItem.TYPE_GD11XUAN5;
			break;
		case 107:
			lotteryType = BetItem.TYPE_JX11XUAN5;
			break;
		case 201:
			lotteryType = TYPE_3D;
			break;
		case 202:
			lotteryType = BetItem.TYPE_QLC;
			break;
		case 52:
			lotteryType = BetItem.TYPE_QXC;
			break;
		case 54:
			lotteryType = BetItem.TYPE_PL3;
			break;
		case 53:
			lotteryType = BetItem.TYPE_PL5;
			break;
		case 102:
			lotteryType = BetItem.TYPE_JSSC;
			break;
		case 114:
			lotteryType = BetItem.TYPE_KLSF;
			break;
		case 115:
			lotteryType = BetItem.TYPE_K3;
			break;
		case 20:
		case 21:
		case 22:
		case 23:
		case 25:
		case 26:
			lotteryType = BetItem.TYPE_JCZQ;
			break;
		case 30:
		case 31:
		case 32:
		case 33:
		case 35:
			lotteryType = BetItem.TYPE_JCLQ;
			break;
		case 3:
		case 4:
		case 5:
		case 6:
		case 7:
		case 8:
		case 9:
			lotteryType = BetItem.TYPE_ZQDC;
			break;
		case 10:
			lotteryType = BetItem.TYPE_SFC;
			break;
		case 11:
			lotteryType = BetItem.TYPE_REN9;
			break;
		case 12:
			lotteryType = BetItem.TYPE_6_BQC;
			break;
		default:
			break;
		}
		return lotteryType;
	}
	
	public static int[] getDanPlayByPlayWayType(int lotteryType,int playWayType){
		int[] playWayIds = null;
		switch (lotteryType) {
		case TYPE_11XUAN5:
		case TYPE_JX11XUAN5:
		case TYPE_GD11XUAN5:
			switch (playWayType) {
			case PY_11XUAN5_REN2:
				playWayIds = new int[]{PY_11XUAN5_REN2,PY_11XUAN5_REN2_DAN};
				break;
			case PY_11XUAN5_REN3:
				playWayIds = new int[]{PY_11XUAN5_REN3,PY_11XUAN5_REN3_DAN};
				break;
			case PY_11XUAN5_REN4:
				playWayIds = new int[]{PY_11XUAN5_REN4,PY_11XUAN5_REN4_DAN};
				break;
			case PY_11XUAN5_REN5:
				playWayIds = new int[]{PY_11XUAN5_REN5,PY_11XUAN5_REN5_DAN};
				break;
			case PY_11XUAN5_REN6:
				playWayIds = new int[]{PY_11XUAN5_REN6,PY_11XUAN5_REN6_DAN};
				break;
			case PY_11XUAN5_REN7:
				playWayIds = new int[]{PY_11XUAN5_REN7,PY_11XUAN5_REN7_DAN};
				break;
			case PY_11XUAN5_QIAN2ZU:
				playWayIds = new int[]{PY_11XUAN5_QIAN2ZU,PY_11XUAN5_QIAN2ZU_DAN};
				break;
			case PY_11XUAN5_QIAN3ZU:	
				playWayIds = new int[]{PY_11XUAN5_QIAN3ZU,PY_11XUAN5_QIAN3ZU_DAN};
				break;
			}
			break;
		case TYPE_K3:
//			if(playWayType == PY_K3_3BTH || playWayType == PY_K3_3BTH_DAN ){
//				playWayIds = new int[]{PY_K3_3BTH,PY_K3_3BTH_DAN};
//			}else if(playWayType == PY_K3_2BTH || playWayType == PY_K3_2BTH_DAN){
//				playWayIds = new int[]{PY_K3_2BTH,PY_K3_2BTH_DAN};
//			}
			break;
		default:
			
			break;
		}
		return playWayIds;
	}
	
	/**
	 * 通过选项索引获取选项名字
	 * @return
	 */
	public static String getOptionNameByIndex(String index,int lotteryType){
		switch (lotteryType) {
		case BetItem.TYPE_JCZQ:
			return ARRAY_JCZQ_PLAY_WAY_NAMES[Integer.parseInt(index)];
		case BetItem.TYPE_JCLQ:
			return ARRAY_JCLQ_PLAY_WAY_NAMES[Integer.parseInt(index)];
		}
		return ARRAY_JCZQ_PLAY_WAY_NAMES[Integer.parseInt(index)];
	}
	
	public static String[] ARRAY_JCZQ_PLAY_WAY_NAMES = {"胜","平","负","让球胜","让球平","让球负"
														,"1:0","2:0","2:1","3:0","3:1","3:2"
														,"4:0","4:1","4:2","5:0","5:1","5:2"
														,"胜其他","0:0","1:1","2:2","3:3","平其他"
														,"0:1","0:2","1:2","0:3","1:3","2:3"
														,"0:4","1:4","2:4","0:5","1:5","2:5"
														,"负其他","0","1","2","3","4","5"
														,"6","7+","胜胜","胜平","胜负","平胜"
														,"平平","平负","负胜","负平","负负"};
	
	public static String[] ARRAY_JCLQ_PLAY_WAY_NAMES = {"主胜","主负","主胜","主负","大","小" ,
		"1-5","6-10","11-15","16-20","21-25","26+",
		"1-5","6-10","11-15","16-20","21-25","26+"};
	
	public static String getOptionNameByIndex(int index,int lotteryType){
		if(lotteryType == BetItem.TYPE_JCZQ){
			return ARRAY_JCZQ_PLAY_WAY_NAMES[index];
		}else if(lotteryType == BetItem.TYPE_JCLQ){
			return ARRAY_JCLQ_PLAY_WAY_NAMES[index];
		}
		return ARRAY_JCZQ_PLAY_WAY_NAMES[index];
	}
	
	public static String getPlayNameByIndex(String index){
		String playWay = null;
		int spIndex = Integer.parseInt(index);
		if(spIndex >= 0 && spIndex < 3){
			playWay = "SPF";
		}else if(spIndex >= 3 && spIndex < 6){
			playWay = "RSPF";
		}else if(spIndex >= 6 && spIndex < 37){
			playWay = "BF";
		}else if(spIndex >= 37 && spIndex < 45){
			playWay = "JQS";
		}else if(spIndex >= 45 && spIndex < 54){
			playWay = "BQC";
		}
		return playWay;
	}
	
	public static String getPlayNameByIndex(int index){
		String name = null;
		switch (index) {
		case 0:
		case 1:
		case 2:
			name = "SPF";
			break;
		case 3:
		case 4:
		case 5:
			name = "RSPF";
			break;
		}
		return name;
	}
	
	public static String[] getAllLotteryNames(){
		return new String[]{"双色球","大乐透","竞彩足球","竞彩篮球","北京单场","3D","七乐彩","排列3","排列5","七星彩"};
	}
	
	public static String[] getAllLotteryIds(){
		return new String[]{"200","51","104,20,21,22,23,24,25,26","30,31,32,33,35","5,6,7,8,9","201","202","54","53","52"};
	}
	
	
	private int mLotteryType,mPlayWayType;
	private String[] lefStrs = null;//左边的百位，十位，个位
	private int itemCount;//一共有多少个item
	private boolean ballColors[];//球的颜色
	private boolean canSelSameBall = true;//能否选择同号
	private boolean canRandomSel = true;//能否机选
	private SubBetItem[] subBetItem;//每个item的信息
	private String mPlayWayName = null;
	private String mPlayWayTypeName = null;//平台对应的玩法名

	private Context mContext;

	public BetItem(Context mContext,int lotteryType,int playWay){
		this.mContext = mContext;
		this.mLotteryType = lotteryType;
		this.mPlayWayType = playWay;
		initData();
	}

	public void setPlayWay(int playWay){
		canSelSameBall = true;
		canRandomSel = true;
		this.mPlayWayType = playWay;
		initData();
	}

	public int getPlayWayType(){
		return mPlayWayType;
	}

	public String getPlayWayName(){
		return mPlayWayName;
	}
	
	public String getPlayWayTypeName(){
		return mPlayWayTypeName;
	}

	public String[] getLeftStrs(){
		return lefStrs;
	}

	public int getItemCount(){
		return itemCount;
	}

	public boolean[] getBallColors(){
		return ballColors;
	}

	public SubBetItem[] getSubBetItem(){
		return subBetItem;
	}

	public boolean isCanSelSameBall(){
		return canSelSameBall;
	}

	public boolean isCanRandomSel(){
		return canRandomSel;
	}


	public  void initData(){

		switch (mLotteryType) {
		case TYPE_PL3:
			doPaiLei3();
			break;
		case TYPE_SSQ:
			doShuangSeQiu();
			break;
		case TYPE_DLT:
			doDaLeTou();
			break;
		case TYPE_PL5:
			doPaiLei5();
			break;
		case TYPE_11XUAN5:
		case TYPE_JX11XUAN5:
		case TYPE_GD11XUAN5:
			do11Xuan5();
			break;
		case TYPE_3D:
			do3D();
			break;
		case TYPE_JSSC:
			doJiuShiShiCai();
			break;
		case TYPE_XSSC:
			doXinShiShiCai();
			break;
		case TYPE_KL8:
			doKuaiLe8();
			break;
		case TYPE_QLC:
			doQiLeCai();
			break;
		case TYPE_QXC:
			doQiXingCai();
			break;
		case TYPE_K3:
			if(mPlayWayType == PY_K3_2BTH_DAN || mPlayWayType == PY_K3_3BTH_DAN || mPlayWayType == PY_K3_2TH_FX){
				canRandomSel = false;
			}
			mPlayWayName = ARRAY_K3_NAME[mPlayWayType-TYPE_K3-1];
			break;
		case TYPE_JCZQ:
			mPlayWayName = ARRAY_JCZQ_NAME[mPlayWayType-TYPE_JCZQ-1];
			mPlayWayTypeName = ARRAY_JCZQ_TYPE_NAME[mPlayWayType-TYPE_JCZQ-1];
			break;
		case TYPE_JCLQ:
			mPlayWayName = ARRAY_JCLQ_NAME[mPlayWayType-TYPE_JCLQ-1];
			mPlayWayTypeName = ARRAY_JCLQ_TYPE_NAME[mPlayWayType-TYPE_JCLQ-1];
			break;
		case TYPE_REN9:
			mPlayWayName = ARRAY_REN9_NAME[mPlayWayType-TYPE_REN9-1];
			mPlayWayTypeName = ARRAY_REN9_TYPE_NAME[mPlayWayType-TYPE_REN9-1];
			break;
		case TYPE_SFC:
			mPlayWayName = ARRAY_SFC_NAME[mPlayWayType-TYPE_SFC-1];
			mPlayWayTypeName = ARRAY_SFC_TYPE_NAME[mPlayWayType-TYPE_SFC-1];
			break;
		case TYPE_ZQDC:
			mPlayWayName = ARRAY_ZQDC_NAME[mPlayWayType-TYPE_ZQDC-1];
			mPlayWayTypeName = ARRAY_ZQDC_TYPE_NAME[mPlayWayType-TYPE_ZQDC-1];
			break;
		default:
			break;
		}
	}

	private void doQiXingCai(){
		mPlayWayName = ARRAY_QXC_NAME[mPlayWayType-TYPE_QXC-1];
		itemCount = 7;
		lefStrs = new String[]{"一","二","三","四","五","六","七"};
		ballColors = new boolean[]{true,true,true,true,true,true,true};

		subBetItem = new SubBetItem[itemCount];
		subBetItem[0] = new SubBetItem(true,true,"每位至少选择一个号码",10,1,-1,null);
		subBetItem[1] = new SubBetItem(false,false,10,1,-1,null);
		subBetItem[2] = new SubBetItem(false,false,10,1,-1,null);
		subBetItem[3] = new SubBetItem(false,false,10,1,-1,null);
		subBetItem[4] = new SubBetItem(false,false,10,1,-1,null);
		subBetItem[5] = new SubBetItem(false,false,10,1,-1,null);
		subBetItem[6] = new SubBetItem(false,false,10,1,-1,null);
	}

	private void doQiLeCai(){
		mPlayWayName = ARRAY_QLC_NAME[mPlayWayType-TYPE_QLC-1];
		switch (mPlayWayType) {
		case PY_QLC_PUTONG:
			itemCount = 1;
			lefStrs = null;
			ballColors = new boolean[]{true};
			subBetItem = new SubBetItem[1];
			subBetItem[0] = new SubBetItem(true,true,"至少选择7个号码",30,7,15,null);
			break;
		case PY_QLC_DANTUO:
			itemCount = 2;
			canSelSameBall = false;
			canRandomSel = false;
			lefStrs = null;
			ballColors = new boolean[]{true,true};
			subBetItem = new SubBetItem[itemCount];
			subBetItem[0] = new SubBetItem(true,false,"胆码区-红球，至少选择1个，最多6个",30,1,6,null);
			subBetItem[1] = new SubBetItem(true,false,"拖码区-红球，至少选择6个",30,6,-1,null);
			break;
		default:
			break;
		}
	}

	private void doKuaiLe8(){
		mPlayWayName = ARRAY_KL8_NAME[mPlayWayType-TYPE_KL8-1];
		itemCount = 1;
		lefStrs = null;
		ballColors = new boolean[]{true};
		subBetItem = new SubBetItem[1];

		//		switch (mPlayWay) {

		String[] str = new String[]{"12","12","12","12","12","12","12","12","12","12",
				"12","12","12","12","12","12","12","12","12","12",
				"12","12","12","12","12","12","12","12","12","12",
				"12","12","12","12","12","12","12","12","12","12",
				"12","12","12","12","12","12","12","12","12","12",
				"12","12","12","12","12","12","12","12","12","12",
				"12","12","12","12","12","12","12","12","12","12",
				"12","12","12","12","12","12","12","12","12","12"};
		switch (mPlayWayType) {
		case PY_KL8_REN1:
			subBetItem[0] = new SubBetItem(true,true,
					"至少选择"+(mPlayWayType-TYPE_KL8)+"个号码",80,mPlayWayType-TYPE_KL8,80,str);
			break;
		case PY_KL8_REN2:
			subBetItem[0] = new SubBetItem(true,true,
					"至少选择"+(mPlayWayType-TYPE_KL8)+"个号码",80,mPlayWayType-TYPE_KL8,32,str);
			break;
		case PY_KL8_REN3:
			subBetItem[0] = new SubBetItem(true,true,
					"至少选择"+(mPlayWayType-TYPE_KL8)+"个号码",80,mPlayWayType-TYPE_KL8,15,str);
			break;
		case PY_KL8_REN4:
			subBetItem[0] = new SubBetItem(true,true,
					"至少选择"+(mPlayWayType-TYPE_KL8)+"个号码",80,mPlayWayType-TYPE_KL8,12,str);
			break;
		case PY_KL8_REN5:
			subBetItem[0] = new SubBetItem(true,true,
					"至少选择"+(mPlayWayType-TYPE_KL8)+"个号码",80,mPlayWayType-TYPE_KL8,11,str);

		case PY_KL8_REN6:
			subBetItem[0] = new SubBetItem(true,true,
					"至少选择"+(mPlayWayType-TYPE_KL8)+"个号码",80,mPlayWayType-TYPE_KL8,11,str);
			break;
		case PY_KL8_REN7:
			subBetItem[0] = new SubBetItem(true,true,
					"至少选择"+(mPlayWayType-TYPE_KL8)+"个号码",80,mPlayWayType-TYPE_KL8,11,str);
			break;
		case PY_KL8_REN8:
			subBetItem[0] = new SubBetItem(true,true,
					"至少选择"+(mPlayWayType-TYPE_KL8)+"个号码",80,mPlayWayType-TYPE_KL8,12,str);
			break;
		case PY_KL8_REN9:
			subBetItem[0] = new SubBetItem(true,true,
					"至少选择"+(mPlayWayType-TYPE_KL8)+"个号码",80,mPlayWayType-TYPE_KL8,12,str);
			break;
		case PY_KL8_REN10:
			subBetItem[0] = new SubBetItem(true,true,
					"至少选择"+(mPlayWayType-TYPE_KL8)+"个号码",80,mPlayWayType-TYPE_KL8,13,str);		

			/*****************
			itemCount = 1;
			lefStrs = null;
			ballColors = new boolean[]{true};
			subBetItem = new SubBetItem[1];
			subBetItem[0] = new SubBetItem(true,true,"至少选择"+(mPlayWayType-TYPE_KL8)+"个号码",80,mPlayWayType-TYPE_KL8,-1);
			 */
			break;
		default:
			break;
		}
	}

	private void doXinShiShiCai(){
		mPlayWayName = ARRAY_XSSC_NAME[mPlayWayType-TYPE_XSSC-1];
		String[] str = new String[]{"12","12","12","12","12","12","12","12","12","12"};
		switch (mPlayWayType) {
		case PY_XSSC_1XFU:
			itemCount = 1;
			ballColors = new boolean[]{true};
			lefStrs = new String[]{"个位"};
			subBetItem = new SubBetItem[1];
			subBetItem[0] = new SubBetItem(true,true,R.string.at_lease_one_per_item,10,1,-1,str);
			break;
		case PY_XSSC_2XFU:
			itemCount = 2;
			ballColors = new boolean[]{true,true};
			lefStrs = new String[]{"十位","个位"};
			subBetItem = new SubBetItem[2];
			subBetItem[0] = new SubBetItem(true,true,R.string.at_lease_one_per_item,10,1,-1,str);
			subBetItem[1] = new SubBetItem(false,false,null,10,1,-1,str);
			break;
		case PY_XSSC_2XZU://
			itemCount = 1;
			ballColors = new boolean[]{true};
			lefStrs = new String[]{"选号"};
			subBetItem = new SubBetItem[1];
			subBetItem[0] = new SubBetItem(true,true,"每位至少选择2个号码",10,2,7,null);
			break;
		case PY_XSSC_3XFU:
			itemCount = 3;
			ballColors = new boolean[]{true,true,true};
			lefStrs = new String[]{"百位","十位","个位"};
			subBetItem = new SubBetItem[itemCount];
			subBetItem[0] = new SubBetItem(true,true,R.string.at_lease_one_per_item,10,1,-1,str);
			subBetItem[1] = new SubBetItem(false,false,null,10,1,-1,str);
			subBetItem[2] = new SubBetItem(false,false,null,10,1,-1,str);
			break;
		case PY_XSSC_4XFU:
			itemCount = 4;
			ballColors = new boolean[]{true,true,true,true};
			lefStrs = new String[]{"千位","百位","十位","个位"};
			subBetItem = new SubBetItem[itemCount];
			subBetItem[0] = new SubBetItem(true,true,R.string.at_lease_one_per_item,10,1,-1,str);
			subBetItem[1] = new SubBetItem(false,false,null,10,1,-1,str);
			subBetItem[2] = new SubBetItem(false,false,null,10,1,-1,str);
			subBetItem[3] = new SubBetItem(false,false,null,10,1,-1,str);
			break;
		case PY_XSSC_3XZU3DAN:
			itemCount = 2;
			canSelSameBall = false;
			ballColors = new boolean[]{true,true};
			lefStrs = new String[]{"重号","单号"};
			subBetItem = new SubBetItem[itemCount];
			subBetItem[0] = new SubBetItem(true,true,R.string.at_lease_one_per_item,10,1,1,null);
			subBetItem[1] = new SubBetItem(false,false,null,10,1,1,null);
			break;
		case PY_XSSC_3XZU3FU:
			itemCount = 1;
			ballColors = new boolean[]{true};
			lefStrs = new String[]{"选号"};
			subBetItem = new SubBetItem[1];
			subBetItem[0] = new SubBetItem(true,true,"每位至少选择2个号码",10,2,7,null);
			break;
		case PY_XSSC_3XZU6:
			itemCount = 1;
			ballColors = new boolean[]{true};
			lefStrs = new String[]{"选号"};
			subBetItem = new SubBetItem[1];
			subBetItem[0] = new SubBetItem(true,true,"每位至少选择3个号码",10,3,7,null);
			break;
		case PY_XSSC_5XFU:
			itemCount = 5;
			ballColors = new boolean[]{true,true,true,true,true};
			lefStrs = new String[]{"万位","千位","百位","十位","个位"};
			subBetItem = new SubBetItem[itemCount];
			subBetItem[0] = new SubBetItem(true,true,R.string.at_lease_one_per_item,10,1,-1,str);
			subBetItem[1] = new SubBetItem(false,false,null,10,1,-1,str);
			subBetItem[2] = new SubBetItem(false,false,null,10,1,-1,str);
			subBetItem[3] = new SubBetItem(false,false,null,10,1,-1,str);
			subBetItem[4] = new SubBetItem(false,false,null,10,1,-1,str);
			break;
		case PY_XSSC_5XTONG:
			itemCount = 5;
			ballColors = new boolean[]{true,true,true,true,true};
			lefStrs = new String[]{"万位","千位","百位","十位","个位"};
			subBetItem = new SubBetItem[itemCount];
			subBetItem[0] = new SubBetItem(true,true,R.string.at_lease_one_per_item,10,1,-1,str);
			subBetItem[1] = new SubBetItem(false,false,null,10,1,-1,str);
			subBetItem[2] = new SubBetItem(false,false,null,10,1,-1,str);
			subBetItem[3] = new SubBetItem(false,false,null,10,1,-1,str);
			subBetItem[4] = new SubBetItem(false,false,null,10,1,-1,str);
			break;
		case PY_XSSC_DXDS:
			itemCount = 2;
			ballColors = new boolean[]{true,true};
			lefStrs = new String[]{"十位","个位"};
			subBetItem = new SubBetItem[itemCount];
			subBetItem[0] = new SubBetItem(true,true,"每位至少选择3个号码",new String[]{"大","小","单","双"},1,1,null);
			subBetItem[1] = new SubBetItem(false,false,null,new String[]{"大","小","单","双"},1,1,null);
			break;
		default:
			break;
		}
	}

	private void doJiuShiShiCai(){
		mPlayWayName = ARRAY_JSSC_NAME[mPlayWayType-TYPE_JSSC-1];
		mPlayWayTypeName = ARRAY_JSSC_TYPE_NAME[mPlayWayType-TYPE_JSSC-1];

		String[] str = null;
		switch (mPlayWayType) {
		case PY_JSSC_1XFU:
			itemCount = 1;
			ballColors = new boolean[]{true};
			lefStrs = new String[]{"个位"};
			subBetItem = new SubBetItem[1];
			subBetItem[0] = new SubBetItem(true,true,R.string.at_lease_one_per_item,10,1,-1,str);
			break;
		case PY_JSSC_2XFU:
			itemCount = 2;
			ballColors = new boolean[]{true,true};
			lefStrs = new String[]{"十位","个位"};
			subBetItem = new SubBetItem[2];
			subBetItem[0] = new SubBetItem(true,true,R.string.at_lease_one_per_item,10,1,-1,str);
			subBetItem[1] = new SubBetItem(false,false,null,10,1,-1,str);
			break;
		case PY_JSSC_2XZU:
			itemCount = 1;
			ballColors = new boolean[]{true};
			lefStrs = new String[]{"选号"};
			subBetItem = new SubBetItem[1];
			subBetItem[0] = new SubBetItem(true,true,"每位至少选择2个号码",10,2,10,null);
			break;
		case PY_JSSC_3XFU:
			itemCount = 3;
			ballColors = new boolean[]{true,true,true};
			lefStrs = new String[]{"百位","十位","个位"};
			subBetItem = new SubBetItem[itemCount];
			subBetItem[0] = new SubBetItem(true,true,R.string.at_lease_one_per_item,10,1,-1,str);
			subBetItem[1] = new SubBetItem(false,false,null,10,1,-1,str);
			subBetItem[2] = new SubBetItem(false,false,null,10,1,-1,str);
			break;
//		case PY_JSSC_3XZU3DAN:
//			itemCount = 2;
//			ballColors = new boolean[]{true,true};
//			lefStrs = new String[]{"重号","单号"};
//			subBetItem = new SubBetItem[itemCount];
//			subBetItem[0] = new SubBetItem(true,true,R.string.at_lease_one_per_item,10,1,1,null);
//			subBetItem[1] = new SubBetItem(false,false,null,10,1,1,null);
//			break;
//		case PY_JSSC_3XZU3FU:
//			itemCount = 1;
//			ballColors = new boolean[]{true};
//			lefStrs = new String[]{"选号"};
//			subBetItem = new SubBetItem[1];
//			subBetItem[0] = new SubBetItem(true,true,"每位至少选择2个号码",10,2,7,null);
//			break;
//		case PY_JSSC_3XZU6:
//			itemCount = 1;
//			ballColors = new boolean[]{true};
//			lefStrs = new String[]{"选号"};
//			subBetItem = new SubBetItem[1];
//			subBetItem[0] = new SubBetItem(true,true,"每位至少选择3个号码",10,3,7,null);
//			break;
		case PY_JSSC_5XFU:
			itemCount = 5;
			ballColors = new boolean[]{true,true,true,true,true};
			lefStrs = new String[]{"万位","千位","百位","十位","个位"};
			subBetItem = new SubBetItem[itemCount];
			subBetItem[0] = new SubBetItem(true,true,R.string.at_lease_one_per_item,10,1,-1,str);
			subBetItem[1] = new SubBetItem(false,false,null,10,1,-1,str);
			subBetItem[2] = new SubBetItem(false,false,null,10,1,-1,str);
			subBetItem[3] = new SubBetItem(false,false,null,10,1,-1,str);
			subBetItem[4] = new SubBetItem(false,false,null,10,1,-1,str);
			break;
		case PY_JSSC_5XTONG:
			itemCount = 5;
			ballColors = new boolean[]{true,true,true,true,true};
			lefStrs = new String[]{"万位","千位","百位","十位","个位"};
			subBetItem = new SubBetItem[itemCount];
			subBetItem[0] = new SubBetItem(true,true,R.string.at_lease_one_per_item,10,1,-1,str);
			subBetItem[1] = new SubBetItem(false,false,null,10,1,-1,str);
			subBetItem[2] = new SubBetItem(false,false,null,10,1,-1,str);
			subBetItem[3] = new SubBetItem(false,false,null,10,1,-1,str);
			subBetItem[4] = new SubBetItem(false,false,null,10,1,-1,str);
			break;
		case PY_JSSC_DXDS:
			itemCount = 2;
			ballColors = new boolean[]{true,true};
			lefStrs = new String[]{"十位","个位"};
			subBetItem = new SubBetItem[itemCount];
			subBetItem[0] = new SubBetItem(true,true,"每位选择1个号码",new String[]{"大","小","单","双"},1,1,null);
			subBetItem[1] = new SubBetItem(false,false,null,new String[]{"大","小","单","双"},1,1,null);
			break;
		default:
			break;
		}
	}

	private void doPaiLei5(){
		mPlayWayName = ARRAY_PL5_NAME[mPlayWayType-TYPE_PL5-1];
		mPlayWayTypeName = ARRAY_PL5_TYPE_NAME[mPlayWayType-TYPE_PL5-1];
		itemCount = 5;
		lefStrs = new String[]{"万位","千位","百位","十位","个位"};
		ballColors = new boolean[]{true,true,true,true,true};
		subBetItem = new SubBetItem[5];
		subBetItem[0] = new SubBetItem(true,true,"每位至少选择一个号码",10,1,-1,null);
		subBetItem[1] = new SubBetItem(false,false,10,1,-1,null);
		subBetItem[2] = new SubBetItem(false,false,10,1,-1,null);
		subBetItem[3] = new SubBetItem(false,false,10,1,-1,null);
		subBetItem[4] = new SubBetItem(false,false,10,1,-1,null);
	}

	private void doPaiLei3(){
		mPlayWayName = ARRAY_PL3_NAME[mPlayWayType-TYPE_PL3-1];
		mPlayWayTypeName = ARRAY_PL3_TYPE_NAME[mPlayWayType-TYPE_PL3-1];
		switch (mPlayWayType) {
		case PY_PL3_ZHIXUAN:
			itemCount = 3;
			ballColors = new boolean[]{true,true,true};
			lefStrs = new String[]{"百位","十位","个位"};
			subBetItem = new SubBetItem[3];
			subBetItem[0] = new SubBetItem(true,true,R.string.at_lease_one_per_item,10,1,-1,null);
			subBetItem[1] = new SubBetItem(false,false,10,1,-1,null);
			subBetItem[2] = new SubBetItem(false,false,10,1,-1,null);
			break;
		case PY_PL3_ZULIU:
			itemCount = 1;
			lefStrs = null;
			ballColors = new boolean[]{true};
			subBetItem = new SubBetItem[1];
			subBetItem[0] = new SubBetItem(true,true,"至少选择3个号码",10,3,-1,null);
			break;
//		case PY_PL3_ZUSANDANSHI:
//			itemCount = 2;
//			canSelSameBall = false;
//			lefStrs = new String[]{"重号","单号"};
//			ballColors = new boolean[]{true,true};
//			subBetItem = new SubBetItem[2];
//			subBetItem[0] = new SubBetItem(true,true,R.string.at_lease_one_per_item,10,1,1,null);
//			subBetItem[1] = new SubBetItem(false,false,null,10,1,1,null);
//			break;
		case PY_PL3_ZUSANFUSHI:
			itemCount = 1;
			lefStrs = null;
			subBetItem = new SubBetItem[1];
			ballColors = new boolean[]{true};
			subBetItem[0] = new SubBetItem(true,true,"每位至少选择2个号码",10,2,-1,null);
			break;
		default:
			break;
		}
	}

	private void do3D(){
		mPlayWayName = ARRAY_3D_NAME[mPlayWayType-TYPE_3D-1];
		mPlayWayTypeName = ARRAY_3D_TYPE_NAME[mPlayWayType-TYPE_3D-1];
		switch (mPlayWayType) {
		case PY_3D_ZHIXUAN:
			itemCount = 3;
			ballColors = new boolean[]{true,true,true};
			lefStrs = new String[]{"百位","十位","个位"};
			subBetItem = new SubBetItem[3];
			subBetItem[0] = new SubBetItem(true,true,R.string.at_lease_one_per_item,10,1,-1,null);
			subBetItem[1] = new SubBetItem(false,false,null,10,1,-1,null);
			subBetItem[2] = new SubBetItem(false,false,null,10,1,-1,null);
			break;
		case PY_3D_ZULIU:
			itemCount = 1;
			lefStrs = null;
			ballColors = new boolean[]{true};
			subBetItem = new SubBetItem[1];
			subBetItem[0] = new SubBetItem(true,true,"至少选择3个号码",10,3,-1,null);
			break;
//		case PY_3D_ZUSANDANSHI:
//			itemCount = 2;
//			canSelSameBall = false;
//			lefStrs = new String[]{"重号","单号"};
//			ballColors = new boolean[]{true,true};
//			subBetItem = new SubBetItem[2];
//			subBetItem[0] = new SubBetItem(true,true,R.string.at_lease_one_per_item,10,1,1,null);
//			subBetItem[1] = new SubBetItem(false,false,null,10,1,1,null);
//			break;
		case PY_3D_ZUSANFUSHI:
			itemCount = 1;
			lefStrs = null;
			subBetItem = new SubBetItem[1];
			ballColors = new boolean[]{true};
			subBetItem[0] = new SubBetItem(true,true,"每位至少选择2个号码",10,2,-1,null);
			break;
		default:
			break;
		}
	}

	private void do11Xuan5(){
		mPlayWayName = ARRAY_11XUAN5_NAME_ALL[mPlayWayType-TYPE_11XUAN5-1];
//		mPlayWayTypeName = ARRAY_11XUAN5_TYPE_NAME[mPlayWayType-TYPE_11XUAN5-1];

//		String[] str = new String[]{"12","12","12","12","12","12","12","12","12","12","12"};
		String[] str = null;
		switch (mPlayWayType) {
		case PY_11XUAN5_REN2:
		case PY_11XUAN5_REN3:
		case PY_11XUAN5_REN4:
		case PY_11XUAN5_REN5:
		case PY_11XUAN5_REN6:
		case PY_11XUAN5_REN7:
		case PY_11XUAN5_REN8:
			itemCount = 1;
			lefStrs = new String[]{"选号"};
			ballColors = new boolean[]{true};
			subBetItem = new SubBetItem[1];
			subBetItem[0] = new SubBetItem(true,true,"至少选择"+(mPlayWayType-TYPE_11XUAN5)+"个球",
					11,mPlayWayType-TYPE_11XUAN5,-1,str);
			break;
		case PY_11XUAN5_REN2_DAN:
		case PY_11XUAN5_REN3_DAN:
		case PY_11XUAN5_REN4_DAN:
		case PY_11XUAN5_REN5_DAN:
		case PY_11XUAN5_REN6_DAN:
		case PY_11XUAN5_REN7_DAN:
			itemCount = 2;
			canSelSameBall = false;
			canRandomSel = false;
			lefStrs = new String[]{"胆码","拖码"};
			ballColors = new boolean[]{true,true};
			subBetItem = new SubBetItem[2];
			subBetItem[0] = new SubBetItem(true,true,"至少选择1个,最多选"+(mPlayWayType-PY_11XUAN5_REN2_DAN + 1)+"个",11,1,mPlayWayType-TYPE_11XUAN5-12,str);
			subBetItem[1] = new SubBetItem(false,false,null,11,1,10,str);
			break;
		case PY_11XUAN5_QIAN2ZU_DAN:
			itemCount = 2;
			canSelSameBall = false;
			canRandomSel = false;
			lefStrs = new String[]{"胆码","拖码"};
			ballColors = new boolean[]{true,true};
			subBetItem = new SubBetItem[2];
			subBetItem[0] = new SubBetItem(true,true,"请选择1个胆码",11,1,1,str);
			subBetItem[1] = new SubBetItem(false,false,null,11,1,10,str);
			break;
		case PY_11XUAN5_QIAN3ZU_DAN:
			itemCount = 2;
			canSelSameBall = false;
			canRandomSel = false;
			lefStrs = new String[]{"胆码","拖码"};
			ballColors = new boolean[]{true,true};
			subBetItem = new SubBetItem[2];
			subBetItem[0] = new SubBetItem(true,true,"请选择1~2个胆码",11,1,2,str);
			subBetItem[1] = new SubBetItem(false,false,null,11,1,10,str);
			break;
		case PY_11XUAN5_REN1:
			itemCount = 1;
			lefStrs = new String[]{"选号"};
			ballColors = new boolean[]{true};
			subBetItem = new SubBetItem[1];
			subBetItem[0] = new SubBetItem(true,true,"至少选择1个号码",11,1,-1,str);
			break;
		case PY_11XUAN5_QIAN2ZHI:
			itemCount = 2;
			canSelSameBall = false;
			lefStrs = new String[]{"万位","千位"};
			ballColors = new boolean[]{true,true};
			subBetItem = new SubBetItem[2];
			subBetItem[0] = new SubBetItem(true,true,"每位至少选择一个号码",11,1,-1,str);
			subBetItem[1] = new SubBetItem(false,false,null,11,1,-1,str);
			break;
		case PY_11XUAN5_QIAN2ZU:
			itemCount = 1;
			lefStrs = new String[]{"选号"};
			ballColors = new boolean[]{true};
			subBetItem = new SubBetItem[1];
			subBetItem[0] = new SubBetItem(true,true,"至少选择2个号码",11,2,-1,str);
			break;
		case PY_11XUAN5_QIAN3ZHI:
			itemCount = 3;
			canSelSameBall = false;
			lefStrs = new String[]{"万位","千位","百位"};
			ballColors = new boolean[]{true,true,true};
			subBetItem = new SubBetItem[3];
			subBetItem[0] = new SubBetItem(true,true,"每位至少选择一个号码",11,1,-1,str);
			subBetItem[1] = new SubBetItem(false,false,null,11,1,-1,str);
			subBetItem[2] = new SubBetItem(false,false,null,11,1,-1,str);
			break;
		case PY_11XUAN5_QIAN3ZU:
			itemCount = 1;
			lefStrs = new String[]{"选号"};
			ballColors = new boolean[]{true};
			subBetItem = new SubBetItem[1];
			subBetItem[0] = new SubBetItem(true,true,"至少选择3个号码",11,3,-1,str);
			break;
		default:
			break;
		}
	}

	private void doShuangSeQiu(){
		mPlayWayName = ARRAY_SSQ_NAME[mPlayWayType-TYPE_SSQ-1];
		switch (mPlayWayType) {
		case PY_SSQ_PUTONG:
			itemCount = 2;
			lefStrs = null;
			ballColors = new boolean[]{true,false};
			subBetItem = new SubBetItem[2];
			subBetItem[0] = new SubBetItem(true,true,"请选择6-21个红球 ",33,6,16,null);
			subBetItem[1] = new SubBetItem(true,false,"请选择1-16个篮球",16,1,-1,null);
			break;
		case PY_SSQ_DANTUO:
			itemCount = 3;
			lefStrs = null;
			canRandomSel = false;
			canSelSameBall = false;
			ballColors = new boolean[]{true,true,false};
			subBetItem = new SubBetItem[3];
			subBetItem[0] = new SubBetItem(true,false,"胆码区-红球，至少选择1个，最多5个",33,1,5,null);
			subBetItem[1] = new SubBetItem(true,false,"拖码区-红球，至少选择2个",33,2,-1,null);
			subBetItem[2] = new SubBetItem(true,false,"拖码区-蓝球，至少选择1个",16,1,-1,null);
			break;
		default:
			break;
		}
	}

	private void doDaLeTou(){
		mPlayWayName = ARRAY_DLT_NAME[mPlayWayType-TYPE_DLT-1];
		switch (mPlayWayType) {
		case PY_DLT_PUTONG:
			itemCount = 2;
			lefStrs = null;
			ballColors = new boolean[]{true,false};
			subBetItem = new SubBetItem[2];
			subBetItem[0] = new SubBetItem(true,true,"前区至少选5个球，后区至少选2个球",35,5,16,null);
			subBetItem[1] = new SubBetItem(false,false,null,12,2,-1,null);
			break;
		case PY_DLT_DANTUO:
			itemCount = 4;
			lefStrs = null;
			canRandomSel = false;
			canSelSameBall = false;
			ballColors = new boolean[]{true,true,false,false};
			subBetItem = new SubBetItem[4];
			subBetItem[0] = new SubBetItem(true,false,"胆码区-前区，至少选择1个，最多4个",35,1,4,null);
			subBetItem[1] = new SubBetItem(true,false,"拖码区-前区，至多选择16个",35,2,16,null);
			subBetItem[2] = new SubBetItem(true,false,"胆码区-后区，最多选择1个",12,0,1,null);
			subBetItem[3] = new SubBetItem(true,false,"拖码区-后区，至少选择2个",12,2,-1,null);
			break;
		default:
			break;
		}
	}


	public class SubBetItem{

		private boolean isLeftTvShow,isShapeTvShow;//左边的“每位至少选择一个号码”和右边的“摇一摇机选”textview是否显示
		private String leftStr;//左边的字符串
		private String[] ballStrs = null;//球的字符串
		private int ballCount;//球的个数
		private int atLeastBallCount = -1;//至少选择多少个球
		private int atMostBallCount = -1;//至多选择多少个球
		private String[] rateStrs = null;//球下方的概率数字

		public SubBetItem(boolean isLeftTvShow,boolean isShapeTvShow,String leftStr,int ballCount,
				int atLeastCount,int atMostCount,String[] rateStrs){
			this.isLeftTvShow = isLeftTvShow;
			this.isShapeTvShow = isShapeTvShow;
			this.leftStr = leftStr;
			this.ballCount = ballCount;
			this.atLeastBallCount = atLeastCount;
			this.atMostBallCount = atMostCount;
			this.rateStrs = rateStrs;
		}

		public SubBetItem(boolean isLeftTvShow,boolean isShapeTvShow,int leftStr,int ballCount,
				int atLeastCount,int atMostCount,String[] rateStrs){
			this.isLeftTvShow = isLeftTvShow;
			this.isShapeTvShow = isShapeTvShow;
			this.leftStr = mContext.getString(leftStr);
			this.ballCount = ballCount;
			this.atLeastBallCount = atLeastCount;
			this.atMostBallCount = atMostCount;
			this.rateStrs = rateStrs;
		}

		public SubBetItem(boolean isLeftTvShow,boolean isShapeTvShow,int ballCount,
				int atLeastCount,int atMostCount,String[] rateStrs){
			this.isLeftTvShow = isLeftTvShow;
			this.isShapeTvShow = isShapeTvShow;
			this.ballCount = ballCount;
			this.atLeastBallCount = atLeastCount;
			this.atMostBallCount = atMostCount;
			this.rateStrs = rateStrs;
		}

		public SubBetItem(boolean isLeftTvShow,boolean isShapeTvShow,String leftStr,String[] ballStrs,
				int atLeastCount,int atMostCount,String[] rateStrs){
			this.isLeftTvShow = isLeftTvShow;
			this.isShapeTvShow = isShapeTvShow;
			this.leftStr = leftStr;
			this.ballStrs = ballStrs;
			this.atLeastBallCount = atLeastCount;
			this.atMostBallCount = atMostCount;
			this.rateStrs = rateStrs;
		}

		public SubBetItem(boolean isLeftTvShow,boolean isShapeTvShow,int leftStr,String[] ballStrs,
				int atLeastCount,int atMostCount,String[] rateStrs){
			this.isLeftTvShow = isLeftTvShow;
			this.isShapeTvShow = isShapeTvShow;
			this.leftStr = mContext.getString(leftStr);
			this.ballStrs = ballStrs;
			this.atLeastBallCount = atLeastCount;
			this.atMostBallCount = atMostCount;
			this.rateStrs = rateStrs;
		}

		public boolean getisLeftTvShow(){
			return isLeftTvShow;
		}

		public boolean getisShapeTvShow(){
			return isShapeTvShow;
		}

		public String getHintStr(){
			return leftStr;
		}

		public int getBallCount(){
			if(ballStrs != null){
				return ballStrs.length;
			}
			return ballCount;
		}

		public String[] getBallStrs(){
			return ballStrs;
		}

		public String[] getRateStrs(){
			return rateStrs;
		}

		public int getAtLeastSelCount(){
			return atLeastBallCount;
		}

		public int getAtMostSelCount(){
			return atMostBallCount;
		}

	}

}
