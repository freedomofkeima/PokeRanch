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
import android.os.Bundle;
import android.view.Window;

public class InGameMenuActivity extends Activity{

	//private Player P; //For storing player info
	private static Integer M; //For save / load activity
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.fragment);
		
		/* Get a Map Code */
        Bundle bn = new Bundle();
        bn = getIntent().getExtras();
        M = (Integer) bn.get("MapCode");
	}
	
	@Override
	public void onBackPressed() {
          this.finish();
	}
	
	public static Integer getMapCode() {return M;}
}
