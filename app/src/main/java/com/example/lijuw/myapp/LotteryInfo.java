package com.example.lijuw.myapp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class LotteryInfo implements Serializable,Cloneable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -629726979237289423L;

	private static final String TAG = "LotteryInfo";
	
	public static final int INDEX_TYPE_SELECTED = 1;
	public static final int INDEX_TYPE_UNSELECTED = 2;

	private int _id;// key
	private String lotteryName;// 彩种名
	private int lotteryType;// 彩种类型
	private String playWayName;// 玩法名
	private int playWayType;// 玩法类型
	private String lotteryNo;// 投注号码
	private String saveNo;// 保存号码
	private int betCount;// 注数
	private int orderIndex = 0; //彩种排序
	private String hot = "0";// 是否是最火最新等 1新 2火
	private int indexType; //1已选择 2未选择 
	

	private String type; // 类型
	private String periodNumber; // 期数
	private int periodId; // 期号
	private String prizeTime; // 开奖时间

	public LotteryInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LotteryInfo(LotteryInfo info) {
		this.lotteryType = info.getlotteryType();
		this.playWayType = info.getPlayWayType();
		this.betCount = 1;
	}

	public int getOrderIndex() {
		return orderIndex;
	}

	public void setOrderIndex(int orderIndex) {
		this.orderIndex = orderIndex;
	}

	public void setId(int id) {
		this._id = id;
	}

	public int getId() {
		return _id;
	}

	public void setLotteryName(String name) {
		this.lotteryName = name;
	}

	public String getLotteryName() {
		return this.lotteryName;
	}

	public void setPlayWayName(String name) {
		this.playWayName = name;
	}

	public String getPlayWayName() {
		return this.playWayName;
	}

	public void setlotteryNo(String lotteryNo) {
		this.lotteryNo = lotteryNo;
	}

	public String getlotteryNo() {
		return this.lotteryNo;
	}

	public void setSaveNo(String saveNo) {
		this.saveNo = saveNo;
	}

	public String getSaveNo() {
		return this.saveNo;
	}

	public void setlotteryType(int type) {
		this.lotteryType = type;
	}

	public int getlotteryType() {
		return this.lotteryType;
	}

	public void setPlayWayType(int type) {
		this.playWayType = type;
	}

	public int getPlayWayType() {
		return this.playWayType;
	}

	public int getBetCount() {
		return betCount;
	}

	public void setBetCount(int count) {
		this.betCount = count;
	}

	/**
	 * @author shouli.luo
	 * @time 2013-4-7 下午04:28:41
	 * @方法作用： 根据彩种获取图片ID
	 * @param
	 * @return
	 */

	
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
		if (type != null) {
			if (ComTools.LotteryTypeMap.containsKey(type)) {
				this.playWayType = ComTools.LotteryTypeMap.get(this.type);
			}

		}
	}


	public String getPeriodNumber() {
		return periodNumber;
	}

	public void setPeriodNumber(String periodNumber) {
		this.periodNumber = periodNumber;
	}

	public int getPeriodId() {
		return periodId;
	}

	public void setPeriodId(int periodId) {
		this.periodId = periodId;
	}

	public String getPrizeTime() {
		return prizeTime;
	}

	public void setPrizeTime(String prizeTime) {
		this.prizeTime = prizeTime;
	}

	public String getHot() {
		return hot;
	}

	public void setHot(String hot) {
		this.hot = hot;
	}

	public int getIndexType() {
		return indexType;
	}

	public void setIndexType(int indexType) {
		this.indexType = indexType;
	}
	
	

	  
}
