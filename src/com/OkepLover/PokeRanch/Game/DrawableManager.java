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
import java.util.Random;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


public class DrawableManager{
	/**
	 * construct pake aplication context
	 * @param context
	 */
	private Resources res;
	private DrawableManager(Context context) {
		// TODO ganti gambar
		res = context.getResources();
		rand = new Random();
	}
	/**
	 * must be called only once
	 * @param context
	 */
	public static void initInstance(Context context) {
		assert instance == null;
		instance = new DrawableManager(context);
	}
	/**
	 * must have called initInstance() before
	 * @return
	 */
	public static DrawableManager getInstance() {
		assert instance != null;
		return instance;
	}

	
	public void setBackground(int id){
		background = BitmapFactory.decodeResource(res, id);
	}
	
	public Bitmap getHealthBarBitmap(){
		if (healthbar == null) {
			healthbar = BitmapFactory.decodeResource(res, R.drawable.healthbar);
		}
		return healthbar;
	}
	public Bitmap getEnemyBarBitmap(){
		if (enemybar== null) {
			enemybar = BitmapFactory.decodeResource(res, R.drawable.hpbars2);
		}
		return enemybar;
	}
	
	public Bitmap getMenuBar() {
		if (menubar == null) {
			menubar = BitmapFactory.decodeResource(res, R.drawable.menubattle);
		}
		return menubar;
	}
	
	public Bitmap getInfoBar(){
		if (infobar == null){
			infobar = BitmapFactory.decodeResource(res, R.drawable.menubattle2);
		}
		return infobar;
	}
	
	public Bitmap getSkillBar(){
		if (skillbar == null){
			skillbar = BitmapFactory.decodeResource(res, R.drawable.skillselect);
		}
		return skillbar;
	}
	
	//buat yang di combinatorium
	public Bitmap getCombinatoriumBackground(){
		if (combbackground == null){
			combbackground = BitmapFactory.decodeResource(res, R.drawable.menubattle);
		}
		return combbackground;
	}
	
	public Bitmap getitemBackground(){
		if (itembackground == null){
			itembackground = BitmapFactory.decodeResource(res, R.drawable.itembackground);
		}
		return itembackground;
	}
		
	public Bitmap getitemSelected(){
		if (itemselected == null){
			itemselected = BitmapFactory.decodeResource(res, R.drawable.itemselected);
		}
	return itemselected;
	}
	public Bitmap getEmberBitmap(){
		if (emberBitmap == null) {
		
			emberBitmap = BitmapFactory.decodeResource(res, R.drawable.jalan);
		}

		return emberBitmap;
	}
	public Bitmap getBapakBitmap(){
		if (bapakBitmap == null) {
			bapakBitmap = BitmapFactory.decodeResource(res, R.drawable.bapak);
		}

		return bapakBitmap;
	}
	
	public Bitmap getKamarBitmap() {
		if (kamar == null) {
			kamar = BitmapFactory.decodeResource(res, R.drawable.kamar);
		}
		return kamar;
	}
	
	public Bitmap getCityBitmap() {
		if (city == null) {
			city = BitmapFactory.decodeResource(res, R.drawable.city);
		}
		return city;
	}
	
	public Bitmap getWildBitmap() {
		if (wild == null) {
			wild = BitmapFactory.decodeResource(res, R.drawable.wild);
		}
		return wild;
	}
	
	public Bitmap getWildMalamBitmap() {
		if (wildMalam == null) {
			wildMalam = BitmapFactory.decodeResource(res, R.drawable.malam);
		}
		return wildMalam;
	}
	
	public Bitmap getKombinatoriumBitmap() {
		if (kombinatorium == null) {
			kombinatorium = BitmapFactory.decodeResource(res, R.drawable.kombinatorium);
		}
		return kombinatorium;
	}
	
	public Bitmap getStadiumBitmap() {
		if (stadium == null) {
			stadium = BitmapFactory.decodeResource(res, R.drawable.stadium);
		}
		return stadium;
	}
	
	public Bitmap getShopBitmap() {
		if (shop == null) {
			shop = BitmapFactory.decodeResource(res, R.drawable.shop);
		}
		return shop;
	}
	
	public Bitmap getMomonBitmap() {
		if (momon == null) {
			momon = BitmapFactory.decodeResource(res, R.drawable.momon);
		}
		return momon;
	}
	
	public Bitmap getPohonBitmap() {
		if (pohon == null) {
			pohon = BitmapFactory.decodeResource(res, R.drawable.pohon);
		}
		return pohon;
	}
	
	public Bitmap getBatuBitmap() {
		if (batu == null) {
			batu = BitmapFactory.decodeResource(res, R.drawable.batu);
		}
		return batu;
	}
	
	private static DrawableManager instance;
	private Bitmap background;
	private Bitmap healthbar;
	private Bitmap menubar;
	private Bitmap infobar;
	private Bitmap enemybar;
	private Bitmap skillbar;
	private Bitmap combbackground;
	private Bitmap itembackground;
	private Bitmap itemselected;
	private Bitmap emberBitmap;
	private Bitmap momon;
	private Bitmap bapakBitmap;
	private Bitmap kamar;
	private Bitmap city;
	private Bitmap wild;
	private Bitmap wildMalam;
	private Bitmap kombinatorium;
	private Bitmap stadium;
	private Bitmap shop;
	private Bitmap pohon;
	private Bitmap batu;
	public final Random rand; 
}
