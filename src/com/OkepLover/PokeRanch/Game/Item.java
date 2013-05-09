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
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;


import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;


public class Item implements Serializable {
    /* Class Item Object Properties */
    private Integer id; //id dari Item
    private String name; //Variabel penyimpan nama Item 
    private Integer price; //Variabel penyimpan harga Item
    private Effect effect; //Variabel efek dari Item
    private Integer cost; //Variabel penyimpan besarnya perubahan dari Item
    private String description; //Penjelasan dari Item yang ada 
    private Integer NItem; //Variabel penyimpan jumlah Item yang dimiliki

   /* 4 Sekawan */
    //Konstruktor
    public Item(){
    /* Default Constructor from Item Class */
      id = 0;
      name = "Unknown";
      price = 1;
      effect = Effect.noneE;
      cost = 0;
      description = "No Description";
      NItem = 1; /* For easy testing */
    }
    public Item(Integer _id, String _name, Integer _price , Effect _effect , Integer _cost, String _description, Integer _NItem){
    /* User-defined Constructor from Item Class */
        id = _id;
        name = _name;
        price = _price;
        effect = _effect;
        cost = _cost;
        description = _description;
        NItem = _NItem;
    }
    
    public Item(Item I){
    /* Copy Constructor from Item Class */    
      id = I.id;
      name = I.name;
      price = I.price;
      effect = I.effect;
      cost = I.cost;
      description = I.description;
      NItem = I.NItem;
    }
    
    /* Assignment operation - Is needed ? */

    /* Getter & Setter */
    public Integer GetID(){
    /* Giving Item ID */
       return id;
    }
    
    public String GetName(){
    /* Giving Item name */
        return name;
    }
    
    public Integer GetPrice(){
    /* Giving Item price */
        return price;
    }
    
    public Effect GetEffect(){
    /* Giving Item effect */
         return effect;
     }
    
   public Integer GetCost(){
   /* Giving Item cost */
         return cost;    
   }
   
   public String GetDescription(){
   /* Giving Item description */
        return description;
   }
   
   public Integer GetNItem(){
   /* Giving Item NItem */
       return NItem;   
   }

   public void SetID(Integer _id){
   /* Setting Item ID */
       id = _id;
   }
   
   public void SetName(String _name){
   /* Setting Item name */
       name = _name;   
   }
   
   public void SetPrice(Integer _price){
   /* Setting Item price */
       price = _price;   
   }
   
   public void SetEffect(Effect _effect){
   /* Setting Item effect */
          effect = _effect;
   }
   
   public void SetCost(Integer _cost){
   /* Setting Item cost */
        cost = _cost;   
   }
   
   public void SetDescription(String _description){
   /* Setting Item description */
       description = _description;
   }
   
   public void SetNItem(Integer _NItem){
   /* Setting item NItem */
       NItem = _NItem;
   }

  /* Method Lain */
 public Monster_Sendiri UseItem(Monster_Sendiri M, Context ctx){
  /* { Digunakan untuk menggunakan Item } */
	 Monster_Sendiri temp = new Monster_Sendiri(M); //Create a copy of Monster_Sendiri
	 boolean IsUsed = false;
	  /* Implementation of healing, paralyzing, hypnotize, statPermanentIncreasing, ressurecting, healingMP */
	  /* Special case of catching, egging, repellant, and lightning are not implemented here */
	  /* This section is ItemList that can be used everywhere */
	 
      if (NItem > 0){
    	  /* Depends on Item */
    	  if ((effect == Effect.healing) && (temp.getStatus() != Status.dead) && (!temp.getCurrentHP().equals(temp.getMaxHP()))) {
    		  temp.setHP(temp.getCurrentHP() + cost);
    		  if (temp.getCurrentHP() > temp.getMaxHP()) temp.setHP(temp.getMaxHP());
    		  IsUsed = true;
    	  }
    	  if ((effect == Effect.paralyzing) && (temp.getStatus() == Status.paralyzed)) {
    		  temp.setStatus(Status.normal);
    		  IsUsed = true;
    	  }
    	  if ((effect == Effect.hypnotize) && (temp.getStatus() == Status.slept)) {
    		  temp.setStatus(Status.normal);
    		  IsUsed = true;
    	  }
    	  if (effect == Effect.statPermanentIncreasing) {
    		  if (cost == 1) temp.setPower(temp.getPower() + 1);
    		  if (cost == 2) temp.setDef(temp.getDef() + 1);
    		  if (cost == 3) temp.setAcc(temp.getAcc() + 1);
    		  IsUsed = true;
    	  }
    	  if ((effect == Effect.ressurecting) && (temp.getStatus() == Status.dead)) {
    		  temp.setStatus(Status.normal);
    		  temp.setHP((Integer) 1);
    		  IsUsed = true;
    	  }
    	  if ((effect == Effect.healingMP) && (!temp.getCurrentMP().equals(temp.getMaxMP()))) {
    		  temp.setMP(temp.getCurrentMP() + cost);
    		  if (temp.getCurrentMP() > temp.getMaxMP()) temp.setMP(temp.getMaxMP()); 
    		  IsUsed = true;
    	  }

    	  if (IsUsed) NItem = NItem - 1; /* reduce NItem */
    	  else {
    		  /* Show AlertDialog - Item cannot be used because of No Effect */
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ctx);
				// set title
				alertDialogBuilder.setTitle("Item Warning!");
				alertDialogBuilder.setMessage("Item has no effect / cannot be used");
				alertDialogBuilder.setCancelable(false)
					.setPositiveButton("Close",new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,int id) {
						}
					});
				// create alert dialog
				AlertDialog alertDialog = alertDialogBuilder.create();
 
				alertDialog.setIcon(R.drawable.item_ico);
				
				// show it
				alertDialog.show();
    	  }
      }
      
      return temp;
  }

  public Integer BuyItem(Integer Jumlah, Integer Money){
  /* { I.S.: Item sembarang } */
  /* { F.S.: Fungsi mengembalikan jumlah uang yang dipotong jika uang mencukupi serta menambah NItem Player.
            Jika uang tidak mencukupi, fungsi mengembalikan nilai -1 } */
    /* Kamus Lokal */
     Integer SumPrice = 0; //Variabel untuk menampung total harga
    /* Algoritma */
      if (Money >= Jumlah * price){
          NItem += Jumlah;
          SumPrice = Jumlah * price;
      }
    return SumPrice;
  }
  
  public Integer SellItem(Integer Jumlah){
  /* { I.S.: Item sembarang } */
  /* { F.S. : Fungsi mengembalikan jumlah uang yang bertambah jika NItem mencukupi. Jika NItem tidak mencukupi,
             fungsi mengembalikan nilai -1 } */
  /* Kamus Lokal */
  Integer SumPrice; //Variabel untuk menampung total harga
  /* Algoritma */
  if (NItem >= Jumlah){
      NItem -= Jumlah;
      SumPrice = Jumlah * price;
  } else SumPrice = -1;
  return SumPrice;
  }
  
  public static ArrayList<Item> LoadItem(Activity t) throws IOException{
  /* { I.S. : List Item kosong } */
  /* { F.S. : Melakukan proses load dari file eksternal ke List Item } */
	  /* Declaration */
	  ArrayList<Item> temp = new ArrayList<Item>();
	  Item tempItem = new Item();
	  
	  try {
		    String XMLName = new String();
	        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
	        factory.setValidating(false);
	        XmlPullParser myxml = factory.newPullParser();
	        
	        InputStream raw = t.getAssets().open("Item.xml");
	        myxml.setInput(raw, null);
	        
	        int eventType = myxml.getEventType();
	        while(eventType != XmlPullParser.END_DOCUMENT) {
	            if(eventType == XmlPullParser.START_DOCUMENT) {
	                Log.d("Debug", "In start document");
	            }
	            else if(eventType == XmlPullParser.START_TAG) {
	            	XMLName = myxml.getName();
	            }
	            else if(eventType == XmlPullParser.END_TAG) {
                   /* after each end tag */
	            	if (XMLName.equals("description")){
	            		 temp.add(new Item(tempItem));
	            	}
	            }
	            else if(eventType == XmlPullParser.TEXT) {
	            	if (XMLName.equals("id")) {tempItem.SetID(Integer.valueOf(myxml.getText()));}
	            	if (XMLName.equals("name")) {tempItem.SetName(myxml.getText());}	 
	            	if (XMLName.equals("price")) {tempItem.SetPrice(Integer.valueOf(myxml.getText()));}
	            	if (XMLName.equals("effect")) {tempItem.SetEffect(Effect.valueOf(myxml.getText().toString()));}
	            	if (XMLName.equals("cost")) {tempItem.SetCost(Integer.valueOf(myxml.getText()));}
	            	if (XMLName.equals("description")) {tempItem.SetDescription(myxml.getText());}
	            }
	            eventType = myxml.next();
	        }
	    } catch (XmlPullParserException e) {}
	  
	  return temp;
 }
  
 // public static void ListStore(list<Item> I){
  /* I,S. : Command "list-store" dieksekusi pemain */
  /* F.S. : Menampilkan semua daftar item yang dijual di toko */

 // }
  
  @Override
  public String toString() {
	  if (MainGameActivity.getShopCode() == 0) return "Item Name: " + this.name + "\nDescription: " + this.description + "\nNumber of Item: " + this.NItem;
      if (MainGameActivity.getShopCode() == 1) return "Item Name: " + this.name + "\nPrice: " + this.price + "\nNumber of Item: " + this.NItem;
      if (MainGameActivity.getShopCode() == 2) return "Item Name: " + this.name + "\nSell Price: " + this.price / 2 + "\nNumber of Item: " + this.NItem;
	  return "???";
  }
  
}
