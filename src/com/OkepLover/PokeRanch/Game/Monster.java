/*
 * [IF2032] Pemrograman Beroirentasi Obyek
 * Mobile PokeRanch
 */
package com.OkepLover.PokeRanch.Game;



import java.io.Serializable;
import java.util.*;
import android.util.Log;

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


public class Monster implements Serializable {

	
	protected Integer id; //id dari Monster
	protected String name; //Nama dari Monster (Dapat di kustomisasi)
	protected Integer level; //Level yang dimiliki Monster
	protected Species species; //Jenis Species dari Monster
	protected Element element; //Elemen yang dimiliki Monster
	protected ArrayList<Skill> skillList; //List-list skill yang dimiliki
	protected Status status; //Status / Efek yang dikenakan pada Monster
	protected Integer currentHP; //Besarnya HP Monster saat ini
	protected Integer maxHP; //Besarnya maxHP yang dimiliki Monster
	protected Integer currentMP; //Besarnya MP Monster saat ini
	protected Integer maxMP; //Besarnya maxMP yang dimiliki Monster
	protected Integer accuracy; //Besarnya akurasi dari Monster
	protected Integer power; //Besarnya kekuatan serangan dari Monster
	protected Integer defend; //Besarnya kemampuan bertahan dari Monster
	protected boolean isLearned[]; //Skill apa saja yang bisa dipelajari monster
	protected Integer canUse[]; //Skill yang dapat digunakan oleh monster
	
	public Monster(){
		id = 0;
		name = "Unknown";
		level = 0;
		species = null;
		element = Element.none;
        /* Skill List */
		status = Status.normal;
		currentHP = 0;
		maxHP = 0;
		currentMP = 0;
		maxMP = 0;
		accuracy = 0;
		power = 0;
		defend = 0;
		skillList = new ArrayList<Skill>();
		isLearned = new boolean [13];
		for (int i = 0; i < 13; i++)
			isLearned[i] = false;
		/* CanUse */
	}
	
	public Monster(Integer _id,String _name, Integer _lv,Species _species,Element _el, ArrayList<Skill> _skillList){
		id = _id;
		name = _name;
		level = _lv;
		species = _species;
		element = _el;
		status = Status.normal;
		isLearned = new boolean [13];
		currentHP = maxHP = computeBaseHPMP(0,species.getHpGrowth(),level);
		currentMP = maxMP = computeBaseHPMP(1,species.getMpGrowth(),level);
		power = computeBaseStat(2,species.getPowGrowth(),level);
		accuracy = computeBaseStat(3,species.getAccGrowth(),level);
		defend = accuracy = computeBaseStat(4,species.getDefGrowth(),level);
		skillList = new ArrayList<Skill>();//_ListSkill;
		for (int i = 0;i<_skillList.size();i++){
			if (level >= _skillList.get(i).getMinLv()){
				if (element.equals(_skillList.get(i).getElement()) || (_skillList.get(i).getElement().equals(Element.none))){
					_skillList.get(i).setEnabled(true);
				}
			} 
			skillList.add(new Skill(_skillList.get(i)));
		}
		
		//simpen ke isLearned
		for (int i = 0 ; i < 13 ; i++){
			if (skillList.get(i).isEnabled()){
				isLearned[i] = true;
			} else {
				isLearned[i] = false;
			}
		}
	}
	
	public Monster (Monster m){
		this.id = m.id;
		this.name = m.name;
		this.level = m.level;
		this.species = m.species;
		this.element = m.element;
		this.status = m.status;
		this.skillList = m.skillList;
		this.currentHP = m.currentHP;
		this.maxHP = m.maxHP;
		this.currentMP = m.currentMP;
		this.maxMP = m.maxMP;
		this.accuracy = m.accuracy;
		this.power = m.power;
		this.defend = m.defend;
		this.isLearned = new boolean [13];
		for (int i = 0; i < 13 ; i++){
			this.isLearned[i] = m.isLearned[i];
		}
	}
	
	public Integer getCurrentHP(){
		return currentHP;
	}
	
	public Integer getCurrentMP(){
		return currentMP;
	} 
	
	public String getName(){
		return name;
	}
	public void setName(String x){
	//mengeset nama monster
		name = x;
	}

	public Integer getID(){
		return id;
	}
	public void setID(Integer x){
	// mengeset ID monster
		id = x;
	}

	public Integer getHP(){
		return currentHP;
	} 
	public void setHP(Integer _hp){
		if (_hp <= 9999) currentHP = _hp;
	}

	public Integer getMP(){
		return currentMP;
	}
	public void setMP(Integer _mp){
		if (_mp <= 9999) currentMP = _mp;
	} 

	public Element getElement(){
		return element;
	}
	public void setElement(Element _element){
		element=_element;
	}
	
	public Integer getLevel(){
		return level;
	}
	public void setLevel(Integer x){
	// mengeset level monster
		if (x<=99) level = x;
	}

	public Integer getMaxHP(){
		return maxHP;
	}
	public void setMaxHP(Integer HP){
		if (HP <= 9999) maxHP = HP;
	}

	public Integer getMaxMP(){
		return maxMP;
	}
	public void setMaxMP(Integer MP){
		if (MP <= 9999) maxMP = MP;
	}

	public Integer getPower(){
		return power;
	}
	public void setPower(Integer Pow){
		if (Pow <= 999) power = Pow;
	}

	public Integer getAcc(){
		return accuracy;
	}
	public void setAcc(Integer Acc){
		if (Acc <= 999) accuracy = Acc;
	}

	public Integer getDef(){
		return defend;
	}
	public void setDef(Integer Def){
		if (Def <= 999) defend = Def;
	}

	public Status getStatus(){
		return status;
	}
	public void setStatus(Status s){
	//mengeset status monster
		status = s;
	}

	public ArrayList<Skill> getListSkill(){
	//mengembalikan list skill monster
		return skillList;
	}
	public void setListSkill(ArrayList<Skill> _skill){
	// mengeset list skill monster
		skillList = _skill;
	}
	
	public boolean getIsLearned(Integer x){
		return isLearned[x];
	}
	
	public void setIsLearned(boolean b, Integer x){
		isLearned[x] = b;
	}
	
	public Species getSpecies(){
		return species;
	}
	public void setSpecies(Species s){
		species = s;
	}
	
	public void ShowStatMonster(){
		//cout << name << "'s HP = " << currentHP << "/" << maxHP << endl ;
		//cout << name << "'s MP = " << currentMP << "/" << maxMP << endl ;
	}
	
	public boolean isDead(){
		return ((status == Status.dead)||(currentHP<=0));
	}
	
	/*public Integer getlifeSpan(){} // mengembalikan lifeSpan monster
	public void setlifeSpan(Integer i){} // mengeset lifeSpan monster
	public Integer getcurrentExp(){} // mengembalikan current experience monster
	public void setcurrentExp(Integer i){} // mengeset current experience monster*/

	public void takeDamage(Integer dmg){
		currentHP -= dmg;
		if (currentHP <= 0){
			currentHP = 0;
			status = Status.dead;
		}
	}

		//growth rate tipe A, buat stat yg lebih unggul
	public int growthA(int lv){
		return (int) (Math.log((double) 3*lv*lv) + Utilities.rand(0,(int) (0.1*lv)));
	}

	//growth rate tipe B, buat stat yg biasa2 aja
	public int growthB(int lv){
		return (int) (Math.log((double) lv*lv) + Utilities.rand(0,(int) (0.05*lv)));
	}


	//growth rate tipe c, buat stat yg gak terlalu cocok ama speciesnya
	public int growthC(int lv){
		return (int) (Math.log((double)lv) + Utilities.rand(0,(int) (0.1*lv)));
	}
	
	//prosedur yg dipanggil ketika menaikkan hp atau mp
	public int increaseHPMP(Integer growth,Integer stat, Integer lv){
		switch (growth){
			case 1: stat += growthA(lv)*10;break;
			case 2: stat += growthB(lv)*10;break;
			case 3: stat += growthC(lv)*10;break;
		}
		//max hp/mp -> 9999
		if (stat > 9999) stat = 9999;
		return stat;
	}

	//prosedur yg dipanggil ketika menaikkan power, acc , atau def
	public int increaseStat(Integer growth,Integer stat, Integer lv){
		switch (growth){
			case 1: stat += growthA(lv);break;
			case 2: stat += growthB(lv);break;
			case 3: stat += growthC(lv);break;
		}
		//max pow/acc/def -> 999
		if (stat > 999) stat = 999;
		return stat;
	}

	public int computeBaseHPMP(Integer stat,Integer growth,Integer lv){ //stat berisi 0 untuk HP, 1 untuk MP,
		int sum = species.getBaseHP();
		if (stat == 1) sum = species.getBaseMP();
		for (Integer i = 2; i <= lv ; ++i){
			sum  = increaseHPMP(growth,sum,i);
			if (stat == 1)
				Log.d("STAT HP lv"+i.toString(),String.valueOf(sum));
		}
		return sum;
	}

	public int computeBaseStat(Integer stat,Integer growth, Integer lv){ //stat: 2=power,3=accuracy,4=defend
		int sum = species.getBasePower();
		if (stat == 3) sum = species.getBaseAccuracy();
		if (stat == 4) sum = species.getBaseDefend();
		for (Integer i = 2; i <= lv ; ++i){
			sum = increaseStat(growth,sum,i);
		}
		return sum;
	}

	
}	