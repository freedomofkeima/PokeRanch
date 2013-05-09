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
import android.util.Log;
import java.math.*;

public class Momon{
	
	private int x, y; // titik tengah
	private Bitmap bitmap;	
	private Rect sourceRect; 
	private int spriteWidth;
	private int spriteHeight;
	private int currentFrame; //the currentFrame
	private boolean IsMoving;
	private String Arah;
	private int Step;
	private boolean active;
	
	private int[][] grid = {
			//   1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0},
				{0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0},
				{0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,9,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0},
				{0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0},
				{0,0,1,1,1,1,1,1,8,8,8,8,8,8,8,8,8,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0},
				{0,0,1,1,1,1,1,1,8,1,1,1,1,1,1,1,8,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0},
				{0,0,1,1,1,1,1,1,8,1,1,1,1,1,1,1,8,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0},
				{0,0,1,1,1,1,1,1,8,1,1,1,1,1,1,1,8,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0},
				{0,0,1,1,1,1,1,1,8,1,1,1,1,1,1,1,8,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0},
				{0,0,1,1,1,1,1,1,8,1,1,1,1,1,1,1,8,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0},
				{0,0,1,1,1,1,1,1,8,1,1,1,1,1,1,1,8,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0},
				{0,0,1,1,1,1,1,1,8,1,1,1,1,1,1,1,8,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0},
				{0,0,1,1,1,1,1,1,8,1,1,1,1,1,1,1,8,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0},
				{0,0,1,1,1,1,1,1,8,8,8,8,8,8,8,8,8,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0},
				{0,0,1,1,1,1,1,1,1,1,1,1,1,1,7,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0},
				{0,0,1,1,1,1,1,1,1,1,1,1,1,1,7,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0},
				{0,0,1,1,1,1,1,1,1,1,1,1,1,1,7,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2,2,2,2,2,2,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2,2,2,2,2,2,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};
	
	public boolean getActive() {
		return active;
	}
	
	public void setActive(boolean R) {
		active = R;
	}
	public Momon(int _x, int _y) {		
		bitmap = DrawableManager.getInstance().getMomonBitmap();
		y = _y; // initial position
		x = _x;
		spriteWidth = bitmap.getWidth()/4;
		spriteHeight = bitmap.getHeight()/4;
		sourceRect = new Rect(0, 0, spriteWidth, spriteHeight);
		currentFrame = 1;
		IsMoving = false;
		Arah = new String("Atas");
		Step = 0;
		active = true;
	}
	
	public void draw(Canvas canvas) {
		//canvas.drawBitmap(bitmap, 0, 0, null);
		if (active) {
			this.sourceRect.left = (currentFrame % 4) * spriteWidth;
			this.sourceRect.right = this.sourceRect.left + spriteWidth;
			this.sourceRect.top = (currentFrame / 4) * spriteHeight;
			this.sourceRect.bottom = this.sourceRect.top + spriteHeight;
			
			//where to draw the sprite
			Rect destRect = new Rect(x, y, x + spriteWidth, y + spriteHeight);
			canvas.drawBitmap(bitmap, sourceRect, destRect, null);
		}
	}

	public void moveUP() {
		y -= 3;
		if ((currentFrame > 11) && (currentFrame < 15)) currentFrame++;
		else currentFrame = 12;
	}
	public void moveDown() {
		y += 3;
		if (currentFrame < 3) currentFrame++;
		else currentFrame = 0;
	}
	public void moveLeft() {
		x -= 3;
		if ((currentFrame > 3) && (currentFrame < 7)) currentFrame++;
		else currentFrame = 4;
	}
	public void moveRight() {
		x += 3;
		if ((currentFrame > 7) && (currentFrame < 11)) currentFrame++;
		else currentFrame= 8;
	}
	public boolean isAgro(int X, int Y) {
		return (Math.sqrt(Math.abs(x - X) * Math.abs(x - X) + Math.abs(y - Y) * Math.abs(y - Y)) < 150);
	}
	public boolean isBattle(int X, int Y) {
		return (Math.sqrt(Math.abs(x - X) * Math.abs(x - X) + Math.abs(y - Y) * Math.abs(y - Y)) < 25);
	}
	
	public void follow(int X2, int Y2)
	{
		/* Mengembalikan nilai Horizon */
		/* 1 -> utara, 2-> antara utara + timur , 3-> timur, 4-> timur + selatan */
		/* 5 -> Selatan, 6 -> Selatan + Barat, 7 -> Barat, 8 -> Barat + Utara */
		/* Monster dan Karakter (M dan K) */
		/* Monster adalah (X1,Y1). Karakter (X2,Y2) */
		/* Mendekat
				K1
			K8		K2
		K7		M		K3
			K6		K4
				K5
		*/
		if (active) {
			if ((X2 >= x) && (Y2 > y)) {
				if (grid[y/20+1][x/20] == 1) moveDown(); //primary move
				
			} else if ((X2 > x) && (Y2 <= y)) {
				if (grid[y/20][x/20+1] == 1) moveRight(); //primary move
				
			} else if ((X2 <= x) && (Y2 < y)) {
				if (grid[y/20-1][x/20] == 1) moveUP(); //primary move
				
			} else if ((X2 < x) && (Y2 >= y)) {
				if (grid[y/20][x/20-1] == 1) moveLeft(); //primary move
			}
		}
		Log.d("posisi momon:",getXV() + " " + getYV());
	}
	
	public void menjauh(int X2, int Y2)
	{
		/* Mengembalikan nilai Horizon */
		/* 1 -> utara, 2-> antara utara + timur , 3-> timur, 4-> timur + selatan */
		/* 5 -> Selatan, 6 -> Selatan + Barat, 7 -> Barat, 8 -> Barat + Utara */
		/* Monster dan Karakter (M dan K) */
		/* Monster adalah (X1,Y1). Karakter (X2,Y2) */
		/* Menjauh
				K1
			K8		K2
		K7		M		K3
			K6		K4
				K5
		*/
		if (active) {
			if ((X2 >= x) && (Y2 > y)) {
				if (grid[y/20-1][x/20] == 1) moveUP(); //primary move
				else if (grid[y/20][x/20-1] == 1) moveLeft(); //primary move
				else if (grid[y/20+1][x/20] == 1) moveDown(); //primary move
				else if (grid[y/20][x/20+1] == 1) moveRight(); //primary move
				
			} else if ((X2 > x) && (Y2 <= y)) {
				if (grid[y/20][x/20-1] == 1) moveLeft(); //primary move
				else if (grid[y/20-1][x/20] == 1) moveUP(); //primary move
				else if (grid[y/20+1][x/20] == 1) moveDown(); //primary move
				else if (grid[y/20][x/20+1] == 1) moveRight(); //primary move
				
			} else if ((X2 <= x) && (Y2 < y)) {
				if (grid[y/20+1][x/20] == 1) moveDown(); //primary move
				else if (grid[y/20][x/20+1] == 1) moveRight(); //primary move
				else if (grid[y/20-1][x/20] == 1) moveUP(); //primary move
				else if (grid[y/20][x/20-1] == 1) moveLeft(); //primary move
				
			} else if ((X2 < x) && (Y2 >= y)) {
				if (grid[y/20][x/20+1] == 1) moveRight(); //primary move
				else if (grid[y/20-1][x/20] == 1) moveUP(); //primary move
				else if (grid[y/20][x/20-1] == 1) moveLeft(); //primary move
				else if (grid[y/20][x/20+1] == 1) moveRight(); //primary move
			}
		}
		Log.d("posisi momon:",getXV() + " " + getYV());
	}
	
	private int ArahMon = 1;
	/* 1 ke atas, 2 kanan, 3 bawah, 4 kiri */
	public void randombanget(int X2, int Y2)
	{
		if (active)
		{
			if (ArahMon == 1) //Jika arahnya atas
			{
				if (grid[y/20-1][x/20] == 1){
					ArahMon = Utilities.rand(1, 4);
					moveUP(); //cek atas apakah bisa?
				}
				else ArahMon = Utilities.rand(1, 4); //jika tidak bisa, ganti arah mon
			}
			else if (ArahMon == 2) //Jika arahnya kanan
			{
				if (grid[y/20][x/20+1] == 1){
					ArahMon = Utilities.rand(1, 4);
					moveRight(); //cek kanan apakah bisa?
				}
				else ArahMon = Utilities.rand(1, 4); //jika tidak bisa, ganti arah mon
			}
			else if (ArahMon == 3) //Jika arahnya bawah
			{
				if (grid[y/20+1][x/20] == 1){
					ArahMon = Utilities.rand(1, 4);
					moveDown(); //cek kanan apakah bisa?
				}
				else ArahMon = Utilities.rand(1, 4); //jika tidak bisa, ganti arah mon
			}
			else if (ArahMon == 4) //Jika arahnya kiri
			{
				if (grid[y/20][x/20-1] == 1){
					ArahMon = Utilities.rand(1, 4);
					moveLeft(); //cek kanan apakah bisa?
				}
				else ArahMon = Utilities.rand(1, 4); //jika tidak bisa, ganti arah mon
			}
			else //jika bukan 1,2,3,4 maka
			{
				ArahMon = 1; //diset 1 kembali
			}
		}
	}
	
	public int getXV() {return x;}
	public int getYV() {return y;}
	public int getStep() {return Step;}
	public void setStep(int x) {Step = x;}
	public String getArah() {return Arah;}
	public boolean IsMove() {return IsMoving;}
	public void setMove(boolean x) {IsMoving = x;}
	public void setXV(int _x) { x = _x; }
	public void setYV(int _y) { y = _y; }
	public int getCurrentFrame() {return currentFrame;}
	public void setCurrentFrame(int _x) { currentFrame = _x;}
}
