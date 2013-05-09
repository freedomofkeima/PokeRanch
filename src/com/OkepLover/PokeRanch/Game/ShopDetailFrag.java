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
import android.app.Fragment;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;


public class ShopDetailFrag  extends Fragment {

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
	View view = inflater.inflate(R.layout.shop_detail_fragment, container, false);
	return view;
	}
	
	public void showBuyItem(){
		/* showing listview to screen */
		ListView view = (ListView) getView().findViewById(R.id.itemshopList);
		view.setVisibility(View.VISIBLE);
		
		MainGameActivity.setShopCode((Integer) 1);
		
		 ArrayList<Item> itemList = MainGameActivity.getPlayerFromBackpack().GetItem();

		 ArrayAdapter<Item> arrayAdapter = new ArrayAdapter<Item>(this.getActivity(),android.R.layout.simple_list_item_1, itemList);
		 view.setAdapter(arrayAdapter); 
		 
		 final Context ctx = this.getActivity();

		 view.setOnItemClickListener(new AdapterView.OnItemClickListener() {

		     @Override
		     public void onItemClick(AdapterView<?> parent, final View view,
		         int position, long id) {
		    	 
		         final Item item = (Item) parent.getItemAtPosition(position);
		    	// custom dialog
					final Dialog dialog = new Dialog(ctx);
					dialog.setContentView(R.layout.shop_dialog);
					dialog.setTitle("Buy " + item.GetName());
		 
					// set the custom dialog components - text, image and button
					final EditText editText = (EditText) dialog.findViewById(R.id.amountofItem);
					
					TextView text = (TextView) dialog.findViewById(R.id.currentMoney);
					text.setText("Current Money: " + MainGameActivity.getPlayerFromBackpack().GetMoney());

		 
					Button buyButton = (Button) dialog.findViewById(R.id.button2shop);
					Button backButton = (Button) dialog.findViewById(R.id.button1shop);		
					
					buyButton.setText("Buy");
					//if button is clicked, buy
					buyButton.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							/* Buy sequence here */
							Integer Amount = Integer.valueOf(editText.getText().toString());
							if ((Amount > 0) && (Amount * item.GetPrice() <= MainGameActivity.getPlayerFromBackpack().GetMoney())){
								MainGameActivity.getPlayerFromBackpack().SetMoney(MainGameActivity.getPlayerFromBackpack().GetMoney() - Amount * item.GetPrice());
								item.SetNItem(item.GetNItem() + Amount);
								dialog.dismiss();
								showBuyItem();
							} else {
								/* Showing message that Amount is not valid */
								TextView textError = (TextView) dialog.findViewById(R.id.messageItem);
								textError.setText("Amount is not valid!");
							}
						}
					}); 					
					
					// if button is clicked, close the custom dialog
					backButton.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							dialog.dismiss();
						}
					}); 
		 
					dialog.show();
		    	 /* After buying process finished - refresh showBuyItem */		    	 
		     }
		 });
	}
	
	public void showSellItem(){
		/* showing listview to screen */
		ListView view = (ListView) getView().findViewById(R.id.itemshopList);
		view.setVisibility(View.VISIBLE);
		
		MainGameActivity.setShopCode((Integer) 2);
		
		 ArrayList<Item> itemList = MainGameActivity.getPlayerFromBackpack().GetItem();
		 
		 ArrayAdapter<Item> arrayAdapter = new ArrayAdapter<Item>(this.getActivity(),android.R.layout.simple_list_item_1, itemList);
		 view.setAdapter(arrayAdapter); 

		 final Context ctx = this.getActivity();
		 
		 view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			 
		     @Override
		     public void onItemClick(AdapterView<?> parent, final View view,
		         int position, long id) {
		    	 
		         final Item item = (Item) parent.getItemAtPosition(position);
		    	// custom dialog
					final Dialog dialog = new Dialog(ctx);
					dialog.setContentView(R.layout.shop_dialog);
					dialog.setTitle("Buy " + item.GetName());
		 
					// set the custom dialog components - text, image and button
					final EditText editText = (EditText) dialog.findViewById(R.id.amountofItem);
					
					TextView text = (TextView) dialog.findViewById(R.id.currentMoney);
					text.setText("Current Money: " + MainGameActivity.getPlayerFromBackpack().GetMoney());

		 
					Button sellButton = (Button) dialog.findViewById(R.id.button2shop);
					Button backButton = (Button) dialog.findViewById(R.id.button1shop);		
					
					sellButton.setText("Sell");
					//if button is clicked, buy
					sellButton.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							/* Sell sequence here */
							Integer Amount = Integer.valueOf(editText.getText().toString());
							if ((Amount > 0) && (Amount <= item.GetNItem())){
								MainGameActivity.getPlayerFromBackpack().SetMoney(MainGameActivity.getPlayerFromBackpack().GetMoney() + Amount * item.GetPrice() / 2);
								item.SetNItem(item.GetNItem() - Amount);
								dialog.dismiss();
								showSellItem();								
							} else {
								/* Showing message that Amount is not valid */
								TextView textError = (TextView) dialog.findViewById(R.id.messageItem);
								textError.setText("Amount is not valid!");								
							}
						}
					}); 					
					
					// if button is clicked, close the custom dialog
					backButton.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							dialog.dismiss();
						}
					}); 
		 
					dialog.show();
		     }
		 });	
	}
}
