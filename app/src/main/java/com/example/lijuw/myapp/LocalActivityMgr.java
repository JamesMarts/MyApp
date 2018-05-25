package com.example.lijuw.myapp;

import java.util.ArrayList;

import android.app.Activity;
import android.util.Log;

/**
 *  管理Activity
 * @author chunjiang.shieh
 *
 */
public class LocalActivityMgr {
	
	private static LocalActivityMgr mInstance;
	private ArrayList<Activity> mActivityList ;
	
	
	public static LocalActivityMgr getInstance(){
		if(mInstance == null){
			mInstance = new LocalActivityMgr();
		}
		return mInstance;
	}
	
	private LocalActivityMgr(){
		mActivityList = new ArrayList<Activity>();
	}
	
	public void pushActivity(Activity activity){
		mActivityList.add(activity);
	}
	
	public void removeActivity(Activity activity){
		if(mActivityList.contains(activity)){
			mActivityList.remove(activity);
		}
	}
	
	public Activity getTopActivity(){
		if(mActivityList.size() > 0){
			Activity screen = mActivityList.get(mActivityList.size() - 1);
			return screen;	
		}else{
			return null;
		}
	}
	
	public int getActivityCount(){
		return mActivityList.size();
	}
	
	
	public void popActivity(){
		if(mActivityList.size() > 0){
			Activity screen = mActivityList.remove(mActivityList.size() - 1);
			screen.finish();
		} 
	}
	
	public void clearActivity(){
		Activity screen = null;
		while (mActivityList.size() > 0 ) {
			screen = mActivityList.remove(mActivityList.size() - 1);
			screen.finish();
		}
	}
	
	/**
	 * @function get Activity by Index
	 * @param index index of activity in list
	 * @return Activity instance of activity
	 */
	public Activity getActivityByIndex(int index){
		return mActivityList.get(index);
	}
	
	/**
	 * @function get actvity by classname(full package and classname)
	 */
	public Activity findActivity(String classname){
		if(classname == null)
			return null;
		
		for(int index = 0; index < mActivityList.size(); index++){
			Activity ac = mActivityList.get(index);
			if(ac != null){
				Log.d("findActivity", "class:" +ac.getComponentName().getClassName().toString());
				if(ac.getComponentName().getClassName().equals(classname)){
					return ac;
				}
			}
		}
		return null;
	}
	
}
