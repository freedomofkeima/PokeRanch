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
/****************************************************************************
 * Monster_Liar.h
 *
 * IF 2032 Pemrograman Berorientasi Objek
 *
 *
 * Header File Monster_Liar
 ***************************************************************************/
import java.io.Serializable;
import java.util.*;

class Monster_Liar extends Monster implements Serializable {
	private Integer bonusExp; //Besarnya bonus EXP yang dapat diberikan Monster
	private Integer bonusMoney; //Besarnya bonus Uang yang dapat diberikan Monster
	
	//Konstruktor Monster_Liar
	public Monster_Liar(){}
	public Monster_Liar(Integer _id,String _name, Integer _lv,Species _species,Element _el,Integer _exp,Integer _money, ArrayList<Skill> _skillList){
		super(_id,_name,_lv,_species,_el, _skillList);
		bonusExp = _exp;
		bonusMoney = _money;
	}
	public Monster_Liar (Monster_Liar m){
		super(m);
		this.bonusExp = m.bonusExp;
		this.bonusMoney = m.bonusMoney;
	}
	
	public Integer getExpReward(){
		return bonusExp;
	}
	public void setExpReward(Integer _exp){
		bonusExp = _exp;
	}

	public Integer getMoneyReward(){
		return bonusMoney;
	}
	public void setMoneyReward(Integer _money){
		bonusMoney = _money;
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
	
}
