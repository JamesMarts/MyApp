package com.example.lijuw.myapp;

import android.content.SharedPreferences;


public class DefaultSP {

	private static DefaultSP sp;

	private DefaultSP() {
	};

	private static final String DEFAULT_SP_NAME = "lottery_config";

	public static final String KEY_COOKIE = "Cookie";
	public static final String KEY_VERSIONCODE = "versionCode";
	public static final String KEY_LAST_LOGIN_UNAME = "last_login_name";
	public static final String KEY_LAST_LOGIN_PWD = "last_login_pwd";
	public static final String KEY_ALREADY_GUIDE = "already_guide"; //是否引导
	public static final String KEY_AUTO_LOGIN = "autologin"; //是否引导
	public static final String KEY_AUTO_UPDATE = "autoUpdate"; //是否引导
	public static final String KEY_IS_BET_CONFIRM = "isBetConfirm"; //是否引导
	public static final String KEY_ONLY_WIFI = "only_wifi"; //是否引导
	
	public static final String KEY_LAST_PAY_TYPE = "lat_pay_type"; //最后一次支付类型
	public static final int TYPE_LAST_PAY_KUAIJIE = 1; //快捷支付
	
	public static final String KEY_LAST_HEMAI_LOTTERY = "last_hemai_lottery"; //是否引导
	
	public static DefaultSP getInstance() {
		if (sp == null) {
			sp = new DefaultSP();
		}
		return sp;
	}

	public void writeCookie(String cookie) {
		writeString(KEY_COOKIE, cookie);
	}
	
	public void writeVersionCode(int versionCode) {
		writeInt(KEY_VERSIONCODE, versionCode);
	}

	public String getCookie() {
		return readString(KEY_COOKIE);
	}
	
	public int getVersionCode() {
		return readInt(KEY_VERSIONCODE);
	}

	public void writeString(String key, String value) {
		getSharedPreferences().edit().putString(key, value).commit();
	}

	public String readString(String key) {
		return getSharedPreferences().getString(key, "");
	}

	public void writeInt(String key, int value) {
		getSharedPreferences().edit().putInt(key, value).commit();
	}

	public int readInt(String key) {
		return getSharedPreferences().getInt(key, 0);
	}

	public void writeBoolean(String key, boolean value) {
		getSharedPreferences().edit().putBoolean(key, value).commit();
	}

	public boolean readBoolean(String key) {
		return getSharedPreferences().getBoolean(key, false);
	}
	
	public boolean readBoolean(String key,boolean defaultValue) {
		return getSharedPreferences().getBoolean(key, defaultValue);
	}

	public void writeFloat(String key, float value) {
		getSharedPreferences().edit().putFloat(key, value).commit();
	}

	public float readFloat(String key) {
		return getSharedPreferences().getFloat(key, 0);
	}
	
	public void writeLong(String key, long value) {
		getSharedPreferences().edit().putLong(key, value).commit();
	}
	
	public long readLong(String key) {
		return getSharedPreferences().getLong(key, 0);
	}

	public SharedPreferences getSharedPreferences() {
		return MainApplication.getApp().getSharedPreferences(DEFAULT_SP_NAME, 0);
	}
	
	public void removeKey(String key){
		getSharedPreferences().edit().remove(key).commit();
	}
}
