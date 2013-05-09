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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ItemBattleActivity extends Activity {
	
	private Integer AreaCode = 0; //Temporary attribute for storing Area Code
	private Integer EnemyPercent = 0; //Temporary attribute for storing enemy HP percentage
	private Activity act = this; //Current activity
	private Monster_Liar M;
	private static boolean IsFinish = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.itembattle_activity);

		/* Get a bundle to differentiate Wild Area and Stadium */
        
		try { 
	        Bundle bn = new Bundle();
	        bn = getIntent().getExtras();
	        HashMap<String, Object> getobj = new HashMap<String, Object>();
	        getobj = (HashMap<String, Object>) bn.getSerializable("bundleobject");
	        AreaCode = (Integer) getobj.get("AreaCode");
	        EnemyPercent = (Integer) getobj.get("EnemyPercent");
	        M =  (Monster_Liar) getobj.get("enemyData");
	    } catch (Exception e) {
	        Log.e("Err", e.getMessage());
	    }
        
        
        BattleView.setItemAct(this);
        
        /* Area Code 1 = Wild Area, Area Code 2 = Stadium */
        /* Area Code 1 can use every Normal Items + Ball, Area Code 2 can only use Normal Items */
    	ListView view = (ListView) findViewById(R.id.BattleitemList);
    	view.setVisibility(View.VISIBLE);
    	
    	ArrayList<Item> tempitemList = MainGameActivity.getPlayerFromBackpack().GetItem();
    	final ArrayList<Item> itemList = new ArrayList<Item>();
    	
    	for (int i = 0; i < tempitemList.size(); i++){
    		if (tempitemList.get(i).GetNItem() > 0){
    			if ((tempitemList.get(i).GetEffect() == Effect.healing) || (tempitemList.get(i).GetEffect() == Effect.paralyzing) 
    			|| (tempitemList.get(i).GetEffect() == Effect.hypnotize) || (tempitemList.get(i).GetEffect() == Effect.ressurecting)
    			|| (tempitemList.get(i).GetEffect() == Effect.healingMP)){
    				itemList.add(tempitemList.get(i));
    			}
    			
    			if ((AreaCode == 1) && (tempitemList.get(i).GetEffect() == Effect.catching)){
    				itemList.add(tempitemList.get(i));    				
    			}
    		}
    	}
    	
    	
    	/* Parse these Item List by several conditions */
    	/* 1. NItem > 0 */
    	/* 2. Depends on Area Code */
    	ArrayAdapter<Item> arrayAdapter = new ArrayAdapter<Item>(this,android.R.layout.simple_list_item_1, itemList);
    	
    	view.setAdapter(arrayAdapter); 
    	
    	view.setOnItemClickListener(new AdapterView.OnItemClickListener() {

    	    @Override
    	    public void onItemClick(AdapterView<?> parent, final View view,
    	        int position, long id) {
    	      final Item item = (Item) parent.getItemAtPosition(position);
    	      if (item.GetNItem() > 0){ /* Showing effect iff NItem > 0 */
    	      for (int i = 0; i < itemList.size(); i++){
    	    	  if (item.GetID() == itemList.get(i).GetID()) {
    	    		  if ((item.GetEffect() == Effect.healing) || 
    	    				  (item.GetEffect() == Effect.paralyzing) || 
    	    				  (item.GetEffect() == Effect.hypnotize) ||
    	    				  (item.GetEffect() == Effect.ressurecting) ||
    	    				  (item.GetEffect() == Effect.healingMP)){
    	    			  /* Select Monster */
    	  				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(view.getContext());
    	                
    	  				ArrayList<String> monsterName = new ArrayList<String>();
    	  				for (int j = 0; j < 6; j++){
    	  					if (MainGameActivity.getPlayerFromBackpack().GetMonster(j).getID() != 0)
    	  						monsterName.add("Slot " + j + " . " + MainGameActivity.getPlayerFromBackpack().GetMonster(j).getName());
    	  				}
    	  				
    	  				if (monsterName.size() == 0){
    						  /* Alert dialog */
    							// set title
    							alertDialogBuilder.setTitle("You have no suitable Poke!");
    							alertDialogBuilder
    							.setCancelable(false)
    							.setPositiveButton("Close",new DialogInterface.OnClickListener() {
    								public void onClick(DialogInterface dialog,int id) {
    								}
    							  });   						
    							// create alert dialog
    							AlertDialog alertDialog = alertDialogBuilder.create();
    							// show it
    							alertDialog.show(); 					
    	  				} else {
    	  				final String[] writeToScreen = new String[monsterName.size()];
    	  				for (int j = 0; j < monsterName.size(); j++)
    	  					writeToScreen[j] = monsterName.get(j);
    	  				// set title
    	  				alertDialogBuilder.setTitle("Select your Poke");
    	  				alertDialogBuilder.setItems(writeToScreen, new DialogInterface.OnClickListener() {
    	  			        public void onClick(DialogInterface dialogInterface, int items) {
    	  			            /* Check Poke by Slot Number, Parse String */
    	                        String[] parse = writeToScreen[items].split(" ");
    	                        /* parse[1] = Slot id */
    	  			        	/* Action here - Show next confirmation message? */
    	                        MainGameActivity.getPlayerFromBackpack().SetMonster(Integer.valueOf(parse[1]),
    	                        		     item.UseItem(MainGameActivity.getPlayerFromBackpack().GetMonster(Integer.valueOf(parse[1])), view.getContext())
    	                        );
    	  			        	/* if Success */
    	  			        	dialogInterface.dismiss();
    	  			        	BattleView.setItemAct(null);
    	  			        	act.finish();
    	  			        }
    	  			    });
    					alertDialogBuilder.setCancelable(false);
    					alertDialogBuilder.setPositiveButton("Close",new DialogInterface.OnClickListener() {
    						public void onClick(DialogInterface dialog,int id) {
    						}
    					  });
    					// create alert dialog
    					AlertDialog alertDialog = alertDialogBuilder.create();
    					// show it
    					alertDialog.show();
    					
    	  				}
    	    		  }

    	    		  /* Note that catching will be called depends on Area Code */
    	    		  if ((item.GetEffect() == Effect.catching) && (AreaCode == 1)){
    	    				int randomNumber = Utilities.rand(0, 120);
    	    				if (randomNumber >= EnemyPercent){
    	    					/* Success */
    	    					Monster_Sendiri M3 = new Monster_Sendiri(M.getID(),"Poke",MainGameActivity.ListSpecies.get(M.getID()),M.getLevel(),M.getElement(),
		                                M.minExp(M.getLevel()),MainGameActivity.ListSpecies.get(M.getID()).getLifeSpan(),MainGameActivity.ListSkill);

								Intent is = new Intent(act,MonsterActivity.class);
								LinkedHashMap<String, Object> obj = new LinkedHashMap<String, Object>();
								obj.put("Monster", M3);
								
								Bundle b2 = new Bundle();
								b2.putSerializable("bundleobject2", obj);
								is.putExtras(b2);
			  			        BattleView.setItemAct(null);
								act.startActivity(is);
								IsFinish = true;
		    	    			act.finish();
    	    				} else {
    	    			  /* finish */
    	    			  //dialogInterface.dismiss();
	  			          BattleView.setItemAct(null);
    	    			  act.finish();
    	    			}
    	    		  }
    	    		  
    	    	  }
    	      }
    	      view.animate().setDuration(1000).alpha(0)
    	          .withEndAction(new Runnable() {
    	            @Override
    	            public void run() {
    	              view.setAlpha(1);
    	            }
    	          });
    	    }
    	    }

    	  });
    	
	}
	
	public static boolean getIsFinish() {return IsFinish; }
	public static void setIsFinish(boolean finish) {IsFinish = finish; }
	
}
