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

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.text.Html;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

@SuppressLint("ViewConstructor")
public class MainGameView extends SurfaceView implements SurfaceHolder.Callback{
	
	public interface BattleListener {
		public void StartBattle();
	}
	
	final int[][] gridKamar = 
		{//  1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}, //1
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}, //2
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}, //3
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}, //4
			{0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,0,0,0,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}, //5
			{0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,0,0,0,1,1,1,99,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}, //6
			{0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,99,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}, //7			
			{0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,99,99,99,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}, //8
			{0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}, //9
			{0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}, //10
			{0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}, //11	
			{0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}, //12
			{0,0,0,0,0,0,0,0,1,1,1,0,0,0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}, //13
			{0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}, //14
			{0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}, //15			
			{0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}, //16	
			{0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}, //17	
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}, //18
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},	
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},	
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},	
			
		};
	
	
	final int[][] gridCity = 
		{//  1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,0,
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,4,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,3,0,0,0,0,0,0,0,1,1,1,1,1,1,0,0,0,0,0,0,0,5,0,0,0,0,0,0,0,0,0},
			{0,0,1,1,1,1,1,0,0,1,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1,0,1,1,1,1,1,0,0,0},
			{0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0},
			{0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0},
			{0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,6,6,0,0,1,0,0,0},
			{0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0},
			{0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}	
			
		};
	final int[][] gridWild = {
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
	
	final int[][] gridStadium = {
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,0,0,0},
			{0,0,1,1,1,1,1,1,0,0,0,0,0,0,1,1,1,1,99,99,99,99,1,1,1,1,0,0,0,0,0,1,1,1,1,1,1,0,0,0},
			{0,0,1,1,1,1,1,1,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,1,1,1,1,1,0,0,0},
			{0,0,1,1,1,1,1,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,1,1,1,1,0,0,0},
			{0,0,1,1,1,1,1,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,1,1,1,1,0,0,0},
			{0,0,1,1,1,1,1,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,1,1,1,1,0,0,0},
			{0,0,1,1,1,1,1,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,1,1,1,1,0,0,0},
			{0,0,1,1,1,1,1,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,1,1,1,1,0,0,0},
			{0,0,1,1,1,1,1,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,1,1,1,1,0,0,0},
			{0,0,1,1,1,1,1,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,1,1,1,1,0,0,0},
			{0,0,1,1,1,1,1,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,1,1,1,1,0,0,0},
			{0,0,1,1,1,1,1,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,1,1,1,1,0,0,0},
			{0,0,1,1,1,1,1,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,1,1,1,1,0,0,0},
			{0,0,1,1,1,1,1,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,1,1,1,1,0,0,0},
			{0,0,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,0,0,0},
			{0,0,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,0,0,0},
			{0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0},
			{0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0},
			{0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0},
			{0,0,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
	};
	
	final int[][] gridKombinatorium = {
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,1,1,1,1,1,1,0,0,0,0,0,99,0,0,0,0,0,0,1,1,1,1,1,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
	};
	
	final int[][] gridShop = {
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,99,99,99,0,1,1,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
	};
    
	private static final String TAG = MainGameView.class.getSimpleName();
	public GameLoopThread thread;
	private Matrix matrix = new Matrix();	
	private boolean GantiArahBapak;
	private int[][] grid;
	private Kamar kamar; 				//1
	private City city;  				//2
	private Wild wild;					//3
	private Stadium stadium;			//4
	private Kombinatorium kombinatorium;//5
	private Shop shop;					//6
	private Bucket bucket;
	private Bapak bapak;
	private Momon momon;
	private Momon momon1;
	private Momon momon2;
	private Momon momon3;
	private Momon Rangga;
	private Momon Yustian;
	private Momon Mangindaan;
	private Pohon pohon1;
	private Pohon pohon2;
	private Pohon pohon3;
	private Batu batu1;
	private BattleListener mBattle;
	private Dialogue dialog;
	private String skillMap = "none";
	private boolean siang; //waktu siang
	private boolean malam; //waktu malam
	private boolean lightArea; //terang / gelap di Area Luar
	private static boolean torch = false;
	private static boolean repellant = false;
	private boolean StartStadium = false;
	public static int betSize;
	
	//blok waktu
	private Paint Waktu = new Paint();
	private int Detik = 0; //Untuk waktu (30 detik ganti siang/malam)
	private int Jam;
	private int Menit;
	
	public boolean getSiangCode(){
		return siang;
	}
	
	public void setSiangCode(boolean S){
		siang = S;
	}
	
	public boolean getLightArea(){
		return lightArea;
	}
	
	public boolean getStartStadium() {
		return StartStadium;
	}
	
	public void setStartStadium(boolean S) {
		StartStadium = S;
	}
	public String getSkillMap() {
		return skillMap;
	}
	
	public void setSkillMap(String S) {
		skillMap = S;
	}
	public int getGrid(int i, int j) {
		return grid[j][i];
	}
	
	public void setGrid(int[][] AG) {
		grid = AG;
	}
	
	public static boolean getTorch() {
		return torch;
	}
	
	public static void setTorch(boolean _x) {
		torch = _x;
	}
	
	public static boolean getRepel() {
		return repellant;
	}
	
	public static void setRepel(boolean _x) {
		repellant = _x;
	}
	
	public MainGameView(Context context, int screenWidth, int screenHeight) {
		super(context);
		getHolder().addCallback(this);
		setFocusable(true);
		kamar = new Kamar();
		city = new City();
		wild = new Wild();
		stadium = new Stadium();
		kombinatorium = new Kombinatorium();
		shop = new Shop();
		bucket = new Bucket();
		bapak = new Bapak(this);
		momon = new Momon(140, 100);
		momon1 = new Momon(550,300);
		momon2 = new Momon(450,250);
		momon3 = new Momon(400,300);
		Rangga = new Momon(250, 250);
		Yustian = new Momon(600, 100);
		Mangindaan = new Momon(550, 300);
		GantiArahBapak = false;
		grid = new int [50][50];
		setGrid(gridKamar);
		mBattle = (BattleListener) context;
		pohon1 = new Pohon(290, 340);
		pohon2 = new Pohon(290, 360);
		pohon3 = new Pohon(290, 380);
		batu1 = new Batu(300, 80);
		dialog = new Dialogue();		
		if (MainGameActivity.getPlayerFromBackpack().GetTimeCounter() > 119) {
			siang = false;
			malam = true;
			Jam = (MainGameActivity.getPlayerFromBackpack().GetTimeCounter() - 120) / 10;
			Menit = MainGameActivity.getPlayerFromBackpack().GetTimeCounter() % 10;
		} else {
			siang = true;
			malam = false;
			Jam = MainGameActivity.getPlayerFromBackpack().GetTimeCounter() / 10;
			Menit = MainGameActivity.getPlayerFromBackpack().GetTimeCounter() % 10;			
		}
	}
	public MainGameView (Context context){
		super(context);
		getHolder().addCallback(this);
		setFocusable(true);
		kamar = new Kamar();
		city = new City();
		wild = new Wild();
		stadium = new Stadium();
		kombinatorium = new Kombinatorium();
		shop = new Shop();
		bucket = new Bucket();
		bapak = new Bapak(this);
		momon = new Momon(140, 100);
		momon1 = new Momon(550,300);
		momon2 = new Momon(450,250);
		momon3 = new Momon(400,300);
		Rangga = new Momon(300, 200);
		Yustian = new Momon(600, 100);
		Mangindaan = new Momon(550, 300);
		GantiArahBapak = false;
		grid = new int [50][50];
		setGrid(gridKamar);
		mBattle = (BattleListener) context;
		pohon1 = new Pohon(290, 340);
		pohon2 = new Pohon(290, 360);
		pohon3 = new Pohon(290, 380);
		batu1 = new Batu(300, 80);
		dialog = new Dialogue();
		if (MainGameActivity.getPlayerFromBackpack().GetTimeCounter() > 119) {
			siang = false;
			malam = true;
			Jam = (MainGameActivity.getPlayerFromBackpack().GetTimeCounter() - 120) / 10;
			Menit = MainGameActivity.getPlayerFromBackpack().GetTimeCounter() % 10;
		} else {
			siang = true;
			malam = false;
			Jam = MainGameActivity.getPlayerFromBackpack().GetTimeCounter() / 10;
			Menit = MainGameActivity.getPlayerFromBackpack().GetTimeCounter() % 10;			
		}
	}
	public MainGameView (Context context, AttributeSet attrs){
		super(context, attrs);
		getHolder().addCallback(this);
		setFocusable(true);
		kamar = new Kamar();
		city = new City();
		wild = new Wild();
		stadium = new Stadium();
		kombinatorium = new Kombinatorium();
		shop = new Shop();
		bucket = new Bucket();
		bapak = new Bapak(this);
		momon = new Momon(140, 100);
		momon1 = new Momon(550,300);
		momon2 = new Momon(450,250);
		momon3 = new Momon(400,300);
		Rangga = new Momon(300, 200);
		Yustian = new Momon(600, 100);
		Mangindaan = new Momon(550, 300);
		GantiArahBapak = false;
		grid = new int [50][50];
		setGrid(gridKamar);
		mBattle = (BattleListener) context;
		pohon1 = new Pohon(290, 340);
		pohon2 = new Pohon(290, 360);
		pohon3 = new Pohon(290, 380);
		batu1 = new Batu(300, 80);
		dialog = new Dialogue();
		if (MainGameActivity.getPlayerFromBackpack().GetTimeCounter() > 119) {
			siang = false;
			malam = true;
			Jam = (MainGameActivity.getPlayerFromBackpack().GetTimeCounter() - 120) / 10;
			Menit = MainGameActivity.getPlayerFromBackpack().GetTimeCounter() % 10;
		} else {
			siang = true;
			malam = false;
			Jam = MainGameActivity.getPlayerFromBackpack().GetTimeCounter() / 10;
			Menit = MainGameActivity.getPlayerFromBackpack().GetTimeCounter() % 10;			
		}
	}
	public MainGameView (Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		getHolder().addCallback(this);
		setFocusable(true);
		kamar = new Kamar();
		city = new City();
		wild = new Wild();
		stadium = new Stadium();
		kombinatorium = new Kombinatorium();
		shop = new Shop();
		bucket = new Bucket();
		bapak = new Bapak(this);
		momon = new Momon(140, 100);
		momon1 = new Momon(550,300);
		momon2 = new Momon(450,250);
		momon3 = new Momon(400,300);
		Rangga = new Momon(300, 200);
		Yustian = new Momon(600, 100);
		Mangindaan = new Momon(550, 300);
		GantiArahBapak = false;
		grid = new int [50][50];
		setGrid(gridKamar);
		mBattle = (BattleListener) context;
		pohon1 = new Pohon(290, 340);
		pohon2 = new Pohon(290, 360);
		pohon3 = new Pohon(290, 380);
		batu1 = new Batu(300, 80);
		dialog = new Dialogue();	
		if (MainGameActivity.getPlayerFromBackpack().GetTimeCounter() > 119) {
			siang = false;
			malam = true;
			Jam = (MainGameActivity.getPlayerFromBackpack().GetTimeCounter() - 120) / 10;
			Menit = MainGameActivity.getPlayerFromBackpack().GetTimeCounter() % 10;
		} else {
			siang = true;
			malam = false;
			Jam = MainGameActivity.getPlayerFromBackpack().GetTimeCounter() / 10;
			Menit = MainGameActivity.getPlayerFromBackpack().GetTimeCounter() % 10;			
		}
	}
	
	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
	}

	@Override
	public void surfaceCreated(SurfaceHolder arg0) {
		// inisialisasi thread
		initThread();
		Log.d(TAG, "surface created");
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {		
		releaseThread();
		Log.d(TAG, "surface destroyed");
	}
	
	// inisialisasi thread
	public void initThread() {
		if (thread == null || !thread.isAlive()) {
			// jika belom diinisialisasi threadnya atau threadnya sudah tidak hidup lagi, maka
			// instansiasi thread utama
			thread = new GameLoopThread(getHolder(), this);
			thread.start();
		}
		thread.setRunning(true);
	}
	 
	private void releaseThread() {
		boolean retry = true;
		while (retry) {
			try {
				thread.join();
				retry = false;
				thread = null;
			} catch (InterruptedException e) {
			}
		}
	}

	public void SpawnRangga() {
		momon.setActive(true);
		momon.setXV(140);
		momon.setYV(90);
		Rangga.setActive(true);
		Rangga.setXV(300);
		Rangga.setYV(200);
		Yustian.setActive(true);
		Yustian.setXV(600);
		Yustian.setYV(100);
		Mangindaan.setActive(true);
		Mangindaan.setXV(550);
		Mangindaan.setYV(200);	
		momon1.setActive(true);
		momon1.setXV(550);
		momon1.setYV(300);
		momon2.setActive(true);
		momon2.setXV(450);
		momon2.setYV(250);
		momon3.setActive(true);
		momon3.setXV(400);
		momon3.setYV(300);
	}
	
	public void render(Canvas canvas) {				
		canvas.setMatrix(matrix);
		/* Set player current location */
		MainGameActivity.getPlayerFromBackpack().SetCurrentMap((Integer) bucket.getMapActive());
		MainGameActivity.getPlayerFromBackpack().SetCurrentX((Integer) bucket.getXV());
		MainGameActivity.getPlayerFromBackpack().SetCurrentY((Integer) bucket.getYV());
		
		if(bucket.getMapActive() == 1) {
			kamar.draw(canvas);
			bapak.draw(canvas);
			bucket.draw(canvas);
		} else if (bucket.getMapActive() == 2) {
			city.draw(canvas);
			bucket.draw(canvas);
			/* Reset all status */
			repellant = false;
			torch = false;
		} else if (bucket.getMapActive() == 3) {
			
			if (((siang) && (Jam >= 6)) || torch || ((malam) && (Jam < 6)))
			{ /* Jika siang sudah bernilai true */
			  /* Gambar semua yang di wild */	
				wild.setSiang();
				wild.draw(canvas);
				pohon1.draw(canvas);
				pohon2.draw(canvas);
				pohon3.draw(canvas);
				batu1.draw(canvas);
				momon.draw(canvas);
				momon1.draw(canvas);
				momon2.draw(canvas);
				momon3.draw(canvas);
				Rangga.draw(canvas);
				Yustian.draw(canvas);
				Mangindaan.draw(canvas);
				bucket.draw(canvas);
				lightArea = true;
			}
			else
			{
				wild.setMalam();
				wild.draw(canvas);
				lightArea = false;
			}
			
			
		} else if (bucket.getMapActive() == 4) {
			
			stadium.draw(canvas);
			bucket.draw(canvas);
		} else if (bucket.getMapActive() == 5) {
			
			kombinatorium.draw(canvas);
			bucket.draw(canvas);
		} else if (bucket.getMapActive() == 6) {
			
			shop.draw(canvas);
			bucket.draw(canvas);
		} 
		dialog.draw(canvas);
		drawJam(canvas);
	}
	
	public void GantiWaktu()
	{
		/* 1 Detik dunia nyata => X detik di android */
		/* Jika waktu > 50, maka ganti status waktu (siang/malam) */
		if ((Jam > 11) && (siang)) //Jika > 50 dan siang,
		{
			Jam = Jam - 12; //di nolkan kembali
			siang = false; //siang menjadi false
			malam = true; //malam menjadi true
		}
		else if ((Jam > 11) && (malam)) //Jika > 50 dan malam
		{
			Jam = Jam - 12; //di nolkan kembali
			siang = true; //siang menjadi true
			malam = false; //malam menjadi false
			/* Kurangi umur monster by 1 */
			Integer MonsterCount = 0;
			for (int i = 0; i < 6; i++)
				if (MainGameActivity.getPlayerFromBackpack().GetMonster(i).getID() != 0) MonsterCount = MonsterCount + 1;
			for (int i = 0; i < 6; i++){
				if (MainGameActivity.getPlayerFromBackpack().GetMonster(i).getID() != 0){
					MainGameActivity.getPlayerFromBackpack().GetMonster(i).setlifeSpan(MainGameActivity.getPlayerFromBackpack().GetMonster(i).getlifeSpan() - 1);
					if ((MonsterCount == 1) && (i == 0) && (MainGameActivity.getPlayerFromBackpack().GetMonster(i).getlifeSpan() == 0))
						MainGameActivity.getPlayerFromBackpack().GetMonster(i).setlifeSpan(1);
					/* if Age = 0, then dismiss that slot */
					if (MainGameActivity.getPlayerFromBackpack().GetMonster(i).getlifeSpan() == 0){
						MainGameActivity.getPlayerFromBackpack().GetMonster(i).setID(0);
					}
				}
			}
		}
		if ((Jam == 6) && (Menit == 0)){
			torch = false; //default torch akan mati ketika pergantian waktu
			SpawnRangga();
			if (repellant) {
				/* Show message that repellant has off */
				repellant = false;
			}			
		}
	}
	
	
	public void drawJam(Canvas canvas) {
		Waktu.setTextSize(25);
		Waktu.setTypeface(Typeface.MONOSPACE);
		Waktu.setColor(Color.WHITE);
		String SiangMalam = "";
		if (siang) SiangMalam = " AM";
		if (malam) SiangMalam = " PM";
		if (Menit < 2) SiangMalam = " 0" + Menit * 6 + "" + SiangMalam;
		else SiangMalam = " " + Menit * 6 + "" + SiangMalam;
		if (Jam < 10) SiangMalam =  "0" + Jam + " :" + SiangMalam;
		else SiangMalam = "" + Jam + " :" + SiangMalam;	
		canvas.drawText(SiangMalam, 40, 40, Waktu);
	}
		
	public void update() { //Ini merupakan thread, otomatis jalan terus
		GantiWaktu(); //Cek detik, ganti waktu jika sudah memenuhi syarat
		if (siang) MainGameActivity.getPlayerFromBackpack().SetTimeCounter(Jam * 10 + Menit);
		else MainGameActivity.getPlayerFromBackpack().SetTimeCounter(Jam * 10 + Menit + 120);
		Detik++; //Increment detik
		if (Detik > 4) {
			if (Menit > 8) {
				Menit = 0;
				Jam++;
			} else {
				Menit++;
			}
			Detik = 0;
		}
		
		// Bucket adalah Player
		if(bucket.getMapActive() == 1) { //Jika berada di map 1 (rumah)
			if (!GantiArahBapak){
				if (bapak.getXV() > 200)
					bapak.moveLeft();
				else GantiArahBapak = true;
			}else {
				if (bapak.getXV() < 400)
					bapak.moveRight();
				else
					GantiArahBapak = false;
			}

		} else if (bucket.getMapActive() == 3) { //Jika berada di map 3 (alam liar)
			if (momon.isAgro(bucket.getXV(), bucket.getYV())) { //Jika masuk dalam range 
				if (!repellant) //jika repellant tidak aktif
					momon.follow(bucket.getXV(), bucket.getYV());
				else //jika repellant aktif
					momon.menjauh(bucket.getXV(), bucket.getYV());
				
				if (momon.isBattle(bucket.getXV(), bucket.getYV())) {
					Log.d("halo","hai:");
					mBattle.StartBattle(); //Jika battle mode
					momon.setXV(1); //monster dibuang ke lokasi 1x1 (setelah battle selesai)
					momon.setYV(1);
					momon.setActive(false);
				}
			}
			if (momon2.isAgro(bucket.getXV(), bucket.getYV())) { //Jika masuk dalam range 
				if (!repellant) //jika repellant tidak aktif
					momon2.follow(bucket.getXV(), bucket.getYV());
				else //jika repellant aktif
					momon2.menjauh(bucket.getXV(), bucket.getYV());
				
				if (momon2.isBattle(bucket.getXV(), bucket.getYV())) {
					Log.d("halo","hai:");
					mBattle.StartBattle(); //Jika battle mode
					momon2.setXV(1); //monster dibuang ke lokasi 1x1 (setelah battle selesai)
					momon2.setYV(11);
					momon2.setActive(false);
				}
			}
			if (Rangga.isAgro(bucket.getXV(), bucket.getYV())) { //Jika masuk dalam range 
				Rangga.menjauh(bucket.getXV(), bucket.getYV());
				if (Rangga.isBattle(bucket.getXV(), bucket.getYV())) {
					Log.d("halo","hai:");
					thread.setRunning(false);
					mBattle.StartBattle(); //idem
					Rangga.setXV(2);
					Rangga.setYV(2);
					Rangga.setActive(false);
				}
			}
			if (momon1.isAgro(bucket.getXV(), bucket.getYV())) { //Jika masuk dalam range 
				momon1.menjauh(bucket.getXV(), bucket.getYV());
				if (momon1.isBattle(bucket.getXV(), bucket.getYV())) {
					Log.d("halo","hai:");
					thread.setRunning(false);
					mBattle.StartBattle(); //idem
					momon1.setXV(1);
					momon1.setYV(3);
					momon1.setActive(false);
				}
			}
			if (true) { //Jika masuk dalam range , Yustian.isAgro(bucket.getXV(), bucket.getYV())
				if (!repellant) //jika repellant tidak aktif
					Yustian.randombanget(bucket.getXV(), bucket.getYV());
				else //jika repellant aktif
					Yustian.menjauh(bucket.getXV(), bucket.getYV());
				
				if (Yustian.isBattle(bucket.getXV(), bucket.getYV())) {
					Log.d("halo","hai:");
					thread.setRunning(false);
					mBattle.StartBattle();
					Yustian.setXV(2); //idem
					Yustian.setYV(3);
					Yustian.setActive(false);
				}
			}
			if (true) { //Jika masuk dalam range , Mangindaan.isAgro(bucket.getXV(), bucket.getYV())
				if (!repellant) //jika repellant tidak aktif
					Mangindaan.randombanget(bucket.getXV(), bucket.getYV());
				else //jika repellant aktif
					Mangindaan.menjauh(bucket.getXV(), bucket.getYV());
				
				if (Mangindaan.isBattle(bucket.getXV(), bucket.getYV())) {
					Log.d("halo","hai:");
					thread.setRunning(false);
					mBattle.StartBattle();
					Mangindaan.setXV(3); //idem
					Mangindaan.setYV(2);
					Mangindaan.setActive(false);
				}
			}
			if (true) { //Jika masuk dalam range , Mangindaan.isAgro(bucket.getXV(), bucket.getYV())
				momon3.randombanget(bucket.getXV(), bucket.getYV());
				if (momon3.isBattle(bucket.getXV(), bucket.getYV())) {
					Log.d("halo","hai:");
					thread.setRunning(false);
					mBattle.StartBattle();
					momon3.setXV(1); //idem
					momon3.setYV(4);
					momon3.setActive(false);
				}
			}
			
		}

			
		if (bucket.IsMove()) {
			if (bucket.getArah() == "Atas") {
				if (bucket.getStep() != 0) {
					bucket.setYV(bucket.getYV() - 5);
					if ((bucket.getCurrentFrame() > 11) && (bucket.getCurrentFrame() < 15)) 
						bucket.setCurrentFrame(bucket.getCurrentFrame() + 1);
					else 
						bucket.setCurrentFrame(12);
					bucket.setStep(bucket.getStep() - 5);
				} else  bucket.setMove(false);
			}
			if (bucket.getArah() == "Bawah") {
				if (bucket.getStep() != 0) {
					bucket.setYV(bucket.getYV() + 5);
					if (bucket.getCurrentFrame() < 3)
						bucket.setCurrentFrame(bucket.getCurrentFrame() + 1);
					else 
						bucket.setCurrentFrame(0);
					bucket.setStep(bucket.getStep() - 5);
				} else  bucket.setMove(false);
			}
			if (bucket.getArah() == "Kiri") {
				if (bucket.getStep() != 0) {
					bucket.setXV(bucket.getXV() - 5);
					if ((bucket.getCurrentFrame() > 3) && (bucket.getCurrentFrame() < 7)) 
						bucket.setCurrentFrame(bucket.getCurrentFrame() + 1);
					else 
						bucket.setCurrentFrame(4);
					bucket.setStep(bucket.getStep() - 5);
				} else  bucket.setMove(false);
			}
			if (bucket.getArah() == "Kanan") {
				if (bucket.getStep() != 0) {
					bucket.setXV(bucket.getXV() + 5);
					if ((bucket.getCurrentFrame() > 7) && (bucket.getCurrentFrame() < 11)) 
						bucket.setCurrentFrame(bucket.getCurrentFrame() + 1);
					else 
						bucket.setCurrentFrame(8);
					bucket.setStep(bucket.getStep() - 5);
				} else  bucket.setMove(false);
			}
		}
			
	}
	
	public int abs(int x) {
		if (x < 0) return -x;
		else return x;
	}

	public Bucket getBucket() {
		return bucket;
	}
	
	public void TebangPohon() {
		if (bucket.getMapActive() == 3) {
			if (bucket.getYV() == 320) {
				pohon1.setActive(false);
				grid[pohon1.getY()/20-1][14] = 1;
				Log.d("pohon1 : ",pohon1.getX()+","+pohon1.getY());
				Log.d("pohon1 grid : ",""+grid[pohon1.getY()/20][pohon1.getX()/20]);
			}
			else if (bucket.getYV() == 340) {
				pohon2.setActive(false);
				grid[pohon2.getY()/20-1][14] = 1;
				Log.d("pohon2 : ",pohon2.getX()+","+pohon2.getY());
				Log.d("pohon2 grid : ",""+grid[pohon2.getY()/20][pohon2.getX()/20]);
			}
			else if (bucket.getYV() == 360) {
				pohon3.setActive(false);
				grid[pohon3.getY()/20-1][14] = 1;
				Log.d("pohon3 : ",pohon3.getX()+","+pohon3.getY());
				Log.d("pohon3 grid : ",""+grid[pohon3.getY()/20][pohon3.getX()/20]);
			}
			
		}
	}
	
	public void ShowDialog() {
		if (dialog.getActive() == false) {
			dialog.setActive(true);
		}
	}
	
	public void DorongBatu() {
		if (bucket.getMapActive() == 3) {
			if (batu1.getX() <= bucket.getXV()) {
				if (grid[batu1.getY()/20][batu1.getX()/20-1] != 0 ) {
					grid[batu1.getY()/20][batu1.getX()/20] = 1;
					batu1.moveLeft();
					grid[batu1.getY()/20][batu1.getX()/20] = 9;
				}
			} else if (batu1.getX() >= bucket.getXV()) {
				if (grid[batu1.getY()/20][batu1.getX()/20+1] != 0 ) {
					grid[batu1.getY()/20][batu1.getX()/20] = 1;
					batu1.moveRight();
					grid[batu1.getY()/20][batu1.getX()/20] = 9;
				}
			} else if (batu1.getY() <= bucket.getYV()) {
				if (grid[batu1.getY()/20-1][batu1.getX()/20] != 0 ) {
					grid[batu1.getY()/20][batu1.getX()/20] = 1;
					batu1.moveUP();
					grid[batu1.getY()/20][batu1.getX()/20] = 9;
				}
			} else if (batu1.getY() >= bucket.getYV()) {
				if (grid[batu1.getY()/20+1][batu1.getX()/20] != 0 ) {
					grid[batu1.getY()/20][batu1.getX()/20] = 1;
					batu1.moveDown();
					grid[batu1.getY()/20][batu1.getX()/20] = 9;
				}
			}
		}
	}

	public void Berenang() {
		if (grid[bucket.getYV()/20][bucket.getXV()/20-1] == 8) //kiri
			bucket.setXV(bucket.getXV()-40);
		else if (grid[bucket.getYV()/20][bucket.getXV()/20+1] == 8) //kanan
			bucket.setXV(bucket.getXV()+40);
		else if (grid[bucket.getYV()/20+1][bucket.getXV()/20] == 8) //bawah
			bucket.setYV(bucket.getYV()+40);
		else if (grid[bucket.getYV()/20-1][bucket.getXV()/20] == 8) //atas
			bucket.setYV(bucket.getYV()-40);
	}
	
	public void TeleportToCity() {
		if (bucket.getMapActive() == 1) {
			bucket.setXV(160);
			bucket.setYV(360);
		} else if (bucket.getMapActive() == 3) {
			bucket.setXV(400);
			bucket.setYV(60);
		} else if (bucket.getMapActive() == 4) {
			bucket.setXV(600);
			bucket.setYV(120);
		} else if (bucket.getMapActive() == 5) {
			bucket.setXV(640);
			bucket.setYV(360);
		} else if (bucket.getMapActive() == 6) {
			bucket.setXV(180);
			bucket.setYV(100);
		} 
		bucket.setMapActive(2);
		setGrid(gridCity);
	}
	
	public void TeleportToKamar() {
		bucket.setMapActive(1);
		bucket.setXV(320);
		bucket.setYV(320);
		setGrid(gridKamar);
		dialog.setMsg("Aduh ngantuk Gw Aing!!!");
	}

	public void TeleportToShop() {
		bucket.setMapActive(6);
		bucket.setXV(240);
		bucket.setYV(300);
		setGrid(gridShop);
		dialog.setMsg("Mau beli apa mas??");
	}

	public void TeleportToKombinatorium() {
		bucket.setMapActive(5);
		bucket.setXV(380);
		bucket.setYV(380);
		setGrid(gridKombinatorium);
		dialog.setMsg("Mau kombine apa mas??");
	}
	
	public void TeleportToStadium() {
		bucket.setMapActive(4);
		bucket.setXV(460);
		bucket.setYV(420);
		setGrid(gridStadium);
		dialog.setMsg("Berasa jago??");
	}
	
	public void TeleportToWild() {
		bucket.setMapActive(3);
		bucket.setXV(440);
		bucket.setYV(420);
		setGrid(gridWild);
		SpawnRangga();
		pohon1.setActive(true);
		pohon2.setActive(true);
		pohon3.setActive(true);
	}
	
	public boolean onTouchEvent(MotionEvent event) {
	    int touchX = (int) event.getX();
		int touchY = (int) event.getY();
		final int actioncode = event.getAction() & MotionEvent.ACTION_MASK;	
		if (dialog.getActive()){ //user tidak dapat menekan tombol bila sedang ada infobar
			switch (actioncode) {
				case MotionEvent.ACTION_DOWN:
					if (dialog.getRect().contains(touchX, touchY)) {
						dialog.setActive(false);
						MainGameActivity.getUP().setVisibility(Button.VISIBLE);
						MainGameActivity.getDOWN().setVisibility(Button.VISIBLE);
						MainGameActivity.getLEFT().setVisibility(Button.VISIBLE);
						MainGameActivity.getRIGHT().setVisibility(Button.VISIBLE);
						MainGameActivity.getSPECIAL().setVisibility(Button.VISIBLE);
						MainGameActivity.getLOVE().setVisibility(Button.VISIBLE);
						MainGameActivity.getPlace().setVisibility(Button.INVISIBLE);
					}
			}
		}return true;
	}
	
	
	public void PilihMonster(){   
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this.getContext());
		ArrayList<String> monsterName = new ArrayList<String>();
		if (skillMap.equals("cut")) {
			for (int j = 0; j < 6; j++){
				if (MainGameActivity.getPlayerFromBackpack().GetMonster(j).getID() != 0) {
					if (MainGameActivity.getPlayerFromBackpack().GetMonster(j).getIsLearned(10))
						monsterName.add("Slot " + j+1 + ". " + MainGameActivity.getPlayerFromBackpack().GetMonster(j).getName());
				}
			}
		} else if (skillMap.equals("push")) {
			for (int j = 0; j < 6; j++){
				if (MainGameActivity.getPlayerFromBackpack().GetMonster(j).getID() != 0) {
					if (MainGameActivity.getPlayerFromBackpack().GetMonster(j).getIsLearned(11))
						monsterName.add("Slot " + j+1 + ". " + MainGameActivity.getPlayerFromBackpack().GetMonster(j).getName());
				}
			}
		} else if (skillMap.equals("button")) {
			for (int j = 0; j < 6; j++){
				if (MainGameActivity.getPlayerFromBackpack().GetMonster(j).getID() != 0) {
					if (MainGameActivity.getPlayerFromBackpack().GetMonster(j).getIsLearned(12))
						monsterName.add("Slot " + j+1 + ". " + MainGameActivity.getPlayerFromBackpack().GetMonster(j).getName());
				}
			}
		}
		
		if (monsterName.size() == 0){
			  /* Alert dialog */
				// set title
				alertDialogBuilder.setTitle("You have no suitable Poke!");
				alertDialogBuilder
				.setCancelable(false)
				.setPositiveButton("Close",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
					}
				  });   						
				// create alert dialog
				AlertDialog alertDialog = alertDialogBuilder.create();
				// show it
				alertDialog.show();
		} else {
			CharSequence[] writeToScreen = new CharSequence[monsterName.size()];
			for (int j = 0; j < monsterName.size(); j++)
				writeToScreen[j] = monsterName.get(j);
			// set title
			alertDialogBuilder.setTitle("Select your Poke");
			alertDialogBuilder.setItems(writeToScreen, new DialogInterface.OnClickListener() {
		        public void onClick(DialogInterface dialog,int id) {
		        	if (skillMap.equals("cut")) 
		        		TebangPohon();
		        	else if (skillMap.equals("push"))
		        		DorongBatu();
		        	else if (skillMap.equals("berenang"))
		        		Berenang();
		        }
		    });
			alertDialogBuilder.setCancelable(false);
			alertDialogBuilder.setPositiveButton("Close",new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog,int id) {
				}
			  });
			// create alert dialog
			AlertDialog alertDialog = alertDialogBuilder.create();
			// show it
			alertDialog.show();
		 }		
	}	
	public void LagiBobo(){    
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this.getContext());
		alertDialogBuilder.setTitle("Sudah malam, ikan bobo!!");
		alertDialogBuilder
		.setCancelable(false)
		.setPositiveButton("Bangun",new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog,int id) {
			}
		  });   						
		// create alert dialog
		AlertDialog alertDialog = alertDialogBuilder.create();
		// show it
		alertDialog.show(); 					
	}
	
	public void PilihBid(){   
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this.getContext());
		ArrayList<String> bid = new ArrayList<String>();
		bid.add(" 100");
		bid.add(" 250");
		bid.add(" 500");
		bid.add("1000");
		bid.add("5000");
		
		CharSequence[] writeToScreen = new CharSequence[bid.size()];
		for (int j = 0; j < bid.size(); j++)
			writeToScreen[j] = bid.get(j);
		// set title
		alertDialogBuilder.setTitle("Seberapa jago dirimu??");
		alertDialogBuilder.setItems(writeToScreen, new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog,int id) {
	        	//apa yang mau diset
	        	if (id == 0) {
	        		betSize = 100;
	        		Log.d("bet : ", ""+betSize);
	        	} else if (id == 2){
	        		betSize = 250;
	        	} else if (id == 3){
	        		betSize = 500;
	        	} else if (id == 4){
	        		betSize = 1000;
	        	} else if (id == 5){
	        		betSize = 5000;
	        	}
	        	StartStadium = true;	
	        }
	    });
		alertDialogBuilder.setCancelable(false);
		alertDialogBuilder.setPositiveButton("Gak jadi deh",new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog,int id) {
				StartStadium = false;
			}
		  });
		// create alert dialog
		AlertDialog alertDialog = alertDialogBuilder.create();
		// show it
		alertDialog.show();
	}
	
	public int getJam() { return Jam; }
	public void setJam(int _x) { Jam = _x; }
	
	
	
	
}