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

public class Wild{
	
	private Bitmap bitmap;	 
	
	public Wild() {		
		bitmap = DrawableManager.getInstance().getWildBitmap();
	}
	
	public void setMalam()
	{
		bitmap = DrawableManager.getInstance().getWildMalamBitmap();
	}
	
	public void setSiang()
	{
		bitmap = DrawableManager.getInstance().getWildBitmap();
	}
	
	public void draw(Canvas canvas) {
		canvas.drawBitmap(bitmap, 0, 0, null);
	}
	
}
