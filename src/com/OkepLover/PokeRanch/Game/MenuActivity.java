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


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class MenuActivity extends Activity {
	
	final Context context = this;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.menu);
		
		if (mediaPlayer == null){
	        mediaPlayer = MediaPlayer.create(this, R.raw.test);
	        mediaPlayer.setLooping(true); // Set looping
	        mediaPlayer.setVolume(100,100);
	        mediaPlayer.start();
		}
	}
	
	public void newButton(View v) {
		Intent i = new Intent(this,NewPlayerMenu.class);
		this.startActivity(i);
		mediaPlayer.stop();
		mediaPlayer = null;
		this.finish();
	} 
	
	public void loadButton(View v){
		Intent i = new Intent(this,SaveLoadActivity.class);

		i.putExtra("MapCode",1);
		this.startActivity(i);
	}
	
	public void helpButton(View v){
		Intent i = new Intent(this,HelpMenu.class);
		this.startActivity(i);
	}
	
	public void exitButton(View v){
		this.finish();
		mediaPlayer.stop();
		System.exit(0); 
	}

	
    public static MediaPlayer mediaPlayer;
}
