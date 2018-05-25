package com.example.lijuw.myapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 *@author shouli.luo
 *@time 2013-4-2 下午04:56:28
 *@introduce 数据库帮助类， 主要用于保存投注的号码
 */
public class DatabaseHelper extends SQLiteOpenHelper {

	
	/**
	 * 数据库版本
	 */
	private static final int VERSION = 2; 
	/**
	 * 数据库名称
	 */
//	private static final String NAME = "/sdcard/caipiao/caipiao.db";
	private static final String NAME = "caipiao_1.db";
	
	/**
	 * 上下文
	 */
	private Context mContext;
	
	private static DatabaseHelper mInstance;
	
	
	public DatabaseHelper(Context context) {
		super(context, NAME, null, VERSION);
		mContext = context;
	}
	
	synchronized public static DatabaseHelper getInstance(Context context) {
		if (mInstance == null) {
			mInstance = new DatabaseHelper(context);
		}
		return mInstance;
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		
		StringBuilder buf = new StringBuilder();
		buf.append("CREATE TABLE ");
		buf.append(Betting.TABLE_BALL_NAME);
		buf.append(" (");
		buf.append(Betting._ID);
		buf.append(" INTEGER PRIMARY KEY AUTOINCREMENT,");
		buf.append(Betting.PLAYWAY_TYPE);
		buf.append(" INTEGER,");
		buf.append(Betting.PLAYWAY_NAME);
		buf.append(" TEXT,");
		buf.append(Betting.SAVENUM);
		buf.append(" TEXT,");
		buf.append(Betting.LOTTERY_TYPE);
		buf.append(" INTEGER,");
		buf.append(Betting.LOTTERY_NAME);
		buf.append(" TEXT,");
		buf.append(Betting.BETTINGNUM);
		buf.append(" TEXT NOT NULL,");
		buf.append(Betting.BETTINGCOUNT);
		buf.append(" INTEGER");
		buf.append(");");
		db.execSQL(buf.toString());
		
		
		buf = new StringBuilder();
		buf.append("CREATE TABLE ");
		buf.append(ZC_Betting.TABLE_ZC_NAME);
		buf.append(" (");
		buf.append(ZC_Betting._ID);
		buf.append(" INTEGER PRIMARY KEY AUTOINCREMENT,");
		buf.append(ZC_Betting.PLAYWAY_TYPE);
		buf.append(" INTEGER,");
		buf.append(ZC_Betting.PLAYWAY_NAME);
		buf.append(" TEXT,");
		buf.append(ZC_Betting.LOTTERY_NAME);
		buf.append(" TEXT,");
		buf.append(ZC_Betting.LOTTERY_TYPE);
		buf.append(" INTEGER,");
		buf.append(ZC_Betting.MARCH_NAME);
		buf.append(" TEXT,");
		buf.append(ZC_Betting.GUEST_NAME);
		buf.append(" TEXT,");
		buf.append(ZC_Betting.OPEN_FLAG);
		buf.append(" INTEGER,");
		buf.append(ZC_Betting.HOST_NAME);
		buf.append(" TEXT,");
		buf.append(ZC_Betting.HANDICAP);
		buf.append(" TEXT,");
		buf.append(ZC_Betting.DAN);
		buf.append(" TEXT,");
		buf.append(ZC_Betting.CUAN);
		buf.append(" TEXT,");
		buf.append(ZC_Betting.SP);
		buf.append(" TEXT,");
		buf.append(ZC_Betting.MATCH_KEY);
		buf.append(" TEXT,");
		buf.append(ZC_Betting.BETTING_MATCH);
		buf.append(" TEXT,");
		buf.append(ZC_Betting.PLAYWAY_TYPENAME);
		buf.append(" TEXT,");
		buf.append(ZC_Betting.BETTING_MATCH_INDEX);
		buf.append(" TEXT,");
		buf.append(ZC_Betting.GROUP_POSITION);
		buf.append(" INTEGER,");
		buf.append(ZC_Betting.CHILD_POSITION);
		buf.append(" INTEGER,");
		buf.append(ZC_Betting.MATCH_KEY_TEXT);
		buf.append(" TEXT,");
		buf.append(ZC_Betting.MATCH_ID);
		buf.append(" TEXT,");
		buf.append(ZC_Betting.DANGUAN);
		buf.append(" TEXT,");
		buf.append(ZC_Betting.SPF_DANGUAN);
		buf.append(" TEXT");
		buf.append(");");
		db.execSQL(buf.toString());
		
		buf = new StringBuilder();
		buf.append("CREATE TABLE ");
		buf.append(Lottery_Category.TABLE_NAME);
		buf.append(" (");
		buf.append(Lottery_Category._ID);
		buf.append(" INTEGER PRIMARY KEY AUTOINCREMENT,");
		buf.append(Lottery_Category.LOTTERY_TYPE);
		buf.append(" INTEGER,");
		buf.append(Lottery_Category.LOTTERY_NAME);
		buf.append(" TEXT,");
		buf.append(Lottery_Category.ORDER_INDEX);
		buf.append(" INTEGER,");
		buf.append(Lottery_Category.HOT);
		buf.append(" INTEGER,");
		buf.append(Lottery_Category.INDEX_TYPE);
		buf.append(" INTEGER");
		buf.append(");");
		db.execSQL(buf.toString());
		
		buf = new StringBuilder();
		buf.append("CREATE TABLE ");
		buf.append(PlayWay_Record.TABLE_NAME);
		buf.append(" (");
		buf.append(PlayWay_Record._ID);
		buf.append(" INTEGER PRIMARY KEY AUTOINCREMENT,");
		buf.append(PlayWay_Record.LOTTERY_TYPE);
		buf.append(" INTEGER,");
		buf.append(PlayWay_Record.PLAYWAY_TYPE);
		buf.append(" INTEGER");
		buf.append(");");
		db.execSQL(buf.toString());
		
		
	}

	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		String sql1 = "drop table if exists " + Betting.TABLE_BALL_NAME;
		db.execSQL(sql1);
		
		String sql2 = "drop table if exists " + ZC_Betting.TABLE_ZC_NAME;
		db.execSQL(sql2);
		
		String sql3 = "drop table if exists " + Lottery_Category.TABLE_NAME;
		db.execSQL(sql3);
		
		String sql4 = "drop table if exists " + PlayWay_Record.TABLE_NAME;
		db.execSQL(sql4);
		
		/**
		 * 重新建表
		 */
		onCreate(db);		
	}
	
	
	/**
	 * 彩种投注号码表名
	 * @author chunjiang.shieh
	 *
	 */
	public static final class Betting implements BaseColumns,BettingColumns {

		public static final String TABLE_BALL_NAME = "betting";

	}
	
	
	/**
	 * 彩种投注号码表名
	 * @author chunjiang.shieh
	 *
	 */
	public static final class ZC_Betting implements BaseColumns,ZC_BettingColumns {
		
		public static final String TABLE_ZC_NAME = "zc_betting";

	}
	
	/**
	 * 彩种列表
	 * @author gl
	 *
	 */
	public static final class Lottery_Category implements BaseColumns,Lottery_CategoryColumns {
		
		public static final String TABLE_NAME = "lottery_category";

	}
	
	/**
	 * 玩法记录
	 * @author gl
	 *
	 */
	public static final class PlayWay_Record implements BaseColumns,PlayWay_RecordColumns {
		
		public static final String TABLE_NAME = "playway_record";
		
	}
	
	
	
	/**
	 * 彩种投注号码字段
	 * @author chunjiang.shieh
	 *
	 */
	public interface BettingColumns {
		
		/**
		 *  玩法类型 int型
		 */
		String PLAYWAY_TYPE = "playway_type";
		
		/**
		 *  玩法名
		 */
		String PLAYWAY_NAME = "playway_name";

	
		/**
		 * 投注号码 String型
		 */
		String BETTINGNUM = "bettingnum";
		/**
		 * 保存号码  String型，用于分割
		 */
		String SAVENUM = "savenum";
		
		/**
		 * 投注号码对应的注数
		 */
		String BETTINGCOUNT = "bettingcount";
		
		/**
		 * 彩种类型
		 */
		String LOTTERY_TYPE = "lottery_type";
		/**
		 * 彩种名
		 */
		String LOTTERY_NAME = "lottery_name";
		
	}
	
	/**
	 * 彩种投注号码字段
	 * @author chunjiang.shieh
	 *
	 */
	public interface ZC_BettingColumns {
		
		/**
		 *  玩法类型 int型
		 */
		String PLAYWAY_TYPE = "playway_type";
		
		/**
		 *  玩法名
		 */
		String PLAYWAY_NAME = "playway_name";

		/**
		 * 彩种类型
		 */
		String LOTTERY_TYPE = "lottery_type";
		/**
		 * 彩种名
		 */
		String LOTTERY_NAME = "lottery_name";
		
		/**
		 * 比赛名
		 */
		String MARCH_NAME = "march_name";
		/**
		 * 主队名 
		 */
		String HOST_NAME = "host_name";
		
		/**
		 * 客队名
		 */
		String GUEST_NAME = "guest_name";
		
		/**
		 * 混投是否开售
		 */
		String OPEN_FLAG = "open_flag";
		
		/**
		 * 让球
		 */
		String  HANDICAP  = "handicap";
		/**
		 * 让球
		 */
		String  PLAYWAY_TYPENAME  = "playTypeName";
		
		/**
		 * 胆
		 */
		String  DAN  = "dan";
		
		/**
		 * 投注的比赛
		 */
		String BETTING_MATCH = "betting_match";
		/**
		 * 投注的比赛索引
		 */
		String BETTING_MATCH_INDEX = "betting_match_index";
		
		/**
		 * 窜
		 */
		String CUAN = "cuan";
		
		/**
		 *赔率
		 */
		String SP = "sp";
		
		/**
		 *match_key
		 */
		String MATCH_KEY = "matchKey";
		
		/**
		 *match_key
		 */
		String GROUP_POSITION = "group_positon";
		
		/**
		 *match_key
		 */
		String CHILD_POSITION = "child_positon";
		
		/**
		 * 场次文本标识 （周一001）
		 */
		String MATCH_KEY_TEXT = "match_key_text";
		
		/**
		 * 每场比赛id （54737）
		 */
		String MATCH_ID = "match_id";
		
		/**
		 * 让球胜平负单关
		 */
		String DANGUAN = "danguan";

		/**
		 * 胜平负单关
		 */
		String SPF_DANGUAN = "spfdanguan";
	}
	
	
	public interface Lottery_CategoryColumns{
		/**
		 * 彩票类型
		 */
		String LOTTERY_TYPE = "lottery_type";
		
		/**
		 * 彩票名称
		 */
		String LOTTERY_NAME = "lottery_name";
		
		/**
		 * 彩种排序
		 */
		String ORDER_INDEX = "order_index";
		
		/**
		 * 是否出现在首页
		 */
		String INDEX_TYPE = "indexType";
		
		/**
		 * 服务器类型 (热、火、新)
		 */
		String HOT = "hot";
	}
	
	public interface PlayWay_RecordColumns{
		/**
		 * 彩票类型
		 */
		String LOTTERY_TYPE = "lottery_type";
		
		/**
		 *  玩法类型 int型
		 */
		String PLAYWAY_TYPE = "playway_type";
	}
	
}
