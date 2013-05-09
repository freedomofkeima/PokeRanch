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
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import android.util.Log;


public class Monster_Sendiri extends Monster implements Serializable {
	
	private int currentExp;
	private int lifeSpan;
	
	//konstruktor
	public Monster_Sendiri(int id, String name, Species species, int level, 
			Element element, int currentExp, int lifeSpan, ArrayList<Skill> _ListSkill){
		super(id,name,level,species,element, _ListSkill);
		this.lifeSpan = lifeSpan;
		this.currentExp = this.minExp(level);
	}
	
	public Monster_Sendiri(){
		currentExp = 0;
		lifeSpan = 0;
	}
	
	public Monster_Sendiri(Monster_Sendiri m){
		super(m);
		//this.name = m.name;
	    this.currentExp = m.currentExp;
	    this.lifeSpan = m.lifeSpan;
	}

	//getter Setter
	public int getCurrentExp(){
		return currentExp;
	}
	
	public int getlifeSpan(){
		return lifeSpan;
	}
	
	public void setCurrentExp(int newExp){
		currentExp = newExp;
	}
	
	public void setlifeSpan(int newLifeSpan){
		lifeSpan = newLifeSpan;
	}
	
	//method
	public Monster_Sendiri Combine (Monster_Sendiri M){
		if (M.getElement()!=Element.none){ //cek elemen monster 2
	  		switch(getElement()){ //sesuaikan dengan elemen monster 1
	  			case fire:
	  				switch(M.getElement()){
	  					case fire:	setElement(Element.fire);break;
	  					case water: setElement(Element.water);break;
	  					case grass: setElement(Element.fire);break;
	  				}
	  				break;
	  			case water:
	  				switch(M.getElement()){
	  					case fire:	setElement(Element.water);break;
	  					case water: setElement(Element.water);break;
	  					case grass: setElement(Element.grass);break;
	  				}
	  				break;
	  			case grass:
	  				switch(M.getElement()){
	  					case fire:	setElement(Element.fire);break;
	  					case water: setElement(Element.grass);break;
	  					case grass: setElement(Element.grass);break;
	  				}
	  				break;
				case none:
	  				switch(M.getElement()){
	  					case fire:	setElement(Element.fire);break;
	  					case water: setElement(Element.water);break;
	  					case grass: setElement(Element.grass);break;
	  				}
	  				break;
	  		}
	  	}
		id = rand(11,20);
		species = MainGameActivity.ListSpecies.get(id);
		level = rand(level, M.level + level);
		//update atribut
		currentHP = maxHP = computeBaseHPMP(0,species.getHpGrowth(),level);
		currentMP = maxMP = computeBaseHPMP(1,species.getMpGrowth(),level);
		power = computeBaseStat(2,species.getPowGrowth(),level);
		accuracy = computeBaseStat(3,species.getAccGrowth(),level);
		defend = accuracy = computeBaseStat(4,species.getDefGrowth(),level);

		return M;
	}
	
	int rand(int a, int b){
		if (a<b) return ((new Random()).nextInt(b-a+1) + a);
		else return 0;
	}
	
	public void victory(int getExp){
		for (int i = 0;i <skillList.size();i++)
			if (skillList.get(i).isEnabled())
				Log.d("BEFORE SKILL",skillList.get(i).getName());
		currentExp += getExp;
		//notifikasi kalau pemain menang
		if ((currentExp >= (minExp(level)))&&(level<99)){
			levelUp();
		}
		//cek skill yg dipelajari
		for (int i = 0;i <skillList.size();i++)
			if (skillList.get(i).isEnabled())
				Log.d("AFTER SKILL",skillList.get(i).getName());
	}
	
	public void levelUp(){
		//ada notifikasi atau alert gitu kalo naik level
		level ++;
		if (level >= MainGameActivity.ListSpecies.get(id).getLevelToEvo()){
			evolve();
		}
		//cek dapat skill baru
		newSkill();
		//increase attribut
		maxHP = increaseHPMP(MainGameActivity.ListSpecies.get(id).getHpGrowth(), maxHP, level);
		maxMP = increaseHPMP(MainGameActivity.ListSpecies.get(id).getMpGrowth(), maxMP, level);
		power = increaseStat(MainGameActivity.ListSpecies.get(id).getPowGrowth(),power,level);
		accuracy = increaseStat(MainGameActivity.ListSpecies.get(id).getAccGrowth(),accuracy,level);
		defend = increaseStat(MainGameActivity.ListSpecies.get(id).getDefGrowth(),defend,level);
		//refresh hp sama mp abis naik level
		currentHP = maxHP;
		currentMP = maxMP;
	}
	
	private void newSkill() {
		for (int i = 0;i<MainGameActivity.ListSkill.size();i++){
			if (level >= MainGameActivity.ListSkill.get(i).getMinLv()&&((element.equals(MainGameActivity.ListSkill.get(i).getElement()) || (MainGameActivity.ListSkill.get(i).getElement().equals(Element.none))))){
				//skillList.get(i).setEnabled(true);
				isLearned[i] = true;
			}
		}
		evolveSkill();
	}

	private void evolveSkill() {
		for (int i = 0; i<MainGameActivity.ListSkill.size();i++){
			if(isLearned[i]){
				if (MainGameActivity.ListSkill.get(i).getlvToEvo()<=level){
					isLearned[i] = false;
					//skillList.get(i).setMinLv(999);
					//skillList.get(i).setEnabled(false);
					//skillList.set(i, skillList.get(skillList.get(i).getEvoID()));
					//hapus skill evonya
					//skillList.get(skillList.get(i).getEvoID()).setID(-1);
					//skillList.get(skillList.get(i).getEvoID()).setEnabled(false);
				}
			}
		}	
	}

	public int minExp(int lv){
	//mengembalikan nilai experience minimum untuk naik ke level tsb
		int minExp = 0;
		if (lv == 0){
			return 0;
		} else {
			for (int i=1;i<=lv;i++){
				if (minExp < 100){
					minExp += 10 + i * i;
				} else {
					if (minExp < 500){
						minExp += i * i * 0.5;
					} else {
						minExp += i * i * 0.2;
					}
				}
			}
			return minExp;
		}
	}
	
	public void evolve(){
		//notifikasi kalau monster okep telah berevolusi
		id = MainGameActivity.ListSpecies.get(id).getEvolutionID();
	}
	
	
}