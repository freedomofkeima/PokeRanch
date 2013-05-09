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

import java.io.*;
import java.util.*;

import android.app.Activity;
import android.content.*;
import android.util.Log;


public class Player implements Serializable {
	
	private Integer id; //id dari Player
	private String filename; //Nama file dari pemain yang digunakan
	private String name; //Nama pemain yang digunakan
	private Monster_Sendiri listMonster[] = new Monster_Sendiri[6]; //List Monster yang dimiliki oleh pemain
	private ArrayList<Item> listItem = new ArrayList<Item> (); //List Item yang dimiliki oleh pemain
	private Integer money; //Uang yang dimiliki pemain
	private Integer stepCounter; //Jumlah langkah yang telah dilakukan pemain
	private Integer timeCounter; //Variabel penyimpan total waktu bermain
	private Integer NWin; //Jumlah menang pemain
	private Integer NLose; //Jumlah kalah pemain
    private boolean IsSeen[]; //Status apakah suatu Poke pernah ditemukan - size 19 
    private Integer CurrentX; //Current X position
    private Integer CurrentY; //Current Y position
    private Integer CurrentMap; //Current Map position
        
        /* 4 Sekawan */
        //Konstruktor
	public Player(Activity v) throws IOException{
	/* Default Constructor from Player Class */
            /* Kamus Lokal */
            Integer i;
           // PoInteger P(1,1,'*',999); /* Default Starting location */
            /* Algoritma */
            id = 0;
            name = "Okep";
            filename = "okep";
            for (int j = 0; j < 6; j++)
            	listMonster[j] = new Monster_Sendiri();
            try {
             ArrayList<Item> templistItem = Item.LoadItem(v);
             /* Create a new copy address of listItem */
             for (int j = 0; j < templistItem.size(); j++)
            	  listItem.add(new Item(templistItem.get(j)));
            } catch (IOException e){
    			e.printStackTrace();
            }
            listMonster[0].setID(1); //Default Monster
            for (i = 1;i < 6; i++) listMonster[i].setID(0);
            boolean IsSeen[] = new boolean[19];
            for (i = 0; i < 19; i++) IsSeen[i] = false;
            money = 50000;
            stepCounter = 0;
            timeCounter = 0;
            NWin = 0;
            NLose = 0;
            CurrentX = 280; // initial position
            CurrentY = 200;
            CurrentMap = 1;
        }
        
	public Player(Integer _id,String _name,Monster_Sendiri _monsters[],ArrayList<Item> _items,Integer _money,Integer _step,Integer _time,Integer _win, Integer _lose){
        /* User-defined Constructor from Player Class (New Player) */
        }
        
        public Player(Player P){
        /* Copy-constructor from Player Class */
        }

        /* Assignment Operation - Is needed? */

	/* Getter & Setter */
	public Integer GetID(){
	/* Giving Player ID */
            return id;
        }
        
	public String GetFileName(){
	/* Giving Player FileName */
            return filename;
        }
                    
	public String GetName(){
	/* Giving Player Name */
            return name;
        }
        
	public Monster_Sendiri GetMonster(Integer x){
	/* Giving Player Monster */
		return listMonster[x];
	}
        
	public Monster_Sendiri[] GetMonsterArr(){
        /* Giving Player Monster Arr */
		return listMonster;
	}
        
	public ArrayList<Item> GetItem() { 
		/* Giving List of Item */
		return listItem;
	}
        
	public Integer GetMoney(){
	/* Giving Player Money */
            return money;
        }
        
	public Integer GetStepCounter(){
	/* Giving Player StepCounter */
            return stepCounter;
        }
        
	public Integer GetTimeCounter(){
	/* Giving Player TimeCounter */
            return timeCounter;
        }
        
	public Integer GetNWin(){
	/* Giving Player NWin */
            return NWin;
        }
        
	public Integer GetNLose(){
	/* Giving Player NLose */
            return NLose;
        }
        
        
	public boolean GetIsSeen(Integer x){
	/* Giving Player IsSeen Status */
            return IsSeen[x];
        }
	
	public Integer GetCurrentX(){
	/* Giving Player CurrentX Status */
            return CurrentX;
        }
	
	public Integer GetCurrentY(){
	/* Giving Player CurrentY Status */
            return CurrentY;
        }
	
	public Integer GetCurrentMap(){
	/* Giving Player CurrentMap Status */
            return CurrentMap;
        }
	

	public void SetID(Integer _id){
	/* Setting Player ID */
            id = _id;
        }
        
	public void SetFileName(String _filename){
	/* Setting Player FileName */
            filename = _filename;
        }
        
	public void SetName(String _name){
	/* Setting Player Name */
            name = _name;
        }
        
	public void SetIDMonster(Integer x, Integer y){
	/* Setting ID Monster */
            
        }
        
	public void SetMonster(Integer x, Monster_Sendiri y){
	/* Setting Player Monster */
		listMonster[x].setName(y.getName());
		listMonster[x].setAcc(y.getAcc());
		listMonster[x].setCurrentExp(y.getCurrentExp());
		listMonster[x].setDef(y.getDef());
		listMonster[x].setElement(y.getElement());
		listMonster[x].setHP(y.getCurrentHP());
		listMonster[x].setID(y.getID());
		listMonster[x].setLevel(y.getLevel());
		listMonster[x].setlifeSpan(y.getlifeSpan());
		listMonster[x].setListSkill(y.getListSkill());
		listMonster[x].setMaxHP(y.getMaxHP());
		listMonster[x].setMaxMP(y.getMaxMP());
		listMonster[x].setMP(y.getCurrentMP());
		listMonster[x].setPower(y.getPower());
		listMonster[x].setSpecies(y.getSpecies());
		listMonster[x].setStatus(y.getStatus());
		for (int i = 0; i < y.getListSkill().size() ; i++)
			listMonster[x].setIsLearned(y.getIsLearned(i), i);
		//listMonster[x] = new Monster_Sendiri(y);
	}
	
    //public void SetItem(list<Item> I){}
	/* Setting Player Item */
        
	public void SetMoney(Integer _money){
	/* Setting Player Money */
            money = _money;
        }
        
	public void SetStepCounter(Integer _stepCounter){
	/* Setting Player StepCounter */
            stepCounter = _stepCounter;
        }
        
	public void SetTimeCounter(Integer _timeCounter){
	/* Setting Player TimeCounter */
            timeCounter = _timeCounter;
        }
        
	public void SetNWin(Integer _NWin){
	/* Setting Player NWin */
            NWin = _NWin;
        }
        
	public void SetNLose(Integer _NLose){
	/* Setting Player NLose */
            NLose = _NLose;
        }
        
	public void SetIsSeen(Integer x, boolean y){
	/* Setting Player IsSeen Status */
            IsSeen[x] = y;
        }
	
	public void SetCurrentX(Integer _CurrentX){
	/* Setting Player CurrentX Status */
            CurrentX = _CurrentX;
        }
	
	public void SetCurrentY(Integer _CurrentY){
	/* Setting Player CurrentY Status */
           CurrentY = _CurrentY;
        }
	
	public void SetCurrentMap(Integer _CurrentMap){
	/* Setting Player CurrentMap Status */
            CurrentMap = _CurrentMap;
        }
        
	public void SetPrimary(Integer x){
	/* Setting Primary Monster */
         Monster_Sendiri Temp;
         //Swap Monster here
         Temp = listMonster[0];
         listMonster[0] = listMonster[x];
         listMonster[x] = Temp;
        }

	/* Method Lain */
	public boolean movePlayer(String S, Integer i, Map M,boolean IsTerminal){
        //Player bergerak dalam Map

            return true;
        }
        
    //public void TeleportPlayer(String x); //Player akan diteleport ke posisi dir

   public static boolean IsPlayerExist(String x, String y){
        /* Fungsi IsPlayerExist mengembalikan true apabila nama Player sudah pernah dibuat sebelumnya */
        
            return true;
        }
        
	public void ShowItemPlayer(){
	/* { Menampilkan semua isi Backpack Player } */
            
        }

	public void ShowMonsterPlayer(){
	/* { Menampilkan semua Poke yang dimiliki Player } */
        
        }
        
	public String ShowStatusPlayer(){
	/* { I.S.: Player sembarang } */
	/* { F.S.: Menampilkan semua status Player } */
            String S = new String("");
            String Location = "";
            if (CurrentMap == 1) Location = "Home";
            if (CurrentMap == 2) Location = "City";
            if (CurrentMap == 3) Location = "Wild Area";
            if (CurrentMap == 4) Location = "Stadium";
            if (CurrentMap == 5) Location = "Combinatorium";
            if (CurrentMap == 6) Location = "Shop";
            S = S + "Statistics\n";
            S = S + "Name: " + name + "\n";
            S = S + "Money: " + money + "\n";
            S = S + "Location: " + Location + " ( " + CurrentX + " , " + CurrentY + " )\n";
            S = S + "Step Counter: " + stepCounter + "\n";
            S = S + "Win Counter: " + NWin + "\n";
            S = S + "Lose Counter: " + NLose + "\n";
            return S;
        }
        
    public void SleepPlayer(){
        /* { Memulihkan semua HP dan Status dari Monster Player } */
        for (int i = 0; i < 6; i++){
        	 listMonster[i].setHP(listMonster[i].getMaxHP());
        	 listMonster[i].setMP(listMonster[i].getMaxMP());
        	 listMonster[i].setStatus(Status.normal);
        }
        timeCounter = (timeCounter + 10) % 240;
    }
        
	public boolean NewPlayer(String x){
	/* { Membuat Player baru dan mengembalikan true jika Player berhasil dibuat pemain } */
        
            return true;
        }
        
    public void SavePlayer(Context ctx){
	  /* { I.S. : Player sudah terinisialisasi dengan baik } */
	  /* { F.S. : Player tersimpan kedalam sebuah internal memori } */
        	//buat file
        	File file = new File(ctx.getFilesDir(), filename);
        	//write
        	try {
        		BufferedWriter out = new BufferedWriter(new FileWriter(file));
        		//cetak ke file disini
        		out.write(id + " \"" + name + "\"\n"); /* line 1 - id & name */
        		out.write(money + "\n"); /* line 2 - money */
        		/* line 3 - item */
        		for (int i = 0; i < listItem.size(); i++){
        			out.write(listItem.get(i).GetNItem().toString());
        			if (i == listItem.size() - 1) out.write("\n");
        			else out.write(" ");
        		}
        		/* line 4 - map position */
        		out.write(CurrentMap + " " + CurrentX + " " + CurrentY + "\n"); /* Map ID - X and Y position */
        		/* line 5 - monster */
        		for (int i = 0; i < 6; i ++){ /* Monster ID, respectively */
        			out.write(listMonster[i].getID().toString());
        			Log.d("Test",listMonster[i].getID() + "");
        			if (i == 5) out.write("\n");
        			else out.write(" ");
        		} 
        		/* line 6 - 11 monster status */

        		   for (int i = 0; i < 6; i++){ /* Monster Player Data */
        			   out.write("\"" + listMonster[i].getName() + "\" " + listMonster[i].getCurrentExp() + " " + listMonster[i].getlifeSpan() + " " + listMonster[i].getLevel() + " ");
                       out.write(listMonster[i].getHP() + " " + listMonster[i].getMP() + " " + listMonster[i].getMaxHP() + " " + listMonster[i].getMaxMP() + " ");
        		       out.write(listMonster[i].getAcc() + " " + listMonster[i].getPower() + " " + listMonster[i].getDef() + " ");
        				  if (listMonster[i].getStatus() == Status.normal) out.write("1 "); /* Status management */
        				  if (listMonster[i].getStatus() == Status.slept) out.write("2 ");
        				  if (listMonster[i].getStatus() == Status.paralyzed) out.write("3 ");
        				  if (listMonster[i].getStatus() == Status.dead) out.write("4 ");
        				  for (int j = 0; j < 13; j++) //skill
        				    if (listMonster[i].getIsLearned(j)) out.write("1 ");
        				      else out.write("0 ");
        				  if (listMonster[i].getElement() == Element.fire) out.write("1\n"); /* Element management */
        				  if (listMonster[i].getElement() == Element.water) out.write("2\n");
        				  if (listMonster[i].getElement() == Element.grass) out.write("3\n");
        				  if (listMonster[i].getElement() == Element.none) out.write("4\n");
        			   }        		
        		
        		out.write(timeCounter + " " + stepCounter + " " + NWin + " " + NLose + "\n");/* line 12 - TimeCounter - StepCounter - NWin - NLose */
        		/* line 13 - pokedex status (optional) */
        		
        		out.close();
        	} catch (IOException e) {
        		e.printStackTrace();
        	} 

        }
        
    public void LoadPlayer(Context ctx){
        /* { I.S. : Filename tidak null } */
        /* { F.S. : Jika file ada, Player di load kedalam permainan } */
    	//Kamus lokal
    	
        	//buat file
        	File file = new File(ctx.getFilesDir(), filename);
        	//read
        	Scanner scanner;
        	String[] parts;
        	Integer counterLine = 0;
        	Integer currentMonster = 0; /* Temporary variable for current Monster */
        	
        	try {
        		scanner = new Scanner(file);
        		while(scanner.hasNextLine()) {
        			//Log.d(TAG, scanner.next());
        			counterLine += 1;
        			parts = null;
        			parts = scanner.nextLine().split(" ");
        			
        			/* line 1 - id & name */
        			if (counterLine == 1){
        			   id = Integer.valueOf(parts[0]);
        			   name = "";
        			   for (int i = 1; i < parts[1].length() - 1; i++)
        				   name = name + parts[1].charAt(i);
        			}
        			
        			/* line 2 - money */
        			if (counterLine == 2){
        				money = Integer.valueOf(parts[0]);
        			}
        			
        			/* line 3 - Item */
                    
                    if (counterLine == 3)         
                        for (int i = 0; i < listItem.size(); i++){
                        	listItem.get(i).SetNItem(Integer.valueOf(parts[i]));
                        }
                    
            		/* line 4 - map position */
                    if (counterLine == 4){
                    	CurrentMap = Integer.valueOf(parts[0]);
                    	CurrentX = Integer.valueOf(parts[1]);
                    	CurrentY = Integer.valueOf(parts[2]);
                    }
                    
                    /* line 5 - monster */
                    if (counterLine == 5){
                    	for (int i = 0; i < 5; i++)
                    		listMonster[i].setID(Integer.valueOf(parts[i]));
                    }
                    
            		/* line 6 - 11 monster status */
                    if ((counterLine > 5) && (counterLine < 12)){
                    	currentMonster = counterLine - 6;
                    	
                       listMonster[currentMonster].setName("");	
         			   for (int i = 1; i < parts[0].length() - 1; i++)
        				   listMonster[currentMonster].setName(listMonster[currentMonster].getName() + parts[0].charAt(i));
         			   
         			   listMonster[currentMonster].setCurrentExp(Integer.valueOf(parts[1]));
         			   listMonster[currentMonster].setlifeSpan(Integer.valueOf(parts[2]));
         			   listMonster[currentMonster].setLevel(Integer.valueOf(parts[3]));
         			   
         			   listMonster[currentMonster].setHP(Integer.valueOf(parts[4]));
         			   listMonster[currentMonster].setMP(Integer.valueOf(parts[5]));
         			   listMonster[currentMonster].setMaxHP(Integer.valueOf(parts[6]));  
         			   listMonster[currentMonster].setMaxMP(Integer.valueOf(parts[7])); 
         			   
         			   listMonster[currentMonster].setAcc(Integer.valueOf(parts[8]));
         			   listMonster[currentMonster].setPower(Integer.valueOf(parts[9]));
         			   listMonster[currentMonster].setDef(Integer.valueOf(parts[10]));
         			   
         			   if (Integer.valueOf(parts[11]) == 1) listMonster[currentMonster].setStatus(Status.normal);
         			   if (Integer.valueOf(parts[11]) == 2) listMonster[currentMonster].setStatus(Status.slept);
         			   if (Integer.valueOf(parts[11]) == 3) listMonster[currentMonster].setStatus(Status.paralyzed);
         			   if (Integer.valueOf(parts[11]) == 4) listMonster[currentMonster].setStatus(Status.dead);
         			   
        			  for (int j = 0; j < 13; j++) //skill
        				  if (Integer.valueOf(parts[12+j]) == 1) listMonster[currentMonster].setIsLearned(true, j);
        				  else listMonster[currentMonster].setIsLearned(false, j);

        		       /* Element management */
        			   if (Integer.valueOf(parts[25]) == 1) listMonster[currentMonster].setElement(Element.fire);
        			   if (Integer.valueOf(parts[25]) == 2) listMonster[currentMonster].setElement(Element.water);
        			   if (Integer.valueOf(parts[25]) == 3) listMonster[currentMonster].setElement(Element.grass);
        			   if (Integer.valueOf(parts[25]) == 4) listMonster[currentMonster].setElement(Element.none);
     			   
                    }
                    
                    /* line 12 - TimeCounter - StepCounter - NWin - NLose */
                    if (counterLine == 12){
                    	timeCounter = Integer.valueOf(parts[0]);
                    	stepCounter = Integer.valueOf(parts[1]);
                    	NWin = Integer.valueOf(parts[2]);
                    	NLose = Integer.valueOf(parts[3]);
                    }
                    
            		/* line 13 - pokedex status (optional) */
                    
        		}
        		
        	} catch (FileNotFoundException e) {
        		e.printStackTrace();
        	}     
        	
        }
}
