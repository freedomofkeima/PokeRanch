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
import android.graphics.Rect;

public class Bapak{
	private int x, y; // titik tengah
	private Bitmap bitmap;	
	private Rect sourceRect; 
	private int spriteWidth;
	private int spriteHeight;
	private int currentFrame; //the currentFrame
	
	public Bapak(MainGameView mainGameView) {		
		bitmap = DrawableManager.getInstance().getBapakBitmap();
		y = 300; // initial position
		x = 300;
		spriteWidth = bitmap.getWidth()/4;
		spriteHeight = bitmap.getHeight()/4;
		sourceRect = new Rect(0, 0, spriteWidth, spriteHeight);
		currentFrame = 1;
	}
	
	public void draw(Canvas canvas) {
		//canvas.drawBitmap(bitmap, 0, 0, null);
		this.sourceRect.left = (currentFrame % 4) * spriteWidth;
		this.sourceRect.right = this.sourceRect.left + spriteWidth;
		this.sourceRect.top = (currentFrame / 4) * spriteHeight;
		this.sourceRect.bottom = this.sourceRect.top + spriteHeight;
		
		//where to draw the sprite
		Rect destRect = new Rect(x, y, x + spriteWidth, y + spriteHeight);
		canvas.drawBitmap(bitmap, sourceRect, destRect, null);
	}
	public Rect getRectangle() {
		return new Rect(0, 0, x + bitmap.getWidth() , y + bitmap.getHeight());
	}
	public void updatePosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void moveUP() {
		y -= 5;
		if ((currentFrame > 11) && (currentFrame < 15)) currentFrame++;
		else currentFrame = 12;
	}
	public void moveDown() {
		y += 5;
		if (currentFrame < 3) currentFrame++;
		else currentFrame = 0;
	}
	public void moveLeft() {
		x -= 5;
		if ((currentFrame > 3) && (currentFrame < 7)) currentFrame++;
		else currentFrame = 4;
	}
	public void moveRight() {
		x += 5;
		if ((currentFrame > 7) && (currentFrame < 11)) currentFrame++;
		else currentFrame= 8;
	}
	public int getXV() {return x;}
	public int getYV() {return y;}
	public void setXV(int _x) { x = _x; }
	public void setYV(int _y) { y = _y; }
	public int getCurrentFrame() {return currentFrame;}
	public void setCurrentFrame(int _x) { currentFrame = _x;}
}
