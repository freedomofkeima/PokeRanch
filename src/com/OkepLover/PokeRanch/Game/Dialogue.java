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
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;

public class Dialogue{
	
	private Bitmap bitmap;	 
	private boolean isActive;
	private Rect kotak;
	private Rect destRect;
	private String msg;
	private Paint infoText = new Paint();
	
	public void setMsg(String s) {msg = s;}
	public boolean getActive() {return isActive;}
	public void setActive(boolean x) {isActive = x;}
	public Rect getRect() {return destRect;}
	
	public Dialogue() {		
		bitmap = DrawableManager.getInstance().getInfoBar();
		isActive = false;
		kotak = new Rect(0,0,bitmap.getWidth(),bitmap.getHeight());
		destRect = new Rect(0, 480 - bitmap.getHeight(), 800, 480);
		msg = "Aduh ngantuk Gw Aing!!!";
	}
	
	public void draw(Canvas canvas) {
		if (isActive) {
			//canvas.drawBitmap(bitmap, 0, 0, null);
			infoText.setTextSize(25);
			infoText.setTypeface(Typeface.MONOSPACE);
			infoText.setColor(Color.WHITE);
			canvas.drawBitmap(bitmap, kotak, destRect, null);
			canvas.drawText(msg, 40, 480 - bitmap.getHeight()+50, infoText);
		}
	}

	
}
