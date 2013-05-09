/*
 * [IF2032] Pemrograman Beroirentasi Obyek
 * Mobile PokeRanch
 */
package com.OkepLover.PokeRanch.Game;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

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

public class MonsterMenu extends Activity {
	
	private TextView[] MonsterHolder; //TextView holder
	private Player p; //Saving player for temporary
	private Integer MonsterCode = 0; //Variable for MonsterCode
	private Activity act = this; //Address to this Activity
	/* 0 = Monster from backpack, 1 = Monster from battle, 2 = Monster for Item */
	
	  @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);

	        requestWindowFeature(Window.FEATURE_NO_TITLE);
	        setContentView(R.layout.monster_menu);
	        
	        Bundle bn = new Bundle();
	        bn = getIntent().getExtras();
	        MonsterCode = (Integer) bn.get("MonsterCode");
	        
	        Button backButton = (Button) findViewById(R.id.back_to_main_from_monster);
	        
	        if (MonsterCode == 1) {
	        	BattleView.setMonsterAct(this);
	        	//backButton.setVisibility(View.GONE);
	        }
	        
	        p = MainGameActivity.getPlayerFromBackpack(); /* Get current Player data */
	        
	        /* Initializing all TextView holder */
	        MonsterHolder = new TextView [6];
	         
	        MonsterHolder[0]  = (TextView) findViewById(R.id.Monster0);
	        MonsterHolder[1] = (TextView) findViewById(R.id.Monster1);
	        MonsterHolder[2]  = (TextView) findViewById(R.id.Monster2);
	        MonsterHolder[3]  = (TextView) findViewById(R.id.Monster3);
	        MonsterHolder[4]  = (TextView) findViewById(R.id.Monster4);
	        MonsterHolder[5]  = (TextView) findViewById(R.id.Monster5);
	        
	        renderInfo();
	  }
	  
	    public void backtoMainfromMonster(View v){
	    	super.finish();
	    }
	    
	    public void CodeZero(View v){
	    	processSignal(0);
	    }
	    
	    public void CodeOne(View v){
	    	processSignal(1);	    	
	    }
	    
	    public void CodeTwo(View v){
	    	processSignal(2);	    	
	    }
	    
	    public void CodeThree(View v){
	    	processSignal(3);	    	
	    }
	    
	    public void CodeFour(View v){
	    	processSignal(4);	    	
	    }
	    
	    public void CodeFive(View v){
	    	processSignal(5);	    	
	    }
	    
	    public void processSignal(final Integer x){
	    	/* Set selection color to the screen */
	    	for (int i = 0; i < 6; i ++)
	    		if ((x == i) && (p.GetMonster(i).getID() != 0)) MonsterHolder[i].setBackgroundResource(R.drawable.monster_border);
	    		   else MonsterHolder[i].setBackgroundColor(Color.parseColor("#CCDDDDDD"));   	
            if ((p.GetMonster(x).getID() != 0) && (x != 0)){
            	
            	AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
              
				// set title
				alertDialogBuilder.setTitle(  p.GetMonster(x).getName() + " - Level " + p.GetMonster(x).getLevel() );

            	if (MonsterCode == 0){
				// set dialog message
			
	               // Setting "Set" Button
				alertDialogBuilder.setNegativeButton("Set", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    // User pressed Set button. Write Logic Here
                     MainGameActivity.getPlayerFromBackpack().SetPrimary(x);
                     
         	        p = MainGameActivity.getPlayerFromBackpack(); /* Get current Player data */
                    renderInfo();
                    }
                });
 
                // Setting "Dismiss" Button
                
				alertDialogBuilder.setNeutralButton("Dismiss", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    // User pressed Dismiss button. Write Logic Here
                    MainGameActivity.getPlayerFromBackpack().GetMonster(x).setID(0);
                    MonsterHolder[x].setBackgroundColor(Color.parseColor("#CCDDDDDD"));
                    
        	        p = MainGameActivity.getPlayerFromBackpack(); /* Get current Player data */
                    renderInfo();
                    }
                });
				
            	} else if (MonsterCode == 1){
            		/* Message for battle mode */
 	               // Setting "Set" Button
    				alertDialogBuilder.setNegativeButton("Change", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                        // User pressed Set button. Write Logic Here
                         MainGameActivity.getPlayerFromBackpack().SetPrimary(x);
                         /* Finish, don't forget to refresh your MonsterList */
                         BattleView.setMonsterAct(null);
             	         act.finish();
                        }
                    });          		
            		
            	} else if (MonsterCode == 2) {
            		/* Message for item usage */
            	}
	 
                // Setting "Back" Button
				alertDialogBuilder.setPositiveButton("Back", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
				
					// create alert dialog
					AlertDialog alertDialog = alertDialogBuilder.create();
					
					// show it
					alertDialog.show();
            }
            
	    	
	    }
	    
	    public void renderInfo(){
	    	/* Show Monster data into TextView */
	    	for (int i = 0; i < 6; i ++)
	    	  if (p.GetMonster(i).getID() != 0)	MonsterHolder[i].setText(
	    			  "Name : " + p.GetMonster(i).getName()
	    			  +"\nSpecies ID : " + p.GetMonster(i).getID()
	    			  +"\nLevel / Age : " + p.GetMonster(i).getLevel() + " / " + p.GetMonster(i).getlifeSpan()
	    			  + "\nHP : " + p.GetMonster(i).getHP() + "/" + p.GetMonster(i).getMaxHP()
	    			  + "  MP : " + p.GetMonster(i).getMP() + "/" + p.GetMonster(i).getMaxMP() );
	    	  else MonsterHolder[i].setText("Empty Slot");
	    }
}
