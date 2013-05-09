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
import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Pohon{
	
	private Bitmap bitmap;	 
	private int x;
	private int y;
	private boolean isActive;
	
	
	public boolean getActive() {return isActive;}
	public void setActive(boolean x) {isActive = x;}
	public int getX() {return x;}
	public int getY() {return y;}
	public void setX(int _x) {x = _x;}
	public void setY(int _y) {y = _y;}
	
	public Pohon(int _x, int _y) {		
		bitmap = DrawableManager.getInstance().getPohonBitmap();
		x = _x;
		y = _y;
		isActive = true;
	}
	
	public void draw(Canvas canvas) {
		if (isActive)
			canvas.drawBitmap(bitmap, x, y, null);
	}
	
}
