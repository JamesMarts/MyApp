package com.example.lijuw.myapp;

import java.io.Serializable;
import java.util.ArrayList;

public class AwardItem implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7203524437413958234L;
	public int id;
	public int periodId;
	public String lotteryName;
	public int localType;
	public String lotteryType;
	public String periodNumber;
	public int prizePool;
	public String prizeTime;
	public String result;
	public ArrayList<String> rsultArr;
	public int totalSales;
	public String guestScore;
	public String homeScore;
	public String homeTeamName;
	public String guestTeamName;
	public int sort = 100;
	
	// "id": 1059,
	// "jqsResult": "S5",
	// "jqsResultSp": 12.92,
	// "lastModifyTime": "2013-05-08 07:15:06",
	// "lineId": 1,
	// "matchDate": 20130507,
	// "matchEndDate": "2013-05-07 23:59:00",
	// "matchKey": "20130507-001",
	// "matchKeyText": "周二001",
	// "matchTime": "2013-05-08 02:45:00",
	// "openFlag": 255,
	// "periodId": 2,
	// "periodNumber": "20130307",
	// "spfResult": "LOSE",
	// "spfResultSp": 5.9,
	// "stop": true,
	// "ticketOfficialEndTime": "2013-05-07 23:59:00",
	// "version": 1,
	// "webOfficialEndTime": "2013-05-07 23:39:00"
	// },

}
