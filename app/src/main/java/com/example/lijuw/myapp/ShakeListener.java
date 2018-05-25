package com.example.lijuw.myapp;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

/**
 * Android 1.5 API Level 3 之后 SensorListener升级到SensorEventListener
 * 检测手机晃动
 * @author chunjiang.shieh
 *
 */
public class ShakeListener implements SensorEventListener {
	
	/**
	 * 力量阀值  控制晃动的速度
	 */
	private static final int FORCE_THRESHOLD = 500;  
	/**
	 * 时间阀值
	 */
	private static final int TIME_THRESHOLD = 200; 
	    
	/**
	 * 每次晃动之间最大的时间间隔 
	 */
	private static final int SHAKE_TIMEOUT = 500;
	/**
	 * 晃动持续时间
	 */
	private static final int SHAKE_DURATION = 700;
	/**
	 * 晃动次数
	 */
	private static final int SHAKE_COUNT = 1;
	
	
	
	private Context mContext;
	private OnShakeListener mOnShakeListener;
	/**
	 * 传感器的管理类
	 */
	private SensorManager mSensorManager;
	
	
	private float mLastX = -1.0f, mLastY = -1.0f, mLastZ = -1.0f;  
	private long mLastTime;  
	/**
	 * 最近晃动的时间
	 */
	private long mLastShake;
	/**
	 * 上次发力的时间
	 */
	private long mLastForce;
    private int mShakeCount = 0;  

	
	
	public interface OnShakeListener{
		public void onShake();
	}
	
	
	public ShakeListener(Context context){
		this.mContext = context;
		resume();
	}
	
	public void setOnShakeListener(OnShakeListener listener){
		this.mOnShakeListener = listener;
	}
	
	/**
	 * 注册传感器
	 */
	public void resume(){
		mSensorManager = (SensorManager) mContext.getSystemService(Context.SENSOR_SERVICE);
		if(mSensorManager == null){
//			throw new UnsupportedOperationException("Sensor not supported");
			return;
		}
		boolean supported = mSensorManager.registerListener(this, 
				mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_UI);
		if(!supported){
			mSensorManager.unregisterListener(this);
//			throw new UnsupportedOperationException("Accelerometer not supported");
			return;
		}
	}
	
	/**
	 * 解除传感器
	 */
	public void pause(){
		if(mSensorManager != null){
			mSensorManager.unregisterListener(this);
			mSensorManager = null;
		}
	}

	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		if(event.sensor.getType() != Sensor.TYPE_ACCELEROMETER){
			return;
		}
		long now = System.currentTimeMillis();
		if((now - mLastForce) > SHAKE_TIMEOUT){
			mShakeCount = 0;
		}
		if ((now - mLastTime) > TIME_THRESHOLD) {  
			long diff = now - mLastTime;  
			float speed = Math.abs(event.values[SensorManager.DATA_X]  
			                                    + event.values[SensorManager.DATA_Y]  
			                                                   + event.values[SensorManager.DATA_Z] - mLastX - mLastY - mLastZ)  
			                                                   / diff * 10000;  
			/**
			 * 晃动速度满足阀值
			 */
			if (speed > FORCE_THRESHOLD) {  
				/**
				 * 晃动次数满足SHAKE_COUNT，并且晃动持续时间超过SHAKE_DURATION
				 */
				if ((++mShakeCount >= SHAKE_COUNT)  
						&& (now - mLastShake > SHAKE_DURATION)) {  
					mLastShake = now;  
					mShakeCount = 0;  
					if (mOnShakeListener != null) {  
						mOnShakeListener.onShake();  
					}  
				}  
				mLastForce = now;  
			}  
			mLastTime = now;  
			mLastX = event.values[SensorManager.DATA_X];  
			mLastY = event.values[SensorManager.DATA_Y];  
			mLastZ = event.values[SensorManager.DATA_Z];  
		}  
		
	}

	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}


}
