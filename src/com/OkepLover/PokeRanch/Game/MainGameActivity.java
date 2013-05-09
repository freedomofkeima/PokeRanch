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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class MainGameActivity extends Activity implements MainGameView.BattleListener {
	
	private static final String TAG = MainGameActivity.class.getSimpleName();
	private DisplayMetrics metrics;	
	private MainGameView mainGameView;
	private static Button UP;
	private static Button DOWN;
	private static Button LEFT;
	private static Button RIGHT;
	private static Button LOVE;
	private static Button SPECIAL;
	private static Button PLACE;
	private static Player d;
	private static Integer ShopCode = 0;
	public static ArrayList<Skill>ListSkill = new ArrayList<Skill>();
	public static ArrayList<Species>ListSpecies = new ArrayList<Species>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		DrawableManager.initInstance(getApplicationContext());
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);		
		metrics = new DisplayMetrics();
		
		getWindowManager().getDefaultDisplay().getMetrics(metrics); // dapetin ukuran layar
		
		try { 
	        Bundle bn = new Bundle();
	        bn = getIntent().getExtras();
	        HashMap<String, Object> getobj = new HashMap<String, Object>();
	        getobj = (HashMap<String, Object>) bn.getSerializable("bundleobject");
	        d = (Player) getobj.get("hashmap");
	    } catch (Exception e) {
	        Log.e("Err", e.getMessage());
	    }
		
		//loader database
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
		
		setContentView(R.layout.main_game_activity);
		
		mainGameView = (MainGameView) findViewById(R.id.main_game_activity);
		UP = (Button) findViewById(R.id.buttonUP);
		DOWN = (Button) findViewById(R.id.buttonDOWN);
		LEFT = (Button) findViewById(R.id.buttonLEFT);
		RIGHT = (Button) findViewById(R.id.buttonRIGHT);
		LOVE = (Button) findViewById(R.id.LoveLove);
		SPECIAL = (Button) findViewById(R.id.specialButton);
		PLACE = (Button) findViewById(R.id.placeButton);
		PLACE.setVisibility(Button.INVISIBLE);
		SPECIAL.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if(mainGameView.getGrid(mainGameView.getBucket().getXV()/20-1, mainGameView.getBucket().getYV()/20) == 7 ||
				   mainGameView.getGrid(mainGameView.getBucket().getXV()/20+1, mainGameView.getBucket().getYV()/20) == 7 ||
				   mainGameView.getGrid(mainGameView.getBucket().getXV()/20, mainGameView.getBucket().getYV()/20-1) == 7 ||
				   mainGameView.getGrid(mainGameView.getBucket().getXV()/20, mainGameView.getBucket().getYV()/20+1) == 7) {
					mainGameView.setSkillMap("cut");
					mainGameView.PilihMonster();
				} else if(mainGameView.getGrid(mainGameView.getBucket().getXV()/20-1, mainGameView.getBucket().getYV()/20) == 9 ||
				   mainGameView.getGrid(mainGameView.getBucket().getXV()/20+1, mainGameView.getBucket().getYV()/20) == 9 ||
				   mainGameView.getGrid(mainGameView.getBucket().getXV()/20, mainGameView.getBucket().getYV()/20-1) == 9 ||
				   mainGameView.getGrid(mainGameView.getBucket().getXV()/20, mainGameView.getBucket().getYV()/20+1) == 9) {
					mainGameView.setSkillMap("push");
					mainGameView.PilihMonster();
				} else if(mainGameView.getGrid(mainGameView.getBucket().getXV()/20-1, mainGameView.getBucket().getYV()/20) == 8 ||
				   mainGameView.getGrid(mainGameView.getBucket().getXV()/20+1, mainGameView.getBucket().getYV()/20) == 8 ||
				   mainGameView.getGrid(mainGameView.getBucket().getXV()/20, mainGameView.getBucket().getYV()/20-1) == 8 ||
				   mainGameView.getGrid(mainGameView.getBucket().getXV()/20, mainGameView.getBucket().getYV()/20+1) == 8) {
					mainGameView.setSkillMap("berenang");
					mainGameView.PilihMonster();
					if (mainGameView.getBucket().getTerra().equals("air"))
						mainGameView.getBucket().setTerra("darat");
					else 
						mainGameView.getBucket().setTerra("air");
				} else if(mainGameView.getGrid(mainGameView.getBucket().getXV()/20-1, mainGameView.getBucket().getYV()/20) == 99 ||
				   mainGameView.getGrid(mainGameView.getBucket().getXV()/20+1, mainGameView.getBucket().getYV()/20) == 99 ||
				   mainGameView.getGrid(mainGameView.getBucket().getXV()/20, mainGameView.getBucket().getYV()/20-1) == 99 ||
				   mainGameView.getGrid(mainGameView.getBucket().getXV()/20, mainGameView.getBucket().getYV()/20+1) == 99) {
					mainGameView.ShowDialog();
					UP.setVisibility(Button.INVISIBLE);
					DOWN.setVisibility(Button.INVISIBLE);
					LEFT.setVisibility(Button.INVISIBLE);
					RIGHT.setVisibility(Button.INVISIBLE);
					SPECIAL.setVisibility(Button.INVISIBLE);
					LOVE.setVisibility(Button.INVISIBLE);
					if (mainGameView.getBucket().getMapActive() == 1) // kamar
						PLACE.setText("Bobo");
					else if (mainGameView.getBucket().getMapActive() == 4) //stadium
						PLACE.setText("Participate");
					else if (mainGameView.getBucket().getMapActive() == 5) // kombinatorium
						PLACE.setText("Kombinasi");
					else if (mainGameView.getBucket().getMapActive() == 6) // shop
						PLACE.setText("Transaksi");
					PLACE.setVisibility(Button.VISIBLE);
				}
			}
		});
		PLACE.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (PLACE.getText().toString().equals("Transaksi")){
					TransaksiGlodok();
				} else if (PLACE.getText().toString().equals("Bobo")) {
					Bobok();
				} else if (PLACE.getText().toString().equals("Participate")) {
					mainGameView.PilihBid();
					PLACE.setText("Mulai yuk!!");
				} else if (PLACE.getText().toString().equals("Kombinasi")) {
					SangatKombinasi();
				}  else if (PLACE.getText().toString().equals("Mulai yuk!!")) {
					if (mainGameView.getStartStadium()){
						StartBattle();
						mainGameView.setStartStadium(false);
					}
				} 
			}
		});
		LOVE.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				BackPack();
			}
		});
		UP.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				// TODO Auto-generated method stub
				if (mainGameView.getBucket().getStep() == 0) {
					if(mainGameView.getGrid(mainGameView.getBucket().getXV()/20, mainGameView.getBucket().getYV()/20-1) == 2) 
						mainGameView.TeleportToKamar();
					else if(mainGameView.getGrid(mainGameView.getBucket().getXV()/20, mainGameView.getBucket().getYV()/20-1) == 3)
						mainGameView.TeleportToShop();
					else if(mainGameView.getGrid(mainGameView.getBucket().getXV()/20, mainGameView.getBucket().getYV()/20-1) == 4)
						mainGameView.TeleportToWild();
					else if(mainGameView.getGrid(mainGameView.getBucket().getXV()/20, mainGameView.getBucket().getYV()/20-1) == 5)
						mainGameView.TeleportToStadium();
					else if(mainGameView.getGrid(mainGameView.getBucket().getXV()/20, mainGameView.getBucket().getYV()/20-1) == 6)
						mainGameView.TeleportToKombinatorium();
					if (mainGameView.getGrid(mainGameView.getBucket().getXV()/20, mainGameView.getBucket().getYV()/20-1) == 1) {
						mainGameView.getBucket().moveUP();
						Log.d("Location : ",mainGameView.getBucket().getXV()+", "+mainGameView.getBucket().getYV());
						Log.d("Isi : ",""+mainGameView.getGrid(mainGameView.getBucket().getXV()/40-1,mainGameView.getBucket().getYV()/40-1));
					}
				}
				return true;
			}
		});
		
		DOWN.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				// TODO Auto-generated method stub
				if (mainGameView.getBucket().getStep() == 0) {
					if(mainGameView.getGrid(mainGameView.getBucket().getXV()/20, mainGameView.getBucket().getYV()/20+1) == 2) 
							mainGameView.TeleportToCity();
					else if (mainGameView.getGrid(mainGameView.getBucket().getXV()/20, mainGameView.getBucket().getYV()/20+1) == 1) {
						mainGameView.getBucket().moveDown();
						Log.d("Location : ",mainGameView.getBucket().getXV()+", "+mainGameView.getBucket().getYV());
						Log.d("Isi : ",""+mainGameView.getGrid(mainGameView.getBucket().getXV()/40-1,mainGameView.getBucket().getYV()/40-1));
					}
				}
				return true;
			}
		});
		
		LEFT.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				// TODO Auto-generated method stub
				if (mainGameView.getBucket().getStep() == 0)
					if (mainGameView.getGrid(mainGameView.getBucket().getXV()/20-1, mainGameView.getBucket().getYV()/20) == 1) {
						mainGameView.getBucket().moveLeft();
						Log.d("Location : ",mainGameView.getBucket().getXV()+", "+mainGameView.getBucket().getYV());
						Log.d("Isi : ",""+mainGameView.getGrid(mainGameView.getBucket().getXV()/40-1,mainGameView.getBucket().getYV()/40-1));
					}
				return true;
			}
		});
		
		
		RIGHT.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				// TODO Auto-generated method stub
				if (mainGameView.getBucket().getStep() == 0) 
					if (mainGameView.getGrid(mainGameView.getBucket().getXV()/20+1, mainGameView.getBucket().getYV()/20) == 1) {
						mainGameView.getBucket().moveRight();
						Log.d("Location : ",mainGameView.getBucket().getXV()+", "+mainGameView.getBucket().getYV());
						Log.d("Isi : ",""+mainGameView.getGrid(mainGameView.getBucket().getXV()/40-1,mainGameView.getBucket().getYV()/40-1));
					}
				return true;
			}
		});
	}
	
	@Override
	protected void onPause() {
		Log.d(TAG,"onPause()");
		mainGameView.thread.setRunning(false); //matiin thread
		super.onPause();			
	}

	@Override
	protected void onResume() {
		super.onResume();
		ShopCode = 0;
	}
	
	public void TransaksiGlodok(){
		this.onPause();
		Intent Is = new Intent(this, ShopActivity.class);
		this.startActivity(Is);
	}
	
	public void SangatKombinasi() {
		this.onPause();
		Intent Is = new Intent(this, CombinatoriumActivity.class);
		this.startActivity(Is);
	}
	public void Bobok() {
		mainGameView.LagiBobo();
		d.SleepPlayer();
		mainGameView.setJam(mainGameView.getJam() + 1);
	}
	
	public void BackPack() {
		this.onPause();
		Intent Is = new Intent(this, InGameMenuActivity.class);
		if (mainGameView.getBucket().getMapActive() == 1) Is.putExtra("MapCode",2); /* Depends on Home ID */
		else Is.putExtra("MapCode",3);
		d.SetStepCounter(d.GetStepCounter()+mainGameView.getBucket().getStepCounter());
		mainGameView.getBucket().setStepCounter((Integer) 0);
		this.startActivity(Is);
	}
	
	public static Player getPlayerFromBackpack(){ return d; }
	public static Button getUP() {return UP;}
	public static Button getDOWN() {return DOWN;}
	public static Button getLEFT() {return LEFT;}
	public static Button getRIGHT() {return RIGHT;}
	public static Button getSPECIAL() {return SPECIAL;}
	public static Button getLOVE() {return LOVE;}
	public static Button getPlace() {return PLACE;}
	
	@Override
	public void StartBattle() {
		// TODO Auto-generated method stub
		this.onPause();
		Intent i = new Intent(this,MainBattleActivity.class);
		if (mainGameView.getLightArea()) i.putExtra("SiangCode", 1);
		else i.putExtra("SiangCode", 0);
		if (mainGameView.getBucket().getTerra().equals("darat")) i.putExtra("WaterCode", 0);
		else i.putExtra("WaterCode", 1);
		
		this.startActivity(i);
	}
	
	public static Integer getShopCode() {return ShopCode;}
	public static void setShopCode(Integer _x) { ShopCode = _x; }
}
