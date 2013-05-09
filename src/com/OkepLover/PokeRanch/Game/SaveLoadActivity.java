/*
 * [IF2032] Pemrograman Beroirentasi Obyek
 * Mobile PokeRanch
 */
package com.OkepLover.PokeRanch.Game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

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
public class SaveLoadActivity extends Activity {

	private Integer MapCode = 0;
	private Activity thisAct = this;
	private Player P;
	private String choice; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.load_activity);
		
        Bundle bn = new Bundle();
        bn = getIntent().getExtras();
        MapCode = (Integer) bn.get("MapCode");
        
        Button savebtn = (Button) findViewById(R.id.savebtn);
        Button loadbtn = (Button) findViewById(R.id.loadbtn);
        Button backbtn = (Button) findViewById(R.id.backbtn);
        
        if (MapCode == 1){
    		try {
    			P = new Player(this);
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        } else {
        	P = MainGameActivity.getPlayerFromBackpack();
        }
        
        if ((MapCode == 1) || (MapCode == 3)){
        	/* MapCode : 1 = MainMenu , 3 = All Map Except Home */
        	savebtn.setEnabled(false);
        	savebtn.setTextColor(Color.GRAY);
        	loadbtn.setTextColor(Color.GREEN);
        	backbtn.setTextColor(Color.GREEN);
        	
        } else if (MapCode == 2){
        	/* MapCode : 2 = Home */
        	savebtn.setEnabled(true);
        	savebtn.setTextColor(Color.GREEN);
        	loadbtn.setTextColor(Color.GREEN);
        	backbtn.setTextColor(Color.GREEN);        	
        }
        
        ReDrawList();
	}
	
	public void saveGame(View v) {
		/* Give an alert if file Exists */
    	//buat file
		SharedPreferences sharedPref = this.getSharedPreferences("OkepPreference", Context.MODE_PRIVATE);
		
		//read
		String value = sharedPref.getString(P.GetFileName().toString(),"");
		
		SharedPreferences.Editor editor = sharedPref.edit();
		
		if (!value.equals("IsExist")){
		//write
		editor.putString(P.GetFileName(), "IsExist");
		editor.commit(); // save change    		
		
		/* Add a Player name */
		editor.putString(P.GetFileName() + "name", P.GetName());
		editor.commit(); // save change
		}
		
		/* Add a timeStamp */
		Time now = new Time();
		now.setToNow();
		editor.putString(P.GetFileName() + "time", now.toString());
		editor.commit(); // save change
		/* Save player */
          MainGameActivity.getPlayerFromBackpack().SavePlayer(getBaseContext());
          ReDrawList();
	} 
	
	
	public void loadGame(View v) {

		if (P.GetFileName() != null){
		   /* At this point, go to next panel (Send data to next panel) */
		    P.SetFileName(choice);
		    P.LoadPlayer(this);
		
		    Intent i = new Intent(this,MainGameActivity.class);
			LinkedHashMap<String, Object> obj = new LinkedHashMap<String, Object>();
			obj.put("hashmap", P);
			
			Bundle b = new Bundle();
			b.putSerializable("bundleobject", obj);
			i.putExtras(b);
			i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			System.gc();
			this.startActivity(i);
			
			if (MenuActivity.mediaPlayer != null){
				MenuActivity.mediaPlayer.stop();
				MenuActivity.mediaPlayer = null;
			}
			
			this.finish();
		} else {
			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
			// set title
			alertDialogBuilder.setTitle("Error!");
			alertDialogBuilder.setMessage("No File Selected :v");
			alertDialogBuilder.setCancelable(false);
			alertDialogBuilder.setPositiveButton("Close",new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog,int id) {
				}
			});
		}
	} 
	
	
	public void backtoGame(View v) {
    	super.finish();
	} 
	
	protected void ReDrawList(){
		/* showing listview to screen */
		final ListView view = (ListView) this.findViewById(R.id.loadList);
		view.setVisibility(View.VISIBLE);
		
		int counter = 0;

		ArrayList<String> loadList = new ArrayList<String>();
		
		SharedPreferences sharedPref = this.getSharedPreferences("OkepPreference", Context.MODE_PRIVATE);
		Map<String, ?> map = sharedPref.getAll();
		for (Map.Entry<String, ?> entry:map.entrySet()) {
            if (entry.getValue().toString().equals("IsExist")){
        		String PlayerName = sharedPref.getString(entry.getKey().toString() + "name" ,"");
        		String SaveTime = sharedPref.getString(entry.getKey().toString() + "time" ,"");
        		
        		String Tahun = SaveTime.charAt(0) + "" + SaveTime.charAt(1) + "" + SaveTime.charAt(2) + "" + SaveTime.charAt(3) + "";
        		String Bulan = SaveTime.charAt(4) + "" + SaveTime.charAt(5) + "";
        		String Hari = SaveTime.charAt(6) + "" + SaveTime.charAt(7) + "";
        		
        		String Jam = SaveTime.charAt(9) + "" + SaveTime.charAt(10) + "";
        		String Menit = SaveTime.charAt(11) + "" + SaveTime.charAt(12) + "";
        		String Detik = SaveTime.charAt(13) + "" + SaveTime.charAt(14) + "";
        		
        		counter ++;
        		loadList.add("Data Number " + counter 
        				     + " \nName : " + PlayerName 
        				     + " \nSave Time : " + Bulan + "/" + Hari + "/" + Tahun + " " + Jam + " : " + Menit + " : " + Detik);
            }
		}
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,R.layout.list_text,R.id.list_content, loadList);
		view.setAdapter(arrayAdapter);
		
		view.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			private View tempView;
			
		    @Override
		    public void onItemClick(AdapterView<?> parent, final View views,
		        int position, long id) {
		      if (tempView != null) tempView.setBackgroundColor(Color.TRANSPARENT);
		      tempView = views;
		      String chosenItem = (String) parent.getItemAtPosition(position);
	          String[] parts = chosenItem.split(" ");
	          if (P == null)
				try {
					P = new Player(thisAct);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	          choice = parts[5];
	          
	          views.setBackgroundColor(Color.BLUE);
	          
		    }

		  });
		
	}
	
	
	
}
