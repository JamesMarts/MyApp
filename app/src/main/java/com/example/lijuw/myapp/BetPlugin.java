package com.example.lijuw.myapp;

import android.text.TextUtils;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

import de.greenrobot.event.EventBus;

/**
 * 投注插件，管理投注逻辑
 * Created by GL on 2015/3/26.
 */
public class BetPlugin {



    public static void doBet(final BaseActivity act,double price) {
        try{
            bindYHCode(act,price);
        }catch (Exception e){
            EventBus.getDefault().post(new BetResult());
        }
    }

    private static void bindYHCode(final BaseActivity act,double price) {

    }

    public static class BetResult {

    }

}
