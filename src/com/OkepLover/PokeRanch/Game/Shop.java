package com.OkepLover.PokeRanch.Game;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Shop{
	
	private Bitmap bitmap;	 
	
	public Shop() {
		bitmap = DrawableManager.getInstance().getShopBitmap();
	}
	
	public void draw(Canvas canvas) {
		canvas.drawBitmap(bitmap, 0, 0, null);
	}
	
}
