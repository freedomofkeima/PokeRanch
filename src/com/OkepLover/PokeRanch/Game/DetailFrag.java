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

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class DetailFrag extends Fragment{
	
@Override
public void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
}

@Override
public void onActivityCreated(Bundle savedInstanceState) {
super.onActivityCreated(savedInstanceState);
}

@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
View view = inflater.inflate(R.layout.detail_fragment, container, false);
return view;
}

public void setText(String item) {
ListView viewA = (ListView) getView().findViewById(R.id.itemList);
viewA.setVisibility(View.INVISIBLE);

TextView view = (TextView) getView().findViewById(R.id.captain);
/* showing textview to screen */
view.setVisibility(View.VISIBLE);
view.setText(item);
}

public void showPokeDex(){
	TextView viewA = (TextView) getView().findViewById(R.id.captain);
	viewA.setVisibility(View.INVISIBLE);

	/* showing listview to screen */
	ListView view = (ListView) getView().findViewById(R.id.itemList);
	view.setVisibility(View.VISIBLE);

	ArrayList<Species> speciesList = new ArrayList<Species>();

	try {
	speciesList = Species.LoadSpecies(this.getActivity());
	ArrayAdapter<Species> arrayAdapter = new ArrayAdapter<Species>(this.getActivity(),android.R.layout.simple_list_item_1, speciesList);
	view.setAdapter(arrayAdapter); 

	view.setOnItemClickListener(new AdapterView.OnItemClickListener() {

	    @Override
	    public void onItemClick(AdapterView<?> parent, final View view,
	        int position, long id) {
	      final Species species = (Species) parent.getItemAtPosition(position);
	      view.animate().setDuration(1000).alpha(0)
	          .withEndAction(new Runnable() {
	            @Override
	            public void run() {
	              view.setAlpha(1);
	            }
	          });
	 
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(view.getContext());

				// set title
				alertDialogBuilder.setTitle("      " + species.getName());

	 
				// set dialog message
				String isSiangStr;
				String EvoToStr;
				String HpStr = "";
				String MpStr = "";
				String AccStr = "";
				String PowerStr = "";
				String DefStr = "";
				if (species.getisSiang()) isSiangStr = "Siang";
				  else isSiangStr = "Malam";
				EvoToStr = "None";
				
				if (species.getId() == 1) EvoToStr = "Alraune";
				if (species.getId() == 2) EvoToStr = "Salamence";
				if (species.getId() == 3) EvoToStr = "White Mochie";
				if (species.getId() == 4) EvoToStr = "D-Rexe";
				if (species.getId() == 5) EvoToStr = "Irone";
				if (species.getId()== 6) EvoToStr = "Wise Centaure";
				if (species.getId()== 7) EvoToStr = "Okame";
				if (species.getId() == 8) EvoToStr = "Kamue";
				if (species.getId() == 9) EvoToStr = "King Nagae";
				if (species.getId() == 10) EvoToStr = "Warlocke";
				
				if (species.getHpGrowth() == 1) HpStr = "A";
				if (species.getHpGrowth() == 2) HpStr = "B";
				if (species.getHpGrowth() == 3) HpStr = "C";
				
				if (species.getMpGrowth() == 1) MpStr = "A";
				if (species.getMpGrowth() == 2) MpStr = "B";
				if (species.getMpGrowth() == 3) MpStr = "C";
				
				if (species.getAccGrowth() == 1) AccStr = "A";
				if (species.getAccGrowth() == 2) AccStr = "B";
				if (species.getAccGrowth() == 3) AccStr = "C";
				
				if (species.getPowGrowth() == 1) PowerStr = "A";
				if (species.getPowGrowth() == 2) PowerStr = "B";
				if (species.getPowGrowth() == 3) PowerStr = "C";
				
				if (species.getDefGrowth() == 1) DefStr = "A";
				if (species.getDefGrowth() == 2) DefStr = "B";
				if (species.getDefGrowth() == 3) DefStr = "C";

				
				alertDialogBuilder
					.setMessage(Html.fromHtml(
							"Description : " + species.getDescription() + "<br>" + 
							"LifeSpan : " + species.getLifeSpan() + "<br>" + 
							"Time Enc. : " + isSiangStr + "<br>" + 
							"Level to Evo :  " + species.getLevelToEvo() + "<br>" + 
							"Evo To : " + EvoToStr + "<br><br>" + 
							"<u><b>Growth Status Rank</b></u><br>" + 
				            "<font color='#ff0000'>HP :  " + HpStr + "</font><br>" +
							"<font color='#ff0000'>MP : " + MpStr + "</font><br>" + 
				            "<font color='#0000ff'>Power :  " + PowerStr + "</font><br>" +
				            "<font color='#0000ff'>Acc : " + AccStr + "</font><br>" + 
				            "<font color='#0000ff'>Def :  " + DefStr + "</font>"
							))
					.setCancelable(false)
					.setPositiveButton("Close",new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,int id) {
						}
					  });
	 
					// create alert dialog
					AlertDialog alertDialog = alertDialogBuilder.create();
	 
					if (species.getId() == 1) alertDialog.setIcon(R.drawable.momon1);
					if (species.getId() == 2) alertDialog.setIcon(R.drawable.momon2);
					if (species.getId() == 3) alertDialog.setIcon(R.drawable.momon3);
					if (species.getId() == 4) alertDialog.setIcon(R.drawable.momon4);
					if (species.getId() == 5) alertDialog.setIcon(R.drawable.momon5);
					if (species.getId() == 6) alertDialog.setIcon(R.drawable.momon6);
					if (species.getId() == 7) alertDialog.setIcon(R.drawable.momon7);
					if (species.getId() == 8) alertDialog.setIcon(R.drawable.momon8);
					if (species.getId() == 9) alertDialog.setIcon(R.drawable.momon9);
					if (species.getId() == 10) alertDialog.setIcon(R.drawable.momon10);
					if (species.getId() == 11) alertDialog.setIcon(R.drawable.momon11);
					if (species.getId() == 12) alertDialog.setIcon(R.drawable.momon12);
					if (species.getId() == 13) alertDialog.setIcon(R.drawable.momon13);
					if (species.getId() == 14) alertDialog.setIcon(R.drawable.momon14);
					if (species.getId() == 15) alertDialog.setIcon(R.drawable.momon15);
					if (species.getId() == 16) alertDialog.setIcon(R.drawable.momon16);
					if (species.getId() == 17) alertDialog.setIcon(R.drawable.momon17);
					if (species.getId() == 18) alertDialog.setIcon(R.drawable.momon18);
					if (species.getId() == 19) alertDialog.setIcon(R.drawable.momon19);
					if (species.getId() == 20) alertDialog.setIcon(R.drawable.momon20);
					
					// show it
					alertDialog.show();
		        
				}


	  });

	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}


public void setListItem(){
TextView viewA = (TextView) getView().findViewById(R.id.captain);
viewA.setVisibility(View.INVISIBLE);

/* showing listview to screen */
ListView view = (ListView) getView().findViewById(R.id.itemList);
view.setVisibility(View.VISIBLE);

final ArrayList<Item> itemList = MainGameActivity.getPlayerFromBackpack().GetItem();

ArrayAdapter<Item> arrayAdapter = new ArrayAdapter<Item>(this.getActivity(),android.R.layout.simple_list_item_1, itemList);
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
    				  (item.GetEffect() == Effect.statPermanentIncreasing) ||
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
  		                setListItem();
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
    		  if (item.GetEffect() == Effect.egging){
    			  /* Apply Accelerator meter */
    				Intent Is = new Intent(getActivity(),EggingActivity.class);
    				getActivity().startActivity(Is);
    			  /* Apply following effect */
				  /* Reduce amount of item by 1 */
				  item.SetNItem(item.GetNItem() - 1);
    		  }
    		  if (item.GetEffect() == Effect.lightning){
    			  /* Get ID Map , if Wild Area = use item nevertheless */
    			  if (MainGameActivity.getPlayerFromBackpack().GetCurrentMap() == 3){
    			  /* Apply following effect */
    				  if (MainGameView.getTorch()) {
    					  /* Alert dialog */
    						AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(view.getContext());
    						// set title
    						alertDialogBuilder.setTitle("You have used this item!");
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
    					  MainGameView.setTorch(true);
    					  /* Reduce amount of item by 1 */
    					  item.SetNItem(item.GetNItem() - 1);
    					  /* Alert dialog */
    						AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(view.getContext());
    						// set title
    						alertDialogBuilder.setTitle("You're using Torch! It will take an effect till 6 O'clock!");
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
    				  }
    			  }
    		  }
    		  if (item.GetEffect() == Effect.repellant){
    			  /* Get ID Map , if Wild Area = use item nevertheless */
    			  if (MainGameActivity.getPlayerFromBackpack().GetCurrentMap() == 3){
    			  /* Apply following effect */
    				  if (MainGameView.getRepel()) {
    					  /* Alert dialog */
    						AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(view.getContext());
    						// set title
    						alertDialogBuilder.setTitle("You have used this item!");
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
    					  MainGameView.setRepel(true);
    					  /* Reduce amount of item by 1 */
    					  item.SetNItem(item.GetNItem() - 1);
    					  /* Alert dialog */
    						AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(view.getContext());
    						// set title
    						alertDialogBuilder.setTitle("You're using Repel! It will take an effect till 6 O'clock!");
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
    				  }
    			  }
    		  }
    		  /* Note that catching will never be called from this fragment */
    	  }
      }
      view.animate().setDuration(1000).alpha(0)
          .withEndAction(new Runnable() {
            @Override
            public void run() {
              view.setAlpha(1);
              setListItem();
            }
          });
    }
    }

  });

}


public void setStatistics() {
ListView viewA = (ListView) getView().findViewById(R.id.itemList);
viewA.setVisibility(View.INVISIBLE);

TextView view = (TextView) getView().findViewById(R.id.captain);
/* showing textview to screen */
view.setVisibility(View.VISIBLE);
view.setText(MainGameActivity.getPlayerFromBackpack().ShowStatusPlayer());
}


}