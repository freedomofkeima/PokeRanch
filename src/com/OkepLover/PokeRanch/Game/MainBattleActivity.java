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
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

public class MainBattleActivity extends Activity {
	
	private static final String TAG = MainBattleActivity.class.getSimpleName();
	private DisplayMetrics metrics;	
	private BattleView BattleView; 
	private Monster_Liar M2;
	private Species species;
	public ArrayList<Skill> ListSkill = new ArrayList<Skill>();
	private ArrayList<Species>ListSpecies = new ArrayList<Species>();
	private Integer siangCode;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//ga penting amat
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//ga penting amat
		
		DrawableManager.initInstance(getApplicationContext());		
		Log.d(TAG,"start game activity");
		metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics); // dapetin ukuran layar
		
		
		Bundle bn = new Bundle();
		bn = getIntent().getExtras();
		siangCode = (Integer) bn.get("SiangCode");
		Log.d("Kode",siangCode + "");
		//createSpecies();
		try {
			ListSkill = Skill.loadSkill(this);
			Log.d("Load","Load Skill");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			ListSpecies = Species.LoadSpecies(this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		M2 = RandomMonster();

		BattleView = new BattleView(this,metrics.widthPixels,metrics.heightPixels, M2);
		setContentView(BattleView);	
	}
	
	private Monster_Liar RandomMonster() {
		// TODO Auto-generated method stub
		Element element;
		Species species;
		Integer level;
		Integer lootMoney;
		Integer getExp;
		//cek kondisi luar
		if (siangCode == 0){
			element = Element.fire;
			int random = Utilities.rand(0, 9);
			species = ListSpecies.get(random*2);
		} else {//kalo malam
			int random = Utilities.rand(1, 2);
			if (random == 1){
				element = Element.grass;
			} else {
				element = Element.water;
			}
			random = Utilities.rand(0, 9);
			species = ListSpecies.get(random*2+1);
		}
		//random level
		int playerLevel = MainGameActivity.getPlayerFromBackpack().GetMonster(0).getLevel(); 
		int random = Utilities.rand(playerLevel/2, playerLevel);
		level = random;
		lootMoney = level * 10;
		getExp = level * 5;
		return new Monster_Liar(species.getId(),species.getName(),level,species,element,getExp,lootMoney,ListSkill);
	}

	public void setListSkill(ArrayList<Skill> list){
		ListSkill = list; 
	}
	
	@Override
	protected void onPause() {
		Log.d(TAG,"onPause()");
		BattleView.thread.setRunning(false); //matiin thread
		super.onPause();			
	}	
}
