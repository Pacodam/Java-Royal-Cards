/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author alu2017454
 */
public abstract class Carta {
    
    private String cardName;
    private int attack;
    private int defense;
    private int costElixir;
    private int lifeLevel;
    private String specialPower;
    private int specialLevelPoints;
    private String CARDTYPE;
    private int totalAttackPoints;    //encapsulation!
    private int totalDefensePoints;   //encapsulation!
    
    
                 
    public Carta(int attack, int defense, int costElixir, int lifeLevel) {
        this.attack = attack;
        this.defense = defense;
        this.costElixir = costElixir;
        this.lifeLevel = lifeLevel;   
    }

    public String getCardName() {
        return cardName;
    }


    public int getAttack() {
        return attack;
    }


    public int getDefense() {
        return defense;
    }


    public int getCostElixir() {
        return costElixir;
    }


    public int getLifeLevel() {
        return lifeLevel;
    }

    public void setLifeLevel(int result) {
        this.lifeLevel -= result;
    }
    
    public void quitLifePoints(int points){
        this.lifeLevel -= points;
    }

    public String getSpecialPower() {
        return specialPower;
    }


    public int getSpecialLevelPoints() {
        return specialLevelPoints;
    }


    public String getCARDTYPE() {
        return CARDTYPE;
    }
    
     
    public int totalAttackPoints(){
        return 0;
    }
    
    
    public int totalDefensePoints(){
        return 0;
    }
     
    
    @Override
    public String toString() {
        return "Card = " + getCardName() + ", type = "+ getCARDTYPE()+ ", attack =" + attack +", defense = "+ defense+ ", cost Elixir = " + costElixir + ", life = " + lifeLevel+ 
                ", \nspecial power = " + getSpecialPower() + ", points of special power = " + getSpecialLevelPoints();
    }
    
    
   
    

    
    
  
    
}
