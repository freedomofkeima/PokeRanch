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

public class ShopListMenuFrag  extends ListFragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	}


	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
	super.onActivityCreated(savedInstanceState);
	String[] values = new String[] { "Buy", "Sell", "Back"};

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
	            l.getChildAt(i).setBackgroundColor(Color.GREEN);
			}
			else l.getChildAt(i).setBackgroundColor(Color.WHITE);
		}

	} 

	ShopDetailFrag frag = (ShopDetailFrag) getFragmentManager().findFragmentById(R.id.shop_item);

	if (frag != null && frag.isInLayout()) {
	   if (IsValid){

	   if (item.equals("Buy")){
		 frag.showBuyItem();
	   } else
	   if (item.equals("Sell")){
		 frag.showSellItem();
	   } else
	   if (item.equals("Back")){
		   getActivity().finish();
	   }
	   
	}

	}



	}
	
}
