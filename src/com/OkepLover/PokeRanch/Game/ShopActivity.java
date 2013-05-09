/*
 * [IF2032] Pemrograman Beroirentasi Obyek
 * Mobile PokeRanch
 */
package com.OkepLover.PokeRanch.Game;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Window;

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

public class ShopActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.shop_fragment);
	}
	

}
