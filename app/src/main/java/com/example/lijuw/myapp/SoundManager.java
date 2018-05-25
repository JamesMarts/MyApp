package com.example.lijuw.myapp;

import java.util.HashMap;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;


public class SoundManager {
	private static SoundManager manager;
	private static SoundPool soundPool;
	private static HashMap<Integer, Integer> soundPoolMap;
	private static int streamVolume;
	public static final int ID_POP = 1;
	private SoundManager() {
	};

	public static SoundManager getInstance(Context context) {
		if (manager == null) {
			manager = new SoundManager();
			soundPool = new SoundPool(4, AudioManager.STREAM_MUSIC, 100);
			soundPoolMap = new HashMap<Integer, Integer>();
			soundPoolMap.put(ID_POP, soundPool.load(context, R.raw.pop_sound, 1));
			AudioManager mgr = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
			streamVolume = mgr.getStreamVolume(AudioManager.STREAM_MUSIC);
		}
		return manager;
	}

	
	public void playSound(int sound, int loop) {
		/*
		 * AudioManager mgr = (AudioManager)
		 * this.getSystemService(Context.AUDIO_SERVICE); float
		 * streamVolumeCurrent = mgr.getStreamVolume(AudioManager.STREAM_MUSIC);
		 * float streamVolumeMax =
		 * mgr.getStreamMaxVolume(AudioManager.STREAM_MUSIC); float volume =
		 * streamVolumeCurrent / streamVolumeMax;
		 * soundPool.play(soundPoolMap.get(sound), volume, volume, 1, loop, 1f);
		 */
		soundPool.play(soundPoolMap.get(sound), streamVolume, streamVolume, 1, loop, 1f);
		
	}

	public void playSound(int sound){
		playSound(sound, 0);
	}
	
	public void stop(int sound){
		soundPool.stop(sound);
	}
}
