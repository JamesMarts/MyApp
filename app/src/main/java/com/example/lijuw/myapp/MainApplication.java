package com.example.lijuw.myapp;

import android.app.Application;
import android.content.Context;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainApplication extends Application {

	private static MainApplication mApplication;
	private boolean isLogin = false;
	private ExecutorService es = Executors.newSingleThreadExecutor();

	@Override
	public void onCreate() {

		super.onCreate();
		mApplication = this;

		ComTools.initLotteryTypeMap();

	}

	public static MainApplication getApp() {
		return mApplication;
	}

	public void setLogin(boolean isLogin) {
		this.isLogin = isLogin;
	}

	public boolean isLogin() {
		return isLogin;
	}


	public void doBackTask(Runnable command) {
		es.execute(command);
	}



}
