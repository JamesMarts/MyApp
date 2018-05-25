package com.example.lijuw.myapp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Vector;

import android.annotation.SuppressLint;


@SuppressLint("UseSparseArrays")
public class GenRandom {

	/** 
	 * 生成不重复随机数 
	 *  
	 * @param num 
	 *            随机数个数 
	 * @param value 
	 *            随机数范围 
	 * @see
	 * @return 
	 * @throws TargetMinException 
	 */  
	public static int[] genNotRepeatNum(int num, int value) throws TargetMinException {  
		int[] arr = new int[num];// 保存最终生成结果  
		int index = 0;// 状态索引 default = 0  
		arr = new int[num];  
		if (value < num) {  
			throw new TargetMinException("随机数取值范围不可以小于生成随机数个数");  
		}  
		boolean result = true;  
		while (result) {// 控制是否继续生成随机数  
			Random rd = new Random();  
			int tempRandomNum = rd.nextInt(value) + 1;  
			if (arr[arr.length - 1] == 0) {// 决定是否继续生成随机数进行赋值  
				if (isHas(tempRandomNum, arr, index)) {// 判断已生成随机数是否与数组中已有数值重复  
					continue;  
				}  
				arr[index++] = tempRandomNum;// 将生成的不重复发的随机数放入数组中  
			} else  
				result = false;  
		}  
		return arr;  
	}  

	/**
	 *@author shouli.luo
	 *@time 2013-4-3 下午03:23:36
	 *@方法作用： 生成不重复随机数 
	 * @param num
	 * @param value
	 * @return
	 * @throws TargetMinException
	 */
	public static Vector<String> genNotRepeatNumVec(int num, int value,boolean isDaXiaoDanShuang) throws TargetMinException {  
		Vector<String> vec = new Vector<String>();
		if (value < num) {  
			throw new TargetMinException("随机数取值范围不可以小于生成随机数个数");  
		}  
		boolean result = true; 
		Random rd = new Random(); 

		for(int i=0;i<num;i++){
			result = true;
			while(result){
				int tempRandomNum = rd.nextInt(value);
				String tempNumStr = "";
				if(isDaXiaoDanShuang){
					if(tempRandomNum  == 0){
						tempNumStr = "大";
					}else if(tempRandomNum == 1){
						tempNumStr = "小";
					}else if(tempRandomNum == 2){
						tempNumStr = "单";
					}else if(tempRandomNum == 3){
						tempNumStr = "双";
					}
				}else{
					if(value > 10){	tempRandomNum++;}
					tempNumStr = String.valueOf(tempRandomNum);
					if(value > 10 && tempRandomNum < 10){
						tempNumStr = "0"+tempNumStr;
					}
				}
				if(!vec.contains(tempNumStr)){
					vec.add(tempNumStr);
					result = false;;
				}
			}

		}

		return vec;  
	}  

	/**
	 *@author shouli.luo
	 *@time 2013-4-10 上午10:01:52

	 * @return
	 * @throws TargetMinException
	 */
	public static void getZuCaiNumMap(Map<Integer,Vector<String>> map,int lotteryType) throws TargetMinException {  

		Random rd = new Random(); 

		if(map == null){
			map = new HashMap<Integer, Vector<String>>();
		}else if(map.size() == 0){
			for(int i=0;i<14;i++){
				Vector<String> v = new Vector<String>();
				map.put(i, v);
			}
		}else if(map.size() == 14){
			for(int i=0;i<14;i++){
				Vector<String> v = map.get(i);
				v.clear();
			}
		}

		if(lotteryType == BetItem.TYPE_SFC){
			for(int i=0;i<14;i++){
				Vector<String> vec = map.get(i);
				int tempRandomNum = rd.nextInt(3);
				if(tempRandomNum == 2){
					vec.add("3");
				}else{
					vec.add(String.valueOf(tempRandomNum));
				}
			}
		}else{
			Vector<Integer> mVec = new Vector<Integer>();
			for(int i=0;i<9;i++){
				int j = rd.nextInt(14);
				while(mVec.contains(j)){
					j = rd.nextInt(14);
				}
				mVec.add(j);
				Vector<String> vec = map.get(j);
				int tempRandomNum = rd.nextInt(3);
				if(tempRandomNum == 2){
					vec.add("3");
				}else{
					vec.add(String.valueOf(tempRandomNum));
				}
			}
		}



	}  


	/**
	 *@author shouli.luo
	 *@time 2013-4-3 下午03:23:36
	 *@方法作用： 生成可重复随机数 
	 * @param num
	 * @param value
	 * @return
	 * @throws TargetMinException
	 */
	public static Vector<String> genHasRepeatNumVec(int num, int value) throws TargetMinException {  
		Vector<String> vec = new Vector<String>();
		if (value < num) {  
			throw new TargetMinException("随机数取值范围不可以小于生成随机数个数");  
		}  
		Random rd = new Random(); 

		for(int i=0;i<num;i++){
			int tempRandomNum = rd.nextInt(value);
			if(value > 10){	tempRandomNum++;}
			String tempNumStr = String.valueOf(tempRandomNum);
			if(value > 10 && tempRandomNum < 10){
				tempNumStr = "0"+tempNumStr;
			}
			vec.add(tempNumStr);
		}

		return vec;  
	}  



	/**
	 * 生成可重复随机数 
	 * @param num	随机数个数
	 * @param value	随机数范围
	 * @return
	 * @throws TargetMinException
	 */
	public static int[] genHasRepeatNum(int num, int value) throws TargetMinException {  
		int[] arr = new int[num];// 保存最终生成结果  
		int index = 0;// 状态索引 default = 0  
		Random rd = new Random(); 
		if (value < num) {  
			throw new TargetMinException("随机数取值范围不可以小于生成随机数个数");  
		}  
		boolean result = true;  
		while (result) {// 控制是否继续生成随机数  
			int tempRandomNum = rd.nextInt(value) + 1;  
			if (arr[arr.length - 1] == 0) {// 决定是否继续生成随机数进行赋值  
				arr[index++] = tempRandomNum;// 将生成的随机数放入数组中  
			} else  
				result = false;  
		}  
		return arr;  
	}  

	/**
	 * 生成相同重复的数
	 * @param num 随机的个数
	 * @param value 随机的范围
	 * @param sameCount 相同的个数
	 * @return
	 * @throws TargetMinException 
	 */
	public static int[] genSameRepeatNum(int num,int value,int sameCount) throws TargetMinException{
		
		if(sameCount > num || value < num){
			throw new TargetMinException("sameCount can't more than num !  or 随机数取值范围不可以小于生成随机数个数  ");  
		}
		
		int[] arr = new int[num];
		Random rd = new Random();
		//首先生成相同的数
		int sameRandom = 1 + rd.nextInt(value);
		for (int i = 0; i < sameCount; i++) {
			arr[i] = sameRandom;
		}
		//再生成剩下的数
		int index = sameCount;
		boolean result = sameCount < num;  
		while (result) {// 控制是否继续生成随机数  
			int tempRandomNum = rd.nextInt(value) + 1;  
			if (arr[arr.length - 1] == 0) {// 决定是否继续生成随机数进行赋值  
				if (isHas(tempRandomNum, arr, index)) {// 判断已生成随机数是否与数组中已有数值重复  
					continue;  
				}  
				arr[index++] = tempRandomNum;// 将生成的不重复发的随机数放入数组中  
			} else  
				result = false;  
		}
		return arr;
	}
	
	
	public static int[] genK3Num(int playWayType) throws TargetMinException{
		int[] arr = null;
		switch (playWayType) {
		case BetItem.PY_K3_HZ:
			arr = genHasRepeatNum(3, 6);
			break;
		case BetItem.PY_K3_3TH:
			arr = genSameRepeatNum(3, 6, 3);
			break;
		case BetItem.PY_K3_2TH_DX:
		case BetItem.PY_K3_2TH_FX:	
			arr = genSameRepeatNum(3, 6, 2);
			break;
		case BetItem.PY_K3_3BTH:
			arr = genNotRepeatNum(3, 6);
			break;
		case BetItem.PY_K3_2BTH:
			arr = genNotRepeatNum(2, 6);
			break;
		}
		return arr;
	}
	
	

	/** 
	 * 判断是否已存在生成的随机数 
	 *  
	 * @param mm 
	 * @param arr 
	 * @param index 
	 * @return 
	 */  
	private static boolean isHas(int tempRandomNum, int[] arr, int index) {  
		for (int i = 0; i < index; i++) {  
			if (tempRandomNum == arr[i]) {  
				return true;  
			}  
		}  
		return false;  
	}  


	/**
	 *@author shouli.luo
	 *@time 2013-4-2 下午01:14:49
	 *@方法作用： 对号码进行排序
	 * @param vec
	 * @return
	 */
	public static void orderNum(Vector<String> vec,boolean isNeedAddZero) {

		int[] arr = new int[vec.size()];
		for(int i=0;i<vec.size();i++){
			arr[i] = Integer.parseInt(vec.get(i));
		}
		showArr(arr,true);
		vec.clear();
		for(int i=0;i<arr.length;i++){
			if(isNeedAddZero && arr[i] < 10){
				vec.add("0"+String.valueOf(arr[i]));
			}else{
				vec.add(String.valueOf(arr[i]));
			}

		}
	}

	/**
	 *@author shouli.luo
	 *@time 2013-4-2 下午01:14:49
	 *@方法作用： 对号码进行排序
	 * @param map
	 * @return
	 */
	public static void orderNum(HashMap<Integer, Vector<String>> map,boolean isNeedAddZero) {

		for(int j=0;j<map.size();j++){
			Vector<String> vec = map.get(j);
			if(vec == null || vec.size() < 2){
				continue;
			}
			orderNum(vec,isNeedAddZero);
		}
	}

	/** 
	 * showArr 
	 *  
	 * @param arr 
	 * @param isSort 
	 */  
	public static void showArr(int[] arr, boolean isSort) {  
		if (isSort) {  
			for (int j = 0; j < arr.length; j++) {  
				Arrays.sort(arr);
			}  
		}
	}  

	public static class TargetMinException extends Exception {  
		private static final long serialVersionUID = 1L;  
		public String message = "";  

		public TargetMinException(String message) {  
			this.message = message;  
		}  

		@Override  
		public String getMessage() {  
			return this.message;  
		}  
	}  

}
