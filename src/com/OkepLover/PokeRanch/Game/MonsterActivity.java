package com.OkepLover.PokeRanch.Game;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


public class MonsterActivity extends Activity {

	private Monster_Sendiri M;
	private Spinner slot;
	private EditText pokeName;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.egg_dialog);

	try { 
        Bundle bn = new Bundle();
        bn = getIntent().getExtras();
        HashMap<String, Object> getobj = new HashMap<String, Object>();
        getobj = (HashMap<String, Object>) bn.getSerializable("bundleobject2");
        M = (Monster_Sendiri) getobj.get("Monster");
    } catch (Exception e) {
        Log.e("Err", e.getMessage());
    }
	
	addItemsOnSpinner();
	
	}
	
	//add items into spinner dynamically
		public void addItemsOnSpinner(){
			/* Slot selection */
			slot = (Spinner) findViewById(R.id.MonsterSlot);
			ArrayList<String> list = new ArrayList<String>();
			for (int i = 0; i < 6; i++){
				if (MainGameActivity.getPlayerFromBackpack().GetMonster(i).getID() != 0) list.add("Replace Slot " + i + " - " +  MainGameActivity.getPlayerFromBackpack().GetMonster(i).getName() );
				else list.add("Replace Slot " + i + " - Empty Slot" );
			}
			ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, list);
			dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			slot.setAdapter(dataAdapter);
			
		}
	
	public void FinishConfirm(View v){
		  TextView textError = (TextView) findViewById(R.id.messageEgg);
		  pokeName = (EditText) findViewById(R.id.PokeNameDialog);
          String[] parts1 = String.valueOf(slot.getSelectedItem()).split(" ");
		  
		  if (pokeName.getText().toString().equals("")){
	    		/* Showing message that Name must not empty */
				textError.setText("Your naming is not valid!");
				textError.setTextColor(Color.RED);			    		
	    	} else {
		    	textError.setText("Combining is successful!");
		    	textError.setTextColor(Color.GREEN);
                /* Set Monster to Player */
		    	M.setName(pokeName.getText().toString());
		    	MainGameActivity.getPlayerFromBackpack().SetMonster(Integer.valueOf(parts1[2]), M);
		    	/* Finish */
		    	this.finish();
	    	}
	}
}
