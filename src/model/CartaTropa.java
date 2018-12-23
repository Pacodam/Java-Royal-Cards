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
public class CartaTropa extends Carta {
    
    private static int numberCard = 1;
    private String cardName;
    private int specialLevelPoints;
    private final String specialPower = "Strength. In ATTACK: attack = attack X strength. Special defense: NO";
   
    
    public CartaTropa(int attack, int defense, int costElixir, int lifeLevel, int specialLevelPoints){
        super(attack, defense, costElixir, lifeLevel);
        this.specialLevelPoints = specialLevelPoints;
        this.cardName = "Tropa" + numberCard;
        numberCard++;
    }


    @Override
    public String getCardName() {
        return cardName;
    }


    @Override
    public int getSpecialLevelPoints() {
        return specialLevelPoints;
    }

   
    @Override
    public  String getCARDTYPE() {
        return "Tropa";
    }

    @Override
    public String getSpecialPower() {
        return specialPower;
    }
    
     @Override
    public int totalAttackPoints(){
        return super.getAttack() * specialLevelPoints;
    }
    
    
    @Override
    public int totalDefensePoints(){
        return super.getDefense();
    }
    
    
    

  
    
    
    
  
    
    
    
   
}
