/*
 * [IF2032] Pemrograman Beroirentasi Obyek
 * Mobile PokeRanch
 */

package com.OkepLover.PokeRanch.Game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

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

public class NewPlayerMenu extends Activity {
	
	private Player P; //Create a new player from Player class
	private EditText PName; //For inserting new Player name
	private EditText PPokeName; //For inserting new Poke name
	private RadioButton Poke1; //Choosing 1st Poke
	private RadioButton Poke2; //Choosing 2nd Poke
	private RadioButton Poke3; //Choosing 3nd Poke
	private RadioGroup radioGroup; //For grouping radio button
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.new_game_activity);

		PName = (EditText) findViewById(R.id.editText1);
		PPokeName = (EditText) findViewById(R.id.editText2);
		radioGroup = (RadioGroup) findViewById(R.id.radioPoke);
		Poke1 = (RadioButton) findViewById(R.id.radioButton1);
		Poke2 = (RadioButton) findViewById(R.id.radioButton2);
		Poke3 = (RadioButton) findViewById(R.id.radioButton3);
		
		/* Initialize a new Player */
		try {
			P = new Player(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
		PName.setText(P.GetName().toCharArray(), 0, P.GetName().length());
		
		radioGroup.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				RadioButton getButton = (RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());
				/* Set Text into XML ? */
				PPokeName.setText(getButton.getText());
			}
		});
		
	}
	
	public void confirmNameButton(View v){
		/* Poke Choices */
		// get selected radio button from radioGroup
	    int selectedId = radioGroup.getCheckedRadioButtonId();
	    RadioButton getButton = (RadioButton) findViewById(selectedId);
		//Log.d("Selected ID",getButton.getText().toString());
		//Log.d("Selected name",PPokeName.getText().toString());
		/* At this point, we need to check A..Z, a..z only */
		String S = new String(PName.getText().toString());
		String S2 = new String(PPokeName.getText().toString());
		//if (S.matches("^[a-zA-Z]*$")){
	    /* At this point, we need to check whether filename already exists */
		
		/* At this point, we need to initialize P.name with this name */
		P.SetName(S);
		P.SetFileName(S);
		
		ArrayList<Skill> ListSkill = new ArrayList<Skill>();
		try {
			ListSkill = Skill.loadSkill(this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ArrayList<Species> ListSpecies = new ArrayList<Species>();
		try {
			ListSpecies = Species.LoadSpecies(this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Monster_Sendiri M = new Monster_Sendiri();
		
		if (getButton.getText().toString().equals("Pixie")){
			M = new Monster_Sendiri(1,S2,ListSpecies.get(0),Integer.valueOf(5), Element.grass,0,ListSpecies.get(0).getLifeSpan(),ListSkill);
		}
		if (getButton.getText().toString().equals("Dragone")){
			M = new Monster_Sendiri(2,S2,ListSpecies.get(1),new Integer(5), Element.fire,0,ListSpecies.get(1).getLifeSpan(),ListSkill);
		}
		if (getButton.getText().toString().equals("Mochie")){
			M = new Monster_Sendiri(3,S2,ListSpecies.get(2),new Integer(5), Element.water,0,ListSpecies.get(2).getLifeSpan(),ListSkill);
		}
		
		//cek skill yg dimiliki monster
		for (int i = 0; i < M.getListSkill().size();i++)
			Log.d("Skill Player",M.getListSkill().get(i).getName());
		Log.d("Stat player",M.getLevel().toString());
		Log.d("Stat player",M.getHP().toString());
		//M.setName(S2);
		
		Log.d("Nama 1:", M.getName());
	    P.SetMonster(0, M); /* Set first player Monster */
		/* At this point, go to next panel (Send data to next panel) */
		Intent i = new Intent(this,MainGameActivity.class);
		LinkedHashMap<String, Object> obj = new LinkedHashMap<String, Object>();
		obj.put("hashmap", P);
		
		Bundle b = new Bundle();
		b.putSerializable("bundleobject", obj);
		i.putExtras(b);
		
		this.startActivity(i);
		if (MenuActivity.mediaPlayer != null){
			MenuActivity.mediaPlayer.stop();
			MenuActivity.mediaPlayer = null;
		}
	    this.finish();	
		
	}
	
    public void backtoMain(View v){
		Intent i = new Intent(this,MenuActivity.class);
		this.startActivity(i);
    	super.finish();
    }
    
	@Override
	public void onBackPressed() {
		Intent i = new Intent(this,MenuActivity.class);
		this.startActivity(i);
    	super.finish();
	}

}
