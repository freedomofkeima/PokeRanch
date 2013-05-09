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
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.app.Activity;
import android.util.Log;

public class Species implements Serializable{
	private Integer id; //id dari Species
	private String name; //Nama Species
	private Integer levelToEvo; //Variabel penyimpan level untuk berevolusi
	private Integer evolutionID; //ID pokemon hasil Evolusi
	private Integer baseHP;	//tingkat ketahanan fisik spesies
	private Integer hpGrowth; //tingkat pertumubhan status hp
	private Integer baseMP;	//tingkat ketahan psikis monster
	private Integer mpGrowth; //tingkat pertumubhan status mp
	private Integer baseAccuracy; //Variabel penyimpan dasar Akurasi dari Species
	private Integer accGrowth; //tingkat pertumubhan status akurasi
	private Integer basePower; //Variabel penyimpan dasar Power dari Species
	private Integer powGrowth; //tingkat pertumubhan status power
	private Integer baseDefend; //Variabel penyimpan dasar Defend dari Species
	private Integer defGrowth; //tingkat pertumubhan status defedn
	private Integer lifeSpan; //waktu hidup spesies
	private boolean canLearn[]; //daftar id skill yang bisa dipelajari species monster
	private String  description; //Mendiskripsikan monster
	private boolean isSiang; //isSiang bernilai true jika siang hari
	
	public Species(Integer id, String name, Integer lvToEvo, Integer evoID, Integer baseHP, Integer hpGrowth, Integer baseMP, Integer mpGrowth,
			Integer baseAcc, Integer accGrowth, Integer basePow, Integer powGrowth, Integer baseDef, Integer defGrowth, Integer lifeSpan){
		this.name = name;
		this.id = id;
		this.levelToEvo = lvToEvo;
		this.baseHP = baseHP;
		this.hpGrowth = hpGrowth;
		this.baseMP = baseMP;
		this.mpGrowth = mpGrowth;
		this.baseAccuracy = baseAcc;
		this.accGrowth = accGrowth;
		this.basePower = basePow;
		this.powGrowth = powGrowth;
		this.baseDefend = baseDef;
		this.defGrowth = defGrowth;
		this.lifeSpan = lifeSpan;
	}
	
	public Species() {
		this.name = "Unknown";
		this.id = 0;
		this.levelToEvo = 0;
		this.baseHP = 0;
		this.hpGrowth = 0;
		this.baseMP = 0;
		this.mpGrowth = 0;
		this.baseAccuracy = 0;
		this.accGrowth = 0;
		this.basePower = 0;
		this.powGrowth = 0;
		this.baseDefend = 0;
		this.defGrowth = 0;
		this.lifeSpan = 0;
		this.canLearn = new boolean [11];
		for (int i = 0; i < 11; i++)
			 canLearn[i] = false;
		this.description = "Unknown";
		this.isSiang = true;
	}

	public Species(Species s) {
		this.name = s.name;
		this.id = s.id;
		this.levelToEvo = s.levelToEvo;
		this.baseHP = s.baseHP;
		this.hpGrowth = s.hpGrowth;
		this.baseMP = s.baseMP;
		this.mpGrowth = s.mpGrowth;
		this.baseAccuracy = s.baseAccuracy;
		this.accGrowth = s.accGrowth;
		this.basePower = s.basePower;
		this.powGrowth = s.powGrowth;
		this.baseDefend = s.baseDefend;
		this.defGrowth = s.defGrowth;
		this.lifeSpan = s.lifeSpan;
		this.canLearn = new boolean [11];
		for (int i = 0; i < 11; i++)
			 canLearn[i] = s.canLearn[i];
		this.description = s.description;
		this.isSiang = s.isSiang;
	}
	
    /* Getter and Setter */
	public Integer getId(){
		return id;
	}
	
	public void setId(Integer _id){
		id = _id;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String _name){
		name = _name;
	}
	
	public Integer getLevelToEvo(){
		return levelToEvo;
	}
	
	public void setLevelToEvo(Integer _levelToEvo){
		levelToEvo = _levelToEvo;
	}
	
	public Integer getEvolutionID(){
		return evolutionID;
	}
	
	public void setEvolutionID(Integer _evolutionID){
		evolutionID = _evolutionID;
	}
	
	public Integer getBaseHP(){
		return baseHP;
	}
	
	public void setBaseHP(Integer _baseHP){
		baseHP = _baseHP;
	}
	
	public Integer getHpGrowth(){
		return hpGrowth;
	}
	
	public void setHPGrowth(Integer _hpGrowth){
		hpGrowth = _hpGrowth;
	}

	public Integer getBaseMP(){
		return baseMP;
	}
	
	public void setBaseMP(Integer _baseMP){
		baseMP = _baseMP;
	}
	
	public Integer getMpGrowth(){
		return mpGrowth;
	}
	
	public void setMPGrowth(Integer _mpGrowth){
		mpGrowth = _mpGrowth;
	}
	
	public Integer getBaseAccuracy(){
		return baseAccuracy;
	}
	
    public void setBaseAccuracy(Integer _baseAccuracy){
    	baseAccuracy = _baseAccuracy;
    }
	
	public Integer getAccGrowth(){
		return accGrowth;
	}
	
	public void setAccGrowth(Integer _accGrowth){
		accGrowth = _accGrowth;
	}

	public Integer getBasePower(){
		return basePower;
	}
	
	public void setBasePower(Integer _basePower){
		basePower = _basePower;
	}
	
	public Integer getPowGrowth(){
		return powGrowth;
	}
	
	public void setPowGrowth(Integer _powGrowth){
		powGrowth = _powGrowth;
	}
	
	public Integer getBaseDefend(){
		return baseDefend;
	}
	
	public void setBaseDefend(Integer _baseDefend){
		baseDefend = _baseDefend;
	}
	
	public Integer getDefGrowth(){
		return defGrowth;
	}
	
	public void setDefGrowth(Integer _defGrowth){
		defGrowth = _defGrowth;
	}

	public Integer getLifeSpan(){
		return lifeSpan;
	}
	
	public void setLifeSpan(Integer _lifeSpan){
		lifeSpan = _lifeSpan;
	}
	
	public boolean getCanLearn(Integer x){
		return canLearn[x];
	}
	
	public void setCanLearn(boolean b, Integer x){
		canLearn[x] = b;
	}

	public String getDescription(){
		return description;
	}
	
	public void setDescription(String _description){
		description = _description;
	}
	
	public boolean getisSiang(){
		return isSiang;
	}
	
	public void setisSiang(boolean _isSiang){
		isSiang = _isSiang;
	}
	

	  public static ArrayList<Species> LoadSpecies(Activity t) throws IOException{
		  /* { I.S. : List Monster kosong } */
		  /* { F.S. : Melakukan proses load dari file eksternal ke List Monster } */
			  /* Declaration */
			  ArrayList<Species> temp = new ArrayList<Species>();
			  Species tempSpecies = new Species();
			  
			  try {
				    String XMLName = new String();
			        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			        factory.setValidating(false);
			        XmlPullParser myxml = factory.newPullParser();
			        
			        InputStream raw = t.getAssets().open("monster.xml");
			        myxml.setInput(raw, null);
			        
			        int eventType = myxml.getEventType();
			        while(eventType != XmlPullParser.END_DOCUMENT) {
			            if(eventType == XmlPullParser.START_DOCUMENT) {
			                Log.d("Debug", "In start document");
			            }
			            else if(eventType == XmlPullParser.START_TAG) {
			            	XMLName = myxml.getName();
			            }
			            else if(eventType == XmlPullParser.END_TAG) {
		                   /* after each end tag */
			            	if (XMLName.equals("isSiang")){
			            		 temp.add(new Species(tempSpecies));
			            	}
			            }
			            else if(eventType == XmlPullParser.TEXT) {
			            	if (XMLName.equals("id")){
			            		tempSpecies.setId(Integer.valueOf(myxml.getText()));
			            	} else if (XMLName.equals("name")){
			            		tempSpecies.setName(myxml.getText());
			            	} else if (XMLName.equals("levelToEvo")){
			            		tempSpecies.setLevelToEvo(Integer.valueOf(myxml.getText()));
			            	} else if (XMLName.equals("EvolutionID")){
			            		tempSpecies.setEvolutionID(Integer.valueOf(myxml.getText()));
			            	} else if (XMLName.equals("baseHP")){
			            		tempSpecies.setBaseHP(Integer.valueOf(myxml.getText()));
			            	} else if (XMLName.equals("hpGrowth")){
			            		tempSpecies.setHPGrowth(Integer.valueOf(myxml.getText()));
			            	} else if (XMLName.equals("baseMP")){
			            		tempSpecies.setBaseMP(Integer.valueOf(myxml.getText()));
			            	} else if (XMLName.equals("mpGrowth")){
			            		tempSpecies.setMPGrowth(Integer.valueOf(myxml.getText()));
			            	} else if (XMLName.equals("baseAcc")){
			            		tempSpecies.setBaseAccuracy(Integer.valueOf(myxml.getText()));
			            	} else if (XMLName.equals("accGrowth")){
			            		tempSpecies.setAccGrowth(Integer.valueOf(myxml.getText()));
			            	} else if (XMLName.equals("basePow")){
			            		tempSpecies.setBasePower(Integer.valueOf(myxml.getText()));
			            	} else if (XMLName.equals("powGrowth")){
			            		tempSpecies.setPowGrowth(Integer.valueOf(myxml.getText()));
			            	} else if (XMLName.equals("baseDef")){
			            		tempSpecies.setBaseDefend(Integer.valueOf(myxml.getText()));
			            	} else if (XMLName.equals("defGrowth")){
			            		tempSpecies.setDefGrowth(Integer.valueOf(myxml.getText()));
			            	} else if (XMLName.equals("lifespan")){
			            		tempSpecies.setLifeSpan(Integer.valueOf(myxml.getText()));
			            	} else if (XMLName.equals("description")){
			            		tempSpecies.setDescription(myxml.getText());
			            	} else if (XMLName.equals("canLearned")){
			            		tempSpecies.setCanLearn(true, Integer.valueOf(myxml.getText()));
			            	}else
			            	if (XMLName.equals("isSiang")) {
			            		if ( myxml.getText().equals("true") )
			            			 tempSpecies.setisSiang(true);
			            		else tempSpecies.setisSiang(false);
			            		}
			            }
			            eventType = myxml.next();
			        }
			    } catch (XmlPullParserException e) {}
			  
			  return temp;
		 }


@Override
public String toString() {
	
    return "ID: " + this.id 
    		+ "\nName: " + this.name;
}

}
