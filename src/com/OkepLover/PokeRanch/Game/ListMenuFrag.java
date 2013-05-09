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

import android.app.ListFragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListMenuFrag extends ListFragment{
		
@Override
public void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
}


@Override
public void onActivityCreated(Bundle savedInstanceState) {
super.onActivityCreated(savedInstanceState);
String[] values = new String[] { "Save/Load", "PokeDex", "Item", "Monster", "Statistics", "Help", "Back"};

ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, values);
setListAdapter(adapter);
}


@Override
public void onListItemClick(ListView l, View v, int position, long id) {
String item = (String) getListAdapter().getItem(position);
Boolean IsValid = new Boolean(true);
/* Get player position for specific results */
if (l != null){
	for (int i = 0; i < l.getChildCount(); i++)
	{
		if (l.getChildAt(i) == l.getChildAt(position)){ 
			/* Save - Load - PokeDex - Monster - Sleep - Statistics */
			//String tS = new String(l.getItemAtPosition(i).toString());
            l.getChildAt(i).setBackgroundColor(Color.GREEN);
		}
		else l.getChildAt(i).setBackgroundColor(Color.WHITE);
	}

} 

DetailFrag frag = (DetailFrag) getFragmentManager().findFragmentById(R.id.frag_capt);

if (frag != null && frag.isInLayout()) {
   if (IsValid){
   if (item.equals("Save/Load")){
	    frag.setText("Save/Load");
	    /* passing mapCode to next activity */
		Intent i = new Intent(getActivity(),SaveLoadActivity.class);
		i.putExtra("MapCode",InGameMenuActivity.getMapCode());
		this.startActivity(i);
		
   } else
   if (item.equals("PokeDex")){
	   frag.showPokeDex();
   } else
   if (item.equals("Item")){
	   frag.setListItem();
   } else
   if (item.equals("Monster")){
	   frag.setText("Monster");
		Intent i = new Intent(getActivity(),MonsterMenu.class);
		i.putExtra("MonsterCode",0);
		this.startActivity(i);	   
  } else  
   if (item.equals("Help")){
	    frag.setText("Help");
		Intent i = new Intent(getActivity(),HelpMenu.class);
		this.startActivity(i);
   } else
   if (item.equals("Back")){
	   frag.setText(getCapt(item));
	   getActivity().finish();
   } else  
   if (item.equals("Statistics")){
	   frag.setStatistics();
   } else frag.setText(getCapt(item));
   } else frag.setText("Your Choice is not Valid!");
}

}

private String getCapt(String ship) {
if (ship.toLowerCase().contains("pokedex")) {
return "PokeDex";
}
if (ship.toLowerCase().contains("back")) {
return "Good Bye!";
}
return "???";
}


}