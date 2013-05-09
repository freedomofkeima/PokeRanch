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

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class CombinatoriumActivity extends Activity {

	private Spinner monster1, monster2;
	private Button btnCombine;
	private EditText pokeName;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.combinatorium_activity);
		
		addItemsOnSpinner();
		addListenerOnButton();

	}
	
	//add items into spinner dynamically
	public void addItemsOnSpinner(){
		/* Monster 1st selection */
		monster1 = (Spinner) findViewById(R.id.Monster1);
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < 6; i++){
			if (MainGameActivity.getPlayerFromBackpack().GetMonster(i).getID() != 0) list.add("Slot " + i + " . " +  MainGameActivity.getPlayerFromBackpack().GetMonster(i).getName() );
		}
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
			android.R.layout.simple_spinner_item, list);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		monster1.setAdapter(dataAdapter);
		
		/* Monster 2nd selection */
		monster2 = (Spinner) findViewById(R.id.Monster2);
		ArrayList<String> list2 = new ArrayList<String>();
		for (int i = 0; i < 6; i++){
			if (MainGameActivity.getPlayerFromBackpack().GetMonster(i).getID() != 0) list2.add("Slot " + i + " . " +  MainGameActivity.getPlayerFromBackpack().GetMonster(i).getName() );
		}
		ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this,
			android.R.layout.simple_spinner_item, list2);
		dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		monster2.setAdapter(dataAdapter2);
	}
	  
	  //get the selected dropdown list value
	  public void addListenerOnButton(){
			monster1 = (Spinner) findViewById(R.id.Monster1);		
			monster2 = (Spinner) findViewById(R.id.Monster2);
			btnCombine = (Button) findViewById(R.id.btnCombine);
			
			final Context ctx = this;
			btnCombine.setOnClickListener(new OnClickListener() {
				 
				  @Override
				  public void onClick(View v) {
			   TextView textError = (TextView) findViewById(R.id.messageCombine);
	           String[] parts1 = String.valueOf(monster1.getSelectedItem()).split(" ");
	           String[] parts2 = String.valueOf(monster2.getSelectedItem()).split(" ");
	          
			   
				/* Combination code here */
			    if (parts1[1].equals(parts2[1])){
					/* Showing message that Combination is not valid */
					textError.setText("You cannot combine two same monsters!");
					textError.setTextColor(Color.RED);
			    } else {
			    	pokeName = (EditText) findViewById(R.id.PokeName);
			    	if (pokeName.getText().toString().equals("")){
			    		/* Showing message that Name must not empty */
						textError.setText("Your naming is not valid!");
						textError.setTextColor(Color.RED);			    		
			    	} else {
			    	
				    	textError.setText("Combining is successful!");
				    	textError.setTextColor(Color.GREEN);
				    	/* Valid Combination */
				    	MainGameActivity.getPlayerFromBackpack().GetMonster(Integer.parseInt(parts1[1])).Combine(MainGameActivity.getPlayerFromBackpack().GetMonster(Integer.parseInt(parts2[1])));
				    	//rename Monster
				    	MainGameActivity.getPlayerFromBackpack().GetMonster(Integer.parseInt(parts1[1])).setName(pokeName.getText().toString());
				    	TextView monsterHasil = (TextView) findViewById(R.id.MonsterHasil);
				    	/* Set Text result here */
				    	monsterHasil.setText("Name : " + MainGameActivity.getPlayerFromBackpack().GetMonster(Integer.parseInt(parts1[1])).getName()
				    			  +"\nSpecies ID : " + MainGameActivity.getPlayerFromBackpack().GetMonster(Integer.parseInt(parts1[1])).getID()
				    			  +"\nLevel / Age : " + MainGameActivity.getPlayerFromBackpack().GetMonster(Integer.parseInt(parts1[1])).getLevel() + " / " + MainGameActivity.getPlayerFromBackpack().GetMonster(Integer.parseInt(parts1[1])).getlifeSpan()
				    			  + "\nHP : " + MainGameActivity.getPlayerFromBackpack().GetMonster(Integer.parseInt(parts1[1])).getCurrentHP() + "/" + MainGameActivity.getPlayerFromBackpack().GetMonster(Integer.parseInt(parts1[1])).getMaxHP()
				    			  + "  MP : " + MainGameActivity.getPlayerFromBackpack().GetMonster(Integer.parseInt(parts1[1])).getCurrentMP() + "/" + MainGameActivity.getPlayerFromBackpack().GetMonster(Integer.parseInt(parts1[1])).getMaxMP());
				    	/* Dismiss 2nd Monster */
				    	MainGameActivity.getPlayerFromBackpack().GetMonster(Integer.valueOf(parts2[1])).setID(Integer.parseInt(parts1[1]));
				    	addItemsOnSpinner();
			    	}
			    }

			}
			 
				});
	  }
	  
	  public void backtoMap(View v){
		  super.finish();
	  }
}
