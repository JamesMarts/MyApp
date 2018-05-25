package com.example.lijuw.myapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;


public class BYPeriodInfo {

	private static final String TAG = "BYPeriodInfo";

	public String Wait;
	public String WareId; // 商品id
	public String IssueNo; // 期号
	public String IssueBeginTime; // 销售开始时间
	public String IssueEndTime;// 销售截止时间
	public String SystemTime; // 服务器时间
	public String RecordTotal; // 记录总数

	public String newMinute; //高频彩倒计时
	public String lastPrizeSecond; //高频彩上一期开奖剩余时间,大于0表示没开奖
	public Map<String, String> currentYL; //当前 遗漏信息
		
	// {
	// Wait: 1,
	// WareId: "14166",
	// IssueNo: "2014049",
	// IssueBeginTime: "2014-05-01 20:00:00",
	// IssueEndTime: "2014-05-04 20:00:00",
	// SystemTime: "2014-05-04 15:30:18",
	// RecordTotal: 526,
	// Records: [
	// {
	// wareIssue: "2014048",
	// wareResult: "06,09,16,17,24,25-16",
	// PrizeTime: "2014-05-01 21:30:00"
	// }
	// }

	public static BYPeriodInfo parseJson(int lotteryType, String json) {
		BYPeriodInfo info = new BYPeriodInfo();
		try {
			JSONObject jsonObj = null;
			JSONArray jresult = null;

			// switch (lotteryType) {
			// case BetItem.TYPE_SSQ:
			// case BetItem.TYPE_DLT:
			// case BetItem.TYPE_QLC:
			// case BetItem.TYPE_QXC:
			// case BetItem.TYPE_PL3:
			// case BetItem.TYPE_PL5:
			// case BetItem.TYPE_3D:
			// jsonObj = new JSONObject(json);
			// jresult = jsonObj.getJSONArray("Records");
			// break;
			// case BetItem.TYPE_JSSC:
			// case BetItem.TYPE_JX11XUAN5:
			// case BetItem.TYPE_GD11XUAN5:
			// jsonObj = new JSONObject();
			// jresult = new JSONArray(json);
			// break;
			// }

			jsonObj = new JSONObject(json);
//			jresult = jsonObj.optJSONArray("Records");
			
			info.Wait = jsonObj.optString("Wait");
			info.WareId = jsonObj.optString("WareId");
			info.IssueNo = jsonObj.optString("IssueNo");
			info.IssueBeginTime = jsonObj.optString("IssueBeginTime");
			info.IssueEndTime = jsonObj.optString("IssueEndTime");
			info.RecordTotal = jsonObj.optString("RecordTotal");
			info.newMinute = jsonObj.optString("newMinute");
			info.lastPrizeSecond = jsonObj.optString("lastPrizeSecond");

			JSONObject ylObj = jsonObj.optJSONObject("currentYL");
			if(ylObj != null){
				JSONArray names = ylObj.names();
				info.currentYL = new HashMap<String, String>();
				for (int i = 0; i < names.length(); i++) {
					info.currentYL.put(names.getString(i), ylObj.getString(names.getString(i)));
				}
				
			}
			
			if (lotteryType == BetItem.TYPE_JSSC || lotteryType == BetItem.TYPE_JX11XUAN5 
					|| lotteryType == BetItem.TYPE_GD11XUAN5 || lotteryType == BetItem.TYPE_K3) {
				info.SystemTime = jsonObj.optString("newDate");
			} else {
				info.SystemTime = jsonObj.optString("SystemTime");
			}

//			int len = jresult.length();
//			ArrayList<Record> records = new ArrayList<Record>(len);
//			for (int i = 0; i < len; i++) {
//				JSONObject jsonObject = jresult.getJSONObject(i);
//				Record r = new Record();
//				r.wareIssue = jsonObject.optString("wareIssue");
//				r.wareResult = jsonObject.optString("wareResult");
//				r.PrizeTime = jsonObject.optString("PrizeTime");
//				records.add(r);
//			}
//			info.Records = records;
		} catch (Exception e) {

			return null;
		}
		return info;
	}

}
