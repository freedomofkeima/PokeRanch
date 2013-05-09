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


enum Effect{ //Enumerasi dari Efek Skill
    noneE,
	healing,
	damaging,
	paralyzing,
	hypnotize,
	catching,
	statPermanentIncreasing,
	buffing,
	debuffing,
	egging,
	ressurecting,
	healingMP,
	lightning,
	repellant
}
 
 
class Skill implements Serializable{

	private Integer id; //id dari Skill
	private String name; //Variabel penyimpan nama Skill
	private Element element; //Variabel penyimpan jenis Element dari Skill
	//private SkType attack; //Besarnya dan efek dari serangan dari sebuah Skill
	private Effect effect;
	private Integer code;
	private Integer cost; //Variabel penyimpan besarnya Mana PoIntegers yang dibutuhkan
	private String description; //Variabel penyimpan deskripsi dari sebuah skill
	private Integer MinLv; //Variabel penyimpan nilai level terendah monster untuk memiliki suatu skill
	private Integer lvToEvo; //level minimum monster untuk dapat mengevolusi skill
	private Integer evoID; //id dari skill hasil evolusi
	private boolean skillEnabled;
	//Konstuktor Skill
	public Skill(){}

	public Skill(Integer _id, String _name, Element _elmt, Effect _effect,Integer _code, Integer _cost, String _desc, Integer _minLv, Integer _lv, Integer _evoID)
	{
		id = _id;
		name = _name;
		element = _elmt;
		effect = _effect;
		code = _code;
		cost = _cost;
		description = _desc;
		MinLv = _minLv;
		lvToEvo = _lv;
		evoID = _evoID;
		skillEnabled = false;
	}
	//Destruktor Skill
	

	//cctor
	public Skill (Skill ski){
		id = ski.id;
		name = ski.name;
		element = ski.element;
		effect = ski.effect;
		code = ski.code;
		cost = ski.cost;
		description = ski.description;
		MinLv = ski.MinLv;
		lvToEvo = ski.lvToEvo;
		evoID = ski.evoID;
		skillEnabled = ski.skillEnabled;
	}
	/*
	//overload operator=
	public Skill operator= (Skill ski){
		id = ski.id;
		name = ski.name;
		element = ski.element;
		effect = ski.effect;
		code = ski.code;
		cost = ski.cost;
		description = ski.description;
		MinLv = ski.MinLv;
		return *this;
	}
	*/
	public Integer getID(){return id;} //mengembalikan id skill
	public String getName(){return name;}//mengembalikan nama skill
	public Integer getValue(){return code;} //mengembalikan tingkat keefektifan skill,kalo damaging skill berarti mengembalikan damage dari skill
	public Effect getEffect(){return effect;}//mengembalikan jenis efek yang dihasilkan skill
	public Integer getCost(){return cost;} //mengembalikan biaya yang diperlukan untuk meng-cast skill
	public Element getElement(){return element;}//mengembalikan elemen skill
	public Integer getMinLv(){return MinLv;}


	public String UseSkill(Monster m1,Monster m){
		int roll;
		// (m1.getName() + " uses " + getName() + "!");
		if (getEffect() == Effect.damaging){
			int totalDamage = Utilities.rand(3*m1.getPower() - 2*m.getDef() + getValue() , 3*m1.getPower() - 2*m.getDef() + 2*getValue());
			//cek keberunggulan elemen skill terhadap musuh
			if (totalDamage < 0 ) totalDamage = 0;
			if (getElement() == Element.fire){
				if (m.getElement() == Element.grass){
					totalDamage *= 2;
					//return ("It's super effective!");
				}
				 if (m.getElement() == Element.water){
					totalDamage = (int) (totalDamage*0.5);
					//return ("It's not very effective");
				}
			} else 
			if (getElement() == Element.water){
				if (m.getElement() == Element.fire){
					totalDamage *= 2;
					//return ("It's super effective!");
				}
			    if (m.getElement() == Element.grass){
					totalDamage = (int) (totalDamage*0.5);
					//return ("It's not very effective");
				}
			} else 
			if (getElement() == Element.grass) {
				if (m.getElement() == Element.water){
					totalDamage *= 2;
					//return ("It's super effective!");
				}
				if (m.getElement() == Element.fire){
					totalDamage = (int) (totalDamage*0.5);
					//return ("It's not very effective");
	
				}
			}
		
			m.setHP(m.getHP()-totalDamage);
			//return (m.getName() + " took " + totalDamage + " damage!");
		}
		if (getEffect() == Effect.healing){
			m1.setHP(m1.getHP()-getValue());
			//return (m1.getName() + " is recovered by " + -1*getValue() + " HP!");
			
		}
		if (getEffect() == Effect.paralyzing){
			roll = Utilities.rand(1,2);
			if (roll == 1){
				m.setStatus(Status.paralyzed);
				//return (m.getName() + " is paralyzed!");
				
			} else {
				//return (m1.getName() + " missed!");
			}
		}
		if (getEffect() == Effect.hypnotize){
			roll = Utilities.rand(1,3);
			if (roll == 1){
				m.setStatus(Status.slept);
				//return (m.getName() + " fell asleep!");
				
			} else {
				//return (m1.getName() + " missed!");
			}
		}
		if (getEffect() == Effect.buffing){
			//return ("buffed");
		}
		if (getEffect() == Effect.debuffing){
			//return ("debuffed");
		}		
		m1.setMP(m1.getMP()-getCost());
		return (m1.getName() + " uses " + getName() + "!");
	}
	
	public static ArrayList<Skill> loadSkill(Activity t) throws IOException{
		  /* { I.S. : List Item kosong } */
		  /* { F.S. : Melakukan proses load dari file eksternal ke List Item } */
		int id = 0;
		String name = "";
		String element = "";
		String effect = "";
		int code = 0;
		int cost = 0;
		String description = "";
		int minlv = 0;
		int lvToEvo = 0;
		int evoID = 0;
		ArrayList<Skill> ListSkill = new ArrayList<Skill>();
		Integer temp = new Integer(0);
			  try {
				  
			        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			        factory.setValidating(false);
			        XmlPullParser myxml = factory.newPullParser();
			        
			        InputStream raw = t.getAssets().open("skill.xml");
			        myxml.setInput(raw, null);
			        
			        int eventType = myxml.getEventType();
			        while(eventType != XmlPullParser.END_DOCUMENT) {
			        	while (true){
				            if(eventType == XmlPullParser.START_DOCUMENT) {
				            }
				            else if(eventType == XmlPullParser.START_TAG) {
				            	if (myxml.getName().equals("id")){
				            		temp = 1;
				            	} else if(myxml.getName().equals("name")){
				            		temp = 2;
				            	} else if(myxml.getName().equals("element")){
				            		temp = 3;
				            	} else if(myxml.getName().equals("effect")){
				            		temp = 4;
				            	} else if(myxml.getName().equals("code")){
				            		temp = 5;
				            	} else if(myxml.getName().equals("cost")){
				            		temp = 6;
				            	} else if(myxml.getName().equals("description")){
				            		temp = 7;
				            	} else if (myxml.getName().equals("minlv")){
				            		temp = 8;
				            	} else if (myxml.getName().equals("lvtoevo")){
				            		temp = 9;
				            	} else if (myxml.getName().equals("evoid")){
				            		temp = 10;
				            	}
				            }
				            else if(eventType == XmlPullParser.END_TAG) {
				                if (myxml.getName().equals("tag")){
				                	//eventType = myxml.next();
				                	break;
				                }
				            }
				            else if(eventType == XmlPullParser.TEXT) {
				            	if (temp == 1){
				            		id = Integer.parseInt(myxml.getText());
				            	} else if(temp == 2){
				            		name = myxml.getText();
				            	} else if(temp == 3){
				            		element = myxml.getText();
				            	} else if(temp == 4){
				            		effect = myxml.getText();
				            	} else if(temp == 5){
				            		code = Integer.parseInt(myxml.getText());
				            	} else if(temp == 6){
				            		cost = Integer.parseInt(myxml.getText());
				            	} else if(temp == 7){
				            		description = myxml.getText();
				            	} else if(temp == 8){
				            		minlv = Integer.parseInt(myxml.getText());
				            	} else if(temp == 9){
				            		lvToEvo = Integer.parseInt(myxml.getText());
				            	} else if(temp == 10){
				            		evoID = Integer.parseInt(myxml.getText());
				            	}
				            }
				            eventType = myxml.next();
			        	}
				        Skill S = new Skill(id, name, Element.valueOf(element), Effect.valueOf(effect), code, cost, description, minlv, lvToEvo, evoID);
				        ListSkill.add(new Skill (S));
				        for (int i = 0;i<ListSkill.size();i++)
				        	Log.d("Skill Name",ListSkill.get(i).getName());
				        eventType = myxml.next();
			        }
			    } catch (XmlPullParserException e) {}
			  	catch (IllegalArgumentException e){e.getMessage();}
		        Log.d("Skill Size",((Integer)ListSkill.size()).toString());
			  return ListSkill;
		 }

	public void setEnabled(boolean set){
		skillEnabled = set;
	}
	
	public boolean isEnabled(){
		return skillEnabled;
	}
	
	public Integer getlvToEvo(){
		return lvToEvo;
	}
	
	public void setLvToEvo(Integer lv){
		lvToEvo = lv;
	}
	
	public Integer getEvoID(){
		return evoID;
	}
	
	public void setEvoID(Integer id){
		evoID = id;
	}
	
	public void setMinLv(Integer lv){
		MinLv = lv;
	}
	
	public void setID(Integer id){
		this.id = id;
	}
}