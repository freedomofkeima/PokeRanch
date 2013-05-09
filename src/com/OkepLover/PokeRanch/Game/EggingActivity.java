/*
 * [IF2032] Pemrograman Beroirentasi Obyek
 * Mobile PokeRanch
 */
package com.OkepLover.PokeRanch.Game;

/**
 *
 * @author OkepLovers
 * Fathan Adi Pranaya / 13511027
 * Mohamad Rivai Ramandhani / 13511043
 * Yogi Salomo Mangontang Pratama / 13511059
 * Renusa Andra Prayogo / 13511063
 * Habibie Faried / 13511069
 * Iskandar Setiadi / 13511073
 * 
 */
import java.util.LinkedHashMap;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;

public class EggingActivity  extends Activity implements SensorEventListener {
	private float mLastX, mLastY, mLastZ;
	private boolean mInitialized;

	private SensorManager mSensorManager;

	private Sensor mAccelerometer;
	
	private ProgressBar eggBar;

	private final float NOISE = (float) 2.0;

	/** Called when the activity is first created. */

	@Override

	public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.egg_activity);
	mInitialized = false;
	mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
	mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
	mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
	eggBar = (ProgressBar) findViewById(R.id.eggBar);
	eggBar.setProgress(0);
	eggBar.setMax(100);
	}
	
	    protected void onResume() {
		super.onResume();
		mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
		}
	
		protected void onPause() {
		super.onPause();
		mSensorManager.unregisterListener(this);
		}
		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// can be safely ignored for this demo
		}
		
		@Override
		public void onSensorChanged(SensorEvent event) {
		float x = event.values[0];
		float y = event.values[1];
		float z = event.values[2];
		Log.d("Value", x + " " + y + " " + z);
		if (!mInitialized) {
		mLastX = x;
		mLastY = y;
		mLastZ = z;

		mInitialized = true;
		} else {
		float deltaX = Math.abs(mLastX - x);
		float deltaY = Math.abs(mLastY - y);
		float deltaZ = Math.abs(mLastZ - z);
		if (deltaX < NOISE) deltaX = (float)0.0;
		if (deltaY < NOISE) deltaY = (float)0.0;
		if (deltaZ < NOISE) deltaZ = (float)0.0;
		mLastX = x;
		mLastY = y;
		mLastZ = z;
        eggBar.setProgress(eggBar.getProgress() + Math.round(deltaX * deltaY * deltaZ / 500));
        if (eggBar.getProgress() >= 100) {
        	/* At this point, go to next panel (Send data to next panel) */
        	
			int randomID = Utilities.rand(1, 20);
			int randomEl = Utilities.rand(1, 3);
			
			Element tempEl = Element.none;
			if (randomEl == 1) tempEl = Element.fire;
			if (randomEl == 2) tempEl = Element.grass;
			if (randomEl == 3) tempEl = Element.water;
			
        	Monster_Sendiri M = new Monster_Sendiri(randomID,"Poke",MainGameActivity.ListSpecies.get(randomID),1,tempEl,
        			                                0,MainGameActivity.ListSpecies.get(randomID).getLifeSpan(),MainGameActivity.ListSkill);
			
    		Intent i = new Intent(this,MonsterActivity.class);
    		LinkedHashMap<String, Object> obj = new LinkedHashMap<String, Object>();
    		obj.put("Monster", M);
    		
    		Bundle b = new Bundle();
    		b.putSerializable("bundleobject2", obj);
    		i.putExtras(b);
    		
    		this.startActivity(i);
        	this.finish();
        }
		}
		}
	
}
