package com.OkepLover.PokeRanch.Game;

import java.util.LinkedHashMap;
import java.util.Random;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class BattleView extends SurfaceView implements SurfaceHolder.Callback{

	//attributes
	public BattleLoopThread thread;
	Resources res = getResources();
	private Monster_Sendiri playerMonster;
	private Monster_Liar enemyMonster;
	//private Monster_Sendiri listM[];
	private Matrix matrix;
	private int screenWidth;
	private int screenHeight;
	
	//Bitmaps
	private Bitmap nightbgs;
	private Bitmap daybgs;
	private Bitmap waterbgs;
	private Bitmap healthbar;
	private Bitmap menubar;
	private Bitmap infobar;
	private Bitmap skillbar;
	private Bitmap enemybar;
	private Bitmap playerimage;
	private Bitmap enemyimage;

	//atribut text2 yang ada di layar
	private Paint monsterName = new Paint();
	private Paint enemyMonsterName = new Paint();
	private Paint enemyMonsterLv = new Paint();
	private Paint monsterLv = new Paint();
	private Paint currentMPText = new Paint();
	private Paint maxMPText = new Paint();
	private Paint infoText = new Paint();
	
	//isi text
	private String msg;
	private String myLv;
	private String enemyLv;
	private String name;
	private String enemyName;
	private String currentMP;
	private String maxMP;
	
	//rect & isi string buat skill bar
	//private String listSkillText[];
	private Paint listSkill1 = new Paint();
	private Rect listSkillRect[] = new Rect[4];
	private String listSkillText[] = {"N/A","N/A","N/A","N/A"};
	
	//status dialog
	private String statusmsg;
	private Bitmap statusdialog;
	
	//backgound
	private Rect sourceRect; 
	private Rect menuRect;
	private int currentFrame = 0;
	private int spriteFrameWidth;
	private int spriteFrameHeight;
	
	//health bar
	private Rect healthRect;
	private Rect enemyhealthRect;
	private int healthbarHeight;
	private int healthbarWidth;
	private int enemybarHeight;
	private int enemybarWidth;
	private Integer enemyhealthFrame = 8;
	private Integer healthFrame = 8;
	
	//flags
	private boolean infobarEnabled = false; //penanda apakah infobar aktif
	private boolean skillbarEnabled = false; //penanda apa sedang menampilkan daftar skill
	private boolean done = false; //penanda apakah battle selesai
	private boolean itemenabled = false;
	private boolean statusdialogenabled = false;
	private boolean selectmonsterEnabled = false;
	
	private static Activity changeMonster; /* An activity holder */
	private static Activity useItem; /* An activity holder */
	private boolean infoCode = false;
	
	public BattleView(Context context, int widthPixels, int heightPixels, Monster_Liar M2) {
		super(context);
		getHolder().addCallback(this);
		setFocusable(true);	
		for (int i = 0 ; i < MainGameActivity.ListSkill.size() ; i++)
		Log.d("BATTLEVIEW",MainGameActivity.ListSkill.get(i).getName());

		//get monster info
		playerMonster = MainGameActivity.getPlayerFromBackpack().GetMonster(0);
		initDisplay();
		Log.d("PLAYER","Lv : "+myLv);
		Log.d("PLAYER","MP : "+currentMP);
		Log.d("PLAYER","HP : "+playerMonster.getCurrentHP().toString());
		enemyMonster = M2;
		enemyName = enemyMonster.getName();
		enemyLv = enemyMonster.getLevel().toString();
		//listM = listMonster;	
		
		//inisialisasi skill bar
		Integer j = 0;
    	for (int i = 0;i<=3;i++){
    		while (j < MainGameActivity.ListSkill.size() && !playerMonster.getIsLearned(j)){
    			j++;
    		}
    		if (j >= MainGameActivity.ListSkill.size()){
            	break;
            } else if (playerMonster.getIsLearned(j)){
         		listSkillText[i] = MainGameActivity.ListSkill.get(j).getName();
         		j++;
         	}
    	}
    	for (int i = 0; i<4 ;i++){
    		listSkillRect[i] = new Rect(0,0,0,0);
    	}
		
		screenWidth = widthPixels;
		screenHeight = heightPixels;
		healthbar = DrawableManager.getInstance().getHealthBarBitmap();
		healthbarHeight = healthbar.getHeight() /3;
		healthbarWidth = healthbar.getWidth() / 3;
		enemybar = DrawableManager.getInstance().getEnemyBarBitmap();
		enemybarHeight = enemybar.getHeight() / 3;
		enemybarWidth = enemybar.getWidth() / 3;
		menubar = DrawableManager.getInstance().getMenuBar();
		infobar = DrawableManager.getInstance().getInfoBar();
		skillbar = DrawableManager.getInstance().getSkillBar();
		daybgs = BitmapFactory.decodeResource(res, R.drawable.daybgs);
		nightbgs = BitmapFactory.decodeResource(res, R.drawable.nightbgs);
		waterbgs = BitmapFactory.decodeResource(res, R.drawable.waterbgs);
		spriteFrameWidth = waterbgs.getWidth();
		spriteFrameHeight = waterbgs.getHeight();
		msg = "N/A";
	}

	private void initDisplay(){
		name = playerMonster.getName();
		myLv = playerMonster.getLevel().toString();
		currentMP = playerMonster.getCurrentMP().toString();
		maxMP = playerMonster.getMaxMP().toString();
	}
	
	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub	
	}

	@Override
	public void surfaceCreated(SurfaceHolder arg0) {
		// TODO Auto-generated method stub
		initBattleLoop();
		Log.d("LOOP","surfaceCreated");
	}

	private void initBattleLoop() {
		if (thread == null || !thread.isAlive()){
			thread = new BattleLoopThread(getHolder(),this);
			thread.start();
		}
		thread.setRunning(true);
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {
		
	}
	public void render(Canvas canvas) {				
		canvas.setMatrix(matrix);
		drawBackground(canvas);
		drawHealthBar(canvas);
		drawEnemyHealthBar(canvas);
		drawMonsterName(canvas);
		drawEnemyMonsterName(canvas);
		drawMonsterLv(canvas);
		drawEnemyLv(canvas);
		drawCurrentMP(canvas);
		drawMaxMP(canvas);
		drawMonster(canvas);
		if (ItemBattleActivity.getIsFinish()) {
			infobarEnabled = true;
			msg = "Catched a monster!";
			done = true;
			ItemBattleActivity.setIsFinish(false);
		}
        if (infobarEnabled) {
        	drawInfoBar(canvas);
        	drawInfoText(canvas);
        	/* An additional command for change Monster */
        	infoCode = true;
        } else if (skillbarEnabled){
        	drawSkillBar(canvas);
        	drawSkillElement(canvas);
        	drawSkill(canvas);
        	} else if (itemenabled){
            /* Please update Player at Backpack first */
            	if (useItem == null) {
            		 if (infoCode == true){
                		 itemenabled = false;
                		 playerMonster = MainGameActivity.getPlayerFromBackpack().GetMonsterArr()[0];
                		 initDisplay();
                         enemyTurn();
            		 } else { 
            			 drawItemBackground(canvas);
                         msg = "Item has been used!";
                         infobarEnabled = true;    			 
            		 }
            	}
            	
        	//if (selected){
        	//	drawItemSelected(canvas,selectedRect);
        	//	drawItemInfo(canvas);
        	//	drawItemButton(canvas);
        	//}
        } else if (statusdialogenabled){
        	drawStatusDialog(canvas);
        } else if (selectmonsterEnabled){
        	/* Please update Player at Backpack first */
        	if (changeMonster == null) {
        		 if (infoCode == true){
            		 selectmonsterEnabled = false;
            		 playerMonster = MainGameActivity.getPlayerFromBackpack().GetMonsterArr()[0];
            		 initDisplay();
                     enemyTurn();
            		 
        		 } else {
        			 
        	         drawSelectMonsterBackground(canvas);
                     msg = "Change Monster to "+playerMonster.getName();
                     infobarEnabled = true;
                     name = playerMonster.getName();
                     myLv = playerMonster.getLevel().toString();       			 
        		 }
        	}
        	
        } else
        	drawMenuBattle(canvas);
	}

	public void update() {
		//update healthbar
		healthFrame = getHealthFrame();
		enemyhealthFrame = getEnemyHealthFrame();
		currentMP = playerMonster.getCurrentMP().toString();
		maxMP = playerMonster.getMaxMP().toString();
		//cekstatus, kalo != normal, tampilin dialog
		if (playerMonster.getStatus()!=Status.normal){
			if (playerMonster.getStatus() == Status.slept){
				statusmsg = playerMonster.getName()+" is fall a sleep!\n";
				playerMonster.setStatus(Status.normal);
				statusdialogenabled = true;
			} else if (playerMonster.getStatus() == Status.paralyzed){
				statusdialogenabled = true;
				statusmsg = playerMonster.getName()+" is paralyzed!\n";
			}
		}
	}
	
	public boolean onTouchEvent(MotionEvent event) {
	    int touchX = (int) event.getX();
		int touchY = (int) event.getY();
		int y = 340; // initial position
		int x = screenWidth / 2;
		Rect infoRect = new Rect(0, screenHeight - infobar.getHeight(), screenWidth, screenHeight);
		Rect FightRect = new Rect (x, y, x + screenWidth / 4, 410);
		Rect PokeRect = new Rect (x, 410, x + screenWidth / 4, screenHeight);
		Rect BagRect = new Rect (x + screenWidth / 4, y, screenWidth, 410);
		Rect RunRect = new Rect (x + screenWidth / 4, 410, screenWidth, screenHeight);
		final int actioncode = event.getAction() & MotionEvent.ACTION_MASK;	
		if (!infobarEnabled && !skillbarEnabled  && !statusdialogenabled){ //user tidak dapat menekan tombol bila sedang ada infobar
			switch (actioncode) {
				case MotionEvent.ACTION_DOWN:
	                if(FightRect.contains(touchX,touchY)){//Skill
	                    playerTurn();
	                    Log.d("HP",playerMonster.getCurrentHP().toString());
	                } else if(PokeRect.contains(touchX,touchY)){//Change Monster
	                    msg = "Change Monster";
	                    selectmonsterEnabled = true;
	                    
	                	/* An User-interface for changing Poke */
	                    infoCode = false;
	            		/* End of User-interface */
	                    if (currentFrame < 12){
	                    	currentFrame++;
	                    } else
	                    	currentFrame = 0;

	                } else if(BagRect.contains(touchX,touchY)){//Item
	                    msg = "Item";
	                    itemenabled = true;
	                    /* An User-interface for using Item */
	                    infoCode = false;
	                    //lanjut ke action itembarenabled 
	                } else if(RunRect.contains(touchX,touchY)){ //Escape
	                	if (MainGameActivity.getPlayerFromBackpack().GetCurrentMap()!=4){
		                	Random rand = new Random();
		                	if (rand.nextInt(10)>5){
			                    infobarEnabled = true;
			                    done = true;
			                    msg = "Go away, you chicken !";
		                	} else {
		                		infobarEnabled = true;
		                		msg = "Failed to flee!";
		                		if(infoRect.contains(touchX,touchY))
		                			enemyTurn();
		                	}
	                	} else { //lagi di stadium g bisa kabur
	                		infobarEnabled = true;
	                		msg = "You cannot escape from a battle!";
	                	}
					break;	
	                }
			}
			//menu info2
		} else if (infobarEnabled){
			switch (actioncode){
			case MotionEvent.ACTION_DOWN:
				if(infoRect.contains(touchX,touchY)){
					if (done){
						((Activity) getContext()).finish();
					} else 
						infobarEnabled = false;
				}
			}
			//menu skill
		} else if (skillbarEnabled){
			listSkillRect[0] = new Rect(30,spriteFrameHeight+20,215,screenHeight-20);
			listSkillRect[1] = new Rect(215,spriteFrameHeight+20,400,screenHeight-20);
			listSkillRect[2] = new Rect(400,spriteFrameHeight+20,585,screenHeight-20);
			listSkillRect[3] = new Rect(585,spriteFrameHeight+20,770,screenHeight-20);
			switch (actioncode){
			case MotionEvent.ACTION_DOWN:
				if((listSkillRect[0].contains(touchX,touchY))&& isSkillEnabled(listSkillText[0])){
					useSkill(0);
				} else if(listSkillRect[1].contains(touchX,touchY)&& isSkillEnabled(listSkillText[1])){
					useSkill(1);
				} else if(listSkillRect[2].contains(touchX,touchY)&& isSkillEnabled(listSkillText[2])){
					useSkill(2);
				} else if(listSkillRect[3].contains(touchX,touchY)&& isSkillEnabled(listSkillText[3])){
					useSkill(3);
				} else { // kalau user pencet selain skillbar
					skillbarEnabled = false;
				}
			}
			//menu item
		} else if (statusdialogenabled){
			Random random = new Random();
			if (touchX>600 && touchY>200){
				if (playerMonster.getStatus()==Status.paralyzed){
					if (random.nextInt(10) > 5){
						playerMonster.setStatus(Status.normal);
						statusdialogenabled = false;
					} else {
						enemyTurn();
					}
					statusdialogenabled = false;
				}
			}
		}
		return true;

	}
	
	private void useSkill(int id){
		int j = 0;
		Skill S = new Skill();
    	for (int i = 0;i <= id ;){
    		while (j < MainGameActivity.ListSkill.size() && !playerMonster.getIsLearned(j)){
    			j++;
    		}
    		if (j >= MainGameActivity.ListSkill.size()){
    			break;
    		} else if ((playerMonster.getIsLearned(j))&& i == id){
    			S = MainGameActivity.ListSkill.get(j);
    			break;
    		} else if ((playerMonster.getIsLearned(j))&& i < id){
    			j++;
    			i++;
    		}
    	}
		if (playerMonster.getIsLearned(j)){
			if (isEnoughMP(MainGameActivity.ListSkill.get(j).getCost())){
				//enemyMonster.setCurrentHP(enemyMonster.getCurrentHP() - playerMonster.getListSkill().get(id).getValue());
				//playerMonster.setCurrentMP(playerMonster.getCurrentMP() - playerMonster.getListSkill().get(id).getCost());
				msg = S.UseSkill(playerMonster, enemyMonster);
				infobarEnabled = true;
				skillbarEnabled = false;
				finishMove();
			} else {
				msg = "Not enough MP!";
				skillbarEnabled = false;
				infobarEnabled = true;
			}
		} else {
			skillbarEnabled = false;
		}
	}
	
	private void finishMove(){
        if (enemyMonster.getCurrentHP()<=0){
        	enemyMonster.setHP(0);
        	infobarEnabled = true;
        	done = true;
        	msg = "You Win !";
			playerMonster.victory(enemyMonster.getExpReward());
			if (MainGameActivity.getPlayerFromBackpack().GetCurrentMap() == 3){ //di alam liar
				MainGameActivity.getPlayerFromBackpack().SetMoney(MainGameActivity.getPlayerFromBackpack().GetMoney()+enemyMonster.getMoneyReward());
			} else if (MainGameActivity.getPlayerFromBackpack().GetCurrentMap() == 4){ //di stadium
				MainGameActivity.getPlayerFromBackpack().SetMoney(MainGameActivity.getPlayerFromBackpack().GetMoney()+MainGameView.betSize);
			}
			MainGameActivity.getPlayerFromBackpack().SetNWin(MainGameActivity.getPlayerFromBackpack().GetNWin() + 1);
        } else {
        	enemyTurn();
        }
	}
	
	private void enemyTurn(){
		Random rand = new Random();
		if (enemyMonster.getStatus() != Status.normal){
			if (enemyMonster.getStatus() == Status.slept){
				msg = enemyMonster.getName()+" is sleeping!";
				infobarEnabled = true;
			} else if (enemyMonster.getStatus() == Status.paralyzed){
				if (rand.nextInt(10) <= 5){
					msg = enemyMonster.getName()+" is paralyzed!";
					infobarEnabled = true;
				} 
			}
		}
		
		if (enemyMonster.getStatus() == Status.normal){ //aksi enemy
			Log.d("Battle stat","EnemyPow : "+enemyMonster.getPower().toString());
			Log.d("Battle stat","PlayerPow : "+playerMonster.getPower().toString());
	        //playerMonster.setCurrentHP(playerMonster.getCurrentHP()-25);
	        //msg = "Enemy Uses paralyzing!";
	        //playerMonster.setStatus(Status.paralyzed);
			//Iterator<Skill> i = enemyMonster.getListSkill().iterator();
			//cek apah ada skill yang bisa membuat monster menang dalam 1 hit
			Skill s = new Skill();
			if (enemyMonster.getCurrentHP() > enemyMonster.getMaxHP()*0.1) {
				for (int i= 0; i<enemyMonster.getListSkill().size();++i){
					s = enemyMonster.getListSkill().get(i);
					if ((s.getEffect() == Effect.damaging) && ((3*enemyMonster.getPower() - 2*enemyMonster.getDef() + s.getValue()) >= enemyMonster.getCurrentHP()) && s.isEnabled()){
						if (s.getCost() <= enemyMonster.getCurrentMP()) {
							msg = s.UseSkill(enemyMonster, playerMonster);
							Log.d("Battle stat","Enemy damage : "+s.getValue());
							if( playerMonster.getHP() <= 0 )
							{
								playerMonster.setHP(0);
								msg = enemyMonster.getName() + " has defeated " + playerMonster.getName();
								infobarEnabled = true;
							}
							break;
						}
					} else {
						int roll; //dadu.untuk menampung nilai random
						//Eksekusi skill berdasar jenis skillnya
						s = enemyMonster.getListSkill().get(i);
						if (s.getEffect() == Effect.damaging) msg = "lulucu2"; 
						if ((s.getEffect() == Effect.damaging) && s.isEnabled()){
							if (s.getCost() <= enemyMonster.getCurrentMP()){
								if (s.getEffect() == Effect.damaging) msg = "lulucu3"; 
								roll = Utilities.rand(1, 100);
								if ((roll <= 20) && (i < enemyMonster.getListSkill().size())) continue;
								msg = s.UseSkill(enemyMonster, playerMonster);
								infobarEnabled = true;
								if( playerMonster.getCurrentHP() <= 0 )
								{
									playerMonster.setHP(0);
									msg = enemyMonster.getName() + " has defeated " + playerMonster.getName();
									infobarEnabled = true;
								}
								break;
							}
						}
						if ((s.getEffect() == Effect.paralyzing) && s.isEnabled()){
							if (s.getCost() <= enemyMonster.getCurrentMP()){
								msg = s.UseSkill(enemyMonster, playerMonster);
								infobarEnabled = true;
								break;
							}
						}
						if ((s.getEffect() == Effect.hypnotize) && s.isEnabled()){
							if (s.getCost() <= enemyMonster.getCurrentMP()){
								msg = s.UseSkill(enemyMonster, playerMonster);
								infobarEnabled = true;
								break;
							}
						}
						if ((s.getEffect() == Effect.buffing) && s.isEnabled()){
							if (s.getCost() <= enemyMonster.getCurrentMP()){
								msg = s.UseSkill(enemyMonster, playerMonster);
								infobarEnabled = true;
								break;
							}
						}
						if ((s.getEffect() == Effect.debuffing) && s.isEnabled()){
							if (s.getCost() <= enemyMonster.getCurrentMP()){
								msg = s.UseSkill(enemyMonster, playerMonster);
								infobarEnabled = true;
								break;
							}
						}
					}
				}
			} else {
				//cek apakah monster dalam keadaan sekarat, jika iya lakukan heal, atau kabur
				int roll; //dadu.untuk menampung nilai random
				//iterasi setiap skill yang dimiliki monster
				for (int i= 0; i<enemyMonster.getListSkill().size();++i){
					s = enemyMonster.getListSkill().get(i);
					if ((s.getEffect() == Effect.healing) && s.isEnabled()){
						if (s.getCost() <= enemyMonster.getCurrentMP()){ //cek apakah MP monster masih cukup untuk meng-cast skill
							//eksekusi skill
							msg = s.UseSkill(enemyMonster,playerMonster);
							infobarEnabled = true;
							break;
						}
					}
				}
				
				if (MainGameActivity.getPlayerFromBackpack().GetCurrentMap() != 4){// jika tidak di stadium, bisa kabur
					//Monster tidak memiliki mekanisme untuk menyembuhkan diri, maka monster akan mencoba kabur dari battle
					msg = enemyMonster.getName() + " is trying to flee!";
					roll = Utilities.rand(1, 5);
					if (roll == 3){ // kemungkinan untuk kabur 20%
						msg = enemyMonster.getName() + " fleed!";
						infobarEnabled = true;
						done = true;
						 //Monster berhasil kabur
					} else {
						msg = enemyMonster.getName() + " failed to flee"; //monster gagal kabur, gilirannya terbuang
						infobarEnabled = true;
					}
				}
			}
		}
		
		if (enemyMonster.getStatus() == Status.paralyzed){ //kalo paralyzed
			Random random = new Random();
			if (random.nextInt(10)>5){ // 50% peluang sembuh
				enemyMonster.setStatus(Status.normal);
			}
		}
		
		if (enemyMonster.getStatus() == Status.slept){ //kalo slept turn berikutnya sembuh
			enemyMonster.setStatus(Status.normal);
		}
		
		//cek kondisi player
		if (playerMonster.getCurrentHP()<=0){
			playerMonster.setHP(0);
			infobarEnabled = true;
			done = true;
			msg = "You lose !";
			if (MainGameActivity.getPlayerFromBackpack().GetCurrentMap() == 4){ //di stadium
				MainGameActivity.getPlayerFromBackpack().SetMoney(MainGameActivity.getPlayerFromBackpack().GetMoney()-MainGameView.betSize);
			}
			MainGameActivity.getPlayerFromBackpack().SetNLose(MainGameActivity.getPlayerFromBackpack().GetNLose() + 1);
		}
	}
	
	private void playerTurn(){
		skillbarEnabled = true;
	}
	
	private boolean isEnoughMP(int MP){
		return (playerMonster.getCurrentMP() >= MP);
	}
	
	private void drawBackground(Canvas canvas) {
		canvas.drawColor(Color.BLACK);// clear screen
		//where to draw the sprite
		Rect destRect = new Rect(0, 0, spriteFrameWidth, spriteFrameHeight);
		if (currentFrame == 2){
			canvas.drawBitmap(waterbgs, sourceRect, destRect, null);
		} else if (currentFrame == 1){
			canvas.drawBitmap(nightbgs, sourceRect, destRect, null);
		} else {
			canvas.drawBitmap(daybgs, sourceRect, destRect, null);
		}	
	}

	private int getHealthFrame(){
		return (int) ((playerMonster.getCurrentHP().floatValue() / playerMonster.getMaxHP()) * 8);
	}
	
	private int getEnemyHealthFrame(){
		return (int) ((enemyMonster.getCurrentHP().floatValue() / enemyMonster.getMaxHP()) * 8);
	}
	
	private void drawHealthBar(Canvas canvas){
		healthRect = new Rect(0,0,healthbarWidth,healthbarHeight);
		this.healthRect.left = (healthFrame % 3) * healthbarWidth;
		this.healthRect.right = this.healthRect.left + healthbarWidth;
		this.healthRect.top = (healthFrame / 3) * healthbarHeight;
		this.healthRect.bottom = this.healthRect.top + healthbarHeight;
		Rect destRect = new Rect(screenWidth - healthbarWidth, spriteFrameHeight - healthbarHeight, screenWidth, spriteFrameHeight);
		canvas.drawBitmap(healthbar, healthRect, destRect, null);
	}
	
	private void drawEnemyHealthBar(Canvas canvas){
		enemyhealthRect = new Rect(0,0,enemybarWidth,enemybarHeight);
		this.enemyhealthRect.left = (enemyhealthFrame % 3) * enemybarWidth;
		this.enemyhealthRect.right = this.enemyhealthRect.left + enemybarWidth;
		this.enemyhealthRect.top = (enemyhealthFrame / 3) * enemybarHeight;
		this.enemyhealthRect.bottom = this.enemyhealthRect.top + enemybarHeight;
		Rect destRect = new Rect(0, enemybarHeight/2, enemybarWidth, enemybarHeight+enemybarHeight/2);
		canvas.drawBitmap(enemybar, enemyhealthRect, destRect, null);
	}
	
	private void drawMenuBattle(Canvas canvas){
		menuRect = new Rect(0,0,menubar.getWidth(),menubar.getHeight());
		Rect destRect = new Rect(0, spriteFrameHeight, screenWidth, screenHeight);
		canvas.drawBitmap(menubar, menuRect, destRect, null);
	}
	
	private void drawMonsterName(Canvas canvas){
		monsterName.setTextSize(35);
		monsterName.setTypeface(Typeface.MONOSPACE);
		monsterName.setColor(Color.WHITE);
		canvas.drawText(name, screenWidth - healthbarWidth + 70, spriteFrameHeight - healthbarHeight + 50, monsterName);
	}
	
	private void drawEnemyMonsterName(Canvas canvas){
		enemyMonsterName.setTextSize(35);
		enemyMonsterName.setTypeface(Typeface.MONOSPACE);
		enemyMonsterName.setColor(Color.WHITE);
		canvas.drawText(enemyName, 10, enemybarHeight, enemyMonsterName);
	}
	
	private void drawEnemyLv(Canvas canvas){
		enemyMonsterLv.setTextSize(25);
		enemyMonsterLv.setTypeface(Typeface.MONOSPACE);
		enemyMonsterLv.setColor(Color.WHITE);
		canvas.drawText(enemyLv,enemybarWidth-100, enemybarHeight/2 + 50,enemyMonsterLv);
	}
	
	private void drawMonsterLv(Canvas canvas){
		monsterLv.setTextSize(25);
		monsterLv.setTypeface(Typeface.MONOSPACE);
		monsterLv.setColor(Color.WHITE);
		canvas.drawText(myLv, screenWidth - healthbarWidth + 320, spriteFrameHeight - healthbarHeight + 45 , monsterLv);
	}
	
	private void drawCurrentMP(Canvas canvas){
		currentMPText.setTextSize(25);
		currentMPText.setTypeface(Typeface.MONOSPACE);
		currentMPText.setColor(Color.WHITE);
		canvas.drawText(currentMP, screenWidth - healthbarWidth + 220, spriteFrameHeight - 15 , currentMPText);
	}
	
	private void drawMaxMP(Canvas canvas){
		maxMPText.setTextSize(25);
		maxMPText.setTypeface(Typeface.MONOSPACE);
		maxMPText.setColor(Color.WHITE);
		canvas.drawText(maxMP, screenWidth - healthbarWidth + 280, spriteFrameHeight - 15 , maxMPText);
	}
	
	private void drawInfoText(Canvas canvas) {
		infoText.setTextSize(25);
		infoText.setTypeface(Typeface.MONOSPACE);
		infoText.setColor(Color.WHITE);
		canvas.drawText(msg, 40, spriteFrameHeight+50, infoText);
	}
	
	private void drawInfoBar(Canvas canvas){
		menuRect = new Rect(0,0,infobar.getWidth(),infobar.getHeight());
		Rect destRect = new Rect(0, spriteFrameHeight, screenWidth, screenHeight);
		canvas.drawBitmap(infobar, menuRect, destRect, null);
	}
	
	private void drawSkillBar(Canvas canvas){
		menuRect = new Rect(0,0,infobar.getWidth(),infobar.getHeight());
		Rect destRect = new Rect(0, spriteFrameHeight, screenWidth, screenHeight);
		canvas.drawBitmap(skillbar, menuRect, destRect, null);
	}
	
	private void drawSkill(Canvas canvas){
		for (int i = 0;i<=3;i++)
		if (!listSkillText[i].equals("N/A")){
			listSkill1.setTextSize(30);
			listSkill1.setTypeface(Typeface.MONOSPACE);
			listSkill1.setColor(Color.DKGRAY);
			canvas.drawText(listSkillText[i], 50+i*185, spriteFrameHeight+60, listSkill1);
		}
	}
	
	private void drawItemBackground(Canvas canvas){
		/* Caution! There's no back button here :v Decide wisely~ */
		if (useItem == null){
			Intent i = new Intent(this.getContext(),ItemBattleActivity.class);
			LinkedHashMap<String, Object> obj = new LinkedHashMap<String, Object>();
			obj.put("enemyData", enemyMonster);
			if (MainGameActivity.getPlayerFromBackpack().GetCurrentMap().equals((Integer) 4)) obj.put("AreaCode",2);
			  else obj.put("AreaCode",1);
			obj.put("EnemyPercent", (Integer) enemyMonster.getHP() / enemyMonster.getMaxHP() * 100);
			
			Bundle b = new Bundle();
			b.putSerializable("bundleobject", obj);
			i.putExtras(b);
			
			this.getContext().startActivity(i);	
		}
	}

	private boolean isSkillEnabled(String s){
		return !s.equals("N/A");
	}
	
	
	private void drawMonster(Canvas canvas){
		if (playerMonster.getID() == 1) playerimage = BitmapFactory.decodeResource(res, R.drawable.momon1);
		if (playerMonster.getID() == 2) playerimage = BitmapFactory.decodeResource(res, R.drawable.momon2);
		if (playerMonster.getID() == 3) playerimage = BitmapFactory.decodeResource(res, R.drawable.momon3);
		if (playerMonster.getID() == 4) playerimage = BitmapFactory.decodeResource(res, R.drawable.momon4);
		if (playerMonster.getID() == 5) playerimage = BitmapFactory.decodeResource(res, R.drawable.momon5);
		if (playerMonster.getID() == 6) playerimage = BitmapFactory.decodeResource(res, R.drawable.momon6);
		if (playerMonster.getID() == 7) playerimage = BitmapFactory.decodeResource(res, R.drawable.momon7);
		if (playerMonster.getID() == 8) playerimage = BitmapFactory.decodeResource(res, R.drawable.momon8);
		if (playerMonster.getID() == 9) playerimage = BitmapFactory.decodeResource(res, R.drawable.momon9);
		if (playerMonster.getID() == 10) playerimage = BitmapFactory.decodeResource(res, R.drawable.momon10);		
		if (playerMonster.getID() == 11) playerimage = BitmapFactory.decodeResource(res, R.drawable.momon11);
		if (playerMonster.getID() == 12) playerimage = BitmapFactory.decodeResource(res, R.drawable.momon12);
		if (playerMonster.getID() == 13) playerimage = BitmapFactory.decodeResource(res, R.drawable.momon13);
		if (playerMonster.getID() == 14) playerimage = BitmapFactory.decodeResource(res, R.drawable.momon14);
		if (playerMonster.getID() == 15) playerimage = BitmapFactory.decodeResource(res, R.drawable.momon15);
		if (playerMonster.getID() == 16) playerimage = BitmapFactory.decodeResource(res, R.drawable.momon16);
		if (playerMonster.getID() == 17) playerimage = BitmapFactory.decodeResource(res, R.drawable.momon17);
		if (playerMonster.getID() == 18) playerimage = BitmapFactory.decodeResource(res, R.drawable.momon18);
		if (playerMonster.getID() == 19) playerimage = BitmapFactory.decodeResource(res, R.drawable.momon19);
		if (playerMonster.getID() == 20) playerimage = BitmapFactory.decodeResource(res, R.drawable.momon20);
		
		if (enemyMonster.getID() == 1) enemyimage = BitmapFactory.decodeResource(res, R.drawable.momon1);
		if (enemyMonster.getID() == 2) enemyimage = BitmapFactory.decodeResource(res, R.drawable.momon2);
		if (enemyMonster.getID() == 3) enemyimage = BitmapFactory.decodeResource(res, R.drawable.momon3);
		if (enemyMonster.getID() == 4) enemyimage = BitmapFactory.decodeResource(res, R.drawable.momon4);
		if (enemyMonster.getID() == 5) enemyimage = BitmapFactory.decodeResource(res, R.drawable.momon5);
		if (enemyMonster.getID() == 6) enemyimage = BitmapFactory.decodeResource(res, R.drawable.momon6);
		if (enemyMonster.getID() == 7) enemyimage = BitmapFactory.decodeResource(res, R.drawable.momon7);
		if (enemyMonster.getID() == 8) enemyimage = BitmapFactory.decodeResource(res, R.drawable.momon8);
		if (enemyMonster.getID() == 9) enemyimage = BitmapFactory.decodeResource(res, R.drawable.momon9);
		if (enemyMonster.getID() == 10) enemyimage = BitmapFactory.decodeResource(res, R.drawable.momon10);
		if (enemyMonster.getID() == 11) enemyimage = BitmapFactory.decodeResource(res, R.drawable.momon11);
		if (enemyMonster.getID() == 12) enemyimage = BitmapFactory.decodeResource(res, R.drawable.momon12);
		if (enemyMonster.getID() == 13) enemyimage = BitmapFactory.decodeResource(res, R.drawable.momon13);
		if (enemyMonster.getID() == 14) enemyimage = BitmapFactory.decodeResource(res, R.drawable.momon14);
		if (enemyMonster.getID() == 15) enemyimage = BitmapFactory.decodeResource(res, R.drawable.momon15);
		if (enemyMonster.getID() == 16) enemyimage = BitmapFactory.decodeResource(res, R.drawable.momon16);
		if (enemyMonster.getID() == 17) enemyimage = BitmapFactory.decodeResource(res, R.drawable.momon17);
		if (enemyMonster.getID() == 18) enemyimage = BitmapFactory.decodeResource(res, R.drawable.momon18);
		if (enemyMonster.getID() == 19) enemyimage = BitmapFactory.decodeResource(res, R.drawable.momon19);
		if (enemyMonster.getID() == 20) enemyimage = BitmapFactory.decodeResource(res, R.drawable.momon20);
		
		canvas.drawBitmap(playerimage, 170, 210, null);
		canvas.drawBitmap(enemyimage, 550, 90, null);
	}
	
	private void drawStatusDialog(Canvas canvas){
		statusdialog = BitmapFactory.decodeResource(res, R.drawable.statusdialog);
		canvas.drawBitmap(statusdialog, 0, 0, null);
		Paint text = new Paint();
		text.setTextSize(25);
		text.setTypeface(Typeface.MONOSPACE);
		text.setColor(Color.WHITE);
		canvas.drawText(statusmsg, 150, 200, text);
	}
	
	private void drawSkillElement(Canvas canvas){
		int j = 0;
		Element element;
    	for (int i = 0;i<4;i++){
    		while (j < MainGameActivity.ListSkill.size() && !playerMonster.getIsLearned(j)){
    			j++;
    		}
    		if (j >= MainGameActivity.ListSkill.size()){
            	break;
            } else if (playerMonster.getIsLearned(j)){
            	element = MainGameActivity.ListSkill.get(j).getElement();
            	int frame = 3;
    			if (element == Element.water){
    				frame = 0;
    			} else if (element == Element.fire){
    				frame = 1;
    			} else if (element == Element.grass){
    				frame = 2;
    			} else if (element == Element.none){
    				frame = 3;
    			}
    			Bitmap skillelement = BitmapFactory.decodeResource(res, R.drawable.skillelement);
    			Rect elementRect = new Rect(0,0,skillelement.getWidth()/2,skillelement.getHeight()/2);
    			elementRect.left = (frame % 2) * skillelement.getWidth()/2;
    			elementRect.right = elementRect.left + skillelement.getWidth()/2;
    			elementRect.top = (frame / 2) * skillelement.getHeight()/2;
    			elementRect.bottom = elementRect.top+ skillelement.getHeight()/2;
    			if (listSkillRect[i] == null){
    				Log.d("DRAW", "skillRect");
    			} else {
    				canvas.drawBitmap(skillelement, elementRect, listSkillRect[i], null);
    			}
         		j++;
         	}
    	}
	}
	
	private void drawSelectMonsterBackground(Canvas canvas){
		//canvas.drawBitmap(selectmonster, 0, 0, null);
		if (changeMonster == null){
			Intent i = new Intent(this.getContext(),MonsterMenu.class);
			i.putExtra("MonsterCode",1);
			this.getContext().startActivity(i);	
		}
	}
	
	public static Activity getMonsterAct() {return changeMonster;}
	public static void setMonsterAct(Activity _act) { changeMonster = _act; }
	
	public static Activity getItemAct() {return useItem;}
	public static void setItemAct(Activity _act) { useItem = _act; }
	
}
