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

public class Bucket{
	
	private int x, y; // titik tengah
	private Bitmap bitmap;	
	private Rect sourceRect; 
	private int spriteWidth;
	private int spriteHeight;
	private int currentFrame; //the currentFrame
	private boolean IsMoving;
	private String Arah;
	private int Step;
	private Integer StepCounter;
	private int MapActive; 
	private String terra = "darat";
	// 1 : kamar
	// 2 : city
	// 3 : wild
	// 4 : stadium
	// 5 : kombinatorium
	// 6 : shop
	
	public int getMapActive() {
		return MapActive;
	}
	
	public void setMapActive(int X) {
		MapActive = X;
	}
	
	public Bucket() {		
		bitmap = DrawableManager.getInstance().getEmberBitmap();
		//bitmap.createScaledBitmap(bitmap, screenHeight, screenHeight, false);
		y = MainGameActivity.getPlayerFromBackpack().GetCurrentY(); // initial position
		x = MainGameActivity.getPlayerFromBackpack().GetCurrentX();
		spriteWidth = bitmap.getWidth()/4;
		spriteHeight = bitmap.getHeight()/4;
		sourceRect = new Rect(0, 0, spriteWidth, spriteHeight);
		currentFrame = 1;
		IsMoving = false;
		Arah = new String("Atas");
		Step = 0;
		StepCounter = 0;
		MapActive = MainGameActivity.getPlayerFromBackpack().GetCurrentMap(); //1 : kamar, 2 : city
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

	public void moveUP() {
		Arah = "Atas";
		Step += 20;
		IsMoving = true;
		StepCounter = StepCounter + 1;
	}
	public void moveDown() {
		Arah = "Bawah";
		Step += 20;
		IsMoving = true;
		StepCounter = StepCounter + 1;
	}
	public void moveLeft() {
		Arah = "Kiri";
		Step += 20;
		IsMoving = true;
		StepCounter = StepCounter + 1;
	}
	public void moveRight() {
		Arah = "Kanan";
		Step += 20;
		IsMoving = true;
		StepCounter = StepCounter + 1;
	}
	public String getTerra() {
		return terra;
	}
	public void setTerra(String S) {
		terra = S;
	}
	public int getXV() {return x;}
	public int getYV() {return y;}
	public int getStep() {return Step;}
	public void setStep(int x) {Step = x;}
	public Integer getStepCounter() {return StepCounter;}
	public void setStepCounter(Integer x) {StepCounter = x;}
	public String getArah() {return Arah;}
	public boolean IsMove() {return IsMoving;}
	public void setMove(boolean x) {IsMoving = x;}
	public void setXV(int _x) { x = _x; }
	public void setYV(int _y) { y = _y; }
	public int getCurrentFrame() {return currentFrame;}
	public void setCurrentFrame(int _x) { currentFrame = _x;}
}
