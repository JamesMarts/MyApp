package com.example.lijuw.myapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.text.TextUtils;

import com.example.lijuw.myapp.BetItem;
import com.example.lijuw.myapp.DatabaseHelper.Betting;
import com.example.lijuw.myapp.DatabaseHelper.Lottery_Category;
import com.orhanobut.logger.Logger;

/**
 * 
 * @author chunjiang.shieh
 * 
 */
@SuppressLint("UseSparseArrays")
public class CaipiaoDao {

	private static CaipiaoDao mInstance;

	public static CaipiaoDao getInstance() {
		if (mInstance == null) {
			mInstance = new CaipiaoDao();
		}
		return mInstance;
	}

	private static final String TAG = "CaipiaoDao";

	/**
	 * 插入投注号码
	 * 
	 * @param context
	 * @param lotteryType
	 * @param
	 * @param num
	 * @return
	 */
	public boolean insertBettingNum(Context context, int playWayType, String playWayName, int lotteryType, String lotteryName, String num, String saveNum, int count) {
		DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
		SQLiteDatabase db = databaseHelper.getWritableDatabase();
		db.beginTransaction();
		StringBuilder sql = new StringBuilder(0);
		sql.append("INSERT OR ROLLBACK INTO ");
		sql.append(Betting.TABLE_BALL_NAME);
		sql.append("(");
		sql.append(Betting.PLAYWAY_TYPE);
		sql.append(",");
		sql.append(Betting.BETTINGNUM);
		sql.append(",");
		sql.append(Betting.SAVENUM);
		sql.append(",");
		sql.append(Betting.PLAYWAY_NAME);
		sql.append(",");
		sql.append(Betting.LOTTERY_NAME);
		sql.append(",");
		sql.append(Betting.LOTTERY_TYPE);
		sql.append(",");
		sql.append(Betting.BETTINGCOUNT);
		sql.append(")");
		sql.append(" VALUES(");
		sql.append("?,?,?,?,?,?,?");
		sql.append(");");
		long insertedRowId = -1;
		SQLiteStatement statement = null;
		try {
			statement = db.compileStatement(sql.toString());
			statement.bindLong(1, playWayType);
			statement.bindString(2, num);
			statement.bindString(3, saveNum);
			statement.bindString(4, playWayName);
			statement.bindString(5, lotteryName);
			statement.bindLong(6, lotteryType);
			statement.bindLong(7, count);
			insertedRowId = statement.executeInsert();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (statement != null) {
				statement.close();
			}
		}

		// 事务成功提交
		db.setTransactionSuccessful();
		// 结束事务
		db.endTransaction();
		if (insertedRowId == -1) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 插入比赛
	 * 
	 * @param context
	 * @param lotteryType

	 * @return
	 */
	public boolean insertBettingMatch(Context context, int playWayType, String playWayName, String playWayTypeName, int lotteryType, String lotteryName, String marchName, String hostName, String guestName, int openFlag, String betting_match,
			String betting_match_index, int handicap, String sp, String matchKey, int danStatus, int groupPosition, int childPosition, String matchKeyText, String matchId,String danGuanFlag) {

		DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
		SQLiteDatabase db = databaseHelper.getWritableDatabase();
		db.beginTransaction();
		StringBuilder sql = new StringBuilder(0);
		sql.append("INSERT OR ROLLBACK INTO ");
		sql.append(DatabaseHelper.ZC_Betting.TABLE_ZC_NAME);
		sql.append("(");
		sql.append(DatabaseHelper.ZC_Betting.PLAYWAY_TYPE);
		sql.append(",");
		sql.append(DatabaseHelper.ZC_Betting.PLAYWAY_NAME);
		sql.append(",");
		sql.append(DatabaseHelper.ZC_Betting.LOTTERY_NAME);
		sql.append(",");
		sql.append(DatabaseHelper.ZC_Betting.LOTTERY_TYPE);
		sql.append(",");
		sql.append(DatabaseHelper.ZC_Betting.HOST_NAME);
		sql.append(",");
		sql.append(DatabaseHelper.ZC_Betting.GUEST_NAME);
		sql.append(",");
		sql.append(DatabaseHelper.ZC_Betting.OPEN_FLAG);
		sql.append(",");
		sql.append(DatabaseHelper.ZC_Betting.BETTING_MATCH);
		sql.append(",");
		sql.append(DatabaseHelper.ZC_Betting.MARCH_NAME);
		sql.append(",");
		sql.append(DatabaseHelper.ZC_Betting.SP);
		sql.append(",");
		sql.append(DatabaseHelper.ZC_Betting.HANDICAP);
		sql.append(",");
		sql.append(DatabaseHelper.ZC_Betting.GROUP_POSITION);
		sql.append(",");
		sql.append(DatabaseHelper.ZC_Betting.CHILD_POSITION);
		sql.append(",");
		sql.append(DatabaseHelper.ZC_Betting.BETTING_MATCH_INDEX);
		sql.append(",");
		sql.append(DatabaseHelper.ZC_Betting.DAN);
		sql.append(",");
		sql.append(DatabaseHelper.ZC_Betting.MATCH_KEY);
		sql.append(",");
		sql.append(DatabaseHelper.ZC_Betting.PLAYWAY_TYPENAME);
		sql.append(",");
		sql.append(DatabaseHelper.ZC_Betting.MATCH_KEY_TEXT);
		sql.append(",");
		sql.append(DatabaseHelper.ZC_Betting.MATCH_ID);
		sql.append(",");
		sql.append(DatabaseHelper.ZC_Betting.DANGUAN);
		sql.append(",");
		sql.append(DatabaseHelper.ZC_Betting.SPF_DANGUAN);
		sql.append(")");
		sql.append(" VALUES(");
		sql.append("?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?");
		sql.append(");");
		long insertedRowId = -1;
		SQLiteStatement statement = null;
		try {
			statement = db.compileStatement(sql.toString());
			statement.bindLong(1, playWayType);
			statement.bindString(2, playWayName);
			statement.bindString(3, lotteryName);
			statement.bindLong(4, lotteryType);
			statement.bindString(5, hostName);
			statement.bindString(6, guestName);
			statement.bindLong(7, openFlag);
			statement.bindString(8, betting_match);
			statement.bindString(9, marchName);
			statement.bindString(10, sp);
			statement.bindLong(11, handicap);
			statement.bindLong(12, groupPosition);
			statement.bindLong(13, childPosition);
			statement.bindString(14, betting_match_index);
			statement.bindLong(15, danStatus);
			statement.bindString(16, matchKey);
			statement.bindString(17, playWayTypeName);
			statement.bindString(18, matchKeyText);
			statement.bindString(19, matchId);
			statement.bindString(20, danGuanFlag);
//			statement.bindString(21, spfDanGuan);

			insertedRowId = statement.executeInsert();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (statement != null) {
				statement.close();
			}
		}

		// 事务成功提交
		db.setTransactionSuccessful();
		// 结束事务
		db.endTransaction();
		if (insertedRowId == -1) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 更新比赛
	 * 
	 * @param context

	 * @return
	 */
	public boolean updateBettingMatch(Context context, String betting_match, String betting_match_index, int _id, String dan) {
		DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
		SQLiteDatabase db = databaseHelper.getWritableDatabase();
		db.beginTransaction();
		ContentValues cv = new ContentValues();
		cv.put(DatabaseHelper.ZC_Betting.BETTING_MATCH, betting_match);
		cv.put(DatabaseHelper.ZC_Betting.BETTING_MATCH_INDEX, betting_match_index);
		cv.put(DatabaseHelper.ZC_Betting.DAN, dan);
		// cv.put(DatabaseHelper.ZC_Betting.SAVENUM, saveNum);
		db.update(DatabaseHelper.ZC_Betting.TABLE_ZC_NAME, cv, Betting._ID + "=?", new String[] { String.valueOf(_id) });
		// 事务成功提交
		db.setTransactionSuccessful();
		// 结束事务
		db.endTransaction();

		return true;
	}

	/**
	 * @author shouli.luo
	 * @time 2013-4-8 下午03:50:40
	 * @方法作用： 是否存在相应的彩种数据
	 * @param context
	 * @param lotteryType
	 * @return
	 */
	public boolean isExitNumByType(Context context, int lotteryType) {
		Cursor c = queryCursorByType(context, lotteryType);
		if (c.getColumnCount() > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 更新某数据
	 * 
	 * @param context
	 * @param lotteryType
	 * @param
	 * @param num
	 * @return
	 */
	public boolean updateBettingNum(Context context, int playWayType, String playWayName, int lotteryType, String lotteryName, String num, String saveNum, int count, int _id) {
		DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
		SQLiteDatabase db = databaseHelper.getWritableDatabase();
		db.beginTransaction();
		ContentValues cv = new ContentValues();
		cv.put(Betting.PLAYWAY_TYPE, playWayType);
		cv.put(Betting.BETTINGNUM, num);
		cv.put(Betting.SAVENUM, saveNum);
		cv.put(Betting.PLAYWAY_NAME, playWayName);
		cv.put(Betting.LOTTERY_NAME, lotteryName);
		cv.put(Betting.LOTTERY_TYPE, lotteryType);
		cv.put(Betting.BETTINGCOUNT, count);
		db.update(Betting.TABLE_BALL_NAME, cv, Betting._ID + "=?", new String[] { String.valueOf(_id) });
		// 事务成功提交
		db.setTransactionSuccessful();
		// 结束事务
		db.endTransaction();

		return true;
	}

	public List<LotteryInfo> getBettingNumList(Context context, int playWay) {
		Cursor cursor = queryBettingNum(context, playWay);
		int count = cursor.getCount();
		List<LotteryInfo> list = new ArrayList<LotteryInfo>();
		for (int i = 0; i < count; i++) {
			LotteryInfo bi = getBettingNum(cursor);
			list.add(bi);
			cursor.moveToNext();
		}
		cursor.close();
		return list;
	}

	/**
	 * @author shouli.luo
	 * @time 2013-4-24 下午05:03:48
	 * @方法作用： 获取竞猜的数据
	 * @param context
	 * @param
	 * @param playWay
	 */


	public int getBettingTotalCount(Context context, int playWay) {
		int totalCount = 0;
		Cursor cursor = queryBettingNum(context, playWay);
		int count = cursor.getCount();
		for (int i = 0; i < count; i++) {
			int subCount = getBettingCount(cursor);
			totalCount += subCount;
			cursor.moveToNext();
		}
		cursor.close();
		return totalCount;
	}

	public int getBettingCountById(Context context, int _id) {
		Cursor cursor = queryCursorById(context, _id);
		int count = getBettingCount(cursor);
		cursor.close();
		return count;
	}

	public LotteryInfo getBettingNum(Cursor c) {
		if (c.getCount() == 0 || c.getPosition() < 0)
			return null;
		LotteryInfo bi = new LotteryInfo();
		bi.setId(c.getInt(c.getColumnIndex(Betting._ID)));
		bi.setPlayWayType(c.getInt(c.getColumnIndex(Betting.PLAYWAY_TYPE)));
		bi.setlotteryNo(c.getString(c.getColumnIndex(Betting.BETTINGNUM)));
		bi.setBetCount(c.getInt(c.getColumnIndex(Betting.BETTINGCOUNT)));
		bi.setPlayWayName(c.getString(c.getColumnIndex(Betting.PLAYWAY_NAME)));
		bi.setLotteryName(c.getString(c.getColumnIndex(Betting.LOTTERY_NAME)));
		bi.setlotteryType(c.getInt(c.getColumnIndex(Betting.LOTTERY_TYPE)));
		bi.setSaveNo(c.getString(c.getColumnIndex(Betting.SAVENUM)));
		// bi.setBetCount(c.getInt(c.getColumnIndex(Betting.BETTINGCOUNT)));
		return bi;
	}

	public HashMap<Integer, String> getNumMap(Context context, int lotteryType, int playWayType, String matchKey) {

		Cursor c = queryCursorByType(context, lotteryType, playWayType, matchKey);

		if (c.getCount() == 0 || c.getPosition() < 0)
			return null;

		String match = c.getString(c.getColumnIndex(DatabaseHelper.ZC_Betting.BETTING_MATCH));
		String match_index = c.getString(c.getColumnIndex(DatabaseHelper.ZC_Betting.BETTING_MATCH_INDEX));
		HashMap<Integer, String> numMap = null;
		if (match != null && !match.equals("")) {
			numMap = new HashMap<Integer, String>();
			String[] strs = match.split("\\|");
			String[] strIndexs = match_index.split("\\|");
			for (int i = 0; i < strs.length; i++) {
				numMap.put(Integer.parseInt(strIndexs[i]), strs[i]);
			}
		}
		c.close();
		return numMap;

	}



	private int getBettingCount(Cursor c) {
		if (c.getPosition() < 0)
			return 0;
		return c.getInt(c.getColumnIndex(Betting.BETTINGCOUNT));
	}

	/**
	 * 根据玩法查询投注号码的游标
	 * 
	 * @param context
	 * @param
	 * @return
	 */
	private Cursor queryBettingNum(Context context, int lotteryType) {
		DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
		SQLiteDatabase db = databaseHelper.getReadableDatabase();
		String selection = Betting.LOTTERY_TYPE + " = " + lotteryType;
		Cursor cursor = db.query(Betting.TABLE_BALL_NAME, null, selection, null, null, null, Betting._ID + " DESC");// ASC
		cursor.moveToFirst();
		return cursor;
	}

	/**
	 * 根据ID获取游标
	 * 
	 * @param context
	 * @param _id
	 * @return
	 */
	public Cursor queryCursorById(Context context, int _id) {
		DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
		SQLiteDatabase db = databaseHelper.getReadableDatabase();
		String selection = Betting._ID + " = " + _id;
		Cursor cursor = db.query(Betting.TABLE_BALL_NAME, null, selection, null, null, null, Betting._ID + " ASC");
		cursor.moveToFirst();
		return cursor;
	}

	/**
	 * 根据ID获取游标
	 * 
	 * @param context
	 * @param
	 * @return
	 */
	public Cursor queryCursorByType(Context context, int lotteryType) {
		DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
		SQLiteDatabase db = databaseHelper.getReadableDatabase();
		String selection = Betting.LOTTERY_TYPE + " = " + lotteryType;
		Cursor cursor = db.query(Betting.TABLE_BALL_NAME, null, selection, null, null, null, Betting._ID + " DESC");
		cursor.moveToFirst();
		Logger.i("cursorCount:", "" + cursor.getCount());
		return cursor;
	}

	/**
	 * 根据ID获取游标
	 * 
	 * @param context
	 * @param _id
	 * @return
	 */
	public Cursor queryCursorByType(Context context, int lotteryType, int playWayType, String matchKey) {
		DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
		SQLiteDatabase db = databaseHelper.getReadableDatabase();
		String selection = DatabaseHelper.ZC_Betting.LOTTERY_TYPE + "=" + lotteryType + " AND " + DatabaseHelper.ZC_Betting.PLAYWAY_TYPE + "=" + playWayType + " AND " + DatabaseHelper.ZC_Betting.MATCH_KEY + "=?";
		Logger.i(TAG, "selection:" + selection);
		Cursor cursor = db.query(DatabaseHelper.ZC_Betting.TABLE_ZC_NAME, null, selection, new String[] { matchKey }, null, null, null);
		cursor.moveToFirst();
		Logger.i("cursorCount:", "cursorCount" + cursor.getCount());
		return cursor;
	}

	/**
	 * 根据ID获取游标
	 * 
	 * @param context
	 * @param _id
	 * @return
	 */
	public Cursor queryCursorByType(Context context, int lotteryType, int playWayType) {
		DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
		SQLiteDatabase db = databaseHelper.getReadableDatabase();
		String selection = DatabaseHelper.ZC_Betting.LOTTERY_TYPE + " = " + lotteryType + " AND " + DatabaseHelper.ZC_Betting.PLAYWAY_TYPE + " = " + playWayType;
		Cursor cursor = db.query(DatabaseHelper.ZC_Betting.TABLE_ZC_NAME, null, selection, null, null, null, Betting._ID + " ASC");
		cursor.moveToFirst();
		Logger.i("cursorCount:", "cursorCount:" + cursor.getCount());
		return cursor;
	}

	/**
	 * 删除单个投注号码
	 * 
	 * @param context
	 * @param _id
	 * @return
	 */
	public int deleteSingleBettingNum(Context context, int _id) {
		String selection = Betting._ID + " = " + _id;
		DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
		SQLiteDatabase db = databaseHelper.getWritableDatabase();
		int result = db.delete(Betting.TABLE_BALL_NAME, selection, null);
		return result;
	}

	/**
	 * 删除单场比赛
	 * 
	 * @param context
	 * @param _id
	 * @return
	 */
	public int deleteSingleBettingMatch(Context context, int _id) {
		String selection = DatabaseHelper.ZC_Betting._ID + " = " + _id;
		DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
		SQLiteDatabase db = databaseHelper.getWritableDatabase();
		int result = db.delete(DatabaseHelper.ZC_Betting.TABLE_ZC_NAME, selection, null);
		return result;
	}

	/**
	 * 删除所有同彩种的投注号码
	 * 
	 * @param context
	 * @return
	 */
	public int deleteBettingNumByType(Context context, int lotteryType) {
		String selection = null;
		if (lotteryType != -1) {
			selection = Betting.LOTTERY_TYPE + " = " + lotteryType;
		}
		DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
		SQLiteDatabase db = databaseHelper.getWritableDatabase();
		int result = db.delete(Betting.TABLE_BALL_NAME, selection, null);
		return result;
	}

	/**
	 * 删除所有同彩种的投注号码
	 * 
	 * @param context
	 * @return
	 */
	public int deleteBettingMatchByType(Context context, int lotteryType, int playWayType) {
		String selection = null;
		if (lotteryType != -1) {
			selection = DatabaseHelper.ZC_Betting.LOTTERY_TYPE + " = " + lotteryType + " AND " + DatabaseHelper.ZC_Betting.PLAYWAY_TYPE + " = " + playWayType;
		}
		DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
		SQLiteDatabase db = databaseHelper.getWritableDatabase();
		int result = db.delete(DatabaseHelper.ZC_Betting.TABLE_ZC_NAME, selection, null);
		return result;
	}

	/**
	 * 删除所有投注号码
	 * 
	 * @param context
	 * @return
	 */
	public int deleteAllBettingNum(Context context) {
		DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
		SQLiteDatabase db = databaseHelper.getWritableDatabase();
		int result = db.delete(Betting.TABLE_BALL_NAME, null, null);
		return result;
	}

	/**
	 * 删除所有投注号码 删除所有 竞彩投注
	 * 
	 * @param context
	 * @return
	 */
	public int deleteAllBettingMatch(Context context) {
		DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
		SQLiteDatabase db = databaseHelper.getWritableDatabase();
		int result = db.delete(DatabaseHelper.ZC_Betting.TABLE_ZC_NAME, null, null);
		return result;
	}

	public boolean insertLotteryCategory(Context context, LotteryInfo info) {
		DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
		SQLiteDatabase db = databaseHelper.getWritableDatabase();

		if (updateLotteryCategory(context, info) > 0) {
			return false;
		}

		ContentValues values = new ContentValues();
		values.put(Lottery_Category.LOTTERY_TYPE, info.getlotteryType());
		values.put(Lottery_Category.LOTTERY_NAME, info.getLotteryName());
		values.put(Lottery_Category.ORDER_INDEX, info.getOrderIndex());
		values.put(Lottery_Category.HOT, info.getHot());
		values.put(Lottery_Category.INDEX_TYPE, info.getIndexType());
		db.insert(Lottery_Category.TABLE_NAME, null, values);
		return true;
	}

	public int deleteLotteryCategory(Context context, int lotteryType) {
		DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
		SQLiteDatabase db = databaseHelper.getWritableDatabase();
		String selection = Lottery_Category.LOTTERY_TYPE + " = " + lotteryType;
		int result = db.delete(Lottery_Category.TABLE_NAME, selection, null);
		return result;
	}
	
	public void deleteAllLotteryCategory(Context context){
		DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
		SQLiteDatabase db = databaseHelper.getWritableDatabase();
		db.delete(Lottery_Category.TABLE_NAME, null, null);
	}
	

	public int updateLotteryCategory(Context context, LotteryInfo info) {
		DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
		SQLiteDatabase db = databaseHelper.getWritableDatabase();
		db.beginTransaction();
		ContentValues values = new ContentValues();
		values.put(Lottery_Category.LOTTERY_TYPE, info.getlotteryType());
		values.put(Lottery_Category.LOTTERY_NAME, info.getLotteryName());
		values.put(Lottery_Category.ORDER_INDEX, info.getOrderIndex());
		values.put(Lottery_Category.HOT, info.getHot());
		values.put(Lottery_Category.INDEX_TYPE, info.getIndexType());
		int result = db.update(Lottery_Category.TABLE_NAME, values, Lottery_Category.LOTTERY_TYPE+"=?", new String[] {String.valueOf(info.getlotteryType())});
		// 事务成功提交
		db.setTransactionSuccessful();
		// 结束事务
		db.endTransaction();
		return result;
	}

	public List<LotteryInfo> queryAllLotteryCategory(Context context) {
		DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
		SQLiteDatabase db = databaseHelper.getWritableDatabase();
		List<LotteryInfo> infos = new ArrayList<LotteryInfo>();
		String[] columns = {Lottery_Category.LOTTERY_TYPE,Lottery_Category.LOTTERY_NAME,
				Lottery_Category.ORDER_INDEX,Lottery_Category.HOT,Lottery_Category.INDEX_TYPE};
		Cursor cursor = db.query(Lottery_Category.TABLE_NAME,columns,null, null, null, null, Lottery_Category.ORDER_INDEX);
		while(cursor.moveToNext()){
			LotteryInfo info = new LotteryInfo();
			info.setlotteryType(cursor.getInt(0));
			info.setLotteryName(cursor.getString(1));
			info.setOrderIndex(cursor.getInt(2));
			info.setHot(cursor.getString(3));
			info.setIndexType(cursor.getInt(4));
			infos.add(info);
		}
		return infos;
	}
	
	public List<LotteryInfo> queryAllLotteryCategoryByIndexType(Context context,int indexType) {
		DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
		SQLiteDatabase db = databaseHelper.getWritableDatabase();
		List<LotteryInfo> infos = new ArrayList<LotteryInfo>();
		String[] columns = {Lottery_Category.LOTTERY_TYPE,Lottery_Category.LOTTERY_NAME,Lottery_Category.ORDER_INDEX,
				Lottery_Category.ORDER_INDEX,Lottery_Category.HOT,Lottery_Category.INDEX_TYPE};
		Cursor cursor = db.query(Lottery_Category.TABLE_NAME,columns,"indexType=?", new String[]{String.valueOf(indexType)}, null, null, Lottery_Category.ORDER_INDEX);
		while(cursor.moveToNext()){
			LotteryInfo info = new LotteryInfo();
			info.setlotteryType(cursor.getInt(0));
			info.setLotteryName(cursor.getString(1));
			info.setOrderIndex(cursor.getInt(2));
			info.setHot(cursor.getString(3));
			info.setIndexType(cursor.getInt(4));
			infos.add(info);
		}
		return infos;
	}
	
	public int getLastPlayWayTypeByLotteryType(Context context,int lotteryType){
		DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
		SQLiteDatabase db = databaseHelper.getReadableDatabase();
		String selection = DatabaseHelper.PlayWay_Record.LOTTERY_TYPE + " = " + lotteryType;
		Cursor cursor = db.query(DatabaseHelper.PlayWay_Record.TABLE_NAME, new String[]{DatabaseHelper.PlayWay_Record.PLAYWAY_TYPE}, selection, null, null, null, null);
		int playWayType = -1;
		if(cursor.moveToNext()){
			playWayType = cursor.getInt(0);
		}
		return playWayType;
	}
	
	public int updateLastPlayWayType(Context context, int lotteryType, int playWayType) {
		DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
		SQLiteDatabase db = databaseHelper.getWritableDatabase();
		db.beginTransaction();
		ContentValues values = new ContentValues();
		values.put(DatabaseHelper.PlayWay_Record.LOTTERY_TYPE, lotteryType);
		values.put(DatabaseHelper.PlayWay_Record.PLAYWAY_TYPE, playWayType);
		int result = db.update(DatabaseHelper.PlayWay_Record.TABLE_NAME, values, DatabaseHelper.PlayWay_Record.LOTTERY_TYPE+"=?", new String[] {String.valueOf(lotteryType)});
		// 事务成功提交
		db.setTransactionSuccessful();
		// 结束事务
		db.endTransaction();
		return result;
	}

	
	public boolean insertLastPlayWayType(Context context, int lotteryType,int playWayType) {
		DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
		SQLiteDatabase db = databaseHelper.getWritableDatabase();

		if (updateLastPlayWayType(context, lotteryType,playWayType) > 0) {
			return false;
		}

		ContentValues values = new ContentValues();
		values.put(DatabaseHelper.PlayWay_Record.LOTTERY_TYPE, lotteryType);
		values.put(DatabaseHelper.PlayWay_Record.PLAYWAY_TYPE, playWayType);
		db.insert(DatabaseHelper.PlayWay_Record.TABLE_NAME, null, values);
		return true;
	}
	
}
