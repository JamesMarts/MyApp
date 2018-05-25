package com.example.lijuw.myapp;

import java.util.Calendar;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.text.TextUtils;
import android.view.View;

public class DialogUtil {

	// 进度对话框
	public static ProgressDialog showProgressDialog(Context context, String message) {
		ProgressDialog pd = new ProgressDialog(context);
		pd.setMessage(message);
		return pd;
	}

	// 日期选择
	public static DatePickerDialog showDatePickerDialog(Context context, DatePickerDialog.OnDateSetListener callBack) {
		DatePickerDialog datePickerDialog = new DatePickerDialog(context, callBack, 1980, 0, 1);
		datePickerDialog.show();
		return datePickerDialog;
	}

	public static DatePickerDialog showDatePickerDialog(boolean today, Context context, DatePickerDialog.OnDateSetListener callBack) {
		Calendar calendar = Calendar.getInstance();
		DatePickerDialog datePickerDialog = new DatePickerDialog(context, callBack, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));
		datePickerDialog.show();
		return datePickerDialog;
	}

	// 多选
	public static AlertDialog showMulDialog(Context context, String[] items, boolean[] checkedItems, DialogInterface.OnMultiChoiceClickListener listener, OnClickListener posListener) {
		AlertDialog.Builder builder = new Builder(context);
		builder.setMultiChoiceItems(items, checkedItems, listener);
		builder.setPositiveButton("确定", posListener);
		builder.setNegativeButton("取消", null);
		AlertDialog dialog = builder.create();
		dialog.show();
		return dialog;
	}

	// 单选
	public static AlertDialog showSingleDialog(Context context, String title, String[] items, int selected, DialogInterface.OnClickListener listener) {
		AlertDialog.Builder singleBuilder = new AlertDialog.Builder(context);
		singleBuilder.setTitle(title);
		singleBuilder.setSingleChoiceItems(items, selected, listener);
		AlertDialog singleDialog = singleBuilder.create();
		singleDialog.show();
		return singleDialog;
	}

	// 自定义
	public static AlertDialog showCustomDialog(Context context, View view, DialogInterface.OnClickListener listener, String title) {

		AlertDialog.Builder customBuilder = new Builder(context);
		customBuilder.setTitle(title);
		customBuilder.setView(view);
		customBuilder.setPositiveButton("确定", listener);
		customBuilder.setNegativeButton("取消", null);
		AlertDialog customDialog = customBuilder.create();
		customDialog.show();
		return customDialog;
	}

	// 一般的
	public static AlertDialog showNormalDialog(Context context, String title, String msg, DialogInterface.OnClickListener listener) {
		return showNormalDialog(context, title, msg, "确定", "取消", listener);
	}

	public static AlertDialog showNormalDialog(Context context, String title, String msg, String pBtn, String nBtn, DialogInterface.OnClickListener listener) {
		AlertDialog.Builder builder = new Builder(context);
		if (!TextUtils.isEmpty(title))
			builder.setTitle(title);
		builder.setMessage(msg);
		builder.setNegativeButton(nBtn, listener);
		builder.setPositiveButton(pBtn, listener);
		AlertDialog dialog = builder.create();
		dialog.show();
		return dialog;
	}

}
