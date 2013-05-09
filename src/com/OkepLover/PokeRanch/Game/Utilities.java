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
import java.util.Random;

enum Status { normal, slept, paralyzed, dead}
enum Element {fire,water,grass,none}
enum Command{ESCAPE, ITEM, CHANGE ,	SKILL ,	DEFEAT,	NOMOVE,	EITEM}


class Utilities implements Serializable{
	public static int rand(int a, int b){
		if (a<b) return ((new Random()).nextInt(b-a+1) + a);
		else return 0;
	}
	//growth rate tipe A, buat stat yg lebih unggul
	public static int growthA(Integer lv){
		return (int) (Math.log((double) 3*lv*lv) + rand(0,(int) (0.1*lv)));
	}

	//growth rate tipe B, buat stat yg biasa2 aja
	public static int growthB(Integer lv){
		return (int) (Math.log((double) lv*lv) + rand(0,(int) (0.05*lv)));
	}


	//growth rate tipe c, buat stat yg gak terlalu cocok ama speciesnya
	public static int growthC(Integer lv){
		return (int) (Math.log((double)lv) + rand(0,(int) (0.1*lv)));
	}
	
	//prosedur yg dipanggil pas ningkatin hp ato mp
	static int increaseHPMP(Integer growth,Integer stat, Integer lv){
		switch (growth){
			case 1: stat += growthA(lv)*10;break;
			case 2: stat += growthB(lv)*10;break;
			case 3: stat += growthC(lv)*10;break;
		}
		//max hp/mp -> 9999
		if (stat > 9999) stat = 9999;
		return stat;
	}

	//prosedur yg d panggil kalo mo naek power, acc , ato def
	static int increaseStat(Integer growth,Integer stat, Integer lv){
		switch (growth){
			case 1: stat += growthA(lv);break;
			case 2: stat += growthB(lv);break;
			case 3: stat += growthC(lv);break;
		}
		//max pow/acc/def -> 999
		if (stat > 999) stat = 999;
		return stat;
	}
	
	public static void main(String[] args){
		System.out.println("HP/MP growth A");
		Integer sum = 200;
		System.out.println("level " + 1 + " = " + sum);
		for (Integer i=2 ; i <= 99 ; ++i ){
			sum = increaseHPMP(1,sum,i);
			System.out.println("level " + i + " HP/MP = " + sum);
		}
	}

}